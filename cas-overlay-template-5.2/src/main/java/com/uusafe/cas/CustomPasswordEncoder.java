package com.uusafe.cas;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author Zengzhx
 * @date 2018/6/21 下午11:22
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence password) {
        try {
            //给数据进行md5加密
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.toString().getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public boolean matches(CharSequence rawPassword, String encodePassword) {
        // 判断密码是否存在
        if (rawPassword == null) {
            return false;
        }

        //通过md5加密后的密码
        String pass = this.encode(rawPassword.toString());
        //比较密码是否相等的问题
        return pass.equals(encodePassword);
    }
}
