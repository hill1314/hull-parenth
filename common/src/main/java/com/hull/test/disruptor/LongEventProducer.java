package com.hull.test.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 事件生产者 （来源）
 *
 * @author
 * @create 2018-05-23 下午4:37
 **/

public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb)
    {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try{
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            Long val = bb.getLong(0);
            System.out.println("生成数据："+val);
            event.set(val);  // Fill with data
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
