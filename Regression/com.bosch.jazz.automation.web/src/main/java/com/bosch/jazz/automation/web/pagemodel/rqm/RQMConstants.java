/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;


/**
 * Constants for model class RQMDashboradPage.
 */
public class RQMConstants {

  private RQMConstants() {

  }

  /**
   * TEST_ARTIFACT_TITLE_VALUE is the title for the RQM requirement
   */
  public static final String TEST_ARTIFACT_TITLE_VALUE = "testArtifactTitleValue";
  /**
   * A dropdown of Creating using template with a text specified by DYNAMIC_VALUE. Possible values: 'Classic test case
   * template', 'Classic test plan template' etc.
   */
  public static final String RQMPROJECT_TESTCASETEMP_DROPDOWN_XPATH =
      "//div[@class='asq-common-popup-chooser-panel']/descendant::span[contains(text(),'DYNAMIC_VAlUE')]/..";
  /**
   * A button with a text specified by DYNAMIC_VALUE. Possible values: 'Ok' , 'Save', 'Cancel'etc.
   */
  public static final String JAZZPAGE_BUTTONS_XPATH = "//button[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * A button in the title actions container with a text specified by DYNAMIC_VALUE. Possible values: 'Save', 'Cancel'
   */
  public static final String RQMPROJECT_TESTCASE_BUTTON_XPATH =
      "//div[@dojoattachpoint=\"titleActionsContainer\"]/descendant::button[text()='DYNAMIC_VAlUE']";
  /**
   * A text field which takes ID to search the test case or test plan.
   */
  public static final String RQMPROJECT_ADDREQUIREMENTID_TEXTBOX_XPATH =
      "//div[@class='dijitReset dijitInputField dijitInputContainer']/input[@name='id']";
  /**
   * Click on table which contains text specified by DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTTYPEVALUE_CHECKBOX_XPATH = "//td[text()='DYNAMIC_VAlUE']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_MANAGESECTION_BUTTON_XPATH = "//a[@title='DYNAMIC_VAlUE']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_SETTOLOCKED_BUTTON_XPATH = "//a[@title='DYNAMIC_VAlUE']";
  /**
   * Click on the name from action menu based on searched name.
   */
  public static final String RQMPROJECT_ADDREQUIREMENTNAME_TEXTBOX_XPATH =
      "//div[@class='dijitReset dijitInputField dijitInputContainer']/input[@name='name']";
  /**
   * Click on the link text specified by DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH = "//*[text()='DYNAMIC_VAlUE']";
  /**
   * Click on the dropdown which specified with select class.
   */
  public static final String RQMPROJECT_ARTIFACTCONTAINERSELECTION_DROPDOWN_XPATH =
      "//select[@id='selectServiceProvider']";
  /**
   * A frame which specified by iframe tag use to move from main html page to frame page.
   */
  public static final String RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH = "//iframe[@dojoattachpoint='iframe']";
  /**
   * A text field which specified with Type to filter by text or by ID use to take id or name of artifact.
   */
  public static final String RMARTIFACTSPAGE_TYPETEXTTO_IFRAME_XPATH =
      "//input[@placeholder='Type to filter by text or by ID']";
  /**
   * A variable in rqm dashboard page which takes value to perform a specific operation.
   */
  public static final String RM_REQ_ID_VALUE = "rmreqIdValue";
  /**
   * A variable in rqm dashboard page which takes value to perform a specific operation.
   */
  public static final String KEYWORD = "Keyword";
  /**
   * A variable in rqm dashboard page which takes value to perform a specific operation.
   */
  public static final String TEST_ARTIFACT_NAME = "testArtifactName";
  /**
   * Add and Close is one of the RQM button use to click on button.
   */
  public static final String ADD_AND_CLOSE = "Add and Close";
  /**
   * A table with a text specified by DYNAMIC_VALUE. Possible values: 'Export Details', 'Export Comprehensive' , 'Export
   * Summary' , 'Run' etc.
   */
  public static final String RQMPROJECT_TESTCASEEXPORT_DETAILS_XPATH = "//td[text()='DYNAMIC_VAlUE']";
  /**
   * exportInline is one of variable in RQMdashboardpage.
   */
  public static final String EXPORTINLINE = "exportInline";
  /**
   * alertAccept is one of variable in RQMdashboardpage.
   */
  public static final String ALERTACCEPT = "alertAccept";
  /**
   * xpath for Artifact format value for normative and inormative documents.
   */
  public static final String RMARTIFACTSPAGE_SELECT_ARTIFACT_FORMAT_VALUE_XPATH =
      "//div[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * Include links to duplicates of the test artifacts that the original artifacts link to.
   */
  public static final String RQMPROJECT_DUP_TESTSUITE_CHECKBOX_XPATH = "//label[text()='DYNAMIC_VAlUE']";
  /**
   * xpath of button used to add Development links to the RQM Artifact.
   */
  public static final String RQMPROJECT_LINKSADDLINKS_BUTTON_XPATH =
      "//div[@class='footerSection']/span/button[@dojoattachpoint='okCmd']";
  /**
   * TEST_ENVIRONMENT_NAME to be added to the test plan in RQM test plan page.
   */
  public static final String TEST_ENVIRONMENT_NAME = "testEnvironmentName";
  /**
   * xpath of the run button used to Execute the RQM Artifact.
   */
  public static final String RQMPROJECT_TESTSUITERUN_BUTTON_XPATH =
      "//a[contains(@title,'DYNAMIC_VAlUE')] | //a[@title='DYNAMIC_VAlUE']";
  /**
   * CREATE is used to define and declare execution variable.
   */
  public static final String CREATE = "Create";
  /**
   * testplanName to be deleted in verifyDelRQMReq() method in RQMDashBoardPage.
   */
  public static final String TESTPLANNAME = "testplanName";
  /**
   * Message after removing all requirement collection link.
   */
  public static final String RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH = "//div[text()='DYNAMIC_VAlUE']";
  /**
   * TESTCASES is the test artifact name in RQMdashboardpage.
   */
  public static final String TESTCASES = "Test Cases";
  /**
   * dupType is used to create a duplicate test case in RQM test case page.
   */
  public static final String DUPTYPE = "dupType";
  /**
   * RUN_T button is to run the test excecution record.
   */
  public static final String RUN_T = "Run T";
  /**
   * FINISH button in excecution record page.
   */
  public static final String FINISH = "Finish";

