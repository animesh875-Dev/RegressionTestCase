/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rmm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author PZP1KOR
 */
public class RMMConstants {

  /**
   *
   */
  public RMMConstants() {
    super();
  }

  /**
  *
  */
  public static final Map<String, String> ADDITIONAL_PARAMS = new LinkedHashMap<>();

  /**
   *
   */
  public static final String RMM_QUICKNAVIGATION_SCROLL_TO_DIAGRAM =
      "//*[name()='svg']//*[name()='rect'][@x='680' and @y='97']";

  /**
   *
   */
  public static final String RMM_QUICKNAVIGATION_DIAGRAM_CLICK = "//table//a[contains(text(),'statechart_19')]";

  /**
   *
   */
  public static final String RMM_QUICKNAVIGATION_PAGE_TITLE =
      "//div[@dojoattachpoint='_headerContent']/span[@title='Statechart: statechart_19']";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK = "//a[@title='Add Link']";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_SATISFIES = "//*[@id='jazz_ui_menu_MenuItem_2']";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_ELOBARATES = "//*[@id='jazz_ui_menu_MenuItem_4']";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_ELOBARATES_GC = "//*[@id='jazz_ui_menu_MenuItem_4']";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_VALIDATED_BY = "//*[@id='jazz_ui_menu_MenuItem_5']";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_VALIDATED_BY_GC = "//*[@id='jazz_ui_menu_MenuItem_5']";


  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREUEST = "//a[@dojoattachpoint='_addChangeRequestLink']";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREUEST_SELECTPROJECTAREA_OK =
      "//button[@dojoattachpoint='ftOkButton' and @class='j-button-primary']";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_SELECT_REQUIREMENT_OK_BUTTON = "//button[@type='submit']";
  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_NAME =
      "https://rb-alm-02-t3.de.bosch.com/am/web/dojo/resources/blank.html";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_SELECT_FILTER_ARTIFACTS_BY_NAME_ID =
      "//div[@id='jazz_ui_FilterBox_0']/div/input";
  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_DIV =
      "//div[@id='jazz_ui_RemoteChooserContainer_1']/iframe";

  /**
   *
   */
  public static final String RMM_DELETE_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_DIV =
      "//div[@id='jazz_ui_RemoteChooserContainer_0']/iframe";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_FILTER_REQUIREMENT_SWITCHTO_IFRAME_DIV =
      "//div[@id='jazz_ui_RemoteChooserContainer_0']/iframe";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_ENTER_WORKITEM_TEXTBOX = "//input[@class='QueryInput']";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_SELECT_TESTCASES = "//input[@type='checkbox']";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_FIND_TESTCASE =
      "//input[@id='com_ibm_asq_common_web_ui_internal_widgets_layout_ASQValidateTextBox_0']";

  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_FIND_TESTCASE_GC = "//input[@title='Type filter text and press Enter']";
  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_SELECT_TESTCASE_OK_BUTTON = "//button[@class='primary-button']";

  /**
  *
  */
  public static final String RMM_WEB_PROJECT = "web/projects/";

  /**
  *
  */
  public static final String RMM_ACTION_URI = "#action=com.ibm.team.rmm.designs.viewResource";

  /**
  *
  */
  public static final String RMM_RESOURCE_URI = "&resourceUri=";
  /**
  *
  */
  public static final String RMM_RESOURCE_RMM = "resource-rmm/";
  /**
  *
  */
  public static final String RMM_OSLC_CONFIG_CONTEXT = "&oslc_config.context=";
  /**
  *
  */
  public static final String RMM_OSLC_STREAM = "rtcoslc/scm/config/s/";

  /**
  *
  */
  public static final String GC_CONFIGURATION = "configuration/";

  /**
   * link name in application administrationpage.
   */
  public static final String RMM_ELEMENT_TABLE_XPATH =
      "//*[@id=\\\"com_ibm_team_rmm_web_ui_internal_widgets_SelectableStyledTable_0\\\"]/div[2]/table";


