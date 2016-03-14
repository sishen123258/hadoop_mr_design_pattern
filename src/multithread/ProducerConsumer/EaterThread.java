package multithread.ProducerConsumer;

import java.util.Random;

/**
 * Created by Yue on 2015/7/23.
 * <p/>
 * ����������
 */
public class EaterThread extends Thread {

    private Random random;
    private Table table;

    public EaterThread(String name, Table table, long seed) {
        super(name);
        this.table = table;
        random=new Random(seed);
    }

    @Override
    public void run() {
        while (true) {
            try {
                table.getCake();
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
