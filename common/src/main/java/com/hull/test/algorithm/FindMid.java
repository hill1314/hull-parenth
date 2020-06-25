package com.hull.test.algorithm;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author
 * @create 2019-12-19 14:14
 **/

public class FindMid {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        //num2 所有的元素都大于num1
        if(nums1[nums1.length-1]<nums2[0]){

        }

        //num1 所有的元素都大于num2
        if(nums1[0]>nums2[nums2.length-1]){

        }



        boolean ou1 = nums1.length%2==0;
        boolean ou2 = nums2.length%2==0;

        int mid1=0,mid2=0;

        if(ou1){
            mid1 = nums1[nums1.length-1];
        }

        return 0;

    }

    public static void main(String[] args) {

    }
}
