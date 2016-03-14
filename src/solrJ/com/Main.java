package solrJ.com;

import com.connector.MySolrServerConnector;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.util.ArrayList;

/**
 * Created by Yue on 2015/10/10.
 */
public class Main {


    private static ArrayList<SolrInputDocument> documents;

    public static void main(String[] args) {

        String url = "http://localhost:8983/solr/collection1";
        MySolrServerConnector server = new MySolrServerConnector(url);

        SolrDocumentList lists = server.query("*:*", 10);

        for (SolrDocument document:lists){
            System.out.println("document = [" + document + "]");
        }

//        TwitterObject twitterObject=new TwitterObject("4","tong",234325354,"2012-05-22T09:30:22Z","zh","www.6vhao.com",5);
//        server.addBean(twitterObject);

//        server.delete("3");
        server.deleteByQuery("screen_name:tong");
    }





/*
server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
        server.setConnectionTimeout(5000); // 5 seconds to establish TCP
        // Setting the XML response parser is only required for cross
        // version compatibility and only when one side is 1.4.1 or
        // earlier and the other side is 3.1 or later.
        server.setParser(new XMLResponseParser()); // binary parser is used by default
        // The following settings are provided here for completeness.
        // They will not normally be required, and should only be used
        // after consulting javadocs to know whether they are truly required.
        server.setSoTimeout(1000);  // socket read timeout
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false);  // defaults to false
        // allowCompression defaults to false.
        // Server side must support gzip or deflate for this to have any effect.
        server.setAllowCompression(true);
 */


}
