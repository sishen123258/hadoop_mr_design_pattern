package designpattern.status.inter;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Tong on 2016/2/26.
 */
public interface StatusCandyMachineRemote extends Remote{

    int getCount() throws RemoteException;
    Status getStatus() throws RemoteException;
    String getLocation() throws RemoteException;

}
