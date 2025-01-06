/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author NCY3HC
 *
 */
public class TS_40536_toModifyComponentProperties extends AbstractFrameworkTest {
  /**
   * This test case describes about modifying component properties.
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_40536_toModifyComponentProperties() throws Throwable {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_GC_PROJECT_AREA");
    String globalArea = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    String  attributeName = this.testDataRule.getConfigData("ATTRIBUTE_NAME")+ DateUtil.getCurrentDateAndTime();
    String dataType = this.testDataRule.getConfigData("DATA_TYPE");
    String numberValue = this.testDataRule.getConfigData("NUMBER_OF_VALUES");
    String initialValue = this.testDataRule.getConfigData("INITIAL_VALUE");
    String uriValue = this.testDataRule.getConfigData("URI");

    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMDashBoardPage().selectProjectAreaAndGlobalConfiguration(projectArea, globalArea,
        componentName, streamName);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    // Click on Administration menu
    getJazzPageFactory().getRMDashBoardPage().openSettingsMenu("Administration");
    Reporter.logPass("Click on Admistration menu");
    // Click on Manage Components and Configurations menu
    getJazzPageFactory().getRMDashBoardPage().openSettingsSubMenu("Manage Component Properties");
    Reporter.logPass("Click on Manage Component Properties successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Click on 'Artifact Attributes' tab
    getJazzPageFactory().getRMManageCompProperties().clickOnTab("Artifact Attributes");
    Reporter.logPass("Click on 'Artifact Attributes' tab successfully");
    getJazzPageFactory().getRMManageCompProperties().waitForSecs(5);
    //Click on 'New Attribute...' button
    getJazzPageFactory().getRMManageCompProperties().clickOnButtons("New Attribute...");
    Reporter.logPass("Click on 'New Attribute...' button successfully");
    getJazzPageFactory().getRMManageCompProperties().waitForSecs(5);
    //Input name for new artifact attribute
    getJazzPageFactory().getRMManageCompProperties().inputNameForComponentProperties(attributeName);
    Reporter.logPass("Input name successfully");
    getJazzPageFactory().getRMManageCompProperties().waitForSecs(5);
    //Input data to create new  artifact attribute
    Map<String, String> param = new LinkedHashMap();
    param.put("DATA_TYPE",dataType);
    param.put("NUMBER_OF_VALUES",numberValue);
    param.put("INITIAL_VALUE",initialValue);
    param.put("URI",uriValue);
    getJazzPageFactory().getRMManageCompProperties().createNewArtifactAttributeDataType(param);
    Reporter.logPass("Input data and click on Save button to create new Artifact Attribute.");
    getJazzPageFactory().getRMManageCompProperties().waitForSecs(5);
    
    //Verify new Artifact Attribute is displayed
    assertTrue(
    getJazzPageFactory().getRMManageCompProperties().isArtifactAttributeDisplayed(attributeName)
    );
    Reporter.logPass("New artifact attribute is created successfully.");
    getJazzPageFactory().getRMManageCompProperties().waitForSecs(5);
    
    //Delete new created attribute
    getJazzPageFactory().getRMManageCompProperties().deleteArtifactAttribute(attributeName);
    Reporter.logPass("Delete new artifact attribute");
    getJazzPageFactory().getRMManageCompProperties().waitForSecs(60);
    
    //Verify new Artifact Attribute is not displayed
    assertFalse(
    getJazzPageFactory().getRMManageCompProperties().isArtifactAttributeDisplayed(attributeName)
    );
    Reporter.logPass("New artifact attribute is deleted successfully.");
    getJazzPageFactory().getRMManageCompProperties().waitForSecs(5);
  }

}
