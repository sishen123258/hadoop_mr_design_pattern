package multithread.Future;

/**
 * Created by Yue on 2015/7/27.
 */
public class RealData implements Data{

    private String content;

    public RealData(int count,char c) {
        System.out.println("    making realData("+ count+" "+ c +") begin");
        char []buffer=new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i]=c;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("    making realData("+ count+" "+ c +") end");
        this.content=buffer.toString();
    }

    @Override
    public String getContent() {
        return content;
    }
}