  /**
   * CLOSE button in excecution record page.
   */
  public static final String CLOSE = "Close";
  /**
   * START_MANUAL_TEST button is to start the manual script.
   */
  public static final String START_MANUAL_TEST = "Start Manual Test";
  /**
   * PASS_CTRL_SHIFT_P is used to set the reslt as passed in Testscript page.
   */
  public static final String PASS_CTRL_SHIFT_P = "Pass (Ctrl+Shift+P)";
  /**
   * PASS_CTRL_SHIFT_G is used to set the reslt as passed in Testscript page.
   */
  public static final String PASS_CTRL_SHIFT_G = "Pass (Ctrl+Shift+G)";
  /**
   * PASS_CTRL_SHIFT_F is used to set the reslt as failed in Testscript page.
   */
  public static final String FAIL_CTRL_SHIFT_F = "Fail (Ctrl+Shift+F)";
  /**
   * Dropdown to select in execution record page of RQM artifact.
   */
  public static final String RQMPROJECT_TESTRESULTSELECT_DROPDOWN_XPATH = "//select[@id='id-Actual Result']";
  /**
   * Dropdown to select in execution record page of RQM artifact.
   */
  public static final String RQMPROJECT_TESTRESULTVALUE_DROPDOWN_XPATH =
      "//label[text()='Actual Result: ']/../../../td[3]";
  /**
   * This is the xpath for checkboxes "Chrome 10"/"1.8" in generate new TestCase execution records page.
   */
  public static final String RQMPROJECT_BROWSERS_CHECKBOX_XPATH = "//input[@name='DYNAMIC_VAlUE']";
  /**
   * Next is a button in generate new TestCase execution records page.
   */
  public static final String NEXT = "Next >";
  /**
   * CSVFILENAME is the downloaded filename in downloadTestData() method.
   */
  public static final String CSVFILENAME = "CSVFileName";
  /**
   * The SLACE is used to form the filepath in downloadTestData() method.
   */
  public static final String SLACE = "\\";
  /**
   * Click on a RQM artifact type with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_NAVIGATEBAR_DROPDOWN_XPATH =
      "//span[text()='DYNAMIC_VAlUE0']/../../span[text()='DYNAMIC_VAlUE1']";
  /**
   * Find node menu item after clicking on main menu
   */
  public static final String NODE_MENU_ITEM_XPATH = "//div[contains(@id,'NodeMenuItem') and text()='DYNAMIC_VAlUE']";
  /**
   * Find menu item after clicking on main menu
   */
  public static final String MENU_ITEM_XPATH = "//span[contains(@id,'MenuItem') and text()='DYNAMIC_VAlUE']";
  /**
   * A text box of RQM artifact use to provide test data.
   */
  public static final String RQMPROJECT_TESTCASEHEADING_TEXTBOX_XPATH = "//textarea[contains(@class,'view-title')]";
  /**
   * use to select type of RQM artifact template based on dynamic value.
   */
  public static final String RQMPROJECT_TESTCASEDROPDOWN_TEXTBOX_XPATH =
      "//label[contains(text(),'DYNAMIC_VAlUE')]/following-sibling::div/span[@class='drop-down-arrow dijitDownArrowButton']";
  /**
   * selectTeamArea used to handle Team Area Attribute.
   */
  public static final String RQMPROJECT_TEAMAREADROPDOWN_TEXTBOX_XPATH =
      "//label[text()='Team Area:']/../../descendant::span[@class='drop-down-arrow dijitDownArrowButton']";
  /**
   * used to select the Life Cycle State of RQM Requirement
   */
  public static final String RQMPROJECT_ACTIONDROPDOWN_TEXTBOX_XPATH =
      "(//div[@class='asq-common-popup-chooser asq-chooser-select attributes-entry-value'])[2]";
  /**
   * This selectOwner handles Owner Dropdown in RQM Requirement.
   */
  public static final String RQMPROJECT_OWNERDROPDOWN_TEXTBOX_XPATH =
      "//label[text()='Owner:']/ancestor::tr[1]//descendant::span[@class='drop-down-arrow dijitDownArrowButton']";
  /**
   * Handles Priority Dropdown in RQM Requirement.
   */
  public static final String RQMPROJECT_PRIORITYDROPDOWN_TEXTBOX_XPATH =
      "//label[text()='Priority:']/ancestor::tr[1]//descendant::span[@class='drop-down-arrow dijitDownArrowButton']";
  /**
   * This selectTypeAttribute used to select the value using Visible Text specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_DROPDOWN_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/../following-sibling::td[1]/descendant::select";
  /**
   * Xpath use for: get option using it value for Category
   */
  public static final String RQMCONSTRUCTIONPAGE_OPTION_VALUE_XPATH = "//option[@value='DYNAMIC_VAlUE']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTCASEALMRELEASE_DROPDOWN_XPATH =
      "//label[text()='Introduced for ALM Release:']/../following-sibling::td[1]/descendant::select";
  /**
   * Select the user from add user section specified with DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_SELECTUSER_NAMES_XPATH = "//option[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * This selectOwner handles Owner Dropdown in RQM Requirement values like CDG ALM Exchange system-user-CC
   * (CAP-SST/ESM3) - DGS9SI
   */
  public static final String RQMPROJECT_SELECT_USER_XPATH = "//select[@dojoattachpoint='userSelector']";
  /**
   * Used to get the text that acknowledge the RQM Records saved For Example "Saved successfully
   */
  public static final String RQMPROJECT_TESTCASESUCCESS_MASSAGE_XPATH =
      "//div[@class='messageArea OK' and @aria-live='assertive']/div[@class='messageSummary']";
  /**
   * Use to get message summary if its warning.
   */
  public static final String RQMPROJECT_TESTCASEWARNING_MASSAGE_XPATH =
      "//div[@class='messageArea WARNING' and @aria-live='assertive']/div[@class='messageSummary']";
  /**
   * Open menu item in Requirement management by taking value of it by DYNAMIC_VALUE. Possible values: summary
   */
  public static final String RQMPROJECT_TESTCASE_SECTIONS_XPATH =
      "//div[@aria-label='Sections']//a[@title='DYNAMIC_VAlUE']";
  /**
   * select the frame and provide the test description.
   */
  public static final String RQMPROJECT_TESTDESCRIPTION_FRAME_XPATH = "//iframe[@title='Rich Text Editor, editor1']";
  /**
   * Get the Created RQM Artifact ID
   */
  public static final String RQMPROJECT_TESTCASE_ID_XPATH = "//span[@dojoattachpoint='viewID']";
  /**
   * use to click on keyword id.
   */
  public static final String RQMPROJECT_ADDREQUIREMENT_ID_XPATH = "//a[contains(@title,'Add Test')]";
  /**
   * use to click on requirement id check box as per id provide in DYNAMIC_VALUE. Possible values: reqID
   */
  public static final String RQMPROJECT_ADDREQUIREMENTID_CHECKBOX_XPATH =
      "//a[text()='DYNAMIC_VAlUE']/../preceding-sibling::td[2]/span";
  /**
   * Click on a team area dropdown.
   */
  public static final String RQMPROJECT_TESTCASE_TEAMAREADROPDOWN_XPATH =
      "//label[text()='Team Area:']/../../descendant::span[@class='drop-down-arrow dijitDownArrowButton']";
  /**
   * used to enter the description in RQM Artifacts.
   */
  public static final String RQMPROJECT_TESTCASEDESCRIPTION_TEXTBOX_XPATH =
      "//div[@dojoattachpoint='summaryNode']/textarea";
  /**
   * use to add test case design link.
   */
  public static final String RQMPROJECT_TESTCASEDESIGN_ADDCONTENTLINK_XPATH =
      "//div[@class='com-ibm-asq-common-richtext-content']/descendant::div[@dojoattachpoint='_emptyMessageNode']/a";
  /**
   * use to add description of rqm artifact.
   */
  public static final String RQMPROJECT_TESTCASEDESIGN_IFRAMETEXTBOX_XPATH =
      "//iframe[@title='Rich Text Editor, editor1']";
  /**
   * use to click on create new snapshot button.
   */
  public static final String RQMPROJECT_CREATESNAPSHOT_BUTTON_XPATH =
      "//div[@dojoattachpoint='actionsAreaRight']/button[text()='Create New Snapshot']";
  /**
   * use to provide snapshot name
   */
  public static final String RQMPROJECT_SNAPSHOTNAME_TEXTBOX_XPATH = "//input[@id='name']";
  /**
   * use to provide snapshot description
   */
  public static final String RQMPROJECT_SNAPSHOTDESCRIPTION_TEXTBOX_XPATH = "//input[@id='description']";
  /**
   * use to select ccm workitem in development link
   */
  public static final String RQMPROJECT_WORKITEM_TEXTBOX_XPATH = "//input[@type='text' and @class='QueryInput']";
  /**
   * use to select filtered ccm workitem value.
   */
  public static final String RQMPROJECT_MATCHINGWORKITEM_WORKITEM_XPATH = "//select[@class='QueryResults']";
  /**
   * use to filter test environment.
   */
  public static final String RQMPROJECT_TESTCASEENVIRONMENT_SEARCHBOX_XPATH =
      "//button[@name='This is Generated Test Environments table Clear Filter Text']/../input[@title='Type Filter Text']";
  /**
   * use to select filtered test environment.
   */
  public static final String RQMPROJECT_TESTCASEENVIRONMENT_CHECKBOX_XPATH =
      "//input[@type='checkbox' and  @aria-label='DYNAMIC_VAlUE']";
  /**
   * use to click on generate test environment button.
   */
  public static final String RQMPROJECT_TESTCASEGENERATETESTENVIRONMENT_TAB_XPATH =
      "//span[@aria-label='Generate Test Environments']";
  /**
   * use to select test script select action.
   */
  public static final String RQMPROJECT_TESTSCRIPT_ACTION_XPATH =
      "//div[@class='asq-common-popup-chooser asq-chooser-select attributes-entry-value']";
  /**
   * use to select test script select left to right
   */
  public static final String RQMPROJECT_STACKLEFTTIRIGHT_IMG_XPATH =
      "//a[@class='button' and @title='Stack Left to Right']";
  /**
   * click on test script left side as per step number provided in DYNAMIC_VAlUE values "Step 1"
   */
  public static final String RQMPROJECT_TESTSCRIPTSTEP1DESC_TEXTBOX_XPATH =
      "//div[@class='edit-layout-wrapper clickable leftToRight' and contains(@aria-label,'DYNAMIC_VAlUE')]";
  /**
   * Click on a select drop down of Product in test plan.
   */
  public static final String RQMPROJECT_TESTPLANPRODUCT_DROPDOWN_XPATH =
      "//label[text()='Product:']/../following-sibling::td[1]/descendant::select";
  /**
   * Click on a release name in test plan based on DYNAMIC_VAlUE like 2018.2.0
   */
  public static final String RQMPROJECT_TESTPLANRELEASE_DROPDOWN_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/../following-sibling::td[1]";
  /**
   * Click on a release name in test plan based on DYNAMIC_VAlUE like 6.0.6 ifix.
   */
  public static final String RQMPROJECT_TESTPLANRELEASE_ATTRIBUTE_XPATH =
      "//tr[@class='dijitReset dijitMenuItem']/td[text()='DYNAMIC_VAlUE']";
  /**
   * Search box xpath to seaerch rqmartofact.
   */
  public static final String RQMPROJECT_RELEASESEARCH_TEXTBOX_XPATH = "//input[@placeholder='Type to Search']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_VALIDITY_CHECKBOX_XPATH =
      "//input[@type='checkbox' and @aria-label='DYNAMIC_VAlUE']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_FORMALREVIEW_DROPDOWN_XPATH =
      "//label[text()='New:']/../descendant::span[@class='dijitInline SectionMenuDownArrowImageContainer SectionMenuHeight']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_FORMALREVIEWDESCRIPTION_TEXTBOX_XPATH =
      "//label[text()='Description:']/../descendant::input[@type='text']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_MESSAGE_TEXT_XPATH = "//div[@role='alert']/div[@class='messageSummary']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_COLLECTIONSELECTION_SEARCHBOX_XPATH =
      "//div[@class='jazz-ui-FilterBox filter-pane-search-field']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_COLLECTIONSELECTION_ARTIFACT_XPATH = "//div[@class='content-area']/a";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_SAVEREQUIREMENT_CHECKBOX_XPATH =
      "//label[text()='Save partial changes_ You must repair the link later for it to function correctly_']/../input";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_ADDRESOURCELOCATION_BUTTON_XPATH = "//a[@title='Add Resource Location']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_SELECTSCRIPTTYPE_DROPDOWN_XPATH = "//select[contains(@id,'script-type')]";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_DEVELOPMENTPLANLINK_BUTTON_XPATH = "//a[@title='Add new links']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_PLANNAME_TEXTBOX_XPATH = "//input[@type='text' and @class='QueryInput']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_MATCHINGPLANS_TEXTBOX_XPATH = "//div[@class='result']/div/div[2]";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTENVIRONMENT_TEXTBOX_XPATH =
      "//div[@class='background']/descendant::input[@title='Type Filter Text']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTENVIRONMENT_CHECKBOX_XPATH =
      "//div[text()='DYNAMIC_VAlUE']/../../td[*]/span";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_SNAPSHOTOK_BUTTON_XPATH =
      "//button[contains(text(),'DYNAMIC_VAlUE') and @disabled='']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTTYPE_DROPDOWN_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/../following-sibling::td[1]";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTSUITE_ALMRELEASE_DROPDOWN_XPATH =
      "//label[text()='ALM Release Version:']/../following-sibling::td[1]/descendant::select";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTSUITE_ALMRELEASE_XPATH =
      "//div[@class='com-ibm-asq-common-tableViewer-ext-search-box']/input";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTSUITERUN_WIDGET_XPATH = "//div[contains(@class,'jazz-ui-Dialog-heading')]";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_DIALOG_XPATH =
      "//div[contains(@class,'header-primary') and @title='DYNAMIC_VAlUE']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTSUITERUN_DROPDOWN_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/../../following-sibling::td[1]/descendant::select";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_SUITEEXECUTIONSCRIPT_DROPDOWN_XPATH =
      "//label[contains(@for,'com_ibm_asq_common_web_ui_internal_widgets')]/following-sibling::select";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTDATATYPE_DROPDOWN_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/../following-sibling::td[1]";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTDATA_SELECTDROPDOWNS_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/../following-sibling::td/div/select";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTDATA_UPLOADCSVBUTTON_XPATH = "//input[@name='uploadFileInput']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTDATA_UPLOADCSVFILENAME_XPATH =
      "//span[text()='File Name: ']/../span[2]/a[text()='Formal PA Users_csv']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTKEYWORDS_SUMMARYTAGS_XPATH =
      "//label[text()='Tags: ']/../following-sibling::td[1]";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTKEYWORDS_ADDEXISTSCRIPTSBUTTON_XPATH =
      "//a[@title='Associate Existing Script']/img";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTKEYWORDS_TEAMAREADROPDOWN_XPATH = "//select[@class='enum-field']";

  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_NAVIGATEBAR_BROWSEDROPDOWN_XPATH =
      "//span[text()='Browse']/../../span[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for Show Inline Filters
   */
  public static final String RQMPROJECT_SHOW_SLIDERDOWN_DROPDOWN_XPATH =
      "//td[@class='table-filter-slider-row']/div[@title='Show Inline Filters']";
  /**
   * Xpath for Hide Filters button
   */
  public static final String RQMPROJECT_HIDE_FILTERS_BUTTON_XPATH =
      "//div[@class='explorer-view-expand-view expander-control explorer-view-expand-view-open'][@title='Hide Filters']";
  /**
   * Xpath for Show Filters button
   */
  public static final String RQMPROJECT_SHOW_FILTERS_BUTTON_XPATH =
      "//div[@class='explorer-view-expand-view expander-control'][@title='Show Filters']";
  /**
   * Xpath for Textbox search in Inline Filter Use for column name: ID, Name, ...
   */
  public static final String RQMPROJECT_TEXTBOX_SEARCHFILTER_XPATH =
      "//div[@class='dijitReset dijitInputField dijitInputContainer']/input[@name='DYNAMIC_VAlUE']";
  /**
   * Xpath for Hide Inline Filters - Construction page
   */
  public static final String RQMPROJECT_HIDE_SLIDERDOWN_DROPDOWN_XPATH =
      "//td[@class='table-filter-slider-row']/div[@title='Hide Inline Filters']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_RQMREQUIREMENT_LINK_XPATH = "//a[.='DYNAMIC_VAlUE']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTSCRIPTSTEP2MODIFY_TEXTBOX_XPATH =
      "//div[@class='edit-layout-wrapper clickable leftToRight' and contains(@aria-label,'DYNAMIC_VAlUE')]";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_COVERAGEDROPDOWN_XPATH =
      "//label[text()='Coverage:']/../following-sibling::td[1]/descendant::select";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_CLIENTTYPEDROPDOWN_XPATH =
      "//label[text()='Client Type:']/../following-sibling::td[1]/descendant::select";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_PROCESSTEMPLATEDROPDOWN_XPATH =
      "//label[text()='Process Template:']/../following-sibling::td[1]/descendant::select";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_AUTOMATIONCOVERAGEDROPDOWN_XPATH =
      "//label[text()='Automation Coverage:']/../following-sibling::td[1]/descendant::select";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTPLANQUALITYOBJ_ADDBUTTON_XPATH = "//a[@title='Add Quality Objectives']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTPLANQUALITYOBJ_OKBUTTON_XPATH = "//button[text()='DYNAMIC_VAlUE']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTPLANCATEGORIES_DROPDOWN_XPATH =
      "//label[text()='Test Team:']/../following-sibling::td[1]/descendant::select";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTDATATITLE_GETTEXT_XPATH = "//span[text()='DYNAMIC_VAlUE']/../span[4]";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_RQMTESTDATAREQUIREMENTID_GETTEXT_XPATH =
      "//span[@dojoattachpoint='_attachmentId']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_KEYWORDS_TITLE_XPATH = "//textarea[contains(@class,'view-title')]";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_KEYWORDS_EDITBUTTON_XPATH = "//a[@title='Edit']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTCASE_EXPORTBUTTON_XPATH =
      "//span[@class='caret jazz-ui-toolbar-Button-caret']/../../a[@title='Export PDF']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTCASEEXPORT_CHECKBOX_XPATH =
      "//div[text()='DYNAMIC_VAlUE']/../../../../descendant::td/a/div[1]";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTCASEEXPORT_DELETEBTN_XPATH =
      "//a[@class='button' and @title='Delete Export Job']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTDATACSV_FILELINK_XPATH = "//a[text()='Formal PA Users.csv']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTSUITE_CREATEEXECUTEVARBUTTON_XPATH =
      "//a[@class='button' and @title='Create Execution Variable']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_EXEVAR_NAME_XPATH = "//input[@id='nameBox']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_EXEVAR_VALUE_XPATH = "//textarea[@id='valueBox']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_EXPRES_ADDDOCSBUTTON_XPATH =
      "//a[@class='button' and @title='Create Document Link']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_EXPRES_DESBOX_XPATH = "//input[@dojoattachpoint='_descBox']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_EXPRES_LINKBOX_XPATH = "//input[@dojoattachpoint='_linkBox']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_RQMREQ_ACTIONDROPDOWN_XPATH = "//span[@class='jazz-ui-MenuPopup']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_DELTESTPLAN_LINK_XPATH = "//td[text()='DYNAMIC_VAlUE']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_DELTESTDATA_CHECKBOX_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/../../../../td/span";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTDATA_DELBUTTON_XPATH = "//a[@class='button' and @title='Delete']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_DUPTESTCASE_DUPBUTTON_XPATH = "//a[@class='button' and @title='Duplicate']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_DUPTESTCASE_DUPTYPEBUTTON_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/../../td/div/input[@type='radio']";
  /**
   * Create TestEnvironment
   */
  public static final String RQMPROJECT_TESTENVI_SUMMARYTEXT_XPATH = "//textarea[contains(@class,'summary-value')]";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTENVI_ADDCONLINK_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/../../../../../preceding-sibling::div/span/a";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTENVI_ADDCONLIST_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/../../../../following-sibling::div/descendant::select";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_TESTENVI_OSADDCONLINK_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/../../../../../preceding-sibling::div/span[1]/a";
  /**
   * Remove All Requirement Collection Links
   */
  public static final String RQMPROJECT_RQMLINKSELECTALL_CHECKBOX_XPATH =
      "//a[@class='button' and @aria-label='Select... Drop-Down Menu']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_REMOVELINK_BUTTON_XPATH = "//a[@class='button' and @title='Remove links']";
  /**
   * Link Test Data Test Script
   */
  public static final String RQMPROJECT_TESTSCRIPTMODE_TESTDATALINK_XPATH =
      "//a[@class='button' and @title='Change Associated Test Data']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_SELECTTESTDATA_RADIOBUTTON_XPATH =
      "//span[text()='DYNAMIC_VAlUE']/../../../preceding-sibling::td/descendant::input";
  /**
   * Add TestEnvironment to Test Plan
   */
  public static final String RQMPROJECT_TESTPLANENVIRONMENT_ADDBUTTON_XPATH =
      "//a[@class='button' and @title='Add Test Environments']";
  /**
   * Test CaseExecution record.
   */
  public static final String RQMPROJECT_TESTCASEEXECUTIONRECORD_LINK_XPATH = "//a[@dojoattachpoint='_tcerLink']";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_REQUIREMENTSLINKS_LIST_XPATH =
      "//tr[starts-with(@name , '_table_row_for_external_wihttps')]//a[starts-with(@href ,'https:')]";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_REQUIREMENTSLINKS_DIV_CONTAINS_ID_XPATH =
      "//tr[starts-with(@name , '_table_row_for_external_wihttps')]//a[starts-with(@href ,'https:')]/div[starts-with(text(),'DYNAMIC_VAlUE')]";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_VIEWASTRACEABILITYDROPDOWN_XPATH =
      "(//label[text()=' View As: '])/../div";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_VIEWASTRACEABILITYSELECTDROPDOWN_XPATH =
      "(//label[text()=' View As: '])/../div/span[text()='DYNAMIC_VAlUE']";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_CHANGECOLUMNDISPLAYSETTINGS_XPATH =
      "//tr[3]/td[3]/div/span[7]";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_REMOVEALLCOLUMNS_XPATH = "//div[4]/span/a/img";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_TYPEFILTERTEXT_XPATH = "//span[text()='Type Filter Text']";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_SELECTAVAILABLECOLUMNSID_XPATH =
      "//div[@style='display: inline-block; width: 97%;']/label[contains(text(),'ID')]";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_SELECTAVAILABLECOLUMNSTESTSDEVELOPMENTITEMS_XPATH =
      "//div[@style='display: inline-block; width: 97%;']/label[contains(text(),'Tests Development Items')]";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_SELECTAVAILABLECOLUMNSNAME_XPATH =
      "//div[@style='display: inline-block; width: 97%;']/label[contains(text(),'Name')]";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_ADDSELECT_XPATH =
      "//div[@class='section-buttons']/div[1]/span/a/img";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_CLICKOK_XPATH = "//Button[text()='OK']";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_CLICKONTESTDEVELOPMENTITEMDROPDOWN_XPATH =
      "//table[@class='content-table font-setting-default']/thead/tr[3]/th[5]";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_SELECTHASDEVELOPMENTITEMFROMDROPDOWN_XPATH =
      "//tr[@class = 'table-non-content-cell']//select";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_CLICKFILTERBUTTON_XPATH = "//button[text()='Filter']";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_GETTESTCASEID_XPATH =
      "//tbody//tr[@name='_RbGYoKSVEemLX9aRFvllXA-row']/td[3]/a";
  /**
   * Requirements Links
   */
  public static final String RQMPROJECT_TESTCASECATEGORIES_GETTESTDEVELOPMENTITEM_XPATH =
      "//div[@class='rich-hover-list-cell']//a/div";
  /**
   * Add New Links
   */
  public static final String ADDNEWLINKS = "Add new links";
  /**
   * Constant string variable to hold place holder text "Type Filter Text".
   */
  public static final String TYPE_FILTER_TEXT_PLACEHOLDER = "Type Filter Text";
  /**
   * Constant string variable to hold menu name "Construction".
   */
  public static final String CONSTRUCTION = "Construction";
  /**
   * Constant string variable to hold menu name "Builds".
   */
  public static final String BUILDS = "Builds";
  /**
   * Constant string variable to hold text on button "Show Result".
   */
  public static final String SHOW_RESULT = "Show Result";
  /**
   * Constant string variable to hold text on button "Close and Show Results".
   */
  public static final String CLOSE_AND_SHOW_RESULT = "Close and Show Results";
  /**
   * Constant string variable to hold text "Pass (Ctrl+Shift+P)".
   */
  public static final String CTRL_SHIFT_P = "Pass (Ctrl+Shift+P)";
  /**
   * Constant string variable to hold text "Pass (Ctrl+Shift+P)".
   */
  public static final String CHANGE_COLUMN_DISPLAY_SETTING = "Change Column Display Settings";

