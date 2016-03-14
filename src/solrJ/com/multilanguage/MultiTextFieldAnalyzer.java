/*
package solrJ.com.multilanguage;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.miscellaneous.RemoveDuplicatesTokenFilter;
import org.apache.solr.schema.IndexSchema;

import java.io.Reader;

*/
/**
* Created by Yue on 2015/11/19.
*//*

public class MultiTextFieldAnalyzer extends Analyzer{

    protected IndexSchema indexSchema;

    public final MultiTextFieldSettings settings;

    public MultiTextFieldAnalyzer(MultiTextFieldSettings settings, IndexSchema indexSchema) {
        super(new PerFieldReuseStrategy());
        this.settings = settings;
        this.indexSchema = indexSchema;
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
        MultiTextFieldTokenizer tokenizer=new MultiTextFieldTokenizer(indexSchema,reader,fieldName,settings);
        Tokenizer source = tokenizer;
        TokenStream result = tokenizer;
        if (settings.removeDuplicates){
            result = new RemoveDuplicatesTokenFilter(tokenizer);
        }
        return new TokenStreamComponents(source, result);
    }
}
*/
