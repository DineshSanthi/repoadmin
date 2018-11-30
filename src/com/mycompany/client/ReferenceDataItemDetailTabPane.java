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
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class ReferenceDataItemDetailTabPane extends TabSet {

    private ListGrid editGrid;
    private Label editorLabel;
    private ResultsLayout resultsLayout;
    private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

    public ReferenceDataItemDetailTabPane(DataSource objectDS, ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
        this.resultsLayout = resultsLayout;

        editorLabel = new Label();
        editorLabel.setWidth100();
        editorLabel.setHeight100();
        editorLabel.setAlign(Alignment.CENTER);
        editorLabel.setContents("Select a record to edit, or a category to insert a new record into");

        final DynamicForm orderForm = new DynamicForm();  
        orderForm.setDataSource(objectDS);  
  
        TextItem appId = new TextItem("id");  
        appId.setTitle("ID");  
        appId.setDisabled(true);  
  
        TextItem refId = new TextItem("refId");  
        refId.setTitle("Reference ID");
        
        TextItem data = new TextItem("data");  
        data.setTitle("Data"); 
        
        TextItem display = new TextItem("display");  
        display.setTitle("Display");  
      
        orderForm.setFields(appId, refId, data,display); 
        
        
        resultsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {  
            public void onSelectionChanged(SelectionEvent event) {  
                Record record = event.getRecord();  
                orderForm.editRecord(record);  
            }  
        });  
        
        
       
        
        IButton button1 = new IButton(Constants.deleteButtonLabel());  
        button1.setTop(250);
        button1.setIconOrientation("right"); 
        button1.addClickHandler(new ClickHandler() {  
        	public void onClick(ClickEvent event) {  
               /* ListGridRecord record = new ListGridRecord();  
                record.setAttribute("id", editGrid.getSelectedRecord().getAttribute("id"));  
                editGrid.removeData(record);  
                editGrid.fetchData();*/
        		//editGrid.removeSelectedData();
        		resultsListGrid.removeSelectedData();
            }   
        }); 
        
        IButton button = new IButton(Constants.saveButtonLabel());
        button.setTop(250);
        button.setIconOrientation("left"); 
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//editGrid.saveAllEdits();
            	orderForm.saveData();
            }
        });
        
        IButton button3 = new IButton(Constants.insertButtonLabel());  
        button3.setTop(250);
       // button1.setIconOrientation("right");
        button3.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//editGrid.startEditingNew();
            	//editGrid.fetchData();
            	resultsListGrid.startEditingNew();
            }
        });
        


        Tab editTab = new Tab(Constants.editTabLabel());
        editTab.setIcon("demoApp/icon_edit.png");
        editTab.setWidth(70);
      //  editTab.setPane(editGrid);
        
        HLayout layout = new HLayout();
        //layout.addMember(editGrid);
        layout.addMember(orderForm);
        layout.addMember(button);
        layout.addMember(button1);
        layout.addMember(button3);
        editTab.setPane(layout);
        
               
        setTabs(editTab);

       /* addTabSelectedHandler(new TabSelectedHandler() {
            public void onTabSelected(TabSelectedEvent event) {
                updateDetails();
            }
        });*/
    }

	

/*
    public void clearDetails(Record selectedCategoryRecord) {
        int selectedTab = getSelectedTabNumber();
        if (selectedTab == 0) {
            //view tab : show empty message
            itemViewer.setData((Record[]) null);
        } else {
            // edit tab : show new record editor, or empty message
            if (selectedCategoryRecord != null) {
                updateTab(1, editorForm);
                Map initialValues = new HashMap();
                initialValues.put("category", selectedCategoryRecord.getAttribute("categoryName"));
                editorForm.editNewRecord(initialValues);
            } else {
                updateTab(1, editorLabel);
            }
        }
    }*/
    

   /* public void updateDetails() {
        Record selectedRecord  = vLayout.getResultGrid().getSelectedRecord();
        int selectedTab = getSelectedTabNumber();
        if (selectedTab == 0) {
            //view tab : show empty message
            itemViewer.setData(new Record[]{selectedRecord});
        } else {
            // edit tab : show record editor
            editorForm.editRecord(selectedRecord);
        }
    }*/
}

