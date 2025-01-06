/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;


import java.lang.reflect.Constructor;

import org.openqa.selenium.WebDriver;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMAdvancedSCMSearchPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMAllProjectsPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMApplicationAdministrationPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMBrowsePlansPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMBuildDefinitionsPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMCreatePlanPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMCreateQueryPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMCreateReportFromResourcePage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMEditWorkItemDialogInPlanPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMPlanPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMProjectAreaDashboardPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMQuickPlannerPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMWelcomeToWorkItemPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMWorkItemEditorPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMArtifactsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMCollectionsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMDashBoardPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMLinksPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMManageComponentPropertiesPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMManageComponentsAndConfigurations;
import com.bosch.jazz.automation.web.pagemodel.dng.RMMiniDashBoardPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMModulePage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMReportsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMReviewsPage;
import com.bosch.jazz.automation.web.pagemodel.gc.BrowseComponentsPage;
import com.bosch.jazz.automation.web.pagemodel.gc.ComponentsPage;
import com.bosch.jazz.automation.web.pagemodel.gc.GCAllProjectsPage;
import com.bosch.jazz.automation.web.pagemodel.gc.GCDashBoardPage;
import com.bosch.jazz.automation.web.pagemodel.gc.GCStreamsPage;
import com.bosch.jazz.automation.web.pagemodel.gc.GlobalConfigurationsPage;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSAllReportsPage;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSBuildNewReportPage;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSMyReportsPage;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSPage;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSViewReportPage;
import com.bosch.jazz.automation.web.pagemodel.rmm.RMMModelElementPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMAllProjectsPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMBuildsPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstructionPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMDashBoardPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMExecutionPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMLabManagmentPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMManageArtifactTemplatesPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMManageComponentsAndConfigurationsPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMManageProjectPropertiesPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMPlanningPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMReportsPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMTrashPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.JazzDashboardPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.JazzLoginPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.ManageProjectAreaPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMCreatePlanPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMCreateQueryPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMEditWorkItemDialogInPlanPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMPlanPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMProjectAreaDashboardPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMQuickPlannerPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMWelcomeToWorkItemPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMWorkItemEditorPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMArtifactsPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMCollectionsPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMDashBoardPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMLinksPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMManageComponentPropertiesPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMManageComponentsAndConfigurationsVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMMiniDashBoardPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMModulePageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMReviewsPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.gc.BrowseComponentsPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.gc.ComponentsPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.gc.GCAllProjectsPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.gc.GCDashBoardPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.gc.GCStreamsPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.gc.GlobalConfigurationsPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.jrs.JRSBuildNewReportPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMConstructionPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMExecutionPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMLabManagmentPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMManageComponentsAndConfigurationsPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMPlanningPageVerification;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMTrashPageVerification;
import com.google.common.base.Verify;

/**
 * Factory class for providing page model objects.
 */
public class JazzPageFactory {

  private final WebDriverCustom webDriverCustom;

  /**
   * @param webDriverCustom is used by every provided page model object for interacting with the browser, must not be
   *          null
   */
  public JazzPageFactory(final WebDriverCustom webDriverCustom) {
    Verify.verifyNotNull(webDriverCustom);
    this.webDriverCustom = webDriverCustom;
  }

