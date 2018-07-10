package com.uusafe.cas.service.serviceImp;

import com.uusafe.cas.Util.BeanUtil;
import com.uusafe.cas.Util.UserRowMapper;
import com.uusafe.cas.bean.User;
import com.uusafe.cas.service.UserServiceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Zengzhx
 * @date 2018/7/7 下午2:41
 */

@Component
public class UserServiceImp implements UserServiceDao{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    protected BeanUtil beanUtil;

    @Override
    public Collection<User> getAllUsers()
    {
        return jdbcTemplate.query("SELECT * FROM t_user", new UserRowMapper());
    }

    @Override
    public Collection<User> getAllUsers(int pageNo, int pageSize, String queryWhere)
    {
        List<User> list = jdbcTemplate.query("SELECT * FROM t_user "+queryWhere.toString()+" limit "+pageNo+","+pageSize, new UserRowMapper());
        return list;
    }

    @Override
    public int iTotalUser()
    {
        //获取用户数量
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_user", int.class);
    }

    @Override
    public int iTotalUser(String queryWhere)
    {
        return  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_user"+queryWhere, int.class);
    }

    @Override
    public User getUserInfo(String company, String username, String password)
    {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM T_User WHERE username = ? AND password = ?",
                    new String[]{username, password}, new UserRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserInfo(int id)
    {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM T_User WHERE id =?", new Object[]{id}, new UserRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean addUser(User... user) {
        return false;
    }



    @Override
    public boolean updateUser(User user, int updateBy)
    {
        Collection<String> exclud = new ArrayList<>();
        exclud.add(User.ID);
        exclud.add(User.COMPANY);
        exclud.add(User.USERNAME);
        exclud.add(User.LOCK);
        exclud.add(User.DISABLE);
        exclud.add(User.INVALI);
        exclud.add(User.CREATETIME);
        exclud.add(User.CREATEBY);
        exclud.add(User.LASTLOGINTIME);
        String sql = beanUtil.getUpdatedSQLbyBean(User.class, "t_user", exclud, User.ID);
        logger.warn("sql [{}]", sql);
        int i = jdbcTemplate.update(sql, user.getPassword(), user.getRealname(), user.getEmail(),
                updateBy, new Timestamp(System.currentTimeMillis()),user.getId());
        logger.warn("sql 执行成功结果 [{}]", i );
        return false;
    }

    @Override
    public boolean updateUserLastLoginTime(User user)
    {
        String sql = "UPDATE t_user SET lastlogintime = ? WHERE id = ?";
        boolean result = false;
        try
        {
            result = jdbcTemplate.update(sql, new Timestamp(System.currentTimeMillis()), user.getId()) == 1;
        }
        catch (BadSqlGrammarException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User... user)
    {

        return false;
    }

    @Override
    public boolean deleteUser(User... user)
    {
        return false;
    }
}
