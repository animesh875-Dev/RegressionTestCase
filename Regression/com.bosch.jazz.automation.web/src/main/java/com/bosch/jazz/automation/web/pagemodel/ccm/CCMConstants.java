/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

/**
 * Constants for model class CCMPage.
 */
public class CCMConstants {

  private CCMConstants() {

  }

  /**
   *
   */
  public static final String ATTRIBUTE_NOT_FOUND_IN_THE_WORKITEM_EDITOR_OR_NOT_A_TYPE_TEXTBOX =
      " attribute not found in the workitem editor or not a type textbox.";
  /**
   *
   */
  public static final String CORRECTION = "Correction";
  /**
   *
   */
  public static final String ESTIMATE = "Estimate";
  /**
   *
   */

  public static final String DIV_CONTAINS_CLASS_COM_IBM_TEAM_APT_WEB_UI_INTERNAL_PARTS_DURATION_WIDGET =
      ".//div[contains(@class, 'com-ibm-team-apt-web-ui-internal-parts-DurationWidget')]";
  /**
   * Additional Information in TEXT,Version,Part Number,Planned Estimate(hrs)).
   */
  public static final String CCMWORKITEMEDITORPAGE_ATTRIBUTES_TEXTAREA_XPATH = "//*[@class='ViewBorder']";
  /**
   *
   */
  public static final String DISABLED = "disabled";
  /**
   *
   */
  public static final String SELECT = ".//select";
  /**
   *
   */
  public static final String INPUT = ".//input";
  /**
   *
   */
  public static final String TIME_SPENT = "Time Spent";
  /**
   *
   */
  public static final String CONSTRAINT_DATE = "Constraint Date";


  /**
   *
   */
  public static final String PRECEDING_SIBLING_TH_LABEL = "../../../preceding-sibling::th//label";
  /**
   *
   */
  public static final String READONLY = "readonly";

  /**
   *
   */
  static final String ARIA_LABEL = "aria-label";
  /**
   *
   */
  public static final String DYNAMIC_READ_ONLY = "DynamicReadOnly";
  /**
   *
   */
  public static final String WORKITEM_ID = "WORKITEM_ID";
  /**
   * The name of the parameter that shall hold the work item type.
   */
  public static final String WORK_ITEM_TYPE = "WORK_ITEM_TYPE";
  /**
   * The name of the parameter that shall hold the attribute name.
   */
  public static final String CLASS = "class";

  /**
   * search for the work item menu 'Project Dashboards'.
   */
  public static final String CMMPROJECTAREAPAGE_WORKITEMMENU_TAB_XPATH = "//span[text()='Project Dashboards']";
  /**
   * click on the link displayed in notification area.
   */

  public static final String CCMWORKITEMPAGE_NOTIFICATIONAREA_LINK_XPATH =
      "//div[@class= 'NotificationView validationMessageError headerValidationMessage']";

  /**
   * Search for the availability of OK button in the page.
   */
  public static final String CCMCREATEPLANPAGE_OK_BUTTON_XPATH = "//button[text() = 'OK']";
  /**
   * A tab with a text specified value DYNAMIC_VAlUE, possible value:TAB NAME.
   */
  public static final String CCMCREATEQUERYPAGE_SELECT_TAB_XPATH = "//div[text()='DYNAMIC_VAlUE']";
  /**
   * A row with a text specified value DYNAMIC_VAlUE, possible value:Recently created.
   */
  public static final String JAZZPAGE_PARENT_LINKS_XPATH = "//a[text()='DYNAMIC_VAlUE']/parent::td";
  /**
   * A button with a text specified value DYNAMIC_VAlUE, possible value:Recently created.
   */
  public static final String JAZZPAGE_LINKS_XPATH = "//a[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String JAZZPAGE_LINKS_XPATH1 = "//div[@class='title']/span[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String JAZZPAGE_LINKS_XPATH2 =
      "//div[@class='title']/span[text()='DYNAMIC_VAlUE']/ancestor::tr[1]//a[@title=\"Create Work Item\"]";
  /**
   * A button with a text specified value DYNAMIC_VAlUE, possible value:Recently created.
   */
  public static final String JAZZADMIN_SPANSELECTION_XPATH = "//span[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * A button with a text specified value DYNAMIC_VAlUE, possible value:Recently created.
   */
  public static final String CCMCREATEQUERYPAGE_INLINE_ATTRIBUTE_XPATH =
      "//span[contains(text(),'DYNAMIC_VAlUE')]/../../../following-sibling :: td/div/div[2]";
  /**
   * A button with a text specified value DYNAMIC_VAlUE, possible value:Recently created.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_CHOOSEATTRIBUTE_DROPDOWN_XPATH =
      "//div[@id='drop-down-attribute']/button[@data-toggle='dropdown']";
  /**
   * A button with a text specified value DYNAMIC_VAlUE, possible value:Recently created.
   */
  public static final String CONTENTEDITABLE = "contenteditable";
  /**
   * A button with a text specified value DYNAMIC_VAlUE, possible value:Recently created.
   */
  public static final String FALSE = "false";
  /**
   * A button with a text specified value DYNAMIC_VAlUE, possible value:Recently created.
   */
  public static final String CCMCREATEPLANPAGE_SELECT_PLANS_XPATH = "//span[text()='DYNAMIC_VAlUE']";
  /**
   * A button with a text specified value DYNAMIC_VAlUE, possible value:Recently created.
   */
  public static final String CCMCREATEQUERYPAGE_PLANPHASE_TEXTBOX_XPATH = "//input[@aria-label='Filter Items']";

  // Application Administration Page
  /**
   * A variable in CCM apllication administration page which takes value to perform a specific operation.
   */
  public static final String PROCESS_DESCRIPTION = "Process Description";
  /**
   * Tab names in application administrationpage
   */
  public static final String CCMAPPLICATIONDMINISTRATIONPAGE_TAB_LINK_XPATH =
      "//div[contains(@class , 'tab') and @title = 'DYNAMIC_VAlUE']/a";
  /**
   * link name in application administrationpage.
   */
  public static final String CCMAPPLICATIONDMINISTRATION_PROJECTAREA_LINK_XPATH =
      "//a[contains(@class , 'j-action-secondary') and text()='Active Project Areas']";
  /**
   * This x-path shows list of practices in the process description tab.
   */
  public static final String CCMAPPLICATIONADMINISTRATIONPAGE_PROCESSDESCRIPTIONPRACTICES_TEXT_XPATH =
      "//span[@class='dijitTreeLabel']";

  /**
   * List of the widgets present on the dashboard.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_WIDGETS_TABLE_XPATH = "//div[@role='heading']";
  /**
   * dashboard page home button drop down.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_HOMEBUTTON_DROPDOWN_XPATH = "//a[@title='Home Menu']";


  // CCMProjectAreaPage

  /**
   * open the mainmenu using href value.
   */
  public static final String CCMPROJECTAREAPAGE_MENU_LINK_XPATH = "//a[@href = 'DYNAMIC_VAlUE']";
  /**
   * open the submenu
   */
  public static final String CCMPROJECTAREAPAGE_SUBMENU_LINK_XPATH =
      "//span[contains(@id , 'jazz_ui_menu_MenuItem_') and text() = 'DYNAMIC_VAlUE']";
  /**
   * list of workitems under the workitems menu.
   */
  public static final String CCMPROJECTAREAPAGE_WORKITEMSELECT_MENUITEMS_XPATH =
      "//div[starts-with(text() , 'Work Item Types: ')]/following-sibling::div//select";
  /**
   * list of workitems under the workitems menu
   */
  public static final String CCMPROJECTAREAPAGE_WORKITEMSELECT_MENUITEMLISTS_XPATH =
      "//div[starts-with(text() , 'Work Item Types: ')]/following-sibling::div//select/option";
  /**
   * Select Type... present under the Work Items menu, which contains a list box with all work items.
   */
  public static final String CCMPROJECTAREAPAGE_WORKITEMSELECTTYPE_LINK_XPATH =
      "//span[starts-with(text(),'Select Type...')]";
  /**
   * A link present under the Work Items menu,click on create query.
   */
  public static final String CCMPROJECTAREAPAGE_CREATEQUARY_LINK_XPATH = "//span[text()='Create Query']";
  /**
   * A link present under the Work Items menu,click on shared queries.
   */
  public static final String CCMPROJECTAREAPAGE_SHAREDQUARY_LINK_XPATH = "//span[text()='Shared Queries']";
  /**
   * Click operation on workitem button.
   */
  public static final String CCMPROJECTAREAPAGE_CREATEWORKITEM_BUTTON_XPATH = "//button[text() = 'Create Work Item']";
  /**
   * Search textbox for searching workitems.
   */
  public static final String CCMPROJECTAREAPAGE_QUICKSEARCH_SEARCHTEXTBOX_XPATH =
      "//input[@class='SearchInputText' and @title='Search']";
  /**
   * Quick search results text.
   */
  public static final String CCMPROJECTAREAPAGE_QUICKSEARCH_RESULTSTEXT_XPATH =
      "//div[@class='search-result']/div[2]/a/span";

  // CCMProjectAreaDashboardPage

  /**
   * Search text box in dashboard page.
   */

  public static final String CCMPROJECTAREADASHBOARDPAGE_SEARCH_SEARCHTEXTBOX_XPATH =
      "//input[@class='SearchInputText']";
  /**
   * 'Project Dashboards' dropdown in dashboard page.
   */

