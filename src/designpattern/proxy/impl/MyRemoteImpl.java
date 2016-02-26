package designpattern.proxy.impl;

import designpattern.proxy.inter.MyRemote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Tong on 2016/2/26.
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{

    protected MyRemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello() throws RemoteException {
        return "HELLO REMOTE CLIENT!";
    }


    public static void main(String[] args) {
        try {
            MyRemote service=new MyRemoteImpl();
            Naming.rebind("rmi://127.0.0.1/remoteHello",service);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e = [" + e.getMessage() + "]");
        }
    }
}
