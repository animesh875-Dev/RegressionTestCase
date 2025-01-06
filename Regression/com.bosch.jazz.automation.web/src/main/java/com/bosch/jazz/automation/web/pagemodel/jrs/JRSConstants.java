/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs;


/**
 * /** Constants for model class JRSAllReportPage,JRSBuildNewReportPage.
 */
public final class JRSConstants {

  private JRSConstants() {

  }

  /**
   * 
   */
  // All Reports
  

  public static final String JRSALLREPORTPAGE_REPORTNAME_CHECKBOX_XPATH =
      "//tr[.//a[normalize-space(text())='DYNAMIC_VAlUE']]//input[@type='checkbox']";
  

  /**
   * Select the folder checkbox in JRS all report page group by folders.
   */
  public static final String JRSALLREPORTPAGE_SELECTFOLDERCHECKBOX_XPATH ="//tr[@data-name='DYNAMIC_VAlUE']//input[@type='checkbox']";
  
  /**
   * Folder radiobutton while moving a report XPATH
   */
  public static final String JRSALLREPORTPAGE_MOVEFOLDERCHECKBOX_XPATH ="//div[@class='folder-item']//a[text()='DYNAMIC_VAlUE']/preceding-sibling::input[@type='radio']";
  
  
  /**
   * Select All check box in JRS All Reports and My Reports page
   */
  public static final String JRSALLREPORTPAGE_SELECTALLCHECKBOXES_CHECKBOXES_XPATH = "//input[@id='select-all-box']";

  
  
  /**
   * Delete Folder on JRS All report page
   */
  public static final String JRSALLREPORTPAGE_DELETEFOLDER_XPATH ="//button[@id='all-reports-delete-button']";
  
  
  /**
   * Tag Name in JRS All Reports and My Reports page
   */
  public static final String JRSALLREPORTPAGE_REPORTTAGNAME_REPORTTAG_XPATH = "//a[@class='title-text folder']";

  /**
   * Move report Button XPATH
   */
  public static final String JRSALLREPORTPAGE_MOVEBUTTON_XPATH = "//button[@id='all-reports-folder-cut-button']";
  
  /**
   * Copy report Button XPATH
   */
  public static final String JRSALLREPORTPAGE_COPYBUTTON_XPATH = "//button[@id='all-reports-folder-copy-button']";
  
  /**
   * Expand icon next to Tag Name in JRS All Reports and My Reports page
   */
  public static final String JRSALLREPORTPAGE_EXPANDICON_EXPAND_XPATH = "//span[@class='icon-folder icon-group-open']";

  /**
   * Filter toggle in the Filter frame of JRS Report
   */
  public static final String JRSALLREPORTPAGE_FILTERFRAME_FILTERFRAME_XPATH = "//span[@id='filters-toggle-twistie']";

  /**
   * Filter text value in the Filter frame of JRS Report
   */
  public static final String JRSALLREPORTPAGE_FILTERVALUE_FILTER_XPATH = "//td[@class='jrs-param-title']";

  /**
   * Filter selection text value in the Filter frame of JRS Report
   */
  public static final String JRSALLREPORTPAGE_PROJECTFILTER_ALLPROJECT_XPATH = "//a[@class='jrs-param-selection-link']";

  /**
   * Frame of 'RUN REPORT' tab
   */
  public static final String JRSALLREPORTPAGE_REPORTIFRAME_IFRAME_XPATH =
      "//iframe[@id='view-frame' or @id='run-report-frame'] | //iframe[@title='Run']";
  /**
   * Frame of 'VIEW REPORT' tab
   */
  public static final String JRSALLREPORTPAGE_VIEWREPORTIFRAME_IFRAME_XPATH = "//iframe[@id='view-frame']";

  /**
   * '+' (add new tab) in the Dashboard page
   */
  public static final String JRSALLREPORTPAGE_ADDNEWTABIMAGE_IMAGE_XPATH = "//a[@class='add-new-tab']";

  /**
   * Catalog dropdown in Add widgets frame
   */
  public static final String JRSALLREPORTPAGE_CATLOG_CATALOGDROPDOWN_XPATH =
      "//tr[@aria-label='Applications ']/parent::tbody";

  /**
   * Search input text in the Add widget frame
   */
  public static final String JRSALLREPORTPAGE_WIDGETSEARCH_FILTEROPEN_XPATH =
      "//input[@class='filterText helpMessage']";

  /**
   * 'Save' button in the dashboard page
   */
  public static final String JRSALLREPORTPAGE_SAVEBUTTON_INPUTBUTTON_XPATH = "//input[@value='Save']";

  /**
   * Report Name that do not have tags in JRS All Reports and My Reports page
   */
  public static final String JRSALLREPORTPAGE_REPORTNAME_REPORTNAME_XPATH =
      "//a[contains(@class,'title-text reportdefinition-link')]";


  // JRScreatePage

  /**
   * Team area filters in the Filter frame of JRS Report
   */
  public static final String JRSCREATEREPORTPAGE_ADDCONDITION_SELECTALLVISIBLEITEM_XPATH =
      "//label[text()='Select all visible items']/parent::div/input[@type='checkbox']";

  /**
   * Label text for all check box in JRS
   */
  public static final String JRSCREATEREPORTPAGE_CHECKBOX_LABELTEXT_XPATH = "//span[@class='label-text']";

  /**
   * Value checkboxes of conditions in 'ADD CONDITIONS' dialog in JRS
   */
  public static final String JRSCREATEREPORTPAGE_CONDITION_VALUE_CHECKBOX__XPATH =
      "//input[@type='checkbox' and @data-role='list-item' and following-sibling::span[normalize-space(text())='DYNAMIC_VAlUE']]";

  /**
   * Highlighted text in the JRS search option
   */
  public static final String JRSCREATEREPORTPAGE_HIGHLIGHTTEXT_SPAN_XPATH =
      "//span[@class='filter-highlight-text' and contains(text(),'DYNAMIC_VAlUE')] | //span[@class='label-text' and contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * "Run"/"Save" button once the filter is set in the JRS report page or the JRS widget
   */
  public static final String JRSCREATEREPORTPAGE_BUTTON_RUNBUTTON_XPATH = "//button[@id='_paramsSaveButton']";

  /**
   * Report loading spinner
   */
  public static final String JRSCREATEREPORTPAGE_REPORTLOADINGSPINNER_SPAN_XPATH =
      "//span[@id='reportloading-spinner']";

  /**
   * Chart container in the 'FORMAT RESULTS' tab of build reports page when Graph is selected.
   */
  public static final String JRSCREATEREPORTPAGE_CHARTCONTAINER_DIV_XPATH = "//div[@id='chart-container']";

  /**
   * No Results found xpath
   */
  public static final String JRSCREATEREPORTPAGE_NORESULTSFOUND_XPATH =
      "//table[@class='table table-condensed-widget query-results-table table-bordered JCLRFlex' or @class='table table-condensed-widget query-results-table']/tbody/tr/td";
  /**
   * Pagination text in 'RUN REPORT' tab of build report
   */
  public static final String JRSCREATEREPORTPAGE_TOTALITEMSINRESULT_DIV_XPATH = "//div[@class='pagination-page-range']";

  /**
   * Title of project area in "All Projects" page
   */
  public static final String JRSCREATEREPORTPAGE_SELECTINGCCMPROJECT_A_XPATH = "//a[@class='jazz-ui-ResourceLink']";

  /**
   * Add widget in the DashBoard page
   */
  public static final String JRSCREATEREPORTPAGE_OPENADDWIDGET_SPAN_XPATH = "//span[@class='add-widget-span']";

  /**
   * Loading text in the Dashboard page
   */
  public static final String JRSCREATEREPORTPAGE_LOADINGDASHBOARDWIDGET_DIV_XPATH = "//div[@class='loadingText']";

  /**
   * Dropdown button for Select Catalog in the Add widget frame
   */
  public static final String JRSCREATEREPORTPAGE_CATALOGDROPDOWN_DIV_XPATH = "//div[@class='jazz-ui-fat-caret']";

  /**
   * Menu items in the Select Catalog drop down of add widget
   */
  public static final String JRSCREATEREPORTPAGE_CATALOGTABLECOLUMN_COLUMN_XPATH =
      "//td[@class='dijitReset dijitMenuItemLabel']";

  /**
   * Loading message when the catalog option is changed in the add widget
   */
  public static final String JRSCREATEREPORTPAGE_LOADINGMESSAGE_DIV_XPATH = "//div[@class='loading-message']";

  /**
   * 'Add Widget' option to add a particular widget from the add widget pop-up
   */
  public static final String JRSCREATEREPORTPAGE_ADDWIDGETTEAXT_BUTTON_XPATH = "//span[contains(text(),'Add Widget')]";
  /**
   * 'Add Widget' option to add a particular widget from the add widget pop-up
   */
  public static final String JRSCREATEREPORTPAGE_ADDWIDGETTEAXT_BUTTON_FOLOW_WIDGETNAME_XPATH = 
      "//a[text()='DYNAMIC_VAlUE']/ancestor::div[contains(@class,'foreground')]//span[text()='Add Widget']";