  public static final String CCMPROJECTAREADASHBOARDPAGE_PROJECTDASHBOARD_DROPDOWN_XPATH =
      "//span[text()='Project Dashboards']";
  /**
   * project area names.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_PROJECTDASHBOARD_PROJECTAREA_XPATH =
      "//span[@data-dojo-attach-point='containerNode']";
  /**
   * This xpath for all personal dashboards.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_HOMEBUTTON_LISTDASHBOARD_LINK_XPATH =
      "//span[text()='All Personal Dashboards']";
  /**
   * persona dashboard list in dashboard page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_PERSONALDASHBOARDLIST_XPATH =
      "//tbody[@class='table-primary']/tr";
  /**
   * names of the All personal dashboards on the page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_PERSONALDASHBOARDSLIST_LINK_XPATH =
      "//tbody[@class='table-primary']/tr/td[1]/a";
  /**
   * All Personal Dashboards in dashboard page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_PERSONALDASHBOARDSLIST_STEPBACKLINK_XPATH =
      "//a[text()='All Personal Dashboards']";
  /**
   * Add Widget button in dashboard page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_BUTTON_XPATH = "//span[text()='Add Widget']";
  /**
   * Click and opens the personal dashboard.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_PERSONALDASHBOARD_LINK_XPATH =
      "(//table[@class='dijit dijitMenu dijitMenuPassive dijitReset dijitMenuTable']/tbody[1]/tr[2]/td[1]/a/span[1])/span[1]";
  /**
   * Clicks on the Minidashboard.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_MINIDASHBOARD_LINK_XPATH =
      "//div[@dojoattachpoint='_innerWrapper']";
  /**
   * minidashboard in dashboard page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_MINIDASHBOARD_TITLE_XPATH = "//div[text()='Mini Dashboard']";
  /**
   * minidashboard iframe x-path
   */
  public static final String CCMMINIDASHBOARD_FRAME_IFRAME_XPATH =
      "//span[contains(text(),'Bulk Operations - Build')]/../../../../following-sibling ::div/div[3]/div/div/div[3]/div/iframe";
  /**
   * Category dropdown in the dashboard page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_DROPDOWN_XPATH =
      "//div[@class = 'jazz-ui-fat-caret']";
  /**
   * adding the widget in dashboard page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_DROPDOWN_SELECTCCM_XPATH =
      "//div[@class='planned-item-toolbar']//span[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * FilterBox for Search widget to by its name.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_SEARCHTEXTBOX_XPATH =
      "//div[@class='jazz-ui-FilterBox']/div/input";
  /**
   * searched widget to dashboard
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_ADDWIDGETBUTTON_XPATH =
      "//div[@class='options']//span[@class='add-widget-span']";
  /**
   *
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_ADDWIDGETBUTTONINDIALOGE_XPATH =
      "//div[@class='jazz-ui-Dialog modeless front is-visible']//div[@class='field']//span[text()='Add Widget']";
  /**
   * Add Widget constant for Buttons etc
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET = "Add Widget";
  /**
   * "save button" for save the dashboard page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_SAVEBUTTON_XPATH = "//input[@class='saveButton']";
  /**
   * Check if dashboard successfully saved or not
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_MESSAGEAREA_LABEL_XPATH = "//div[@class = 'messageSummary']";
  /**
   * addWidget image in minidashboard.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_MINIDASHBOARD_ADDWIDGET_XPATH = "//img[@alt='Add Widget']";
  /**
   * click Edit Multiple Work Items in create query page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_EDITMULTIPLEWI_MINIDASHBOARD_XPATH =
      "//img[@alt='Edit Multiple Work Items']";
  /**
   * Close button in add OpenSocial gadget.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_CLOSE_BUTTON_XPATH = "//input[@type='submit']";
  /**
   * Select the workitem button in dashboard page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_SELECTEDWI_BUTTON_XPATH =
      "//button[@title='Get Selected Work Item(s)']";
  /**
   * It contains a list box with all work items.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_ADDTEXT_BOX_XPATH =
      "//div[@id='panel-body-choose-attribute']/div/table/tbody/tr/td[3]/span/textarea";
  /**
   * Adding the hours in dashboard page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_ADDTEXTHOURS_BOX_XPATH =
      "//div[@id='panel-body-choose-attribute']/div/table/tbody/tr/td[3]/span/input";
  /**
   * click on update the attribute changes button.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_UPDATEATTRIBUTECHANGES_BUTTON_XPATH =
      "//button[@title='Add/Update Attribute Change']";
  /**
   * Change attribute button.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_ATTRIBUTECHANGES_BUTTON_XPATH =
      "//button[@title='Change attribute']";
  /**
   * 'Change Attribute Mode' button in dashboard page.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_DELIVERYATTRIBUTECHANGES_BUTTON_XPATH =
      "//button[@title='Change Attribute Mode']";
  /**
   * Select the paritcular WorkItem from the shared query.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_SELECTEDWI_CHECKBOX_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/../../../preceding-sibling :: td[3]/div/input";
  /**
   * Adding Social Gadget in MiniDashboard.
   */
  public static final String CCMPROJECTAREADASHBOARDPAGE_MINIDASHBOARD_ADDWIDGET_URLINPUTTEXT_XPATH =
      "//input[@dojoattachpoint='_urlInputText']";

  // WorkItem page Id's
  /**
   * Attribute name and values in workitem editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_ATTRIBUTES_LIST_XPATH = "//tr[@class='LabelValueTableRow']";
  /**
   * clicking on Select link type menu under Workitems menu.
   */
  public static final String CCMWORKITEMEDITORPAGE_LINK_TYPE_XPATH = "//a[text()='DYNAMIC_VAlUE']";

  /**
   * search all user name from dropdown
   */
  public static final String CCMWORKITEMEDITORPAGE_SEARCH_USER_XPATH = "//option";
  /**
   * Summary,Description,Additional Information in HTML,Comments,Snapshot | Baseline URI,Base Snapshot | Base Baseline
   * URI,Issued By,Analysis Result,External Id,External Participants,Stream URI,Mitigation Actions in work item editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_RICHTEXTEDITORWIDGET_TEXTBOX_XPATH = "//div[@role='textbox']";
  /**
   * List box is shown after clicking on Select Work item type menu under DropDown.
   */
  public static final String CCMWORKITEMEDITORPAGE_INLINEATTRIBUTEEDITOR_TEXTBOX_XPATH =
      "//span[contains(text(),'DYNAMIC_VAlUE')]/../../../following-sibling :: td/div/input";
  /**
   * Click on show progress check box in planview.
   */
  public static final String CCMWORKITEMEDITORPAGE_VALIDITY_CHECKBOX_XPATH = "//input[@type='checkbox']";
  /**
   * 'Internal Participants' attribute in wrokitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_ADDBUTTON_BUTTON_XPATH =
      "//div[contains(@id, 'com_ibm_team_rtc_foundation_web_ui_views_ArtifactListView')]";
  /**
   * Tags attribute in wrokitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_TAGSREADONLY_TEXTBOX_XPATH =
      "//div[contains(@id, 'com_ibm_team_rtc_foundation_web_ui_views_tags_TagsView')]";
  /**
   * List of all writable attributes in the work item editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_DROPDOWNREADONLY_LISTBOX_XPATH =
      "//div[starts-with(@id , 'com_ibm_team_workitem_web_mvvm_view_queryable_combo_QueryableComboView')]";
  /**
   * Set the constraint date.
   */
  public static final String CCMWORKITEMEDITORPAGE_CONSTRAINTDATE_LISTBOX_XPATH =
      "//input[@aria-label='Constraint Date']";
  /**
   * Due Date,Date of Receipt in workitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_DATE_CALENDER_XPATH = "//input[@class = 'dateInput ViewBorder']";

  /**
   * Account Numbers,Account Numbers of Receipt in work item editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_ACCOUNTNUMBER_TEXTFIELD_XPATH = "//textarea[@class = 'ViewBorder']";

  /**
   * this x-path shows the tab name in wrokitem editor.tab names like Overview, Links, Approvals, Misc, History, Time
   * Tracking and Validity.
   */
  public static final String CCMWORKITEMEDITORPAGE_TABS_LIST_XPATH = "//a[@class='tab' and @role='tab']";
  /**
   * 'Time Spent' attribute in workitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_TIMESPENT_TEXTFIELD_XPATH =
      "//div[@id='com_ibm_team_apt_web_ui_internal_parts_TimeSpentAttributePart_0']/div";
  /**
   * 'Duration' attribute in workitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_DURATION_TEXTFIELD_XPATH =
      "//div[contains(@id,'com_ibm_team_apt_web_ui_internal_parts_DurationAttributePart')]/div";
  /**
   * Estimate,Correction attributes in workitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_ESTIMATE_TEXTFIELD_XPATH =
      "//div[contains(@id,'com_ibm_team_apt_web_ui_internal_parts_EstimateAndCorrectionAttributePart')]/div";
  /**
   * History text filed.
   */
  public static final String CCMWORKITEMEDITORPAGE_HISTORY_TEXTFIELDS_XPATH =
      "//tr[@tabindex='0']/td[contains(text(),'DYNAMIC_VAlUE')]/following-sibling::td";
  /**
   * Residual Estimate (hrs) attribute in workitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_PLANNINGESTIMATEREADONLY_TEXTVIEW_XPATH =
      "//span[@class='LabelValue']/../../../preceding-sibling :: th//label";
  /**
   * Id of the workitem
   */
  public static final String CCMWORKITEMEDITORPAGE_WORKITEMID_TEXT_XPATH = "//span[@class = 'TitleText']";
  /**
   * Attribute dropdown button in workitem editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_ATTRIBUTEDROPDOWN_XPATH =
      "//tr[@class='LabelValueTableRow']/th/span/label[contains(text(),'DYNAMIC_VAlUE')]/../../following-sibling::td";
  /**
   * Constraint Date attribute in workitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_CONSTRAINTDATEREADONLY_LISTBOX_XPATH =
      "//div[contains(@id , 'com_ibm_team_apt_web_ui_internal_parts_ConstraintDatePart')]/span[2]";
  /**
   * Current status of the workitem
   */
  public static final String CCMWORKITEMEDITORPAGE_STATE_LISTBOX_XPATH = "//select[@aria-label = 'Status']";
  /**
   * Current resolution of the workitem.
   */
  public static final String CCMWORKITEMEDITORPAGE_RESOLUTION_LISTBOX_XPATH = "//select[@aria-label='Resolution']";
  /**
   * save button in work item editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_SAVE_BUTTON_XPATH = "//button[text()='Save']";

  /**
   * contact person text field in work item editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_CONTACTPERSON_FIELD_XPATH =
      "//span[text()='DYNAMIC_VAlUE']//ancestor::span";

  /**
   * contact person delete button in work item editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_DELETECONTACTPERSONE_BUTTON_XPATH =
      "//span[@title='Remove' or @title='Delete']";


  /**
   * This is the xpath for shows the mandatory description attribute name in workitem editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_MANDATORYSUMMARY_LABEL_XPATH =
      "(//span[@style = 'visibility: visible;' and text() = '* ']/../../..)/span[1]";
  /**
   * This is the xpath for shows the mandatory description attribute's in workitem editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_MANDATORYDESCRIPTION_LABEL_XPATH =
      "(//span[@style = 'visibility: visible;' and text() = '* ']/../../..)/span[2]";
  /**
   * This is the xpath for shows the mandatory attribute's in workitem editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_MANDATORY_LABEL_XPATH =
      "(//span[@style = 'visibility: visible;' and text() = '* ']/../..)//label";
  /**
   * List of all attributes in the work item editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_ATTRIBUTE_LABEL_XPATH = "//label";
  /**
   * set value in the Tags field.
   */
  public static final String CCMWORKITEMEDITORPAGE_TAGS_TEXTFIELD_XPATH =
      "//div[@id='com_ibm_team_rtc_foundation_web_ui_views_tags_TagsView_0']";
  /**
   * List of attribute value's in dropdown button.
   */
  public static final String CCMWORKITEMEDITORPAGE_OPTIONSLIST_LISTBOX_XPATH =
      "//div[@class = 'SelectOptions']/div[1]/ul/li";
  /**
   * List box is shown after clicking on Select Work item type menu under DropDown.
   */
  public static final String CCMWORKITEMEDITORPAGE_OPTIONSLIST_LISTBOX_TEXT_XPATH =
      "//div[@class = 'SelectOptions']/div[1]/ul/li/span[3]";
  /**
   * search text box in workitem editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_SEARCHINPUT_LISTBOX_XPATH =
      "//input[@dojoattachpoint='_searchInput']";
  /**
   * Filter text box in workitem editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_FILTER_TEXTBOX_XPATH =
      "//input[@placeholder='Type to filter list ...']";
  /**
   * Action button in shared queries.
   */
  public static final String CCMWORKITEMEDITORPAGE_ACTION_BUTTON_XPATH = "//img[@alt='Edit']";
  /**
   * validation for workitem saved successfully or not.
   */
  public static final String CCMWORKITEMEDITORPAGE_VALIDATIONMESSAGE_TEXTFIELD_XPATH =
      "//a[@dojoattachpoint = 'validationMessage']";

