package com.mycompany.client;

import java.util.List;

public class RepoConfiguration {

	protected static native List<String> getFieldList(String objectDefintionId) /*-{
		try {
			var list = @java.util.ArrayList::new()();
			var fields = eval("$wnd.repo.objectDefinition['" + objectDefintionId + "']");
			if (fields)
			{
				var keys = Object.keys(fields);
				for (var i=0; i<keys.length; i++)
				{
					list.@java.util.ArrayList::add(Ljava/lang/Object;)(keys[i]);
				}
			}
			return list;
		} catch (error) {
			var errorText = "getFieldList failed to read value";
		}
		 return @java.util.ArrayList::new()();	
	}-*/;	

	protected static native String getServiceUrl() /*-{
		if (!$wnd.repo) return "http://127.0.0.1:8080/";
		return $wnd.repo.serviceUrl;
	}-*/;

	protected static native String getAccessToken() /*-{
	if (!$wnd.keycloak.token) return "";
	return $wnd.keycloak.token;
	}-*/;

	
}