  /**
   * 'loading in progress' text when a widget is added to dashboard
   */
  public static final String JRSCREATEREPORTPAGE_LOADINGPROGRESS_DIV_XPATH = "//span[@id='show-parameters-spinner']";

  /**
   * Info message displayed in the Dashboard page.
   */
  public static final String JRSCREATEREPORTPAGE_MESSAGESUMMARY_DIV_XPATH = "//div[@class='messageSummary']";

  /**
   * Spinner when the projects are loaded in the 'CHOOSE ARTIFACTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH =
      "//span[@id='project-spinner'] | //input[@placeholder='Name this report']";
  /**
   * A button with a text specified value DYNAMIC_VAlUE, possible value:Recently created.
   */
  public static final String JRSCREATEREPORTPAGE_TAB_XPATH = "//a[text()='DYNAMIC_VAlUE']";
  /**
   * Spinner when the meta model are loaded in build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH =
      "//span[@id='metamodel-spinner'] | //a[text()='Details']";

  /**
   * Spinner when the conditions are loaded in the 'CHOOSE ARTIFACTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_FILTERSPINNER_SPAN_XPATH = "//span[@id='filter-spinner']";

  /**
   * Spinner when the preview are loaded in the 'FORMAT RESULT' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_PREVIEWSPINNER_SPAN_XPATH = "//span[@id='preview-spinner']";

  /**
   * 'Continue' button in the 'CHOOSE ARTIFACTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_CONTINUE_BUTTON_XPATH = "//button[contains(text(),'Continue')]";

  /**
   * 'Continue' button which is enable in tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_ENABLE_CONTINUE_BUTTON_XPATH =
      "//div[@id=\\\"gatherDataTab\\\"]//div[contains(@class,'panel-collapse') and contains(@class,'in')]//button[contains(text(),'Continue')]";


  /**
   * 'Table/Graph/Refresh' button in the 'FORMAT RESULTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_FORMAT_BUTTON_XPATH = "//button[normalize-space()='DYNAMIC_VAlUE']";


  /**
   * 'Trace relationships and add artifacts' section in the 'CHOOSE ARTIFACTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_RALTIONSHIPSECTIONPRESENT_DIV_XPATH =
      "//div[@id='relationships-section']";

  /**
   * 'Dynamic value button' in build report page
   */
  public static final String JRSCREATEREPORTPAGE_CONTINUE_DYNAMICBUTTON_XPATH = "//button[@id = 'DYNAMIC_VAlUE']";
  
  /**
   * Global configuration link after clicking Run report button
   */
  public static final String  JRS_GLOBAL_CONFIG_LINK = "//a[text()='DYNAMIC_VAlUE']" ;

  /**
   * Navigated page after clicking the Global configuration link
   */
  public static final String  JRS_GLOBAL_CONFIG_NAVIGATED_PAGE = "//span[contains(@class,'title') and contains(text(),'DYNAMIC_VAlUE')]" ;

  /**
   * Project area part in the 'Limit the scope' section in the 'CHOOSE ARTIFACTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_PRJECTAREASECTION_PROJECTAREA_XPATH =
      "//div[@id='select_project_content']";

  /**
   * Search Text box
   */
  public static final String JRSCREATEREPORTPAGE_SEARCHBOX_SPAN_XPATH = "//input[@class='form-control input-sm']";

  /**
   * "Work Item" radio button in "Choose an artifact" section of "CHOOSE DATA" tab in build report page of JRS.
   */
  public static final String JRSCREATEREPORTPAGE_ARTIFACT_ARTIFACTID_XPATH = "//input[@id='Work Item11 Input']";

  /**
   * 'Change Data source' option in "Choose a report type" section of "CHOOSE DATA" tab in build report page of JRS
   */
  public static final String JRSCREATEREPORTPAGE_BUTTON_EDITDATASOURCE_XPATH = "//button[@id='edit-data-source']";

  /**
   * Drop down for "Change the Data Source" pop up for selecting data source to build JRS report.
   */
  public static final String JRSCREATEREPORTPAGE_BUTTON_SELECTDATASOURCE_XPATH = "//select[@class='editable']";

  /**
   * 'OK' buttons in the JRS Page
   */
  public static final String JRSCREATEREPORTPAGE_OK_BUTTON_XPATH = "//button[contains(text(),'OK')]";

  /**
   * 'CANCEL' button in the build new reports page
   */
  public static final String JRSCREATEREPORTPAGE_CANCEL_BUTTON_XPATH = "//button[contains(@class,'clear-choices')]";

  /**
   * 'OK' button when change cancel option is clicked
   */
  public static final String JRSCREATEREPORTPAGE_DISCARDCHANGESOK_BUTTON_XPATH =
      "//button[@id='discard-report-ok-button']";

  /**
   * 'Report Name' text box in 'NAME AND SHARE' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SAVEREPORT_SAVEREPORTTITLE_XPATH = "//input[@id='resource-title']";

  /**
   * Inputs of type 'Text'
   */
  public static final String JRSCREATEREPORTPAGE_RICHTEXTEDITORWIDGET_TEXTBOX_XPATH = "//input[@type='text']";

  /**
   * Inputs of type 'Text Area'
   */
  public static final String JRSCREATEREPORTPAGE_RICHTEXTEDITORWIDGET_TEXTAREABOX_XPATH =
      "//textarea[@id='resource-description']";

  /**
   * Text box to add Tag in 'NAME AND SHARE' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_RICHTEXTEDITORWIDGET_TAG_XPATH =
      "//input[@class='tag-input textInputLimit']";

  /**
   * 'Add Owner' button in 'NAME AND SHARE' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_BUTTON_ADDOWNER_XPATH = "//button[@id='add-owner-button']";

  /**
   * Search Text field in 'CHOOSE REPORT OWNERS' pop-up in 'NAME AND SHARE' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_BUTTON_ADDOWNERSEARCHTEXT_XPATH = "//input[@id='search-text']";

  /**
   * 'All users' section in 'CHOOSE REPORT OWNERS' pop-up in 'NAME AND SHARE' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_BUTTON_ADDOWNERNAME_XPATH = "//select[@id='all-users']";

  /**
   * Select a traceability relationship existence from the drop down for the added relationship as ‘Required’.
   */
  public static final String JRSCREATEREPORTPAGE_ADDEDRELATIONSHIPAS_REQUIRED_DROPDOWN_XPATH =
      "//select[@class='path-existence-options']";
  /**
   * 'Domain' section in 'Choose a domain' pop-up in 'Choose a configuration' dailog of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SELECTDROPDOWN_DOMAINVALUE_XPATH = "//select[@id='domain-select']";
  /**
   * 'ADD>' button in 'CHOOSE REPORT OWNERS' pop-up in 'NAME AND SHARE' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_ADDREPORTMANAGER_BUTTON_XPATH = "//button[@id='add-report-manager']";

  /**
   * 'select Folder' button in 'SELECT A FOLDER' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SELECTFOLDER_COLLECTION_XPATH = "//div[@class='folder-collection']";


  /**
   * 'folder split path' button in 'SELECT A FOLDER' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SELECTFOLDER_FOLDERSPLITPATH = "/";

  /**
   * 'show icon' button in 'SELECT A FOLDER' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SELECTFOLDER_SHOWICON_XPATH =
      "preceding-sibling::span[contains(@class,'folder-icon')]";

  /**
   * 'radio' button in 'SELECT A FOLDER' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SELECTFOLDER_RADIO_XPATH = "preceding-sibling::input";

  /**
   * 'display name' button in 'SELECT A FOLDER' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SELECTFOLDER_DISPLAYNAME_XPATH = "//span[@class='displayName']";

  /**
   * 'DYNAMIC ITEM VALUE' button in 'SELECT A FOLDER' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SELECTFOLDER_ITEM_XPATH =
      "//span[text()='DYNAMIC_VAlUE'] | //a[text()='DYNAMIC_VAlUE']";


  /**
   * 'select Folder' button in 'NAME AND SHARE' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_BUTTON_SELECTFOLDER_XPATH = "//button[@id='select-folder-button']";

  
  /**
   * Radio button under Select Folder Dialog
   */
  public static final String JRSCREATEREPORTPAGE_BUTTON_SELECTFOLDERRADIO_XPATH = "//div[@class='folder-item' and .//a[normalize-space(text())='DYNAMIC_VAlUE']]//input[@type='radio']";

  
  public static final String JRSCREATEREPORTPAGE_BUTTON_NEWFOLDER_XPATH = "//button[contains(@id, 'selectAfolder-') and normalize-space(text())='DYNAMIC_VAlUE']";
  
  
  /**
   * 'SORT TABLE' TABLE in 'FORMAT RESULTS' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_TABLE_SORTYYPE_XPATH =
      "//table[@class='table table-hover result-rows']/tbody";


  /**
   * 'SORTTYPE COLUMN' in 'FORMAT RESULTS' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_COLUMN_SORTYYPE_XPATH =
      "child::td[@class='column-name manualEdit-hide']";

  /**
   * 'DROPDOWN SORTTYPE COLUMN' in 'FORMAT RESULTS' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_DROPDOWN_SORTYYPE_XPATH =
      "child::td[@class='column-sort-type manualEdit-hide']/select";


  /**
   * 'SAVE' button in 'NAME AND SHARE' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SAVE_BUTTON_XPATH = "//button[contains(text(),'Save')]";

  /**
   * Spinner when save report is clicked in 'NAME AND SHARE' tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SAVEREPORTSPINNER_SPAN_XPATH = "//span[@id='save-spinner']";

  /**
   * 'Add a relationship' button in 'Trace relationships and add artifacts' section in the 'CHOOSE ARTIFACTS' tab of
   * build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_ADDRELATIONSHIP_RELATIONSHIP_XPATH =
      "//div[contains(text(),'Add a relationship')]";

  /**
   * 'OK' button in the 'How do you combine the results?' pop-up in the 'CHOOSE ARTIFACTS' tab of build report page in
   * JRS
   */
  public static final String JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTADDBUTTON_BUTTON_XPATH =
      "//button[@id='new-path-ok-button']";

