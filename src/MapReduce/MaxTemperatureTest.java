/*
package MapReduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

*/
/**
 * Created by root on 12/16/15.
 *//*

public class MaxTemperatureTest {

    //MapperReduce unite test
    @Test
    public void testMapper() throws IOException {
        Text value = new Text("0043011990999991950051518004+68750+023550FM-12+0382"	+
                "99999V0203201N00261220001CN9999999N9-00111+99999999999");
        new MapDriver<LongWritable,Text,Text,IntWritable>()
                .withMapper(new MaxTemperatureMapper())
                .withInput(new LongWritable(0),value)
                .withOutput(new Text("1950"),new IntWritable(-11))
                .runTest();
    }

}
*/
