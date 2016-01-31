package storm.first;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * Created by root on 1/30/16.
 */
public class StormMain  {

    public static void main(String[] args) throws InterruptedException {


        args=new String[]{"/opt/hadoop/hadoop-2.5.2/REDEME.txt"};
        //1 TOPOLOGY definition
        TopologyBuilder builder=new TopologyBuilder();
        builder.setSpout("word-reader",new StreamWordReaderSpout());
        builder.setBolt("word-normalizer",new WordNormalizerBolt())
                .shuffleGrouping("word-reader");
        builder.setBolt("word-counter",new WordCounterBolt())
                .fieldsGrouping("word-normalizer",new Fields("word"));

        //2 configuration
        Config config=new Config();
        config.put("wordsFile",args[0]);
        config.setDebug(false);

        //Topo run
        config.put(Config.TOPOLOGY_MAX_SPOUT_PENDING,1);

        LocalCluster cluster=new LocalCluster();
        cluster.submitTopology("Storm Hello Word",config,builder.createTopology());
        Thread.sleep(1000);
        cluster.shutdown();







    }


}
