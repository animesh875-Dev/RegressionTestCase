/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;


/**
 * Unit test coverage for the methods of RMReportsPage.
 */
public class RMReportsPageTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test to verify {@link RMReportsPage#clickOnGenerateReport()}.
   */
  @Test
  public void testClickOnGenerateReport() {
    loadPage("dng/generateReport.html");
    RMReportsPage rm = getJazzPageFactory().getRMReportsPage();
    assertNotNull(rm);
    rm.clickOnGenerateReport();
  }

  /**
   * <p>
   * Unit test to verify {@link RMReportsPage#generateReport(String)}.
   */
  @Test
  public void testGenerateReport() {
    loadPage("dng/generate_reportfor_view_reporttype.html");
    RMReportsPage rm = getJazzPageFactory().getRMReportsPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    Map<Integer, String> clickNumberToPagePathAction = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/generate_reportfor_view_next_button.html");
    clickNumberToPagePath.put(2, "dng/generate_reportfor_view_report_name.html");
    clickNumberToPagePath.put(3, "dng/generate_reportfor_view_next_but.html");
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/generate_reportfor_view_finish_button.html");
    clickNumberToPagePath.put(6, "dng/reportClose.html");
    clickNumberToPagePath.put(7, "dng/reportPDF.html");
    loadNewPageOnActionsCall(clickNumberToPagePathAction);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.generateReport("Print Module Book");
  }

  /**
   * <p>
   * Unit test to verify {@link RMReportsPage#getReportGenerationStatusText()}.
   */
  @Test
  public void testGetReportGenerationStatusText() {
    loadPage("dng/report_generation_complete.html");
    RMReportsPage rm = getJazzPageFactory().getRMReportsPage();
    assertNotNull(rm);
    assertEquals("Report generation complete", rm.getReportGenerationStatusText());
  }

}