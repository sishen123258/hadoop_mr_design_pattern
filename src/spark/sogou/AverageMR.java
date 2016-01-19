package spark.sogou;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

/**
 * Created by root on 1/18/16.
 */
public class AverageMR {

    public static void main(String[] args) {

        SparkConf conf=new SparkConf().setAppName("sougou").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> javaRDD = sc.textFile("hdfs://localhost:8020/tong/sougo/spark/min/part-00001");
        Accumulator<Integer> accumulator = sc.accumulator(0);

        javaRDD.foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                int term = Integer.parseInt(s);
                if (term > 0)
                    accumulator.add(term);
            }
        });

        System.out.println("accumulator = " + accumulator.value());
    }

}
