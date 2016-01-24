package domain.custom;

import org.apache.hadoop.mapreduce.InputSplit;

import java.io.IOException;

/**
 * Created by root on 1/24/16.
 */
public class FakeInputSplit extends InputSplit {
    @Override
    public long getLength() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public String[] getLocations() throws IOException, InterruptedException {
        return new String[0];
    }
}
