/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.ByType;
import com.bosch.jazz.automation.web.common.constants.JazzSettingsEnums.SettingsMenusEnum;
import com.bosch.jazz.automation.web.common.constants.JazzSettingsEnums.SettingsSubMenusEnum;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.pagemodel.AbstractJazzWebPage;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.jazz.automation.web.pagemodel.ManageProjectAreaPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.RadioButton;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.RadioButtonFinder;
import com.bosch.psec.web.test.finder.container.RowFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.JazzTextFinder;

/**
 * Represents the Requirement Management DashBoard Page.
 */
public class RMDashBoardPage extends AbstractRMPage {

  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom required for interacting with the browser.
   */
  public RMDashBoardPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzButtonFinder(), new JazzDropdownFinder(), new RowCellFinder(),
        new JazzDialogFinder(), new JazzRadioButtonFinder(), new JazzTextFinder(),
        new JazzRowFinder(this.driverCustom.getWebDriver()), new LinkFinder(), new RowFinder(),
        new RadioButtonFinder());
  }

  /**
   * <p>
   * Open any Requirement Management project area, project dashboard page is displayed.<br>
   * Click on 'Home Menu' link present in dashboard page.
   */
  public void clickOnHomeMenu() {
    waitForPageLoaded();
    Link btnHomeDropDown =
        this.engine.findElementWithinDuration(Criteria.isLink().withToolTip("Home Menu"), Duration.ofSeconds(10)).getFirstElement();
    btnHomeDropDown.click();
  }

  /**
   * <p>
   * Open any Requirement Management project area, project dashboard page is displayed.<br>
   * Click on 'Home Menu' link present in dashboard page.<br>
   * Click on personal dashboard link.
   */
  public void openPersonalDashboard() {
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_PERSONALDASHBOARD_LINK_XPATH);
  }

  /**
   * <p>
   * Open any Requirement Management project area, project dashboard page is displayed.<br>
   * Click on 'Home Menu' link present in dashboard page using {@link RMDashBoardPage#clickOnHomeMenu()}.<br>
   * Open personal dashboard using {@link RMDashBoardPage#openPersonalDashboard()}.<br>
   * Click on all personal dashboard link.
   *
   * @param dashBoardType refers to personal dashboard type.
   */
  public void clickOnAllPersonalDashboardLink(final String dashBoardType) {
    clickonWebElement(dashBoardType);
  }

  /**
   * <p>
   * Open any Requirement Management project area, project dashboard page is displayed.<br>
   * Click on 'Home Menu' link present in dashboard page using {@link RMDashBoardPage#clickOnHomeMenu()}.<br>
   * Open personal dashboard using {@link RMDashBoardPage#openPersonalDashboard()}.<br>
   * Click on all personal dashboard link using {@link RMDashBoardPage#clickOnAllPersonalDashboardLink(String)}.<br>
   * Click on 'Create dashboard' link, 'Create Dashboard' dialog is displayed.<br>
   * Select the dashboard type and click on 'Finish' button.
   *
   * @param dashBoardType Dashboard Type. Creates personal dashboard based on given dashboard type.
   */
  public void createDashboard(final String dashBoardType) {
    clickonWebElement("Create dashboard");
    Row rowdashBoardType = this.engine.findElement(Criteria.isRow().withText(dashBoardType)).getFirstElement();
    RadioButton rdoDashboardType =
        this.engine.findElement(Criteria.isRadioButton().inContainer(rowdashBoardType)).getFirstElement();
    rdoDashboardType.click();
    Button btnFinish = this.engine.findElement(Criteria.isButton().withText("Finish")).getFirstElement();
    btnFinish.click();
  }

  /**
   * <p>
   * Open any Requirement Management project area, project dashboard page is displayed.<br>
   * Click on 'Home Menu' link present in dashboard page using {@link RMDashBoardPage#clickOnHomeMenu()}.<br>
   * Click on 'All Personal Dashboards', 'All Personal Dashboards' is displayed.<br>
   * Get the List of All personal Dash board Names.
   *
   * @return names of the All personal dashboards on the page.
   */
  public List<String> getListOfAllPersonalDashBoardNames() {
    return this.driverCustom.getWebElementsText(RMConstants.RMDASHBOARDPAGE_PERSONALDASHBOARDSLIST_LINK_XPATH);
  }


  /**
   * <p>
   * Open any Requirement Management project area, project dashboard page is displayed.<br>
   * Click on 'Home Menu' link present in dashboard page using {@link RMDashBoardPage#clickOnHomeMenu()}.<br>
   * Open personal dashboard.<br>
   * Click on 'Add Widget' link,'General' tab is displayed to add the widget.<br>
   * Click on drop down menu to select the category.<br>
   * Search the widget name in 'Search' text box present right side of the page.<br>
   * Add the searched widget.<br>
   * Return the added widget name.
   *
   * @param categoryType choose category from the dropdown like 'Change and Configuration Management','Requirement
   *          Management' etc...
   * @param widgetName name of widget to add after selecting catagory.
   * @return the added widget name
   */
  public String addWidget(final String categoryType, final String widgetName) {
    clickonWebElement("Add Widget");
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_ADDWIDGET_DROPDOWN_XPATH);
    WebElement element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
    while (true) {
      if ((element.getText()).startsWith(categoryType)) {
        ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].click();", element);
        break;
      }
      element.sendKeys(Keys.ARROW_DOWN);
      element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
    }
    WebElement element2 = this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_ADDWIDGET_SEARCHTEXTBOX_XPATH);
    for (int i = 0; i < widgetName.length(); i++) {
      char c = widgetName.charAt(i);
      String s = new StringBuilder().append(c).toString();
      this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(1), widgetName);
      element2.sendKeys(s);
    }
    WebElement ele = this.driverCustom.getWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, widgetName)
        .findElement(By.xpath("../following-sibling :: div[1]/div/div[1]/span/a"));
    ele.click();
    return this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_FIRSTWIDGET_LINK_XPATH).getText();

  }

  /**
   * <p>
   * Open any Requirement Management project area, project dashboard page is displayed.<br>
   * Click on 'Home Menu' link present in dashboard page.<br>
   * Click on personal dashboard link using {@link RMDashBoardPage#openPersonalDashboard()}.<br>
   * Click on 'Delete' icon present right side of the dashboard page.<br>
   * Pop up window displayed for deleting the dashboard.<br>
   * Click on 'OK' to delete the dashboard.
   */
  public void deletePersonalDashbord() {
    Button btnDelete =
        this.engine.findElementWithinDuration(Criteria.isButton().withToolTip("Delete"), Duration.ofSeconds(10)).getFirstElement();
    btnDelete.click();
    this.driverCustom.getWebDriver().switchTo().alert().accept();
  }


  /**
   * <p>
   * Open any Requirement Management project area, project dashboard page is displayed.<br>
   * Click on 'Home Menu' link present in dashboard page.<br>
   * Click on personal dashboard link using {@link RMDashBoardPage#openPersonalDashboard()}.<br>
   * Gives the list of widgets present on the dashboard.
   *
   * @return list of widgets present on the dashboard.
   */
  public List<String> getListOfWidgetsOnDashboard() {
    return this.driverCustom.getWebElementsText(RMConstants.RMDASHBOARDPAGE_WIDGETS_TABLE_XPATH);
  }

  /**
   * <p>
   * Verifies created dashboard saved successfully.
   *
   * @return true if dashboard successfully saved.
   */
  public boolean isDashboardSaved() {
    return getMessageSummary().contains("Dashboard successfully saved");
  }

  /**
   * <p>
   * Verifies created personal dashboard saved successfully.
   *
   * @return true if personal dashboard successfully saved.
   */
  public boolean isPersonalDashBoardSaved() {
    return this.driverCustom.isTextExactMatchWithTheElementText(
        this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_SUCCESSFULLYDASHBOARDSAVED_LABEL_XPATH),
        "Congratulations! You have successfully created a personal dashboard. You can now get started customizing it to suit your interests."
            .trim(),
        3);
  }

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
  public boolean isSomeUserLoggedIn() {
    return this.driverCustom.getWebElementNoException("user-name", ByType.CLASS_NAME) != null;
  }

  /**
   * <p>
   * Open any Requirement Management project area, project dashboard page is displayed.<br>
   * Click on 'Artifacts','Reviews' and ' Reports' menu.
   *
   * @param menu refers to Artifacts, Reviews and Reports.
   */
  public  void openMenu(final String menu) {
    waitForPageLoaded();
    try {
      Link lnkArtifact =
          this.engine.findElementWithDuration(Criteria.isLink().withText(menu), this.timeInSecs).getFirstElement();
      lnkArtifact.click();
      waitForSecs(2);
      waitForPageLoaded();
      LOGGER.LOG.info(menu + " menu opened successfully.");
    }
    catch (Exception e) {
      throw new IllegalArgumentException(menu + ": menu not displayed.\n" + " or\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Click on 'Administration' icon, list of options displayed.<br>
   * 'Create Component' dialog is displayed.<br>
   * Set the component name and description in 'Create Component' dialog.<br>
   * Click on 'Next >' button.<br>
   * Click on 'Finish' button.<br>
   * Click on 'Close' button.
   *
   * @param name component names.
   */
  public void createComponent(final String[] name) {
    waitForPageLoaded();
    while (!this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTCREATECOMPONENT_BUTTON_XPATH, Duration.ofSeconds(2))) {
      openSettingsMenu(SettingsMenusEnum.ADMINISTRATION.toString());
    }
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECTCREATECOMPONENT_BUTTON_XPATH);
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTCOMPONENTNAME_TEXTBOX_XPATH, Duration.ofSeconds(5));
    this.driverCustom.typeText(RMConstants.RMARTIFACTSPAGE_SELECTCOMPONENTNAME_TEXTBOX_XPATH, name[0]);
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTCOMPONENTDESCRIPTION_TEXTBOX_XPATH, Duration.ofSeconds(20));
    this.driverCustom.getWebDriver().findElement(By.xpath("//textarea[@value='']")).sendKeys(name[1]);
    Button btnNext = this.engine.findElement(Criteria.isButton().withText("Next >")).getFirstElement();
    btnNext.click();
    waitForPageLoaded();
    Button btnFinish = this.engine.findElement(Criteria.isButton().withText("Finish")).getFirstElement();
    btnFinish.click();
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, Duration.ofSeconds(5), "Component Setup");
    this.driverCustom.isElementClickable("//button[text()='Close']", Duration.ofSeconds(20));
    Button btnClose = this.engine.findElement(Criteria.isButton().withText("Close")).getFirstElement();
    btnClose.click();
  }

  /**
   * <p>
   * Click on 'Administration' icon.<br>
   * Click on 'Manage Components and Configurations' option,component page is displayed.<br>
   * Click on 'Archive this component to hide it and its contents' icon.<br>
   * Click on 'Archive' button.
   **/
  public void archiveComponent() {
    waitForPageLoaded();
    openSettingsMenu(SettingsMenusEnum.ADMINISTRATION.toString());
    openSettingsSubMenu(SettingsSubMenusEnum.MANAGE_COMPONENTS_AND_CONFIG.toString());
    Button btnArchiveComponent =
        this.engine.findElement(Criteria.isButton().withToolTip("Archive this component to hide it and its contents"))
            .getFirstElement();
    btnArchiveComponent.click();
    Button btnArchive = this.engine.findElement(Criteria.isButton().withText("Archive")).getFirstElement();
    btnArchive.click();
    waitForSecs(5);
  }

  /**
   * <p>
   * Click on 'Administration' icon.<br>
   * Click on 'Manage Components and Configurations' option,component page is displayed.<br>
   * Click on 'Browse Components' link present top of the component and configuration page, 'Browse Components' page is
   * displayed.<br>
   * Click on any of the component name, selected component page is displayed.<br>
   * Edit the component name by clicking on component name.<br>
   * Click on 'Description' tab and edit the Description field.
   *
   * @param name component name and Description
   */
  public void modifyComponentNameAndDescription(final String[] name) {
    waitForPageLoaded();
    openSettingsMenu(SettingsMenusEnum.ADMINISTRATION.toString());
    openSettingsSubMenu(SettingsSubMenusEnum.MANAGE_COMPONENTS_AND_CONFIG.toString());
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTARCHIVEBUTTON_XPATH, Duration.ofSeconds(5));
    String name1 = this.driverCustom.getAttribute(RMConstants.RMARTIFACTSPAGE_GETCOMPONENTNAME_TEXTBOX_XPATH, "value");
    waitForPageLoaded();
    this.driverCustom.typeText(RMConstants.RMARTIFACTSPAGE_SELECTCOMPONENTNAME_MODIFYCOMPONENT_TEXTBOX_XPATH,
        name1 + name[4]);
    this.driverCustom.getWebDriver().findElement(By.xpath("(//span[text()='Description'])[1]")).click();
    waitForPageLoaded();
    this.driverCustom.getWebDriver().findElement(By.xpath("//textarea[@placeholder='Add a description.']"))
        .sendKeys(name[5]);
    Button btnSave = this.engine.findElement(Criteria.isButton().withText("Save")).getFirstElement();
    btnSave.click();
  }

  /**
   * Open artifacts page using {@link RMDashBoardPage #openMenu(String)}.<br>
   * Click on 'Current Configuration Drop down' present next to the configuration name,list of options displayed.<br>
   * Click on required configuration name like 'Create Baseline...','Create Stream...,'Create Change Set...' etc.. <br>
   * If there was a dialog: "Existing Delivery Sessions Detected" select option: "Cancel all the other sessions and
   * continue with this delivery"<br>
   *
   * @param menuItemInConfigMenu - Menu item option of the local configuration. <br>
   *          Ex: 'Create Baseline...',Create Stream...','Deliver Baseline...' etc..
   */
  public void clickOnCurrentConfigurationDropdownMenu(final String menuItemInConfigMenu) {
    waitForSecs(10);
    waitForPageLoaded();
    this.driverCustom.isElementClickable(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH, Duration.ofSeconds(20));
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH);
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, menuItemInConfigMenu);
    switch (menuItemInConfigMenu) {
      case "Create Change Set...":
        String existingChangeSet = "Current configuration context has an existing change set";
        boolean isDialogVisibleV6 =
            this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, Duration.ofSeconds(10), existingChangeSet);
        boolean isDialogVisibleV7 =
            this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_DIALOG_TITLE_XPATH, Duration.ofSeconds(10), existingChangeSet);
        if (isDialogVisibleV6 || isDialogVisibleV7) {
          Button btnOk = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
          btnOk.click();
        }
        break;
      case "Deliver Change Set...":
        waitForSecs(10);
        waitForPageLoaded();
        // If this comment out affects other testscripts, contact YUU3HC
