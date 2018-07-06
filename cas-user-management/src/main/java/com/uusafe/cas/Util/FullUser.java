package com.uusafe.cas.Util;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.uusafe.cas.bean.User;

@Component
public class FullUser {
	
	public User attributeTOBean(Map<String,Object> attributes)
	{
		if(attributes.size() == 0)
		{
			return null;
		}
		User user = new User();
		user.setId(Integer.valueOf(attributes.get(User.ID).toString()));
		user.setUsername((String)attributes.get(User.USERNAME));
		user.setRealname((String)attributes.get(User.REALNAME));
		user.setEmail((String)attributes.get(User.EMAIL));
		return user;
	}

}
