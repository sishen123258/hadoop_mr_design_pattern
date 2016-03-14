package multithread.WorkThread;

import java.util.Random;

/**
 * Created by Yue on 2015/7/26.
 */
public class Request {

    private final String name;
    private final int number;
    private static final Random random=new Random();

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName()+" execute "+this);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
