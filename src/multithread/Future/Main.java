package multithread.Future;

/**
 * Created by Yue on 2015/7/27.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("main begin");
        Host host=new Host();
        Data data1=host.request(10,'a');
        Data data2=host.request(20,'b');
        Data data3=host.request(30,'c');

        System.out.println("main other job begin");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main other job end");
        System.out.println("data1"+data1.getContent());
        System.out.println("data2"+data2.getContent());
        System.out.println("data3"+data3.getContent());
    }
}
