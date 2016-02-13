package storm.realtime.basefunction;

import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import org.json.simple.JSONValue;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import java.util.Map;

/**
 * Created by Tong on 2016/2/4.
 */
public class JsonProjectFunction  extends BaseFunction{

    private Fields fields;

    public JsonProjectFunction(Fields fields) {
        this.fields = fields;
    }

    /**
     * When the function receives a tuple, it will parse the JSON in the tuple's str field, iterate the
     * Fields object's values, and emit the corresponding value from the input JSON.
     * @param tuple
     * @param collector
     */
    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        String json=tuple.getString(0);
        Map<String,Object> map = (Map<String, Object>) JSONValue.parse(json);
        Values values =new Values();
        for(int i=0;i<this.fields.size();i++){
            values.add(map.get(this.fields.get(i)));
        }
        collector.emit(values);
    }
}
