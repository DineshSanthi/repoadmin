package com.mycompany.client;

import com.smartgwt.client.data.fields.DataSourceTextField;

public class ReferenceRestDS extends AbstractRestDS{
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	@Override
	protected void setEntityFields() {
        DataSourceTextField refId = new DataSourceTextField("refId", Constants.referenceIdLabel());  
        DataSourceTextField description = new DataSourceTextField("description",Constants.descriptionLabel());    
        addField(refId);
        addField(description);
	}

	@Override
	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase();
	}
	
}