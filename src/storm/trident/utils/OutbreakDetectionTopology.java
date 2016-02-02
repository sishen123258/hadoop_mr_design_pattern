package storm.trident.utils;

import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import storm.trident.Stream;
import storm.trident.TridentTopology;
import storm.trident.aggregator.Count;
import storm.trident.basefunction.CityAssignment;
import storm.trident.basefunction.DispatchAlert;
import storm.trident.basefunction.HourAssignment;
import storm.trident.basefunction.OutbreakDetector;
import storm.trident.factory.OutbreakTrendFactory;
import storm.trident.filter.DiseaseFilter;
import storm.trident.stream_src.DiagnosisEventSpout;

/**
 * Created by Tong on 2016/2/2.
 */
public class OutbreakDetectionTopology {


    public static StormTopology buildTopology(){
        TridentTopology topology=new TridentTopology();
        DiagnosisEventSpout diagnosisEventSpout=new DiagnosisEventSpout();
        Stream inputStream=topology.newStream("event",diagnosisEventSpout);

        inputStream.each(new Fields("event"),new DiseaseFilter())
                .each(new Fields("event"),new CityAssignment(),new Fields("city"))
                .each(new Fields("city"),new HourAssignment(),new Fields("hour","cityDiseaseHour"))
                .groupBy(new Fields("cityDiseaseHour"))
                .persistentAggregate(new OutbreakTrendFactory(),new Count(),new Fields("count"))
                .newValuesStream()
                .each(new Fields("cityDiseaseHour","count"),new OutbreakDetector(),new Fields("alert"))
                .each(new Fields("alert"),new DispatchAlert(),new Fields());

        return topology.build();
    }

}
