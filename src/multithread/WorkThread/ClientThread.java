package multithread.WorkThread;

import java.util.Random;

/**
 * Created by Yue on 2015/7/26.
 */
public class ClientThread extends Thread{


    private final Channel channel;
    private Random random = new Random();

    public ClientThread(String name,Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {

            try {
                for (int i = 0; true; i++) {
                    Request request = new Request(getName(), i);
                    channel.putRequest(request);
                    Thread.sleep(random.nextInt(1000));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
