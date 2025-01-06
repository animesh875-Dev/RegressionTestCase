/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_JRS;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author GUX2KOR
 *
 */
public class TS_39818_chooseThePaperSizeForMicrosoftWordPdfOutput extends AbstractFrameworkTest {
  /**
   * This test case for export the report to Microsoft Word, PDF, or HTML  
   * should return correct data contains text
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39818_chooseThePaperSizeForMicrosoftWordPdfOutput() throws Throwable {
    
    String url = this.testDataRule.getConfigData("JRS_SERVER_URL");
    String tagName = this.testDataRule.getConfigData("TAG_NAME");
    String reportName = this.testDataRule.getConfigData("REPORT_NAME");
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    
    // select All reports tab
    getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("All reports");
    Reporter.logPass( " All reports have been clicked successfully.");
    
    // select group by tag
    getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("Group by tag");
    getJazzPageFactory().getJRSAllReportsPage().clickOnLink("Group by tag");
    Reporter.logPass("Clicked on Group by tag successfully.");
    
    // select the required tag
    getJazzPageFactory().getJRSAllReportsPage().clickOnLink(tagName);
    Reporter.logPass(tagName + ": is clicked successfully.");
    getJazzPageFactory().getAbstractGCPage().waitForSecs(5);
    
    // select the required report
    getJazzPageFactory().getJRSAllReportsPage().clickOnLink(reportName);
    Reporter.logPass(reportName + ": opened successfully.");
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    
    // click upon export button
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnButtons("Export");
    Reporter.logPass( "Export option clicked successfully.");
    getJazzPageFactory().getAbstractCCMPage().waitForSecs(5);
    
    // select required export option
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnLink("Export the report to Microsoft Word, PDF, or HTML");
    Reporter.logPass( "Export sub option clicked successfully.");
    getJazzPageFactory().getAbstractCCMPage().waitForSecs(5);
    
    // switch focus to export page
    getJazzPageFactory().getJRSBuildNewReportPage().switchToWindow();
    getJazzPageFactory().getAbstractCCMPage().waitForSecs(5);    
    
    // Generate a document from your report 
    Map<String,String> inputParameters = new LinkedHashMap<String,String>();
    inputParameters.put("File format", "Microsoft Word");
    inputParameters.put("Orientation", "Portrait");
    inputParameters.put("Paper size", "A4");
    inputParameters.put("Font size", "Medium");
    getJazzPageFactory().getAbstractGCPage().waitForSecs(5);
    getJazzPageFactory().getJRSBuildNewReportPage().generateAWordDocumentFromYourReport(inputParameters);
    getJazzPageFactory().getAbstractCCMPage().waitForSecs(5);
    Reporter.logPass( "The report is successfully is exported as word document with " + inputParameters.get("Paper size") + " paper size.");
    
    // verification of document generation
    TestAcceptanceMessage Ta = getJazzPageFactory().getJRSBuildNewReportPageVerification().verifygenerateAWordDocumentFromYourReport(inputParameters.get("Paper size"));
    if(Ta.getState().equals("FAILED")) {
      throw new WebAutomationException(Ta.getMessage());
    }
    Reporter.logPass(Ta.getMessage());
  } 
}