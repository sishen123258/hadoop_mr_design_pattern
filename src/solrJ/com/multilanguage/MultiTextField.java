package solrJ.com.multilanguage;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexableField;
import org.apache.solr.common.SolrException;
import org.apache.solr.schema.IndexSchema;
import org.apache.solr.schema.PreAnalyzedField;
import org.apache.solr.schema.SchemaField;
import org.apache.solr.schema.TextField;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yue on 2015/11/19.
 */
public class MultiTextField extends TextField {
    //fieldType 里面的属性
    private final static String FIELD_MAPPINGS = "fieldMappings";
    private final static String DEFAULT_FIELDTYPE = "defaultFieldType";
    private final static String IGNORE_INVALID_MAPPINGS = "ignoreMissingMappings";
    private final static String KEY_FROM_TEXT_DELIMITER = "keyFromTextDelimiter";
    private final static String MULTI_KEY_DELIMITER = "multiKeyDelimiter";
    private final static String REMOVE_DUPLICATES = "removeDuplicates";

    @Override
    protected void init(IndexSchema schema, Map<String, String> args) {
        super.init(schema, args);
        //defaults
        char keyFromTextDelimiter = '|';
        char multiKeyDelimiter = ',';
        String defaultFieldTypeName = "";
        HashMap<String, String> fieldMappings = null;
        boolean ignoreMissingMappings = false;
        boolean removeDuplicates = true;
        boolean hidePrependedLangs = false;

        if(args.containsKey(KEY_FROM_TEXT_DELIMITER)){
            String delimiter=args.get(KEY_FROM_TEXT_DELIMITER);
            if (delimiter.length()==1){
                keyFromTextDelimiter=delimiter.charAt(0);
            }else {
                if (delimiter.length()>1){
                    throw new SolrException(SolrException.ErrorCode.SERVER_ERROR,"Schema configuration"
                            + " error for " + this.getClass().getSimpleName() + "."
                            + "Attribute 'keyFromTextDelimiter' must be a single character.");
                }
            }
            args.remove(KEY_FROM_TEXT_DELIMITER);
        }

        if(args.containsKey(MULTI_KEY_DELIMITER)){
            String delimiter=args.get(MULTI_KEY_DELIMITER);
            if (delimiter.length() == 1){
                multiKeyDelimiter = delimiter.charAt(0);
            }
            else{
                if (delimiter.length() > 1){
                    throw new SolrException(SolrException.ErrorCode.SERVER_ERROR, "Schema configuration"
                            + " error for " + this.getClass().getSimpleName() + "."
                            + "Attribute 'multiKeyDelimiter' must be a single character.");
                }
            }
            args.remove(MULTI_KEY_DELIMITER);
        }

        if (args.containsKey(DEFAULT_FIELDTYPE)){
            if (schema.getFieldTypes().containsKey(args.get(DEFAULT_FIELDTYPE))){
                defaultFieldTypeName = args.get(DEFAULT_FIELDTYPE);
            }
            else {
                throw new SolrException(SolrException.ErrorCode.SERVER_ERROR, "Invalid defaultFieldType defined in "
                        + this.getClass().getSimpleName() + ". FieldType '" + args.get(DEFAULT_FIELDTYPE)
                        + " does not exist in the schema.");

            }
            args.remove(DEFAULT_FIELDTYPE);
        }

        if (args.containsKey(FIELD_MAPPINGS)){
            HashMap<String,String> possibleFieldMappings=new HashMap<String, String>();
            if (args.get(FIELD_MAPPINGS).length()>0){
                String[] mappingPairs=args.get(FIELD_MAPPINGS).split(",");
                for (int i=0;i<mappingPairs.length;i++){
                    String[] mapping=mappingPairs[i].split(":");
                    for (int j=0;j<mapping.length;j++){
                        String key="";
                        String fieldType="";
                        if(mapping.length==2){
                            key=mapping[0].trim();
                            fieldType=mapping[1].trim();
                        }else if (mapping.length==1){
                            fieldType = mapping[1].trim();
                            key = fieldType;
                        }
                        if (mapping.length == 0 || mapping.length > 2){
                            throw new SolrException(SolrException.ErrorCode.SERVER_ERROR,
                                    "Schema configuration error for " + this.getClass().getSimpleName()
                                            + ". Field Mapping '" + mappingPairs [i] + "' is syntactically incorrect.");
                        }
                        else{
                            if (!schema.getFieldTypes().containsKey(fieldType)){
                                throw new SolrException(SolrException.ErrorCode.SERVER_ERROR,
                                        "Schema configuration error for " + this.getClass().getSimpleName()
                                                + ".  FieldType '" + fieldType + "' is not defined.");
                            }
                        }
                        possibleFieldMappings.put(key, fieldType);
                    }
                }
            }
        }

        if(args.containsKey(IGNORE_INVALID_MAPPINGS)){
            ignoreMissingMappings=Boolean.parseBoolean(args.get(IGNORE_INVALID_MAPPINGS));
            args.remove(IGNORE_INVALID_MAPPINGS);
        }

        if(args.containsKey(REMOVE_DUPLICATES)){
            removeDuplicates=Boolean.parseBoolean(args.get(REMOVE_DUPLICATES));
            args.remove(REMOVE_DUPLICATES);
        }

        MultiTextFieldSettings indexSetting=new MultiTextFieldSettings(
                MultiTextFieldSettings.AnalyzerModes.index,keyFromTextDelimiter,
                multiKeyDelimiter,defaultFieldTypeName,fieldMappings,
                ignoreMissingMappings,removeDuplicates,hidePrependedLangs);

        MultiTextFieldSettings querySetting=new MultiTextFieldSettings(
                MultiTextFieldSettings.AnalyzerModes.query,keyFromTextDelimiter,
                multiKeyDelimiter,defaultFieldTypeName,fieldMappings,
                ignoreMissingMappings,removeDuplicates,hidePrependedLangs);

        MultiTextFieldSettings multiTermSettings=new MultiTextFieldSettings(
                MultiTextFieldSettings.AnalyzerModes.multiTerm,keyFromTextDelimiter,
                multiKeyDelimiter,defaultFieldTypeName,fieldMappings,
                ignoreMissingMappings,removeDuplicates,hidePrependedLangs);

        MultiTextFieldAnalyzer indexAnalyzer=new MultiTextFieldAnalyzer(indexSetting,schema);
        MultiTextFieldAnalyzer queryAnalyzer=new MultiTextFieldAnalyzer(querySetting,schema);
        MultiTextFieldAnalyzer multiTermAnalyzer=new MultiTextFieldAnalyzer(multiTermSettings,schema);

        this.setAnalyzer(indexAnalyzer);
        this.setQueryAnalyzer(queryAnalyzer);
        this.setMultiTermAnalyzer(multiTermAnalyzer);
    }

