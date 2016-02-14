package mrdesignpatterb.average;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class AverageMapper extends Mapper<Object,Text,LongWritable,LongWritable> {

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        context.write(new LongWritable(1),new LongWritable(Long.parseLong(value.toString())));
    }
}

