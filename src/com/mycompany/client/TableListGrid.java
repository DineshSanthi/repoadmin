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
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.grid.*;

public class TableListGrid extends ListGrid {

	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
    public TableListGrid(DataSource restDS) {

    /*	ListGridField tableThumbnail = new ListGridField("id","Image");
    	tableThumbnail.setType(ListGridFieldType.IMAGE);
    	tableThumbnail.setImageURLPrefix(RepoConfiguration.getServiceUrl() +"tablethumbnail/" );
    	
    	ListGridField appName = new ListGridField("appName", Constants.appNameLabel());
        appName.setShowHover(true);
        appName.setHidden(true);
        ListGridField tableName = new ListGridField("tableName",Constants.tableNameLabel());
        tableName.setShowHover(true);
        ListGridField tableDescription = new ListGridField("tableDescription",Constants.descriptionLabel());
        tableDescription.setShowHover(true);
        
        
        ListGridField descTamil = new ListGridField("descTamil",Constants.descriptonTamilLabel());
        descTamil.setShowHover(true);
        ListGridField descTelugu = new ListGridField("descTelugu",Constants.descriptionTeluguLabel());
        descTelugu.setShowHover(true);
        ListGridField category = new ListGridField("category",Constants.categoryLabel());
        category.setShowHover(true);
        ListGridField ttl = new ListGridField("ttl",Constants.ttlLabel());
        ttl.setShowHover(true);*/
        
        setUseAllDataSourceFields(true);
        setAutoFetchData(true);  
        setCanEdit(false);
        setCanDragRecordsOut(true);
        setHoverWidth(200);
        setHoverHeight(20);
        setSelectionType(SelectionStyle.SINGLE);
        setShowAllRecords(true);
        setDataSource(restDS);
    }
}
