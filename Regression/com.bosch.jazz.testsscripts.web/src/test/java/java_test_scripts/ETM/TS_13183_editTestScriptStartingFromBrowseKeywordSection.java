/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ETM;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMMainMenus;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMSectionMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBO1KOR
 *
 */
public class TS_13183_editTestScriptStartingFromBrowseKeywordSection extends AbstractFrameworkTest {
  /**
   * This test case Browse KeyWords,search for the test script inside Keywords. Create new manual step in the test script,
   * delete the added step and validate new step is added and also deleted from the test script.
   * 
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_13183_editTestScriptStartingFromBrowseKeywordSection() throws Throwable {
    
    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String keyWordId= this.testDataRule.getConfigData("KEYWORD_ID");
    String testScriptId=this.testDataRule.getConfigData("TEST_SCRIPT_ID");
    String descrContent=this.testDataRule.getConfigData("DESCRIPTION_CONTENT");
    String exrContent=this.testDataRule.getConfigData("EXPECTED_RESULT_CONTENT");
    
    //Log in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    //Select the project area.
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);
    //Click on CONSTRUCTION tab
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.CONSTRUCTION.toString());
    Reporter.logPass("Clicked on construction tab");
    //Click on browse Test Scripts.
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Keywords");
    Reporter.logPass("Clicked on browse keywords");
    //Click on slide down.
    getJazzPageFactory().getRQMConstructionPage().selectSlideDown();
    //Click on existing test script.
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Keywords");
    additionalParams.put("artifactID", keyWordId);
    getJazzPageFactory().getRQMConstructionPage().filterRqmArtifacts(additionalParams);
    Reporter.logPass(keyWordId + " opened successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // Open Test Scripts in Keywords.
    getJazzPageFactory().getRQMConstructionPage().openRQMSection(RQMSectionMenus.TEST_SCRIPTS.toString());
    Reporter.logPass(RQMSectionMenus.TEST_SCRIPTS.toString() + " opened successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    //Click on the test script inside keywords.
    getJazzPageFactory().getRQMConstructionPage().clickOnTestScriptId(testScriptId);
    Reporter.logPass(testScriptId + " opened successfully");
    //Open Manual Steps in Test script.
    getJazzPageFactory().getRQMConstructionPage().openRQMSection(RQMSectionMenus.MANUAL_STEPS.toString());
    Reporter.logPass(RQMSectionMenus.MANUAL_STEPS.toString() + " opened successfully");
    //Insert new empty step in the test script.
    getJazzPageFactory().getRQMConstructionPage().createNewStep("Insert New Step Before","Create Empty Step");
    Reporter.logPass("New empty step added in the test script");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    additionalParams.put("description", descrContent);
    additionalParams.put("descValue", "Step  1");
    additionalParams.put("expecResultValue", "Step  1 Expected Results");
    additionalParams.put("expectedResult", exrContent);
    //Add step value in the test script.
    getJazzPageFactory().getRQMConstructionPage().addStepValuesInTestScript(additionalParams);
    Reporter.logPass("Description and Expected Result added in the test script");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    //Click on Save button.
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Save");
    Reporter.logPass("Test script is saved successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    //Validate 'Description' is added in the test script.
    Assert.assertTrue(descrContent.contains(getJazzPageFactory().getRQMConstructionPage().getContentOfStep("1", "Description"))); 
    //Validate 'Expected Result' is added in the test script.
    Assert.assertTrue(exrContent.contains(getJazzPageFactory().getRQMConstructionPage().getContentOfStep("1", "Expected Results")));
    //Delete the added step in the manual test script.
    getJazzPageFactory().getRQMConstructionPage().deleteStep("2");
    Reporter.logPass("Added manual step in the test script is deleted");
    //Click on Save button
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Save");
    Reporter.logPass("Test script is saved successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);
    //Validate 'Description' is deleted from the test script.
    Assert.assertFalse(descrContent.contains(getJazzPageFactory().getRQMConstructionPage().getContentOfStep("1", "Description"))); 
    //Validate 'Expected Result' is deleted from the test script.
    Assert.assertFalse(exrContent.contains(getJazzPageFactory().getRQMConstructionPage().getContentOfStep("1", "Expected Results")));
    
  }

}
