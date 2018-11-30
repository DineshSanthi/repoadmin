package com.mycompany.client;

import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.JSON;

public class ColumnScriptRestDS extends AbstractRestDS{
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	@Override
	protected void setEntityFields() {
        DataSourceTextField id = new DataSourceTextField("id",Constants.idLabel() );  
        
        DataSourceTextField requiredEvent = new DataSourceTextField("requiredEvent", Constants.requiredEventLabel());
        DataSourceTextField hiddenEvent = new DataSourceTextField("hiddenEvent", Constants.hiddenEventLabel());
        DataSourceTextField disabledEvent = new DataSourceTextField("disabledEvent", Constants.disabledEventLabel());
        DataSourceTextField readOnlyEvent = new DataSourceTextField("readOnlyEvent", Constants.readOnlyEventLabel());
       
        
        addField(id);
        addField(requiredEvent);
        addField(hiddenEvent);
        addField(disabledEvent);
        addField(readOnlyEvent);
        
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