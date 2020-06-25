package com.hull.test.sort;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2019-12-10 16:45
 **/

public class Find {

    public static void main(String[] args) {

        int[] array = {4,2,7,16,8,2,18,10};

        findSecond(array);

    }

    /**
     * 找出第二大的数
     * @param array
     */
    private static void findSecond(int[] array) {
        int m1=array[0],m2=array[1];
        int temp;

        for(int i=2;i<array.length;i++){
            if(m2>m1){
                temp = m1;
                m1 = m2;
                m2 = temp;
            }
            if(array[i]>m1){
                m2=m1;
                m1=array[i];
            }else if(array[i]>m2){
                m2=array[i];
            }
        }
        System.out.println("第二大的："+m2);
    }

}
