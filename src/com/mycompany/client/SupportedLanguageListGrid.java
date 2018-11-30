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

import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.grid.*;

public class SupportedLanguageListGrid extends ListGrid {
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

    public SupportedLanguageListGrid(DataSource restDS) {


        ListGridField appName = new ListGridField("appName", Constants.appNameLabel());
        appName.setShowHover(true);
        ListGridField languageCode = new ListGridField("languageCode",Constants.languageCodeLabel());
        languageCode.setShowHover(true);
        ListGridField languageName = new ListGridField("languageName",Constants.languageNameLabel());
        languageName.setShowHover(true);
        ListGridField description = new ListGridField("description",Constants.descriptionLabel());
        description.setShowHover(true);
        setUseAllDataSourceFields(true);
        setAutoFetchData(false);
     
        setFields(appName,languageCode,languageName, description);
        setCanEdit(true);
        setCanDragRecordsOut(true);
        setHoverWidth(200);
        setHoverHeight(20);
        setSelectionType(SelectionStyle.SINGLE);
        setDataSource(restDS);
    	
    
    }
}
