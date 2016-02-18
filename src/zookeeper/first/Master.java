package zookeeper.first;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by root on 2/18/16.
 */
public class Master implements Watcher {

    ZooKeeper zk;
    String hostPort;

    public Master(String hostPort) {
        this.hostPort = hostPort;
    }

    public void startZk() throws IOException {
        //new ,the connection is established
        //so the code can not be instanced at constructor ,is the zk is instance and the callback is
        //able to called ,but some other code may not prepared properly
        zk=new ZooKeeper(hostPort,15000,this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("watchedEvent = [" + watchedEvent + "]");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Master master=new Master("127.0.0.1:2181");
        master.startZk();
        Thread.sleep(60000);

    }

}
