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
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class RelationItemDetailTabPane extends AbstractTabPane {

    private ListGrid editGrid;
    private Label editorLabel;
    private ResultsLayout resultsLayout;
    private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

    public RelationItemDetailTabPane(final String appName,final DataSource objectDS,final DataSource tableRestDS, ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
        this.resultsLayout = resultsLayout;

        editorLabel = new Label();
        editorLabel.setWidth100();
        editorLabel.setHeight100();
        editorLabel.setAlign(Alignment.CENTER);
        editorLabel.setContents("Select a record to edit, or a category to insert a new record into");

        final DynamicForm orderForm = new DynamicForm();  
        orderForm.setDataSource(objectDS); 
        
        TextItem applicationName = new TextItem("appName");
        applicationName.setHidden(true);
        applicationName.setDefaultValue(appName);
  
        
        TextItem appId = new TextItem("id");  
        appId.setTitle(Constants.idLabel());  
        appId.setDisabled(true);
        appId.setHidden(true);
  
        SelectItem primaryTable = new SelectItem("primaryTable");  
        primaryTable.setTitle(Constants.primaryTableLabel());  
        primaryTable.setWidth(240);  
        primaryTable.setOptionDataSource(tableRestDS);  
        primaryTable.setValueField("tableName");  
        primaryTable.setDisplayField("tableName");  
        primaryTable.setPickListWidth(450);
        primaryTable.setRequired(true);
        
        SelectItem secondaryTable = new SelectItem("secondaryTable");  
        secondaryTable.setTitle(Constants.secondaryTableLabel());  
        secondaryTable.setWidth(240);  
        secondaryTable.setOptionDataSource(tableRestDS);  
        secondaryTable.setValueField("tableName");  
        secondaryTable.setDisplayField("tableName");  
        secondaryTable.setPickListWidth(450); 
        secondaryTable.setRequired(true);
      
        TextItem minimumMultiplicity = new TextItem("minimumMultiplicity");  
        minimumMultiplicity.setTitle(Constants.minimumMultiplicityLabel()); 
        minimumMultiplicity.setRequired(true);
        
        TextItem maximumMultiplicity = new TextItem("maximumMultiplicity");  
        maximumMultiplicity.setTitle(Constants.maximumMultiplicityLabel());  
        
        
  
        orderForm.setFields(applicationName,appId,primaryTable, secondaryTable,minimumMultiplicity,maximumMultiplicity); 
        
        
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

