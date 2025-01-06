package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.ByType;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemType;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;
import com.bosch.jazz.automation.web.pagemodel.ManageProjectAreaPage;
import com.bosch.jazz.automation.web.pagemodel.PagemodelConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.container.ColumnFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;

import finder.button.JazzButtonFinder;
import finder.container.row.JazzRowFinder;


/**
 * Represents all capabilities common to all web pages that are shown in the context of a project area. For dedicated
 * Pages in a project area, e.g. SCM search page take a look at the subclasses like {@link CCMAdvancedSCMSearchPage}.
 * Project area page, consists method for opening menu, sub menus and dash board activities.
 */
public class CCMProjectAreaDashboardPage extends AbstractCCMPage {

  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom used for interacting with the browser, must not be null
   */
  public CCMProjectAreaDashboardPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzButtonFinder(), new RowCellFinder());

  }

  /**
   * Opens the page by simplifying opening the specific URL.
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    throw new UnsupportedOperationException("Currently Method is not support to any operation");
  }

  /**
   * <p>
   * Change and configuration management has menus like "Project Dashboard", "Work items", "Plans" and etc. <br>
   * Menus are shown in all the pages of change and configuration management application.<br>
   * This methods will be available in all the pages which extends this class.
   * <p>
   * After opening project area from the list of all project areas page {@link CCMAllProjectsPage}}, Dashboard will the
   * first page opened so methods {@link #openMenu(String)} and {@link #openSubMenu(String)} are implemented in this
   * page.
   *
   * @param menuName menu name to open particular menu item in the application.See {@link CCMMenus} enum values for
   *          names of the menus.
   */
  @Override
  public void openMenu(final String menuName) {
    waitForPageLoaded();
    Optional<WebElement> matchedOption =
        this.driverCustom.getWebElements("//a[starts-with(@class,'jazz-ui-MenuPopup')]").stream()
            .filter(x -> x.getText().equals(menuName)).findFirst();
    if (!matchedOption.isPresent()) {
      throw new WebAutomationException(menuName + " is invalid or does not exists in the list of menus.");
    }
    matchedOption.get().click();
    LOGGER.LOG.info("'" + menuName + "' clicked successfully from the list of menus.");
  }

  @Override
  protected void clickOnSelectWorkItemType() {
    waitForPageLoaded();
    this.driverCustom.click(CCMConstants.CCMPROJECTAREAPAGE_WORKITEMSELECTTYPE_LINK_XPATH);

  }

  /**
   * <p>
   * Drop down is shown with list of work item types, It is shown after clicking on Work items menu using
   * {@link #open(String, String, Map)} method and opening {@link #openSubMenu(String)} sub menu.
   *
   * @return list of work items names present in the drop down.
   */
  public List<String> getWorkItemsList() {
    waitForPageLoaded();
    return this.driverCustom.getWebElementsText(CCMConstants.CCMPROJECTAREAPAGE_WORKITEMSELECT_MENUITEMLISTS_XPATH);
  }

  /**
   * <p>
   * After opening work item menu drop down, only few work item types are shown in sub menu,To select others, open
   * "Select type" sub menu which directs to "Create work item dialog".
   * <p>
   * Select work item which is required to create from the list by passing input available in {@link WorkItemType} enum
   * values. <br>
   * After selecting the work item it clicks on "Create Work item button".
   *
   * @param workItemType work item name required to select. e.g "Task,Story,Defect ..and etc" To get proper names refer
   *          to enum values in {@link WorkItemType}
   */
  public void selectWorkItemFromCreateWorkitemDialogToCreate(final String workItemType) {
    waitForPageLoaded();

    // this.driverCustom
    // .getPresenceOfWebElement("//div[@class='jazz-ui-StyledBox sbBlue sbDark shadow jazz-ui-Dialog-absolute']");
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPROJECTAREAPAGE_WORKITEMSELECT_MENUITEMS_XPATH);
    waitForSecs(Duration.ofSeconds(4));// In 02T1 NonGc server getting one message dilog.
    this.driverCustom.select(CCMConstants.CCMPROJECTAREAPAGE_WORKITEMSELECT_MENUITEMS_XPATH, workItemType,
        SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
    LOGGER.LOG.info("Work item type : '" + workItemType + "' selected successfully from \"Create work item dialog\"");
    clickOnCreateWorkItemButton();
    waitForSecs(Duration.ofSeconds(6));
    LOGGER.LOG.info("\"Create Work item button\" clicked successfully from \"Create work item dialog\"");
  }

  private void clickOnCreateWorkItemButton() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPROJECTAREAPAGE_CREATEWORKITEM_BUTTON_XPATH);

    this.driverCustom.click(CCMConstants.CCMPROJECTAREAPAGE_CREATEWORKITEM_BUTTON_XPATH);
  }

  /**
   * <p>
   * 
   * @author KAZ1COB This method is used to import CSV file to create new WI from the project area AE Workplace ALM (CCM
   *         agile) in the work items menu
   * @param fileName : file to be imported ( importCSV.csv)
   */
  public void openImportFromCSVFileTab(final String fileName) {
    String importFileAbsolutePath = "";
    importFileAbsolutePath = Paths.get(CCMConstants.IMPORT_FILE_LOCATION + fileName).toAbsolutePath().toString();
    if (this.driverCustom.isElementVisible("//button[text()='Browse']", Duration.ofSeconds(3))) {
      WebElement fileInput = this.driverCustom.getWebDriver().findElement(By.xpath("//input[@type='file']"));
      fileInput.sendKeys(importFileAbsolutePath);
      LOGGER.LOG.info("Import CSV File is selected!");
      this.driverCustom.isElementVisible("//input[text()='importCSV.csv']", Duration.ofSeconds(3));
      LOGGER.LOG.info("Element visible");

    }
    WebElement radioButton = this.driverCustom.getWebDriver()
        .findElement(By.id("com_ibm_team_workitem_web_ui_internal_page_importer_DataTab_0-comma"));
    boolean isEnabled = radioButton.isEnabled();
    if (isEnabled) {
      LOGGER.LOG.info("Radio button of Values in this file are separated by Comma is enabled.");
    }
    else {
      radioButton.click();
      LOGGER.LOG.info("Radio button of Values in this file are separated by Comma is disabled.");
    }


    WebElement wiradioButton = this.driverCustom.getWebDriver()
        .findElement(By.id("com_ibm_team_workitem_web_ui_internal_page_importer_DataTab_0-gen-newwi"));
    boolean enabled = wiradioButton.isEnabled();
    if (enabled) {
      LOGGER.LOG.info("Radio button For each entry in the selected CSV file: Create a new work item is enabled.");
    }
    else {
      wiradioButton.click();
      LOGGER.LOG.info("Radio button For each entry in the selected CSV file: Create a new work item is disabled.");
    }

    waitForSecs(20);
    this.driverCustom.isElementClickable("//button[text()='Import']", this.timeInSecs);
    Button btnImport =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Import"), this.timeInSecs).getFirstElement();
    btnImport.click();
    LOGGER.LOG.info("Import Button is clicked");

  }

  /**
   * <p>
   *
   * @author KAZ1COB This verifies whether the displayed message is displayed or not
   * @param message : Displayed after the csv file is imported
   * @return failedMsg
   */
  public String verifyValidationMessageAfterCSVFileImported(final String message) {
    boolean isDisplayed =
        this.driverCustom.isElementVisible(String.format("//span[text()='%s']", message), this.timeInSecs);
    if (isDisplayed) {
      LOGGER.LOG.info(String.format("Message: '%s' is displayed", message));
      return message;
    }
    String failedMsg = String.format("Message: '%s' is not displayed", message);
    fail(failedMsg);
    return failedMsg;
  }

  /**
   * Quick Search widget
   *
   * @param wiName is name of the Work Item
   */
  public void quickSearch(final String wiName) {

    this.driverCustom.isElementInvisible(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH,Duration.ofSeconds(10));
    Optional<WebElement> opt =
        this.driverCustom.getWebElements("//div[@class='SearchWrapper']//input[@title='Search']").stream().findFirst();
    if (opt.isPresent()) {
      opt.get().clear();
    }
    else {
      try {
        this.driverCustom.switchToDefaultContent();
        this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH).click();
        this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH).sendKeys(Keys.BACK_SPACE);
        this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH)
            .sendKeys(String.valueOf(wiName.charAt(wiName.length() - 1)));
      }
      catch (Exception ex) {
        throw new WebAutomationException(wiName + " invalid workitem name.");
      }
    }
    Optional<WebElement> opti =
        this.driverCustom.getWebElements("//div[@class='SearchWrapper']//input[@title='Search']").stream().findFirst();
    if (opti.isPresent()) {
      opti.get().click();
      opti.get().sendKeys(wiName);
    }
    else {
      throw new WebAutomationException(wiName + " invalid workitem name.");
    }
    waitForSecs(Duration.ofSeconds(3));
    LOGGER.LOG.info(wiName + " Search Items text set successfully in search box.");


  }

  /**
   * Open the change and management configuration like artifacts , modules and collections etc from search box.
   *
   * @param name of the requirement management specification.
   * @returns true if the specification opened.
   * @author LTU7HC
   */
  public void openSearchedSpecification(final String name) {
    if (this.driverCustom.isElementPresent(
        "//div[@class='search-result']/descendant::b[contains(text(),'DYNAMIC_VAlUE')]", Duration.ofSeconds(5), name)) {
      this.driverCustom.click("//div[@class='search-result']/descendant::b[contains(text(),'DYNAMIC_VAlUE')]", name);
      LOGGER.LOG.info(name + " : Searched item id found successfully.");
      if (this.driverCustom.isElementVisible("//a[@class='closeButton']", Duration.ofSeconds(2))) {
        this.driverCustom.getFirstVisibleWebElement("//a[@class='closeButton']", null).click();
      }
      // After opening search specification should clear text at search input
      // in case of the mouse is not release then search result pop up will show up
      // and it will hide button Save so we can not click on Save button after that
      Optional<WebElement> opt = this.driverCustom
          .getWebElements("//div[@class='SearchWrapper']//input[@title='Search']").stream().findFirst();
      if (opt.isPresent()) {
        opt.get().clear();
        opt.get().sendKeys(Keys.ESCAPE);
      }
      this.driverCustom.isElementInvisible(CCMConstants.LOADING_MESSAGE_XPATH, this.timeInSecs);
    }
  }

  /**
   * @param wiNum id Of the Work Item
   */
  public void searchForCCMArtifacts(final String wiNum) {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().findElement(By.xpath("//input[@class='SearchInputText']")).sendKeys(wiNum);
    Link lnkSearch = this.engine.findFirstElement(Criteria.isLink().withText("Search"));
    lnkSearch.click();
  }

  /**
   * opens the home button drop down.
   *
   * @return true if click on Home button successfully or vice versa
   */
  public boolean clickOnHomeButtonDropDown() {
    Link clickOnHomeButton = this.engine.findFirstElement(Criteria.isLink().withToolTip("Home Menu"));
    clickOnHomeButton.click();
    return true;
  }

  /**
   * Clicks and opens the personal dashboard.
   *
   * @return true if open Personal Dashboard successfully
   */
  public boolean openPersonalDashboard() {
    waitForPageLoaded();
    this.driverCustom.click(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_PERSONALDASHBOARD_LINK_XPATH);
    return true;
  }
  /**
   * Adding Social Gadget in mini dashboard.
   *
   * @param widgetLink is link
   */
  public void addSocialGadgetUrlLink(final String widgetLink) {
    this.driverCustom.getWebDriver().findElement(By.xpath("//input[@dojoattachpoint='_urlInputText']"))
    .sendKeys(widgetLink);
   
  }
  
  /**
   * @param widgetTitle
   * @return
   */
  public boolean isWidgetSavedInMiniDashboard(final String widgetTitle) {
    waitForSecs(5);
//   
//    try {
//      this.driverCustom.click("//button[@aria-label='close']");
//    }catch (Exception e) {
//      // TODO: handle exception
//    }
//    
//  int size = this.driverCustom.getWebDriver().findElements(By.xpath("//iframe")).size();
//     for (int i = 0; i <= size; i++) {
//    this.driverCustom.getWebDriver().switchTo().frame(i);
//     LOGGER.LOG.info("Frame " + i + " is displayed.");
// this.driverCustom.getPresenceOfWebElement("//div[@class='header']");
//    //this.driverCustom.isElementVisible("//div[@class='header']", 2);
//LOGGER.LOG.info(this.driverCustom.isElementVisible("//div[@class='header-primary' and @title='DYNAMIC_VAlUE']",Duration.ofSeconds(5),  widgetTitle));
//     }  
    
   // this.driverCustom.getWebDriver().findElements(By.xpath("//div[@class='gadget-content']//iframe"));
    
   // this.driverCustom.switchToFrame("//div[@class='gadget-content']//iframe");
    // LOGGER.LOG.info(this.driverCustom.isElementVisible("//div[@class='header-primary' and
    // @title='DYNAMIC_VAlUE']",Duration.ofSeconds(5), widgetTitle));
    
    
//return this.driverCustom.isElementVisible("//div[@class='header-primary' and @title='DYNAMIC_VAlUE']",Duration.ofSeconds(5),  widgetTitle);
    
//    return this.driverCustom.isElementVisible("//div[@class='header-primary' and @title='DYNAMIC_VAlUE']",Duration.ofSeconds(5),  widgetTitle);
    
    String xpath = PagemodelConstants.SPAN_TEXT + widgetTitle +"']";
    LOGGER.LOG.info("Verified Widget Saved In Mini Dashboard : " +widgetTitle);
    LOGGER.LOG.info("Verified Widget Saved In Mini Dashboard : " +
        this.driverCustom.isElementVisible(xpath, Duration.ofSeconds(10)));
    return this.driverCustom.isElementVisible(xpath, Duration.ofSeconds(10));
  }
  
  
  
  /**
   * @param widgetLink
   * @return
   */
  public void removeWidgetFromMiniDashboard(final String widgetTitle) {

    WebElement btnRemove =
        this.driverCustom.getWebElement(CCMConstants.CCMDASHBOARDPAGE_JRS_WIDGET_REMOVE_BUTTON, widgetTitle);
    if (btnRemove.isDisplayed()) {
      btnRemove.click();
    }
  }

  /**
   * Adding Social Gadget in mini dashboard.
   *
   * @param widgetLink is link
   */
  public void addSocialGadget(final String widgetLink) {
    waitForPageLoaded();
    if (!(this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(5),
        "Bulk Operations - Build"))) {
      Button btnAddWidget =
          this.engine.findElement(Criteria.isButton().withToolTip(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET))
              .getFirstElement();
      btnAddWidget.click();
      try {
        this.driverCustom.getPresenceOfWebElement(
            "//div[@class='jazz-ui-Dialog modeless front is-visible']//div[@class='header-text' and text()='Select Category']//..//a[text()='DYNAMIC_VAlUE']",
            "Add OpenSocial Gadget");
        this.driverCustom.click(
            "//div[@class='jazz-ui-Dialog modeless front is-visible']//div[@class='header-text' and text()='Select Category']//..//a[text()='DYNAMIC_VAlUE']",
            "Add OpenSocial Gadget");
    
     this.driverCustom.getWebDriver().findElement(By.xpath("//input[@dojoattachpoint='_urlInputText']"))
     .sendKeys(widgetLink);
     
     
      }
      catch (Exception e) {
        // TODO: handle exception
      
        if (this.driverCustom.isElementVisible(CCMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(5),
            "Add OpenSocial Gadget")) {
          Link lnkAddOpenSocialGadget =
              this.engine.findFirstElement(Criteria.isLink().withText("Add OpenSocial Gadget"));
        lnkAddOpenSocialGadget.click();
      }
      }
      this.driverCustom.getWebDriver().findElement(By.xpath("//input[@dojoattachpoint='_urlInputText']"))
          .sendKeys(widgetLink);
      this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_CLOSE_BUTTON_XPATH);
      this.driverCustom.getWebDriver().switchTo().activeElement().sendKeys(Keys.TAB, Keys.ENTER);
      this.driverCustom.getWebDriver().findElement(By.xpath("//input[@type='submit']")).click();
    }
  }

  /**
   * Clicks on the mini dashboard.
   */
  public void clickOnMiniDashbaord() {
    waitForPageLoaded();
    this.driverCustom.click(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_MINIDASHBOARD_LINK_XPATH);
  }

  /**
   * This method verifies mini dashboard.
   */
  public void verifyMiniDashboard() {
    if (!"Mini Dashboard"
        .equals(this.driverCustom.getText(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_MINIDASHBOARD_TITLE_XPATH))) {
      fail("Failed to open mini dashboard");
    }
  }
  public void clickOnAddWidgetInMiniDashbaord() {
   
    waitForPageLoaded();
    this.driverCustom.click("//div[@dojoattachpoint='_innerWrapper']//a[@title='Pin']");
    waitForSecs(5);
    this.driverCustom.click("//div[@dojoattachpoint='_innerWrapper']//a[@title='Add Widget']");
    waitForSecs(5);
   
  }

  
