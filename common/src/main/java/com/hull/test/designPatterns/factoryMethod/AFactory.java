package com.hull.test.designPatterns.factoryMethod;

import com.hull.test.designPatterns.simleFacory.IProduct;
import com.hull.test.designPatterns.simleFacory.ProductA;

/**
 *
 *
 * @author
 * @create 2019-12-12 10:25
 **/

public class AFactory implements IFactory{

    @Override
    public IProduct createProduct() {
        return new ProductA();
    }
}
