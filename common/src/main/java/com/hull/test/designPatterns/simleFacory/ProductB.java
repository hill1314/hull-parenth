package com.hull.test.designPatterns.simleFacory;

/**
 *
 *
 * @author
 * @create 2019-12-12 10:17
 **/

public class ProductB implements IProduct {

    @Override
    public String getName() {
        return "B";
    }

    @Override
    public String getPrice() {
        return "200";
    }
}
