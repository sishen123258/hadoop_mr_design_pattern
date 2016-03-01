package multithread.SingleThread;

/**
 * Created by Yue on 2015/7/23.
 */
public class UserThread extends Thread {

    private final Gate gate;
    private final String myname;
    private final String myaddress;

    public UserThread(Gate gate, String myname, String myaddress) {
        this.gate = gate;
        this.myname = myname;
        this.myaddress = myaddress;
    }


    @Override
    public void run() {
        System.out.println("Begin");
        while (true){
            gate.pass(this.myname,this.myaddress);
        }
    }
}
