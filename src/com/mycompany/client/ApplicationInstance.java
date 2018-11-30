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

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

public class ApplicationInstance extends HLayout {
	private SearchForm searchForm;
	private NavigatorTreeGrid navigatorTree;
	private ListGrid resultsListGrid;

	public ListGrid getResultsListGrid() {
		return resultsListGrid;
	}

	public void setResultsListGrid(ListGrid resultsListGrid) {
		this.resultsListGrid = resultsListGrid;
	}
	private SupportedLanguageListGrid languageListGrid;
	private TableRestDS tableRestDS;
	private TableScriptRestDS tableScriptDS;
	private ResultsLayout resultsLayout;
	private EditLayout editLayout;
	private ResultsPanelToolStrip resultsPanelToolStrip;
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

	public ApplicationInstance(final String appName) {

		this.setWidth100();
		this.setHeight100();
		this.setLayoutMargin(10);

		DataSource appRestDS = new ApplicationRestDS();
		DataSource repoRestDS = RepositoryRestDS.getInstance(appName);

		navigatorTree = new NavigatorTreeGrid(repoRestDS);
		navigatorTree.setAutoFetchData(true);

		navigatorTree.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				String objectType = event.getRecord().getAttribute("objectType");
				findObjects(objectType, appName);
			}
		});

		RPCManager.setAllowCrossDomainCalls(true);

		resultsLayout = new ResultsLayout();
		editLayout = new EditLayout();
		resultsListGrid = new ApplicationListGrid(appRestDS);
		resultsListGrid.setWidth100();
		resultsListGrid.setHeight100();
		resultsListGrid.setCanEdit(true);
