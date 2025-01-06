/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DriverSetup;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.ByType;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Artifact;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.jazz.automation.web.reporter.Reporter;
import com.bosch.jazz.automation.web.utility.ExcelCSVReader;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.container.TreeNode;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.ListboxFinder;
import com.bosch.psec.web.test.finder.container.DialogFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFinder;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.container.treeNode.JazzTreeNodeFinder;
import finder.dropdown.JazzDivDropDownFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.JazzTextFinder;
import finder.text.label.JazzSpanLabelFinder;
import finder.text.textField.JazzSpanTextFieldFinder;

/**
 * Represents the Requirement Management Modules Page.
 */
public class RMModulePage extends AbstractRMPage {

  /**
   * Waiting time to load an element.
   */
  protected static final int LOADING_ELEMENT = 2000;
  /**
   * Waiting time to load an element in sec.
   */
  protected static final int WAITING_TIME_IN_SEC = 60;

  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom required for interacting with the browser.
   */
  public RMModulePage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTreeNodeFinder(), new JazzDropdownFinder(), new JazzButtonFinder(),
        new JazzTextFinder(), new JazzSpanLabelFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new JazzDialogFinder(), new RowCellFinder(), new LinkFinder(), new JazzSpanTextFieldFinder(), new TextFinder(),
        new JazzDivDropDownFinder(), new JazzTabFinder(), new ListboxFinder(), new JazzTreeNodeFinder(),
        new DialogFinder());
  }

  /**
   * <p>
   * Opens the module page and waits at max (shortest of given 'maxWaitTimeForModuleLoaded' or the default 'explicit
   * wait time' given in property file) for the module to be fully loaded.
   *
   * @param moduleFullUrl the url of the module to open, must not be null
   * @param maxWaitTimeForModuleLoaded maximum time to wait until the module is fully loaded, if not shown until
   *          deadline an exception is thrown
   */
  public void openModule(final String moduleFullUrl, final Duration maxWaitTimeForModuleLoaded) {
    waitForPageLoaded();
    this.driverCustom.openURL(moduleFullUrl);

    new WebDriverWait(this.driverCustom.getWebDriver(), maxWaitTimeForModuleLoaded)
    .ignoring(StaleElementReferenceException.class).until(ExpectedConditions
        .elementToBeClickable(this.driverCustom.getWebElement("numArtifacts", ByType.CLASS_NAME)));
  }

  /**
   * <p>
   * Search any existing module using {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'More Actions' icon(icon will display with three lines) present right side of the Modules page.<br>
   * Click on 'Open History' option from the 'More Actions' drop down.<br>
   * Opens the 'History' page of a module. A module must be loaded before this method is called.
   */
  public void openHistory() {
    try {
      waitForPageLoaded();
    }
    catch (Exception e) {
      // Open History in Artifact details page
    }
    Dropdown drdMoreActions = this.engine.findFirstElement(Criteria.isDropdown().withToolTip("More Actions"));
    drdMoreActions.selectOptionWithText("Open History");
  }

  /**
   * <p>
   * Search the existing module in quick search text box.<br>
   * Open the searched module.<br>
   * Add the required column in the module.<br>
   * Sort the attribute type in required order.<br>
   * Get the text of added column names.
   *
   * @param headingText Header name
   * @param order Select the sorted order
   * @param type option 'Show Full Hierarchy' or 'Show 1 Level'
   * @param moduleID is id of the module
   * @return Selected Link type list values
   */
  public List<String> getValuesOfHeaders(final String headingText, final String order, final String type,
      final String moduleID) {
    List<String> lt = new ArrayList<>();
    Link navigateBackLink = this.engine.findFirstElement(Criteria.isLink().withToolTip("To Artifacts"));
    navigateBackLink.click();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
    this.driverCustom.typeText(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH, moduleID);
    WebElement searchModule = this.driverCustom.getWebDriver()
        .findElement(By.xpath("//div[@class = 'search-result']//span[contains(text(),'" + moduleID + "')]/.."));
    searchModule.click();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
    RMArtifactsPage rm = new RMArtifactsPage(this.driverCustom);
    rm.addArtifactAttributeInToArtifactTable(headingText);
    moreAction(type);
    String b = this.driverCustom.getWebDriver()
        .findElement(
            By.xpath("//div[contains(@widgetid,'com_ibm_rdm_web_grid_ViewHeader')]/table//tr//div/span[text() = '" +
                headingText + "']/../.."))
        .getAttribute("aria-sort");
    while (!(b.trim().equals(order))) {
      this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_ORDER_XPATH, headingText);
      waitForPageLoaded();
      b = this.driverCustom.getWebDriver()
          .findElement(
              By.xpath("//div[contains(@widgetid,'com_ibm_rdm_web_grid_ViewHeader')]/table//tr//div/span[text() = '" +
                  headingText + "']/../.."))
          .getAttribute("aria-sort");
    }
    List<WebElement> ls;
    int count = 2;
    ls = this.driverCustom.getWebElements(RMConstants.RMARTIFACTSPAGE_HEADING_XPATH);
    for (WebElement ele : ls) {
      if (ele.getText().equalsIgnoreCase(headingText)) {
        count++;
        break;
      }
      count++;
    }
    this.driverCustom.getWebDriver().navigate().refresh();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
    List<WebElement> el = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(60))
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//div[@class='innerPage']/div/table/tbody/tr/td[" + count + "]/div/div")));
    lt.clear();
    for (WebElement lis : el) {
      lt.add(lis.getText());
    }
    return lt;
  }

  /**
   * <p>
   * Create module with required information which will provide an unique id.<br>
   * Returns id of the created module.
   *
   * @return id of the module.
   */

  public String getModuleID() {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_GETMODULETEST_XPATH,  Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)));
    return this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_GETMODULETEST_XPATH);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'Modules' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the existing module using {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Click on the image menu launcher,list of options is displayed.<br>
   * Click on 'Move Artifact to Folder...' option from the drop down list,'Move Artifact to Folder...' dialog is
   * displayed.<br>
   * Select a folder and click on 'OK' button.
   *
   * @param moduleID Module id
   */
  public void moveArtifactToFolder(final String moduleID) {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().findElement(By.linkText(RMConstants.MODULE)).click();
    waitForPageLoaded();
    Text txtSearchVerify = this.engine.findFirstElement(
        Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID));
    txtSearchVerify.setText(moduleID + Keys.ENTER);
    waitForPageLoaded();
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_MODULESERACHSELECTDROPDOWN_XPATH);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_MODULE_MOVEARTIFACT_XPATH);
    waitForPageLoaded();
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_SELECTTHEPROJECTFOLDER_XPATH);
    Button btnOk =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), Duration.ofSeconds(WAITING_TIME_IN_SEC)).getFirstElement();
    btnOk.click();
  }

  /**
   * <p>
   * Open any existing module.<br>
   * Get all the artifacts IDs present inside a module.
   *
   * @return list of Artifacts ID from a Module.
   */
  public List<String> getArtifactIDListFromModule() {
    waitForPageLoaded();
    return this.driverCustom.getWebElementsText(RMConstants.RMMODULEINSIDE_ARTIFACT_IDS_XPATH);
  }
  
  /**
   * Open any existing module <br>
   * Get artifact ID that present inside the module using artifact name. <br>
   * @author NCY3HC
   * @param artifactName : Name of artifact which you want to get ID
   * @return Artifact ID.
   */
  
  public String getArtifactIDByArtifactContent(String artifactName) {
    waitForPageLoaded();
   return this.driverCustom.getText(RMConstants.RMMODULEINSIDE_ARTIFACT_ID_BY_ARTIFACT_CONTENT_XPATH, artifactName);
  }

  /**
   * Opens the "Audit History" page and waits at max (shortest of given 'maxWaitTime' or the default 'explicit wait
   * time' given in property file) for the first audit history page to show up.<br>
   * If the page does not show up or an error message is being shown a Selenium exception is being thrown.
   *
   * @see DriverSetup#getConfigurationForExplicitWaitTime()
   * @param maxWaitTime maximum time to wait until first audit history page shall be shown, if not shown until given
   *          time an exception is thrown
   */
  public void runAuditHistory(final int maxWaitTime) {

    clickOn(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebElement("//span[text()='Audit History']", ByType.XPATH), Duration.ofSeconds(200));
    ExpectedCondition<WebElement> errorMsgPresentCondition =
        ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.messageArea.ERROR"));
    new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(maxWaitTime)).ignoring(NoSuchElementException.class)
    .until(ExpectedConditions.or(
        ExpectedConditions.presenceOfElementLocated(By.xpath(RMConstants.FIRST_PAGE_LINK_XPATH)),
        errorMsgPresentCondition));
    new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(maxWaitTime)).ignoring(NoSuchElementException.class)
    .until(ExpectedConditions.or(
        ExpectedConditions.visibilityOfElementLocated(By.xpath(RMConstants.FIRST_PAGE_LINK_XPATH)),
        errorMsgPresentCondition));
    new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(maxWaitTime)).ignoring(NoSuchElementException.class)
    .until(ExpectedConditions.or(
        ExpectedConditions.elementToBeClickable(
            this.driverCustom.getWebDriver().findElement(By.xpath(RMConstants.FIRST_PAGE_LINK_XPATH))),
        errorMsgPresentCondition));

    // if the audit history was successfully computed then this call will be executed successfully but if there was an
    // error this call should throw an exception
    this.driverCustom.getWebDriver().findElement(By.xpath(RMConstants.FIRST_PAGE_LINK_XPATH));
  }

  @Override
  public void logout() {

    WebElement userMenuLink =
        this.driverCustom.getWebDriver().findElement(By.xpath("//span[@class='sprite-image user-menu']"));
    userMenuLink.click();

    WebElement logoutLink = this.driverCustom.getWebDriver().findElement(By.xpath("//span[text()='Log Out']"));
    logoutLink.click();
  }

  private static void clickOn(final WebDriver driver, final WebElement locator, final Duration timeout) {
    new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
    .until(ExpectedConditions.elementToBeClickable(locator));
    locator.click();
  }

  /**
   * <p>
   * Search and Open any existing module, Module page is displayed.<br>
   * Select the tab in requirements management page i.e after opening a module on Right side two tabs with name 'Module'
   * and 'Selected Artifact'.
   *
   * @param tabName name of the Tab to be Selected inside the module like 'Module','Selected Artifact' etc...
   */
  public void chooseTabInsideModule(final String tabName) {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_SECTION_TITLE_TEXT_XPATH, Duration.ofSeconds(10), tabName);
    this.driverCustom.clickUsingActions(
        this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_SECTION_TITLE_TEXT_XPATH, tabName));
    waitForSecs(5);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Select the folder from the displayed folder list.<br>
   * Click on drop down menu present next to folder name.<br>
   * Select the option 'Create Artifact',list of options displayed.<br>
   * Select the option 'Other...','Create Artifact' dialog is displayed.<br>
   * Provide Module Name,Artifact Type,Artifact Format should be 'Module' and click on 'OK' button.
   * 
   * @param additionalParams stores key value for 'FOLDER_NAME','MODULE_NAME','MODULE_TYPE' etc...
   * @return module Name.
   */
  public String createModule(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String createArtifactHeadingXpath = "//div[@class='jazz-ui-Dialog-heading'][text()='Create Artifact']";
    boolean isDialogCreateArtifactDisplayed = this.driverCustom.isElementVisible(createArtifactHeadingXpath, Duration.ofSeconds(WAITING_TIME_IN_SEC));
    if (!isDialogCreateArtifactDisplayed) {
    // Select Create Artifact with Other option
    TreeNode treeModuleFolder =
        this.engine.findElementWithDuration(Criteria.isTreeNode().containText(additionalParams.get("FOLDER_NAME")),
            Duration.ofSeconds(180)).getFirstElement();
    Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().inContainer(treeModuleFolder));
    drdMenu.selectOptionsWithText((RMConstants.CREATE_ARTIFACT), ("Other..."));
    }
    Dialog dlgCreateArtifact = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle(RMConstants.CREATE_ARTIFACT), Duration.ofSeconds(WAITING_TIME_IN_SEC))
        .getFirstElement();
    // Input module name
    Text txtName = this.engine
        .findElementWithDuration(Criteria.isTextField().hasLabel(RMConstants.NAME).inContainer(dlgCreateArtifact),
            Duration.ofSeconds(WAITING_TIME_IN_SEC))
        .getElementByIndex(2);
    txtName.setText(additionalParams.get("MODULE_NAME"));
    // Select Artifact Type
    Dropdown drdArtifactType = this.engine.findFirstElement(
        Criteria.isDropdown().withLabel(Artifact.ARTIFACT_TYPE.toString() + ":").inContainer(dlgCreateArtifact));
