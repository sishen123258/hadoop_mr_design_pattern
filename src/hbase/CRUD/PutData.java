package hbase.CRUD;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by root on 2/8/16.
 */
public class PutData {

    public static Configuration configuration;
    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "192.168.8.122");
        configuration.set("hbase.master", "192.168.8.122:600000");
    }

    public static void main(String[] args) throws IOException {

        String tableName="test";
//        createTable(tableName);
        //putData(tableName);
        dropTable(tableName);



    }

    private static void dropTable(String tableName) {
        try {
            HBaseAdmin admin=new HBaseAdmin(configuration);
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void putData(String tableName) throws IOException {
        HTable table=new HTable(configuration,tableName);
        //one put is one row data
        Put put=new Put(Bytes.toBytes("row1"));
        put.add(Bytes.toBytes("column1"),Bytes.toBytes("qual1"),Bytes.toBytes("val1"));
        put.add(Bytes.toBytes("column1"),Bytes.toBytes("qual2"),Bytes.toBytes("val2"));
        put.add(Bytes.toBytes("column2"),Bytes.toBytes("qual3"),Bytes.toBytes("val3"));

        table.put(put);
    }

    private static void createTable(String tableName) {
        System.out.println("start create table ......");
        try {
            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
            if (hBaseAdmin.tableExists(tableName)) {// 如果存在要创建的表，那么先删除，再创建
                hBaseAdmin.disableTable(tableName);
                hBaseAdmin.deleteTable(tableName);
                System.out.println(tableName + " is exist,detele....");
            }
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            tableDescriptor.addFamily(new HColumnDescriptor("column1"));
            tableDescriptor.addFamily(new HColumnDescriptor("column2"));
            tableDescriptor.addFamily(new HColumnDescriptor("column3"));
            hBaseAdmin.createTable(tableDescriptor);
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end create table ......");
    }


}
