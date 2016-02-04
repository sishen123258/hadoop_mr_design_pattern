package storm.realtime.basefunction;

import backtype.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

/**
 * Created by Tong on 2016/2/4.
 */
public class ThresholdFilterFunction extends BaseFunction {
    private static final Logger LOG = LoggerFactory.getLogger(ThresholdFilterFunction.class);
    private static enum State{
        BLOW,ABOVE
    }

    private State state=State.BLOW;
    private double threshHold;

    public ThresholdFilterFunction(double threshHold) {
        this.threshHold = threshHold;
    }

    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        double val=tuple.getDouble(0);
        State newState =this.threshHold>val?State.BLOW:State.ABOVE;
        boolean stateChanged=this.state!=newState;
        collector.emit(new Values(stateChanged,this.threshHold));
        LOG.debug("State change? --> {}", stateChanged);
    }
}
