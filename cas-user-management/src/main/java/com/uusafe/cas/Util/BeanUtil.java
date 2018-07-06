package com.uusafe.cas.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.stereotype.Component;

@Component
public class BeanUtil {
	
	public <T> String  getUpdatedSQLbyBean(Class<T> t,String tablename, String primarykey)
	{
		return getUpdatedSQLbyBean(t, tablename, Collections.emptyList(), primarykey);
	}
	
	
	public <T> String  getUpdatedSQLbyBean(Class<T> t, String tablename, Collection<String> exclude,String primarykey)
	{
		StringBuilder sql = new StringBuilder(" UPDATE "+tablename+" SET ");
		Collection<String> filedArray = new ArrayList<String>();
		Field[] fileds = t.getFields();
		for(Field filed : fileds)
		{
			filedArray.add(filed.getName().toLowerCase());
		}
		filedArray.removeAll(exclude);
		filedArray.forEach(filedItem -> {
			sql.append(" "+filedItem +" = ? ," );
		});
		sql.delete(sql.lastIndexOf(","), sql.length());
		sql.append(" WHERE "+primarykey+"=? ");
		return sql.toString();
	}
	
}
