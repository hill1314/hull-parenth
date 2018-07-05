package com.hull.test.mycollection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.hash;

/**
 *
 *
 * @author
 * @create 2018-07-02 下午3:04
 **/

public class MyHashMap implements Map{

    public static class Node<K,V>{
        private K key;
        private V value;
        private int hashVal;
        private Node<K,V> next;

        public Node(K key,V value,int hashVal,Node next){
            this.key = key;
            this.value = value;
            this.hashVal = hashVal;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public int getHashVal() {
            return hashVal;
        }

        public void setHashVal(int hashVal) {
            this.hashVal = hashVal;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }

    private Node[] tables;
    private int size;
    private int capacity;
    private float factor;

    public MyHashMap(){
        this(16);
    }

    public MyHashMap(int capacity){
        this(capacity,0.75f);
    }

    public MyHashMap(int capacity,float factor){
        this.capacity = capacity;
        this.factor = factor;
        this.size = 0 ;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {


        return null;
    }

    @Override
    public Object put(Object key, Object value) {

        //key为null 放第一个位置
        if(key==null){
            tables[0] = null;
            size++;
            return value;
        }

        if(this.size+1>this.capacity*this.factor){
            //如果容量超过阈值，进行扩容
            resize();
        }

        int hashVal = hash(key);
        Node node = new Node(key,value,hashVal,null);
        //获取数组坐标
        int index = hashVal%capacity;
        if(tables[index]==null){
            tables[index] = node;
        }else {
            //链表长度小于8，是单向链表
            tables[index].next = node;
            tables[index] = node;
            //长度大于8，是红黑树

        }
        size++;
        return value;
    }

    private void resize() {
        this.capacity = this.capacity*2;
        //之前的node 需要重新分配
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
