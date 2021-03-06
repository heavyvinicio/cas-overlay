package com.uusafe.cas.config;

import com.uusafe.cas.configproperties.CasClientAutoConfig;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
/**
 * @author Zengzhx
 * @date 2018/7/5 下午5:12
 */
@Configuration
public class CasConfig {

    @Autowired
    CasClientAutoConfig autoconfig;

    private static boolean casEnabled  = false;

    @Bean
    public CasClientAutoConfig getSpringCasAutoconfig(){
        return new CasClientAutoConfig();
    }

    /**
     * 用于实现单点登出功能
     */
    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listener = new ServletListenerRegistrationBean<>();
        listener.setEnabled(casEnabled);
        listener.setListener(new SingleSignOutHttpSessionListener());
        listener.setOrder(1);
        return listener;
    }

    /**
     * 该过滤器用于实现单点登出功能，单点退出配置，一定要放在其他filter之前
     */
    @Bean
    public FilterRegistrationBean<LogoutFilter> logOutFilter() {
        FilterRegistrationBean<LogoutFilter> filterRegistration = new FilterRegistrationBean<LogoutFilter>();
        LogoutFilter logoutFilter = new LogoutFilter(autoconfig.getCasServerUrlPrefix() + "/logout?service=" + autoconfig.getServerName(),new SecurityContextLogoutHandler());
        filterRegistration.setFilter(logoutFilter);
        filterRegistration.setEnabled(casEnabled);
        if(autoconfig.getSignOutFilters().size()>0)
        {
            filterRegistration.setUrlPatterns(autoconfig.getSignOutFilters());
        }
        else
        {
            filterRegistration.addUrlPatterns("/logout");
        }
        filterRegistration.addInitParameter("casServerUrlPrefix", autoconfig.getCasServerUrlPrefix());
        filterRegistration.addInitParameter("serverName", autoconfig.getServerName());
        filterRegistration.setOrder(2);
        return filterRegistration;
    }

    /**
     * 该过滤器用于实现单点登出功能，单点退出配置，一定要放在其他filter之前
     */
    @Bean
    public FilterRegistrationBean<SingleSignOutFilter> singleSignOutFilter() {
        FilterRegistrationBean<SingleSignOutFilter> filterRegistration = new FilterRegistrationBean<SingleSignOutFilter>();
        filterRegistration.setFilter(new SingleSignOutFilter());
        filterRegistration.setEnabled(casEnabled);
        if(autoconfig.getSignOutFilters().size()>0)
        {
            filterRegistration.setUrlPatterns(autoconfig.getSignOutFilters());
        }
        else
        {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.addInitParameter("casServerUrlPrefix", autoconfig.getCasServerUrlPrefix());
        filterRegistration.addInitParameter("serverName", autoconfig.getServerName());
        filterRegistration.setOrder(3);
        return filterRegistration;
    }

    /**
     * 该过滤器负责用户的认证工作
     */
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> filterRegistration = new FilterRegistrationBean<AuthenticationFilter>();
        filterRegistration.setFilter(new AuthenticationFilter());
        filterRegistration.setEnabled(casEnabled);
        if(autoconfig.getAuthFilters().size()>0)
        {
            filterRegistration.setUrlPatterns(autoconfig.getAuthFilters());
        }
        else
        {
            filterRegistration.addUrlPatterns("/*");
        }
        //casServerLoginUrl:cas服务的登陆url
        filterRegistration.addInitParameter("casServerLoginUrl", autoconfig.getCasServerLoginUrl());
        //本项目登录ip+port
        filterRegistration.addInitParameter("serverName", autoconfig.getServerName());
        filterRegistration.addInitParameter("useSession", autoconfig.isUseSession()?"true":"false");
        filterRegistration.addInitParameter("redirectAfterValidation", autoconfig.isRedirectAfterValidation()?"true":"false");
        filterRegistration.setOrder(4);
        return filterRegistration;
    }

    /**
     * 该过滤器负责对Ticket的校验工作
     */
    @Bean
    public FilterRegistrationBean<Cas30ProxyReceivingTicketValidationFilter> cas30ProxyReceivingTicketValidationFilter() {
        FilterRegistrationBean<Cas30ProxyReceivingTicketValidationFilter> filterRegistration = new FilterRegistrationBean<Cas30ProxyReceivingTicketValidationFilter>();
        Cas30ProxyReceivingTicketValidationFilter cas30ProxyReceivingTicketValidationFilter = new Cas30ProxyReceivingTicketValidationFilter();
        cas30ProxyReceivingTicketValidationFilter.setServerName(autoconfig.getServerName());
        filterRegistration.setFilter(cas30ProxyReceivingTicketValidationFilter);
        filterRegistration.setEnabled(casEnabled);
        if(autoconfig.getValidateFilters().size()>0)
        {
            filterRegistration.setUrlPatterns(autoconfig.getValidateFilters());
        }
        else
        {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.addInitParameter("casServerUrlPrefix", autoconfig.getCasServerUrlPrefix());
        filterRegistration.addInitParameter("serverName", autoconfig.getServerName());
        filterRegistration.setOrder(5);
        return filterRegistration;
    }


    /**
     * 该过滤器对HttpServletRequest请求包装， 可通过HttpServletRequest的getRemoteUser()方法获得登录用户的登录名
     *
     */
    @Bean
    public FilterRegistrationBean<HttpServletRequestWrapperFilter> httpServletRequestWrapperFilter() {
        FilterRegistrationBean<HttpServletRequestWrapperFilter> filterRegistration = new FilterRegistrationBean<HttpServletRequestWrapperFilter>();
        filterRegistration.setFilter(new HttpServletRequestWrapperFilter());
        filterRegistration.setEnabled(true);
        if(autoconfig.getRequestWrapperFilters().size()>0)
        {
            filterRegistration.setUrlPatterns(autoconfig.getRequestWrapperFilters());
        }
        else
        {
            filterRegistration.addUrlPatterns("/uu/login");
        }
        filterRegistration.setOrder(6);
        return filterRegistration;
    }

    /**
     * 该过滤器使得可以通过org.jasig.cas.client.util.AssertionHolder来获取用户的登录名。
     比如AssertionHolder.getAssertion().getPrincipal().getName()。
     这个类把Assertion信息放在ThreadLocal变量中，这样应用程序不在web层也能够获取到当前登录信息
     */
    @Bean
    public FilterRegistrationBean<AssertionThreadLocalFilter> assertionThreadLocalFilter() {
        FilterRegistrationBean<AssertionThreadLocalFilter> filterRegistration = new FilterRegistrationBean<AssertionThreadLocalFilter>();
        filterRegistration.setFilter(new AssertionThreadLocalFilter());
        filterRegistration.setEnabled(true);
        if(autoconfig.getAssertionFilters().size()>0)
        {
            filterRegistration.setUrlPatterns(autoconfig.getAssertionFilters());
        }
        else
        {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.setOrder(7);
        return filterRegistration;
    }


}
