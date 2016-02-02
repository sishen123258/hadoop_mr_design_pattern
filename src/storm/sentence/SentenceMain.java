package storm.sentence;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * Created by root on 1/31/16.
 */
public class SentenceMain {

    private static final String SENTENCE_SPOUT_ID = "sentence-spout";
    private static final String SPLIT_BOLT_ID = "split-bolt";
    private static final String COUNT_BOLT_ID = "count-bolt";
    private static final String REPORT_BOLT_ID = "report-bolt";
    private static final String TOPOLOGY_NAME = "word-count-topology";

    public static void main(String[] args) throws InterruptedException {
        SentenceSpout sentenceSpout=new SentenceSpout();
        SplitSentenceBolt splitSentenceBolt=new SplitSentenceBolt();
        WordContBolt wordContBolt=new WordContBolt();
        ReportBolt reportBolt=new ReportBolt();

        TopologyBuilder topologyBuilder=new TopologyBuilder();

        topologyBuilder.setSpout(SENTENCE_SPOUT_ID,sentenceSpout,2); //2 spout executors
        topologyBuilder.setBolt(SPLIT_BOLT_ID,splitSentenceBolt,2).setNumTasks(4).
                shuffleGrouping(SENTENCE_SPOUT_ID);//2 split(thread) bolt 4 task ,
        topologyBuilder.setBolt(COUNT_BOLT_ID,wordContBolt,4).
                fieldsGrouping(SPLIT_BOLT_ID,new Fields("word"));
        topologyBuilder.setBolt(REPORT_BOLT_ID,reportBolt).globalGrouping(COUNT_BOLT_ID);

        Config config=new Config();
        //Nodes work(jvms) executors(thread) task(spout,bout)
//        config.setNumWorkers(2);  //two works

        LocalCluster cluster=new LocalCluster();

        cluster.submitTopology(TOPOLOGY_NAME,config,topologyBuilder.createTopology());

        Thread.sleep(10000);

        cluster.killTopology(TOPOLOGY_NAME);
        cluster.shutdown();




    }


}
