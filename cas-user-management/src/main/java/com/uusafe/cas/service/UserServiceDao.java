package com.uusafe.cas.service;

import com.uusafe.cas.bean.User;

import java.util.Collection;

/**
 * @author Zengzhx
 * @date 2018/7/7 下午2:38
 */
public interface UserServiceDao {

    Collection<User> getAllUsers();

    Collection<User> getAllUsers(int pageSize, int pageNo,String queryWhere);

    int iTotalUser();

    int iTotalUser(String queryWhere);

    User getUserInfo(String company, String username, String password);

    User getUserInfo(int id);

    boolean addUser(User... user);

    boolean updateUser(User user, int updateBy);

    boolean updateUserLastLoginTime(User user);

    boolean updateUser(User... user);

    boolean deleteUser(User... user);

}
