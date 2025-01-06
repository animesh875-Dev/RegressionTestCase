/*
 * Copyright (c) ETAS GmbH 2023. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertNotNull;

import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.pagemodel.rmm.RMMModelElementPage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 *
 */
public class TS_41472_DeleteSatisfiesOSLCLinkFromAMGC extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   */
  @Test
  public void ts_41472_deleteSatisfiesOSLCLinkFromAMGC() throws InvalidKeyException {

    String amServerurl = this.testDataRule.getConfigData("AM_SERVER_URL");
    String gcServerurl = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String elementId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamId = this.testDataRule.getConfigData("GC_STREAM_ID");
    String workItemID = this.testDataRule.getConfigData("WORKITEM_ID");
    String requirementID = this.testDataRule.getConfigData("REQUIREMENT_ID");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().SSOCheckloginWithGivenPassword(getUserId(), getUserPassword(), amServerurl);
    Reporter.logPass(getUserId() + " user logged in to the " + amServerurl + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    RMMModelElementPage rmm = getJazzPageFactory().getRMMModelElementPage();
    assertNotNull(rmm);

    String element_URI = rmm.getRepositoryURLForRMMGC(elementId, amServerurl, streamId, gcServerurl, projectArea);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().navigateToURL(element_URI);

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
