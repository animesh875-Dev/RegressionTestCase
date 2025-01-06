/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author YUU3HC
 */
public class TS_39862_loginToTheCcmWebAccessThePa extends AbstractFrameworkTest {

  /**
   * This test case for login to CCM web and access Project Area available.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39862_loginToTheCcmWebAccessThePa() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_PROJECT_AREA");

    String viewas = this.testDataRule.getConfigData("VIEW_AS");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    // Select plans from Work Items drop down menu.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.PLANS.toString());
    Reporter.logPass(CCMMenus.PLANS.toString() + " : menu opened successfully.");
    // click on All Plans sub menu.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu("All Plans");
    Reporter.logPass("All Plans" + " : sub menu opened successfully.");

    // select plan view as from drop down.
    getJazzPageFactory().getCCMPlanPage().viewAs(viewas);
    Reporter.logPass("Plan view as : " + viewas + " selected successfully.");

  }
}
