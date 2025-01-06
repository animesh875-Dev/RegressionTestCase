/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author GUX2KOR
 *
 */
public class TS_39830_checkIfAllPlanViewsAreWorkingFine extends AbstractFrameworkTest{

  /**
   * @throws Throwable
   */
  @Test
  public void tcs_39830_checkIfAllPlanViewsAreWorkingFine() throws Throwable {
    
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("PROJECT_AREA");
    String planName = this.testDataRule.getConfigData("PLAN_NAME");
    String viewas = this.testDataRule.getConfigData("VIEW_AS");
    String WIcount = this.testDataRule.getConfigData("WI_COUNT");
    String WI = this.testDataRule.getConfigData("WI");
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");

    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + " : project area opened successfully.");

    // Select plans from Work Items drop down menu.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.PLANS.toString());
    Reporter.logPass(CCMMenus.PLANS.toString() + " : menu opened successfully.");

    // click on All Plans sub menu.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu("All Plans");
    Reporter.logPass("All Plans" + " : sub menu opened successfully.");

    // search plan phase.
    getJazzPageFactory().getCCMPlanPage().searchBox(planName);
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    Reporter.logPass(planName + " : plan phase searched successfully.");

    // click on welcome to work items sub menu.
    getJazzPageFactory().getCCMPlanPage().clickOnPlanPhase(planName);
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5);
    Reporter.logPass(planName + " : Plan phase opened successfully.");

    // select plan view as from drop down.
    getJazzPageFactory().getCCMPlanPage().viewAs(viewas);
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5);
    Reporter.logPass("Plan view as : " + viewas + " selected successfully.");
    
    // verify the view is loaded completely or not
    TestAcceptanceMessage Ta = getJazzPageFactory().getCCMPlanPageVerification().verifyViewLoad();
    if(Ta.getState().equals("FAILED")) {
      throw new WebAutomationException(Ta.getMessage());
    } 
    Reporter.logPass(Ta.getMessage());
    
    // verify the WI in plan view
    Ta = getJazzPageFactory().getCCMPlanPageVerification().verifyWIUnderView(WIcount, WI);
    if(Ta.getState().equals("FAILED")) {
      throw new WebAutomationException(Ta.getMessage());
    }
    Reporter.logPass(Ta.getMessage());
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5);
    
  }
}
