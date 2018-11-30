package com.mycompany.client;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;

public class TableScriptToolStrip extends ToolStrip {

	private AceEditor ed = new AceEditor();
	public TableScriptToolStrip(final DynamicForm form, final TableItemDetailTabPane pane, final ListGrid resultsListGrid) {
		
	
	this.setWidth100();  
	  
    //push all buttons to the right  
    this.addFill();  

  

    ToolStripButton saveButton = new ToolStripButton();  
    saveButton.setIcon("icons/16/save_as.png"); 
    saveButton.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
	        form.setValue("id", resultsListGrid.getSelectedRecord().getAttribute("id"));
	        String beforeSave 	=	pane.getEd().getValue();
	        String afterSave 	=	pane.getEd1().getValue();
	        String beforeFetch 	=	pane.getEd2().getValue();
	        String afterFetch 	=	pane.getEd3().getValue();
	        String beforeDelete =	pane.getEd4().getValue();
	        String afterDelete 	=	pane.getEd5().getValue();
	        String beforeInsert =	pane.getEd6().getValue();
	        String afterInsert 	=	pane.getEd7().getValue();
	        form.setValue("beforeSave", beforeSave);
	        form.setValue("afterSave", afterSave);
	        form.setValue("beforeFetch", beforeFetch);
	        form.setValue("afterFetch", afterFetch);
	        form.setValue("beforeDelete", beforeDelete);
	        form.setValue("afterDelete", afterDelete);
	        form.setValue("beforeInsert", beforeInsert);
	        form.setValue("afterInsert", afterInsert);
	        form.saveData();
	        form.reset();
        }
    });
    
    
    this.addButton(saveButton);
      
    this.draw();  
}  
}


  

