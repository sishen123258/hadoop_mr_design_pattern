package storm.sentence;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root on 1/31/16.
 */
public class SentenceSpout extends BaseRichSpout{

    private SpoutOutputCollector collector;
    private ConcurrentHashMap<UUID,Values> pindings;

    private String[] sentences={
            "My dog is fleas",
            "i like breads",
            "the dog ate my homework",
            "i don't have a cow man",
            "i don't think i like fleas"
    };
    private int index=0;


    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("sentence"));
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector=spoutOutputCollector;
        this.pindings=new ConcurrentHashMap<>();
    }

    @Override
    public void nextTuple() {
        Values values = new Values(sentences[index]);
        UUID msgId=UUID.randomUUID();
        this.pindings.put(msgId,values);
        this.collector.emit(values,msgId);
        index++;
        if (index>=sentences.length){
            index=0;
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void ack(Object msgId) {
        this.pindings.remove(msgId);
    }

    @Override
    public void fail(Object msgId) {
        this.collector.emit(this.pindings.get(msgId),msgId);
    }
}