//    drdArtifactType.selectOptionWithText(additionalParams.get("MODULE_TYPE"));
    drdArtifactType.click();
    this.driverCustom.getPresenceOfWebElement(
        "//div[contains(@id,'artifactTypeField_popup')][@role='listbox']//div[text()='DYNAMIC_VAlUE']",
        additionalParams.get("MODULE_TYPE")).click();
    // Select Artifact Format
    Dropdown drdArtifacFormat = this.engine.findFirstElement(
        Criteria.isDropdown().withLabel(Artifact.ARTIFACT_FORMAT.toString() + ":").inContainer(dlgCreateArtifact));
    drdArtifacFormat.selectOptionWithText("Module");
    
    // Check 'Open Artifact' checkbox if it's not checked before
    //Try- Catch to avoid error for previous implemetation, since OPEN_MODULE is not used before 
    
    try {
      String openArtifact = additionalParams.get("OPEN_ARTIFACT");
      String cbxOpenArtifact = this.driverCustom.getAttribute("//label[contains(text(),'Open artifact')]//ancestor::div//input[@role='checkbox']", "aria-checked");
          if (!openArtifact.equalsIgnoreCase(cbxOpenArtifact)) {
            Checkbox checkbox = this.engine.findElementWithDuration(Criteria.isCheckbox().withLabel("Open artifact"), timeInSecs).getFirstElement();
            checkbox.click();
          }
      
    } catch (NullPointerException e) {
      
    }
    // Click on Ok button
    Button btnOK = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgCreateArtifact), Duration.ofSeconds(WAITING_TIME_IN_SEC))
        .getFirstElement();
    btnOK.click();
    waitForSecs(5);
    return additionalParams.get("MODULE_NAME");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'Modules' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Select the folder from the displayed folder list.<br>
   * Search the module in 'Type to filter artifacts by text or by ID' text box.<br>
   * Hover the mouse for the searched module,'Edit Name' icon displayed near to the module name.<br>
   * Edit the module name in the displayed text box.<br>
   * Click on 'Refresh' button present right side top of the module page.
   *
   * @param oldModuleName searched module name.
   * @param newModuleName edited module name
   * @return new module name.
   */
  public String editModuleName(final String oldModuleName, final String newModuleName) {
    waitForPageLoaded();
    // Click on module folder
    TreeNode treeModuleFolder = this.engine
        .findElementWithDuration(Criteria.isTreeNode().containText(RMConstants.MODULE_ARTIFACTS), Duration.ofSeconds(WAITING_TIME_IN_SEC))
        .getFirstElement();
    treeModuleFolder.click();
    // Search module
    Text txtSearch = this.engine
        .findFirstElement(Criteria.isTextField().withPlaceHolder("Type to filter artifacts by text or by ID"));
    txtSearch.setText(oldModuleName + Keys.ENTER);
    // Click on Edit Name button
    WebElement elmCollection = this.driverCustom.getWebDriver()
        .findElement(By.xpath("//div[.='" + oldModuleName + "']/img[contains(@class,'icon')]"));
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.moveToElement(elmCollection).build().perform();

    Button btnEditName = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Edit Name"), Duration.ofSeconds(WAITING_TIME_IN_SEC)).getFirstElement();
    btnEditName.click();
    WebElement elmTextModuleName =
        this.driverCustom.getWebDriver().findElement(By.xpath("//textarea[@value = '" + oldModuleName + "']"));
    elmTextModuleName.sendKeys(Keys.CONTROL, "A");
    elmTextModuleName.sendKeys(Keys.DELETE);
    elmTextModuleName.sendKeys(newModuleName);
    // Click Refresh button
    Button btnRefresh = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Refresh"), Duration.ofSeconds(WAITING_TIME_IN_SEC)).getFirstElement();
    btnRefresh.click();
    return newModuleName;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'Modules' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Select the folder from the displayed folder list present left side of the page.<br>
   * Search the module in 'Type to filter artifacts by text or by ID' text box.<br>
   * Click on the image menu launcher from the searched module.<br>
   * Select the module using module name/module id, list of options is displayed.<br>
   * Click on 'Delete Artifact...' option from the drop down list, 'Delete Artifact' dialog is displayed.<br>
   * Click on 'Confirm Deletion' option to delete the module.
   *
   * @param folderName name of the folder.
   * @param moduleName deletes the module name.
   */
  public void deleteModule(final String folderName, final String moduleName) {
    waitForPageLoaded();
    // Click on collection folder
    TreeNode treeProjectFolder = this.engine
        .findElementWithDuration(Criteria.isTreeNode().containText(folderName), Duration.ofSeconds(WAITING_TIME_IN_SEC)).getFirstElement();
    treeProjectFolder.click();
    // Search module
    Text txtSearch = this.engine
        .findFirstElement(Criteria.isTextField().withPlaceHolder("Type to filter artifacts by text or by ID"));
    txtSearch.setText(moduleName + Keys.ENTER);
    // Select Module Name.
    Row rowModule = this.engine.findElementWithDuration(Criteria.isRow().containsText(moduleName), Duration.ofSeconds(WAITING_TIME_IN_SEC))
        .getFirstElement();
    Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().inContainer(rowModule));
    drdMenu.selectOptionWithText("Delete Artifact...");
    // Click on Delete Artifact button
    Button btnDeleteArtifact =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Delete Artifact"), Duration.ofSeconds(WAITING_TIME_IN_SEC))
        .getFirstElement();
    btnDeleteArtifact.click();

  }

  /**
   * <p>
   * Search any existing module in Quick Search text box.<br>
   * Open the searched module using {@link RMDashBoardPage#openSearchedSpecification(String)}.<br>
   * Click on 'Create' button present left side of the page.<br>
   * Click on 'Add an Existing Artifact...' option from the displayed list, 'Add to module...' dialog is displayed.<br>
   * Search the module id in ' Type to filter artifacts by text or by ID' text box. <br>
   * Select the searched artifact and click on 'Add' button.
   *
   * @param artifactID to be added in the module.
   * @param button - button after select artifact
   */

  public void addArtifactToModule(final String artifactID,final String button) {
    // Add to module... dialog
    Dialog dlgAlltoModule = this.engine
        .findElementWithinDuration(Criteria.isDialog().withTitle("Add to module..."), Duration.ofSeconds(60)).getFirstElement();
    // Clear filters
    try {
      Button btnClearAllFilter =
          this.engine.findElementWithDuration(Criteria.isButton().withToolTip(RMConstants.CLEAR_FILTER_BUTTON_TOOLTIP), Duration.ofSeconds(30)).getFirstElement();
      btnClearAllFilter.click();
      LOGGER.LOG.info("Click on Clear all filters button");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    // Search Artifact
    Text txtSearchArtifact = this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID).inContainer(dlgAlltoModule), Duration.ofSeconds(60)).getFirstElement();
    txtSearchArtifact.setText(artifactID + Keys.ENTER);
    // Select Artifact
    Link linktArtifact = this.engine.findElementWithinDuration(Criteria.isLink().withText(artifactID + ":"), Duration.ofSeconds(60)).getFirstElement();
    linktArtifact.click();
    // Click on Add / Add and Close button
    Button btnAdd = this.engine.findElementWithinDuration(Criteria.isButton().withText(button).inContainer(dlgAlltoModule), Duration.ofSeconds(60)).getFirstElement();
    btnAdd.click();
    waitForSecs(5);
  }

  /**
   * In module page, perform Add an artifact to module
   * @author VDY1HC
   * @param artifactID - artifact to be added
   */
  public void addArtifactToModule (final String artifactID) {
    WebElement drdCreateArtifact = this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_CREATE_BUTTON_XPATH);
    this.driverCustom.getClickableWebElement(drdCreateArtifact).click();
    WebElement opt = this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_VIEWOPTIONS_MENU_XPATH, "Add an Existing Artifact");
    opt.click();
    addArtifactToModule(artifactID, "Add and Close");
    this.waitForPageLoaded();
  }
  /**
   * <p>
   * Search and Open any existing module,Module page is displayed.<br>
   * List of Artifacts displayed in the module page.<br>
   * Select the menu launcher icon contains the row which artifact you want to remove from the module.<br>
   * Select the option 'Remove Artifact...' from the drop down option.<br>
   * 'Confirm Removal' dialog is displayed.<br>
   * Click on 'Remove' button.
   *
   * @param artifactID id of artifact need to remove from module/collection
   */
  public void removeArtifact(final String artifactID) {

    Row rowArtifact = this.engine
        .findElementWithDuration(Criteria.isRow().containsText(artifactID),  Duration.ofSeconds((this.timeInSecs.getSeconds() / 5))).getFirstElement();
    Dropdown drdMenu = this.engine
        .findElementWithDuration(Criteria.isDropdown().inContainer(rowArtifact), Duration.ofSeconds(WAITING_TIME_IN_SEC)).getFirstElement();
    drdMenu.selectOptionWithText("Remove Artifact...");
    LOGGER.LOG.info("Select drop down menu of " + artifactID + " then select 'Remove Artifact...'");
    waitForSecs(3);

    Dialog dlgRemoveArtifact =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Confirm Removal"), Duration.ofSeconds(WAITING_TIME_IN_SEC))
        .getFirstElement();
    Button btnRemove =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Remove").inContainer(dlgRemoveArtifact),
            Duration.ofSeconds(WAITING_TIME_IN_SEC)).getFirstElement();
    btnRemove.click();
    LOGGER.LOG.info("Click on Remove button to confirm");
    waitForSecs(5);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules,'Collections' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Select the folder from the displayed folder list.<br>
   * Click on drop down menu present next to folder name.<br>
   * Select the option 'Create Artifact',list of options displayed.<br>
   * Select the option Artifact type/'Other...' as sub menu,'Create Artifact' dialog will displayed.
   *
   * @param subMenu in the Create context menu
   */
  public void openCreateContextMenuAndSelectSubmenu(final String subMenu) {
    Dropdown drdAddArtifact = this.engine
        .findElementWithDuration(Criteria.isDropdown().withToolTip("Create New Artifact"), Duration.ofSeconds(300))
        .getFirstElement();
    drdAddArtifact.selectOptionWithText(subMenu + "...");
  }

  /**
   * <p>
   * Verify All Artifacts ID with Excel file Artifacts ID.
   *
   * @param path excel file path.
   * @param ids Artifact IDs.
   * @throws FileNotFoundException to handle exception.
   */
  public void verifyExcelID(final String path, final String ids) throws FileNotFoundException {
    String[] idss = ids.split(",");
    FileInputStream fs = new FileInputStream(path);
    try (Workbook wb = WorkbookFactory.create(fs);) {
      Sheet sh = wb.getSheetAt(0);
      int rw = sh.getLastRowNum() - 7;
      for (int i = 1; i <= rw; i++) {
        int cellval = (int) sh.getRow(i).getCell(0).getNumericCellValue();
        String cellvalue = cellval + "";
        if (cellvalue.equalsIgnoreCase(idss[i - 1])) {
          LOGGER.LOG.info("Module id " + idss[i - 1] + " is matched with Excel id " + cellvalue);
        }
        else {
          throw new WebAutomationException("Module id " + idss[i - 1] + " is not matched with Excel id " + cellvalue);
        }
      }
    }
    catch (Exception e) {
      waitForSecs(2);
    }
  }

  /**
   * <p>
   * Get the URL of linked Artifact from the Excel file.
   *
   * @param path excel file path.
   * @return artifact URL.
   * @throws FileNotFoundException to handle exception.
   */
  public String getArtifactUrl(final String path) throws FileNotFoundException {
    FileInputStream fs = new FileInputStream(path);
    String url = "";
    try (Workbook wb = WorkbookFactory.create(fs);) {
      Sheet sh = wb.getSheetAt(0);
      int rw = sh.getLastRowNum() - 7;
      for (int i = 1; i <= rw; i++) {
        int lstcel = sh.getRow(i).getLastCellNum();
        for (int j = 1; j <= lstcel; j++) {

          Pattern p = Pattern.compile("https://(.*?)}$");
          String data = "";
          try {
            data = sh.getRow(i).getCell(j).getNumericCellValue() + "";
          }
          catch (Exception e) {
            waitForSecs(2);
          }
          try {
            data = sh.getRow(i).getCell(j).getStringCellValue();
          }
          catch (Exception e) {
            waitForSecs(2);
          }
          Matcher m = p.matcher(data);
          if (m.find()) {
            return m.group(0).trim().substring(0, m.group(0).indexOf('}', 4));

          }
        }
      }
    }
    catch (Exception e) {
      waitForSecs(2);
    }
    if (url.isEmpty()) {
      throw new WebAutomationException("Link not found from the Excel file.");
    }
    LOGGER.LOG.info("Artifact URL is : " + url);
    return url;
  }

  /**
   * <p>
   * Click on drop down option under Artifact menu drop down.
   *
   * @param option drop down value.
   */
  public void clickOnDropdownOption(final String option) {
    Cell cel = this.engine.findElement(Criteria.isCell().withText(option)).getFirstElement();
    cel.click();
  }

  /**
   * <p>
   * Verify Artifact ID from the CSV file.
   *
   * @param filePath path of CSV file.
   * @param option drop down value.
   * @return CSV id.
   */
  public boolean verifyCSVid(final String filePath, final String option) {
    try (CSVReader cs = new CSVReader(new FileReader(filePath));) {
      List<String[]> test = cs.readAll();
      for (String[] data : test) {
        for (String da : data) {
          if (da.contains(option)) {
            return true;
          }
        }
      }
    }
    catch (Exception e) {
      waitForSecs(2);
    }
    return false;
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         Verify test data from the CSV file.
   * @param filePath path of CSV file.
   * @param testdata test data value.
   * @return CSV testdata.
   */
  public boolean isDataPresentInCSVfile(final String filePath, final String testdata) {
    try (CSVReader cs = new CSVReader(new FileReader(filePath));) {
      List<String[]> test = cs.readAll();
      for (String[] data : test) {
        for (String da : data) {
          if (da.contains(testdata)) {
            LOGGER.LOG.info("'" + testdata + "' Attribute present in the file");
            return true;
          }
        }
      }
    }
    catch (Exception e) {
      LOGGER.LOG.info("'" + testdata + "' Not present in the file");
      waitForSecs(2);
    }
    LOGGER.LOG.info("'" + testdata + "' Not present in the file");
    return false;
  }

  /**
   * <p>
   * Get the URL of Linked Artifact from the Excel file.
   *
   * @param filePath path of file.
   * @return artifact URL.
   */
  public String getCSVArtifactURL(final String filePath) {
    try (CSVReader cs = new CSVReader(new FileReader(filePath));) {
      List<String[]> test = cs.readAll();
      for (String[] data : test) {
        for (String da : data) {
          Pattern p = Pattern.compile("Export Module=https://(.*?)$");
          Matcher m = p.matcher(da);
          if (m.find()) {
            return m.group(0).trim().substring(14);
          }
        }
      }
    }
    catch (Exception e) {
      waitForSecs(2);
    }
    return null;
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_TYPETOFILTERTEXTBOX_XPATH);
    }
    catch (Exception e) {}
  }


  /**
   * <p>
   * Access to Module page <br>
   * Click on More Ations icon and select Duplicate Artifact {@link RMModulePage#moreAction(String)} .<br>
   * Input "new Name", "Choose destination folder", "Copy items from the original artifact", "Duplicating or Reusing
   * Artifacts in Modules" and click on OK button to genereate duplicated Artifact .<br>
   *
   * @param additionalParams stores key values for:<br>
   *          'newName': New name of duplicated artifact.<br>
   *          'destinationFolder': Name of the existing folder store duplicated artifact<br>
   *          'isCheckCopyLinks': true if checked, false if un-checked <br>
   *          'isCheckCopyTags': true if checked, false if un-checked <br>
   *          'duplicationPolicy': select option in Duplication policy drop-down <br>
   * @return true if copied artifact is generated successfully and dialog close.
   */
  public boolean duplicateArtifactToFolder(final Map<String, String> additionalParams) {
    boolean resultMessage = false;
    // wait for load dialog:
    this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_DUPLICATEARTIFACT_NEWNAME_XPATH, Duration.ofSeconds(50));

    // Input new Name:
    this.driverCustom.getWebElement(RMConstants.RMMODULEPAGE_DUPLICATEARTIFACT_NEWNAME_XPATH)
    .sendKeys(additionalParams.get("newName"));
    LOGGER.LOG.info("Input new Name to Duplicate Artifact to Folder as " + additionalParams.get("newName"));

    // Select Destinate Folder
    Actions act = new Actions(this.driverCustom.getWebDriver());
    WebElement element = this.driverCustom
        .getWebElement(RMConstants.RMMODULEPAGE_DUPLICATEARTIFACT_DESTINATIONFOLDER_XPATH, additionalParams.get("destinationFolder"));
    ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].scrollIntoView();", element);
    act.moveToElement(element);
    act.click().build().perform();

    //    this.driverCustom.clickTwice(RMConstants.RMMODULEPAGE_DUPLICATEARTIFACT_DESTINATIONFOLDER_XPATH,
    //        additionalParams.get("destinationFolder"));

    LOGGER.LOG.info(
        "Select destinate folder in Duplicate Artifact to Folder as " + additionalParams.get("destinationFolder"));

    // Copy items from the original artifact - Copy Link
    String getStatusCopyLink = this.driverCustom
        .getWebElement(RMConstants.RMMODULEPAGE_DUPLICATEARTIFACT_COPYLINKS_XPATH).getAttribute("aria-checked");

    if (!getStatusCopyLink.equalsIgnoreCase(additionalParams.get("isCheckCopyLinks"))) {
      Checkbox cbxCopyLink = this.engine.findElement(Criteria.isCheckbox().withLabel("Copy links")).getFirstElement();
      cbxCopyLink.click();
      LOGGER.LOG.info(
          "Check/uncheck Copy Link in Duplicate Artifact to Folder as " + additionalParams.get("isCheckCopyLinks"));
    }
    else {
      LOGGER.LOG.info(
          "Not check/uncheck Copy Link in Duplicate Artifact to Folder as " + additionalParams.get("isCheckCopyLinks"));
    }


    // Copy items from the original artifact - Copy Tag
    String getStatusCopyTag = this.driverCustom.getWebElement(RMConstants.RMMODULEPAGE_DUPLICATEARTIFACT_COPYTAGS_XPATH)
        .getAttribute("aria-checked");
    if (!getStatusCopyTag.equalsIgnoreCase(additionalParams.get("isCheckCopyTags"))) {
      Checkbox cbxCopyTag = this.engine.findElement(Criteria.isCheckbox().withLabel("Copy tags")).getFirstElement();
      cbxCopyTag.click();
      LOGGER.LOG
      .info("Check/uncheck Copy Tag in Duplicate Artifact to Folder as " + additionalParams.get("isCheckCopyTags"));
    }
    else {
      LOGGER.LOG.info(
          "Not check/uncheck Copy Tag in Duplicate Artifact to Folder as " + additionalParams.get("isCheckCopyTags"));
    }


    // Duplicating or Reusing Artifacts in Modules - Duplication Policy
    Dropdown selDuplicationPolicy =
        this.engine.findElement(Criteria.isDropdown().withDefaultValue("Duplicate all artifacts")).getFirstElement();
    selDuplicationPolicy.selectOptionWithPartText(additionalParams.get("duplicationPolicy"));

    // Click on OK button
    this.driverCustom.isElementClickable(RMConstants.JAZZPAGE_BUTTON_XPATH, Duration.ofSeconds(10), RMConstants.OK);
    this.driverCustom.click(RMConstants.JAZZPAGE_BUTTON_XPATH, RMConstants.OK);


    // wait for close dialog
    waitForSecs(10);
    if (this.driverCustom.isElementInvisible(RMConstants.RMMODULEPAGE_DUPLICATEARTIFACT_NEWNAME_XPATH, Duration.ofSeconds(300))) {
      resultMessage = true;
    }

    return resultMessage;
  }

  /**
   * <p>
   * Get number of Artifact showing in table
   *
   * @return Number of Artifact showing in table as String variable type.
   */
  public String getNumberOfArtifactShowInTable() {
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE, Duration.ofSeconds((this.timeInSecs.getSeconds() * 2)));
    List<WebElement> lstNumberOfArtifact = this.driverCustom.getWebElements(RMConstants.NUMBER_ARTIFACT_SHOWING_IN_TABLE_XPATH);
    String showingArtifacttxt = "";
    String count = "";
    for (WebElement numberOfArtifact : lstNumberOfArtifact) {
      if (numberOfArtifact.isDisplayed()) {
        showingArtifacttxt = numberOfArtifact.getText();
      }
    }

    count = showingArtifacttxt.split(" ")[1].trim();
    LOGGER.LOG.info("number: " + count);
    return count;
  }

  /**
   * <p>
   * after go to the module successfully, check if there one filter or not, if yes, click on 'Clear all filters' button
   * then type the artifact ID or artifact content that you want to search then press 'Enter'
   * <p>
   *
   * @author NVV1HC
   * @param artifactIDOrContent ID or content of artifact you want to search
   * @param isClearFilter option to clear filter before searching an artifact
   */
  public void searchArtifact(final String artifactIDOrContent, final String isClearFilter) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    if (isClearFilter.equalsIgnoreCase("true")) {
      try {
        RMArtifactsPage artifactPage = new RMArtifactsPage(driverCustom);
        artifactPage.clearFilter();
      }
      catch (Exception e) {}
    }
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    Text txtSearchArtifact = this.engine.findElementWithDuration(
        Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID),
        Duration.ofSeconds(WAITING_TIME_IN_SEC)).getFirstElement();
    txtSearchArtifact.setText(artifactIDOrContent + Keys.ENTER);
    LOGGER.LOG.info("Input " + artifactIDOrContent + " in 'Type to filter artifacts by text or by ID' text field");
    waitForSecs(5);
  }

  /**
   * <p>
   * after searching artifact by ID using ${@link RMModulePage#searchArtifact(String,String)} then click on edit content button
   * using ${@link RMModulePage#clickOnEditContentOfArtifact(String)} <br>
   * then click on "Insert" button -> select type of artifact to be insert, e.g: Table, Image, Artifact, Symbol, etc..
   * "Insert Artifact" dialog is displayed, input the target artifact ID that you want to insert to the search box, then
   * select the search result and click on "OK" button to insert and close "Insert Artifact" dialog
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of source artifact you want to edit content
   * @param typeOfInsertedContent type of artiface to be inserted: Table, Image, Artifact, etc..
   * @param insertedArtifactID ID of artifact will in inserted into source artifact
   */
  public void editArtifact_insertContent(final String artifactID, final String typeOfInsertedContent,
      final String insertedArtifactID) {
    filterArtifactByTextOrId(artifactID);
    clickOnEditContentOfArtifact(artifactID);

    this.driverCustom
    .getPresenceOfWebElement(RMConstants.RMMODULEPAGE_INSERTBUTTON_AFTERCLICKONEDITCONTENTOFANARTIFACT_XPATH)
    .click();
    waitForSecs(1);
    try {
      this.driverCustom.switchToFrame(RMConstants.RMMODULEPAGE_IFRAME_AFTERCLICKINGINSERTBUTTON);
      waitForSecs(2);
    }
    catch (Exception e) {}
    this.driverCustom
    .getPresenceOfWebElement(RMConstants.RMMODULEPAGE_TYPEOFARTIFACT_TOBEINSERTED_XPATH, typeOfInsertedContent)
    .click();

    Dialog dlgAlltoModule =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Insert Artifact"),  Duration.ofSeconds(WAITING_TIME_IN_SEC))
        .getFirstElement();
    LOGGER.LOG.info("Displayed 'Insert Artifact' dialog.");
    try {
      Button btnClearAllFilter =
          this.engine.findElementWithDuration(Criteria.isButton().withToolTip(RMConstants.CLEAR_FILTER_BUTTON_TOOLTIP),
              Duration.ofSeconds(WAITING_TIME_IN_SEC)).getFirstElement();
      btnClearAllFilter.click();
      LOGGER.LOG.info("Click on Clear all filters button");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    waitForSecs(3);

    Text searchArtifactTextbox = this.engine.findElementWithDuration(Criteria.isTextField()
        .withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID).inContainer(dlgAlltoModule),
        Duration.ofSeconds(WAITING_TIME_IN_SEC)).getFirstElement();
    searchArtifactTextbox.setText(insertedArtifactID + Keys.ENTER);
    LOGGER.LOG.info("Input " + insertedArtifactID + " in 'Type to filter artifacts by text or by ID' text field");
    waitForSecs(3);

    try {
      this.driverCustom.click(RMConstants.ARTIFACTRESULT_AFTERSEARCHINGTOADDORINSERT_XPATH, insertedArtifactID);
      LOGGER.LOG.info("Select " + insertedArtifactID + " artifact");
    }
    catch (Exception e) {
      throw new InvalidArgumentException(insertedArtifactID + " - doesn't found.");
    }

    Button btnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK")/* .inContainer(dlgAlltoModule) */,
            Duration.ofSeconds(WAITING_TIME_IN_SEC)).getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("Click on OK button");
    waitForSecs(3);
    this.driverCustom.click(RMConstants.RMMODULEPAGE_REFRESHBUTTON_XPATH);
    waitForPageLoaded();
    waitForSecs(8);
  }

  /**
   * <p>
   * After going to one module using ${@link RMDashBoardPage#quickSearch(String)} click on "Create" button and select
   * "Create Other Artifact Type..." option <br>
   * "Create Artifact" dialog is displayed, set content, artifact type and artifact format as expected then click "OK"
   * button to finish creating a new artifact inside a module <br>
   * <p>
   *
   * @author NVV1HC
   * @param additionalParams stores key values for:<br>
   *          'ARTIFACT_NAME': name of new artifact<br>
   *          'ARTIFACT_TYPE': type of new artifact<br>
   *          'ARTIFACT_FORMAT': format new of artifact <br>
   * @return ID of created artifact
   */
  public String createANewArtifact(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    waitForSecs(5);
    this.driverCustom.getWebElement(RMConstants.RMMODULEPAGE_CREATEBUTTON_XPATH).click();
    waitForSecs(5);
    this.driverCustom
    .getWebElement(RMConstants.RMARTIFACTPAGE_CREATEOTHERARTIFACTTYPE_SELECTION_XPATH,
        "Create Other Artifact Type").click();

    Dialog createArtifactDialog = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Create Artifact"), this.timeInSecs).getFirstElement();
    Text txtInitalContent = this.engine.findElementWithDuration(
        Criteria.isTextField().hasLabel(RMConstants.INITIAL_CONTENT).isMultiLine().inContainer(createArtifactDialog),
        this.timeInSecs).getElementByIndex(1);
    txtInitalContent.setText(additionalParams.get("ARTIFACT_NAME"));

    Dropdown drdArtifactType = this.engine.findFirstElementWithDuration(
        Criteria.isDropdown().withLabel("Artifact type:").inContainer(createArtifactDialog), this.timeInSecs);
    waitForSecs(2);
    drdArtifactType.selectOptionWithText(additionalParams.get("ARTIFACT_TYPE"));
    waitForSecs(2);
    try {
      Dropdown drdArtifacFormat = this.engine.findFirstElementWithDuration(
          Criteria.isDropdown().withLabel("Artifact format:").inContainer(createArtifactDialog), this.timeInSecs);
      drdArtifacFormat.selectOptionWithText(additionalParams.get("ARTIFACT_FORMAT"));
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    Button btnOK = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(createArtifactDialog), this.timeInSecs)
        .getFirstElement();
    btnOK.click();
    waitForSecs(60);

    return this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_ARTIFACTIDJUSTCREATED_XPATH).getText();
  }

  /**
   * <p>
   * Search any existing module using {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module / Artifact using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'More Actions' icon(icon will display with three lines) present right side of the Modules / Artifact
   * page.<br>
   * Click on 'Open History' option from the 'More Actions' drop down using {@link RMModulePage#openHistory()} <br>
   * Base on the time and the version number given, select the represent thumbnail.<br>
   * Click on 'More Actions' icon(icon will display with three lines) present right side of History page.<br>
   * Click on 'Restore' option from the 'More Actions' drop down.<br>
   * Click "Yes" button in Confirmation Dialog 'History' page of a module / artifact must be loaded before this method
   * is called. <br>
   *
   * @param timeline - The time that contains date of selected verison (Ex: Today, Yesterday, Past Week,...) <br>
   * @param versionNumber - The order number of the version in the time, count from the right to left, start with 1.
   */
  public void restoreRevisionUsingHistory(final String timeline, final String versionNumber) {
    Tab tabRevisions =
        this.engine.findElementWithDuration(Criteria.isTab().withText("Revisions"), Duration.ofSeconds(60)).getFirstElement();
    tabRevisions.click();
    String timelines = timeline.replace(" ", "").toLowerCase();
    WebElement versionToBeRestore = this.driverCustom.getWebElement("//td[contains(@dojoattachpoint,'" + timelines +
        "')]//span[contains(@class,'thumbnailBorder')][" + versionNumber + "]");
    versionToBeRestore.click();
    Dropdown drdMoreActions = this.engine.findFirstElement(Criteria.isDropdown().withToolTip("More Actions"));
    drdMoreActions.selectOptionWithPartText("Restore");
    Button btnYes = this.engine.findElementWithDuration(Criteria.isButton().withText("Yes"), Duration.ofSeconds(60)).getFirstElement();
    btnYes.click();
  }

  /**
   * <p>
   * Search any existing module using {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Open the History of the module using [{@link RMModulePage#openHistory()}.<br>
   * Click on 'More Actions' icon(icon will display with three lines) present right side of the Modules History
   * page.<br>
   * Select the option in "More Action" menu.
   *
   * @param option - menu item (Ex: 'Show Full Hierarchy', 'Show 1 Level', 'Duplicate Artifact",...)
   * @return option
   */
  public String moreAction(final String option) {
    waitForPageLoaded();

    Button moreActionButton =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("More Actions"), Duration.ofSeconds(WAITING_TIME_IN_SEC))
        .getFirstElement();
    moreActionButton.click();

    // Show artifact using Hierarchy
    if (option.equals("Show Full Hierarchy") || option.equals(RMConstants.LEVELNAME)) {
      WebElement showHierarchyMenuItem = this.driverCustom.getWebElement("//*[contains(@class,'hierarchyOn')]");
      showHierarchyMenuItem.click();
      WebElement levelLink = this.driverCustom.getWebElement(
          "//table[contains(@class,'dijitMenuActive') and not(contains(@class,'more-actions-menu'))]//td[text()='" +
              option + "']");
      levelLink.click();
      waitForSecs(5);
    }
    else {
      waitForPageLoaded();
      WebElement createLevelLink =
          this.driverCustom.getWebDriver().findElement(By.xpath("//span[text()='" + option + "']"));
      createLevelLink.click();
    }
    return option;
  }

  /**
   * <p>
   * Search an artifact using ${@link RMModulePage#searchArtifact(String,String)} then select this artifact and click on
   * action tooltip -> select "Insert New Artifact" option -> select type of insert, e.g: After, Before, Below (as a
   * Child) <br>
   * after a new artifact is generated as After/Before/Below the target artifact, input content for that new artifact
   * then click to name of current module to the new created artifact is updated <br>
   * <p>
   *
   * @author LTU7HC
   * @param params store pairs of key and value: <br>
   *          "targetArtifactID":target artifact ID you want to insert a new artifact after or before "typeOfInsertion":
   *          insert a new artifact "After", "Before", "Below as a child"
   * @return ID of new created artifact
   */
  public String insertNewArtifact(final Map<String, String> params) {
    String targetArtifactID = params.get("targetArtifactID");
    String typeOfInsertion = params.get("typeOfInsertion");
    String artifactContent = params.get("artifactContent");
    String artifactType = params.get("artifactType");

    this.driverCustom.switchToDefaultContent();
    Row rowArtifact = this.engine.findElementWithDuration(Criteria.isRow().withText(targetArtifactID), timeInSecs).getFirstElement();
    Dropdown drdRowOption = this.engine.findElementWithDuration(Criteria.isDropdown().inContainer(rowArtifact), timeInSecs).getFirstElement();
    drdRowOption.selectOptionsWithText("Insert New Artifact",typeOfInsertion);
    
    if (typeOfInsertion.contains("Other Artifact")) {
      Dialog createArtifactDialog = this.engine
          .findElementWithDuration(Criteria.isDialog().withTitle("Create Artifact"), this.timeInSecs).getFirstElement();
      Text txtInitalContent = this.engine.findElementWithDuration(
          Criteria.isTextField().hasLabel(RMConstants.INITIAL_CONTENT).isMultiLine().inContainer(createArtifactDialog),
          this.timeInSecs).getElementByIndex(1);
      txtInitalContent.setText(params.get("artifactContent"));

      Dropdown drdArtifactType = this.engine.findFirstElementWithDuration(
          Criteria.isDropdown().withLabel("Artifact type:").inContainer(createArtifactDialog), this.timeInSecs);
      waitForSecs(2);
      drdArtifactType.selectOptionWithText(params.get("artifactType"));
      waitForSecs(2);
      try {
        Dropdown drdArtifacFormat = this.engine.findFirstElementWithDuration(
            Criteria.isDropdown().withLabel("Artifact format:").inContainer(createArtifactDialog), this.timeInSecs);
        drdArtifacFormat.selectOptionWithText(params.get("ARTIFACT_FORMAT"));
      }
      catch (Exception e) {
        LOGGER.LOG.error(e.getMessage());
      }
      Button btnOK = this.engine
          .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(createArtifactDialog), this.timeInSecs)
          .getFirstElement();
      btnOK.click();
      waitForSecs(60);
    }else {
    this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_CONTENTAREA_OF_NEWARTIFACTINSERTED, timeInSecs);
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_CONTENTAREA_OF_NEWARTIFACTINSERTED)
    .sendKeys(artifactContent);
    if (!this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_DEFAULT_TYPE_OF_INSERTED_ARTIFACT_XPATH)
        .equalsIgnoreCase(artifactType.toLowerCase())) {
      this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_ARTIFACTTYPE_INSERTED_XPATH, timeInSecs);
      this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_ARTIFACTTYPE_INSERTED_XPATH);
      this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_OTHER_ARTIFACT_TYPE_XPATH, timeInSecs);
      this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_OTHER_ARTIFACT_TYPE_XPATH);
      this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_ARTIFACT_TYPE_XPATH, timeInSecs, artifactType);
      this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_ARTIFACT_TYPE_XPATH, artifactType);
      clickOnButtons("OK");
      if(this.driverCustom.isElementClickable(RMConstants.RMMODULEPAGE_CLOSELOOKUPTERMDIALOGBUTTON_XPATH, timeInSecs)) {
        this.driverCustom.click(RMConstants.RMMODULEPAGE_CLOSELOOKUPTERMDIALOGBUTTON_XPATH);
      }
      this.driverCustom.isElementInvisible(RMConstants.RMARTIFACTSPAGE_SELECT_AN_ARTIFACT_TYPE_DIALOG_XPATH, timeInSecs);
      this.driverCustom.isElementInvisible(RMConstants.RMMANAGECOMPONENTANDCONFIGURATIONPAGE_SAVINGLOADING_XPATH, timeInSecs);
    }
    this.driverCustom.isElementClickable(RMConstants.RMMODULEPAGE_MODULENAME_XPATH, Duration.ofSeconds(20));
    this.driverCustom.click(RMConstants.RMMODULEPAGE_MODULENAME_XPATH);
    }
    if (typeOfInsertion.contains("After") || typeOfInsertion.contains("Below")) {
      waitForSecs(5);
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_AFTER_XPATH, timeInSecs,
          targetArtifactID);
      return this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_AFTER_XPATH,
          targetArtifactID);
    }
    waitForSecs(5);
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_BEFORE_XPATH, timeInSecs,
        targetArtifactID);
    return this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_BEFORE_XPATH,
        targetArtifactID);
  }
  
  /**
   * Open context menu of an artifact using  ${@link RMArtifactsPage#selectArtifactAndOpenContextMenu(String)}
   * Select <Insert ting Artifact> option -> Select a type of insert as Before/After/Below (as a Child)...
   * In the 'Add to module' window, input artifact ID into search box, select target artifact in  the predictive list
   * then click on 'Add and Close' button
   * @author NCY3HC
   * @param additionalParams store pairs of key and value: <br>
   *          "existingArtifactID": artifact that you want to insert into module
   *          "typeOfInsertion": structure when added an existing artifact into module, like After/ Before/Below (as a child)
   *          insert a new artifact "After", "Before", "Below as a child"
   *          targetArtifactID: the selected artifact inside module
   *          

   */
  
  public void insertExistingArtifact(Map<String, String> additionalParams) {
    String existingArtifactID = additionalParams.get("EXISTING_ARTIFACT_ID");
    String typeOfInsertion = additionalParams.get("TYPE_OF_INSERTION");
    String targetArtifactID = additionalParams.get("TARGET_ARTIFACT_ID");

    this.driverCustom.switchToDefaultContent();
    Row rowArtifact = this.engine.findElementWithDuration(Criteria.isRow().withText(targetArtifactID), timeInSecs).getFirstElement();
    Dropdown drdRowOption = this.engine.findElementWithDuration(Criteria.isDropdown().inContainer(rowArtifact), timeInSecs).getFirstElement();
    drdRowOption.selectOptionsWithText("Insert Existing Artifact", typeOfInsertion);
    
    // Waiting for 'Add to module...' dialog displayed
    Dialog dlgAlltoModule = this.engine
        .findElementWithinDuration(Criteria.isDialog().withTitle("Add to module..."), Duration.ofSeconds(60)).getFirstElement();

    // Search Artifact
    Text txtSearchArtifact = this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID).inContainer(dlgAlltoModule), Duration.ofSeconds(60)).getFirstElement();
    txtSearchArtifact.setText(existingArtifactID + Keys.ENTER);
    // Select Artifact
    Link linktArtifact = this.engine.findElementWithinDuration(Criteria.isLink().withText(existingArtifactID + ":"), Duration.ofSeconds(60)).getFirstElement();
    linktArtifact.click();
    // Click on  'Add and Close' button
    Button btnAdd = this.engine.findElementWithinDuration(Criteria.isButton().withText("Add and Close").inContainer(dlgAlltoModule), Duration.ofSeconds(60)).getFirstElement();
    btnAdd.click();
    waitForSecs(5);
  }

  /**
   * <p>
   * precondition: search artifact in search box, and display exectly the target artifact, then click on Contents column
   * of the searched artifact -> click on "Edit Contents" button
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact to be clicked on
   */
  public void clickOnEditContentOfArtifact(final String artifactID) {
    waitForSecs(1);
    WebElement contentArea = this.driverCustom.getWebElement(RMConstants.RMMODULEPAGE_ARTIFACTCONTENT_AREA, artifactID);
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.doubleClick(contentArea).perform();
    waitForSecs(2);
  }

  /**
   * <p>
   * after searching an artifact inside a module, click on edit content of the artifact then press "CTRL + SPACE" to
   * look up the term
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact is look up
   * @param expectedTermValue expected value of term will displays in look up result table
   * @param numberOfTermMatch number of the results match with the look up term
   */
  public void lookUpTerm(final String artifactID, final String expectedTermValue, final String numberOfTermMatch) {
    clickOnEditContentOfArtifact(artifactID);
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.keyDown(Keys.CONTROL).sendKeys("a");
    action.keyDown(Keys.CONTROL).sendKeys(Keys.SPACE).keyUp(Keys.CONTROL).perform();
    waitForSecs(3);
  }

  /**
   * <p>
   * Search artifact using artifact ID ${@link RMModulePage#searchArtifact(String,String)} then click on edit content
   * button of artifact using ${@link RMModulePage#clickOnEditContentOfArtifact(String)} then send keys "CTRL + 'a'" and
   * input a new content <br>
   * click outside (on module name area) to finish editing the content of artifact
   *
   * @param additionalParams stores key values for:<br>
   *          'EDIT_OPTION': option to edit artifact, e.g: Replace new artifact content, Upload a new version, Insert
   *          Image. <br>
   *          'ARTIFACT_ID': ID of artifact to be edited. <br>
   *          'NEW_CONTENT': this param stores a new content of artifact if EDIT_OPTION is 'Replace new artifact
   *          content' or 'Upload a new version'. <br>
   *          'IMAGE_ARTIFACT_ID': ID of image artifact to be inserted into a target artifact. <br>
   *          'IMAGE_FOLDER': Folder contains image
   */
  public void editArtifactContent(final Map<String, String> additionalParams) {
    String option = additionalParams.get("EDIT_OPTION");
    String artifactID = additionalParams.get("ARTIFACT_ID");
    filterArtifactByTextOrId(artifactID);
    clickOnEditContentOfArtifact(artifactID);
    Actions action = new Actions(this.driverCustom.getWebDriver());

    switch (option) {
      case "Replace new artifact content": {
        String newArtifactContent = additionalParams.get("NEW_CONTENT");
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        waitForSecs(1);
        action.sendKeys(newArtifactContent).perform();
        waitForSecs(1);
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_MODULENAME_XPATH).click();
        waitForSecs(3);
      }
      break;
      case "Upload a new version": {
        String newArtifactContent = Paths.get(RMConstants.IMPORT_FILE_LOCATION + additionalParams.get("NEW_CONTENT"))
            .toAbsolutePath().toString();
        WebElement img = this.driverCustom
            .getWebElement("//table[@rowlabel='Artifact " + artifactID + "']//img[@alt='Embedded image']");
        action.contextClick(img).perform();
        waitForSecs(1);
        this.driverCustom.switchToFrame("//iframe[@class='cke_panel_frame']");
        waitForSecs(2);
        WebElement opt = this.driverCustom.getWebElement("//a[@title='Upload a new version']");
        opt.click();
        this.driverCustom.switchToDefaultContent();
        waitForSecs(3);
        if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_UPLOADBUTTON_XPATH, Duration.ofSeconds(5))) {
          this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_UPLOADBUTTON_XPATH).sendKeys(newArtifactContent);
        }
        else {
          JavascriptExecutor jse =(JavascriptExecutor) this.driverCustom.getWebDriver();
          WebElement inputUpload = this.driverCustom.getWebDriver().findElement(By.xpath(RMConstants.RMARTIFACTSPAGE_UPLOADBUTTON_XPATH));
          jse.executeScript("arguments[0].style='display: block;'",inputUpload);
          inputUpload = this.driverCustom.getClickableWebElement(inputUpload);
          inputUpload.sendKeys(newArtifactContent);
        }
        Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"),  Duration.ofSeconds(60)).getFirstElement();
        btnOK.click();
        waitForSecs(5);
      }
      break;
      case "Insert Image": {
        String imageArtifactID = additionalParams.get("IMAGE_ARTIFACT_ID");
        String imageFolder = additionalParams.get("IMAGE_FOLDER");
        WebElement txb = this.driverCustom.getWebElement(RMConstants.EDIT_ARTIFACT_TEXTBOX_XPATH);
        action.contextClick(txb).perform();
        waitForSecs(1);
        this.driverCustom.switchToFrame("//iframe[@class='cke_panel_frame']");
        waitForSecs(2);
        WebElement opt = this.driverCustom.getWebElement("//a[@title='Insert Image']");
        opt.click();
        this.driverCustom.switchToDefaultContent();
        WebElement folder = this.driverCustom.getWebElement("//span[@role='treeitem' and @title='" + imageFolder + "']");
        JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
        je.executeScript("arguments[0].scrollIntoView(true);", folder);
        this.driverCustom.clickUsingActions(folder);
        this.driverCustom.getPresenceOfWebElement("//span[@class='matching-results' and contains(text(),' ')]");
        WebElement txbSearch =
            this.driverCustom.getWebElement("//div[@dojoattachpoint='_artifactPickerDiv']//input[@name='filter-box']");
        txbSearch.click();
        txbSearch.sendKeys(imageArtifactID + Keys.ENTER);
        waitForSecs(2);
        WebElement selectedArtifact =
            this.driverCustom.getWebElement("//a[@class='jazz-ui-ResourceLink' and text()='" + imageArtifactID + ":']/following-sibling::span");
        selectedArtifact.click();
        Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), Duration.ofSeconds(60)).getFirstElement();
        btnOK.click();
        waitForSecs(5);
      }
      break;
      default:
        throw new WebAutomationException("Option - " + option + " - is not handled in automation.");
    }
  }

  /**
   * <p>
   * Check if a filter exises or not, if yes -> click on "Clear all filter" <br>
   * search artifact using ${@link RMModulePage#searchArtifact(String,String)} <br>
   * select artifact -> click on "Action" tolltile and select "Remove Artifact..." option <br>
   * then check on "If the artifact is not in other modules, permanently delete it." check box to confirm delete
   * artifact then click on "Remove and Delete" button
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact you want to delete
   */
  public void deleteArtifact(final String artifactID) {
    this.driverCustom.isElementClickable(RMConstants.RMMODULEPAGE_ARTIFACTCONTENT_AREA, this.timeInSecs, artifactID);
    this.driverCustom.click(RMConstants.RMMODULEPAGE_ARTIFACTCONTENT_AREA, artifactID);
    this.driverCustom.isElementClickable(RMConstants.RMMODULEPATE_ACTION_BUTTON_OF_ARTIFACT, this.timeInSecs,
        artifactID);
    this.driverCustom.click(RMConstants.RMMODULEPATE_ACTION_BUTTON_OF_ARTIFACT, artifactID);
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTPAGE_REMOVEARTIFACT_BUTTON_XPATH, Duration.ofSeconds(20));
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_REMOVEARTIFACT_BUTTON_XPATH);
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTPAGE_CHECKBOX_REMOVEANDDELETEARTIFACT_XPATH, Duration.ofSeconds(20));
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_CHECKBOX_REMOVEANDDELETEARTIFACT_XPATH);
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTPAGE_REMOVEANDDELETE_BUTTON_XPATH, Duration.ofSeconds(20));
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_REMOVEANDDELETE_BUTTON_XPATH);
    // Wait for artifact disappeared after delete
    this.driverCustom.isElementInvisible(RMConstants.RMMODULEPAGE_ARTIFACTCONTENT_AREA, Duration.ofSeconds(10), artifactID);
  }

  /**
   * <p>
   * Check if a filter exises or not, if yes -> click on "Clear all filter" <br>
   * search artifact using ${@link RMModulePage#searchArtifact(String,String)} <br>
   * select artifact -> click on "Action" tolltile and select "Remove Artifact..." option <br>
   * then if click on check box "If the artifact is not in other modules, permanently delete it." if it is enable
   * then click on "Remove and Delete" button
   * <p>
   *
   * @author VDY1HC
   * @param artifactID ID of artifact you want to delete
   * @param expectedIsDelete - expected state of is delete
   * @return is check box delete enable when delete
   */
  public String deleteArtifactAndGetIsDeleteState (final String artifactID, String expectedIsDelete) {
    boolean isDelete = false;
    if (this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_FILTERREMOVEBUTTON_XPATH, Duration.ofSeconds(60))) {
      this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_FILTERREMOVEBUTTON_XPATH);
      waitForSecs(2);
    }
    searchArtifact(artifactID, "false");
    this.driverCustom.isElementClickable(RMConstants.RMMODULEPAGE_ARTIFACTCHECKBOX_XPATH, Duration.ofSeconds(30), artifactID);
    this.driverCustom.click(RMConstants.RMMODULEPAGE_ARTIFACTCHECKBOX_XPATH, artifactID);
    this.driverCustom.isElementClickable(RMConstants.RMMODULEPATE_ACTION_BUTTON_OF_ARTIFACT, Duration.ofSeconds(30), artifactID);
    this.driverCustom.click(RMConstants.RMMODULEPATE_ACTION_BUTTON_OF_ARTIFACT, artifactID);
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTPAGE_REMOVEARTIFACT_BUTTON_XPATH, Duration.ofSeconds(30));
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_REMOVEARTIFACT_BUTTON_XPATH);
    if (this.driverCustom.isEnabled(RMConstants.RMARTIFACTPAGE_CHECKBOX_REMOVEANDDELETEARTIFACT_XPATH)) {
      // If check box Remove and Delete enable click on it
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_CHECKBOX_REMOVEANDDELETEARTIFACT_XPATH);
      waitForSecs(2);
      isDelete = true;
    }
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_REMOVE_BUTTON_XPATH);
    waitForSecs(10);
    return String.valueOf(isDelete);
  }

  /**
   * <p>
   * Verify created link of an artifact is displayed in selected artifact tab
   * <p>
   *
   * @author NVV1HC
   * @param linkType type of link: Satisfied By, Implemented By, Artifact Type, etc..
   * @param artifactIDOrName artifact name or artifact ID
   * @return true if link is displayed in selected tab or vice versa
   */
  public boolean isLinkDisplayedInSelectedArtifactTab(final String linkType, final String artifactIDOrName) {
    waitForPageLoaded();
    waitForSecs(10);
    List<WebElement> listTable =
        this.driverCustom.getWebElements(RMConstants.MODULEPAGE_SELECTEDARTIFACTTAB_LISTLINK, linkType);
    List<String> listLink = new ArrayList<String>();
    for (int i = 0; i < listTable.size(); i++) {
      if (!listTable.get(i).getAttribute("className").equals("resourceRow")) {
        break;
      }
      String[] typeAndIndex = { linkType, Integer.toString(i + 1) };

      listLink.add(
          this.driverCustom.getWebElement(RMConstants.MODULEPAGE_SELECTEDARTIFACTTAB_LINK, typeAndIndex).getText());
    }
    if (listLink.contains(artifactIDOrName)) {
      Reporter.logInfo("Link of artifact '" + artifactIDOrName + "' is displayed in Selected artifact tab");
      return true;
    }
    Reporter.logError("Expected link should be displayed: '" + artifactIDOrName +
        "'\nActual list artifact displayed: '" + listLink + "'");
    fail("Link of artifact '" + artifactIDOrName + "' is not displayed in Selected artifact tab");
    return false;
  }

  /**
   * <p>
   * This method is to expand an artifact section to view the children of the expanded artifact
   * <p>
   *
   * @author NVV1HC
   * @param artifactSection section of artifact, e.g: 1.1
   * @return true if expand successfully or vice versa
   */
  public boolean expandArtifactSection(final String artifactSection) {
    waitForSecs(5);
    WebElement sectionNumber =
        this.driverCustom.getWebElement(RMConstants.MODULEPAGE_SECTIONNUMBER_XPATH, artifactSection);
    ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript(RMConstants.SCROLLINTOVIEW_COMMAND,
        sectionNumber);
    if (this.driverCustom.isElementClickable(RMConstants.MODULEPAGE_COLLAPSED_SECTION_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)),
        artifactSection)) {
      WebElement collapsedSectionNumber =
          this.driverCustom.getWebElement(RMConstants.MODULEPAGE_COLLAPSED_SECTION_XPATH, artifactSection);
      collapsedSectionNumber.click();
      waitForSecs(5);
      ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript(RMConstants.SCROLLINTOVIEW_COMMAND,
          this.driverCustom.getWebElement(RMConstants.MODULEPAGE_SECTIONNUMBER_XPATH, artifactSection));
      waitForSecs(3);
    }
    boolean result = this.driverCustom.isElementVisible(RMConstants.MODULEPAGE_EXPANDED_SECTION_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)), artifactSection);
    if (!result) {
      fail("Expand section number '" + artifactSection + "' failed!");
      return false;
    }
    return true;
  }

  /**
   * <p>
   * This method is to expand an artifact section to view the children of the expanded artifact
   * <p>
   *
   * @author NVV1HC
   * @param artifactSection section of artifact, e.g: 1.1
   * @return true if artifact section is collapsed successfully or vice versa
   */
  public boolean collapseArtifactSection(final String artifactSection) {
    waitForSecs(5);
    WebElement sectionNumber =
        this.driverCustom.getWebElement(RMConstants.MODULEPAGE_SECTIONNUMBER_XPATH, artifactSection);
    ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript(RMConstants.SCROLLINTOVIEW_COMMAND,
        sectionNumber);
    if (this.driverCustom.isElementClickable(RMConstants.MODULEPAGE_EXPANDED_SECTION_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)),
        artifactSection)) {
      WebElement expandedSectionNumber =
          this.driverCustom.getWebElement(RMConstants.MODULEPAGE_EXPANDED_SECTION_XPATH, artifactSection);
      expandedSectionNumber.click();
      waitForSecs(5);
      ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript(RMConstants.SCROLLINTOVIEW_COMMAND,
          this.driverCustom.getWebElement(RMConstants.MODULEPAGE_SECTIONNUMBER_XPATH, artifactSection));
      waitForSecs(3);
    }
    boolean result = this.driverCustom.isElementVisible(RMConstants.MODULEPAGE_COLLAPSED_SECTION_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)), artifactSection);
    if (!result) {
      fail("Collapse section number '" + artifactSection + "' failed!");
      return false;
    }
    return true;
  }

  /**
   * <p>
   * This method is to generate the PDF document from a module
   * <p>
   *
   * @author NVV1HC
   * @param params store options to generate the PDF document, e.g: <"includeComments":"false">,
   *          <"includeAttributes":"false">, <"includeTitlesForInsertedArtifacts":"true">
   */
  public void exportModuleWithPDFOrWordDocument(final Map<String, String> params) {
    waitForPageLoaded();
    String exportOption = params.get("exportOption");
    String isIncludeComments = params.get("includeComments");
    String isIncludeAttributes = params.get("includeAttributes");
    String isIncludeTitlesForInsertedArtifacts = params.get("includeTitlesForInsertedArtifacts");
    String moduleLayout = params.get("moduleLayout");
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_MOREACTION_BUTTON_XPATH).click();
    waitForSecs(2);
    this.driverCustom.click(RMConstants.RMMODULEPAGE_TYPEOFARTIFACT_TOBEINSERTED_XPATH, exportOption);
    waitForSecs(3);
    WebElement includeCommentsElm = this.driverCustom
        .getWebElement(RMConstants.MODULEPAGE_CREATEPDFDOCUMENTDIALOG_INCLUDE_OPTION_XPATH, "Include comments");
    WebElement includeAttributesElm = this.driverCustom
        .getWebElement(RMConstants.MODULEPAGE_CREATEPDFDOCUMENTDIALOG_INCLUDE_OPTION_XPATH, "Include attributes");
    WebElement includeTitlesForInsertedArtifactsElm = this.driverCustom.getWebElement(
        RMConstants.MODULEPAGE_CREATEPDFDOCUMENTDIALOG_INCLUDE_OPTION_XPATH, "Include titles for inserted artifacts");

    WebElement moduleLayoutElm =
        this.driverCustom.getWebElement(RMConstants.MODULEPAGE_CREATEPDFDOCUMENTDIALOG_MODULELAYOUT_SELECTED_XPATH);

    if (!isIncludeComments.equalsIgnoreCase(includeCommentsElm.getAttribute("aria-checked"))) {
      includeCommentsElm.click();
    }
    if (!isIncludeAttributes.equalsIgnoreCase(includeAttributesElm.getAttribute("aria-checked"))) {
      includeAttributesElm.click();
    }
    if (!isIncludeTitlesForInsertedArtifacts
        .equalsIgnoreCase(includeTitlesForInsertedArtifactsElm.getAttribute("aria-checked"))) {
      includeTitlesForInsertedArtifactsElm.click();
    }
    if (!moduleLayout.equalsIgnoreCase(moduleLayoutElm.getText())) {
      this.driverCustom.getWebElement(RMConstants.RMMODULEPAGE_MODULELAYOUT_DROP_XPATH).click();
      waitForSecs(3);
      WebElement moduleLayoutOptionElm =
          this.driverCustom.getWebElement(RMConstants.MODULEPAGE_CREATEPDFDOCUMENTDIALOG_MODULELAYOUT_OPTION_XPATH,
              moduleLayout);
      moduleLayoutOptionElm.click();
    }
    waitForSecs(3);
    this.driverCustom.click(RMConstants.MODULEPAGE_OKBTN_XPATH);
    waitForSecs(15);
    try {
      WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds((this.timeInSecs.getSeconds() *3)));
      wait.until(ExpectedConditions
          .visibilityOfElementLocated(By.xpath(RMConstants.RMARTIFACTPAGE_COPYARTIFACTOK_MASSAGE_XPATH)));
    }
    catch (Exception e) {}
  }

  /**
   * <p>
   * This method is called to open a link in a new tab from DNG
   * <p>
   *
   * @author LTU7HC
   * @param params stores a pair of key and value for: "linkType":"Validated By", "Satisfied By",etc.. "link": test case
   *          ID and name, e.g: "59174: DernCANReporting"
   */
  public void openLinkInTheNewTab(final Map<String, String> params) {
    RMLinksPage rmLinksPage = new RMLinksPage(driverCustom);
    String colName = "";
    if(rmLinksPage.getColoumAttributeByHeader(params.get("linkType")) != null) {
      colName = rmLinksPage.getColoumAttributeByHeader(params.get("linkType"));
    }else {
      String columnHeader =
          String.format("(//td[@role='columnheader']//span[contains(text(), '%s')]/ancestor::td[@role='columnheader'])[1]",
              params.get("linkType"));
      colName = this.driverCustom.getWebDriver().findElement(By.xpath(columnHeader)).getAttribute("column");
    }
    String [] dynamicValues = {colName, params.get("link")};
    WebElement linkToBeClicked =
        this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_LINKOFARTIFACT_XPATH, dynamicValues);
    WebDriver driver = this.driverCustom.getWebDriver();
    JavascriptExecutor je = (JavascriptExecutor) driver;
    je.executeScript("arguments[0].scrollIntoView(true);", linkToBeClicked);
    Actions action = new Actions(driver);
    action.moveToElement(linkToBeClicked).keyDown(Keys.CONTROL).click(linkToBeClicked).keyUp(Keys.CONTROL).build().perform();
    waitForSecs(10);
    LOGGER.LOG.info(String.format("Successfully opened link '%s' in new tab", params.get("link")));
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search artifact using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Click on artifact by using ${@link RMArtifactsPage#clickOnArtifact(String)}. <br>
   * Open History of artifact using ${@link RMModulePage#openHistory()}. <br>
   * Then call this method to click on Audit History tab
   * <p>
   *
   * @author NVV1HC
   * @return true if loading Audit History done or vice versa
   */
  public boolean clickOnAuditHistory() {
    waitForSecs(3);
    try {
      this.driverCustom.click(RMConstants.ARTIFACTHISTORYPAGE_AUDITHISTORY_TAB_XPATH);
      return this.driverCustom.isElementInvisible(RMConstants.ARTIFACTHISTORYPAGE_LOADINGICON_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() *3)));
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search artifact using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Click on artifact by using ${@link RMArtifactsPage#clickOnArtifact(String)}. <br>
   * Open History of artifact using ${@link RMModulePage#openHistory()}. <br>
   * Click on Audit History tab using ${@link RMModulePage#clickOnAuditHistory()}. <br>
   * Then call this method to verify the first displayed message contains the expected message or not
   * <p>
   *
   * @author NVV1HC
   * @param message expected message to be verified
   * @param expectedCondition true if you want to check the first displayed message contains the expected message, or
   *          vice versa
   * @return true if the visible of message is as expectation
   */
  public boolean verifyFirstMessageInAuditHistory(final String message, final String expectedCondition) {
    waitForSecs(5);
    try{
      this.driverCustom.click(RMConstants.AUDITHISTORYPAGE_FIRSTMESSAGEROW_XPATH);
      waitForSecs(2);
    }catch(Exception e){}
    WebElement firstMsgElm = null;
    try {
      firstMsgElm = this.driverCustom.getWebElement(RMConstants.ARTIFACTHISTORYPAGE_FIRSTMESSAGE_XPATH, message);
    }
    catch (Exception e) {}

    if (expectedCondition.equalsIgnoreCase("true")) {
      if ((null != firstMsgElm) & this.driverCustom.isElementVisible(firstMsgElm, Duration.ofSeconds(20))) {
        return true;
      }
      return false;
    }
    if ((null == firstMsgElm) || !this.driverCustom.isElementVisible(firstMsgElm,  Duration.ofSeconds(20))) {
      return true;
    }
    return false;
  }

  /**
   * <p>
   * <p>
   *
   * @author NVV1HC
   * @param expectedNumber expected number of artifacts belong to module
   * @return true if the comparision matches with your expectation, for ex: return true if you want to check the number
   *         of artifacts displayed is the same with the number you expected, isEqual = true
   */
  public boolean verifyNumberOfArtifactDisplayedInModule(final String expectedNumber) {
    return getNumberOfArtifactShowInTable().equals(expectedNumber);
  }

  /**
   * Navigated to Module page, drag and hold artifact row to change artifact order <br>
   * @author VDY1HC
   * @param mainArtifactID - Artifact ID to be drag
   * @param targetArtifactID - Artifact ID to be move to
   * @param isAfterTargetArtifact - true if Position is after target artifact
   */
  public void clickAndDragArtifactToChangeOrder (final String mainArtifactID, final String targetArtifactID, final String isAfterTargetArtifact) {
    WebElement mainArtifact = this.driverCustom.getPresenceOfWebElement
        ("//td[@colid='_selector' and contains(@aria-label,'Artifact " + mainArtifactID + "')]//div[@class='dragHandle']");
    WebElement targetArtifact = this.driverCustom.getPresenceOfWebElement
        ("//td[@colid='_selector' and contains(@aria-label,'Artifact " + targetArtifactID + "')]");
    int positionX = targetArtifact.getLocation().getX() + 100;
    int positionY = targetArtifact.getLocation().getY();
    if (isAfterTargetArtifact.equalsIgnoreCase("true")) {
      positionY = positionY - targetArtifact.getSize().getHeight();
    }
    Actions act = new Actions(this.driverCustom.getWebDriver());
    act.moveToElement(mainArtifact).perform();
    waitForSecs(2);
    act.clickAndHold(mainArtifact).dragAndDropBy(mainArtifact, positionX, positionY).build().perform();
    waitForSecs(10);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Click on module by using ${@link RMArtifactsPage#clickOnArtifact(String)}. <br>
   * Then call this method to verify the number artifact and percentage display at the bottom of page
   * <p>
   *
   * @author NVV1HC
   * @param expectedPercentage expected percentage of artifacts displayed
   * @return true if number artifact and percentage displays as expected input value or vice versa
   */
  public boolean verifyPercentageDisplayed(final String expectedPercentage) {
    waitForSecs(10);
    RMArtifactsPage rmArtifactPage = new RMArtifactsPage(this.driverCustom);
    String percentageDisplayed = rmArtifactPage.getPercentageOfArtifactDisplayed();
    if (percentageDisplayed.trim().equals(expectedPercentage)) {
      return true;
    }
    fail(
        "Verify percentage is displayed incorrectly!\nExpected percentage should be displayed: '" + expectedPercentage +
        "'.\nActual percentage displayed: '" +
        percentageDisplayed + "'");
    return false;
  }

  /**
   * <p>
   * Search for module id in the quick search using {@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open module using {@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Click on create button with option 'Heading', 'Infomation',... using ${@link RMArtifactsPage#clickOnCreateButton(String)}. <br>
   * Then call this method to input the content of artifact display at the bottom of page
   * <p>
   *
   * @author PDU6HC
   * @param name : name of artifact content.
   * @return true if number artifact and percentage displays as expected input value or vice versa
   */
  public String inputNameForArtifactContent(final String name) {
    waitForSecs(10);
    WebElement newArtifactContentTextBox =
        this.driverCustom.getWebElement(RMConstants.RMMODULEPAGE_CONTENTAREA_OF_NEWARTIFACTINSERTED);
    String keysSave =  Keys.chord(Keys.CONTROL, "s");
    newArtifactContentTextBox.clear();
    newArtifactContentTextBox.sendKeys(name, keysSave);
    this.driverCustom.isElementVisible(RMConstants.STATUS_MESSAGE, this.timeInSecs);
    this.driverCustom.isElementInvisible(RMConstants.STATUS_MESSAGE, this.timeInSecs);
    return this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_ARTIFACTIDJUSTCREATED_XPATH).getText();
  }

  /**
   * <p>
   * Search for module id in the quick search using {@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open module using {@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Open context menu of the artifact and click on 'Edit Artifact Row'
   * using {@link RMArtifactsPage#openContextMenuForSelectedArtifact(String, String)}
   * Open Term dialog using {@link RMArtifactsPage#openLookUpTermDialogByRightClick()}
   *
   * Then call this method to click on 'Search other components' in look up term dialog.
   * <p>
   *
   * @author PDU6HC
   */
  public void clickOnSearchOtherComponents() {
    Link lnkSearchOtherComp = this.engine.findFirstElement(Criteria.isLink().withText("Search other components"));
    lnkSearchOtherComp.click();
  }

  /**
   * <p>
   * Search for module id in the quick search using {@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open module using {@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Open context menu of the artifact and click on 'Edit Artifact Row'
   * using {@link RMArtifactsPage#openContextMenuForSelectedArtifact(String, String)}
   * Open Term dialog using {@link RMArtifactsPage#openLookUpTermDialogByRightClick()}
   *
   * Then call this method verify the number of matches in term dialog look up.
   * <p>
   *
   * @author PDU6HC
   * @param termMatches is number of matches in term dialog you want to verify
   * @return return true if the matches is correct or element is represent
   */
  public boolean verifyNumberOfMatchesInTermDialog(final String termMatches) {
    return this.driverCustom.isElementPresent(RMConstants.RMMODULEPAGE_MATCHES_IN_LOOKUP_TERM_DIGLOG_XPATH,
        this.timeInSecs, termMatches);
  }

  /**
   * <p>
   * Search for module id in the quick search using {@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open module using {@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Open context menu of the artifact and click on 'Edit Artifact Row'
   * using {@link RMArtifactsPage#openContextMenuForSelectedArtifact(String, String)}
   * Open Term dialog using {@link RMArtifactsPage#openLookUpTermDialogByRightClick()}
   * Click on 'Search other components' using {@link RMModulePage#clickOnSearchOtherComponents()}
   * Then call this method to add term from another components in term dialog look up.
   * <p>
   *
   * @author PDU6HC
   * @param index is the position of the term hyperlink in the term dialog.
   * @return true if term is added and saved successfully.
   *
   */
  public boolean addTermOfOtherComponentHyperLinkandSave(final String index) {
    waitForSecs(5);
    this.driverCustom.click(RMConstants.RMMODULEPAGE_TERM_INDEX_XPATH, index);
    waitForSecs(5);
    WebElement artifactContentTextBox =
        this.driverCustom.getWebElement(RMConstants.RMMODULEPAGE_CONTENTAREA_OF_NEWARTIFACTINSERTED);
    String keysSave =  Keys.chord(Keys.CONTROL, "s");
    artifactContentTextBox.sendKeys(keysSave);
    return this.driverCustom.isElementInvisible(RMConstants.STATUS_MESSAGE, this.timeInSecs);
  }


  /**
   * <p>
   * Search for module id in the quick search using {@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open module using {@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Then call this method to click on term hyperlink of an artifact.
   * <p>
   *
   * @author PDU6HC
   * @param artifactId is artifact id which contains the term.
   * @param term is the term name you need to click on.
   * @return true if hyperlink of term in artifact is visible.
   *
   */
  public boolean navigateToTermHyperLink(final String artifactId, final String term) {
    String[] artifactIdAndTerm = {artifactId, term};
    boolean termHyperlink = this.driverCustom
        .isElementVisible(RMConstants.TERM_HYPERLINK_IN_ARTIFACT, this.timeInSecs, artifactIdAndTerm);
    Link termLink = this.engine.findFirstElementWithDuration(Criteria.isLink().withText(term), this.timeInSecs);
    termLink.click();
    waitForSecs(5);
    return termHyperlink;
  }

  /**
   * In module details page, open Module tab and get attribute value for attribute name. <br>
   * @author VDY1HC
   * @param attributeName - name of attribute
   * @return value of attribute
   */
  public String getModuleAttribute (final String attributeName) {
    if (!this.driverCustom.isElementPresent("//span[text()='Module']//ancestor::span[@role='tab' and contains(@class,'selected')]", Duration.ofSeconds((this.timeInSecs.getSeconds() / 5))))
    {
      chooseTabInsideModule("Module");
    }
    WebElement attributeField = this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_ATTRIBUTESECTION_ATTRIBUTEVALUE_XPATH, attributeName);
    return attributeField.getText();
  }


  /**
   * <p>
   * Search for module id in the quick search using {@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open module using {@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Then call this method to click on term hyperlink of an artifact.
   * <p>
   *
   * @author PDU6HC
   * @param removeLinkText the text on link you want to remove.
   * @param linkType is the type of link you want to remove.
   * @return true is removing status message disappeared.
   *
   */
  public boolean removeLinkInsideModule(final String removeLinkText, final String linkType) {
    waitForSecs(5);
    this.driverCustom.click("//span[@class='section-content-title' and text()='Module']");
    waitForSecs(5);
    this.driverCustom.click("//span[text()='Module Links']");
    waitForSecs(5);
    this.driverCustom.click("//a[text()='DYNAMIC_VAlUE']//preceding::img[1]", removeLinkText);
    this.driverCustom.click("//span[text()='DYNAMIC_VAlUE']//following::img[1]", linkType);
    waitForSecs(3);
    this.driverCustom.click("//td[text()='Remove']");
    waitForSecs(3);
    this.driverCustom.isElementClickable("//button[text()='Yes']", this.timeInSecs);
    this.driverCustom.click("//button[text()='Yes']");
    return this.driverCustom.isElementInvisible("//div[@class='status-message']", this.timeInSecs);
  }


  /**
   * <p>
   * Search for module id in the quick search using {@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open module using {@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Select artifact using {@link RMArtifactsPage#selectArtifact(String)}. <br>
   *
   * Then call this method to click on edit button of artifact type HSIFlag and
   * change artifact attribute 'AEn_ThermoSystems_HSI-FlagSavedToLeanComErrorStruct:' from 'no' to 'yes' and
   * click done
   * <p>
   *
   * @author PDU6HC
   * @param artifactId is id of artifact selected
   * @param attribute attribute of artifact type 'AEn_ThermoSystems_HSI-Flag' dropdown.
   * @param attributeValue attribute value for attribute.
   * @return true if attribute has value according to requirement.
   *
   */
  public boolean editArtifactTypeHSIFlagDropDownInModule(final String artifactId, final String attribute, final String attributeValue) {
    //    Click on 'Edit' button
    this.driverCustom.isElementClickable(RMConstants.RMMODULEPAGE_EDIT_BUTTON_ON_SIDE_PANEL_XPATH, this.timeInSecs, artifactId);
    this.driverCustom.click(RMConstants.RMMODULEPAGE_EDIT_BUTTON_ON_SIDE_PANEL_XPATH, artifactId);
    //    Click on dropdown
    this.driverCustom.isElementClickable(RMConstants.RMMODULEPAGE_DROPDOWN_OF_HSIFLAG_XPATH, this.timeInSecs, attribute);
    this.driverCustom.click(RMConstants.RMMODULEPAGE_DROPDOWN_OF_HSIFLAG_XPATH, attribute);
    //    Select option
    waitForSecs(10);
    this.driverCustom.isElementClickable(RMConstants.JAZZPAGE_TITLE_XPATH, this.timeInSecs, attributeValue);
    this.driverCustom.click(RMConstants.JAZZPAGE_TITLE_XPATH, attributeValue);
    //    Click on 'Done'
    this.driverCustom.isElementClickable(RMConstants.RMMODULEPAGE_DONE_BUTTON_ON_SIDE_PANEL_XPATH, this.timeInSecs, artifactId);
    this.driverCustom.click(RMConstants.RMMODULEPAGE_DONE_BUTTON_ON_SIDE_PANEL_XPATH, artifactId);

    String[] attributeAndAttributeValue = {attribute, attributeValue};
    return this.driverCustom
        .isElementVisible(RMConstants.RMMODULEPAGE_HSIFLAG_ATTRIBUTE_XPATH, this.timeInSecs, attributeAndAttributeValue);
  }


  /**
   * <p>
   * Search for module id in the quick search using {@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open module using {@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Select artifact using {@link RMArtifactsPage#selectArtifact(String)}. <br>
   * Navigate to 'Audit history' of selected artifact by {@link RMArtifactsPage#navigateToAuditHistory(String)}
   * then call this method to click on 'To module' icon to navigate back to module
   * <p>
   *
   * @author PDU6HC
   *
   */
  public void navigateToModuleFromAuditHistory() {
    this.driverCustom.isElementClickable(RMConstants.RMMODULEPAGE_CLOSE_HISTORY_BUTTON_XPATH, this.timeInSecs);
    this.driverCustom.click(RMConstants.RMMODULEPAGE_CLOSE_HISTORY_BUTTON_XPATH);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Click on module by using ${@link RMArtifactsPage#clickOnArtifact(String)}. <br>
   * Then call this method to expand the children of the target artifact
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact to be expanded
   * @return true if the 'expand' button is clicked successfully
   */
  public boolean expandArtifact(final String artifactID) {
    this.driverCustom.isElementClickable(RMConstants.RMMODULEPAGE_EXPANDARTIFACT_ICON_XPATH, this.timeInSecs,
        artifactID);
    this.driverCustom.click(RMConstants.RMMODULEPAGE_EXPANDARTIFACT_ICON_XPATH, artifactID);
    return this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_EXPANDARTIFACT_ICON_EXPANDED_XPATH,
        this.timeInSecs, artifactID);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Click on module by using ${@link RMArtifactsPage#clickOnArtifact(String)}. <br>
   * Then call this method to expand the children of the target artifact
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact to be expanded
   * @return true if the 'expand' button is clicked successfully
   */
  public boolean collapseArtifact(final String artifactID) {
    if (this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_EXPANDARTIFACT_ICON_EXPANDED_XPATH, this.timeInSecs,
        artifactID)) {
      this.driverCustom.click(RMConstants.RMMODULEPAGE_EXPANDARTIFACT_ICON_XPATH, artifactID);
      return this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_EXPANDARTIFACT_ICON_COLLAPSED_XPATH,
          this.timeInSecs, artifactID);
    }
    LOGGER.LOG.info("The artifact '" + artifactID + "' is already collased.");
    return true;
  }

  /**
   * <p>
   * Search for module id in the quick search using {@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open module using {@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Search artifact using ${@link RMModulePage#searchArtifact(String, String)}. <br>
   * Add maxLb collumn using ${@link RMArtifactsPage#addArtifactAttributeInToArtifactTable(String)} <br>
   * Then call this method to edit the maxLb value of artifact.
   * <p>
   *
   * @author PDU6HC
   * @param artifactID ID of artifact for editing maxLb value
   * @param maxLbValue is value of maxLb you want to set
   * 
   */
  public void editMaxLb(final String artifactID, final String maxLbValue) {
    this.driverCustom.click(RMConstants.RMMODULEPAGE_MAXLB_ROW_XPATH, artifactID);
    this.driverCustom.isElementClickable(RMConstants.RMMODULEPAGE_MAXLB_EDIT_BUTTON_XPATH, this.timeInSecs);
    this.driverCustom.click(RMConstants.RMMODULEPAGE_MAXLB_EDIT_BUTTON_XPATH);
    this.driverCustom.isElementPresent(RMConstants.RMMODULEPAGE_MAXLB_EDIT_DIALOG_XPATH, this.timeInSecs);
    WebElement textBoxMaxLb = this.driverCustom.getWebElement(RMConstants.RMMODULEPAGE_MAXLB_EDIT_TEXTBOX_XPATH);
    textBoxMaxLb.clear();
    textBoxMaxLb.sendKeys(maxLbValue);
    clickOnJazzButtons("OK");
    this.driverCustom.click(RMConstants.RMMODULEPAGE_CONTENT_COLLUMN_XPATH);

  }
  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Open the searched module using ${@link RMArtifactsPage#openRMSpecification(String)}.<br>Search a view using {@link RMArtifactsPage#searchView(String)}.
   * <br>Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>Export the view into XLSX format.<br>Count the number artifacts present inside the exported Excel file.
   * 
   * @param excelPath path of the excel file.
   * @return the number of Artifacts exported to the Excel file.
   * @throws IOException Exception when reading Excel/CSV file.
   */
  public int getNumberOfArtifactsFromExcelFile(final String excelPath) throws IOException {
    int num = 0;
    ExcelCSVReader ex = new ExcelCSVReader(excelPath);
    num = ex.getNumberofArtifactsFromExcelFile();
    return num;
  }
  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Open the searched module using ${@link RMArtifactsPage#openRMSpecification(String)}.<br>Search a view using {@link RMArtifactsPage#searchView(String)}.
   * <br>Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>Add validated by,implemented by etc..links.<br> Export the view into XLSX format.<br>Click on any of the link and navigate to that page.
   * 
   * @param excelFilePath path of the excel file.
   * @param linkType type of link ex-Validated By,Implemented By,Satisfied By etc...
   * @param artifactID id of the Artifact.
   * @return link added with the artifact.
   * @throws IOException Exception when reading Excel/CSV file.
   */
  public String getURLOfLinkedArtifactFromExcelFile(String excelFilePath, final String linkType, final String artifactID) throws IOException {
    String result = "";
    ExcelCSVReader ex = new ExcelCSVReader(excelFilePath);
    result = ex.getUrlofLinkedArtifactFromExcelFile(linkType,artifactID);
    return result;
  }
  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Open the searched module using ${@link RMArtifactsPage#openRMSpecification(String)}.<br>Search a view using {@link RMArtifactsPage#searchView(String)}.
   * <br>Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>Select the artifacts from view using {@link RMArtifactsPage#selectArtifact(String)}.
   * <br>Count the number of artifacts selected from the view.
   * 
   * @return count of the selected artifact.
   */
  public String getSelectedArtifacts() {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(RMConstants.NUMBER_OF_SELECTED_ARTIFACT_SHOWING_IN_TABLE_XPATH, this.timeInSecs);
    String selectedArtifacttxt =
        this.driverCustom.getWebElement(RMConstants.NUMBER_OF_SELECTED_ARTIFACT_SHOWING_IN_TABLE_XPATH).getText();
    String count = "";
    count = selectedArtifacttxt.split(" ")[0].trim();
    LOGGER.LOG.info("Selected Artifact count: " + count);
    return count;
  }
  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Open the searched module using ${@link RMArtifactsPage#openRMSpecification(String)}.<br>Search a view using {@link RMArtifactsPage#searchView(String)}.
   * <br>Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>Export the view into CSV format.
   * <br>Count the number artifacts present inside the exported Excel file.
   * 
   * @param excelPath path of the excel file.
   * @return number of artifacts exported into csv file.
   * @throws CsvValidationException the Exception by Line Validation.
   * @throws IOException Exception when reading Excel/CSV file.
   */
  public int getNumberOfArtifactsFromCSVFile(final String excelPath) throws CsvValidationException, IOException {
    int num = 0;
    ExcelCSVReader ex = new ExcelCSVReader(excelPath);
    num = ex.getNumberofArtifactsFromCSVFile();
    return num;
  }
  /**
   * * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Open the searched module using ${@link RMArtifactsPage#openRMSpecification(String)}.<br>Search a view using {@link RMArtifactsPage#searchView(String)}.
   * <br>Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>Add validated by,implemented by etc..links.<br> Export the view into CSV format.
   * <br>Click on any of the link and navigate to that page from exported csv file.
   * 
   * @param excelFilePath path of the excel file.
   * @param linkType type of link ex-Validated By,Implemented By,Satisfied By etc...
   * @param artifactID id of the Artifact.
   * @return link added with the artifact.
   * @throws IOException Exception when reading Excel/CSV file.
   * @throws CsvValidationException the Exception by Line Validation.
   */
  public String getURLOfLinkedArtifactFromCSVFile(String excelFilePath, final String linkType, final String artifactID) throws IOException, CsvValidationException {
    String result = "";
    ExcelCSVReader ex = new ExcelCSVReader(excelFilePath);
    result = ex.getUrlofLinkedArtifactFromCSVFile(linkType,artifactID);
    return result;
  }

  /**
   * When in Modules, Click on Link in navigation section
   * @author VDY1HC
   * @param linkText - Text of link
   */
  public void clickNavigationLink (String linkText) {
    WebElement lnkNavigationFolder = this.driverCustom.getPresenceOfWebElement("//div[@dojoattachpoint='_resourceHeaderRow']//a[contains(text(),'" + linkText +"')]");
    this.driverCustom.getClickableWebElement(lnkNavigationFolder).click();
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * 
   * Then call this method to verify error message appear if artifact is not in Module using Go To Find in module.
   * 
   * Click on 'Search' button present in Artifacts page inside module, 'Module Find' window is displayed.<br>
   * Click on 'Go To' tab, Enter artifact id in 'Module Find' dialog.<br>
   * Click on 'Go To' button.<br>
   * Verify error message appear: "The artifact ID, 'artifactID', was not found.show details"
   * Close dialog.
   *
   * @author PDU6HC
   * @param artifactID is ID of artifact not belong to this module
   * @return true if found and selected artifact inside module successfully.
   */
  public boolean verifyArtifactNotInModuleUsingModuleGoToFind(final String artifactID) {
    // Click on search Go To icon
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_MODULEARTIFACTSEARCH_BUTTON_XPATH, this.timeInSecs);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_MODULEARTIFACTSEARCH_BUTTON_XPATH);
    // Click on GO TO tab in dialog
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_MODULEFIND_GOTOTAB_XPATH, Duration.ofSeconds(50));
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_MODULEFIND_GOTOTAB_XPATH);
    // Input Artifact ID to searching field
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTPAGE_MODULEFIND_TEXTBOX_XPATH, Duration.ofSeconds(50));
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_MODULEFIND_TEXTBOX_XPATH).sendKeys(artifactID);
    // Click on GO TO button
    this.driverCustom.isElementClickable(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(50), RMConstants.GOTO);
    clickOnJazzButtons(RMConstants.GOTO);

    // Verify if error message exist
    return this.driverCustom
        .isElementPresent(RMConstants.RMMODULEPAGE_ERROR_MESSAGE_MODULE_FIND_XPATH, this.timeInSecs, artifactID);
  }
  
  /**
   * In module page, verify every section number input, the content of that section should be match expected <br>
   * @author VDY1HC
   * @param listOfSectionAndName - List of section number and name (EX: <sectionNumber1>:<sectionHeading1>;<sectionNumber2>:<sectionHeading2>;...
   * @return true if every section number displayed with its heading
   */
  public boolean verifyModuleStructureDisplayedCorrectly(String listOfSectionAndName) {
    boolean result = true;
    String[] array = listOfSectionAndName.split(";");
    Map<String, String> mapOfSectionName = new HashMap<>();
    for (String item : array) {
      mapOfSectionName.put(item.split(":")[0], item.split(":")[1]);
    }
    for (Map.Entry<String, String> entry : mapOfSectionName.entrySet()) {
      if (!this.driverCustom.isElementPresent(RMConstants.ARTIFACT_CONTENT_BY_SECTION_ID_XPATH, timeInSecs, entry.getKey())) {
       throw new WebAutomationException("There was no Section with number: " + entry.getKey());
      }
      WebElement rowArtifact = this.driverCustom.getPresenceOfWebElement(RMConstants.ARTIFACT_CONTENT_BY_SECTION_ID_XPATH, entry.getKey());
      JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
      je.executeScript("arguments[0].scrollIntoView(true);", rowArtifact);
      String contentText = rowArtifact.getText();
      if (!contentText.contains(entry.getValue())) {
        result = false;
      }
    }
    return result;
  }
  /**
   * In a module, verify the new Artifact should be successfully added into the module structure Before/After/Below(as a child) the selected artifact
   * @author NCY3HC
   * @param additionalParams contains:
   *        targetArtifactID: the selected artifact inside module
   *        insertedArtifactID: artifact ID which is insert before the selected artifact
   *        typeOfInsertion: like Before/After/Below as a child when select option 'Insert Existing Artifact'
   * @return true if new artifact is successfully added into the module structure Before the selected artifact
   */
  public boolean verifyInsertExistingArtifactStructureDisplayCorrectly (Map<String,String> additionalParams) {
    boolean result= false;
    String targetArtifactID = additionalParams.get("TARGET_ARTIFACT_ID");
    String insertedArtifactID= additionalParams.get("INSERTED_ARTIFACT_ID");
    String typeOfInsertion = additionalParams.get("TYPE_OF_INSERTION");
    String actualInsertedArtifactID="null";
    
    if (typeOfInsertion.equalsIgnoreCase("Before...")) {
      boolean insertedArtifactVisible= this.driverCustom.isElementVisible(RMConstants.ARTIFACT_ID_BY_ADJACENCE_ABOVE_ARTIFACT_XPATH, timeInSecs, targetArtifactID);
      if(insertedArtifactVisible) {
        //This case happens when insert artifact before the selected artifact which is the first in table
    actualInsertedArtifactID = this.driverCustom.getText(RMConstants.ARTIFACT_ID_BY_ADJACENCE_ABOVE_ARTIFACT_XPATH, targetArtifactID);
    }else {
      //This case happens when insert artifact before the selected artifact which is from the second in table
    actualInsertedArtifactID = this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_BEFORE_XPATH, targetArtifactID);
    }
    }
    if (typeOfInsertion.equalsIgnoreCase("Below (as a Child)...")) {
      actualInsertedArtifactID = this.driverCustom.getText(RMConstants.ARTIFACT_CHILD_ID_BY_PARENT_ARTIFACT_XPATH, targetArtifactID);
    }
    if (typeOfInsertion.equalsIgnoreCase("After...")) {
      actualInsertedArtifactID = this.driverCustom.getText(RMConstants.ARTIFACT_ID_BY_ADJACENCE_BELOW_ARTIFACT_XPATH, targetArtifactID);
    }
    
    if (actualInsertedArtifactID.equalsIgnoreCase(insertedArtifactID)) {
      result = true;
    }
    return result;
  }
  /**
   * <p>
   * After creating a module, click on 'Add existing artifatct' button at the center of page
   * <p>
   *
   * @author NCY3HC
   * @return true if click on Add Existing Artifact option successfully.
   *
   */
  public boolean clickOnAddExistingArtifactOption() {
    this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_ADDEXISTINGARTIFACT_BUTTON_XPATH, Duration.ofSeconds(10));
    Button btn=this.engine.findElement(Criteria.isButton().withText("Add Existing Artifact")).getFirstElement();
    btn.click();
    LOGGER.LOG.info("Add Existing Artifact  --> Clicked successfully");
    return true;
  }
  /**
   * <p>
   * Paste Artifact after copy artifact using method {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)}
   * <p>
   *
   *@author NCY3HC
   * @param params store pairs of key and value: <br>
   *          "targetArtifactID":target artifact ID you want to insert a new artifact after or before "typeOfInsertion":
   *          insert a new artifact "After", "Before", "Below as a child"
   * @return ID of new created artifact
   */
  public String pasteArtifact(final Map<String, String> params) {
    String targetArtifactID = params.get("targetArtifactID");
    String typeOfInsertion = params.get("typeOfInsertion");

    this.driverCustom.switchToDefaultContent();
    Row rowArtifact = this.engine.findElementWithDuration(Criteria.isRow().withText(targetArtifactID), timeInSecs).getFirstElement();
    Dropdown drdRowOption = this.engine.findElementWithDuration(Criteria.isDropdown().inContainer(rowArtifact), timeInSecs).getFirstElement();
    drdRowOption.selectOptionsWithText("Paste Artifact",typeOfInsertion);

    if (typeOfInsertion.contains("After") || typeOfInsertion.contains("Below")) {
      waitForSecs(5);
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_AFTER_XPATH, timeInSecs,
          targetArtifactID);
      return this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_AFTER_XPATH,
          targetArtifactID);
    }
    waitForSecs(5);
    boolean artifactIsDisplay= this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_BEFORE_XPATH, timeInSecs,
        targetArtifactID);
    if (artifactIsDisplay) {
    return this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_BEFORE_XPATH,
        targetArtifactID);
    }
    return this.driverCustom.getText(RMConstants.ARTIFACT_ID_BY_ADJACENCE_ABOVE_ARTIFACT_XPATH,
        targetArtifactID);
  }
  /**
   * <p>
   * Upload New Artifact Before/After/Below as a Child of the selected artifact inside Module
   * <p>
   *
   *@author NCY3HC
   * @param params store pairs of key and value: <br>
   *          "targetArtifactID":target artifact ID you want to insert a new artifact after or before "typeOfInsertion":
   *          insert a new artifact "After", "Before", "Below as a child"
   * @return ID of new created artifact
   */
  public String uploadNewArtifact (final Map<String, String> params) {
    String targetArtifactID = params.get("targetArtifactID");
    String typeOfInsertion = params.get("typeOfInsertion");
    String filePath = params.get(RMConstants.FILETYPE);

    this.driverCustom.switchToDefaultContent();
    Row rowArtifact = this.engine.findElementWithDuration(Criteria.isRow().withText(targetArtifactID), timeInSecs).getFirstElement();
    Dropdown drdRowOption = this.engine.findElementWithDuration(Criteria.isDropdown().inContainer(rowArtifact), timeInSecs).getFirstElement();
    drdRowOption.selectOptionsWithText("Upload New Artifact",typeOfInsertion);
    Dialog uploadFileDialog = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Upload File"), this.timeInSecs).getFirstElement();
    
    if (!filePath.contains("\\")) {
      filePath = Paths.get(RMConstants.IMPORT_FILE_LOCATION + filePath).toAbsolutePath().toString();
    }
    if (this.driverCustom.isElementVisible("(//button[text()='Browse...'])[1]", Duration.ofSeconds(3))) {
      if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_UPLOADBUTTON_XPATH, Duration.ofSeconds(3))) {
        this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_UPLOADBUTTON_XPATH).sendKeys(filePath);
      }
      else {
        JavascriptExecutor jse =(JavascriptExecutor) this.driverCustom.getWebDriver();
        WebElement inputUpload = this.driverCustom.getWebDriver().findElement(By.xpath(RMConstants.RMARTIFACTSPAGE_UPLOADBUTTON_XPATH));
        jse.executeScript("arguments[0].style='display: block;'",inputUpload);
        inputUpload = this.driverCustom.getClickableWebElement(inputUpload);
        inputUpload.sendKeys(filePath);
      }
    }
      Button btnOK = this.engine
          .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(uploadFileDialog), this.timeInSecs)
          .getFirstElement();
      btnOK.click();
    
    if (typeOfInsertion.contains("After") || typeOfInsertion.contains("Below")) {
      waitForSecs(5);
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_AFTER_XPATH, timeInSecs,
          targetArtifactID);
      return this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_AFTER_XPATH,
          targetArtifactID);
    }
    waitForSecs(5);
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_BEFORE_XPATH, timeInSecs,
        targetArtifactID);
    return this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_BEFORE_XPATH,
        targetArtifactID);
}
  /**
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}. <br>
   * Click on 'Modules' type using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on create button present top left of Artifacts page using
   * {@link RMArtifactsPage#clickOnCreateButton(String)}. <br>
   * 'Create Artifact' pop up window will display. <br>
   * Provide necessary details like Module Name,Module Type,Module Format etc.. <br>
   * Click on 'Open Artifact' check box if it's not enabled.<br>
   * Click on 'OK' button present inside 'Create Artifact' window.
   *
   * @param additionalParams list of keys like 'MODULE_NAME','MODULE_TYPE','MODULE_FORMAT'...
   * @return module Name.
   */
  public String createAndOpenModule(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    // Input module name
    Dialog dlgCreateArtifact = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle(RMConstants.CREATE_ARTIFACT),  Duration.ofSeconds(10))
        .getFirstElement();
    Text txtName = this.engine
        .findElementWithDuration(Criteria.isTextField().hasLabel(RMConstants.NAME).inContainer(dlgCreateArtifact),
            Duration.ofSeconds(10))
        .getElementByIndex(2);
    txtName.setText(additionalParams.get("MODULE_NAME"));
    LOGGER.LOG.info("Enter the Module Name as -"+additionalParams.get("MODULE_NAME"));
    // Select Artifact Type
    Dropdown drdArtifactType = this.engine.findFirstElement(
        Criteria.isDropdown().withLabel(Artifact.ARTIFACT_TYPE.toString() + ":").inContainer(dlgCreateArtifact));
    drdArtifactType.selectOptionWithText(additionalParams.get("MODULE_TYPE"));
    LOGGER.LOG.info("Select the Module Type as - "+additionalParams.get("MODULE_TYPE"));
    // Select Artifact Format
    Dropdown drdArtifacFormat = this.engine.findFirstElement(
        Criteria.isDropdown().withLabel(Artifact.ARTIFACT_FORMAT.toString() + ":").inContainer(dlgCreateArtifact));
    try {
    drdArtifacFormat.selectOptionWithText("Module");
    }
    catch(Exception e){
      waitForSecs(1);
    }
    if(!this.driverCustom.isElementVisible("//div[@class='open-artifact-option ']//input[@aria-checked='"+additionalParams.get("OPEN_ARTIFACT")+"']",  Duration.ofSeconds(5))) {
      this.driverCustom.getWebElement("//div[@class='open-artifact-option ']//input").click();
      LOGGER.LOG.info("Clicked on 'Open artifact' check box with value - " +additionalParams.get("OPEN_ARTIFACT"));
     }
    // Click on Ok button
    Button btnOK = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgCreateArtifact),  Duration.ofSeconds(10))
        .getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("Clicked on 'OK' button");
    waitForSecs(5);
    return additionalParams.get("MODULE_NAME");
  }
  /**
   * Open any existing module <br>
   * Get artifact ID that present inside the module using artifact name. <br>
   * @param artifactName : Name of artifact for which you want to get ID
   * @return Artifact ID.
   */
  
  public String getArtifactIDByArtifactName(String artifactName) {
    waitForPageLoaded();
    LOGGER.LOG.info("Get the 'Artifact ID' from 'Artifact Name'.");
   return this.driverCustom.getText(RMConstants.RMMODULEINSIDE_ARTIFACT_ID_BY_ARTIFACT_NAME_XPATH, artifactName);
  }
  
  /** Utility to get current applied view
   * @author VUP5HC
   * @return View name
   */
  public String getViewNameApplied() {
    String viewXpath =
        "//span[@class='condition-summary-title' and text()='View']/following-sibling::span[@class='condition-summary-value']";
    try {
      WebElement lblView = this.driverCustom.getPresenceOfWebElement(viewXpath);
      LOGGER.LOG.info("Getting current applied view name.");
      return lblView.getText();          }
    catch (Exception e) {
      LOGGER.LOG.info(e.getMessage() + "\nCannot get View name. Please check if view applied");
      return null;
    }    
  }

  /** Check if View is applied
   * @author VUP5HC
   * @param viewName view name to check
   * @return true if view is applied, else failed
   */
  public boolean isViewApplied(String viewName) {
    try {
      return getViewNameApplied().equals(viewName);
    } catch (NullPointerException e) {
      return false;
    }
  }
  /**
   * <p> Create a new module.
   * <br>
   * @param option  to select inside a new module.
   * Such as 'Create New Artifact', Add Existing Artifact.
   * 
   */
  public void clickOnOptionToAddContentInModule(final String option) {
   this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH,option).click();
   LOGGER.LOG.info("Clicked on 'Create New Artifact' option.");
  }
  /**
   * <br>Click on navigate icon present left side of the web page.<br>It will navigate to Module page.
   * 
   * @param moduleName name of the module
   */
  public void navigateToModulePage(final String moduleName) {
   
    try {
      waitForSecs(5);
      this.driverCustom.click("//div[@class='client-navigation-icon-back']");
 
    waitForSecs(5);
    LOGGER.LOG.info("Navigated to '"+moduleName+"' expected page.");
    }catch (Exception e) {
      
      Link navigateBackLink = this.engine.findFirstElement(Criteria.isLink().withToolTip("To "+moduleName+""));
      navigateBackLink.click();
      waitForSecs(5);
      LOGGER.LOG.info("Navigated to '"+moduleName+"' module page.");
    }
    
  }
}

