package com.uusafe.cas.DB;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Zengzhx
 * @date 2018/6/22 上午11:09
 */
public class DBSource {
    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Connection getConnection()throws SQLException
    {
        return this.jdbcTemplate.getDataSource().getConnection();
    }
}
