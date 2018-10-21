package com.hull.test.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2018-06-15 下午3:00
 **/

public class ForkJoinWorkDemo {
    public static final Long aLong = 1000000000L;
//    public static final Long aLong = 10000000000L;

    public static void test1() {
        //ForkJoin实现
        long l = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();//实现ForkJoin 就必须有ForkJoinPool的支持
        ForkJoinTask<Long> task = new ForkJoinWork(0L,aLong);//参数为起始值与结束值
        Long invoke = forkJoinPool.invoke(task);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + invoke+"  time: " + (l1-l));
        //invoke = -5340232216128654848  time: 71694
    }

    public static void test2(){
    //普通线程实现
        Long x = 0L;
        Long y = aLong;
        long l = System.currentTimeMillis();
        for (Long i = 0L; i <= y; i++) {
            x+=i;
        }
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + x+"  time: " + (l1-l));
        //invoke = -5340232216128654848  time: 160939
    }

//    @Test
    public static void test3(){
        //Java 8 并行流的实现
        long begin = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, aLong).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("invoke = " + reduce+"  time: " + (end-begin));
        //invoke = -5340232216128654848  time: 15531 3064
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

}