  /**
   * Notification View contains validation Message Error.
   */
  public static final String CCMWORKITEMEDITORPAGE_VALIDATIONMESSAGEFROMNOTIFICATIONAREA_TEXTFIELD_XPATH =
      "//div[@class=\"NotificationView validationMessageError headerValidationMessage\"]";

  /**
   * Check 'Detected In' attribute in workitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_DETECTEDIN_VIEWPICKER_XPATH =
      "//div[contains(@id, 'com_ibm_team_workitem_web_mvvm_view_WorkItemView')]";
  /**
   * Selected Dropdown Vlaue in workitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_DROPDOWNATTRIBUTE_SELECTEDTEXT_XPATH =
      "//div[@role='combobox' and contains(@aria-label,'DYNAMIC_VAlUE')]/span[2]";
  /**
   * Selected Right Side Attribute Value in workitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_RIGHTSIDEATTRIBUTE_SELECTEDTEXT_XPATH =
      "//td[@class='Column rightColumn']//label[contains(text(),'DYNAMIC_VAlUE')]/../../following-sibling :: td/div/div[2]";
  /**
   * Selected Left Side Attribute Value
   */
  public static final String CCMWORKITEMEDITORPAGE_LEFTSIDEATTRIBUTE_SELECTEDTEXT_XPATH =
      "//td[@class='Column leftColumn']//label[contains(text(),'DYNAMIC_VAlUE')]/../../following-sibling :: td/div/div[2]";
  /**
   * it's showing the readonly attribute selected text box.
   */
  public static final String CCMWORKITEMEDITORPAGE_READONLYATTRIBUTE_SELECTEDTEXT_XPATH =
      "//span" + "/label[contains(text(),'DYNAMIC_VAlUE')]/../../following-sibling :: td/div/div[2]";

  // Quick Information
  /**
   * Navigate to the parent Work Item by clicking on the 'Parent (1)' link in the QuickInformation section.
   */
  public static final String CCMWORKITEMEDITORPAGE_QUICKINFORMATION_PARENTLINK_XPATH =
      "//ul[li[@class='com-ibm-team-workitem-QuickInformationListItem']/a[text() = 'Parent (1): ']]/li[@class='com-ibm-team-workitem-QuickInformationListItemDetail']/a";
  /**
   * Dropdownarrow in workitem editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_LINKS_ARROW_XPATH =
      "//span[text() = 'DYNAMIC_VAlUE']/../../..//span[@class = 'DropdownArrow']";
  /**
   * Get Children/Contributes To/Parent/Related links for 'Process Links'.
   */
  public static final String CCMWORKITEMEDITORPAGE_LINKS_PROCESSLINKSORLINKS_XPATH =
      "//span[text() = 'DYNAMIC_VAlUE']/../../..//*//div";
  /**
   * List of links in workitem editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_LINKS_LINKSLISTLABEL_XPATH =
      "//span[text() = 'DYNAMIC_VAlUE0']/../../..//*/div[div[@aria-label = 'DYNAMIC_VAlUE1 links list Expanded']]/div[@class='ValueNode']/div/div/div/span[@class='LabelValue']/a";
  /**
   * search status for workitem editor.
   */
  public static final String CCMWORKITEMEDITORPAGE_LINKS_SEARCHSTATUS_XPATH =
      "//div[@class=\"innerWrapper keywordSearch\"]/div[@class=\"NormalStatus\"]";
  /**
   * List of links in workitem editor page. DYNAMIC_VAlUE0 is link section and DYNAMIC_VAlUE1 is links type
   */
  public static final String CCMWORKITEMEDITORPAGE_LINKS_DELETELINKS_XPATH =
      "//span[text() = 'DYNAMIC_VAlUE0']/../../../*//div[div[@aria-label = 'DYNAMIC_VAlUE1 list Expanded']]/div[@class='ValueNode']/div/span[@title='Delete']";
  /**
   * link to the work item editor in the links tab. like Related artifices, tested by test case.
   */
  public static final String CCMWORKITEMEDITORPAGE_FRAME_IFRAME_XPATH = "//iframe[@dojoattachpoint='iframe']";

  // NewQueryPage id's

  /**
   * search for the WorkItems link in work item page.
   */
  public static final String CCMCREATEQUERYPAGE_WORKITEMS_LINK_XPATH =
      "//a[contains(@class , 'j-action-secondary') or text()='Work Items']";

  /**
   * queryName to be entered in the text box.
   */
  public static final String CCMCREATEQUERYPAGE_NEWQUERYFIELD_TEXTBOX_XPATH = "//input[@class = 'nameInput']";
  /**
   * unassigned checkbox in create query page.
   */
  public static final String CCMCREATEQUERYPAGE_UNASSIGNED_CHECKBOX_XPATH =
      "//label[text()='Unassigned']/preceding-sibling :: input";
  /**
   * click on add condition button in createQuery.
   */
  public static final String CCMCREATEQUERYPAGE_ADDCONDITIONBUTTON_LINK_XPATH = "//span[text() = 'Add Condition']";
  /**
   * sets AND or OR conditions in query page.
   */
  public static final String CCMCREATEQUERYPAGE_ADDCONDITION_LINK_XPATH =
      "(//span[text() = 'DYNAMIC_VAlUE']/../../following-sibling::div//span[text() = 'Add Condition'])[2]";
  /**
   * give the header Count.
   */
  public static final String CCMCREATEQUERYPAGE_TABLEHEADERS_XPATH = "//table[@summary='Query Results']/thead/tr/th";
  /**
   * text value to be selected in list of workitems.
   */
  public static final String CCMCREATEQUERYPAGE_FILTERTEXT_TEXTBOX_XPATH =
      "//div[@id='com_ibm_team_workitem_web_ui_internal_view_query_QueryResultPageControl_0']//input[@type='text']";

