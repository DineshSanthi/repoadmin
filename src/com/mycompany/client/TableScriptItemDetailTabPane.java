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
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class TableScriptItemDetailTabPane extends AbstractTabPane {

    private ListGrid editGrid;
    private Label editorLabel;
    private ResultsLayout resultsLayout;
    private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

    public TableScriptItemDetailTabPane(DataSource tableScriptDS,DataSource tableRestDS,ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
        this.resultsLayout = resultsLayout;

        editorLabel = new Label();
        editorLabel.setWidth100();
        editorLabel.setHeight100();
        editorLabel.setAlign(Alignment.CENTER);
        editorLabel.setContents("Select a record to edit, or a category to insert a new record into");

        final DynamicForm tableScriptForm = new DynamicForm();  
        tableScriptForm.setDataSource(tableScriptDS);  
  
        SelectItem tableName = new SelectItem("id");  
        tableName.setTitle(Constants.idLabel());  
        tableName.setWidth(150);  
        tableName.setOptionDataSource(tableRestDS);  
        tableName.setValueField("id");  
        tableName.setDisplayField("tableName");  
        tableName.setPickListWidth(150);
        tableName.setRequired(true);
        
        TextAreaItem beforeSave = new TextAreaItem("beforeSave");  
        beforeSave.setTitle(Constants.beforeSaveLabel());  
        
        TextAreaItem afterSave = new TextAreaItem("afterSave");  
        afterSave.setTitle(Constants.afterSaveLabel());  
        
        TextAreaItem beforeFetch = new TextAreaItem("beforeFetch");  
        beforeFetch.setTitle(Constants.beforeFetchLabel()); 
        
        TextAreaItem afterFetch = new TextAreaItem("afterFetch");  
        afterFetch.setTitle(Constants.afterFetchLabel()); 
        
        TextAreaItem beforeDelete = new TextAreaItem("beforeDelete");  
        beforeDelete.setTitle(Constants.beforeDeleteLabel()); 
      
        TextAreaItem afterDelete = new TextAreaItem("afterDelete");  
        afterDelete.setTitle(Constants.afterDeleteLabel());  
        
        TextAreaItem beforeInsert = new TextAreaItem("beforeInsert");  
        beforeInsert.setTitle(Constants.beforeInsertLabel());
        
        
        TextAreaItem afterInsert = new TextAreaItem("afterInsert");  
        afterInsert.setTitle(Constants.afterInsertLabel());
        
  
        tableScriptForm.setFields(tableName, beforeSave, afterSave,beforeFetch,afterFetch,beforeDelete,afterDelete,beforeInsert,afterInsert); 
        
        
        resultsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {  
            public void onSelectionChanged(SelectionEvent event) {  
                Record record = event.getRecord();  
                tableScriptForm.editRecord(record);  
            }  
        });  
        
        
        EditPanelToolStrip toolStrip = new EditPanelToolStrip(tableScriptForm);


        Tab editTab = new Tab(Constants.editTabLabel());
        editTab.setIcon("demoApp/icon_edit.png");
        editTab.setWidth(70);
        
        VLayout layout = new VLayout();
        layout.addMember(toolStrip);
        layout.addMember(tableScriptForm);
        editTab.setPane(layout);
               
        setTabs(editTab);

        
     
    }
	
}

