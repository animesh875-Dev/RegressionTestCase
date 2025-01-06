/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertTrue;

import java.security.InvalidKeyException;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.pagemodel.rmm.RMMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author PZP1KOR
 */
public class TS_41402_AddOSLCLinkOptionPresence extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   */
  @Test
  public void ts_41402_addOSLCLinkPresense() throws InvalidKeyException {
    String amServerURL = this.testDataRule.getConfigData("AM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String modelElementID = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamID = this.testDataRule.getConfigData("AM_STREAM_ID");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().SSOCheckloginWithGivenPassword(getUserId(), getUserPassword(), amServerURL);
    Reporter.logPass(getUserId() + " user logged in to the " + amServerURL + " repository successfully.");

    // open Element in RMM
    String elementURL = getJazzPageFactory().getAbstractRMMPage().getRepositoryURLForRMMNonGC(modelElementID,
        amServerURL, streamID, projectArea);
    getJazzPageFactory().getAbstractRMMPage().open(elementURL, projectArea, RMMConstants.ADDITIONAL_PARAMS);
    Reporter.logPass("Navigated to Project Area Successfully");


    // scroll to perticular Element
    getJazzPageFactory().getAbstractRMMPage().scrollViewToElement(RMMConstants.RMM_ADD_OSLC_LINK);

    // click on quick Navigation
    WebElement element =
        getJazzPageFactory().getAbstractRMMPage().getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK);

    Reporter.logPass("Is ADD LINK option present?: " + (element != null));

    assertTrue("ADD LINK option is present", (element != null));
  }
}
