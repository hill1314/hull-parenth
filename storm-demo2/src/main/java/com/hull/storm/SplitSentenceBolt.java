package com.hull.storm;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 切割句子
 * @author zhengcy
 *
 */
@SuppressWarnings("serial")
public class SplitSentenceBolt extends BaseBasicBolt {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        //定义了传到下一个bolt的字段描述
        declarer.declare(new Fields("word"));
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String sentence = input.getStringByField("sentence");
        logger.info("SplitSentenceBolt get sentence========="+sentence);
        String[] words = sentence.split(" ");
        for (String word : words) {
            logger.info("SplitSentenceBolt emit word========="+word);
            //发送单词
            collector.emit(new Values(word));
        }
    }
}