  /**
   * 'Add a relationship' button in 'Trace relationships and add artifacts' section in the 'CHOOSE ARTIFACTS' tab of
   * build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_ADDRELATIONSHIPBUTTON_RELATIONSHIP_XPATH =
      "//button[contains(text(),'Add a relationship')]";

  /**
   * 'Pick a relationship' title in 'Trace relationships and add artifacts' section in the 'CHOOSE ARTIFACTS' tab of
   * build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_ADDREL_ADDRELTITLE_XPATH = "//div[@class='pick-component-text']";

  /**
   * Relation ship labels available in 'Pick a relationship' part of 'Trace relationships and add artifacts' section in
   * the 'CHOOSE ARTIFACTS' tab of build report page in JRS. (Includes 'All link types' label)
   */
  public static final String JRSCREATEREPORTPAGE_ADDRELATIONSHIP_RELATIONSHIPVALUE_XPATH =
      "//span[@class='label-text']";

  /**
   * Relation ship labels available in 'Pick a relationship' part of 'Trace relationships and add artifacts' section in
   * the 'CHOOSE ARTIFACTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_LISTVALUE_TRACEBILITY_XPATH = "//li[@class='relationship-item']";

  /**
   * Relation ship editable text of 'Trace relationships and add artifacts' section in the 'CHOOSE ARTIFACTS' tab of
   * build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_CLICKONTEXT_DIV_XPATH = "//div[@class='component-in-path-text']";

  /**
   * Label text in JRS Page
   */
  public static final String JRSCREATEREPORTPAGE_CHOOSEAETIFACT_LABEL_XPATH = "//label";
  /**
   * Label text in JRS Page
   */
  public static final String JRSCREATEREPORTPAGE_WORNINGWIDGET_OKBUTTON_XPATH =
      "//button[@id='discard-report-ok-button']";
  /**
   * 'ADD CONDITION' button in Set Conditions section in the 'CHOOSE ARTIFACTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_ADDCONDITION_BUTTON_XPATH = "//button[@id='add-attribute-button']";

  /**
   * Radio button with value 'Today'
   */
  public static final String JRSCREATEREPORTPAGE_RADIO_BUTTON_XPATH = "//span[contains(text(),'Today')]";

  /**
   * 'ADD AND CLOSE' button in Add calculated value pop-up
   */
  public static final String JRSCREATEREPORTPAGE_ADD_SAVEANDCLOSECALCULATEDCOLUMNBUTTON_XPATH =
      "//p[text()='Add calculated value columns']/../..//button[@class='btn btn-primary btn-bluemix save-and-close-attribute-select' or @class='bx--btn bx--btn--secondary save-and-close-attribute-select' and not(@disabled)]";

  /**
   * 'ADD AND CLOSE' button version 6
   */
  public static final String JRSCREATEREPORTPAGE_ADDANDCLOSE_BUTTON_V6_XPATH =
      "//button[contains(@class, 'btn btn-primary btn-bluemix') and contains(text(),'Add and Close')]";

  /**
   * 'Add and Close' button version 7
   */
  public static final String JRSCREATEREPORTPAGE_ADDANDCLOSE_BUTTON_V7_XPATH =
      "//button[contains(@class, 'bx--btn bx--btn--secondary') and contains(text(),'Add and Close')]";

  /**
   * 'CLOSE' button
   */
  public static final String JRSCREATEREPORTPAGE_CLOSEBUTTON_SPAN_XPATH =
      "//button[@class='btn btn-default btn-bluemix close-attribute-select']";

  /**
   * 'ADD' button
   */
  public static final String JRSCREATEREPORTPAGE_BUTTON_ADDATTRIBUTE_XPATH = "//button[@id='add-column-button']";

  /**
   * 'Attribute Of' drop down in 'ADD ATTRIBUTES TO THE REPORT' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_ADDATTRIBUTE_SELECT_XPATH = "//select[@id='columns-resource']";

  /**
   * 'ADD' button in 'ADD ATTRIBUTES TO THE REPORT' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_ADD_BUTTON_XPATH =
      "//button[@class='btn btn-primary save-attribute-select']";

  /**
   * 'x' button in 'Limit the scope' section in 'CHOOSE DATA' tab
   */
  public static final String JRSCREATEREPORTPAGE_X_BUTTON_XPATH = "//button[@id='clear-action']";

  /**
   * Table section of 'FORMAT RESULTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_TABLECLASS_TABLE_XPATH =
      "//table[@class='table table-hover result-rows']";
  /**
   * 'CALCULATED VALUE' button in 'FORMAT RESULTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_BUTTON_ADDCALCULATEDCOLUMN_XPATH =
      "//button[@id='add-calc-column-button']";

  /**
   * 'Choose a calculation' section in Add calculated value pop-up in 'FORMAT RESULTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_BUTTON_ADDCALCULATEDCOLUMNSELECTLIST_XPATH =
      "//select[@id='calculation-type']";

  /**
   * 'All (Count all artifacts in the group)' radio button in Add calculated value pop-up in 'FORMAT RESULTS' tab of
   * build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_SELECTCOUNTALLARTIFACTINCALCULATEDCOLUMN_SPAN_XPATH =
      "//span[@id='calc-select-all-values-label']";

  /**
   * 'Limit (Count all artifacts with selected attribute values)' radio button in Add calculated value pop-up in 'FORMAT
   * RESULTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_SELECTLIMITARTIFACTINCALCULATEDCLOUMN_SPAN_XPATH =
      "//span[@id='calc-select-specific-values-label']";

  /**
   * Radio buttons in 'Choose limiter values' section when String attribute is selected in Add calculated value pop-up
   * in 'FORMAT RESULTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_CALCULATEDCOLUMNCOUNTVALUE_INPUT_XPATH =
      "//input[@name='string-undefined']";

  /**
   * Radio buttons in 'Choose limiter values' section when Boolean attribute is selected in Add calculated value pop-up
   * in 'FORMAT RESULTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_CALCULATEDCLOUMNCOUNTVALUE2_INPUT_XPATH =
      "//input[@name='boolean-undefined']";

  /**
   * Radio buttons in 'Choose limiter values' section when User attribute is selected in Add calculated value pop-up in
   * 'FORMAT RESULTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_CALCULATEDCLOUMNCOUNTVALUE3_INPUT_XPATH =
      "//input[@name='person-undefined']";

  /**
   * 'Selected User' radio button in 'Choose limiter values' section when User attribute is selected in Add calculated
   * value pop-up in 'FORMAT RESULTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE7_SPAN_XPATH = "//span[@class='plus-icon']";

  /**
   * 'ADD AND CLOSE' button in Select user pop-up
   */
  public static final String JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE11_ADDCLOSEBUTTON_XPATH =
      "//button[@class='btn btn-default action-addAndClose']";

  /**
   * Search Text field
   */
  public static final String JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE8_INPUTSEARCH_XPATH =
      "//input[@class='form-control search-text']";

  /**
   * 'SEARCH' button in select user pop-up
   */
  public static final String JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE9_BUTTONSEARCH_XPATH =
      "//button[@class='btn btn-default action-search']";

  /**
   * Search result section of select user pop-up
   */
  public static final String JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE10_SELECTUSER_XPATH =
      "//select[@class='search-values']";

  /**
   * 'Set time range...' button in 'Choose limiter values' section when Date attribute is selected in Add calculated
   * value pop-up in 'FORMAT RESULTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_CALCULATEDCLOUMNDATE1_BUTTON_XPATH =
      "//button[@class='btn btn-default add-button set-timerange-button']";

  /**
   * 'ADD' button in Set time range pop-up
   */
  public static final String JRSCREATEREPORTPAGE_SETTIMERANGEADDBUTTON_BUTTON_XPATH =
      "//button[@class='btn btn-primary save-timerange']";

  /**
   * Radio buttons for Start Date in Set time range pop-up
   */
  public static final String JRSCREATEREPORTPAGE_SETTIMERANGESTARTDATE_INPUT_XPATH = "//input[@name='date-start']";

  /**
   * 'Choose an attribute' labels in Add calculated value pop-up in 'FORMAT RESULTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_CHILDARTIFACT_ARTIFACT_XPATH =
      "//input[@class='filter-select-checkbox']";

  /**
   * Remove button
   */
  public static final String JRSCREATEREPORTPAGE_CALCULATEDCLOUMNREMOVEBUTTON_BUTTON_XPATH =
      "//button[@class='btn btn-default btn-sm']";

