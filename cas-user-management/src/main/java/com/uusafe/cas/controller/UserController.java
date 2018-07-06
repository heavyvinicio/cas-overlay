package com.uusafe.cas.controller;

import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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


	@RequestMapping(value = "/getList")
	@ResponseBody
	public String getList(@RequestParam String aoData) {
		//json格式化用的是fastjson
		JSONArray jsonarray=(JSONArray) JSONArray.parse(aoData);
		String sEcho = null;
		// 起始索引
		int iDisplayStart = 0;
		// 每页显示的行数
		int iDisplayLength = 10;

		//搜索栏
		String search = "";
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			if ("sEcho".equals(obj.get("name")))
				sEcho = obj.get("value").toString();

			if ("iDisplayStart".equals(obj.get("name")))
				iDisplayStart =Integer.parseInt(obj.get("value").toString());

			if ("iDisplayLength".equals(obj.get("name")))
				iDisplayLength = Integer.parseInt(obj.get("value").toString());

			if ("sSearch".equals(obj.get("name")))
				search = obj.get("value").toString();
		}
		StringBuilder queyWhere = new StringBuilder();
		if(!StringUtils.isEmpty(search))
		{
			queyWhere.append(" WHERE ")
					.append(" company like ").append("\'%"+search+"%\'").append(" OR ")
					.append(" realname like ").append("\'%"+search+"%\'").append(" OR ")
					.append(" email    like ").append("\'%"+search+"%\'");
		}
		logger.debug(" SQL QueyWhere  [{}]", queyWhere.toString());
		int iTotalRecords = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_user"+queyWhere.toString(), int.class);
		List<User> list = jdbcTemplate.query("SELECT * FROM t_user "+queyWhere.toString()+" limit "+iDisplayStart+","+iDisplayLength,
				new UserRowMapper() {});
		JSONObject getObj = new JSONObject();

		// DataTable前台必须要的
		getObj.put("sEcho", sEcho);

		//实际的行数，调用查询总记录数的方法
		getObj.put("iTotalRecords",iTotalRecords);

		//显示的行数,这个要和上面写的一样
		getObj.put("iTotalDisplayRecords",iTotalRecords);

		//把查到数据装入aaData,要以JSON格式返回
		getObj.put("aaData", list);


		return JSONObject.toJSONString(getObj);
	}
}
