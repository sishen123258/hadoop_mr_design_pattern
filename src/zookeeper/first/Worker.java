package zookeeper.first;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Tong on 2016/2/19.
 */
public class Worker implements Watcher {

    private Log LOG= LogFactory.getLog(Watcher.class);

    private ZooKeeper zk;
    private String hostPort;
    private String serviceId=Integer.toHexString(new Random().nextInt());

    private AsyncCallback.StringCallback createWorkerCallBack=new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)){
                case CONNECTIONLOSS:
                    LOG.info("Connection loss");
                    register();
                    break;
                case OK:
                    LOG.info("Register successfully!"+serviceId);
                    break;
                case NODEEXISTS:
                    LOG.info("work-"+serviceId+" is exists");
                    break;
                default:
                    LOG.error(KeeperException.create(KeeperException.Code.get(rc),path));
            }
        }
    };

    public Worker(String hostPort) {
        this.hostPort = hostPort;
    }

    public void startWorker() throws IOException {
        zk=new ZooKeeper(hostPort,15000,this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }

    public void register(){
        zk.create("/master/master-"+serviceId,"idle".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,createWorkerCallBack,null);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Worker worker=new Worker("192.168.161.129:2181");
        worker.startWorker();
        worker.register();

        Thread.sleep(60000);
    }

}