  /**
   * 'OK' button of confirmation pop-up
   */
  public static final String JRSCREATEREPORTPAGE_ADDCALCCOLUMNBUTTON_OKBUTTON_XPATH =
      "//button[@id='confirm-remove-ok-button'] | //button[text()='OK']";

  /**
   * 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_FORMATRESULT_FORMATRESULTTITLE_XPATH =
      "//div[@id='create-table-tabs']";

  /**
   * 'RUN REPORTS' tab frame
   */
  public static final String JRSCREATEREPORTPAGE_LOADREPORTIFRAME_IFRAME_XPATH = "//iframe[@id='run-report-frame']";

  /**
   * 'Current Data' radio button in 'CHOOSE REPORT TYPE' section of 'CHOOSE ARTIFACTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_REPORTTYPE1_INPUT_XPATH = "//input[@id='report-type-basic']";

  /**
   * 'Historical Trends' radio button in 'CHOOSE REPORT TYPE' section of 'CHOOSE ARTIFACTS' tab of build report page in
   * JRS
   */
  public static final String JRSCREATEREPORTPAGE_REPORTTYPE2_INPUT_XPATH = "//input[@id='report-type-historical']";

  /**
   * Spinner on selecting the time range in 'CHOOSE ARTIFACTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_TIMERANGESPINNER_SPAN_XPATH = "//span[@id='timerange-spinner']";

  /**
   * 'SET Time Range' button in 'CHOOSE ARTIFACTS' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_SETTIMERANGEBUTTON_BUTTON_XPATH =
      "//button[@id='set-timerange-button']";

  /**
   * time range preview section after the time range is selected
   */
  public static final String JRSCREATEREPORTPAGE_SETTIMERANGEPOPUP_DIV_XPATH = "//div[@id='timerange-preview']";

  /**
   * Radio buttons for Pass Date in Set time range pop-up
   */
  public static final String JRSCREATEREPORTPAGE_SETTIMERANGEPASSDATE_INPUT_XPATH = "//input[@class='exactdate']";

  /**
   * Radio buttons for Start Date in Set time range pop-up
   */
  public static final String JRSCREATEREPORTPAGE_SETTIMERANGEENDDATE_INPUT_XPATH = "//input[@name='date-end']";

  /**
   * Valid start and end date are selected in the "Set time range" pop-up
   */
  public static final String JRSCREATEREPORTPAGE_BLABEL_LABEL_XPATH = "//b";

  /**
   * "Set Conditions" section of "CHOOSE DATA" tab in build report page of JRS.
   */
  public static final String JRSCREATEREPORTPAGE_SETCONDITIONVERIFY_SPAN_XPATH = "//span[@class='filter-attribute']";

  /**
   * Enabling multiple paths option in "Trace relationships and add artifacts" section of "CHOOSE DATA" tab in build
   * report page of JRS.
   */
  public static final String JRSCREATEREPORTPAGE_ENABLEMUTIPLEPATH_INPUT_XPATH = "//input[@id='enable-multiple-paths']";

  /**
   * Add mutilple relationship path by enabling multiple paths option in "Trace relationships and add artifacts" section
   * of "CHOOSE DATA" tab in build report page of JRS.
   */
  public static final String JRSCREATEREPORTPAGE_ADDMULTIPLERELATIONSHIPPATH_BUTTON_XPATH =
      "//button[@class='btn btn-default btn-xs btn-bluemix action-branch path-existence']";

  /**
   * Combining options to combine the relationships.
   */
  public static final String JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTMERGE_DIV_XPATH =
      "//div[@id='merge-results-option']";

  /**
   * combining options to combine the relationships. "Merge" or "Append" or "Append in new columns"
   */
  public static final String JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTAPPEND_DIV_XPATH =
      "//div[@id='append-results-same-source-option']";

  /**
   * Combining options to combine the relationships.
   */
  public static final String JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTAPPENDWITHNEWCOLUMN_DIV_XPATH =
      "//div[@id='append-results-new-source-option']";

  /**
   * Available when "Enable multiple path" is selected in JRS Build page.
   */
  public static final String JRSCREATEREPORTPAGE_ADDARTIFACTMULTIPLERELATIONSHIP_BUTTON_XPATH =
      "//button[@class='btn btn-default btn-xs btn-bluemix action-add-artifact']";

  /**
   * SourceArtifact the artifact to be selected.
   */
  public static final String JRSCREATEREPORTPAGE_SELECTARTIFACTOKBUTTON_BUTTON_XPATH =
      "//button[@id='select-artifact-type-ok-button']";

  /**
   * widget content spinner for openWorkItemIdFilter
   */
  public static final String JRSCREATEREPORTPAGE_WIDGETCONTENTSPINNER_SPAN_XPATH =
      "//span[@id='widget-content-spinner']";

  /**
   * column filter for for openWorkItemIdFilter
   */
  public static final String JRSCREATEREPORTPAGE_COLUMNFILTER_SPAN_XPATH = "//span[@class='column-filter']";

  /**
   * column filter title text for for openWorkItemIdFilter
   */
  public static final String JRSCREATEREPORTPAGE_COLUMNFILTER_TITLE_XPATH = "//span[@data-original-title='']";

  /**
   * Column filter text box for openWorkItemIdFilter
   */
  public static final String JRSCREATEREPORTPAGE_COLUMNFILTERTEXTBOX_INPUT_XPATH =
      "//input[@class='form-control gadget-input-xs jrs-param-inner']";

  /**
   * Filter Column Run for openWorkItemIdFilter
   */
  public static final String JRSCREATEREPORTPAGE_FILTERCOLUMNRUN_A_XPATH = "//a[contains(text(),'Filter')]";

  /**
   * Result page table rows for openWorkItemIdFilter
   */
  public static final String JRSCREATEREPORTPAGE_RESULTPAGETABLEROWS_TABLEROWS_XPATH =
      "//table[@id='table-header-container']";

  /**
   * Results for the Unassigned Value.
   */
  public static final String JRSCREATEREPORTPAGE_RESULTCOLUMNCLASS_TD_XPATH = "//td[@class='bidiAware']";

  /**
   * Select "Graph" option in the "FORMAT RESULTS" tab in build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SELECTGRAPHBUTTON_BUTTON_XPATH = "//button[@id='show-as-chart']";

  /**
   * X path for the logout option
   */
  public static final String JRSCREATEREPORTPAGE_LOGOUT_A_XPATH = "//a";

  /**
   * The value to be selected from drop down "Graph" or "Table"
   */
  public static final String JRSCREATEREPORTPAGE_SELECTGRAPHONRESULTPAGE_DROPDOWN_XPATH = "//select[@id='viz-select']";

  /**
   * Set the relationship existence for the relationship added in the "Trace relationships and add artifacts" section of
   * "CHOOSE DATA" tab in build report page of JRS.
   */
  public static final String JRSCREATEREPORTPAGE_ADDRELATIONSHIP_PATHEXISTENCEOPTIONS_XPATH =
      "//*[@id=\"group1\"]/div/table/tbody/tr/td[1]/div/div[1]/div[3]/select/option";

  /**
   * Click on the "RUN REPORT" tab in the build report page.
   */
  public static final String JRSCREATEREPORTPAGE_HEADERMENU_RUNREPORTBUTTON_XPATH =
      "//*[@id=\"create-report-tab-headers\"]/li[4]/a";

  /**
   * Click on the "CHOOSE DATA" tab in the build report page.
   */
  public static final String JRSCREATEREPORTPAGE_HEADERMENU_CHOOSEDATABUTTON_XPATH =
      "//*[@id=\"create-report-tab-headers\"]/li[1]/a";

  /**
   * The alert message matches the expected alert message.
   */
  public static final String JRSCREATEREPORTPAGE_ALERTSESSION_ALERTSUMMARY_XPATH = "//*[@id=\"alert-summary\"]";

  /**
   * the "Export the report to Microsoft Excel" in the "RUN REPORT" tab of build report page and download the file.
   */
  public static final String JRSCREATEREPORTPAGE_EXPORTTOEXCELBUTTON_BUTTON_XPATH = "//button[@id='export-drop-down']";

  /**
   * The "Export the report to Microsoft Excel" in the "RUN REPORT" tab of build report page and download the file.
   */
  public static final String JRSCREATEREPORTPAGE_EXPORTTOEXCELLIST_A_XPATH = "//a[@id='export-excel-button']";

  /**
   * The "Export the report to Microsoft Excel" in the "RUN REPORT" tab of build report page and download the file.
   */
  public static final String JRSCREATEREPORTPAGE_DOWNLOADEXCELSPREADSHEET_A_XPATH =
      "//a[contains(text(),'Download a static spreadsheet with the current report results.')]";

