package storm.realtime.basefunction;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

/**
 * Created by Tong on 2016/2/4.
 */
public class JsonProjectFunction  extends BaseFunction{

    private String fields;

    public JsonProjectFunction(String fields) {
        this.fields = fields;
    }

    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {

    }
}
