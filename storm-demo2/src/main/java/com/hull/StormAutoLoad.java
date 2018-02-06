package com.hull;

import com.hull.storm2.ExclamationBolt;
import com.hull.storm2.TestWordSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * spring 自动加载storm
 *
 * @author
 * @create 2018-01-20 下午6:50
 **/

//@Configuration
//@Component
public class StormAutoLoad {
//public class StormAutoLoad implements ApplicationListener<ContextRefreshedEvent> {

//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        try {
//            //实例化topologyBuilder类。
//            TopologyBuilder builder = new TopologyBuilder();
//
//            builder.setSpout("spout", new TestWordSpout(), 1);
//            builder.setBolt("exclaim1", new ExclamationBolt(), 2).shuffleGrouping("spout");
//            builder.setBolt("exclaim2", new ExclamationBolt(), 2).shuffleGrouping("exclaim1");
//
//            Config config = new Config();
//            config.setDebug(false);
//            /*设置该topology在storm集群中要抢占的资源slot数，一个slot对应这supervisor节点上的以个worker进程
//             如果你分配的spot数超过了你的物理节点所拥有的worker数目的话，有可能提交不成功，加入你的集群上面已经有了
//             一些topology而现在还剩下2个worker资源，如果你在代码里分配4个给你的topology的话，那么这个topology可以提交
//             但是提交以后你会发现并没有运行。 而当你kill掉一些topology后释放了一些slot后你的这个topology就会恢复正常运行。
//            */
//            config.setNumWorkers(1);
//            LocalCluster cluster = new LocalCluster();
//            cluster.submitTopology("spring-spout", config, builder.createTopology());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