  /**
   * select the one text value in listbox.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_TREEITEM_XPATH =
      "//span[@class='label label' and text() = 'DYNAMIC_VAlUE']";
  /**
   * add attribute condition button in create query.
   */
  public static final String CCMCREATEQUERYPAGE_ADDATTRIBUTECONDITION_BUTTON_XPATH =
      "//span[text() = 'Add attribute condition']";
  /**
   * Text box for declare the query value.
   */
  public static final String CCMCREATEQUERYPAGE_QUERYVALUE_TEXTBOX_XPATH = "//input[@class = 'text-control']";
  /**
   * filter text box for select the attribute.
   */
  public static final String CCMCREATEQUERYPAGE_SELECTATTRIBUTE_TEXTBOX_XPATH = "//input[@class='queryInput']";
  /**
   * Select the planphase attribute .
   */
  public static final String CCMCREATEQUERYPAGE_SELECTATTRIBUTEPLANPHASE_TEXTBOX_XPATH =
      "//input[@class='filter' and @type='search']";
  /**
   * Select the attribute in query page.
   */
  public static final String CCMCREATEQUERYPAGE_SELECT_ATTRIBUTE_XPATH = "//div[@class='result']/div/div[2]/a[1]";
  /**
   * Select the planphase attribute .
   */
  public static final String CCMCREATEQUERYPAGE_SELECTPLANPHASE_ATTRIBUTE_XPATH =
      "(//table[@class='contentTable c1_resize row'])[1]";
  /**
   * Selecting peritcular attribute column text.
   */
  public static final String CCMCREATEQUERYPAGE_SELECTATTRIBUTE_COLUMN_XPATH =
      "//tr[@class='visibleRow']/td[DYNAMIC_VAlUE]/span/span";
  /**
   * Click on query button.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_BUTTON_XPATH = "//button[text() = 'DYNAMIC_VAlUE']";
  /**
   * 'Click for more values' button for select attribute values in workitem editor page.
   */
  public static final String CCMCREATEQUERYPAGE_DROPDOWNMOREVALUE_BUTTON_XPATH =
      "//a[@title = 'Click for more values']";
  /**
   * Select the attribute value.
   */
  public static final String CCMCREATEQUERYPAGE_SELECT_TEXT_XPATH = "//select[@dojoattachpoint = 'userSelector']";
  /**
   * Select the attribute value.
   */
  public static final String CCMCREATEQUERYPAGE_SELECT_WITHVALUE_TEXT_XPATH =
      "//select[@dojoattachpoint = 'userSelector']//option[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * selecting query text in list of query's.
   */
  public static final String CCMCREATEQUERYPAGE_SELECTQUERY_BUTTON_XPATH = "//span[text() = 'DYNAMIC_VAlUE']";
  /**
   * click on addColumn... button.
   */
  public static final String CCMCREATEQUERYPAGE_ADDCOLUMNBUTTON_LINK_XPATH = "//a[text()='Add Column...']";
  /**
   * table header attribute's in create query page.
   */
  public static final String CCMCREATEQUERYPAGE_TABLE_HEADERS_XPATH = "//table[@summary='Query Results']/thead/tr/th";
  /**
   * plan phase table header attribute's in create query page.
   */
  public static final String CCMCREATEQUERYPAGE_PLANPHASETABLE_HEADERS_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/../../../../../../following-sibling :: div[1]/table/tbody/tr/td/div[2]/div/span";
  /**
   * Refresh button
   */
  public static final String CCMCREATEQUERYPAGE_INLINE_REFRESH_XPATH = "//img[@title='Refresh']";

  // Plans id

  /**
   * Plans link in plan page.
   */
  public static final String CCMPLANPAGE_PLANS_LINK_XPATH =
      "//a[contains(@title , 'Welcome to Plans') and text()='Plans']";

  /**
   * Click on browse button in createplan.
   */
  public static final String CCMCREATEPLANPAGE_BROWSE_BUTTON_XPATH =
      "//button[contains(@title,'DYNAMIC_VAlUE') and text() = 'Browse...']";
  /**
   * plan name text box in create plan page.
   */
  public static final String CCMCREATEPLANPAGE_PLANNAME_TEXTBOX_XPATH =
      "//input[contains(@class,'input com-ibm-team-apt-web-ui-internal-editor-InPlaceLabelEditor')]";

  /**
   * select the type of the plan in the plan editor.
   */
  public static final String CCMCREATEPLANPAGE_PLANTYPE_LISTBOX_XPATH = "//select[@aria-label = 'Plan Type:']";
  /**
   * list of team area names in createplan page.
   */
  public static final String CCMCREATEPLANPAGE_TREEBRANCH_LISTBOX_XPATH = "//div[contains(@class , 'treeBranch')]";
  /**
   * search box in createplan.
   */
  public static final String CCMCREATEPLANPAGE_SEARCHBOX_TEXTFEILD_XPATH =
      "//input[@aria-describedby='searchInstructions']";

  /**
   * Search text box in planpage.
   */
  public static final String CCMCREATEPLANPAGE_QUICKSEARCH_TEXT_XPATH = "//input[@dojoattachpoint='_searchText']";
  /**
   * QuickSearch button in planpage.
   */
  public static final String CCMCREATEPLANPAGE_QUICKSEARCH_BUTTON_XPATH = "//div[@class='MenuTwistie']";
  /**
   * click on attribute button in plan page.
   */
  public static final String CCMCREATEPLANPAGE_ATTRIBUTE_BUTTON_XPATH = "//td[@title='DYNAMIC_VAlUE']";
  /**
   * A button with a text specified value DYNAMIC_VAlUE, possible value:Recently created.
   */
  public static final String CCMCREATEPLANPAGE_PLANEPHASEATTRIBUTE_TEXT_XPATH =
      "//div[@class='jazz-ui-Menu-border']/div/table/tbody/tr";
  /**
   * plan view column text box.
   */
  public static final String CCMCREATEPLANPAGE_PLANEVIEWCOLUMNDISPLAY_TEXT_XPATH =
      "//table[@class='contentTable c1_resize row']/tbody/tr/td/div/span";
  /**
   * Clicking on Edit plan View in Selected Plan Phase
   */
  public static final String CCMCREATEPLANPAGE_EDIT_PLANVIEW_XPATH = "//span[@title='Edit Plan View']";
  /**
   * Adding the Attribute in ColumunDisplay.
   */
  public static final String CCMCREATEPLANPAGE_ATTRIBUTELIST_COLUMNDISPALY_XPATH =
      "//div[@class='gadget configuration']/span";
  /**
   * Create workitem button in plan page.
   */
  public static final String CCMCREATEPLANPAGE_CREATEWI_BUTTON_XPATH =
      "//span[@title='Create Work Item']/../following-sibling :: a";
  /**
   * Clickon sub button(like edit , create, refresh, plan for, remove).
   */
  public static final String CCMCREATEPLANPAGE_CREATEWI_SUBBUTTONS_XPATH = "//td[text()='DYNAMIC_VAlUE']";

  /**
   *
   */
  public static final String CCMCREATEPLANPAGE_CREATEWI_LEFTACTIONSUBBUTTONS_XPATH =
      "//td[text()='DYNAMIC_VAlUE' and @colspan='2' ]";

  /**
   * it's select the team area depend on aria-level number.
   */
  public static final String CCMCREATEPLANPAGE_SELECTNODE_TREEITEM_XPATH =
      "//div[@aria-label = 'DYNAMIC_VAlUE' and @aria-level='1']";
  /**
   * it's select the team area depend on aria-level number.
   */
  public static final String CCMCREATEPLANPAGE_SELECTNODENONSELECTABLE_TREEITEM_XPATH =
      "//span[text() = 'DYNAMIC_VAlUE']/../../..//div[@aria-level='1']";
  /**
   * Presses on 'Save' button to create a plan.
   */
  public static final String CCMCREATEPLANPAGE_SAVE_BUTTON_XPATH =
      "(//button[text() = 'Save']/../../..)/div//button[text() = 'Save']";
  /**
   * Error message shown on the plan editor.
   */
  public static final String CCMCREATEPLANPAGE_ERRORMESSAGE_SUMMARY_LABEL_SELECTOR = "//div[@class = 'messageSummary']";
  /**
   * Option in 'Add work item' dropdown xpath
   */
  public static final String CCMPLANPAGE_ADDWORKITEM_DROPDOWN_XPATH =
      "//tr[@role='menuitem']//td[text()='DYNAMIC_VAlUE']";
  /**
   * List of all plans in menu
   */
  public static final String CCMCREATEPLANPAGE_ALLPLANS_TEXT_XPATH =
      "//div[text()='Browse']/../../following-sibling :: tr/td/a/span/span";
  /**
   * Add approver button xpath
   */
  public static final String CCMCREATEWORKITEM_A_BUTTON_XPATH = "//a[@role='button' and text()='DYNAMIC_VAlUE']";
  /**
   * Plan types in plan page.
   */
  public static final String CCMCREATEPLANPAGE_PLANS_ATTRIBUTE_XPATH = "//a[@title='DYNAMIC_VAlUE']";
  /**
   * Retriving the all plans from the particular plan.
   */
  public static final String CCMCREATEPLANPAGE_SELECTEDPLANS_TEXT_XPATH = "//div[@class='plan']/a";
  /**
   * Seach box in Plan page
   */
  public static final String CCMCREATEPLANPAGE_SEARCHPLANS_TEXTBOX_XPATH = "//input[@name='filter-box']";

  /**
   * Selecting the Plan Title attribute.
   */
  public static final String CCMCREATEPLANPAGE_SELECTEDPLANS_TITLE_XPATH = "//a[@class='tab selected']";
  /**
   * get Plan Name in all plans list.
   */
  public static final String CCMCREATEPLANPAGE_PLANS_TABTEXT_XPATH = "//div[@class='tab-area']/div";
  /**
   * progress bar status.
   */
  public static final String CCMCREATEPLANPAGE_BARCHART_STATUS_XPATH =
      "//div[@dojoattachpoint='_progressBar']/following-sibling :: div[1]";
  /**
   * This x-path for Attriubte of progress bar in select plan.
   */
  public static final String CCMCREATEPLANPAGE_PLANS_PROGRESSBAR_XPATH =
      "//a[text()='DYNAMIC_VAlUE']/../../../following-sibling :: td[3]/div[2]";
  /**
   * Textbox for selecting the proper expession.
   */
  public static final String CCMCREATEPLANPAGE_SELECTION_TEXTBOX_XPATH =
      "//div[contains(@class,'expression')]/div/div/input";
  /**
   * Selecting the Exculding options in plan.
   */
  public static final String CCMCREATEPLANPAGE_EXCLUDEPLANS_SELECTION_XPATH =
      "(//table[contains(@class,'editor-config-ConfigurationElementRow row configuration')])[2]/tbody/tr/td/div/div/div/select";

