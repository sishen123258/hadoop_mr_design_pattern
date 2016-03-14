package multithread.GuardedSuspensioin;

import java.util.Random;

/**
 * Created by Yue on 2015/7/23.
 */
public class ClientThread extends Thread{

    private Random random;
    private RequestQueue queue;

    public ClientThread(RequestQueue queue,String name,Long seed) {
        super(name);
        this.queue = queue;
        random=new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Request request=new Request("No "+ i);
            System.out.println(Thread.currentThread().getName()+" Request "+request);
            queue.put(request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
