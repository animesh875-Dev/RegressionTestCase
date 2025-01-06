/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author GUX2KOR
 *
 */
public class TS_39827_openSocialGadgetWidgetsShouldWork extends AbstractFrameworkTest{

  /**
   * @throws Throwable
   */
  @Test
  public void tcs_39827_openSocialGadgetWidgetsShouldWork() throws Throwable {
    
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_PROJECT_AREA");
    String widgetUrl = this.testDataRule.getConfigData("WIDGET_URL");
    String widgetTitle = this.testDataRule.getConfigData("WIDGET_TITLE");
    
    // Login to the sever
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(10);
    
    // Select the Project Area
    getJazzPageFactory().getCCMAllProjectsPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(10);
    
    // Add widget in minidashboard using widgetUrl
    getJazzPageFactory().getCCMProjectAreaDashboardPage().addOpenSocialGadget(widgetUrl);
    Reporter.logPass( widgetTitle + " is added successfully using Add OpenSocial Gadget option in minidashboard");    
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(10);
    
    // Verification of added widget
    assertTrue(getJazzPageFactory().getCCMProjectAreaDashboardPage().isWidgetSavedInMiniDashboard(widgetTitle));
    Reporter.logPass( "Addition of " + widgetTitle + " widget is verified successfully.\nACTUAL RESULT : Widget added successfully.\nEXPECTED RESULT : Widget added successfully.");    
    
    // Teardown
    Reporter.logPass(" ********** Tear Down started. ********** ");
    
    // Remove widget from minidashboard
    getJazzPageFactory().getCCMProjectAreaDashboardPage().refresh();
    getJazzPageFactory().getCCMProjectAreaDashboardPage().removeOpenSocialGadget(widgetTitle);
//    getJazzPageFactory().getCCMProjectAreaDashboardPage().removeOpenSocialGadget(widgetTitle);
    Reporter.logPass(widgetTitle + ": widget is removed successfully.");
//    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(10);
    
    // Verifying the removal of widget 
    assertFalse(getJazzPageFactory().getCCMProjectAreaDashboardPage().isWidgetSavedInMiniDashboard(widgetTitle));
    Reporter.logPass( "Removal of " + widgetTitle + " widget is verified successfully.");
    
    Reporter.logPass(" ********** Tear Down end. ********** ");
    
  }
}
