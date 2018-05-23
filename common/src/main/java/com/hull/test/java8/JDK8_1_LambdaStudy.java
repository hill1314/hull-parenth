package com.hull.test.java8;

/**
 * Lambda 表达式
 * <p>
 * (parameters) -> expression
 * 或
 * (parameters) ->{ statements; }
 * <p>
 * lambda表达式的重要特征:
 * (1)可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 * (2)可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
 * (3)可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
 * (4)可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
 *
 * 变量作用域
 lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。
 * @author
 * @create 2018-04-22 下午3:29
 **/

public class JDK8_1_LambdaStudy {
    public static void main(String[] args) {
        //参数类型声明
        MathOperation addOperation = (int a, int b) -> a + b;
        //不用参数声明
        MathOperation subOperation = (a, b) -> a - b;
        //大括号中的返回语句
        MathOperation mulOperation = (a, b) -> {
            return a * b;
        };

        System.out.println(operate(2,1,addOperation));
        System.out.println(operate(2,1,subOperation));
        System.out.println(operate(2,1,mulOperation));
    }


    interface MathOperation {
        int operation(int a, int b);
    }

    private static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}
