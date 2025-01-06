/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMAdvancedSCMSearchPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMAllProjectsPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMApplicationAdministrationPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMBrowsePlansPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMBuildDefinitionsPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMCreatePlanPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMCreateQueryPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMCreateReportFromResourcePage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMPlanPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMProjectAreaDashboardPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMWorkItemEditorPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMArtifactsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMDashBoardPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMManageComponentPropertiesPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMModulePage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMReportsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMReviewsPage;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSAllReportsPage;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSBuildNewReportPage;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSMyReportsPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMManageComponentsAndConfigurationsPage;
//import com.bosch.jazz.automation.web.pagemodel.jrs.JazzDashboardPage;

/**
 * Unit tests coverage for the JazzPageFactory.
 */
public class JazzPageFactoryTest extends AbstractFrameworkUnitTest {

  /**
   * Creates the object for CCMBuildPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMBuildPage() {
    CCMBuildDefinitionsPage pageobject = getJazzPageFactory().getCCMBuildPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for CCMApplicationAdministrationPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMApplicationAdministrationPage() {
    CCMApplicationAdministrationPage pageobject = getJazzPageFactory().getCCMApplicationAdministrationPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for CCMPlansPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMPlansPage() {
    CCMCreatePlanPage pageobject = getJazzPageFactory().getCCMPlansPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for CCMQueryPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMQueryPage() {
    CCMCreateQueryPage pageobject = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for CCMReportPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMReportPage() {
    CCMCreateReportFromResourcePage pageobject = getJazzPageFactory().getCCMReportPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for CCMProjectAreaDashboardPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMProjectAreaDashboardPage() {
    CCMProjectAreaDashboardPage pageobject = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for CCMAllProjectsPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMAllProjectsPage() {
    CCMAllProjectsPage pageobject = getJazzPageFactory().getCCMAllProjectsPage();
    assertNotNull(pageobject);
  }
  /**
   * Creates the object for WorkItemPage() and checks the objet should not be null.
   */
  @Test
  public void testGetWorkItemPage() {
    CCMWorkItemEditorPage pageobject = getJazzPageFactory().getWorkItemPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for CCMBrowsePlansPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMBrowsePlansPage() {
    CCMBrowsePlansPage pageobject = getJazzPageFactory().getCCMBrowsePlansPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for CCMCreatePlanPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMCreatePlanPage() {
    CCMCreatePlanPage pageobject = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for CCMPlanPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMPlanPage() {
    CCMPlanPage pageobject = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for JRSAllReportsPage() and checks the objet should not be null.
   */
  @Test
  public void testGetJRSAllReportsPage() {
    JRSAllReportsPage pageobject = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for JRSBuildNewReportPage() and checks the objet should not be null.
   */
  @Test
  public void testGetJRSBuildNewReportPage() {
    JRSBuildNewReportPage pageobject = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for JRSMyReportsPage() and checks the objet should not be null.
   */
  @Test
  public void testGetJRSMyReportsPage() {
    JRSMyReportsPage pageobject = getJazzPageFactory().getJRSMyReportsPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for UserProfilePage() and checks the objet should not be null.
   */
  @Test
  public void testGetUserProfilePage() {
    UserProfilePage pageobject = getJazzPageFactory().getUserProfilePage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for RMArtifactPage() and checks the objet should not be null.
   */
  @Test
  public void testGetRMArtifactPage() {
    RMArtifactsPage pageobject = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for RMDashBoardPage() and checks the objet should not be null.
   */
  @Test
  public void testGetRMDashBoardPage() {
    RMDashBoardPage pageobject = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for RmManageCompProperties() and checks the objet should not be null.
   */
  @Test
  public void testRmManageCompProperties() {
    RMManageComponentPropertiesPage pageobject = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for CCMAdvancedSCMSearchPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMAdvancedSCMSearchPage() {
    CCMAdvancedSCMSearchPage pageobject = getJazzPageFactory().getCCMAdvancedSCMSearchPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for LoginPage() and checks the objet should not be null.
   */
  @Test
  public void testGetLoginPage() {
    JazzLoginPage pageobject = getJazzPageFactory().getLoginPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for RMManageProjectAreaPage() and checks the objet should not be null.
   */
  @Test
  public void testGetRMManageProjectAreaPage() {
    ManageProjectAreaPage pageobject = getJazzPageFactory().getRMManageProjectAreaPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for RMReviewsPage() and checks the objet should not be null.
   */
  @Test
  public void testGetRMReviewsPage() {
    RMReviewsPage pageobject = getJazzPageFactory().getRMReviewsPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for CCMWorkItemEditorPage() and checks the objet should not be null.
   */
  @Test
  public void testGetCCMWorkItemEditorPage() {
    CCMWorkItemEditorPage pageobject = getJazzPageFactory().getCCMWorkItemEditorPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for WorkEnvironmenUserProfilePage() and checks the objet should not be null.
   */
  @Test
  public void testGetWorkEnvironmenUserProfilePage() {
    WorkEnvironmenUserProfilePage pageobject = getJazzPageFactory().getWorkEnvironmenUserProfilePage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for RMModulePage() and checks the objet should not be null.
   */
  @Test
  public void testGetRMModulePage() {
    RMModulePage pageobject = getJazzPageFactory().getRMModulePage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for JazzDashboardPage() and checks the objet should not be null.
   */
  @Test
  public void testGetJazzDashboardPage() {
    JazzDashboardPage pageobject = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for RMReportsPage() and checks the objet should not be null.
   */
  @Test
  public void testGetRMReportsPage() {
    RMReportsPage pageobject = getJazzPageFactory().getRMReportsPage();
    assertNotNull(pageobject);
  }

  /**
   * Creates the object for RQMManageComponentsAndConfigurationsPage() and checks the objet should not be null.
   * @author KYY1HC
   */
  @Test
  public void testGetRQMManageComponentsAndConfigurationsPage() {
    RQMManageComponentsAndConfigurationsPage pageobject = getJazzPageFactory().getRQMManageComponentsAndConfigurationsPage();
    assertNotNull(pageobject);
  }
  
  /**
   * Creates the object for testCreate() and checks the create method returns runtimeexception or not.
   */
  @Test(expected = RuntimeException.class)
  public void testCreate() {
    getJazzPageFactory().create(JazzDashboardPage.class);
  }
}