package com.hull.test.designPatterns.simleFacory;

/**
 * 简单工厂模式
 *  *  简单，但是不符合开闭原则，每次增加产品需要修改 原工厂类
 *
 * @author
 * @create 2019-12-12 10:22
 **/

public class Test {

    public static void main(String[] args) {
        IProduct p = Factory.createProduct("A");
        System.out.println(p.getName());
    }
}