  /**
   * Constant string variable to hold the test execution record var, iteration,test env etc.
   */
  public static final String RQM_TEST_CASE_EXECUTION_RECORD = "//div[text()='DYNAMIC_VAlUE']";

  /**
   * Constant string variable to hold the test execution record table
   */
  public static final String RQM_TEST_ARTIFACT_EXECUTION_RECORD_TABLE =
      "//table[contains(@summary, 'Execution Record table') or contains(@summary, 'Execution Records table')]//tbody";

  /**
   * Constant string variable to hold the test execution record var, iteration,test env etc.
   */
  public static final String TEST_ENVIRONMENT = "TestEnvironment";

  /**
   * Constant string variable to hold the test execution record var, iteration,test env etc.
   */
  public static final String TSER_NAME = "TSER_Name";
  /**
   * A variable in rqm dashboard page which takes value to perform a specific operation.
   */
  public static final String ARTIFACT_ID = "artifactID";
  /**
   * A variable in rqm dashboard page which takes value to perform a specific operation.
   */
  public static final String RUN_TEST_SUITE = "Run Test Suite";
  /**
   * A variable in rqm dashboard page which takes value to perform a specific operation.
   */
  public static final String RUN_TEST_CASE = "Run Test Case";
  /**
   * Xpath for adding value in Description and Expected Result inside Manual Steps.
   */
  public static final String RQM_TESTSCRIPT_STEP_EXPECTEDRESULT_DESCRIPTION_TEXTBOX_XPATH =
      "//div[contains(@class,'edit-layout-wrapper') and contains(@aria-label,'DYNAMIC_VAlUE')]";
  /**
   * Xpath for adding value in Description and Expected Result inside Manual Steps. DYNAMIC_VAlUE is without spaces
   * between characters
   */
  public static final String RQM_TESTSCRIPT_STEP_EXPECTEDRESULT_DESCRIPTION_TEXTBOX_XPATH_UPDATE =
      "//div[contains(translate(@aria-label,' ',''),'DYNAMIC_VAlUE')]/div[@class='step-content']";
  /**
   * Xpath for fetching the Description and Expected Result value from the manual step.
   */
  public static final String RQM_MANUALSTEP_TESTSCRIPT_XPATH =
      "//span[text()='DYNAMIC_VAlUE0']/ancestor::div[@class='content-layout']//div[contains(@aria-label,'DYNAMIC_VAlUE1')]";
  /**
   * Xpath for fetching the Artifact Title
   */
  public static final String RQM_ARTIFACT_TITLE_XPATH = "//span[@role='heading' and @class='view-title non-editable']";

