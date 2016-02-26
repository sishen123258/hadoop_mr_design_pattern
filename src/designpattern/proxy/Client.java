package designpattern.proxy;

import designpattern.proxy.inter.MyRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Tong on 2016/2/26.
 */
public class Client {

    public static void main(String[] args) {
        new Client().go();
    }

    public void go(){
        try {
            MyRemote remote = (MyRemote)Naming.lookup("rmi://127.0.0.1/remoteHello");
            String s = remote.sayHello();
            System.out.println(s);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
