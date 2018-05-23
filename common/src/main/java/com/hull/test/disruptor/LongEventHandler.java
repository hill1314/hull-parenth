package com.hull.test.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 事件处理 （客户端）
 *
 * @author
 * @create 2018-05-23 下午4:36
 **/

public class LongEventHandler implements EventHandler<LongEvent>
{
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch)
    {
        System.out.println("sequence="+sequence+", 处理事件 Event: " + event.toString()+"");
    }
}
