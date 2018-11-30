package com.mycompany.client;
  
import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;  
  
public class GridEditor implements EntryPoint {  
  
    public void onModuleLoad() {  
  
        final ListGrid editGrid = new ListGrid();  
        editGrid.setWidth(620);  
        editGrid.setHeight(224);  
        editGrid.setShowAllRecords(true);  
        editGrid.setCellHeight(42);  
        editGrid.setWrapCells(true);  
        editGrid.setDataSource(ApplicationDS.getInstance());  
  
        ListGridField nameField = new ListGridField("appName", "Name", 100);  
  
  
        ListGridField descriptionField = new ListGridField("appDescription", "Description", 175);  
  
        TextAreaItem textAreaItem = new TextAreaItem();  
        textAreaItem.setHeight(70);  
        descriptionField.setEditorProperties(textAreaItem);  
  
  
        editGrid.setFields(nameField, descriptionField);  
  
        editGrid.setAutoFetchData(true);  
        editGrid.setCanEdit(true);  
        editGrid.setEditEvent(ListGridEditEvent.CLICK);  
  
        editGrid.draw();  
    }  
  
}  
