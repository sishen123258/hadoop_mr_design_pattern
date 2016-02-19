package utils;

import org.apache.hadoop.hbase.util.Bytes;

import java.util.HashSet;

/**
 * Created by Tong on 2016/2/14.
 */
public class Test {


    public static void main(String[] args) {

//        testMD5();
        testHashSet();

    }

    private static void testHashSet() {

        HashSet set1=new HashSet();
        set1.add(123);
        HashSet set2=new HashSet();
        set2.add(223);

        System.out.println();


    }

    private static void testMD5() {


    }
}
