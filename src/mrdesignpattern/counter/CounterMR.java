package mrdesignpattern.counter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

public class CounterMR {

    public static class CounterMapper extends Mapper<Object,Text,NullWritable,NullWritable>{

        enum ClickCount{
            CLICK_COUNT
        }


        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            context.getCounter(ClickCount.CLICK_COUNT).increment(1);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args=new String[]{"/tong/sougo/spark/min", "/tong/sougo/output6"};

        Configuration conf=new Configuration();
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/core-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/yarn-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/hdfs-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/mapred-site.xml"));
        conf.set("mapred.jar","/root/IdeaProjects/hadoop/out/artifacts/counter_jar/hadoop.jar");


        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: wordcount <in> <out>");
            System.exit(2);
        }

        Job job=new Job(conf,"CounterMR");

        //reduce == 0
        job.setNumReduceTasks(0);

        job.setJarByClass(CounterMR.class);
        job.setMapperClass(CounterMapper.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        System.exit(job.waitForCompletion(true)? 0 : 1);
    }
}