  /**
   * Xpath for fetching the Artifact Title
   */
  public static final String RQM_ARTIFACT_HEADING_XPATH =
      "//span[@role='heading' and @class='view-title non-editable' and text()='DYNAMIC_VAlUE' or text()='Artifact Types']";

  /**
   * A variable in RQM artifact browse which clear text data in filter field
   */
  public static final String CLEAR_FILTER_TEXT_TOOLTIP = "Clear Filter Text";
  /**
   * A variable in RQM artifact browse which takens data to filter
   */
  public static final String TYPE_FILTER_TEXT_AND_PRESS_ENTER_PLACEHOLDER = "Type filter text and press Enter";
  /**
   * A variable in RQM artifact browse which takens data to filter
   */
  public static final String TYPE_FILTER_TEXT_AND_PRESS_ENTER_AREALABLE =
      "This is Test Case Results table: filter text input";
  /**
   * A variable in RQM artifact browse which takens data to filter
   */
  public static final String TYPE_FILTER_TEXT_AND_PRESS_ENTER_TITLE = "Type filter text and press Enter";
  /**
   * A variable in RQM artifact browse which takens data to filter
   */
  public static final String TYPE_FILTER_TEXT_OR_ID = "Type filter text or ID";
  /**
   * A variable in rqm dashboard page which takes value to perform a specific operation.
   */
  public static final String REQ_NAME_VALUE = "rmReqNameValue";
  /**
   * Xpath for rqm build record name text box.
   */
  public static final String RQM_BUILD_RECORD_NAME_TEXTBOX_XPATH =
      "//textarea[@id='com_ibm_asq_common_web_ui_internal_widgets_TitleTextAreaEditor_0']";
  /**
   * Xpath for rqm build definition name text box.
   */
  public static final String RQM_BUILD_DEFINITION_NAME_TEXTBOX_XPATH =
      "//textarea[@id='com_ibm_asq_common_web_ui_internal_widgets_TitleTextAreaEditor_5']";
  /**
   * Xpath for attribute name text box.
   */
  public static final String RQM_ATTRIBUTE_NAME_TEXTBOX_XPATH =
      "//tr[@class='action-editable attribute-editor-row']/td[2]//input[1]";
  /**
   * Create Keyword Template dialog.
   */
  public static final String CREATE_KEYWORD_TEMPLATE = "Create Keyword Template";
  /**
   * A button on Test Environments section
   */
  public static final String RQM_MANAGE_PLATFORM_TOOLTIP = "Manage the platforms to be covered";