  /**
   *
   */
  public static final String RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME =
      "//div[@id='jazz_ui_RemoteChooserContainer_1']/iframe";

  /**
  *
  */
  public static final String RMM_ELEMENT_TYPE_XPATH = "//span[@class='title']";

  /**
  *
  */
  public static final String LINK_VALIDITY_XPATH =
      "//span[@id='com_ibm_team_rmm_web_ui_internal_pages_links_validity_LinkValidityControlWidget_0']/a";

  /**
  *
  */
  public static final String SHOW_LINK_VALIDITY_XPATH =
      "//span[contains(.,'Show Link Validity')]//parent::label//input[@type='checkbox']";

  /**
  *
  */
  public static final String SHOW_LABEL_MODE_XPATH =
      "//span[contains(.,'Label Mode')]//parent::label//input[@type='checkbox']";

  /**
   *
   */
  public static final String RMM_VERIFY_OSLC_LINK_AVAILABILITY = "//a[contains(text(), 'DYNAMIC_VAlUE')]";
  /**
  *
  */
  public static final String DISPLAY_FIELD_AVAILABILITY = "//label[contains(text(), 'DYNAMIC_VAlUE')]";

  /**
   *
   */
  public static final String RMM_SPAN_VERIFY_TEXT_AVAILABILITY = "//span[contains(text(), 'DYNAMIC_VAlUE')]";

  /**
   *
   */
  public static final String RM_DIV_VERIFY_TEXT_AVAILABILITY = "//div[contains(text(), 'DYNAMIC_VAlUE')]";

  /**
   *
   */
  public static final String RM_CLOSE_INOFORMATION_IN_LINKS_SECTION = "//a[@title='DYNAMIC_VAlUE']";

  /**
   *
   */
  public static final String CCM_LINKS_TAB_SCROLL_TO_ELOBARATES_BY_ARCHITECTURE_ELEMENT =
      "//div[@aria-label='Elaborated by Architecture Element  links list Expanded']";

  /**
   *
   */
  public static final String CCM_LINKS_TAB = "";

  /**
  *
  */
  public static final String RMM_OPTION_VERIFY_TEXT_AVAILIBILITY = "//option[contains(text(), 'DYNAMIC_VAlUE')]";

  /**
   *
   */
  public static final String DESCRIPTION_FIELD_XPATH =
      "//div[@id='com_ibm_team_rmm_web_ui_internal_widgets_RMMStyledBox_7_label']";

  /**
   *
   */
  public static final String SCROLL_TO_VALIDATED_BY_ARCHITECTURE_ELEMENT =
      "//div[@id='com_ibm_rqm_defects_web_ui_internal_AmLinksSection_0']";

  /**
   *
   */
  public static final String ADD_LINK_TO_ARTIFACT_RM = "//span[@title='Add Link to Artifact']";

  /**
   *
   */
  public static final String RMM_TD_TEXT_AVAILABILITY = "//td[contains(text(), 'DYNAMIC_VAlUE')]";

  /**
   *
   */
  public static final String RMM_INPUT_WITH_ID = "//input[@id='com_ibm_rdm_web_common_FilteringSelect_2']";

  /**
   *
   */
  public static final String RMM_INPUT_COMPONENT_NAME =
      "//div[@class='textInputDiv']/input[@dojoattachpoint ='_nameInput' and @class='textInput']";

  /**
   *
   */
  public static final String RMM_EXPAND_COMPONENT_NAME = "//div[@class='dojoxGridExpandoNode']";

  /**
   *
   */
  public static final String RMM_CLOSE_RM_PA_SLECTION_WINDOW = "//button[@dojoattachpoint='closeButton']";

  /**
   *
   */
  public static final String RM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_NAME =
      "https://rb-alm-02-t3.de.bosch.com/rm/web/dojo/resources/blank.html";

