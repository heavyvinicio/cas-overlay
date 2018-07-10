package org.apereo.cas.authentication;

import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UsernamePasswordCredential implements Credential, Serializable {

    /**
     * Authentication attribute name for password.
     **/
    public static final String AUTHENTICATION_ATTRIBUTE_PASSWORD = "credential";

    private static final long serialVersionUID = -700605081472810939L;

    @Size(min = 1, message = "required.username")
    private String username;

    @Size(min = 1, message = "required.password")
    private String password;

    @Size(min = 1, message = "required.company")
    private String company;

    @Size(min = 1, message = "required.authcode")
    private String authcode;

    /**
     * 设置用户id
     * 根据id获取用户对应的数据，避免只根据用户名查询
     */
    private int uid =-1;
    /**
     * Default constructor.
     */
    public UsernamePasswordCredential() {
    }

    /**
     * Creates a new instance with the given username and password.
     *
     * @param userName Non-null user name.
     * @param password Non-null password.
     */
    public UsernamePasswordCredential(final String userName, final String password) {
        this.username = userName;
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String userName) {
        this.username = userName;
    }

    public String getCompany() { return company; }

    public void setCompany(String company) { this.company = company; }

    public String getAuthcode() { return authcode; }

    public void setAuthcode(String authcode) { this.authcode = authcode; }

    public int getUid() { return uid; }

    public void setUid(int uid) { this.uid = uid; }

    @Override
    public String getId() {
        return this.username;
    }

    @Override
    public String toString() {
        return String.format("uid %s username %s , company %s", this.uid, this.username, this.company);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final UsernamePasswordCredential that = (UsernamePasswordCredential) o;


        if (this.password != null ? !this.password.equals(that.password) : that.password != null) {
            return false;
        }

        if (this.company != null ? !this.company.equals(that.company) : that.company != null) {
            return false;
        }

        return this.username != null ? this.username.equals(that.username) : that.username == null;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.username)
                .append(this.password)
                .append(this.company)
                .toHashCode();
    }

}