  /**
   * "Available Environment Options" dialog in Test Environment section
   */
  public static final String RQM_MANAGE_PLATFORM_DIALOG = "Available Environment Options";

  /**
   * "Delete Approval Group" icon in Formal Review section
   */
  public static final String RQM_DELETE_APPROVAL_GROUP_BUTTON = "Delete Approval Group";
  /**
   * Create Keyword Template dialog.
   */
  public static final String COPY_TEMPLATE = "Copy Template";
  /**
   * Constant string variable to hold the text Actions.
   */
  public static final String ACTIONS = "Actions";
  /**
   * Constant string variable to hold the text Filter.
   */
  public static final String FILTERS = "Filter";
  /**
   * Constant string variable to Clear Filter button.
   */
  public static final String CLEAR_FILTER_BUTTON_TOOLTIP = "Clear Filter Text";
  /**
   * Constant string variable to hold the text moreLinkValue.
   */
  public static final String MORELINKVALUE = "moreLinkValue";
  /**
   * Constant string variable to identify Add Risk button
   */
  public static final String ADD_RISK_TOOLTIP = "Add Risk";
  /**
   * Constant string variable to identify Add Risk dialog
   */
  public static final String ADD_RISK_DIALOG_TITLE = "Add Risk";
  /**
   * Constant string variable to identify Risk Type dropdown in Add Risk dialog
   */
  public static final String RISK_TYPE_LABEL = "Risk Type:";
  /**
   * Constant string variable to identify Risk dropdown in Add Risk dialog
   */
  public static final String RISK_LABEL = "Risk:";
  /**
   * Constant string variable to identify Risk dropdown in Add Risk dialog
   */
  public static final String LIKELIHOOD_LABEL = "Likelihood:";
  /**
   * Constant string variable to identify Impact dropdown in Add Risk dialog
   */
  public static final String IMPACT_LABEL = "Impact:";

  /**
   * Constant string variable to identify Mitigation Action text area in Add Risk dialog
   */
  public static final String MITIGATION_ACTION_LABEL = "Mitigation Action:";
  /**
   * Constant string variable to identify Matching Users list in Select User dialog
   */
  public static final String MATCHING_USERS_LABEL = "Matching users:";
  /**
   * Constant string variable to identify filter text field in Select User dialog
   */
  public static final String ENTER_A_NAME_FILTER_LABEL = "Enter a name filter to load the list.";
  /**
   * Constant string variable to identify Select User dialog
   */
  public static final String SELECT_USER_DIALOG_TITLE = "Select User";
  /**
   * Constant string variable to identify Owner dropdown in test plan, test case, test suite...
   */
  public static final String OWNER_LABEL = "Owner:";
  /**
   * Constant string variable to identify Manage Sections dialog
   */
  public static final String MANAGE_SECTIONS_DIALOG_TITLE = "Manage Sections";
  /**
   * Constant string variable to identify Avaialable section list
   */
  public static final String AVAILABLE_SECTIONS_LABEL = "Available Sections:";
  /**
   * Constant string variable to identify Selected section list
   */
  public static final String SELECTED_SECTIONS_LABEL = "Selected Sections:";
  /**
   * Constant string variable to identify Selected section option
   */
  public static final String SELECTED_SECTIONS_OPTION_XPATH = "//option[text()='DYNAMIC_VAlUE']";
  /**
   * Constant string variable to upload file
   */
  public static final String JAZZPAGE_UPLOAD_FILE_XPATH = "//input[@type='file' and @name='uploadFileInput']";
  /**
   * Constant string variable to test data link
   */
  public static final String TEST_DATA_LINK_XPATH = "//span[text()='DYNAMIC_VAlUE']";
  /**
   * Constant string variable to test data link
   */
  public static final String TESTSCRIPT_ID_LINK_XPATH = "//a[contains(@id,'tableViewer') and text()='DYNAMIC_VAlUE']";
  /**
   * Constant string variable to test data link
   */
  public static final String TESTSCRIPT_NAME_LINK_XPATH =
      "//a[contains(@id,'tableViewer')]/div[text()='DYNAMIC_VAlUE']";
  /**
   * Constant string variable to filter textbox
   */
  public static final String FILTER_TEXTBOX_XPATH =
      "//input[@title='DYNAMIC_VAlUE'] | //input[@aria-label='DYNAMIC_VAlUE']";
  /**
   * Constant string variable to test data's location for Attachment test script
   */
  public static final String TEST_DATA_LOCATION = "src\\test\\resources\\rqm\\";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String CUSTOM_ATTRIBUTE = "Custom attribute";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String ADD_CONTENT = "Add Content";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String MORE = "More...";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String PRE_CONDITION = "//body[starts-with(@class,'cke_editable')]";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String RQMCONSTRUCTIONPAGE_EXECUTIONRECORD_TESTPLANSELECT_XPATH =
      "//span[text()='Overview']/ancestor::table/following-sibling::div//label[text()='Test Plan']/../..//option";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String TABLE_MESSAGE_XPATH = "//div[@class='table-message']";
  /**
   * Xpath for Result in Construction query page
   */
  public static final String RQMCONSTRUCTIONPAGE_ARTIFACTROW_XPATH =
      "//div[@role='tabpanel' and contains(@class,'dijitVisible')]//tr[contains(@name,'-row')]//*[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * Xpath for Result in Construction query page
   */
  public static final String RQMCONSTRUCTIONPAGE_ARTIFACTROW_ID_XPATH =
      "//div[@role='tabpanel' and contains(@class,'dijitVisible')]//tr[contains(@name,'-row')]//*[text()='DYNAMIC_VAlUE']";
  /**
   * Xapth of visible message in inside table
   */
  public static final String VISIBLE_TABLE_MESSAGE_XPATH =
      "//div[@class='table-message' and contains(@style,'display: block')]";

