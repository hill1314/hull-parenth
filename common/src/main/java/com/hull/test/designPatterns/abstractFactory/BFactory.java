package com.hull.test.designPatterns.abstractFactory;

import com.hull.test.designPatterns.simleFacory.IProduct;
import com.hull.test.designPatterns.simleFacory.ProductB;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2019-12-12 10:40
 **/

public class BFactory extends AbstractFactory{
    @Override
    public IProduct createProduct() {
        return new ProductB();
    }

    @Override
    public IGift createGift() {
        return new GiftB();
    }
}
