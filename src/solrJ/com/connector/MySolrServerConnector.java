package solrJ.com.connector;

import com.domain.TwitterObject;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Yue on 2015/10/14.
 */
public class MySolrServerConnector {

    private SolrServer solrServer;

    public MySolrServerConnector(String url) {
        this.solrServer = new HttpSolrServer(url);
//        this.solrServer = new EmbeddedSolrServer();
    }


    public SolrResponse addDocs(Collection<SolrInputDocument> documents){
        try {
            UpdateResponse response = solrServer.add(documents);
            System.out.println("response = [" + response.getStatus() + "]");
            solrServer.commit();
            return response;
        } catch (SolrServerException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public SolrDocumentList query(String query,int rows){
        if(query==null || query.equals("")){
            return null;
        }
        SolrQuery solrQuery=new SolrQuery(query);
        solrQuery.setRows(rows);
        try {
            QueryResponse queryResponse = solrServer.query(solrQuery);
            SolrDocumentList list=queryResponse.getResults();
            return list;
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int delete(String id){
        try {
            UpdateResponse updateResponse = solrServer.deleteById(id);
            solrServer.optimize();
            return updateResponse.getStatus();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int addBean(TwitterObject twitterObject){
        try {
            UpdateResponse updateResponse = solrServer.addBean(twitterObject);
            solrServer.optimize();
            return updateResponse.getStatus();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void deleteByQuery(String query){
        try {
            solrServer.deleteByQuery(query);
            solrServer.optimize();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
