package storm.trident.basefunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.bean.DiagnosisEvent;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tong on 2016/2/2.
 */
public class CityAssignment  extends BaseFunction{
    //Function:they replace tuple fields and values
    private static final Logger LOGGER= LoggerFactory.getLogger(CityAssignment.class);
    private static Map<String, double[]> CITIES = new HashMap<String, double[]>();

    { // Initialize the cities we care about.
        double[] phl = { 39.875365, -75.249524 };
        CITIES.put("PHL", phl);
        double[] nyc = { 40.71448, -74.00598 };
        CITIES.put("NYC", nyc);
        double[] sf = { -31.4250142, -62.0841809 };
        CITIES.put("SF", sf);
        double[] la = { -34.05374, -118.24307 };
        CITIES.put("LA", la);
    }


    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        DiagnosisEvent diagnosis= (DiagnosisEvent) tuple.getValue(0);
        double leastDistance = Double.MAX_VALUE;
        String closestCity = "NONE";

        // Find the closest city.
        for (Map.Entry<String, double[]> city : CITIES.entrySet()) {
            double R = 6371; // km
            double x = (city.getValue()[0] - diagnosis.lng) *
                    Math.cos((city.getValue()[0] + diagnosis.lng) / 2);
            double y = (city.getValue()[1] - diagnosis.lat);
            double d = Math.sqrt(x * x + y * y) * R;
            if (d < leastDistance) {
                leastDistance = d;
                closestCity = city.getKey();
            }
        }
        ArrayList<Object> values=new ArrayList<>();
        values.add(closestCity);
        collector.emit(values);
    }
}
