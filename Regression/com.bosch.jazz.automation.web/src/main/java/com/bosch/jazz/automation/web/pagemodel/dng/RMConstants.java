/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;


/**
 * Constants for model class RMPage.
 */
public class RMConstants {

  private RMConstants() {

  }

  /**
   * Constant string variable to test data's location for importing artifact
   */
  public static final String IMPORT_FILE_LOCATION = "src\\test\\resources\\dng\\";
  /**
   * A WebEelement which contains the projectarea/component in the folder selection in the RMArtifactPage
   */
  public static final String RMARTIFACTSPAGE_SELECTANDCLICKTHEPROJECTAREA_XPATH =
      "(//span[@data-dojo-attach-point='contentNode'])[1]";

  /**
   * A intial content text field in create artifact window in RMArtifactPage
   */
  public static final String RMARTIFACTSPAGE_SENDINITIALCONTENTANDNAME_XPATH =
      "//div[@class='rdm-control rdm-col-8']/textarea";
  /**
   * A text clicks on the name based on text specified by DYNAMIC_VALUE. Possible values: 'requirement name', 'link
   * names(Satisfied By , Validated By,..etc)' etc. in RMArtifactPage
   */
  public static final String RMARTIFACTSPAGE_SELECTREQUIREMENT_XPATH = "//td[text()='DYNAMIC_VAlUE']";
 
  
  /**
   * A artifact type dropdown field in RMArtifactPage .
   */
  public static final String RMARTIFACTSPAGE_ARTIFACTTYPE_DROPDOWN_XPATH = "//input[@role = 'combobox']";

  /**
   * OK button in create artifact window in RMArtifactPage .
   */
  public static final String RMARTIFACTSPAGE_OKBUTTONBOX_XPATH = "//button[@type='submit']";

  /**
   * Current configuration dropdown button in RMDashBoardPage .
   */
  public static final String RMDASHBOARDPAGE_CURRENTCONFIG_XPATH =
      "(//span[@class='caret jazz-ui-MenuPopup-caret'])[2]";

  /**
   * Current configuration dropdown button in RMDashBoardPage .
   */
  public static final String CONTEXT_DROPDOWN = "Streams";

  /**
   * A link with a text specified by DYNAMIC_VALUE. Possible values: 'More...' , 'Import Artifact...', 'More Actions'
   * etc.
   */
  public static final String RMREVIEWSPAGE_SELECT_TYPE_XPATH = "//td[text()='DYNAMIC_VAlUE']";

  /**
   * A Navigate back link in the RMArtifactPage
   */
  public static final String RMARTIFACTSPAGE_NAVIGATEBACK_XPATH = "//a[@class='j-action-secondary']";

  /**
   * Constant String variable to hold text "Place new artifacts with no module hierarchy in a module"
   */
  public static final String RMARTIFACTSPAGE_IMPORTARTIFACT_PLACEINMODULE =
      "Place new artifacts with no module hierarchy in a module";

  /**
   * Quick Search text field/box in all RMPages
   */
  public static final String RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH = "//input[@role='search']";
  /**
   * Quick Search text field/box in all RMPages
   */
  public static final String RMARTIFACTSPAGE_SELECTDROPDOWNARTIFACT_XPATH =
      "//div[@class = 'search-result']//span[contains(text(),'DYNAMIC_VAlUE')]/..";

  /**
   * Save button in the RMArtifactPage
   */
  public static final String RMARTIFACTSPAGE_SAVETHEARTIFACT_XPATH = "//button[text()='Save']";

  /**
   * Edit button in the RMArtifactPage
   */
  public static final String RMARTIFACTSPAGE_EDITTHEARTIFACT_XPATH = "//button[text()='Edit']";

  /**
   * Done button in the RMArtifactPage
   */
  public static final String RMARTIFACTSPAGE_DONETHEARTIFACT_XPATH = "//button[text()='Done']";

  /**
   * Done button in the collection Page
   */
  public static final String RMARTIFACTSPAGE_DONETHEARTIFACTINCOLLECTIONS_XPATH = "//button[text()='Done']";

  /**
   * Text/Message webelement path in RMArtifactPage
   */
  public static final String RMARTIFACTSPAGE_REQIFSUCCESSMSG_XPATH = "//a[text()='Show Report']/.. ";

  /**
   * Next button in import artifact pages
   */
  public static final String RMARTIFACTSPAGE_SELECTNEXT_BUTTON_XPATH = "//button[@ dojoattachpoint='wizardNextButton']";

  /**
   * Stream webelement in compoent and configuration page
   */
  public static final String RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIG_STREAM_NAME_XPATH =
      "//span[@id='configurationTitleNode']";

  /**
   * Change set tab section in compoent and configuration page
   */
  public static final String RMARTIFACTPAGE_CHANGESETS_TAB_XPATH = "//div[@id='changesets']";

  /**
   * Dropdown to select the number of to showing list of changeset/baseline/steams in compoent and configuration page
   */
  public static final String RMARTIFACTPAGE_PAGESIZECONTROL_DROPDOWN_XPATH =
      "(//div[@class='pageSizeControlNode']/select)[2]";

  /**
   * List of change sets count path in compoent and configuration page
   */
  public static final String RMARTIFACTPAGE_CHANGESETSNUMBERS_TAB_XPATH = "//tr[@class='cmTreeGridRow']";

  /**
   * Text field in with a text specified by DYNAMIC_VALUE.
   */
  public static final String RMMODULEINSIDEPAGE_CREATELINKTESTCASE_TEXTBOX_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/ancestor::tr/descendant::input[@name='textField']";

  /**
   * List of artifact path in RMArtifactPage
   */
  public static final String RMARTIFACTPAGE_ARTIFACTNAMESFOR_EXPORT_XPATH = "//div[@class='content-area']/a/span";

  /**
   * Search box path of module in RMArtifactPage
   */
  public static final String RMARTIFACTSPAGE_MODULESERACHBOX_XPATH =
      "(//div[@class='filter-pane-search-div']/div/div/input)[3]";

  /**
   * Artifact menu button path in RMArtifactPage
   */
  public static final String RMARTIFACTPAGE_MENULAUNCHER_BUTTON_XPATH =
      "//div[@role='button']/img[@class='menuLauncher']";

  /**
   * Select configuration dropdown button path of in RMArtifactPage
   */
  public static final String RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH = "//span[text()='Streams']";

  /**
   * Component name filter text field in Configuration window.
   */
  public static final String RMARTIFACTPAGE_GCCOMPONENTSEARCH_FILTER_XPATH = "//input[@class='jazz-ui-TagBox__input']";
  /**
   * Xpath for the Section title in Module page.
   */
  public static final String RMARTIFACTPAGE_SECTION_TITLE_TEXT_XPATH =
      "//span[@class = 'section-content-title' and text() = 'DYNAMIC_VAlUE']";
  /**
   * A link in the title actions container with a text specified by DYNAMIC_VALUE. Possible values: 'New Definition' ,
   * 'Streams', etc.
   */
  public static final String LEFTSIDE_CONTAINER_BOX_XPATH =
      "//div[starts-with(@class, 'section-header section-heade')]//span[starts-with(text(),'DYNAMIC_VAlUE')]";
  /**
   * A link in the title actions container with a text specified by DYNAMIC_VALUE. Possible values: 'New Definition' ,
   * 'Streams', etc.
   */
  public static final String JAZZADMIN_SPANSELECTION_XPATH = "//*[starts-with(text(),'DYNAMIC_VAlUE')]";
  /**
   * A button in the title actions container with a text specified by DYNAMIC_VALUE. Possible values: 'Add' , 'Close',
   * 'Cancel'etc.
   */
  public static final String JAZZPAGE_BUTTONS_XPATH = "//button[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * A link in the title actions container with a text specified by DYNAMIC_VALUE. Possible values: 'Show Report' ,
   * 'Show Details', etc.
   */
  public static final String JAZZPAGE_LINK_XPATH = "//a[contains(text(),'DYNAMIC_VAlUE')]";
  
  /**
  *
  */
  public static final String SUMMARY = "Summary";
  /**
   * 
   */
  public static final String FILED_AGAINST = "FiledAgainst";
  
  
  /**
  *
  */
  public static final String TESTCASE_NAME = "TestCaseName";

  
  /**
   * A link with a text specified by DYNAMIC_VALUE. Possible values: 'Show Details' ,"ID's"
   */
  public static final String JAZZPAGE_LINKS_XPATH = "//a[text()='DYNAMIC_VAlUE']";
  /**
   * A link with a text specified by DYNAMIC_VALUE. Requirement Link in test case"
   */
  public static final String JAZZPAGE_REQUIREMENT_LINKS_XPATH = "//a[contains(@id,'ResourceLink')]/div[text()='DYNAMIC_VAlUE']";
  /**
   * Link(s) of Linked Artifact"
   */
  public static final String JAZZPAGE_LINKED_LINKS_XPATH = "//a[@class='jazz-ui-ResourceLink']";
  /**
   *
   */
  public static final String JAZZPAGE_TITLE_XPATH = "//.[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * A button with a text specified by DYNAMIC_VALUE. Possible values: 'Ok' , 'Save', 'Cancel'etc.
   */
  public static final String JAZZPAGE_BUTTON_XPATH = "//button[text()='DYNAMIC_VAlUE']";

  /**
   * A button with a text specified by DYNAMIC_VALUE. Possible values: 'Ok' , 'Save', 'Cancel'etc.
   */
  public static final String JAZZPAGE_SPANBUTTONS_XPATH = "//span[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Click on the Project area name
   */
  public static final String MANAGECOMPPROPERTIES_SELECTPA_XPATH =
      "//div[@class='reqif_title_col coloredLabel gridCellWrapper']";

  /**
   * Click on text specified by DYNAMIC_VALUE. Possible values: 'Show Full Hierarchy' , 'Show 1 Level', etc...
   */
  public static final String RMDASHBOARDPAGE_CREATE_CONFIG_XPATH = "//span[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for Project area/ Component name in Scope by dialog when using quick search
   */
  public static final String QUICKSEARCH_SCOPE_BY_NAME_XPATH =
      "//tr[@dojoattachpoint='_content']//span[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for Dialog after click on Configuration menu option
   */
  public static final String RMDASHBOARDPAGE_DIALOG_TITLE_XPATH = "//div[@role='dialog']//div[text()='DYNAMIC_VAlUE']";
  /**
   * Click on text specified by include DYNAMIC_VALUE. Possible values: 'Show Full Hierarchy' , 'Show 1 Level', etc...
   */
  public static final String RMDASHBOARDPAGE_APART_OF_TEXT_CREATE_CONFIG_XPATH =
      "//span[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Module link button with a text specified by DYNAMIC_VALUE. Possible values: 'Validated By' , 'Link To', 'Satisfied
   * By' etc.
   */
  public static final String RMMODULEINSIDE_MODULELINKS_BUTTON_XPATH =
      "//div[@class='section-header section-header-expanded']/following-sibling::div[@class='section-content']/descendant::span[contains(@title,'DYNAMIC_VAlUE')]/..";

  /**
   * Configuration filter type text field with a text specified by DYNAMIC_VALUE. Possible values: 'Type to filter' etc.
   */
  public static final String RMMODULEINSIDE_CONFIGURELINKDISPLAY_TEXTBOX_XPATH =
      "(//input[@placeholder='DYNAMIC_VAlUE'])[3]";

  /**
   * Module search box dropdown path in RMArtifactPage
   */
  public static final String RMARTIFACTSPAGE_MODULESERACHSELECTDROPDOWN_XPATH =
      "(//div[@aria-label='Row Actions'])[2]/img";

  /**
   * Dropdown with a text specified by DYNAMIC_VALUE. Possible values: 'Streams' , 'Baselines', 'Change set' etc.
   */
  public static final String RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH =
      "//tbody[@class='dijitReset']/descendant::span[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * Xpath of all the button present in select artifacts/module ..
   */
  public static final String MANAGECOMPPROPERTIES_BUTTONCLICK_XPATH = "//button[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath of ReqIF Text field.".
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_TEXTBOX_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/../following-sibling::td/div";
  /**
   * Xpath of reqIF save button ".
   */
  public static final String MANAGECOMPPROPERTIES_BUTTONDISABLED_XPATH = "//span[@id='dijit_form_Button_4']";

  /**
   * Component name filter text field in Configuration window.
   */
  public static final String RMARTIFACTPAGE_COMPONENTSEARCH_FILTER_XPATH =
      "//td[@class='search-filter']/descendant::input[@class='filterText helpMessage']";

  /**
   * Baseline name filter text field in Configuration window.
   */
  public static final String RMARTIFACTPAGE_BASELINESEARCH_FILTER_XPATH = "//tbody[@role='listbox']/tr[1]/td[1]";

  /**
   * Artifact filter text field in RMArtifactPage
   */
  public static final String RMARTIFACTSPAGE_TYPETOFILTERTEXTBOX_XPATH =
      "//input[@placeholder='Type to filter artifacts by text or by ID' or @placeholder='Type to filter by text or by ID']";
  /**
   *
   */
  public static final String JAZZ_MESSAGE_SUMMARY_XPATH = "//div[@class = 'messageSummary']";
  /**
   *
   */
  public static final String RM_ARTIFACTSPAGE_TYPE_SELECT_LISTBOX_XPATH = "//select[@class = 'selectSelect']";
  /**
   *
   */
  public static final String RM_ARTIFACTSPAGE_QUERY_SELECT_LISTBOX_XPATH =
      "//select[@dojoattachpoint = 'queryResults']";
  /**
   *
   */
  public static final String RM_ARTIFACTSPAGE_CREATE_LINK_SEARCH_DIAGRAM_XPATH = "//input[@class = 'SearchInputText']";
  /**
   * Search box for Artifact inside 'Create Link' dialog
   */
  public static final String RM_ARTIFACTSPAGE_CREATE_LINK_SEARCH_BOX_XPATH =
      "//div[@role='dialog']//input[@placeholder='Type to filter artifacts by text or by ID' or @placeholder='Type to filter by text or by ID']";
  /**
   *
   */
  public static final String RM_ARTIFACTSPAGE_ARTIFACT_TABLE_XPATH = "//table[@rowlabel='DYNAMIC_VAlUE']";
  /**
   * List of column elements.
   */
  public static final String RM_ARTIFACTSPAGE_COLOUMS_TABLE_XPATH = "//td[@role = 'columnheader']";
  /**
   *
   */
  public static final String RM_ARTIFACTSPAGE_TITLE_LINK_XPATH =
      "//a[@dojoattachpoint = 'titleLink' and starts-with(text() , 'DYNAMIC_VAlUE')]";
  /**
   *
   */
  public static final String RM_ARTIFACTSPAGE_TESTSPEC_ID_TEXTFEILD_XPATH =
      "//input[@role = 'textbox' and @name = 'id']";
  /**
   *
   */
  public static final String RM_ARTIFACRPAGE_TESTSPEC_RUN_BUTTON_XPATH =
      "//button[text() = 'Run' or text() = 'Filter']";


  /**
   * A link in the text actions container with a text specified by DYNAMIC_VALUE. Possible values: 'New Defination' ,
   * 'Streams', etc.
   */
  public static final String JAZZ_SPANSELECTION_XPATH = "//span[contains(text(),'DYNAMIC_VAlUE')]";


  /**
   *
   */
  public static final String RM_ARTIFACRPAGE_TESTSPEC_CHECKBOX_BUTTON_XPATH =
      "//a[text()='DYNAMIC_VAlUE']/../preceding-sibling::td//input";
  /**
   *
   */
  public static final String RM_ARTIFACRPAGE_TESTSPEC_CREATEOREXISTS_RADIO_XPATH =
      "//label[text() = 'DYNAMIC_VAlUE']/preceding-sibling::input[1]";
  /**
   *
   */
  public static final String RM_ARTIFACTPAGE_MODULE_LINKS_LIST_XPATH =
      "//div[@class = 'com-ibm-rdm-web-grid-Module module-container']//table[@rowtype = 'linkTypesGrouping' or @rowtype = 'resourceRow']";
  /**
   *
   */
  public static final String RM_ARTIFACTPAGE_MODULE_CHILD_LINKS_LIST_XPATH = "//table[@rowtype = 'resourceRow']";

  /**
   *
   */
  public static final String RM_ARTIFACE_WARNING_MESSAGE_EXPECTED =
      "One or more links were not created because they already exist.";
  /**
   * Artifact check box with a text specified by DYNAMIC_VALUE. Possible values:"artifact ids"
   */
  public static final String RMARTIFACTSPAGE_SELECTCHECKBOX_XPATH =
      "//a[text()='DYNAMIC_VAlUE']/../../../../../preceding-sibling::td[2]/div/label";

  /**
   * Artifact attribute path with a text specified by DYNAMIC_VALUE. Possible values:"artifact ids"
   */
  public static final String RMARTIFACTSPAGE_ARTIFACTTYPEGETTEXT_XPATH =
      "//a[text()='DYNAMIC_VAlUE']/../../../../../following-sibling :: td[1]/div/div";

  /**
   * Adding link path with a text specified by DYNAMIC_VALUE. Possible values:"Validated by"
   */
  public static final String RMMODULEINSIDE_ADDLINKTOARTIFACT_LEFTSIDEMENU_XPATH =
      "//div[@dojoattachpoint='menuWrapper']/descendant::td[starts-with(text() , 'DYNAMIC_VAlUE')]";

  /**
   * A variable to store xpath for the sub menu elements which are opened after clicking on add link to artifact which
   * is present in module container.
   */
  public static final String RMLINKSPAGE_ADDLINKTOARTIFACT_SUBMENU_XPATH =
      "//div[@class='dijitPopup jazz-ui-MenuPopup']//td[starts-with(text() , 'DYNAMIC_VAlUE')]";

  /**
   * Adding link path in module with a text specified by DYNAMIC_VALUE.
   */
  public static final String RMMODULEINSIDE_ADDLINKTOARTIFACT_LEFTSIDEMENU_TYPE_TWO_XPATH =
      "//div[@class = 'editmenu com-ibm-rdm-web-grid-EditMenu']/following-sibling::div//td[starts-with(text() , 'DYNAMIC_VAlUE')]";

  /**
   * OK button in the special paste option
   */
  public static final String RMMODULEINSIDE_PASTESPECIALOK_BUTTON_XPATH =
      "//button[@data-dojo-attach-point='okButton' and @disabled='']";

  /**
   * Scroll down button in the link option
   */
  public static final String RM_ADD_ARTIFACT_LINKS_SUB_MENU_DOWNARROW_XPATH = "//div[@class='scrollButton down']/span";
  /**
   *
   */
  public static final String RM_ADD_ARTIFACT_LINKS_SUB_MENU_UPARROW_XPATH = "//div[@class='scrollButton down']/span";
  /**
   * Name is the field name in RMArtifactPage
   */
  public static final String NAME = "Name:";

  /**
   * Name is the field initial Content in RMArtifactPage
   */
  public static final String INITIAL_CONTENT = "Initial content:";
  /**
   * Filter text field name in RMArtifactPage
   */
  public static final String TYPE_OF_FILTERS = "Type to filter";

  /**
   * Module Link name in RMArtifactPage
   */
  public static final String MODULELINK = "Links Buttons Container";

  /**
   * Artifact link names in RMArtifactPage
   */
  public static final String LEFTSIDELINK = "Left side menu";

  /**
   * Artifact menu values in RMArtifactPage
   */
  public static final String RIGHTSIDEMENU = "Click Right side menu";

  /**
   * Module hierarchy view level in RMArtifactPage
   */
  public static final String LEVELNAME = "Show 1 Level";

  /**
   * Overview section in RMArtifactPage
   */
  public static final String OVERVIEW = "Overview";

  /**
   * Module tab link in RMArtifactPage
   */
  public static final String MODULE = "Modules";

  /**
   * Finish button in RMArtifactPage
   */
  public static final String FINISHBUTTON = "Finish";
  /**
   * Browse... button in RMArtifactPage
   */
  public static final String BROWSEBUTTON = "Browse...";
  /**
   * Delete... button in RMArtifactPage
   */
  public static final String DELETEBUTTON = "Delete...";

  /**
   * Close button in RMArtifactPage
   */
  public static final String CLOSEBUTTON = "Close";

  /**
   * Constant string variable to hold text "KIND_OF_VALUE".
   */
  public static final String KIND_OF_VALUE = "KIND_OF_VALUE";

  /**
   * Next button in RMArtifactPage
   */
  public static final String NEXT = "Next >";
  

  /**
   * Next button in RMArtifactPage
   */
  public static final String FINISH = "Finish";


  /**
   * Upload button in RMArtifactPage
   */
  public static final String UPLOAD = "Upload";

  /**
   * Show Report link option in RMArtifactPage
   */
  public static final String SHOWREPORT = "Show Report";

  /**
   * Show Report link option in RMArtifactPage
   */
  public static final String GOTO = "Go To";

  /**
   * Create change set link in RMArtifactPage
   */
  public static final String CREATECHANGESET = "Create Change Set...";

  /**
   * Create steam link in RMArtifactPage
   */
  public static final String CREATESTREAM = "Create Stream...";

  /**
   * 'Deliver Changes...' in Current configuration dropdown
   */
  public static final String DELIVER_CHANGES = "Deliver Changes...";
  /**
   * 'Create Baseline...' in Current configuration dropdown
   */
  public static final String CREATE_BASELINE = "Create Baseline...";

  /**
   * Title of create stream dialog
   */
  public static final String CREATE_STREAM_DIALOG = "Create Stream";

  /**
   * Title of create baseline dialog
   */
  public static final String CREATE_BASELINE_DIALOG = "Create Baseline";
  /**
   * Title of Compare Configuration dialog
   */
  public static final String COMPARE_CONFIGURATION_DIALOG = "Select Configuration to Compare";
  /**
   * Title of Select Target Configuration dialog
   */
  public static final String SELECT_TARGET_CONFIGURATION_DIALOG = "Select Target Configuration";
  /**
   * Title of Select Source Configuration dialog
   */
  public static final String SELECT_SOURCE_CONFIGURATION_DIALOG = "Select Source Configuration";
  /**
   * Title of create change set dialog
   */
  public static final String CREATE_CHANGE_SET_DIALOG = "Create Change Set";

  /**
   * Title of create change set dialog
   */
  public static final String LINK_TO_A_WORKITEM_SET_DIALOG = "Link to a Work item...";

  /**
   * Title of create change set dialog
   */
  public static final String DISCARD_CHANGE_SET_DIALOG = "Discard the Change Set";

  /**
   * Constant string variable to hold text "CONFIG_OPTION".
   */
  public static final String CONFIG_OPTION = "CONFIG_OPTION";

  /**
   * Constant string variable to hold text "CONFIG_ACTION_OPTION".
   */
  public static final String CONFIG_ACTION_OPTION = "CONFIG_ACTION_OPTION";

  /**
   * Constant string variable to hold text "CONFIG_NAME".
   */
  public static final String CONFIG_NAME = "CONFIG_NAME";

  /**
   * SelectType is the requirment type in RMArtifactPage
   */
  public static final String SELECTTYPE = "SELECT_REQUIREMENT_TYPE";

  /**
   * IMPORTTYPE is the importing option button in RMArtifactPage
   */
  public static final String IMPORTTYPE = "IMPORT_TYPE";

  /**
   * Import requirements from within a text document is the importing type name in RMArtifactPage
   */
  public static final String TEXTDOCUMENT = "Import requirements from within a text document";

  /**
   * Import requirements from a CSV file or spreadsheet is the importing type name in RMArtifactPage
   */
  public static final String CSVIMPORT = "Import requirements from a CSV file or spreadsheet";

  /**
   * Import requirements from within a text document is the importing type name in RMArtifactPage
   */
  public static final String REQIF_IMPORT = "Import requirements from a ReqIF file";

  /**
   * Import requirements from a CSV file or spreadsheet is the importing type name in RMArtifactPage
   */
  public static final String MIGRATION_IMPORT = "Import requirements from a migration package";

  /**
   * FILETYPE is the file path
   */
  public static final String FILETYPE = "FILE_PATH";

  /**
   * ARTIFACT_ID is the parameter for passing artifact id
   */
  public static final String ARTIFACTID = "ARTIFACT_ID";

  /**
   * NEWDEFINATION is link in RMManagementComponentProperties
   */
  public static final String NEWDEFINATION = "New Definition";

  // Select Project Area

  /**
   *
   */
  public static final String RMPROJECTAREADASHBOARDPAGE_MENUITEM_XPATH = "//a[@title='DYNAMIC_VAlUE']";

