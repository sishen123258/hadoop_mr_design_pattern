package designpattern.status;

/**
 * Created by Tong on 2016/2/25.
 */
public class Client {

    public static void main(String[] args) {


        CandyMachine machine=new CandyMachine(2);
        machine.insertQuarter();
        machine.turnCrank();
        machine.insertQuarter();
        machine.turnCrank();
        machine.insertQuarter();
        System.out.println("machine.getCount = [" + machine.getCount() + "]");


    }


}