  /**
   * The results for the calculated column in the "RUN REPORT" tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_RESULTTABLECLASS_TABLE_XPATH =
      "//table[@class='table table-condensed-widget query-results-table' or @class='table table-condensed-widget query-results-table table-bordered JCLRFlex']";

  /**
   * Set the units and dimensions for the graph when "Graph" is selected in the "FORMATS RESULTS" tab of build report
   * page.
   */
  public static final String JRSCREATEREPORTPAGE_GRAPHUNITS_DROPDOWN_XPATH = "//select[@id='count-select']";

  /**
   * The dimension value to be selected for the graph when "Graph" is selected in the "FORMATS RESULTS" tab of build
   * report page.
   */
  public static final String JRSCREATEREPORTPAGE_GRAPHDIMENSIONS_DROPDOWN_XPATH = "//select[@id='series-select']";

  /**
   * A report in the "All Reports" Page of JRS.
   */
  public static final String JRSCREATEREPORTPAGE_REPORTNAME_REPORTNAME_XPATH =
      "//a[@class='title-text reportdefinition-link']";

  /**
   * The graph type to be selected in the "FORMATS RESULTS" tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SETGRAPHTYPE_BUTTON_XPATH = "//button[@id='chart-select-button']";

  /**
   * Button Stacked bar chart to be selected in the "FORMATS RESULTS" tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_STACKEDCHARTTYPE_DIV_XPATH = "//div[@id='buttonstackedbarchartfalse']";

  /**
   * Grouped Bar to be selected in the "FORMATS RESULTS" tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_GROUPEDBARCHARTTYPE_DIV_XPATH =
      "//div[@id='buttongroupedbarchartfalse']";

  /**
   * Line to be selected in the "FORMATS RESULTS" tab of build report page.
   */
  public static final String JRSCREATEREPORTPAGE_LINECHARTTYPE_DIV_XPATH = "//div[@id='buttonlinechartfalse']";

  /**
   * the Date Scale when Graph was selected in the "FORMATS RESULTS" tab in the build report page. For Date Scale to be
   * available for historical trends and when attribute of type Date is selected for the axis.
   */
  public static final String JRSCREATEREPORTPAGE_SETDATESCALE_BUTTON_XPATH = "//button[@id='datescale-select-button']";

  /**
   * The Date Scale when Graph was selected in the "FORMATS RESULTS" tab in the build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SCALEBYDAYS_INPUT_XPATH = "//input[@id='scale-by-days']";

  /**
   * The Week Scale when Graph was selected in the "FORMATS RESULTS" tab in the build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SCALEBYWEEKS_INPUT_XPATH = "//input[@id='scale-by-weeks']";

  /**
   * The Months Scale when Graph was selected in the "FORMATS RESULTS" tab in the build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SCALEBYMONTHS_INPUT_XPATH = "//input[@id='scale-by-months']";

  /**
   * The Years Scale when Graph was selected in the "FORMATS RESULTS" tab in the build report page.
   */
  public static final String JRSCREATEREPORTPAGE_SCALEBYYEARS_INPUT_XPATH = "//input[@id='scale-by-years']";

  /**
   * LogOut from the JRS server.
   */
  public static final String JRSCREATEREPORTPAGE_USERLOGOUT_SPAN_XPATH = "//span[@id='jazzone-banner-user-name']";
  /**
   * Provides jrs report name as per the dynamic value.
   */
  public static final String JRSCREATEREPORTPAGE_REPORTNAME_TEXTBOX_XPATH = "//input[@id='DYNAMIC_VAlUE']";
  /**
   * Provides jrs report name as per the dynamic value.
   */
  public static final String JRSVIEWREPORTPAGE_QMWORKITEM_ID_XPATH = "//td[text()='DYNAMIC_VAlUE']";

  /**
   * provides jrs report name as per the dynamic value.
   */
  public static final String JRSVIEWREPORTPAGE_CCMWORKITEM_ID_XPATH =
      "//td[text()='DYNAMIC_VAlUE']/following-sibling::td[2]";
  /**
   * provides jrs report name as per the dynamic value.
   */
  public static final String JRSVIEWREPORTPAGE_RUNREPORTFRAME_ID_XPATH = "//iframe[@id='run-report-frame']";
  /**
   * Provides jrs report name as per the dynamic value.
   */
  public static final String JRSVIEWREPORTPAGE_PICKARELATIONSHIP_TEXT_XPATH =
      "//b[text()='DYNAMIC_VAlUE0']/following-sibling::ul/descendant::span[contains(text(),'DYNAMIC_VAlUE1')]";
  /**
   * Selecting 'add relationshio' in trace relationship
   */
  public static final String JRSVIEWREPORTPAGE_CLOCKONADDRELATIONSHIP_XPATH = "//div[@class='add-path-container']";
  /**
   * Selecting the relationship after selecting add relationship
   */

  public static final String JRSVIEWREPORTPAGE_PICARELATIONSHIP_XPATH = "//span[text()=' Related Work Item']";
  /**
   * Click ok button in relationship.
   */
  public static final String JRSVIEWREPORTPAGE_CLICKONOKRELATIONSHIP_XPATH =
      "//button[@class='btn btn-default btn-xs btn-bluemix action-ok']";
  /**
   * Clicking on Work Item in pic an artifact from related work item
   */
  public static final String JRSVIEWREPORTPAGE_PICANARTIFACT_XPATH = "//span[text()=' Work Item']";
  /**
   * Clicking on ok button from an artifact.
   */
  public static final String JRSVIEWREPORTPAGE_CLICKOKFROMARIFACT_XPATH =
      "//div[@class='resource-choice']/div/button[@class='btn btn-default btn-xs btn-bluemix action-ok']";
  /**
   * Clicking on ok button from an artifact.
   */
  public static final String JAZZDASHBOARDPAGE_SPAN_TEXT_XPATH = "//span[text()='";
  /**
   * Clicking on ok button from an artifact.
   */
  public static final String JAZZDASHBOARDPAGE_PARENT_SPAN_XPATH =
      "']//parent::span//parent::span//span[@class='menu']";
  /**
   * Clicking on ok button from an artifact.
   */
  public static final String JAZZDASHBOARDPAGE_TD_TEXT_XPATH = "//td[text()='";

  /**
   * Element id to click the tab name in Jazz DashBoard page.
   */
  public static final String DIGIT_FORM_TEXTBOX_0 = "dijit_form_TextBox_0";
  /**
   * Dashboard page all tabs
   */
  public static final String JAZZDASHBOARDPAGE_ALLTABS_DIV_XPATH = "//div[@class='com-ibm-team-dashboard-tabs']";

  /**
   * Build report preview tab
   */
  public static final String JRSBUILDREPORT_PREVIEWTAB_DIV_XPATH = "//div[@id='preview-tabs']";
  /**
   * Schedule button x-path in the report page
   */
  public static final String JRS_SCHEDULE_BUTTON = "//button[text()='Schedule']";

  /**
   * Filter button x-path in the report page
   */
  public static final String JRS_FILTER_BUTTON = "//button[@class='btn btn-xs btn-default gadget-show-filter']";
  /**
   * Pass the button value "Continue".
   */
  public static final String CONTINUE = "Continue";
  /**
   * Xpath for adding widget into dashboard.
   */
  public static final String ADD_WIDGET_TO_DASHBOARD_XPATH =
      "//a[text()='DYNAMIC_VAlUE']/../..//span[text()='Add Widget']";
  /**
   * Xpath for tab name in dashboard page.
   */
  public static final String NEW_TAB_TEXTBOX_XPATH = "//input[contains(@id,'dijit_form_TextBox')]";
  /**
   * Xpath for tab name in dashboard page.
   */
  public static final String SEARCH = "Search";
  /**
   * Loading text in the Dashboard page
   */
  public static final String JRSBUILDNEWREPORTPAGE_CHOOSEARTIFACT_LABEL_XPATH =
      "//input[@type='radio']/following-sibling::label[text()='DYNAMIC_VAlUE']";
  /**
   * Loading text in the Dashboard page
   */
  public static final String JRSBUILDNEWREPORTPAGE_CHOOSEARTIFACT_LABELEXPAND_BUTTON_XPATH =
      "//input[@type='radio']/following-sibling::label[text()='DYNAMIC_VAlUE']";
  // "//input[@type='radio']/following-sibling::label[text()='DYNAMIC_VAlUE']/preceding-sibling::span[@class='icon-folder
  // icon-group-closed' or @class='icon-folder icon-group-closed icon-group-hidden']";
  /**
   * Loading text in the Dashboard page
   */
  public static final String JRSBUILDNEWREPORTPAGE_TWISTILE_BUTTON_XPATH =
      "//a[contains(text(),'DYNAMIC_VAlUE')]/ancestor::h4[@class='panel-title']//span[contains(@class,'twistie')]";
  /**
   * Loading text in the Dashboard page
   */
  public static final String JRSBUILDNEWREPORTPAGE_EDIT_DATASOURCE_BUTTON_XPATH = "//a[@id='primary-data-source']";
  /**
   * Loading text in the Dashboard page
   */
  public static final String JRSBUILDNEWREPORTPAGE_FILTER_RADIO_BUTTON_XPATH =
      "//span[contains(text(),'DYNAMIC_VAlUE')]/preceding-sibling::input[@type='checkbox' and not(contains(@style,'display: none'))]";
  /**
   * Loading text in the Dash board page
   */
  public static final String JRSBUILDNEWREPORTPAGE_TEXTBOX_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/ancestor::div[contains(@class,'row')]//input";
  /**
   * Loading text in the Dash board page
   */
  public static final String JRSBUILDNEWREPORTPAGE_ATTRIBUTE_TEXTBOX_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/ancestor::div[@class='row']//input";
  /**
   * Loading text in the Dash board page
   */
  public static final String JRSBUILDNEWREPORTPAGE_DIALOG_BUTTON_XPATH =
      "//div[@class='modal-dialog']//button[text()='DYNAMIC_VAlUE']";
  /**
   * XPATH for MYREPORT Link
   */
  public static final String JRSBUILDNEWREPORTPAGE_MYREPORTLINK_XPATH = "//span[text()='My reports']";
  /**
   * XPATH for Group by dropdown
   */
  public static final String JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWN_XPATH =
      "//select[@id='select-group-by'and @title='Order the reports alphabetically, by sharing, tag, or folder.'] | //button[@id='mine-reports-groupByButton' and @title='Order the reports alphabetically, by sharing, tag, or folder.']";

