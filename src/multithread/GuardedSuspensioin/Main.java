package multithread.GuardedSuspensioin;

/**
 * Created by Yue on 2015/7/23.
 */
public class Main {

    public static void main(String[] args) {
        RequestQueue queue=new RequestQueue();
        new Thread(new ClientThread(queue,"ClientThread1",1000l)).start();
        new Thread(new ClientThread(queue,"ClientThread2",1000l)).start();
        new Thread(new ClientThread(queue,"ClientThreads3",1000l)).start();
        new Thread(new ServerThread(queue,"ServerThread1",500l)).start();
        new Thread(new ServerThread(queue,"ServerThread2",500l)).start();
    }
}
