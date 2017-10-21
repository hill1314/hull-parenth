package com.hull.config.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leilei.hu
 * @create 2017-10-15 上午10:24
 * @desc
 **/
@Aspect
@Component
public class ServiceLogAspect {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.hull.service..*.*(..))")
    public void aspect(){
        //TODO 切面测试有问题，待修改
    }

//    @Around("aspect()")
//    public void around(ProceedingJoinPoint joinPoint){
//        logger.info("service log aspect around before ...");
//        try {
//            joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            logger.error(throwable.getLocalizedMessage());
//        }
//        logger.info("service log aspect around before ...");
//    }

//    @Before("aspect()")
//    public void before(JoinPoint joinPoint){
//        logger.info("service log aspect before ...");
//
//        logger.info("我是前置通知!!!");
//        //获取目标方法的参数信息
//        Object[] obj = joinPoint.getArgs();
//        //AOP代理类的信息
//        joinPoint.getThis();
//        //代理的目标对象
//        joinPoint.getTarget();
//        //用的最多 通知的签名
//        Signature signature = joinPoint.getSignature();
//        //代理的是哪一个方法
//        logger.info(signature.getName());
//        //AOP代理类的名字
//        logger.info(signature.getDeclaringTypeName());
//        //AOP代理类的类（class）信息
//        signature.getDeclaringType();
//        //获取RequestAttributes
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        //从获取RequestAttributes中获取HttpServletRequest的信息
//        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        //如果要获取Session信息的话，可以这样写：
//        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
//        Enumeration<String> enumeration = request.getParameterNames();
//        Map<String,String> parameterMap = new HashMap<>();
//        while (enumeration.hasMoreElements()){
//            String parameter = enumeration.nextElement();
//            parameterMap.put(parameter,request.getParameter(parameter));
//        }
//        String str = JSON.toJSONString(parameterMap);
//        if(obj.length > 0) {
//            logger.info("请求的参数信息为："+str);
//        }
//    }

//    @After("aspect()")
//    public void after(){
//        logger.info("service log aspect after ...");
//    }
//
//    @AfterReturning("aspect()")
//    public void afterReturning(){
//        logger.info("service log aspect afterReturning ...");
//    }
//
//    @AfterThrowing("aspect()")
//    public void afterThrowing(){
//        logger.info("service log aspect afterThrowing ...");
//    }

}
