package domain.custom;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 1/24/16.
 */
public class RandomInputFormat extends InputFormat<Text, NullWritable> {

    public static final String NUM_MAP_TASKS = "random.generator.map.tasks";
    public static final String NUM_RECORDS_PER_TASK =
            "random.generator.num.records.per.map.task";
    public static final String RANDOM_WORD_LIST =
            "random.generator.random.word.file";

    @Override
    public List<InputSplit> getSplits(JobContext context) throws IOException, InterruptedException {
        int numSplit=context.getConfiguration().getInt(NUM_MAP_TASKS,-1);

        ArrayList<InputSplit> inputSplits=new ArrayList<>(numSplit);
        for(int i=0;i<inputSplits.size();i++){
            inputSplits.add(new FakeInputSplit());
        }

        return inputSplits;
    }

    @Override
    public RecordReader<Text, NullWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        //initialize RecordReader
        RandomRecordReader recordReader=new RandomRecordReader();
        recordReader.initialize(split,context);

        return recordReader;
    }

    public static void setNumMapTasks(Job job, int i) {
        job.getConfiguration().setInt(NUM_MAP_TASKS, i);
    }
    public static void setNumRecordPerTask(Job job, int i) {
        job.getConfiguration().setInt(NUM_RECORDS_PER_TASK, i);
    }
    public static void setRandomWordList(Job job, Path file) {
        DistributedCache.addCacheFile(file.toUri(), job.getConfiguration());
    }



}
