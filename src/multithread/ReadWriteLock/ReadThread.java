package multithread.ReadWriteLock;

/**
 * Created by Yue on 2015/7/23.
 */
public class ReadThread extends Thread {

    private final  Data data;


    public ReadThread(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        try{
            while (true){
                char[] c=data.read();
                System.out.println(Thread.currentThread().getName()+" get "+String.valueOf(c));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
