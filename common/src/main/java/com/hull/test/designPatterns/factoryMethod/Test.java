package com.hull.test.designPatterns.factoryMethod;

import com.hull.test.designPatterns.simleFacory.IProduct;

/**
 * 工厂方法模式
 *    符合开闭原则，每次增加产品 需要增加一个对应的工厂类和产品
 *
 * @author
 * @create 2019-12-12 10:26
 **/

public class Test {
    public static void main(String[] args) {
        IFactory factory = new AFactory();
        IProduct p = factory.createProduct();
        System.out.println(p.getName());
    }
}
