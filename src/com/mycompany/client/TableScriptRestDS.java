package com.mycompany.client;

import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.util.JSOHelper;

public class TableScriptRestDS extends AbstractRestDS{
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	@Override
	protected void setEntityFields() {
        DataSourceTextField id = new DataSourceTextField("id",Constants.idLabel() );  
        DataSourceTextField beforeSave = new DataSourceTextField("beforeSave", Constants.beforeSaveLabel()); 
        DataSourceTextField afterSave = new DataSourceTextField("afterSave", Constants.afterSaveLabel());
        DataSourceTextField beforeFetch = new DataSourceTextField("beforeFetch", Constants.beforeFetchLabel());
        DataSourceTextField afterFetch = new DataSourceTextField("afterFetch", Constants.afterFetchLabel());
        DataSourceTextField beforeDelete = new DataSourceTextField("beforeDelete", Constants.beforeDeleteLabel());
        DataSourceTextField afterDelete = new DataSourceTextField("afterDelete", Constants.afterDeleteLabel());
        DataSourceTextField beforeInsert = new DataSourceTextField("beforeInsert", Constants.beforeInsertLabel());
        DataSourceTextField afterInsert = new DataSourceTextField("afterInsert", Constants.afterInsertLabel());
        
        addField(id);
        addField(beforeSave);
        addField(afterSave);
        addField(beforeFetch);
        addField(afterFetch);
        addField(beforeDelete);
        addField(afterDelete);
        addField(beforeInsert);
        addField(afterInsert);
        
	}

	protected Object transformRequest(DSRequest dsRequest) {
		if (dsRequest.getOperationType() == DSOperationType.FETCH) {
			JavaScriptObject jsObj = dsRequest.getData();
			Map<String,Object> map = JSOHelper.convertToMap(jsObj);
			String primaryKey = map.get("id").toString();
			dsRequest.setActionURL(getFetchDataURL() + "/" + primaryKey);
		}		
		return super.transformRequest(dsRequest);
	}
	
	@Override
	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase();
	}
	
}