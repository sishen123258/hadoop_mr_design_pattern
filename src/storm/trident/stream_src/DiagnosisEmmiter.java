package storm.trident.stream_src;

import storm.trident.bean.DiagnosisEvent;
import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout;
import storm.trident.topology.TransactionAttempt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Tong on 2016/2/2.
 */
public class DiagnosisEmmiter implements ITridentSpout.Emitter<Long>,Serializable {

    private AtomicInteger successForTransactions=new AtomicInteger(0);


    @Override
    public void emitBatch(TransactionAttempt tx, Long coordinatorMeta, TridentCollector collector) {

        for(int i=0;i < 10000;i++){
            //TODO ??????
            ArrayList<Object> objects=new ArrayList<>();
            double lat =new Double(-30 + (int) (Math.random() * 75));
            double lng =new Double(-120 + (int) (Math.random() * 70));
            long time = System.currentTimeMillis();
            String diag = new Integer(320 + (int) (Math.random() * 7)).toString();
            DiagnosisEvent event = new DiagnosisEvent(lat, lng, time, diag);
            objects.add(event);
            collector.emit(objects);
        }

    }

    @Override
    public void success(TransactionAttempt tx) {
        successForTransactions.incrementAndGet();
    }

    @Override
    public void close() {

    }
}
