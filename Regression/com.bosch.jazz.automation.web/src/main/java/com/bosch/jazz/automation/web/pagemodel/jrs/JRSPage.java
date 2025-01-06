/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractJazzWebPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;

/**
 * Common base class for all JRS pages.
 */
public class JRSPage extends AbstractJazzWebPage {

  /**
   * See {@link AbstractJazzWebPage#AbstractJazzWebPage(WebDriverCustom)}
   */
  public JRSPage(final WebDriverCustom driverCustom) {
    super(driverCustom);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.openURL(repositoryURL);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getLoggedInUser() {

    throw new UnsupportedOperationException("Adapt this method to JRS!");
  }

  /**
   * Verify the total number of Items in the result of the report to the expected input value.
   *
   * @param value the expected input value as a number . Ex: "5"
   * @param xpath x-path of the main frame where report is available
   * @throws IllegalStateException if the expected and actual number of records do not match or no records found or run
   *           report is not visible. {@link IllegalStateException#IllegalStateException(String)}
   */
  public void verifyTotalNoOfRecords(final String value, final String xpath) {
    if (Integer.parseInt(value) != 0) {
      if (this.driverCustom.isElementPresent(xpath, this.timeInSecs)) {
        this.driverCustom.switchToFrame(xpath);
        if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_TOTALITEMSINRESULT_DIV_XPATH, Duration.ofSeconds(50))) {
          List<WebElement> totalCount =
              this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_TOTALITEMSINRESULT_DIV_XPATH);
          String text = totalCount.get(0).getText();
          String[] words = text.split("\\s");// splits the string based on whitespace

          String filter = getValueOfNumberOfRecords(words);
          verifyRecordValue(value, filter);
        }
        else {
          fail("No records found");
        }
      }
      else {
        fail("frame not found");
      }
    }
    // value = 0
    else {
      if (this.driverCustom.isElementPresent(xpath, this.timeInSecs)) {
        this.driverCustom.switchToFrame(xpath);
        String getText = driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_NO_RESULT_MESSAGE_TD_XPATH).getText().trim();
        if(getText.contains(JRSConstants.JRSCREATEREPORTPAGE_NO_RESULT_MESSAGE)) {
            Reporter.logPass("passes");
          }
        else {
          fail("Failed: Result return although expected record is zero (0).");
        }
      } 
    }
  }

  public Integer getTotalNumberOfRecords() {
    Integer totalRecords = 0;
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_PAGEINATION_XPATH,
        Duration.ofSeconds(50))) {
      String text = this.driverCustom.getText(JRSConstants.JRSCREATEREPORTPAGE_PAGEINATION_XPATH);
      String[] words = text.split("\\s");// splits the string based on whitespace
      String filter = getValueOfNumberOfRecords(words);
      totalRecords = Integer.valueOf(filter);
    }
    else {
      fail("No records found");
    }
    return totalRecords;
  }
  private String getValueOfNumberOfRecords(final String[] words) {
    String filter = null;
    int j = 0;
    // using java foreach loop to print elements of string array
    for (String w : words) {
      Reporter.logInfo(w);
      if (j == 4) {
        filter = w;
        break;
      }
      j++;
    }
    return filter;
  }

  private void verifyRecordValue(final String value, final String filter) {
    if (filter != null) {
      if (filter.trim().equals(value)) {
        Reporter.logPass("passes");
      }
      else {
        fail("No of records are not correct, total no of records in the reports is" + filter +
            " and total no recrods expected is " + value);
      }
    }
  }

  void clickAndCheckIfFound(final String tagName, final String errorMessgae, final String xpath, final boolean wait) {
    boolean artfactFound = selectIfMatches(tagName, false, xpath, wait);
    if (!artfactFound) {
      fail(errorMessgae);
    }
  }

  boolean selectIfMatches(final String filterValue, final boolean artfactFound, final String xpath,
      final boolean wait) {
    boolean artFound = artfactFound;
    List<WebElement> scopeOptions = this.driverCustom.getWebElements(xpath);
    for (WebElement opt9 : scopeOptions) {
      Reporter.logInfo("text is:  " + opt9.getText().trim());
      if (opt9.getText().trim().equals(filterValue.trim())) {
        opt9.click();
        artFound = true;
        if (wait) {
          waitForElement(5);
        }
        break;
      }
    }
    return artFound;
  }

  void verifyGraphResultInContainer(final Screen screen, final Pattern pattern) {
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_CHARTCONTAINER_DIV_XPATH, Duration.ofSeconds(30))) {
      Finder finder = new Finder(screen.capture().getImage());
      verifyPattern(pattern, finder);
    }
  }


  void verifyPattern(final Pattern pattern, final Finder finder) {
    finder.find(pattern);
    if (finder.hasNext()) {
      Match match = finder.next();
      Reporter.logInfo("Match found with " + ((match.getScore()) * 100) + "%");
      finder.destroy();
    }
    else {
      Reporter.logFailure("No Match Found");
      fail("Graph is not correct");
    }
  }

  /**
   * @param k The maximum time to wait in milliseconds
   */
  public void waitForElement(final int k) {
    waitForSecs(k);
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page. This method wait for
   * presence of Build link in 'JRSReportBuilder' Page.
   */
  @Override
  public void waitForPageLoaded() {

    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZ_SPANSELECTION_XPATH, "BUILD");

  }


}
