package com.uusafe.cas.interceptor;

import com.uusafe.cas.Util.Constants;
import com.uusafe.cas.bean.User;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zengzhx
 * @date 2018/7/5 下午1:32
 */
public class SessionInterceptor implements HandlerInterceptor{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().contains("/login")) {
            User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
            if (null == user) {
                response.sendRedirect("/login");
                return false;
            }
        }
        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

//        System.out.println("SessionInterceptor . postHandle . handle"+handler.toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//        System.out.println("SessionInterceptor . afterCompletion handle"+handler.toString());
    }
}