  /**
   *
   */
  public static final String CCM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_NAME =
      "https://rb-alm-02-t3.de.bosch.com/ccm/web/dojo/resources/blank.html";

  /**
   *
   */
  public static final String QM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_NAME =
      "https://rb-alm-02-t3.de.bosch.com/qm/web/dojo/resources/blank.html";

  /**
   *
   */
  public static final String RM_SELECT_ARCHITECTURE_ELEMENT_OK_BUTTON =
      "//button[@dojoattachpoint='_okCmd' and @class='j-button-primary']";

  /**
   *
   */
  public static final String RM_SELECT_ARCHITECTURE_ELEMENT_LINK = "//a[contains(@href, 'DYNAMIC_VAlUE')]";

  /**
  *
  */
  public static final String QM_SELECT_ARCHITECTURE_ELEMENT_LINK =
      "//a[contains(@href, 'DYNAMIC_VAlUE')]/div/span[contains(text(),'DYNAMIC_VAlUE')]";

  /**
   *
   */
  public static final String ELEMENT_WITH_ANCHOR_TAG_TITLE = "//a[@title='DYNAMIC_VAlUE']";

  /**
  *
  */
  public static final String FIELD_LABEL = "Label:";

  /**
   *
   */
  public static final String QM_NAVIGATE_OSLC_LINK =
      "//tr[contains(@name,'DYNAMIC_VAlUE')]//following-sibling::a[contains(@href, 'DYNAMIC_VAlUE')]";

  /**
   *
   */
  public static final String RM_QUICK_VIEW_OPEN_REQUIREMENT =
      "//span[contains(text(), 'DYNAMIC_VAlUE')]//parent::a[@class='jazz-ui-ResourceLink']";

  /**
   *
   */
  public static final String ANCHOR_TAG_HREF_CONTAINS_TEXT = "//a[contains(@href, 'DYNAMIC_VAlUE')]";

  /**
   *
   */
  public static final String REMOVE_OSLC_LINK_FROM_CCM =
      "//a[contains(@href, 'DYNAMIC_VAlUE')]//ancestor::div[@class='ValueDiv']/span[@title='Remove']";

  // GC Constants
  /**
   *
   */
  public static final String DNG_GC_URL_PARAM_ACTION = "web#action=com.ibm.rdm.web.pages.showArtifactPage";

  /**
   *
   */
  public static final String DNG_GC_URL_PARAM_ARTIFACT_URI = "&artifactURI=";

  /**
   *
   */
  public static final String DNG_GC_URL_PARAM_RESOURCES = "resources/";

  /**
   *
   */
  public static final String DNG_GC_URL_PARAM_COMPONENT_URI = "&componentURI=";


  /**
   *
   */
  public static final String DNG_GC_URL_PARAM_RM_PROJECTS = "rm-projects/";


  /**
   *
   */
  public static final String DNG_GC_URL_PARAM_COMPONENT = "/components/";

  /**
   *
   */
  public static final String CCM_GC_URL_PARAM_WEB_PROJECTS = "web/projects/";

  /**
   *
   */
  public static final String CCM_GC_URL_PARAM_ACTION = "#action=com.ibm.team.workitem.viewWorkItem";

  /**
   *
   */
  public static final String CCM_GC_URL_PARAM_ID = "&id=";

  /**
   *
   */
  public static final String QM_GC_URL_PARAM_WEB_CONSOLE = "web/console/";

  /**
   *
   */
  public static final String QM_GC_URL_PARAM_JAZZ_LOCAL_PATH = "?jazz_config.local_path=";


  /**
   *
   */
  public static final String QM_GC_URL_PARAM_ACTION = "#action=com.ibm.rqm.planning.home.actionDispatcher";

  /**
   *
   */
  public static final String QM_GC_URL_PARAM_SUBACTION = "&subAction=viewTestCase&id=";
}
