package spark.sogou;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 1/3/16.
 */
public class Sougou {

    public static void main(String []args){


        SparkConf conf = new SparkConf().setAppName("sougou").setMaster("local");
//        conf.set("yarn.resourcemanager.hostname","localhost");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> javaRDD = sc.textFile("hdfs://localhost:8020/tong/sogo");

        JavaRDD<Integer> pageOrderRdd=javaRDD.map(new Function<String, Integer>() {
            @Override
            public Integer call(String s) throws Exception {

                Map<String,String> orderMap = parseLogToMap(s);
                try {
                    int pageOrder = Integer.parseInt(orderMap.get("pageOrder"));
                    return pageOrder;
                }catch (Exception e){
                    e.printStackTrace();
                }

                return Integer.MAX_VALUE;
            }
        });

//        List<Integer> pageOrderList = pageOrderRdd.collect();
        pageOrderRdd.cache();
        /*JavaRDD<Integer> sortedRdd = pageOrderRdd.sortBy(new Function<Integer, Object>() {
            @Override
            public Object call(Integer integer) throws Exception {
                return integer;
            }
        }, true, 1);*/


        List<Integer> min = pageOrderRdd.takeOrdered(1);
        System.out.println(min +" is the minest");
        pageOrderRdd.saveAsTextFile("hdfs://localhost:8020/tong/sougo/spark/min");


    }



    public static Map parseLogToMap(String s) {

        String[] dataList=s.toString().split("\t");
        Map userLogMap=new HashMap<String,String>();

        userLogMap.put("hour",dataList[0]);
        userLogMap.put("userId",dataList[1]);
        userLogMap.put("searchWord",dataList[2]);
        userLogMap.put("pageOrder",dataList[3]);
        userLogMap.put("clickOrder",dataList[4]);
        userLogMap.put("url",dataList[5]);
        return userLogMap;
    }


}
