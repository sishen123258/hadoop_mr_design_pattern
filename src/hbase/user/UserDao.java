package hbase.user;

import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by root on 2/13/16.
 */
public class UserDao {

    public static final byte[] TABLE_NAME =
            Bytes.toBytes("users");
    public static final byte[] INFO_FAM =
            Bytes.toBytes("info");
    private static final byte[] USER_COL =
            Bytes.toBytes("user");
    private static final byte[] NAME_COL =
            Bytes.toBytes("name");
    private static final byte[] EMAIL_COL =
            Bytes.toBytes("email");
    private static final byte[] PASS_COL =
            Bytes.toBytes("password");
    public static final byte[] TWEETS_COL =
            Bytes.toBytes("tweet_count");

    private HTablePool pool;

    public UserDao(HTablePool pool) {
        this.pool = pool;
    }

    private static Get mkGet(String user){
        Get get=new Get(Bytes.toBytes(user));
        get.addFamily(INFO_FAM);
        return get;
    }

    private static Put mkPut(User user){
        Put p=new Put(Bytes.toBytes(user.user));
        p.addColumn(INFO_FAM,USER_COL,Bytes.toBytes(user.user));
        p.addColumn(INFO_FAM,NAME_COL,Bytes.toBytes(user.name));
        p.addColumn(INFO_FAM,EMAIL_COL,Bytes.toBytes(user.email));
        p.addColumn(INFO_FAM,PASS_COL,Bytes.toBytes(user.password));
        return p;
    }

    private static Delete mkDelete(String user){
        Delete delete=new Delete(Bytes.toBytes(user));
        return delete;
    }

    public void addUser(String user,String name,String email,String password) throws IOException {
        HTableInterface users = pool.getTable(TABLE_NAME);
        User addUser=new User(user,name,email,password);
        Put put=mkPut(addUser);
        users.put(put);
        users.close();
    }

    public void deleteUser(String user) throws IOException {
        HTableInterface users = pool.getTable(TABLE_NAME);
        Delete delete=mkDelete(user);
        users.delete(delete);
        users.close();
    }

    public hbase.user.User getUser(String user) throws IOException {
        HTableInterface users = pool.getTable(TABLE_NAME);
        Get get=mkGet(user);
        Result result = users.get(get);
        if (result.isEmpty()){
            return  null;
        }
        User gettedUser=new User(result);
        users.close();
        return gettedUser;
    }

    public List<hbase.user.User> getUsers() throws IOException {

        HTableInterface users = pool.getTable(TABLE_NAME);
        Scan scan=new Scan();
        ResultScanner scanner = users.getScanner(scan);
        ArrayList<hbase.user.User> list=new ArrayList<>();

        Iterator<Result> res = scanner.iterator();
        while (res.hasNext()){
            Result r=res.next();
            User user=new User(r);
            list.add(user);
        }

        return list;
    }



    /**
     * When the param is more than 3,maybe should user a object user to encapulate it
     * @param user
     * @param name
     * @param email
     * @param password
     * @return
     */
    public static Put mkPut(String user,String name,String email,String password){
        Put put=new Put(Bytes.toBytes(user));
        put.add(INFO_FAM,NAME_COL,Bytes.toBytes(name));
        put.add(INFO_FAM,EMAIL_COL,Bytes.toBytes(email));
        put.add(INFO_FAM,PASS_COL,Bytes.toBytes(password));
        return put;
    }

    private static class User extends hbase.user.User {

        private User(Result r) {
            this(r.getValue(INFO_FAM, USER_COL),
                    r.getValue(INFO_FAM, NAME_COL),
                    r.getValue(INFO_FAM, EMAIL_COL),
                    r.getValue(INFO_FAM, PASS_COL),
                    r.getValue(INFO_FAM, TWEETS_COL) == null
                            ? Bytes.toBytes(0L)
                            : r.getValue(INFO_FAM, TWEETS_COL));
        }

        private User(byte[] user,
                     byte[] name,
                     byte[] email,
                     byte[] password,
                     byte[] tweetCount) {
            this(Bytes.toString(user),
                    Bytes.toString(name),
                    Bytes.toString(email),
                    Bytes.toString(password));
//            this.tweetCount = Bytes.toLong(tweetCount);
        }

        private User(String user,
                     String name,
                     String email,
                     String password) {
            this.user = user;
            this.name = name;
            this.email = email;
            this.password = password;
        }

    }




}

