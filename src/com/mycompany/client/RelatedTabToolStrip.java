package com.mycompany.client;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;

public class RelatedTabToolStrip extends ToolStrip {

	RelatedTabToolStrip(final ListGrid resultGrid,final ListGrid listGrid,final DynamicForm form)
	{
		this.setWidth100();  
		  
	    //push all buttons to the right  
	    this.addFill();  

	  
	    ToolStripButton insertButton = new ToolStripButton();  
	    insertButton.setIcon("icons/16/save_as.png"); 
	    insertButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
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
          
                IButton button = new IButton( "Save Item");
                button.setTop(250);
                button.setIconOrientation("left"); 
                button.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                    	form.saveData();
                    	String applicationName = resultGrid.getSelectedRecord().getAttribute("appName");
                    	Criteria criteria = new Criteria();
    					criteria.addCriteria("appName",applicationName);
                    	listGrid.fetchData(criteria);
                    	listGrid.refreshData();
                    	winModal.close();
                    }
                });
                
                winModal.addItem(form);
                winModal.addItem(button);
                winModal.show();  
	        }
	    });
	    
	    ToolStripButton deleteButton = new ToolStripButton();  
	    deleteButton.setIcon("icons/16/close.png"); 
	    deleteButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	listGrid.removeSelectedData();
	        	listGrid.refreshData();
	        }
	    });
	    
	    
	    ToolStripButton updateButton = new ToolStripButton();  
	    updateButton.setIcon("icons/16/find.png"); 
	    updateButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	    	Record record = listGrid.getSelectedRecord();
	    	final Window winModal = new Window();  
            winModal.setAutoSize(true);  
            winModal.setTitle("Update Record");  
            winModal.setShowMinimizeButton(false);  
            winModal.setIsModal(true);  
            winModal.setShowModalMask(true);  
            winModal.centerInPage();  
            winModal.addCloseClickHandler(new CloseClickHandler() {  
                public void onCloseClick(CloseClickEvent event) {  
                    winModal.destroy();  
                }
              });
      
            IButton button = new IButton( "Save Item");
            button.setTop(250);
            button.setIconOrientation("left"); 
            button.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                	form.saveData();
                	winModal.close();
                }
            });
            form.editRecord(record);
            winModal.addItem(form);
            winModal.addItem(button);
            winModal.show();  
        }	
	    });
	    
	    
	    this.addButton(insertButton);
	    this.addButton(updateButton);
	    this.addButton(deleteButton);
	    
	      
	    this.draw();  

	}
	
}


  

