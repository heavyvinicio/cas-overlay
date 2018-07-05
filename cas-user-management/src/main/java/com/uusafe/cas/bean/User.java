package com.uusafe.cas.bean;

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

    private String company;

    public  static final String USERNAME = "username";

    private String username;

    public  static final String PASSWORD = "password";

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
}
