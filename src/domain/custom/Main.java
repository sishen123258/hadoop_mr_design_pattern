package domain.custom;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by root on 1/24/16.
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/core-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/yarn-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/hdfs-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/mapred-site.xml"));
        int numMapTasks = Integer.parseInt(args[0]);
        int numRecordsPerTask = Integer.parseInt(args[1]);
        Path wordList = new Path(args[2]);
        Path outputDir = new Path(args[3]);
        Job job = new Job(conf, "RandomDataGenerationDriver");
        job.setJarByClass(Main.class);
        job.setNumReduceTasks(0);

        job.setInputFormatClass(RandomInputFormat.class);
        RandomInputFormat.setNumMapTasks(job, numMapTasks);
        RandomInputFormat.setNumRecordPerTask(job,numRecordsPerTask);
        RandomInputFormat.setRandomWordList(job, wordList);
        TextOutputFormat.setOutputPath(job, outputDir);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        System.exit(job.waitForCompletion(true) ? 0 : 2);

    }




}
