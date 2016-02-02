package storm.trident;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Fields;
import storm.trident.spout.ITridentSpout;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Tong on 2016/2/2.
 */
public class DiagnosisEventSpout implements ITridentSpout<Long>,Serializable {

    private SpoutOutputCollector collector;
    private BatchCoordinator<Long> coordinator=new DefaultCoordinator();
    private Emitter<Long> emitter=new DiagnosisEmmiter();

    @Override
    public BatchCoordinator<Long> getCoordinator(String s, Map map, TopologyContext topologyContext) {
        return coordinator;
    }

    @Override
    public Emitter<Long> getEmitter(String s, Map map, TopologyContext topologyContext) {
        return emitter;
    }

    @Override
    public Map getComponentConfiguration() {
        return null;
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("event");
    }
}
