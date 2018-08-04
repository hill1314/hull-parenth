package com.hull.test.proxy.cglib;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2018-08-04 下午10:12
 **/

public class TestCgLib {

    public static void main(String[] args) {

        MeiPoProxy proxy = new MeiPoProxy();
        Meizi meizi = (Meizi) proxy.getInstance(new Meizi());
        meizi.findLove();
    }



}
