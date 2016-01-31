package storm.sentence;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.*;

/**
 * Created by root on 1/31/16.
 */
public class ReportBolt extends BaseRichBolt {

    private Map<String,Integer> counts;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        counts=new HashMap<>();
    }

    @Override
    public void execute(Tuple tuple) {
        String word=tuple.getStringByField("word");
        Integer count=tuple.getIntegerByField("count");
        this.counts.put(word,count);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public void cleanup() {
        System.out.println("--- FINAL COUNTS ---");
        List<String> keys=new ArrayList<>();
        keys.addAll(counts.keySet());
        Collections.sort(keys);
        for (String key:keys){
            System.out.println("key = " + key+" count="+counts.get(key));
        }

        System.out.println("--- FINAL COUNTS OVER ---");


    }


}
