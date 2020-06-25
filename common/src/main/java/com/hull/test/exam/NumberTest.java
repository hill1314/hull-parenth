package com.hull.test.exam;

/**
 *  最终返回值
 *
 * @author
 * @create 2019-12-13 15:00
 **/

public class NumberTest {

    public static void main(String[] args) {
        System.out.println(add(10));
    }

    private static int add(int i) {
        try {
            i+=1;
            i = i/0;
        }catch (Exception e){
            i++;
        }
        i++;

//        System.out.println(i);
        return i++;
    }



}
