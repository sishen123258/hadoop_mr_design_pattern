package multithread.ReadWriteLock;

import java.util.Random;

/**
 * Created by Yue on 2015/7/23.
 */
public class WriteThread extends Thread {

    private static final Random random=new Random();
    private Data data;
    private int index=0;
    private String filter;

    public WriteThread(String name,String filter,Data data){
        super(name);
        this.filter=filter;
        this.data=data;
    }

    @Override
    public void run() {
        try {
            while(true){
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private char nextChar() {
        char c=filter.charAt(index);
        index++;
        if (index>=filter.length()){
            index=0;
        }
        return c;
    }
}
