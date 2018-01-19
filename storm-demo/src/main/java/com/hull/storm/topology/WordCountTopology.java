package com.hull.storm.topology;

import com.hull.storm.spout.SentenceSpout;
import com.hull.storm.blot.SplitSentenceBolt;
import com.hull.storm.blot.WordCountBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * 单词统计拓扑
 * @author zhengcy
 *
 */
public class WordCountTopology {

//    public static void main(String[] args) throws Exception {
//        run(0);
//    }

    public static void run(int type) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new SentenceSpout(), 1);

        builder.setBolt("split", new SplitSentenceBolt(), 2).shuffleGrouping("spout");
        builder.setBolt("count", new WordCountBolt(), 2).fieldsGrouping("split", new Fields("word"));

        Config conf = new Config();
        conf.setDebug(true);

        if (type==1) {
            // 集群模式
            conf.setNumWorkers(2);
            StormSubmitter.submitTopology("name", conf, builder.createTopology());
        } else {
            // 本地模式
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("word-count", conf, builder.createTopology());
            Thread.sleep(10000);
            cluster.shutdown();
        }
    }
}
