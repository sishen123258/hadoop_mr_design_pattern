package multithread.GuardedSuspensioin;

import java.util.LinkedList;

/**
 * Created by Yue on 2015/7/23.
 */
public class RequestQueue {

    private LinkedList<Request> queue=new LinkedList<Request>();

    public synchronized Request getRequest(){
        if (queue.size()<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.removeFirst();
    }

    public synchronized void put(Request request){
        queue.addLast(request);
        notifyAll();
    }
}
