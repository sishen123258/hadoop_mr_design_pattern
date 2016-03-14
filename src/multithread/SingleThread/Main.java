package multithread.SingleThread;

/**
 * Created by Yue on 2015/7/23.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Please go head!");
        Gate gate=new Gate();
        new Thread(new UserThread(gate,"Alice","Aka")).start();
        new Thread(new UserThread(gate,"Bob","Bom")).start();
        new Thread(new UserThread(gate,"David","Dala")).start();
    }
}
