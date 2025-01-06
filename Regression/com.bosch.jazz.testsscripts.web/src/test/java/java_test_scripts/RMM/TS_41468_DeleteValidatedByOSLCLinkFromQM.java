/*
 * Copyright (c) ETAS GmbH 2023. All rights reserved.
 */
package java_test_scripts.RMM;

import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.pagemodel.rmm.RMMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 *
 */
public class TS_41468_DeleteValidatedByOSLCLinkFromQM extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   */
  @Test
  public void ts_41468_deleteValidatedByOSLCLinkFromQM() throws InvalidKeyException {

    String qmServerURL = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RMM_QM_PROJECT_AREA");
    String testcaseID = this.testDataRule.getConfigData("TESTCASE_ID");
    String elementResourceId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String linkType = this.testDataRule.getConfigData("LINK_TYPE");
    String savebutton = this.testDataRule.getConfigData("SAVE_BUTTON");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), qmServerURL);
    Reporter.logPass(getUserId() + " user logged in to the " + qmServerURL + " repository successfully.");

    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    getJazzPageFactory().getAbstractRMMPage().quickSearchInRM(testcaseID);
    Reporter.logPass(testcaseID + ": Testcase opened successfully.");

    // Open searched artifact
    getJazzPageFactory().getAbstractRMMPage()
        .clickElementwithDynamicValue(RMMConstants.QM_SELECT_ARCHITECTURE_ELEMENT_LINK, testcaseID);
    Reporter.logPass("Open '" + testcaseID + "' module found in the quick search");

    getJazzPageFactory().getAbstractRMMPage().waitForSecs(20);

    Reporter.logPass("Click Architecture Element Links");
    getJazzPageFactory().getAbstractRMMPage().clickElementwithDynamicValue(RMMConstants.ELEMENT_WITH_ANCHOR_TAG_TITLE,
        linkType);

    getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

    boolean isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isLinkVisibleWithHref(elementResourceId);
    Reporter.logPass("Check if OSLC Link Present to Delete?: " + isLinkPresent);

    if (isLinkPresent) {
      getJazzPageFactory().getAbstractRMMPage().waitForSecs(5);
      getJazzPageFactory().getAbstractRMMPage().deleteAMLinkFromQM(elementResourceId);

      Reporter.logPass("Click on Save Button");

      getJazzPageFactory().getAbstractRMMPage().clickOnButtons(savebutton);
      getJazzPageFactory().getAbstractRMMPage().waitForSecs(15);

      Reporter.logPass("Refresh Page");

      // Refresh CCM page
      getJazzPageFactory().getAbstractRMMPage().refresh();

      Reporter.logPass("Click Architecture Element Links");
      getJazzPageFactory().getAbstractRMMPage().clickElementwithDynamicValue(RMMConstants.ELEMENT_WITH_ANCHOR_TAG_TITLE,
          linkType);

      // Verify CCM links are deleted or not
      isLinkPresent = getJazzPageFactory().getRMMModelElementPage().isLinkVisibleWithHref(elementResourceId);
      Reporter.logPass("Check if OSLC Link Present after Deletion: " + isLinkPresent);

      Assert.assertFalse(isLinkPresent);
      Reporter.logPass(elementResourceId + " : Link is deleted succesfully");
    }
    else {
      Reporter.logFailure(elementResourceId + " : Link does not present in the Architecture element");
      Assert.assertTrue(isLinkPresent);
    }
  }

}