  /**
   * XPATH for Group by dropdown select
   */
  public static final String JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWNSELECT_XPATH =
      "//select[@id='select-group-by' or @id='mine-reports-select-group-by']";

  /**
   * XPATH for Group by dropdown select options
   */
  public static final String JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWNSELECTOPTION_XPATH =
      "//div[@id='mine-reports-dropdown']/ul[@id='mine-reports-select-group-by']/li";
  /**
   * XPATH for Group by Folder Expand
   */
  public static final String JRSBUILDNEWREPORTPAGE_GROUPBYFOLDEREXPAND_XPATH =
      "//a[contains(@id, 'folderName') and text()='DYNAMIC_VAlUE']";
  /**
   * Loading text in the Dash board page
   */
  public static final String JRSBUILDNEWREPORTPAGE_CHARTTYPE_DROPDOWN_XPATH = "//button[contains(.,'DYNAMIC_VAlUE')]";
  /**
   * Loading text in the Dash board page
   */
  public static final String JRSBUILDNEWREPORTPAGE_CHART_BUTTON_XPATH =
      "//p[text()='DYNAMIC_VAlUE']/following-sibling::div[@class='chart-button']";
  /**
   * Loading text in the Dash board page
   */
  public static final String JRSBUILDNEWREPORTPAGE_REPORT_BUTTON_XPATH =
      "//span[contains(text(),'DYNAMIC_VAlUE')]/preceding-sibling::input";
  /**
   * Loading text in the Dash board page
   */
  public static final String JRSBUILDNEWREPORTPAGE_CHART_DROPDOWN_XPATH =
      "//div[contains(@id,'chart-select')]//i[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for search text field in JRS.
   */
  public static final String JRSBUILDNEWREPORTPAGE_SEARCH_PROJECTAREA_TEXTFIELD_XPATH =
      "//input[@placeholder='Search']";
  /**
   * Xpath for search text field in JRS.
   */
  public static final String JRSBUILDNEWREPORTPAGE_REPORTNAME_XPATH =
      "//*[contains(text(),'DYNAMIC_VAlUE')]/ancestor::tr//a[contains(@class,'dropdown')]";

  /**
   * Xpath to click 'Choose Configuration' button.
   * 
   */
  public static final String JRSBUILDNEWREPORTPAGE_CLICKONCHOOSECONFIGURATION_XPATH =
      "//td[text()='DYNAMIC_VAlUE']| //button[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for 'Choose Configuration' label, click on this to expand section
   * 
   */
  public static final String JRSBUILDNEWREPORTPAGE_CLICKONCHOOSECONFIGURATION_LABEL_XPATH =
      "//p[text()='DYNAMIC_VAlUE']/parent::button[not(contains(@aria-expanded,'true'))]";

  /**
   * Xpath for Choose Domain from 'Choose Configuration' wizard.
   */
  public static final String JRSBUILDNEWREPORTPAGE_CLICKONFILTEROPTION_XPATH = "//span[@id='filters-toggle-twistie']";
  /**
   * Xpath to switch ifame
   */
  public static final String JRSBUILDNEWREPORTPAGE_SWITCHTOIFRAME_XPATH = "//iframe[@id='run-report-frame']";
  /**
   * Xpath for Choose Domain from 'Choose Configuration' wizard. //span[@id='filters-toggle-twistie']
   */
  public static final String JRSBUILDNEWREPORTPAGE_LOCATECHOOSEDOMAIN_XPATH = "//select[@id='domain-select']";
  /**
   * Xpath for Choose Domain from 'Choose Configuration' wizard.
   */
  public static final String JRSBUILDNEWREPORTPAGE_CHOOSEDOMAIN_XPATH =
      "//select[@class='form-control resource-attributes' and @id='domain-select']";

  /**
   * Xpath for Choose a Project Area from 'Choose Configuration' wizard.
   */
  public static final String JRSBUILDNEWREPORTPAGE_CLICKONPROJECTAREA_XPATH = "//span[@id='project-twistie']";
  /**
   *
   */
  public static final String JRSBUILDNEWREPORTPAGE_SEARCHPROJECTAREATEXTBOX_XPATH =
      "//div[@class='input-group']/input[@id='search-project-input']";
  /**
   *
   */
  public static final String JRSBUILDNEWREPORTPAGE_CHOOSEPROJECTAREA_XPATH = "//select[@id='project-select']";
  /**
   * Xpath for Choose Component from 'Choose Configuration' wizard.
   */
  public static final String JRSBUILDNEWREPORTPAGE_CLICKONCOMPONENT_XPATH =
      "//span[@id='component-twistie'] | //td[contains(text(),'Choose a component to filter the options on the')]";
  /**
   *
   */
  public static final String JRSBUILDNEWREPORTPAGE_SEARCHCONFIGURATIONTEXTBOX_LABELEXPANDED_XPATH =
      "//p[text()='Choose a configuration']/ancestor::div[contains(@class,'panel-heading')]//a[@aria-expanded='true'] | //label[text()='Choose a configuration']/preceding-sibling::span[@class='filter-open']";
  /**
   *
   */
  public static final String JRSBUILDNEWREPORTPAGE_SEARCHCOMPONENTTEXTBOX_LABELEXPANDED_XPATH =
      "//p[text()='Choose a component to filter the options on the \\\"Choose a configuration\\\" menu']/ancestor::div[contains(@class,'panel-heading')]//a[@aria-expanded='true'] | //label[text()='Choose a component']/preceding-sibling::span[@class='filter-open']";
  /**
   *
   */
  public static final String JRSBUILDNEWREPORTPAGE_SEARCHCOMPONENTTEXTBOX_XPATH =
      "//div[@class='input-group']/input[@id='search-component-input']|//p[text()='Choose a component to filter the options on the \"Choose a configuration\" menu']/ancestor::div[contains(@class,'panel-heading')]/following-sibling::div//input[@placeholder='Search']";
  /**
   *
   */
  public static final String JRSBUILDNEWREPORTPAGE_CHOOSECOMPONENT_XPATH =
      "//select[@id='component-select' or @id='treeSelect01']";
  /**
   * Xpath for Choose a Configuration from 'Choose Configuration' wizard.
   */
  public static final String JRSBUILDNEWREPORTPAGE_CLICKONCONFIGURATION_6_XPATH =
      "//td[@class='required']/following-sibling::td[(text()='Choose a configuration')]";
  /**
   *
   */
  public static final String JRSBUILDNEWREPORTPAGE_CLICKONCONFIGURATION_7_XPATH = "//span[@id='configuration-twistie']";

  /**
   *
   */
  public static final String JRSBUILDNEWREPORTPAGE_SEARCHCONFIGURATIONTEXTBOX_XPATH =
      "//div[@class='input-group']/input[@id='search-configuration-input'] | //p[text()='Choose a configuration']/ancestor::div[contains(@class,'panel-heading')]/following-sibling::div//input[@placeholder='Search']";
  /**
   *
   */
  public static final String JRSBUILDNEWREPORTPAGE_CHOOSECONFIGURATION_XPATH = "//select[@id='configuration-select']";
  /**
   * Xpath for search text field in JRS.
   */
  public static final String JRSBUILDNEWREPORTPAGE_REPORT_DELETE_XPATH =
      "//*[contains(text(),'DYNAMIC_VAlUE')]/ancestor::tr//a[text()='Delete']";
  /**
   * Xpath for search text field in JRS.
   */
  public static final String JRSBUILDNEWREPORTPAGE_REPORT_EDIT_XPATH =
      "//span[contains(text(),'DYNAMIC_VAlUE')]/ancestor::tr//a[text()='Edit']";
  /**
   * Xpath for Report builder link field in JRS.
   */
  public static final String JRSBUILDNEWREPORTPAGE_REPORT_LINK_XPATH =
      "//div[@id=\"sideBarTitleBox\"]/a[@href='/rs/reports#start']";
  /**
   * Xpath for My Reports field in JRS.
   */
  public static final String JRSBUILDNEWREPORTPAGE_MYREPORT_LINK_XPATH =
      "//a[@id='jaas-mystuff']/span[text()='MY REPORTS'] | //a[@id='myReports']/span[text()='My reports']";
  /**
   * Constant String value to hold text "\" successfully.".
   */
  public static final String SUCCESSFULLY = "\" successfully.";
  /**
   * Constant String value to hold text "Verified: ".
   */
  public static final String VERIFIED = "Verified: ";
  /**
   * Constant String value to hold text " not Selected.".
   */
  public static final String NOT_SELECTED = " not Selected.";
  /**
   * Constant String value to hold text "disabled".
   */
  public static final String DISABLED = "disabled";
  /**
   * Constant String value to hold text "class".
   */
  public static final String CLASS = "class";


