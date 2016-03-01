package multithread.ReadWriteLock;

/**
 * Created by Yue on 2015/7/23.
 */
public class Main {

    public static void main(String[] args) {
        Data data=new Data(10);
        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();
        new WriteThread("WT-1","asdfghjklmnbvcxz",data).start();
        new WriteThread("WT-2","qwertyuioplkjhgfdsa",data).start();

    }


}
