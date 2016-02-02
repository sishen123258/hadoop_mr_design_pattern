package storm.trident.basefunction;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import java.util.ArrayList;

/**
 * Created by Tong on 2016/2/2.
 */
public class OutbreakDetector extends BaseFunction {
    private static final int THRESHOLD=1000;

    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {

        String key= (String) tuple.getValue(0);
        Long count= (Long) tuple.getValue(1);
        if(count>THRESHOLD){
            ArrayList<Object> values=new ArrayList<>();
            values.add("Outbreak detected for ["+key+"]");
            collector.emit(values);
        }

    }
}
