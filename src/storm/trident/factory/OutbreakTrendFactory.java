package storm.trident.factory;

import backtype.storm.task.IMetricsContext;
import storm.trident.bean.OutbreakTrendBackingMap;
import storm.trident.state.OutbreakTrendState;
import storm.trident.state.State;
import storm.trident.state.StateFactory;

import java.util.Map;

/**
 * Created by Tong on 2016/2/2.
 */
public class OutbreakTrendFactory implements StateFactory{



    @Override
    public State makeState(Map conf, IMetricsContext metrics, int partitionIndex, int numPartitions) {
        //the state is used by storm to persist information
        return new OutbreakTrendState(new OutbreakTrendBackingMap());
    }
}