  // SourceControl id
  /**
   * gets select the artifacts type and search artifact.(note )
   */
  public static final String CCMSOURCECONTROLPAGE_ADVANCEDSEARCH_SEARCHARTIFACT_XPATH =
      "//div[@class='SectionContent']/span/label[text() = 'DYNAMIC_VAlUE']/preceding-sibling::input";
  /**
   * Enters the given artifact prefix and then clicks on the search button.
   */
  public static final String CCMSOURCECONTROLPAGE_ADVANCEDSEARCH_ENTERTEXTINSEARCHBOX_XPATH =
      "//input[@class='textInput']";
  /**
   * Search button in SourceControl page .
   */
  public static final String CCMSOURCECONTROLPAGE_ADVANCEDSEARCH_CLICKTHESEARCHBUTTON_XPATH =
      "//button[text()='Search']";
  /**
   * list of artifacts shown on the page after advance search.
   */
  public static final String CCMSOURCECONTROLPAGE_ADVANCEDSEARCH_LISTOFARTIFACTS_XPATH =
      "//div[@class='resultArea']/table/tbody/tr/td[1]/a";

  // Builds
  /**
   * workiem menu builds in ccm build defination page.
   */
  public static final String CCMBUILDDEFINITION_WORKITEMMENU_BUILDS_XPATH = "//span[text()='Builds']";

  // NewReportPage id

  /**
   * Reports link in Reports page.
   */
  public static final String CCMREPORSPAGE_REPORTS_LINK_XPATH =
      "//a[contains(@class , 'j-action-secondary') and text()='Reports']";
  /**
   * Sets the description in the report.
   */

  public static final String CCMCREATEREPORTFROMRESOURCEPAGE_REPORTDESCRIPTION_TEXTBOX_XPATH =
      "//textarea[@id='descriptionText']";
  /**
   * Sets the name of the report
   */
  public static final String CCMCREATEREPORTFROMRESOURCEPAGE_REPORTNAME_TEXTBOX_XPATH = "//input[@class='nameInput']";
  /**
   * Select the folder in report page.
   */
  public static final String CCMCREATEREPORTFROMRESOURCEPAGE_FOLDER_LISTBOX_XPATH =
      "//div[@aria-label='Folder']//div[text() = 'DYNAMIC_VAlUE']";
  /**
   * validation message for saving the report.
   */
  public static final String CCMCREATEREPORTFROMRESOURCEPAGE_VALIDATIONMESSAGE_TEXTFIELD_XPATH =
      "//div[@class='messageArea ERROR']/div[2]";
  /**
   * select the proper Resource in report.
   */
  public static final String CCMCREATEREPORTFROMRESOURCEPAGE_REPORTRESOURCES_LISTBOX_XPATH =
      "//div[text() = 'Resource: ']/following-sibling::div//div[text() ='DYNAMIC_VAlUE']";
  /**
   * save button in create report page.
   */
  public static final String CCMCREATEREPORTFROMRESOURCEPAGE_REPORTSAVE_BUTTON_XPATH = "//button[text() = 'Save']";
  /**
   * Search input test box for work item
   */
  public static final String CCMWORKITEMPAGE_SEARCHINPUTTEXT_TEXTAREA_XPATH = "//input[@class='SearchInputText']";

  /**
   * Click search button in dashboard page
   */
  public static final String CCMWORKITEMPAGE_SEARCHBUTTON_BUTTON_XPATH =
      "//a[@class='SearchButton SearchButtonEnabled']";

  /**
   * Click search button in dashboard page
   */
  public static final String CCMWORKITEMPAGE_DUEDATE_INPUT_XPATH =
      "//span[@class='SectionMenuActionLabel SectionMenuAction_Enabled' and text()='DYNAMIC_VAlUE']/ancestor::tr[@class='SectionListTableRow']/descendant::input[@class='dateInput ViewBorder']";
  /**
   * search approvar in dashboard page
   */
  public static final String CCMWORKITEMPAGE_APROVARUSER_INPUT_XPATH =
      "//span[@class='SectionMenuActionLabel SectionMenuAction_Enabled' and text()='DYNAMIC_VAlUE']/ancestor::tr/following-sibling::tr[@class='SectionListTableRow']/descendant::div[@class='Value'][2]";
  /**
   * click on the condition type as per the values ex: AND or OR.
   */
  public static final String CCMWORKITEMPAGE_CONDITION_TYPE_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/following-sibling::span//a[@role='menuitem']";
  /**
   * Twistle button use to click on button.
   */
  public static final String CCMCREATECREATEQUERYPAGE_TWISTLE_BUTTON_XPATH =
      "//div[@class='com-ibm-team-workitem-queryeditor-tree treeContainer']//a[@class='twistie']";
  /**
   * click on description text box.
   */
  public static final String CCMCREATECREATEQUERYPAGE_DESCRIPTION_TEXTBOX_XPATH = "//textarea[@class='textArea']";
  /**
   * click on description text box.
   */
  public static final String CCMCREATECREATEQUERYPAGE_SHOWLINKS_CHECKBOX_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/preceding-sibling::input[1]";
  /**
   * Expand Team Area or Project Area
   */
  public static final String CCMCREATECREATEQUERYPAGE_TEAMAREA_EXPANDBUTTON_XPATH =
      "//div[@role='tree']/div/div/img[@data-dojo-attach-point='expandoNode']";
  /**
   * Constant string variable to hold text "Select Attributes".
   */
  public static final String SELECT_ATTRIBUTES = "Select Attributes";

  /**
   * Identify team area and user.
   */
  public static final String CCMQUERYPAGE_TEAMAREA_USER_XPATH = "//td[text()='DYNAMIC_VAlUE']";
  /**
   * Remove team area and user xpath
   */
  public static final String CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH = "//div[@title='DYNAMIC_VAlUE']";
  /**
   * XPath for Dialog title
   */
  public static final String DIALOG_TITLE_XPATH = "//div[@title='DYNAMIC_VAlUE']";
  /**
   * XPath for Context menus displayed
   */
  public static final String CONTEXT_MENUS_XPATH =
      "//div[contains(@class,'jazz-ui-MenuPopup') and not(contains(@style,'display: none;'))]";
  /**
   * XPath for: New row in Plan page
   */
  public static final String CCMPLANPAGE_NEWROW_TEXTBOX_XPATH =
      "//table[contains(@class,'item unsaved')]//td[contains(@class,'DYNAMIC_VAlUE0')]//div[@class='outliner']//a[text()='DYNAMIC_VAlUE1']";
  /**
   * XPath for: New row in Plan page
   */
  public static final String CCMPLANPAGE_NEWROW_XPATH = "//table[contains(@class,'item unsaved')]";
  /**
   * Remove team area and user xpath
   */
  public static final String CCMQUERYPAGE_COLUMN_ORDER_XPATH =
      "//div[text()='DYNAMIC_VAlUE0']/../..//td[text()='DYNAMIC_VAlUE1']";
  /**
   * Remove team area and user xpath
   */
  public static final String CCMQUERYPAGE_COLUMN_BUTTON_XPATH =
      "//div[@title='DYNAMIC_VAlUE0']//div[@class='actionArea imageLinks']//a[text()='DYNAMIC_VAlUE1']";
  /**
   * Remove team area and user xpath
   */
  public static final String CCMQUERYPAGE_SORTORDER_DROPDOWN_XPATH =
      "//div[text()='Sort Order']/../..//td[text()='DYNAMIC_VAlUE']/..//select";
  /**
   * click on the condition type as per the values ex: AND or OR.
   */
  public static final String CCMWORKITEMPAGE_CONDITION_BY_INDEX_TYPE_XPATH =
      "(//span[text()='DYNAMIC_VAlUE0'])[DYNAMIC_VAlUE1]/following-sibling::span//a[@role='menuitem']";
  /**
   * click on the condition type as per the values ex: AND or OR.
   */
  public static final String CCMWORKITEMPAGE_CONDITION_TEXT_BOX_XPATH =
      "//input[@class='text-control validation-failed']";
  /**
   * click on the condition type as per the values ex: AND or OR.
   */
  public static final String CCMCREATEQUERYPAGE_SELECT_DROPDOWN_XPATH =
      "//a[@class='tab selected' and @title='Shared Queries']";
  /**
   * Constant string variable to hold text linkActions.
   */
  public static final String LINKACTIONS = "linkActions";
  /**
   * Constant string variable to hold text value.
   */
  public static final String VALUE = "value";
  /**
   * Constant string variable to hold text Select Work Items.
   */
  public static final String SELECTWORKITEMS = "Select Work Items";
  /**
   * Constant string variable to hold text Select Work Items.
   */
  public static final String SELECTTESTRESULT = "Select Test Result";
  /**
   * Constant string variable to hold text Work Items Search.
   */
  public static final String WORKITEMS_SEARCH =
      "Work Item Number or Words Contained in the Text. Use quotes for a phrase search:";
  /**
   * Constant string variable to hold text Matching Work Items.
   */
  public static final String MATCHING_WORKITEMS = "Matching Work Items:";
  /**
   * Constant string variable to hold text Add Link.
   */
  public static final String ADD_LINK = "Add Link:";
  /**
   * Constant string variable to hold text Search.
   */
  public static final String SEARCH = "Search...";
  /**
   * Constant string variable to hold text rqmProjectArea.
   */
  public static final String RQM_PROJECT_AREA = "rqmProjectArea";

  /**
   * Constant string variable to hold text component Name.
   */
  public static final String RM_COMPONENT_NAME = "componentName";
  /**
   * Constant string variable to hold text rmProjectArea.
   */
  public static final String RM_PROJECT_AREA = "rmProjectArea";
  /**
   * Constant string variable to hold text tracksRequirementName.
   */
  public static final String TRAKSREQUIREMENT_NAME = "tracksRequirementName";

