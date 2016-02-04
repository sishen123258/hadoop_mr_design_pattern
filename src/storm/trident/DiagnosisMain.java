package storm.trident;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import storm.trident.utils.OutbreakDetectionTopology;

/**
 * Created by Tong on 2016/2/2.
 */
public class DiagnosisMain {

    public static void main(String[] args) {
        Config config=new Config();
        LocalCluster cluster=new LocalCluster();
        cluster.submitTopology("cdc",config,OutbreakDetectionTopology.buildTopology());
    }
}
