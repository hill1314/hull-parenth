package com.hull.storm2;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2018-01-08 上午9:53
 **/

public class ExclamationTopology {

    public static void main(String[] args) throws Exception {

        ApplicationContext springContext = new ClassPathXmlApplicationContext("spring-demo.xml");
        SpringBeanUtil.setContext(springContext);

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new TestWordSpout(), 1);
        builder.setBolt("exclaim1", new ExclamationBolt(), 2).shuffleGrouping("spout");
        builder.setBolt("exclaim2", new ExclamationBolt(), 2).shuffleGrouping("exclaim1");

        Config conf = new Config();
        conf.setDebug(true);

        if (args != null && args.length > 0) {
            // 集群模式
            conf.setNumWorkers(2);
            StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
        } else {
            // 本地模式
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("exclamation", conf, builder.createTopology());
            Thread.sleep(10000);
//            cluster.shutdown();
        }
    }
}
