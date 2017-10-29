package com.hull.common.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.ThreadUtils;

import java.util.concurrent.*;

/**
 * 多线程工具类
 *
 * @author hull
 * @create 2017-10-28 下午5:46
 * @desc
 **/
public class ThreadUtil {

    /**
     * 获取线程池
     * @param poolName
     * @param threadPoolSize
     * @return
     */
    public static ExecutorService createThreadPool(String poolName, int threadPoolSize){
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(poolName+"-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(threadPoolSize,20,100L,
                TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(100000),threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println("成功创建线程池"+pool.toString());
        return pool;
    }

    /**
     * 执行线程任务
     * @param pool
     * @param countDownLatch
     * @return
     */
    public static Future executeThreadMethod(ExecutorService pool, CountDownLatch countDownLatch, int num){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程 "+Thread.currentThread().getName()+" 正在执行 "+num);
                threadTask(num);
                countDownLatch.countDown();
            }
        });
        Future future = pool.submit(thread);
        return future;
    }

    //
    private static void threadTask(int num) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行第："+num);
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = ThreadUtil.createThreadPool("test",10);
        int num =500;
        CountDownLatch countDownLatch = new CountDownLatch(num);

        long begTime = System.currentTimeMillis();
        for(int n=0;n<num;n++){
            ThreadUtil.executeThreadMethod(threadPool,countDownLatch,n);
        }

        countDownLatch.await();
        threadPool.shutdown();
        long endTime = System.currentTimeMillis();

        System.out.println("多线程任务都执行完了,总共花费 "+(endTime - begTime)/1000+" s，继续主线程吧。。。");
    }
}
