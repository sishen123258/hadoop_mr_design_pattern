package storm.trident.state;

import storm.trident.bean.OutbreakTrendBackingMap;
import storm.trident.state.map.NonTransactionalMap;

/**
 * Created by Tong on 2016/2/2.
 */
public class OutbreakTrendState extends NonTransactionalMap<Long> {

    public OutbreakTrendState(OutbreakTrendBackingMap backing) {
        super(backing);
    }

}
