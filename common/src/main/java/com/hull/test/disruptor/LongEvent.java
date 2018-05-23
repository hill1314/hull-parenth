package com.hull.test.disruptor;

/**
 *
 *
 * @author
 * @create 2018-05-23 下午4:33
 **/

public class LongEvent {

    private long value;

    public void set(long value)
    {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
