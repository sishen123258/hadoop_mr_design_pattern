package hbase.user;

import org.apache.hadoop.hbase.client.HTablePool;

import java.io.IOException;

/**
 * Created by Tong on 2016/2/14.
 */
public class UserMain {

    public static final String usage =
            "UsersTool action ...\n" +
                    " help - print this message and exit.\n" +
                    " add user name email password" +
                    " - add a new user.\n" +
                    " get user - retrieve a specific user.\n" +
                    " list - list all installed users.\n";

    public static void main(String[] args) throws IOException {

        if(args.length==0 || "help".equals(args[0])){
            System.out.println("usage = " + usage);
            System.exit(0);
        }

        HTablePool pool=new HTablePool();
        UserDao userDao=new UserDao(pool);

        if ("add".equals(args[0])){
            userDao.addUser(args[1],args[2],args[3],args[4]);
            User user = userDao.getUser(args[1]);
            System.out.println("user = " + user);
        }

        if ("get".equals(args[0])){
            User user = userDao.getUser(args[1]);
            System.out.println("user = " + user);
        }

        if("delete".equals(args[0])){
            userDao.deleteUser(args[1]);
        }





    }
}
