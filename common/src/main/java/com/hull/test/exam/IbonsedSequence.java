package com.hull.test.exam;

import com.google.common.collect.Maps;

import java.math.BigInteger;
import java.util.Map;

/**
 *
 * 斐波那契数列(ibonsed sequence).又称黄金分割数列，因以
 兔子繁殖例子引入.故又称兔子数列，指的是这样一个敷列:0 、1、1、2 ,3、5
 、8、13、21、34......，在数学上髪波納契数列以如下被以遊怐的方法を乂F（0）=0，F(1)=1,
 F(n)=F(n-1)+F(n-2)   (n>=2)，用lava语言安現一个斐波那契数列.并輪出前10000个数的值
 *
 * @author
 * @create 2018-06-29 上午10:33
 **/

public class IbonsedSequence {
    public static Map<String,Long> fMap = Maps.newConcurrentMap();

    public static void main(String[] args) {
//        fun(100);
//        fun2(10000);
        funForBig(100);
    }

    public static Long fun(int n){
        Long r ;
        if(fMap.get(""+n)!=null){
            r = fMap.get(""+n);
            return r;
        }
        if(n==0){
            r = 0L;
        }else if(n==1){
            r=1L;
        }else {
            r = fun(n-1)+fun(n-2);
        }
        fMap.put(""+n,r);
        System.out.println("f("+n+")="+r);
        return r;
    }

    public static Long fun2(int n){
        Long r = 0L;
        if(n==0){
            r = 0L;
        }else if(n==1){
            r=1L;
        }else {
            Long a=0L,b=1L;
            for(int i =2; i <= n; i++){
                r = a + b ;
                a = b;
                b = r;
                System.out.println("f("+i+")="+r);
            }
        }
        System.out.println("f("+n+")="+r);
        return r;
    }

    public static BigInteger funForBig(int n){
        BigInteger r = BigInteger.valueOf(0L);
        if(n==0){
            r = BigInteger.valueOf(0L);
        }else if(n==1){
            r=BigInteger.valueOf(1L);
        }else {
            BigInteger a=BigInteger.valueOf(0L),b=BigInteger.valueOf(1L);
            for(int i =2; i <= n; i++){
                r = a.add(b);
                a = b;
                b = r;
                System.out.println("f("+i+")="+r);
            }
        }
        System.out.println("f("+n+")="+r);
        return r;
    }

}
