package designpattern.proxy.inter;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Tong on 2016/2/26.
 */
public interface MyRemote extends Remote{

    //此处应该生命异常。这样在接口中生命异常他的实现就必须处理异常，防止网络异常时造成程序崩溃
    String sayHello() throws RemoteException;

}
