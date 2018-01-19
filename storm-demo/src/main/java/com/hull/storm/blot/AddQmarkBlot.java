package com.hull.storm.blot;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 加问号
 *
 * @author
 * @create 2018-01-06 下午7:57
 **/

public class AddQmarkBlot extends BaseRichBolt{
    public static Logger logger = LoggerFactory.getLogger(AddQmarkBlot.class);

    private OutputCollector collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        String word = input.getString(0);
        logger.info("receive:"+word);
        collector.emit(new Values(word+"???"));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        //定义了传到下一个bolt的字段描述
        declarer.declare(new Fields("qWord"));
    }

}
