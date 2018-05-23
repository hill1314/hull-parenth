package com.hull.test.java8;

import java.util.Arrays;
import java.util.List;

/**
 * 方法引用
 *方法引用通过方法的名字来指向一个方法。
 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 方法引用使用一对冒号 ::

 **** 前提是 该方法 是一个接口的实现 ***
 * @author
 * @create 2018-04-22 下午3:18
 **/

public class JDK8_2_callMethodTest {
    public static void main(String[] args) {

        //构造器引用：它的语法是Class::new，或者更一般的Class<T>::new
        Car car = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car);

        //静态方法引用：它的语法是Class::static_method
        cars.forEach(Car::collide);

        //特定类的任意对象的方法引用：它的语法是Class::method
        cars.forEach(Car::repair);

        //特定对象的方法引用：它的语法是instance::method
        cars.forEach(car::follow);
    }



}