  /**
   * Creates a page model instance of the given class. The class must have either a constructor that takes a
   * {@link WebDriver} or takes a {@link WebDriverCustom}. If such a constructor is not available then an Exception will
   * be thrown.
   *
   * @param pageClass the class to create an instance from.
   * @return the created instance
   */
  public <T> T create(final Class<T> pageClass) {
    try {
      Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
      if (constructor != null) {
        return constructor.newInstance(this.webDriverCustom);
      }
      constructor = pageClass.getConstructor(WebDriverCustom.class);
      if (constructor != null) {
        return constructor.newInstance(this.webDriverCustom.getWebDriver());
      }
      throw new IllegalArgumentException("No suitable constructor found for: " + pageClass.getSimpleName());
    }
    catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @SuppressWarnings("javadoc")
  public CCMBuildDefinitionsPage getCCMBuildPage() {
    return new CCMBuildDefinitionsPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMApplicationAdministrationPage getCCMApplicationAdministrationPage() {
    return new CCMApplicationAdministrationPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMCreatePlanPage getCCMPlansPage() {
    return new CCMCreatePlanPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMCreateQueryPage getCCMQueryPage() {
    return new CCMCreateQueryPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMCreateReportFromResourcePage getCCMReportPage() {
    return new CCMCreateReportFromResourcePage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMAdvancedSCMSearchPage getCCMAdvancedSCMSearchPage() {
    return new CCMAdvancedSCMSearchPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMProjectAreaDashboardPage getCCMProjectAreaDashboardPage() {
    return new CCMProjectAreaDashboardPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMAllProjectsPage getCCMAllProjectsPage() {
    return new CCMAllProjectsPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public JazzLoginPage getLoginPage() {
    return new JazzLoginPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMWorkItemEditorPage getWorkItemPage() {
    return new CCMWorkItemEditorPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMWelcomeToWorkItemPage getCCMWelcomeToWorkItemPage() {
    return new CCMWelcomeToWorkItemPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMBrowsePlansPage getCCMBrowsePlansPage() {
    return new CCMBrowsePlansPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMCreatePlanPage getCCMCreatePlanPage() {
    return new CCMCreatePlanPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMPlanPage getCCMPlanPage() {
    return new CCMPlanPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public JRSAllReportsPage getJRSAllReportsPage() {
    return new JRSAllReportsPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public JRSBuildNewReportPage getJRSBuildNewReportPage() {
    return new JRSBuildNewReportPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public JRSMyReportsPage getJRSMyReportsPage() {
    return new JRSMyReportsPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public JRSViewReportPage getJRSViewReportPage() {
    return new JRSViewReportPage(this.webDriverCustom);
  }
  
  @SuppressWarnings("javadoc")
  public JRSPage getJRSPage() {
    return new JRSPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public ManageProjectAreaPage getManageProjectAreaPage() {
    return new ManageProjectAreaPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public UserProfilePage getUserProfilePage() {
    return new UserProfilePage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RMArtifactsPage getRMArtifactPage() {
    return new RMArtifactsPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RMDashBoardPage getRMDashBoardPage() {
    return new RMDashBoardPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RMManageComponentPropertiesPage getRMManageCompProperties() {
    return new RMManageComponentPropertiesPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RQMConstructionPage getRQMConstructionPage() {
    return new RQMConstructionPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RQMLabManagmentPage getRQMLabManagmentPage() {
    return new RQMLabManagmentPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RQMPlanningPage getRQMPlanningPage() {
    return new RQMPlanningPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RQMBuildsPage getRQMBuildsPage() {
    return new RQMBuildsPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RQMManageProjectPropertiesPage getRQMManageProjectPropertiesPage() {
    return new RQMManageProjectPropertiesPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RQMManageComponentsAndConfigurationsPage getRQMManageComponentsAndConfigurationsPage() {
    return new RQMManageComponentsAndConfigurationsPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RQMTrashPage getRQMTrashPage() {
    return new RQMTrashPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public ManageProjectAreaPage getRMManageProjectAreaPage() {
    return new ManageProjectAreaPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RMReviewsPage getRMReviewsPage() {
    return new RMReviewsPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMWorkItemEditorPage getCCMWorkItemEditorPage() {
    return new CCMWorkItemEditorPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMEditWorkItemDialogInPlanPage getCCMEditWorkItemDialogInPlanPage() {
    return new CCMEditWorkItemDialogInPlanPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public WorkEnvironmenUserProfilePage getWorkEnvironmenUserProfilePage() {
    return new WorkEnvironmenUserProfilePage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RMModulePage getRMModulePage() {
    return new RMModulePage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public JazzDashboardPage getJazzDashboardPage() {
    return new JazzDashboardPage(this.webDriverCustom);
  }

  /**
   * @return
   */
  @SuppressWarnings("javadoc")
  public RMReportsPage getRMReportsPage() {
    return new RMReportsPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RMCollectionsPage getRMCollectionsPage() {
    return new RMCollectionsPage(this.webDriverCustom);
  }


  @SuppressWarnings("javadoc")
  public RMLinksPage getRMLinksPage() {
    return new RMLinksPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RQMReportsPage getRQMReportsPage() {
    return new RQMReportsPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RQMManageArtifactTemplatesPage getRQMManageArtifactTemplates() {
    return new RQMManageArtifactTemplatesPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RMManageComponentsAndConfigurations getRMManageComponentsAndConfigurations() {
    return new RMManageComponentsAndConfigurations(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public JazzLoginPageVerification getJazzLoginPageVerification() {
    return new JazzLoginPageVerification(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMCreateQueryPageVerification getCCMCreateQueryPageVerification() {
    return new CCMCreateQueryPageVerification(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMEditWorkItemDialogInPlanPageVerification getCCMEditWorkItemDialogInPlanPageVerification() {
    return new CCMEditWorkItemDialogInPlanPageVerification(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMPlanPageVerification getCCMPlanPageVerification() {
    return new CCMPlanPageVerification(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMProjectAreaDashboardPageVerification getCCMProjectAreaDashboardPageVerification() {
    return new CCMProjectAreaDashboardPageVerification(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMWelcomeToWorkItemPageVerification getCCMWelcomeToWorkItemPageVerification() {
    return new CCMWelcomeToWorkItemPageVerification(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMWorkItemEditorPageVerification getCCMWorkItemEditorPageVerification() {
    return new CCMWorkItemEditorPageVerification(this.webDriverCustom);
  }


  /**
   * @return RMArtifactsPageVerification object.
   */
  public RMArtifactsPageVerification getRMArtifactsPageVerification() {
    return new RMArtifactsPageVerification(this.webDriverCustom);
  }

  /**
   * @return RMArtifactsPageVerification object.
   */
  public RMDashBoardPageVerification getRMDashBoardPageVerification() {
    return new RMDashBoardPageVerification(this.webDriverCustom);
  }

  /**
   * @return RMArtifactsPageVerification object.
   */
  public RMLinksPageVerification getRMLinksPageVerification() {
    return new RMLinksPageVerification(this.webDriverCustom);
  }

  /**
   * @return RMArtifactsPageVerification object.
   */
  public RMModulePageVerification getRMModulePageVerification() {
    return new RMModulePageVerification(this.webDriverCustom);
  }

  /**
   * @return RMArtifactsPageVerification object.
   */
  public RMManageComponentsAndConfigurationsVerification getRMManageComponentsAndConfigurationsVerification() {
    return new RMManageComponentsAndConfigurationsVerification(this.webDriverCustom);
  }

  /**
   * @return RQMConstructionPageVerification object.
   */
  public RQMConstructionPageVerification getRQMConstructionPageVerification() {
    return new RQMConstructionPageVerification(this.webDriverCustom);
  }

  /**
   * @return RQMPlanningPageVerification object.
   */
  public RQMPlanningPageVerification getRQMPlanningPageVerification() {
    return new RQMPlanningPageVerification(this.webDriverCustom);
  }

  /**
   * @return RQMLabManagmentPageVerification object.
   */
  public RQMLabManagmentPageVerification getRQMLabManagmentPageVerification() {
    return new RQMLabManagmentPageVerification(this.webDriverCustom);
  }

  /**
   * @return RQMManageComponentsAndConfigurationsPageVerification object.
   */
  public RQMManageComponentsAndConfigurationsPageVerification getRQMManageComponentsAndConfigurationsPageVerification() {
    return new RQMManageComponentsAndConfigurationsPageVerification(this.webDriverCustom);
  }

  /**
   * @return RQMTrashPageVerification object.
   */
  public RQMTrashPageVerification getRQMTrashPageVerification() {
    return new RQMTrashPageVerification(this.webDriverCustom);
  }

  /**
   * @return AbstractWebPageVerification object.
   */
  public AbstractWebPageVerification getAbstractWebPageVerification() {
    return new AbstractWebPageVerification(this.webDriverCustom);
  }

  /**
   * @return ComponentsPageVerification object.
   */
  public ComponentsPageVerification getComponentsPageVerification() {
    return new ComponentsPageVerification(this.webDriverCustom);
  }

  /**
   * @return BrowseComponentsPageVerification object.
   */
  public BrowseComponentsPageVerification getBrowseComponentsPageVerification() {
    return new BrowseComponentsPageVerification(this.webDriverCustom);
  }

  /**
   * @return GCAllProjectsPageVerification object.
   */
  public GCAllProjectsPageVerification getGCAllProjectsPageVerification() {
    return new GCAllProjectsPageVerification(this.webDriverCustom);
  }

  /**
   * @return GCAllProjectsPageVerification object.
   */
  public GlobalConfigurationsPageVerification getGlobalConfigurationsPageVerification() {
    return new GlobalConfigurationsPageVerification(this.webDriverCustom);
  }

  /**
   * @return AbstractGC Page object.
   */
  public AbstractGCPage getAbstractGCPage() {
    return new AbstractGCPage(this.webDriverCustom);
  }

  /**
   * @return AbstractGC Page object.
   */
  public RQMAllProjectsPage getRQMAllProjectsPage() {
    return new RQMAllProjectsPage(this.webDriverCustom);
  }

  /**
   * @return AbstractGC Page object.
   */
  public RQMDashBoardPage getRQMDashBoardPage() {
    return new RQMDashBoardPage(this.webDriverCustom);
  }

  /**
   * @return AbstractCCM Page object.
   */
  public AbstractCCMPage getAbstractCCMPage() {
    return new AbstractCCMPage(this.webDriverCustom);
  }

  /**
   * @return BrowseComponents Page object.
   */
  public BrowseComponentsPage getBrowseComponentsPage() {
    return new BrowseComponentsPage(this.webDriverCustom);
  }

  /**
   * @return GCAllProjects Page object.
   */
  public GCAllProjectsPage getGCAllProjectsPage() {
    return new GCAllProjectsPage(this.webDriverCustom);
  }

  /**
   * @return Components Page object.
   */
  public ComponentsPage getComponentsPage() {
    return new ComponentsPage(this.webDriverCustom);
  }

  /**
   * @return GlobalConfigurations Page object.
   */
  public GlobalConfigurationsPage getGlobalConfigurationsPage() {
    return new GlobalConfigurationsPage(this.webDriverCustom);
  }

  /**
   * @return GCDashBoard Page object.
   */
  public GCDashBoardPage getGCDashBoardPage() {
    return new GCDashBoardPage(this.webDriverCustom);
  }

  /**
   * @return GCDashBoardPageVerifycation object.
   */
  public GCDashBoardPageVerification getGCDashBoardPageVerification() {
    return new GCDashBoardPageVerification(this.webDriverCustom);
  }

  /**
   * @return GCStreams Page object.
   */
  public GCStreamsPage getGCStreamsPage() {
    return new GCStreamsPage(this.webDriverCustom);
  }

  /**
   * @return GCStreamsPageVerifycation object.
   */
  public GCStreamsPageVerification getGCStreamsPageVerification() {
    return new GCStreamsPageVerification(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMQuickPlannerPage getCCMQuickPlannerPage() {
    return new CCMQuickPlannerPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMQuickPlannerPageVerification getCCMQuickPlannerPageVerification() {
    return new CCMQuickPlannerPageVerification(this.webDriverCustom);
  }

  /**
   * @return JRSBuildNewReportPageVerification Page object.
   */
  public JRSBuildNewReportPageVerification getJRSBuildNewReportPageVerification() {
    return new JRSBuildNewReportPageVerification(this.webDriverCustom);
  }

  /**
   * @return RMManageComponentPropertiesPageVerification Page object.
   */
  public RMManageComponentPropertiesPageVerification getRMManageComponentPropertiesPageVerification() {
    return new RMManageComponentPropertiesPageVerification(this.webDriverCustom);
  }

  /**
   * @return RQMExecution Page object.
   */
  public RQMExecutionPage getRQMExecutionPage() {
    return new RQMExecutionPage(this.webDriverCustom);
  }

  /**
   * @return RQMExecution Page object.
   */
  public RQMExecutionPageVerification getRQMExecutionPageVerification() {
    return new RQMExecutionPageVerification(this.webDriverCustom);
  }

  /**
   * @return AbstractRQMPage Page object.
   */
  public AbstractRQMPage getAbstractRQMPage() {
    return new AbstractRQMPage(this.webDriverCustom);
  }

  /**
   * @return ManageProjectAreaPageVerification object.
   */
  public ManageProjectAreaPageVerification getManageProjectAreaPageVerification() {
    return new ManageProjectAreaPageVerification(this.webDriverCustom);
  }

  /**
   * @return RMReviewsPageVerification object.
   */
  public RMReviewsPageVerification getRMReviewsPageVerification() {
    return new RMReviewsPageVerification(this.webDriverCustom);
  }

  /**
   * @return RMMiniDashBoardPage Page object.
   */
  public RMMiniDashBoardPage getRMMiniDashBoardPage() {
    return new RMMiniDashBoardPage(this.webDriverCustom);
  }

  /**
   * @return RMMiniDashBoardPageVerification Page object.
   */
  public RMMiniDashBoardPageVerification getRMMiniDashBoardPageVerification() {
    return new RMMiniDashBoardPageVerification(this.webDriverCustom);
  }

  /**
   * @return JazzDashboardPageVerification object.
   */
  public JazzDashboardPageVerification getJazzDashboardPageVerification() {
    return new JazzDashboardPageVerification(this.webDriverCustom);
  }

  /**
   * @return RMCollectionsPageVerification Page Object
   */
  public RMCollectionsPageVerification getRMCollectionsPageVerification() {
    return new RMCollectionsPageVerification(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public CCMCreatePlanPageVerification getCCMCreatePlanPageVerification() {
    return new CCMCreatePlanPageVerification(this.webDriverCustom);
  }

  // RMM Automation
  @SuppressWarnings("javadoc")
  public AbstractRMMPage getAbstractRMMPage() {
    return new AbstractRMMPage(this.webDriverCustom);
  }

  @SuppressWarnings("javadoc")
  public RMMModelElementPage getRMMModelElementPage() {
    return new RMMModelElementPage(this.webDriverCustom);
  }
}