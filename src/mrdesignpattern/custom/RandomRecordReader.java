package mrdesignpattern.custom;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by root on 1/24/16.
 */
public class RandomRecordReader extends RecordReader<Text,NullWritable>{

    private int numRecordsToCreate = 0;
    private int createdRecords = 0;
    private Text key = new Text();
    private NullWritable value = NullWritable.get();
    private Random rndm = new Random();
    private ArrayList<String> randomWords = new ArrayList<String>();
    private SimpleDateFormat frmt = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS");
    private String randomText;


    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        this.numRecordsToCreate=context.getConfiguration().
                getInt(RandomInputFormat.NUM_RECORDS_PER_TASK,-1);

        URI[] files= DistributedCache.getCacheFiles(context.getConfiguration());
        BufferedReader rdr=new BufferedReader(new FileReader(files[0].getPath()));
        String line;

        while ((line=rdr.readLine())!=null){
            randomWords.add(line);
        }

        rdr.close();

    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {

        // If we still have records to create
        if (createdRecords < numRecordsToCreate) {
            // Generate random data
            int score = Math.abs(rndm.nextInt()) % 15000;
            int rowId = Math.abs(rndm.nextInt()) % 1000000000;
            int postId = Math.abs(rndm.nextInt()) % 100000000;
            int userId = Math.abs(rndm.nextInt()) % 1000000;
            String creationDate = frmt
                    .format(Math.abs(rndm.nextLong()));
            // Create a string of text from the random words
            String text = getRandomText();
            String randomRecord = "score="+score+"rowId"+rowId+"postId"+postId
                    +"userId"+userId;
            key.set(randomRecord);
            ++createdRecords;
            return true;
        }

        return false;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public NullWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return (float) createdRecords/(float) numRecordsToCreate;
    }

    @Override
    public void close() throws IOException {

    }

    public String getRandomText() {
        StringBuilder bldr = new StringBuilder();
        int numWords = Math.abs(rndm.nextInt()) % 30 + 1;
        for (int i = 0; i < numWords; ++i) {
            bldr.append(randomWords.get(Math.abs(rndm.nextInt())
                    % randomWords.size())
                    + " ");
        }
        return bldr.toString();
    }
}
