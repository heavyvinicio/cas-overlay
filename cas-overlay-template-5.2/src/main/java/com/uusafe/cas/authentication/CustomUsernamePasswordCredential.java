package com.uusafe.cas.authentication;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import javax.validation.constraints.Size;

/**
 * @author Zengzhx
 * @date 2018/6/22 上午12:05
 */
public class CustomUsernamePasswordCredential extends UsernamePasswordCredential {

    @Size(min = 1, message = "required.company")
    private String company;

    @Size(min = 1, message = "required.authcode")
    private String authcode;

    public CustomUsernamePasswordCredential() {}

    public CustomUsernamePasswordCredential(String company, String userName, String password, String authcode) {
        super(userName, password);
        this.company = company;
        this.authcode = authcode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    @Override
    public int hashCode() {
        return (new HashCodeBuilder()).append(this.company).append(super.getUsername()).append(super.getPassword()).append(this.authcode).toHashCode();
    }


}
