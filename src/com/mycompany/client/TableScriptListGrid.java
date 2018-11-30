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
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.grid.*;

public class TableScriptListGrid extends ListGrid {

	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
    public TableScriptListGrid(DataSource restDS) {


    	ListGridField id = new ListGridField("id", Constants.appNameLabel());
        id.setHidden(true);
        ListGridField beforeSave = new ListGridField("beforeSave",Constants.beforeSaveLabel());
        beforeSave.setShowHover(true);
        ListGridField afterSave = new ListGridField("afterSave",Constants.afterSaveLabel());
        afterSave.setShowHover(true);
        ListGridField beforeFetch = new ListGridField("beforeFetch",Constants.beforeFetchLabel());
        beforeFetch.setShowHover(true);
        ListGridField afterFetch = new ListGridField("afterFetch",Constants.afterFetchLabel());
        afterFetch.setShowHover(true);
        ListGridField berforeDelete = new ListGridField("berforeDelete",Constants.beforeDeleteLabel());
        berforeDelete.setShowHover(true);
        ListGridField afterDelete = new ListGridField("afterDelete",Constants.afterDeleteLabel());
        afterDelete.setShowHover(true);
        ListGridField beforeInsert = new ListGridField("beforeInsert",Constants.beforeInsertLabel());
        beforeInsert.setShowHover(true);
        ListGridField afterInsert = new ListGridField("afterInsert",Constants.afterInsertLabel());
        afterInsert.setShowHover(true);
        
        setUseAllDataSourceFields(true);
        setAutoFetchData(true);  
       
        setFields(id,beforeSave,afterSave,beforeFetch,afterFetch,berforeDelete,afterDelete,beforeInsert,afterInsert);
        setCanEdit(false);
        setCanDragRecordsOut(true);
        setHoverWidth(200);
        setHoverHeight(20);
        setSelectionType(SelectionStyle.SINGLE);
        setDataSource(restDS);
    }
}