//img[@alt='Add Widget']
  /**
   * Clicks on all personal dashboard link.
   */
  public void clickOnAllPersonalDashboardLink() {
    waitForPageLoaded();
    this.engine.addFinders(new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder(), new ColumnFinder(),
        new LinkFinder());
    Cell clickOnAllPersonalDashboardLink =
        this.engine.findElement(Criteria.isCell().withText("All Personal Dashboards")).getFirstElement();
    clickOnAllPersonalDashboardLink.click();
  }

  /**
   * @return names of the All personal dashboards on the page.
   */
  public List<String> getListOfAllPersonalDashBoardNames() {
    waitForPageLoaded();
    return this.driverCustom
        .getWebElementsText(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_PERSONALDASHBOARDSLIST_LINK_XPATH);
  }

  /**
   * Iterates all over the links by opening the link and verify its title and navigate back to origainal page.
   */
  public void verifyALLPersonalDashBoardsOpenAndNavigateBack() {

    List<WebElement> list =
        this.driverCustom.getWebElements(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_PERSONALDASHBOARDSLIST_LINK_XPATH);
    for (WebElement element : list) {
      element.click();
      this.driverCustom.waitForPageLoaded(element.getText());
      navigateBack();
      ExpectedConditionsCustom.titleNotEmptyAndContainsNot(element.getText());
    }
    navigateBack();
  }

  /**
   * @return returns names of the widgets present on the dashboard.
   */
  public List<String> getListOfWidgetsOnDashBoard() {
    waitForPageLoaded();
    return this.driverCustom.getWebElementsText(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_WIDGETS_TABLE_XPATH);
  }

  /**
   * Clicks on the Add Widget button.
   *
   * @return true if click Add Widget button successfully or vice versa
   */
  public boolean clickOnAddWidgetButton() {
    try {
      waitForSecs(5);
      this.driverCustom.isElementVisible(
          CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_ADDWIDGETBUTTONINDIALOGE_XPATH, this.timeInSecs);
      this.driverCustom.click(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_ADDWIDGETBUTTONINDIALOGE_XPATH);
     // WebElement btnAddWidget1 =
     //     this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_ADDWIDGETBUTTONINDIALOGE_XPATH);
     // btnAddWidget1.click();
      LOGGER.LOG.info("'Add Widget' button clicked successfully.  ");
      waitForSecs(5);
    }
    catch (Exception e) {
      // TODO: handle exception
    
      this.driverCustom.isElementVisible(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_ADDWIDGETBUTTON_XPATH,
          this.timeInSecs);
      WebElement btnAddWidget = this.driverCustom
          .getPresenceOfWebElement(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_ADDWIDGETBUTTON_XPATH);
    btnAddWidget.click();
    LOGGER.LOG.info("'Add Widget' button clicked successfully.  ");
    }
    return this.driverCustom.isElementVisible(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_DROPDOWN_XPATH,
        this.timeInSecs);
    
    }

  /**
   * @return true if the Widget button is visible on the dashboard.
   */
  public boolean isWidgetVisible() {
    waitForPageLoaded();
   
    return this.driverCustom.isElementVisible(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_BUTTON_XPATH,
        Duration.ofSeconds(10));
  }

  /**
   * Clicks on the select category dropdown in the dashboard page.
   *
   * @return true if click successfully
   */
  public boolean clickOnSelectCatalogDropdown() {
    waitForPageLoaded();
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_DROPDOWN_XPATH)
        .click();

    waitForSecs(Duration.ofSeconds(5));
    LOGGER.LOG.info("catalog Name Dropdown clicked");
    return true;
  }

  /**
   * choose category from the dropdown in the dashboard page.
   *
   * @param catalogName parameter holds the name of the category.
   */
  public void selectCatalogDropdownValue(final String catalogName) {
    waitForPageLoaded();
    String ccmXpath = "(//tbody[@class='dijitReset']//span[contains(text(),'" + catalogName + "')])[1]";
    if (catalogName.equalsIgnoreCase("Change and Configuration Management")) {
      ccmXpath = "(//tbody[@class='dijitReset']//span[contains(text(),'" + catalogName +
          "')])//span[contains(text(),'(/ccm)')]";
    }
    this.driverCustom.isElementVisible(ccmXpath, this.timeInSecs);
    try {
      this.driverCustom.click(ccmXpath);
      waitForSecs(Duration.ofSeconds(5));
      LOGGER.LOG.info("Selected : " + catalogName);
    }
    catch (Exception e) {
      LOGGER.LOG.error("Can not select : " + catalogName);
    }
  }

  /**
   * @param categorytype category type
   */
  public void selectCategoryType(final String categorytype) {
    waitForPageLoaded();
    waitForSecs(Duration.ofSeconds(5));
    if (categorytype.equals("Add OpenSocial Gadget")) {
    
      this.driverCustom.click(
          "//div[@class='jazz-ui-Dialog modeless front is-visible']//div[@class='header-text' and text()='Add External Widgets']//..//a[text()='DYNAMIC_VAlUE']",
          categorytype);
    }
    else {   
      this.driverCustom.click("//div[@class='header-text' and text()='Select Category']//..//a[text()='DYNAMIC_VAlUE']",
        categorytype);
    }
    } 

  /**
   * Search widget to by its name. E.G Report.
   *
   * @param widgetName name of the report.
   */
  public void searchWidgetByName(final String widgetName) {
    waitForPageLoaded();
    waitForSecs(Duration.ofSeconds(5));
    WebElement web = this.driverCustom.getPresenceOfWebElement("(//input[@name='filter-box'])[1]");
    web.click();
    web.clear();
    waitForSecs(Duration.ofSeconds(5));
    web.sendKeys(widgetName);
    waitForSecs(Duration.ofSeconds(5));
    LOGGER.LOG.info(" searched widget Name is : " + widgetName);
  }

  /**
   * This method adds the searched widget to dashboard.
   *
   * @return true if add successfully or vice versa
   */
  public boolean addWidgetToDashboard() {
    waitForPageLoaded();
    Button btnAddWidgetToDashboard = this.engine.findFirstElementWithDuration(
        Criteria.isButton().withText(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET), this.timeInSecs);
    btnAddWidgetToDashboard.click();
    LOGGER.LOG.info("'Add Widget' button inside Catalog is clicked successfully.");
    waitForSecs(Duration.ofSeconds(5));
    return true;
  }

  /**
   * @param widgtName widgt Name
   * @return {@link Boolean}
   */
  public boolean isWidgetAdded(final String widgtName) {
    waitForPageLoaded();

    // 1st close the widget window
    try {
      waitForSecs(Duration.ofSeconds(10));

      this.driverCustom.getWebElement("//div[@class='cbd-close-button-site']").click();
      LOGGER.LOG.info("clicked 'X' to close Catalog of widget");
      waitForSecs(Duration.ofSeconds(5));
    }
    catch (Exception e) {
      // TODO: handle exception
    }
    this.driverCustom.getPresenceOfWebElement("//div[@class='header-primary']//span[text()='DYNAMIC_VAlUE']",
        widgtName);
    LOGGER.LOG.info("check presence of " + widgtName);
    waitForSecs(Duration.ofSeconds(5));
    return true;

  }

  /**
   * This method save the dashboard.
   */
  public void saveDashboard() {
    waitForPageLoaded();
    Button btnSave =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Save"), this.timeInSecs).getFirstElement();
    btnSave.click();
  }

  /**
   * @return true if dashboard successfully saved.
   */
  public boolean isDashboardSaved() {
    return getMessageSummary().contains("Dashboard successfully saved");
  }

  /**
   * Assumes that some user is logged in, the browser shows a Jazz page and then returns the name of the logged in user.
   *
   * @return the string shown in the browser for the logged in user. If the user name and organisation exceeds a certain
   *         length not the full text is shown/returned but it is cut off and some dots added instead. E.g. Tomljenovic
   *         Marko (CDG-SMT/E...
   *         <p>
   *         If no user is logged in or the user cannot be identified
   */
  public boolean isSomeUserLoggedIn() {
    waitForPageLoaded();
    return this.driverCustom.getWebElementNoException("user-name", ByType.CLASS_NAME) != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_SEARCH_SEARCHTEXTBOX_XPATH);
  }

  /**
   * <p>
   * Create work items from work item template after open Work Items tab by
   * ${@link CCMProjectAreaDashboardPage#openMainMenuByMenuTitle(String)} then select option Create From Template... by
   * using ${@link CCMProjectAreaDashboardPage#openSubMenu(String)}
   * <p>
   *
   * @author NVV1HC
   * @param params is MAP, included Template Name, Filed Against value and Planned For value as well as some additional
   *          params called 'variable1', 'variable2' as (<variable>, <value>): ("templateName","Work item template for
   *          automation testing UC 173420_update") <br>
   *          ("filedAgainst", <FILEDAGAINT_VALUE>) <br>
   *          ("plannedFor", <PLANNEDFOR_VALUE>) <br>
   *          ("variable1", <VALUE1>) <br>
   *          ("variable2", <VALUE2>) <br>
   *          'variable1' and 'variable2' are additional params, in this case (TS_20418: use 2 variable likes:
   *          ${summary} and ${description})
   * @return List ID of generated work items
   */
  public List<String> createWorkItemsFromWorkItemTemplate(final Map<String, String> params) {
    String templateName = params.get("templateName");
    String filedAgainst = params.get("filedAgainst");
    String plannedFor = params.get("plannedFor");
    waitForPageLoaded();
    // Select a template
    Dialog createWorkItemsDlg =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Create Work Items From Template"),
            Duration.ofSeconds(60)).getFirstElement();
    waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.click(CCMConstants.QUERYPAGE_TEMPLATENAME_XPATH, templateName);
    try {
      Button nextBtn =
          this.engine.findElementWithDuration(Criteria.isButton().inContainer(createWorkItemsDlg).withText("Next >"),
              Duration.ofSeconds(20)).getFirstElement();
      nextBtn.click();
    }
    // Finish button enable, template don't require field input - TS_39850 
    catch (Exception e) {
      Button btnFinish = this.engine
          .findElementWithDuration(Criteria.isButton().withText("Finish"), Duration.ofSeconds(20)).getFirstElement();
      btnFinish.click();
      waitForSecs(Duration.ofSeconds(5));
      List<WebElement> listWorkItemElm =
          this.driverCustom.getWebElements(CCMConstants.QUERYPAGE_GENERATEDBYCREATEWORKITEMFROMTEMPLATE_LISTWORKITEMID);
      List<String> workItemID = new ArrayList<>();
      for (WebElement element : listWorkItemElm) {
        workItemID.add(element.getText());
      }
      return workItemID;
    }
    waitForSecs(Duration.ofSeconds(2));
    // Select Filed Against
    Button filedAgainstBtn =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Select a value for Filed Against."),
            Duration.ofSeconds(20)).getFirstElement();
    filedAgainstBtn.click();
    Dialog chooseCategoriesForFiledAgainst =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Choose from categories for Filed Against"),
            Duration.ofSeconds(20)).getFirstElement();
    Text searchFiledAgainst =
        this.engine.findElementWithDuration(Criteria.isTextField().inContainer(chooseCategoriesForFiledAgainst),
            Duration.ofSeconds(20)).getFirstElement();
    searchFiledAgainst.setText(filedAgainst);
    waitForSecs(Duration.ofSeconds(2));
    Button btnOK = this.engine
        .findElementWithDuration(Criteria.isButton().inContainer(chooseCategoriesForFiledAgainst).withText("OK"),
            Duration.ofSeconds(20))
        .getFirstElement();
    btnOK.click();
    // Select Planned For
    Button plannedForBtn =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Select a value for Planned For."),
            Duration.ofSeconds(20)).getFirstElement();
    plannedForBtn.click();
    Dialog chooseIterationForPlannedFor =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Choose from Iterations for Planned For"),
            Duration.ofSeconds(20)).getFirstElement();
    Text searchPlannedFor =
        this.engine.findElementWithDuration(Criteria.isTextField().inContainer(chooseIterationForPlannedFor),
            Duration.ofSeconds(20)).getFirstElement();
    searchPlannedFor.setText(plannedFor);
    waitForSecs(Duration.ofSeconds(2));
    if (this.driverCustom.isElementVisible(CCMConstants.CREATEWORKITEM_FROMTEMPLATE_PlANNEDFOR_OPTION_XPATH,
        Duration.ofSeconds(5), plannedFor)) {
      this.driverCustom.click(CCMConstants.CREATEWORKITEM_FROMTEMPLATE_PlANNEDFOR_OPTION_XPATH, plannedFor);
      waitForSecs(Duration.ofSeconds(2));
    }
    btnOK = this.engine
        .findElementWithDuration(Criteria.isButton().inContainer(chooseIterationForPlannedFor).withText("OK"),
            Duration.ofSeconds(20))
        .getFirstElement();
    btnOK.click();
    waitForSecs(Duration.ofSeconds(2));
    int totalVariable = params.size();
    if (totalVariable > 3) {
      for (int i = 1; i <= ((totalVariable - 3) / 2); i++) {
        String variable = params.get("variable" + i);
        String value = params.get("value" + i);
        if (!value.isEmpty()) {
          WebElement variableElm = this.driverCustom.getPresenceOfWebElement(
              CCMConstants.QUERYPAGE_WORKITEMID_GENERATEDBYCREATEWORKITEMFROMTEMPLATE_VARIABLE_XPATH, variable);
          String defaultValue = variableElm.getText();
          if (!value.equals(defaultValue.trim())) {
            variableElm.clear();
            waitForSecs(Duration.ofSeconds(1));
            variableElm.click();
            waitForSecs(Duration.ofSeconds(1));
            variableElm.sendKeys(value);
            waitForSecs(Duration.ofSeconds(2));
          }
        }
      }
    }
    Button btnFinish = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Finish"), Duration.ofSeconds(20)).getFirstElement();
    btnFinish.click();
    waitForSecs(Duration.ofSeconds(5));
    List<WebElement> listWorkItemElm =
        this.driverCustom.getWebElements(CCMConstants.QUERYPAGE_GENERATEDBYCREATEWORKITEMFROMTEMPLATE_LISTWORKITEMID);
    List<String> workItemID = new ArrayList<>();
    for (WebElement element : listWorkItemElm) {
      workItemID.add(element.getText());
    }
    return workItemID;
  }

  /**
   * @author NVV1HC
   * @param workItemID ID of work item that you want to get information
   * @return list information of the target work item as format: {workItemType, workItemID, summary, description,
   *         parentID, childrenID}
   */
  public Map<String, String> getOverviewInformationOfOneWorkItemCreatedFromWorkItemTemplate(final String workItemID) {
    Map<String, String> workItemInfo = new LinkedHashMap<String, String>();
    waitForPageLoaded();
    waitForSecs(Duration.ofSeconds(5));
    workItemInfo.put("workItemID", workItemID);
    // Get summary of work item
    WebElement summaryElm = this.driverCustom.getWebElement(CCMConstants.WORKITEMEDITORPAGE_SUMMARY_XPATH);
    workItemInfo.put("summary", summaryElm.getText().trim());

    // Get Work item type
    WebElement workItemType = this.driverCustom.getWebElement(CCMConstants.WORKITEMEDITORPAGE_WORKITEM_TYPE_XPATH);
    workItemInfo.put("workItemType", workItemType.getText().trim());

    // Get Filed Against value
    WebElement filedAgainstElm =
        this.driverCustom.getWebElement(CCMConstants.WORKITEMEDITORPAGE_WORKITEM_FILEDAGAINST_XPATH);
    workItemInfo.put("filedAgainst", filedAgainstElm.getText().trim());

    // Get Planned For value
    WebElement plannedForElm =
        this.driverCustom.getWebElement(CCMConstants.WORKITEMEDITORPAGE_WORKITEM_PLANNEDFOR_XPATH);
    workItemInfo.put("plannedFor", plannedForElm.getText().trim());

    // Get description of work item
    WebElement descriptionElm =
        this.driverCustom.getWebElement(CCMConstants.WORKITEMEDITORPAGE_WORKITEM_DESCRIPTION_XPATH);
    workItemInfo.put("description", descriptionElm.getText().trim());
    return workItemInfo;
  }

  /**
   * <p>
   * <p>
   *
   * @author NVV1HC
   * @param workItemTemplateName name of template
   * @param workItemID ID of work item
   * @param summary summary of work item
   * @param workItemType work item type, e.g: Task, Defect, Story, etc.
   * @param filedAgainst Filed Against value of work item
   * @param plannedFor Planned For value of work item
   * @param description Description value of work item
   * @return true if overview information of work item are displayed as expectation
   */
  public boolean verifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(final String workItemTemplateName,
      final String workItemID, final String summary, final String workItemType, final String filedAgainst,
      final String plannedFor, final String description) {

    Map<String, String> workItemInfo = new LinkedHashMap<String, String>();

    Row rowWorkItem = this.engine
        .findElementWithDuration(Criteria.isRow().containsText(workItemID), Duration.ofSeconds(20)).getFirstElement();
    Button workItemIDElm =
        this.engine.findElementWithDuration(Criteria.isButton().inContainer(rowWorkItem).withText(workItemID),
            Duration.ofSeconds(20)).getFirstElement();
    workItemIDElm.click();
    waitForSecs(Duration.ofSeconds(5));

    workItemInfo = getOverviewInformationOfOneWorkItemCreatedFromWorkItemTemplate(workItemID);
    List<String> listInformationOfWorkItem_input = new ArrayList<>();
    List<String> listInformationOfWorkItem_display = new ArrayList<>();

    listInformationOfWorkItem_input.add(workItemID.trim());
    listInformationOfWorkItem_input.add(summary.trim());
    listInformationOfWorkItem_input.add(workItemType.trim());
    listInformationOfWorkItem_input.add(filedAgainst.trim());
    listInformationOfWorkItem_input.add(plannedFor.trim());
    listInformationOfWorkItem_input.add(description.trim());

    listInformationOfWorkItem_display.add(workItemInfo.get("workItemID"));
    listInformationOfWorkItem_display.add(workItemInfo.get("summary"));
    listInformationOfWorkItem_display.add(workItemInfo.get("workItemType"));
    listInformationOfWorkItem_display.add(workItemInfo.get("filedAgainst"));
    listInformationOfWorkItem_display.add(workItemInfo.get("plannedFor"));
    listInformationOfWorkItem_display.add(workItemInfo.get("description"));

    boolean result = listInformationOfWorkItem_display.equals(listInformationOfWorkItem_input);
    if (!result) {
      LOGGER.LOG.error("List information of artifact are displayed in work item editor page: '" +
          listInformationOfWorkItem_display + "'");
      LOGGER.LOG.error("List information of artifact are inputed: '" + listInformationOfWorkItem_input + "'");
      System.out.println("List information of artifact are displayed in work item editor page: '" +
          listInformationOfWorkItem_display + "'");
      System.out.println("List information of artifact are inputed: '" + listInformationOfWorkItem_input + "'");
      fail("Verify overview information of work item '" + workItemID + "' failed!");
      return false;
    }
    return true;
  }

  /**
   * <p>
   * Get ID of work item is automatically generated after
   * ${@link CCMProjectAreaDashboardPage#createWorkItemsFromWorkItemTemplate(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param workItemSummary summary of work item is generated
   * @return ID of generated work item
   */
  public String getWorkItemIDGenerated(final String workItemSummary) {
    waitForSecs(Duration.ofSeconds(3));
    Row rowWorkItem =
        this.engine.findElementWithDuration(Criteria.isRow().containsText(workItemSummary), Duration.ofSeconds(30))
            .getFirstElement();
    Cell workItemIDCell =
        this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowWorkItem), Duration.ofSeconds(30))
            .getElementByIndex(3);
    return workItemIDCell.getText();
  }

  /**
   * <p>
   * Verify related link is displayed in 'Links' tab of an artifact
   * <p>
   *
   * @author NVV1HC
   * @param typeOfLink link type, e.g: validated By
   * @param relatedWorkItemID ID of work item, test case or artifact is linked to this work item
   * @return true if link is displayed as expectation or vice versa
   */
  public boolean verifyTabLinks(final String typeOfLink, final String relatedWorkItemID) {
    Tab linksTab = this.engine.findElementWithDuration(Criteria.isTab().withToolTip("Links"), Duration.ofSeconds(30))
        .getFirstElement();
    linksTab.click();
    waitForSecs(Duration.ofSeconds(5));

    String listRelatedWorkItem = "";
    String xpath = "";
    if (typeOfLink.contains("Parent")) {
      xpath = CCMConstants.WORKITEMEDITORPAGE_LINKSTAB_LISTPARENT_XPATH;
    }
    if (typeOfLink.contains("Children")) {
      xpath = CCMConstants.WORKITEMEDITORPAGE_LINKSTAB_LISTCHILDREN_XPATH;
    }
    List<WebElement> listRelatedWorkItemElm = this.driverCustom.getWebElements(xpath);
    for (WebElement relatedWorkItemElm : listRelatedWorkItemElm) {
      if (listRelatedWorkItem != "") {
        listRelatedWorkItem += ", ";
      }
      listRelatedWorkItem += relatedWorkItemElm.getText().trim();
    }
    return listRelatedWorkItem.contains(relatedWorkItemID);
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         This method used to close the widget.
   * @param workItemSummary widget name.
   * @return {@link Boolean}
   */
  public boolean clickOnClose(final String workItemSummary) {
    boolean result = false;
    Actions action = new Actions(this.driverCustom.getWebDriver());
    String xpathCloseButton = "//span[@dojoattachpoint='_headerPrimarySpan' and text()='" + workItemSummary +
        "']/ancestor::div[@role='region']//img[@alt='Remove']";
    this.driverCustom.isElementVisible(xpathCloseButton, this.timeInSecs);
    List<WebElement> lstCloseButton = this.driverCustom.getWebElements(xpathCloseButton);
    for (WebElement btnClose : lstCloseButton) {
      if (btnClose.isDisplayed()) {
        action.moveToElement(btnClose).perform();
        waitForSecs(Duration.ofSeconds(3));
        action.click(btnClose).perform();
        ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(30));
        waitForSecs(Duration.ofSeconds(5));
        LOGGER.LOG.info(workItemSummary + " is removed successfully.");
        result = true;
      }
    }
    return result;
  }

  /**
   * <p>
   * Back to Query page after ${@link CCMProjectAreaDashboardPage#createWorkItemsFromWorkItemTemplate(Map)}
   * <p>
   *
   * @author NVV1HC
   */
  public void backToQueryPage() {
    waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.click(CCMConstants.BACK_TO_QUERY_BUTTON_XPATH);
    waitForSecs(Duration.ofSeconds(2));
  }
  
  /**
   * After open project area using method {@link CCMProjectAreaDashboardPage#openSettingsSubMenu(String)} Select
   * Timeline tab using method {@link ManageProjectAreaPage#tabSection(String)} Select Archive Timeline/ Archive
   * Iteration
   *
   *@param  propertyName - name of newly created timeline or iteration
   * @author NCY3HC
   * @return true if archive Timeline/Iteration successfully and vice versa.
   */
  public boolean archiveTimelineOrIteration (final String propertyName) {
    this.driverCustom
        .getWebElement(CCMConstants.CCMPROJECTAREA_NEWCREATETIMELINE_NEWCRETAEITERATION_DISPLAYED_XPATH, propertyName)
        .click();
     //Archive Timeline/Iteration
     this.driverCustom.getWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, "Archive").click();     
     this.driverCustom.acceptAlertAndReturnAlertText();
     //Click on Save button
    Button btnSave = this.engine.findElementWithDuration(Criteria.isButton().withText("Save"), Duration.ofSeconds(30))
        .getFirstElement();
     btnSave.isElementEnable(Duration.ofSeconds(10));
     btnSave.click();
    this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_XPATH, this.timeInSecs);
    return this.driverCustom.isElementInvisible(
        CCMConstants.CCMPROJECTAREA_NEWCREATETIMELINE_NEWCRETAEITERATION_DISPLAYED_XPATH, this.timeInSecs,
        propertyName);
  }
  
  /**
   * <p>
   * After Add widget 'the Report Builder report' to CCM dashboard 'Choose a configuration' for the report in CCM
   * dashboard
   * <p>
   *
   * @author NCY3HC
   */
  public void chooseConfigurationInJrsReportWidget(final String domainDropDownValue, final String projectName,
      final String componentName, final String configurationName) {
    WebDriver driver = this.driverCustom.getWebDriver();
    driver.switchTo().frame("jazz_opensocial_Gadget_1");
    Actions action = new Actions(this.driverCustom.getWebDriver());
    // Click on title 'Choose a Configuration' to expand section
    WebElement lblChooseConfig = this.driverCustom.getWebElement(
        JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCHOOSECONFIGURATION_LABEL_XPATH, "Choose a configuration");
    action.moveToElement(lblChooseConfig).perform();
    // Click on 'Choose a Configuration' button
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCHOOSECONFIGURATION_LABEL_XPATH,
        this.timeInSecs, "Choose a configuration")) {
      this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCHOOSECONFIGURATION_LABEL_XPATH,
          "Choose a configuration").click();
    }
   Button button = this.engine.findElement(Criteria.isButton().withText("Choose a configuration")).getFirstElement();
   button.click();
   //Choose a domain
    this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_LOCATECHOOSEDOMAIN_XPATH,
        Duration.ofSeconds(10));
   this.driverCustom.select(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSEDOMAIN_XPATH, domainDropDownValue,
       SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
   //Choose a project area for configuration
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONPROJECTAREA_XPATH,
        Duration.ofSeconds(20))) {
     this.driverCustom.click(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONPROJECTAREA_XPATH);
     this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCHPROJECTAREATEXTBOX_XPATH)
     .sendKeys(projectName);
     this.driverCustom.select(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSEPROJECTAREA_XPATH, projectName,
         SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
     waitForSecs(3);
  }
   //Choose a component
   this.driverCustom.click(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCOMPONENT_XPATH);
   this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCHCOMPONENTTEXTBOX_XPATH)
   .sendKeys(componentName + Keys.ENTER);
   this.driverCustom.select(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSECOMPONENT_XPATH, componentName,
       SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
   //Choose a configuration
    WebElement drdbtn =
        this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCONFIGURATION_7_XPATH);
   drdbtn.click();
   this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCHCONFIGURATIONTEXTBOX_XPATH)
   .sendKeys(configurationName + Keys.ENTER);
   this.driverCustom.select(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSECONFIGURATION_XPATH, configurationName,
       SelectTypeEnum.SELECT_BY_VALUE);
   // Click on Accept button
   Button accept = this.engine.findElement(Criteria.isButton().withText("Accept")).getFirstElement();
   accept.click();
   LOGGER.LOG.info("Clicked on 'Accept' button.");
  }
  
  /**
   * To pin the minidashboard
   */
  public void pinMiniDashboard() {
    waitForPageLoaded();
    this.driverCustom.click("//div[@dojoattachpoint='_innerWrapper']//a[@title='Pin']");
  }
  
  /**
   *  The required widget is selected from the widget management widget
   * @param widget - is the widget to be selected
   */
  public void openWidgetFromWidgetManagementWidget(final String widget) {
    waitForPageLoaded();
    // switch to widget management widget frame
    this.driverCustom.switchToFrame("//iframe[@name='jazz_opensocial_Gadget_0']");
    waitForSecs(5);

    // click on widget
    WebElement element = this.driverCustom.getWebElement("//div[text()='Workitem Utility']");
    element.click();
    
    //switch to default frame
    this.driverCustom.switchToDefaultContent();
    
    //switch to workitem utility widget frame
    this.driverCustom.switchToFrame("//iframe[contains(@title,'widget')]");
    
  }
  
  /**
   *  to create work item using workitem utility widget
   * @param parameters - contains data such as type, projct area, etc.
   * @return return the id of the workitem created
   */
  public String createWorkItemsUsingWorkitemUtilityWidget(final Map<String, String> parameters) {
    
    JavascriptExecutor je = (JavascriptExecutor)this.driverCustom.getWebDriver();
    
    //select new workitems tab
    WebElement ele = this.driverCustom.getWebElement("//li[text()='New WorkItems']");
    je.executeScript("arguments[0].click();", ele);
    
    //click on add new work item
    ele = this.driverCustom.getWebElement("//button[contains(@title,'Add new work item')]");
    je.executeScript("arguments[0].click();", ele);
    
    //select project area
    Select option = new Select(this.driverCustom.getWebElement("//select[@class='DYNAMIC_VAlUE']", "projectAreaSelect"));
    option.selectByVisibleText(parameters.get("projectArea"));
    
    //select type
    option = new Select(this.driverCustom.getWebElement("//select[@class='DYNAMIC_VAlUE']", "workItemType"));
    option.selectByVisibleText(parameters.get("type"));
    
    //input summary value
    ele = this.driverCustom.getWebElement("//textarea[contains(@class,'Summary')]");
    ele.sendKeys(parameters.get("summary"));
    
    //select filed against
    ele = this.driverCustom.getWebElement("(//a[@title='Show All Items'])[1]");
    je.executeScript("arguments[0].click();", ele);
    ele = this.driverCustom.getWebElement("//div[@class='ui-menu-item-wrapper' and text()='DYNAMIC_VAlUE']",parameters.get("filedAgainst"));
    je.executeScript("arguments[0].click();", ele);
    
    //click on create-update workitem
    ele = this.driverCustom.getFirstVisibleWebElement("//button[text()='Create-Update Workitems']", null);
    je.executeScript("arguments[0].click();", ele);
    
    //get the workitem Id
    ele = this.driverCustom.getFirstVisibleWebElement("//a[contains(@title,'Work Item Id:')]",null);
    return ele.getText();

  }
  
  /**
   * To close the workitem Utility widget
   */
  public void closeWorkitemUtilityWidget() {
    
    //switch to default frame
    this.driverCustom.switchToDefaultContent();
    
    this.driverCustom.click("//img[@alt='Close']");
  }
  
  /**
   * Click on Close widget dialog
   */
  
  public void clickOnCloseWidgetDialog() {
    WebElement btnClose = this.driverCustom.getWebElement("//img[@title='Close']");
    if(btnClose.isDisplayed()) {
      btnClose.click();
    }
  }
  
  
}

