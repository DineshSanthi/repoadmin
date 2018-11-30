package com.mycompany.client;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ColumnType {

	private static ListGridRecord[] records;    
    
    public static ListGridRecord[] getRecords() {  
        if (records == null) {  
            records = getNewRecords();    
        }    
        return records;    
    }    
    
    public static ListGridRecord createRecord(String columnType){
    	
    	ListGridRecord record = new ListGridRecord();  
        record.setAttribute("columnType", columnType);  
        return record;
    }
    
    public static ListGridRecord[] getNewRecords() {  
        return new ListGridRecord[]{ 		
        		createRecord("string"),
        		createRecord("double"),
        		createRecord("object"),
        		createRecord("array"),
        		createRecord("binData"),
        		createRecord("objectId"),
        		createRecord("date"),
        		createRecord("decimal"),
        		createRecord("long"),
        		createRecord("int"),
        		createRecord("javascript")
        		
        };
    }
    
}
