package com.uusafe.cas.bean;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zengzhx
 * @date 2018/7/5 上午11:28
 */

public class User {

    public User() {
    }

    public  static final String ID = "id";

    private int id;

    public  static final String COMPANY = "company";
    
    @NotNull
    private String company;

    public  static final String USERNAME = "username";
    
    @NotEmpty(message="用户名不能为空")
    private String username;

    public  static final String PASSWORD = "password";

    @NotEmpty(message="密码不能为空")
    @Size(min=6, max=20)
    private String password;
    
    public  static final String REALNAME = "realname";

    private String realname;

    public  static final String EMAIL = "email";

    private String email;

    public  static final String LOCK = "lock";

    private int lock;

    public  static final String INVALI = "invali";

    private int invali;

    public  static final String DISABLE = "disable";

    private int disable;

    public  static final String CREATEBY = "createby";

    private String createby;

    public  static final String CREATETIME = "createtime";

    private Timestamp createtime;

    public  static final String UPDATEBY = "updateby";

    private String updateby;

    public  static final String UPDATETIME = "updatetime";

    private Timestamp updatetime;

    public  static final String LASTLOGINTIME = "lastlogintime";

    private Timestamp lastlogintime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public int getInvali() {
        return invali;
    }

    public void setInvali(int invali) {
        this.invali = invali;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    public Timestamp getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Timestamp lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                ", lock=" + lock +
                ", invali=" + invali +
                ", disable=" + disable +
                ", createby='" + createby + '\'' +
                ", createtime=" + createtime +
                ", updateby='" + updateby + '\'' +
                ", updatetime=" + updatetime +
                ", lastlogintime=" + lastlogintime +
                '}';
    }
}
