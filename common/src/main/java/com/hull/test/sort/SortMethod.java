package com.hull.test.sort;

import java.util.Arrays;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2019-12-07 12:24
 **/

public class SortMethod {

    /**
     * 快速排序
     *
     * 基本思想：（分治）
     * （1）先从数列中取出一个数作为key值；
     * （2）将比这个数小的数全部放在它的左边，大于或等于它的数全部放在它的右边；
     * （3）对左右两个小数列重复第二步，直至各区间只有1个数
     *
     * @param a
     * @param l
     * @param r
     */
    public static void quickSort(int a[],int l,int r){
        if(l>=r)
            return;

        int i = l; int j = r; int key = a[l];//选择第一个数为key

        while(i<j){
            while(i<j && a[j]>=key)//从右向左找第一个小于key的值
                j--;
            if(i<j){
                a[i] = a[j];
                i++;
            }

            while(i<j && a[i]<key)//从左向右找第一个大于key的值
                i++;

            if(i<j){
                a[j] = a[i];
                j--;
            }
        }
        //i == j
        a[i] = key;
        quickSort(a, l, i-1);//递归调用
        quickSort(a, i+1, r);//递归调用
    }


    public static void quickSort2(int a[],int l,int r){
        if(l>=r){
            return;
        }

        int i = l;int j=r; int key = a[l];

        while(i<j){
            while (i<j && a[j]>=key)
                j--;
            if(r<l){
                a[i] = a[j];
                i++;
            }

            while (i<j && a[i]<key)
                i++;

            if(r<l){
                a[j] = a[i];
                j--;
            }
        }

        a[i]=key;
        quickSort2(a,l,i-1);
        quickSort2(a,i+1,r);

    }



    public static void main(String[] args) {
        int[] array = {4,2,1,16,8,2,18,10};
        quickSort(array,0,7);
        System.out.println(array);
        int[] array2 = {4,2,1,16,8,2,18,10};
        quickSort2(array2,0,7);
        System.out.println(array2);
    }


}
