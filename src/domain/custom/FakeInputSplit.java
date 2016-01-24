package domain.custom;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.io.Writable;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by root on 1/24/16.
 */
public class FakeInputSplit extends InputSplit implements Writable{
    @Override
    public long getLength() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public String[] getLocations() throws IOException, InterruptedException {
        return new String[0];
    }

    @Override
    public void write(DataOutput out) throws IOException {

    }

    @Override
    public void readFields(DataInput in) throws IOException {

    }
}