//        if (this.driverCustom.isElementVisible("//div[text()='DYNAMIC_VALUE']", Duration.ofSeconds(10),
//            "Existing Delivery Session Detected")) {
//          WebElement btnCancel = null;
//          try {
//            Button btn = this.engine
//                .findElement(
//                    Criteria.isButton().withText("Cancel all the other sessions and continue with this delivery"))
//                .getFirstElement();
//            btnCancel = this.driverCustom.getClickableWebElement(btn.getWebElement());
//          }
//          catch (Exception e) {
//            Button btn = this.engine
//                .findElement(Criteria.isButton().withText("Cancel the other session and continue with this delivery"))
//                .getFirstElement();
//            btnCancel = this.driverCustom.getClickableWebElement(btn.getWebElement());
//          }
//          btnCancel.click();
//          LOGGER.LOG.info("Clicked on button 'Cancel all the other sessions and continue with this delivery'.");
//        }
//        else
//        {
        if (this.driverCustom.isElementVisible("//*[contains(text(),'Existing Delivery Sessions Detected')]", Duration.ofSeconds(10))) {
          try {
            this.driverCustom.getWebDriver()
                .findElement(By.xpath("//*[text()='Cancel the other session and continue with this delivery']"))
                .click();
            LOGGER.LOG.info("Clicked on button 'Cancel the other session and continue with this delivery'.");
          }
          catch (Exception e) {
            this.driverCustom.getWebDriver()
                .findElement(By.xpath("//*[text()='Cancel all the other sessions and continue with this delivery']"))
                .click();
            LOGGER.LOG.info("Clicked on button 'Cancel all the other sessions and continue with this delivery'.");
          }
        }
        break;
      default:
    }
  }

  /**
   * <p>
   * Click on 'Administration' icon.<br>
   * Click on 'Manage Components and Configurations' option,component page is displayed.<br>
   * Click on the required stream name displayed on component page,selected stream page is displayed.
   * <p>
   * If Config Option is 'Create Change Set...' click on the link text 'Change Sets'.
   * <p>
   * If Config Option is 'Create Stream...' click on the link text 'Streams'.
   * <p>
   * If Config Option is 'Create Baseline...' click on the link text 'Baselines'.
   *
   * @param additionalParams stores key value for 'STREAM_NAME','CONFIG_OPTION' etc..
   */

  public void selectConfigMenu(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String streamName = additionalParams.get("STREAM_NAME");
    String configOption = additionalParams.get(RMConstants.CONFIG_OPTION);
    this.driverCustom.isElementVisible(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(5), streamName);
    waitForPageLoaded();
    clickonWebElement(streamName);
    switch (configOption) {
      case RMConstants.CREATECHANGESET:
        this.driverCustom.isElementClickable(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10), RMConstants.CHANGE_SET);
        Text textChangeSets = this.engine.findFirstElement(Criteria.isLink().withText(RMConstants.CHANGE_SET));
        textChangeSets.click();
        break;
      case RMConstants.CREATESTREAM:
        waitForPageLoaded();
        this.driverCustom.isElementClickable(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10), "Streams");
        Text textStreams = this.engine.findFirstElement(Criteria.isLink().withText("Streams"));
        textStreams.click();
        Dropdown drdStream =
            this.engine.findFirstElement(Criteria.isDropdown().withToolTip(RMConstants.ITEMS_PER_PAGE));
        drdStream.selectOptionWithText("100");
        break;
      case RMConstants.CREATE_BASELINE:
        List<WebElement> lsist =
            this.driverCustom.getWebElements(RMConstants.RMDASHBOARDPAGE_SETTINGS_BASELINECONFIGDROPDOWN_XPATH);
        for (WebElement element : lsist) {
          if (this.driverCustom.isElementVisible(element, Duration.ofSeconds(5))) {
            Dropdown drdBaseline =
                this.engine.findFirstElement(Criteria.isDropdown().withToolTip(RMConstants.ITEMS_PER_PAGE));
            drdBaseline.selectOptionWithText("100");
          }
        }
        break;
      default:
        throw new WebAutomationException(configOption + " is not handle in automation");
    }
  }

  /**
   * <p>
   * Click on 'Administration' icon.<br>
   * Click on 'Manage Components and Configurations' option,component page is displayed.<br>
   * Click on the required stream name displayed on component page,selected stream page is displayed.<br>
   * Click on the required config option using {@link RMDashBoardPage#selectConfigMenu(Map)}.<br>
   * Select the Items per page from displayed drop down.<br>
   * Hover on the selected Baseline,Stream,Change Sets,'Actions' menu displayed.<br>
   * Click on the 'Actions' menu drop down lsit of options displayed.<br>
   * Click on option 'Rename','Archive' as per requirement.
   * <p>
   * If selected option is 'Rename','Rename Baseline' dialog is displayed.<br>
   * Provide new name and click on 'OK' button.
   * <p>
   * If selected option is 'Archive','Archive Warning' pop up is displayed.<br>
   * Click on button 'Archive the configuration and all children'.
   *
   * @param additionalParams stores key value for CONFIG_ACTION_OPTION,'MODIFIED_NAME' etc...
   * @return the message successfully for renamed/archived
   */
  public String configActionMenu(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String st = null;
    waitForPageLoaded();
    Dropdown drdItemsPerPage =
        this.engine.findFirstElement(Criteria.isDropdown().withToolTip(RMConstants.ITEMS_PER_PAGE));
    drdItemsPerPage.selectOptionWithText("10");
    this.driverCustom.isElementClickable(RMConstants.JAZZ_SPANSELECTION_XPATH, Duration.ofSeconds(10),
        additionalParams.get(RMConstants.CONFIG_NAME));
    WebElement element = this.driverCustom.getWebElement(RMConstants.JAZZ_SPANSELECTION_XPATH,
        additionalParams.get(RMConstants.CONFIG_NAME));
    new Actions(this.driverCustom.getWebDriver()).moveToElement(element).build().perform();
    Button btnActions = this.engine.findElement(Criteria.isButton().withToolTip("Actions")).getFirstElement();
    btnActions.click();
    waitForPageLoaded();
    Cell cellOptions =
        this.engine.findElement(Criteria.isCell().withText(additionalParams.get(RMConstants.CONFIG_ACTION_OPTION)))
            .getFirstElement();
    cellOptions.click();
    if (additionalParams.get(RMConstants.CONFIG_ACTION_OPTION).equals("Rename")) {
      this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_SETTINGS_RENAME_TEXTBOX_XPATH).clear();
      Dialog dlgRenameBaseline =
          this.engine.findElement(Criteria.isDialog().withTitle("Rename Baseline")).getFirstElement();
      Text textName =
          this.engine.findFirstElement(Criteria.isTextField().inContainer(dlgRenameBaseline).hasLabel("Name:"));
      textName.setText(additionalParams.get("MODIFIED_NAME"));
    }
    if (additionalParams.get(RMConstants.CONFIG_ACTION_OPTION).equals("Archive")) {
      Button archive = this.engine
          .findElement(Criteria.isButton().withText("Archive the configuration and all children")).getFirstElement();
      archive.click();
    }
    if (this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_MSG_XPATH, Duration.ofSeconds(4))) {
      st = this.driverCustom.getText(RMConstants.RMDASHBOARDPAGE_SETTINGS_MSG_XPATH);
      if (st.trim().equals("The operation was not successful.")) {
        Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
        btnOK.click();
        Link lnkShowmore = this.engine.findFirstElement(Criteria.isLink().withText("show more"));
        lnkShowmore.click();
        st = st + this.driverCustom.getWebDriver().findElement(By.xpath("//div[@class='notificationView']")).getText();
      }
    }
    if (this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, Duration.ofSeconds(1), "OK")) {
      clickOnJazzSpanButtons("OK");
    }
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
    return st;
  }

  /**
   * <p>
   * Open artifacts page using {@link RMDashBoardPage #openMenu(String)}.<br>
   * Click on 'Current Configuration Drop down' present next to the configuration name,list of options displayed.<br>
   * Click on required configuration option like 'Create Baseline...','Create Stream...,'Create Change Set...' etc..
   * using {@link RMDashBoardPage#clickOnCurrentConfigurationDropdownMenu(String)}.
   * <p>
   * If Config option is 'Create Change set' provide change set name and click on 'Create' button in 'Create Change Set'
   * dialog.
   * <p>
   * If Config option is 'Create Stream' provide stream name,enable check box 'Switch to the new stream' inside 'Create
   * Stream' dialog and click on 'OK' button.
   *
   * @param additionalParams stores key value for 'CONFIG_NAME','CONFIG_OPTION' etc..
   */
  public void createConfig(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    if (additionalParams.get(RMConstants.CONFIG_OPTION).equals(RMConstants.CREATECHANGESET) ||
        additionalParams.get(RMConstants.CONFIG_OPTION).equals("Discard Change Set...")) {
      if (additionalParams.get(RMConstants.CONFIG_OPTION).equals(RMConstants.CREATECHANGESET)) {
        if (this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_PROJECTDB_ADDWIDGET_DROPDOWN_SELECTDNG_XPATH,
            Duration.ofSeconds(10), "Current configuration context already change set")) {
          clickOnJazzButtons("OK");
        }
        this.driverCustom.typeText(RMConstants.RMDASHBOARDPAGE_SETTINGS_CHANGESETNAMEBOX_XPATH,
            additionalParams.get(RMConstants.CONFIG_NAME));
        clickOnDialogButton("Create Change Set", "Create");
      }
      else {
        this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_CHANGESETS_BOX_XPATH, Duration.ofSeconds(2));
        Button btnDiscardChangeSet =
            this.engine.findElement(Criteria.isButton().withText("Discard the Change Set")).getFirstElement();
        btnDiscardChangeSet.click();
      }
    }
    else {
      waitForPageLoaded();
      this.driverCustom.typeText(RMConstants.RMDASHBOARDPAGE_NAME_TEXTBOX_XPATH,
          additionalParams.get(RMConstants.CONFIG_NAME));
      waitForPageLoaded();
      this.driverCustom.typeText(RMConstants.RMDASHBOARDPAGE_DESCRIPTION_TEXTBOX_XPATH,
          "Baseline created by test team");
      if (additionalParams.get(RMConstants.CONFIG_OPTION).equals(RMConstants.CREATESTREAM)) {
        Dialog dlgCreateStream =
            this.engine.findElement(Criteria.isDialog().withTitle("Create Stream")).getFirstElement();
        Checkbox cbxSwitchStream = this.engine
            .findElement(Criteria.isCheckbox().inContainer(dlgCreateStream).withLabel("Switch to the new stream"))
            .getFirstElement();
        cbxSwitchStream.click();
      }
      try {
        WebElement element =
            this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
        element.sendKeys(Keys.TAB);
        element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
        element.click();
        waitForPageLoaded();
      }
      catch (Exception e) {}
      Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
      btnOK.click();
    }
    waitForPageLoaded();
  }

  /**
   * <p>
   * Search Artifact Id,Artifact Name,Module Id,Collections Id etc... in quick search text box present on right side top
   * of the 'Requirement Management' page.
   *
   * @param name element to be searched.
   * @return element is available or not.
   */
  public boolean quickSearch(final String name) {
    this.driverCustom.isElementPresent(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() * 2)));
    try {
      String xpathQuickSearch = "//input[@class='SearchInputText']";
      WebElement txtQuickSearch = this.driverCustom.getWebElement(xpathQuickSearch);
      txtQuickSearch.clear();
//      this.driverCustom.typeCharByChar(xpathQuickSearch, name, Duration.ofSeconds(1));
      //TypeCharByChar too SLOW. Replace with sendKeys, if fails contact YUU3HC
      txtQuickSearch.sendKeys(name);
      waitForSecs(5);
      txtQuickSearch.click();
      txtQuickSearch.sendKeys(Keys.ENTER);
    }
    catch (Exception e) {
      Text txtSearchField = this.engine.findFirstElementWithDuration(Criteria.isTextField().withText("Search Artifacts"), this.timeInSecs);
      txtSearchField.clearText();
      txtSearchField.setText(name + Keys.ENTER);
    }
    // Wait for quick search loading disappeared
   // this.driverCustom.isElementInvisible(RMConstants.QUICK_SEARCH_BOX_LOADING_XPATH, this.timeInSecs);
    if(this.driverCustom.isElementInvisible(RMConstants.QUICK_SEARCH_BOX_LOADING_XPATH, Duration.ofSeconds(5))) {
    this.driverCustom.isElementInvisible(RMConstants.QUICK_SEARCH_BOX_LOADING_XPATH, this.timeInSecs);
    }
    LOGGER.LOG.info(name + " - searched in the quick search text box.");
    return true;
  }

  /**
   * Search Artifact Id,Artifact Name,Module Id,Collections Id etc... in quick search text box present on right side top
   * of the 'Requirement Management' page. <br>
   * Apply search for project name or all projects <br>
   *
   * @author VDY1HC
   * @param searchText - element to be searched.
   * @param projectName - Name of project or / All Projects
   */
  public void quickSearchFilterByProject(final String searchText, final String projectName) {
    this.driverCustom.isElementPresent(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH, timeInSecs);
    WebElement btnScope = this.driverCustom.getPresenceOfWebElement("//img[@alt='Scope']");
    this.driverCustom.getClickableWebElement(btnScope).click();
    WebElement menuItem =
        this.driverCustom.getPresenceOfWebElement(RMConstants.QUICKSEARCH_SCOPE_BY_NAME_XPATH, projectName);
    menuItem.click();
    WebElement txbSearch = this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH);
    txbSearch.click();
    txbSearch.sendKeys(Keys.CONTROL, "a");
    txbSearch.sendKeys(Keys.DELETE);
    txbSearch.sendKeys(searchText + Keys.ENTER);
    if (this.driverCustom.isElementVisible("//div[@class='status-info' and text()='Loading...']", Duration.ofSeconds(5))) {
      this.driverCustom.isElementInvisible("//div[@class='status-info' and text()='Loading...']", timeInSecs);
    }
  }

  /**
   * <p>
   * Search any existing Artifacts,Modules,Collections using Id or Name.<br>
   * Open the searched requirement management specification.
   *
   * @param artifactIDOrName ID or Name of the requirement management specification.
   */
  public void openSearchedSpecification(final String artifactIDOrName) {
    waitForSecs(5);
    List<WebElement> results = this.driverCustom
        .getWebElements("//div[@class='search-area']//div[@class='results-area']/div[@class='search-result']")
        .stream().filter(x -> x.getText().contains(artifactIDOrName) && !x.getText().contains("In Module: ")).collect(Collectors.toList()); //add filter condition, if fails contact YUU3HC
    if (!results.isEmpty()) {
      new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(120))
          .until(new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(final WebDriver input) {
              try {
                Optional<WebElement> allLinks = results.stream().map(x -> x.findElement(By.xpath(".//a")))
                    .collect(Collectors.toList()).stream().findFirst();
                if (allLinks.isPresent()) {
                  return allLinks.get();
                }

              }
              catch (Exception j) {
                try {
                  Optional<WebElement> link =
                      results.stream().filter(x -> x.getText().contains(artifactIDOrName)).findFirst();
                  if (link.isPresent()) {
                    return link.get();
                  }
                }
                catch (StaleElementReferenceException e) {
                  LOGGER.LOG.error(e.getMessage());
                }
              }
              return null;
            }
          }).click();
    }
    waitForSecs(10);
    try {
      this.driverCustom.click(RQMConstants.RQMCONSTRUCTIONPAGE_BANNER_TITLE_XPATH);
      waitForSecs(2);
    }
    catch (Exception e) {}
    LOGGER.LOG.info("Clicked on " + artifactIDOrName + " from searched artifact content.");
  }

  /**
   * <p>
   * Click on 'Administration' icon,list of options displayed.<br>
   * Click on 'Manage This Project Area' option, 'Manage Project Area' page is displayed.<br>
   * Click on 'Configuration Management' option.<br>
   * Verify the successful message displayed down of the page.
   *
   * @param name use to pass Project area name
   * @return checking Project area CM enable or not enabled
   */
  public boolean checkCMEnable(final String[] name) {
    waitForPageLoaded();
    ManageProjectAreaPage dng = new ManageProjectAreaPage(this.driverCustom);
    dng.openSettingsMenu(SettingsMenusEnum.ADMINISTRATION.toString());
    dng.openSettingsSubMenu(SettingsSubMenusEnum.MANAGE_THIS_PROJECT_AREA.toString());
    dng.tabSection(name[0]);
    return this.driverCustom
        .isEnabled(RMConstants.RMARTIFACTPAGE_MANAGETHISPROJECTAREA_ENABLECONFIGURATIONMANAGEMENT_ENABLE_XPATH);
  }

  /**
   * <p>
   * use to check compare configuration enabled or not if only one stream is there it use to create a stream and check
   * compare configuration enabled or not.
   * <p>
   * NOTE:-Pages not found.
   */
  public void compareConfiguration() {
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIG_STREAM_EXPAND_XPATH,
        Duration.ofSeconds(3))) {
      Link lnkExpand = this.engine.findFirstElement(Criteria.isLink().withToolTip("Expand"));
      lnkExpand.click();
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIG_STREAM_NAME_XPATH, Duration.ofSeconds(3));
      List<WebElement> artifactList =
          this.driverCustom.getWebElements(RMConstants.RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIG_STREAM_NAME_XPATH);
      String[] stream = new String[artifactList.size()];
      int i = 0;
      for (WebElement a : artifactList) {
        stream[i] = a.getText();
        i++;
      }
      for (int j = 1; j < 3; j++) {
        Actions action = new Actions(this.driverCustom.getWebDriver());
        this.driverCustom.isElementPresent(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, Duration.ofSeconds(3), stream[j]);
        WebElement str = this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_STREAM_NAME_XPATH, stream[j]);
        action.moveToElement(str).build().perform();
        this.driverCustom.isElementPresent(
            RMConstants.RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIG_STREAMSELECT_CHECKBOX_XPATH, Duration.ofSeconds(3), stream[j]);
        WebElement e = this.driverCustom
            .getWebElement(RMConstants.RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIG_STREAMSELECT_CHECKBOX_XPATH, stream[j]);
        action.moveToElement(e).click().build().perform();
      }
    }
    else {

      Actions action = new Actions(this.driverCustom.getWebDriver());
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIGACTION_DROPDOWN_XPATH, Duration.ofSeconds(3));
      WebElement placeholder = this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_STREAM_PLACEHOLDER_XPATH);
      action.moveToElement(placeholder).build().perform();
      WebElement dropdown =
          this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIGACTION_DROPDOWN_XPATH);
      action.moveToElement(dropdown).click().build().perform();
      Button btnActions = this.engine.findElement(Criteria.isButton().withToolTip("Actions")).getFirstElement();
      btnActions.click();
      Cell cellCreateStream = this.engine.findElement(Criteria.isCell().withText("Create Stream")).getFirstElement();
      cellCreateStream.click();
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_CREATESTREAM_TEXTBOX_XPATH)
          .sendKeys("Stream_" + DateUtil.getCurrentDateAndTime());
      Button btnCreate = this.engine.findElement(Criteria.isButton().withText("Create")).getFirstElement();
      btnCreate.click();
      clickOnJazzSpanButtons("OK");
    }
  }

  /**
   * <p>
   * Click on 'Administration' icon using {@link AbstractJazzWebPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Components and Configurations' option using
   * {@link AbstractJazzWebPage#openSettingsSubMenu(String)}.<br>
   * Component page will display.<br>
   * Verify 'Compare the selected configurations' button is enabled present right side of the page.
   *
   * @return true if 'Compare the selected configurations' button is enabled.
   */
  public boolean compareConfigrationEnabled() {
    return this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_COMPARECONFIGENABLED_BUTTON_XPATH, Duration.ofSeconds(1));
  }

  /**
   * *
   * <p>
   * Click on 'Administration' icon using {@link AbstractJazzWebPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Components and Configurations' option using
   * {@link AbstractJazzWebPage#openSettingsSubMenu(String)}.<br>
   * Component page will display.<br>
   * Verify 'Expand Node Hidden' button is present.
   *
   * @return true if Expand Button is Hidden.
   */
  public boolean expandButtonHidden() {
    return this.driverCustom.isElementInvisible(RMConstants.RMARTIFACTSPAGE_EXPAND_BUTTON_XPATH, Duration.ofSeconds(1));
  }

  /**
   * <p>
   * Click on 'Administration' icon.<br>
   * Click on 'Manage Components and Configurations' option,component page is displayed.<br>
   * Click on the required stream name displayed on component page,selected stream page is displayed.<br>
   * Click on 'Change Sets' tab on the page.<br>
   * Click on the button 'Include change sets from other users'present right side of the page near to 'Overview'
   * section.<br>
   * Choose the Items Per Page value as '100'.<br>
   * Click on 'Refresh' button present right side of the page near to 'Overview' section.
   *
   * @return Number of change sets are equal after multiple refresh
   **/
  public boolean checkNumberOfChangesets() {
    waitForPageLoaded();
    ManageProjectAreaPage dng = new ManageProjectAreaPage(this.driverCustom);
    Actions action = new Actions(this.driverCustom.getWebDriver());
    dng.openSettingsMenu(SettingsMenusEnum.ADMINISTRATION.toString());
    dng.openSettingsSubMenu(SettingsSubMenusEnum.MANAGE_COMPONENTS_AND_CONFIG.toString());
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIG_STREAM_NAME_XPATH, Duration.ofSeconds(10));
    List<WebElement> artifactList =
        this.driverCustom.getWebElements(RMConstants.RMARTIFACTPAGE_MANAGECOMPONENTANDCONFIG_STREAM_NAME_XPATH);
    String[] stream = new String[artifactList.size()];
    int i = 0;
    for (WebElement a : artifactList) {
      stream[i] = a.getText();
      i++;
    }
    for (int j = 1; j <= 1; j++) {
      this.driverCustom.isElementPresent(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, Duration.ofSeconds(3), stream[j]);
      WebElement str = this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_STREAM_NAME_XPATH, stream[j]);
      action.moveToElement(str).click().build().perform();
    }
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_CHANGESETS_TAB_XPATH, Duration.ofSeconds(5));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_CHANGESETS_TAB_XPATH);
    waitForPageLoaded();
    this.driverCustom.isElementClickable(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10), "Change Sets");
    Text textChangeSets = this.engine.findFirstElement(Criteria.isLink().withText("Change Sets"));
    textChangeSets.click();
    Button btnIncludechangesetsfromotherusers = this.engine
        .findElement(Criteria.isButton().withToolTip("Include change sets from other users")).getFirstElement();
    btnIncludechangesetsfromotherusers.click();
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_PAGESIZECONTROL_DROPDOWN_XPATH);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_PAGESIZECONTROL_DROPDOWN_XPATH);
    Dropdown drdItemsPerPage =
        this.engine.findFirstElement(Criteria.isDropdown().withToolTip(RMConstants.ITEMS_PER_PAGE));
    drdItemsPerPage.selectOptionWithText("100");
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_CHANGESETSNUMBERS_TAB_XPATH);
    List<String> artifactlist =
        this.driverCustom.getWebElementsText(RMConstants.RMARTIFACTPAGE_CHANGESETSNUMBERS_TAB_XPATH);
    final int beforeRefresh = artifactlist.size();
    for (int k = 0; k < 5; k++) {
      Button btnRefresh = this.engine.findElement(Criteria.isButton().withToolTip("Refresh")).getFirstElement();
      btnRefresh.click();
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_CHANGESETSNUMBERS_TAB_XPATH);
    }
    List<String> artList = this.driverCustom.getWebElementsText(RMConstants.RMARTIFACTPAGE_CHANGESETSNUMBERS_TAB_XPATH);
    final int afterRefresh = artList.size();
    return beforeRefresh == afterRefresh;
  }

  /**
   * <p>
   * Create a new change set, do some changes and deliver the change set. <br>
   * Change set can be deliver with different option like 'Standard','Express','Custom' etc..
   *
   * @param changeSetOption is delivery option of the change set.
   */
  public void chooseChangeSetDeliveryOption(final String changeSetOption) {
    waitForSecs(10);
    if (this.driverCustom.isElementVisible("//div[text()='DYNAMIC_VAlUE']", Duration.ofSeconds(20),
        "Existing Delivery Sessions Detected")) {
      Button btn = this.engine
          .findElement(Criteria.isButton().withText("Cancel all the other sessions and continue with this delivery"))
          .getFirstElement();
      btn.click();
      LOGGER.LOG.info("Clicked on button 'Cancel all the other sessions and continue with this delivery'.");
    }
    waitForSecs(5);
    RadioButton rdoChangeSetOption =
        this.engine.findElement(Criteria.isRadioButton().withText(changeSetOption)).getFirstElement();
    rdoChangeSetOption.click();
    LOGGER.LOG.info("Clicked on radio button with option " + changeSetOption);
    waitForSecs(5);
  }

  /**
   * <p>
   * Create a new change set, do some changes and deliver the change set.<br>
   * Enable the change set delivery mode for conflicts as required.
   *
   * @param option delivery option.
   */
  public void enableChangeSetDeliveryModeForConflicts(final String option) {
    Checkbox chbx = this.engine.findElement(Criteria.isCheckbox().withLabel(option)).getFirstElement();
    chbx.click();
    LOGGER.LOG.info(option + " - is enabled while delivering change set.");
  }

  /**
   * <p>
   * Deliver the change set with created artifact. <br>
   * Get the id of the artifact.
   *
   * @param artifactID id of the artifact.
   * @return true if the artifactID visible.
   */
  public boolean getDeliveredArtifact(final String artifactID) {
    LOGGER.LOG.info("Verified the delivered artifact with id - " + artifactID);
    return this.driverCustom.isElementVisible(
        "//div[@dojoattachpoint='_nameIdNode']/table[@role='presentation']/tr//td[contains(text(),'DYNAMIC_VAlUE')]",
        Duration.ofSeconds(30), artifactID);
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Artifacts");
    String currentWindow = this.driverCustom.getWebDriver().getWindowHandle();
    Set<String> windows = this.driverCustom.getWebDriver().getWindowHandles();

    if (windows.size() > 1) {
      // Now iterate using Iterator
      Iterator<String> window = windows.iterator();
      while (window.hasNext()) {
        String childWindow = window.next();
        if (!currentWindow.equals(childWindow)) {
          // Switch to the SPNEGO window to take screenshot and write to log file.
          this.driverCustom.getWebDriver().switchTo().window(childWindow);
          String childWindowTitle = this.driverCustom.getWebDriver().switchTo().window(childWindow).getTitle();
          LOGGER.LOG.info(" New window : " + childWindowTitle);
          // Handle SPNEGO Authentication Dialog
          if (childWindowTitle.contains("SPNEGO authentication is not supported.")) {
            LOGGER.LOG.info("'" + childWindowTitle + "' --> new window found automatic test script will not compleate");
            throw new WebAutomationException(
                " Unexpected window found with message 'SPNEGO authentication is not supported.' ");
          }
        }
      }
    }
  }

  /**
   * waitForPageLoadedHandleSPNEGODialog method is use to wait for a element which is common in this current page.
   * However, some servers will display SPNEGO authentication dialog. And this method to by-pass this dialog by closing
   * SPNEGO dialog, open a second window to login with the logged account. Then back to current window to start with the
   * next action
   *
   * @author KYY1HC
   * @param username username to login application
   * @param url repository URL to login
   */
  public void waitForPageLoadedHandleSPNEGODialog(final String username, final String url) {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Artifacts");
    String parentWindow = this.driverCustom.getWebDriver().getWindowHandle();
    Set<String> lstWindow = this.driverCustom.getWebDriver().getWindowHandles();

    // Now iterate using Iterator
    Iterator<String> window = lstWindow.iterator();
    while (window.hasNext()) {
      String childWindow = window.next();
      if (!parentWindow.equals(childWindow)) {
        this.driverCustom.getWebDriver().switchTo().window(childWindow);
        String windowTitle = this.driverCustom.getWebDriver().switchTo().window(childWindow).getTitle();
        if (this.driverCustom.getWebDriver().switchTo().window(childWindow).getTitle()
            .contains("SPNEGO authentication is not supported.")) {
          LOGGER.LOG.info("'" + windowTitle + "' --> new window found automatic test script will not compleate");
          // handle to by-pass SPNEGO Authentication Dialog.
          this.driverCustom.getWebDriver().close();
          LOGGER.LOG.info(windowTitle + "--> window is closed");
          // open second window
          // login with current user
          // close all windows and switch to current window
        }

        // switch to the parent window
        this.driverCustom.getWebDriver().switchTo().window(parentWindow);
        LOGGER.LOG.info(" --> current window");
      }
    }
  }

  /**
   * <p>
   * * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Create Component' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}, 'Create Component'
   * dialog is displayed.<br>
   * Input component name and description. <br>
   * Click on 'Next >' button. <br>
   * Check on 'Use a template to initially populate the component' checkbox. <br>
   * Select one template to create a new component. <br>
   * Click on 'Finish' button
   * <p>
   *
   * @author NVV1HC
   * @param name name of component should be created
   * @param description of component
   * @param templateName template name using to create a new component
   */
  public void createComponentUsingTemplate(final String name, final String description, final String templateName) {
    waitForPageLoaded();
    openSettingsMenu("Administration");
    openSettingsSubMenu("Create Component");
    Text componentNameTextbox =
        this.engine.findElementWithDuration(Criteria.isTextField().hasLabel("Component name:*"), this.timeInSecs)
            .getFirstElement();
    componentNameTextbox.setText(name);
    Text descriptionTextbox = this.engine
        .findElementWithDuration(Criteria.isTextField().hasLabel("Description:"), this.timeInSecs).getFirstElement();
    descriptionTextbox.setText(description);
    Button btnNext =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >"), this.timeInSecs).getFirstElement();
    btnNext.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.getPresenceOfWebElement(RMConstants.USEATEMPLATETOINITIALLYPOPULATETHECOMPONENT_CHECKBOX_XPATH)
        .click();
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RMConstants.TEMPLATENAME_CLONEFROMACOMPONENT_XPATH, templateName).click();
    Button btnFinish =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Finish"), this.timeInSecs).getFirstElement();
    btnFinish.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
  }

  /**
   * <p>
   * Click on the 'Current Configuration' icon then select 'Discard Change Set...' option in the Requirement Management
   * Page using {@link RMDashBoardPage#openAndSelectSubMenuInCurrentConfiguration(String)}.<br>
   * Click on 'Discard the Change Set' button on 'Discard the Change Set' confirmation dialog. <br>
   * <p>
   *
   * @author NVV1HC
   * @param changeSetName name of change set that you want to discard
   */
  public void discardChangeset(final String changeSetName) {
    openAndSelectSubMenuInCurrentConfiguration("Discard Change Set...");
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_CHANGESETS_BOX_XPATH, Duration.ofSeconds(10));
    Button btnDiscardChangeSet =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Discard the Change Set"), this.timeInSecs)
            .getFirstElement();
    btnDiscardChangeSet.click();
    waitForPageLoaded();
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
  }

  /**
   * <p>
   * Click on the 'Current Configuration' icon and verify if the configuration is enabled or not
   * <p>
   *
   * @author NVV1HC
   * @param name name of configuration, e.g: Create Baselines..., Compare Configuration...
   * @return true if the configuration is enabled or vice versa
   */
  public boolean checkCurrentConfigurationsEnabled(final String name) {
    waitForPageLoaded();
    this.driverCustom.isElementClickable(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH, Duration.ofSeconds(20));
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH);
    waitForSecs(3);
    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.CONFIGURATION_XPATH, name);
      return true;
    }
    catch (Exception e) {
      fail("The Configuration '" + name + "' is not displayed!");
      return false;
    }
  }

  /**
   * Select Stream to delivery changes from Component to a Stream <br>
   * This method called after {@link RMDashBoardPage#clickOnCurrentConfigurationDropdownMenu(String)} with option
   * "Delivery Changes..." <br>
   *
   * @author VDY1HC
   * @param streamName - name of local stream to delivery changes
   */
  public void deliveryChangesToAStream(final String streamName) {
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
    this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
    WebElement searchbox =
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_SEARCH_TEXTBOX_XPATH);
    searchbox.click();
    searchbox.clear();
    searchbox.sendKeys(streamName + Keys.ENTER);
    this.driverCustom.isElementInvisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTCOMP_XPATH, Duration.ofSeconds(15), streamName);
    WebElement optStream = this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTCOMP_XPATH, streamName);
    this.driverCustom.clickUsingActions(optStream);
    Button btnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOK.click();
    try {
      Dialog dlgExistingDeliver =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Existing Delivery Session Detected"), Duration.ofSeconds(30))
              .getFirstElement();
      Button btnCancel = this.engine
          .findElementWithDuration(Criteria.isButton()
              .withText("Cancel all the other sessions and continue with this delivery").inContainer(dlgExistingDeliver), Duration.ofSeconds(10))
          .getFirstElement();
      btnCancel.click();
    }
    catch (Exception e) {
      // No existing Deliver session
    }
    waitForSecs(60);
    this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CHECKING_HEADER_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() *3)), "Deliver Change Sets");
  }

  /**
   * Click on "Next >" in delivery changes set view, until found wanted section This method called after
   * {@link RMDashBoardPage#chooseChangeSetDeliveryOption(String)}
   *
   * @param sectionName - name of Section expected
   */
  public void clickOnNextButtonToDeliverySpecificSection(final String sectionName) {
    waitForSecs(30);
    WebElement currentSectionTitle = null;
    try {
      currentSectionTitle =
          this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_CURRENT_DELIVERY_SECTION_TITLE);
    }
    catch (Exception e) {
      waitForSecs(30);
      currentSectionTitle =
          this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_CURRENT_DELIVERY_SECTION_TITLE);
    }
    String currentSection = currentSectionTitle.getText();
    while (!currentSection.equalsIgnoreCase(sectionName)) {
      waitForSecs(5);
      this.driverCustom
          .getClickableWebElement(this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_SELECT_NEXTBUTTON_XPATH))
          .click();
      currentSectionTitle =
          this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_CURRENT_DELIVERY_SECTION_TITLE);
      currentSection = currentSectionTitle.getText();
    }
  }

  /**
   * Open dropdown dialog and check on option if option is not checked
   *
   * @author VDY1HC
   * @param drdToolTip - Tooltip of filter dropdown
   * @param option - option to be checked
   * @param isChecked - Expected state of option is checked or not if checked: isChecked = true
   * @return true - if perform action click false - if not perform action click
   */
  public boolean selectCheckboxInDropDownWhenDeliveryChanges(final String drdToolTip, final String option,
      final String isChecked) {
    this.driverCustom.switchToDefaultContent();
    WebElement drdFilter = null;
    switch (drdToolTip) {
      case "Filter Artifacts":
        drdFilter = this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_FILTER_SECTION_DROPDOWN_XPATH,
            drdToolTip);
        break;
      case "Filter by change type":
        drdFilter = this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_FILTER_TYPE_DROPDOWN_XPATH,
            drdToolTip);
        break;
      default:
        throw new WebAutomationException("Dropdown is not defined");
    }
    this.driverCustom.getClickableWebElement(drdFilter).click();
    WebElement selectOption =
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_FILTER_RADIOBUTTON_XPATH, option);
    if (!selectOption.getAttribute("aria-checked").equalsIgnoreCase(isChecked)) {
      this.driverCustom.clickUsingActions(selectOption);
      return true;
    }
    return false;
  }


  /**
   * @author UUM4KOR
   *         <p>
   *         This method used after clicking add widget button using { #addWidgetToDashboard}
   */
  public void clickOnSelectAViewLink() {
    Link lnkSelectAReview =
        this.engine.findFirstElementWithDuration(Criteria.isLink().withText("select a view"), this.timeInSecs);
    lnkSelectAReview.click();
    LOGGER.LOG.info(" 'select a view' link clicked successfully.");

  }

  /**
   * @author UUM4KOR This method used after {@link #RMDashBoardPage(WebDriverCustom)
   *         ,RMDashBoardPage(WebDriverCustom)#clickOnSelectAlinkView}
   * @param attribute attribute name ex: View
   * @param value select searched value ex:All briBk1 SW RS
   */
  public void selectViewValue(final String attribute, final String value) {
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement("//label[text()='" + attribute + "']//..//..//Button[text()='Select...']")
        .click();
    LOGGER.LOG.info(" clicked select.. button of " + attribute + " successfully.");
    // Search and select user
    Dialog dlgSelectViews =
        this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Choose a view"), this.timeInSecs);
    // Search and select approver
    try {
      Text txtSearchViews = this.engine.findFirstElementWithDuration(
          Criteria.isTextField().withPlaceHolder("Search Views").inContainer(dlgSelectViews), this.timeInSecs);
      txtSearchViews.setText(value);
      LOGGER.LOG.info("'" + value + "'  set text in Search Views successfully.");
    } catch (Exception e) {}
    this.driverCustom.getPresenceOfWebElement("//div[@class='content-area']//span[text()='" + value + "']").click();
    LOGGER.LOG.info(" select a '" + value + "' clicked successfully.");
    Button btnOK1 = this.engine
        .findFirstElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgSelectViews), this.timeInSecs);
    btnOK1.click();
    LOGGER.LOG.info("OK button in 'select a view' dialog is clicked successfully.");
    Button btnOK = this.engine.findFirstElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs);
    btnOK.click();
    LOGGER.LOG.info("OK button in 'Requirements View' new widget is clicked successfully.");
    Button btnSave = this.engine.findFirstElementWithDuration(Criteria.isButton().withText("Save"), this.timeInSecs);
    btnSave.click();
    LOGGER.LOG.info("Save button clicked successfully.");
  }

  /**
   * @author LTU7HC
   *         <p>
   *         This method used to select values for Requirements View
   *         <p>
   *         This method used after clicking on 'select a view' in 'Requirements View' {@link RMDashBoardPage#clickOnSelectAViewLink()}
   * @param additionalParams stores key value for 'PROJECT','COMPONENT', 'CONFIGURATION', 'MODULE', 'VIEW', 'ITEMS_TO_SHOW'
   */
  public void selectValuesForRequirementsView(final Map<String, String> additionalParams) {
    String project =  additionalParams.get("PROJECT");
    String component = additionalParams.get("COMPONENT");
    String configuration = additionalParams.get("CONFIGURATION");
    String module = additionalParams.get("MODULE");
    String view = additionalParams.get("VIEW");
    String itemsToShow = additionalParams.get("ITEMS_TO_SHOW");
    // Select Project
    if (!project.equals("")) {
      try {
        final String selectProjectArea = "Select Project Area";
        this.driverCustom.getWebElementClickbale(RMConstants.SELECT_PROJECT_BUTTON_IN_REQUIREMENTS_VIEW_XPATH).click();
        this.driverCustom.isElementVisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), selectProjectArea);
        this.driverCustom.getWebElementClikbale(RMConstants.SELECTPROJECTAREA_VALUE_IN_REQUIREMENTS_VIEW_XPATH, project)
            .click();
        clickOnDialogButton(selectProjectArea, "OK");
        LOGGER.LOG.info(String.format("Select 'Project' as '%s' successfully", project));
        this.driverCustom.isElementInvisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), selectProjectArea);
      }
      catch (Exception e) {
        LOGGER.LOG.error("Can not select 'Project' " + e.getMessage());
      }
    }

    // Select Component
    if ((!component.trim().equals(""))&&(!component.trim().equals("Use Current Component"))) {
      try {
        String selectTheComponentContext = "Select the Component Context";
        this.driverCustom.getWebElementClickbale(RMConstants.SELECT_COMPONENT_BUTTON_IN_REQUIREMENTS_VIEW_XPATH)
            .click();
        this.driverCustom.isElementVisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), selectTheComponentContext);
        this.waitForSecs(3);
        Text txtSearch = this.engine
            .findElementWithinDuration(
                Criteria.isTextField().withPlaceHolder("Type to search names (enter * to show all)"), Duration.ofSeconds(60))
            .getFirstElement();
        txtSearch.setText(component.trim());
        this.waitForSecs(3);
        this.driverCustom.switchToFrame("//iframe[@title='Component Picker']");
        this.driverCustom
            .getWebElementClikbale(RMConstants.SELECTTHECOMPONENTCONTEXT_VALUE_IN_REQUIREMENTS_VIEW_XPATH, component)
            .click();
        clickOnButtons("OK");
        this.driverCustom.switchToDefaultContent();
        LOGGER.LOG.info(String.format("Select 'Component' as '%s' successfully", component));
        this.driverCustom.isElementInvisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), selectTheComponentContext);
      }
      catch (Exception e) {
        LOGGER.LOG.error("Can not select 'Component' " + e.getMessage());
      }
    }
    
    // Select Configuration
    if ((!configuration.trim().equals("")) && (!configuration.trim().equals("Use Current Configuration"))) {
      try {
        final String selectTheConfigurationContext = "Select the Configuration Context";
        String gloalConfigXpath = "//label[normalize-space(text())='Global Configuration']";
        this.driverCustom.getWebElementClickbale(RMConstants.SELECT_CONFIGURATION_BUTTON_IN_REQUIREMENTS_VIEW_XPATH)
            .click();
        this.driverCustom.isElementVisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), selectTheConfigurationContext);
        this.waitForSecs(3);
        if (this.driverCustom.isElementVisible(gloalConfigXpath, Duration.ofSeconds(10))) {
          this.driverCustom.getWebElementClickbale(gloalConfigXpath).click();
        }
        this.waitForSecs(30);
        Text txtSearchConfiguration = this.engine
            .findElementWithinDuration(
                Criteria.isTextField().withPlaceHolder("Type to search names or tags (enter * to show all)"), this.timeInSecs)
            .getFirstElement();
        txtSearchConfiguration.setText(configuration.trim());
        this.waitForSecs(3);
        this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
        this.driverCustom.getWebElementClikbale(
            RMConstants.SELECTTHECONFIGURATIONCONTEXT_VALUE_IN_REQUIREMENTS_VIEW_XPATH, configuration.trim()).click();
        clickOnButtons("OK");
        this.driverCustom.switchToDefaultContent();
        LOGGER.LOG.info(String.format("Select 'Configuration' as '%s' successfully", configuration));
        this.driverCustom.isElementInvisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), selectTheConfigurationContext);
      }
      catch (Exception e) {
        LOGGER.LOG.error("Can not select 'Configuration' " + e.getMessage());
      }
    }

    // Select Module
    if ((!module.trim().equals("")) && (!module.trim().equals("None"))) {
      try {
        final String chooseAModule = "Choose a module";
        this.driverCustom.getWebElementClickbale(RMConstants.SELECT_MODULE_BUTTON_IN_REQUIREMENTS_VIEW_XPATH).click();
        this.driverCustom.isElementVisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), chooseAModule);
        this.waitForSecs(3);
        this.driverCustom.typeText(RMConstants.TEXTBOX_CHOOSEAMODULE_POPUP_IN_REQUIREMENTS_VIEW_XPATH, module.trim());
        this.waitForSecs(3);
        this.driverCustom.clickUsingActions(
            this.driverCustom.getWebElement(RMConstants.CHOOSEAMODULE_VALUE_IN_REQUIREMENTS_VIEW_XPATH,  module.trim()));
        clickOnDialogButton(chooseAModule, "OK");
        LOGGER.LOG.info(String.format("Select 'Module' as '%s' successfully", module));
        this.driverCustom.isElementInvisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), chooseAModule);
      }

      catch (Exception e) {
        LOGGER.LOG.error("Can not select 'Module' " + e.getMessage());
      }
    }
    
    // Select View
    if (!view.trim().equals("")) {
      try {
        final String chooseAView = "Choose a view";
        this.driverCustom.getWebElementClickbale(RMConstants.SELECT_VIEW_BUTTON_IN_REQUIREMENTS_VIEW_XPATH).click();
        this.driverCustom.isElementVisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), chooseAView);
        waitForSecs(3);
        this.driverCustom.typeText(RMConstants.TEXTBOX_CHOOSEAVIEW_POPUP_IN_REQUIREMENTS_VIEW_XPATH, view.trim());
        waitForSecs(3);
        this.driverCustom.getWebElementClikbale(RMConstants.CHOOSEAVIEW_VALUE_IN_REQUIREMENTS_VIEW_XPATH, view.trim()).click();
        waitForSecs(3);
        clickOnDialogButton(chooseAView, "OK");
        LOGGER.LOG.info(String.format("Select 'View' as '%s' successfully", view));
        this.driverCustom.isElementInvisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), chooseAView);
      }
      catch (Exception e) {
        LOGGER.LOG.error("Can not select 'View' " + e.getMessage());
      }
    }

    // Choose Items to show
    if (!itemsToShow.trim().equals("")) {
      try {
        this.driverCustom.select(RMConstants.ITEMSTOSHOW_DROPDOWN_IN_REQUIREMENTS_VIEW_XPATH, itemsToShow.trim(),
            SelectTypeEnum.SELECT_BY_VALUE);
        LOGGER.LOG.info(String.format("Select 'Items to show' as '%s' successfully", itemsToShow));
      }
      catch (Exception e) {
        LOGGER.LOG.error("Can not select 'itemsToShow' " + e.getMessage());
      }
    }
    waitForSecs(5);
    this.driverCustom.click(RMConstants.BUTTON_OK_IN_REQUIREMENTS_VIEW_XPATH);
    LOGGER.LOG.info("Select Values for 'Requirements View' successfully");
    saveProjectDashboard();
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         This method used to save projects dashboard
   * @return {@link Boolean}.
   */
  public boolean saveProjectDashboard() {
    Button btnSave = this.engine.findFirstElementWithDuration(Criteria.isButton().withText("Save"), this.timeInSecs);
    btnSave.click();
    LOGGER.LOG.info("Save button  clicked successfully.");
    return true;
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         This method used to check new view is displayed.
   *         <p>
   *         This method used after select View Value {RMDashBoardPage(WebDriverCustom)#selectViewValue}
   * @param viewTitle expected view title
   * @param viewData expected data
   * @return {@link Boolean}
   */
  public boolean isViewDataDisplayed(final String viewTitle, final String viewData) {
    waitForSecs(5);
    List<WebElement> viewDatalist =
        this.driverCustom.getWebElements("//a[@class='jazz-ui-ResourceLink'][text()='" + viewData + " ']");
    for (WebElement del : viewDatalist) {
      if (del.getText().contains(viewData)) {
        LOGGER.LOG.info("'" + viewData + "' link is displayed on the second page successfully.");
        return true;
      }
    }
    return false;

  }
  
  /**
   * <p>
   * Open any Requirement Management project area, project dashboard page is displayed.<br>
   * This method is used to remove widget out of Project Dashboard.<br>
   * 
   * @param widgetNameOrWidgetContainsName is full name of widget want to remove or widget contains name widget want to remove
   * Ex: Widget name 'AE_TS_25842_Module (1126090) : View_SYSRS_Review' => removeWidget("View_SYSRS_Review")
   * @author LTU7HC
   */
  public void removeWidgetWithContains(final String widgetNameOrWidgetContainsName) {
    WebElement btnRemove = this.driverCustom.getPresenceOfWebElement (RMConstants.BUTTON_REMOVE_HEADER_IN_REQUIREMENTS_VIEW_XPATH, widgetNameOrWidgetContainsName);
    this.driverCustom.clickUsingActions(btnRemove);
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(30));
    waitForSecs(5);
    LOGGER.LOG.info(widgetNameOrWidgetContainsName + " is removed successfully.");
  }
  
  /**
   * Select Stream to compare configuration <br>
   * This method called after {@link RMDashBoardPage#clickOnCurrentConfigurationDropdownMenu(String)} with option
   * "Compare Configuration..." <br>
   *
   * @author GLN4HC
   * @param streamName - name of local stream to compare configurations
   */
  public void selectConfigurationToCompare(final String streamName) {
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
    this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
    WebElement searchbox =
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_SEARCH_TEXTBOX_XPATH);
    searchbox.click();
    searchbox.clear();
    searchbox.sendKeys(streamName + Keys.ENTER);
    this.driverCustom.isElementInvisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTCOMP_XPATH, Duration.ofSeconds(15), streamName);
    WebElement optStream = this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTCOMP_XPATH, streamName);
    this.driverCustom.clickUsingActions(optStream);
    waitForSecs(5);
    LOGGER.LOG.info("Select stream name to compare successfully.");
  }
  
  /**
   * <p>
   * Open any Requirement Management project area, project dashboard page is displayed.<br>
   * Click on 'Settings' button, select 'Manage Components and Configuration'by using {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   *
   * @return names of the All streams under the component on the page.
   */
  public List<String> getListOfAllStreamsUnderComponent() {
    List<WebElement> list = this.driverCustom.getWebElements("//span[@class='stream']");
    List<String> values = new ArrayList<>();
    for(WebElement el:list){
      String value = el.getText();
      values.add(value);
    }
    return values;
   }
  
  
  /**
   * Create new component with name, template are same to the existing component
   * Verify that duplicate component can not be created, error message will be retured.
   * @param componentName : name of existing component
   * @return true if system return warning message which means not able to 2 components with same name
   */
  public boolean isWarningDuplicateComponentDisplayed (final String componentName) {
      boolean isMessageDisplayed = this.driverCustom.isElementVisible(RMConstants.RMCOMPONENT_WARNING_DUPLICATE_MESSAGE,timeInSecs,componentName);
      if (isMessageDisplayed) {
        return true;      
    }
    return false;
    
  }
  

  
  public void selectCurrentConfiguration () {
    
    final String selectTheConfigurationContext = "Select the Configuration Context";
    String gloalConfigXpath = "//label[normalize-space(text())='Global Configuration']";
    this.driverCustom.getWebElementClickbale(RMConstants.SELECT_CONFIGURATION_BUTTON_IN_REQUIREMENTS_VIEW_XPATH)
        .click();
    this.driverCustom.isElementVisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), selectTheConfigurationContext);
    this.waitForSecs(3);
    if (this.driverCustom.isElementVisible(gloalConfigXpath, Duration.ofSeconds(10))) {
      this.driverCustom.getWebElementClickbale(gloalConfigXpath).click();
    }
    this.waitForSecs(30);
    Text txtSearchConfiguration = this.engine
        .findElementWithinDuration(
            Criteria.isTextField().withPlaceHolder("Type to search names or tags (enter * to show all)"), this.timeInSecs)
        .getFirstElement();
    String configuration = null;
    txtSearchConfiguration.setText(configuration.trim());
    this.waitForSecs(3);
    this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
    this.driverCustom.getWebElementClikbale(
        RMConstants.SELECTTHECONFIGURATIONCONTEXT_VALUE_IN_REQUIREMENTS_VIEW_XPATH, configuration.trim()).click();
    clickOnButtons("OK");
    this.driverCustom.switchToDefaultContent();
    LOGGER.LOG.info(String.format("Select 'Configuration' as '%s' successfully", configuration));
    this.driverCustom.isElementInvisible(RMConstants.HEADER_POPUP_IN_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(5), selectTheConfigurationContext);
  }
  
  public void changeStream(String streamName) {
    
    waitForSecs(20);
   
    this.driverCustom.waitForSecs(Duration.ofSeconds(30));
    this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH, Duration.ofSeconds(20));
    String xpathIconGlobalConfiguration = "//a[@class='configurationUiNode hideLink']//img[contains(@class, 'globalConfigurationNode')]";
    String xpathStreamText = "//*[@title='Current Configuration']//*[@class='titleNode'][text()='DYNAMIC_VALUE']";
    this.driverCustom.waitForSecs(Duration.ofSeconds(5)); 
    //this.driverCustom.isElementPresent(xpathIconGlobalConfiguration, Duration.ofSeconds(15)) && this.driverCustom.isElementVisible(xpathStreamText, Duration.ofSeconds(15), streamName) 
   // {
      WebElement currentConfiguration = this.driverCustom.getPresenceOfWebElement("//a[@aria-label='Current Configuration Drop-Down Menu']");
      this.driverCustom.clickUsingActions(currentConfiguration); 
      
      this.driverCustom.waitForSecs(Duration.ofSeconds(5)); 
      WebElement Switch = this.driverCustom.getPresenceOfWebElement("//button[text()='Switch']");
    /*
     * Button btnSwitch = this.engine.findFirstElementWithDuration(Criteria.isButton().withText("Switch"),
     * Duration.ofSeconds(60)) btnSwitch.click();
     */
      
      Switch.click();
      System.out.println("Switch button clicked");
      
    this.driverCustom.waitForSecs(Duration.ofSeconds(30)); 
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SWITCHTOGC_CONFIGURATION_XPATH);
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH, Duration.ofSeconds(20));
    this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH, Duration.ofSeconds(20));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH);
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH, Duration.ofSeconds(20), RMConstants.STREAMS);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH, "Streams");
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_GCCOMPONENTSEARCH_FILTER_XPATH);
    this.driverCustom.waitForSecs(Duration.ofSeconds(5)); 
    
    this.driverCustom.typeText(RMConstants.RMARTIFACTPAGE_GCCOMPONENTSEARCH_FILTER_XPATH, streamName);
    this.driverCustom.waitForSecs(Duration.ofSeconds(20));
    WebElement config =  this.driverCustom.getPresenceOfWebElement("//span[@class='gc-ScrollableTable-focusedTitle']");
    config.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
   
    this.driverCustom.click("//button[@type='submit']");
    System.out.println("clicks on the configuration");
}
  }


