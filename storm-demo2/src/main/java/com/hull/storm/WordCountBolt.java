package com.hull.storm;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统计单词
 * @author zhengcy
 *
 */
@SuppressWarnings("serial")
public class WordCountBolt extends BaseBasicBolt {
    Logger logger = LoggerFactory.getLogger(getClass());

    private  Map<String, Long> counts = null;

    @SuppressWarnings("rawtypes")
    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        this.counts = new HashMap<String, Long>();
    }

    @Override
    public void cleanup() {
        //拓扑结束执行
        for (String key : counts.keySet()) {
            System.out.println(key + " : " + this.counts.get(key));
        }
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String word = input.getStringByField("word");
        logger.info("WordCountBolt get word==========="+word);
        Long count = this.counts.get(word);
        if (count == null) {
            count = 0L;
        }
        count++;
        this.counts.put(word, count);
        logger.info("result======="+word+" : "+count);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

}
