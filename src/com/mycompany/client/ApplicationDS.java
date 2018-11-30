package com.mycompany.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;

public class ApplicationDS extends DataSource {
	private static int count = 0;
    private static ApplicationDS instance = null;
    public ApplicationDS()
    {
    	setDataFormat(DSDataFormat.JSON);
    //	setDataProtocol(DSProtocol.POSTMESSAGE);
    	setJsonPrefix(null);
    	setJsonSuffix(null);
    }
    
    public static ApplicationDS getInstance() {
        if (instance == null) {
        	count = count + 1;
            instance = new ApplicationDS("RestDS" + count);
            
        }
        return instance;
    }

    public ApplicationDS(String id) {

        setID(id);
        setClientOnly(false);

        DataSourceField propertyField = new DataSourceField("id", FieldType.TEXT);
        propertyField.setPrimaryKey(true);
        propertyField.setHidden(true);

        DataSourceField labelField = new DataSourceField("appName", FieldType.TEXT, "Name");
        labelField.setCanEdit(false);

        DataSourceField groupField = new DataSourceField("appDescription", FieldType.TEXT, "Description");
        groupField.setCanEdit(false);
        
        this.setFields(propertyField,labelField,groupField);
        
        this.setDataURL("http://localhost:8080/application/all");

    } 
    
   /* private static ApplicationDS instance = null;
    
    public static ApplicationDS getInstance() {
     if(instance == null) {
      instance = new ApplicationDS("RestDS");
     }
     
     return instance;
    }
    
    private ApplicationDS(String id) {
     super(id);
    }
    
    protected void init() {
     setDataFormat(DSDataFormat.JSON);
     setJsonRecordXPath("/");
     
     DataSourceField idField = new DataSourceField("id", FieldType.INTEGER, "ID");
     idField.setPrimaryKey(true);
     idField.setCanEdit(false);
     idField.setHidden(true);

     DataSourceField labelField = new DataSourceField("appName", FieldType.TEXT, "Name");

     DataSourceField groupField = new DataSourceField("appDescription", FieldType.TEXT, "Description");
     
     setFields(idField, labelField,groupField);
    }

    @Override
    protected String getServiceRoot() {
     return "http://127.0.0.1:8080/application/all";
    }*/
    
}
