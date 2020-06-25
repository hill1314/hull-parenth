package com.hull.test.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author
 * @create 2019-12-30 15:10
 **/

public class ListTest {

//    @Test

    public static void main(String[] args) {
        listToStr();
    }

    public static void listToStr(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        //list 2 strs
        String[] strs = list.toArray(new String[0]);
        //strs 2 list
        Arrays.asList(strs);
        System.out.println(strs.toString());
    }
}
