package com.uusafe.zengzhx.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Zengzhx
 * @date 2018/6/17 下午1:45
 */
@Component
@PropertySource(value = "classpath:custom.properties")
public class Animal {


    @Value(value = "${animal.name}")
    private String name;

    @Value(value = "${animal.age}")
    private int age ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
