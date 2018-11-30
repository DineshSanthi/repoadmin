package com.mycompany.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;

public class ColumnDS extends DataSource {

    private static ColumnDS instance = null;
    public ColumnDS()
    {
    	setDataFormat(DSDataFormat.JSON);
    //	setDataProtocol(DSProtocol.POSTMESSAGE);
    	setJsonPrefix(null);
    	setJsonSuffix(null);
    }
    
    public static ColumnDS getInstance() {
        if (instance == null) {
            instance = new ColumnDS("RestDS");
            
        }
        return instance;
    }

    public ColumnDS(String id) {

        setID(id);
        setClientOnly(false);

        DataSourceField propertyField = new DataSourceField("id", FieldType.TEXT);
        propertyField.setPrimaryKey(true);
        propertyField.setHidden(true);
        
        DataSourceField groupField = new DataSourceField("tableName", FieldType.TEXT, "Table Name");
        groupField.setCanEdit(false);

        DataSourceField labelField = new DataSourceField("columnName", FieldType.TEXT, " Column Name");
        labelField.setCanEdit(false);
        
        DataSourceField labelField1 = new DataSourceField("columnType", FieldType.TEXT, " Column Type");
        labelField1.setCanEdit(false);
        
        DataSourceField labelField2 = new DataSourceField("columnSize", FieldType.TEXT, " Column Size");
        labelField2.setCanEdit(false);

       
        
        this.setFields(propertyField,groupField,labelField,labelField1,labelField2);
        
        this.setDataURL("http://127.0.0.1:8080/column/all");

    } 
    
}
