package com.hull.test.synchTest;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2019-12-17 16:09
 **/

public class ThreadA extends Thread{
    SynchronizeTest sTest ;

    public ThreadA(SynchronizeTest sTest) {
        this.sTest = sTest;
    }

    @Override
    public void run() {
        sTest.method1();
    }
}
