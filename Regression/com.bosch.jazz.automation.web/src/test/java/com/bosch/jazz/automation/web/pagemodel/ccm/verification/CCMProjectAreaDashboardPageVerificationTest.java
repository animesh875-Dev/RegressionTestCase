/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm.verification;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMProjectAreaDashboardPageVerification;

/**
 * Loads a JazzLogin Page and verifies the methods.
 */
public class CCMProjectAreaDashboardPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link CCMProjectAreaDashboardPageVerification#verifySelectWorkItemFromCreateWorkitemDialogToCreate(String,String)}.
   *
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectWorkItemFromCreateWorkitemDialogToCreate() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySelectWorkItemFromCreateWorkitemDialogToCreate("Task", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMProjectAreaDashboardPageVerification#verifySelectWorkItemFromCreateWorkitemDialogToCreate(String,String)}.
   *
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySelectWorkItemFromCreateWorkitemDialogToCreate() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySelectWorkItemFromCreateWorkitemDialogToCreate("test", "");
  }

  /**
   * <p>
   * Unit test covers for
   * ${@link CCMProjectAreaDashboardPageVerification#verifyCreateWorkItemsFromWorkItemTemplate(Map, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyCreateWorkItemsFromWorkItemTemplate() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/backToQueryPage.html");
    Map<String, String> params = new HashMap<String, String>();
    params.put("templateName", "Work item template for automation testing UC 173420-02T1_NonGC_1");
    params.put("filedAgainst", "ALM Project");
    params.put("plannedFor", "Sprint 1");
    assertNotNull(cqpv);
    cqpv.verifyCreateWorkItemsFromWorkItemTemplate(params, "270960");
  }

  /**
   * <p>
   * Unit test covers for
   * ${@link CCMProjectAreaDashboardPageVerification#verifyCreateWorkItemsFromWorkItemTemplate(Map, String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyCreateWorkItemsFromWorkItemTemplate() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/backToQueryPage.html");
    Map<String, String> params = new HashMap<String, String>();
    params.put("templateName", "test");
    params.put("filedAgainst", "abc");
    params.put("plannedFor", "edf");
    assertNotNull(cqpv);
    cqpv.verifyCreateWorkItemsFromWorkItemTemplate(params, "123");
  }


  /**
   * <p>
   * Unit test covers for ${@link CCMProjectAreaDashboardPageVerification#verifyGetWorkItemIDGenerated(String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyGetWorkItemIDGenerated() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/backToQueryPage.html");
    assertNotNull(cqpv);
    cqpv.verifyGetWorkItemIDGenerated("Epic for automation testing UC 173420_Summary", "270960");
  }

  /**
   * <p>
   * Unit test covers for ${@link CCMProjectAreaDashboardPageVerification#verifyGetWorkItemIDGenerated(String, String)}
   * <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyGetWorkItemIDGenerated() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/backToQueryPage.html");
    assertNotNull(cqpv);
    cqpv.verifyGetWorkItemIDGenerated("test", "12345");
  }

  /**
   * <p>
   * Unit test covers for ${@link CCMProjectAreaDashboardPageVerification#verifyBackToQueryPage(String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyBackToQueryPage() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/backToQueryPage.html");
    assertNotNull(cqpv);
    cqpv.verifyBackToQueryPage("");
  }

  /**
   * <p>
   * Unit test covers for ${@link CCMProjectAreaDashboardPageVerification#verifyBackToQueryPage(String)} Cover for the
   * failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyBackToQueryPage() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/backToQueryPage.html");
    assertNotNull(cqpv);
    cqpv.verifyBackToQueryPage("");
  }

  /**
   * <p>
   * Unit test covers for ${@link CCMProjectAreaDashboardPageVerification#verifyVerifyTabLinks(String,String,String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyVerifyTabLinks() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/verifyTabLinks.html");
    assertNotNull(cqpv);
    cqpv.verifyVerifyTabLinks("Parent", "270960", "");
  }

  /**
   * <p>
   * Unit test covers for ${@link CCMProjectAreaDashboardPageVerification#verifyVerifyTabLinks(String,String,String)}
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyVerifyTabLinks() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/verifyTabLinks.html");
    assertNotNull(cqpv);
    cqpv.verifyVerifyTabLinks("test", "12345", "");
  }

  /**
   * <p>
   * Unit test covers for
   * ${@link CCMProjectAreaDashboardPageVerification#verifyVerifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(String, String, String, String, String, String, String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyVerifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/verifyOverviewInformationOfWorkItemCreatedByWorkItemTemplate.html");
    assertNotNull(cqpv);
    cqpv.verifyVerifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(
        "Work item template for automation testing UC 173420-02T1_NonGC_1", "270960",
        "Epic for automation testing UC 173420_Summary", "Epic", "ALM Project", "Sprint 1",
        "Epic for automation testing UC 173420_Description", "");
  }

  /**
   * <p>
   * Unit test covers for
   * ${@link CCMProjectAreaDashboardPageVerification#verifyVerifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(String, String, String, String, String, String, String, String)}
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyVerifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate() {
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    loadPage("ccm/verifyOverviewInformationOfWorkItemCreatedByWorkItemTemplate.html");
    assertNotNull(cqpv);
    cqpv.verifyVerifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate("test", "12345", "adc", "test", "test",
        "test", "test", "");
  }
  
  /**
   * Unit test covers for method ${@link CCMProjectAreaDashboardPageVerification#verifyArchiveTimelineOrIteration(String, String)}
   * @author NCY3HC
   */
  @Test
  public void testVerifyArchiveTimelineOrIteration() {
    loadPage("ccm/archiveTimeline3.html");
    CCMProjectAreaDashboardPageVerification cqpv = getJazzPageFactory().getCCMProjectAreaDashboardPageVerification();
    assertNotNull(cqpv);
    cqpv.verifyArchiveTimelineOrIteration("TimeLine created by Automation 29_09_2022_14_09_685", "true");
    cqpv.verifyArchiveTimelineOrIteration("TimeLine created by Automation 29_09_2022_14_09_685", "false");
  }
  
}