  // Project DashBoard
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_CURRENTCONFIGCOMP_XPATH =
      "//*[@id='jazz_ui_PageTemplate_0']/div[3]/div[3]/div/div/div/div/span[3]";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH = "//span[@class='project-select']/div";
  /**
   * Xpath for title of current delivery section
   */
  public static final String RMDASHBOARDPAGE_CURRENT_DELIVERY_SECTION_TITLE =
      "//div[contains(@id,'stepDOM') and contains(@style,'display: block;')]//h1[contains(@dojoattachpoint,'head')]";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_PROJECTAREA_XPATH =
      "//div[@class='menuWrapper wrapper']/table/tbody/tr/td/span/span[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_SEARCHCOMP_XPATH = "//td[@class='search-filter']/div/div/input";
  /**
   * Xpath for option in Select configuration Dialog
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_SELECTCOMP_XPATH = "//td[@cell-value='DYNAMIC_VAlUE']//span[text()]";
  /**
   * click on home button.
   */
  public static final String RMDASHBOARDPAGE_HOMEBUTTON_LINK_XPATH = "//a[@title='Home']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_CREATECOMP_XPATH =
      "//tr[contains(@class,'MenuItem')]//*[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_ARCHIVEBUTTON_LINK_XPATH = "//img[@alt='Archive Project Area']";
  /**
   * list of widgets present on the dashboard.
   */
  public static final String RMDASHBOARDPAGE_WIDGETS_TABLE_XPATH = "//div[@role='heading']";
  /**
   * message area whether dahsboard saved or not.
   */
  public static final String RMDASHBOARDPAGE_MESSAGEAREA_LABEL_XPATH = "//div[@class = 'messageSummary']";

  // Personal DashBoard
  /**
   * home button drop down in dashboard page.
   */
  public static final String RMDASHBOARDPAGE_HOMEBUTTON_DROPDOWN_XPATH = "//a[@class='home-menu jazz-ui-MenuPopup']";
  /**
   * message area whether personal dahsboard saved or not.
   */
  public static final String RMDASHBOARDPAGE_SUCCESSFULLYDASHBOARDSAVED_LABEL_XPATH =
      "//div[@class='messageArea OK']/span";
  /**
   * List of All personal Dash board Names.
   */
  public static final String RMDASHBOARDPAGE_PERSONALDASHBOARDSLIST_LINK_XPATH =
      "//tbody[@class='table-primary']/tr/td[1]/a";
  /**
   * Clicks on the Personal DashBoard Delete button.
   */
  public static final String RMDASHBOARDPAGE_PERSONALDASHBOARDDELETEBUTTON_LINK_XPATH = "//img[@alt='Delete']";
  /**
   * dropdown button in dashboard page for selecting the proper application.
   */
  public static final String RMDASHBOARDPAGE_ADDWIDGET_DROPDOWN_XPATH = "//div[@dojoattachpoint='_caret']";
  /**
   * dropdown button in dashboard page for selecting the proper application.
   */
  public static final String RMARTIFACTPAGE_LINKCONTEXTMENU_OPTION_XPATH = "//td[contains(text(),'DYNAMIC_VAlUE')]"; // =
  // "//td[text()='DYNAMIC_VAlUE']";
  /**
   * search box for search widget.
   */
  public static final String RMDASHBOARDPAGE_ADDWIDGET_SEARCHTEXTBOX_XPATH = "//input[@aria-label='Search text']";
  /**
   * Clicks and opens the personal dashboard.
   */
  public static final String RMDASHBOARDPAGE_PERSONALDASHBOARD_LINK_XPATH =
      "(//table[@class='dijit dijitMenu dijitMenuPassive dijitReset dijitMenuTable']/tbody[1]/tr[2]/td[1]/a/span[1])/span[1]";
  /**
   * adding the widget in dashboard page.
   */
  public static final String RMDASHBOARDPAGE_FIRSTWIDGET_LINK_XPATH =
      "//div[@id='jazz_ensemble_internal_layout_Column_0']/div[1]/div[2]/div[1]/div[2]/div/div[2]/span";

  // CreateStream
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_CURRENTCONFI_DROPDOWN_XPATH =
      "(//span[@class='caret jazz-ui-MenuPopup-caret'])[2]";

  /**
   * Name text box for create the stream.
   */
  public static final String RMDASHBOARDPAGE_NAME_TEXTBOX_XPATH = "//*[@id='nameInputBox']";
  /**
   * description text in create stream page.
   */
  public static final String RMDASHBOARDPAGE_DESCRIPTION_TEXTBOX_XPATH =
      "//textarea[@data-dojo-attach-point='focusNode,containerNode,textbox']";
  /**
   * click on stream check box.
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_STREAMCHECKBOX_XPATH = "//div[@class='checkboxNode']/div";
  /**
   * streams cofig dropdown in create stream page.
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_STREAMCONFIGDROPDOWN_XPATH =
      "//span[text()='Streams']/../../../../following-sibling:: div/div[2]/div[2]/div[2]/div/div/select";
  /**
   * configuration button in create stream.
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_CONFIGACTIONSBUTTON_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/../../../preceding-sibling ::td/span";
  /**
   * clear the text in name text box for rename the stream.
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_RENAME_TEXTBOX_XPATH = "//input[@class='nameInput']";

  // BaseLine
  /**
   * baseline config drop down button.
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_BASELINECONFIGDROPDOWN_XPATH = "//Select";
  /**
   * display the message
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_MSG_XPATH = "//div[(@class='')]/span[2]";


  // Change Set
  /**
   * name text box for create change set.
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_CHANGESETNAMEBOX_XPATH = "//*[@id='nameInput']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_SELECTCREATEBUTTONTOBASELINE_XPATH = "(//span[text()='Create'])[2]";
  /**
   * Deliver action button in create change set page.
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_DELIVERYACTIONBUTTON_XPATH =
      "//span[contains(text(),'DYNAMIC_VAlUE')]/../../../preceding-sibling::td/div/span";

  // ReviewsPage
  /**
   *
   */
  public static final String RMREVIEWSPAGE_BROWSEREVIEWSPAGE_XPATH =
      ".//*[@id='jazz_app_internal_CustomNavbar_0']/div[1]/a[2]";
  /**
   *
   */
  public static final String RMREVIEWSPAGE_BROWSERREVIEW_DROPDOWNBUTTON_XPATH = "//input[@value='▼ ']";

  /**
   * XPATH of Dropdown button of Component of 'Where to link in' label in Create Link dialog
   */
  public static final String RMARTIFACTPAGE_CREATELINKDIALOG_COMPONENT_DROPDOWNBUTTON_XPATH = "//div[contains(@id,'ComponentsFilteringSelect')]//input[@value='▼ ']";
  /**
  * XPATH of Dropdown button of Modules of 'What to look in' label in Create Link dialog
  */
 public static final String RMARTIFACTPAGE_CREATELINKDIALOG_MODULES_DROPDOWNBUTTON_XPATH = "//div[contains(@id,'ArtifactInstancesFilteringSelect_1')]//input[@value='▼ ']";
  /**
   *
   */
  public static final String RMREVIEWSPAGE_ENTERREVIEW_NAME_XPATH = "//input[@aria-required='true']";
  /**
   *
   */
  public static final String RMREVIEWSPAGE_CLICKOK_BUTTON_XPATH = "//button[@type='submit']";
  /**
   *
   */
  public static final String RMREVIEWSPAGE_SELECTUSERS_SEARCHBOX_XPATH = "(//input[@role='searchbox'])[1]";
  /**
   ****
   */
  public static final String RMREVIEWSPAGE_SELECT_USER_XPATH = "//select[@dojoattachpoint='userSelector']/option";
  /**
   *
   */
  public static final String RMREVIEWSPAGE_SELECT_PROJECT_XPATH =
      "//*[@id='com_ibm_rdm_web_project_ContextTreeNode_0']/div[1]";
  /**
   *
   */
  public static final String RM_TEMPLATEPAGE_SELECTART_LIST_XPATH = "//div[@class='jazz-ui-ListBox']/div[2]/a";
  /**
   *
   */
  public static final String RMREVIEWSPAGE_DELETETHE_REVIEW_XPATH = "//a[@title='Delete']";
  /**
   *
   */
  public static final String RMREVIEWSPAGE_CLICKTHEDELETETHEREVIEW_XPATH = "//button[text()='Delete Review']";


  // Project Dashboard
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_PROJECTDB_LINK_XPATH = "//div[@class='net-jazz-ajax-PageList']/a/span";
  /**
   * add widget button in dashboard page.
   */
  public static final String RMDASHBOARDPAGE_PROJECTDB_MODIFY_ADDWIDGET_XPATH = "//span[text()='Add Widget']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_PROJECTDB_ADDWIDGET_DROPDOWN_XPATH = "//div[@dojoattachpoint='_caret']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_PROJECTDB_ADDWIDGET_DROPDOWN_SELECTDNG_XPATH =
      "//span[text() = 'DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_PROJECTDB_ADDWIDGET_SEARCHTEXTBOX_XPATH =
      "//div[@class='jazz-ui-FilterBox']/div/input";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_PROJECTDB_ADDWIDGET_ADDWIDGETBUTTON_XPATH =
      "//a[@class=\"button\"]//span[text() = 'Add Widget']";
  /**
   * save button for saving the widget in dashboard page.
   */
  public static final String RMDASHBOARDPAGE_PROJECTDB_ADDWIDGET_SAVEBUTTON_XPATH = "//input[@class='saveButton']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_PROJECTDB_WIDGETS_TABLE_XPATH =
      "//div[@class='header-text true jazz-ui-dnd-handle']";

  /**
   *
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_SETUPOKBUTTON_XPATH =
      "//div[@class='buttonSection']/button[contains(text(),'OK')]";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_ADMIN_XPATH = "//a[@aria-label='Administration Drop-Down Menu']";
  /**
   *
   */
  public static final String DNG_ADMINPAGE_REQIFTAB_XPATH = "//span[text()='ReqIF']";
  /**
   *
   */
  public static final String DNG_ADMINPAGE_NEWDEFBUTTON_XPATH = "//span[text()='New Definition...']";
  /**
   *
   */
  public static final String DNG_ADMINPAGE_REQIFNAME_XPATH = "(//input[@class='dijitReset dijitInputInner'])[8]";
  /**
   *
   */
  public static final String DNG_ADMINPAGE_REQIFDESC_XPATH = "(//textarea[@name='description'])[3]";
  /**
   *
   */
  public static final String DNG_REQIF_ADDARTIFACT_XPATH = "//span[text()='Add Artifact...']";
  /**
   *
   */
  public static final String DNG_TEMPLATEPAGE_ADDANDCLOSEBUTTON_XPATH = "//button[text()='Add & Close']";

  /**
   *
   */
  // Assign Workflow
  public static final String RMDASHBOARDPAGE_WORKFLOWID_XPATH = "//input[@data-dojo-attach-point='idField']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_STATEACTION_XPATH =
      "//div[text()='DYNAMIC_VAlUE']/preceding-sibling::div/a";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_STATENAME_XPATH = "//input[@id='{id}_nameField']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_STATEDROPDOWN_XPATH =
      "//select[@data-dojo-attach-point='startActionField']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_MESSAGE_XPATH = "//div[@dojoattachpoint='messageArea']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_ACTIONNAME_XPATH =
      "//input[@data-dojo-attach-point='nameField'][@class='formField']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_STATEACTION_DROPDOWN_XPATH =
      "//label[text()='Name:']/../../following-sibling ::tr/td[2]/select[@class='formField']";

  // Assign Team Ownership


  /**
   *
   */
  public static final String RMARTIFACTPAGE_PAFOLDER_XPATH = "//span[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_ASSIGNTEAMOWNERSHIPLINK_XPATH =
      "//*[contains(text(), 'Assign Team Ownership')]";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_PAFOLDER_SELECTTEAMAREALINK_XPATH = "//div[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_PAFOLDER_TEXTBOX_XPATH =
      "//div[@class='dijitReset dijitInputField dijitInputContainer']/input";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_PAFOLDER_OKBUTTON_XPATH = "//button[@type='submit']";


  // Artifact Template

  /**
   *
   */
  public static final String DNG_ADMINPAGE_PROJECTPPTY_XPATH = "//span[text()='Manage Project Properties']";
  /**
   *
   */
  public static final String DNG_ADMINPAGE_TEMPLATESTAB_XPATH = "//span[text()='Templates']";
  /**
   *
   */
  public static final String DNG_TEMPLATESPAGE_ARTITEMP_XPATH = "//li[text()='Artifact Templates']";
  /**
   *
   */
  public static final String DNG_TEMPLATESPAGE_NEWTEMPBUTTON_XPATH = "(//span[text()='New Template...'])[3]";
  /**
   *
   */
  public static final String DNG_TEMPLATESPAGE_TEMPLATENAME_XPATH = "(//input[@class='dijitReset dijitInputInner'])[8]";
  /**
   *
   */
  public static final String DNG_TEMPLATEPAGE_TEMPLDES_XPATH =
      "(//textarea[@data-dojo-attach-point='focusNode,containerNode,textbox'])[4]";
  /**
   *
   */
  public static final String DNG_TEMPLATEPAGE_BROWSEBUTTON_XPATH = "//button[text()='Browse...']";
  /**
   *
   */
  public static final String DNG_TEMPLATEPAGE_SELECTART_TEXTBOX_XPATH = "(//input[@aria-label='Search text'])[2]";
  /**
   *
   */
  public static final String DNG_TEMPLATEPAGE_SELECTART_LIST_XPATH = "//div[@class='jazz-ui-ListBox']/div[2]/a";
  /**
   *
   */
  public static final String DNG_TEMPLATEPAGE_OKBUTTON_XPATH = "//button[text()='OK']";
  /**
   *
   */
  public static final String DNG_TEMPLATEPAGE_CREATEBUTTON_XPATH = "//button[text()='Create']";
  /**
   *
   */
  public static final String DNG_ARTITYPELIST_XPATH = "//div[@class='artifactType_col gridCellWrapper']";
  /**
   *
   */
  public static final String DNG_CREATEDTEMPLATE_XPATH = "//div[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String DNG_TEMPLATE_ARTIFACTTYPE_XPATH =
      "//div[text()='DYNAMIC_VAlUE']/../../../..//td[@class = 'artifactType_col']";
  @SuppressWarnings("javadoc")
  public static final String DNG_ARTIFACTSMENU_XPATH = "//a[text()='Artifacts']";
  /**
  * Create button in Artifacts page
  */
 public static final String DNG_ARTIFACTCREATE_CREATEBUTTON_XPATH = 
   "//span[@title='Create New Artifact'][./span[text()='Create']]";
   /**
    * Create button in Artifacts page
    */
 public static final String DNG_MODULEPAGE_CREATEBUTTON_XPATH = "//span[@title='Create New Artifact']";
  /**
   *
   */
  public static final String DNG_ARTIFACTTYPE_SELECT_XPATH = "//td[text()='DYNAMIC_VAlUE']";


  /**
   *
   */
  public static final String DNG_ARTIFACTNAME_XPATH =
      "(//div[@class='dijitReset dijitInputField dijitInputContainer']/input)[1]";
  /**
   *
   */
  public static final String RMREVIEWSPAGE_SELECTARTIFACTTYPEDROPDOWNBOX_XPATH =
      "(//input[@role='button presentation'])[1]";

  /**
   *
   */
  public static final String RMREVIEWSPAGE_SELECTTEMPLATEDROPBOX_XPATH = "(//input[@role='button presentation'])[4]";
  /**
   *
   */
  public static final String RMREVIEWSPAGE_SELECTOKBUTTONBOX_XPATH = "//button[@type='submit']";
  /**
   *
   */
  public static final String DNG_ARTIFACTS_CHECKBOX_XPATH = "//input[@class='dijitReset dijitCheckBoxInput']";


  /**
   *
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_MAINITERATION_EXPAND_XPATH =
      "//img[@class='dijitTreeExpando dijitTreeExpandoClosed']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_TEAMAREA_SELECTTIMELINE_TIMELINE_XPATH =
      "//option[text()='DYNAMIC_VAlUE']";


  /**
   *
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_TEAMAREA_PROCESSROLE_XPATH = "//td[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_TEAMAREA_DESELECTPROCESSROLE_XPATH =
      "//select[@class='selectRoles']/option[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_SETTINGS_TEAMAREA_MEMBER_SEARCHBOX_XPATH =
      "//div[text()='No members selected']/following-sibling :: div[2]/div/div/input";


  /**
   *
   */
  public static final String DNG_ARTTYPE_XPATH = "//td[text()='Test Plan Collection']";
  /**
   *
   */
  public static final String ARTIFACTTYPE_XPATH = "(//div[@data-dojo-attach-point='_popupStateNode']/div[1]/input)[1]";
  /**
   *
   */
  public static final String TEMPLATE_XPATH = "(//div[@data-dojo-attach-point='_popupStateNode']/div[1]/input)[3]";


  // CreateArtifactsPage

  /**
   * search box.
   */
  public static final String RMARTIFACTSPAGE_ARTIFACTS_SEARCHBOX_XPATH =
      "(//div[@class='filter-pane-search-div']/div/div/input)[1]";
  /**
   * select the dropdown button in artifact page.
   */
  public static final String RMARTIFACTSPAGE_ARTIFACT_SELECTIMGDROPDOEN_XPATH =
      "(//div[@aria-label='Row Actions'])[1]/img";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_CREATEARTIFACT_XPATH = "//span[text()='Create']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_TEMPLATE_DROPDOWNS_XPATH = "//input[@role = 'textbox']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_ADMINMENUDROPDOWNANDSELECTMANAGECOMP_XPATH =
      "(//span[text()='Manage Components and Configurations'])[2]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_TEMPLATE_DROPDOWN_XPATH =
      "//label[contains(text(),'Artifact type')]/following-sibling::div/div/div[3]/input";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTARTIFACTTYPEDROPDOWNBOX_XPATH =
      "(//input[@role='button presentation'])[1]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTARTIFACTFORMATDROPDOWNBOX_XPATH =
      "(//input[@role='button presentation'])[2]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTARTIFACTVALUEDROPDOWNBOX_XPATH =
      ".//*[@id='com_ibm_rdm_web_project_NewArtifactForm_1_artifactTypeField']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTARTIFACTFORMAT_XPATH =
      "//label[contains(text(),'DYNAMIC_VAlUE')]/following-sibling :: div/div/div[1]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTARTIFACTFORMATVALUE_XPATH =
      "//div[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTTEMPLATEDROPDOWNBOX_XPATH =
      "(//input[@role='button presentation'])[3]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_DONEBUTTON_XPATH = "//button[text()='Done']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTOKBUTTONBOXANDUPLOADARTIFACT_XPATH =
      "//*[contains(text(),'Upload Artifact...')]";

  // EditArtifact
  /**
   * id of the artifact.
   */
  public static final String RMARTIFACTSPAGE_GETARTIFACTTEST_XPATH = "//span[@class = 'resource-id']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_ARTIFACTID_LINK_XPATH = "//a[text() = 'DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SEARCH_BUTTON_XPATH = "//a[@title = 'Search']";
  /**
   * Xpath for Enable Edit button in Artifact page
   */
  public static final String RMARTIFACTSPAGE_EDIT_BUTTON_XPATH =
      "//button[text()='Edit' and contains(@style,'display: inline-block;')]";

  /**
   * Description button in artifact page.
   */
  public static final String RMARTIFACTSPAGE_DESCRIPTIONBUTTON_XPATH =
      "//div[@data-dojo-type='dijit.layout.ContentPane' and contains(@style,'width')]//textarea[contains(@id,'_description')  and contains(@id,'_OverviewSection_')]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_OVERVIEWTAB_DROPDOWN_XPATH =
      "//label[starts-with(text() , 'DYNAMIC_VAlUE')]/following-sibling::div";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_OVERVIEWTAB_TEXTFIELD_XPATH =
      "//label[starts-with(text() , 'DYNAMIC_VAlUE')]/following-sibling::div";
  /**
   * OverView Tab field in editArtifact page.
   */
  public static final String RMARTIFACTSPAGE_OVERVIEWTAB_FIELD_XPATH =
      "//span[@class = 'section-title-span j-label']/span[2][contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * Comment text box in artifact page.
   */
  public static final String RMARTIFACTSPAGE_COMMENT_LINKS_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/preceding-sibling :: span";
  /**
   * subject text filed in artifact page.
   */
  public static final String RMARTIFACTSPAGE_SUBJECT_TEXTFIELD_XPATH = "//input[contains(@id , '_commentSubjectId')]";
  /**
   * Artifact ID locator inside a Module, base on Artifact Name 
   */
//  public static final String RMMODULEINSIDE_ARTIFACT_ID_BY_ARTIFACT_NAME_XPATH= "//a[contains(text(),'DYNAMIC_VAlUE')]//ancestor::td[@class='name cellEditable']//preceding-sibling::td[@class='id-col']//a";
  public static final String RMMODULEINSIDE_ARTIFACT_ID_BY_ARTIFACT_CONTENT_XPATH= "//p[contains(text(),'DYNAMIC_VAlUE')]//ancestor::td[contains(@class,'module_content')]//preceding-sibling::td[@class='id-col']//a";
  /**
   * user selector in edit artifact page.
   */
  public static final String RMARTIFACTSPAGE_SELECT_USER_XPATH = "//select[@dojoattachpoint = 'userSelector']";
  /**
   * Text from the seated Section in artifact editing
   */
  public static final String RMARTIFACTSPAGE_COMMENT_TEXT_XPATH = "//table[@dojoattachpoint ='_commentTable']";
  /**
   * comments i frame space in artifact page.
   */
  public static final String RMARTIFACTSPAGE_COMMENT_IFRAME_XPATH = "//iframe[@frameborder = '0' and @tabindex = '0']";
  /**
   * Configuration Page Settings window in artifacts page.
   */
  public static final String RMARTIFACTSPAGE_CONFIGSETTINGS_XPATH = "//img[@alt = 'Configure Page Settings']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_MOREACTION_BUTTON_XPATH = "//a[@title='More Actions']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_HEADING_XPATH =
      "//div[contains(@widgetid,'com_ibm_rdm_web_grid_ViewHeader')]/table/tbody/tr/td/div/span";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_HEADINGTEXT_XPATH =
      "//div[@class='innerPage']//table//tr/td[DYNAMIC_VAlUE]//div";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_ORDER_XPATH =
      "//div[contains(@widgetid,'com_ibm_rdm_web_grid_ViewHeader')]//tbody//td//span[text()='DYNAMIC_VAlUE']";
  /**
   * Artifact Link Type in artifact page.
   */
  public static final String RMARTIFACTSPAGE_ARTIFACT_LINKTYPE_XPATH =
      "//label[contains(@title , 'Available on all artifact types')]";
  /**
   * Artifact Link Type in artifact page.
   */
  public static final String RMARTIFACTSPAGE_CREATELINK_SHOWATTRIBUTES_RESOURCELINK_XPATH =
      "//*[text()='DYNAMIC_VAlUE']";
  /**
   * Checkbox to show all Work Items related to current Global Configuration
   */
  public static final String RMARTIFACTSPAGE_CREATELINK_SHOWRELATED_WORKITEM_XPATH =
      "//span[@dojoattachpoint='toggleGlobalConfigurationCheckbox']/input";
  /**
   * Artifact Link Type in artifact page.
   */
  public static final String RMARTIFACTSPAGE_CREATELINK_SHOWATTRIBUTES_EXPAND_DIAGRAM_XPATH =
      "//*[text()='DYNAMIC_VAlUE']//parent::span//parent::div//div[@dojoattachevent='onclick:onToggle']";
  /**
   * List of column names in selected artifact page.
   */
  public static final String RMARTIFACTSPAGE_SELECTEDARTIFACT_LINKTYPE_XPATH = "//td[@class = 'selected_name_col']";
  /**
   *
   */
  public static final String RMPROJECT_COLLECTIONSELECTION_ARTIFACT_XPATH = "//div[@class='content-area']/a";


  /**
   *
   */
  public static final String RMARTIFACTSPAGE_BROWSEOPTION_XPATH = "//td[contains(@aria-label,'DYNAMIC_VAlUE')]/div/img";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_ARTIFACTTYPECHECKBOX_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/../../../preceding-sibling::td";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_ARTIFACTTYPEDDOWN_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/../../../following-sibling ::td/div/div/span";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_ARTIFACTTYPE_XPATH =
      "//span[contains(@title, 'The type of the artifact')]/../../../following-sibling :: td[1]/descendant::input[@role='button presentation' and @aria-hidden='true']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_FILTERREMOVEBUTTON_XPATH = "//img[@title='Clear all filters']";
  /**
   * XPath to entering component in "Where to link to:" in Create Link dialog when creating Link To
   */
  public static final String RMARTIFACTSPAGE_COMPONENTSFILTERINGSELECT_CREATELINK_XPATH =
      "//input[@id='com_ibm_rdm_web_common_ArtifactInstancesFilteringSelect_0']";
  // MoveArtifacts
  /**
   *
   */
  public static final String RMARTIFACTPAGE_SERCHTHEARTIFACTNUMBER_XPATH =
      "(//div[@class='filter-pane-search-div']/div/div/input)[2]";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_MODULE_MOVEARTIFACT_XPATH =
      "//*[contains(text(), 'Move Artifact to Folder...')]";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_SELECTTHEPROJECTFOLDER_XPATH =
      "(//span[@class='dijitTreeContent dijitTreeContentExpanded'])[4]";

