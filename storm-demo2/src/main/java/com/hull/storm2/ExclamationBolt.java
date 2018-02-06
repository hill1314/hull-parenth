package com.hull.storm2;

import com.hull.storm2.service.TestService;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2018-01-08 上午9:58
 **/

public class ExclamationBolt extends BaseRichBolt{
//    Logger logger = LoggerFactory.getLogger(getClass());

    OutputCollector _collector;
    TestService testService;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
        testService = (TestService) SpringBeanUtil.getBeanByName("testService");
    }

    @Override
    public void execute(Tuple input) {
        String value = input.getString(0) + "!!!";
        testService.print(value);
//        logger.info("ExclamationBolt deal======"+value);
        _collector.emit(input, new Values(value));
        _collector.ack(input);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
