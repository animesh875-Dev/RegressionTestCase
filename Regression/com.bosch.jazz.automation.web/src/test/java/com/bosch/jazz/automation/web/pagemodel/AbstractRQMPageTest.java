/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstructionPage;

/**
 * This class represents AbstractRQMPage.
 */
public class AbstractRQMPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testExecutionRecord() {
    loadPage("rqm/select_project_area_and_global_configuration_select_pa.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Cases");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/select_project_area_and_global_configuration_current_project_component.html");
    clickToPage.put(2, "rqm/select_project_area_and_global_configuration_choose_another_component.html");
    clickToPage.put(3, "rqm/select_project_area_and_global_configuration_type_to_filter_list.html");
    clickToPage.put(4, null);
    clickToPage.put(5, null);
    clickToPage.put(6, null);
    clickToPage.put(7, "rqm/select_project_area_and_global_configuration_component_and_ok.html");
    clickToPage.put(8, "dng/select_theconfig_Context.html");
    clickToPage.put(9, "dng/selectTheConfigContext_window.html");
    clickToPage.put(10, "rqm/select_project_area_and_global_configuration_stream.html");
    clickToPage.put(11, null);
    clickToPage.put(12, null);
    clickToPage.put(13, "rqm/select_project_area_and_global_configuration_stream_files/configurationPicker.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.selectProjectAreaAndGlobalConfiguration("ALM Test (QM)", "test", "ALM_System",
        "ALM_System_AcceptanceTest_Platform");
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testExecutionRecordCompNotMatching() {
    loadPage("rqm/select_project_area_and_global_configuration_select_pa.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Cases");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/select_project_area_and_global_configuration_current_project_component.html");
    clickToPage.put(2, "rqm/select_project_area_and_global_configuration_choose_another_component.html");
    clickToPage.put(3, "rqm/select_project_area_and_global_configuration_type_to_filter_list.html");
    clickToPage.put(4, null);
    clickToPage.put(5, null);
    clickToPage.put(6, null);
    clickToPage.put(7, "rqm/select_project_area_and_global_configuration_component_and_ok.html");
    clickToPage.put(8, "dng/select_theconfig_Context.html");
    clickToPage.put(9, "dng/selectTheConfigContext_window.html");
    clickToPage.put(10, "rqm/select_project_area_and_global_configuration_stream.html");
    clickToPage.put(11, null);
    clickToPage.put(12, null);
    clickToPage.put(13, "rqm/select_project_area_and_global_configuration_stream_files/configurationPicker.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.selectProjectAreaAndGlobalConfiguration("ALM Test (QM)", "test", "ALM_System_2",
        "ALM_System_AcceptanceTest_Platform");
  }
  
  /**
   * Unit test to verify {@link AbstractRQMPage#getNumberOfRowsInTable(String)}
   * @author LTU7HC
   */
  @Test
  public void testGetNumberOfRowsInTable() {
    loadPage("rqm/get_number_of_row_in_table.html");
    AbstractRQMPage rqm = getJazzPageFactory().getAbstractRQMPage();
    assertNotNull(rqm);
    String numberOfRow = rqm.getNumberOfRowsInTable("Test Suites");
    assertEquals("25", numberOfRow);
  }
  
  
  /**
   * Unit test to verify {@link AbstractRQMPage#showInlineFilters()}
   * @author LTU7HC
   */
  @Test
  public void testShowInlineFilters() {
    loadPage("rqm/select_slide_down.html");
    AbstractRQMPage rqm = getJazzPageFactory().getAbstractRQMPage();
    assertNotNull(rqm);
    rqm.showInlineFilters();
  }
  
  /**
   * Unit test to verify {@link AbstractRQMPage#showFilters()}
   * @author LTU7HC
   */
  @Test
  public void testShowFilters() {
    loadPage("rqm/select_slide_down.html");
    AbstractRQMPage rqm = getJazzPageFactory().getAbstractRQMPage();
    assertNotNull(rqm);
    rqm.showFilters();
  }
}
