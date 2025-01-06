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
public class TS_41397_DeleteSatisfiesOSLCLinkFromAM extends AbstractFrameworkTest {

  /**
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void ts_41397_deleteSatisfiesOSLCLinkFromAM() throws Throwable {
    String serverurl = this.testDataRule.getConfigData("AM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String elementId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamId = this.testDataRule.getConfigData("AM_STREAM_ID");
    String requirementID = this.testDataRule.getConfigData("REQUIREMENT_ID");
    String workItemID = this.testDataRule.getConfigData("WORKITEM_ID");

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

    boolean iselementPresent = getJazzPageFactory().getRMMModelElementPage().isOSLCLinkVisibleinWeb(requirementID);
    if (iselementPresent) {
      // Remove ccm link
      getJazzPageFactory().getRMMModelElementPage().deleteRMMToDNGLinkbyId(requirementID, workItemID);

      getJazzPageFactory().getAbstractRMMPage().waitForSecs(15);

      // Refresh RMM page
      getJazzPageFactory().getAbstractRMMPage().refresh();

      // Verify CCM links are deleted or not
      Assert.assertFalse(getJazzPageFactory().getRMMModelElementPage().isOSLCLinkVisibleinWeb(requirementID));
      Reporter.logPass(requirementID + " : Requirement link is deleted succesfully");
    }
    else {

      Reporter.logFailure(requirementID + " : Requirement link does not present in the Architecture element");
      Assert.assertTrue(iselementPresent);
    }

  }

}
