package com.hull.test.designPatterns.abstractFactory;

import com.hull.test.designPatterns.simleFacory.IProduct;
import com.hull.test.designPatterns.simleFacory.ProductA;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2019-12-12 10:40
 **/

public class AFactory extends AbstractFactory{
    @Override
    public IProduct createProduct() {
        return new ProductA();
    }

    @Override
    public IGift createGift() {
        return new GiftA();
    }
}