  // GenerateReport
  /**
   *
   */
  public static final String RMARTIFACTPAGE_SELECTREPORTTYPE_LISTITEMS_XPATH = "//span[text() = 'DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_REPORTNAME_TEXTBOX_XPATH =
      "//div[@dojoattachpoint='_reportNameFieldNode']//input";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_REPORTGENMSG_LABEL_XPATH =
      "//div[@dojoattachpoint='messageArea']//span[text() = 'Report generation complete']";
  /**
   * More action button in artifact page.
   */
  public static final String RMARTIFACTSPAGE_SELECTMOREACTIONDROWPDOWNBUTTON_XPATH = "//a[@title='More Actions']";
  /**
   * delete artifact text in artifact page.
   */
  public static final String RMARTIFACTSPAGE_MOREACTIOM_SELECTDELETEARTIFACT_XPATH = "//*[contains(text(), 'Delete')] ";
  /**
   * delete artifact button in artifact page.
   */
  public static final String RMARTIFACTSPAGE_MOREACTIOM_DELETEARTIFACT_XPATH = "//button[text()='Delete Artifact']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTUPLOADARTIFACT_BUTTON_XPATH =
      "//*[contains(text(),'Upload Artifact...')]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_UPLOADARTIFACTDROPDOWN_XPATH =
      "//select[@dojoattachpoint='_mimeTypeDropDown']";
  /**
   *
   */
  public static final String RMARTIFACTSPAG_UPLOADARTIFACTFILEINDESKTOP_XPATH = "(//input[@type='file'])[3]";
  /**
   * click on submit button.
   */
  public static final String RMARTIFACTSPAGE_SELECTOK_BUTTON_XPATH = "//button[@type='submit']";

  // ImportArtifacts
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_CREATEARTIFACT_BUTTON_XPATH = "//span[text()='Create']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTIMPORTTHEARTIFACT_XPATH =
      "//*[contains(text(), 'Import Artifact...')]";
  /**
   * clicking on radio button for select the text document.
   */
  public static final String RMARTIFACTSPAGE_SELECTTEXTDOCUMENT_RADIOBUTTON_XPATH =
      "//strong[text()='DYNAMIC_VAlUE']/../preceding-sibling::div/input";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTTEXTDOCUMENTADDFILEBUTTON_XPATH =
      ".//*[@id='com_ibm_rdm_web_external_RMUploadWidget_0']/table/tbody/tr[1]/td[2]/span[1]/input";
  /**
   * upload button for upload the text document.
   */
  public static final String RMARTIFACTSPAGE_DOCUMENT_UPLOADBUTTON_XPATH =
      "//input[@name='uploadedfiles[]']/following-sibling::span";
  /**
   * upload a file in import requirements from a csv file.
   */
  public static final String RMARTIFACTSPAGE_CSVFILE_UPLOADBUTTON_XPATH = "//input[@name='dataStream']";
  /**
   * upload button for import artifact page.
   */
  public static final String RMARTIFACTSPAGE_UPLOADBUTTON_XPATH = "//input[@name='upload']";

