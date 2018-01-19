package com.hull.storm.topology;

import com.hull.storm.blot.AddEmarkBlot;
import com.hull.storm.blot.AddQmarkBlot;
import com.hull.storm.spout.WordGeneratorSpot;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.ConfigurableTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单词后面加 叹号和问号
 *
 * @author
 * @create 2018-01-06 下午7:45
 **/

public class RollingTopWords extends ConfigurableTopology {

    private static final Logger LOG = LoggerFactory.getLogger(RollingTopWords.class);
    private static final int TOP_N = 5;

    private RollingTopWords() {
    }

//    public static void main(String[] args) throws Exception {
//        ConfigurableTopology.start(new RollingTopWords(), args);
//    }


    @Override
    protected int run(String[] args) throws Exception {

        String topologyName = "slidingWindowCounts";
        if (args.length >= 1) {
            topologyName = args[0];
        }

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new WordGeneratorSpot(), 1);

        builder.setBolt("eBlot", new AddEmarkBlot(), 2).shuffleGrouping("spout");
        builder.setBolt("qBlot", new AddQmarkBlot(), 2).shuffleGrouping("eBlot");

        LOG.info("Topology name: " + topologyName);

        return submit(topologyName, conf, builder);
    }





}
