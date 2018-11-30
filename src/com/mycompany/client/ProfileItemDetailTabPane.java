package com.mycompany.client;

/*
 * SmartGWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * SmartGWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  SmartGWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class ProfileItemDetailTabPane extends AbstractTabPane {

	private ResultsLayout resultsLayout;
	private ListGrid permissionListGrid;
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

	public ProfileItemDetailTabPane( final String applicationName,DataSource profileRestDS,DataSource permissionRestDS,ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
		this.resultsLayout = resultsLayout;

		final DynamicForm orderForm = new DynamicForm();
		orderForm.setDataSource(profileRestDS);
		orderForm.setWidth100();

		TextItem profileId = new TextItem("id");
		profileId.setTitle("ID");
		profileId.setDisabled(true);
		profileId.setHidden(true);
		profileId.setWidth("*");
		
		TextItem appName = new TextItem("appName");
		appName.setTitle(Constants.appNameLabel());
		appName.setDefaultValue(applicationName);
		appName.setHidden(true);
		
		TextItem profileName = new TextItem("propertyName");
		profileName.setTitle(Constants.propertyNameLabel());
		profileName.setRequired(true);

		orderForm.setFields(profileId, appName, profileName);

		resultsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
			public void onSelectionChanged(SelectionEvent event) {
				Record record = event.getRecord();
				orderForm.editRecord(record);
			}
		});

		permissionListGrid =new ListGrid();
		permissionListGrid.setWidth100();  
		permissionListGrid.setHeight100();  
		permissionListGrid.setShowAllRecords(true);  
		permissionListGrid.setCellHeight(42);  
		permissionListGrid.setWrapCells(true);  
		permissionListGrid.setDataSource(permissionRestDS);
		
		ListGridField permissionId = new ListGridField("id","Permission ID");
		permissionId.setCanEdit(false);
		permissionId.setHidden(true);
        
		ListGridField appPermissionName = new ListGridField("appName",Constants.appNameLabel());
		appPermissionName.setCanEdit(false);
		
		ListGridField tableName = new ListGridField("tableName",Constants.tableNameLabel());
		tableName.setCanEdit(false);
		
		ListGridField operationId = new ListGridField("operationId",Constants.operationIdLabel());
		operationId.setCanEdit(false);
		
		permissionListGrid.setFields(permissionId,appPermissionName,tableName,operationId);
		permissionListGrid.setAutoFetchData(true);
		
		EditPanelToolStrip toolStrip = new EditPanelToolStrip(orderForm);

		Tab editTab = new Tab(Constants.editTabLabel());
		editTab.setIcon("demoApp/icon_edit.png");
		editTab.setWidth(70);
		
		Tab permissionTab = new Tab("Permission");
		permissionTab.setIcon("demoApp/icon_edit.png");
		permissionTab.setWidth(70);
		permissionTab.setPane(permissionListGrid);
		


		VLayout layout = new VLayout();
		layout.addMember(toolStrip);
		layout.addMember(orderForm);
		editTab.setPane(layout);

		setTabs(editTab,permissionTab);
	}

}
