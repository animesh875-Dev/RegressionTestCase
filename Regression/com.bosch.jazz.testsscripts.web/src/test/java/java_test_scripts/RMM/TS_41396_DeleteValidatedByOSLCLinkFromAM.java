/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.pagemodel.rmm.RMMConstants;
import com.bosch.jazz.automation.web.pagemodel.rmm.RMMModelElementPage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author HLB1KOR
 */
public class TS_41396_DeleteValidatedByOSLCLinkFromAM extends AbstractFrameworkTest {

  /**
   * @throws Throwable
   */
  @SuppressWarnings("javadoc")
  @Test
  public void ts_41396_deleteValidatedByOSLCLinkFromAM() throws Throwable {
    String serverurl = this.testDataRule.getConfigData("AM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String elementId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamId = this.testDataRule.getConfigData("AM_STREAM_ID");
    String testCaseID = this.testDataRule.getConfigData("TESTCASE_ID");

    RMMModelElementPage rmm = getJazzPageFactory().getRMMModelElementPage();
    assertNotNull(rmm);

    String element_URI = rmm.getRepositoryURLForRMMNonGC(elementId, serverurl, streamId, projectArea);

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().SSOCheckloginWithGivenPassword(getUserId(), getUserPassword(), element_URI);
    Reporter.logPass(getUserId() + " user logged in to the " + element_URI + " successfully.");

    getJazzPageFactory().getRMMModelElementPage().waitForSecs(5);

    // Scroll to particular element
    getJazzPageFactory().getAbstractRMMPage().scrollViewToElement(RMMConstants.RMM_ADD_OSLC_LINK);
    getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

    boolean iselementPresent = getJazzPageFactory().getRMMModelElementPage().isOSLCLinkVisibleinWeb(testCaseID);
    if (iselementPresent) {
      // Remove ccm link
      getJazzPageFactory().getRMMModelElementPage().deleteRMMLinkbyId(testCaseID);

      getJazzPageFactory().getRMMModelElementPage().waitForSecs(5);

      // Refresh RMM page
      getJazzPageFactory().getAbstractRMMPage().refresh();

      // Verify CCM links are deleted or not
      Assert.assertFalse(getJazzPageFactory().getRMMModelElementPage().isOSLCLinkVisibleinWeb(testCaseID));
      Reporter.logPass(testCaseID + ": QM link is deleted succesfully");
    }
    else {

      Reporter.logFailure(testCaseID + ": QM link does not present in the Architecture element");
      Assert.assertTrue(iselementPresent);
    }
  }
}
