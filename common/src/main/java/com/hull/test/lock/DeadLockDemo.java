package com.hull.test.lock;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2019-12-29 08:58
 **/


public class DeadLockDemo {

    public static void main(String[] args) {
        new Thread(new DeadLockThread1()).start();
        new Thread(new DeadLockThread2()).start();
    }
}

class DeadLockThread1 implements Runnable{
    @Override
    public void run() {
        while (true){
            synchronized (DeadLock.obj1){
                System.out.println("get obj1 lock...");
                synchronized (DeadLock.obj2){
                    System.out.println("get obj2 lock...");
                }
            }
        }
    }
}

class DeadLockThread2 implements Runnable{
    @Override
    public void run() {
        while (true){
            synchronized (DeadLock.obj2){
                System.out.println("get obj2 lock...");
                synchronized (DeadLock.obj1){
                    System.out.println("get obj1 lock...");
                }
            }
        }
    }
}

class DeadLock{
    public static Object obj1 = new Object();
    public static Object obj2 = new Object();
}
