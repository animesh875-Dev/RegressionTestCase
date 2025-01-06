/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.ByType;
import com.bosch.jazz.automation.web.common.constants.JazzSettingsEnums.SettingsMenusEnum;
import com.bosch.jazz.automation.web.common.constants.JazzSettingsEnums.SettingsSubMenusEnum;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMMainMenus;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMArtifactsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMManageComponentPropertiesPage;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.element.text.TextField;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.container.RowFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.container.treeNode.JazzTreeNodeFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.JazzTextFinder;
import finder.text.label.JazzSpanLabelFinder;
import finder.text.textField.JazzSpanTextFieldFinder;


/**
 * Base page class for all Jazz based web pages. Contains functionality that is common to all Jazz applications/pages.
 */
public abstract class AbstractJazzWebPage extends AbstractWebPage {

  private static final String AUTH_LOGOUT = "auth/logout";
  private static final String WEB_PROJECTS = "/web/projects/";

  /**
   *
   */
  protected final Duration timeInSecs = this.driverCustom.getWebDriverSetup().getConfigurationForExplicitWaitTime();

  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom must not be null
   */
  public AbstractJazzWebPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTreeNodeFinder(), new JazzDropdownFinder(), new JazzButtonFinder(),
        new JazzTextFinder(), new JazzSpanLabelFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new JazzDialogFinder(), new RowCellFinder(), new LinkFinder(), new JazzSpanTextFieldFinder(), new TextFinder(),
        new RowFinder(), new JazzTabFinder(), new CheckboxFinder());
  }

  /**
   * <p>
   * Can be called to open this page from any current URL that the browser is in at the moment. In order to open the
   * page properly the repository url, project area name and potentially additional params can be provided to this
   * method.
   *
   * @param repositoryURL the URL of the repository that defines the context to open the page in, must not be null and
   *          must NOT end with a slash '/'
   * @param projectAreaName the name of the project area that defines the context to open the page in, may be null if
   *          not required for the specific page
   * @param additionalParams if some special parameters are required they can be added here, may be null/empty if no
   *          additional params required/provided
   */
  public abstract void open(final String repositoryURL, final String projectAreaName,
      Map<String, String> additionalParams);

  /**
   * <P>
   * Wait until the page is loaded/Opened.
   * <p>
   * Some times page is loaded but elements are not loaded properly in the dom, So a static element is checked for its
   * presence.
   */
  @Override
  public abstract void waitForPageLoaded();

  /**
   * <p>
   * Assumes that some user is logged in, the browser shows a Jazz page and then returns the name of the logged in user.
   *
   * @return the string shown in the browser for the logged in user. If the user name and organisation exceeds a certain
   *         length not the full text is shown/returned but it is cut off and some dots added instead. E.g. Tomljenovic
   *         Marko (CDG-SMT/E...
   *         <p>
   *         If no user is logged in or the user cannot be identified
   */
  public String getLoggedInUser() {
    WebElement webElement = this.driverCustom.getWebElement("user-name", ByType.CLASS_NAME);
    return webElement.getText();
  }

  /**
   * <p>
   * Returns the url to open the given project area in the browser.
   *
   * @param repositoryURL the repository url without trailing slash, must not be null
   * @param projectAreaName the project area name, may be null if the project area context is not known or not required
   *          for this page
   * @return the url to open the given project area in the browser
   */
  public String getProjectAreaURL(final String repositoryURL, final String projectAreaName) {
    return repositoryURL + WEB_PROJECTS + projectAreaName;
  }

  /**
   * <p>
   * This pages verifies whether the opened page is actual page or not.
   *
   * @param pageName title of the webpage.
   */
  public void verifyPageByTitle(final String pageName) {
    this.driverCustom.verifyPageTitleContains(pageName);
  }
  /**
   * <p>
   * Waits until un known element "Any Name" is visible. <br>
   * Its a type of static wait.Since the element wont be available in the dom. <br>
   * Its keep checking for the element to get visible. <br>
   * To avoid using "Thread.sleep" this methods is used. <br>
   * Note:Use it only where there is necessary.Justify why the wait is required.
   *
   * @param i is time to wait for element visibility
   */
  public void waitForSecs(final String i) {
    int number = Integer.parseInt(i);
    this.driverCustom.waitForSecs(Duration.ofSeconds(number));
  }
  /**
   * <p>
   * Waits until un known element "Any Name" is visible. <br>
   * Its a type of static wait.Since the element wont be available in the dom. <br>
   * Its keep checking for the element to get visible. <br>
   * To avoid using "Thread.sleep" this methods is used. <br>
   * Note:Use it only where there is necessary.Justify why the wait is required.
   *
   * @param i is time to wait for element visibility
   */
  public void waitForSecs(@SuppressWarnings("hiding") final int i) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(i));
  }

  /**
   * <p>
   * Waits until un known element "Any Name" is visible. <br>
   * Its a type of static wait.Since the element wont be available in the dom. <br>
   * Its keep checking for the element to get visible. <br>
   * To avoid using "Thread.sleep" this methods is used. <br>
   * Note:Use it only where there is necessary.Justify why the wait is required.
   *
   * @param duration is time to wait for element visibility, string parameter converted integer.
   */
  public void waitForSecs(final Duration duration) {
    int result = (int) duration.getSeconds();
    this.driverCustom.waitForSecs(Duration.ofSeconds(result));
  }

  /**
   * <p>
   * Login in to requirement management application "https://<local-host:8080>/rm" using
   * {@link JazzLoginPage#loginWithGivenPassword(String, String)}. <br>
   * Right side top of the menu bar, "My Stuff"," "User Profile", "Administration" and "Help" menus are found. <br>
   * Opens above setting menu,use {@link #openSettingsSubMenu(String)} to open sub setting menus under main seeting
   * menu.
   *
   * @param menuName name of the setting menu {@link SettingsMenusEnum}..
   */
  public void openSettingsMenu(final String menuName) {
    waitForSecs(Duration.ofSeconds(10));
    this.driverCustom.isElementClickable(RMConstants.RMPROJECTAREADASHBOARDPAGE_MENUITEM_XPATH, timeInSecs, menuName);
    try {
      Link lnkArtifact =
          this.engine.findElementWithDuration(Criteria.isLink().withText(menuName), timeInSecs).getFirstElement();
      lnkArtifact.click();
    }
    catch (NullPointerException e) {

      Optional<WebElement> matchingOptional = this.driverCustom.getWebElements("//a[@class = 'jazz-ui-MenuPopup']")
          .stream().filter(x -> x.getAttribute("title").startsWith(menuName)).findFirst();
      if (matchingOptional.isPresent()) {
        matchingOptional.get().click();
      }
      else {
        throw new WebAutomationException(menuName + "  or locater is invalid.");
      }
    }
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    LOGGER.LOG.info(menuName + "- setting menu opened sucessfully.");
  }

  /**
   * <p>
   * Open main menu using {@link #openMainMenuByMenuTitle(String)}.
   *
   * @param sectionName name of the section in sub menu.
   * @param subMenuName name of the sub menu.
   */
  public void openSubMenuUnderSection(final String sectionName, final String subMenuName) {
    String[] dynamicValue = { sectionName, subMenuName };
    this.driverCustom.getClickableWebElement(
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_NAVIGATEBAR_DROPDOWN_XPATH, dynamicValue));
    this.driverCustom.click(RQMConstants.RQMPROJECT_NAVIGATEBAR_DROPDOWN_XPATH, dynamicValue);
    refresh();
    waitForSecs(Duration.ofSeconds(10));
    LOGGER.LOG.info("Under the section -" + sectionName + " sub menu " + subMenuName + " opened successfully.");
  }

  /**
   * <p>
   * Open setting menu {@link #openSettingsMenu(String)}. <br>
   * Under "My Stuff"," "User Profile", "Administration" and "Help" these main menus sub menu items are present <br>
   * Open sub menu by providing exact name.
   *
   * @param subMenuName parameter holds name of the Sub Menu.{@link SettingsSubMenusEnum}.
   */
  public void openSettingsSubMenu(final String subMenuName) {
    waitForSecs(Duration.ofSeconds(10));
    try {
      try {
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_CREATECOMP_XPATH, subMenuName).click();
      }
      catch (Exception e) {
//        Some time on 05Q server will get page load time out error - so in catch block do nothing
//        Link lnkArtifact = this.engine.findElementWithDuration(Criteria.isLink().withText(subMenuName), timeInSecs)
//            .getFirstElement();
//        lnkArtifact.click();
      }
    }
    catch (NullPointerException e) {
      Optional<WebElement> matchingOptional = this.driverCustom.getWebElements("//tr[@role = 'menuitem']").stream()
          .filter(x -> x.getText().startsWith(subMenuName)).findFirst();
      if (matchingOptional.isPresent()) {
        matchingOptional.get().click();
      }
      else {
        throw new WebAutomationException(subMenuName + "  or locater is invalid.");
      }

    }
    this.driverCustom.isElementInvisible("//div[@class='status-message'][text()='Loading Project Areas...']",
        Duration.ofSeconds(20));
    LOGGER.LOG.info(subMenuName + "- setting sub menu opened sucessfully.");
  }

  /**
   * <p>
   * Verifies the title not contains on the page.
   *
   * @param textNotInTitle parameter to hold the title which is not empty and not containing in the page title.
   */
  public void titleNotEmptyAndContainsNot(final String textNotInTitle) {
    ExpectedConditionsCustom.titleNotEmptyAndContainsNot(textNotInTitle);
  }

  /**
   * <p>
   * Switches back from the current page to previous page.
   */
  public void navigateBack() {
    this.driverCustom.getWebDriver().navigate().back();
  }

  /**
   * <p>
   * Refresh the current page.
   */
  public void refresh() {
    this.driverCustom.getWebDriver().navigate().refresh();
  }

  /**
   * <p>
   * After opening any application using selenium web driver.Page will be loaded in the browser window.
   *
   * @return main window name.
   */
  public String getMainWindow() {
    return this.driverCustom.getWebDriver().getWindowHandle();
  }

  /**
   * <p>
   * After opening any application using selenium web driver.Page will be loaded in the browser window. <br>
   *
   * @return list of All window names.
   */
  public Set<String> getALLWindows() {
    return this.driverCustom.getWebDriver().getWindowHandles();
  }

  /**
   * @param windowName Name of the window.
   */
  public void switchToWindow(final String windowName) {
    this.driverCustom.getWebDriver().switchTo().window(windowName);
  }

  /**
   * close the current window.
   *
   * @return true if the total tabs after closing the current active window are reduced by 1
   */
  public boolean closeWindow() {
    WebDriver driver = this.driverCustom.getWebDriver();
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    int totalTabsBeforeClosing = tabs.size();
    this.driverCustom.getWebDriver().close();
    WebDriver driver2 = this.driverCustom.getWebDriver();
    ArrayList<String> tabs2 = new ArrayList<String>(driver2.getWindowHandles());
    int totalTabsAfterClosing = tabs2.size();
    if ((totalTabsBeforeClosing - totalTabsAfterClosing) == 1) {
      return true;
    }
    return false;
  }

  /**
   * button is common for all webelement buttons
   *
   * @param button is common for all webelement buttons
   */
  public void clickOnJazzButtons(final String button) {
    waitForSecs(Duration.ofSeconds(5));
    List<WebElement> lst = this.driverCustom.getWebElements(RMConstants.JAZZPAGE_BUTTONS_XPATH, button);
    for (WebElement ele : lst) {
      if (this.driverCustom.isElementVisible(ele, Duration.ofSeconds(5))) {
        ele.click();
        LOGGER.LOG.info("Clicked on '" + button + "'");
        waitForSecs(Duration.ofSeconds(5));
      }
    }
  }

  /**
   * button is common for all web element buttons
   *
   * @param button is common for all web element buttons
   */
  public void clickOnJazzSpanButtons(final String button) {
    waitForSecs(Duration.ofSeconds(2));
    try {
      Button spanBtn =
          this.engine.findElementWithDuration(Criteria.isButton().withText(button), timeInSecs).getFirstElement();
      spanBtn.click();
      LOGGER.LOG.info("Clicked on '" + button + "' button.");
    }
    catch (Exception e) {
      Optional<WebElement> opt =
          this.driverCustom.getWebElements("//span[contains(text(),'DYNAMIC_VAlUE')]", button).stream().findFirst();
      if (opt.isPresent()) {
        opt.get().click();
      }
      else {
        throw new WebAutomationException(button + " invalid button name.");
      }
    }
    this.driverCustom.switchToDefaultContent();
  }

  /**
   * Type is common Locator for all webelements
   *
   * @param Type is common Locator for all webelements
   */
  public void clickonWebElement(final String Type) {
    List<WebElement> lst = this.driverCustom.getWebElements(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Type);
    for (WebElement element : lst) {
      if (this.driverCustom.isElementVisible(element, Duration.ofSeconds(5))) {
        element.click();
        break;
      }
    }
  }

  /**
   * <p>
   * Logs out by clicking on the logout menu entry.
   */
  public void logout() {
    Dropdown drdUserProfile = this.engine
        .findElement(Criteria.isDropdown().withToolTip(SettingsMenusEnum.USER_PROFILE.toString())).getFirstElement();
    drdUserProfile.selectOptionWithText(SettingsSubMenusEnum.LOG_OUT.toString());
  }

  /**
   * <p>
   * Logs out by opening the log out URL in the browser. For that the repository URL is required.
   *
   * @param repositoryUrl the repository to log out from, must not be null
   */
  public void logOutWithUrl(final String repositoryUrl) {
    waitForSecs(Duration.ofSeconds(5));
    refresh();
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getWebDriver().get(repositoryUrl + AUTH_LOGOUT);

  }

  /**
   * <p>
   * Logs out by opening the log out URL in the browser. For that the repository URL is required.
   *
   * @param ssoEnabled holds value for ssoEnabled.
   * @param repositoryUrl the repository to log out from, must not be null
   */
  public void logOut(final String ssoEnabled, final String repositoryUrl) {

    if (Boolean.valueOf(ssoEnabled)) {
      waitForSecs(Duration.ofSeconds(5));
      try {
        refresh();
      }
      catch (Exception e) {
        e.printStackTrace();
        LOGGER.LOG.info("Page takes a lot of time to load, wait for more 20 seconds to finish loading");
        waitForSecs(10);
        // Force stop loading
        this.driverCustom.executeJavaScript("\"window.stop();\"");
      }
      ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
      this.driverCustom.getWebDriver().get(repositoryUrl + AUTH_LOGOUT);
      this.driverCustom.getWebDriver().get(repositoryUrl + "auth/authrequired");
      refresh();
      waitForSecs(Duration.ofSeconds(5));
      this.driverCustom.getWebDriver().manage().deleteAllCookies();
    }

    else {
      Dropdown drdUserProfile = this.engine
          .findElement(Criteria.isDropdown().withToolTip(SettingsMenusEnum.USER_PROFILE.toString())).getFirstElement();
      drdUserProfile.selectOptionWithText(SettingsSubMenusEnum.LOG_OUT.toString());
      this.driverCustom.getWebDriver().manage().deleteAllCookies();
    }

  }

  /**
   * Add OpenSocialGadget by passing the OpenSocialGadget url.
   *
   * @param widgetUrl The URL for adding the Widget, must not be null
   */
  public void addOpenSocialGadget(final String widgetUrl) {

    miniDashboardOpen();

    clickOn(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebElement(
            "//a[@class='button' and @dojoattachpoint='titleNode,focusNode' and @title='Add Widget']", ByType.XPATH),
        Duration.ofSeconds(20));
    waitForSecs(5);
    
    clickOn(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebElement("//a[@dojoattachpoint='_addGadgetLink']", ByType.XPATH),
        Duration.ofSeconds(20));
    
    WebElement webElementForUrl =
        this.driverCustom.getWebElement("//input[@dojoattachpoint='_urlInputText']", ByType.XPATH);
    webElementForUrl.sendKeys(widgetUrl);

    Button addWidgetBtn = this.engine.findElement(Criteria.isButton().withText("Add Widget")).getFirstElement();
    addWidgetBtn.click();
    try {
      clickOn(this.driverCustom.getWebDriver(),
          this.driverCustom.getWebElement("//input[@type='submit' and @class= 'close']", ByType.XPATH),
          Duration.ofSeconds(20));

      clickOn(this.driverCustom.getWebDriver(),
          this.driverCustom.getWebElement("//input[@type='submit' and @class= 'close']", ByType.XPATH),
          Duration.ofSeconds(20));
    }
    catch (Exception e) {
      clickOn(this.driverCustom.getWebDriver(),
          this.driverCustom.getWebElement("//button[@aria-label='close']", ByType.XPATH), Duration.ofSeconds(20));
    }
    Reporter.logPass( widgetUrl + " URL added successfully using Add OpenSocial Gadget option in minidashboard");
  }

  /**
   * Remove OpenSocialGadget by passing the OpenSocialGadget Name.
   *
   * @param name The name for the OpenSocialGadget which is need to be removed, must not be null
   */
  public void removeOpenSocialGadget(final String name) {
    waitForSecs(5);
    miniDashboardOpen();
    String xpath = PagemodelConstants.SPAN_TEXT + name +
        "']//parent::div//parent::div//parent::div[@dojoattachpoint='_headerText']//preceding-sibling::div[@role='toolbar']//a[@title='Remove']";
    clickOn(this.driverCustom.getWebDriver(), this.driverCustom.getWebElement(xpath, ByType.XPATH),
        Duration.ofSeconds(20));
    this.driverCustom.getWebDriver().switchTo().alert().accept();
    LOGGER.LOG.info(name+ " Widget removed.");
    waitForSecs(5);
  }

  /**
   * Opens Mini Dashboard
   */
  public void miniDashboardOpen() {
    int i = 0;
    do {
      this.waitForSecs(Duration.ofSeconds(5));
      this.driverCustom.getPresenceOfWebElement("//div[@class='jazz-ui-Drawer-inner']").click();
      i++;
      if (i >= 5) {
        break;
      }
    }
    while (this.driverCustom.isElementVisible("//div[@aria-label='Mini Dashboard']", timeInSecs));
  }

  /**
   * <p>
   * open Artifact Types by taking text value of tabs.
   *
   * @param tabName use to select tabs from Manage Component Properties Artifact types
   */
  public void openManageComponentProperties(final String tabName) {
    Tab tabReqIF =
        this.engine.findElementWithDuration(Criteria.isTab().withText(tabName), timeInSecs).getFirstElement();
    tabReqIF.click();
  }

  /**
   * <p>
   * Main menu items for requirements management dash board page contains "Project Dashboards", "Requirements",
   * "Planning", "Construction", "Lab Management", "Builds", "Execution", "Reports" and "Change Requests".
   *
   * @see RQMMainMenus#REQUIREMENTS Enum for href values.
   * @param menuTitle title value.
   */
  public void openMainMenuByMenuTitle(final String menuTitle) {
    waitForSecs(Duration.ofSeconds(10));
    try {
      Link lnkArtifact =
          this.engine.findElementWithDuration(Criteria.isLink().withText(menuTitle), timeInSecs).getFirstElement();
      lnkArtifact.click();
      LOGGER.LOG.info(menuTitle + " menu opened successfully.");
    }
    catch (Exception e) {
      throw new WebAutomationException(
          menuTitle + " : menu not found.Please check for valid menu title.\n" + "or\n" + e.getMessage());
    }
  }

  /**
   * Open menu item in Requirement management by taking menu href value of it.
   *
   * @see RQMMainMenus Enum for title values.
   * @param Sections Title value.
   */
  public void openRQMSection(final String Sections) {
    try {
      waitForSecs(Duration.ofSeconds(5));
      Link lnkArtifact =
          this.engine.findElementWithDuration(Criteria.isLink().withText(Sections), timeInSecs).getFirstElement();
      lnkArtifact.click();
      LOGGER.LOG.info(Sections + " - section opened sucessfully.");
    }
    catch (Exception e) {
      throw new WebAutomationException(
          Sections + " section not found.Please check for valid section name.\n" + "or\n" + e.getMessage());
    }
    waitForSecs(Duration.ofSeconds(2));
  }

  /**
   * Waits up to ten seconds to find a clickable link to a project area or any other Jazz resource.
   */
  public void waitForProjectAreaLinkToBeClickable() {
    // wait until a link to a project area is visible
    new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(By.className("jazz-ui-ResourceLink")));
  }

  /**
   * Navigating to url.
   *
   * @param url is repository url.
   */
  public void navigateToURL(final String url) {
    // wait until a link to a project area is visible
    this.driverCustom.openURL(url);
  }

  /**
   * Message from class messageSummary with division tag.
   *
   * @return messages summary.
   */
  public String getMessageSummary() {
    if (this.driverCustom.isElementVisible(RMConstants.JAZZ_MESSAGE_SUMMARY_XPATH, Duration.ofSeconds(10))) {
      String errorMsg = "";
      for (String s : this.driverCustom.getWebElementsText(RMConstants.JAZZ_MESSAGE_SUMMARY_XPATH)) {
        errorMsg = s.concat(errorMsg);
      }
      return errorMsg;
    }
    return "";

  }

  /**
   * Clicks on button which is actually image.
   * <p>
   * The button which are implemented with Image tag, when we hover on that button the name of button displays. <br>
   * To click on that image type buttons we can use this method.
   *
   * @param button is common for all image buttons
   * @return true is button clicked.
   */
  public Boolean clickOnJazzImageButtons(final String button) {
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(),
        Duration.ofSeconds((timeInSecs.getSeconds() / 10)));
    waitForSecs(Duration.ofSeconds(3));
    switch (button) {
      case "Pass (Ctrl+Shift+P)":
        /*
         * this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH,
         * RQMConstants.PASS_CTRL_SHIFT_P); this.driverCustom.click(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH,
         * RQMConstants.PASS_CTRL_SHIFT_P);
         */
        Actions pass = new Actions(this.driverCustom.getWebDriver());
        pass.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("P").build().perform();
        return true;
      case "Pass (Ctrl+Shift+G)":
        if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH, timeInSecs,
            RQMConstants.PASS_CTRL_SHIFT_G)) {
          Actions pass1 = new Actions(this.driverCustom.getWebDriver());
          pass1.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("G").build().perform();
          return true;
        }
        return false;
      case "Fail (Ctrl+Shift+F)":
        /*
         * this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH,
         * RQMConstants.PASS_CTRL_SHIFT_P); this.driverCustom.click(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH,
         * RQMConstants.PASS_CTRL_SHIFT_P);
         */
        Actions fail = new Actions(this.driverCustom.getWebDriver());
        fail.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("F").build().perform();
        return true;
      case "Associate this test script with test case":
        WebElement siteBar = this.driverCustom.getPresenceOfWebElement("//div[@class='show-bar show-bar-main']");
        if (this.driverCustom.isElementPresent("//div[@class='arrow-left']", timeInSecs)) {
          siteBar.click();
        }
        Button btnAssociate =
            this.engine.findElementWithDuration(Criteria.isButton().withToolTip(button), timeInSecs).getFirstElement();
        btnAssociate.click();
        return true;
      case "Associate this test case with test suite":
        WebElement siteBar1 = this.driverCustom.getPresenceOfWebElement("//div[@class='show-bar show-bar-main']");
        if (this.driverCustom.isElementPresent("//div[@class='arrow-left']", timeInSecs)) {
          siteBar1.click();
        }
        Button btnAssociate1 =
            this.engine.findElementWithDuration(Criteria.isButton().withToolTip(button), timeInSecs).getFirstElement();
        btnAssociate1.click();
        return true;
      case "Associate this test case with test plan":
        WebElement siteBar2 = this.driverCustom.getPresenceOfWebElement("//div[@class='show-bar show-bar-main']");
        if (this.driverCustom.isElementPresent("//div[@class='arrow-left']", timeInSecs)) {
          siteBar2.click();
        }
        Button btnAssociate2 =
            this.engine.findElementWithDuration(Criteria.isButton().withToolTip(button), timeInSecs).getFirstElement();
        btnAssociate2.click();
        return true;
      case "Move or copy this work item to another project area":
        WebElement btnMoveWorkItem =
            this.driverCustom.getWebElement(CCMConstants.CCMWORKITEMEDITPAGE_MOVE_WORKITEM_XPATH, button);
        btnMoveWorkItem.click();
        waitForSecs(2);
        return true;
      default:
        try {
          Button spanBtn = this.engine.findElementWithDuration(Criteria.isButton().withToolTip(button), timeInSecs)
              .getFirstElement();
          spanBtn.click();
          LOGGER.LOG.info(PagemodelConstants.BUTTON + button + "' is clicked successfully.");
          return true;
        }
        catch (Exception e) {
          LOGGER.LOG.info(PagemodelConstants.BUTTON + button + "' is not found by engine.\n" + e.getMessage());
          return false;
        }
    }
  }

  /**
   * <p>
   * Clicks on tab which is present in same window with multiple pages. <br>
   * E.g. CCM work item page Overview, Links, Attachment etc tabs.
   *
   * @param tab is common for all image buttons
   */
  public void clickOnTab(final String tab) {
    Tab tabArtifactCategories =
        this.engine.findElementWithDuration(Criteria.isTab().withText(tab), timeInSecs).getFirstElement();
    tabArtifactCategories.click();
    LOGGER.LOG.info(tab + " tab is clicked successfully.");
  }

  /**
   * <P>
   * Click on Button which present inside a dialog.
   *
   * @param dialog name of the dialog.
   * @param button name of the button.
   * @return true if dialog button is clicked.
   */
  public Boolean clickOnDialogButton(final String dialog, final String button) {
    try {
      waitForSecs(Duration.ofSeconds(10));
      Dialog dlgCreateRootCategory =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle(dialog), timeInSecs).getFirstElement();
      Button btnOK1 = this.engine
          .findElementWithDuration(Criteria.isButton().withText(button).inContainer(dlgCreateRootCategory), timeInSecs)
          .getFirstElement();
      btnOK1.click();
      waitForSecs(Duration.ofSeconds(3));
      if (btnOK1.isElementEnable(Duration.ofSeconds(10))) {
        btnOK1.click();
        waitForSecs(Duration.ofSeconds(3));
      }
      LOGGER.LOG.info("Button \"" + button + "\" in '" + dialog + "' dialog is clicked sucessfully.");
      return true;
    }
    catch (Exception e) {
      LOGGER.LOG
          .info("Button \"" + button + "\" in '" + dialog + "' dialog is not found by engine.\n" + e.getMessage());
      return false;
    }
  }

  /**
   * <P>
   * Click on Button which present inside a dialog and works with ToolTip.<br>
   *
   * @param dialog name of the dialog.
   * @param toolTiputton name of the button.
   * @return true if button in dialog is clicked.
   */
  public Boolean clickOnDialogToolTipButton(final String dialog, final String toolTiputton) {
    try {
      Dialog dlgCreateRootCategory =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle(dialog), timeInSecs).getFirstElement();
      Button btnOK1 =
          this.engine.findElement(Criteria.isButton().withToolTip(toolTiputton).inContainer(dlgCreateRootCategory))
              .getFirstElement();
      btnOK1.click();
      LOGGER.LOG.info("Button '" + toolTiputton + "' in '" + dialog + "' dialog is clicked sucessfully.");
      return true;
    }
    catch (Exception e) {
      LOGGER.LOG
          .info("Button '" + toolTiputton + "' in '" + dialog + "' dialog is not found by engine.\n" + e.getMessage());
      return false;
    }
  }

  /**
   * <p>
   * Click on label by using label name.
   *
   * @param labelName is name of label.
   */
  public void clickOnLabel(final String labelName) {
    Label tabArtifactCategories =
        this.engine.findFirstElementWithDuration(Criteria.isLabel().withText(labelName), timeInSecs);
    tabArtifactCategories.click();
  }

  /**
   * <p>
   * Click on label by using label name.
   *
   * @param dialog name of the dialog.
   * @param labelName is name of label.
   */
  public void clickOnDialogLabel(final String dialog, final String labelName) {
    Dialog dlgCreateRootCategory = this.engine.findElement(Criteria.isDialog().withTitle(dialog)).getFirstElement();
    Label tabArtifactCategories = this.engine
        .findElement(Criteria.isLabel().withText(labelName).inContainer(dlgCreateRootCategory)).getFirstElement();
    tabArtifactCategories.click();
  }

  /**
   * Click on a link with displayed text
   *
   * @param linkText text of the link
   * @return true if link is clicked successfully.
   */
  public Boolean clickOnLink(final String linkText) {
    try {
      Link link =
          this.engine.findElementWithDuration(Criteria.isLink().withText(linkText), timeInSecs).getFirstElement();
      link.click();
      waitForSecs(Duration.ofSeconds(10));
      LOGGER.LOG.info("Link with text is '" + linkText + "' is clicked sucessfully.");
      return true;
    }
    catch (Exception e) {
      try {
        if (this.driverCustom.isElementVisible("//a[@title='Expand']", Duration.ofSeconds(10))) {
          this.driverCustom.getWebElement("//a[@title='Expand']").click();
        }
        this.driverCustom.clickOnLink(linkText, false);
        return true;
      }
      catch (Exception e1) {
        LOGGER.LOG.info("Link with text is '" + linkText + "' is not found by engine.\n" + e1.getMessage());
        return false;
      }
    }
  }

  /**
   * Click on a link with a part of displayed text
   *
   * @param linkText text of the link
   * @return true if link is clicked successfully.
   */
  public Boolean clickOnLinkWithPartOfText(final String linkText) {
    try {
      this.driverCustom.clickOnLink(linkText, true);
      LOGGER.LOG.info("Link with a part of text is '" + linkText + "' is clicked sucessfully.");
      return true;
    }
    catch (Exception e) {
      LOGGER.LOG.info("Link with a part of text is '" + linkText + "' is not found.\n" + e.getMessage());
      return false;
    }
  }

  /**
   * Opens the project area specified by the given parameters
   *
   * @param repositoryURL the repository URL of the project area, must not be null
   * @param projectAreaName the name of the project area, must not be null
   */
  public void openProjectArea(final String repositoryURL, final String projectAreaName) {
    this.driverCustom.openURL(getProjectAreaURL(repositoryURL, projectAreaName));
    this.driverCustom.verifyPageTitleContains(projectAreaName);
  }

  /**
   * Assumes the page is shown where all project areas are listed, then clicks on the given project area in the list.
   *
   * @param projectAreaName the name of the project area, must not be null
   */
  public void selectProjectArea(final String projectAreaName) {
    try {
      Link lnkProjectAreaName = this.engine
          .findElementWithDuration(Criteria.isLink().withText(projectAreaName), timeInSecs).getFirstElement();
      lnkProjectAreaName.scrollToElement();
      lnkProjectAreaName.click();
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPAGE_DASHBOARD_TITLE_XPATH);
    }
    catch (Exception e) {
      if (e.getMessage().contains("No element were found")) {
        throw new IllegalArgumentException(projectAreaName + "- project area not found. \n or \n " + e.getMessage());
      }
    }
    LOGGER.LOG.info(projectAreaName + " project area opened sucessfully.");
  }


  /**
   * To selecting the particular Component in Configuration Management Project Area DashBoard
   *
   * @param CompName is selecting particular component name
   * @param ProjArea Select the Component from the Project Area
   */
  public void selectPariticularComp(final String CompName, final String ProjArea) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_SETTINGS_CURRENTCONFIGCOMP_XPATH);
    if (this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(5),
        "Component Setup")) {
      clickOnJazzButtons("Close");
    }
    waitForPageLoaded();
    for (int i = 0; i < 6; i++) {
      if (!this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10),
          "Choose Another Component...")) {
        this.driverCustom.isElementClickable(RMConstants.RMDASHBOARDPAGE_SETTINGS_CURRENTCONFIGCOMP_XPATH,
            Duration.ofSeconds(10));
        Link currentProjectlink =
            this.engine.findFirstElement(Criteria.isLink().withToolTip("Current Project Component"));
        currentProjectlink.click();
      }
      else {
        break;
      }
    }
    this.driverCustom.click(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, "Choose Another Component...");
    this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH, Duration.ofSeconds(5));
    for (int i = 0; i < 6; i++) {
      if (!this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_PROJECTAREA_XPATH,
          Duration.ofSeconds(5), ProjArea)) {
        this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH);
      }
      else {
        break;
      }
    }
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SETTINGS_PROJECTAREA_XPATH, ProjArea);
    this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SEARCHCOMP_XPATH, Duration.ofSeconds(5));
    Dialog dialog = this.engine.findElement(Criteria.isDialog().withTitle("Choose Component")).getFirstElement();
    TextField t = this.engine.findFirstElement(
        Criteria.isTextField().inContainer(dialog).withPlaceHolder("Type to search names (enter * to show all)"));
    t.setText(CompName);
    if (this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTCOMP_XPATH,
        Duration.ofSeconds(10), CompName)) {
      this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTCOMP_XPATH, CompName);
      clickOnJazzButtons("OK");
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_ARTIFACTTYPE_LINKTEXT_LINKTEXT,
          Duration.ofSeconds(20), "All");
    }
    else {
      clickOnJazzButtons("Cancel");
    }
  }

  /**
   * <P>
   * Open project area from list of the project areas. <b>Switch to global configuration.
   *
   * @param projectAreaName name of the project area to be opened.
   * @param gcName name of the configuration.
   * @param componentName name of the component.
   * @param streamName name of the stream.
   */
  public void selectProjectAreaAndGlobalConfiguration(final String projectAreaName, final String gcName,
      final String componentName, final String streamName) {
  }

  /**
   * filterArtifactByTextOrId is used to search the Artifact,Module,Collection Id/text in search box "Type to filter
   * artifacts by text or by ID" under Artifacts menu.
   *
   * @param artifactID is Id of the Artifact, Modules, Collections
   */
  public void filterArtifactByTextOrId(final String artifactID) {
    waitForPageLoaded();
    // Clear Filter in Artifacts page
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_CLEAR_FILTER_XPATH_VERSION7,
        Duration.ofSeconds(5))) {
      WebElement btnClearAllFilterAndResetColumns =
          this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_CLEAR_FILTER_XPATH_VERSION7);
      btnClearAllFilterAndResetColumns.click();
      LOGGER.LOG.info("Clicked on 'Clear all filters and reset columns' button.");
    }
    // Clear Filter in Module page
    if (this.driverCustom.isElementVisible(RMConstants.RMMODULE_CLEAR_FILTER_XPATH, Duration.ofSeconds(5))) {
      this.driverCustom.getWebElementClickbale(RMConstants.RMMODULE_CLEAR_FILTER_XPATH).click();
      LOGGER.LOG.info("Clicked on 'Clear all filters and reset columns' button.");
    }
    Text txtSearchVerify = this.engine
        .findElementWithDuration(
            Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID), timeInSecs)
        .getFirstElement();
    txtSearchVerify.setText(artifactID + Keys.ENTER);
    waitForSecs(Duration.ofSeconds(5));
    LOGGER.LOG.info("Searched " + artifactID + " in 'Type To Filter' textbox in artifacts page.");
  }

  /**
   * Can be called to add a new tab in the dashboard of user profile page. In order to add new tab, tab name and no of
   * columns in the layout must be provide to this method. Tab name must be unique that means no other tab is there with
   * the same name.
   *
   * @param tabName The name of the newly created Tab must not be null and should not already exits
   * @param noOfColumns the total number of columns in the layout of the tab
   */
  public void addNewTab(final String tabName, final String noOfColumns) {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().navigate().refresh();
    waitForPageLoaded();
    Link linkAddNewTab = this.engine.findFirstElement(Criteria.isLink().withToolTip("Add New Tab"));
    linkAddNewTab.click();
    Link lnkEdit = this.engine.findFirstElement(Criteria.isLink().withText("New tab"));
    lnkEdit.click();
    this.driverCustom.getWebDriver().findElement(By.id(JRSConstants.DIGIT_FORM_TEXTBOX_0)).sendKeys(tabName);
    Link linkUserProfile = this.engine.findFirstElement(Criteria.isLink().withToolTip("User Profile"));
    linkUserProfile.click();
    Link lnkTab = this.engine.findFirstElement(Criteria.isLink().withText(tabName));
    lnkTab.click();
    Dropdown drdNewTabMenub = this.engine.findFirstElement(Criteria.isDropdown().withToolTip("New tab menu"));
    drdNewTabMenub.click();
    Dropdown drdNewTabMenua = this.engine.findFirstElement(Criteria.isDropdown().withToolTip("New tab menu"));
    drdNewTabMenua.selectOptionWithText("Layout");
    Cell oneCell = this.engine.findElement(Criteria.isCell().withText(noOfColumns)).getFirstElement();
    oneCell.click();
  }

  /**
   * Can be called to delete the already or newly created tab in the dashboard of user profile page. In order to delete
   * the tab, tab name must be provide to this method.
   *
   * @param tabName the name of the tab thats need to be delete and this must not be null
   */
  public void deleteTab(final String tabName) {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().navigate().refresh();
    Link lnkTab =
        this.engine.findElementWithDuration(Criteria.isLink().withText(tabName), timeInSecs).getFirstElement();
    lnkTab.click();
    waitForSecs(Duration.ofSeconds(2));
    Dropdown drdNewTabMenu = this.engine
        .findElementWithDuration(Criteria.isDropdown().withToolTip(tabName + " menu"), timeInSecs).getFirstElement();
    drdNewTabMenu.click();
    drdNewTabMenu.selectOptionWithText("Delete");
    this.driverCustom.getWebDriver().switchTo().alert().accept();
  }

  /**
   * Can be called to rename the already created tab in the dashboard of user profile page. In order to rename the tab
   * name, old name of the tab and new name of the tab must be provide to this method.
   *
   * @param oldName the existing name of the tab, must not be null
   * @param newName the new name of the tab, must not be null
   */
  public void renameTab(final String oldName, final String newName) {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().navigate().refresh();
    Link lnkEdit = this.engine.findFirstElement(Criteria.isLink().withText(oldName));
    lnkEdit.click();
    lnkEdit.click();
    this.driverCustom.getWebDriver().findElement(By.id(JRSConstants.DIGIT_FORM_TEXTBOX_0)).sendKeys(newName);
    Link linkUserProfile = this.engine.findFirstElement(Criteria.isLink().withToolTip("User Profile"));
    linkUserProfile.click();
  }

  /**
   * Can be called to change the layout of the already created tab in the dashboard of the user profile page. In order
   * to change the layout, tab name and number of columns must be provide to this method
   *
   * @param tabName the name of the tab thats need to be update and this must not be null
   * @param noOfColumns the total number of columns in the layout of the tab
   */
  public void changeTabLayout(final String tabName, final int noOfColumns) {
    this.driverCustom.getWebDriver().navigate().refresh();
    clickOn(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebElement(JRSConstants.JAZZDASHBOARDPAGE_SPAN_TEXT_XPATH + tabName + "']", ByType.XPATH),
        Duration.ofSeconds(20));
    String menuXPath = "//span[text()='" + tabName + "']//parent::span//parent::span//span[@class='menu']";
    clickOn(this.driverCustom.getWebDriver(), this.driverCustom.getWebElement(menuXPath, ByType.XPATH),
        Duration.ofSeconds(20));
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECTREQUIREMENT_XPATH, "Layout");
    String columnXPath = null;
    if (noOfColumns == 1) {
      columnXPath = "//td[text()='" + noOfColumns + " Column']";
    }
    else {
      columnXPath = "//td[text()='" + noOfColumns + " Columns']";
    }
    clickOn(this.driverCustom.getWebDriver(), this.driverCustom.getWebElement(columnXPath, ByType.XPATH),
        Duration.ofSeconds(20));
  }

  /**
   * Can be called to add a new widget in a tab in the dashboard of the user profile page. In order to add an new widget
   * in the tab, tab name, widget category and name of the widget must be provide to this method.
   *
   * @param tabName the name of the tab where new widget is going to add and this must not be null
   * @param widgetCategory category name where the widget belongs and this must not be null
   * @param widgetName name of the widget and this must not be null
   */
  public void addDashboardWidget(final String tabName, final String widgetCategory, final String widgetName) {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().navigate().refresh();
    waitForSecs(Duration.ofSeconds(15));
    clickOn(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebElement("//span[text()='" + tabName + "']", ByType.XPATH), timeInSecs);
    clickOn(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebElement("//span[@class='add-widget-span']", ByType.XPATH), timeInSecs);
    clickOn(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebElement("//table[@class='select-left']//span[@class='MenuItemContent']", ByType.XPATH),
        timeInSecs);
    clickOn(this.driverCustom.getWebDriver(), this.driverCustom.getWebElement(
        "//div[@class='categories-site']//a[text()='" + widgetCategory + "']", ByType.XPATH), timeInSecs);
    clickOn(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebElement(
            "//div[@class='com-ibm-team-dashboard-catalog']//input[@aria-label='Search text']", ByType.XPATH),
        timeInSecs);
    WebElement webElementTabName = this.driverCustom.getWebElement(
        "//div[@class='com-ibm-team-dashboard-catalog']//input[@aria-label='Search text']", ByType.XPATH);
    webElementTabName.clear();
    waitForSecs(Duration.ofSeconds(5));
    webElementTabName.sendKeys(widgetName);
    waitForSecs(Duration.ofSeconds(5));
    clickOn(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebElement(
            "//a[text()='" + widgetName + "']//parent::div//following-sibling::div//span[text()='Add Widget']",
            ByType.XPATH),
        timeInSecs);

    clickOn(this.driverCustom.getWebDriver(), this.driverCustom.getWebElement("//img[@title='Close']", ByType.XPATH),
        timeInSecs);
  }

  private static void clickOn(final WebDriver driver, final WebElement locator, final Duration timeout) {
    new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
        .until(ExpectedConditions.elementToBeClickable(locator));
    locator.click();
  }

  /**
   * Select the project area from the All Project page
   *
   * @param projectName the project to be selected
   */
  public void selectProject(final String projectName) {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_SELECTINGCCMPROJECT_A_XPATH,
        Duration.ofSeconds(60))) {
      List<WebElement> projectCount =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_SELECTINGCCMPROJECT_A_XPATH);
      for (WebElement ele : projectCount) {
        if (ele.getText().trim().equals(projectName.trim())) {
          ele.click();
          break;
        }
      }
    }
  }

  /**
   * Click on save button once the changes are done in the dashboard.
   */
  public void saveWidget() {
    waitForPageLoaded();
    this.driverCustom.switchToParentFrame();
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_SAVEBUTTON_INPUTBUTTON_XPATH,
        Duration.ofSeconds(3))) {
      this.driverCustom.click(JRSConstants.JRSALLREPORTPAGE_SAVEBUTTON_INPUTBUTTON_XPATH);
    }

    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_MESSAGESUMMARY_DIV_XPATH,
        Duration.ofSeconds(3))) {
      Reporter.logPass("save process completed");
    }
  }

  /**
   * Click on the "+" (add new tab) option in the Dashboard page.
   */
  public void addNewTabInDashboard() {
    waitForPageLoaded();
    Link lnkTab =
        this.engine.findElementWithDuration(Criteria.isLink().withToolTip("Add New Tab"), timeInSecs).getFirstElement();
    lnkTab.click();
    LOGGER.LOG.info("Clicked on 'Add New Tab' button.");

  }

  /**
   * Add a JRS widget into the dashboard page.
   *
   * @param reportWidgetName the widget name
   */
  public void addWidget(final String reportWidgetName) {
    waitForPageLoaded();

    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_OPENADDWIDGET_SPAN_XPATH,
        Duration.ofSeconds(40))) {
      List<WebElement> widgetCount =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_OPENADDWIDGET_SPAN_XPATH);
      widgetCount.get(0).click();
      waitForSecs(3);
      if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_LOADINGDASHBOARDWIDGET_DIV_XPATH,
          timeInSecs)) {
       if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CATALOGDROPDOWN_DIV_XPATH,
           Duration.ofSeconds(30))) {
          this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_CATALOGDROPDOWN_DIV_XPATH);
        }
      }
      selectReportBuilderCatalog();

      if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_LOADINGMESSAGE_DIV_XPATH,
         timeInSecs)) {

        WebElement widgetSearch =
            this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_WIDGETSEARCH_FILTEROPEN_XPATH);
        widgetSearch.sendKeys(reportWidgetName);
      }
      waitForPageLoaded();
      try {
        WebElement reportCount =
            this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_ADDWIDGETTEAXT_BUTTON_FOLOW_WIDGETNAME_XPATH,reportWidgetName);
        reportCount.click();
      }
      catch (org.openqa.selenium.StaleElementReferenceException ex) {
        List<WebElement> reportCount =
            this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_ADDWIDGETTEAXT_BUTTON_XPATH);
        reportCount.get(1).click();
      }
      
      WebDriver driver = this.driverCustom.getWebDriver();
      driver.switchTo().frame(0);
      if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_LOADINGPROGRESS_DIV_XPATH,
          Duration.ofSeconds(3))) {
        Reporter.logPass("Widget Report Successfully added");
      }
      driver.switchTo().defaultContent();
    }
  }

  private void selectReportBuilderCatalog() {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_CATLOG_CATALOGDROPDOWN_XPATH,
        Duration.ofSeconds(20))) {
      List<WebElement> catalogCount =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CATALOGTABLECOLUMN_COLUMN_XPATH);
      for (WebElement ele : catalogCount) {
        Reporter.logInfo("text is   " + ele.getText().trim());
        if (ele.getText().trim().equals("Report Builder (/rs)")) {
          ele.click();
          break;
        }
      }
    }
    else {
      Reporter.logFailure("adding widget is not successful");
    }
  }

  /**
   * <p>
   * Open 'Dashboard'page.<br>
   * Click on 'New Tab'. <br>
   * Edit tab will display,Set the tab name with current date and time.
   *
   * @param tabName name of the project dashboard tab.
   * @return tab name with current date and time.
   */
  public String editTabName(final String tabName) {
    waitForPageLoaded();
    Link lnkEdit =
        this.engine.findElementWithDuration(Criteria.isLink().withText("New tab"), timeInSecs).getFirstElement();
    lnkEdit.click();
    String newtabName = tabName + DateUtil.getCurrentDateAndTime();
    this.driverCustom.isElementVisible(JRSConstants.NEW_TAB_TEXTBOX_XPATH, timeInSecs);
    this.driverCustom.getWebDriver().findElement(By.xpath(JRSConstants.NEW_TAB_TEXTBOX_XPATH)).sendKeys(newtabName);
    LOGGER.LOG.info("Enter " + tabName + " is the name of new tab.");
    return newtabName;
  }

  /**
   * <p>
   * Open 'Dashboard' page.<br>
   * Click on 'Add Widget' link.
   */
  public void clickOnAddWidgetLink() {
    waitForPageLoaded();
    Link lnkAddWidget =
        this.engine.findElementWithDuration(Criteria.isLink().withText("Add Widget"), timeInSecs).getFirstElement();
    lnkAddWidget.click();
    LOGGER.LOG.info("Clicked on 'Add Widget' link.");
  }

  /**
   * <p>
   * Open 'Dashboard' page.<br>
   * After clicking 'Add Widget' link,select the category.
   *
   * @param category in dashboard page.
   */
  public void selectCategory(final String category) {
    waitForPageLoaded();
    Link lnkCategory =
        this.engine.findElementWithDuration(Criteria.isLink().withText(category), timeInSecs).getFirstElement();
    lnkCategory.click();
    LOGGER.LOG.info("Clicked on '" + category + "' category.");
  }

  /**
   * <p>
   * Open 'Dashboard' page.<br>
   * After adding the widget verify dialog is displayed.
   *
   * @param dialogName name of the dialog.
   * @return true if dialog is displayed.
   */
  @SuppressWarnings("null")
  public boolean isDialogVisible(final String dialogName) {
    waitForPageLoaded();
    Dialog dlgWidget =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(dialogName), timeInSecs).getFirstElement();
    dlgWidget.scrollToElement();
    LOGGER.LOG.info(dialogName + "is showing properly.");
    return dlgWidget != null;
  }

  /**
   * <p>
   * Open 'Dashboard' page.<br>
   * After deleting the tab verify tab is deleted.
   *
   * @param tabName name of the tab.
   * @return true if tab is deleted.
   */
  public boolean isTabDeleted(final String tabName) {
    waitForPageLoaded();
    Link lnkTabVerify = null;
    try {
      lnkTabVerify = this.engine.findElement(Criteria.isLink().withText("tabName")).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return lnkTabVerify == null;
  }

  /**
   * <p>
   * Open 'Dashboard' page.<br>
   * Add the widget with widget name.
   *
   * @param widgetName name of the widget.
   */
  public void addWidgetToDashboard(final String widgetName) {
    waitForPageLoaded();
    this.driverCustom.click(JRSConstants.ADD_WIDGET_TO_DASHBOARD_XPATH, widgetName);
    LOGGER.LOG.info(widgetName + "is added properly.");

  }

  /**
   * <p>
   * Open 'Dashboard' page.<br>
   * Remove the widget using the widget name.
   *
   * @param widgetName name of the widget.
   */
  public void removeWidget(final String widgetName) {
    waitForPageLoaded();
    Dialog dlgWidget =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(widgetName), timeInSecs).getFirstElement();
    Button btnClose = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Remove").inContainer(dlgWidget), timeInSecs)
        .getFirstElement();
    btnClose.click();
    this.driverCustom.getWebDriver().switchTo().alert().accept();
    LOGGER.LOG.info(widgetName + " is removed properly.");
  }

  /**
   * <p>
   * Open 'Dashboard' page.<br>
   * Remove the widget using the widget name. <br>
   * After removing the dialog verify dialog is removed from dashboard.
   *
   * @param widgetName name of the widget.
   * @return true if dialog removed.
   */
  public boolean isDialogRemoved(final String widgetName) {
    waitForPageLoaded();
    Dialog dlgWidgetVerify = null;
    try {
      dlgWidgetVerify = this.engine.findElement(Criteria.isDialog().withTitle(widgetName)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return dlgWidgetVerify == null;
  }

  /**
   * <p>
   * Open 'Dashboard' page.<br>
   * Click on the buttons present in dashboard page.
   *
   * @param button name of the button.
   */
  public void clickOnButtons(final String button) {
    // waitForPageLoaded();
    waitForSecs((Duration.ofSeconds(5)));
    Button btnElement =
        this.engine.findElementWithDuration(Criteria.isButton().withText(button), timeInSecs).getFirstElement();
    btnElement.click();
  }

  /**
   * click on Current Configuration and select sub menu in there
   *
   * @param subMenu or button under Current Configuration context
   */
  public void openAndSelectSubMenuInCurrentConfiguration(final String subMenu) {
    refresh();
    waitForSecs(Duration.ofSeconds(5));
    try {
      Dropdown currentConfiguration =
          this.engine.findElementWithDuration(Criteria.isDropdown().withToolTip("Current Configuration"),
              Duration.ofSeconds((timeInSecs.getSeconds() / 5))).getFirstElement();
      currentConfiguration.selectOptionWithText(subMenu);
      LOGGER.LOG.info("Click on 'Current Configuration' context menu and select " + subMenu);
    }catch (Exception e) {
      clickOn(this.driverCustom.getWebDriver(),
          this.driverCustom.getWebElement("//div[@title='Current Configuration']", ByType.XPATH),
          Duration.ofSeconds(20));
      LOGGER.LOG.info("Click on 'Current Configuration' context menu and select " + subMenu);
    }
    if (this.driverCustom.isElementVisible("//div[text()='Current configuration context has an existing change set']",
        timeInSecs)) {
      Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
      btnOK.click();
    }
    waitForSecs(Duration.ofSeconds(5));
  }

  /**
   * @return the current configuration
   */
  public String getCurrentConfiguration() {
    waitForPageLoaded();
    LOGGER.LOG.info("Get current configuration ");
    if (this.driverCustom.isElementVisible(RMConstants.RMCURRENT_CONFIGURATION_XPATH, Duration.ofSeconds(10))) {
      return this.driverCustom.getText(RMConstants.RMCURRENT_CONFIGURATION_XPATH);
    }
    return this.driverCustom.getText(
        "//span[@title='Current Configuration']//a[@class='configurationUiNode hideLink']//span[@class='titleNode']");
  }

  /**
   * @param name of Baseline, Stream, Change Set...
   * @param description of Baseline, Stream, Change Set...
   * @return the inputed name with date time
   */
  public String createConfiguration(final String name, final String description) {
    try {
      Text txtName =
          this.engine.findElementWithDuration(Criteria.isTextField().hasLabel("Name:"), timeInSecs).getFirstElement();
      txtName.setText(name);
    }
    catch (Exception e) {
      WebElement txbName = this.driverCustom.getWebElement("//input[@id='nameInputBox']");
      txbName.click();
      txbName.sendKeys(name);
    }
    LOGGER.LOG.info("Input configuration name is " + name);
    try {
      Text txtDescription =
          this.engine.findElementWithDuration(Criteria.isTextField().isMultiLine().hasLabel("Description:"),
              Duration.ofSeconds(10)).getFirstElement();
      txtDescription.setText(description);
      LOGGER.LOG.info("Input configuration description is " + description);
    }
    catch (Exception e) {
    }

    waitForSecs(Duration.ofSeconds(2));
    Button btnCreate =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Create"), timeInSecs).getFirstElement();
    btnCreate.click();
    waitForSecs(Duration.ofSeconds(5));
    try {
      LOGGER.LOG.info("Re-try click on Create button");
      Actions action = new Actions(this.driverCustom.getWebDriver());
      WebElement btnCreate1 =
          this.driverCustom.getWebElement("//span[contains(@id,'dijit_form_Button') and text()='Create']");
      action.moveToElement(btnCreate1).click().build().perform();
    }
    catch (Exception e) {
      LOGGER.LOG.info("Clicked on Create button successfully");
    }
    return name;
  }

  /**
   * @param button name of button.
   * @return text of button
   */
  public String clickOnImageButton(final String button) {
    if (this.driverCustom.isElementPresent(
        "//a[@title='" + button + "' or aria-disabled='false']|//button[@title='" + button + "']",
        Duration.ofSeconds(10))) {
      Button spanBtn = this.engine
          .findElementWithinDuration(Criteria.isButton().withToolTip(button), Duration.ofSeconds(5)).getFirstElement();
      spanBtn.click();
      this.driverCustom.switchToDefaultContent();
      LOGGER.LOG.info("Button '" + button + "' is clicked successfully.");
      waitForSecs(Duration.ofSeconds(2));
    }
    else if (this.driverCustom.isElementPresent("//button[@aria-label='" + button + "']", timeInSecs)) {
      this.driverCustom.clickTwice("//button[@aria-label='" + button + "']/.");
    }
    else {
      LOGGER.LOG
          .info("Button '" + button + "' is not found by engine.\n or button '" + button + "'not enabled to click \n ");
      waitForSecs(Duration.ofSeconds(2));
    }
    waitForSecs(Duration.ofSeconds(2));
    return button;
  }

  /**
   * <P>
   * This method use to switch to 2nd tab in the opened browser. <br>
   * Navigate to DNG {@link RMManageComponentPropertiesPage} and click on ReqIF tab. <br>
   * After exporting a particular ReqIF file click on show result and then a new tab will open.
   *
   * @return -
   */
  public String switchTowindowTab() {
    if (this.driverCustom.getWebDriver().getCurrentUrl().contains("reqif/reports")) {
      refresh();
    }
    String main = this.driverCustom.getWebDriver().getWindowHandle();
    Set<String> windows = this.driverCustom.getWebDriver().getWindowHandles();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
    for (String win : windows) {
      if (!win.equals(main)) {
        this.driverCustom.getWebDriver().switchTo().window(win);
        break;
      }
    }
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(15));
    return "";
  }

  /**
   *
   */
  public void switchToWindow() {
    String parentWindowHandle = this.driverCustom.getWebDriver().getWindowHandle();
    Set<String> winHandles = this.driverCustom.getWebDriver().getWindowHandles();
    // Loop through all handles
    for (String handle : winHandles) {
      if (!handle.equals(parentWindowHandle)) {
        this.driverCustom.getWebDriver().switchTo().window(handle);
      }
    }
  }

  /**
   * <p>
   * Get absolute path of target file then using this path on some method to import file
   * ${@link RMArtifactsPage#importArtifactTypes(Map)}
   * <p>
   *
   * @param fileName name of file include the format of file, e.g 'TestData.xlsx'
   * @return the path of file
   */
  public String getAbsoluteFilePathToBeImported(final String fileName) {
    return Paths.get(RMConstants.IMPORT_FILE_LOCATION + fileName).toAbsolutePath().toString();
  }

  /**
   * <P>
   * Enable a checkbox with given criteria value and criteria type which are withToolTip or withLabel.<br>
   * 
   * @author YNT2HC
   * @param criteriaValue is value of criteria
   * @param criteriaType is type of criteria
   */
  public void enableACheckBox(final String criteriaValue, final String criteriaType) {
    Checkbox cbxElement = null;
    try {
      switch (criteriaType) {
        case "withLabel":
          cbxElement = this.engine.findElementWithDuration(Criteria.isCheckbox().withLabel(criteriaValue), timeInSecs)
              .getFirstElement();
          cbxElement.click();
          LOGGER.LOG.info("Checkbox '" + criteriaValue + "' with '" + criteriaType + "' is found by engine.");
          break;
        case "withAriaLabel":
          cbxElement =
              this.engine.findElementWithDuration(Criteria.isCheckbox().withAriaLabel(criteriaValue), timeInSecs)
                  .getFirstElement();
          cbxElement.click();
          LOGGER.LOG.info("Checkbox '" + criteriaValue + "' with '" + criteriaType + "' is found by engine.");
          break;
        case "withToolTip":
          cbxElement = this.engine.findElementWithDuration(Criteria.isCheckbox().withToolTip(criteriaValue), timeInSecs)
              .getFirstElement();
          cbxElement.click();
          LOGGER.LOG.info("Checkbox '" + criteriaValue + "' with '" + criteriaType + "' is found by engine.");
          break;
        default:
          LOGGER.LOG
              .info(criteriaType + " is NOT a proper criteria which is supported by engine.\n Please double-check it!");
      }
    }
    catch (Exception e) {
      LOGGER.LOG.info(
          "Checkbox '" + criteriaValue + "' with '" + criteriaType + "' is NOT found by engine.\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * This method is to remove all widgets displayed
   * <p>
   *
   * @author LTU7HC
   * @param widgetName name of widget.
   */
  public void removeAllWidgets(final String widgetName) {
    String removeWidgetBtn = String.format("//span[text()='%s']/preceding::a[@title='Remove']/img", widgetName);
    WebDriverWait wait =
        new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds((timeInSecs.getSeconds() / 2)));
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(removeWidgetBtn)));
    List<WebElement> lstremoveWidgets = this.driverCustom.getWebDriver().findElements(By.xpath(removeWidgetBtn));
    for (WebElement e : lstremoveWidgets) {
      if (e.isDisplayed() && e.isEnabled()) {
        e.click();
        ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(3));
      }
    }
    LOGGER.LOG.info(String.format("All Widgets with name: '%s' are removed", widgetName));
    this.driverCustom.isElementInvisible(removeWidgetBtn, Duration.ofSeconds(5));
  }
  /**
   * Takes screenshot  of the WebElement
   * @param locator xpath of the WebElement
   * @return screenshot file.
   */
  public File getScreenshotOFWebElement(final String locator)
  {
    
    return driverCustom.getWebElement(locator).getScreenshotAs(OutputType.FILE);
  }

}