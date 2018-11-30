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

public class ColumnScriptToolStrip extends ToolStrip {

	private AceEditor ed = new AceEditor();
	public ColumnScriptToolStrip(final DynamicForm form, final ColumnItemDetailTabPane pane, final ListGrid resultsListGrid) {
		
	
	this.setWidth100();  
	  
    //push all buttons to the right  
    this.addFill();  

  

    ToolStripButton saveButton = new ToolStripButton();  
    saveButton.setIcon("icons/16/save_as.png"); 
    saveButton.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
	        form.setValue("id", resultsListGrid.getSelectedRecord().getAttribute("id"));
	        String requiredEvent =	pane.getEd().getValue();
	        String hiddenEvent =	pane.getEd1().getValue();
	        String disabledEvent =	pane.getEd2().getValue();
	        String readOnlyEvent =	pane.getEd3().getValue();
	        form.setValue("requiredEvent", requiredEvent);
	        form.setValue("hiddenEvent", hiddenEvent);
	        form.setValue("disabledEvent", disabledEvent);
	        form.setValue("readOnlyEvent", readOnlyEvent);
	        form.saveData();
	        form.reset();
        }
    });
    
    
    this.addButton(saveButton);
      
    this.draw();  
}  
}


  

