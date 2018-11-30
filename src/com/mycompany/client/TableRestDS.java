package com.mycompany.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.JSON;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class TableRestDS extends AbstractRestDS{
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	
	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	private String applicationName = null;
	
	public ListGrid getLanguageListGrid() {
		return languageListGrid;
	}

	public void ListGrid(ListGrid languageListGrid) {
		this.languageListGrid = languageListGrid;
	}

	private ListGrid languageListGrid;

	private DSRequest fetchProps;

	public DSRequest getFetchProps() {
		return fetchProps;
	}

	public void setFetchProps(DSRequest fetchProps) {
		this.fetchProps = fetchProps;
	}

/*
	public TableRestDS(String applicationName,ListGrid listGrid) {
		this.applicationName = applicationName;
		this.languageListGrid = listGrid;
		setID(SC.generateID("TableRestDS"));
		setDataFormat(DSDataFormat.JSON);
		setDataProtocol(DSProtocol.POSTMESSAGE);
		setJsonPrefix(null);
		setJsonSuffix(null);
		OperationBinding fetch = new OperationBinding();
		fetch.setOperationType(DSOperationType.FETCH);
		fetch.setDataProtocol(DSProtocol.GETPARAMS);
		fetch.setDataProtocol(DSProtocol.POSTMESSAGE);
		fetchProps = new DSRequest();
		fetchProps.setHttpMethod("PUT");
		fetchProps.setContentType("application/json");

		fetch.setRequestProperties(fetchProps);

		OperationBinding add = new OperationBinding();
		add.setOperationType(DSOperationType.ADD);
		add.setDataProtocol(DSProtocol.POSTMESSAGE);
		DSRequest addProps = new DSRequest();
		addProps.setHttpMethod("POST");
		addProps.setContentType("application/json");
		add.setRequestProperties(addProps);

		OperationBinding update = new OperationBinding();
		update.setOperationType(DSOperationType.UPDATE);
		update.setDataProtocol(DSProtocol.POSTMESSAGE);

		DSRequest updateProps = new DSRequest();
		updateProps.setHttpMethod("PUT");
		updateProps.setContentType("application/json");
		update.setRequestProperties(updateProps);

		OperationBinding remove = new OperationBinding();
		remove.setOperationType(DSOperationType.REMOVE);
		DSRequest removeProps = new DSRequest();
		removeProps.setHttpMethod("DELETE");
		removeProps.setContentType("application/json");
		remove.setRequestProperties(removeProps);
		setOperationBindings(fetch, add, update, remove);
		
		setDataSourceFields();

		setFetchDataURL(getCustomFetchDataURL());
		setAddDataURL(getCustomAddDataURL());
		setUpdateDataURL(getCustomUpdateDataURL());
		setRemoveDataURL(getRemoveDataURL());

	}*/
	
	public TableRestDS(String applicationName) {
		this.applicationName = applicationName;

		setID(SC.generateID("TableRestDS"));
		setDataFormat(DSDataFormat.JSON);
		setDataProtocol(DSProtocol.POSTMESSAGE);
		setJsonPrefix(null);
		setJsonSuffix(null);
		OperationBinding fetch = new OperationBinding();
		fetch.setOperationType(DSOperationType.FETCH);
		fetch.setDataProtocol(DSProtocol.GETPARAMS);
		fetch.setDataProtocol(DSProtocol.POSTMESSAGE);
		fetchProps = new DSRequest();
		fetchProps.setHttpMethod("PUT");
		fetchProps.setContentType("application/json");

		fetch.setRequestProperties(fetchProps);

		OperationBinding add = new OperationBinding();
		add.setOperationType(DSOperationType.ADD);
		add.setDataProtocol(DSProtocol.POSTMESSAGE);
		DSRequest addProps = new DSRequest();
		addProps.setHttpMethod("POST");
		addProps.setContentType("application/json");
		add.setRequestProperties(addProps);

		OperationBinding update = new OperationBinding();
		update.setOperationType(DSOperationType.UPDATE);
		update.setDataProtocol(DSProtocol.POSTMESSAGE);

		DSRequest updateProps = new DSRequest();
		updateProps.setHttpMethod("PUT");
		updateProps.setContentType("application/json");
		update.setRequestProperties(updateProps);

		OperationBinding remove = new OperationBinding();
		remove.setOperationType(DSOperationType.REMOVE);
		DSRequest removeProps = new DSRequest();
		removeProps.setHttpMethod("DELETE");
		removeProps.setContentType("application/json");
		remove.setRequestProperties(removeProps);
		setOperationBindings(fetch, add, update, remove);
		
		setDataSourceFields(applicationName);

		setFetchDataURL(getCustomFetchDataURL());
		setAddDataURL(getCustomAddDataURL());
		setUpdateDataURL(getCustomUpdateDataURL());
		setRemoveDataURL(getRemoveDataURL());

	}
	
	public String getCustomFetchDataURL() {
		return getServiceRoot() + "/" + applicationName;
	}

	public String getCustomAddDataURL() {
		return getServiceRoot() + "/insert";
	}

	public String getCustomUpdateDataURL() {
		return getServiceRoot() + "/update";
	}

	public String getRemoveDataURL() {
		return getServiceRoot() + "/delete";
	}

	public String getServiceRoot() {
		return RepoConfiguration.getServiceUrl() + getServiceName();
	}
	
/*	protected ListGridRecord[] CreateLanguageDS(){
		String name =applicationName;
		DataSource languageDS = new SupportedLanguagesRestDS(name);
		languageListGrid = new SupportedLanguageListGrid(languageDS);
		Criteria criteria = new Criteria();
		criteria.addCriteria("appName",name);
		languageListGrid.fetchData(criteria);
		ListGridRecord[] records = languageListGrid.getRecords();
		return records;
	}*/

	public void setDataSourceFields(String name) {
		
		DataSource languageDS = new SupportedLanguagesRestDS(name);
		languageListGrid = new SupportedLanguageListGrid(languageDS);
		Criteria criteria = new Criteria();
		criteria.addCriteria("appName",name);
		languageListGrid.fetchData(criteria);
		ListGridRecord[] records = languageListGrid.getRecords();
	    
		DataSourceField[] dataSourceFields = new DataSourceField[5 + records.length];
        DataSourceTextField appName = new DataSourceTextField("appName",Constants.appNameLabel() );  
        dataSourceFields[0] = appName;
        DataSourceTextField tableName = new DataSourceTextField("tableName", Constants.tableNameLabel()); 
        dataSourceFields[1] = tableName;
        DataSourceTextField tableDesc = new DataSourceTextField("tableDescription", Constants.descriptionLabel()); 
        dataSourceFields[2] = tableDesc;
        DataSourceTextField category = new DataSourceTextField("category", Constants.categoryLabel());
        dataSourceFields[3] = category;
        DataSourceTextField ttl = new DataSourceTextField("ttl", Constants.ttlLabel());
        dataSourceFields[4] = ttl;
        
       
        
        for (int i = 0; i < records.length; i++) {
    	   
        	DataSourceTextField dataSourceField = new DataSourceTextField(records[i].getAttribute("languageCode"),"Description_" + records[i].getAttribute("languageCode"));
        	dataSourceField.setRequired(true);
        	dataSourceFields[5 + i] = dataSourceField;
        }
        
     //   DataSourceTextField descTamil = new DataSourceTextField("descTamil",Constants.descriptonTamilLabel());
     //   DataSourceTextField descTelugu = new DataSourceTextField("descTelugu", Constants.descriptionTeluguLabel());
        
        setFields(dataSourceFields);
        
       
    /*    addField(appName);
        addField(tableName);
        addField(tableDesc);
     //   addField(descTamil);
    //    addField(descTelugu);
        addField(category);
        addField(ttl);*/
        
        
        
	}
	@Override
	protected void setEntityFields(){
		
	/*	DataSourceField[] dataSourceFields = new DataSourceField[5];
        DataSourceTextField appName = new DataSourceTextField("appName",Constants.appNameLabel() );  
        dataSourceFields[0] = appName;
        DataSourceTextField tableName = new DataSourceTextField("tableName", Constants.tableNameLabel()); 
        dataSourceFields[1] = tableName;
        DataSourceTextField tableDesc = new DataSourceTextField("tableDescription", Constants.descriptionLabel()); 
        dataSourceFields[2] = tableDesc;
        DataSourceTextField category = new DataSourceTextField("category", Constants.categoryLabel());
        dataSourceFields[3] = category;
        DataSourceTextField ttl = new DataSourceTextField("ttl", Constants.ttlLabel());
        dataSourceFields[4] = ttl;
        
        setFields(dataSourceFields);
		*/
/*		
		ListGridRecord[] records = CreateLanguageDS();
		DataSourceField[] dataSourceFields = new DataSourceField[5 + records.length];
        DataSourceTextField appName = new DataSourceTextField("appName",Constants.appNameLabel() );  
        dataSourceFields[0] = appName;
        DataSourceTextField tableName = new DataSourceTextField("tableName", Constants.tableNameLabel()); 
        dataSourceFields[1] = tableName;
        DataSourceTextField tableDesc = new DataSourceTextField("tableDescription", Constants.descriptionLabel()); 
        dataSourceFields[2] = tableDesc;
        DataSourceTextField category = new DataSourceTextField("category", Constants.categoryLabel());
        dataSourceFields[3] = category;
        DataSourceTextField ttl = new DataSourceTextField("ttl", Constants.ttlLabel());
        dataSourceFields[4] = ttl;
        
       
        
        for (int i = 0; i < records.length; i++) {
    	   
        	DataSourceTextField dataSourceField = new DataSourceTextField(records[i].getAttribute("languageCode"),"Description_" + records[i].getAttribute("languageCode"));
        	dataSourceField.setRequired(true);
        	dataSourceFields[5 + i] = dataSourceField;
        }*/
	}
	@Override
	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase();
	}
	
	protected String getPrimaryKeyFieldId() {
		return "id";
	}

	@Override
	protected Object transformRequest(DSRequest dsRequest) {


		if (dsRequest.getOperationType() == DSOperationType.FETCH) {
			
			dsRequest.setActionURL(getCustomFetchDataURL());
			
		} else if (dsRequest.getOperationType() == DSOperationType.UPDATE) {
			final JavaScriptObject basicJSObject = dsRequest.getOldValues().getJsObj();
			final JavaScriptObject latestChanges = dsRequest.getData();
			JSOHelper.addProperties(basicJSObject, latestChanges);
			final String resultString = JSON.encode(basicJSObject);
			return resultString;
		} else if (dsRequest.getOperationType() == DSOperationType.ADD) {
			final JavaScriptObject latestChanges = dsRequest.getData();
			final String resultString = JSON.encode(latestChanges);
			return resultString;
		} else if (dsRequest.getOperationType() == DSOperationType.REMOVE) {
			String primaryKey = dsRequest.getOldValues().getAttribute(getPrimaryKeyFieldId());
			dsRequest.setActionURL(getRemoveDataURL() + "/" + primaryKey);
		}
		return super.transformRequest(dsRequest);
	}

	@Override
	protected void transformResponse(DSResponse response, DSRequest request, Object data) {
		super.transformResponse(response, request, data);
	}

	
}