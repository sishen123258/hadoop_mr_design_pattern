package storm.realtime;

import backtype.storm.spout.MultiScheme;
import backtype.storm.tuple.Fields;
import storm.kafka.KafkaConfig;
import storm.kafka.StringScheme;
import storm.kafka.trident.OpaqueTridentKafkaSpout;
import storm.kafka.trident.TridentKafkaConfig;
import storm.realtime.basefunction.EWMA;
import storm.realtime.basefunction.JsonProjectFunction;
import storm.realtime.basefunction.MovingAverageFunction;
import storm.trident.Stream;
import storm.trident.TridentTopology;

import java.util.Arrays;

/**
 * Created by Tong on 2016/2/4.
 */
public class Main {
    public static void main(String[] args) {
        TridentTopology topology = new TridentTopology();
        //create a list of Kafka hosts with which to connect
        KafkaConfig.StaticHosts kafkaHosts = KafkaConfig.StaticHosts.
                fromHostString(Arrays.asList(new String[] { "localhost" }), 1);
        //pass the hosts list and the unique identify
        TridentKafkaConfig spoutConf = new TridentKafkaConfig(kafkaHosts, "log-analysis");
        spoutConf.scheme = (MultiScheme) new StringScheme();
        spoutConf.forceStartOffsetTime(-1);

        OpaqueTridentKafkaSpout spout=new OpaqueTridentKafkaSpout(spoutConf);
        Stream stream=topology.newStream("kafka-stream",spout);

        Fields jsonFields = new Fields("level", "timestamp","message", "logger");
        stream.each(new Fields("str"),new JsonProjectFunction(jsonFields),jsonFields);

        EWMA ewma = new EWMA().sliding(1.0, EWMA.Time.MINUTES).withAlpha(EWMA.ONE_MINUTE_ALPHA);
        Fields timestamp = new Fields("timestamp");
        stream.each(timestamp,new MovingAverageFunction(ewma, EWMA.Time.MINUTES),timestamp);

    }
}
