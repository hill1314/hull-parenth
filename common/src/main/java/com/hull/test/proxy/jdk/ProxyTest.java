package com.hull.test.proxy.jdk;

import sun.misc.ProxyGenerator;

/**
 * @author
 * @create 2018-08-04 下午12:54
 **/
public class ProxyTest {
    public static void main(String[] args) {
        javaProxy();
    }


    /**
     * java 动态代理
     */
    public static void javaProxy() {
        //java 动态代理
        JinJiRenProxy jinJiRenProxy = new JinJiRenProxy(new LiuDeHua("德华"));
        Singer singer = (Singer) jinJiRenProxy.getInstance();
        System.out.println("class：" + singer.getClass());
        singer.dance();

        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{singer.getClass()});
        System.out.println(bytes);
    }
}
