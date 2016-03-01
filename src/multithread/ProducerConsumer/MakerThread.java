package multithread.ProducerConsumer;

import java.util.Random;

/**
 * Created by Yue on 2015/7/23.
 *
 * ����������
 */
public class MakerThread extends Thread{

    private Table table;
    private Random random;
    private static int id;

    public MakerThread(String name,Table table,Long seed) {
        super(name);
        this.table = table;
        random=new Random(seed);
    }

    public static int nextId(){
        return id++;
    }

    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(random.nextInt(1000));
                String cake="No "+nextId()+" by "+getName()+" ";
                table.put(cake);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
