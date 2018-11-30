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
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

public class TableItemDetailTabPane extends AbstractTabPane {

	private ListGrid editGrid;
	private Label editorLabel;
	private ResultsLayout resultsLayout;
	private static ListGrid relatedColumnGrid;
	private static ListGrid relatedTableScriptGrid;
	private TabSet topTabSet = new TabSet();  
	private Tab scriptTab;
	private DynamicForm tableScriptForm = new DynamicForm(); 
	private AceEditor ed = new AceEditor();
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

	public AceEditor getEd4() {
		return ed4;
	}

	public void setEd4(AceEditor ed4) {
		this.ed4 = ed4;
	}

	public AceEditor getEd5() {
		return ed5;
	}

	public void setEd5(AceEditor ed5) {
		this.ed5 = ed5;
	}

	public AceEditor getEd6() {
		return ed6;
	}

	public void setEd6(AceEditor ed6) {
		this.ed6 = ed6;
	}

	public AceEditor getEd7() {
		return ed7;
	}

	public void setEd7(AceEditor ed7) {
		this.ed7 = ed7;
	}

	private AceEditor ed1 = new AceEditor();
    private AceEditor ed2 = new AceEditor();
    private AceEditor ed3 = new AceEditor();
	private AceEditor ed4 = new AceEditor();
    private AceEditor ed5 = new AceEditor();
    private AceEditor ed6 = new AceEditor();
    private AceEditor ed7 = new AceEditor();
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

