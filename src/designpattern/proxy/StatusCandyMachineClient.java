package designpattern.proxy;

import designpattern.status.inter.StatusCandyMachineRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Tong on 2016/2/26.
 */
public class StatusCandyMachineClient {

    public static void main(String[] args) {

        new StatusCandyMachineClient().go();

    }

    public void go(){
        try {
            StatusCandyMachineRemote candyMachineRemote= (StatusCandyMachineRemote) Naming.lookup("rmi://127.0.0.1/remoteCandyMachine");
            System.out.println(candyMachineRemote.getCount());
            System.out.println(candyMachineRemote.getLocation());
            System.out.println(candyMachineRemote.getStatus());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
