package com.uusafe.cas.handler;

import com.uusafe.cas.Bean.UserBean;
import com.uusafe.cas.CustomPasswordEncoder;
import com.uusafe.cas.DB.DBSource;
import com.uusafe.cas.authentication.CustomUsernamePasswordCredential;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.exceptions.AccountDisabledException;
import org.apereo.cas.authentication.exceptions.InvalidLoginLocationException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.annotation.Resource;
import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.FailedLoginException;
import javax.sql.DataSource;
import java.security.GeneralSecurityException;
import java.sql.*;
import java.util.Collections;

/**
 * @author Zengzhx
 * @date 2018/6/21 下午11:49
 */
public class CustomerHandler extends AbstractPreAndPostProcessingAuthenticationHandler {

    public CustomerHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    /**
     * 用于判断用户的Credential(换而言之，就是登录信息)
     * 就是有可能是，子站点的登录信息中不止有用户名密码等信息，还有部门信息的情况
     */
    @Override
    public boolean supports(Credential credential) {
        //判断传递过来的Credential 是否是自己能处理的类型
        return credential instanceof UsernamePasswordCredential;
    }

    private static ApplicationContext ac;
    /**
     * 用于授权处理
     */
    @Override
    protected HandlerResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
        CustomUsernamePasswordCredential usernamePasswordCredentia = (CustomUsernamePasswordCredential) credential;
        //获取传递过来的用户名和密码
        String company = usernamePasswordCredentia.getCompany();
        String username = usernamePasswordCredentia.getUsername();
        String password = usernamePasswordCredentia.getPassword();
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        String encodepassword = customPasswordEncoder.encode(password);
        ac = new ClassPathXmlApplicationContext("classpath*:profile/dev/application-dev.xml");
        DBSource dbSource = (DBSource) ac.getBean("dbSource");
        String sql = "SELECT * FROM t_user WHERE company=? AND username =? AND password=?";
        UserBean userBean = dbSource.getJdbcTemplate().query(sql, new Object[]{company, username, encodepassword}, new ResultSetExtractor<UserBean>() {
            @Override
            public UserBean extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next())
                {
                    UserBean userBean1 = new UserBean();
                    userBean1.setCompany(rs.getString("company"));
                    userBean1.setUsername(rs.getString("username"));
                    userBean1.setRealname(rs.getString("realname"));
                    userBean1.setEmail(rs.getString("email"));
                    userBean1.setLock(rs.getInt("lock")==1);
                    userBean1.setAccount(rs.getInt("account"));
                    userBean1.setDisable(rs.getInt("disable")==1);
                    userBean1.setInvali(rs.getInt("invali")==1);
                    return userBean1;
                }
                return null;
            }
        });
        if (userBean != null)
        {
            if (userBean.isInvali())
            {
                throw new InvalidLoginLocationException();
            }

            if (userBean.isLock())
            {
                throw new AccountLockedException();
            }

            if (userBean.isDisable())
            {
                throw new AccountDisabledException();
            }
            //允许登录，并且通过this.principalFactory.createPrincipal来返回用户属性
//          return createHandlerResult(credential, this.principalFactory.createPrincipal(username, Collections.emptyMap()), null);
            return createHandlerResult(credential, this.principalFactory.createPrincipal(userBean.getUsername(), userBean.getMapAttribues()), null);
        }

        throw new FailedLoginException();
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, company);
//            ps.setString(2, username);
//            ps.setString(3, encodepassword);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()) {
//                //当是admin用户的情况，直接就登录了，谁叫他是admin用户呢
//                if(username.startsWith("admin")) {
//                    //允许登录，并且通过this.principalFactory.createPrincipal来返回用户属性
//                    return createHandlerResult(credential, this.principalFactory.createPrincipal(username, Collections.emptyMap()), null);
//                }else if (username.startsWith("lock")) {
//                    //用户锁定
//                    throw new AccountLockedException();
//                } else if (username.startsWith("disable")) {
//                    //用户禁用
//                    throw new AccountDisabledException();
//                } else if (username.startsWith("invali")) {
//                    //禁止登录该工作站登录
//                    throw new InvalidLoginLocationException();
//                } else if (username.startsWith("passorwd")) {
//                    //密码错误
//                    throw new FailedLoginException();
//                } else if (username.startsWith("account")) {
//                    //账号错误
//                    throw new AccountLockedException();
//                }
//
//                return createHandlerResult(credential, this.principalFactory.createPrincipal(username, Collections.emptyMap()), null);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            if(conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

    }
}
