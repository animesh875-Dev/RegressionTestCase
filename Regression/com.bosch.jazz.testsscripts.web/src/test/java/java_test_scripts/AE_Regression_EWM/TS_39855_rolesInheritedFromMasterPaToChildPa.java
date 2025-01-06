/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author NCY3HC
 *
 */
public class TS_39855_rolesInheritedFromMasterPaToChildPa extends AbstractFrameworkTest {

  /**
   * Verify test case creation, input summary, select Filed Against and Save 
   * should be reflect immediately 
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39855_rolesInheritedFromMasterPaToChildPa() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_PROJECT_AREA");
    String projectTemplateName= this.testDataRule.getConfigData("PROJECT_TEMPLATE_NAME");
    String operationName = this.testDataRule.getConfigData("OPERATION_NAME");
    String permissionListInOrder = this.testDataRule.getConfigData("PERMISSION_LIST_INORDER");    
    String operationTypeSortInOrder = this.testDataRule.getConfigData("OPERATION_TYPE_INORDER");
    String roleName = this.testDataRule.getConfigData("ROLE_NAME");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    

    // Click on 'Administration' icon.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSettingsMenu("Administration");
    Reporter.logPass("Clicked on gear icon at the top right corner");
    // Open 'Manage This Project Area' option.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSettingsSubMenu("Manage This Project Area");
    Reporter.logPass("Selected `Manage This Project Area` option");
    //Select 'Overview' tab
    getJazzPageFactory().getManageProjectAreaPage().tabSection("Overview");
    Reporter.logPass("Selected `Overview` tab");
    //Get and check if the Process sharing is set to master 
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    assertEquals( getJazzPageFactory().getManageProjectAreaPage().getProjectAreainProcessSharing(),projectTemplateName );
    Reporter.logPass( "Checked if the Process sharing is set to master");
    //Select permission tab
    getJazzPageFactory().getManageProjectAreaPage().tabSection("Permissions");
    Reporter.logPass("Selected `Permission` tab");
    //Search permissions which were inherited from master
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getManageProjectAreaPage().setValueIntoSearchBox(operationName);
    Reporter.logPass( "Inputted value to searchbox and entered");
    //Navigate to view on Permission
    Map<String,String> param = new LinkedHashMap();
    param.put("PERMISSION_SORT_IN_ORDER",permissionListInOrder);
    param.put("OPERATION_TYPE_SORT_IN_ORDER",operationTypeSortInOrder);
    param.put("ROLE_NAME",roleName);
    assertTrue(
        getJazzPageFactory().getManageProjectAreaPage().verifyIsPermissionShowByRole(param)
        );
    Reporter.logInfo("Able to view permissions shown by role which inherited from master");
    
  }
}
