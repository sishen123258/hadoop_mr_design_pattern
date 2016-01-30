package storm;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by root on 1/30/16.
 */
public class StreamWordReaderSpout implements IRichSpout{

    private SpoutOutputCollector collector;
    private FileReader fileReader;
    private boolean complete=false;
    private TopologyContext context;


    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("line"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.context=context;
        try {
            this.fileReader=new FileReader(conf.get("wordsFile").toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.collector=collector;
    }

    @Override
    public void close() {
        System.out.println("spot close");
    }

    @Override
    public void activate() {
        System.out.println("spot activate");
    }

    @Override
    public void deactivate() {
        System.out.println("spot deactivate");
    }

    @Override
    public void nextTuple() {

        if(complete){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        String str;
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        try {
            while ((str=bufferedReader.readLine())!=null){
                this.collector.emit(new Values(str),str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            complete=true;
        }

    }

    @Override
    public void ack(Object msgId) {
        System.out.println("OK = [" + msgId + "]");
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("FAIL = [" + msgId + "]");
    }
}
