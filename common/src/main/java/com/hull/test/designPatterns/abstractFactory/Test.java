package com.hull.test.designPatterns.abstractFactory;

/**
 * 抽象工厂模式
 *
 * @author
 * @create 2019-12-12 10:41
 **/

public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        AbstractFactory factory = AbstractFactory.getFactory(AFactory.class);

//        AbstractFactory factory = new AFactory();
        System.out.println(factory.createProduct().getName()
                +"-"+factory.createGift().getGiftName());
    }
}
