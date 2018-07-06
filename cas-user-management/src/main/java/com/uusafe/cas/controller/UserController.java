package com.uusafe.cas.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uusafe.cas.Util.BeanUtil;
import com.uusafe.cas.Util.UserRowMapper;
import com.uusafe.cas.bean.User;

/**
 * @author Zengzhx
 * @date 2018/7/5 下午3:50
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	  
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	public BeanUtil beanUtil;
	
	@RequestMapping(value = "/add")
	public String addUser() {
		return "";
	}
	
	
	@RequestMapping(value = "/data/list")
	@ResponseBody
    public ResponseEntity<String> dataTables(HttpServletRequest httpServletRequest, Map<String,Object> model,
					    				@RequestParam(value ="name", required=false) String name,
					    				@RequestParam(value ="value", required=false) String value)
    {
        List<User> list = jdbcTemplate.query("SELECT * FROM t_user", new UserRowMapper() {});
        model.put("userList",list);
        JSONArray array = new JSONArray();
        JSONObject  json = new JSONObject();
        json.put("bbbb", "ccccccc");
        array.add(json);
        ResponseEntity<String> response = new ResponseEntity<String>(array.toString(), HttpStatus.OK);
        return response;
    }
	
	@RequestMapping(value = "/edit")
    public ModelAndView editUser(@ModelAttribute User user)
    {
		logger.warn("form user info [{}]", user.toString());
		Collection<String> exclud = new ArrayList<String>();
		exclud.add(User.ID);
		exclud.add(User.COMPANY);
		exclud.add(User.USERNAME);
		exclud.add(User.LOCK);
		exclud.add(User.DISABLE);
		exclud.add(User.INVALI);
		String sql = beanUtil.getUpdatedSQLbyBean(User.class, "t_user", exclud, User.ID);
		logger.warn("sql [{}]", sql);
		int i = jdbcTemplate.update(sql, user.getPassword(), user.getRealname(), user.getEmail(), user.getId());
		logger.warn("sql 执行成功结果 [{}]", i );
		
        return new ModelAndView("redirect:/data/tables");
    }

	@RequestMapping(value = "/delete")
	public String deleteUser() {
		return "";
	}

}
