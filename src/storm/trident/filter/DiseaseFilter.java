package storm.trident.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.bean.DiagnosisEvent;
import storm.trident.operation.BaseFilter;
import storm.trident.tuple.TridentTuple;

/**
 * Created by Tong on 2016/2/2.
 */
public class DiseaseFilter extends BaseFilter{

    private static final Logger LOGGER= LoggerFactory.getLogger(DiseaseFilter.class);

    @Override
    public boolean isKeep(TridentTuple tuple) {
        DiagnosisEvent event= (DiagnosisEvent) tuple.getValue(0);
        Integer code= Integer.valueOf(event.diag);
        if(code<=322){
            LOGGER.info("Emmit diagnosis "+ code);
            return true;
        }else {
            LOGGER.info("Filter diagnosis "+ code);
            return false;
        }
    }
}
