/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMSubMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author YUU3HC
 *
 */
public class TS_39824_fulltextQuerySearchShouldGiveCorrectData extends AbstractFrameworkTest {
  /**
   * This test case for verify full text query search 
   * should return correct data contains text
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39824_fulltextQuerySearchShouldGiveCorrectData() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_PROJECT_AREA");
    String textInput = this.testDataRule.getConfigData("TEXT_INPUT");
    String conditionName = this.testDataRule.getConfigData("CONDITION_NAME");
    String conditionOption = this.testDataRule.getConfigData("CONDITION_OPTION");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    //Click on Work Item menu bar
    // Select "Create Query" from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    Reporter.logPass("Clicked on work items sub menu");
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.CREATE_QUERY.toString());
    Reporter.logPass(CCMSubMenus.CREATE_QUERY.toString() + " Selected from Work Items drop down.");
    // page refresh
    getJazzPageFactory().getCCMProjectAreaDashboardPage().refresh();
    // Click on Add Condition button to add a condition.
    getJazzPageFactory().getCCMQueryPage().clickAddCondition();
    Reporter.logPass("Add Condition widget opened to select a condition");
    // Select attribute and specify the value.
    getJazzPageFactory().getCCMQueryPage().filterText(conditionName);
    Reporter.logPass("Clicked on Type Filter Text and select the condition: " + conditionName);
    // Select the attribute from the list box
    getJazzPageFactory().getCCMQueryPage().selectItemInListBox(conditionName);
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Condition selected from the list: " + conditionName);
    // Select conditions option
    getJazzPageFactory().getCCMQueryPage().selectConditionType(conditionName, conditionOption);
    Reporter.logPass("Conditions option selected as: " + conditionName + ": " + conditionOption);
    // Set text to filter box
    getJazzPageFactory().getCCMQueryPage().setAttributeTextBox(conditionName, textInput);
    Reporter.logPass("Full Text value entered: " + textInput);
    // Click on Run button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass("Clicked on Run button. Query is running");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    // Validate Text in the searched query.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Summary").contains(textInput));
    Reporter.logPass("Displayed all query results contains: " + textInput);
  }
}
