package com.mycompany.client;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.WidgetCanvas;

public class AceEditorCanvas extends WidgetCanvas {

	
	    public AceEditorCanvas(Widget widget)
	    {
	        super(widget);

	        setOverflow(Overflow.HIDDEN);
	    }

	
}
