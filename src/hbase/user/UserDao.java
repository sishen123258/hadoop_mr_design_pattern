package hbase.user;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

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

    public static Get mkGet(String user){
        Get get=new Get(Bytes.toBytes(user));
        get.addFamily(INFO_FAM);
        return get;
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

