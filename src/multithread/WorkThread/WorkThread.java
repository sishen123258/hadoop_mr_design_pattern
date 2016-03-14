package multithread.WorkThread;

import java.util.Random;

/**
 * Created by Yue on 2015/7/26.
 */
public class WorkThread extends Thread {

    private final Channel channel;
    private Random random = new Random();

    public WorkThread(String name,Channel channel) {
        super(name);
        this.channel = channel;
    }



    @Override
    public void run() {
        while (true) {
            Request request = channel.takeRequest();
            request.execute();
        }
    }
}
