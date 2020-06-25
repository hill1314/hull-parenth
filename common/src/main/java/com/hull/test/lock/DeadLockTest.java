package com.hull.test.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁的例子
 *
 * @author
 * @create 2019-12-27 15:25
 **/

public class DeadLockTest {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();


    public static void main(String[] args) {

        Thread t1 = new Thread(){
          public void run(){
              lock1.lock();
              try {
                  TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              while (!lock2.tryLock()){
              }
              lock1.unlock();
              lock2.unlock();
          }
        };
        Thread t2 = new Thread(){
            public void run(){
                lock2.lock();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (!lock1.tryLock()){
                }
                lock2.unlock();
                lock1.unlock();
            }
        };

        t1.start();
        t2.start();

    }
}
