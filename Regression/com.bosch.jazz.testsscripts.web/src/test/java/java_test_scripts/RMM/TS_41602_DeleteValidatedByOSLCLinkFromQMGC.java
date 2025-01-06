/*
 * Copyright (c) ETAS GmbH 2023. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertNotNull;

import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.pagemodel.rmm.RMMConstants;
import com.bosch.jazz.automation.web.pagemodel.rmm.RMMModelElementPage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 *
 */
public class TS_41602_DeleteValidatedByOSLCLinkFromQMGC extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   */
  @Test
  public void ts_41602_deleteValidatedByOSLCLinkFromQMGC() throws InvalidKeyException {
    boolean isLinkPresent = false;

    String qmServerurl = this.testDataRule.getConfigData("QM_SERVER_URL");
    String gcServerurl = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RMM_QM_PROJECT_AREA");
    String elementResourceId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamId = this.testDataRule.getConfigData("GC_STREAM_ID");
    String testcaseID = this.testDataRule.getConfigData("TESTCASE_ID");
    String linkType = this.testDataRule.getConfigData("LINK_TYPE");
    String qmStreamID = this.testDataRule.getConfigData("QM_STREAM_ID");
    String savebutton = this.testDataRule.getConfigData("SAVE_BUTTON");


    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), qmServerurl);
    Reporter.logPass(getUserId() + " user logged in to the " + qmServerurl + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    RMMModelElementPage rmm = getJazzPageFactory().getRMMModelElementPage();
    assertNotNull(rmm);

    String element_URI =
        rmm.getRepositoryURLForQMGC(testcaseID, qmServerurl, projectArea, qmStreamID, gcServerurl, streamId);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().navigateToURL(element_URI);

    getJazzPageFactory().getAbstractRMMPage().waitForSecs(10);

    Reporter.logPass("Click Architecture Element Links");
    getJazzPageFactory().getAbstractRMMPage().clickElementwithDynamicValue(RMMConstants.ELEMENT_WITH_ANCHOR_TAG_TITLE,
        linkType);

    getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();
    isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isLinkVisibleWithHref(elementResourceId);
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