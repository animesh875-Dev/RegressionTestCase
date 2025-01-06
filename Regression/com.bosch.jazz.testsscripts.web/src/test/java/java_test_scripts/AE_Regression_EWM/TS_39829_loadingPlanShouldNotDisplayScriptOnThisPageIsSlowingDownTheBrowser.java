/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author YUU3HC
 */
public class TS_39829_loadingPlanShouldNotDisplayScriptOnThisPageIsSlowingDownTheBrowser extends AbstractFrameworkTest {

  /**
   * This test case for login to CCM web and access Project Area available.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39829_loadingPlanShouldNotDisplayScriptOnThisPageIsSlowingDownTheBrowser() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_PROJECT_AREA");

    String planPhase = this.testDataRule.getConfigData("PLAN_PHASE");
    String viewAs = this.testDataRule.getConfigData("VIEW_AS");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    // Search for plans menu dropdown.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu("Plans");
    Reporter.logPass("Clicked on menu: Plans");
    // click on "All Plans" to plans sub menu.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu("All Plans");
    Reporter.logPass("Clicked on submenu: All Plans");

    // Search Plan
    getJazzPageFactory().getCCMPlanPage().searchBox(planPhase);
    Reporter.logPass("Searched plan: " + planPhase);
    Assert.assertTrue(isElementVisible(planPhase));

    // Click on respective plan name
    getJazzPageFactory().getCCMPlanPage().clickOnPlanPhase(planPhase);
    Reporter.logPass("Clicked on plan: " + planPhase);
    // Select plan view
    getJazzPageFactory().getCCMPlanPage().viewAs(viewAs);
    Reporter.logPass("View As selected: " + viewAs);

    // Handle Alert present - get alert text
    if (ExpectedConditionsCustom.isAlertPresent(driver, Duration.ofSeconds(5))) {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      Reporter.logFailure("Alert displayed: " + alertText);
    }
    else {
      Reporter.logPass("Test plan loaded sucessfully");
    }
  }

  @SuppressWarnings("javadoc")
  public boolean isElementVisible(String name) {
    return driver.findElement(By.xpath(("//*[text()='" + name + "']"))).isDisplayed();
  }
}
