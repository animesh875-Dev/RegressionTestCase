/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;


/**
 * @author NEE2KOR
 */
public class PagemodelConstants {

  private PagemodelConstants() {

  }

  /**
   * adding the member in create project area page.
   */
  public static final String MANAGEPROJECTAREAPAGE_SELECTUSER_XPATH =
      "//select[@dojoattachpoint='userSelector']/option[1]";
  /**
   * search the name in select user box for adding the member.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_ADD_POSTSEARCHBOX_XPATH =
      "//div[@class='com-ibm-team-repository-web-ui-internal-TeamContributorSelectionDialog']/div/div[5]/select/option";
  /**
   * Click on Project Area in Team Area
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREATOPROJAREA_XPATH =
      "//div[@class='jazz-ui-Breadcrumbs j-action-secondary']/span[2]/a";
  /**
   * nagivate back button in modify the process description page.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSDESTOPROJECTAREA_NAVIGATEBACK_XPATH =
      "//span[@class='jazz-ui-toolbar-Button has-icon']/a";
  /**
   * name text filed in create team area.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_TITLENAME_XPATH =
      "//input[@dojoattachpoint='name']";

  /**
   * set the name for create timeline.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_NAME_XPATH = "//input[@value='DYNAMIC_VAlUE']";
  /**
   * Tab Section names in Manage This Project Area page like Timlines,Roles,Permissions etc...
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_TABSECTION_XPATH="//a[@title='DYNAMIC_VAlUE']";
  /**
   * Create Team in Mange Project Area page.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_CREATETEAM_AREA_XPATH="//a[@title='Create Team...']";
  /**
   * Add link xpath of Team Area in Manage Project Area page.
   */
  public static final String MANAGEPROJECTAREAPAGE_TEAMAREA_ADDLINK_XPATH="//div[text()='DYNAMIC_VAlUE']/preceding-sibling::div/a";
  /**
   * Constant string variable to hold add link.
   */
  public static final String ADD_LINK="Add...";
  /**
   * Add new members for Manage project area page.
   */
  public static final String MANAGEPROJECTAREAPAGE_ADDNEWMEMBERS_XPATH="//span[text() = 'Add New Members']";
  /**
   * Search text area for adding members.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_SEARCHTEXTAREA_XPATH = "//input[@class='searchText']";
  /**
   * Selected members in "Add New Members" dialog box while adding members.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_SELECTEDMEMBER_XPATH= "//select[@class='selectedMembersList']/option[1]";
  /**
   * Available roles after adding members.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_AVAILABLEROLES_XPATH = "//select[(@class='selectRoles' or @class='availableRoles') and @aria-label='Available Roles:']";
  /**
   * Add while slecting the roles.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_POSTSELECTROLENEXTBUTTON_XPATH = "//button[@title='Add' and @class=\"\"]/img[@class='addDisabled']";
  /**
   * Practice name xpath in Manage Project Area Page.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSATTACHMENT_SELECTPRACTICENAME_XPATH="//span[@role='treeitem'][text()='DYNAMIC_VAlUE']";
  /**
   * Duration for the Iteration.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_DURATION_XPATH="//input[contains(@value,'DYNAMIC_VAlUE')]/../../../following-sibling :: td[2]/div/div[3]/input[1]";
  /**
   * Team area name.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_NAME_XPATH="//span[@class='previewField']";
  /**
   * Archive button for team area.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_TIMELINES_SELECTARCHIVEBUTTON_XPATH ="(//a[text() = 'DYNAMIC_VAlUE']/../..)[1]//div[1]//a";
  /**
   * Create New Practice while creating process description.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSDESCRIPTION_CREATENEWPRACTICE_TITLE_XPATH = "//div[@class='com-ibm-team-process-authoring-inlineEditor']/input";
  /**
   * Delete button for Process Description.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSDESCRIPTION_DELETE_BUTTON_XPATH ="//span[@class='jazz-ui-toolbar-Button']/a[@title='Delete process description']";
  /**
   * Successfull Message area.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSDESCRIPTION_SUCCESSFULLMESSAGE_XPATH="//div[@class='messageArea OK']/div[2]";
  /**
   * Explore Project button.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_EXPLOREPROJECT_BUTTON_XPATH ="//span[@class='jazz-ui-toolbar-Button has-icon']/a";
  /**
   * Constant string variable to hold text "Create a new process description".
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_CREATE_PROCESSDESCRIPTION="Create a new process description";
  /**
   * Constant string variable to hold text "show details".
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_SHOWDETAILS="show details";
  /**
   * Attachments section xpath in process description.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSATTACHMENTS_XPATH="//div[contains(text(),'Attachments')]";
  /**
   * Choose file in process attachments.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSATTACHMENT_BROWSE_XPATH ="//input[@name='fileInput']";
  /**
   * Process template in Create Project Area.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_PROCESSTEMPLATE_XPATH ="//select[@dojoattachpoint='processSelector']";
  /**
   * Timelines in Manage this project Area.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_PROJECTAREA_TIMELINE_XPATH="//span[contains(text(),'DYNAMIC_VAlUE')]/preceding-sibling::img/../../img";
  /**
   * Constant string variable to hold text "Description:".
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_DESCRIPTION="Description:";
  /**
   * Constant string variable to hold text "Add...".
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_ADD_BUTTON="Add...";
  /**
   * Constant string variable to hold text "Name:".
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_WORKFLOWS_NAME="Name:";
  /**
   * Members in Manage Project area page.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_MEMBER_XPATH="//a[contains(text(),'DYNAMIC_VAlUE')]/../../../..//a[@class ='button']";
  /**
   * Select Role in Team Area.
   */
  public static final String MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_SELECTROLES_XPATH ="//select[(@class='selectRoles' or @class='availableRoles')]";
  /**
   * Work flow dropdown in Manage Project Area.
   */
  public static final String MANAGEPROJECTAREAPAGE_WORKFLOW_SELECTWORKFLOW_DROPDOWN_XPATH ="//select[@name='_selectWorkflow']";
  /**
   * Constant string variable to hold text "Button '".
   */
  public static final String BUTTON="Button '";
  /**
   * Constant string variable to hold text "//span[text()='".
   */
  public static final String SPAN_TEXT="//span[text()='";
  /**
   * Save button in Manage Project Area.
   */
  public static final String MANAGEPROJECTAREAPAGE_SAVE_BTTUON_XPATH ="//button[text()='Save']";
  /**
   * Page Title in Manage Project Area.
   */
  public static final String MANAGEPROJECTAREAPAGE_PAGE_TITLE_XPATH ="//div[contains(@class,'main-column')]//div[@class='pageTitle']";
  /**
   * Header Page Title in Manage Project Area.
   */
  public static final String MANAGEPROJECTAREAPAGE_OVERVIEW_HEADER_PAGE_TITLE_XPATH ="//div[@dojoattachpoint='overViewHeaderSection']//div[@class='pageTitle']";
  /**
   * List categories of permission in Manage Project Area.
   */
  public static final String MANAGEPROJECTAREAPAGE_PERMISSION_CATEGORIES_XPATH ="(//tr[@class='permisionsTableRow categoryRow'])";
  /**
   * Condition wait to disappeared loading message
   */
  public static final String MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_XPATH ="//div[@class='status-message' and contains(@style,'display: none;')]";
  /**
   * Condition wait to dislay loading message
   */
  public static final String MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_DISPLAY_XPATH ="//div[@class='status-message'][contains(@style,'display: block')]";
  /**
   * Permission table when Show by Role
   */
  public static final String MANAGEPROJECTAREAPAGE_PERMISSIONTABLE_FOR_ROLE_XPATH = "(//div[contains(@class,'PermissionsByRolePermissionsColumn') and not(contains(@class,'hidden'))]//td[@class='permissionColumn'])[1]";
  /**
   * Permission table when Show by Operation
   */
  public static final String MANAGEPROJECTAREAPAGE_PERMISSIONTABLE_FOR_OPERATION_XPATH = "(//div[@class='permissionsByActionTable' and not(contains(@class,'hidden'))]//td[@class='permissionColumn'])[1]";
  /**
   * Condition for loading completed permission table
   */
  public static final String MANAGEPROJECTAREAPAGE_PERMISSIONTABLE_LOADED_XPATH = "(//div[contains(@class,'dijitLayoutContainer') and not(contains(@class,'hidden'))]//td[@class='permissionColumn']//a)[1]";
  /**
   * Operation: condition for loading completed permission table
   */
  public static final String MANAGEPROJECTAREAPAGE_OPERATION_PERMISSIONTABLE_LOADED_XPATH = "(//div[contains(@class,'permissionsByActionTable') and not(contains(@class,'hidden'))]//td[@class='permissionColumn']//a)[1]";
  /**
   * Searchbox in permission tab
   */
  public static final String MANAGEPROJECTAREAPAGE_PERMISSIONTAB_SEARCHBOX_XPATH = "//div[contains(@class,'dijitLayoutContainer') and not (contains(@class,'hidden'))]//input[@aria-label='Search text']";

  /**
   * Add member button
   */
  public static final String MANAGEPROJECTAREAPAGE_ADDMEMBER_BUTTON_XPATH = "//a[@dojoattachpoint='memberAddButton']";

  /**
   * Add role dialog - first option
   */
  public static final String MANAGEPROJECTAREAPAGE_ADDROLE_FIRSTOPTION_XPATH =
      "//select[(@class='selectRoles' or @class='availableRoles') and @aria-label='Available Roles:']/option[1]";

  /**
   * Add role dialog - last option
   */
  public static final String MANAGEPROJECTAREAPAGE_ADDROLE_LASTOPTION_XPATH =
      "//select[(@class='selectRoles' or @class='availableRoles') and @aria-label='Available Roles:']/option[last()]";
}