  /**
   * Xapth to idenfify the artifact name in the artifact details page
   */
  public static final String RQMPROJECT_TESTCASE_NAME_XPATH =
      "//textarea[contains(@id,'com_ibm_asq_common_web_ui_internal_widgets_TitleTextAreaEditor')]";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String TABLE_MESSAGE_NO_RESULT_IN_DIALOG_1 = "No items match the current filter criteria.";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String TABLE_MESSAGE_NO_RESULT_IN_DIALOG_2 = "No items found.";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String TITLE_CONTAINER_XPATH = "//div[@role='button' and contains(.,'DYNAMIC_VAlUE')]";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String ALERT = "Alert";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String DELETE = "Delete";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String TEAMAREA_VALUE = "teamAreaValue";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String RADIO_TEST_ARTIFACT_XPATH = "//input[contains(@aria-label, 'DYNAMIC_VAlUE')]";
  /**
   * Constant string variable to hold text "Custom attribute".
   */
  public static final String RADIO_OPTION_IN_ASSOCIATE_DIALOG_XPATH =
      "//label[text()='DYNAMIC_VAlUE']/preceding-sibling::input";
  /**
   * testplanName to be deleted in verifyDelRQMReq() method in RQMDashBoardPage.
   */
  public static final String SEARCH_TEST_ENV_NAME = "searchTestEnvName";
  /**
   * testplanName to be deleted in verifyDelRQMReq() method in RQMDashBoardPage.
   */
  public static final String TESTPLAN_NAME = "testPlanName";
  /**
   * Constant string variable to hold text 'varName'.
   */
  public static final String VAR_NAME = "varName";
  /**
   * Constant string variable to hold text 'description'.
   */
  public static final String DESCRIPTION = "description";
  /**
   * Constant string variable to hold text 'descriptionValue'.
   */
  public static final String DESCRIPTION_VALUE = "descValue";
  /**
   * Constant string variable to hold text 'expectedResult'.
   */
  public static final String EXPECTED_RESULT = "expectedResult";
  /**
   * Constant string variable to hold text 'expecResultValue'.
   */
  public static final String EXPECTED_RESULT_VALUE = "expecResultValue";
  /**
   * Xpath for all the artifacts present in the construction page.
   */
  public static final String RQMARTIFACT_LIST_XPATH = "//table[contains(@summary,'table')]/descendant::tbody/tr//a";
  /**
   * Xpath for all the test scripts present in keyword.
   */
  public static final String TESTSCRIPTS_LIST_FROM_KEYWORDS_XPATH =
      "//table[@summary='This is Test Scripts table']/descendant::tbody/tr//a";
  /**
   * Constant string variable to hold text "Iteration".
   */
  public static final String ITERATION = "Iteration";
  /**
   * Constant string variable to hold text "artifactName".
   */
  public static final String ARTIFACT_NAME = "artifactName";
  /**
   * Xpath for list of the test ids present in test plan.
   */
  public static final String TESTCASES_LIST_XPATH =
      "//table[@summary='This is Test Cases table']/descendant::tbody/tr/td//a";
  /**
   * Xpath for all the iteration from the 'Generate Execution Record' wizard.
   */
  public static final String RQMCONSTRUCTIONPAGE_EXECUTIONRECORD_ITERATION_SELECT_XPATH =
      "//span[text()='Overview']/ancestor::table/following-sibling::div//label[text()='Iteration']/../..//option";
  /**
   * Xpath for Description text area in Rqm artifact
   */
  public static final String RQMCONSTRUCTIONPAGE_DESCRIPTION_TEXTBOX_XPATH =
      "//label[text()='Description:']/following-sibling::textarea";
  /**
   * Xpath for the title of the RQM sections.
   */
  public static final String RQM_SECTION_TITLE_XPATH = "//span[@name='Title' and text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for the text field of the Name field in Create Baseline, Create Stream... dialog
   */
  public static final String RQM_CONFIGURATION_NAME_TEXTFIELD_XPATH = "//input[@id='nameInputBox']";
  /**
   * Xpath for the text field of the Description field in Create Baseline, Create Stream... dialog
   */
  public static final String RQM_CONFIGURATION_DESCRIPTION_TEXTFIELD_XPATH = "//textarea[@id='descriptionInput']";
  /**
   * Xpath to open a drop down from Choose a component dialog.
   */
  public static final String RQMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH = "//div[@class='jazz-ui-fat-caret']";
  /**
   * Hint text under test step of the test script
   */
  public static final String TEST_SCRIPT_STEP_HINT_TEXT =
      "Use Ctrl+Space to search for keyword to insert to this step position";
  /**
   * Constant string variable to hold the text "Completed".
   */
  public static final String COMPLETED = "Completed";
  /**
   * Constant string variable to hold the text "testEnvName".
   */
  public static final String TEST_ENV_NAME = "testEnvName";
  /**
   * Constant string variable to hold text "Clicked on 'Next' button."
   */
  public static final String CLICKED_ON_NEXT_BUTTON = "Clicked on 'Next' button.";
  /**
   * Actual Result status.
   */
  public static final String RQMEXECUTIONPAGE_ACTUALRESULT_DROPDOWN =
      "//label[text()='Actual Result: ']/ancestor::tr[@width='100%']//span[@class='selection-label']";
  /**
   * Actual Result status.
   */
  public static final String RQMEXECUTIONPAGE_TESTEXECUTIONRESULT_TITLE_XPATH =
      "//div[@summary='Script Steps']//span[@class='step-num' and text()='DYNAMIC_VAlUE']/following-sibling::span[@dojoattachpoint='rowVerdictNode']/div";
  /**
   * Actual Result status.
   */
  public static final String RQMEXECUTIONPAGE_EXECUTIONRECORD_NAME_XPATH =
      "//div[@dojoattachpoint='titleOutterContainerNode']";
  /**
   * Actual Result status.
   */
  public static final String RQMEXECUTIONPAGE_EXECUTIONRECORDS_NAME_XPATH =
      "//div[@dojoattachpoint='titleOutterContainerNode']//textarea";
  /**
   * Actual Result status.
   */
  public static final String RQMEXECUTIONPAGE_EXECUTIONRECORDS_BUTTON_XPATH =
      "//*[@class='button' and @title='Run Test Case  (Ctrl+Shift+X)']";
  /**
   * Xpath for 'Run Test Suite (Ctrl+Shift+X)' button.
   */
  public static final String RQMEXECUTIONPAGE_RUN_TEST_SUITE_BUTTON_XPATH =
      "//*[@class='button' and @title='Run Test Suite (Ctrl+Shift+X)']";
  /**
   * Constant String variable to hold the text "title".
   */
  public static final String TITLE = "title";
  /**
   * Constant String variable to hold the text "Project Dashboards".
   */
  public static final String PROJECT_DASHBOARDS = "Project Dashboards";
  /**
   * Xpath for start link in RQM Execution Page.
   */
  public static final String RQMEXECUTION_START_LINK_XPATH = "//a[text()='Start']";
  /**
   * Xpath for test case not run.
   */
  public static final String RQMEXECUTIONPAGE_TEST_CASE_NOT_RUN_XPATH = "//label[@aria-label='Test Cases Not Run: 0']";
  /**
   * Xpath for title of the RQM application.
   */
  public static final String RQMALLPROJECTSPAGE_APPLICATION_TITLE_LINK_XPATH = "//li//a[text()='DYNAMIC_VAlUE']";
  /**
   * Click on a title with specified DYNAMIC_VALUE.
   */
  public static final String RQMPROJECT_STATUS_MESSAGE_TEXT_XPATH =
      "//div[@role='status']//div[@class='messageSummary']";
  /**
   * Xpath for hide in line filter.
   */
  public static final String RQMPROJECT_HIDE_INLINE_FILTER_XPATH =
      "//td[@class='table-filter-slider-row']/div[@title='Hide Inline Filters']";
  /**
   * Xpath for hide in line filter.
   */
  public static final String EXPECTED_RESULTS = "\nExpected Result \"";
  /**
   * Xpath for hide in line filter.
   */
  public static final String ACTUAL_RESULT_IS = "\nActual result is '";
  /**
   * Xpath for hide in line filter.
   */
  public static final String IS_BUTTON_CLICKED_SUCESSFULLY = "' is clicked sucessfully.";
  /**
   * Xpath for hide in line filter.
   */
  public static final String VERIFIED = "Verified '";
  /**
   * Xpath for hide in line filter.
   */
  public static final String VERIFIED_COLON = "Verified: '";
  /**
   * Xpath for hide in line filter.
   */
  public static final String CLICKED_SUCESSFULLY = "' clicked successfully.";
  /**
   * Xpath for hide in line filter.
   */
  public static final String NOT_CLICKED_SUCESSFULLY = "' not clicked successfully.";
  /**
   * Baseline name filter text field in Configuration window.
   */
  public static final String RQMPROJECT_BASELINESEARCH_FILTER_XPATH = "//td[@cell-value][substring-after(normalize-space(.),'')='DYNAMIC_VAlUE']";
  /**
   * Baseline name filter text field in Configuration window.
   */
  public static final String VAR_DESCRIPTION_CONTENT = "varDescriptionContent";
  /**
   * Baseline name filter text field in Configuration window.
   */
  public static final String VAR_DESCRIPTION = "varDescription";
  /**
   * Categories field in New Test Script Dialog.
   */
  public static final String VAR_CATEGORIES = "varCategories";
  /**
   * Attributes field in New Test Script Dialog.
   */
  public static final String VAR_ATTRIBUTES = "varAttributes";

  
  /**
   * Xpath for selecting the component in RQM Project area.
   */
  public static final String RQM_SELECT_COMP_XPATH = "//*[@title='DYNAMIC_VAlUE']";
  /**
   * Xpath for selecting radio button with text inline
   */
  public static final String RQM_LABEL_TEXT_XPATH = "//label[text()='DYNAMIC_VAlUE']";
  /**
   * Xpath for the button in the Execution record.
   */
  public static final String RQM_EXECUTION_RECORD_BUTTON_XPATH = "//button[@title='DYNAMIC_VAlUE']";
  /**
   * Constant string variable to hold text "'Alert' dialog is displayed.".
   */
  public static final String ALERT_DIALOG_IS_DISPLAYED = "'Alert' dialog is displayed.";
  /**
   * Constant string variable to hold text "Clicked on 'OK' button present in 'Alert' pop up.".
   */
  public static final String CLICKED_ON_OK_BUTTON_PRESENT_IN_ALERT_POP_UP =
      "Clicked on 'OK' button present in 'Alert' pop up.";
  /**
   * Xpath for select class of the iteration.
   */
  public static final String RQMPLANNINGPAGE_SELECT_ITERATION_XPATH =
      "//label[text()='Iteration']/../following-sibling::td//select";
  /**
   * Constant String variable to hold text "ownerValue".
   */
  public static final String OWNER_VALUE = "ownerValue";
  /**
   * Constant String variable to hold text "Verified the test artifact '".
   */
  public static final String VERIFIED_THE_TEST_ARTIFACT = "Verified the test artifact '";
  /**
   * Constant String variable to hold text "' dialog successfully.\n".
   */
  public static final String DIALOG_SUCCESSFULLY = "' dialog successfully.\n";
  /**
   * Constant String variable to hold text "value".
   */
  public static final String VALUE = "value";
  /**
   * Constant String variable to hold text "' and added Expected Result step value is '".
   */
  public static final String ADDED_EXPECTED_RESULT_STEP_VALUE_IS = "' and added Expected Result step value is '";
  /**
   * Constant String variable to hold text "' is displayed.".
   */
  public static final String IS_DISPLAYED = "' is displayed.";
  /**
   * Xpath for the title of the dialog of RQM pages.
   */
  public static final String RQMPAGE_DIALOG_TITLE_XPATH = "//span[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * Xpath for the dashboard title of RQM pages.
   */
  public static final String RQMPAGE_DASHBOARD_TITLE_XPATH = "//span[@class='dashboard-title']";
  /**
   * Xpath for Loading message after search in Construction page
   */
  public static final String RQMCONSTRUCTIONPAGE_LOADING_MESSAGE_XPATH =
      "//div[@class='com-ibm-asq-common-tableViewer']//div[@dojoattachpoint='loadingProgressNode']";
  /**
   * Constant String variable to hold text "Clicked on ".
   */
  public static final String CLICKED_ON = "Clicked on ";
  /**
   * Constant String variable to hold text "Select...".
   */
  public static final String SELECT = "Select...";
  /**
   * Constant String variable to hold text "testArtifactDescriptionValue".
   */
  public static final String TEST_ARTIFACT_DESCRIPTION_VALUE = "testArtifactDescriptionValue";

