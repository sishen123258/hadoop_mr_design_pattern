package zookeeper.first;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

/**
 * Created by root on 2/18/16.
 */
public class Master implements Watcher {

    ZooKeeper zk;
    String hostPort;
    Random random = new Random();
    String serverId = Integer.toHexString(random.nextInt());
    boolean isLeader = false;

    AsyncCallback.DataCallback masterCheckCallBack=new AsyncCallback.DataCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            switch (KeeperException.Code.get(rc)){
                case CONNECTIONLOSS:
                    checkMaster();
                    return;
                case NONODE:
                    runForMaster();
                    return;
            }
        }
    };

    AsyncCallback.StringCallback masterCreateCallBack= new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)){
                case CONNECTIONLOSS:
                    checkMaster();
                    return;
                case OK:
                    isLeader=true;
                    break;
                default:
                    isLeader=false;
            }
            System.out.println("isLeader = " + isLeader);
        }
    };

    public Master(String hostPort) {
        this.hostPort = hostPort;
    }

    public void startZk() throws IOException {
        //new ZooKeeper(),the connection is established
        //so the code can not be instanced at constructor , the zk is a instance and the callback is
        //able to called ,but some other code may not prepared properly
        zk = new ZooKeeper(hostPort, 15000, this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("watchedEvent = [" + watchedEvent + "]");
    }

    public void stopZk() throws InterruptedException {
        zk.close();
    }

    //because the create is AsyncCallback,we do not worry about the InterruptedException
    public void runForMaster(){
        zk.create("/master", serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, masterCreateCallBack,null);
    }
    /**
     * version 1
    public void runForMaster() throws InterruptedException {
        while (true) {
            try {
                zk.create("/master", serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            }catch (KeeperException.NodeExistsException e) {
                e.printStackTrace();
                isLeader = false;
                break;
            } catch (KeeperException e) {
                e.printStackTrace();
            }
            //捕获的这两个异常并不能确定当前操作的状态，如果master已经创建，但是该master却不知道自己是mastership，
            // 那么其他竞选者也精选不了master，此时系统中会没有master，因此，我们必须弄明白当前操作处于什么状态
            if (checkMaster()) break;
        }
    }*/

    public void checkMaster(){
        zk.getData("/master",false, masterCheckCallBack,null);
    }

    /**
     *
    public boolean checkMaster() {
        while (true) {
            try {
                Stat stat = new Stat();
                byte[] data = zk.getData("/master", null, stat);
                isLeader = new String(data).equals(serverId);
                return true;
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }*/

    public static void main(String[] args) throws IOException, InterruptedException {
        Master master = new Master("192.168.161.129:2181");
        master.startZk();
        master.runForMaster();
        while (!master.isLeader)
            System.out.println("Someone else is the leader");
            Thread.sleep(1000);
        if (master.isLeader) {
            System.out.println("I'm the leader");
            Thread.sleep(60000);
        }
        master.stopZk();
    }

}
