package flink.wordcount;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.aggregation.Aggregations;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * Created by root on 2/28/16.
 */
public class WordCountFlink {

    public static void main(String[] args) throws Exception {

        final ExecutionEnvironment environment=ExecutionEnvironment.getExecutionEnvironment();

        //get the input data
        DataSet<String> text=environment.fromElements("This Etext file is presented by Project Gutenberg, in\n" +
                "cooperation with World Library, Inc., from their Library of the\n" +
                "Future and Shakespeare CDROMS.  Project Gutenberg often releases\n" +
                "Etexts that are NOT placed in the Public Domain!!");

        DataSet<Tuple2<String,Integer>> counts=text.flatMap(new LineSplitter())
                .groupBy(0).sum(1);
        counts.writeAsText("/home/tong/test_flimk.txt");
        environment.execute("WordCount-flink");

    }

}

