/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.ByType;
import com.bosch.jazz.automation.web.common.constants.JazzSettingsEnums.SettingsMenusEnum;
import com.bosch.jazz.automation.web.common.constants.JazzSettingsEnums.SettingsSubMenusEnum;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.rmm.RMMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.text.Text;

/**
 * @author PZP1KOR
 */
public class AbstractRMMPage extends AbstractJazzWebPage {


  /**
   * @param driverCustom
   */
  public AbstractRMMPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    this.driverCustom.openURL(repositoryURL);
  }

  /**
   * @param xPath
   */
  public void clickElement(final String xPath) {
    this.driverCustom.click(xPath);
  }

  /**
   * @param xPath
   * @return
   * @return
   */
  public void scrollViewToElement(final String xPath) {
    this.driverCustom.scrollIntoCenterOfView(xPath);
  }

  /**
   * @throws AWTException
   */
  public void zoomOutScreen() throws AWTException {
    Robot robot = new Robot();
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_MINUS);
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_MINUS);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyRelease(KeyEvent.VK_MINUS);
  }

  /**
   * @throws AWTException
   */
  public void zoomInScreen() throws AWTException {
    Robot robot = new Robot();
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_EQUALS);
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_EQUALS);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyRelease(KeyEvent.VK_EQUALS);
  }

  /**
   * @param xPath
   */
  public void scrollIframeToEndOfWindow(final String xPath) {
    this.driverCustom.executeJavaScript("document.getElementByClassName('footer').scrollTo(0, 999);");
  }

  /**
   * @param xPath
   */
  public void windowframeToEnd() {
    this.driverCustom.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    try {
      this.driverCustom.waitForSecs(Duration.ofSeconds(20));
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
  }

  /**
   * @param xPathValue
   * @param typeOfSelector
   * @param valueOfSelector
   * @return
   */
  public WebElement getModelElementIfFoundByXpath(final String xPathValue) {
    return this.driverCustom.getWebElementNoException(xPathValue, ByType.XPATH);
  }

  /**
   * @param xPathValue
   * @return
   */
  public WebElement getModelElementIfFoundByXpathWithDynamicValue(final String xPathValue, final String dynamicValue) {
    return this.driverCustom.getWebElement(xPathValue, dynamicValue);
  }


  /**
   * @return
   */
  public String getPageTitle() {
    return this.driverCustom.getCurrentPageTitle();
  }

  /**
   * @param xPath
   * @param inputValue
   * @param element
   */
  public void inputTextWorkItem(final String inputValue, final WebElement element) {

    WebElement textBox =
        this.driverCustom.getChildElement(element, By.xpath(RMMConstants.RMM_ADD_OSLC_LINK_ENTER_WORKITEM_TEXTBOX));
    textBox.sendKeys(inputValue);
  }

  /**
   * @param frameId
   * @param element
   */
  public void switchToFrameViewByNameOrID(final String frameId) {
    WebDriver driver = this.driverCustom.getWebDriver();
    driver.switchTo().frame(frameId);
  }

  /**
   * @param frameId
   */
  public void switchToFrameViewToActiveElement() {
    WebDriver driver = this.driverCustom.getWebDriver();
    driver.switchTo().defaultContent();
  }

  /**
   * @param element
   */
  public void switchToFrameViewByWebElement(final WebElement element) {
    WebDriver driver = this.driverCustom.getWebDriver();
    driver.switchTo().frame(element);
  }

  /**
   * @param workItemID
   * @return
   */
  public boolean quickSearch(final String workItemID) {
    waitForPageLoaded();
    this.driverCustom.switchToFrame(0);
    try {
      String xpathQuickSearch = "//input[@class='QueryInput']";
      WebElement txtQuickSearch = this.driverCustom.getWebElement(xpathQuickSearch);
      txtQuickSearch.clear();
      this.driverCustom.typeCharByChar(xpathQuickSearch, workItemID, Duration.ofSeconds(1));
      txtQuickSearch.click();
      txtQuickSearch.sendKeys(Keys.ENTER);
    }
    catch (Exception e) {
      Text txtSearchField = this.engine.findFirstElementWithDuration(Criteria.isTextField().withText(
          "Work Item Number or Words Contained in the Text. Use quotes for a phrase search:"), this.timeInSecs);
      txtSearchField.clearText();
      txtSearchField.setText(workItemID + Keys.ENTER);
    }
    // Wait for quick search loading disappeared
    this.driverCustom.isElementInvisible(RMConstants.QUICK_SEARCH_BOX_LOADING_XPATH, this.timeInSecs);
    LOGGER.LOG.info(workItemID + " - searched in the quick search text box.");
    return true;
  }

  /**
   * @param xPath
   */
  public void doubleClick(final String xPath) {
    this.driverCustom.clickTwice(xPath);
  }

  /**
   * @param xPath
   * @param value
   */
  public void doubleClickWithDynomicValue(final String xPath, final String value) {
    this.driverCustom.clickTwice(xPath, value);
  }


  /**
   * @param modelElementID Element ID
   * @param amServerURL Am Server URL
   * @param streamID
   * @param projectArea
   * @param modelElement
   * @param serverURL
   * @return
   */
  public String getRepositoryURLForRMMNonGC(final String modelElementID, final String amServerURL,
      final String streamID, final String projectArea) {

    StringBuilder resourceURI = new StringBuilder(RMMConstants.RMM_RESOURCE_URI).append(amServerURL)
        .append(RMMConstants.RMM_RESOURCE_RMM).append(modelElementID);

    StringBuilder oslcContext = new StringBuilder(RMMConstants.RMM_OSLC_CONFIG_CONTEXT).append(amServerURL)
        .append(RMMConstants.RMM_OSLC_STREAM).append(streamID);

    StringBuilder elementURI =
        new StringBuilder(amServerURL).append(RMMConstants.RMM_WEB_PROJECT).append(projectArea.replace(" ", "%20"))
            .append(RMMConstants.RMM_ACTION_URI).append(resourceURI).append(oslcContext);

    return elementURI.toString();


  }

  /**
   * @param dynamicValue
   */
  public void clickElementwithDynamicValue(final String xPath, final String dynamicValue) {
    this.driverCustom.click(xPath, dynamicValue);
  }

  /**
   * @param modelElementID
   * @param amServerURL
   * @param streamID
   * @param gcServerURL
   * @param projectArea
   * @param modelElement
   * @param serverURL
   * @return
   */
  public String getRepositoryURLForRMMGC(final String modelElementID, final String amServerURL, final String streamID,
      final String gcServerURL, final String projectArea) {

    StringBuilder resourceURI = new StringBuilder(RMMConstants.RMM_RESOURCE_URI).append(amServerURL)
        .append(RMMConstants.RMM_RESOURCE_RMM).append(modelElementID);

    StringBuilder oslcContext = new StringBuilder(RMMConstants.RMM_OSLC_CONFIG_CONTEXT).append(gcServerURL)
        .append(RMMConstants.GC_CONFIGURATION).append(streamID);

    StringBuilder elementURI =
        new StringBuilder(amServerURL).append(RMMConstants.RMM_WEB_PROJECT).append(projectArea.replace(" ", "%20"))
            .append(RMMConstants.RMM_ACTION_URI).append(resourceURI).append(oslcContext);

    return elementURI.toString();

  }

  /**
   * @param testCaseID
   * @param qmServerURL
   * @param qmProjectArea
   * @param qmStreamId
   * @param gcServerURL
   * @param gcStreamID
   * @return
   */
  public String getRepositoryURLForQMGC(final String testCaseID, final String qmServerURL, final String qmProjectArea,
      final String qmStreamId, final String gcServerURL, final String gcStreamID) {

    StringBuilder oslcContext = new StringBuilder(RMMConstants.RMM_OSLC_CONFIG_CONTEXT).append(gcServerURL)
        .append(RMMConstants.GC_CONFIGURATION).append(gcStreamID);

    StringBuilder subActionURI = new StringBuilder(RMMConstants.QM_GC_URL_PARAM_ACTION)
        .append(RMMConstants.QM_GC_URL_PARAM_SUBACTION).append(testCaseID);

    StringBuilder elementURI = new StringBuilder(qmServerURL).append(RMMConstants.QM_GC_URL_PARAM_WEB_CONSOLE)
        .append(qmProjectArea).append("/").append(qmStreamId).append(RMMConstants.QM_GC_URL_PARAM_JAZZ_LOCAL_PATH)
        .append(qmStreamId).append(oslcContext).append(subActionURI);

    return elementURI.toString();

  }

  /**
   * @param dngArtifactID
   * @param dngServerURL
   * @param modelElementID
   * @param amServerURL
   * @param streamID
   * @param gcServerURL
   * @param projectArea
   * @param dngRMComponent
   * @param dngRMProject
   * @return
   */
  public String getRepositoryURLForDNGGC(final String dngArtifactID, final String dngServerURL, final String streamID,
      final String gcServerURL, final String projectArea, final String dngRMProject, final String dngRMComponent) {

    StringBuilder artifactURI = new StringBuilder(RMMConstants.DNG_GC_URL_PARAM_ARTIFACT_URI).append(dngServerURL)
        .append(RMMConstants.DNG_GC_URL_PARAM_RESOURCES).append(dngArtifactID);

    StringBuilder oslcContext = new StringBuilder(RMMConstants.RMM_OSLC_CONFIG_CONTEXT).append(gcServerURL)
        .append(RMMConstants.GC_CONFIGURATION).append(streamID);

    StringBuilder elementURI = new StringBuilder(dngServerURL).append(RMMConstants.DNG_GC_URL_PARAM_ACTION)
        .append(artifactURI).append(oslcContext);


    return elementURI.toString();

  }

  /**
   * @param workItemID
   * @param ccmServerURL
   * @param ccmProjectArea
   * @return
   */
  public String getRepositoryURLForCCMGC(final String workItemID, final String ccmServerURL,
      final String ccmProjectArea) {
    StringBuilder elementURI =
        new StringBuilder(ccmServerURL).append(RMMConstants.CCM_GC_URL_PARAM_WEB_PROJECTS).append(ccmProjectArea)
            .append(RMMConstants.CCM_GC_URL_PARAM_ACTION).append(RMMConstants.CCM_GC_URL_PARAM_ID).append(workItemID);
    return elementURI.toString();
  }

  /**
   * <p>
   * Click on 'Administration' icon,list of options displayed.<br>
   * Click on 'Manage This Project Area' option, 'Manage Project Area' page is displayed.<br>
   * Click on 'Configuration Management' option.<br>
   * Verify the successful message displayed down of the page.
   *
   * @param name use to pass Project area name
   * @return checking Project area if provided property is enable or not
   */
  public boolean checkCMEnable(final String[] name) {
    waitForPageLoaded();
    ManageProjectAreaPage rmm = new ManageProjectAreaPage(this.driverCustom);
    rmm.openSettingsMenu(SettingsMenusEnum.ADMINISTRATION.toString());
    rmm.openSettingsSubMenu(SettingsSubMenusEnum.MANAGE_THIS_PROJECT_AREA.toString());
    rmm.tabSection(name[0]);
    JavascriptExecutor jse = (JavascriptExecutor) this.driverCustom.getWebDriver();

    System.out.println("Is checkbox checked: " +
        jse.executeScript("return arguments[0].checked", this.driverCustom.getWebElement(name[1])));
    return (boolean) jse.executeScript("return arguments[0].checked", this.driverCustom.getWebElement(name[1]));
  }


  /**
   * @param linkId to delete the CCM Link in RMM web
   */
  public void deleteRMMLinkbyId(final String linkId) {

    WebElement link = this.driverCustom.getWebElement("//a[contains(.,'" + linkId + ":" + "')]//ancestor::span");
    String typeOfElement = link.getText();
    if (typeOfElement.startsWith(linkId + ":")) {
      Actions actions = new Actions(this.driverCustom.getWebDriver());
      actions.moveToElement(link).build().perform();
      waitForSecs(3);

      WebElement btnDeleteList2 = this.driverCustom.getWebElement("//a[contains(.,'" + linkId +
          "')]//ancestor::span//parent::*//following-sibling::td//a[@class='deleteAction']", linkId);
      Actions actions1 = new Actions(this.driverCustom.getWebDriver());

      actions1.moveToElement(btnDeleteList2).build().perform();

      btnDeleteList2.click();
      WebElement deleteworkitem = this.driverCustom.getWebElement("//button[text()='Yes']");

      Actions actions2 = new Actions(this.driverCustom.getWebDriver());

      actions2.moveToElement(deleteworkitem).build().perform();

      if (deleteworkitem.isDisplayed()) {

        deleteworkitem.click();

      }
      LOGGER.LOG.info("Removed OSLC link : '" + linkId + "' from the 'Links' section of the RMM Element.");
      Reporter.logPass("Removed OSLC link : '" + linkId + "' from the 'Links' section of the RMM Element.");
    }
  }

  /**
   * @param linkId to delete the CCM Link in RMM web
   * @param workItemId is to associate while deleting DNG OSLC link in RMM
   */
  public void deleteRMMToDNGLinkbyId(final String linkId, final String workItemId) {
    // click on Delete option for selected link
    WebElement btnDelete = this.driverCustom.getWebElement(
        "//a[contains(.,'" + linkId + "')]//ancestor::span//parent::*//following-sibling::td//a[@class='deleteAction']",
        linkId);

    btnDelete.click();

    // associate Change request to delete DNG link in RMM web
    associateChangeRequestForDeleteDNGLink(workItemId);
    switchToFrameViewToActiveElement();
    WebElement deleteworkitem = this.driverCustom.getWebElement("//button[text()='Yes']");
//
//    Actions actions2 = new Actions(this.driverCustom.getWebDriver());
//
//    actions2.moveToElement(deleteworkitem).build().perform();

    if (deleteworkitem.isDisplayed()) {

      deleteworkitem.click();

    }
    LOGGER.LOG.info("Removed OSLC link : '" + linkId + "' from the 'Links' section of the RMM Element.");
    Reporter.logPass("Removed OSLC link : '" + linkId + "' from the 'Links' section of the RMM Element.");
  }

  /**
   * @param workItemID
   */
  private void associateChangeRequestForDeleteDNGLink(final String workItemID) {
    // click on Associate change request in the add oslc link popup window
    clickElement(RMMConstants.RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREUEST);

    // Wait for 5s for PageLoad
    waitForSecs(5);

    // click on Associate change request in the add oslc link popup window. Select Project area and click OK.
    clickElement(RMMConstants.RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREUEST_SELECTPROJECTAREA_OK);

    // Wait for 20s for PageLoad
    waitForSecs(5);
    WebElement iFrameDiv =
        getModelElementIfFoundByXpath(RMMConstants.RMM_DELETE_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_DIV);

    switchToFrameViewByWebElement(iFrameDiv);
    // this.driverCustom.switchToFrame(0);

    WebElement element = getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_ENTER_WORKITEM_TEXTBOX);

    element.sendKeys(workItemID);
    element.sendKeys(Keys.ENTER);

    doubleClickWithDynomicValue(RMMConstants.RMM_OPTION_VERIFY_TEXT_AVAILIBILITY, workItemID);

    waitForSecs(3);
  }

  /**
   * @param oslcLinkIDOrText to check is selected Link visible in RMM element
   * @return true if LINk is visible in RMM web else return fase
   */
  public boolean isOSLCLinkVisibleinWeb(final String oslcLinkIDOrText) {

    waitForPageLoaded();
    try {

      List<WebElement> lnkProcessLink =
          this.driverCustom.getWebElements(RMMConstants.RMM_VERIFY_OSLC_LINK_AVAILABILITY, oslcLinkIDOrText);
      Reporter.logPass("Check if OSLC Link Present?: " + (lnkProcessLink != null));

      if (lnkProcessLink != null) {
        return true;
      }
      return false;

    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * @param name
   * @return
   */
  public boolean quickSearchInRM(final String name) {
    try {
      String xpathQuickSearch = "//input[@class='SearchInputText']";
      WebElement txtQuickSearch = this.driverCustom.getWebElement(xpathQuickSearch);
      txtQuickSearch.clear();
      txtQuickSearch.sendKeys(name);
      txtQuickSearch.click();
      txtQuickSearch.sendKeys(Keys.ENTER);
    }
    catch (Exception e) {
      Text txtSearchField = this.engine
          .findFirstElementWithDuration(Criteria.isTextField().withText("Search Artifacts"), this.timeInSecs);
      txtSearchField.clearText();
      txtSearchField.setText(name + Keys.ENTER);
    }
    // Wait for quick search loading disappeared
    this.driverCustom.isElementInvisible(RMConstants.QUICK_SEARCH_BOX_LOADING_XPATH, this.timeInSecs);
    LOGGER.LOG.info(name + " - searched in the quick search text box.");
    return true;
  }

  /**
   * @param iFrameName iFrameName
   * @param componentName componentName
   */
  public void selectModelElement(final String iFrameName, final String componentName) {

    Reporter.logPass("Select Model Resource: Method is Started");

    switchToFrameViewByNameOrID(iFrameName);

    // Wait for 20s for PageLoad
    waitForPageLoaded();


    WebElement enterComponentName = getModelElementIfFoundByXpath(RMMConstants.RMM_INPUT_COMPONENT_NAME);

    enterComponentName.sendKeys(componentName);
    enterComponentName.sendKeys(Keys.ENTER);
    Reporter.logPass("Select Model Element: Component Name: " + componentName + "Is Filtered");


    clickElement(
        "//div[@id='com_ibm_team_rmm_web_ui_internal_pages_explorer_AddComponentsWidget_0']//following-sibling::span[text()='com.bosch.automationmainmodel']");

    Reporter.logPass("Select Model Element: Selected Component Name: " + componentName);

    // Wait for 20s for PageLoad
    waitForPageLoaded();

    Reporter.logPass("Select Model Element: Select Artifact: Expand Project Area");

    clickElement("//div[@title='Select artifacts']//div[@class='dojoxGridExpandoNode']");

    Reporter.logPass("Select Model Element: Select Artifact: Object Model Diagrams");
    clickElement("//span[contains(text(), 'Object Model Diagrams')]//parent::div/div/div");

    Reporter.logPass("Select Model Element: Select Artifact: Select Domain Diagram");
    clickElement(
        "//a[contains(text(), 'domain diagram')]//ancestor::table[@role='presentation' and @class='dojoxGridRowTable']");
    Reporter.logPass("Select Model Element: Select Artifact: Select Domain Diagram: Click OK");

    clickElement("//button[text()='Ok']");
    Reporter.logPass("Select Model Resource: Method is Completed");

  }

  /**
   * @param iFrameName
   * @param componentName
   */
  public void selectModelElementGC(final String iFrameName, final String componentName) {

    Reporter.logPass("Select Model Resource: Method is Started");

    switchToFrameViewByNameOrID(iFrameName);

    // Wait for 20s for PageLoad
    waitForPageLoaded();


    WebElement enterComponentName = getModelElementIfFoundByXpath(RMMConstants.RMM_INPUT_COMPONENT_NAME);

    enterComponentName.sendKeys(componentName);
    enterComponentName.sendKeys(Keys.ENTER);
    Reporter.logPass("Select Model Element: Component Name: " + componentName + "Is Filtered");


    clickElement(
        "//div[@id='com_ibm_team_rmm_web_ui_internal_pages_explorer_AddComponentsWidget_0']//following-sibling::span[text()='com.bosch.gcworkam']");

    Reporter.logPass("Select Model Element: Selected Component Name: " + componentName);

    // Wait for 20s for PageLoad
    waitForPageLoaded();

    Reporter.logPass("Select Model Element: Select Artifact: Expand Project Area");

    clickElement("//div[@title='Select artifacts']//div[@class='dojoxGridExpandoNode']");

    Reporter.logPass("Select Model Element: Select Artifact: Object Model Diagrams");
    clickElement("//span[contains(text(), 'Object Model Diagrams')]//parent::div/div/div");

    Reporter.logPass("Select Model Element: Select Artifact: Select Abstract Dishwasher");
    clickElement(
        "//a[contains(text(), 'Abstract Dishwasher')]//ancestor::table[@role='presentation' and @class='dojoxGridRowTable']");
    Reporter.logPass("Select Model Element: Select Artifact: Select Abstract Dishwasher: Click OK");

    clickElement("//button[text()='Ok']");
    Reporter.logPass("Select Model Resource: Method is Completed");

  }

  /**
   * @param elementResourceId
   * @param workItemID
   * @return visibility of Element
   */
  public boolean verifyLinkNavigationToAM(final String elementResourceId, final String ID) {

    switchToFrameViewToActiveElement();

    clickElementwithDynamicValue(RMMConstants.RM_SELECT_ARCHITECTURE_ELEMENT_LINK, elementResourceId);

    // Wait for 20s for PageLoad
    waitForPageLoaded();

    return isOSLCLinkVisibleinWeb(ID);

  }

  /**
   * @param fieldXPath xpath points to the field
   * @param fieldName name of the filed
   * @return true if field is visible in RMM web else return fase
   */
  public boolean isProvidedFieldAvailableInWebElement(final String fieldXPath, final String fieldName) {

    waitForPageLoaded();
    try {

      List<WebElement> lnkProcessLink = this.driverCustom.getWebElements(fieldXPath, fieldName);
      if (lnkProcessLink != null) {
        return true;
      }
      return false;

    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * @param id
   * @return
   */
  public boolean isLinkVisibleWithHref(final String id) {
    waitForPageLoaded();
    try {

      List<WebElement> lnkProcessLink =
          this.driverCustom.getWebElements(RMMConstants.ANCHOR_TAG_HREF_CONTAINS_TEXT, id);
      if (lnkProcessLink != null) {
        return true;
      }
      return false;

    }
    catch (Exception e) {
      return false;
    }

  }

  /**
   * @param elementID
   * @return
   */
  public void deleteAMLinkFromCCM(final String elementID) {

    WebElement link = this.driverCustom.getWebElement("//a[contains(@href,'" + elementID + "')]");
    Reporter.logPass("Scroll to Links Section");

    Actions actions = new Actions(this.driverCustom.getWebDriver());
    actions.moveToElement(link).build().perform();
    waitForSecs(3);

    // click on Delete option for selected link
    WebElement btnDeleteList2 = this.driverCustom.getWebElement(
        "//a[contains(@href,'" + elementID + "')]//ancestor::div[@class='ValueDiv']//span[@title='Remove']");
    Reporter.logPass("Click on Remove Link from CCM Application");

    Actions actions1 = new Actions(this.driverCustom.getWebDriver());
    actions1.moveToElement(btnDeleteList2).build().perform();
    if (btnDeleteList2.isDisplayed()) {
      btnDeleteList2.click();
    }

    Reporter.logPass("Removed OSLC link : '" + elementID + "' from the 'Links' section of the RMM Element.");

    LOGGER.LOG.info("Removed OSLC link : '" + elementID + "' from the 'Links' section of the RMM Element.");

  }

  /**
   * @param elementID
   * @return
   */
  public void deleteAMLinkFromQM(final String elementID) {

    waitForSecs(3);

    Reporter.logPass("Delete QM Link: Select the Testcase'" + elementID + "'");
    this.driverCustom.click(
        "//a[contains(@href, '" + elementID + "')]//ancestor::tr[contains(@name, '" + elementID + "')]/td[1]//input");

    waitForSecs(5);
    // click on Delete option for selected link
    Reporter.logPass("Delete QM Link: Click on Remove Links");
    this.driverCustom.click("//a[@title='Remove links']");

    waitForSecs(5);
    Reporter.logPass("Delete QM Link: Click on Remove Confirmation");
    this.driverCustom.click("//button[text()='Remove']");

    Reporter.logPass("Removed OSLC link : '" + elementID + "' from the 'Links' section of the RMM Element.");

    LOGGER.LOG.info("Removed OSLC link : '" + elementID + "' from the 'Links' section of the RMM Element.");


  }

}

