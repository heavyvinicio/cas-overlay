package com.uusafe.cas.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Zengzhx
 * @date 2018/7/7 下午3:34
 */

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.uusafe.cas.controller.*.*(..))")
    public void webLog() {
        System.out.println("weblog1");

        System.out.println("weblog2");
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL:[{}]",  request.getRequestURL().toString());
        logger.info("HTTP_METHOD:[{}]" , request.getMethod());
        logger.info("IP:[{}]" ,request.getRemoteAddr());
        logger.info("CLASS_METHOD:[{}]" ,joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS:[{}]" , Arrays.toString(joinPoint.getArgs()));
    }


    @AfterReturning(returning = "value", pointcut = "webLog()")
    public void doAfterReturning(Object value) throws Throwable {
        // 处理完请求，返回内容
        logger.info("方法的返回值 : [{}]",value);
    }


    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint jp){
        logger.info("方法用时: [{}]" , ((System.currentTimeMillis() - startTime.get()))+"ms");
    }


    //环绕通知,环绕增强，相当于MethodInterceptor
//    @Around("webLog()")
//    public Object arround(ProceedingJoinPoint pjp) {
//        System.out.println("方法环绕start.....");
//        try {
//            Object o =  pjp.proceed();
//            System.out.println("方法环绕proceed，结果是 :" + o);
//            return o;
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
