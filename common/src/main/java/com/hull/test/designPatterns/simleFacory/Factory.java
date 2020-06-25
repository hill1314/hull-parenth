package com.hull.test.designPatterns.simleFacory;

/**
 *  简单工厂模式
 *  简单，但是不符合开闭原则，每次增加产品需要修改 原工厂类
 *
 * @author
 * @create 2019-12-12 10:21
 **/

public class Factory {

    public static IProduct createProduct(String type){
        IProduct p = null;
        switch (type){
            case "A":
                p = new ProductA(); break;
            case "B":
                p = new ProductB(); break;
        }
        return p;
    }
}