  /**
   *
   */
  public static final String RMARTIFACTSPAGE_TEXTDOCUMENTUPLOADED_XPATH =
      "//div[@class='dijitProgressBarFull' and  @style='width: 100%;']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTTEXTDOCUMENTREMOVEALLBUTTON_XPATH = "//span[text()='Remove All']";
  /**
   * text document in import artifact page.
   */
  public static final String RMARTIFACTSPAGE_SELECTTEXTDOCUMENTHEADER1_XPATH =
      "//h1[text()='Specify how to identify requirements']";
  /**
   * extract requirements in artifact page.
   */
  public static final String RMARTIFACTSPAGE_SELECTTEXTDOCUMENTHEADER2_XPATH = "//h1[text()='Extract Requirements']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SUCCESSFULLMSG_XPATH = "//div[contains(text(),'The import is complete')]";
  /**
   * error message display if select wrong document.
   */
  public static final String RMARTIFACTSPAGE_ERRORMSG_XPATH = "//div[@class='messageArea ERROR']/div[2]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTFINISH_BUTTON_XPATH = "//button[text()='Finish']";


  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTCSVFILE_RADIOBUTTON_XPATH =
      "//strong[text()='Import requirements from a CSV file or spreadsheet']/../preceding-sibling::div/input";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTMIGRATIONPACKAGE_RADIOBUTTON_XPATH =
      "//strong[text()='Import requirements from a migration package']/../preceding-sibling::div/input";
  /**
   * message summary for display the csv document successfully uploaded.
   */
  public static final String RMARTIFACTSPAGE_CSVSUCCESSMSG_XPATH = "//div[@class='messageSummary']";
  /**
   * message box.
   */
  public static final String RMARTIFACTSPAGE_DETAILSMSG_XPATH = "//div[@class='detailsBox']";
  /**
   * message area.
   */
  public static final String RMARTIFACTSPAGE_DOCUSUCCESSMSG_XPATH = "//div[@class='messageArea OK']/div[2]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTCOMPONENTORPROJECT_RADIOBUTTON_XPATH =
      "//strong[contains(text(),'ReqIF file in this component or project')]/../preceding-sibling::div/input";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTCOMPONENTORPROJECTREQ1_XPATH =
      "(//td [@class='Title result-set-column-cell'])[1]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_COMPONENTORPROJECT_SUCCESSMSG_XPATH =
      "//*[contains(text(),'The import is')]";

  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTRICHTEXTARTIFACT_RADIOBUTTON_XPATH =
      "//strong[contains(text(),'rich text artifact')]/../preceding-sibling::div/input";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTREQF_RADIOBUTTON_XPATH =
      "(//input[@class='dijitReset dijitCheckBoxInput'])[3]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_BROWSETHEREQFFILE_XPATH = "//input[@type='file']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_BROWSETHEREQFFILEANDCLICKUPLOADBUUTTON_XPATH = "//button[text()='Upload']";
  /**
   * close button in import artifact page.
   */
  public static final String RMARTIFACTSPAGE_CLOSETHEREQFWIZARD_XPATH = "//button[text()='Close']";
  /**
   * upload button in import artifact page.
   */
  public static final String RMARTIFACTSPAGE_RICHTEXTBROWSE_BUTTON_XPATH = "//input[@name='upload']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_RICHTEXTSUCCESS_MASSAGE_XPATH =
      "//div[text()='The document was converted.']";
  // CreateModule
  /**
   * create button in artifact page.
   */
  public static final String RMARTIFACTSPAGE_CREATE_BUTTON_XPATH = "//span[text() = 'Create']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_CREATEEXPANDED_BUTTON_XPATH = "//span[text() = 'Create']/..";
  /**
   * Artifact types.
   */
  public static final String RMARTIFACTSPAGE_ARTIFACTTYPE_LINKTEXT_LINKTEXT = "//a[text() = 'DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_MODULE_CLICKTHEMODULETAB_XPATH = "(//a[text()='Modules'])[2]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_MODULE_SELECTCREATETHEMODULE_XPATH = "(//span[text()='Create'])[2]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_MODULE_DROPDOWNSELECTMODULE_XPATH = "//td[text()='Module']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_MODULE_SENDTHENAMETOMODULE_XPATH =
      "(//div[@class='rdm-control rdm-col-8']/div/div[2])[1]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_MODULE_SELECTARTIFACTTYPE_XPATH =
      "(//input[@role='button presentation'])[3]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_MODULE_SELECTTEMPLATETYPE_XPATH =
      "(//input[@role='button presentation'])[5]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_MODULE_SAVETHEMODULE_XPATH = "//button[text()='OK']";
  // EditModule
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_GETMODULETEST_XPATH = "//span[@class='resource-id']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTDROPDOWNMODULE_XPATH = "//span[contains(text(),'Test Module')]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_EDITTHEMODULE_XPATH = "(//button[text()='Edit'])[1]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_EDITTHEMODULETEXTBOX_XPATH = "//textarea[@aria-disabled='false']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_CLICKTHEDONEBUTTON_XPATH = "(//button[text()='Done'])[1]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_CLICKTHEARTIFACTINENTRYLABEL_XPATH =
      "//span[contains(@class,'entry-label')]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_CLICKTHEADDANDCLOSEBUTTON_XPATH = "//button[@type='submit']";

  // DeleteModule

  /**
   *
   */
  public static final String RMARTIFACTSPAGE_MODULESERACHSELECTDROPDOWNANDSELECTDELETE_XPATH =
      "//td[text()='Delete Artifact...']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_MODULE_DELETEMODULE_XPATH = "//button[text()='Delete Artifact']";

  // CreateCopllections
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_CLICKTHECOLLECTIONS_XPATH = "(//a[text()='Collections'])[2]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_CLICKTHECREATECOLLECTIONS_XPATH =
      "(//span[text()='Create'])[2]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_SELECTCREATECOLLECTIONS_XPATH =
      "//td[text()='Test Plan Collection']";
  /**
   * name text box in create artifact page.
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_SENDTHENAMETOCOLLECTIONS_XPATH =
      "(//div[@class='rdm-control rdm-col-8']/div/div[2])[1]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_ARTIFACTTYPEDROPDOWN_XPATH =
      "(//input[@role='button presentation'])[3]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_SELECTTEMPLATEDROPDOWN_XPATH =
      "(//input[@role='button presentation'])[5]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_CLICKOKBUTTON_XPATH = "//button[text()='OK']";

  // EditCollections
  /**
   * get the collection Id.
   */
  public static final String RMARTIFACTSPAGE_GETCOLLECTIONSTEST_XPATH = "//span[@class='resource-id']";
  /**
   * select the dropdown button.
   */
  public static final String RMARTIFACTSPAGE_SELECTDROPDOWNCOLLECTIONS_XPATH =
      "//span[contains(text(),'Test Collections')]";
  /**
   * click on add button in artifact page.
   */
  public static final String RMARTIFACTSPAGE_CLICKADDTHEARTIFCATSDROPDOWN_XPATH = "//span[text()='Add']";
  /**
   * click on control button in list of collections.
   */
  public static final String RMARTIFACTSPAGE_CLICKTHEREQUIREMENTINDROPDOWN_XPATH = "//td[text()='CONTROL']";
  /**
   * Initial content text field in artifact page.
   */
  public static final String RMARTIFACTSPAGE_SENDINCIALCANTENTANDNAMETOCOLLECTIONARTIFACT_XPATH =
      "//div[@class='rdm-control rdm-col-8']/textarea";
  /**
   * click on button.
   */
  public static final String RMARTIFACTSPAGE_SELECTOKBUTTONINCOLLECTIONREQ_XPATH = "//button[@type='submit']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_MODIFIEDARTIAFCTCLICKYESBUTTON_XPATH = "//button[text()='Yes']";
  /**
   * save button in artifact page.
   */
  public static final String RMARTIFACTSPAGE_SAVETHEARIFCATINCOLLECTIONS_XPATH = "//button[text()='Save']";

  // DeleteCollections
  /**
   * search box for selecting the artifact.
   */
  public static final String RMARTIFACTSPAGE_COLLECTIONS_SEARCHBOX_XPATH =
      "(//div[@class='filter-pane-search-div']/div/div/input)[2]";
  /**
   * click on dropdown button in artifact page.
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_SELECTIMGDROPDOEN_XPATH =
      "//*[@id='com_ibm_rdm_web_grid_Module_1']/div/div/table/tbody/tr[1]/td[2]/div/img";
  /**
   * delete artifact in artifact page.
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_SELECTDELETARTIFACTS_XPATH =
      "//td[text()='Delete Artifact...']";
  /**
   * delete artifact button for deleting the artifact.
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_DELETARTIFACTS_XPATH = "//button[text()='Delete Artifact']";
  // Manage Component Properties

  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_SELECTPAGE_XPATH =
      "//div[@role='tablist']//span[text()='DYNAMIC_VAlUE']";
  /**
  *
  */
  public static final String RMARTIFACTSPAGE_ATTRIBUTE_MAIN_COL_XPATH =
      "//td[@class='attribute_main_col']//span[text()='DYNAMIC_VALUE']";
 
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_NAMEFIELD_XPATH =
      "//label[contains(text(),'Name:')]/../following-sibling ::td/div/div[2]/input";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_AT_NAMEFIELD_XPATH =
      "//label[contains(text(),'Name:')]/following-sibling ::div/div[2]/input";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_DESFIELD_XPATH =
      "//label[contains(text(),'Description:')]/../following-sibling ::td/textarea";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_INCLUDECHECKBOX_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/../preceding-sibling::td/div/input";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_SELECTMODULE_XPATH =
      "//span[@class='dijitTreeContent dijitTreeContentExpanded']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_CONFIG_XPATH =
      "//td[text()='DYNAMIC_VAlUE']/following-sibling :: td/div";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_CONFIGTYPE_XPATH = "//td[@dojoattachpoint = '_configTypeNode']/span";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_SEARCH_TEXT_XPATH =
      "//input[@placeholder='Type to search (enter * to show all)']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_LIST_XPATH = "//tbody[@role='listbox']/tr/td";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH = "//div[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_REQIF_DROPDOWN_XPATH =
      "//div[text()='DYNAMIC_VAlUE']/../../../following-sibling :: td/div/img";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_ARTIFACTTYPE_DROPDOWN_XPATH =
      "//div[text()='DYNAMIC_VAlUE']/../../../../../../following-sibling::td/div/img";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_MENU_XPATH = "//td[text()='DYNAMIC_VAlUE']";
  /**
   * XPATH for EXPORT COMPLETED MESSAGE
   */
  public static final String MANAGECOMPPROPERTIES_EXPORT_COMPLETED_MESSAGE_XPATH =
      "//*[text()='Creating ReqIF File']/ancestor::div[@role='dialog']//div[@class='messageSummary']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_IMPORTPROPERTIES_MSG_XPATH = "//div[@class='messageSummary']";
  /**
   * Xpath to find Progeress bar
   */
  public static final String MANAGECOMPPROPERTIES_PROGRESSBAR_XPATH =
      "//div[@class='dijitProgressBar dijitProgressBarEmpty progress-bar']";
  /**
   * Xpath to find 100% Progeress bar
   */
  public static final String MANAGECOMPPROPERTIES_DONE_PROGRESSBAR_XPATH =
      "//div[@role='progressbar']//div[text()='100%']";
  /**
   * Xpath to find Widget Title
   */
  public static final String MANAGECOMPPROPERTIES_WIDGET_XPATH =
      "//div[text()='Select Modules for Export' or @title='Select Modules for Export' and @class='header-primary']";
  /**
   * Xpath to find tabs
   */
  public static final String MANAGECOMPPROPERTIES_TABS_XPATH = "//span[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath to find button
   */
  public static final String MANAGECOMPPROPERTIES_BUTTON_XPATH = "//button[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath to scroll next
   */
  public static final String MANAGECOMPPROPERTIES_ARROW_XPATH =
      "//div[@id=\"dijit_layout_TabContainer_0_tablist_rightBtn\"]";
  /**
   * Xpath to find invisible text filed
   */
  public static final String MANAGECOMPPROPERTIES_TEXTBOXINVISIBLE_XPATH =
      "//div[@class=\"dijit dijitReset dijitInline dijitLeft noMargin text-input dijitTextBox dijitValidationTextBox dijitTextBoxError dijitValidationTextBoxError dijitError dijitTextBoxFocused dijitValidationTextBoxFocused dijitTextBoxErrorFocused dijitValidationTextBoxErrorFocused dijitErrorFocused dijitFocused\"]";
  /**
   *
   */

  public static final String MANAGECOMPPROPERTIES_EXPORTMENU_XPATH =
      "//div[@dojoattachpoint = '_packageGridDiv']//div[2]//div/table//td[1]/div";

  // Artifact Data Type
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_ENUM_RADIOBUTTON_XPATH =
      "//input[contains(@id , '_listRadionBtn' ) and @type = 'radio']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_BOUNDARY_RADIOBUTTON_XPATH =
      "//input[contains(@id , '_rangeRadioBtn' ) and @type = 'radio']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_SIMPLE_RADIOBUTTON_XPATH =
      "//input[contains(@id , '_noneRadioBtn' ) and @type = 'radio']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_BASEDATATYPE_XPATH = "//table[@role='listbox']//tr/td[2]";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_SELECTTYPE_XPATH = "//td[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_MINIMAX_TEXTFIELD_XPATH =
      "//label[contains(text() , 'DYNAMIC_VAlUE')]/../following-sibling :: td//span//div/div[2]/input[1]";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_MULTIPLEVALUE_TEXTFIELD_XPATH =
      "//textarea[@dojoattachpoint = '_addMultipleValuesTextArea']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_URI_TEXTFIELD_XPATH =
      "//label[text() = 'URI:']/../following-sibling :: td/div/div[2]/input";
  /**
  *
  */
 public static final String MANAGECOMPPROPERTIES_IMPORT_COMPONENT_PROPERTIES_FROM_AN_EXISTING_COMPONENT_XPATH =
     "//div[@class='linkSection']//a[contains(text(),'DYNAMIC_VAlUE')]";
 /**
 *
 */
public static final String MANAGECOMPPROPERTIES_INITIALVALUE_TEXTFIELD_XPATH =
    "//label[text() = 'Initial Value:']/..//following-sibling :: td//textarea";
 
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_ALT_LIST_XPATH =
      "//div[@class='atl_title_col coloredLabel gridCellWrapper']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_MUTIPLEURI_XPATH =
      "//div[@class = 'multiSelectionTableBody']//tbody/tr";
  // Artifact Attribute
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_SELECTDATATYPE_XPATH =
      "//div[@class='singleSelectionTableBody']//tbody//tr/td[1]";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_DATATYPEBOX_XPATH = "//div[@class='singleSelectionTableBody']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_SINGLE_RADIOBUTTON_XPATH =
      "//input[contains(@id , '_singleValuedRadioButton')]";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_MULTIPLE_RADIOBUTTON_XPATH =
      "//input[contains(@id , '_multiValuedRadioButton')]";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_INITIAL_TEXTFIELD_XPATH =
      "//label[contains(text(),'Initial Value:')]/../following-sibling :: td//span//div/div[1]";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_INITIAL_TEXTBOX_XPATH =
      "//label[contains(text(),'Initial Value:')]/../following-sibling :: td//span//div/div[3]/input";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_MULTIPLEVALUE_CHECKBOX_XPATH =
      "//label[starts-with(text(),'DYNAMIC_VAlUE')]";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_ADT_LIST_XPATH =
      "//div[@class='adl_title_col coloredLabel gridCellWrapper']";

  // Artifact Types
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_DEFAULTARTIFACTFORMATE_CHECKBOX_XPATH =
      "//label[contains(text(),'Default artifact format:')]/../span//div[3]/input[1]";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_URI_TEXTFIELDS_XPATH =
      "//label[text() = 'URI:']/following-sibling :: div/div[2]/input";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_ATTRIBUTE_DATATYPE_XPATH = "//td[@class='aa_title_col']//div/div";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_ATTRIBUTE_WINDOW_XPATH =
      "(//div[contains(@id,'com_ibm_rdm_web_grid_Module') and @dojoattachpoint='containerNode'])[4]";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_AT_LIST_XPATH =
      "//div[@class='otl_title_col coloredLabel gridCellWrapper']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_AT_URI_TEXTFIELD_XPATH =
      "//label[text() = 'URI:']/following-sibling :: div/div[2]/input";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_AT_ATTRIBUTELIST_XPATH =
      "//div[@class='o_title_col gridCellWrapper']";

  // ExportArtifactwithinCollections
  /**
   * click on Export Artifact
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_EXPORTARTIFACT_XPATH = "//td[text()='Export Artifact...']";
  /**
   * click on 'ok' button.
   */
  public static final String RMARTIFACTSPAGE_COLLECTION_CLICKEXPORTOKBUTTON_XPATH = "//button[text()='OK']";

  // ReportsPage
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_BROWSEREPORTS_XPATH =
      "//*[@id='jazz_app_internal_CustomNavbar_0']/div[1]/a[3]";

  // CreateComponent
  /**
   * select the create component button.
   */
  public static final String RMARTIFACTSPAGE_SELECTCREATECOMPONENT_BUTTON_XPATH =
      "//img[@class='dijitIcon dijitMenuItemIcon icons-create-component']/../following-sibling::td[1]";
  /**
   * pass the component name in component text filed.
   */
  public static final String RMARTIFACTSPAGE_SELECTCOMPONENTNAME_TEXTBOX_XPATH =
      "//div[@class='dijitReset dijitInputField dijitInputContainer']/input";
  /**
   * Description text filed in create component page.
   */
  public static final String RMARTIFACTSPAGE_SELECTCOMPONENTDESCRIPTION_TEXTBOX_XPATH = "//textarea[@value='']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTNEXTBUTTON_XPATH = "//button[@dojoattachpoint='wizardNextButton']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_NEWITERATIONTYPE_XPATH = "//img[@alt='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_CHOOSEPROJECTAREA_XPATH =
      "//span[@class='MenuItemContent']/span[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_CHOOSECOMPONENT_XPATH =
      "//tbody[@role='listbox']/descendant::span[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_ITERATIONTYPEFIELDS_XPATH = "//input[@aria-label='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_ADMINISTRATION_XPATH = "//a[@title='Administration']";
  // ArchiveComponent
  /**
   * Archive this component button.
   */
  public static final String RMARTIFACTSPAGE_SELECTARCHIVECOMPONENT_XPATH =
      "//img[contains(@alt,'Archive this component')]";
  /**
   * Archive button for archive the component.
   */
  public static final String RMARTIFACTSPAGE_SELECTARCHIVECOMPONENT_ARCHIVEBUTTON_XPATH = "//button[text()='Archive']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTACTIONSDROPDOWN_XPATH =
      "//a[@aria-pressed='false']/img[@alt='Actions']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTDROPDOWNARCHIVE_XPATH = "//span[text()='Archive']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTDROPDOWNARCHIVEOK_XPATH = "//span[text()='OK']";
  /**
   * component icon in artifact page.
   */
  public static final String RMARTIFACTSPAGE_SELECTARCHIVEBUTTON_XPATH = "//img[@dojoattachpoint='_iconNode']";

  // ModifyComponent
  /**
   * it's get the modify component name.
   */
  public static final String RMARTIFACTSPAGE_GETCOMPONENTNAME_TEXTBOX_XPATH =
      "//img[@dojoattachpoint='_iconNode']/following-sibling::input";
  /**
   * it's showing and select the modify component in the component text field.
   */
  public static final String RMARTIFACTSPAGE_SELECTCOMPONENTNAME_MODIFYCOMPONENT_TEXTBOX_XPATH =
      "//input[@placeholder='Component Name']";
  /**
   * click on description tab.
   */
  public static final String RMARTIFACTSPAGE_SELECTDESCRIPTION_TEXTBOX_XPATH = "(//span[text()='Description'])[1]";
  /**
   * get the description text in artifact page.
   */
  public static final String RMARTIFACTSPAGE_ADDDESCR_TEXT_XPATH = "//textarea[@placeholder='Add a description.']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTSAVEBUTTON_XPATH = "//button[text()='Save']";


  // Regression Test Cases Properties
  /**
   *
   */
  public static final String RMARTIFACTPAGE_MANAGECOMPONENTSANDCONFIGURATIONS_CLICKTHESTREAM_XPATH =
      "//*[@id=\"configurationTitleNode\"]";
  /**
   * checking Project area Configuration Management enable or not.
   */
  public static final String RMARTIFACTPAGE_MANAGETHISPROJECTAREA_ENABLECONFIGURATIONMANAGEMENT_ENABLE_XPATH =
      "//div[@class='messageArea OK']";
  /**
   * Xpath for 'Explore Project' text present in 'Manage This Project Area' page.
   */
  public static final String RMARTIFACTPAGE_MANAGETHISPROJECTAREA_EXPLOREPROJECT_BUTTON_XPATH =
      "//span[@dojoattachpoint='_exploreProjectAreaSpan']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_SELECT_COMPONENT_XPATH =
      "//h3[text()='Components']/../../descendant::a[text()='DYNAMIC_VAlUE']";
  /**
   * Expand button in manage component and config page.
   */
  public static final String RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIG_STREAM_EXPAND_XPATH = "//a[@title='Expand']";
  /**
   * select the check box in manage component and config page.
   */
  public static final String RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIG_STREAMSELECT_CHECKBOX_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/../../../../td[1]/div/span[1]/a/img";
  /**
   * check Expand Button is Hidden or not.
   */
  public static final String RMARTIFACTSPAGE_EXPAND_BUTTON_XPATH = "//a[@class='expandoNode hidden']";
  /**
   * check CompareConfigration button Enabled or not.
   */
  public static final String RMARTIFACTSPAGE_COMPARECONFIGENABLED_BUTTON_XPATH =
      "//a[@aria-disabled='false']/img[@alt='Compare the selected configurations']";
  /**
   * check the Action check box in selected stream.
   */
  public static final String RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIGACTION_DROPDOWN_XPATH = "//a[@title='Actions']";
  /**
   * text box for create the stream.
   */
  public static final String RMARTIFACTPAGE_CREATESTREAM_TEXTBOX_XPATH = "//input[@id='nameInputBox']";
  /**
   * it display the list of streams present streams tab.
   */
  public static final String RMARTIFACTPAGE_STREAM_PLACEHOLDER_XPATH = "//a[@class='configurationUiNode']";
  /**
   * it showing the stream name.
   */
  public static final String RMARTIFACTPAGE_STREAM_NAME_XPATH =
      "//a[@class='configurationUiNode']/span/span[text()='DYNAMIC_VAlUE']";
  /**
   * clicking on collapse Stream Button in manage component and configuration.
   */
  public static final String RMARTIFACTPAGE_STREAM_COLLAPSE_XPATH = "//a[@class='expandoNode collapseNode']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_HEADINGTEXT_BOX_XPATH = "//h1/div[2]";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_MODULEINNERARTIFACT_IDS_XPATH =
      "//div[@dojoattachpoint='moduleContentPane']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_CHILDCLOSE_BUTTON_XPATH =
      "(//div[@aria-label='Toggle Children' and @class='twistie'])[1]";
  /**
   * XPath expression for link to first module audit history page.
   */
  public static final String FIRST_PAGE_LINK_XPATH = "//a[text()='1']";
  /**
   * Constant String variable to hold text "Module1 artifacts".
   */
  public static final String MODULE_ARTIFACTS = "Module1 artifacts";
  /**
   * Xpath for search button inside module.
   */
  public static final String RMARTIFACTPAGE_MODULEARTIFACTSEARCH_BUTTON_XPATH =
      "//a[@class='button' and @title='Find/Go To']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_MODULEARTIFACTMASSAGE_BOX_XPATH = "//div[@class='messageSummary']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_MODULEARTIFACTSEARCH_HIGHLIGHT_XPATH = "//p[text()='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_SELECTMODULE_XPATH =
      "//span[@class='dijitTreeContent dijitTreeContentExpanded']";
  /**
   * this x-path shows the discard the change set button.
   */
  public static final String RMARTIFACTPAGE_CHANGESETS_BOX_XPATH =
      "//span[text()='Discard the Change Set' and @data-dojo-attach-point='containerNode']";
  /**
   * 'include the change sets from other users' button in manage component and configuration.
   */
  public static final String RMARTIFACTPAGE_INCLUDEOTHERSCHANGESET_BUTTON_XPATH =
      "//img[@class='button-img icons-all-users']";
  /**
   * Refresh button in manage component and configuration page.
   */
  public static final String RMARTIFACTPAGE_REFRESH_BUTTON_XPATH =
      "//div[@class='rightActionsNode']/descendant::img[@class='button-img icons-refresh']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_MODULEVIEW_BUTTON_XPATH = "//span[@title='Save as new view']";
  /**
   * it showing the content of quick search text box.
   */
  public static final String RMARTIFACTPAGE_SEARCHCONTENT_LINK_XPATH =
      "//div[@class='search-result']/descendant::span[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_MODULEINSIDEARTIFACT_XPATH = "//p[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_FIRSTELEMENTTABLE_XPATH =
      "//div[@class=\"com-ibm-rdm-web-Module\"]/descendant::table[1]";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_SELECTFOLDER_LINK_XPATH =
      "//div[contains(@id,'com_ibm_rdm_web_project')]/descendant::span[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_SELECTFOLDERMENU_LINK_XPATH =
      "//tbody[@class='dijitReset']/descendant::td[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_COMPONENT_DROPDOWN_XPATH =
      "//td[text()='Component:']/following-sibling::td[@class='project-select']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_CREATEA_ARTIFACT_XPATH = "//td[text()='AT']";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_DESCRIPTIONS_TEXTBOX_XPATH = "//textarea[@id='descriptionInput']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_SELECTMODULES_ARTIFACT_XPATH =
      "(//span[@class='dijitTreeContent dijitTreeContentExpanded'])[2]";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_COPYARTIFACTOK_MASSAGE_XPATH = "//div[@class='messageArea OK']";
  /**
   * Configuration context area.
   */
  public static final String RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH = "//iframe[@title='Configuration Picker']";
  /**
   * Xpath for Search box in Select Configuration dialog
   */
  public static final String RMARTIFACTPAGE_SETCONFIGURATON_SEARCH_TEXTBOX_XPATH =
      "//td[@dojoattachpoint='_searchConfigNode']//input";
  /**
   * A frame which specified by iframe tag use to move from main html page to frame page.
   */
  public static final String RMPROJECT_CREATELINK_IFRAME_XPATH = "//iframe[@dojoattachpoint='iframe']";
  /**
   * Xpath for: Title of Operation page
   */
  public static final String RMDASHBOARDPAGE_CHECKING_HEADER_XPATH = "//h1[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * Global Configuration radio button: Global Configuration.
   */
  public static final String RMDASHBOARDPAGE_SWITCHTOGC_CONFIGURATION_XPATH =
      "(//label[contains(@class,'gc-ContextPicker')])[last()-1]";

  /**
   * Global Configuration radio button: Requirements Management Configuration.
   */
  public static final String RMDASHBOARDPAGE_SWITCH_TO_GC_CONFIGURATION_SC_XPATH =
      "(//label[contains(@class,'gc-ContextPicker')])[last()]";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_ARTIFACTCHILD_ID_XPATH =
      "//table[contains(@class,'resourceRow depth2 indent-by-depth heading')]/descendant::a[@href]";
  /**
   *
   */
  public static final String RMDASHBOARDPAGE_COMPARECON_COMPONENTPROP_XPATH =
      "//div[@class='com-ibm-rdm-web-compare compareWidgetParent']";

  // CreateArtifactInsideTheModule
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_CREATEARTIFACTINSIDETHEMODULE_XPATH =
      "//span[@data-dojo-attach-point='_addNew']";
  // RMReportsPage
  /**
   *
   */
  public static final String RMREPORTSPAGE_CLICKTHEGENERATEREPORT_XPATH = "//a[@dojoattachpoint='_generateDocText']";
  // RM Module inside page
  /**
   *
   */
  public static final String RMMODULEINSIDE_ARTIFACT_IDS_XPATH = "//div[@class='id-col gridCellWrapper']";
  /**
   *
   */
  public static final String RMMODULEINSIDE_CONFIGUREPAGESETTINGS_IMG_XPATH =
      "//tr[@role='row']/descendant::img[@class='menuLauncher']";
  /**
   *
   */
  public static final String RMMODULEINSIDE_FILTERATTNAME_FILTERTEXT_XPATH = "//input[@title='DYNAMIC_VAlUE']";
  /**
   *
   */
  public static final String RMMODULEINSIDE_ARTIFACT_ID_XPATH =
      "//td[@colid='id-col']//a[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * Xpath for Attribute values in Module Details page
   */
  public static final String RMMODULEPAGE_ATTRIBUTESECTION_ATTRIBUTEVALUE_XPATH =
      "//div[contains(@widgetid,'SidebarSection') and not(contains(@style,'display:none'))]//label[contains(text(),'DYNAMIC_VAlUE')]//span";
  /**
   *
   */
  public static final String RMMODULEINSIDE_ARTIFACTEDIT_MENU_XPATH =
      "//div[contains(@class,'editmenu com-ibm-rdm-web-grid-EditMenu')]/descendant::td[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   *
   */
  public static final String RMMODULEINSIDE_ARTIFACTID_CHECKBOX_XPATH =
      "//td[contains(@aria-label , 'Artifact DYNAMIC_VAlUE.')]//label";
  /**
   *
   */
  public static final String RMMODULEINSIDE_CREATELINK_SEARCHID_XPATH =
      "//input[@placeholder='Type to filter by text or by ID' and @class='filterText']";
  /**
   *
   */
  public static final String RMMODULEINSIDE_SELECTATT_LINKTYPES_XPATH =
      "//div[@class='attribute_main_col gridCellWrapper']/descendant::label";
  // Paste Special
  /**
   *
   */
  public static final String RMMODULEINSIDE_PLACETHEPASTEDARTIFACT_RADIOBUTTON_XPATH =
      "//label[contains(text(),'DYNAMIC_VAlUE')]/preceding-sibling::input";
  /**
   *
   */
  public static final String RMMODULEINSIDE_PASTESPECIAL_ARTIFACTTYPES_XPATH =
      "//div[@class='sourceCol gridCellWrapper']";
  /**
   *
   */
  public static final String RMMODULEINSIDE_SELECTED_CHECKBOX_XPATH =
      "//label[text()='DYNAMIC_VALUE']/preceding-sibling::div/input[@aria-checked=\"true\"]";
  /**
   *
   */
  public static final String RMMODULEINSIDE_PASTESPECIALLINK_DROPDOWN_XPATH =
      "//span[text()='Select the link type']/ancestor::tbody";
  /**
   *
   */
  public static final String RMMODULEINSIDE_SELECTLINKTYPE_BUTTON_XPATH =
      "//table[@class=\"dijitReset dijitMenuTable\"]/descendant::td[contains(text(),'DYNAMIC_VAlUE')]";


  // Configure Link Display
  /**
   *
   */
  public static final String RMMODULEINSIDE_SELECTLINKTYPE_CHECKBOX_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/../../descendant::input[@type='checkbox']";
  /**
   *
   */
  public static final String RMMODULEINSIDE_SELECTLINK_BUTTON_XPATH = "//span[@title='DYNAMIC_VAlUE']";
  /**
   * Xpath for: Checkbox option in filter when delivery changeset
   */
  public static final String RMDASHBOARDPAGE_FILTER_RADIOBUTTON_XPATH =
      "//table[contains(@style,'visibility: visible;')]//td[text()='DYNAMIC_VAlUE']/parent::tr";
  /**
   * Xpath for: Dropdown filter when delivery changeset with text "Filter Artifacts"
   */
  public static final String RMDASHBOARDPAGE_FILTER_SECTION_DROPDOWN_XPATH =
      "//div[contains(@id,'stepDOM_') and contains(@style,'display: block;')]//span[@title='DYNAMIC_VAlUE']";
  /**
   * Xpath for: Dropdown filter when delivery changeset with text "Filter Artifacts"
   */
  public static final String RMDASHBOARDPAGE_FILTER_TYPE_DROPDOWN_XPATH =
      "//div[contains(@class,'artifactMerge') and contains(@style,'display: block;')]//span[@title='DYNAMIC_VAlUE']";
  // Create Link
  /**
   *
   */
  public static final String RMMODULEINSIDEPAGE_IMPLEMENTED_TEXTBOX_XPATH = "//input[@class='QueryInput']";
  /**
   *
   */
  // Phung code
  public static final String RMMODULEINSIDEPAGE_IMPLEMENTED_LISTWI_XPATH =
      "//div[@id='contentAreaID']//span[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   *
   */
  public static final String RMMODULEINSIDEPAGE_CREATELINKTESTCASEOK_BUTTON_XPATH =
      "//button[@dojoattachevent='onclick:_onOK' and @class='primary-button']";
  /**
   *
   */
  public static final String RMMODULEINSIDEPAGE_CREATELINKNEWTESTCASE_BUTTON_XPATH =
      "//label[text()='Create New']/preceding-sibling::input[@id='newItemCheckBox']";
  /**
   *
   */
  public static final String RMMODULEINSIDEPAGE_CREATELINKTESTPLANDISABLED_BUTTON_XPATH =
      "//button[@class='primary-button' and @disabled]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_UNDERMAINSTREAMSELECTTHECHILDSTREAM_XPATH =
      "//div[contains(@widgetid,'workspaces')]";

  /**
   *
   */
  public static final String RMARTIFACTSPAGE_STREAMSLISTINUNDERMAINSTREAM_XPATH =
      "//span[contains(text(),'Test_Stream')]";

  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTTHETREAMINSTREAMLIST_XPATH =
      "(//span[@class='caret jazz-ui-toolbar-Button-caret'])[1]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTTHESTREAMANDRENAME_XPATH = "//span[text()='Rename']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SELECTTHETEXTRENAME_XPATH = "//input[@data-dojo-attach-point='mainInput']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_ARTIFACTCONTAINER_XPATH =
      "//span[text()='Artifact Container:']/following-sibling::span/descendant::input[@value='▼ ']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_ARTIFACTCONTAINER_TEXTBOX_XPATH =
      "//span[text()='Artifact Container:']/following-sibling::span/descendant::input[@role='textbox']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_CREATE_LINKTYPE_XPATH =
      "//label[text()='Link type:']/../following-sibling::td/descendant::input[@value='▼ ']";
  /**
   * Adding link path with a text specified by DYNAMIC_VALUE. Possible values:"Validated by"
   */
  public static final String RMMODULEINSIDE_SELECT_LINKTYPE_XPATH =
      "//td[@class='dijitReset dijitMenuItemLabel' and starts-with(text() ,'DYNAMIC_VAlUE')]";
  /**
   * Adding link path with a text specified by DYNAMIC_VALUE. Possible values:"Validated by"
   */
  public static final String RMMODULEINSIDE_SELECT_LEFTSIDE_MENU_XPATH =
      "//td[@class='dijitReset dijitMenuItemLabel' and starts-with(text() , 'DYNAMIC_VAlUE')]";
  /**
   * Can be used to find the web element using any text.
   */
  public static final String DYNAMIC_TEXT_XPATH = "//*[text() = 'DYNAMIC_VAlUE']";
  /**
   * COLLECTIONID use to send the collectionID string.
   */
  public static final String COLLECTIONID = "collectionID";
  /**
   * TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID sends the Type to filter artifacts by text or by ID string.
   */
  public static final String TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID = "Type to filter artifacts by text or by ID";
  /**
   * Artifact Type is a string which is used as a constant in RMArtifactPage.
   */
  public static final String ARTIFACTS_TYPE = "Artifact Type";
  /**
   * Dropdown xpath with value same but paragraph content changing in RMArtifactPage
   */
  public static final String DROPDOWN_FOR_HEADING_XPATH = "//p[text() = 'DYNAMIC_VAlUE']/..//input[@value = '▼ ']";
  /**
   * String to identify literal show details
   */
  public static final String SHOW_DETAILS = "show details";
  /**
   * String to identify rich text document import
   */
  public static final String RICH_TEXT_IMPORT = "Import a text document and convert to a rich text artifact";
  /**
   * upload button for import artifact page.
   */
  public static final String RMARTIFACTSPAGE_UPLOADBUTTON_FOR_DOC_XPATH = "//input[@name='uploadedfiles[]']";
  /**
   * xpath for next button
   */
  public static final String RMARTIFACTSPAGE_SELECT_NEXTBUTTON_XPATH = "//button[text()='Next >']";
  /**
   * Select Item use to select the
   */
  public static final String SELECT_ITEM = "Select Item";
  /**
   * String for Items per page
   */
  public static final String ITEMS_PER_PAGE = "Items per page: ";
  /**
   * xpath for ALM relese information
   */
  public static final String REL_VERSION =
      "/html/body/div[3]/div[2]/div[2]/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[2]";
  /**
   * xpath for ALM ifix relese information
   */
  public static final String IFIX_VERSION =
      "/html/body/div[3]/div[2]/div[2]/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[3]";
  /**
   * String to identify literal show details
   */
  public static final String DOWNLOAD = "Download";
  /**
   * String to identify literal show details
   */
  public static final String CLOSE = "Close";
  /**
   * String to identify literal show details
   */
  public static final String ADD_AND_CLOSE = "Add & Close";
  /**
   * String to identify literal show details
   */
  public static final String MORE = "More...";
  /**
   * Constant string variable to hold text "Change Sets".
   */
  public static final String CHANGE_SET = "Change Sets";
  /**
   * Click on close button from the create
   */
  public static final String CLOSE_BUTTON = "//a[@class='closeButton']";
  /**
   * Create link is a window which used in a dialogue to find the searchbox and buttons.
   */
  public static final String CREATE_LINK = "Create Link";
  /**
   * Create to find the Create searchbox and buttons.
   */
  public static final String CREATE = "Create";
  /**
   * Xpath for - Level Name link.
   */
  public static final String LEVEL_NAME_LINK = "//td[contains(text(),'Show ') and contains(text(),' Level')]";

  /**
   * Constant string variable to hold text "Create Artifact".
   */
  public static final String CREATE_ARTIFACT = "Create Artifact";

  /**
   * Constant string variable to hold text "select user".
   */
  public static final String SELECT_USER = "//select[@dojoattachpoint = 'userSelector']/option";
  /**
   * Xpath for search content No results found.
   */
  public static final String SEARCHCONTENT_NORESULTS_FOUND = "//div[contains(text(),'No results found for ')]";

  /**
   * Artifact Type is a string which is used as a constant in RMArtifactPage.
   */
  public static final String ARTIFACTS = "Artifacts";
  /**
   * Xpath for enter view name in 'New View' wizard.
   */
  public static final String RMARTIFACTPAGE_VIEWNAME_TEXTBOX_XPATH =
      "//div[@class='saved-filter-name-field-div']//input";
  /**
   * Xpath for 'New View' wizard in name.
   */
  public static final String RMARTIFACTPAGE_NEW_VIEW_WIZARD_XPATH = "//span[text()='New View']";
  /**
   * Xpath for view is displayed in RM Artifact page.
   */
  public static final String RMARTIFACTPAGE_VIEW_XPATH = "//div[@class='tags-container']//span[text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for condition of filter displayed in RM Artifact page.
   */
  // public static final String RMARTIFACTPAGE_CONDITION_FILTER_XPATH =
  // "//div[normalize-space(@title)='DYNAMIC_VAlUE']";
  public static final String RMARTIFACTPAGE_CONDITION_FILTER_XPATH =
      "//div[@class='condition-div' and starts-with(@title,'DYNAMIC_VAlUE')]";

  /**
   * Xpath for clear filter in view .
   */
  public static final String RMARTIFACTPAGE_CLEAR_FILTER_XPATH = "//img[@title='Clear all filters']";
  /**
   * Xpath for clear filter in Module page .
   */
  public static final String RMMODULE_CLEAR_FILTER_XPATH = "//img[@title='Clear all filters and reset columns']";
  /**
   * Xpath for clear filter in view 7.0 version .
   */
  public static final String RMARTIFACTPAGE_CLEAR_FILTER_XPATH_VERSION7 = 
      "//div[contains(@class,'dijitContentPane dijitContentPaneSingleChild') and not(contains(@style,'display: none'))]//img[@title='Clear all filters and reset columns']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_EDITATTRIBUTES_CHECKBOX_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/ancestor::tr//input[@type='checkbox']";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_EDITATTRIBUTES_TEXTBOX_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/ancestor::tr//textarea";
  /**
   *
   */
  public static final String RMARTIFACTPAGE_EDITATTRIBUTES_PROGRESSBAR_XPATH = "//div[@class='dijitProgressBarLabel']";
  /**
   * Click on a RQM artifact type with specified DYNAMIC_VALUE.
   */
  public static final String RMARTIFACTPAGE_SELECTTAGS_TEXT_XPATH =
      "//div[@title='DYNAMIC_VAlUE0'']/ancestor::div[@class='background']//span[@aria-selected='false' and text()='DYNAMIC_VAlUE1']";
  /**
   * Xpath for Validated by link displayed in table view
   */
  public static final String RMMODULEPAGE_VALIDATEDBY_LINK_XPATH = 
      "//table[@rowlabel='Artifact DYNAMIC_VAlUE0']//td[@colid='val-by']//*[contains(text(),'DYNAMIC_VAlUE1')]/parent::div[@class='label trimLabel']";
  /**
   * Column name available in the change add column display settings.
   */
  public static final String RMMODULEPAGE_COLUMNNAME_TEXT_XPATH =
      "//span[contains(text(),'DYNAMIC_VAlUE')]/ancestor::div[@dojoattachpoint='moduleContentPane']";

  /**
   * Get current configuration in the right conner
   */
  public static final String RMCURRENT_CONFIGURATION_XPATH =
      "//a[@aria-label='Current Configuration Drop-Down Menu']//span[@class='iconNode']/following-sibling::span[@class='titleNode'] | //a[@class='configurationUiNode hideLink']//following-sibling::span[@class='titleNode']";
  /**
   * Success message displayed after creating baseline, stream, change set success
   */
  public static final String CREATE_CONFIGURATION_MESSAGE_SUCCESS_XPATH =
      "//div[@data-dojo-attach-point='successMessageContent']";

  /**
   * Xpath of submenu under current configuration
   */
  public static final String SUBMENU_UNDER_CURRENT_CONFIGURATION_XPATH =
      "//span[contains(@id,'jazz_ui_menu_MenuItem') and contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Xpath to identify the Next button of pagging
   */
  public static final String NEXT_PAGING_BUTTON_XPATH = "//a[contains(@class,'next')]";
  /**
   * Xpath for all the artifacts link present in artifact table.
   */
  public static final String ARTIFACTSPAGE_ALL_ARTIFACTS_LINKS_XPATH =
      "//table[contains(@class,'resourceRow')]//td[3]/descendant::a";
  /**
   * Constant string value to hold text 'Edit Attributes'.
   */
  public static final String EDIT_ATTRIBUTES = "Edit Attributes";
  /**
   * Xpath for all the atrribute type present in filtered list
   */
  public static final String ATTRIBUTE_LIST_IN_NEW_FILTER_XPATH =
      "//div[@class='container']/div[contains(@class,'FilteredListLabelItem')]";

  /**
   * Tooltip of clear filter button in Artifacts page, Module or Collection page
   */
  public static final String CLEAR_FILTER_BUTTON_TOOLTIP = "Clear all filters";

  /**
   * Tooltip of Apply filters button in Artifacts page, Module or Collection page
   */
  public static final String APPLY_ALL_FILTERS_TOOLTIP = "Apply all filters";

  /**
   * Xapth for all component in the Browse Component page
   */
  public static final String COMPONENT_LIST_IN_BROWSE_COMPONENT_XAPTH =
      "//tr/td/img[contains(@class,'componentIcon') or contains(@class,'componentArchivedIcon')]/following-sibling::a";
  /**
   * Xpath for quick search box status information.
   */
  public static final String QUICK_SEARCH_BOX_STATUS_XPATH = "//div[@class='search-area']//div[@class='status-info']";
  /**
   * Xpath for quick search box result
   */
  public static final String QUICK_SEARCH_BOX_RESULT_XPATH =
      "//div[@class='jazz-ui-BannerSearchArea']//div[@class='status-info' and not(text()='Loading...')]";

  /**
   * Xpath for quick search box loading
   */
  public static final String QUICK_SEARCH_BOX_LOADING_XPATH =
      "//div[@class='jazz-ui-BannerSearchArea']//div[@class='status-info' and (text()='Loading...')]";
  /**
   * X path for check-box in the New Filter dialog
   */
  public static final String FILTER_XPATH = "//div[@title='Artifact Type - is any of - DYNAMIC_VAlUE']";


  /**
   * Tool tip of clear filter button in Artifacts page, Module or Collection page
   */


  public static final String STREAMS = "Streams";
  /**
   * X path for all the attribute type present in filtered list
   */
  public static final String RMARTIFACTSPAGE_COLUMNBORDER_DROPDOWN_XPATH =
      "//div[@class='result-set-grid-view']//td[@role='columnheader']";
  /**
   * Tool tip of clear filter button in Artifacts page, Module or Collection page
   */
  public static final String SEARCHED = "Searched '";
  /**
   * Constant string variable to hold text "Clicked on 'OK' button."
   */
  public static final String CLICK_ON_OK_BUTTON = "Clicked on 'OK' button.";
  /**
   * Constant string variable to hold text "Clicked on 'CANCEL' button."
   */
  public static final String CLICK_ON_CANCEL_BUTTON = "Clicked on 'CANCEL' button.";
  /**
   * Constant string variable to hold text "Clicked on 'Apply' button."
   */
  public static final String CLICK_ON_APPLY_BUTTON = "Clicked on 'APPLY' button.";
  /**
   * Tool tip of clear filter button in Artifacts page, Module or Collection page
   */
  public static final String NEW_VIEW = "New View";
  /**
   * Tool tip of clear filter button in Artifacts page, Module or Collection page
   */
  public static final String PROJECT_AREA = "ProjectArea";
  /**
   * Tool tip of clear filter button in Artifacts page, Module or Collection page
   */
  public static final String CLASS = "class";
  /**
   * Tool tip of clear filter button in Artifacts page, Module or Collection page
   */
  public static final String NAVIGATE_TO_PARENT = "./../../..";
  /**
   * Tool tip of clear filter button in Artifacts page, Module or Collection page
   */
  public static final String EXPANDED = "expanded";
  /**
   * Tool tip of clear filter button in Artifacts page, Module or Collection page
   */
  public static final String FROM_LINK_TYPE_DROPDOWN = " from link type dropdown.";
  /**
   * Tool tip of clear filter button in Artifacts page, Module or Collection page
   */
  public static final String BUTTON = " button.";
  /**
   * Constant string variable to hold text " checkbox."
   */
  public static final String CHECKBOX = " checkbox.";
  /**
   * Constant string variable to hold text "Clicked on "
   */
  public static final String CLICKED_ON = "Clicked on ";
  /**
   * Constant string variable to hold text "Clicked on Artifact "
   */
  public static final String CLICKED_ON_ARTIFACT = "Clicked on Artifact ";
  /**
   * Constant string variable to hold text " is displayed"
   */
  public static final String IS_DISPLAYED = " is displayed.";
  /**
   * Constant string variable to hold text " is selected"
   */
  public static final String IS_SELECTED = " is selected.";
  /**
   * CLICKED_ON
   */
  public static final String CHOOSE_ANOTHER_COMPONENT = "Choose Another Component...";
  /**
   * CLICKED_ON
   */
  public static final String SWITCH = "Switch";
  /**
   * CLICKED_ON
   */
  public static final String KEY = "KEY :";
  /**
   * CLICKED_ON
   */
  public static final String CREATED_ON = "Created On";
  /**
   * CLICKED_ON
   */
  public static final String VALUE = " VALUE :";
  /**
   * CLICKED_ON
   */
  public static final String ACTUAL_VALUE_OF_ATTRIBUTE = "Actual value of attribute '";
  /**
   * CLICKED_ON
   */
  public static final String IS = "' is '";
  /**
   * CLICKED_ON
   */
  public static final String VERIFY_THE_ARTIFACT_AFTER_CLICK_ON_THE = "Verify the artifact after click on the '";
  /**
   * CLICKED_ON
   */
  public static final String VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE =
      "Verify the displayed dialog after click on the '";
  /**
   * Xpath for Edit attribute text box.
   */
  public static final String EDIT_ATTRIBUTE_ARTIFACT_TYPE_TEXTBOX_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/ancestor::tr//input[@role='textbox']";
  /**
   * Xpath for Edit attribute text box.
   */
  public static final String EDIT_ATTRIBUTE_TEXTBOX_XPATH = "//span[text()='DYNAMIC_VAlUE']/following::textarea";
  /**
   * CLICKED_ON
   */
  public static final String EXPORT = "Export";
  /**
   * CLICKED_ON
   */
  public static final String LINK_BY_ATTRIBUTE = "Link by Attribute";
  /**
   * CLICKED_ON
   */
  public static final String OF_ARTIFACT_ACTUAL_IS_THE = "' of artifact.\nActual is the '";
  /**
   * CLICKED_ON
   */
  public static final String THE = "The '";
  /**
   * CLICKED_ON
   */
  public static final String ACTUAL_IS = "'.\nActual is ";
  /**
   * CLICKED_ON
   */
  public static final String OF = "' of '";
  /**
   * CLICKED_ON
   */
  public static final String DESCRIPTION = "Description";
  /**
   * CLICKED_ON
   */
  public static final String CUSTOMER_ID = "Customer ID";
  /**
   * CLICKED_ON
   */
  public static final String CUSTOMER_COMMENT = "Customer Comment";
  /**
   * CLICKED_ON
   */
  public static final String HISTORY_LOG = "History Log";
  /**
   * CLICKED_ON
   */
  public static final String VERIFIED_NAVIGATION_OF = "Verified navigation of '";
  /**
   * CLICKED_ON
   */
  public static final String VERIFY_THE_DIALOG_AFTER_CLICKING_ON = "Verify the dialog after clicking on '";
  /**
   * CLICKED_ON
   */
  public static final String UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE =
      "' under Current Configuration menu.\nActual Result is the '";
  /**
   * CLICKED_ON
   */
  public static final String DIALOG_IS_DISPLAYED_EXPECTED_IS_THE = "' dialog is displayed.\\nExpected is the '";
  /**
   * CLICKED_ON
   */
  public static final String DIALOG_SHOULD_BE_DISPLAYED = "' dialog should be displayed";
  /**
   * CLICKED_ON
   */
  public static final String DIALOG_IS_NOT_DISPLAYED_EXPECTED_IS_THE = "' dialog is NOT displayed.\\nExpected is the '";
  /**
   * CLICKED_ON
   */
  public static final String VERIFY_THE_PAGE_AFTER_CLICKING_ON = "Verify the page after clicking on '";
  /**
   * CLICKED_ON
   */
  public static final String SHOULD_BE_CREATED = "' should be created.";
  /**
   * CLICKED_ON
   */
  public static final String VERIFIED_THE = "Verified the '";
  /**
   * CLICKED_ON
   */
  public static final String VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON =
      "Verify the displayed dialog after click on '";
  /**
   * CLICKED_ON
   */
  public static final String MENU_FROM_ACTIONS_DROPDOWN_MENU_OF = "' menu from Actions dropdown menu of '";
  /**
   * Constant string variable to hold the text "'.\nActual is the Archive Warning dialog is displayed".
   */
  public static final String ACTUAL_IS_THE_ARCHIVE_WARNING_DIALOG_IS_DISPLAYED =
      "'.\nActual is the Archive Warning dialog is displayed";
  /**
   * Constant String variable to hold text "Archive".
   */
  public static final String ARCHIVE = "Archive";
  /**
   * Constant String variable to hold text "Create Stream".
   */
  public static final String CREATE_STREAM = "Create Stream";
  /**
   * Constant String variable to hold text "Rename".
   */
  public static final String RENAME = "Rename";
  /**
   * Constant String variable to hold text "Rename Baseline".
   */
  public static final String RENAME_BASELINE_DIALOG = "Rename Baseline";
  /**
   * Constant String variable to hold text "Export View..."
   */
  public static final String EXPORT_VIEW = "Export View...";
  /**
   * Constant String variable to hold text "Export View..."
   */
  public static final String GENERAL_REPORT_FOR_VIEW = "Generate Report for View...";
  /**
   * Constant String variable to hold text "Export View..."
   */
  public static final String EDIT_VIEW = "Edit View...";
  /**
   * Constant String variable to hold text "Edit Attributes from View..."
   */
  public static final String EDIT_ATTRIBUTE_FROM_VIEW = "Edit Attributes from View...";
  /**
   * Constant String variable to hold text "Link by Attribute..."
   */
  public static final String LINK_BY_ATTRIBUTE_TEXT = "Link by Attribute...";
  /**
   * Constant String variable to hold text "Delete View..."
   */
  public static final String DELETE_VIEW = "Delete View...";
  /**
   * Constant String variable to hold text "Share Link to View..."
   */
  public static final String SHARE_LINK_TO_VIEW = "Share Link to View...";
  /**
   * Xpath for disable button.
   */
  public static final String RMPROJECT_DISABLE_BUTTON_XPATH =
      "//button[contains(text(),'DYNAMIC_VAlUE') and @disabled='']";
  /**
   * xpath for add link to artifact button.
   */
  public static final String ADD_LINK_TO_ARTIFACT_BUTTON_XPATH =
      "//div[@class='section-header section-header-expanded']/following-sibling::div//span[@title='Add Link to Artifact']";
  /**
   * Centered message after deleting a artifact folder.
   */
  public static final String CENTERED_MESSAGE =
      "To show artifacts, either select a folder or view, or create a new filter.";
  /**
   * Constant String variable to hold text "Delete Artifact".
   */
  public static final String DELETE_ARTIFACT = "Delete Artifact";
  /**
   * Constant string variable to hold text "Clicked on '".
   */
  public static final String CLICKED_ON_TYPE = "Clicked on '";
  /**
   * Constant string variable to hold text "Confirm Deletion".
   */
  public static final String CONFIRM_DELETION = "Confirm Deletion";
  /**
   * Constant string variable to hold text '.\n\nExpected is all values should be '.
   */
  public static final String EXPECTED_IS_ALL_VALUES_SHOULD_BE = "'.\n\nExpected is all values should be '";
  /**
   * Constant string variable to hold text "Verify data of the '".
   */
  public static final String VERIFY_DATA_OF_THE = "Verify data of the '";

  /**
   * xpath for artifact folder
   */
  public static final String ARTIFACT_FOLDER_XPATH = 
      "//span[@class='dijitTreeLabel' and contains(text(),'DYNAMIC_VAlUE')]/ancestor::div[@data-dojo-attach-point='rowNode']";
  /**
   * xpath for menu on artifact folder
   */
  public static final String MENU_ON_ARTIFACT_FOLDER_XPATH =
      "//span[@class='dijitTreeLabel' and contains(text(),'DYNAMIC_VAlUE')]/ancestor::div[@data-dojo-attach-point='rowNode']//div[contains(@class,'button-tree')]";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_PROJECT_FOLDER_XPATH =
      "//span[text()='DYNAMIC_VAlUE' and @role='treeitem']";
  /**
   * Constant string variable to hold text "Confirm Deletion".
   */
  public static final String ARIA_SELECTED = "aria-selected";
  /**
   * Xpath for view column header.
   */
  public static final String VIEW_COLUMN_HEADER_XPATH =
      "//div[@class='result-set-grid-view']//td[@role='columnheader']";
  /**
   * Xpath for view column header.
   */
  public static final String WIZIRD_CHECKBOX_XPATH =
      "//div[@dojoattachpoint='_openReportDiv']//input[@type='checkbox']";
  /**
   * Constant string variable to hold text "Create a Report".
   */
  public static final String CREATE_A_REPORT = "Create a Report";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_SAVE_REPORT_TO_PROJECT_XPATH =
      "//label[text()='Save report to project' or text()='Save report to component' ]/..//input[@type='checkbox']";

  /**
   *
   */
  public static final String RMARTIFACTSPAGE_OPEN_REPORT_WHEN_WIZARDIS_CLOSED_XPATH =
      "//label[text()='Open the report when the wizard is closed.']/..//input[@type='checkbox']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_ADD_APPENDDATETIME_INFORMATION_TO_THE_REPORTNAME_XPATH =
      "//label[text()='Append date/time information to the report name']/..//input[@type='checkbox']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_COMPANYNAME_IN_REPORT_XPATH =
      "//table[@objectid='companyName']//input[@type='text']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_AUTHORNAME_IN_REPORT_XPATH =
      "//table[@objectid='authorName']//input[@type='text']";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_CONTEXTPICKER_RADIO_XPATH = "//input[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   *
   */
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_GC_ROW_XPATH = "//form//*[@cell-value][substring-after(normalize-space(.),'')='DYNAMIC_VAlUE']";
  // public static final String RMARTIFACTSPAGE_GC_ROW_XPATH = "//div[@class='gc-sdk-flexstack__fill
  // gc-sdk-overflow-y']//span";

  /**
   *
   */
  public static final String RMARTIFACTSPAGE_GC_CONFIGURATION_XPATH =
      "//td[@class='config-row-cell']//span[text()='DYNAMIC_VAlUE']";
  /**
   * Constant string variable to hold text "streamName".
   */
  public static final String STREAM_NAME = "streamName";
  /**
   *
   */
  public static final String RMARTIFACTSPAGE_EXPORT_BUTTON_XPATH = "//label[text()='CSV']/preceding-sibling::div/input";
  /**
   * Constant string variable to hold text "aria-checked".
   */
  public static final String ARIA_CHECKED = "aria-checked";
  /**
   * Constant string variable to hold text "false".
   */
  public static final String FALSE = "false";
  /**
   * Xpath for child element present in left side container.
   */
  public static final String LEFTSIDE_CONTAINER_CHILDS_XPATH = "./../../..";
  /**
   * Constant string variable to hold text "' is displayed as current configuration.\n\nExpected Result '".
   */
  public static final String IS_DISPLAYED_AS_CURRENT_CONFIGURATION =
      "' is displayed as current configuration.\n\nExpected Result '";
  /**
   * Constant string variable to hold text "' should be displayed as current configuration.".
   */
  public static final String SHOULD_BE_DISPLAYED_AS_CURRENT_CONFIGURATION =
      "' should be displayed as current configuration.";

  /**
   * Xpath for the current configuration.
   */
  public static final String CURRENT_CONFIGURATION_XPATH =
      "//span[@title='Current Configuration']//a[@class='configurationUiNode hideLink']//span[@class='titleNode']";
  /**
   * Constant string variable to hold text "Verified: Displayed page after click on '".
   */
  public static final String VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON = "Verified: Displayed page after click on '";
  /**
   * Constant string variable to hold text "Verified submenu after click on '".
   */
  public static final String VERIFIED_SUBMENU_AFTER_CLICK_ON = "Verified submenu after click on '";
  /**
   * Constant string variable to hold text "Verified: '".
   */
  public static final String VERIFIED = "Verified: '";
  /**
   * Constant string variable to hold text "' section should be displayed".
   */
  public static final String SECTION_SHOULD_BE_DISPLAYED = "' section should be displayed";
  /**
   * Constant string variable to hold text "Verified displayed section after click on RQM section in the artifact
   * details page.".
   */
  public static final String VERIFIED_DISPLAYED_SECTION_AFTER_CLICK_ON_RQM_SECTION_IN_THE_ARTIFACT_DETAILS_PAGE =
      "Verified displayed section after click on RQM section in the artifact details page.";
  /**
   * Constant string variable to hold text "' section menu and expected is the '".
   */
  public static final String SECTION_MENU_AND_EXPECTED_IS_THE = "' section menu and expected is the '";
  /**
   * Constant string variable to hold text "Verified displayed page after click on sub menu under section."
   */
  public static final String VERIFIED_DISPLAYED_PAGE_AFTER_CLICK_ON_SUB_MENU_UNDER_SECTION =
      "Verified displayed page after click on sub menu under section.";
  /**
   * Constant string variable to hold text "\nActual result is the '.
   */
  public static final String ACTUAL_RESULT_IS_THE = "\nActual result is the '";
  /**
   * Constant string variable to hold text "' page is displayed after clicking on '".
   */
  public static final String PAGE_IS_DISPLAYED_AFTER_CLICKING_ON = "' page is displayed after clicking on '";
  /**
   * Constant string variable to hold text "' menu under '".
   */
  public static final String MENU_UNDER = "' menu under '";
  /**
   * Constant string variable to hold text "' page should be displayed".
   */
  public static final String PAGE_SHOULD_BE_DISPLAYED = "' page should be displayed";
  /**
   * Constant string variable to hold text "' page is NOT displayed after clicking on '".
   */
  public static final String PAGE_IS_NOT_DISPLAYED_AFTER_CLICKING_ON = "' page is NOT displayed after clicking on '";
  /**
   * Constant string variable to hold text "' node menu and expected is the '".
   */
  public static final String NODE_MENU_AND_EXPECTED_IS_THE = "' node menu and expected is the '";
  /**
   * Constant string variable to hold text "Browse".
   */
  public static final String BROWSE = "Browse";
  /**
   * Constant string variable to hold text "Verified displayed menu after click on main menu."
   */
  public static final String VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU =
      "Verified displayed menu after click on main menu.";
  /**
   * Constant string variable to hold text "Project Dashboard:".
   */
  public static final String PROJECT_DASHBOARD = "Project Dashboard:";
  /**
   * Xpath for the All Projects banner title.
   */
  public static final String RMALLPROJECTS_BANNER_TITLE_XPATH = "//span[starts-with(@class, 'banner-title')]";
  /**
   * Constant string variable to hold text "Verified by banner title text present next to home button \n \n Actual title
   * \"".
   */
  public static final String VERIFIED_BANNER_TITLE =
      "Verified by banner title text present next to home button \n \n Actual title \"";
  /**
   * Constant string variable to hold text "\" contains expected text : \"".
   */
  public static final String CONTAINS_EXPECTED_TEXT = "\" contains expected text : \"";
  /**
   * Constant string variable to hold text "\"\nExpected result is \"".
   */
  public static final String EXPECTED_RESULT_IS = "\"\nExpected result is \"";
  /**
   * Constant string variable to hold text "' of artifact.\nActual is the 'Create a Report' dialog is displayed as
   * expectation.".
   */
  public static final String ACTUAL_IS_THE_CREATE_A_REPORT_DIALOG_IS_DISPLAYED_AS_EXPECTED =
      "' of artifact.\nActual is the 'Create a Report' dialog is displayed as expectation.";
  /**
   * Constant string variable to hold text "' of artifact.\nActual is the 'Create a Report' dialog is not displayed as
   * expectation.".
   */
  public static final String ACTUAL_IS_THE_CREATE_A_REPORT_DIALOG_IS_NOT_DISPLAYED_AS_EXPECTED =
      "' of artifact.\nActual is the 'Create a Report' dialog is not displayed as expectation.";
  /**
   * Constant string variable to hold text "The filter with the '".
   */
  public static final String THE_FILTER_WITH_THE = "The filter with the '";
  /**
   * Constant string variable to hold text "IMPORTED_MODULE_NAME".
   */
  public static final String IMPORTED_MODULE_NAME = "IMPORTED_MODULE_NAME";
  /**
   * Constant string variable to hold text "IMPORTED_MODULE_ID".
   */
  public static final String IMPORTED_MODULE_ID = "IMPORTED_MODULE_ID";
  /**
   * Constant string variable to hold text "arguments[0].scrollIntoView(true);".
   */
  public static final String SCROLL_INTO_VIEW = "arguments[0].scrollIntoView(true);";
  /**
   * Xpath of all the Artifact IDs present in view table.
   */
  public static final String VIEW_COLUMNS_ARTIFACT_IDS_XPATH =
      "//table[@rowType='resourceRow']//td[@class='id-col']//a";
  /**
   * Xpath of ReqIF check box.
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_CHECKBOX_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/../preceding-sibling::td//input";
  /**
   * Constant string variable to hold text "\" should be deleted."
   */
  public static final String SHOULD_BE_DELETED = "\" should be deleted.";
  /**
   * Xpath for "Select View" option while adding module level view for export reqif
   */
  public static final String RMARTIFACTSPAGE_SELECTVIEW_XPATH = "//span[text()='DYNAMIC_VAlUE']";
  /**
   * Constant string variable to hold text "Cancel".
   */
  public static final String CANCEL = "Cancel";
  /**
   * Constant string variable to hold text "OK".
   */
  public static final String OK = "OK";
  /**
   * Constant string variable to hold text "Remove".
   */
  public static final String REMOVE = "Remove";
  /**
   * Constant string variable to hold text "Yes".
   */
  public static final String YES = "Yes";
  /**
   * Constant string variable to hold text "ARTIFACT_TYPE".
   */
  public static final String ARTIFACT_TYPE = "ARTIFACT_TYPE";
  /**
   * Constant string variable to hold text "Edit Links".
   */
  public static final String EDIT_LINKS = "Edit Links";
  /**
   * Constant string variable to hold text "Apply".
   */
  public static final String APPLY = "Apply";

  /**
   * Xpath for New Name - in Duplicate Artifact to Folder dialog - Module page
   */
  public static final String RMMODULEPAGE_DUPLICATEARTIFACT_NEWNAME_XPATH =
      "//input[@id='com_ibm_rdm_web_picker_CopyFolderPicker_0_copyName']";

  /**
   * Xpath for Destination Folder - in Duplicate Artifact to Folder dialog - Module page
   */
  public static final String RMMODULEPAGE_DUPLICATEARTIFACT_DESTINATIONFOLDER_XPATH =
      "//div[@class='dijitTreeContainer']//span[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Xpath for Copy Links - in Duplicate Artifact to Folder dialog - Module page
   */
  public static final String RMMODULEPAGE_DUPLICATEARTIFACT_COPYLINKS_XPATH =
      "//label[contains(text(),'Copy links')]//preceding-sibling::div//input";

  /**
   * Xpath for Copy Tags - in Duplicate Artifact to Folder dialog - Module page
   */
  public static final String RMMODULEPAGE_DUPLICATEARTIFACT_COPYTAGS_XPATH =
      "//input[@id='com_ibm_rdm_web_picker_CopyFolderPicker_0_copyTagsCheckMark']";

  /**
   * Xpath for Folder Attribute - in New Filter dialog - Artifact page
   */
  public static final String RMARTIFACTPAGE_NEWFILTER_FOLDERATTRIBUTE_XPATH =
      "//div[@class='list-label-div']//following-sibling::div//label[contains(text(),'Folder')]//parent::div";


  /**
   * Xpath for Destination Folder in Choose Value column - in New Filter dialog - Artifact page
   */
  public static final String RMARTIFACTPAGE_NEWFILTER_VALUEOFFOLDERATTRIBUTE_XPATH =
      "//div[contains(text(),'Choose Values:')]/parent::div//following-sibling::div//div[@class='dijitTreeContainer']//span[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Xpath for "Add and Close" button - in New Filter dialog - Artifact page
   */
  public static final String RMARTIFACTPAGE_NEWFILTER_ADDANDCLOSEBUTTON_XPATH =
      "//button[contains(text(),'Add and Close')]";


  /**
   * Xpath for Check box of one artifact - in Artifact page
   */
  public static final String RMARTIFACTPAGE_CHECKBOX_OFARTIFACT_XPATH =
      "//tr//td[@class='id-col']//a[text()='DYNAMIC_VAlUE']//ancestor::div[@class='id-col gridCellWrapper']//parent::td//preceding-sibling::td[2]";

  /**
   * Xpath for link in Validated by column - in New Filter dialog - Artifact page
   */
  public static final String RMARTIFACTPAGE_VALIDATEDBYLINK_XPATH = "//a[text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for Select one Type Link in "Add Links to Artifact" in right column - in New Filter dialog - Artifact page
   */
  public static final String RMARTIFACTPAGE_TYPELINK_INADDLINKSTOARTIFACT_XPATH =
      "//div[@class='jazz-ui-Menu-border']//table[@class='dijit dijitMenu dijitMenuPassive dijitReset dijitMenuTable']//td[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Xpath for Go To tab - in Module Find dialog - Artifact page
   */
  public static final String RMARTIFACTPAGE_MODULEFIND_GOTOTAB_XPATH = "//span[text()='Go To']";
  
  /**
   * Xpath for "Enter section number or artifact id:" textbox
   */
  public static final String RMARTIFACTPAGE_MODULEFIND_TEXTBOX_XPATH = 
      "//label[contains(text(),'Enter section number or artifact id:')]/ancestor::div[@class='top-field-label-div']/following-sibling::div[@class='goto-value-field-div']//div[contains(@class, 'InputContainer')]/input[@type='text']";

  /**
   * Xpath for COMPONENT/PROJECT TITLE in DM page
   */
  public static final String COMPONENT_PROJECT_TITLE_XPATH = "//span[@title='DYNAMIC_VAlUE']";

  /**
   * Xpath for COMPONENT/PROJECT TITLE in DM page
   */
  public static final String LINKSTAB_IN_DMPAGE_XPATH = "//a[@dojoattachpoint='linkNode' and text()='Links']";

  /**
   * Xpath for Satisfies Group in Links tab in DM page
   */
  public static final String SATISFIES_IN_LINKSTAB_DMPAGE_XPATH = "//td[contains(text(),'Satisfies')]";

  /**
   * Xpath for Artifact ID in Satisfies Group in Links tab in DM page
   */
  public static final String ARTIFACTID_IN_LINKSTAB_DMPAGE_XPATH =
      "//tr[@class='entry']//a[@class='jazz-ui-ResourceLink' and contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * Xpath for Artifact ID in Satisfies Group in Links tab in AM page
   */
  public static final String ARTIFACTID_IN_LINKSTAB_AMPAGE_XPATH =
      "//span[text()='Satisfies']/ancestor::tr[@class='header']/following-sibling::tr//a[contains(@class,'jazz-ui-ResourceLink') and contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Xpath for Limit by lifecycle status in Filter dialog - Choose Item XPATH
   */
  public static final String ITEM_LIMIT_BY_LIFECYCLE_STATUS_FILTER_XPATH =
      "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Xpath for Limit by lifecycle status in Filter dialog - Choose Value of Item XPATH
   */
  public static final String VALUE_ITEM_LIMIT_BY_LIFECYCLE_STATUS_FILTER_XPATH =
      "//following-sibling::div//span[@role='option' and text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for Limit by lifecycle status in Filter dialog - Choose Value of Item XPATH
   */
  public static final String SUMMARY_CONDITION_LIMIT_BY_LIFECYCLE_STATUS_FILTER_XPATH =
      "//div[text()='Summary of Condition:']//following-sibling::div//span[@class='condition-summary-value']";

  /**
   * Xpath for Visible of Limit by lifecycle status in Filter - XPATH
   */
  public static final String VISIBLE_LIMIT_BY_LIFECYCLE_STATUS_FILTER_XPATH =
      "//div[@title='Limit by lifecycle status -  - 'DYNAMIC_VAlUE']";


  /**
   * Xpath for Content of Artifact in Artifact Details page
   */
  public static final String RMARTIFACTPAGE_ARTIFACTCONTENT_XPATH = "//body[@contenteditable='true']";

  /**
   * Xpath for Showing number of Artifact in table- XPATH
   */
  public static final String NUMBER_ARTIFACT_SHOWING_IN_TABLE_XPATH = "//span[@class='numArtifacts']";

  /**
   * Xpath for Radio button - XPATH
   */
  public static final String RADIO_BUTTON_XPATH =
      "//div[@class='radio']//label[contains(text(),'DYNAMIC_VAlUE')]//preceding-sibling::div";

  /**
   * Xpath for Radio button get properties- XPATH
   */
  public static final String RADIO_BUTTON_PROPERTIES_XPATH =
      "//div[@class='radio']//label[contains(text(),'DYNAMIC_VAlUE')]//preceding-sibling::div//input";

  /**
   * Xpath for header edit filter dialog- XPATH
   */
  public static final String HEADER_EDIT_FILTER_DIALOG_XPATH =
      "//span[@dojoattachpoint='_headerPrimarySpan' and text()='Edit Filter'] | //div[@class='jazz-ui-Dialog-heading' and text()='Edit Filter'] | //div[@class='header-primary']/span[text()='Edit Filter']";

  /**
   * Xpath for Filter of Attribute Name need to edit value- XPATH
   */
  public static final String FILTER_ATTRIBUTE_NAME_XPATH =
      "//span[@class='condition-summary-title' and text()='DYNAMIC_VAlUE']//parent::div";

  /**
   * Constant string variable to hold text "Insert".
   */
  public static final String INSERT = "Insert";

  /**
   * Constant string variable to hold text "Artifact".
   */
  public static final String ARTIFACT = "Artifact";

  /**
   * xpath for project area dropdown
   */
  public static final String RMARTIFACTPAGE_PROJECTAREA_DROPDOWN_XPATH =
      "//td[text()='Project Area:']/following-sibling::td[@class='project-select']//span[@class='MenuItemContent']";

  /**
   * xpath for foler in Artifact page
   */
  public static final String RMARTIFACTPAGE_FOLDER_XPATH = "//span[@role='treeitem' and text()='DYNAMIC_VAlUE']";
  /**
   * xpath for foler icon in Artifact page
   */
  public static final String RMARTIFACTPAGE_FOLDER_ICON_XPATH = "//span[@role='treeitem' and text()='DYNAMIC_VAlUE']//preceding-sibling::span[@role='presentation']";

  /**
   * xpath for tooltip of folder after moving mouse to folder
   */
  public static final String RMARTIFACTPAGE_FOLDER_TOOLTIP_XPATH =
      "//span[span[@role='treeitem' and text()='DYNAMIC_VAlUE']]/following-sibling::div[@role='button'][1]";

  /**
   * xpath for 'Clone From a Component' option after moving mouse to a folder
   */
  public static final String RMARTIFACTPAGE_CLONE_FROM_A_COMPONENT_OPTION =
      "//div[not(contains(@style,'display: none'))]//td[contains(text(),'Clone From a Component')]";

  /**
   * xpath for project area to be selected displayed in 'Clone From a Component' dialog
   */
  public static final String CLONE_FROM_A_COMPONENT_DIALOG_PROJECTAREA_OPTION =
      "//span[@class='MenuItemContent']/span[text()='DYNAMIC_VAlUE']";

  /**
   * xpath for component to be selected displayed in 'Clone From a Component' dialog
   */
  public static final String CLONE_FROM_A_COMPONENT_DIALOG_COMPONENTNAME_OPTION =
      "//span[@class='name-cell' and text()='DYNAMIC_VAlUE']";

  /**
   * xpath for search result artifact displayed in 'Clone From a Component' dialog
   */
  public static final String ARTIFACTRESULT_AFTERSEARCHINGTOADDORINSERT_XPATH =
      "//a[@class='jazz-ui-ResourceLink' and contains(.,'DYNAMIC_VAlUE')]";

  /**
   *
   */
  public static final String RMDASHBOARD_SELECT_SUB_MENU =
      "//span[@class='MenuItemContent']/span[text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for: Change set name
   */
  public static final String RMDASHBOARD_CHANGESET_CONFIG =
      "//span[@class='changeset-open']/span[text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for: component after searching in "Browse Component" page
   */
  public static final String RMMANGECOMPONENTSANDCONFIGURATION_SEARCH_RESULT_COMPONENT =
      "//table[@aria-label='Components Table']//tr//a[text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for: component name displays in Artifact page
   */
  public static final String RMARTIFACTPAGE_COMPONENT_NAME =
      "//span[@class='rdm-team-component-menu' and text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for: 'Delete Artifact' button
   */
  public static final String RMARTIFACTPAGE_DELETEARTIFACT_BUTTON =
      "//div[@class='rdmDialogForm']//button[text()='Delete Artifact']";

  /**
   * Xpath for: one line in Artifact Content with expected text
   */
  public static final String RMARTIFACTSPAGE_ARTIFACT_ARTIFACTCONTENTLINE_XPATH =
      "//div[@dojoattachpoint='_contentArea']//*[text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for: content column of artifact after searching artifact in search box
   */
  public static final String RMMODULEPAGE_ARTIFACTCONTENT_AREA =
      "//table[@rowlabel='Artifact DYNAMIC_VAlUE']//td[@colid='module_content_col']//p";
  /**
   * Xpath for: content column of artifact after searching artifact in search box
   */
  public static final String RMMODULEPAGE_ARTIFACTCHECKBOX_XPATH =
      "//table[@aria-label='Artifact DYNAMIC_VAlUE.']//label[@class='mainSelect']";

  /**
   * Xpath for: action tooltip of artifact after searching artifact in search box and move mouse to this artifact ->
   * display action tooltip
   */
  public static final String RMMODULEPATE_ACTION_BUTTON_OF_ARTIFACT =
      "//a[text()='DYNAMIC_VAlUE']/preceding::div[1][@aria-label='Row Actions']/img[@class='menuLauncher']";

  /**
   * Xpath for: Insert New Artifact option after clicking on action tooltip of an artifact
   */
  public static final String RMMODULEPAGE_INSERTNEWARTIFACT_BUTTON = "//td[text()='Insert New Artifact']";

  /**
   * Xpath for: options displayed in the second pop up after click on "Action" button to show the 1st popup -> select
   * option -> open the second popup; e.g: type of insertion, e.g: After, Before, Below (as a Child)
   */
  public static final String RMMODULEPAGE_TYPEOFINSERTION_BUTTON =
      "//div[@class='dijitPopup jazz-ui-MenuPopup']//td[text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for: content column of an artifact is just inserted from the other artifact inside a module
   */
  public static final String RMMODULEPAGE_CONTENTAREA_OF_NEWARTIFACTINSERTED =
      "//div[contains(@aria-label,'Rich Text Editor, editor')]";

  /**
   * Xpath for: name of the current module
   */
  public static final String RMMODULEPAGE_MODULENAME_XPATH = "//span[@class='resource-title']";

  /**
   * Xpath for: ID of an artifact is just inserted from the other artifact inside a module
   */
  public static final String RMMODULE_NEWARTIFACTID_JUSTBEINSERTED_XPATH =
      "(//td[@class='id-col']//a[@class='jazz-ui-ResourceLink'])[2]";

  /**
   * Xpath for: edit content button of an artifact after moving mouse to this artifact
   */
  public static final String RMMODULEPAGE_EDITCONTENTOFARTIFACT_BUTTON_XPATH =
      "//a[@title='Edit Contents']/span[@class='editDescriptionCommand']";

  /**
   * Xpath for: Remove Artifact selection after click on Action tooltip of an artifact
   */
  public static final String RMARTIFACTPAGE_REMOVEARTIFACT_BUTTON_XPATH = "//td[text()='Remove Artifact...']";

  /**
   * Xpath for: check box to confirm remove and delete artifac if this artifact is not in other module
   */
  public static final String RMARTIFACTPAGE_CHECKBOX_REMOVEANDDELETEARTIFACT_XPATH =
      "//input[@type='checkbox' and contains(@id,'removeAndDelete')]";

  /**
   * Xpath for: Remove and Delete confirm button
   */
  public static final String RMARTIFACTPAGE_REMOVEANDDELETE_BUTTON_XPATH = "//button[text()='Remove and Delete']";
  /**
   * Xpath for: Remove confirm button in delete dialog
   */
  public static final String RMARTIFACTPAGE_REMOVE_BUTTON_XPATH = "//button[contains(text(),'Remove')]";

  /**
   * Xpath for: Create Otehr Artifact Type selection after clicking on 'Create' button
   */
  public static final String RMARTIFACTPAGE_CREATEOTHERARTIFACTTYPE_SELECTION_XPATH =
      "//td[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Xpath for: ID of a new artifact is just created
   */
  public static final String RMMODULEPAGE_ARTIFACTIDJUSTCREATED_XPATH = "(//div[@class='icon-resourcelink'])[last()]";
  /**
   * Xpath for: Content of a new artifact is just created
   */
  public static final String RMMODULEPAGE_ARTIFACTCONTENTJUSTCREATED_XPATH =
      "(//table[@rowtype='resourceRow'])[last()]//div[contains(@class,'primaryText')]//p";

  /**
   * Xpath for: Insert button: after clicking on edit content tooltip of an artifact, the Insert button will be
   * displayed
   */
  public static final String RMMODULEPAGE_INSERTBUTTON_AFTERCLICKONEDITCONTENTOFANARTIFACT_XPATH =
      "//span[@class='cke_button_label cke_button__insertmenu_label']";

  /**
   * Xpath for: iframe after clicking on Insert button
   */
  public static final String RMMODULEPAGE_IFRAME_AFTERCLICKINGINSERTBUTTON = "//iframe[@class='cke_panel_frame']";

  /**
   * Xpath for: type of artifact to be inserted: after clicking Insert button, one dialog displays list out all types of
   * artifact can be inserted include: Table, Image, Artifact, Symbol
   */
  public static final String RMMODULEPAGE_TYPEOFARTIFACT_TOBEINSERTED_XPATH = "(//span[contains(text(),'DYNAMIC_VAlUE')])[last()]";

  /**
   * Xpath for: content of inserted artifact, e.g: after inserting "Artifact_01" into content of "Artifact_02", the
   * content of "Artifact_02" will be displayed in the content column of "Artifact_01"
   */
  public static final String RMMODULEPAGE_CONTENTOFINSERTEDARTIFACT_XPATH = "//span[@class='embeddedRTTitle']";
  
  /**
   * Latest version: 7.0.2 Xpath for: content of inserted artifact, e.g: after inserting "Artifact_01" into content of
   * "Artifact_02", the content of "Artifact_02" will be displayed in the content column of "Artifact_01"
   */
  public static final String RMMODULEPAGE_CONTENTOFINSERTEDARTIFACT_XPATH2 = "//div[@class='embeddedRequirement']/p";

  /**
   * Xpath for: button to link to the inserted artifact, e.g: after inserting "Artifact_01" into content of
   * "Artifact_02", the content of "Artifact_02" will be displayed in the content column of "Artifact_01, moving mouse
   * to the content of the "Artifact_02" and 'Opent Inserted Artifact' button will be displayed, if clicking on this
   * button will navigate to "Artifact_02"
   */
  public static final String RMMODULEPAGE_INSERTEDARTIFACTLINK_XPATH = "//span[@title='Open Inserted Artifact']";

  /**
   * Xpath for: ID of artifact just be inserted after an artifact with 'DYNAMIC_VAlUE' ID
   */
  public static final String RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER =
      "//a[@class='jazz-ui-ResourceLink' and text()='DYNAMIC_VAlUE']/following::td[@colid='id-col'][1]//a[@class='jazz-ui-ResourceLink']";

  /**
   * Xpath for: ID of artifact just be inserted before an artifact with 'DYNAMIC_VAlUE' ID
   */
  public static final String RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTBEFORE =
      "//a[@class='jazz-ui-ResourceLink' and text()='DYNAMIC_VAlUE']/preceding::td[@colid='id-col'][1]//a[@class='jazz-ui-ResourceLink']";

  /**
   * Xpath for: searching results of Look up term
   */
  public static final String RMMODULEPAGE_TERMRESULT_LOOKUPTERM_XPATH = "//div[@class='terms']//div[@class='title']";

  /**
   * Xpath for: Close button to close the Look up dialog
   */
  public static final String RMMODULEPAGE_CLOSELOOKUPTERMDIALOGBUTTON_XPATH = "//img[@alt='Close']";

  /**
   * Xpath for: "Browse Components" link, click to this link will navigate to the 'Browse Components" page
   */
  public static final String RMMANAGECOMPONENTANDCONFIGURATIONPAGE_BROWSECOMPONENTLINK_XPATH =
      "//a[text()='Browse Components']";

  /**
   * Xpath for: Close button to close the Look up dialog
   */
  public static final String RMMANAGECOMPONENTANDCONFIGURATIONPAGE_BROWSECOMPONENTPAGETITLE_XPATH =
      "//a[text()='Browse Components']";

  /**
   * Xpath for: Create Component dialog - step 2 - "Use a template to initially populate the component" check-box
   */
  public static final String CHECKBOX_CREATECOMPONENTDIALOG_XPATH = "//input[@class='dijitReset dijitCheckBoxInput']";


  /**
   * Xpath for: Loading Project Dashboard... loading message
   */
  public static final String RMMANAGECOMPONENTANDCONFIGURATIONPAGE_PROJECTDASHBOARDLOADING_XPATH =
      "//div[text()='Loading Project Dashboard...']";
  /**
   * Xpath for: Loading artifacts page... loading message
   */
  public static final String RMMANAGECOMPONENTANDCONFIGURATIONPAGE_ARTIFACTSPAGELOADING_XPATH =
      "//div[text()='Loading artifacts page...']";
  /**
   * Xpath for: Loading permissions... loading message
   */
  public static final String RMMANAGECOMPONENTANDCONFIGURATIONPAGE_PERMISSIONSLOADING_XPATH =
      "//div[text()='Loading permissions...']";
  /**
   * Xpath for: Saving... loading message
   */
  public static final String RMMANAGECOMPONENTANDCONFIGURATIONPAGE_SAVINGLOADING_XPATH = "//div[text()='Saving...']";
  /**
   * Xpath for: Refresh button
   */
  public static final String RMMODULEPAGE_REFRESHBUTTON_XPATH = "//img[@alt='Refresh']";
  /**
   * Xpath for: Title in Module page xpath:
   */
  public static final String TITLE_ID_MOLDUE_IN_MODULE_PAGE_XPATH = "//span[@class='DYNAMIC_VAlUE']";

  /**
   * Xpath for: Title in Module page xpath:
   */
  public static final String CHOOSE_VALUE_TEXTFIELD_XPATH =
      "//div[@class='dijitReset dijitInputField dijitInputContainer']//input";

  /**
   * Xpath for: Removing artifact icon
   */
  public static final String REMOVING_ARTIFACT_XPATH = "//div[text()='Removing Artifact(s)...']";

  /**
   * Xpath for: a folder
   */
  public static final String FOLDER_NAME_XPATH = 
      "//div[@dojotype='dijit.layout.ContentPane' and contains(@style,'display: block')]//span[@role='treeitem' and text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for: Verify click on folder
   */
  public static final String FILTER_FOLDER_ENABLE_NAME_XPATH = 
      "//div[@class='condition-div' and contains(@title,'Folder') and contains(@title,'treenodeName')]";

  /**
   * Xpath for: Create Artifact option after click on context menu of an artifact or a module
   */
  public static final String CREATE_ARTIFACT_OPTION_XPATH =
      "//div[@class='dijitPopup dijitMenuPopup' and not(contains(@style,'display: none'))]//td[text()='Create Artifact']";

  /**
   * Xpath for: option to create a new artifact, e.g: Heading, Information, Import Artifact
   */
  public static final String CREATE_ARTIFACT_SUBOPTION_XPATH =
      "//table[contains(@class,'MenuActive')]//td[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Xpath for: an option in 'Import' artifact dialog
   */
  public static final String IMPORT_OPTION_CHECKBOX_XPATH =
      "//label[strong[text()='DYNAMIC_VAlUE']]/preceding-sibling::div[1]/input[@type='radio']";

  /**
   * Xpath for: a configuration, e.g: Create Baselines..., Compare Configuration...,Create Stream...
   */
  public static final String CONFIGURATION_XPATH = "//tr[contains(@title,'DYNAMIC_VAlUE') and @aria-disabled='false']";

  /**
   * Xpath for: 'Use a template to initially populate the component' checkbox while cloning a new artifact using a
   * template
   */
  public static final String USEATEMPLATETOINITIALLYPOPULATETHECOMPONENT_CHECKBOX_XPATH =
      "//div[label[text()='Use a template to initially populate the component']]//input[@type='checkbox']";

  /**
   * Xpath for: 'Use a template to initially populate the component' checkbox while cloning a new artifact using a
   * template
   */
  public static final String TEMPLATENAME_CLONEFROMACOMPONENT_XPATH =
      "//div[@dojoattachpoint='templatesListWrapper']//label[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * Xpath for: Add and Close button in Add Filter dialog
   */
  public static final String ADD_AND_CLOSE_BUTTON_XPATH = "//button[contains(text(),'Add and Close')]";


  /**
   * Xpath for: Default value in condition in Add Filter dialog
   */
  public static final String DEFAULT_CONDITION_IN_ADD_FILTER_DIALOG =
      "//div[@dojoattachpoint='containerNode']//span[text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for: Expect value in condition in Add Filter dialog
   */
  public static final String EXPECT_CONDITION_IN_ADD_FILTER_DIALOG = "//td[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for: Loading message
   */
  public static final String LOADING_MESSAGE = "//div[@class='status-message' and text()='Loading...']";
  /**
   * Xpath for: Loaded project area message
   */
  public static final String LOADED_PROJECTAREA_MESSAGE =
      "//div[@class='status-message' and text()='Loading Project Area...' and contains(@style,'display: none;')]";
  /**
   * Xpath for: Process roles cell
   */
  public static final String MANAGECOMPPROPERTIES_PROCESSROLES_CELL_XPATH = 
      "(//div[@role='heading' and text()='Members']/ancestor::div[@class='section']//div[@class='memberName']//*[text()='DYNAMIC_VAlUE']/ancestor::tr)[last()]//div[@class='memberRoles']";

  /**
   * Xpath for: Add Filter icon
   */
  public static final String ADD_FILTER_ICON =
      "//img[@class='filter-pane-add-condition-button' and @title='Add filter']";
  /**
   * Xpath for Find what text filed inside Goto Module find dailog.
   */
  public static final String RMARTIFACTPAGE_FINDWHAT_TEXTFIELD_XPATH =
      "//input[@id='com_ibm_rdm_web_artifactgrid_ModuleFindForm_0_findValueId']";
  /**
   * Xpath TO DOUBBLE CLICK ARTIFACT CONTENT TO COPY.
   */
  public static final String RMARTIFACTPAGE_ARTIFACT_CONTENT_XPATH = "//*[text()='DYNAMIC_VAlUE']";
  
  /**
   * Xpath for artifact content following artifact ID
   */
  public static final String RMARTIFACTPAGE_ARTIFACT_CONTENT_FOLLOWING_ARTIFACT_ID_XPATH =
      "//a[text()='DYNAMIC_VAlUE0']//ancestor::td[@role='gridcell']//following-sibling::td//*[text()='DYNAMIC_VAlUE1']";
  /**
   * Xpath TO VERIFY SEARCHED ARTIFACT USING CONTENT FROM MODULE FIND DAILOG.
   */
  public static final String RMARTIFACTPAGE_SEARCHARTIFACT_CONTENT_XPATH =
      "//p[@id='_1623047271971']/span/span[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for: Dropdown is any of / is not any of in Filter Dialog
   */
  public static final String FILTERDIALOG_ATTRIBUTE_CONDITION_XPATH =
      "//span[@class='attributeNameSpan' and text()='DYNAMIC_VAlUE']/parent::div//span[@data-dojo-attach-point='containerNode']";
  /**
   * Xpath for: Option for Dropdown is any of / is not any of in Filter Dialog
   */
  public static final String FILTERDIALOG_ATTRIBUTE_CONDITION_OPTION_XPATH =
      "//table[contains(@class,'dijitMenuActive')]//tr[@role='menuitem']//td[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for: All attribute value for Attribute name in Filter Dialog
   */
  public static final String FILTERDIALOG_ATTRIBUTE_VALUE_XPATH =
      "(//div[@dojoattachpoint='valueSectionNode']//div[@class='enumValue']//input)";
  /**
   * Xpath for: Filter displayed on Artifacts/ Module page, using Title attribute
   */
  public static final String ARTIFACTSPAGE_FILTER_CONDITION_XPATH =
      "//div[@class='condition-div' and contains(@title,'DYNAMIC_VAlUE')]";
  /**
   * Xpath for: Attribute value displayed on Artifacts/ Module page after filterring. Need Parent Element. Using
   * ARTIFACTSPAGE_FILTER_CONDITION_XPATH <br>
   * Using webElement.findElement()
   */
  public static final String ARTIFACTSPAGE_FILTER_ATTRIBUTEVALUE_CONDITION_XPATH =
      ".//span[@class='condition-summary-value']";

  /**
   * Xpath Get quantity of link type in side bare area
   */
  public static final String QUANTITY_OF_LINK_TYPE_IN_SIDE_BAR_AREA_XPATH =
      "//span[@class='rdm-group-title' and contains(text(),'DYNAMIC_VAlUE')]//following-sibling::span[@class='rdm-group-title-count']";
  /**
   * Xpath Warning Message of Artifact Link in side bare area
   */
  public static final String WARNING_MESSAGE_XPATH =
      "//div[@class='messageArea WARNING']//div[@class='messageSummary']";

  /**
   * Xpath Loading in Artifact Links
   */
  public static final String LOADING_ARTIFACT_LINKS_XPATH = "//span[contains(text(),'Artifact Links (Loading...)')]";

  /**
   * Xpath Artifact ID xpath in header page
   */
  public static final String ARTIFACT_ID_XPATH = "//span[@class='resource-id']";


  /**
   * Xpath message loading
   */
  public static final String MESSAGE_LOADING_XPATH = "//div[@class='status-message' and text()='Loading...']";
  /**
   * Xpath view Table
   */
  public static final String TABLE_XPATH = "//div[@class='result-set-grid-view']";

  /**
   * Xpath Header table
   */
  public static final String HEADER_TABLE_XPATH = "//div[@class='result-set-grid-view']//td[@role='columnheader']";

  /**
   * Xpath ubdefined Row in table
   */
  public static final String ROW_TABLE_XPATH = "./td[@aria-label='undefined']";

  /**
   * Xpath Row in table
   */
  public static final String ROW_IN_TABLE_XPATH = "//table[@role='row presentation']//tr[1]";

  /**
   * Xpath for:
   */
  public static final String REVIEWPAGE_REVIEW_XPATH =
      "(//table[@rowtype='reviewRow']//td[@class='name_col']//a[text()='DYNAMIC_VAlUE'])[1]";

  /**
   * Xpath for:
   */
  public static final String REVIEWPAGE_DISABLEDNEXT_BUTTON_XPATH = "//span[@class='next']/a[not(@class='disable')]";

  /**
   * Xpath for:
   */
  public static final String REVIEWPAGE_NEXT_BUTTON_XPATH = "//span[@class='next']/a[text()='Next']";

  /**
   * Xpath for:
   */
  public static final String REVIEWPAGE_SHARELINKTOREVIEW_BUTTON_XPATH = "//img[@alt='Share Link to Review...']";

  /**
   * Xpath for:
   */
  public static final String REVIEWPAGE_SHARELINKTOREVIEW_TEXT_XPATH = "//textarea[contains(@style,'display: block;')]";

  /**
   * Xpath for:
   */
  public static final String REVIEWPAGE_RESOURCETITLE_XPATH = "//span[@class='resource-title']";

  /**
   * Xpath for:
   */
  public static final String REVIEWPAGE_ARTIFACTDISPLAYED_XPATH =
      "//table[@rowtype='artifactRow']//td[@class='id_col']//span[text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for:
   */
  public static final String REVIEWPAGE_PARTICIPANTANDTYPE_XPATH =
      "//span[@class='userSpan']/a[text()='DYNAMIC_VAlUE0']/following::td[1][@class='type_col'][//span[text()='DYNAMIC_VAlUE1']]";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_SEARCHWIDGET_XPATH =
      "//div[@class='com-ibm-team-dashboard-catalog']//input[@aria-label='Search text']";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_ADDWIDGETBUTTON_XPATH =
      "//div[@class='entry-foreground']/div[@class='att-title-site'][a[text()='DYNAMIC_VAlUE']]/following-sibling::div[1][@class='add-widget-button-site']//span[text()='Add Widget']";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_FAILEDTOADDWIDGET_MESSAGE_XPATH =
      "//div[@class='messageSummary' and text()='Failed to add widget']";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_HEADINGCHAPTER_XPATH =
      "//div[@id='explorer']//span[@class='item heading' and text()[normalize-space()='DYNAMIC_VAlUE']]";

  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_REQIF_VIEWS_XPATH =
      "//div[@class='headerInner headerNoPadding']//span[text()='Views : ']";

  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_REQIF_VIEWSBUTTON_XPATH =
      "//a[@role='button' and text()='0 selected...']";

  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_REQIF_VIEWSELECTED_XPATH =
      "//a[@role='button' and text()='1 selected...']";
  /**
   *
   */
  public static final String MANAGECOMPPROPERTIES_REQIF_DISPLAYEDVIEW_XPATH =

      "//span[@id='com_ibm_rdm_web_grid_ViewHeader_12-view_main_col']|//span[@id='com_ibm_rdm_web_grid_ViewHeader_10-view_main_col']";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_SECTIONNUMBER_EXPAND_XPATH =
      "//div[contains(@class,'row collapsed')]/span[@class='item heading'][span[@class='sectionNumber' and text()='DYNAMIC_VAlUE']]/following-sibling::div[1][@class='expander']";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_ARTIFACT_INMODULEEXPLORER_XPATH =
      "//*[(@class='item heading' or @class='item non-heading') and text()[normalize-space()='DYNAMIC_VAlUE']]";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_EXPANDBUTTON_INMODULEEXPLORER_XPATH =
      "//*[text()[normalize-space()='DYNAMIC_VAlUE']]/following-sibling::div[@class='expander']";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_CHILDRENARTIFACT_OFANARTIFACT_XPATH =
      "//*[(@class='item heading' or @class='item non-heading') and text()[normalize-space()='DYNAMIC_VAlUE0']]/parent::div[not(contains(@class,'collapsed'))]/div[contains(@class,'row')]/span[contains(text(),'DYNAMIC_VAlUE1')]";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_MINIDASHBOARD_XPATH = "//div[@aria-label='Mini Dashboard']";

  /**
   * Xpath for:
   */
  public static final String DASHBOARDPAGE_WIDGET_XPATH =
      "//div[@class='com-ibm-team-dashboard-viewletpage']//div[@class='header-primary' and contains(@id,'jazz_ensemble_internal_WidgetContainer')]/span[text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for:
   */
  public static final String DASHBOARDPAGE_DISABLEDSAVEBUTTON_XPATH = "//input[@class='saveButton' and @disabled]";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_IFRAME_MODULEEXPLORER_WIDGET_XPATH =
      "(//span[text()='Module Explorer']/following::iframe)[1]";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_WIDGET_XPATH =
      "//div[@aria-label='Mini Dashboard']//div[@class='header-primary']/span[text()='DYNAMIC_VAlUE']";

  /**
   * Xpath for:
   */
  public static final String MINIDASHBOARDPAGE_VERIFY_ARTIFACTISSELECTED_INMODULE =
      "//table[@aria-label='Artifact DYNAMIC_VAlUE0 selected.']//td[@colid='module_content_col']//p[.='DYNAMIC_VAlUE1']";

  /**
   * Xpath for: message indicates that saving dash board successfully
   */
  public static final String DASHBOARDPAGE_SAVEDASHBOARDSUCCESSFULLY_XPATH =
      "//div[@class='messageSummary' and text()='Dashboard successfully saved']";

  /**
   * Constants for CREATE TERM Dialog: Initial Content
   */
  public static final String INITIAL_CONTENT_IN_CREATE_TERM_DIALOG = "Initial_Content";
  /**
   * Constants for CREATE TERM Dialog:
   */
  public static final String ARTIFACT_TYPE_IN_CREATE_TERM_DIALOG = "Artifact_Type";

  /**
   * XPATH ELEMENT EDIT ENABLE in Artifact table:
   */
  public static final String XPATH_ELEMENT_EDIT_ENABLE = "//div[@contenteditable='true']//p";
  /**
   * XPATH IFRAME MENU DROPDOWN RIGHT CLICK:
   */
  public static final String XPATH_IFRAME_MENU_DROPDOWN_RIGHT_CLICK = "//iframe[@class='cke_panel_frame']";
  /**
   * XPATH OPTION CREATE TERM:
   */
  public static final String XPATH_OPTION_CREATE_TERM =
      "//span[@class='cke_menuitem']//span[text()='Create Term']//ancestor::a";
  /**
   * XPATH HEADING OF CREATE TERM DIALOG:
   */
  public static final String XPATH_HEADING_OF_CREATE_TERM_DIALOG = 
      "//div[@class='header-primary']/span[text()='Create Term']|//div[contains(@class, 'heading')][text()='Create Term']";

  /**
   * XPATH IFRAME CREATE TERM DIALOG:
   */
  public static final String XPATH_IFRAME_CREATE_TERM_DIALOG =
      "//div[@role='dialog']//iframe[@class='dijitBackgroundIframe']";

  /**
   * XPATH for list link in Selected Artifact tab on Module page
   */
  public static final String MODULEPAGE_SELECTEDARTIFACTTAB_LISTLINK =
      "//span[@class='rdm-group-title' and  text()[normalize-space()='DYNAMIC_VAlUE']]/ancestor::table[contains(@class,'linkTypesGrouping groupRow')]/following-sibling::table";


  /**
   * XPATH for one link in Selected Artifact tab on Module page
   */
  public static final String MODULEPAGE_SELECTEDARTIFACTTAB_LINK =
      "(//span[@class='rdm-group-title' and  text()[normalize-space()='DYNAMIC_VAlUE0']]/following::table[@class='resourceRow']//a[@class='jazz-ui-ResourceLink'])[DYNAMIC_VAlUE1]";

  /**
   * XPATH OK button CREATE TERM DIALOG:
   */
  public static final String XPATH_OK_BUTTON_CREATE_TERM_DIALOG = "//button[@class='button-primary' and text() = 'OK']";

  /**
   * XPATH ARTIFACT ID TEXTFIELD EDIT FILTER DIALOG:
   */
  public static final String XPATH_ARTIFACT_ID_TEXTFIELD_EDIT_FILTER_DIALOG = "//span[@class='attributeNameSpan']";

  /**
   * XPATH_message Loading:
   */
  public static final String XPATH_MESSAGE_LOADING = "//div[@class='status-message' and text()='Loading']";

  /**
   * XPATH Artifact ID Filter:
   */
  public static final String XPATH_ARTIFACT_ID_FILTER =
      "//span[@class='condition-summary-title' and text()='Artifact ID']//following-sibling::span[@class='condition-summary-value']";

  /**
   * XPATH OPTION LOOKUP TERM:
   */
  public static final String XPATH_OPTION_LOOKUP_TERM =
      "//span[@class='cke_menuitem']//span[text()='Look Up Term']//ancestor::a";

  /**
   * XPATH HEADER OF LOOKUP TERM:
   */
  public static final String XPATH_HEADER_LOOKUP_TERM =
      "//div[@role='dialog']//span[starts-with(.,'Terms starting with')]|//div[contains(@class,'heading')][contains(text(),'Terms starting with')]";

  /**
   * XPATH LIST TERM IN LOOK UP TERM DIALOG :
   */
  public static final String XPATH_LIST_TERM_IN_LOOKUP_TERM_DIALOG =
      "//div[@class='collapsible']//p[contains(text(),'DYNAMIC_VAlUE')]|//div[@class='termsScroller']//p[contains(normalize-space(text()), 'DYNAMIC_VAlUE')]";

  /**
   * Xpath for link from Right Side bar - Artifact page
   */
  public static final String RMARTIFACTPAGE_LINK_FROM_RIGHTSIDEBAR_XPATH = "//a[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for HEADER of Page
   */
  public static final String HEADER_PAGE_XPATH =
      "//a[@title='About This Application' and contains(text(),'DYNAMIC_VAlUE')]";


  /**
   * XPATH for 'Configure Page Settings' button in Artifact page
   */
  public static final String ARTIFACTPAGE_CONFIGUREPGESETTINGS_XPATH = "//img[@title='Configure Page Settings']";

  /**
   * XPATH for 'More...' button in Artifact page
   */
  public static final String ARTIFACTPAGE_MOREBUTTON_XPATH = "//td[text()='More...']";

  /**
   * XPATH for 'Save changes to this view' button in Artifact page
   */
  public static final String ARTIFACTPAGE_SAVECHANGESTOTHISVIEW_XPATH =
      "//a[@class='button' and @title='Save changes to this view']";

  /**
   * XPATH for collapsed section number of artifact in Module page
   */
  public static final String MODULEPAGE_COLLAPSED_SECTION_XPATH =
      "//div[@class='rdm-artifactgrid-contenteditablecellwidget expandable collapsed']//div[@class='sectionNumber']/span[text()='DYNAMIC_VAlUE']";

  /**
   * XPATH for expanded section number of artifact in Module page
   */
  public static final String MODULEPAGE_EXPANDED_SECTION_XPATH =
      "//div[@class='rdm-artifactgrid-contenteditablecellwidget expandable']//div[@class='sectionNumber']/span[text()='DYNAMIC_VAlUE']";

  /**
   * XPATH for column is diplayed in the artifact table
   */
  public static final String ARTIFACTPAGE_DISPLAYEDCOLUMNHEADER_XPATH =
      "//td[@class='selected_name_col']//div[text()[normalize-space()='DYNAMIC_VAlUE']]";

  /**
   * XPATH for section number of artifact in Module page
   */
  public static final String MODULEPAGE_SECTIONNUMBER_XPATH =
      "//div[@class='sectionNumber']/span[text()='DYNAMIC_VAlUE']";

  /**
   * store command line for 'scroll to into view'
   */
  public static final String SCROLLINTOVIEW_COMMAND = "arguments[0].scrollIntoView(true);";

  /**
   * XPATH for options in Create PDF Document in Module Page
   */
  public static final String MODULEPAGE_CREATEPDFDOCUMENTDIALOG_INCLUDE_OPTION_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/preceding-sibling::div/input[@type='checkbox']";

  /**
   * XPATH for OK button in Module page
   */
  public static final String MODULEPAGE_OKBTN_XPATH = "//button[text()[normalize-space()='OK']]";

  /**
   * XPATH for Report generation message in Module page
   */
  public static final String RMMODULEPAGE_MESSAGEGENERATEREPORT_XPATH =
      "//div[@dojoattachpoint='messageArea']/div[text() = 'Report generation complete']";

  /**
   * XPATH for Loading message:
   */
  public static final String LOADING_MESSAGE_XPATH = "//div[@class='status-message' and text()='Loading...']";

  /**
   * XPATH for ARTIFACT ROW
   */
  public static final String ARTIFACT_ROW_XPATH = "//table[@role='row presentation']";

  /**
   * XPATH for IMG ICON
   */
  public static final String IMG_ICON_XPATH = "//table[@class='no-table-style']//tr//span[@class='imgWrapper']//img";
  /**
   * Xpath for Error message displayed
   */
  public static final String ERROR_MESSAGE_XPATH = "//div[contains(@class,'messageArea ERROR')]";
  
  /**
   * Xpath for success message displayed
   */
  public static final String SUCCESSFUL_MESSAGE_XPATH = "//div[contains(@class,'messageSummary')]";
  /**
   * Xpath for Edit artifact text box in module
   */
  public static final String EDIT_ARTIFACT_TEXTBOX_XPATH =
      "//div[@data-dojo-attach-point='_primaryTextNode' and contains(@aria-label,'Rich Text Editor')]";

  /**
   * Xpath for Loading message
   */
  public static final String LOADING_MESSAGE_01 = "//span[@class='asq-progress-label' and text()='Loading...']";

  /**
   * Xpath for Loading message
   */
  public static final String LOADING_MESSAGE_02 = "//div[@class='status-message' and contains(text(),'Loading')]";

  /**
   *
   */
  public static final String RMMODULEPAGE_CREATEBUTTON_XPATH = "//div[@class='create-new-artifact-button-container']";

  /**
   *
   */
  public static final String RMARTIFACTPAGE_SAVIGLINK_XPATH =
      "//div[@class='status-message' and contains(text(),'Saving link')]";

  /**
   *
   */
  public static final String RMARTIFACTPAGE_LOADINGIMG_XPATH = "//img[@class='loadingImg']";

  /**
   * XPATH for options in Create PDF Document in Module Page
   */
  public static final String MODULEPAGE_CREATEPDFDOCUMENTDIALOG_MODULELAYOUT_SELECTED_XPATH =
      "//select[@data-dojo-attach-point='_modLayout']/option[@selected='selected']";

  /**
   * XPATH for options in Create PDF Document in Module Page
   */
  public static final String MODULEPAGE_CREATEPDFDOCUMENTDIALOG_MODULELAYOUT_OPTION_XPATH =
      "//select[@data-dojo-attach-point='_modLayout']/option[text()='DYNAMIC_VAlUE']";

  /**
   * XPATH for artifact with DYNAMIC_VAlUE is artifact ID
   */
  public static final String RMARTIFACTPAGE_ARTIFACT_XPATH =
      "//a[@class='jazz-ui-ResourceLink' and text()='DYNAMIC_VAlUE']";

  /**
   * XPATH for link of an artifact
   */
  public static final String RMARTIFACTPAGE_LINKOFARTIFACT_XPATH =
      "//td[@colid='DYNAMIC_VAlUE0']//a[@class='jazz-ui-ResourceLink' and text()='DYNAMIC_VAlUE1']/parent::div";

  /**
   * XPATH for link of an artifact
   */
  public static final String RMMODULEPAGE_MODULELAYOUT_DROP_XPATH = "//select[@data-dojo-attach-point='_modLayout']";

  /**
   * XPATH for content of an artifact
   */
  public static final String RMMODULEPAGE_ARTIFACTCONTENT_XPATH =
      "//a[@class='jazz-ui-ResourceLink' and text()='DYNAMIC_VAlUE']/following::td[1][@colid='module_content_col']//div[@data-dojo-attach-point='_primaryTextNode']";

  /**
   * XPATH for Add widget button in Mini Dashboard page
   */
  public static final String RMMINIDASHBOARDPAGE_ADDWIDGETBUTTON_XPATH = "//img[@alt='Add Widget']";

  /**
   * XPATH for filter box in Mini Dashboard page
   */
  public static final String RMMINIDASHBOARDPAGE_FILTERWIDGET_XPATH = "//input[@name='filter-box']";

  /**
   * XPATH for Add Widget button below the widget in Mini Dashboard page
   */
  public static final String RMMINIDASHBOARDPAGE_ADDWIDGETBUTTON_BELOWTHEWIDGET_XPATH =
      "//div[@class='widget']//div[//a[@class='att-title' and text()='DYNAMIC_VAlUE']]/following-sibling::div[@class='add-widget-button-site']//span[text()='Add Widget']";

  /**
   * XPATH for close button button of Add Widget dialog in Mini Dashboard page
   */
  public static final String RMMINIDASHBOARDPAGE_CLOSEBUTTON_OFADDWIDGETDIALOG_XPATH =
      "//button[@dojoattachpoint='closeButton']";

  /**
   * XPATH for name of the first widget in Mini Dashboard page
   */
  public static final String RMMINIDASHBOARDPAGE_NAMEOFTHEFIRSTWIDGET_XPATH =
      "(//div[@class='jazz-ui-Drawer-content com-ibm-team-dashboard-web-ui-DashboardMini']//div[@class='jazz-ui-StyledBox jazz-ensemble-internal-WidgetContainer sbBlue sbLight shadow normal dndItem'])[1]//div[@class='header-primary']";

  /**
   * XPATH for name of the first widget in Mini Dashboard page
   */
  public static final String RMMINIDASHBOARDPAGE_FILTERBOX_XPATH =
      "//div[@class='jazz-ensemble-internal-catalog-WidgetCatalog']//input[@name='filter-box']";

  /**
   * XPATH for Close widget button in Mini Dashboard page
   */
  public static final String RMMINIDASHBOARDPAGE_REMOVEWIDGETBUTTON_XPATH =
      "(//span[text()='DYNAMIC_VAlUE']/preceding::a[@title='Remove'])[1]";

  /**
   * Xpath for Views counts of reqIF definitions page
   */
  public static final String MANAGECOMPPROPERTIES_REQIF_VIEWSCOUNT_XPATH =
      "//a[@role='button' and text()='0 selected...'] | //a[@role='button' and text()='1 selected...']";

  /**
   * XPATH for Audit History tab displayed in History page of artifact or module
   */
  public static final String ARTIFACTHISTORYPAGE_AUDITHISTORY_TAB_XPATH = "//span[text()='Audit History']";

  /**
   * XPATH for Loading icon displayed in History page of artifact or module
   */
  public static final String ARTIFACTHISTORYPAGE_LOADINGICON_XPATH = "//div[@class='loadingMsg']";

  /**
   * XPATH for a first message of an artifact's audit history
   */
  public static final String ARTIFACTHISTORYPAGE_FIRSTMESSAGE_XPATH =
      "//div[contains(@class,'AuditHistoryViewer')]//div[@dojoattachpoint='_contentDiv']/div[1]//td[@class='valueCell' and contains(.,'DYNAMIC_VAlUE')]";

  /**
   * XPATH for list column header of module, e.g: Artifact Type, Name, Validated By
   */
  public static final String ARTIFACTPAGE_LISTHEADEROFTABLE_XPATH =
      "//div[@class='com-ibm-rdm-web-grid-ViewHeader']//tr[@role='row']/td";

  /**
   * XPATH for Verification Criteria column of an artifact
   */
  public static final String ARTIFACTPAGE_VERIFICATIONCRITERIA_COLUMN_XPATH =
      "// table[@rowlabel='Artifact DYNAMIC_VAlUE0']//tr[not(contains(@style,'display:none'))]/td[DYNAMIC_VAlUE1]";

  /**
   * XPATH for Edit verification criteria button
   */
  public static final String ARTIFACTPAGE_EDIT_VERIFICATOINCRITERIA_BUTTON_XPATH =
      "//table[@rowlabel='Artifact DYNAMIC_VAlUE']//a[@title='Edit Verification Criteria']";

  /**
   * XPATH for Veritifaction Criteria text area
   */
  public static final String ARTIFACTPAGE_EDIT_VERIFICATOINCRITERIA_TEXTAREA_XPATH =
      "//div[@title='Verification Criteria']//textarea";

  /**
   * XPATH for Verification Criteria popup of an artifact
   */
  public static final String ARTIFACTPAGE_VERIFICATIONCRITERIA_POPUP_XPATH =
      "//span[@class='popup-attribute-editor-title-span' and text()='Verification Criteria']";

  /**
   * XPATH for last column of artifact table
   */
  public static final String ARTIFACTPAGE_LASTCOLUMNOFARTIFACTTABLE_XPATH = "//td[@class='_lastCol']";

  /**
   * XPATH for first Audit history message row
   */
  public static final String AUDITHISTORYPAGE_FIRSTMESSAGEROW_XPATH =
      "//div[contains(@class,'AuditHistoryViewer')]//div[@dojoattachpoint='_contentDiv']/div[1]";

  /**
   * XPATH for "Remove filter" of a attribute in filter
   */
  public static final String RMARTIFACTPAGE_REMOVEFILTEROFATTRIBUTE_BUTTON_XPATH =
      "//div[@class='condition-div' and starts-with(@title,'DYNAMIC_VAlUE')]/preceding::img[@alt='Remove filter'][1]";

  /**
   * XPATH for disabled "Apply" button
   */
  public static final String RMARTIFACTPAGE_DISABLED_APPLY_BUTTON_XPATH =
      "//div[@class='filter-pane-apply-conditions-div']//button[text()='Apply' and @disabled]";

  /**
   * XPATH for disabled "Add exsiting Artifact" button
   */
  public static final String RMARTIFACTPAGE_ADDEXISTINGARTIFACT_BUTTON_XPATH =
      "//ul/li[1]/span[text()='Add Existing Artifact'] | //ul/li[1][@class='addExisting']";

  /**
   * XPATH for  "Add exsiting Artifact" button
   */
  public static final String RMMODULEPAGE_ADDEXISTINGARTIFACT_BUTTON_XPATH =
      "//span[text()='Add Existing Artifact']";
  /**
   * XPATH for view in module
   */
  public static final String RMMODULEPAGE_VIEW_XPATH =
      "//span[contains(@class,'list-view') and @tabindex and text()='DYNAMIC_VAlUE']";

  /**
   * XPATH for view options button
   */
  public static final String RMARTIFACTPAGE_VIEWOPTION_BUTTON_XPATH =
      "//div[contains(@style,'visibility: visible')]//span[@title='View Options']";

  /**
   * XPATH for button "Export View..." in Artifact page
   */
  public static final String RMARTIFACTPAGE_EXPORTVIEW_BUTTON_XPATH = "//td[text()='Export View...']";

  /**
   * XPATH for export format option, e.g: XLS, CSV
   */
  public static final String RMARTIFACTPAGE_EXPORTFORMAT_OPTION_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/preceding::input[1][@name='createGroup']";

  /**
   * XPATH for test case status (PASSED) under Validated By attribute of an artifact
   */
  public static final String RMARTIFACTPAGE_TESTCASESTATUS_PASSED_UNDER_VALIDATEDBYATTRIBUTE_XPATH =
      "//table[@rowlabel='Artifact DYNAMIC_VAlUE0']//td[@class='val-by']//tr[@class='calm-link-row-widget' and contains(.,'DYNAMIC_VAlUE1')]//span[@class='imgWrapper']/img[contains(@src,'success_ovr.gif')]";

  /**
   * XPATH for test case status (FAILED) under Validated By attribute of an artifact
   */
  public static final String RMARTIFACTPAGE_TESTCASESTATUS_FAILED_UNDER_VALIDATEDBYATTRIBUTE_XPATH =
      "//table[@rowlabel='Artifact DYNAMIC_VAlUE0']//td[@class='val-by']//tr[@class='calm-link-row-widget' and contains(.,'DYNAMIC_VAlUE1')]//span[@class='imgWrapper']/img[contains(@src,'fail_ovr.gif')]";
  /**
   * XPATH for tab page
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_TAB_XPATH = "//div[@title='DYNAMIC_VAlUE']";
  /**
   * XPATH for Artifact Content inside module
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_ARTIFACTCONTENTINSIDEMODULE_XPATH =
      "//div[@data-dojo-attach-point='_primaryTextNode']//p[text()='DYNAMIC_VAlUE']";
  /**
   * XPATH for Artifact Content inside module
   */
  public static final String RMMODULEPAGE_CREATE_ARTIFACT_DIALOG_XPATH = "//div[@class='border']";
  /**
   * XPATH for module radio button on Create Link dialog
   */
  public static final String RMLINKSPAGE_MODULE_RADIO_BUTTON_XPATH =
      "//span[contains(@dojoattachpoint,'Modules')]//input[@type='radio']";
  /**
   * XPATH for module input on Create Link dialog
   */
  public static final String RMMODULEPAGE_MODULE_INPUT_XPATH =
      "//input[@id='com_ibm_rdm_web_common_ArtifactInstancesFilteringSelect_0']";
  /**
   * XPATH for module Id input on Create Link dialog
   */
  public static final String RMLINKSPAGE_MODULE_INPUT_XPATH =
      "//div[@title='DOORS/DNG Link to RDM Requirements Specification (DYNAMIC_VAlUE)']";
  /**
   * XPATH for Views in Create Link dialog
   */
  public static final String RMLINKSPAGE_VIEWS_XPATH =
      "//span[@id='dijit_layout_TabContainer_1_tablist_com_ibm_rdm_web_project_SavedFiltersSection_3' and text()='Views']";
  /**
   * XPATH for What to look in in Create Link dialog
   */
  public static final String RMLINKSPAGE_WHAT_TO_LOOK_IN_XPATH =
      "//legend[@class='status-messages search-message' and text()='What to look in:']";
  /**
   * XPATH for child attribute label in Condition
   */
  public static final String RMARTIFACTSPAGE_CHILD_ATTRIBUTE_LABEL_XPATH =
      "//div[@class='child-conditions-group']//span[@class='condition-summary-title' and text()='DYNAMIC_VAlUE']";
  /**
   * XPATH for attribute value in filter
   */
  public static final String RMARTIFACTSPAGE_ATTRIBUTE_VALUE_IN_FILTER_XPATH =
      "//div[@title='DYNAMIC_VAlUE - is any of - (any)']";
  /**
   * XPATH for edit button for attribute value in filter
   */
  public static final String RMARTIFACTSPAGE_ATTRIBUTE_VALUE_EDIT_BUTTON_FILTER_XPATH =
      "//div[@title='DYNAMIC_VAlUE - is any of - (any)']/preceding-sibling::div//a[@title='Edit filter']";
  /**
   * XPATH for edit button for 'Modules:' label in Create Link Dialog
   */
  public static final String RMLINKSPAGE_MODULES_LABEL_CREATELINK_DIALOG_XPATH =
      "//span[contains(@dojoattachpoint,'Modules')]";
  /**
   * XPATH for newly artifact inserted After or Below
   */
  public static final String RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_AFTER_XPATH =
      "//table[@rowlabel='Artifact DYNAMIC_VAlUE']/following-sibling::table[1]//td[@class='id-col']//a[@class='jazz-ui-ResourceLink']";
  /**
   * XPATH for newly artifact inserted Before
   */
  public static final String RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_BEFORE_XPATH =
      "//table[@rowlabel='Artifact DYNAMIC_VAlUE']/preceding-sibling::table[1]//td[@class='id-col']";
  /**
   * XPATH for Artifact type dropdown after inserting a new artifact before or after an existing artifact in the module
   */
  public static final String RMARTIFACTSPAGE_ARTIFACTTYPE_INSERTED_XPATH =
      "//td[@class='typeDropdownContainer']//table";
  /**
   * XPATH for option "Other Artifact Type or Row Style" in selecting artifact type pop-up
   */
  public static final String RMARTIFACTSPAGE_OTHER_ARTIFACT_TYPE_XPATH =
      "//td[contains(text(),'Other Artifact Type or Row Style')]";
  /**
   * XPATH for artifact type while selecting type for artifact inserted after/before an existing artifact. <br>
   * DYNAMIC_VAlUE: is the type of artifact ID to be inserted
   */
  public static final String RMARTIFACTSPAGE_ARTIFACT_TYPE_XPATH =
      "(//a[@class='entry' and @aria-label='DYNAMIC_VAlUE'])[last()]";
  /**
   * XPATH for "Select an Artifact Type" dialog. <br>
   */
  public static final String RMARTIFACTSPAGE_SELECT_AN_ARTIFACT_TYPE_DIALOG_XPATH =
      "//div[@class='header-primary' and @title='Select an Artifact Type']";
  /**
   * XPATH for default artifact type of inserted artifact. <br>
   */
  public static final String RMARTIFACTSPAGE_DEFAULT_TYPE_OF_INSERTED_ARTIFACT_XPATH =
      "//div[@class='rdmTypeGroupContentArea']//div[@data-dojo-attach-point='titleNode']";
  /**
   * XPATH for delete Artifact Type button <br>
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_DELETE_ARTIFACT_TYPE_XPATH = "//td[text()='Delete...']";
  /**
   * XPATH for delete Artifact Type button <br>
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_YES_BUTTON_XPATH =
      "//button[@data-cspf-tag='MessageForm_button_Yes']";
  /**
   * XPATH for Notice message when deleting artifact type <br>
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_NOTICE_MESSAGE_XPATH = "//div[text()='Notice']";
  /**
   * XPATH for filter condition in Artifact page DYNAMIC_VAlUE0: attribute to filter, e.g: Artifact Type, Link type
   * DYNAMIC_VAlUE1: is any of, exists DYNAMIC_VAlUE2: condition value: Heading, Information (Artifact Type); Validated
   * By, Satisfied By (Link type)
   */
  public static final String RMARTIFACTPAGE_FILTERCONDITION_XPATH =
      "//*[span[@class='condition-summary-title' and text()='DYNAMIC_VAlUE0'] and span[@class='condition-summary-operator' and text()='DYNAMIC_VAlUE1'] and span[@class='condition-summary-title' and normalize-space(text()='DYNAMIC_VAlUE2')]]";
  /**
   * XPATH for hyperlink display in artifact content DYNAMIC_VAlUE0: artifact ID which have hyperlink in the content
   * DYNAMIC_VAlUE1: hyperlink text
   */
  public static final String RMARTIFACTPAGE_HYPERLINK_INARTIFACTCONTENT_XPATH =
      "(//table[@rowlabel='Artifact DYNAMIC_VAlUE0']//p[@dir='ltr']//a[@class='jazz-ui-ResourceLink' and text()='DYNAMIC_VAlUE1'])[1]";
  /**
   * XPATH for hover pop up of hyperlink displayed in artifact content DYNAMIC_VAlUE: hyperlink include ID and name,
   * e.g: '474709: Operating point - T100A3655'
   */
  public static final String RMARTIFACTPAGE_HOVER_POPUP_HYPERLINK_INARTIFACTCONTENT_XPATH =
      "//div[@class='jazz-ui-HoverPopup jazz-ui-ResourceHover']//a[@dojoattachpoint='titleLink' and text()='DYNAMIC_VAlUE']";
  /**
   * XPATH for 'Search other components' in term look up dialog<br>
   */
  public static final String RMMODULEPAGE_SEARCH_OTHER_COMPONENTS_XPATH = "//a[text()='Search other components']";
  /**
   * XPATH for 'Showing X matches' in term look up dialog<br>
   */
  public static final String RMMODULEPAGE_MATCHES_IN_LOOKUP_TERM_DIGLOG_XPATH =
      "//span[contains(text(),'Showing DYNAMIC_VAlUE match')]";
  /**
   * XPATH for term hyperlink in term look up dialog<br>
   */
  public static final String RMMODULEPAGE_TERM_HYPERLINK_IN_TERM_DIGLOG_XPATH =
      "//span[text()=' (DYNAMIC_VAlUE0)']//preceding::a[text()='DYNAMIC_VAlUE1'][1]";
  /**
   * XPATH for status message<br>
   */
  public static final String STATUS_MESSAGE = "//div[@class='status-message']";
  /**
   * XPATH for term hyperlink in artifact content<br>
   */
  public static final String TERM_HYPERLINK_IN_ARTIFACT =
      "//a[text()='DYNAMIC_VAlUE0']//following::a[text()='DYNAMIC_VAlUE1']";
  /**
   * XPATH for "Loading artifact links..." message
   */
  public static final String LOADINGARTIFACTLINKS_MESSAGE_XPATH =
      "//div[@class='status-message' and contains(text(),'Loading artifact links')]";
  /**
   * XPATH for "Loading artifact links..." message
   */
  public static final String RMMODULEPAGE_TERM_INDEX_XPATH =
      "(//div[@class='definition'])[DYNAMIC_VAlUE]/parent::div/parent::div/div[@class='title']/a";
  /**
   * XPATH for Showing selected modules in artifact page
   */
  public static final String RMARTIFACTPAGE_SELECTED_MODULE_NUM_XPATH = "//span[text()='DYNAMIC_VAlUE selected']";
  /**
   * XPATH cancel button in Create Link dialog
   */
  public static final String RMLINKSPAGE_CANCEL_BUTTON_XPATH = "//button[text()='Cancel']";
  /**
   * XPATH Edit Link pencil icon
   */
  public static final String RMARTIFACTSPAGE_EDIT_LINK_ICON_XPATH = "//a[@title='Edit Links']";
  /**
   * XPATH Remove Link option
   */
  public static final String RMARTIFACTSPAGE_REMOVE_LINK_OPTION_XPATH = "//td[text()='Remove Link']";
  /**
   * XPATH for Traced By Architecture Element link type icon
   */
  public static final String RMARTIFACTSPAGE_TRACED_BY_ICON_XPATH = "//a[text()='DYNAMIC_VAlUE']//preceding::img[1]";
  /**
   * XPATH for 'Edit Links' icon
   */
  public static final String RMARTIFACTSPAGE_EDIT_LINKS_ICON_XPATH =
      "//a[text()='DYNAMIC_VAlUE']//following::a[@title='Edit Links'][1]";
  /**
   * XPATH for 'Remove Link' sub option
   */
  public static final String RMARTIFACTSPAGE_REMOVE_LINK_SUB_OPTION_XPATH =
      "//table[contains(@class,'dijitMenuActive')]//td[text()='Remove Link']";
  /**
   * XPATH for link need to remove sub option
   */
  public static final String RMARTIFACTSPAGE_LINK_TO_REMOVE_OPTION_XPATH =
      "//table[contains(@class, 'dijitMenuActive')]//td[text()='DYNAMIC_VAlUE']";
  /**
   * XPATH for link icon
   */
  public static final String RMARTIFACTSPAGE_LINK_ICON_XPATH =
      "//a[text()='DYNAMIC_VAlUE0']//following::a[text()='DYNAMIC_VAlUE1'][1]//preceding::img[1]";
  /**
   * XPATH for link present in collumn
   */
  public static final String RMARTIFACTSPAGE_LINK_ON_COLUMN_XPATH =
      "//a[text()='DYNAMIC_VAlUE0']//following::a[text()='DYNAMIC_VAlUE1'][1]";
  /**
   * XPATH for 'Edit' button on side panel in module of artifact
   */
  public static final String RMMODULEPAGE_EDIT_BUTTON_ON_SIDE_PANEL_XPATH =
      "//span[text()='DYNAMIC_VAlUE:']//preceding::button[text()='Edit'][1]";
  /**
   * XPATH for dropdown button of attribute 'AEn_ThermoSystems_HSI-FlagSavedToLeanComErrorStruct'
   */
  public static final String RMMODULEPAGE_DROPDOWN_BUTTON_OF_HSIFLAG_XPATH =
      "//label[text()='AEn_ThermoSystems_HSI-FlagSavedToLeanComErrorStruct:']//following::input[1]";
  /**
   * XPATH for yes option of attribute 'AEn_ThermoSystems_HSI-FlagSavedToLeanComErrorStruct'
   */
  public static final String RMMODULEPAGE_YES_OPTION_XPATH = "//div[text()='yes']";
  /**
   * XPATH for 'Done' button on side panel in module of artifact
   */
  public static final String RMMODULEPAGE_DONE_BUTTON_ON_SIDE_PANEL_XPATH =
      "//span[text()='DYNAMIC_VAlUE:']//preceding::button[text()='Done'][1]";
  /**
   * XPATH for value of attribute 'AEn_ThermoSystems_HSI-FlagSavedToLeanComErrorStruct' of selected artifact
   */
  public static final String RMMODULEPAGE_HSIFLAG_ATTRIBUTE_XPATH =
      "//label[text()='DYNAMIC_VAlUE0:']//span[text()='DYNAMIC_VAlUE1']";
  /**
   * XPATH for 'To module' icon button in 'Audit History'
   */
  public static final String RMMODULEPAGE_TO_MODULE_BUTTON_XPATH = "//img[@class='client-navigation-icon-back']";
  /**
   * XPATH for 'module' tab inside module
   */
  public static final String RMMODULEPAGE_MODULE_TAB_XPATH =
      "//span[text()='Selected Artifact']//preceding::span[text()='Module']";
  /**
   * XPATH for 'Close History' button
   */
  public static final String RMMODULEPAGE_CLOSE_HISTORY_BUTTON_XPATH = "//button[text()='Close History']";
  /**
   * XPATH for Create button in module page
   */
  public static final String RMMODULEPAGE_CREATE_BUTTON_XPATH = 
      "//div[@data-dojo-attach-point='_createNewArtifactButtonContainerDiv']//span[text()='Create']";
  /**
   * XPATH for View Options menu item
   */
  public static final String RMMODULEPAGE_VIEWOPTIONS_MENU_XPATH = 
      "(//table[contains(@class,'dijitMenuActive')] | //table[contains(@class, 'dijitMenuSelected')])//*[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   * XPATH for 'expand' artifact button
   */
  public static final String RMMODULEPAGE_EXPANDARTIFACT_ICON_XPATH =
      "//table[@rowlabel='Artifact DYNAMIC_VAlUE']//*[@class='twistie' and @aria-label='Toggle Children']";

  /**
   * XPATH for the 'expand' artifact button which is already expanded
   */
  public static final String RMMODULEPAGE_EXPANDARTIFACT_ICON_EXPANDED_XPATH =
      "//table[@rowlabel='Artifact DYNAMIC_VAlUE']//div[@class='rdm-artifactgrid-contenteditablecellwidget expandable']";

  /**
   * XPATH for the 'expand' artifact button which is already collapsed
   */
  public static final String RMMODULEPAGE_EXPANDARTIFACT_ICON_COLLAPSED_XPATH =
      "//table[@rowlabel='Artifact DYNAMIC_VAlUE']//div[@class='rdm-artifactgrid-contenteditablecellwidget expandable collapsed']";
  /**
   * XPATH for dropdown button of attribute for artifact type 'AEn_ThermoSystems_HSI-Flag'
   */
  public static final String RMMODULEPAGE_DROPDOWN_OF_HSIFLAG_XPATH =
      "//label[text()='DYNAMIC_VAlUE:']//following::input[1]";
  /**
   * XPATH for Link contains text
   */
  public static final String RMARTIFACTPAGE_LINK_CONTAINS_TEXT_XPATH =
      "//a[@class='jazz-ui-ResourceLink'][starts-with(.,'DYNAMIC_VAlUE')]";
  /**
   * XPATH for Iframe after hovering on link
   */
  public static final String RMARTIFACTPAGE_LINK_HOVERIFRAME_QUICKINFO_XPATH =
      "//iframe[@dojoattachpoint='hoverIframe']";
  /**
   * XPATH for entry message "Loading artifacts..." after selecting Module in "What to look in:" option
   */
  public static final String RMARTIFACTPAGE_LINK_MODULE_LOADINGARTIFACTS_XPATH =
      "//span[text()='Loading artifacts...']";

  /**
   * XPATH for
   */
  public static final String RMDASHBOARDPAGE_HOMEMENU_XPATH = "//div[contains(@class,'jazz-app-HomeMenu')]";

  /**
   * XPATH for name of an Artifact Type to be selected
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_NAMEOFARTIFACTTYPE_XPATH =
      "//input[contains(@id,'nameField')]";

  /**
   * XPATH for
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_ATTRIBUTE_XPATH =
      "//td[@colid='adl_title_col']//div[text()='DYNAMIC_VAlUE']";

  /**
   * XPATH for
   */
  public static final String RMMANAGECOMPONENTPROPERTIES_REQIFEXPORTED_XPATH = "//div[text()='DYNAMIC_VAlUE']";

  /**
   * XPATH for
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_ARTIFACTTYPE_XPATH =
      "//div[@class='object-list-item' and text()='DYNAMIC_VAlUE']";

  /**
   * XPATH for
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_ARTIFACTATTRIBUTE_INARTIFACTTYPE_XPATH =
      "//div[@dojoattachpoint='_attributeDefinitionsTable']//td[@class='o_title_col']//div[text()='DYNAMIC_VAlUE']";

  /**
   * XPATH for
   */
  public static final String RMMANAGECOMPONENTPROPERTIESPAGE_MOREACTIONS_OFARTIFACTATTRIBUTE_XPATH =
      "//td[@colid='adl_title_col']//div[text()='DYNAMIC_VAlUE']/following::img[1][@class='menuLauncher']";

  /**
   * XPATH for Confirm Deletion dialog
   */
  public static final String RMARTIFACTPAGE_CONFIRMDELETION_DIALOG_XPATH = "//div[text()='Confirm Deletion']";
  
  /**
   * XPATH for maxLb row of artifact
   */
  public static final String RMMODULEPAGE_MAXLB_ROW_XPATH =
      "//span[text()='maxLb']//following::a[text()='DYNAMIC_VAlUE']//following::td[2]";

  /**
   * XPATH for maxLb edit button of artifact
   */
  public static final String RMMODULEPAGE_MAXLB_EDIT_BUTTON_XPATH = "//a[@title='Edit maxLb']//span";
  
  /**
   * XPATH for maxLb edit dialog of artifact
   */
  public static final String RMMODULEPAGE_MAXLB_EDIT_DIALOG_XPATH = 
      "//div[@class='popup-attribute-editor-title-div']//span[text()='maxLb']";

  /**
   * XPATH for maxLb edit textbox of artifact
   */
  public static final String RMMODULEPAGE_MAXLB_EDIT_TEXTBOX_XPATH =
      "//span[@class='popup-attribute-editor-title-span']//following::input[2]";
  
  /**
   * XPATH for content collumn
   */
  public static final String RMMODULEPAGE_CONTENT_COLLUMN_XPATH = "//span[text()='Contents']";
 
  /**
   * XPATH for maxLb value of artifact
   */
  public static final String RMMODULEPAGE_MAXLB_VALUE_ROW_XPATH = 
      "//span[text()='maxLb']//following::a[text()='DYNAMIC_VAlUE0']//following::td[2]//div//div[text()='DYNAMIC_VAlUE1']";
  
  /**
   * XPATH for actual maxLb value of artifact
   */
  public static final String RMMODULEPAGE_MAXLB_ACTUAL_VALUE_ROW_XPATH =
      "//span[text()='maxLb']//following::a[text()='DYNAMIC_VAlUE']//following::td[2]//div//div";
  /**
   * XPATH to click create q qurey from welcom workitem page
   */
  public static final String CCMMODULEPAGE_CLICKON_CREATEAQUERYLINK_XPATH = "//a[text()='DYNAMIC_VAlUE']";
  /**
   * XPATH for finding number of selected artifacts.
   */
  public static final String NUMBER_OF_SELECTED_ARTIFACT_SHOWING_IN_TABLE_XPATH=
      "//div[@class='numSelectedArtifacts']";
  /**
   * XPATH for error message in module find dialog indicates searched artifact not existing in module.
   */
  public static final String RMMODULEPAGE_ERROR_MESSAGE_MODULE_FIND_XPATH=
      "//div[text()=\"The artifact ID, 'DYNAMIC_VAlUE', was not found.\"]";
  /**
   * XPATH for Add to module dialog.
   */
  public static final String RMMODULEPAGE_ADD_TO_MODULE_XPATH=
      "//span[@dojoattachpoint='_headerPrimarySpan' and text()='Add to module...']";
  /**
   * XPATH for Artifact added message from add to module dialog.
   */
  public static final String RMMODULEPAGE_ARTIFACT_ADDED_MESSAGE_XPATH = "//div[text()='Artifact(s) have been added.']";

  /*-----------------Requirements View Widget-----------------*/
  /**
   * XPATH for 'Requirements View' header widget.
   */
  public static final String HEADER_REQUIREMENTS_VIEW_XPATH =
      "(//div[@class='header-primary'][.//a[contains(text(),'DYNAMIC_VAlUE')]])[1]";
  /**
   * XPATH for 'Select...' Project button in 'Requirements View' widget.
   */
  public static final String SELECT_PROJECT_BUTTON_IN_REQUIREMENTS_VIEW_XPATH =
      "//label[normalize-space(text())='Project']/parent::td[@class='label']/parent::tr//button[normalize-space(text())='Select...']";
  /**
   * XPATH for 'Select...' Component button in 'Requirements View' widget.
   */
  public static final String SELECT_COMPONENT_BUTTON_IN_REQUIREMENTS_VIEW_XPATH =
      "//label[normalize-space(text())='Component']/parent::td[@class='label']/parent::tr//button[normalize-space(text())='Select...']";
  /**
   * XPATH for 'Select...' Configuration button in 'Requirements View' widget.
   */
  public static final String SELECT_CONFIGURATION_BUTTON_IN_REQUIREMENTS_VIEW_XPATH =
      "//label[normalize-space(text())='Configuration']/parent::td[@class='label']/parent::tr//button[normalize-space(text())='Select...']";
  /**
   * XPATH for 'Select...' Module button in 'Requirements View' widget.
   */
  public static final String SELECT_MODULE_BUTTON_IN_REQUIREMENTS_VIEW_XPATH =
      "//label[normalize-space(text())='Module']/parent::td[@class='label']/parent::tr//button[normalize-space(text())='Select...']";
  /**
   * XPATH for 'Select...' View button in 'Requirements View' widget.
   */
  public static final String SELECT_VIEW_BUTTON_IN_REQUIREMENTS_VIEW_XPATH =
      "//label[normalize-space(text())='View']/parent::td[@class='label']/parent::tr//button[normalize-space(text())='Select...']";
  /**
   * XPATH for 'Items to show' dropdown in 'Requirements View' widget.
   */
  public static final String ITEMSTOSHOW_DROPDOWN_IN_REQUIREMENTS_VIEW_XPATH =
      "//label[normalize-space(text())='Items to show']/parent::td[@class='label']/parent::tr//select[@name='itemsToShow']";
  /**
   * XPATH for 'Select Project Area' value after click on 'Select...' Project in 'Requirements View' widget.
   */
  public static final String SELECTPROJECTAREA_VALUE_IN_REQUIREMENTS_VIEW_XPATH =
      "//div[@class='label'][text()='DYNAMIC_VAlUE']";
  /**
   * XPATH for 'Select the Component Context' value after click on 'Select...' Component in 'Requirements View' widget.
   */
  public static final String SELECTTHECOMPONENTCONTEXT_VALUE_IN_REQUIREMENTS_VIEW_XPATH =
      "//span[normalize-space(text())='DYNAMIC_VAlUE']/parent::td[@cell-value]";
  /**
   * XPATH for 'Select the Configuration Context' value after click on 'Select...' Configuration in 'Requirements View'
   * widget.
   */
  public static final String SELECTTHECONFIGURATIONCONTEXT_VALUE_IN_REQUIREMENTS_VIEW_XPATH =
      "//span[normalize-space(text())='DYNAMIC_VAlUE']/ancestor::td[@cell-value='DYNAMIC_VAlUE']";
  /**
   * XPATH for Header popup in 'Requirements View' widget.
   */
  public static final String HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH =
      "//div[contains(@class, 'heading')][text()='DYNAMIC_VAlUE']";
  /**
   * XPATH for textbox search in 'Choose a module' popup after click on 'Select...' Module in 'Requirements View'
   * widget.
   */
  public static final String TEXTBOX_CHOOSEAMODULE_POPUP_IN_REQUIREMENTS_VIEW_XPATH =
      "//div[normalize-space(text())='Choose a module']/parent::div[contains(@class,'header')]/following-sibling::div[contains(@class,'content')]//input[@role='textbox']";
  /**
   * XPATH for textbox search in 'Choose a View' popup after click on 'Select...' View in 'Requirements View' widget.
   */
  public static final String TEXTBOX_CHOOSEAVIEW_POPUP_IN_REQUIREMENTS_VIEW_XPATH =
      "//div[normalize-space(text())='Choose a view']/parent::div[contains(@class,'header')]/following-sibling::div[contains(@class,'content')]//input[@class='SearchInputText']";
  /**
   * XPATH for 'Choose a module' value after click on 'Select...' Module in 'Requirements View' widget.
   */
  public static final String CHOOSEAMODULE_VALUE_IN_REQUIREMENTS_VIEW_XPATH =
      "(//div[contains(@class,'dijitComboBoxMenuPopup')]//span[contains(normalize-space(text()), 'DYNAMIC_VAlUE')]/parent::div[contains(@class, 'dijitMenuItem')][@role='option'])[1]";
  /**
   * XPATH for 'Choose a view' value after click on 'Select...' View in 'Requirements View' widget.
   */
  public static final String CHOOSEAVIEW_VALUE_IN_REQUIREMENTS_VIEW_XPATH =
      "//span[normalize-space(text())='DYNAMIC_VAlUE']";
  /**
   * XPATH for 'OK' button in 'Requirements View' widget.
   */
  public static final String BUTTON_OK_IN_REQUIREMENTS_VIEW_XPATH =
      "//span[normalize-space(text()) = 'Requirements View']/following::button[text()='OK']";
  /**
   * XPATH for 'Remove' button header in 'Requirements View' widget.
   */
  public static final String BUTTON_REMOVE_HEADER_IN_REQUIREMENTS_VIEW_XPATH =
      "(//span[contains(text(), 'DYNAMIC_VAlUE')]/ancestor::div[@role='region']//img[@alt='Remove' or @alt='Close'])[1]";
  /**
   * XPATH for 'OK' button in 'Requirements View' widget.
   */
  public static final String COMMENT_COLLUMN_XPATH = "//span[@class='comment-column-icon']";
  /**
   * XPATH for comment in artifact with subject 'Test Comment'.
   */
  public static final String COMMENT_IN_ARTIFACT_XPATH =
      "(//span[contains(text(),'DYNAMIC_VAlUE:')]//following::span[text()='Test Comment'])[1]";
  /**
   * XPATH for comment in artifact.
   */
  public static final String COMMENT_ARTIFACT_XPATH =
      "(//span[contains(text(),'DYNAMIC_VAlUE0:')]//following::span[text()='DYNAMIC_VAlUE1'])[1]";
  /**
   * XPATH for delete comment in artifact.
   */
  public static final String DELETE_COMMENT_XPATH = "//a[@title='Delete Comment']";
  /**
   * Xpath for Row Attribute in Add Attribute Dialog
   */
  public static final String ADD_ATTRIBUTE_ROW_ATTRIBUTE_XPATH = 
      "//div[@class='jazz-ui-Dialog-heading' and text()='Add Attribute']//ancestor::div[@role='dialog']//div[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for Artifact content in module using Section number
   */
  public static final String ARTIFACT_CONTENT_BY_SECTION_ID_XPATH = 
      "(//div[@class='sectionNumber']//span[text()='DYNAMIC_VAlUE']/ancestor::td)[last()]//.";  
  /**
   * Xpath for artifact ID in module, which is insert above the another selected artifact
   */
  public static final String ARTIFACT_ID_BY_ADJACENCE_ABOVE_ARTIFACT_XPATH ="(//a[text()='DYNAMIC_VAlUE']//ancestor::div//preceding-sibling::div[@class='topInsertParent']//div[@class='id-col gridCellWrapper']//a)[last()]";
  
  /**
   * Xpath for artifact ID in module, which is insert below as a child of  the another selected artifact
   */
  public static final String ARTIFACT_CHILD_ID_BY_PARENT_ARTIFACT_XPATH ="(//a[text()='DYNAMIC_VAlUE']//ancestor::table[contains(@class,'depth1')]//following-sibling::table[contains(@class,'depth2')]//td[@class='id-col']//a)[1]";
  
  /**
   * Xpath for artifact ID in module, which is insert after the another selected artifact
   */
  public static final String ARTIFACT_ID_BY_ADJACENCE_BELOW_ARTIFACT_XPATH ="(//a[text()='DYNAMIC_VAlUE']//ancestor::table[contains(@class,'depth')]//following-sibling::table[contains(@class,'depth')]//td[@class='id-col']//a)[1]";

  
  /**
   * Xpath for expand button at folder hirarchy
   */
  public static final String FOLDER_HIERARCHY_EXPAND_BUTTON_XPATH = 
      "//span[text()='DYNAMIC_VAlUE']/parent::span/parent::div/span[contains(@class,'TreeExpando') and not(contains(@class,'TreeExpandoLeaf'))]";
  /**
   * Xpath for child level 1 at folder hirarchy
   */
  public static final String FOLDER_HIERARCHY_CHILD_LEVEL1_XPATH = 
      "//div[contains(@class,'TreeIsRoot')][.//span[text()='DYNAMIC_VAlUE0']]/div[@data-dojo-attach-point='containerNode']/div[contains(@id,'ContextTreeNode')]//span[@role='treeitem' and text()='DYNAMIC_VAlUE1']";
  /**
   * Xpath for folder at folder hirarchy
   */
  public static final String FOLDER_HIERARCHY_FOLDER_XPATH = 
      "//span[text()='DYNAMIC_VAlUE']/parent::span[contains(@class,'TreeContentExpanded')]/parent::div/following-sibling::div[contains(@class,'TreeNodeContainer')]/div";
  /**
   * Xpath for Artifact ID inside a Module, based on Artifact Name 
   */
  public static final String RMMODULEINSIDE_ARTIFACT_ID_BY_ARTIFACT_NAME_XPATH= "//a[contains(text(),'DYNAMIC_VAlUE')]//ancestor::td[@class='name cellEditable']//preceding-sibling::td[@class='id-col']//a";
  
  /**
   * Xpath for comment textfield
   */
  public static final String RMARTIFACTSPAGE_COMMENT_TEXTFIELD_XPATH= "//body[@contenteditable='true']";
  
  /**
   * Xpath for Artifact ID "In Module", when using Quick Search. It will shown more than 1 result if options is set to "Folder and Module".
   */
  public static final String RMARTIFACTPAGE_QUICKSEARCH_ARTIFACT_ID_IN_MODULE_= "//*[contains(text(),'In Module: ')]/parent::div//preceding-sibling::div[1]//*[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * 
   */
  public static final String RMCOMPONENT_WARNING_DUPLICATE_MESSAGE ="//*[contains(@class,'Dialog-header')]/following-sibling::div[contains(@class,'Dialog-content')]//div[contains(text(),'DYNAMIC_VAlUE')]";
}