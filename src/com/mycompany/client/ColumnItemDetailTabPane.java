package com.mycompany.client;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;

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
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

public class ColumnItemDetailTabPane extends AbstractTabPane {

    private ListGrid editGrid;
    private Label editorLabel;
    private ResultsLayout resultsLayout;
    private TabSet leftTabSet = new TabSet();  
    private DynamicForm columnScriptForm = new DynamicForm();  
    
    public AceEditor getEd() {
		return ed;
	}

	public void setEd(AceEditor ed) {
		this.ed = ed;
	}

	public AceEditor getEd1() {
		return ed1;
	}

	public void setEd1(AceEditor ed1) {
		this.ed1 = ed1;
	}

	public AceEditor getEd2() {
		return ed2;
	}

	public void setEd2(AceEditor ed2) {
		this.ed2 = ed2;
	}

	public AceEditor getEd3() {
		return ed3;
	}

	public void setEd3(AceEditor ed3) {
		this.ed3 = ed3;
	}

	private AceEditor ed = new AceEditor();
    private AceEditor ed1 = new AceEditor();
    private AceEditor ed2 = new AceEditor();
    private AceEditor ed3 = new AceEditor();
    
    private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

    public ColumnItemDetailTabPane(final String appName,DataSource objectDS,DataSource tableRestDS,DataSource referenceDS,DataSource columnTypeDS,DataSource defaultDataDS,final ColumnScriptRestDS columnScriptDS, ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
        this.resultsLayout = resultsLayout;

        editorLabel = new Label();
        editorLabel.setWidth100();
        editorLabel.setHeight100();
        editorLabel.setAlign(Alignment.CENTER);
        editorLabel.setContents("Select a record to edit, or a category to insert a new record into");

        final DynamicForm orderForm = new DynamicForm();  
        orderForm.setDataSource(objectDS);  
  
        TextItem columnId = new TextItem("id");  
        columnId.setTitle("ID");  
        columnId.setDisabled(true); 
        columnId.setHidden(true);
        
        TextItem applicationName = new TextItem("appName");
        applicationName.setHidden(true);
        applicationName.setDefaultValue(appName);
  
        SelectItem tableName = new SelectItem("tableName");  
        tableName.setTitle(Constants.tableNameLabel());  
        tableName.setWidth(150);  
        tableName.setOptionDataSource(tableRestDS);  
        tableName.setValueField("tableName");  
        tableName.setDisplayField("tableName");  
        tableName.setPickListWidth(150);
        tableName.setRequired(true);
        
        TextItem columnName = new TextItem("columnName");  
        columnName.setTitle(Constants.columnNameLabel());  
        columnName.setRequired(true);
        
        TextItem columnDesc = new TextItem("columnDesc");  
        columnDesc.setTitle(Constants.descriptionLabel());  
        columnDesc.setRequired(true);
        
  /*      TextItem descTamil = new TextItem("descTamil");  
        descTamil.setTitle(Constants.descriptonTamilLabel()); 
        descTamil.setRequired(true);
        
        TextItem descTelugu = new TextItem("descTelugu");  
        descTelugu.setTitle(Constants.descriptionTeluguLabel()); 
        descTelugu.setRequired(true);*/
        
        TextItem category = new TextItem("category");  
        category.setTitle(Constants.categoryLabel()); 
        category.setRequired(true);
        SelectItem columnType = new SelectItem("columnType");  
        columnType.setTitle(Constants.columnTypeLabel());  
        columnType.setWidth(150);  
        columnType.setOptionDataSource(columnTypeDS);
        columnType.setValueField("columnType"); 
        columnType.setDisplayField("columnType");  
        columnType.setPickListWidth(150);
        columnType.setRequired(true);
        
        
        SelectItem referenceData = new SelectItem("reference");  
        referenceData.setTitle(Constants.referenceTableEntityLabel());  
        referenceData.setWidth(150);  
        referenceData.setOptionDataSource(referenceDS);  
        referenceData.setValueField("refId");  
        referenceData.setDisplayField("refId");  
        referenceData.setPickListWidth(150);
        
        
        TextItem columnSize = new TextItem("columnSize");  
        columnSize.setTitle(Constants.columnSizeLabel());
        columnSize.setRequired(true);
        
        SelectItem required = new SelectItem("required");  
        required.setTitle(Constants.requiredLabel());  
        required.setWidth(150);  
        required.setOptionDataSource(defaultDataDS);  
        required.setValueField("data");  
        required.setDisplayField("data");  
        required.setPickListWidth(150);
        
        SelectItem hidden = new SelectItem("hidden");  
        hidden.setTitle(Constants.hiddenLabel());  
        hidden.setWidth(150);  
        hidden.setOptionDataSource(defaultDataDS);  
        hidden.setValueField("data");  
        hidden.setDisplayField("data");  
        hidden.setPickListWidth(150);
        
        SelectItem disabled = new SelectItem("disabled");  
        disabled.setTitle(Constants.disabledLabel());  
        disabled.setWidth(150);  
        disabled.setOptionDataSource(defaultDataDS);  
        disabled.setValueField("data");  
        disabled.setDisplayField("data");  
        disabled.setPickListWidth(150); 
        
        SelectItem readOnly = new SelectItem("readOnly");  
        readOnly.setTitle(Constants.readOnlyLabel());  
        readOnly.setWidth(150);  
        readOnly.setOptionDataSource(defaultDataDS);  
        readOnly.setValueField("data");  
        readOnly.setDisplayField("data");  
        readOnly.setPickListWidth(150);
        
  
        orderForm.setFields(columnId, applicationName,tableName, columnName,columnDesc,category,columnType,referenceData,columnSize,required,disabled,hidden,readOnly); 
        
        
        resultsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {  
            public void onSelectionChanged(SelectionEvent event) {  
                Record record = event.getRecord();  
                orderForm.editRecord(record); 
                Criteria criteria = new Criteria();
                criteria.addCriteria("id", record.getAttribute("id"));
                columnScriptForm.fetchData(criteria, new DSCallback() {
					@Override
					public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
						Record[] records = dsResponse.getData();
						if(records != null)
						{
			                ed.setValue(records[0].getAttribute("requiredEvent")!=null?records[0].getAttribute("requiredEvent"):"");
			                ed1.setValue(records[0].getAttribute("hiddenEvent")!=null?records[0].getAttribute("hiddenEvent"):"");
			                ed2.setValue(records[0].getAttribute("disabledEvent")!=null?records[0].getAttribute("disabledEvent"):"");
			                ed3.setValue(records[0].getAttribute("readOnlyEvent")!=null?records[0].getAttribute("readOnlyEvent"):"");
						}
						else
						{
			                ed.setValue("");
			                ed1.setValue("");
			                ed2.setValue("");
			                ed3.setValue("");							
						}
					}
				});

            }  
        });  
        
        columnScriptForm.setDataSource(columnScriptDS);  
  
        TextItem id = new TextItem("id");  
        id.setRequired(true);
        
        TextAreaItem requiredEvent = new TextAreaItem("requiredEvent");  
        requiredEvent.setTitle(Constants.requiredEventLabel());  
        
        TextAreaItem hiddenEvent = new TextAreaItem("hiddenEvent");  
        hiddenEvent.setTitle(Constants.hiddenEventLabel());  
        
        TextAreaItem disabledEvent = new TextAreaItem("disabledEvent");  
        disabledEvent.setTitle(Constants.disabledEventLabel()); 
        
        TextAreaItem readOnlyEvent = new TextAreaItem("readOnlyEvent");  
        readOnlyEvent.setTitle(Constants.readOnlyEventLabel()); 
        
        columnScriptForm.setFields(id,requiredEvent,hiddenEvent,disabledEvent,readOnlyEvent);
        
        leftTabSet.setTabBarPosition(Side.LEFT);  
        leftTabSet.setWidth100();
        leftTabSet.setHeight100();
        leftTabSet.setDefaultTabWidth(550);
        leftTabSet.setOverflow(Overflow.VISIBLE);
   
        
        EditPanelToolStrip toolStrip = new EditPanelToolStrip(orderForm);
        
    	ed.setHeight("100%");
     	ed.setWidth("100%");
     		
     	VLayout layout2 = new VLayout();
     	layout2.addMember(new AceEditorCanvas(ed));
     	
     	ed1.setHeight("100%");
     	ed1.setWidth("100%");
     		
     	VLayout layout3 = new VLayout();
     	layout3.addMember(new AceEditorCanvas(ed1));
     	
     	ed2.setHeight("100%");
     	ed2.setWidth("100%");
     		
     	VLayout layout4 = new VLayout();
     	layout4.addMember(new AceEditorCanvas(ed2));
     	
     	ed3.setHeight("100%");
     	ed3.setWidth("100%");
     		
     	VLayout layout5 = new VLayout();
     	layout5.addMember(new AceEditorCanvas(ed3));
     	
        Tab requiredEventTab = new Tab(Constants.requiredEventLabel()); 
        requiredEventTab.setPane(layout2);  
        Tab hiddenEventTab = new Tab(Constants.hiddenEventLabel()); 
        hiddenEventTab.setPane(layout3);
        Tab disabledEventTab = new Tab(Constants.disabledEventLabel()); 
        disabledEventTab.setPane(layout4);
        Tab readOnlyEventTab = new Tab(Constants.readOnlyEventLabel()); 
        readOnlyEventTab.setPane(layout5);
        
        
        leftTabSet.addTab(requiredEventTab);
        leftTabSet.addTab(hiddenEventTab);
        leftTabSet.addTab(disabledEventTab);
        leftTabSet.addTab(readOnlyEventTab);
        
        ed.startEditor();
		ed.setMode(AceEditorMode.JAVASCRIPT);
		ed.setTheme(AceEditorTheme.TWILIGHT);
		ed.setValue(columnScriptForm.getAttribute("requiredEvent"));
		
		ed1.startEditor();
		ed1.setMode(AceEditorMode.JAVASCRIPT);
		ed1.setTheme(AceEditorTheme.ECLIPSE);
		ed1.setValue(columnScriptForm.getAttribute("hiddenEvent"));
		
		ed2.startEditor();
		ed2.setMode(AceEditorMode.JAVASCRIPT);
		ed2.setTheme(AceEditorTheme.TWILIGHT);
		ed2.setValue(columnScriptForm.getAttribute("disabledEvent"));
		
		ed3.startEditor();
		ed3.setMode(AceEditorMode.JAVASCRIPT);
		ed3.setTheme(AceEditorTheme.ECLIPSE);
		ed3.setValue(columnScriptForm.getAttribute("readOnlyEvent"));

        Tab editTab = new Tab(Constants.editTabLabel());
        editTab.setIcon("demoApp/icon_edit.png");
        
        VLayout layout = new VLayout();
        layout.addMember(toolStrip);
        layout.addMember(orderForm);
        editTab.setPane(layout);
        
      /*  editTab.addTabSelectedHandler(new TabSelectedHandler() {
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				Tab selectedTab = event.getTab(); 
				if (event.getTabNum() == 1)
				{
					ed1.setValue(resultsListGrid.getSelectedRecord().getAttribute(""));
				}
				
			}
		});*/
        
        
        ColumnScriptToolStrip toolStrip1  = new ColumnScriptToolStrip(columnScriptForm, this,resultsListGrid);
        
        Tab scriptTab = new Tab(Constants.scriptTabLabel());
        VLayout layout1 = new VLayout();
        layout1.addMember(toolStrip1);
        layout1.addMember(leftTabSet);
        scriptTab.setPane(layout1);
        
               
        setTabs(editTab,scriptTab);
    
    }
 
}

