package com.hull.test.designPatterns.abstractFactory;

import com.hull.test.designPatterns.factoryMethod.IFactory;
import com.hull.test.designPatterns.simleFacory.IProduct;

/**
 * 抽象工厂
 *
 * @author
 * @create 2019-12-12 10:24
 **/

public abstract class AbstractFactory {
    public abstract IProduct createProduct();
    public abstract IGift createGift();


    public static AbstractFactory getFactory(Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        AbstractFactory factory = (AbstractFactory) Class.forName(clazz.getName()).newInstance();
        return factory;
    }

}
