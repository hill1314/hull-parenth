package com.hull.test.algorithm;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * @author
 * @create 2019-12-19 10:07
 **/

public class Solution {
    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        StringBuffer str = new StringBuffer();
        StringBuffer bak = new StringBuffer();

        str.append(s.charAt(0));
        for(int i=1;i<length;i++){
            if(!str.toString().contains(s.charAt(i)+"")){
                str.append(s.charAt(i));
            }else{
                int index = str.toString().indexOf(s.charAt(i));
                if(i-index>1){
                    if(bak.length()<str.length()){
                        bak = new StringBuffer(str.toString());
                    }
                    //清空str,重新拼接str
                    str = new StringBuffer(str.substring(index+1));
                    str.append(s.charAt(i));
                }else {
                    if(bak.length()<str.length()){
                        bak = new StringBuffer(str.toString());
                        //清空str,重新拼接str
                        str.delete(0,str.length());
                        str.append(s.charAt(i));
                    }
                }
            }
        }

        return str.length()>bak.length()?str.length():bak.length();
    }

    public static void main(String[] args) {
//        String s = "dvdf";
        String s = "abcabcbb";
//        String s = "pwwkewabcdwab";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
