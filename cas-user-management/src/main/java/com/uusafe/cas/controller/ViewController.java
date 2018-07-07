package com.uusafe.cas.controller;

import com.uusafe.cas.Util.Constants;
import com.uusafe.cas.Util.FullUser;
import com.uusafe.cas.Util.SafePasswordEncoder;
import com.uusafe.cas.Util.UserRowMapper;
import com.uusafe.cas.bean.User;
import com.uusafe.cas.service.UserServiceDao;
import org.jasig.cas.client.authentication.AttributePrincipal;
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
import java.util.ArrayList;
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
    protected UserServiceDao userServiceImp;

    @Autowired
    protected SafePasswordEncoder safePasswordEncoder;

    @Autowired
    protected FullUser fullUser;

    /**
     * 首页
     */
    @RequestMapping(value = {"/", "/index"})
    public String index(HttpServletRequest httpServletRequest, Map<String, Object> model) {
        //获取用户数量
        int iTotalUser = userServiceImp.iTotalUser();
        model.put("iTotalUser" ,iTotalUser);
        return "index";
    }

    @RequestMapping(value = "login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest httpServletRequest, Map<String, Object> model,
                        @RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password) {
        User user = (User) httpServletRequest.getSession().getAttribute(Constants.USER_SESSION);
        if (user != null)
            return "redirect:/index";

        AttributePrincipal principal = (AttributePrincipal) httpServletRequest.getUserPrincipal();
        if (principal != null) {
            Map<String, Object> attributes = principal.getAttributes();
            logger.debug(" Attributes ---> [{}] ", attributes.size());
            user = fullUser.attributeTOBean(attributes);
        }
        if (user == null) {
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                return "page-login";
            }
            String encodePassword = safePasswordEncoder.encode(password);
            user = userServiceImp.getUserInfo("", username, encodePassword);
            if (user == null) {
                logger.error("Username : [{}] Is Null -----> Please Check！", username);
                return "page-login";
            }
        }
        //刷新用户最后上线时间
        System.currentTimeMillis();

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(Constants.USER_SESSION, user);
        return "redirect:/index";
    }


    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute(Constants.USER_SESSION);
        return "page-login";
    }

    @RequestMapping(value = {"/basic/tables"})
    public String basicTables(HttpServletRequest httpServletRequest, Map<String, Object> model) {
        List<User> list = (List<User>) userServiceImp.getAllUsers();
        model.put("simpleList", list);
        return "table-basic";
    }


    @RequestMapping(value = {"/data/tables"})
    public String dataTables(HttpServletRequest httpServletRequest, Map<String, Object> model,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "value", required = false) String value) {
        List<User> list = (List<User>) userServiceImp.getAllUsers();
        model.put("userList", list);
        return "table-data-table";
    }


    @RequestMapping(value = {"/edit"})
    public String toEditUserView(HttpServletRequest httpServletRequest,
                                 Map<String, Object> model,
                                 @RequestParam(value = "userid", required = true) int userid) {
        System.out.println(String.format("userid {%d}", userid));
        User user = userServiceImp.getUserInfo(userid);
        logger.debug(" GET UserInfo [{}]", user);
        model.put("user", user);
        return "editUser";
    }


}