package com.hull.test.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 *
 * @author
 * @create 2018-08-04 下午12:00
 **/

public class JinJiRenProxy implements InvocationHandler{

    private Object target;

    public JinJiRenProxy(Singer target){
        this.target =target;
    }

    public Object getInstance(){
        System.out.println("被代理对象："+target.getClass());
        return  Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理对象："+this.getClass());
        Object result = method.invoke(target,args);
        System.out.println(result);
        return result;
    }
}
