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
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class ReferenceItemDetailTabPane extends AbstractTabPane {

    private ListGrid editGrid;
    private Label editorLabel;
    private ResultsLayout resultsLayout;
    private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
    public static ListGrid getRelatedDataGrid() {
		return relatedDataGrid;
	}

	public static void setRelatedDataGrid(ListGrid relatedDataGrid) {
		ReferenceItemDetailTabPane.relatedDataGrid = relatedDataGrid;
	}

	private static ListGrid relatedDataGrid;

    public ReferenceItemDetailTabPane(final DataSource objectDS,final DataSource referenceDataRestDS, ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
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
        appId.setHidden(true);
  
        TextItem refId = new TextItem("refId");  
        refId.setTitle("Reference ID");
        refId.setRequired(true);
        
        TextItem description = new TextItem("description");  
        description.setTitle("Description"); 
        description.setRequired(true);
      
        orderForm.setFields(appId, refId, description); 
        
        
        resultsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {  
            public void onSelectionChanged(SelectionEvent event) {  
                Record record = event.getRecord();  
                orderForm.editRecord(record);  
            }  
        });  
        
        
       
        
      /*  IButton button1 = new IButton("Delete");  
        button1.setTop(250);
        button1.setIconOrientation("right"); 
        button1.addClickHandler(new ClickHandler() {  
        	public void onClick(ClickEvent event) {  
                ListGridRecord record = new ListGridRecord();  
                record.setAttribute("id", editGrid.getSelectedRecord().getAttribute("id"));  
                editGrid.removeData(record);  
                editGrid.fetchData();
        		//editGrid.removeSelectedData();
        		resultsListGrid.removeSelectedData();
            }   
        }); 
        
        IButton button = new IButton( "Save Item");
        button.setTop(250);
        button.setIconOrientation("left"); 
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//editGrid.saveAllEdits();
            	orderForm.saveData();
            }
        });
        
        IButton button3 = new IButton("Insert");  
        button3.setTop(250);
        button3.setShowDown(true);
       // button1.setIconOrientation("right");
        button3.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//resultsListGrid.startEditingNew();
            	final Window winModal = new Window();  
                winModal.setAutoSize(true);  
                winModal.setTitle("New Record");  
                winModal.setShowMinimizeButton(false);  
                winModal.setIsModal(true);  
                winModal.setShowModalMask(true);  
                winModal.centerInPage();  
                winModal.addCloseClickHandler(new CloseClickHandler() {  
                    public void onCloseClick(CloseClickEvent event) {  
                        winModal.destroy();  
                    }
                  });
               final DynamicForm form = new DynamicForm();  
                form.setHeight100();  
                form.setWidth100();  
                form.setPadding(5); 
                form.setDataSource(objectDS);
                form.setLayoutAlign(VerticalAlignment.BOTTOM);  
          
                TextItem refId = new TextItem("refId");  
                refId.setTitle("Reference ID");
                
                TextItem description = new TextItem("description");  
                description.setTitle("Description"); 
               
                IButton button = new IButton( "Save Item");
                button.setTop(250);
                button.setIconOrientation("left"); 
                button.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                    	//editGrid.saveAllEdits();
                    	form.saveData();
                    }
                });
                
                form.setFields(refId, description);  
                winModal.addItem(form);
                winModal.addItem(button);
                winModal.show();  
            }  
        });      
         */
        relatedDataGrid = new ListGrid();
        relatedDataGrid.setWidth100();  
        relatedDataGrid.setHeight100();  
        relatedDataGrid.setShowAllRecords(true);  
        relatedDataGrid.setCellHeight(42);  
        relatedDataGrid.setWrapCells(true);  
        relatedDataGrid.setDataSource(referenceDataRestDS); 
        
        ListGridField labelField = new ListGridField("refId","Reference ID");
        labelField.setCanEdit(false);

        ListGridField labelField1 = new ListGridField("code","Code");
        labelField1.setCanEdit(true);
        
        ListGridField labelField2 = new ListGridField("displayValue","Display Value");
        labelField2.setCanEdit(true);
        
        ListGridField displayValueTamil = new ListGridField("displayValueTamil","Display Value(Tamil)");
        displayValueTamil.setShowHover(true);
        
        ListGridField displayValueTelugu = new ListGridField("displayValueTelugu","Display Value(Telugu)");
        displayValueTelugu.setShowHover(true);
        
        relatedDataGrid.setFields(labelField,labelField1,labelField2,displayValueTamil,displayValueTelugu);  
        
        relatedDataGrid.setAutoFetchData(true); 
        relatedDataGrid.saveAllEdits();
        
        IButton button4 = new IButton(Constants.deleteButtonLabel());  
        button4.setTop(250);
        button4.setIconOrientation("right"); 
        button4.addClickHandler(new ClickHandler() {  
        	public void onClick(ClickEvent event) {  
               /* ListGridRecord record = new ListGridRecord();  
                record.setAttribute("id", editGrid.getSelectedRecord().getAttribute("id"));  
                editGrid.removeData(record);  
                editGrid.fetchData();*/
        		//editGrid.removeSelectedData();
        		relatedDataGrid.removeSelectedData();
            }   
        }); 
        
        IButton button5 = new IButton(Constants.insertButtonLabel());  
        button5.setTop(250);
        button5.setShowDown(true);
       // button1.setIconOrientation("right");
        button5.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//resultsListGrid.startEditingNew();
            	final Window winModal = new Window();  
                winModal.setAutoSize(true);  
                winModal.setTitle("New Record");  
                winModal.setShowMinimizeButton(false);  
                winModal.setIsModal(true);  
                winModal.setShowModalMask(true);  
                winModal.centerInPage();  
                winModal.addCloseClickHandler(new CloseClickHandler() {  
                    public void onCloseClick(CloseClickEvent event) {  
                        winModal.destroy();  
                    }
                  });
             final   DynamicForm form1 = new DynamicForm();  
                form1.setHeight100();  
                form1.setWidth100();  
                form1.setPadding(5); 
                form1.setDataSource(referenceDataRestDS);
                form1.setLayoutAlign(VerticalAlignment.BOTTOM);  
                
                SelectItem refId = new SelectItem("refId");
                refId.setTitle("Reference ID");  
                refId.setWidth(240);  
                refId.setOptionDataSource(objectDS);  
                refId.setValueField("id");  
                refId.setDisplayField("refId");  
                refId.setPickListWidth(450); 
                refId.setRequired(true);
          
                TextItem code = new TextItem("code");  
                code.setTitle("Code");
                
                TextItem displayValue = new TextItem("displayValue");  
                displayValue.setTitle("Display Value"); 
                
                TextItem dataValueTamil = new TextItem("dataValueTamil");  
                dataValueTamil.setTitle("Display Value(Tamil)");
                
                TextItem dataValueTelugu = new TextItem("dataValueTelugu");  
                dataValueTelugu.setTitle("Display Value(Telugu)");
               
                IButton button6 = new IButton(Constants.saveButtonLabel());
                button6.setTop(250);
                button6.setIconOrientation("left"); 
                button6.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                    	//editGrid.saveAllEdits();
                    	form1.saveData();
                    	winModal.close();
                    }
                });
                
                form1.setFields(refId,code, displayValue,dataValueTamil,dataValueTelugu);  
                winModal.addItem(form1);
                winModal.addItem(button6);
                winModal.show();  
            }  
        });      
         
        
        
        EditPanelToolStrip toolStrip = new EditPanelToolStrip(orderForm);


        Tab editTab = new Tab(Constants.editTabLabel());
        editTab.setIcon("demoApp/icon_edit.png");
        editTab.setWidth(70);
      //  editTab.setPane(editGrid);
        
        VLayout layout = new VLayout();
        //layout.addMember(editGrid);
        layout.addMember(toolStrip);
        layout.addMember(orderForm);
      /*  layout.addMember(button);
        layout.addMember(button1);
        layout.addMember(button3);*/
        editTab.setPane(layout);
        
        Tab dataTab = new Tab(Constants.relatedDataTabLabel());
        dataTab.setWidth(70);
        VLayout layout1 = new VLayout();
        layout1.addMember(relatedDataGrid);
        HLayout layout2 = new HLayout();
        layout2.addMember(button4);
        layout2.addMember(button5);
        layout1.addMember(layout2);
        dataTab.setPane(layout1);
        
        
               
        setTabs(editTab,dataTab);

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

