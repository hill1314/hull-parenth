package com.hull.test.asm;

import java.lang.management.ManagementFactory;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2020-06-25 下午7:26
 **/

public class Base2 {

    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("RuntimeMXBean="+name);
        String s = name.split("@")[0];
        //打印当前Pid
        System.out.println("pid:" + s);
        while (true) {
            try {
                Thread.sleep(5000L);
            } catch (Exception e) {
                break;
            }
            process();
        }
    }

    public static void process() {
        System.out.println("process");
    }
}
