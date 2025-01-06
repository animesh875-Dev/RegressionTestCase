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
public class TS_41401_QucikNavigationFromDiagram extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   */
  @Test
  public void ts_41401_qickNavigationFromDiagram() throws InvalidKeyException {
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

    // scroll to perticular Element
    getJazzPageFactory().getAbstractRMMPage().scrollViewToElement(RMMConstants.RMM_QUICKNAVIGATION_SCROLL_TO_DIAGRAM);

    // click on quick Navigation
    getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_QUICKNAVIGATION_SCROLL_TO_DIAGRAM);

    getJazzPageFactory().getRMMModelElementPage().waitForSecs(15);

    // get Model Element Text
    WebElement element = getJazzPageFactory().getAbstractRMMPage()
        .getModelElementIfFoundByXpath(RMMConstants.RMM_QUICKNAVIGATION_DIAGRAM_CLICK);

    String diagramLinkText = element.getText();

    // click on quick navigation link appeared
    getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_QUICKNAVIGATION_DIAGRAM_CLICK);

    // Wait for 20s for PageLoad
    getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

    // get Model Element Text
    String pageTitle = getJazzPageFactory().getAbstractRMMPage().getPageTitle();

    assertTrue("Quick Navigation is Working", pageTitle.contains(diagramLinkText));


  }


}
