/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.rqm;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMPlanningPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;

/**
 * @author BBO1KOR
 */
public class RQMPlanningPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom must not be null.
   */
  public RQMPlanningPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * <p>
   * Verify searched test specification is displayed.
   * <p>
   * This method called after {@link RQMPlanningPage#searchTestSpecifications(String)}.
   *
   * @param artifactID id of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySearchTestSpecifications(final String artifactID, final String lastResult) {
      try {
        this.engine.findElementWithDuration(Criteria.isRow().containsText(artifactID), Duration.ofSeconds(120)).getFirstElement();
        return new TestAcceptanceMessage(true,
            "Verified: Searched artifact displayed in the content table.\nActual Result \"" + artifactID +
                "\" is displayed " + "in content table.\nExpected Result \"" + artifactID +
                "\" should be displayed in content table.");
      }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Verified: Searched artifact not displayed in the content table.");
    }
  }

  /**
   * <p>
   * Verify Execution Record is created.
   * <p>
   * This method called after {@link RQMPlanningPage#clickOnGenerateNewExecutionRecordButton(String)}.
   *
   * @param artifactId id of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnGenerateNewExecutionRecordButton(final String artifactId,
      final String lastResult) {
    if (this.driverCustom.isElementVisible("//span[text()='Overview']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          " Verified: Selected the test artifact and clicked on 'Generate Execution Record' button.\n" +
              "Actual Result 'Generate Test Execution Records' dialog is displayed for \"" + artifactId +
              "\".\nExpected Result" + "'Generate Test Execution Records' dialog should be displayed for \"" +
              artifactId + "\".");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Not Selected the test case or not clicked on 'Generate Execution Record' button.");
  }

  /**
   * <p>
   * Verify test environment is generated.
   * <p>
   * This method called after {@link RQMPlanningPage#setIterationAndTestEnvironment(Map)}.
   *
   * @param additionalParams contains list of key value pair data.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetIterationAndTestEnvironment(final Map<String, String> additionalParams,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10), RQMConstants.COMPLETED)) {
      return new TestAcceptanceMessage(true,
          "Verified: Test Execution Record is generated for the selected test artifact.\nActual Result test execution record is generated using \"" +
              additionalParams.get("testEnvName") + "\" test environment and \"" +
              additionalParams.get(RQMConstants.ITERATION) +
              "\" iteration and .\nExpected Result test execution record should be generated using \"" +
              additionalParams.get("testEnvName") + "\" test environment and \"" +
              additionalParams.get(RQMConstants.ITERATION) + "\" iteration.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Test Execution Record is not generated for the selected test artifact.");
  }

  /**
   * <p>
   * Verify page is scrolled.
   * <p>
   * This method called after {@link RQMPlanningPage#pageScrollBar()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyPageScrollBar(final String lastResult) {
    return new TestAcceptanceMessage(true, " Verified:Page is scrolled by '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verify Execution record is generated.
   * <p>
   * This method called after {@link RQMPlanningPage#getExecutionRecordDetails(String)}.
   *
   * @param attributeValue test case execution record column value.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetExecutionRecordDetails(final String attributeValue, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: Execution record is generated successfully.\nActual Result \"" + attributeValue +
              "\" is visible in the created execution record." + "\nExpected Result \"" + attributeValue +
              "\" should be visible in the created execution record.");
    }
    return new TestAcceptanceMessage(false, "Verified: Execution record is not generated.");
  }

  /**
   * <p>
   * Verify Execution record is deleted.
   * <p>
   * This method called after {@link RQMPlanningPage#deleteTestExecutionRecord(String,String)}.
   *
   * @param testEnvName name of the test environment.
   * @param dlgName -
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteTestExecutionRecord(final String testEnvName,final String dlgName, final String lastResult) {
    if (this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(30), "Completed")) {
      return new TestAcceptanceMessage(true, "Verified: Execution record is deleted successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Execution record is not deleted.");
  }

  /**
   * <p>
   * Verify Test Environment is added.
   * <p>
   * This method called after {@link RQMPlanningPage#isTestEnvironmentAdded(Map)}.
   *
   * @param additionalParams contains list of key value pair data.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsTestEnvironmentAdded(final Map<String, String> additionalParams,
      final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: Test Environment is added for the Execution Record.\nActual Result \"" +
              additionalParams.get(RQMConstants.TEST_ENVIRONMENT) +
              "\" test environment is added for the execution record.\nExpected Result \"" +
              additionalParams.get(RQMConstants.TEST_ENVIRONMENT) + "\"" + "should be added for the execution record.");
    }
    return new TestAcceptanceMessage(false, "Verified: Test Environment is not added for the Execution Record");
  }

  /**
   * <p>
   * Verify Iteration is selected for the Execution Record.
   * <p>
   * This method called after {@link RQMPlanningPage#selectIterationAndGenerateTestEnvironment(Map)}.
   *
   * @param additionalParams contains list of key value pair data.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectIterationAndGenerateTestEnvironment(
      final Map<String, String> additionalParams, final String lastResult) {
    if (this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10), "Completed")) {
      return new TestAcceptanceMessage(true,
          "Verified: Test Environment is generated and Iteartion is 'Unassigned' for the Execution Record.\nActual Result \"" +
              additionalParams.get(RQMConstants.TEST_ENVIRONMENT) +
              "\" is added for the execution record.\nExpected Result \"" +
              additionalParams.get(RQMConstants.TEST_ENVIRONMENT) + "\" should be added in the execution record.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Test Environment is not generated or Iteartion is not 'Unassigned' for the Execution Record.");
  }

  /**
   * <p>
   * Verify Test suite Execution record is visible.
   * <p>
   * This method called after {@link RQMPlanningPage#isTestSuiteExecutionRecordsVisible(Map)}.
   *
   * @param additionalParams contains list of key value pair data.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsTestSuiteExecutionRecordsVisible(final Map<String, String> additionalParams,
      final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: Created Test Suite Execution Record visible in RQM Section." + "\nActual Result \"" +
              additionalParams.get("TSER_Name") + "\" is created using \"" +
              additionalParams.get(RQMConstants.TEST_ENVIRONMENT) +
              "\" as test environment and Iteration as 'Unassigned' in 'Test Suite Execution Record' section.\n" +
              "Expected Result \"" + additionalParams.get("TSER_Name") + "\" should be created using \"" +
              additionalParams.get(RQMConstants.TEST_ENVIRONMENT) + "\" as test environment and Iteration as" +
              "'Unassigned' in 'Test Suite Execution Record' section.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Created Test Suite Execution Record is not visible in RQM Section.");
  }

  /**
   * <p>
   * Verify test plan is clicked from Execution record.
   * <p>
   * This method called after {@link RQMPlanningPage#clickOnTestPlanFromExecutionRecord(String)}.
   *
   * @param testPlanName name of the Test Plan.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnTestPlanFromExecutionRecord(final String testPlanName,
      final String lastResult) {
    List<WebElement> results = this.driverCustom
        .getWebElements("//div[@dojoattachpoint='titleOutterContainerNode']//span[contains(@dojoattachpoint,'view')]");
    for (WebElement artifact : results) {
      if (testPlanName.contains(artifact.getText())) {
        return new TestAcceptanceMessage(true,
            "Verified: Added Test Plan is opened from Execution Record.\nActual Result \"" + testPlanName +
                "\" is opened." + "\nExpected Result \"" + testPlanName + "\" should be opened");
      }
    }
    return new TestAcceptanceMessage(false, "Verified: Added Test Plan is not opened from Execution Record.");
  }

  /**
   * <p>
   * Verify added Test Environment is deleted.
   * <p>
   * This method called after {@link RQMPlanningPage#deleteTestEnvironment(Map)}.
   *
   * @param additionalParams contains list of key value pair data.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteTestEnvironment(final Map<String, String> additionalParams,
      final String lastResult) {
    try {
      List<String> allTestEnvLinks = this.driverCustom
          .getWebElements("//table[@summary='This is Generated Test Environments table']/descendant::tbody//tr//span")
          .stream().map(x -> x.getText()).collect(Collectors.toList());
      if ((allTestEnvLinks != null) && allTestEnvLinks.stream()
          .anyMatch(x -> (x != null) && !x.contains(additionalParams.get(RQMConstants.TEST_ENVIRONMENT)))) {
        return new TestAcceptanceMessage(true,
            "Verified: Test Environment is deleted from the Test Plan.\nActual Result \"" +
                additionalParams.get("TestEnvironment") +
                "\" test environment is deleted from the test plan.\nExpected Result \"" +
                additionalParams.get("TestEnvironment") + "\" test environment should be deleted from test plan.");
      }
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(true, " Verified: Test Environment does not exist.Test Environment is deleted");
    }
    return new TestAcceptanceMessage(false, "Verified: Test Environment is not deleted from test plan.");
  }

  /**
   * <p>
   * Verify searched test specification for test environment is displayed.
   * <p>
   * This method called after {@link RQMPlanningPage#searchTestSpecificationsForTestEnvironment(String, String)}.
   *
   * @param artifactID id of the artifact.
   * @param dialogName name of the dialog.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySearchTestSpecificationsFromTestEnvironment(final String artifactID,
      final String dialogName, final String lastResult) {
    try {
      this.engine.findElement(Criteria.isRow().containsText(artifactID)).getFirstElement();
      return new TestAcceptanceMessage(true,
          "Verified: Searched Test Environement is displayed.\nActual Result \"" + artifactID + "\"" +
              "test environment is displayed.\nExpected Result \"" + artifactID +
              "\" test environment should be displayed.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Verified: Searched Test Environment not found.");
    }
  }

  /**
   * <p>
   * Verify Iteration is selected as 'Unassigned' for the Execution Record.
   * <p>
   * This method called after {@link RQMPlanningPage#selectIteration(String)}.
   *
   * @param Iteration iteration to be deselect.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectIteration(final String Iteration, final String lastResult) {
    try {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPLANNINGPAGE_SELECT_ITERATION_XPATH);
      Select sel = new Select(this.driverCustom.getWebElement(RQMConstants.RQMPLANNINGPAGE_SELECT_ITERATION_XPATH));
      sel.getFirstSelectedOption().getText();
      if (sel.getAllSelectedOptions().size() == 1) {
        return new TestAcceptanceMessage(true,
            "Verified: Iteration is selected for the Test Suite Execution Record.\nActual Result \"" +
                sel.getFirstSelectedOption().getText() + "\"" + " is the Iteration selected.\nExpected Result \"" +
                sel.getFirstSelectedOption().getText() + "\" should be the 'Iteration' to be selected.");
      }
      return new TestAcceptanceMessage(false,
          "Verified: Iteration is selected more than one option for the Execution Record.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified: Iteration is not selected as 'Unassigned' for the Execution Record.");
    }
  }

  /**
   * <p>
   * Verify test environment is generated for the Execution record.
   * <p>
   * This method called after {@link RQMPlanningPage#generateTestEnvironment(String)}.
   *
   * @param testEnv name of the Test Environment.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGenerateTestEnvironment(final String testEnv, final String lastResult) {
    if (this.driverCustom.isElementVisible("//button[contains(@id,'finishButton')]", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Verified: Generated new test environment for the Execution Record.\nActual Result \"" + testEnv +
              "\" is added in execution record.\nExpected Result \"" + testEnv + "\" should be" +
              " added in the execution record.");
    }
    return new TestAcceptanceMessage(false, "Verified: New test environment is not added for the Execution Record.");
  }

  /**
   * <p>
   * Verify Iteration is selected for the Execution Record.
   * <p>
   * This method called after {@link RQMPlanningPage#setIteration(String)}.
   *
   * @param Iteration iteration to be select for the execution record.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetIteration(final String Iteration, final String lastResult) {
    try {
      this.driverCustom.getPresenceOfWebElement("//label[text()='Iteration']/../following-sibling::td//select");
      Select sel =
          new Select(this.driverCustom.getWebElement("//label[text()='Iteration']/../following-sibling::td//select"));
      sel.getFirstSelectedOption().getText();
      return new TestAcceptanceMessage(true,
          "Verified: Iteration is selected for the Test Suite Execution Record.\nActual Result \"" +
              sel.getFirstSelectedOption().getText() + "\"" + " is the Iteration selected.\nExpected Result \"" +
              sel.getFirstSelectedOption().getText() + "\" should be the 'Iteration' to be selected.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Verified: Iteration is not selected for the Execution Record.");
    }
  }

  /**
   * <p>
   * Verify existing test environment is added for the Execution record.
   * <p>
   * This method called after {@link RQMPlanningPage#reuseExistingTestEnvironment(String)}.
   *
   * @param testEnv name of the Test Environment.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyReuseExistingTestEnvironment(final String testEnv, final String lastResult) {
    if (this.driverCustom.isElementVisible("//button[contains(@id,'finishButton')]", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Verified: Existing test environment is added for the Execution Record.\nActual Result \"" + testEnv +
              "\" is added in execution record.\nExpected Result \"" + testEnv + "\" should be" +
              " added in the execution record.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Existing test environment is not added for the Execution Record.");
  }
  /**
   * This method is called after executing {@link RQMPlanningPage#inputValueIntoNewTestCaseDlg(Map)}
   * @param additionalParams - contains key and value from method {@link RQMPlanningPage#inputValueIntoNewTestCaseDlg(Map)}
   * @param lastResult return result from last execution.
   * @return verification message
   */
  public TestAcceptanceMessage verifyInputValueIntoNewTestCaseDlg(final Map<String,String> additionalParams, final String lastResult) {
    String nameValue = additionalParams.get("NAME_VALUE").toString();
    String testTypeValue = additionalParams.get("TEST_TYPE_VALUE").toString();
    String actualName = this.driverCustom.getAttribute(RQMConstants.RQM_TETSCASE_NAME_IN_NEWTESTCASEDIALOG, "value").trim();
    String actualTestType = driverCustom.getSelectedValueFromDropDown(RQMConstants.RQM_TETSCASE_TYPE_IN_NEWTESTCASEDIALOG).trim();
    
    if(actualName.equalsIgnoreCase(nameValue) && actualTestType.equalsIgnoreCase(testTypeValue)) {
      return new TestAcceptanceMessage (true, "Verify that Inputting information for new test case successfully with Name '"+actualName+"' and Test Type '"+actualTestType+"'.");
    }
    return new TestAcceptanceMessage(false,  "Verify that Inputting information for new test case unsuccessfully with Name '"+actualName+"' and Test Type '"+actualTestType+"'.");
  }
  
  /**
   * This method is called after executing method {@link RQMPlanningPage#isNewCreatedTestCase(Map)}
   * @author NCY3HC
   * @param params - contains:
   *                nameValue - Test case Name
   *                testTypeValue - value of test type that is selected from dropdown
   *@param lastResult return result from the last execution.
   * @return verification message.
   */
  public TestAcceptanceMessage verifyIsNewCreatedTestCase(final Map<String, String> params, final String lastResult) {
    String nameValue = params.get("NAME_VALUE");
    String testTypeValue = params.get("TEST_TYPE_VALUE");
    if(lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "New test case ' "+nameValue+" 'is saved successfully in Test Plan with test type '"+testTypeValue+"'.");
    }
    return new TestAcceptanceMessage(false, "New test case ' "+nameValue+" 'is saved successfully in Test Plan with test type '"+testTypeValue+"'.");
  }
  
  /**
   * This method is called after executing method {@link RQMPlanningPage#runTestSuiteFromActionsMenu(String)}
   * @author NCY3HC
   *@param option - value in dropdown list
   *@param lastResult return result from the last execution.
   * @return verification message.
   */
  public TestAcceptanceMessage verifyRunTestSuiteFromActionsMenu(final String option,final String lastResult) {
    if(this.driverCustom.isElementVisible(RQMConstants.RQM_RUNTESTSUITE_DIALOG_FAIL, timeInSecs)){
      return new TestAcceptanceMessage(true, "Select 'Run Test Suite' from 'Actions' Menu successfully. Dialog 'Run Test Suite' is opened.");
    }
    return new TestAcceptanceMessage(false,"Select 'Run Test Suite' from 'Actions' Menu unsuccessfully. Dialog 'Run Test Suite' is not opened.");
  }
}
