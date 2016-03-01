package solrJ.com.index;

import com.utils.MD5FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.solr.common.SolrInputDocument;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Yue on 2015/10/14.
 */
public class IndexFile {

    private ArrayList<SolrInputDocument> documents;

    public IndexFile() {
        this.documents = new ArrayList<SolrInputDocument>();
    }

    public ArrayList<SolrInputDocument> getDocuments(String path) {
        if(path==null || path.equals("")){
            File[] file=File.listRoots();
            indexAllFiles(file);
        }else {
            indexAllFiles(new File(path));
        }
        return documents;
    }

    public void indexAllFiles(File ...files) {

        for (File file:files){
            Iterator<File> fileIterator = FileUtils.iterateFiles(file, null, true);
            while (fileIterator.hasNext()){
                File f=fileIterator.next();
                try {
                    addSolrDocumentList(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void addSolrDocumentList(File file) throws IOException {
        SolrInputDocument document=new SolrInputDocument();
        document.addField("id", MD5FileUtil.getFileMD5String(file));
        document.addField("name", file.getName());
        document.addField("content", getFileContent(file));
        document.addField("path", file.getAbsolutePath());
        document.addField("modifyDate",file.lastModified());
        documents.add(document);
    }

    private String getFileContent(File file) {
        //TODO add tika
        if(file.getName().endsWith(".txt") || file.getName().endsWith(".md") ||
                file.getName().endsWith(".py")|| file.getName().endsWith(".html")) {
            try {
                return IOUtils.toString(new FileReader(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
