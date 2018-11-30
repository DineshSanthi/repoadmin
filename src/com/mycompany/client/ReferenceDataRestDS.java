package com.mycompany.client;

import com.smartgwt.client.data.fields.DataSourceTextField;

public class ReferenceDataRestDS extends AbstractRestDS{
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	@Override
	protected void setEntityFields() {
        DataSourceTextField refId = new DataSourceTextField("refId", Constants.referenceIdLabel()); 
        DataSourceTextField code = new DataSourceTextField("code", Constants.codeLabel()); 
        DataSourceTextField displayValue = new DataSourceTextField("displayValue", Constants.displayValueLabel()); 
        DataSourceTextField displayValueTamil = new DataSourceTextField("displayValueTamil", Constants.displayValueTamilLabel()); 
        DataSourceTextField displayValueTelugu = new DataSourceTextField("displayValueTelugu", Constants.displayValueTeluguLabel()); 
        addField(refId);
        addField(code);
        addField(displayValue);
        addField(displayValueTamil);
        addField(displayValueTelugu);
	}

	@Override
	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase();
	}
	
}