package mrdesignpatterb.minmax;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MinUserIdReduce extends Reducer<LongWritable,NullWritable,LongWritable,NullWritable>{

        long min=Long.MAX_VALUE;

        @Override
        protected void reduce(LongWritable key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

            long temp=key.get();
            if (temp<min){
                min=temp;
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            context.write(new LongWritable(min),NullWritable.get());
        }
    }
