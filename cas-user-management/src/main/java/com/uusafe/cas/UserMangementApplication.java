package com.uusafe.cas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * 应用启动入口
 * @author Zengzhx
 * @date 2018/7/4 下午8:34
 */
@SpringBootApplication
public class UserMangementApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(UserMangementApplication.class, args);
    }

    /**
     * 需要把web项目打成war包部署到外部tomcat运行时需要改变启动方式
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UserMangementApplication.class);
    }

}
