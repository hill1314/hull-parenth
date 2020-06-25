package com.hull.test.sort;

/**
 *
 *
 * @author
 * @create 2019-12-10 23:00
 **/

public class BinarySearch {

    public static void main(String[] args) {     // write your code here
        System.out.print("二分法算法 :");
        int[] arr = {1, 3, 5, 7, 9, 11};
        int value = 7;
        int index = binarySearch(arr, arr.length, value);
        System.out.println("算法 :" + index);

        System.out.println("我找的对吗:"+search(arr,value));
    }

    //二分法排序
    static int binarySearch(int[] array, int size, int value) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            final int mid = (lo + hi) >>> 1;
            final int midVal = array[mid];
            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal > value) {
                hi = mid - 1;
            } else {
                return mid;  // value found
            }
        }
        return ~lo;  // value not present
    }



    public static int search(int[] arr,int key){
        int length = arr.length;
        int b = 0;
        int e = length-1;

        while (b<e){
            int mid = (e+b)/2; //TODO
            int midVal = arr[mid];
            if(midVal==key){
                return mid;
            }else if(midVal>key){
                e = mid-1;
            }else {
                b = mid+1;
            }
        }

        return -1;

    }

}
