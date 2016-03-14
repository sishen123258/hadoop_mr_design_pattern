package multithread.WorkThread;

/**
 * Created by Yue on 2015/7/26.
 */
public class Channel {

    private static final int max_size=100;
    private int tail;
    private int head;
    private int count;
    private Request[] requestQueue;
    private WorkThread [] threadPool;

    public Channel(int threads) {
        this.tail = 0;
        this.head = 0;
        this.count = 0;

        requestQueue=new Request[max_size];
        threadPool=new WorkThread[threads];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i]=new WorkThread("work-"+i,this);
        }
    }



    public synchronized Request takeRequest(){

        while (count<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request=requestQueue[head];
        head=(head+1)%requestQueue.length;
        count--;
        notifyAll();
        return request;

    }

    public  synchronized void putRequest(Request request){
        while (count>=requestQueue.length){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        requestQueue[tail]=request;
        count++;
        tail=(tail+1)%requestQueue.length;
        notifyAll();
    }

    public void startWorks(){
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }
    }
}
