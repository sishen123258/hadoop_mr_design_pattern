package solrJ.com.domain;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by Yue on 2015/11/7.
 */
public class TwitterObject {

    @Field
    private String id;
    @Field("screen_name")
    private String name;
    @Field
    private long user_id;
    @Field("timestamp")
    private String date;
    @Field
    private String lang;
    @Field
    private String link;
    @Field
    private int favorites_count;

    public TwitterObject() {
    }

    public TwitterObject(String id, String name, long user_id, String date, String lang, String link, int favorites_count) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
        this.date = date;
        this.lang = lang;
        this.link = link;
        this.favorites_count = favorites_count;
    }
}
