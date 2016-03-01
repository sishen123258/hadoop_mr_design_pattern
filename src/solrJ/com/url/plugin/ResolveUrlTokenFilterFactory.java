package solrJ.com.url.plugin;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

import java.util.Map;
import java.util.regex.Pattern;


public class ResolveUrlTokenFilterFactory extends TokenFilterFactory {

    private Pattern patternToMatchShortenedUrls;

    public ResolveUrlTokenFilterFactory(Map<String, String> args) {
        super(args);
        assureMatchVersion();
        //从solr读取的配置文件信息中获取正则表达式信息
        String shortenedUrls=require(args,"shortenedUrlPattern");
        patternToMatchShortenedUrls=Pattern.compile(shortenedUrls);
    }

    @Override
    public TokenFilter create(TokenStream tokenStream) {
        //创建ResolveUrlTokenFilter实例对象
        return new ResolveUrlTokenFilter(tokenStream,patternToMatchShortenedUrls);
    }
}
