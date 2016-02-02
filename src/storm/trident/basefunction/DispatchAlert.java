package storm.trident.basefunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

/**
 * Created by Tong on 2016/2/2.
 */
public class DispatchAlert extends BaseFunction{

    private static final Logger LOGGER= LoggerFactory.getLogger(DispatchAlert.class);



    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        String alert= (String) tuple.getValue(0);
        LOGGER.error("ALERT RECEIVED [" + alert + "]");
        LOGGER.error("Dispatch the national guard!");
        System.exit(0);
    }
}
