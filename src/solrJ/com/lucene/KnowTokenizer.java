package solrJ.com.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;

/**
 * Created by Yue on 2015/11/25.
 */
public class KnowTokenizer {

    public static void main(String[] args) throws IOException {

        String text="The only way to survive was to enjoy the good moments and not dwell too much on the bad!";
        IndexWriter writer=new IndexWriter(new RAMDirectory(),
                new IndexWriterConfig(Version.LUCENE_47,new StandardAnalyzer(Version.LUCENE_47)));
        Document document=new Document();
        document.add(new Field("text",text, Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(document);
        writer.commit();




    }



}
