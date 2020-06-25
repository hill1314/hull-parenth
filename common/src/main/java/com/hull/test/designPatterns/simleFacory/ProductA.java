package com.hull.test.designPatterns.simleFacory;

/**
 *
 *
 * @author
 * @create 2019-12-12 10:17
 **/

public class ProductA implements IProduct {

    @Override
    public String getName() {
        return "A";
    }

    @Override
    public String getPrice() {
        return "100";
    }
}