	public TableItemDetailTabPane(final String applicationName,final DataSource appRestDS, final DataSource tableDS, final DataSource tableScriptDS,
			DataSource columnDS, ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
		this.resultsLayout = resultsLayout;

		editorLabel = new Label();
		editorLabel.setWidth100();
		editorLabel.setHeight100();
		editorLabel.setAlign(Alignment.CENTER);
		editorLabel.setContents("Select a record to edit, or a category to insert a new record into");

		final DynamicForm orderForm = new DynamicForm();
		orderForm.setDataSource(tableDS);

		TextItem appId = new TextItem("id");
		appId.setTitle("ID");
		appId.setDisabled(true);
		appId.setHidden(true);
		
		TextItem appName = new TextItem("appName");
		appName.setTitle(Constants.appNameLabel());
		appName.setDefaultValue(applicationName);
		appName.setHidden(true);


		TextItem tableName = new TextItem("tableName");
		tableName.setTitle(Constants.tableNameLabel());
		tableName.setRequired(true);

		TextItem tableDesc = new TextItem("tableDescription");
		tableDesc.setTitle(Constants.descriptionLabel());
		tableDesc.setRequired(true);

		/*TextItem descTamil = new TextItem("descTamil");
		descTamil.setTitle(Constants.descriptonTamilLabel());
		descTamil.setRequired(true);

		TextItem descTelugu = new TextItem("descTelugu");
		descTelugu.setTitle(Constants.descriptionTeluguLabel());
		descTelugu.setRequired(true);*/
		

		TextItem category = new TextItem("category");
		category.setTitle(Constants.categoryLabel());
		category.setRequired(true);

		IntegerItem ttl = new IntegerItem("ttl");
		ttl.setTitle(Constants.ttlLabel());
		ttl.setRequired(true);


		orderForm.setFields(appId, appName, tableName, tableDesc, category, ttl);
		
		 resultsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {  
	            public void onSelectionChanged(SelectionEvent event) {  
	            	scriptTab.setDisabled(false); 
	            	topTabSet.redraw();
	                Record record = event.getRecord();  
	                orderForm.editRecord(record); 
	                Criteria criteria = new Criteria();
	                criteria.addCriteria("id", record.getAttribute("id"));
	                tableScriptForm.fetchData(criteria, new DSCallback() {
						@Override
						public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
							Record[] records = dsResponse.getData();
							if(records != null)
							{
				                ed.setValue(records[0].getAttribute("beforeSave")!=null?records[0].getAttribute("beforeSave"):"");
				                ed1.setValue(records[0].getAttribute("afterSave")!=null?records[0].getAttribute("afterSave"):"");
				                ed2.setValue(records[0].getAttribute("beforeFetch")!=null?records[0].getAttribute("beforeFetch"):"");
				                ed3.setValue(records[0].getAttribute("afterfetch")!=null?records[0].getAttribute("afterfetch"):"");
				                ed4.setValue(records[0].getAttribute("beforeDelete")!=null?records[0].getAttribute("beforeDelete"):"");
				                ed5.setValue(records[0].getAttribute("afterDelete")!=null?records[0].getAttribute("afterDelete"):"");
				                ed6.setValue(records[0].getAttribute("beforeInsert")!=null?records[0].getAttribute("beforeInsert"):"");
				                ed7.setValue(records[0].getAttribute("afterInsert")!=null?records[0].getAttribute("afterInsert"):"");
						
							}
							else
							{
				                ed.setValue("");
				                ed1.setValue("");
				                ed2.setValue("");
				                ed3.setValue("");	
				                ed4.setValue("");
				                ed5.setValue("");
				                ed6.setValue("");
				                ed7.setValue("");
							}
						}
					});

	            }  
	        });  
	        

		resultsListGrid.saveAllEdits();


		relatedColumnGrid = new ListGrid();
		relatedColumnGrid.setWidth100();
		relatedColumnGrid.setHeight100();
		relatedColumnGrid.setShowAllRecords(true);
		relatedColumnGrid.setCellHeight(42);
		relatedColumnGrid.setWrapCells(true);
		relatedColumnGrid.setDataSource(columnDS);

		ListGridField labelField = new ListGridField("tableName", Constants.tableNameLabel());
		labelField.setCanEdit(false);
		labelField.setHidden(true);

		ListGridField labelField1 = new ListGridField("columnName", Constants.columnNameLabel());
		labelField1.setCanEdit(false);

		ListGridField labelField2 = new ListGridField("columnType", Constants.columnTypeLabel());
		labelField2.setCanEdit(false);

		ListGridField labelField3 = new ListGridField("columnSize", Constants.columnSizeLabel());
		labelField3.setCanEdit(false);

		relatedColumnGrid.setFields(labelField, labelField1, labelField2, labelField3);

		relatedColumnGrid.setAutoFetchData(true);

		EditPanelToolStrip toolStrip = new EditPanelToolStrip(orderForm);
		
		tableScriptForm.setDataSource(tableScriptDS);  
		  
	    TextItem id = new TextItem("id");
	    id.setRequired(true);
	        
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
	        
	  
	    tableScriptForm.setFields(id, beforeSave, afterSave,beforeFetch,afterFetch,beforeDelete,afterDelete,beforeInsert,afterInsert); 
	    
	    topTabSet.setTabBarPosition(Side.TOP);  
	    topTabSet.setWidth100();
	    topTabSet.setHeight100();
	    topTabSet.setDefaultTabWidth(150);
	    topTabSet.setOverflow(Overflow.VISIBLE);
	    
	    ed.setHeight("100%");
     	ed.setWidth("100%");
     	
     	ed1.setHeight("100%");
     	ed1.setWidth("100%");
     	
     	ed2.setHeight("100%");
     	ed2.setWidth("100%");
     	
     	ed3.setHeight("100%");
     	ed3.setWidth("100%");
     	
     	ed4.setHeight("100%");
     	ed4.setWidth("100%");
     	
     	ed5.setHeight("100%");
     	ed5.setWidth("100%");
     	
     	ed6.setHeight("100%");
     	ed6.setWidth("100%");
     	
     	ed7.setHeight("100%");
     	ed7.setWidth("100%");
     	 
     	Tab beforeSaveTab = new Tab(Constants.beforeSaveLabel()); 
     	beforeSaveTab.setPane(new AceEditorCanvas(ed));  
        Tab afterSaveTab = new Tab(Constants.afterSaveLabel()); 
        afterSaveTab.setPane(new AceEditorCanvas(ed1));
        Tab beforeFetchTab = new Tab(Constants.beforeFetchLabel()); 
        beforeFetchTab.setPane(new AceEditorCanvas(ed2));
        Tab afterFetchTab = new Tab(Constants.afterFetchLabel()); 
        afterFetchTab.setPane(new AceEditorCanvas(ed3));
        Tab beforeDeleteTab = new Tab(Constants.beforeDeleteLabel()); 
        beforeDeleteTab.setPane(new AceEditorCanvas(ed4));  
        Tab afterDeleteTab = new Tab(Constants.afterDeleteLabel()); 
        afterDeleteTab.setPane(new AceEditorCanvas(ed5));
        Tab beforeInsertTab = new Tab(Constants.beforeInsertLabel()); 
        beforeInsertTab.setPane(new AceEditorCanvas(ed6));
        Tab afterInsertTab = new Tab(Constants.afterInsertLabel()); 
        afterInsertTab.setPane(new AceEditorCanvas(ed7));
        
        topTabSet.addTab(beforeSaveTab);
        topTabSet.addTab(afterSaveTab);
        topTabSet.addTab(beforeFetchTab);
        topTabSet.addTab(afterFetchTab);
        topTabSet.addTab(beforeDeleteTab);
        topTabSet.addTab(afterDeleteTab);
        topTabSet.addTab(beforeInsertTab);
        topTabSet.addTab(afterInsertTab);
        
        ed.startEditor();
		ed.setMode(AceEditorMode.JAVASCRIPT);
		ed.setTheme(AceEditorTheme.TWILIGHT);
		
		ed1.startEditor();
		ed1.setMode(AceEditorMode.JAVASCRIPT);
		ed1.setTheme(AceEditorTheme.ECLIPSE);
		
		ed2.startEditor();
		ed2.setMode(AceEditorMode.JAVASCRIPT);
		ed2.setTheme(AceEditorTheme.TWILIGHT);
		
		ed3.startEditor();
		ed3.setMode(AceEditorMode.JAVASCRIPT);
		ed3.setTheme(AceEditorTheme.ECLIPSE);
		
		ed4.startEditor();
		ed4.setMode(AceEditorMode.JAVASCRIPT);
		ed4.setTheme(AceEditorTheme.TWILIGHT);
		
		ed5.startEditor();
		ed5.setMode(AceEditorMode.JAVASCRIPT);
		ed5.setTheme(AceEditorTheme.ECLIPSE);
		
		ed6.startEditor();
		ed6.setMode(AceEditorMode.JAVASCRIPT);
		ed6.setTheme(AceEditorTheme.TWILIGHT);
		
		ed7.startEditor();
		ed7.setMode(AceEditorMode.JAVASCRIPT);
		ed7.setTheme(AceEditorTheme.ECLIPSE);

		TableScriptToolStrip toolStrip1  = new TableScriptToolStrip(tableScriptForm,this,resultsListGrid);
		
		scriptTab = new Tab(Constants.scriptTabLabel());
	    VLayout layout1 = new VLayout();
	    layout1.addMember(toolStrip1);
	    layout1.addMember(topTabSet);
	    scriptTab.setPane(layout1);
		//scriptTab.setDisabled(true);
		
		Tab editTab = new Tab(Constants.editTabLabel());
		editTab.setIcon("demoApp/icon_edit.png");
		editTab.setWidth(70);

		VLayout layout = new VLayout();
		layout.addMember(toolStrip);
		layout.addMember(orderForm);
		editTab.setPane(layout);


		Tab relatedColumnTab = new Tab(Constants.relatedColumnTabLabel());
		relatedColumnTab.setWidth(70);
		relatedColumnTab.setPane(relatedColumnGrid);
		
		 setTabs(editTab,relatedColumnTab,scriptTab);



		
	}

	public static ListGrid getRelatedColumnGrid() {
		return relatedColumnGrid;
	}

	public void setRelatedColumnGrid(ListGrid relatedColumnGrid) {
		this.relatedColumnGrid = relatedColumnGrid;
	}

}
