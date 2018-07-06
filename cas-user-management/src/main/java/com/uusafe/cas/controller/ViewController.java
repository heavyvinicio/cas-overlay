package com.uusafe.cas.controller;

import com.uusafe.cas.Util.Constants;
import com.uusafe.cas.Util.SafePasswordEncoder;
import com.uusafe.cas.Util.UserRowMapper;
import com.uusafe.cas.bean.User;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Zengzhx
 * @date 2018/6/17 下午12:46
 */
@Controller
public class ViewController {

    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected SafePasswordEncoder safePasswordEncoder;

    /**
     * 首页
     */
    @RequestMapping(value = {"/" , "/index"})
    public String index(HttpServletRequest httpServletRequest, Map<String,Object> model)
    {
        return "index";
    }

    @RequestMapping(value = "login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest httpServletRequest, Map<String,Object> model,
                        @RequestParam(value = "username" , required = false)  String username,
                        @RequestParam(value = "password" , required = false)  String password)
    {
        AttributePrincipal principal = (AttributePrincipal) httpServletRequest.getUserPrincipal();
        Map attributes = principal.getAttributes();
//        model.addAttribute("attributes", attributes);
//        System.out.println(attributes.toString());
//        System.out.println("loginloginloginloginloginlogin");
        System.out.println("attributes size ---- >" + attributes.size());
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            return "page-login";
        }
        String encodePassword = safePasswordEncoder.encode(password);
        User user = null;
        try {
            user =  jdbcTemplate.queryForObject("SELECT company, username, realname, email FROM T_User WHERE username = ? AND password = ?",
                new String[]{username, encodePassword}, new RowMapper<User>() {
                    @Nullable
                    @Override
                    public User mapRow(ResultSet resultSet, int i) throws SQLException {
                        User user1 = new User();
                        user1.setCompany(resultSet.getString("company"));
                        user1.setUsername(resultSet.getString("username"));
                        user1.setRealname(resultSet.getString("realname"));
                        user1.setEmail(resultSet.getString("email"));
                        return user1;
                    }
                });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (user == null) {
            logger.error("Username : [{}] Is Null -----> Please Check！", username);
            return "page-login";
        }
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(Constants.USER_SESSION, user);

        return "index";
    }


    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest httpServletRequest)
    {
        httpServletRequest.getSession().removeAttribute(Constants.USER_SESSION);
        return "page-login";
    }

    @RequestMapping(value = {"/basic/tables"})
    public String basicTables(HttpServletRequest httpServletRequest, Map<String,Object> model)
    {
        List<User> list = jdbcTemplate.query("SELECT * FROM t_user", new UserRowMapper());
        model.put("simpleList",list);
        return "table-basic";
    }


    @RequestMapping(value = {"/data/tables"})
    public String dataTables(HttpServletRequest httpServletRequest, Map<String,Object> model)
    {
        List<User> list = jdbcTemplate.query("SELECT * FROM t_user", new UserRowMapper() {});
        model.put("userList",list);
        return "table-data-table";
    }

    @RequestMapping(value = {"/edit"})
    public String toEditUserView(HttpServletRequest httpServletRequest,
                                 Map<String,Object> model,
                                 @RequestParam(value = "userid", required = true) int userid)
    {
        System.out.println(String.format("userid {%d}",userid));

        User user = null;
        try {
            user =  jdbcTemplate.queryForObject("SELECT * FROM T_User WHERE id =?",
                    new Object[]{userid}, new UserRowMapper());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        model.put("user", user);
        return "editUser";
    }

}
