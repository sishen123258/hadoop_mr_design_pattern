package multithread.TwoTermination;

/**
 * Created by Yue on 2015/7/28.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Count start:");
        CountupThread thread = new CountupThread();
        thread.start();
        Thread.sleep(10000);
        System.out.println("shutdown");
        thread.setShunDown(true);
        System.out.println("main join");
        thread.join();

    }
}
