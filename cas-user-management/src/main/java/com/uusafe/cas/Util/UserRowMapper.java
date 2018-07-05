package com.uusafe.cas.Util;

import com.uusafe.cas.bean.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Zengzhx
 * @date 2018/7/5 下午4:16
 */
public class UserRowMapper implements RowMapper<User>{

    @Nullable
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user1 = new User();
        user1.setId(resultSet.getInt(User.ID));
        user1.setCompany(resultSet.getString(User.COMPANY));
        user1.setUsername(resultSet.getString(User.USERNAME));
        user1.setRealname(resultSet.getString(User.REALNAME));
        user1.setEmail(resultSet.getString(User.EMAIL));
        user1.setPassword(resultSet.getString(User.PASSWORD));
        user1.setLock(resultSet.getInt(User.LOCK));
        user1.setInvali(resultSet.getInt(User.INVALI));
        user1.setDisable(resultSet.getInt(User.DISABLE));
        return user1;
    }
}
