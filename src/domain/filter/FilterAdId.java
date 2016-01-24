package domain.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

/**
 * Created by root on 1/23/16.
 */
public class FilterAdId {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        args=new String[]{ "/tong/hypers/","hdfs://localhost:8020/tong/hypers/filterAdId"};

        Configuration conf=new Configuration();

        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/core-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/yarn-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/hdfs-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/mapred-site.xml"));
        //mapreduce.job.cache.files
        conf.set("mapreduce.job.cache.files","/tong/irid-bloom-filter-20151201");
        conf.set("mapred.jar","/root/IdeaProjects/hdesignpattern/hadoop_mr_design_pattern/out/artifacts/bloomFilter_jar/hadoop_mr_design_pattern.jar");


        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: wordcount <in> <out>");
            System.exit(2);
        }

        Job job=new Job(conf,"bloom filter");
        job.setJarByClass(FilterAdId.class);
        job.setMapperClass(FilterAdMapper.class);
        job.setNumReduceTasks(0);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job,new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true)? 0 : 1);



    }

    public static class FilterAdMapper extends Mapper<LongWritable,Text,Text,NullWritable>{
        private Logger logger = Logger.getLogger(FilterAdMapper.class.getName());
        private BloomFilter filter=new BloomFilter();

        //read filter res from DistributedCache
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            URI[] files = DistributedCache.getCacheFiles(context.getConfiguration());
            System.out.println("files =  "+files );
            logger.info("files =  "+files );
            FileSystem fs = FileSystem.get(context.getConfiguration());
            for (URI uri : files){
                DataInputStream inputStream=new DataInputStream(fs.open(new Path(uri.getPath())));
                filter.readFields(inputStream);
                inputStream.close();
            }

        }


        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //if id in bloom filter res
            String id = value.toString().split(",")[0];
            if(filter.membershipTest(new Key(id.getBytes()))){
                context.write(value,NullWritable.get());
            };

        }


    }



}
