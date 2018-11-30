package com.mycompany.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.widgets.grid.ListGrid;

public class UserRestDS extends AbstractRestDS{
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	@Override
	protected void setEntityFields() {
        DataSourceTextField userName = new DataSourceTextField("userName", "User Name");  
        DataSourceTextField email = new DataSourceTextField("email", "Email");
        DataSourceTextField firstName = new DataSourceTextField("firstName", "First Name");
        DataSourceTextField lastName = new DataSourceTextField("lastName","Last Name");
       
        setFields(userName,email,firstName,lastName);
        
	}
	
	@Override
	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase();
	}
	
}