    @Override
    public IndexableField createField(SchemaField field, Object value, float boost) {
        String indexableValue=String.valueOf(value);
        String storeableValue=indexAbleToStoreable(indexableValue);
        if (indexableValue.equals(storeableValue)){
            return super.createField(field,value,boost);
        }

        IndexableField f = null;

        try {
            f = separateIndexableFromStorableValue(field, indexableValue, storeableValue, boost);
        } catch (Exception e) {
            return null;
        }
        return f;
    }

    private IndexableField separateIndexableFromStorableValue(SchemaField field, String indexableValue,
                                                              String storableValue, float boost) {

        if (indexableValue == null || indexableValue.trim().length() == 0
                || storableValue == null || storableValue.trim().length() == 0) {
            return null;
        }

        FieldType type= PreAnalyzedField.createFieldType(field);
        if (type == null) {
            return null;
        }
        Field f = null;
        if (field.stored()) {
            f = new Field(field.getName(), storableValue, type);
        } else {
            type.setStored(false);
        }

        if (field.indexed()) {
            type.setIndexed(true);
            type.setTokenized(true);
            TokenStream indexableTokenStream = ((MultiTextFieldAnalyzer)this.getAnalyzer()).
                    createComponents(field.getName(), new StringReader(indexableValue)).getTokenStream();
            if (f != null) {
                f.setTokenStream(indexableTokenStream);
            } else {
                f = new Field(field.getName(), indexableTokenStream, type);
            }
        } else {
            if (f != null) {
                f.fieldType().setIndexed(false);
                f.fieldType().setTokenized(false);
            }
        }

        if (f != null) {
            f.setBoost(boost);
        }
        return f;
    }

    private String indexAbleToStoreable(String indexableValue) {
        String storableValue = indexableValue;
        if (indexableValue.startsWith("[") && indexableValue.contains("|]")){
            storableValue = indexableValue.substring(indexableValue.indexOf("|]") + "|]".length(), indexableValue.length() -1);
        }
        return storableValue;
    }
}
