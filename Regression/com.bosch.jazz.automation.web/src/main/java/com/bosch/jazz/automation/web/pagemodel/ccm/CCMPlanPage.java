/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.google.common.collect.ImmutableMap;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.JazzTextFinder;

/**
 * Represents the page showing an existing plan
 */
public class CCMPlanPage extends AbstractCCMPage {

  /**
   * The parameter key that holds the plan uuid
   */
  public static final String PLAN_UUID = "PLAN_UUID";

  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom required for interacting with the browser.
   */
  public CCMPlanPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDropdownFinder(), new JazzButtonFinder(), new JazzTextFinder());
    this.engine.addFinders(new JazzButtonFinder(), new JazzDropdownFinder(), new JazzTextFinder(),
        new JazzDialogFinder(), new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder(),
        new LinkFinder());

  }

  /**
   * Do not use this method if you don't know how to fill 'additionalParams' rather use one of the other provided open
   * methods.
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    String planUUid = additionalParams.get(PLAN_UUID);
    this.driverCustom.openURL(
        getProjectAreaURL(repositoryURL, projectAreaName) + "#action=com.ibm.team.apt.viewPlan&id=" + planUUid);

  }

  /**
   * @param repositoryURL the repository URL, must not be null
   * @param projectAreaName the PA name, must not be null
   * @param planUUID the uuid of the plan to open, must not be null
   */
  public void openPlan(final String repositoryURL, final String projectAreaName, final String planUUID) {
    ImmutableMap<String, String> params = ImmutableMap.of(PLAN_UUID, planUUID);
    open(repositoryURL, projectAreaName, params);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * Selecting on the Sub Menu Under the Plan Menu
   *
   * @param planType is Planing Type
   */
  public void clickOnSelectPlanType(final String planType) {


    // Click on Create Cross-Project Plan menu
    Dropdown drdPlans = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Plans"));
    drdPlans.selectOptionWithText(planType);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * Quick Search widget
   *
   * @param planType is type
   * @param planName is name of plan
   * @return Quick search results Text
   */
  public String quickSearch(final String planType, final String planName) {
    waitForPageLoaded();
    this.driverCustom.click(CCMConstants.CCMCREATEPLANPAGE_QUICKSEARCH_BUTTON_XPATH);
    List<WebElement> lst =
        this.driverCustom.getWebElements(CCMConstants.CCMCREATEPLANPAGE_SELECT_PLANS_XPATH, planType);
    for (WebElement el : lst) {
      if (this.driverCustom.isElementVisible(el, Duration.ofSeconds(2))) {
        el.click();
        break;
      }
    }
    this.driverCustom.typeText(CCMConstants.CCMCREATEPLANPAGE_QUICKSEARCH_TEXT_XPATH, planName);
    return this.driverCustom.getWebElement(CCMConstants.CCMPROJECTAREAPAGE_QUICKSEARCH_RESULTSTEXT_XPATH).getText();
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * this method used to return all list of plan
   *
   * @return List of all plans in menu
   */
  public List<String> listOfPlan() {
    return this.driverCustom.getWebElementsText(CCMConstants.CCMCREATEPLANPAGE_ALLPLANS_TEXT_XPATH);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * enter required plan name in search box using{@link CCMPlanPage #searchBox(String)}} click on plan using
   * {@link CCMPlanPage #clickOnPlanPhase}<br>
   * entering filter required work item using{@link CCMPlanPage #filterItems(String)}} <br>
   * This method Checking workitem.
   *
   * @param wiName name of the workitem
   * @return List of all plans in menu
   */
  public boolean checkingWi(final String wiName) {
    return this.driverCustom.isElementVisible(CCMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(5), wiName);
  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * enter required plan name in search box using{@link CCMPlanPage #searchBox(String)}} click on plan using
   * {@link CCMPlanPage #clickOnPlanPhase}
   *
   * @param Plantype Name of Plan Type
   * @return Attribute of Selected Plan
   */
  public String planAttributeStatus(final String Plantype) {
    waitForPageLoaded();
    return this.driverCustom.getWebElement(CCMConstants.CCMCREATEPLANPAGE_PLANS_ATTRIBUTE_XPATH, Plantype)
        .getAttribute("class");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * Retriving the all plans from the particular plan
   *
   * @return the all plans from the particular plan
   */
  public List<String> gettingAllPlans() {
    waitForPageLoaded();
    return this.driverCustom.getWebElementsText(CCMConstants.CCMCREATEPLANPAGE_SELECTEDPLANS_TEXT_XPATH);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * enter required plan name in search box using{@link CCMPlanPage #searchBox(String)}} <br>
   * Seach box in Plan page
   *
   * @param PlanName name of the plan
   */
  public void searchBox(final String PlanName) {
    waitForSecs(Duration.ofSeconds(10));
    waitForPageLoaded();
    try {
      waitForSecs(Duration.ofSeconds(10));
      this.driverCustom.typeText(CCMConstants.CCMCREATEPLANPAGE_SEARCHPLANS_TEXTBOX_XPATH, PlanName);
      LOGGER.LOG.info(PlanName + " : item entered successfully.");
      waitForSecs(Duration.ofSeconds(15));
    }
    catch (Exception e) {
      throw new WebAutomationException(PlanName + ": item not entered in search box.\n " + "or\n " + e.getMessage());

    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * enter required plan name in search box using{@link CCMPlanPage #searchBox(String)}} click on plan using
   * {@link CCMPlanPage #clickOnPlanPhase}
   *
   * @param PlanName is name of plan
   * @return the Attriubte of progres bar
   */
  public boolean planProgressBar(final String PlanName) {
    waitForPageLoaded();
    return this.driverCustom.isElementVisible(CCMConstants.CCMCREATEPLANPAGE_PLANS_PROGRESSBAR_XPATH, Duration.ofSeconds(3), PlanName);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * . Clicking on check box in plan View
   */
  public void showProgressCheckbox() {
    waitForPageLoaded();
    List<WebElement> lst = this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_VALIDITY_CHECKBOX_XPATH);
    for (WebElement el : lst) {
      if (this.driverCustom.isElementVisible(el, Duration.ofSeconds(2))) {
        el.click();
        break;
      }
    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * Clicking on Edit plan View in Selected Plan Phase
   */
  public void clickonEditPlanView() {
    waitForPageLoaded();
    this.driverCustom.click(CCMConstants.CCMCREATEPLANPAGE_EDIT_PLANVIEW_XPATH);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} then click edit phase using
   * {@link CCMPlanPage #clickonEditPlanView()}<br>
   * After clicking 'edit plan view' Selecting the Exculding options from 'Options' dropdown.
   *
   * @param value is Dropdownvalue
   * @param Type expession
   */
  public void excludeWorkItem(final String value, final String Type) {
    waitForPageLoaded();
    WebElement el = this.driverCustom.getWebElement(CCMConstants.CCMCREATEPLANPAGE_EXCLUDEPLANS_SELECTION_XPATH);
    Select se = new Select(el);
    se.selectByVisibleText(value);
    Text txtSearchPlan = this.engine.findElement(Criteria.isTextField().withText("")).getElementByIndex(2);
    txtSearchPlan.clearText();
    txtSearchPlan.setText(Type);
  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} then click edit phase using
   * {@link CCMPlanPage #clickonEditPlanView()}<br>
   * Adding the Attribute in ColumunDisplay
   *
   * @return the Dispalyed Columns List
   */
  public List<String> addAttributeInColumnDisplay() {
    waitForPageLoaded();
    return this.driverCustom.getWebElementsText(CCMConstants.CCMCREATEPLANPAGE_ATTRIBUTELIST_COLUMNDISPALY_XPATH);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}}<br>
   * Clicking on the Selected Plan in Plan Phase
   *
   * @param planName is Clicking on Selected the Plan
   */
  public void clickOnPlanPhase(final String planName) {
    waitForSecs(Duration.ofSeconds(10));
    waitForPageLoaded();
    try {
      this.driverCustom.waitForPageLoaded();
      int breakpoint = 10;
      while (breakpoint >= 0) {
        new Actions(this.driverCustom.getWebDriver())
            .moveToElement(this.driverCustom.getWebElement(CCMConstants.JAZZPAGE_LINKS_XPATH, planName)).click().build()
            .perform();
        waitForSecs(Duration.ofSeconds(20));
        LOGGER.LOG.info("Clicking on the Selected Plan " + planName + " in Plan Phase");
        if (this.driverCustom.isElementVisible(CCMConstants.CCMCREATEQUERYPAGE_PLANPHASE_TEXTBOX_XPATH, Duration.ofSeconds(10))) {

          break;
        }
        breakpoint--;
      }

      ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
    }
    catch (Exception e) {
      throw new WebAutomationException(
          planName + ": item not found to clock,Please provide valid input.\n " + "or\n" + e.getMessage());

    }
    waitForSecs(Duration.ofSeconds(25));
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'well come to plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}}<br>
   * Click Browse To plan link in Welcome plans
   *
   * @return the Selected Plan Title attribute
   */
  public String selectedPlan() {
    waitForPageLoaded();
    return this.driverCustom.getWebElement(CCMConstants.CCMCREATEPLANPAGE_SELECTEDPLANS_TITLE_XPATH).getAttribute("title");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * enter required plan name in search box using{@link CCMPlanPage #searchBox(String)}} <br>
   * Clicking on selected SubButtons(Like edit workitem,refresh,remove....etc)
   *
   * @param wiName is name of created workitem
   * @param subbuttons - Text of sub menu: 1 level - Edit Work Item
   *                                       2 level - Create Work Item > Task
   */
  public void clickOnActionInPlanPhase(final String wiName, final String subbuttons) {
    waitForPageLoaded();
    this.driverCustom.isElementClickable(CCMConstants.CCMCREATEQUERYPAGE_PLANPHASE_TEXTBOX_XPATH, timeInSecs);
    Text txtSearchFields = this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder("Type to Filter"));
    txtSearchFields.click();
    txtSearchFields.clearText();
    txtSearchFields.setText(wiName);
    String[] buttons = subbuttons.split(" > ");
    this.driverCustom.isElementPresent(CCMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(60), wiName);
    WebDriver driver = this.driverCustom.getWebDriver();
    waitForSecs(2);
    try {
    WebElement ele = this.driverCustom.getWebElement("//a[contains(text(),'"+wiName+"')]/..");
    ele.click();
    }catch (Exception e) {
      // TODO: handle exception
    }
    waitForSecs(2);
    String actionMenuXpath = "//a[contains(text(),'" + wiName + "')]//ancestor::tr[@class='rowContent item hover keyboardHover']//a[contains(@class,'toolbarGadget-dropDown')]";
    WebElement btnActionMenu = driver.findElement(By.xpath(actionMenuXpath));
   
  //  WebElement ele = this.driverCustom.getWebElement("//a[text()='Task Work Item for Automation Testing']/..");
   // ele.click();
    // new Actions(this.driverCustom.getWebDriver()).moveToElement(ele).build().perform();
    
    new Actions(driver).moveToElement(btnActionMenu).click().build().perform();
    for (int i = 1; i <= buttons.length; i ++ ) {
      waitForSecs(Duration.ofSeconds(2));
      this.driverCustom.getPresenceOfWebElement
      ("(//table[contains(@class,'dijitMenuActive')] | //table[contains(@class,'dijitMenuSelected ')])[" + i + "]" +
          "//*[contains(text(),'" + buttons[i-1] + "')]").click();
    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * enter required plan name in search box using{@link CCMPlanPage #searchBox(String)}} <br>
   * This method gets Texts of the all the Plan views column display.
   *
   * @return the all the Columns Texts
   */
  public List<String> getPlanViewSelecteColumnDisplay() {
    waitForPageLoaded();
    return this.driverCustom.getWebElementsText(CCMConstants.CCMCREATEPLANPAGE_PLANEVIEWCOLUMNDISPLAY_TEXT_XPATH);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * This method get Plan Name of the all availble in after selecting.
   *
   * @return the all the Plan Names Texts
   */
  public List<String> getPlans() {
    waitForPageLoaded();
    return this.driverCustom.getWebElementsText(CCMConstants.CCMCREATEPLANPAGE_PLANS_TABTEXT_XPATH);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * enter required plan name in search box using{@link CCMPlanPage #searchBox(String)}} <br>
   * Gives the Attribute values in Planing Tab
   *
   * @return the attribute value
   * @param attribute is attribute name
   */
  public List<String> getAttributeValueInPlanPage(final String[] attribute) {
    waitForPageLoaded();
    List<String> list = new ArrayList<>();

    for (String el : attribute) {
      if (this.driverCustom.isElementVisible(CCMConstants.CCMCREATEQUERYPAGE_INLINE_ATTRIBUTE_XPATH, Duration.ofSeconds(2), el)) {
        String data = this.driverCustom.getText(CCMConstants.CCMCREATEQUERYPAGE_INLINE_ATTRIBUTE_XPATH, el);
        String read = this.driverCustom.getWebElement(CCMConstants.CCMCREATEQUERYPAGE_INLINE_ATTRIBUTE_XPATH, el)
            .getAttribute("class");
        list.add(el + ":" + data + ":" + read);
      }
      else if (el.equals("PM Interface Change Log")) {
        list.add(el + ":" + "false");

      }
      else {
        list.add("Attribute is hidden");
      }

    }
    this.driverCustom.getWebDriver().navigate().refresh();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
    return list;
  }

  /**
   * <p>
   * After opening plan using {@link #clickOnSelectPlanType(String)} by name.<br>
   * By default "Planned items" tab is opened, with current iteration items are displayed under the iteration. <br>
   * View all the iteration items in the plan using {@link #viewAs(String)}
   * <p>
   * Select iteration and click on left side action button which opens drop down, Select work item type from the "Create
   * work item" menu. SubButtons[@param Task,Story....etc] Under respective plan name.
   *
   * @param iteration is name of plan where to created work item.
   * @param workItemType is click on sub button(Task,Story....etc)
   */
  public void selectWorkItemToCreateFromLeftSideActionButtonOfPlanItems(final String iteration,
      final String workItemType) {
    waitForPageLoaded();
    this.driverCustom.isElementInvisible("//div[@class='status-message' and contains(text(),'100%')]", Duration.ofSeconds(600));
    try{
    WebElement plannedItem = this.driverCustom.getPresenceOfWebElement("//div[@class='title']/span[text()='DYNAMIC_VAlUE']", iteration);
    plannedItem.click();
    LOGGER.LOG.info("Clicked on " + iteration + " To enable action button");
    }
    catch(Exception e) {
    WebElement plannedItem = this.driverCustom.getPresenceOfWebElement("//div[@class='title' and text()='DYNAMIC_VAlUE']", iteration);
    plannedItem.click();
    LOGGER.LOG.info("Clicked on " + iteration + " To enable action button");
    }
    try {
    WebElement ele = this.driverCustom.getWebElement("//div[@class='title']/span[text()='" + iteration +
        "']/ancestor::tr[1]//div[@actionid='com.ibm.team.apt.web.ui.internal.editor.actions.WorkItemMenuAction']");
    new Actions(this.driverCustom.getWebDriver()).moveToElement(ele).build().perform();
    ele.click();
    }
    catch(Exception e) {
      WebElement ele = this.driverCustom.getWebElement("//div[@class='title' and text()='"+iteration+"']/ancestor::tr[1]//div[@actionid='com.ibm.team.apt.web.ui.internal.editor.actions.WorkItemMenuAction']");
      new Actions(this.driverCustom.getWebDriver()).moveToElement(ele).build().perform();
      ele.click();
    }
    this.driverCustom.click(CCMConstants.CCMCREATEPLANPAGE_CREATEWI_SUBBUTTONS_XPATH, workItemType);
    LOGGER.LOG.info("work item type " + workItemType + " selected from respective plan " + iteration);
  }
  
 

  /**
   * <p>
   * After opening plan using {@link #clickOnSelectPlanType(String)} by name.<br>
   * By default "Planned items" tab is opened, with current iteration items are displayed under the iteration. <br>
   * View all the iteration items in the plan using {@link #viewAs(String)}<br>
   * Click on "Add Work Item" drop down button and select work item using {@link #addWorkItemInsidePlan(String)}
   * <p>
   * click on Action button drop down and select menu .
   *
   * @param summary summary of the work item, if empty new work item action button will be selected.
   * @param menu name of the menu E.g "Create Child Work Item,Edit Work Item,Delete Draft Item,Plan For, Assign To
   *          Owner,Remove,Apply Rank Above,Add Link"
   */
  public void openDropdownFromLeftSideActionButtonOfPlanItemAndSelectMenu(final String summary, final String menu) {
    waitForPageLoaded();
    waitForSecs(Duration.ofSeconds(15));

    try {
      WebElement ele = this.driverCustom
          .getWebElement("//div[@class='outliner']/a[text()='DYNAMIC_VAlUE']/ancestor::tr[1]//a[2]", summary);
      ele.click();
      LOGGER.LOG.info("Clicked on action menu drop down which contains " + summary + " Summary");
      waitForSecs(Duration.ofSeconds(5));
      this.driverCustom.click(CCMConstants.CCMCREATEPLANPAGE_CREATEWI_LEFTACTIONSUBBUTTONS_XPATH, menu);
      waitForSecs(Duration.ofSeconds(10));
      LOGGER.LOG.info("Clicked on " + menu + " Action menu drop down successfully");
    }
    catch (Exception e) {
      try {
        this.driverCustom.getWebElement("(//div[@class='outliner'][./a[text()='']])[last()]").click();
        waitForSecs(Duration.ofSeconds(3));
        LOGGER.LOG.info("Clicked on empty work item to enable work item action menu drop down");
        Actions action = new Actions(this.driverCustom.getWebDriver());
        String dropdownXpath = String.format(
            "//div[@class='outliner']/a[text()='%s']/ancestor::tr[contains(@class,'rowContent')]//a[contains(@class,'dropDown')]",
            summary);
        WebElement dropdown = this.driverCustom.getWebDriver().findElement(By.xpath(dropdownXpath));
        action.moveToElement(dropdown).click().perform();
        LOGGER.LOG.info("Clicked on action menu drop down which contains " + summary + " Summary");
        waitForSecs(Duration.ofSeconds(5));
        this.driverCustom.click(CCMConstants.CCMCREATEPLANPAGE_CREATEWI_LEFTACTIONSUBBUTTONS_XPATH, menu);
        waitForSecs(Duration.ofSeconds(10));
        LOGGER.LOG.info("Clicked on " + menu + " Action menu drop down successfully");
      }
      catch (Exception ex) {
        throw new WebAutomationException(
            "Not clicked on " + menu + " action menu dropdown." + " or\n" + e.getMessage());
      }
    }
  }

  /**
   * <p>
   * After saving work item using {@link CCMEditWorkItemDialogInPlanPage#saveWorkItem()} . Work item created will be
   * displayed under plan item. <br>
   * <p>
   * Check whether work item created with the given name exists under plan item.
   *
   * @param workItemName name of the work item.
   * @return true if work item is displayed.
   */
  public boolean isWorkItemDisplayedUnderPlanItem(final String workItemName) {
    waitForSecs(Duration.ofSeconds(10));
    this.driverCustom.getPresenceOfWebElement("//div[@class='outliner']/a[text()='" + workItemName + "']");
    LOGGER.LOG.info("Verified Work Item '" + workItemName + "' Displayed Under Plan Item or not");
    return this.driverCustom.isElementVisible("//div[@class='outliner']/a[text()='" + workItemName + "']", Duration.ofSeconds(60));
  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * enter required plan name in search box using{@link CCMPlanPage #searchBox(String)}} <br>
   * Click Browse To plan link in Welcome plans
   *
   * @param withtype use to click on work item which sent by user. E.g : Task
   */
  public void addWorkItemInsidePlan(final String withtype) {
    waitForSecs(Duration.ofSeconds(25));
    try {
      this.driverCustom.getPresenceOfWebElement(
          "//div[@class='status-message' and contains(text(),'100%') and contains(@style,'display: none;')]");
    }
    catch (Exception e) {
      //do nothing if exception happen
    }
    try {
      this.driverCustom.getPresenceOfWebElement(
          CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDWIDGET_DROPDOWN_SELECTCCM_XPATH, "Add Work Item").click();
      LOGGER.LOG.info("Clicked on 'Add Work Item' button inside the plan.");
      waitForSecs(Duration.ofSeconds(18));

      Cell workitemtype = this.engine.findElement(Criteria.isCell().withText(withtype)).getFirstElement();
      waitForSecs(Duration.ofSeconds(12));
      workitemtype.getWebElement().click();
      waitForSecs(Duration.ofSeconds(10));
      LOGGER.LOG.info("Work item type " + withtype + " Selected successfully");
    }
    catch (Exception e) {
      try {
        WebElement optWorkItem =
            this.driverCustom.getWebElement("//tr[@role='menuitem']//td[text()='DYNAMIC_VAlUE']", withtype);
        optWorkItem.click();
        waitForSecs(Duration.ofSeconds(10));
        LOGGER.LOG.info("Work item type " + withtype + " Selected successfully");
      }
      catch (Exception ex) {
        throw new WebAutomationException(withtype +
            " : item not found in the 'Add Work Item' drop down,Please provide valid input.\n or\n 'Add Work Item' button not found inside the plan.\n " +
            "or\n " + ex.getMessage());
      }
    }
  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * enter required plan name in search box using{@link CCMPlanPage #searchBox(String)}} <br>
   * after opening plan using {@link CCMPlanPage #clickOnPlanPhase}
   * <p>
   * This method select plan view as from drop down.
   *
   * @param viewType use to select view type which sent by user.
   */
  public void viewAs(final String viewType) {
    waitForSecs(Duration.ofSeconds(5));
    try {
      this.driverCustom.isElementVisible("//select[@aria-label= 'View As:']", Duration.ofSeconds(30));
      this.driverCustom.select("//select[@aria-label= 'View As:']", viewType, SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
      LOGGER.LOG.info("selected " + viewType + " from plan view as drop down successfully.");
      waitForSecs(Duration.ofSeconds(10));// wait to load all plans in result section
    }
    catch (Exception e) {
      throw new WebAutomationException(viewType +
          ": value not found in the view as drop down,Please provide valid input.\n" + "or\n" + e.getMessage());

    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}<br>
   * after selecting 'All plans'submenu option from 'plans' menu using
   * {@link CCMPlanPage #clickOnSelectPlanType(String)}} <br>
   * <P>
   * This method used after opened any plan. enter text in to "Type to Filter" search box.
   *
   * @param filterItem name of the filter item.
   */
  public void filterItems(final String filterItem) {
    waitForSecs(Duration.ofSeconds(10));
    try {
      waitForSecs(Duration.ofSeconds(10));
      this.driverCustom.isElementVisible(CCMConstants.CCMPLANPAGE_FILTERTEXT_XPATH, Duration.ofSeconds(60));
      this.driverCustom.getWebElement(CCMConstants.CCMPLANPAGE_FILTERTEXT_XPATH).click();
      this.driverCustom.getWebElement(CCMConstants.CCMPLANPAGE_FILTERTEXT_XPATH).sendKeys(filterItem);
      LOGGER.LOG.info("Filter item '" + filterItem + "' Entered in search field successfully.");
      waitForSecs(Duration.ofSeconds(10));
    }
    catch (Exception e) {
      throw new WebAutomationException(
          "filter item '" + filterItem + "' not entered in search field.\n" + "or\n" + e.getMessage());
    }
  }
  
  /**
   * After select Create new work item in Plans details page (using {link {@link CCMPlanPage#clickOnActionInPlanPhase(String, String)}) <br>
   * This method used for input data in new row.
   * @author VDY1HC
   * @param additionalParams - contains keys:
   *                    ATTRIBUTE_NAME - name of attribute to be input (column header)
   *                    ATTRIBUTE_VALUE - value of attribute
   */
  public void inputDataForNewWorkItemRow(final Map<String,String> additionalParams) {
    String attributeName = additionalParams.get("ATTRIBUTE_NAME");
    String attributeValue = additionalParams.get("ATTRIBUTE_VALUE");
    if (attributeName.equalsIgnoreCase("Summary")) {
      attributeName = "summary";
    }
    WebElement inputPlace = this.driverCustom.getPresenceOfWebElement("//table[contains(@class,'item unsaved')]//td[contains(@class,'" + attributeName + "')]//div[@class='outliner']");
    this.driverCustom.clickUsingActions(inputPlace);
    WebElement txb = this.driverCustom.getChildElement(inputPlace, By.xpath(".//input"));
    txb.sendKeys(attributeValue + Keys.ENTER);
  }

  /**
   * This method wait for the presence of Plans link in plan page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.isElementPresent(CCMConstants.CCMPLANPAGE_PLANS_LINK_XPATH, timeInSecs);
    waitForSecs(Duration.ofSeconds(5));
  }
}
