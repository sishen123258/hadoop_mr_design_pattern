package solrJ.com.multilanguage;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yue on 2015/11/22.
 */
public class MultiTextFieldInput {

    public final char keyFromTextDelimiter;
    public final char multiKeyDelimiter;

    public List<String> keys;
    public Reader reader;
    public Integer strippedIncomingPrefixLength;

    public MultiTextFieldInput(Reader reader, char keyFromTextDelimiter, char multiKeyDelimiter) throws IOException {
        this.keyFromTextDelimiter = keyFromTextDelimiter;
        this.multiKeyDelimiter = multiKeyDelimiter;
        setReader(reader);
    }


    public void setReader(Reader reader) throws IOException {

        StringBuffer beforeDelimiter=new StringBuffer();
        StringBuffer afterDelimiter=new StringBuffer();

        this.keys=new LinkedList<String>();
        this.strippedIncomingPrefixLength=0;
        boolean delimiterWasHit=false;

        for (int nextChar=reader.read();nextChar!=-1;nextChar=reader.read()){
            if (!delimiterWasHit && (char)nextChar==this.keyFromTextDelimiter){
                delimiterWasHit=true;
            }else {
                if (delimiterWasHit){
                    afterDelimiter.append((char)nextChar);
                }else {
                    afterDelimiter.append((char)nextChar);
                }
            }
        }

        String textAfterDelimiter = afterDelimiter.toString();
        String textBeforeDelimiter = beforeDelimiter.toString();

        if (delimiterWasHit){
            this.strippedIncomingPrefixLength = (textBeforeDelimiter + this.multiKeyDelimiter).length();

            //special case: if prefix is wrapped in [ ], this means the prefix does not exist
            //in the stored version of this field.  As such, we should adjust the position offsets.
            //i.e. [en,es|]hablo spanish.  In this case, the en,es| were added inside solr by language
            //detection or were stripped out before the stored values were saved
            if ( textBeforeDelimiter.startsWith("[") && textAfterDelimiter.startsWith("]") ){
                this.strippedIncomingPrefixLength = 0;
                textBeforeDelimiter = textBeforeDelimiter.substring(1);
                textAfterDelimiter = textAfterDelimiter.substring(1);
            }

            String[] multiKeysArray = textBeforeDelimiter.split(String.valueOf(this.multiKeyDelimiter));
            String currentKey;
            for (int i = 0; i < multiKeysArray.length; i++){
                if ( multiKeysArray[i] != null){
                    currentKey = multiKeysArray[i].trim();
                    if (currentKey.length() > 0 && !this.keys.contains(currentKey) ){
                        this.keys.add(currentKey);
                    }
                }
            }
        }
        else{
            textAfterDelimiter = textBeforeDelimiter;
            textBeforeDelimiter = "";
            this.strippedIncomingPrefixLength = 0;
        }

        this.reader = new StringReader(textAfterDelimiter);

    }
}
