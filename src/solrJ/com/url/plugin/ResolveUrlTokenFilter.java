package solrJ.com.url.plugin;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Pattern;


public class ResolveUrlTokenFilter extends TokenFilter {
    public static final Logger log = LoggerFactory.getLogger(ResolveUrlTokenFilter.class);

    private final CharTermAttribute charTermAttribute=addAttribute(CharTermAttribute.class);
    private final Pattern patternToMatchShortenedUrls;

    public ResolveUrlTokenFilter(TokenStream input, Pattern patternToMatchShortenedUrls) {
        super(input);
        this.patternToMatchShortenedUrls = patternToMatchShortenedUrls;
    }

    @Override
    public boolean incrementToken() throws IOException {
        if (!input.incrementToken())
            return false;

        //charTermAttribute会保存读取char
        char[] term=charTermAttribute.buffer();
        int len=term.length;
        //构造字符串
        String token=new String(term,0,len);
        log.info("token = [" + token + "]");
        //匹配token中是否出现我们需要重构的场景
        if(patternToMatchShortenedUrls.matcher(token).matches()){
            charTermAttribute.setEmpty().append(resolveUrlToken(token));
        }

        return true;
    }

    private String resolveUrlToken(String token) {
        //TODO 根据实际需求处理token
        try {
            if ("http://bit.ly/3ynriE".equals(token)) {
                log.info("token [lucene.apache.org/solr]");
                return "lucene.apache.org/solr";
            } else if ("http://bit.ly/15tzw".equals(token)) {
                return "manning.com";
            }
        } catch (Exception exc) {
            // rather than failing analysis if you can't resolve the URL,
            // you should log the error and return the un-resolved value
            exc.printStackTrace();
        }
        log.info("token = [" + token + "]");
        return token;
    }
}
