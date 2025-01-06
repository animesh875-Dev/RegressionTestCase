/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit tests for the CCMCreateReportFromResourcePage
 *
 * @author UUM4KOR
 */
public class CCMCreateReportFromResourcePageTest extends AbstractFrameworkUnitTest {


  /**
   * Loads an reports page in change and configuration management dashboard and checks if
   * getVadationMessageOnReportResource() gets the proper Validation message.
   */

  @Test
  public void testGetVadationMessageOnReportResource() {
    loadPage("ccm/report_get_vadation_message_on_report_resource.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    String errorMsg = "Missing Required Parameter: Build Definition";
    assertTrue(report.getVadationMessageOnReportResource().contains(errorMsg));
  }

  /**
   * Loads an reports page in change and configuration management dashboard and checks if saveReport() Saving the
   * report.
   */

  @Test
  public void testSaveReport() {
    loadPage("ccm/report_save_report.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    report.saveReport();
  }

  /**
   * Loads an reports page in change and configuration management dashboardt and checks if selectFolder() gets selecting
   * the proper folder.
   */

  @Test
  public void testSelectFolder() {
    loadPage("ccm/report_select_folder.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    report.selectFolder("My Reports");
  }

  /**
   * Loads an reports page in change and configuration management dashboardt and checks if selectFolder() gets selecting
   * the folder as empty and validate the exception message.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testSelectFolderOne() {
    loadPage("ccm/report_select_folder.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    String folderName = "";
    report.selectFolder(folderName);
  }

  /**
   * Loads an reports page in change and configuration management dashboardt and checks if selectFolder() gets selecting
   * the folder as null and validate the exception message.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testSelectFolderTwo() {
    loadPage("ccm/report_select_folder.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    String folderName = null;
    report.selectFolder(folderName);
  }

  /**
   * Loads an reports page in change and configuration management dashboard and checks if selectResource() gets
   * selecting the proper Resource.
   */


  @Test
  public void testSelectResource() {
    loadPage("ccm/report_select_resource.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    report.selectResource("Burnup");

  }

  /**
   * Loads an reports page in change and configuration management dashboard and checks if selectResource() gets
   * selecting the Resource as emplty and validate the exception message.
   */


  @Test(expected = IllegalArgumentException.class)
  public void testSelectResourceOne() {
    loadPage("ccm/report_select_resource.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    String resouceName = "";
    report.selectResource(resouceName);

  }

  /**
   * Loads an reports page in change and configuration management dashboard and checks if selectResource() gets
   * selecting the Resource as null and validate the exception message.
   */


  @Test(expected = IllegalArgumentException.class)
  public void testSelectResourceTwo() {
    loadPage("ccm/report_select_resource.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    String resouceName = null;
    report.selectResource(resouceName);
  }

  /**
   * Loads an reports page in change and configuration management dashboard and checks if setDescription() setting the
   * Description.
   */

  @Test
  public void testSetDescription() {
    loadPage("ccm/report_set_description.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    report.setDescription("Created by tester ");
  }

  /**
   * Loads an reports page in change and configuration management dashboard and checks if setDescription() setting the
   * Description as empty and validate the exception message.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testSetDescriptionOne() {
    loadPage("ccm/report_set_description.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    String desc = "";
    report.setDescription(desc);
  }

  /**
   * Loads an reports page in change and configuration management dashboard and checks if setDescription() setting the
   * Description as null and validate the exception message.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testSetDescriptionTwo() {
    loadPage("ccm/report_set_description.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    String desc = null;
    report.setDescription(desc);
  }


  /**
   * Loads an Reports page in change and configuration management dashboard and checks if setReportName() setting the
   * Report name.
   */
  @Test
  public void testSetReportName() {
    loadPage("ccm/report_set_report_name.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    report.setReportName("Report name created by tester");
  }

  /**
   * Loads an Reports page in change and configuration management dashboard and checks if setReportName() setting the
   * Report name as empty and validate the exception message.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testSetReportNameOne() {
    loadPage("ccm/report_set_report_name.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    String reportName = "";
    report.setReportName(reportName);

  }

  /**
   * Loads an Reports page in change and configuration management dashboard and checks if setReportName() setting the
   * Report name as null and validate the exception message.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testSetReportNameTwo() {
    loadPage("ccm/report_set_report_name.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    String reportName = null;
    report.setReportName(reportName);
  }

  /**
   * Loads an Reports page in change and configuration management dashboard and checks if openViaMenu() setting the
   * Report menu.
   */
  @Test
  public void testOpenViaMenu() {
    loadPage("ccm/report_open_via_menu_one.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    report.openViaMenu();
  }

  /**
   * Loads an Reports page in change and configuration management dashboard and checks if isReportSaved() Checking the
   * report is saved successfully or not.
   */

  @Test
  public void testIsReportSaved() {
    loadPage("ccm/report_is_report_saved.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    assertFalse(report.isReportSaved());
  }

  /**
   * Loads an Reports page in change and configuration management dashboard and checks if isReportSaved() Checking the
   * report is saved successfully or not.
   */

  @Test
  public void testIsReportSavedOne() {
    loadPage("ccm/report_save_report.html");
    CCMCreateReportFromResourcePage report = getJazzPageFactory().getCCMReportPage();
    assertNotNull(report);
    assertTrue(report.isReportSaved());
  }


}
