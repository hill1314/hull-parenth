package com.hull.test.proxy.jdk;

/**
 *
 *
 * @author
 * @create 2018-08-04 上午11:58
 **/

public class LiuDeHua implements Singer {

    private String name;

    public LiuDeHua(String name){
        this.name = name;
    }

    public String sing(){
        System.out.println(name + " 在唱歌...");
        return "success";
    }

    public String dance(){
        System.out.println(name + " 在跳舞...");
        return "success";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
