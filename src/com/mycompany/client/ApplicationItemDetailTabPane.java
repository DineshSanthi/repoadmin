package com.mycompany.client;

import com.smartgwt.client.data.Criteria;

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

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ChangedEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class ApplicationItemDetailTabPane extends AbstractTabPane {

	private ListGrid editGrid;
	private Label editorLabel;
	private ResultsLayout resultsLayout;
	private static ListGrid relatedPropertyGrid;
	private static ListGrid relatedApplicationUserGrid;
	public static ListGrid getUserGrid() {
		return userGrid;
	}

	public static void setUserGrid(ListGrid userGrid) {
		ApplicationItemDetailTabPane.userGrid = userGrid;
	}

	private static ListGrid userGrid;

	public ListGrid getEditGrid() {
		return editGrid;
	}

	public void setEditGrid(ListGrid editGrid) {
		this.editGrid = editGrid;
	}

	public Label getEditorLabel() {
		return editorLabel;
	}

	public void setEditorLabel(Label editorLabel) {
		this.editorLabel = editorLabel;
	}

	public ResultsLayout getResultsLayout() {
		return resultsLayout;
	}

	public void setResultsLayout(ResultsLayout resultsLayout) {
		this.resultsLayout = resultsLayout;
	}

	public static ListGrid getRelatedApplicationUserGrid() {
		return relatedApplicationUserGrid;
	}

	public static void setRelatedApplicationUserGrid(ListGrid relatedApplicationUserGrid) {
		ApplicationItemDetailTabPane.relatedApplicationUserGrid = relatedApplicationUserGrid;
	}

	public static String getApplicationName() {
		return applicationName;
	}

	public static void setApplicationName(String applicationName) {
		ApplicationItemDetailTabPane.applicationName = applicationName;
	}

	public static ApplicationMessages getConstants() {
		return Constants;
	}

	private static String applicationName;

	public static ListGrid getRelatedPropertyGrid() {
		return relatedPropertyGrid;
	}

	public static void setRelatedPropertyGrid(ListGrid relatedPropertyGrid) {
		ApplicationItemDetailTabPane.relatedPropertyGrid = relatedPropertyGrid;
	}

	private static ListGrid relatedLanguageGrid;

	public static ListGrid getRelatedLanguageGrid() {
		return relatedLanguageGrid;
	}

	public static void setRelatedLanguageGrid(ListGrid relatedLanguageGrid) {
		ApplicationItemDetailTabPane.relatedLanguageGrid = relatedLanguageGrid;
	}

	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

	public ApplicationItemDetailTabPane(final DataSource userDS, final DataSource appRestDS, final DataSource tableDS,
			DataSource defaultDataDS, final DataSource appPropsRestDS, final DataSource supportedLanguageDS,
			final DataSource appUserRestDS,ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
		this.resultsLayout = resultsLayout;

		editorLabel = new Label();
		editorLabel.setWidth100();
		editorLabel.setHeight100();
		editorLabel.setAlign(Alignment.CENTER);
		editorLabel.setContents("Select a record to edit, or a category to insert a new record into");

		final DynamicForm orderForm = new DynamicForm();
		orderForm.setDataSource(appRestDS);
		orderForm.setWidth100();

		TextItem appId = new TextItem("id");
		appId.setTitle("ID");
		appId.setDisabled(true);
		appId.setHidden(true);
		appId.setWidth("*");

		TextItem appName = new TextItem("appName");
		appName.setTitle(Constants.appNameLabel());
		appName.setRequired(true);

		TextItem appDesc = new TextItem("appDescription");
		appDesc.setTitle(Constants.descriptionLabel());
		appDesc.setRequired(true);

		/*
		 * TextItem descTamil = new TextItem("descTamil");
		 * descTamil.setTitle(Constants.descriptonTamilLabel());
		 * descTamil.setRequired(true);
		 * 
		 * TextItem descTelugu = new TextItem("descTelugu");
		 * descTelugu.setTitle(Constants.descriptionTeluguLabel());
		 * descTelugu.setRequired(true);
		 */

		SelectItem messagingFeature = new SelectItem("messagingFeature");
		messagingFeature.setTitle(Constants.messageFeatureLabel());
		messagingFeature.setWidth(240);
		messagingFeature.setOptionDataSource(defaultDataDS);
		messagingFeature.setValueField("data");
		messagingFeature.setDisplayField("data");
		messagingFeature.setPickListWidth(450);
		messagingFeature.setRequired(true);

		SelectItem webApplication = new SelectItem("webApplication");
		webApplication.setTitle(Constants.webApplicationLabel());
		webApplication.setWidth(240);
		webApplication.setOptionDataSource(defaultDataDS);
		webApplication.setValueField("data");
		webApplication.setDisplayField("data");
		webApplication.setPickListWidth(450);
		webApplication.setRequired(true);

		SelectItem mobileApplication = new SelectItem("mobileApplication");
		mobileApplication.setTitle(Constants.mobileApplicationLabel());
		mobileApplication.setWidth(240);
		mobileApplication.setOptionDataSource(defaultDataDS);
		mobileApplication.setValueField("data");
		mobileApplication.setDisplayField("data");
		mobileApplication.setPickListWidth(450);
		mobileApplication.setRequired(true);

		orderForm.setFields(appId, appName, appDesc, messagingFeature, webApplication, mobileApplication);

		resultsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
			public void onSelectionChanged(SelectionEvent event) {
				Record record = event.getRecord();
				orderForm.editRecord(record);
			}
		});

		editGrid = new ListGrid();
		editGrid.setWidth(620);
		editGrid.setHeight(224);
		editGrid.setShowAllRecords(true);
		editGrid.setCellHeight(42);
		editGrid.setWrapCells(true);
		editGrid.setDataSource(appRestDS);

		editGrid.setAutoFetchData(true);
		editGrid.setCanEdit(true);
		editGrid.setEditEvent(ListGridEditEvent.CLICK);

		EditPanelToolStrip toolStrip = new EditPanelToolStrip(orderForm);

		relatedPropertyGrid = new ListGrid();
		relatedPropertyGrid.setWidth100();
		relatedPropertyGrid.setHeight100();
		relatedPropertyGrid.setShowAllRecords(true);
		relatedPropertyGrid.setCellHeight(42);
		relatedPropertyGrid.setWrapCells(true);
		relatedPropertyGrid.setDataSource(appPropsRestDS);

		ListGridField groupField = new ListGridField("appName", Constants.appNameLabel());
		groupField.setCanEdit(false);
		groupField.setHidden(true);

		ListGridField propertyName = new ListGridField("propertyName", Constants.propertyNameLabel());
		propertyName.setCanEdit(false);

		ListGridField propertyDesc = new ListGridField("propertyDesc", Constants.propertyDescLabel());
		propertyDesc.setCanEdit(false);

		ListGridField propertyValue = new ListGridField("propertyValue", Constants.propertyValueLabel());
		propertyValue.setCanEdit(false);

		relatedPropertyGrid.setFields(groupField, propertyName, propertyDesc, propertyValue);

		relatedPropertyGrid.setAutoFetchData(false);

		relatedLanguageGrid = new ListGrid();
		relatedLanguageGrid.setWidth100();
		relatedLanguageGrid.setHeight100();
		relatedLanguageGrid.setShowAllRecords(true);
		relatedLanguageGrid.setCellHeight(42);
		relatedLanguageGrid.setWrapCells(true);
		relatedLanguageGrid.setDataSource(supportedLanguageDS);

		ListGridField appNameField = new ListGridField("appName", Constants.appNameLabel());
		appNameField.setCanEdit(false);
		appNameField.setHidden(true);

		ListGridField languageName = new ListGridField("languageName", Constants.languageNameLabel());
		languageName.setCanEdit(false);

		ListGridField languageCode = new ListGridField("languageCode", Constants.languageCodeLabel());
		languageCode.setCanEdit(false);

		ListGridField description = new ListGridField("description", Constants.descriptionLabel());
		description.setCanEdit(false);

		relatedLanguageGrid.setFields(appNameField, languageName, languageCode, description);

		relatedLanguageGrid.setAutoFetchData(false);

		final DynamicForm propertyForm = new DynamicForm();

		resultsListGrid.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				applicationName = resultsListGrid.getSelectedRecord().getAttribute("appName");
				propertyForm.setHeight100();
				propertyForm.setWidth100();
				propertyForm.setPadding(5);
				propertyForm.setDataSource(appPropsRestDS);
				propertyForm.setLayoutAlign(VerticalAlignment.BOTTOM);

				TextItem propertyFormField = new TextItem("appName");
				propertyFormField.setTitle("Application Name");
				propertyFormField.setDefaultValue(applicationName);
				;
				propertyFormField.setRequired(true);
				propertyFormField.setHidden(true);

				TextItem propertyFormField1 = new TextItem("propertyName");
				propertyFormField1.setTitle("Property Name");

				TextItem propertyFormField2 = new TextItem("propertyDesc");
				propertyFormField2.setTitle("Property Description");

				TextItem propertyFormField3 = new TextItem("propertyValue");
				propertyFormField3.setTitle("Property Value");

				propertyForm.setFields(propertyFormField, propertyFormField1, propertyFormField2, propertyFormField3);
				
			}
		});

		RelatedTabToolStrip toolStrip1 = new RelatedTabToolStrip(resultsListGrid,relatedPropertyGrid, propertyForm);

		final DynamicForm languageForm = new DynamicForm();
		resultsListGrid.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				applicationName = resultsListGrid.getSelectedRecord().getAttribute("appName");
				languageForm.setHeight100();
				languageForm.setWidth100();
				languageForm.setPadding(5);
				languageForm.setDataSource(supportedLanguageDS);
				languageForm.setLayoutAlign(VerticalAlignment.BOTTOM);

				TextItem languageFormField = new TextItem("appName");
				languageFormField.setTitle("Application Name");
				languageFormField.setDefaultValue(applicationName);
				languageFormField.setRequired(true);
				languageFormField.setHidden(true);

				TextItem languageFormField1 = new TextItem("languageName");
				languageFormField1.setTitle("Language Name");

				TextItem languageFormField2 = new TextItem("languageCode");
				languageFormField2.setTitle("Language Code");

				TextItem languageFormField3 = new TextItem("description");
				languageFormField3.setTitle("Description");

				languageForm.setFields(languageFormField, languageFormField1, languageFormField2, languageFormField3);

			}
		});

		RelatedTabToolStrip toolStrip2 = new RelatedTabToolStrip(resultsListGrid,relatedLanguageGrid, languageForm);
		Tab editTab = new Tab(Constants.editTabLabel());
		editTab.setIcon("demoApp/icon_edit.png");
		editTab.setWidth(70);
		
		
		relatedApplicationUserGrid = new ListGrid();
		relatedApplicationUserGrid.setWidth100();
		relatedApplicationUserGrid.setHeight100();
		relatedApplicationUserGrid.setShowAllRecords(true);
		relatedApplicationUserGrid.setCellHeight(42);
		relatedApplicationUserGrid.setWrapCells(true);
		relatedApplicationUserGrid.setDataSource(appUserRestDS);
		

		ListGridField userName = new ListGridField("userName", Constants.userNameLabel());
		userName.setCanEdit(false);

		ListGridField email = new ListGridField("email", Constants.emailLabel());
		email.setCanEdit(false);

		ListGridField firstName = new ListGridField("firstName", Constants.firstNameLabel());
		firstName.setCanEdit(false);

		ListGridField lastName = new ListGridField("lastName", Constants.lastNameLabel());
		lastName.setCanEdit(false);

		relatedApplicationUserGrid.setFields(userName, email, firstName, lastName);
		relatedApplicationUserGrid.setAutoFetchData(false);
		

		final DynamicForm userForm = new DynamicForm();
		resultsListGrid.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				applicationName = resultsListGrid.getSelectedRecord().getAttribute("appName");
				userForm.setHeight100();
				userForm.setWidth100();
				userForm.setPadding(5);
				userForm.setDataSource(appUserRestDS);
				userForm.setLayoutAlign(VerticalAlignment.BOTTOM);

				TextItem appName = new TextItem("appName");
				appName.setTitle("Application Name");
				appName.setDefaultValue(applicationName);
				appName.setRequired(true);
				appName.setHidden(true);

				SelectItem userFormField = new SelectItem("userName");
				userFormField.setTitle("User Name");
				userFormField.setWidth(240);
				userFormField.setOptionDataSource(userDS);
				userFormField.setValueField("userName");
				userFormField.setDisplayField("userName");
				userFormField.setPickListWidth(450);
				userFormField.setRequired(true);
				
				/*userFormField.addChangedHandler(new com.smartgwt.client.widgets.form.fields.events.ChangedHandler() {
					
					@Override
					public void onChanged(com.smartgwt.client.widgets.form.fields.events.ChangedEvent event) {
						// TODO Auto-generated method stub
						String appUserName = userFormField.getAttribute("userName");
						userGrid = new UserListGrid(userDS);
						String userName = userGrid.getAttribute("userName");
						if(appUserName.equalsIgnoreCase(userName))
						{
							Criteria criteria = new Criteria();
							criteria.addCriteria("userName",appUserName);
							userGrid.fetchData(criteria);
							ListGridRecord[] grid = userGrid.getRecords();
							relatedApplicationUserGrid.setData(grid);
						}
					}
				});*/
					
				
				userForm.setFields(appName,userFormField);

			}
		});
		RelatedTabToolStrip toolStrip3 = new RelatedTabToolStrip(resultsListGrid,relatedApplicationUserGrid, userForm);

		VLayout layout = new VLayout();
		layout.addMember(toolStrip);
		layout.addMember(orderForm);
		editTab.setPane(layout);

		VLayout propertyLayout = new VLayout();
		propertyLayout.addMember(toolStrip1);
		propertyLayout.addMember(relatedPropertyGrid);

		VLayout languageLayout = new VLayout();
		languageLayout.addMember(toolStrip2);
		languageLayout.addMember(relatedLanguageGrid);

		VLayout userLayout = new VLayout();
		userLayout.addMember(toolStrip3);
		userLayout.addMember(relatedApplicationUserGrid);

		Tab propertyTab = new Tab("Application Properties");
		propertyTab.setWidth(70);
		propertyTab.setPane(propertyLayout);

		Tab languageTab = new Tab("Supported Languages");
		languageTab.setWidth(70);
		languageTab.setPane(languageLayout);

		Tab appUserTab = new Tab("Application User");
		appUserTab.setWidth(70);
		appUserTab.setPane(userLayout);

		setTabs(editTab, propertyTab, languageTab, appUserTab);
	}

}
