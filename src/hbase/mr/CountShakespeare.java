package hbase.mr;

import hbase.user.UserDao;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.NullOutputFormat;

import java.io.IOException;

/**
 * Created by root on 2/16/16.
 */
public class CountShakespeare {

    public static enum Counters {ROWS, SHAKESPEAREAN};

    public static class Map extends TableMapper<Text,LongWritable>{
        @Override
        protected void map(ImmutableBytesWritable rowKey, Result result, Context context)
                throws IOException, InterruptedException {

            byte[] bytes = result.getColumnLatest(UserDao.INFO_FAM, UserDao.USER_COL).getValueArray();
            String msg = Bytes.toString(bytes);

            if(msg!=null && !msg.isEmpty()){
                context.getCounter(Counters.ROWS).increment(1);
            }

            if(cotainsShakespeare(msg)){
                context.getCounter(Counters.SHAKESPEAREAN).increment(1);
            }

        }

        private boolean cotainsShakespeare(String msg) {
            return msg.contains("shake");
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        HBaseConfiguration conf=new HBaseConfiguration();
        Job job = new Job(conf, "TwitBase Shakespeare counter");
        job.setJarByClass(CountShakespeare.class);

        Scan scan=new Scan();
        scan.addColumn(UserDao.INFO_FAM,UserDao.USER_COL);
        TableMapReduceUtil.initTableMapperJob(
                UserDao.TABLE_NAME,
                scan,
                Map.class,
                Text.class,
                LongWritable.class,
                job
        );

        job.setOutputFormatClass(NullOutputFormat.class);
        job.setNumReduceTasks(0);
        System.exit(job.waitForCompletion(true) ? 0 : 1);




    }



}
