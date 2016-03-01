package multithread.TwoTermination;

/**
 * Created by Yue on 2015/7/28.
 */
public class CountupThread extends Thread{

    private int counter;
    private volatile boolean shunDown=false;

    public void setShunDown(boolean shunDown) {
        this.shunDown = shunDown;
        interrupt();
    }

    public boolean isShunDown() {
        return shunDown;
    }

    @Override
    public final void run() {
        try {
            while (!shunDown) {
                doWork();

            }
        }catch (InterruptedException e){

        }finally {
            doShutdown();
        }
    }

    private void doShutdown() {
        System.out.println("shunt down ,counter:"+this.counter);
    }

    private void doWork() throws InterruptedException {
        this.counter++;
        System.out.println("Counter"+this.counter);
        Thread.sleep(500);

    }
}
