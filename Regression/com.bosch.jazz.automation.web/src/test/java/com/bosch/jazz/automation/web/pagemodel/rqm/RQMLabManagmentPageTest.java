/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.DateUtil;

/**
 * Unit tests coverage for the RQMLabManagmentPage.
 */
public class RQMLabManagmentPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads RQM test data page and summaryText(final Map<String, String> additionalParams) use to pass test environment
   * summary.
   */
  @Test
  public void testSummaryText() {
    loadPage("rqm/summary_text.html");
    RQMLabManagmentPage rqm = getJazzPageFactory().getRQMLabManagmentPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactTitleValue", "Test_Environment_" + DateUtil.getCurrentDateAndTime());
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.summaryText(additionalParams);
  }

  /**
   * Loads RQM test Environment page and addTestEnvironCriteria(final Map<String, String> additionalParams,String data)
   * will click on add criteria Type.
   */
  @Test
  public void testAddTestEnvironCriteria() {
    loadPage("rqm/add_test_environ_criteria.html");
    RQMLabManagmentPage rqm = getJazzPageFactory().getRQMLabManagmentPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("typeName", "Type:");
    assertNotNull(rqm);
    rqm.addTestEnvironCriteria(additionalParams, "typeName");
  }

  /**
   * Loads RQM test environment page and selectTypeAddContents(final Map<String, String> additionalParams, String
   * selectName, String selectValue, String domainName) will add operating system label to test environment.
   */
  @Test
  public void testSelectTypeAddContents() {
    loadPage("rqm/add_test_environ_criteria.html");
    RQMLabManagmentPage rqm = getJazzPageFactory().getRQMLabManagmentPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("typeName", "Type:");
    additionalParams.put("selectCriteriaName", "Select Criteria");
    additionalParams.put("selectOSValue", "Physical Machine");
    additionalParams.put("addButton", "Add and Close");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/add_teste_envi_content.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.selectTypeAddContents(additionalParams, "selectCriteriaName", "selectOSValue", "typeName");
  }

  /**
   * Loads RQM test environment page and selectTypeAddContents(final Map<String, String> additionalParams, String
   * selectName, String selectValue, String domainName) will add Installed Software label to test environment.
   */
  @Test
  public void testSelectTypeAddContent() {
    loadPage("rqm/select_type_add_contents.html");
    RQMLabManagmentPage rqm = getJazzPageFactory().getRQMLabManagmentPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("osName", "Operating System:");
    additionalParams.put("selectCriteriaName", "Select Criteria");
    additionalParams.put("selectInstalledSWValue", "Physical Machine");
    additionalParams.put("addButton", "Add and Close");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/select_type_add_contents_first.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.selectTypeAddContents(additionalParams, "selectCriteriaName", "selectInstalledSWValue", "osName");
  }

  /**
   * Loads RQM test environment page and selectTypeAddContents(final Map<String, String> additionalParams, String
   * selectName, String selectValue, String domainName) will add Installed Software label to test environment.
   */
  @Test
  public void testSelectTypesAddContent() {
    loadPage("rqm/select_type_add_contents_first.html");
    RQMLabManagmentPage rqm = getJazzPageFactory().getRQMLabManagmentPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("osName", "Operating");
    additionalParams.put("selectCriteriaName", "Select Criteria");
    additionalParams.put("selectInstalledSWValue", "Installed Software");
    additionalParams.put("addButton", "Add and Close");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/select_type_add_contents_first.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.selectTypeAddContents(additionalParams, "selectCriteriaName", "selectInstalledSWValue", "osName");
  }
  /**
   * 
   */
  @Test
  public void testRemoveTestEnvironment() {
    loadPage("rqm/remove_test_environment.html");
    RQMLabManagmentPage rqm = getJazzPageFactory().getRQMLabManagmentPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/remove_test_environment_button.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.removeTestEnvironment("AMD64", "Yes");
  }

}