  /**
   * Constant string variable to hold text RequirementName
   */
  public static final String REQUIREMENT_NAME = "RequirementName";

  /**
   * Constant string variable to hold text DuplicateOfName
   */
  public static final String DUPLICATE_OF_NAME = "DuplicateOfName";

  /**
   * Constant string variable to hold text Label of Project area.
   */
  public static final String SELECT_PROJECTAREA_LABEL =
      "Select either the location of the existing artifact you want to link to or the location of the new artifact you want to create";
  /**
   * Constant string variable to hold text Clear all filters.
   */
  public static final String CLEAR_ALL_FILTERS = "Clear all filters";
  /**
   * Constant string variable to hold text testCaseId.
   */
  public static final String TESTCASE_ID = "testCaseId";
  /**
   * Constant string variable to hold text Filter.
   */
  public static final String FILTER = "Filter";
  /**
   * Constant string variable to hold text testCaseName
   */
  public static final String TESTCASE_NAME = "testCaseName";
  /**
   * Xpath for the new test case frame.
   */
  public static final String NEW_TESTCASE_FRAME_XPATH = "//iframe[@dojoattachpoint='iframe']";
  /**
   * Xpath for the label of the test case.
   */
  public static final String TESTCASE_LABEL_XPATH = "//label[text()='DYNAMIC_VAlUE']";
  /**
   * Constant string variable to hold text testCaseDomainMoreLinkValue.
   */
  public static final String TESTCASE_DOMAIN_MORE_LINK_VALUE = "testCaseDomainMoreLinkValue";
  /**
   * Constant string variable to hold text testPlanName.
   */
  public static final String TESTPLAN_NAME = "testPlanName";
  /**
   * Constant string variable to hold text testPlanReleaseMoreLinkValue.
   */
  public static final String TESTPLAN_RELEASE_MORE_LINK_VALUE = "testPlanReleaseMoreLinkValue";
  /**
   * Constant string variable to hold text linkId.
   */
  public static final String LINK_ID = "linkId";
  /**
   * Xpath for test case label mandatory field.
   */
  public static final String TESTCASE_LABEL_MANDATORY_FIELD_XPATH =
      "//label[text()='Release:']/following-sibling::span[text()='*']";
  /**
   * Xpath for test case label mandatory field.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_TITLE_XPATH = "//span[contains(@title,'DYNAMIC_VAlUE')]";
  /**
   * Xpath for test case label mandatory field.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_SECTIONDETAILS_XPATH =
      "//div[@title='DYNAMIC_VAlUE']//div[@class='sectionContent']";
  /**
   * Xpath for test case label mandatory field.
   */
  public static final String CCMCREATEQUERYPAGE_CCMPAGE_DROPDOWN_XPATH =
      "//div[@class='navbar-wrapper']//a[contains(@aria-label,'DYNAMIC_VAlUE')]";

  /**
   * Xpath for test case label mandatory field.
   */
  public static final String CCMCREATEQUERYPAGE_DELETEQUERY_DROPDOWN_XPATH =
      "//a[text()='DYNAMIC_VAlUE']/ancestor::tr[@class='rowContent item']//a[@class='toolbarGadget-dropDown']";
  /**
   * queryName to be entered in the text box.
   */
  public static final String CCMCREATEQUERYPAGE_EDITCURRENT_QUERY_TITLE_XPATH = "//div[@class='title']";
  /**
   * queryName to be entered in the text box.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_VERIFICATION = "Verified \"";
  /**
   * queryName to be entered in the text box.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_EXPECTEDNAME = "\" \n Expected query name\"";
  /**
   * queryName to be entered in the text box.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_VALUE = "value";
  /**
   * queryName to be entered in the text box.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_NOTSELECTED = "\" is not selected.";
  /**
   * queryName to be entered in the text box.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_CONDITION = " Verified condition \"";
  /**
   * queryName to be entered in the text box.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_ADDATTRIBUTE_CONDITION = "Add attribute condition";
  /**
   * queryName to be entered in the text box.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_ACTUAL_EXPECTED = " Column Name added to the Query page.";
  /**
   * queryName to be entered in the text box.
   */
  public static final String CCMCREATEQUERYPAGE_QUERY_COLUMN_NAME = " Verified Column Name Not found.";
  /**
   * Constant string variable to hold text "testPlanReleaseValue".
   */
  public static final String TESTPLAN_RELEASE_VALUE = "testPlanReleaseValue";
  /**
   * Constant string variable to hold text "domainValue".
   */
  public static final String DOMAIN_VALUE = "domainValue";
  /**
   * Constant string variable to hold text "linksSection".
   */
  public static final String LINKS_SECTION = "linksSection";
  /**
   * Constant string variable to hold text "dropdownText".
   */
  public static final String DROPDOWN_TEXT = "dropdownText";
  /**
   * Path for test case label mandatory field.
   */
  public static final String CCMPLANPAGEVERIFICATION_LABEL_XPATH = "//select[@aria-label= 'View As:']";
  /**
   * Path for test case label mandatory field.
   */
  public static final String CCMPLANPAGEVERIFICATION_NEWWORKITEMTYPE_ID_XPATH =
      "//div[@class='outliner']/a[text()='']/following::td[@title='Id']";
  /**
   * Path for test case label mandatory field.
   */
  public static final String CCMPLANPAGEVERIFICATION_WORKITEMTYPE_VERIFIED = "Verified work item type '";
  /**
   * Constant string variable to hold text "User Profile".
   */
  public static final String USER_PROFILE = "User Profile";
  /**
   * Constant string variable to hold text parentWorkItemId.
   */
  public static final String PARENTWORKITEM_ID = "parentWorkItemId";
  /**
   * Constant string variable to hold text testPlanId.
   */
  public static final String TESTPLAN_ID = "testPlanId";
  /**
   * Constant string variable to hold text testResultId.
   */
  public static final String TESTRESULT_ID = "testResultId";
  /**
   * Constant string variable to hold text tracksRequirementID.
   */
  public static final String TRACKSREQUIREMENT_ID = "tracksRequirementID";
  /**
   * Constant string variable to hold text TEST_CASE_RESULT_ID.
   */
  public static final String TEST_CASE_RESULT_ID = "testResultId";
  /**
   * Constant string variable to hold text ContributeWorkItemID.
   */
  public static final String CONTRIBUTEWORKITEM_ID = "ContributeWorkItemID";
  /**
   * Constant string variable to hold text ContributeWorkItemID.
   */
  public static final String RELATEDWORKITEM_ID = "relatedWorkItemID";
  /**
   * Constant string variable to hold text childrenWorkItemId.
   */
  public static final String CHILDRENWORKITEM_ID = "childrenWorkItemId";
  /**
   * Constant string variable to hold text linkTypeID.
   */
  public static final String LINKTYPE_ID = "linkTypeID";
  /**
   * Constant string variable to hold text TitleText.
   */
  public static final String TITLE_TEXT = "//span[@class='TitleText']";

  /**
   * Constant string variable to hold text TitleText.
   */
  public static final String CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH =
      "//div[@dojoattachpoint='_value']//span[@class='LabelValue']//a";
  /**
   * Constant string variable to hold text TEXT FIELD..
   */
  public static final String CCMWORKITEMEDITORPAGE_TEXT_FIELD_XPATH =
      "//div[@class='header-label-content']/a[text()='Name']/ancestor::tr/following-sibling::tr[@name='header-filter-control']/th[3]//input[@class='dijitReset dijitInputInner']";
  /**
   * Constant string variable to hold text filter button.
   */
  public static final String CCMWORKITEMEDITORPAGE_FILTERBUTTON_XPATH = "//button[@title='Apply all filters']";
  /**
   * Constant string variable to hold text SUMMARY.
   */
  public static final String CCMQUICKPLANNER_SUMMARYFIELD_XPATH = "//input[@placeholder='Type a work item summary']";
  /**
   * Constant string variable to hold text SUMMARY.
   */
  public static final String CCMPLANPAGE_FILTERTEXT_XPATH = "//input[@placeholder='Type to Filter']";
  /**
   * Constant string variable to hold text SUMMARY.
   */
  public static final String CCMCREATEQUERYPAGE_SEARCHBOX_XPATH =
      "//span[@class='filter']//div[@class='input-wrapper']/input";
  /**
   * Constant string variable to hold text SUMMARY.
   */
  public static final String CCMCREATEQUERYPAGE_SELECTGROUP_DROPDOWN_XPATH =
      "//select[@class='queryGrid-select-group']";
  /**
   * Constant string variable to hold text "Selected".
   */
  public static final String SELECTED = " Selected '";
  /**
   * Constant string variable to hold text "' project area in 'Add Link' dialog.".
   */
  public static final String PROJECT_AREA_ADD_LINK_DIALOG = "' project area in 'Add Link' dialog.";
  /**
   * Constant string variable to save project area in 'Add Link' dialog xpath
   */
  public static final String PROJECT_AREA_ADD_LINK_DIALOG_XPATH =
      "//div[@class='jazz-ui-Dialog-heading' or @class='header-primary' and contains(., 'Add Link')]";
  /**
   * Xpath for tab of the work item editor page.
   */
  public static final String CCMWORKITEM_EDITOR_PAGE_TAB_XPATH = "//a[@class='tab']";
  /**
   * Constant string variable to hold text " Searched '".
   */
  public static final String SEARCHED = " Searched '";
  /**
   * Xpath for selecting user of the work item editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_WORKITEM_SELECTUSER_XPATH =
      "//a[contains(.,'DYNAMIC_VAlUE')]//ancestor::span";
  /**
   * Constant string variable to hold text "Edit Description".
   */
  public static final String EDIT_DESCRIPTION = "Edit Description";
  /**
   * Constant string variable to hold text "//span[text()='".
   */
  public static final String SPAN_TEXT = "//span[text()='";
  /**
   * Constant string variable to hold text "Verify the displayed results as per the Query conditions.\n\nActual result
   * is '".
   */
  public static final String VERIFY_THE_DISPLAYED_RESULTS_AS_PER_THE_QUERY_CONDITION =
      "Verify the displayed results as per the Query conditions.\n\nActual result is '";
  /**
   * Constant string variable to hold text "' items found under '".
   */
  public static final String ITEMS_FOUND_UNDER = "' items found under '";
  /**
   * Constant string variable to hold text "' column.".
   */
  public static final String COLUMN = "' column.";
  /**
   * Constant string variable to hold text " Expected Result '".
   */
  public static final String EXPECTED_RESULT = " Expected Result '";
  /**
   * Constant string variable to hold text "conditionTitle".
   */
  public static final String CONDITIONTITLE = "conditionTitle";
  /**
   * Constant string variable to hold text "\" is added in 'Links' section.\n".
   */
  public static final String IS_ADDED_IN_LINKS_SECTION = "\" is added in 'Links' section.\n";
  /**
   * Constant string variable to hold text "\" Should be added in 'Links' section.".
   */
  public static final String SHOULD_BE_ADDED_IN_LINKS_SECTION = "\" Should be added in 'Links' section.";
  /**
   * Constant string variable to hold text "\n Actual Result \"".
   */
  public static final String ACTUAL_RESULT = "\n Actual Result \"";
  /**
   * Constant string variable to hold text "Type filter text and press Enter".
   */
  public static final String TYPE_FILTER_TEXT_AND_PRESS_ENTER = "Type filter text and press Enter";
  /**
   * Constant string variable to hold text "' in '".
   */
  public static final String IN = "' in '";
  /**
   * Constant string variable to hold text "' dialog.".
   */
  public static final String DIALOG = "' dialog.";
  /**
   * Constant string variable to hold text "Clicked on 'OK' button in 'Select Test Case' dialog."
   */
  public static final String CLICKED_ON_OK_BUTTON_IN_SELECT_TEST_CASE_DIALOG =
      "Clicked on 'OK' button in 'Select Test Case' dialog.";
  /**
   * XPath for Access Link to Welcome Page:
   */
  public static final String LINK_TO_WELCOME_PAGE_XPATH = "//a[@class='j-action-secondary']";

