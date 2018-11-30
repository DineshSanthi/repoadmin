package com.mycompany.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;

public class ResultsRestDS extends DataSource {

    private static ResultsRestDS instance = null;
    public ResultsRestDS()
    {
    	setDataFormat(DSDataFormat.JSON);
    //	setDataProtocol(DSProtocol.POSTMESSAGE);
    	setJsonPrefix(null);
    	setJsonSuffix(null);
    }
    private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
    public static ResultsRestDS getInstance() {
        if (instance == null) {
            instance = new ResultsRestDS("RestDS");
            
        }
        return instance;
    }

    public ResultsRestDS(String id) {

        setID(id);
        setClientOnly(false);
      /*  OperationBinding fetchOperationBinding = new OperationBinding();
        fetchOperationBinding.setOperationType(DSOperationType.FETCH);
     //   fetchOperationBinding.setDataProtocol(DSProtocol.POSTMESSAGE);
        fetchOperationBinding.setDataURL("http://127.0.0.1:8080/application/all");

        this.setOperationBindings(fetchOperationBinding);*/

        DataSourceField propertyField = new DataSourceField("id", FieldType.TEXT);
        propertyField.setPrimaryKey(true);
        propertyField.setHidden(true);

        DataSourceField labelField = new DataSourceField("tableName", FieldType.TEXT, Constants.tableNameLabel());
        labelField.setCanEdit(false);
        labelField.setPrimaryKey(true);

        DataSourceField groupField = new DataSourceField("tableDescription", FieldType.TEXT, Constants.descriptionLabel());
        groupField.setCanEdit(false);
        
        this.setFields(propertyField,labelField,groupField);
        
        this.setDataURL("http://127.0.0.1:8080/customer/all");

    } 
    
}
