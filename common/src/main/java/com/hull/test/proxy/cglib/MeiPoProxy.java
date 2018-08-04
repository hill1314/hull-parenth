package com.hull.test.proxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 的代理，原理是父子 继承
 *
 * @author
 * @create 2018-08-04 下午10:00
 **/

public class MeiPoProxy implements MethodInterceptor {


    public Object getInstance(Object obj){
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     *  代理原理也是 字节码重组
     * @param o
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {
        System.out.println("媒婆 干点事。。。");
        //invokeSuper 执行父类的方法，不能直接 invoke ，返回死循环
        methodProxy.invokeSuper(o,args);
        return null;
    }
}