  /**
   * Constant text of hyperlink access to Plans page
   */
  public static final String PLANS = "Plans";

  /**
   * Constant text of hyperlink access to Work Items page
   */
  public static final String WORK_ITEMS = "Work Items";

  /**
   * XPath for Links tab in CCM Plan page:
   */
  public static final String LINKS_TAB_IN_CCMPLANPAGE_XPATH = "//div[@class='title' and contains(text(),'Links')]";

  /**
   * XPath for Link Type heading in table in CCM Plan page:
   */
  public static final String LINKTYPE_HEADING_XPATH =
      "//span[@class='label unsorted' and contains(text(),'Link Type')]";

  /**
   * XPath for one ROW of Implements Requirement Collection in Links tab in CCM Plan page:
   */
  public static final String IMPLEMENTS_REQUIREMENT_COLLECTION =
      "//div[@class='link']//a[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * XPath for one ROW of Implements Requirement Collection in Links tab in CCM Plan page:
   */
  public static final String CCMPLANPAGE_LOADING_MESSAGE_XPATH =
      "//div[@class='status-message' and contains(text(),'100%')]";
  /**
   * XPath for ID of a new work item created after select Create Work Item from Template
   */
  public static final String QUERYPAGE_WORKITEMID_GENERATEDBYCREATEWORKITEMFROMTEMPLATE =
      "//span[@class='btd' and text()='DYNAMIC_VAlUE']/preceding::td[1][@class='id']//span[@class='btd']";

  /**
   * XPath for variable of work item in Create Work Items From Template dialog
   */
  public static final String QUERYPAGE_WORKITEMID_GENERATEDBYCREATEWORKITEMFROMTEMPLATE_VARIABLE_XPATH =
      "//td[@class='string-variable-name' and text()='DYNAMIC_VAlUE:']/following-sibling::td[@class='string-variable-value']/input";

  /**
   * XPath for list ID of work items are generated by selecting option Create Work Items from Template
   */
  public static final String QUERYPAGE_GENERATEDBYCREATEWORKITEMFROMTEMPLATE_LISTWORKITEMID =
      "//td[@class='id' or @class='com-ibm-team-rtc-foundation-web-ui-gadgets-table-TableCell id']//a[@class='jazz-ui-ResourceLink']";

  /**
   * XPath for summary of a work item in work item editor page
   */
  public static final String WORKITEMEDITORPAGE_SUMMARY_XPATH =
      "//div[@class='workItemEditor workItemEditorWithShadedBackground' and not(contains(@style,'display: none'))]//div[@aria-label='Summary']";

  /**
   * XPath for type of a work item in work item editor page
   */
  public static final String WORKITEMEDITORPAGE_WORKITEM_TYPE_XPATH =
      "//div[@class='workItemEditor workItemEditorWithShadedBackground' and not(contains(@style,'display: none'))]//div[@role='combobox' and contains(@aria-label,'Type')]//span[@class='ValueLabelHolder']";

  /**
   * XPath for Filed Against value of a work item in work item editor page
   */
  public static final String WORKITEMEDITORPAGE_WORKITEM_FILEDAGAINST_XPATH =
      "//div[@class='workItemEditor workItemEditorWithShadedBackground' and not(contains(@style,'display: none'))]//div[@role='combobox' and contains(@aria-label,'Filed Against')]//span[@class='ValueLabelHolder']";

  /**
   * XPath for Planned For value of a work item in work item editor page
   */
  public static final String WORKITEMEDITORPAGE_WORKITEM_PLANNEDFOR_XPATH =
      "//div[@class='workItemEditor workItemEditorWithShadedBackground' and not(contains(@style,'display: none'))]//div[@role='combobox' and contains(@aria-label,'Planned For')]//span[@class='ValueLabelHolder']";

  /**
   * XPath for description of a work item in work item editor page
   */
  public static final String WORKITEMEDITORPAGE_WORKITEM_DESCRIPTION_XPATH =
      "//div[@class='workItemEditor workItemEditorWithShadedBackground' and not(contains(@style,'display: none'))]//div[@aria-label='Description']";

  /**
   * XPath for template name in Create Work Items From Template dialog
   */
  public static final String QUERYPAGE_TEMPLATENAME_XPATH =
      "//a[contains(@entryid,'com.ibm.team.workitem.template')]/span[text()='DYNAMIC_VAlUE']";

  /**
   * XPath for button Back to Query page
   */
  public static final String BACK_TO_QUERY_BUTTON_XPATH = "//a[contains(@href,'showTemplateQuery')]";

  /**
   * XPath for list parent work item of current work item
   */
  public static final String WORKITEMEDITORPAGE_LINKSTAB_LISTPARENT_XPATH =
      "(//div[@class='workItemEditor workItemEditorWithShadedBackground' and not(contains(@style,'display: none;'))]//div[@class='LinksSection'])[1]//div[div[@class='ListHeader' and text()='Parent']]//div[@class='ValueDiv']";

  /**
   * XPath for list children work item of current work item
   */
  public static final String WORKITEMEDITORPAGE_LINKSTAB_LISTCHILDREN_XPATH =
      "(//div[@class='workItemEditor workItemEditorWithShadedBackground' and not(contains(@style,'display: none;'))]//div[@class='LinksSection'])[1]//div[div[@class='ListHeader' and text()='Children']]//div[@class='ValueDiv']";

  /**
   * XPath for template name displayed in Query page after creating work items from template
   */
  public static final String QUERYPAGE_TEMPLATENAME_AFTERFINISHCREATINGWORKITEMS_XPATH =
      "//div[@class='topRow queryEditor' and not(contains(@style,'display: none'))]//span[@title='DYNAMIC_VAlUE']";

  /**
   * XPath for click Print icon
   */
  public static final String WORKITEMEDITORPAGE_PRINTICON_BUTTON_XPATH = "//span[@id='jazz_ui_toolbar_Button_6']/a";
  /**
   * XPath for get description text filed data
   */
  public static final String WORKITEMEDITORPAGE_DESCRIPTION_TEXTFIELD_XPATH =
      "//table[5]/tbody/tr[2]/td/div | //table[9]/tbody/tr[4]/td/a | //th[text()='Description']/../../tr/td/div[text()='DYNAMIC_VAlUE']";
  /**
   * XPath for Planned For option displayed in Create Work Items From Template dialog
   */
  public static final String CREATEWORKITEM_FROMTEMPLATE_PlANNEDFOR_OPTION_XPATH =
      "//a[@aria-label='DYNAMIC_VAlUE' and @aria-selected='false']";
  /**
   * XPath for condition: Loaded completedly list artifact in Add link dialog
   */
  public static final String CCMWORKITEMEDITORPAGE_ADDLINKDIALOG_LOADED_ARTIFACT_XPATH =
      "//span[@class='matching-results' and contains(text(),' ')]";
  /**
   * XPath for condition: Loading message (visible state)
   */
  public static final String LOADING_MESSAGE_XPATH = "//div[@class='status-message']";
  /**
   * XPath for Action menu of plan item in plan page
   */
  public static final String CCMPLANPAGE_PLANITEM_ACTION_BUTTON_XPATH =
      "//a[text()='DYNAMIC_VAlUE']//ancestor::tr[@class='rowContent item']//a[contains(@class,'toolbarGadget-dropDown')]";
  /**
   * XPath for Module search box in add link dialog
   */
  public static final String CCMWORKITEMEDITORPAGE_ADDLINKDIALOG_MODULE_SEARCHBOX_XPATH =
      "//span[@dojoattachpoint='_moduleNode']//div[@role='combobox' and not(contains(@style,'display: none'))]//input[@role='textbox']";

