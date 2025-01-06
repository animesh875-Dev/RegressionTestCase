/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;

/**
 * @author GHO6HC
 */
public class JRSViewReportPage extends JRSPage {

  /**
   * @param driverCustom
   */
  public JRSViewReportPage(WebDriverCustom driverCustom) {
    super(driverCustom);
    // TODO Auto-generated constructor stub
  }

  /**
   * Switch to the iframe in the browser.
   */
  public void switchViewReportFrame(Integer index) {
    this.driverCustom.switchBrowserTabWithIndex(index);
    this.driverCustom.switchToFrame(JRSConstants.JRSCREATEREPORTPAGE_VIEWREPORTIFRAME_XPATH);
  }

  /**
   * Switch to the iframe after duplicating report.
   */
  public void switchRunReportFrame() {
    this.driverCustom.isElementInvisible(JRSConstants.JRSALLREPORTPAGE_LOADING_XPATH, this.timeInSecs);
    this.driverCustom.switchToFrame(JRSConstants.JRSCREATEREPORTPAGE_RUNREPORTIFRAME_XPATH);
  }


  /**
   * Switch to the iframe result
   * 
   * @return
   */
  public void switchViewReportFrame() {
    this.driverCustom.switchToDefaultContent();
    this.driverCustom.switchToFrame(JRSConstants.JRSCREATEREPORTPAGE_VIEWREPORTIFRAME_XPATH);
  }

  /**
   * Delete my report by name
   * 
   * @param report
   */
  public void deleteReportByName(final String report) {
    String url = this.driverCustom.getWebDriver().getCurrentUrl();
    String mainurl = url.substring(0, url.lastIndexOf('#'));
    this.driverCustom.getWebDriver().navigate().to(mainurl + "#mine");
    refresh();
    this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_MYREPORT_LINK_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSMYREPORTPAGE_SEARCHTXT_XPATH, this.timeInSecs);
    WebElement txtSearch = this.driverCustom.getWebElement(JRSConstants.JRSMYREPORTPAGE_SEARCHTXT_XPATH);
    txtSearch.sendKeys(report);
    this.driverCustom.click(JRSConstants.JRSMYREPORTPAGE_SEARCHICON_XPATH);
    this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_REPORTNAME_XPATH, report).click();
    this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_REPORT_DELETE_XPATH, report).click();
    Alert alt = this.driverCustom.getWebDriver().switchTo().alert();
    alt.accept();
  }

  /**
   * <p>
   * Choose Domain from 'Choose Configuration' wizard.
   *
   * @param domainDropDownValue dropdown value
   * @return true or false.
   */

  public boolean chooseDomainForConfigurations(final String domainDropDownValue) {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCHOOSECONFIGURATION_XPATH,
        Duration.ofSeconds(20), "Choose a configuration")) {
      this.driverCustom.getFirstVisibleWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCHOOSECONFIGURATION_XPATH,
          "Choose a configuration").click();
      // Choose Domain from 'Choose Configuration' wizard.
      Button button = null;

      if (this.driverCustom.isElementVisible("//p[text()='Choose a configuration']", this.timeInSecs)) {
        this.driverCustom.getWebElement("//p[text()='Choose a configuration']/parent::button").click();
      }
      button = this.engine.findElement(Criteria.isButton().withText("Choose a configuration")).getFirstElement();

      button.click();
      this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_LOCATECHOOSEDOMAIN_XPATH,
          Duration.ofSeconds(10));
      this.driverCustom.select(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSEDOMAIN_XPATH, domainDropDownValue,
          SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
      waitForSecs(10);
      return true;
    }
    return true;
  }


  /**
   * <p>
   * Choose a Configuration from 'Choose Configuration' wizard.
   *
   * @param configDropDownValue drop down value
   * @return true or false.
   */
  public boolean chooseConfiguration(final String configDropDownValue) {
    this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_REPORTIFRAME_IFRAME_XPATH, Duration.ofSeconds(5));
    WebElement drdbtn = null;
    try {
      // 7.0
      drdbtn = this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCONFIGURATION_7_XPATH);
    }
    catch (Exception ex) {
      // 6.0
      drdbtn = this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCONFIGURATION_6_XPATH);
    }
    drdbtn.click();
    LOGGER.LOG.info("Clicked on 'Choose a configuration' option.");
    this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCHCONFIGURATIONTEXTBOX_XPATH)
        .sendKeys(configDropDownValue + Keys.ENTER);
    WebElement dialogTitle = null;
    try {
      dialogTitle = this.driverCustom.getWebElement("//p[@id='choose-config-title']");
    }
    catch (Exception e) {
      // not found element
    }
    if (dialogTitle == null) {
      WebElement optConfiguration = this.driverCustom.getWebElement(
          "//li[@data-text='" + configDropDownValue + "']//input[not (contains(@style,'display: none;'))]");
      optConfiguration.click();
      waitForSecs(2);
    }
    else {
      this.driverCustom.select(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSECONFIGURATION_XPATH, configDropDownValue,
          SelectTypeEnum.SELECT_BY_VALUE);
      // Click on Accept button
      Button accept = this.engine.findElement(Criteria.isButton().withText("Accept")).getFirstElement();
      accept.click();
      LOGGER.LOG.info("Clicked on 'Accept' button.");
    }
    // Click on Run
    waitForSecs(1);
    Button btnRun = this.engine.findElement(Criteria.isButton().withText("Run")).getFirstElement();
    btnRun.click();
    LOGGER.LOG.info("Clicked on 'Run' button.");
    return true;
  }

  /**
   * <p>
   * Set View as
   *
   * @param domainDropDownValue dropdown value
   * @return true or false.
   */

  public void setViewAs(final String domainDropDownValue) {
    this.driverCustom.isElementVisible("//select[@id='viz-select']", Duration.ofSeconds(60));
    this.driverCustom.select("//select[@id='viz-select']", domainDropDownValue, SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
    this.driverCustom.waitForPageLoaded();
  }
}
