package storm.trident.basefunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.bean.DiagnosisEvent;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tong on 2016/2/2.
 */
public class HourAssignment extends BaseFunction{

    private static final Logger LOGGER= LoggerFactory.getLogger(HourAssignment.class);

    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        DiagnosisEvent diagnosis = (DiagnosisEvent) tuple.getValue(0);
        String city = (String) tuple.getValue(1);
        long timestamp = diagnosis.time;
        long hourSinceEpoch = timestamp / 1000 / 60 / 60;
        LOGGER.debug("Key = [" + city + ":" + hourSinceEpoch + "]");
        String key = city + ":" + diagnosis.diag + ":" +
                hourSinceEpoch;
        List<Object> values = new ArrayList<Object>();
        values.add(hourSinceEpoch);
        values.add(key);
        collector.emit(values);
    }
}
