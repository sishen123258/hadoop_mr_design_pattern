package designpattern.status;

import java.rmi.RemoteException;

/**
 * Created by Tong on 2016/2/25.
 */
public class Client {

    public static void main(String[] args) throws RemoteException {


//        CandyMachine machine=new CandyMachine(2);
//        machine.insertQuarter();
//        machine.turnCrank();
//        machine.insertQuarter();
//        machine.turnCrank();
//        machine.insertQuarter();
//        System.out.println("machine.getCount = [" + machine.getCount() + "]");

        StatusCandyMachine candyMachine=new StatusCandyMachine(2);
        candyMachine.insertQuarter();
        candyMachine.turnCrank();
        System.out.println(candyMachine.getCount());

    }


}
