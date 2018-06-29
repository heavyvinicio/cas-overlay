package com.uusafe.cas.Bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zengzhx
 * @date 2018/6/27 上午9:54
 */
public class UserBean {

    private String company;

    private String username;

    private String passorwd;

    private String realname;

    private String email;

    private boolean lock;

    private boolean disable;

    private boolean invali;

    private int account;


    public UserBean(){}

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLock() { return lock; }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isInvali() {
        return invali;
    }

    public void setInvali(boolean invali) {
        this.invali = invali;
    }

    public void setAccount(int account) {
        this.account = account;
    }


    public Map<String,Object> getMapAttribues()
    {
        Map<String,Object> attribuesMap = new HashMap<>(10);
        attribuesMap.put("username",this.username);
        attribuesMap.put("realname",this.realname);
        attribuesMap.put("email",this.email);
        attribuesMap.put("company",this.company);
        attribuesMap.put("lock",this.lock);
        attribuesMap.put("invali",this.invali);
        attribuesMap.put("account",this.account);
        attribuesMap.put("disable",this.disable);
        return attribuesMap;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "company='" + company + '\'' +
                ", username='" + username + '\'' +
                ", passorwd='" + passorwd + '\'' +
                ", realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                ", lock=" + lock +
                ", disable=" + disable +
                ", invali=" + invali +
                ", account=" + account +
                '}';
    }
}