/*
		tableListGrid = new TableListGrid(tableDS);
		tableListGrid.setWidth100();
		tableListGrid.setHeight100();
		tableListGrid.setCanEdit(true);

		resultsListGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				Criteria criteria = new Criteria();
				criteria.addCriteria("appName", resultsListGrid.getSelectedRecord().getAttribute("appName"));
				ApplicationItemDetailTabPane.getRelatedTableGrid().filterData(criteria);
			}
		});*/

		SectionStack leftSideLayout = new SectionStack();
		leftSideLayout.setWidth(280);
		leftSideLayout.setShowResizeBar(true);
		leftSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
		leftSideLayout.setAnimateSections(true);

		SectionStackSection suppliesCategorySection = new SectionStackSection(Constants.categorySectionTitle());
		suppliesCategorySection.setExpanded(true);
		suppliesCategorySection.setItems(navigatorTree);

		leftSideLayout.setSections(suppliesCategorySection);

		SectionStack rightSideLayout = new SectionStack();
		rightSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
		rightSideLayout.setAnimateSections(true);

		/*resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);

		resultsLayout.addMember(resultsPanelToolStrip);
		resultsLayout.addMember(resultsListGrid);
*/
		SectionStackSection supplyItemsSection = new SectionStackSection(Constants.resultSectionTitle());
		supplyItemsSection.setItems(resultsLayout);
		supplyItemsSection.setExpanded(true);

		SectionStackSection itemDetailsSection = new SectionStackSection(Constants.detailSectionTitle());

		/*editLayout.addMember(
				new ApplicationItemDetailTabPane(appRestDS, tableDS, defaultDataDS, resultsLayout, resultsListGrid));
*/
		itemDetailsSection.setItems(editLayout);
		itemDetailsSection.setExpanded(true);

		rightSideLayout.setSections(supplyItemsSection, itemDetailsSection);

		this.addMember(leftSideLayout);
		this.addMember(rightSideLayout);

	}

	public void findObjects(String objectType, String appName) {

		resultsLayout.removeMember(resultsPanelToolStrip);
		resultsLayout.removeMember(resultsListGrid);

		if (objectType.equals("application")) {
			DataSource appDS = new ApplicationRestDS();
			DataSource languageDS = new SupportedLanguagesRestDS(appName);
			DataSource tableDS = new TableRestDS(appName);
			DataSource defaultDataDS = DefaultDataDS.getInstance();
			DataSource appPropsDS = new ApplicationPropertiesRestDS(appName);
			DataSource appUserRestDS = new ApplicationUserRestDS(appName);
			DataSource userDS = new UserRestDS();
			resultsListGrid = new ApplicationListGrid(appDS);
			resultsListGrid.addRecordClickHandler(new RecordClickHandler() {
				public void onRecordClick(RecordClickEvent event) {
					Criteria criteria = new Criteria();
					criteria.addCriteria("appName", resultsListGrid.getSelectedRecord().getAttribute("appName"));
					ApplicationItemDetailTabPane.getRelatedPropertyGrid().fetchData(criteria);
					ApplicationItemDetailTabPane.getRelatedLanguageGrid().fetchData(criteria);
					ApplicationItemDetailTabPane.getRelatedApplicationUserGrid().fetchData(criteria);
				}
			});
			resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
			editLayout.removeMember(editLayout.getMember(0));
			editLayout.addMember(
					new ApplicationItemDetailTabPane(userDS,appDS, tableDS, defaultDataDS,appPropsDS,languageDS,appUserRestDS,resultsLayout, resultsListGrid));
		} 
		else if (objectType.equals("globalproperties")) {
			DataSource globalPropsDS = new GlobalPropertiesRestDS();
			resultsListGrid = new GlobalPropertiesListGrid(globalPropsDS);
			resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
			editLayout.removeMember(editLayout.getMember(0));
			editLayout.addMember(
					new GlobalPropertiesItemDetailTabPane(globalPropsDS, resultsLayout, resultsListGrid));
		} 
		else if (objectType.equals("user")) {
			DataSource userRestDS = new UserRestDS();
			resultsListGrid = new UserListGrid(userRestDS);
			resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
			editLayout.removeMember(editLayout.getMember(0));
			editLayout.addMember(
					new UserItemDetailTabPane(userRestDS, resultsLayout, resultsListGrid));
		} 
		else if (objectType.equals("role")) {
			DataSource roleRestDS = new RolesRestDS();
			resultsListGrid = new RolesListGrid(roleRestDS);
			resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
			editLayout.removeMember(editLayout.getMember(0));
			editLayout.addMember(
					new RolesItemDetailTabPane(roleRestDS, resultsLayout, resultsListGrid));
		} 
		else if (objectType.equals("profile")) {
			DataSource profileRestDS = new ProfileRestDS(appName);
			DataSource permissionRestDS = new PermissionRestDS(appName);
			resultsListGrid = new RolesListGrid(profileRestDS);
			resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
			editLayout.removeMember(editLayout.getMember(0));
			editLayout.addMember(
					new ProfileItemDetailTabPane(appName,profileRestDS,permissionRestDS, resultsLayout, resultsListGrid));
		} 
		else if (objectType.equals("table")) {
			DataSource appRestDS = new ApplicationRestDS();
			DataSource columnDS = new ColumnRestDS(appName);
//			DataSource languageDS = new SupportedLanguagesRestDS(appName);
			DataSource tableDS = new TableRestDS(appName);
			TableScriptRestDS tableScriptDS1 = new TableScriptRestDS();
			resultsListGrid = new TableListGrid(tableDS);
			resultsListGrid.addRecordClickHandler(new RecordClickHandler() {
				public void onRecordClick(RecordClickEvent event) {
					Criteria criteria = new Criteria();
					criteria.addCriteria("tableName", resultsListGrid.getSelectedRecord().getAttribute("id"));
					TableItemDetailTabPane.getRelatedColumnGrid().filterData(criteria);
				}
			});
			resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
			editLayout.removeMember(editLayout.getMember(0));
			editLayout.addMember(new TableItemDetailTabPane(appName,appRestDS, tableDS, tableScriptDS1, columnDS, resultsLayout,
					resultsListGrid));
		} else if (objectType.equals("column")) {
			DataSource columnDS = new ColumnRestDS(appName);
			DataSource languageDS = new SupportedLanguagesRestDS(appName);
			languageListGrid = new SupportedLanguageListGrid(languageDS);
			DataSource tableDS = new TableRestDS(appName);
			DataSource referenceDS = new ReferenceRestDS();
			DataSource columnTypeDS = ColumnTypeDS.getInstance();
			ColumnScriptRestDS columnScriptDS = new ColumnScriptRestDS();
			DataSource defaultDataDS = DefaultDataDS.getInstance();
			resultsListGrid = new ColumnListGrid(columnDS);
			resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
			editLayout.removeMember(editLayout.getMember(0));
			editLayout.addMember(new ColumnItemDetailTabPane(appName,columnDS, tableDS, referenceDS, columnTypeDS,
					defaultDataDS, columnScriptDS, resultsLayout, resultsListGrid));
		} else if (objectType.equals("relation")) {
			DataSource relationDS = new RelationshipRestDS(appName);
			DataSource tableDS = new TableRestDS(appName);
			resultsListGrid = new RelationshipListGrid(relationDS);
			resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
			editLayout.removeMember(editLayout.getMember(0));
			editLayout.addMember(new RelationItemDetailTabPane(appName,relationDS, tableDS, resultsLayout, resultsListGrid));
		} else if (objectType.equals("reference")) {
			DataSource referenceDS = new ReferenceRestDS();
			DataSource referenceDataDS = new ReferenceDataRestDS();
			resultsListGrid = new ReferenceListGrid(referenceDS);
			resultsListGrid.addRecordClickHandler(new RecordClickHandler() {
				public void onRecordClick(RecordClickEvent event) {
					Criteria criteria = new Criteria();
					criteria.addCriteria("refId", resultsListGrid.getSelectedRecord().getAttribute("id"));
					ReferenceItemDetailTabPane.getRelatedDataGrid().filterData(criteria);
				}
			});
			resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
			editLayout.removeMember(editLayout.getMember(0));
			editLayout.addMember(
					new ReferenceItemDetailTabPane(referenceDS, referenceDataDS, resultsLayout, resultsListGrid));
		} else if (objectType.equals("tableScript")) {
			DataSource tableScriptDS = new TableScriptRestDS();
			DataSource languageDS = new SupportedLanguagesRestDS(appName);
			languageListGrid = new SupportedLanguageListGrid(languageDS);
			DataSource tableDS = new TableRestDS(appName);
			resultsListGrid = new TableScriptListGrid(tableScriptDS);
			resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
			editLayout.removeMember(editLayout.getMember(0));
			editLayout.addMember(
					new TableScriptItemDetailTabPane(tableScriptDS, tableDS, resultsLayout, resultsListGrid));
		} else if (objectType.equals("referencedata")) {
			DataSource referenceDataDS = new ReferenceDataRestDS();
			ReferenceDataListGrid listGrid = new ReferenceDataListGrid(referenceDataDS);
			listGrid.setWidth100();
			listGrid.setHeight100();
			resultsLayout.removeMember(listGrid);

		}

		resultsLayout.addMember(resultsPanelToolStrip);
		resultsLayout.addMember(resultsListGrid);

	}

}
