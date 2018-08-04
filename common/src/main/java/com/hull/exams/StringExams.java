package com.hull.exams;

/**
 *
 *
 * @author
 * @create 2018-08-04 下午8:52
 **/

public class StringExams {

    public static void main(String[] args) {
        swapVal();
    }

    /**
     * 交还 a\b，不使用变量
     */
//    @Test
    public static void swapVal() {
        int a = 5, b = 6;
        System.out.println("a=" + a + ",b=" + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a=" + a + ",b=" + b);
    }

}
