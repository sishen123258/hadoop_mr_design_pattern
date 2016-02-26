package designpattern.proxy;

import designpattern.status.StatusCandyMachine;
import designpattern.status.inter.StatusCandyMachineRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Created by Tong on 2016/2/26.
 */
public class StatusCandyMachineDriver {

    public static void main(String[] args) {

        try {
            StatusCandyMachineRemote candyMachine=new StatusCandyMachine(5,"ShangHai");
            Naming.rebind("remoteCandyMachine",candyMachine);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }


}
