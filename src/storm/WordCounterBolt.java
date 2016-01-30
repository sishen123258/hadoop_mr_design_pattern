package storm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 1/30/16.
 */
public class WordCounterBolt implements IRichBolt {

    Integer id;
    String name;
    Map<String,Integer> counters;
    private OutputCollector collector;


    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector=collector;
        this.counters=new HashMap<String,Integer>();
        this.name=context.getThisComponentId();
        this.id=context.getThisTaskId();
    }

    @Override
    public void execute(Tuple input) {
        String str=input.toString();
        if(!counters.containsKey(str)){
            counters.put(str,1);
        }else {
            Integer count = counters.get(str)+1;
            counters.put(str,count);
        }
        collector.ack(input);
    }

    @Override
    public void cleanup() {
        System.out.println("Word counter ["+name+"-"+id+"] --");
        for (Map.Entry<String,Integer> entry:counters.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
