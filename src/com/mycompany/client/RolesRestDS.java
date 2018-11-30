package com.mycompany.client;

import com.smartgwt.client.data.fields.DataSourceTextField;

public class RolesRestDS extends AbstractRestDS{
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	@Override
	protected void setEntityFields() {
        DataSourceTextField roleName = new DataSourceTextField("roleName", Constants.roleNameLabel());  
        DataSourceTextField roleDesc = new DataSourceTextField("description", Constants.descriptionLabel());

        setFields(roleName, roleDesc);
        
	}
	
	@Override
	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase();
	}
	
}