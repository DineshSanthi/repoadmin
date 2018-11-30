package com.mycompany.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.widgets.grid.ListGrid;

public class GlobalPropertiesRestDS extends AbstractRestDS{
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	@Override
	protected void setEntityFields() {
        DataSourceTextField propertyName = new DataSourceTextField("propertyName", Constants.propertyNameLabel());  
        DataSourceTextField propertyDesc = new DataSourceTextField("propertyDesc", Constants.propertyDescLabel());
        DataSourceTextField propertyValue = new DataSourceTextField("propertyValue", Constants.propertyValueLabel());

        setFields(propertyName, propertyDesc,propertyValue);
        
	}
	
	@Override
	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase();
	}
	
}