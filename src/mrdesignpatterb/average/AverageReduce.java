package mrdesignpatterb.average;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by root on 1/17/16.
 */
public class AverageReduce extends Reducer<LongWritable,LongWritable,LongWritable,LongWritable> {

    @Override
    protected void reduce(LongWritable key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        int sum=0;
        int counter=0;

        for(LongWritable n:values){
            sum+=n.get();
            counter++;
        }
        long average=sum/counter;
        context.write(new LongWritable(average),new LongWritable(counter));

    }
}
