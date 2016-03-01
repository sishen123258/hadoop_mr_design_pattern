package multithread.WorkThread;

/**
 * Created by Yue on 2015/7/26.
 */
public class Main {

    public static void main(String[] args) {
        Channel channel=new Channel(5);
        channel.startWorks();

        new ClientThread("A",channel).start();
        new ClientThread("B",channel).start();
        new ClientThread("C",channel).start();
        new ClientThread("D",channel).start();
    }
}
