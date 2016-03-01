package multithread.Future;

/**
 * Created by Yue on 2015/7/27.
 */
public class FutureData implements Data{

    private boolean ready=false;
    private RealData realData;

    public synchronized void setRealData(RealData realData){
        if(ready==true){
            return;
        }
        this.realData=realData;
        ready=true;
        notifyAll();
    }

    @Override
    public synchronized String getContent() {
        while (ready==false){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getContent();
    }
}
