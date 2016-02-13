package hbase.user;

import scala.runtime.StringFormat;

/**
 * Created by root on 2/13/16.
 */
public abstract class User {

    public String name;
    public String user;
    public String email;
    public String password;

    @Override
    public String toString() {
        return String.format("<User: %s, %s, %s>",user,name,email);
    }
}
