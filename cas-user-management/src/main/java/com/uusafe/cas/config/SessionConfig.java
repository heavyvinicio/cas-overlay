package com.uusafe.cas.config;

import com.uusafe.cas.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springboot 添加自定义拦截器
 * @author Zengzhx
 * @date 2018/7/5 下午1:34
 */

@Configuration
public class SessionConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                //静态资源取消拦截
                .excludePathPatterns("/js/**", "/css/**");
    }

}
