package com.mycompany.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.JSON;
import com.smartgwt.client.util.SC;

public class ColumnRestDS extends AbstractRestDS{
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	
private String appName = null;
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	private DSRequest fetchProps;

	public DSRequest getFetchProps() {
		return fetchProps;
	}

	public void setFetchProps(DSRequest fetchProps) {
		this.fetchProps = fetchProps;
	}


	public ColumnRestDS(String appName) {
		this.appName = appName;

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

		setEntityFields();

		/*
		 * DataSourceTextField appId = new
		 * DataSourceTextField(getPrimaryKeyFieldId(), "ID");
		 * appId.setPrimaryKey(true); appId.setHidden(true);
		 * appId.setCanEdit(false); addField(appId);
		 */

		setFetchDataURL(getCustomFetchDataURL());
		setAddDataURL(getCustomAddDataURL());
		setUpdateDataURL(getCustomUpdateDataURL());
		setRemoveDataURL(getRemoveDataURL());

	}

	public String getCustomFetchDataURL() {
		return getServiceRoot() + "/application/" + appName;
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
	
	@Override
	protected void setEntityFields() {
		DataSourceTextField appName = new DataSourceTextField("appName", Constants.appNameLabel());
        DataSourceTextField tableName = new DataSourceTextField("tableName", Constants.tableNameLabel());  
        DataSourceTextField columnName = new DataSourceTextField("columnName", Constants.columnNameLabel());  
        DataSourceTextField columnDesc = new DataSourceTextField("columnDesc", Constants.descriptionLabel()); 
        DataSourceTextField columnType = new DataSourceTextField("columnType", Constants.columnTypeLabel()); 
        DataSourceTextField reference = new DataSourceTextField("reference", "Reference"); 
        DataSourceTextField columnSize = new DataSourceTextField("columnSize", Constants.columnSizeLabel()); 
     //   DataSourceTextField descTamil = new DataSourceTextField("descTamil", Constants.descriptonTamilLabel());
    //    DataSourceTextField descTelugu = new DataSourceTextField("descTelugu",Constants.descriptionTeluguLabel());
        DataSourceTextField category = new DataSourceTextField("category", Constants.categoryLabel());
        
        addField(appName);
        addField(tableName);
        addField(columnName);
        addField(columnDesc);
        addField(category);
        addField(columnType);
        addField(reference);
        addField(columnSize);
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
			String primaryKey = dsRequest.getOldValues().getAttribute(getPrimaryKeyFieldId());
			dsRequest.setActionURL(getCustomUpdateDataURL() + "/" + primaryKey);
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