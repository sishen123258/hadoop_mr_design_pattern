package multithread.ProducerConsumer;

/**
 * Created by Yue on 2015/7/23.
 */
public class Main {

    public static void main(String[] args) {
        Table table=new Table(3);
        new MakerThread("MK-1",table,3000l).start();
        new MakerThread("MK-2",table,2000l).start();
        new MakerThread("MK-3",table,1000l).start();
        new EaterThread("ET-3",table,1000l).start();
        new EaterThread("ET-2",table,2000l).start();
        new EaterThread("ET-1",table,3000l).start();
    }

}