  /**
   *
   */
  public static final String LINK_SECTION = "linksSection";
  /**
   * XPath for Detailed Status dropdown of a work item
   */
  public static final String CCMWORKITEMEDITORPAGE_DETAILEDSTATUS_DROPDOWN_XPATH =
      "//div[contains(@aria-label,'Detailed Status')]";

  /**
   * XPath for Detailed Status of a work item, e.g: Reopen, Solved, Invalid, Duplicate
   */
  public static final String CCMWORKITEMEDITORPAGE_DETAILEDSTATUSOPTION_XPATH =
      "//li[@role='option']/span[text()='DYNAMIC_VAlUE']";

  /**
   * XPath for Detailed Status is selected
   */
  public static final String CCMWORKITEMEDITORPAGE_DETAILEDSTATUS_XPATH =
      "//div[contains(@aria-label,'Detailed Status')]//*[@class='ValueLabelHolder']";

  /**
   * XPath for Start Date in Create New Timeline or Create Iteration dialog
   */
  public static final String CCMPROJECTAREA_STARTDATE_INCREATETIMELINE_CRETAEITERATION_XPATH =
      "//div[@class='startDate']//input[contains(@id,'DateTextBox')]";

  /**
   * XPath for Start Time in Create New Timeline or Create Iteration dialog
   */
  public static final String CCMPROJECTAREA_STARTTIME_INCREATETIMELINE_CRETAEITERATION_XPATH =
      "//span[contains(@class,'startTime')]//input[contains(@id,'TimeTextBox')]";

  /**
   * XPath for End Time in Create New Timeline or Create Iteration dialog
   */
  public static final String CCMPROJECTAREA_ENDTIME_INCREATETIMELINE_CRETAEITERATION_XPATH =
      "//span[contains(@class,'endTime')]//input[contains(@id,'TimeTextBox')]";

  /**
   * XPath for Name field in Create New Timeline or Create Iteration dialog
   */
  public static final String CCMPROJECTAREA_NAME_INCREATETIMELINE_CRETAEITERATION_DIALOG_XPATH =
      "//td[@class='nameColumn selectRowOnClick']//input[contains(@id,'ValidationTextBox') and @type='text']";

  /**
   * XPath for Duration field in Create New Timeline or Create Iteration dialog
   */
  public static final String CCMPROJECTAREA_DURATION_INCREATETIMELINE_CRETAEITERATION_DIALOG_XPATH =
      "//span[text()='weeks']//preceding-sibling::div//input[@role='spinbutton' and @type='text']";

  /**
   * XPath for new timeline or new iteration which just created
   */
  public static final String CCMPROJECTAREA_NEWCREATETIMELINE_NEWCRETAEITERATION_DISPLAYED_XPATH =
      "//span[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * XPath for value in dropdown likes 'Planned For, Priority, Filed Against, Type, etc' after new work item is saved
   * successfully
   */
  public static final String CCMWORKITEMEDIT_DROPDOWN_VALUE_XPATH =
      "//label[contains(text(),'DYNAMIC_VAlUE')]//ancestor::th//following-sibling::td//span[@class='ValueLabelHolder']";

  /**
   * A button in the title actions container with a text specified by DYNAMIC_VALUE. Possible values: 'Save' , 'Create
   * Timeline..', 'Create Iteration'etc.
   */
  public static final String JAZZPAGE_BUTTONS_XPATH = "//button[contains(text(),'DYNAMIC_VAlUE')]";


  /**
   * Xpath for Owner/Iteration which is selected in Create New Plan screen
   */
  public static final String CCMCREATEPLANPAGE_SELECTEDOWNER_ITERATION_XPATH =
      "//span[contains(text(),'DYNAMIC_VAlUE')]//parent::td//following-sibling::td//span[@class='label']";

  /**
   * Xpath for search box 'Planned For' field in Create Task screen
   */
  public static final String CCMWORKITEM_SEARCHBOX_PLANNED_FOR_XPATH =
      "//div[@class='SearchBox']//span[contains(@id,'Button')]/preceding-sibling::input";
  /**
   * Xpath for value of 'Planned For' field in Create Task screen
   */
  public static final String CCMWORKITEM_PLANNED_FOR_VALUE_XPATH =
      "//label[contains(text(),'Planned For')]//ancestor::th//following-sibling::td//span[contains(@class,'ValueLabelHolder')]";
  /**
   * Xpath for get attribute value of Plan Name
   */
  public static final String CCMWORKITEM_PLAN_NAME_VALUE_XPATH =
      "//div[@aria-label='Plan Name']//following-sibling::input";
  /**
   * Xpath for Next button in particular section in right side bar
   */
  public static final String CCMWORKITEM_NEXT_ENABLE_BUTTON_XPATH = "//a[@aria-disabled='false' and @title='Next']";

  /**
   * Xpath for header of pop-up "Move or copy this work item"
   */
  public static final String CCMWORKITEM_MOVE_OR_COPY_WORKITEM_POPUP =
      "//span[text()= 'Move or Copy this Work Item']//ancestor::div[contains(@id,'popup')]";

  /**
   * Xpath for Project Area dropdown in 'Move or copy thisc work item' pop-up
   */
  public static final String CCMWORKITEM_PROJECTARE_MOVE_OR_COPY_WORKITEM_POPUP =
      "//label[text()='Project Area:']//ancestor::div[@class='middle-section']//select";

  /**
   * Xpath for Project Area list in 'Move or copy thisc work item' pop-up
   */
  public static final String CCMWORKITEM_PROJECTARE_LIST =
      "//label[text()='Project Area:']//ancestor::div[@class='middle-section']//select//option";

  /**
   * Xpath for radio button 'Move' in 'Move or copy this work item' pop-up
   */
  public static final String CCMWORKITEM_MOVE_BUTTON = "//input[@id='input_move']";

  /**
   * Xpath for radio button 'Copy' in 'Move or copy this work item' pop-up
   */
  public static final String CCMWORKITEM_COPY_BUTTON = "//input[@id='input_copy']";
  /**
   * Xpath for checkbox 'Copy Attachments' in 'Move or copy this work item' pop-up
   */
  public static final String CCMWORKITEM_COPY_ATTACHMENTS_CHECKBOX = "//input[@id='input_copy_attachments']";
  /**
   * Xpath for current Project Area in 'Move or copy thisc work item' pop-up
   */
  public static final String CCMWORKITEM_CURRENT_PROJECTARE = "//div[@class='project-area-select-div']//select";
  /**
   * Xpath for header validation message
   */
  public static final String CCMWORKITEM_HEADER_MESSAGE = "//div[@class='message']//a[@role='alert']//div";
  /**
   * Xpath for checking the displaying of Links in workitem editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_LINKS_LINKSLABEL_XPATH =
      "//span[text() = 'DYNAMIC_VAlUE0']/../../..//*/div[div[@aria-label = 'DYNAMIC_VAlUE1 links list Expanded']]/div[@class='ValueNode']/div/div/div/span[@class='LabelValue']/a[text()='DYNAMIC_VAlUE2']";

  /**
   * Xpath for checking comment displaying in workitem editor page.
   */
  public static final String CCMWORKITEMEDITORPAGE_COMMENT_INSIDE_WORKITEM_XPATH =
      "//div[@class='Comments']//div[contains(@class,'CommentBody') and contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Xpath for 'Move or copy this work item to another project area' button.
   */
  public static final String CCMWORKITEMEDITPAGE_MOVE_WORKITEM_XPATH = "//a[@role='button' and @title='DYNAMIC_VAlUE']";
  /**
   * Xpath for attachment inside workitem
   */
  public static final String CCMWORKITEMEDITPAGE_ATTACHMENT_XPATH =
      "//div[@class='AttachmentDetails']//label[@class='AttachmentLabel' and contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Xpath for tag value in work item overview tab
   */
  public static final String CCMWORKITEMEDITPAGE_TAG_VALUE_BY_LABEL_XPATH =
      "//span[contains(.,'DYNAMIC_VAlUE')]/parent::th/following-sibling::td//span[@class='LayoutValue']";

  /**
   * Xpath for Delete Plan button in All Plans page
   */
  public static final String CCMWORKITEMEDITPAGE_DELETE_PLAN_XPATH =
      "//a[text()='DYNAMIC_VAlUE']//ancestor::tr[contains(@class,'rowContent')]//div[@actionid='plan.delete']";
  /**
   * Xpath for Test Plan Name in All Plans page
   */
  public static final String CCMWORKITEMEDITPAGE_TEST_PLAN_XPATH = "//a[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for icon in front of Test Plan Name in All Plans page
   */
  public static final String CCMWORKITEMEDITPAGE_TEST_PLAN_ICON_XPATH =
      "//*[contains(text(),'DYNAMIC_VAlUE')]/parent::div/preceding-sibling::span";

  /**
   * Xpath for switching to JRS widget iframe in CCM dashboard
   * //span[text()='DYNAMIC_VAlUE']/ancestor::div[contains(@class,'header')]/following-sibling::div[@class='collapsible']//iframe
   */
  public static final String CCMDASHBOARDPAGE_JRS_WIDGET_IFRAME = "(//iframe[@class='iframe'])[1]";
  /**
   * Button Remove widget from dashboard by widget's name
   */
  public static final String CCMDASHBOARDPAGE_JRS_WIDGET_REMOVE_BUTTON =
      "//div[@class='header-primary' and @title='DYNAMIC_VAlUE']/../../..//a[@title='Remove']";

  /**
   * Constant string variable to test data's location for importing csv
   */
  public static final String IMPORT_FILE_LOCATION = "src\\test\\resources\\ccm\\";

  /**
   * Click operation on browse button.
   */
  public static final String CCMPROJECTAREAPAGE_IMPORTWIFROMCSV_BUTTON_XPATH = "//button[text() = 'Browse']";

}