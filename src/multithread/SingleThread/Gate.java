package multithread.SingleThread;

/**
 * Created by Yue on 2015/7/23.
 */
public class Gate {
    private int counter =0;
    private String name="Nobody";
    private String address="NoWhere";


    public void check(){
        if (name.charAt(0)!=address.charAt(0)){
            System.out.println("****Broken****"+this.toString());
        }
        System.out.println("Fun "+this.toString());
    }

    public synchronized void pass(String name,String address){

        this.counter++;
        this.name=name;
        this.address=address;
        check();

    }

    @Override
    public synchronized String toString() {
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