  /**
   * XPATH for Work Items pop up after clicking on Work Items tab
   */
  public static final String WORKITEMS_POPUP_XPATH = "//div[@class='menu-title' and text()='Create Work Item']";
  /**
   * XPATH for Add Content in RQM contruction page
   */
  public static final String RQMCONSTRUCTIONPAGE_ADDCONTENT_XPATH =
      "//div[contains(@class,'dijitTitlePaneSelected') and not(contains(@class,'hidden'))]//a[text()='Add Content']";


  /**
   * XPATH for tab name on the left menu of RQM Manage Components and Configurations page
   */
  public static final String RQMMANAGECOMPONENTANDCONFIGURATIONPAGE_TABONTHELEFTMENU_XPATH =
      "//label[normalize-space(text())='DYNAMIC_VALUE']";

  /**
   * XPATH for header title after selecting Tab name on the left menu of RQM Manage Components and Configurations page
   */
  public static final String RQMMANAGECOMPONENTANDCONFIGURATIONPAGE_HEADERTITLE_TABONTHELEFTMENU_XPATH =
      "//div[@class='agg-config-content-header-title']/label[text()='DYNAMIC']";

  /**
   * Used to get the text "The baseline is deleted." that acknowledge the RQM deleted a baseline successfully.
   */
  public static final String RQMPROJECT_DELETEBASELINESUCCESS_MASSAGE_XPATH = "//div[@class='messageSummary']";

  /**
   * Used to get the text "No items match the current filter criteria." that acknowledge no items match after searching
   * a baseline.
   */
  public static final String RQMPROJECT_DELETEBASELINESEARCHING_MASSAGE_XPATH =
      "//div[@class='table-message'][@style='display: block;']";


