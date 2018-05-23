package com.hull.test.java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。

 Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。

 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。

 元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。

 +--------------------+       +------+   +------+   +---+   +-------+
 | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
 +--------------------+       +------+   +------+   +---+   +-------+
 *
 *
 【数据源】 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
 【聚合操作】 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。


 * @author
 * @create 2018-04-22 下午3:18
 **/

public class JDK8_5_StreamApiTest {
    public static void main(String[] args) {

//        createStream();
//        mapTest();
//        filterTest();
//        limitTest();
//        parallelTest();
//        collectorsTest();
        summaryTest();


    }

    /**
     * 一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，
     * 它们可以用来产生类似如下的统计结果
     */
    private static void summaryTest() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream()
                .mapToInt((x) -> x)
                .summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

    /**
     * Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。
     * Collectors 可用于返回列表或字符串
     */
    private static void collectorsTest() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream()
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream()
                .filter(string -> !string.isEmpty())
                .collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }

    /**
     * parallelStream 是流并行处理程序的代替方法。
     * 以下实例我们使用 parallelStream 来输出空字符串的数量
     */
    private static void parallelTest() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count);
    }

    /**
     * 'forEach' 来迭代流中的每个数据
     * limit 方法用于获取指定数量的流
     * sorted 方法用于对流进行排序
     */
    private static void limitTest() {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    /**
     * filter 方法用于通过设置的条件过滤出元素
     */
    private static void filterTest() {
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);
    }

    /**
     * map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数
     */
    private static void mapTest() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream()
                .map( i -> i*i)
                .distinct()
                .collect(Collectors.toList());
        squaresList.forEach(System.out::println);
    }

    /**
     * 生成流
     stream() − 为集合创建串行流
     parallelStream() − 为集合创建并行流
     */
    private static void createStream() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream()
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

    


}
