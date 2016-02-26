package designpattern.status.inter;

import java.rmi.RemoteException;

/**
 * Created by Tong on 2016/2/26.
 */
public interface StatusCandyMachineRemote {

    int getCount() throws RemoteException;
    Status getStatus() throws RemoteException;
    String getLocation() throws RemoteException;

}