  /**
   * XPATH for link in the right side in RQM contruction page, DYNAMIC_VAlUE0 like type, e.g: Parent Test Plans, Related
   * Test Suites DYNAMIC_VAlUE1 link, e.g: Test suite for testing Traceability Links
   */
  public static final String RQMCONSTRUCTIONPAGE_LINKINRIGHTSIDE_XPATH =
      "//div[@class='title-holder']/span[text()='DYNAMIC_VAlUE0']/following::ul[1]//a[contains(text(),'DYNAMIC_VAlUE1')]";

  /**
   * XPATH for checkbox 'Create a result without executing' in RQM contruction page
   */
  public static final String RQMCONSTRUCTIONPAGE_CREATE_A_RESULT_WITHOUT_EXECUTING_CHECKBOX_XPATH =
      "//input[@dojoattachpoint='_quickResult']";

  /**
   * XPATH for checkbox 'Create a result without executing' in RQM contruction page
   */
  public static final String RQMCONSTRUCTIONPAGE_QUICKSEARCH_RESULT_XPATH =
      "(//div[@class='search-result']/div[@class='link']//span[contains(text(),'DYNAMIC_VAlUE')])[1]";

  /**
   * XPATH for banner title in RQM contruction page
   */
  public static final String RQMCONSTRUCTIONPAGE_BANNER_TITLE_XPATH = "//span[@dojoattachpoint='_bannerTitle']";

  /**
   * XPATH for tab 'Generate a New Test Case Execution Record'
   */
  public static final String RQMCONSTRUCTIONPAGE_GENERATE_A_NEW_TCER_TAB_XPATH =
      "//span[@aria-label='Generate a New Test Case Execution Record']";

  /**
   * XPATH for LOADED MESSAGE
   */
  public static final String RQM_LOADED_MESSAGE_XPATH =
      "//div[@class='status-message' and contains(@style,'display: none;')]";

  /**
   * XPATH for ERROR MESSAGE
   */
  public static final String RQM_ERROR_MESSAGE_XPATH =
      "//div[contains(@class,'messageArea') and contains(@class,'ERROR')]";

  /**
   * Xpath for checking test case information in 'Test Cases' section of Test Plan
   */
  public static final String RQM_TETSCASE_IN_TESTPLAN =
      "//td[descendant::*[contains(text(),'DYNAMIC_VAlUE0')] and following::*[contains(text(),'DYNAMIC_VAlUE1')]]";
  /**
   * Xpath for "Name:" field in New Test Case Dialog
   */
  public static final String RQM_TETSCASE_NAME_IN_NEWTESTCASEDIALOG =
      "//label[contains(text(),'Name')]/following::td[1]//input[@role='textbox']";
  /**
   * Xpath for "Test Type:" field in New Test Case Dialog
   */
  public static final String RQM_TETSCASE_TYPE_IN_NEWTESTCASEDIALOG =
      "//label[contains(text(),'Test Type')]/following::td[1]//select";
  /**
   * Xpath for dropdown with label
   */
  public static final String RQM_DEFAULTVALUE_IN_DROPDOWN_WITH_SELECTTAG =
      "//*[contains(text(),'DYNAMIC_VAlUE')]/following::td[2]/select";
  /**
   * Xpath for link with label
   */
  public static final String RQM_LINK_WITH_LABEL =
      "//*[contains(text(),'DYNAMIC_VAlUE')]/ancestor::td[contains(@class,'label')]/following-sibling::td//a[contains(@class,'ResourceLink')]";
  /**
   * Xpath for breadcrumb link
   */
  public static final String RQM_BREADCRUMB_LINKTEXT =
      "//*[contains(@class,'breadcrumb')]//.[contains(text(),'DYNAMIC_VAlUE')]";
  /**
   * Xpath for last result 'Passed' in test execution record  
   */
  public static final String RQM_LASTRESULT_TESTEXECUTIONRECORD_PASSED =
      "//div[contains(text(),'DYNAMIC_VAlUE0')]/ancestor::tr[contains(@name,'row')]//div[@title='DYNAMIC_VAlUE1']/ancestor::tr[contains(@name,'row')]//div[@title='DYNAMIC_VAlUE2']/ancestor::tr[contains(@name,'row')]//img[contains(@src,'pass_obj.gif')]";
  /**
   * Xpath for last result 'Error' in test execution record  
   */
  public static final String RQM_LASTRESULT_TESTEXECUTIONRECORD_ERROR =
      "//div[contains(text(),'DYNAMIC_VAlUE0')]/ancestor::tr[contains(@name,'row')]//div[@title='DYNAMIC_VAlUE1']/ancestor::tr[contains(@name,'row')]//div[@title='DYNAMIC_VAlUE2']/ancestor::tr[contains(@name,'row')]//img[contains(@src,'errorexecution_obj.gif')]";
  /**
   * Xpath for last result 'Error' in test execution record  
   */
  public static final String RQM_LASTRESULT_TESTEXECUTIONRECORD_FAIL =
      "//div[contains(text(),'DYNAMIC_VAlUE0')]/ancestor::tr[contains(@name,'row')]//div[@title='DYNAMIC_VAlUE1']/ancestor::tr[contains(@name,'row')]//div[@title='DYNAMIC_VAlUE2']/ancestor::tr[contains(@name,'row')]//img[contains(@src,'fail_obj.gif')]";
  /**
   * Xpath for last result 'Error' in test execution record  
   */
  public static final String RQM_LASTRESULT_TESTEXECUTIONRECORD_INCONCLUSIVE =
      "//div[contains(text(),'DYNAMIC_VAlUE0')]/ancestor::tr[contains(@name,'row')]//div[@title='DYNAMIC_VAlUE1']/ancestor::tr[contains(@name,'row')]//div[@title='DYNAMIC_VAlUE2']/ancestor::tr[contains(@name,'row')]//img[contains(@src,'inconclusive_obj.gif')]";
  /**
   * Xpath for 'Run Test Suie' dialog
   */
  public static final String RQM_RUNTESTSUITE_DIALOG_FAIL =
      "//div[text()='Run Test Suite']";
  /**
   * Xpath for list of number of row in table
   */
  public static final String RQM_LISTVALUE_NUMBER_OF_ROW_IN_TABLE =
      "//span[@title='DYNAMIC_VAlUE']/ancestor::div[@dojoattachpoint='viewHeaderBackNode']/following-sibling::div[@class='explorer-style']//table[@summary]/tbody/tr";
  
  /**
   * Xpath for 'Merge Configuration...' dialog
   */
  public static final String RQM_MERGECONFIGURATION_DIALOG =
      "//div[contains(@role, 'dialog') and contains(.//div, 'Merge Configuration...')]";
  
  /**
   * Xpath for search textbox - Stream -  inside 'Merge Configuration...' dialog
   */
  public static final String RQM_TYPETOSEARCH_MERGECONFIGURATION_DIALOG =
      "//input[contains(@title, 'Type to search')]";
  
  /**
   * Xpath for searched Stream name inside 'Merge Configuration...' dialog
   */
  public static final String RQM_STREAM_MERGECONFIGURATION_DIALOG =
      "//div[contains(text(),'DYNAMIC_VAlUE')]";
  
  /**
   * Xpath for search textbox - Test Plan - inside 'Merge Configuration...' dialog
   */
  public static final String RQM_TYPEFILTERTEXTANDPRESSENTER_MERGECONFIGURATION_DIALOG =
      "//input[contains(@title, 'Type to search')]";
  
  /**
   * Xpath for searched Test Plan inside 'Merge Configuration...' dialog
   */
  public static final String RQM_TESTPLAN_MERGECONFIGURATION_DIALOG =
      "//input[contains(@title, 'Type to search')]";
  
  /**
   * Xpath for titleNode select on Compare Configuration page
   */
  public static final String RQM_TITLENODE_COMPARECONFIGURATION_PAGE =
      "//div[contains(text(),'DYNAMIC_VAlUE') and @dojoattachpoint='titleNode']";
  
  /**
   * Constant string variable to hold text "Confirmation".
   */
  public static final String CONFIRMATION = "Confirmation";
  
  /**
   * Constant string variable to hold text "'Confirmation' dialog is displayed.".
   */
  public static final String CONFIRMATION_DIALOG_IS_DISPLAYED = "'Confirmation' dialog is displayed.";
}

