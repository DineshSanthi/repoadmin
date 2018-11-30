package com.mycompany.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class ColumnTypeDS extends DataSource {
	
	private static ColumnTypeDS instance = null;  
    
    public static ColumnTypeDS getInstance() {  
        if (instance == null) {  
            instance = new ColumnTypeDS("columnTypeDS");  
        }  
        return instance;  
    }  
    
    public ColumnTypeDS(String id) {  
        setID(id);  
          
        DataSourceTextField columnTypeField = new DataSourceTextField("columnType", "Column Type");  
        setFields(columnTypeField);  
          
        setTestData(ColumnType.getNewRecords());  
        setClientOnly(true);  
    }  
}  
    


