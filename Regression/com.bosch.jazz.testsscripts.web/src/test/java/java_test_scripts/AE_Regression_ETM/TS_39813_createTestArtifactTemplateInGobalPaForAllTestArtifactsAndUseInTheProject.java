/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_ETM;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author GUX2KOR
 *
 */
public class TS_39813_createTestArtifactTemplateInGobalPaForAllTestArtifactsAndUseInTheProject extends AbstractFrameworkTest{

  /**
   * @throws Throwable
   */
  @Test
  public void tcs_39813_createTestArtifactTemplateInGobalPaForAllTestArtifactsAndUseInTheProject() throws Throwable {
    
    String username = this.testDataRule.getConfigData("USERNAME");
    String password = this.testDataRule.getConfigData("PASSWORD");
    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String templateType = this.testDataRule.getConfigData("TEMPLATE_TYPE");
    String templateName = this.testDataRule.getConfigData("TEMPLATE_NAME");
    String templateDesc = this.testDataRule.getConfigData("TEMPLATE_DESC");
    String section = this.testDataRule.getConfigData("SECTION");
    String sections = this.testDataRule.getConfigData("SECTIONS");
    sections = sections + ",Manage Sections";
    String[] finalSections = sections.split(",");
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(username, password, url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(10);
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    // Opening manage Artifact Templates Page in Administration
    getJazzPageFactory().getRQMConstructionPage().openSettingsMenu("Administration");
    getJazzPageFactory().getRQMConstructionPage().openSettingsSubMenu("Manage Artifact Templates");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(10);
    
    // Creating Test Plan Template
    getJazzPageFactory().getRQMManageArtifactTemplates().selectValueFromCreateTemplate(templateType);
    String finalTemplateName = getJazzPageFactory().getRQMManageArtifactTemplates().setRQMArtifactTemplateName(templateType, templateName);
    getJazzPageFactory().getRQMManageArtifactTemplates().setRQMArtifactTemplateDescription(templateType, templateDesc);
    getJazzPageFactory().getRQMManageArtifactTemplates().selectAvaiableSectionsValue(templateType, section);
    getJazzPageFactory().getRQMManageArtifactTemplates().clickOnDialogToolTipButton(templateType, "Add");
    getJazzPageFactory().getRQMManageArtifactTemplates().clickOnDialogButton(templateType, "OK");
    Reporter.logPass("Test Plan template : " + finalTemplateName + " is created successfully.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(10);
    
    // Creating Test Plan using newly created Test Plan Template
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle("Planning");
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Create", "Test Plan");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("creatingUsingTemplateName", "Creating using template:");
    additionalParams.put("testArtifactTemplateValue", finalTemplateName);
    getJazzPageFactory().getRQMConstructionPage().chooseTemplateFromSummaryForTestPlan(additionalParams);
    Reporter.logPass(finalTemplateName + " is selected successfully in create using template variable of Summary section.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(10);
    
    // Verify that only selected streams are visible
    List<String> sectionsList = getJazzPageFactory().getRQMConstructionPage().getListSectionsMenuItem();
    TestAcceptanceMessage Ta = this.getJazzPageFactory().getRQMConstructionPageVerification().verifyChooseTemplateFromSummaryForTestPlan(finalSections, additionalParams, sectionsList);
    if(Ta.getState().equals("FAILED")) {
      throw new WebAutomationException(Ta.getMessage());
    }
    Reporter.logPass(Ta.getMessage());
    
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    
  }
}
