package com.hull.test.blockQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 阻塞队列学习
 * https://blog.csdn.net/chenchaofuck1/article/details/51660119
 *
 * @author
 * @create 2018-07-01 下午4:38
 **/

public class BlockQueueTest {

    public static class Producer implements Runnable{
        private BlockingQueue<Integer> queue;
        private volatile boolean flag;
        private volatile Integer n=1;
        private Random random;

        public Producer(BlockingQueue<Integer> queue){
            this.queue = queue;
            this.flag = true;
            random = new Random();
        }

        @Override
        public void run() {
            while (flag){
                try {
//                    Integer info = random.nextInt(100);
                    Integer info = n++;
                    queue.put(info);
                    System.out.println(Thread.currentThread().getName()+" put:"+info);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutDown(){
            this.flag = false;
        }
    }

    public static class Consumer implements Runnable{
        private BlockingQueue<Integer> queue;
        private volatile boolean flag;

        public Consumer(BlockingQueue<Integer> queue){
            this.queue = queue;
            this.flag = true;

        }

        @Override
        public void run() {
            while (flag){
                try {
//                    queue.stream().forEach(s-> System.out.print(s+","));
                    Integer info = queue.take();
                    System.out.println(Thread.currentThread().getName()+" take:"+info);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutDown(){
            this.flag = false;
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        for(int i =0;i<10;i++){
            if(i<5){
                new Thread(producer,"producer"+i).start();
            }else {
                new Thread(consumer,"consumer"+(i-5)).start();
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.shutDown();
//        consumer.shutDown();
    }
}
