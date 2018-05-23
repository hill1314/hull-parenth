package com.hull.test.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2018-05-23 下午4:34
 **/

public class LongEventFactory implements EventFactory<LongEvent>
{
    public LongEvent newInstance()
    {
        return new LongEvent();
    }
}
