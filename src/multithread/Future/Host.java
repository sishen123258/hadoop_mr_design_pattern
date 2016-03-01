package multithread.Future;

/**
 * Created by Yue on 2015/7/27.
 */
public class Host {

    public Data request(final int count, final char c){
        System.out.println("Request realData("+ count+" "+ c +") begin");
        final FutureData futureData=new FutureData();

        new Thread(){
            @Override
            public void run() {
                RealData realData=new RealData(count,c);
                futureData.setRealData(realData);
            }
        }.start();

        System.out.println("Request realData("+ count+" "+ c +") end");
        return futureData;
    }


}
