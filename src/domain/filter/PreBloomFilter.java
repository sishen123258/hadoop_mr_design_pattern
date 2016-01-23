package domain.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;
import org.apache.hadoop.util.hash.Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * Created by root on 1/23/16.
 */
public class PreBloomFilter {

    public static void main(String[] args) throws IOException {

        if(args.length==0){
            args=new String[]{"/tong/ir","/tong/irid-bloom-filter"};
        }

        Path path=new Path(args[0]);
        Path bPath=new Path(args[1]);

        BloomFilter filter=new BloomFilter(100,100, Hash.MURMUR_HASH);

        Configuration conf=new Configuration();
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/core-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/yarn-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/hdfs-site.xml"));
        conf.addResource(new Path("/root/IdeaProjects/hadoop/conf/mapred-site.xml"));

        FileSystem fs=FileSystem.get(conf);
        String line=null;

        for (FileStatus status:fs.listStatus(path)){
            FileStatus statusNew=fs.listStatus(status.getPath())[0];
            BufferedReader reader=new BufferedReader(new InputStreamReader
                    (fs.open(statusNew.getPath())));

            while ((line=reader.readLine())!=null){
                String id = line.split(",")[0];
                if(id=="id"){}
                else {
                    filter.add(new Key(id.getBytes()));
                }


            };
            reader.close();

        }

        FSDataOutputStream outputStream=fs.create(bPath);
        filter.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }


}