  /**
   * Table section of 'RUN REPORT' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_RUNREPORT_TABLE_XPATH =
      "//table[@class='table table-condensed-widget query-results-table' or @class='table table-condensed-widget query-results-table table-bordered JCLRFlex']/tbody";

  /**
   * Table section of 'RUN REPORT' tab of build report page in JRS
   */
  public static final String JRSCREATEREPORTPAGE_RUNREPORT_TABLE_HEADER_XPATH =
      "preceding-sibling::thead/tr/th[contains(@data-resizable-column-id,'REFERENCE_ID')]";

  /**
   * Xpath to click 'Choose Configuration' button.
   */
  public static final String JRSBUILDNEWREPORTPAGE_SAMPLELAYOUTTABLE_XPATH = "//div[@id='table-viewport']";
  /**
   * Xpath to click 'Choose Configuration' button.
   */
  public static final String JRSBUILDNEWREPORTPAGE_SAMPLELAYOUTTABLE_LABLE_XPATH =
      "//table/thead/tr/th[2]/span[text()='DYNAMIC_VAlUE']";

  /**
   * 'CANCEL' button in the build new reports page
   */
  public static final String JRSCREATEREPORTPAGE_CUSTOMEXPRESSION_BUTTON_XPATH =
      "//button[@id='add-custom-column-button']";

  /**
   * 'ADD' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_ADD_BUTTON_CUSTOMEXPRESSION_XPATH =
      "//div[@id='add-custom-column-modal']//button[text()='Add']";

  /**
   * 'ADD' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_ADD_BUTTON_ADDATTRIBUTE_XPATH =
      "//div[@class='bx--modal in is-visible']//button[text()='Add']|//*[@id='add-columns-modal']/div[2]/div/div[1]/div[3]/button[1]";

  /**
   * 'ADD' button in 'ATTRIBUTE COLUMN' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_ADD_COLUMN_BUTTON_ADDATTRIBUTE_XPATH =
//      "//button[@class='bx--btn bx--btn--primary save-attribute-select']//button[text()='Add']";
//      "//button[contains(@class='bx--btn bx--btn--primary save-attribute-select')]//button[contains(text(),'Add')]";
  // div[@id='add-columns-modal']//button[text()='Add']
//      "//button[@class='bx--btn bx--btn--primary save-attribute-select']";
      "//div[@aria-labelledby='add-selected-columns-title']//button[text()='Add']";


  /**
   * 'ADD' 'CUSTOME EXPRESSION' pop-up in 'Test Area'
   */
  public static final String JRSCREATEREPORTPAGE_TEXTAREA_CUSTOMEXPRESSION_XPATH =
      "//textarea[@id='custom-expression-text']";
  /**
   * 'VALIDATE' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_VALIDATE_BUTTON_CUSTOMEXPRESSION_XPATH =
      "//div[@id='add-custom-column-modal']//button[text()='Validate']";

  /**
   * 'VALIDATE' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_ATTRIBUTEOF_DROPDOWN_CUSTOMEXPRESSION_XPATH =
      "//select[@id='custom-attribute-resource']";

  /**
   * 'VALIDATE' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_PREVIEWREPORT_COLUMN_HEADER_XPATH =
      "//div[@id='table-viewport']//thead//th[@class='sortable']";

  /**
   * 'VALIDATE' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_SELECTED_TAB =
      "//ul[@id='create-report-tab-headers']/li[@class='active' or @aria-selected='true']/a";
  /**
   * 'Xpath for LIMIT Project area in run report page'
   */
  public static final String JRSCREATEREPORTPAGE_LIMITPA_XPATH = "//span[text()=': ALM Test (CCM) - CCM Project']";

  /**
   * 'VALIDATE' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSRUNREPORTPAGE_IFRAME_XPATH = "//iframe[@id='run-report-frame']";

  /**
   * 'VALIDATE' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSRUNREPORTPAGE_REPORT_COLUMN_HEADER_XPATH = "//div[@id='table-viewport']//thead//th";

  /**
   * 'VALIDATE' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_SORT_ORDER_VALUE_XPATH =
      "//tr[td[@class='column-name manualEdit-hide' and text()='DYNAMIC_VAlUE']]/td[@class='column-sort-order manualEdit-hide']/input";

  /**
   * 'VALIDATE' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_ARTIFACT_PARENT_XPATH =
      "(//a[@id='DYNAMIC_VAlUE' and not(contains(@style,'display: none;'))]/label)[1]";

  /**
   * 'VALIDATE' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_ARTIFACT_EXPAND_ICON_XPATH =
      "(//a[@id='DYNAMIC_VAlUE' and not(contains(@style,'display: none;'))]/span[contains(@class,'icon-folder')])[1]";

  /**
   * 'VALIDATE' button in 'CUSTOME EXPRESSION' pop-up in 'FORMAT RESULTS' tab
   */
  public static final String JRSCREATEREPORTPAGE_ARTIFACT_CHILD_XPATH =
      "(//a[@id='%s' and not(contains(@style,'display: none;'))])[1]/following::div[1]//label[text()='%s']";

  /**
   * Checkbox in the condition row undser 'Set Condition' section in REPORT BUILDER page
   */
  public static final String JRSCREATEREPORTPAGE_CONDITION_ROW_CHECKBOX_XPATH =
      "//div[contains(@data-id, 'filter')]//button[contains(., 'DYNAMIC_VAlUE')]/preceding-sibling::input";

  /**
   * Drop down for for selecting group type of the group of selected conditions.
   */
  public static final String JRSCREATEREPORTPAGE_SELECT_GROUPTYPE_XPATH = "//div[@class='filter-group']//select";

  /**
   * Drop down for for selecting outer group type of the group of selected conditions.
   */
  public static final String JRSCREATEREPORTPAGE_SELECT_OUTER_GROUPTYPE_XPATH =
      "//select[not (ancestor::div[@class='groups-container']) and ancestor::div[@class='filter-group-body']]";

  /**
   * VERIFY all of selected conditions undser 'CONDITIONS' section in REPORT BUILDER page
   */
  public static final String JRSCREATEREPORTPAGE_ALL_SELECTED_CONDITIONS_XPATH = "//div[@class='summary-filter-group']";

  /**
   * VERIFY all of selected value of conditions in 'Filters' section in REPORT BUILDER page
   */
  public static final String JRSCREATEREPORTPAGE_ALL_PARAMS_VALUES_IN_FILTERS_XPATH =
      "//span[@class='jrs-param-values']";

  /**
   * VERIFY group of selected conditions undser 'CONDITIONS' section in REPORT BUILDER page
   */
  public static final String JRSCREATEREPORTPAGE_GROUPOF_SELECTED_CONDITIONS_XPATH =
      "//div[@class='summary-filter-group']/div[@class='summary-filter-group']";

  /**
   * Textbox to input value of daysago of Date condition in REPORT BUILDER page
   */
  public static final String JRSCREATEREPORTPAGE_CONDITION_DAYSAGO_INPUT_XPATH = "//input[@class='daysago']";

  /**
   * Dropdown of select daysago of Date condition in REPORT BUILDER page
   */
  public static final String JRSCREATEREPORTPAGE_CONDITION_DAYSAGO_SELECT_XPATH = "//select[@class='selectdaysago']";

  /**
   * Textbox to input value of condition in REPORT BUILDER page
   */
  public static final String JRSCREATEREPORTPAGE_CONDITION_INPUT_XPATH =
      "//div[@class='filter-node']//input[@type='text']";

  /**
   * Aggregate checkbox in 'ADD A CUSTOM EXPRESSION COLUMN' dialog
   */
  public static final String JRSCREATEREPORTPAGE_AGGREGATE_CHECKBOX_XPATH = "//input[@id='aggregate-checkbox']";

  /**
   * Div element which contain message/warning return after validating a custom exrpession in 'ADD A CUSTOM EXPRESSION
   * COLUMN' dialog
   */
  public static final String JRSCREATEREPORTPAGE_DIV_VALIDATING_MESSAGE_XPATH = "//div[@id='custom-column-error']";

  /**
   * Success validating message after validating a custom exrpession in 'ADD A CUSTOM EXPRESSION COLUMN' dialog
   */
  public static final String SUCCESS_VALIDATING_MESSAGE = "The expression is valid. Click Add.";

  /**
   * The header title of 'EXPRESSION COLUMN' dialog in version 7.0 after clicking Custom Expression button
   */
  public static final String JRSCREATEREPORTPAGE_HEADER_CUSTOM_EXPRESSION_PTAG_XPATH =
      "//p[contains(@class, 'modal-header__heading') and text()='Custom Expression']";

