package com.hull.storm2;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2018-01-08 上午9:54
 **/

public class TestWordSpout extends BaseRichSpout{
    Logger logger = LoggerFactory.getLogger(getClass());

    SpoutOutputCollector _collector;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        _collector = collector;
    }

    @Override
    public void nextTuple() {
        Utils.sleep(1000);
//        final String[] words = new String[] {"nathan", "mike", "jackson", "golda", "bertels"};
//        final Random rand = new Random();
//        final String word = words[rand.nextInt(words.length)];
        logger.info("请输入:");
        String word = "";
        try {
            Scanner scan = new Scanner(System.in);
            word = scan.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("spout word======="+word);
        _collector.emit(new Values(word));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
