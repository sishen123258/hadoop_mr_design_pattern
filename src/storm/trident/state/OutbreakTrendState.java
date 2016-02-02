package storm.trident.state;

import storm.trident.state.map.IBackingMap;
import storm.trident.state.map.NonTransactionalMap;

/**
 * Created by Tong on 2016/2/2.
 */
public class OutbreakTrendState extends NonTransactionalMap<Long> {

    protected OutbreakTrendState(IBackingMap<Long> backing) {
        super(backing);
    }

}
