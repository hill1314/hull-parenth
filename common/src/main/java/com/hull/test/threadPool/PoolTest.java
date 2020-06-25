package com.hull.test.threadPool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 *
 * @author
 * @create 2019-12-27 03:59
 **/

public class PoolTest {
    public static AtomicInteger num = new AtomicInteger(0);
    public static void main(String[] args) throws IOException {

//        Executors.newFixedThreadPool()
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,4,
                5, TimeUnit.SECONDS, new ArrayBlockingQueue(5));

        Thread thread = new Thread(){
            public void run(){
                System.out.println(num.incrementAndGet()+"thread begin...");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(num.get()+"thread end...");
            }
        };

        while (System.in.read()>0){
            executor.execute(thread);
            System.out.println("size="+executor.getPoolSize()
            +",active="+executor.getActiveCount()
            +",TaskCount="+executor.getTaskCount()
            +",Queue="+executor.getQueue().size()
            );
        }


    }
}
