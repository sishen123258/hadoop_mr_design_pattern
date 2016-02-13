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
public class MovingAverageFunction extends BaseFunction {

    private static final Logger LOG = LoggerFactory.getLogger(BaseFunction.class);

    private EWMA ewma;
    private EWMA.Time emitRatePer;
    public MovingAverageFunction(EWMA ewma, EWMA.Time time) {
        this.emitRatePer =time;
        this.ewma=ewma;
    }

    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        this.ewma.mark(tuple.getLong(0));
        LOG.debug("Rate: {}", this.ewma.getAverageRatePer(this.emitRatePer));

        //在此处通过外面的类进行数据处理
        collector.emit(new Values(this.ewma.getAverageRatePer(this.emitRatePer)));
    }
}
