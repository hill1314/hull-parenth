package com.hull.test.blockQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2019-12-22 20:02
 **/

public class SimplePubSubDemo {

    public static class Producer implements Runnable{
        private BlockingQueue queue;
        private volatile boolean flag = true;
        private Integer count;

        public Producer(BlockingQueue queue) {
            this.queue = queue;
            count = 0;
        }

        @Override
        public void run() {
            while (flag){
                try {
                    queue.put(count++);
                    System.out.println("队列长度："+queue.size()+";"+Thread.currentThread().getName()+"发送："+count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stop(){
            flag=false;
        }
    }

    public static class Customer implements Runnable{
        private BlockingQueue queue;
        private boolean flag = true;

        public Customer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (flag){
                try {
                    Integer count = (Integer) queue.take();
                    System.out.println("队列长度："+queue.size()+";"+Thread.currentThread().getName()+"收到："+count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void stop(){
            flag=false;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(20);
        Producer producer = new Producer(queue);
        Customer customer = new Customer(queue);
        new Thread(producer).start();

        TimeUnit.SECONDS.sleep(2);
        new Thread(customer).start();
        producer.stop();
        TimeUnit.SECONDS.sleep(2);
//        customer.stop();

    }


}
