/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.pagemodel.rmm.RMMConstants;
import com.bosch.jazz.automation.web.pagemodel.rmm.RMMModelElementPage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;


/**
 * @author HLB1KOR
 */
public class TS_40798_tableviewPresenceInModel extends AbstractFrameworkTest {

  /**
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_40798_tableviewPresenceInModelElement() throws Throwable {
    String serverurl = this.testDataRule.getConfigData("AM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String elementId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamId = this.testDataRule.getConfigData("AM_STREAM_ID");
    String artifactType = this.testDataRule.getConfigData("ELEMENT_TYPE");

    RMMModelElementPage rmm = getJazzPageFactory().getRMMModelElementPage();
    assertNotNull(rmm);

    String element_URI = rmm.getRepositoryURLForRMMNonGC(elementId, serverurl, streamId, projectArea);

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().SSOCheckloginWithGivenPassword(getUserId(), getUserPassword(), element_URI);
    Reporter.logPass("Model Element Page: " + element_URI + "Opened  successfully.");

    getJazzPageFactory().getRMMModelElementPage().waitForSecs(10);

    // Validate element type
    WebElement value = getJazzPageFactory().getRMMModelElementPage()
        .getModelElementIfFoundByXpath(RMMConstants.RMM_ELEMENT_TYPE_XPATH);
    String typeOfElement = value.getText();
    // ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    Assert.assertTrue(typeOfElement.contains(artifactType));

    Reporter.logPass("The element type is : " + artifactType);

    // Validate filters added according to mentioned filter criteria.
    Assert.assertTrue(getJazzPageFactory().getRMMModelElementPage().validateTableContent());

    Reporter.logPass(artifactType + " have data and does not replace space with %EOL% in the content");

  }

}
