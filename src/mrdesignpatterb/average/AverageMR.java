package mrdesignpatterb.average;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * Created by root on 1/17/16.
 */
public class AverageMR {


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        args=new String[]{"/tong/sougo/spark/min", "/tong/sougo/output5"};

        Configuration conf=new Configuration();
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/core-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/yarn-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/hdfs-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/mapred-site.xml"));
        conf.set("mapred.jar","/root/IdeaProjects/hadoop/out/artifacts/average_jar/hadoop.jar");

        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: wordcount <in> <out>");
            System.exit(2);
        }

        Job job=new Job(conf,"AverageMR");

        job.setJarByClass(AverageMR.class);
        job.setMapperClass(AverageMapper.class);
        job.setReducerClass(AverageReduce.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(LongWritable.class);
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        System.exit(job.waitForCompletion(true)? 0 : 1);
    }
}
