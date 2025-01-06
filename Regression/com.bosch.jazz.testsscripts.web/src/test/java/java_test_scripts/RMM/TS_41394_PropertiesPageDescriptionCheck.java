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
 * @author RHU8KOR
 */
public class TS_41394_PropertiesPageDescriptionCheck extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   */
  @Test
  public void tcs_41394_Propertiespagecheck() throws InvalidKeyException {


    String amServerURL = this.testDataRule.getConfigData("AM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String modelElementID = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamID = this.testDataRule.getConfigData("AM_STREAM_ID");

//Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().SSOCheckloginWithGivenPassword(getUserId(), getUserPassword(), amServerURL);
    Reporter.logPass(getUserId() + " user logged in to the " + amServerURL + " repository successfully.");

// open Element in RMM
    String elementURL = getJazzPageFactory().getAbstractRMMPage().getRepositoryURLForRMMNonGC(modelElementID,
        amServerURL, streamID, projectArea);
    getJazzPageFactory().getAbstractRMMPage().open(elementURL, projectArea, RMMConstants.ADDITIONAL_PARAMS);

//Scroll to particular element
    getJazzPageFactory().getAbstractRMMPage().scrollViewToElement(RMMConstants.DESCRIPTION_FIELD_XPATH);
    getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

//get field value text
    WebElement field =
        getJazzPageFactory().getAbstractRMMPage().getModelElementIfFoundByXpath(RMMConstants.DESCRIPTION_FIELD_XPATH);
    String fieldtext = field.getText();

    assertTrue("Description field exists", !fieldtext.isEmpty());
    Reporter.logPass("field exists : " + fieldtext);

  }
}
