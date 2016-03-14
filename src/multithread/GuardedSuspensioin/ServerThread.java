package multithread.GuardedSuspensioin;

import java.util.Random;

/**
 * Created by Yue on 2015/7/23.
 */
public class ServerThread extends Thread{

    private Random random;
    private RequestQueue queue;

    public ServerThread(RequestQueue queue, String name, Long seed) {
        super(name);
        this.queue = queue;
        random=new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Request request= null;
            request = queue.getRequest();
            System.out.println(Thread.currentThread().getName()+" Response "+request);

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

}
