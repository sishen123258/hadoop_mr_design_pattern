package storm.sentence;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 1/31/16.
 */
public class WordContBolt extends BaseRichBolt {

    private OutputCollector collector;
    private Map<String,Integer> counts;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector=outputCollector;
        counts=new HashMap<>();
    }

    @Override
    public void execute(Tuple tuple) {
        String word=tuple.getStringByField("word");
        Integer count=counts.get(word);
        if(count==null){
            count=0;
        }
        count++;
        counts.put(word,count);
        this.collector.emit(tuple,new Values(word,count));
//        this.collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word","count"));
    }
}