  /**
   * Select multiple path option - dropdown in 'Trace relationship and add artifact' section of REPORT BUILDER page
   */
  public static final String JRSCREATEREPORTPAGE_MULTIPLE_PATH_SELECT_XPATH =
      "//select[@class='group-indicator' and preceding-sibling::div/div[@title='DYNAMIC_VAlUE']]";

  /**
   * New artifact path in 'Trace relationship and add artifact' section of REPORT BUILDER page
   */
  public static final String JRSCREATEREPORTPAGE_NEW_ARTIFACT_PATH_DIV_XPATH =
      "//div[@class='query-diagram-area']//div[@title='DYNAMIC_VAlUE']";

  /**
   * No result message td xpath in RUN REPORT tab when no record matching current conditions
   */
  public static final String JRSCREATEREPORTPAGE_NO_RESULT_MESSAGE_TD_XPATH = "//tbody[@id='_resultBody']/tr/td";

  /**
   * No result message shown in result table of RUN REPORT tab when no record matching current conditions
   */
  public static final String JRSCREATEREPORTPAGE_NO_RESULT_MESSAGE = "No results found.";

  /**
   * Table result in Choose component Dialog
   */
  public static final String JRSCREATEREPORTPAGE_CHOOSE_COMPONENT_RESULT_XPATH =
      "//tbody[@id='_resultBody'] | //div[@id='_visualization']//*[@id='chart-container']";

  /**
   * Relation ship labels available in 'Pick an artifact' part of 'Trace relationships and add artifacts' section
   */
  public static final String JRSCREATEREPORTPAGE_LISTVALUE_PICK_AN_ARTIFACT_XPATH =
      "//div[text()='Pick an artifact']/parent::div[contains(@class,'resource-choice')]/ul/li";
  /**
   * Constant string variable to hold text "linksSection".
   */
  public static final String JRSCREATEREPORTPAGE_TAB = "reportTab";

  /**
   * Constant string variable to hold text "linksSection".
   */
  public static final String JRSCREATEREPORTPAGE_PANELHEADING = "panelHeading";
  
  /**
   * Label drop down: 'Privacy and sharing'
   */
  public static final String JRSALLREPORTPAGE_FILTERSELECTVISIBILITYDROPDOWN_LABEL = "Privacy and sharing:";
  
  /**
   * Tag Name in JRS All Reports
   */
  public static final String JRSALLREPORTPAGE_REPORTTAG_XPATH = "//a[contains(@class,'title-text folder')]";
  
  /**
   * Loading message
   */
  public static final String JRSALLREPORTPAGE_LOADING_XPATH = "//h3[contains(@text,'Loading, please wait...')]";

  
  /**
   * XPATH for Search box in All reports page
   */
  public static final String JRSALLREPORTPAGE_SEARCHTXT_XPATH = "//div[@id='all-reports']//input[@placeholder='Search']";
  
  /**
   * XPATH for search icon in ALL report page
   */
  public static final String JRSALLREPORTPAGE_SEARCHICON_XPATH = "//div[@id='all-reports']//button[@title='Search']/i";
  
  
  /**
   * 'VIEW REPORTS' tab frame
   */
  public static final String JRSCREATEREPORTPAGE_VIEWREPORTIFRAME_XPATH = "//iframe[@id='view-frame']";
  /**
   * 'RUN REPORTS' tab frame
   */
  public static final String JRSCREATEREPORTPAGE_RUNREPORTIFRAME_XPATH = "//iframe[@id='run-report-frame']";
  /**
   * XPATH for Search textbox in Myreport
   */
  public static final String JRSMYREPORTPAGE_SEARCHTXT_XPATH = "//div[@id='mine-reports']//input[@placeholder='Search']";
  /**
   * XPATH for Search icon in Myreport
   */
  public static final String JRSMYREPORTPAGE_SEARCHICON_XPATH = "//div[@id='mine-reports']//button[@title='Search']/i";

  /**
   * Pagination text in 'RUN REPORT' tab of build report
   */
  public static final String JRSCREATEREPORTPAGE_PAGEINATION_XPATH = "//div[@id='pagination-bottom']//div[@class='pagination-page-range']";
  
  /**
   * Report Name in JRS All Reports
   */
  public static final String JRSALLREPORTPAGE_REPORTNAME_XPATH = "//a[normalize-space()='DYNAMIC_VAlUE']";
  
  /**
   * All reports show filter explorer button in All reports
   */
  public static final String JRSALLREPORTPAGE_SHOWFILTER_XPATH = "//button[@id='all-reports-show-filter-explorer']";
  
  
  /**
   * Show My Choices right sidebar in build report
   */
  public static final String JRSALLREPORTPAGE_SHOWMYCHOICES_XPATH = "//label[contains(text(),'Show My Choices')]/parent::button";
  
  /**
   * 'Select User' dialog displayed after click on 'Add owner' button
   */
  public static final String JRSALLREPORTPAGE_SELECT_USER_DIALOG_XPATH = "//div[@role='dialog' and @aria-label='Select users']";
  
  /**
   * Search box in 'Select User' dialog
   */
  public static final String JRSALLREPORTPAGE_SEARCHBOX_ADDUSER_DIALOG_XPATH = "//input[@id='searchBox-spp-id']";
  
  
  /**
   * 'Create' button at the right top of report page
   */
  public static final String JRSALLREPORTPAGE_CREATE_BUTTON_XPATH = "//button[@id='createBtn']";
  
  /**
   * XPATH for Folder 
   */
  public static final String JRSBUILDNEWREPORTPAGE_FOLDER_XPATH =
      "//a[@aria-label='DYNAMIC_VAlUE'][contains(text(),'DYNAMIC_VAlUE')]";
  
  /**
   * Xpath for More option in report in JRS.
   */
  public static final String JRSBUILDNEWREPORTPAGE_REPORTMOREACTION_XPATH =
      "//div[contains(@data-testid,'row-action-container-background')]/button[@aria-label='More actions']";
  
  /**
   * Xpath for More option in report in JRS.
   */
  public static final String JRSBUILDNEWREPORTPAGE_REPORTDELETE_XPATH = "//div[@title='Delete']";
  
  /**
   * Select view as My reports or All reports in report page
   */
  public static final String JRSALLREPORTPAGE_SELECT_VIEW_AS_XPATH = "//div[contains(@class,'box--expanded')]//descendant::span[text()='DYNAMIC_VAlUE ']";
  
  /**
   * Select view as My reports or All reports in report page
   */
  public static final String JRSALLREPORTPAGE_REPORT_LIST_VIEW_XPATH = "//span[contains(@class,'list-box__label')]//span[2]";
  /**
   * Toolbar search current folder/all folder in report page
   */
  public static final String JRSALLREPORTPAGE_TOOLBAR_SEARCHFOLDER_XPATH = "//input[contains(@id,'toolbar-search')]";
  
  /**
   * Edit report button
   */
  public static final String JRSALLREPORTPAGE_EDIT_BUTTON_FOLLOWING_REPORTNAME_XPATH = 
      "//a[text()='DYNAMIC_VAlUE']//ancestor::td[@data-column='title']//following-sibling::td[contains(@class,'iot--row-actions')]//descendant::button[contains(@data-testid,'edit')]";
  /**
   * Filter report button
   */
  public static final String JRSALLREPORTPAGE_FILTER_BUTTON_XPATH = 
      "//button[@data-testid='filter-button']";
  /**
   * Textbox Name in filter bar - report page
   */
  public static final String JRSALLREPORTPAGE_TEXTBOX_NAME_FILTERBAR_XPATH = "//input[@id='title']";
  /**
   * Edit button following the report name - report page
   */
  public static final String JRSALLREPORTPAGE_EDITBUTTON_FOLLOW_REPORTNAME_XPATH = "//a[text()='DYNAMIC_VAlUE']//ancestor::tr//td[contains(@class,'row-action')]//button[contains(@data-testid,'edit')]";
  /**
   * Report name in Editor mode
   */
  public static final String JRSALLREPORTPAGE_REPORTNAME_IN_EDITOR_XPATH = "//label[contains(text(),'Report name')]/following-sibling::div/input[@title='Report Name']";
  /**
   * Tags in Editor mode
   */
  public static final String JRSALLREPORTPAGE_TAGS_IN_EDITOR_XPATH = "//select[@title='Tags']/following-sibling::span[@class='badge tag']/label";
  /**
   * Visualization in Editor mode
   */
  public static final String JRSALLREPORTPAGE_VISUALIZATION_IN_EDITOR_XPATH = "//select[@id='default-viz-select']";
  /**
   *Owners list in Editor mode
   */
  public static final String JRSALLREPORTPAGE_OWNERS_LIST_IN_EDITOR_XPATH = "//div[@id='report-owners']//descendant::label";
  /**
   * edit button next to report name in Editor mode
   */
  public static final String JRSALLREPORTPAGE_EDITBUTTON_IN_EDITOR_XPATH = "//td[contains(@class,'row-action')]//button[contains(@data-testid,'edit')]";
  
}
