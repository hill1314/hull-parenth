package com.hull.test.synchTest;

import java.util.concurrent.TimeUnit;

/**
 * synchronize 各种锁的测试
 *
 * 参考 https://blog.csdn.net/luckey_zh/article/details/53815694
 *
 * @author
 * @create 2019-12-17 15:59
 **/

public class SynchronizeTest {

    public void method1(){
        //锁住this对象
        synchronized (this){
            try {
                System.out.println("method1 begin...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("method1 end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void method0(){
        //锁住方法
        try {
            System.out.println("method1 begin...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("method1 end...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2(){
        System.out.println("method2 begin...");
        System.out.println("method2 end...");
    }


    public static void main(String[] args) {
        SynchronizeTest synchronizeTest = new SynchronizeTest();

        test1(synchronizeTest);
//        test2(synchronizeTest);
//        test1d(synchronizeTest);
    }

    //同一个对象，执行同步方法，对象的其他方法也会被阻塞
    private static void test2(SynchronizeTest synchronizeTest) {
        ThreadC threadC = new ThreadC(synchronizeTest);
        ThreadB threadB = new ThreadB(synchronizeTest);
        threadC.start();
        threadB.start();
    }

    private static void test1(SynchronizeTest synchronizeTest) {
        //同一个对象，执行锁住对象的方法，对象的其他方法也会被阻塞
        ThreadA threadA = new ThreadA(synchronizeTest);
        ThreadB threadB = new ThreadB(synchronizeTest);
        threadA.start();
        threadB.start();
    }

    private static void test1d(SynchronizeTest synchronizeTest) {
        //不同的对象，执行锁住对象的方法，对象的其他方法不会被阻塞
        ThreadA threadA = new ThreadA(synchronizeTest);
        SynchronizeTest s2 = new SynchronizeTest();
        ThreadB threadB = new ThreadB(s2);
        threadA.start();
        threadB.start();
    }


}
