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
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class GlobalPropertiesItemDetailTabPane extends AbstractTabPane {

	private ResultsLayout resultsLayout;
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

	public GlobalPropertiesItemDetailTabPane( DataSource globalPropsRestDS,ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
		this.resultsLayout = resultsLayout;

		final DynamicForm orderForm = new DynamicForm();
		orderForm.setDataSource(globalPropsRestDS);
		orderForm.setWidth100();

		TextItem propId = new TextItem("id");
		propId.setTitle("ID");
		propId.setDisabled(true);
		propId.setHidden(true);
		propId.setWidth("*");
		

		TextItem propertyName = new TextItem("propertyName");
		propertyName.setTitle(Constants.propertyNameLabel());
		propertyName.setRequired(true);

		TextItem propertyDesc = new TextItem("propertyDesc");
		propertyDesc.setTitle(Constants.propertyDescLabel());
		propertyDesc.setRequired(true);

		TextItem propertyValue = new TextItem("propertyValue");
		propertyValue.setTitle(Constants.propertyValueLabel());
		propertyValue.setRequired(true);

		orderForm.setFields(propId, propertyName, propertyDesc, propertyValue);

		resultsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
			public void onSelectionChanged(SelectionEvent event) {
				Record record = event.getRecord();
				orderForm.editRecord(record);
			}
		});

		EditPanelToolStrip toolStrip = new EditPanelToolStrip(orderForm);

		Tab editTab = new Tab(Constants.editTabLabel());
		editTab.setIcon("demoApp/icon_edit.png");
		editTab.setWidth(70);

		VLayout layout = new VLayout();
		layout.addMember(toolStrip);
		layout.addMember(orderForm);
		editTab.setPane(layout);

		setTabs(editTab);
	}

}
