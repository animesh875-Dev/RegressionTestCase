package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.container.TreeNode;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.element.text.TextField;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LabelFinder;
import com.bosch.psec.web.test.service.WebElementService;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.treeNode.JazzTreeNodeFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.JazzTextFinder;
import finder.text.label.JazzLabelFinder;
import finder.text.textField.JazzTextFieldFinder;


/**
 * @author hrt5kor
 */
public class CCMCreateQueryPage extends AbstractCCMPage {

  /**
   *
   */
  private static final String INVALID_PARAMETER_OR_NULL_OR_EMPTY = "Invalid parameter or null or empty.";
  private static final String AND = "AND";
  private static final String OR = "OR";
  private final String queryName = "NOTDEFINED";
  private static final String EDIT_DESCRIPTION = "Edit Description";


  /**
   * Declared variable for Web Element Service.
   */
  protected WebElementService webElementService;
  /**
   * Declared wait time for opening query
   */
  protected static final int WAITFOR_OPENING_QUERY = 5000;
  /**
   * Declared wait time for loading element
   */
  protected static final int LOADING_ELEMENT = 2000;

  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom required for interacting with the browser.
   */
  public CCMCreateQueryPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTextFinder(), new JazzDropdownFinder(), new JazzButtonFinder(),
        new JazzDialogFinder(), new JazzLabelFinder(), new JazzButtonFinder(), new JazzTextFieldFinder(),
        new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder(), new CheckboxFinder(),
        new LabelFinder(), new JazzLabelFinder(), new JazzTreeNodeFinder(), new DropdownFinder());

    this.webElementService = new WebElementService();
  }

  /**
   * Do not use this method if you don't know how to fill 'additionalParams' rather use one of the other provided open
   * methods.
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    this.driverCustom
        .openURL(getProjectAreaURL(repositoryURL, projectAreaName) + "#action=com.ibm.team.workitem.newQuery&tq=");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * this methiod set query name in Name: text field
   *
   * @param queryName to be entered in the text box.
   */
  public void setQueryName(final String queryName) {
    waitForPageLoaded();
    String queryNameLocator = "//input[@class='nameInput']";
    if ((queryName != null) && (!queryName.isEmpty())) {
      this.driverCustom.isElementInvisible("//div[text()='Opening Query...']", Duration.ofSeconds(30));
      ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
      this.driverCustom.isElementVisible(queryNameLocator, Duration.ofSeconds(15));
      Text txtQueryName = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Name:"));
      txtQueryName.clearText();
      txtQueryName.setText(queryName);
      return;
    }
    throw new IllegalArgumentException(INVALID_PARAMETER_OR_NULL_OR_EMPTY);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * This method select teh condition
   *
   * @param name Sets AND or OR condition in the query.
   */
  public void selectCondition(final String name) {
    waitForPageLoaded();
    if ((name != null) && (name.equals(AND) || name.equals(OR))) {
      this.driverCustom.click(CCMConstants.CCMCREATEQUERYPAGE_ADDCONDITION_LINK_XPATH, name);
      return;
    }
    throw new IllegalArgumentException("Invalid condition name.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   *
   * @param text value to be selected in list box.
   */
  public void filterText(final String text) {
    waitForPageLoaded();
    if ((text != null) && (!text.isEmpty())) {
      Text txtFilterText = this.engine
          .findFirstElementWithDuration(Criteria.isTextField().withPlaceHolder("Type Filter Text"), this.timeInSecs);
      txtFilterText.click();
      txtFilterText.setText(text);
      return;
    }
    throw new IllegalArgumentException(INVALID_PARAMETER_OR_NULL_OR_EMPTY);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   *
   * @param text value to be selected in list box.
   */
  public void selectItemInListBox(final String text) {
    waitForPageLoaded();
    if ((text != null) && (!text.isEmpty())) {
      if (this.driverCustom.isElementVisible(CCMConstants.CCMCREATECREATEQUERYPAGE_TWISTLE_BUTTON_XPATH,
          Duration.ofSeconds(10))) {
        this.driverCustom.click(CCMConstants.CCMCREATECREATEQUERYPAGE_TWISTLE_BUTTON_XPATH);
      }
      Label selectItemInListBox = this.engine.findElement(Criteria.isLabel().withText(text)).getFirstElement();
      selectItemInListBox.click();
      waitForSecs(2);
      return;
    }
    throw new IllegalArgumentException(INVALID_PARAMETER_OR_NULL_OR_EMPTY);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Click on add condition.
   */
  public void clickOnAddCondition() {
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
    waitForPageLoaded();
    Button btnAddCondition = this.engine.findElement(Criteria.isButton().withText("Add Condition")).getFirstElement();
    List<WebElement> clickOnAddCondition = btnAddCondition.getWebElement()
        .findElements(By.xpath(CCMConstants.CCMCREATEQUERYPAGE_ADDCONDITIONBUTTON_LINK_XPATH));
    for (WebElement ele : clickOnAddCondition) {
      if (this.driverCustom.isElementVisible(ele, Duration.ofSeconds(2))) {
        ele.click();
        break;
      }
    }
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Click on Particular selecting Type based on Text
   *
   * @param Type Query Text
   */
  public void clickOnSelectType(final String Type) {
    waitForPageLoaded();
    Label lblClickOnSelectType = this.engine.findElement(Criteria.isLabel().withText(Type)).getFirstElement();
    lblClickOnSelectType.scrollToElement();
    this.driverCustom.clickUsingActions(lblClickOnSelectType.getWebElement());
    List<WebElement> ele = this.driverCustom.getWebElements("//a[contains(@class,'entry')]");
    for (WebElement test : ele) {
      if (!test.getAttribute("aria-selected").equals("true") && test.getText().equalsIgnoreCase(Type)) {
        this.driverCustom.getFirstVisibleWebElement("//div[@class='content jazz-ui-PageTemplate-flexrow']", null)
            .click();
        this.driverCustom.clickUsingActions(lblClickOnSelectType.getWebElement());
      }
    }
  }

  /**
   * Click on option in Condtion box with mentioned title when creating query <br>
   * After select filter using {@link CCMCreateQueryPage#selectItemInListBox(String)} <br>
   *
   * @author VDY1HC
   * @param title - title of Condition box
   * @param text - select option
   */
  public void clickOnSelectTypeWithTitle(final String title, final String text) {
    waitForPageLoaded();
    WebElement selectItemInListBox = this.driverCustom.getWebElement("//span[@title='" + title +
        "']/ancestor::div[@class='top-section']/following-sibling::div[@class='middle-section']//a[@role='option' and @aria-label='" +
        text + "']");
    JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
    je.executeScript("arguments[0].scrollIntoView(true);", selectItemInListBox);
    selectItemInListBox.click();
    waitForSecs(Duration.ofSeconds(2));
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Click on Particular selecting Type based on Text
   *
   * @param Type Query Text
   * @param tabNumber -
   */
  public void selectOptionInDropDownBYIndex(final String Type, final String tabNumber) {
    waitForPageLoaded();
    Label lblClickOnSelectType =
        this.engine.findElement(Criteria.isLabel().withText(Type)).getElementByIndex(Integer.parseInt(tabNumber));
    lblClickOnSelectType.scrollToElement();
    lblClickOnSelectType.click();
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Click on add attribute condition.
   *
   * @param Type is tab name
   */
  public void selectAttribute(final String Type) {
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible(CCMConstants.CCMCREATEQUERYPAGE_SELECTATTRIBUTE_TEXTBOX_XPATH,
        Duration.ofSeconds(2))) {
      Dialog selectAttributeDialog =
          this.engine.findElement(Criteria.isDialog().withTitle(CCMConstants.SELECT_ATTRIBUTES)).getFirstElement();
      Text txtSelectAttribute = this.engine.findFirstElement(Criteria.isTextField().inContainer(selectAttributeDialog));
      txtSelectAttribute.setText(Type);
      Label lblAttributeType = this.engine.findElement(Criteria.isLabel().withText(Type)).getFirstElement();
      lblAttributeType.click();
    }
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Click on add attribute condition.
   *
   * @param Type is tab name
   */
  public void selectTab(final String Type) {
    waitForPageLoaded();
    waitForSecs(5);

    if (this.driverCustom.isElementClickable("//div[text()='DYNAMIC_VAlUE'][2]", Duration.ofSeconds(5), Type) ||
        this.driverCustom.isElementClickable(CCMConstants.CCMCREATEQUERYPAGE_SELECT_TAB_XPATH, Duration.ofSeconds(5),
            Type)) {
      Tab lnkSelectTab = this.engine.findElementWithinDuration(Criteria.isTab().withText(Type), Duration.ofSeconds(10))
          .getFirstElement();
      lnkSelectTab.click();
      LOGGER.LOG.info(Type + " selected successfully");
    }
    else {
      LOGGER.LOG.info(Type + " Not selected successfully");
    }
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Click on add attribute condition.
   *
   * @param Type is tab name
   */
  public void tab(final String Type) {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().navigate().refresh();
    if (this.driverCustom.isElementClickable(CCMConstants.CCMCREATEQUERYPAGE_SELECT_TAB_XPATH, Duration.ofSeconds(5),
        Type)) {
      Link lnkTab = this.engine.findElement(Criteria.isLink().withText(Type)).getFirstElement();
      lnkTab.click();
    }

  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Click on add Column...
   */
  public void clickOnAddColumn() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEQUERYPAGE_ADDCOLUMNBUTTON_LINK_XPATH);
    this.driverCustom.click(CCMConstants.CCMCREATEQUERYPAGE_ADDCOLUMNBUTTON_LINK_XPATH);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Click on add attribute condition.
   */
  public void clickOnAddAttributeCondition() {
    waitForPageLoaded();
    this.driverCustom.click(CCMConstants.CCMCREATEQUERYPAGE_ADDATTRIBUTECONDITION_BUTTON_XPATH);
    waitForSecs(Duration.ofSeconds(2));
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Currently supports to query which takes only one string value.
   *
   * @param text value to be passed in text control.
   */
  public void setValueInTextControl(final String text) {
    waitForPageLoaded();
    if ((text != null) && (!text.isEmpty())) {
      this.driverCustom.typeText(CCMConstants.CCMCREATEQUERYPAGE_QUERYVALUE_TEXTBOX_XPATH, text);
      return;
    }
    throw new IllegalArgumentException(INVALID_PARAMETER_OR_NULL_OR_EMPTY);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * runs the query, results will be displayed on screen.
   */

  public void runQuery() {
    waitForPageLoaded();
    this.driverCustom.click(CCMConstants.CCMCREATEQUERYPAGE_QUERY_BUTTON_XPATH, "Run");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * click on buttons
   *
   * @param buttonName is button Name
   */
  public void button(final String buttonName) {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(CCMConstants.CCMCREATEQUERYPAGE_QUERY_BUTTON_XPATH, Duration.ofSeconds(30),
        buttonName);
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEQUERYPAGE_QUERY_BUTTON_XPATH, buttonName);
    try {
      Button lblClickOnSelectType = this.engine.findElement(Criteria.isButton().withText(buttonName)).getFirstElement();
      lblClickOnSelectType.scrollToElement();
      // scroll is updated due to RQM fail
      this.driverCustom.isElementVisible(CCMConstants.CCMCREATEQUERYPAGE_QUERY_BUTTON_XPATH, Duration.ofSeconds(5),
          buttonName);
      ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("scroll(0,0)");
    }
    catch (Exception e) {
      // label is already in view
    }
    this.driverCustom.click(CCMConstants.CCMCREATEQUERYPAGE_QUERY_BUTTON_XPATH, buttonName);
    LOGGER.LOG.info(buttonName + " Button clicked succesfully");
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Press Enter button
   */
  public void enterOKButton() {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().switchTo().activeElement().sendKeys(Keys.ENTER);
  }


  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * A link present under the Work Items menu, which contains a list box with all work items.
   */
  public void clickOnCreateQuaryLink() {
    waitForPageLoaded();
    this.driverCustom.click(CCMConstants.CCMPROJECTAREAPAGE_CREATEQUARY_LINK_XPATH);

  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * A link present under the Work Items menu,Click on Shared Queries.
   */
  public void clickOnSharedQuaryLink() {
    waitForPageLoaded();
    this.driverCustom.click(CCMConstants.CCMPROJECTAREAPAGE_SHAREDQUARY_LINK_XPATH);

  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Click on Recently created link in shared queries.
   */
  public void clickOnRecentlyCreatedLink() {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().navigate().refresh();
    Link lnkRecentlyCreatedLink = this.engine.findFirstElement(Criteria.isLink().withText("Recently created"));
    lnkRecentlyCreatedLink.click();

  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select the particular WorkItem from the shared query.
   *
   * @param wiName is particular wi
   */
  public void clickOnSelectedWICheckbox(final String wiName) {
    waitForPageLoaded();
    Button btnClickOnSelectedWICheckbox =
        this.engine.findElement(Criteria.isButton().withToolTip("Edit Multiple Work Items")).getElementByIndex(1);
    btnClickOnSelectedWICheckbox.click();
    this.driverCustom.click(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_SELECTEDWI_CHECKBOX_XPATH, wiName);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * This method will gives the attributes
   *
   * @param wiName is Created WorkItem
   */
  public void clickOnActionInSharedQuery(final String wiName) {
    waitForPageLoaded();
    WebElement ele = this.driverCustom.getWebElement(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, wiName);
    new Actions(this.driverCustom.getWebDriver()).moveToElement(ele).build().perform();
    List<WebElement> lst = this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_ACTION_BUTTON_XPATH);
    for (WebElement el : lst) {
      if (this.driverCustom.isElementVisible(el, Duration.ofSeconds(3))) {
        el.click();
        break;
      }
    }

    this.driverCustom.click(CCMConstants.CCMCREATEQUERYPAGE_INLINE_REFRESH_XPATH);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * List box is shown after clicking on Select Work item type menu under DropDown.
   *
   * @param AttributeName is Attribute name
   * @param value Attribute value
   */
  public void setInLineAttributeTextBox(final String AttributeName, final String value) {
    waitForPageLoaded();
    Text txtAttributeTextBox = this.engine.findFirstElement(Criteria.isTextField().hasLabel(AttributeName));
    txtAttributeTextBox.clearText();
    txtAttributeTextBox.setText(value);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Gives the Attribute value in InLine Editor.
   *
   * @param wiName is particular work item.
   * @param attribute is name of attribute
   * @return the attribute value
   */
  public List<String> inLineEditorAttributeData(final String wiName, final String[] attribute) {
    waitForPageLoaded();
    List<String> list = new ArrayList<>();

    boolean str = false;
    String st = String.valueOf(str);
    for (String el : attribute) {

      if (this.driverCustom.isElementVisible(CCMConstants.CCMCREATEQUERYPAGE_INLINE_ATTRIBUTE_XPATH,
          Duration.ofSeconds(5), el)) {
        Text txtSummary = this.engine.findElement(Criteria.isTextField().hasLabel(el)).getFirstElement();
        String data = txtSummary.getText();
        list.add(el + ":" + data);
      }
      else if (el.equals("PM Interface Change Log")) {
        list.add(el + ":" + st);

      }
      else {
        list.add("Attribute is hidden");
      }

    }

    this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(5), wiName);
    ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("scroll(0,100)");
    this.driverCustom.click(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, wiName);
    this.driverCustom.click(CCMConstants.JAZZPAGE_LINKS_XPATH, "Planning");
    return list;
  }


  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * A link present under the Work Items menu, which contains a list box with all work items.
   *
   * @param wiName is name of work item
   * @param attributedata passing the data into attribute field
   * @param attributenames is name of attribute
   * @param username is role
   * @param Hours is hours
   */
  public void addDataToHiddenAttribute(final String wiName, final String attributedata, final String[] attributenames,
      final String username, final String Hours) {


    this.driverCustom.click(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_MINIDASHBOARD_LINK_XPATH);
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMMINIDASHBOARD_FRAME_IFRAME_XPATH);
    this.driverCustom.switchToFrame(CCMConstants.CCMMINIDASHBOARD_FRAME_IFRAME_XPATH);
    this.driverCustom.isElementVisible(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_SELECTEDWI_BUTTON_XPATH,
        Duration.ofSeconds(5));
    Button btnDeleteWorkItem =
        this.engine.findElement(Criteria.isButton().withToolTip("Get Selected Work Item(s)")).getFirstElement();
    btnDeleteWorkItem.click();
    for (String ele1 : attributenames) {
      this.driverCustom.click(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_CHOOSEATTRIBUTE_DROPDOWN_XPATH);
      this.driverCustom.click(CCMConstants.JAZZPAGE_LINKS_XPATH, ele1);
      getValidateHiddenData(wiName, attributedata, attributenames, username, Hours, ele1);
    }
    if (username.equals("Developer") || username.equals("Team Member")) {
      WebElement e = this.driverCustom
          .getWebElement(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_DELIVERYATTRIBUTECHANGES_BUTTON_XPATH);
      new Actions(this.driverCustom.getWebDriver()).moveToElement(e).click().build().perform();
    }
    else {
      WebElement we =
          this.driverCustom.getWebElement(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ATTRIBUTECHANGES_BUTTON_XPATH);
      new Actions(this.driverCustom.getWebDriver()).moveToElement(we).click().build().perform();
    }
    this.driverCustom.switchToParentFrame();

  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * A link present under the Work Items menu, which contains a list box with all work items.
   *
   * @param wiName is name of work item
   * @param attributedata passing the data into attribute field
   * @param attributenames is name of attribute
   * @param username is role
   * @param Hours is hours
   * @param ele1 is the elements of work item.
   */
  public void getValidateHiddenData(final String wiName, final String attributedata, final String[] attributenames,
      final String username, final String Hours, final String ele1) {

    if (ele1.equals("PM Interface Element ID") || ele1.equals("Execution Progress") ||
        ele1.equals("PM Interface Change Log") || ele1.equals("PM Interface Project Status") ||
        ele1.equals("PM Interface Resource Group Assignment")) {

      int i = 0;
      while (i <= 10) {
        if (this.driverCustom.isElementVisible(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDTEXT_BOX_XPATH,
            Duration.ofSeconds(3))) {
          this.driverCustom.getWebElement(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDTEXT_BOX_XPATH)
              .sendKeys(attributedata);

          break;
        }
        this.driverCustom.click(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_CHOOSEATTRIBUTE_DROPDOWN_XPATH);
        this.driverCustom.click(CCMConstants.JAZZPAGE_LINKS_XPATH, ele1);
        i++;
      }
    }
    if (ele1.equals("Residual Estimate (hrs)") || ele1.equals("Deviation (hrs)") ||
        ele1.equals("Estimation Sum (hrs)")) {
      int i = 0;
      while (i <= 10) {
        if (this.driverCustom.isElementVisible(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDTEXTHOURS_BOX_XPATH,
            Duration.ofSeconds(3))) {
          this.driverCustom.getWebElement(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_ADDTEXTHOURS_BOX_XPATH)
              .sendKeys(Hours);
          break;
        }
        this.driverCustom.click(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_CHOOSEATTRIBUTE_DROPDOWN_XPATH);
        this.driverCustom.click(CCMConstants.JAZZPAGE_LINKS_XPATH, ele1);
        i++;
      }
    }
    this.driverCustom
        .getPresenceOfWebElement(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_UPDATEATTRIBUTECHANGES_BUTTON_XPATH);
    this.driverCustom.click(CCMConstants.CCMPROJECTAREADASHBOARDPAGE_UPDATEATTRIBUTECHANGES_BUTTON_XPATH);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * give the header Count
   *
   * @param ColumnName is name of header Column Name
   * @return is returns the column Number
   */
  public int getColumnIndex(final String ColumnName) {
    waitForPageLoaded();
    List<WebElement> lst;
    int count = 0;
    lst = this.driverCustom.getWebElements(CCMConstants.CCMCREATEQUERYPAGE_TABLEHEADERS_XPATH);
    for (WebElement ele : lst) {
      if (ele.getText().equals(ColumnName)) {
        count++;
        break;
      }
      count++;
    }
    return count;

  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * gives the particular column text
   *
   * @param count gives the Column number
   * @return Returning the particular column text
   */
  public String getColumnValue(final int count) {
    waitForPageLoaded();

    String n = String.valueOf(count);
    return this.driverCustom.getWebElement(CCMConstants.CCMCREATEQUERYPAGE_SELECTATTRIBUTE_COLUMN_XPATH, n).getText();
  }


  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Save the query.
   */
  public void saveQuery() {
    waitForPageLoaded();
    if (!this.driverCustom.isEnabled(CCMConstants.CCMCREATEQUERYPAGE_QUERY_BUTTON_XPATH, "Save")) {
      throw new WebAutomationException("Save button disabled cannot save the work item.");
    }

    new Actions(this.driverCustom.getWebDriver())
        .moveToElement(this.driverCustom.getWebElement(CCMConstants.CCMCREATEQUERYPAGE_QUERY_BUTTON_XPATH, "Save"))
        .click().build().perform();

  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   *
   * @return true if work item query saved.
   */
  public boolean isWorkItemQuerySaved() {
    waitForPageLoaded();
    return (!this.driverCustom.isEnabled(CCMConstants.CCMCREATEQUERYPAGE_QUERY_BUTTON_XPATH, "Save")) &&
        (this.driverCustom.isEnabled(CCMConstants.CCMCREATEQUERYPAGE_QUERY_BUTTON_XPATH, "Save Copy")) &&
        this.driverCustom.isTitleContains(this.queryName, Duration.ofSeconds(15));
  }


  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * View and run the personal queries
   *
   * @param personalQueryName name of the query.
   */

  public void runPersonalQuery(final String personalQueryName) {
    waitForPageLoaded();
    // Click on My Queries menu
    Dropdown drpWorkItem = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Work Items"));
    drpWorkItem.selectOptionWithText("My Queries");
    // Search the Shared Query
    Text txtSearchVerify = this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder(CCMConstants.SEARCH));
    txtSearchVerify.setText(personalQueryName);
    // Click on shared query
    Link lnkShareQuery = this.engine.findFirstElement(Criteria.isLink().withText(personalQueryName));
    lnkShareQuery.click();
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to create query page, after adding Id condition using
   * {@link CCMCreateQueryPage#selectItemInListBox(String)} id value use to set.
   *
   * @param idValue is work item Id.
   */
  public void setId(final String idValue) {
    waitForSecs(Duration.ofSeconds(10));
    Dialog dialog = this.engine.findElement(Criteria.isDialog().withTitle("Id")).getFirstElement();
    TextField attributeCond = this.engine.findFirstElement(Criteria.isTextField().inContainer(dialog));
    attributeCond.setText(idValue);

    if (!this.driverCustom.getWebElement("//div[@class='text-control-container']/input")
        .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equals(idValue)) {
      throw new WebAutomationException("Entered id " + idValue + " Is not matched with text box id.");
    }
    LOGGER.LOG.info(idValue + " Id value entered in query Id field.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * After navigating to create query page using {@link CCMProjectAreaDashboardPage#openSubMenu(String)}, clicks on add
   * condition button.
   */
  public void clickAddCondition() {
    waitForPageLoaded();
    Button btnAddCondition = this.engine.findElement(Criteria.isButton().withText("Add Condition")).getFirstElement();
    btnAddCondition.click();
    LOGGER.LOG.info("Clicked on Add Condition button.");
  }


  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * After navigating to create query page using {@link CCMProjectAreaDashboardPage#openSubMenu(String)} search for
   * query.
   *
   * @param queryname name of the query.
   * @return true if query is available.
   */
  public boolean searchQueryName(final String queryname) {
    waitForSecs(Duration.ofSeconds(10));
    this.driverCustom.getWebElement(CCMConstants.CCMCREATEQUERYPAGE_SEARCHBOX_XPATH).clear();
    this.driverCustom.getPresenceOfWebElement("//span[@class='filter']//div[@class='jazz-ui-FilterBox']");
    if (this.driverCustom.isElementVisible(CCMConstants.CCMCREATEQUERYPAGE_SELECTGROUP_DROPDOWN_XPATH,
        this.timeInSecs)) {
      WebElement ele = this.driverCustom.getWebElement(CCMConstants.CCMCREATEQUERYPAGE_SELECTGROUP_DROPDOWN_XPATH);
      ele.click();
      Select sel = new Select(ele);
      sel.selectByVisibleText("Hide groups");
    }
    this.driverCustom.isElementClickable(CCMConstants.CCMCREATEPLANPAGE_SEARCHPLANS_TEXTBOX_XPATH, this.timeInSecs);
    Text textSearch = this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder("Search..."));
    textSearch.clearText();
    textSearch.setText(queryname);
    this.driverCustom.getPresenceOfWebElement("//span[@class='filter']//div[@class='input-wrapper']/input");
    if (!this.driverCustom.getWebElement("//span[@class='filter']//div[@class='input-wrapper']/input")
        .getAttribute(CCMConstants.VALUE).equals(queryname)) {
      throw new WebAutomationException(queryname + " Entered Query name is not matched with Searched query.\n");
    }
    LOGGER.LOG.info(queryname + " query name is provided in Search query field to search the query.");
    return this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_LINK_TYPE_XPATH, this.timeInSecs,
        queryname);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * After searching a query name using {@link CCMCreateQueryPage#searchQueryName(String)} click on searched query.
   *
   * @param queryname name of the query.
   */
  public void clickOnQuery(final String queryname) {
    waitForPageLoaded();
    try {
      Link clickonquery = this.engine.findFirstElement(Criteria.isLink().withText(queryname));
      clickonquery.click();
    }
    catch (Exception e) {
      throw new WebAutomationException(queryname + " Query name not found.\n" + " Or\n " + e.getMessage());
    }
    this.driverCustom.isElementVisible(CCMConstants.CCMCREATEQUERYPAGE_QUERY_TITLE_XPATH, Duration.ofSeconds(50),
        queryname);
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
    LOGGER.LOG.info("Clicked on Query " + queryname);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to result query page and store all the result columns name and respective result values in a map. <br>
   * As per provided column name it will return the result column values.
   *
   * @param columnName name of the column.
   * @return string of result values of the column.
   */
  public String getListOfContentsForEachColumn(final String columnName) {
    Optional<WebElement> queryResultsTableMatched = this.driverCustom
        .getWebElements("//table[@summary='Query Results'] |//div[@summary='Query Results']").stream().findFirst();
    WebElement queryResultsTable = queryResultsTableMatched.isPresent() ? queryResultsTableMatched.get() : null;
    if (queryResultsTable == null) {
      throw new WebAutomationException("query results table is empty.");
    }
    List<WebElement> headers = queryResultsTable.findElements(By.xpath(".//th"));
    List<String> headersList = new ArrayList<>();
    headers.stream().forEach(x -> headersList.add(x.getText()));
    LOGGER.LOG.info("Header column names are added in List.");
    List<WebElement> rows = queryResultsTable.findElements(
        By.xpath("//tr[@class='com-ibm-team-rtc-foundation-web-ui-gadgets-table-TableRow' or @class= 'visibleRow']"));
    Map<Integer, List<String>> map = new LinkedHashMap<>();
    for (int i = 0; i < rows.size(); i++) {
      List<WebElement> lts = rows.get(i).findElements(By.xpath("./td"));
      if (headersList.size() != lts.size()) {
        throw new WebAutomationException(
            "Coloums size" + headersList.size() + " rows size " + lts.size() + " are not matching.");
      }
      List<String> rowContent = new ArrayList<>();
      lts.stream().forEach(x -> rowContent.add(x.getText()));
      map.put(i, rowContent);
    }
    LOGGER.LOG.info("Query result column content added to the list.");
    ArrayList<String> columnresults = new ArrayList<>();
    if (headersList.stream().anyMatch(x -> x.equals(columnName))) {
      int k = headersList.indexOf(columnName);
      for (int j = 0; j < map.size(); j++) {
        columnresults.add(map.get(j).get(k));
      }
      return String.join("^", columnresults);
    }
    throw new WebAutomationException(columnName + " invalid coloumn name.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to My Queries or shared Queries using {@link CCMProjectAreaDashboardPage#openSubMenu(String)} delete the
   * query as per the query name.
   *
   * @param queryname is the name of query.
   */
  public void deleteQuery(final String queryname) {
    refresh();
    waitForPageLoaded();
    try {
      this.driverCustom.isElementVisible("//span[@class='filter']//div[@class='jazz-ui-FilterBox']", this.timeInSecs);
      Text textSearch = this.engine.findFirstElementWithDuration(
          Criteria.isTextField().withPlaceHolder(CCMConstants.SEARCH), Duration.ofSeconds(10));
      textSearch.clearText();
      textSearch.setText(queryname);
      Row row = this.engine.findFirstElementWithDuration(Criteria.isRow().withText(queryname), Duration.ofSeconds(20));
      row.hoverOnElement();
      LOGGER.LOG.info("Click on row contains Query " + queryname);
      Dropdown dp =
          this.engine.findFirstElementWithDuration(Criteria.isDropdown().inContainer(row), Duration.ofSeconds(10));
      dp.selectOptionWithText("Delete Query");
    }
    catch (Exception e1) {
      LOGGER.LOG.warn("Not Found Delete Query dropdown and throw exception " + e1.getMessage());
      this.driverCustom.getWebElement(CCMConstants.JAZZPAGE_PARENT_LINKS_XPATH, queryname).click();
      LOGGER.LOG.info("Click on row contains Query " + queryname);
      Link lnkDelete =
          this.engine.findFirstElementWithDuration(Criteria.isLink().withToolTip("Delete"), Duration.ofSeconds(10));
      lnkDelete.click();
    }
    LOGGER.LOG.info("Clicked on Delete button of Query " + queryname);
    Dialog dlgDeteleQuery;
    try {
      dlgDeteleQuery = this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Delete the query?"),
          Duration.ofSeconds(10));
    }
    catch (Exception e) {
      dlgDeteleQuery = this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Delete Query?"),
          Duration.ofSeconds(10));
    }
    try {
      LOGGER.LOG.info("Delete the query? dialog found successfully");
      Button btn = this.engine.findFirstElementWithDuration(
          Criteria.isButton().withText("Delete").inContainer(dlgDeteleQuery), Duration.ofSeconds(10));
      btn.click();
      LOGGER.LOG.info("Clicked Delete Query 'Delete' button.");
    }
    catch (Exception e) {
      LOGGER.LOG.warn("Not Found Delete button on Delete Query? dialog and throw exception " + e.getMessage());
      Button btnOK =
          this.engine.findFirstElementWithDuration(Criteria.isButton().withText("OK"), Duration.ofSeconds(10));
      btnOK.click();
      LOGGER.LOG.info("Clicked OK button on Delete Query? dialog.");
    }
    waitForSecs(Duration.ofSeconds(5));
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * After adding a query condition using {@link CCMCreateQueryPage#selectItemInListBox(String)} by condition title the
   * condition type will be selected.
   *
   * @param conditionTitle title of the condition.
   * @param selectOption "is" , "is not" etc.
   */
  public void selectConditionType(final String conditionTitle, final String selectOption) {
    String cndTitle = conditionTitle;
    if (conditionTitle.equalsIgnoreCase("OR")) {
      cndTitle = "AND";
    }
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMPAGE_CONDITION_TYPE_XPATH, cndTitle).click();
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEPLANPAGE_CREATEWI_SUBBUTTONS_XPATH, selectOption);
    if (!this.driverCustom.isElementVisible(CCMConstants.CCMCREATEPLANPAGE_CREATEWI_SUBBUTTONS_XPATH,
        Duration.ofSeconds(10), selectOption)) {
      throw new WebAutomationException(selectOption + " is not visible");
    }
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEPLANPAGE_CREATEWI_SUBBUTTONS_XPATH, selectOption)
        .click();
    LOGGER.LOG.info("Clicked on drop down option " + selectOption + " from " + cndTitle + " widget. ");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to create query page, after adding condition using {@link CCMCreateQueryPage#selectItemInListBox(String)}
   * provides value to text box.
   *
   * @param AttributeName is attribute title.
   * @param value attribute value.
   */
  public void setAttributeTextBox(final String AttributeName, final String value) {
    waitForPageLoaded();
    waitForSecs(Duration.ofSeconds(10));
    try {
      Dialog dialg = this.engine
          .findElementWithinDuration(Criteria.isDialog().withTitle(AttributeName), this.timeInSecs).getFirstElement();
      TextField attriCond = this.engine
          .findElementWithDuration(Criteria.isTextField().inContainer(dialg), this.timeInSecs).getFirstElement();
      attriCond.setText(value);
      LOGGER.LOG.info("Attribute '" + AttributeName + "' set value '" + value + "' set in text field successfully.");
    }
    catch (Exception e) {
      throw new WebAutomationException(
          AttributeName + "Attibute is not visible or locator not found.\n" + " Or \n " + e.getMessage());
    }
    LOGGER.LOG.info(value + " Value provided in " + AttributeName + " widget.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate into a query using {@link CCMCreateQueryPage#clickOnQuery(String)} clicks on edit current query button.
   */
  public void clickOnEditCurrentQuery() {
    this.driverCustom.getPresenceOfWebElement("//a[@title='Edit Current Query']");
    try {
      Button btn = this.engine.findElement(Criteria.isButton().withToolTip("Edit Current Query")).getFirstElement();
      btn.click();
    }
    catch (Exception e) {
      throw new WebAutomationException(
          "Edit Current Query button is not visible or locator not found.\n" + " Or\n" + e.getMessage());
    }
    LOGGER.LOG.info("Clicked on Edit Current Query Button.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to create query page, after adding condition using {@link CCMCreateQueryPage#selectItemInListBox(String)}
   * clicks on enable input of condition values when query is run.
   */
  public void enableInputConditionValuesWhenQueryIsRun() {
    try {
      Button lnk =
          this.engine.findElement(Criteria.isButton().withToolTip("Enable Input of Condition Values when Query is Run"))
              .getFirstElement();
      lnk.click();
    }
    catch (Exception e) {
      throw new WebAutomationException(
          "Enable Input of Condition Values when Query is Run button is not visible or locator not found.\n" + " Or\n" +
              e.getMessage());
    }

    LOGGER.LOG.info("Clicked on Enable Input of Condition Values when Query is Run Button.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * This method takes Query name as input and returns the Query name with "...".
   *
   * @param query name the user need to send.
   * @return the query name with "...";
   */
  public String getEnableInputQueryName(final String query) {
    LOGGER.LOG.info("Provided query name " + query + " Returned query name as " + query + "...");
    return query + "...";
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * After providing a query name using {@link CCMCreateQueryPage#setQueryName(String)} query name use to return.
   *
   * @return return query name.
   */
  public String getQueryname() {
    LOGGER.LOG.info("Returns query name from the Query name text box.");
    return this.driverCustom.getWebElement(CCMConstants.CCMCREATEREPORTFROMRESOURCEPAGE_REPORTNAME_TEXTBOX_XPATH)
        .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * After navigating to My queries page using {@link CCMProjectAreaDashboardPage#openSubMenu(String)} clicking on
   * "Edit" button to edit the query.
   *
   * @param queryname is the name of the query.
   */
  public void editQuery(final String queryname) {
    this.driverCustom.getPresenceOfWebElement(CCMConstants.JAZZPAGE_LINKS_XPATH, queryname);
    if (!this.driverCustom.isElementVisible(CCMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(10), queryname)) {
      throw new WebAutomationException(queryname + " not found");
    }
    try {
      Row test = this.engine.findElement(Criteria.isRow().withText(queryname)).getFirstElement();
      Dropdown dp = this.engine.findElement(Criteria.isDropdown().inContainer(test)).getFirstElement();
      dp.selectOptionWithText("Edit Query");
    }
    catch (Exception e) {
      this.driverCustom.isElementClickable(CCMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(10), queryname);
      this.driverCustom.isElementVisible(CCMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(20), queryname);

      new Actions(this.driverCustom.getWebDriver())
          .moveToElement(this.driverCustom.getWebElement(CCMConstants.JAZZPAGE_LINKS_XPATH, queryname)).build()
          .perform();
      LOGGER.LOG.info("Mouse hover on Query " + queryname);
      this.driverCustom.click(CCMConstants.CCMCREATEPLANPAGE_PLANS_ATTRIBUTE_XPATH, "Edit");
    }
    LOGGER.LOG.info("Click on Edit button of Query" + queryname);
    waitForSecs(Duration.ofSeconds(5));
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * After navigating to details tab using {@link #selectTab(String)} in Create query page and adds a description under
   * description section.
   *
   * @param description is the query description.
   */
  public void editDescription(final String description) {
    waitForPageLoaded();
    Link lnkeditDescription = this.engine.findFirstElement(Criteria.isLink().withText(EDIT_DESCRIPTION));
    lnkeditDescription.click();
    LOGGER.LOG.info("Clicked on Edit Description button.");
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATECREATEQUERYPAGE_DESCRIPTION_TEXTBOX_XPATH);
    if (!this.driverCustom.isElementVisible(CCMConstants.CCMCREATECREATEQUERYPAGE_DESCRIPTION_TEXTBOX_XPATH,
        Duration.ofSeconds(10))) {
      throw new WebAutomationException("Edit Description text box not found");
    }
    this.driverCustom.getWebElement(CCMConstants.CCMCREATECREATEQUERYPAGE_DESCRIPTION_TEXTBOX_XPATH)
        .sendKeys(description);
    if (!this.driverCustom.getWebElement("//div[@class='sectionContent']/textarea")
        .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equals(description)) {
      throw new WebAutomationException(description + " Description not matched.");
    }
    LOGGER.LOG.info("Provided query description as " + description);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * After navigating to details tab using {@link #selectTab(String)} in Create query page and adds a column under
   * result columns section.
   *
   * @param userId Id is the id of a user.
   * @param userValue name of the user.
   */
  public void addUser(final String userId, final String userValue) {
    waitForPageLoaded();
    Link lnkAddUser =
        this.engine.findFirstElementWithDuration(Criteria.isLink().withText("Add User..."), this.timeInSecs);
    lnkAddUser.click();
    LOGGER.LOG.info("Clicked on Add User button.");
    try {
      Dialog dlgSelectUser =
          this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Select Users"), this.timeInSecs);
      Text txtSearchApprover = this.engine.findFirstElementWithDuration(
          Criteria.isTextField().hasLabel("Enter a name filter to load the list.").inContainer(dlgSelectUser),
          this.timeInSecs);
      txtSearchApprover.setText(userId);
      LOGGER.LOG.info("User id given as " + userId);
      if (this.driverCustom.isElementVisible("//select[@dojoattachpoint='userSelector']/option", this.timeInSecs)) {
        this.driverCustom.getWebElement("//select[@dojoattachpoint='userSelector']/option").click();
        LOGGER.LOG.info(userValue.trim() + " selected user from Matching users");
        waitForSecs(Duration.ofSeconds(5));
        Button btnAddandClose = this.engine.findFirstElementWithDuration(
            Criteria.isButton().withText("Add and Close").inContainer(dlgSelectUser), this.timeInSecs);
        btnAddandClose.click();
        LOGGER.LOG.info("Clicked on Add and Close button.");
        LOGGER.LOG.info(userId + " : Approver added successfully.");
        waitForSecs(Duration.ofSeconds(3));
      }
      else {
        Button btnAddandClose = this.engine.findFirstElementWithDuration(
            Criteria.isButton().withText("Close").inContainer(dlgSelectUser), this.timeInSecs);
        btnAddandClose.click();
        LOGGER.LOG.info("Clicked on Close button.");

      }
      waitForSecs(Duration.ofSeconds(5));

    }
    catch (Exception e) {
      this.driverCustom.getPresenceOfWebElement("//div[@class='SectionsPanel-spp']//input[@placeholder='Search']")
          .click();
      waitForSecs(3);
      this.driverCustom.getPresenceOfWebElement("//div[@class='SectionsPanel-spp']//input[@placeholder='Search']")
          .sendKeys(userId);
      waitForSecs(3);
      try {
        String disabled = this.driverCustom.getAttribute("//div[@data-testid=' disabled_DYC9SI']", "data-testid");
        if (disabled.contains("disabled")) {
          LOGGER.LOG.info(userId + " : Approver can't be selected (Approver is disabled)");
        }
      }
      catch (Exception e1) {
        // Not disable
      }
      this.driverCustom.getPresenceOfWebElement("//div[@aria-label='Add']").click();
      waitForSecs(3);
      Button btnAddandCloseinSelectUser =
          this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
      btnAddandCloseinSelectUser.click();
      waitForSecs(3);
    }
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * After navigating to column display tab using {@link #selectTab(String)} in Create query page and adds a column
   * under result columns section.
   *
   * @param columnName name of column.
   */
  public void addColumn(final String columnName) {
    waitForSecs(Duration.ofSeconds(10));
    Link lnkAddColumn = this.engine.findFirstElement(Criteria.isLink().withText("Add Column..."));
    lnkAddColumn.click();
    LOGGER.LOG.info("Clicked on Add Column... button.");
    Dialog selectAttributes =
        this.engine.findElement(Criteria.isDialog().withTitle("Select Attributes")).getFirstElement();
    this.driverCustom.getWebElement(CCMConstants.CCMCREATECREATEQUERYPAGE_SHOWLINKS_CHECKBOX_XPATH, "Show links")
        .click();
    LOGGER.LOG.info("Clicked on Show links checkbox.");
    TextField attriCond = this.engine.findFirstElement(Criteria.isTextField().inContainer(selectAttributes));
    attriCond.setText(columnName);
    LOGGER.LOG.info(columnName + "Column name searched from Select Attributes widget.");
    Label lblColumnAttribute =
        this.engine.findElementWithinDuration(Criteria.isLabel().containText(columnName).inContainer(selectAttributes),
            Duration.ofSeconds(10)).getFirstElement();
    lblColumnAttribute.click();
    LOGGER.LOG.info("Clicked on column name " + columnName);
    Button btnOK =
        this.engine.findElement(Criteria.isButton().withText("OK").inContainer(selectAttributes)).getFirstElement();
    btnOK.click();
    List<WebElement> users = this.driverCustom.getWebElements("//table[@class='actionTable']//td");
    ArrayList<String> columnnames = new ArrayList<>();
    for (WebElement name : users) {
      columnnames.add(name.getText());
    }
    if (!columnnames.contains(columnName)) {
      throw new WebAutomationException(columnName + " Column name not matched with added column. ");
    }
    LOGGER.LOG.info("Clicked on Add Column OK button.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigates to column display tab in Create query page and adds a column under sort order section
   *
   * @param columnName name of column.
   */
  public void addSortColumn(final String columnName) {
    Link lnkAddSortColumn = this.engine.findFirstElement(Criteria.isLink().withText("Add Sort Column..."));
    lnkAddSortColumn.click();
    LOGGER.LOG.info("Clicked on Add Sort Column... button.");
    Dialog dlgSelectAttributes =
        this.engine.findElement(Criteria.isDialog().withTitle("Select Attributes")).getFirstElement();
    TextField attriCond = this.engine.findFirstElement(Criteria.isTextField().inContainer(dlgSelectAttributes));
    attriCond.setText(columnName);
    LOGGER.LOG.info(columnName + "Column name searched from Select Attributes widget.");
    Label lblSortAttribute = this.engine
        .findElementWithinDuration(Criteria.isLabel().containText(columnName).inContainer(dlgSelectAttributes),
            Duration.ofSeconds(10))
        .getFirstElement();
    lblSortAttribute.click();
    LOGGER.LOG.info("Clicked on column name " + columnName);
    Button btnOK2 =
        this.engine.findElement(Criteria.isButton().withText("OK").inContainer(dlgSelectAttributes)).getFirstElement();
    btnOK2.click();
    List<WebElement> user =
        this.driverCustom.getWebElements("//div[@title='Sort Order']//div[@class='sectionContent']//td");
    ArrayList<String> columnnames = new ArrayList<>();
    for (WebElement name : user) {
      columnnames.add(name.getText());
    }
    if (!columnnames.contains(columnName)) {
      throw new WebAutomationException(columnName + " Column name not matched with added column.");
    }
    LOGGER.LOG.info("Clicked on OK button.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigates to details tab in Create query page and expands the project area and adds a team area under Team and
   * Project Area Sharing section.
   *
   * @param ProjectArea name of Project Area.
   * @param TeamArea name of Team Area.
   */
  public void selectTeamAreaOrProjectArea(final String ProjectArea, final String TeamArea) {
    waitForPageLoaded();
    Link lnkAddTeamOrProjectArea = this.engine
        .findFirstElementWithDuration(Criteria.isLink().withText("Add Team or Project Area..."), this.timeInSecs);
    lnkAddTeamOrProjectArea.click();
    LOGGER.LOG.info("Clicked on Add Team or Project Area... button.");
    Dialog dlgSelectTeamAreaOrProjectArea = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select Team or Project Area"), this.timeInSecs)
        .getFirstElement();
    TreeNode rootNodeProjectArea = this.engine
        .findElementWithDuration(
            Criteria.isTreeNode().containText(ProjectArea).inContainer(dlgSelectTeamAreaOrProjectArea), this.timeInSecs)
        .getFirstElement();
    waitForSecs(Duration.ofSeconds(10));
    rootNodeProjectArea.expand();
    waitForSecs(Duration.ofSeconds(5));
    LOGGER.LOG.info("Clicked on Expand button from Project area " + ProjectArea);

    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(30));
    TreeNode treeNodeTeamArea = this.engine
        .findElementWithDuration(
            Criteria.isTreeNode().containText(TeamArea).inContainer(dlgSelectTeamAreaOrProjectArea), this.timeInSecs)
        .getFirstElement();
    treeNodeTeamArea.scrollToElement();
    treeNodeTeamArea.click();
    LOGGER.LOG.info("Clicked on team area " + TeamArea);

    waitForSecs(5);
    Button btnOK = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgSelectTeamAreaOrProjectArea),
            this.timeInSecs)
        .getFirstElement();
    btnOK.click();
    List<WebElement> teamarea = this.driverCustom
        .getWebElements("//div[@title='Team and Project Area Sharing']//div[@class='sectionContent']//td");
    ArrayList<String> teamareanames = new ArrayList<>();
    for (WebElement name : teamarea) {
      teamareanames.add(name.getText());
    }
    if (!teamareanames.contains(TeamArea)) {
      throw new WebAutomationException(TeamArea + " Column name not matched with added column.");
    }
    LOGGER.LOG.info("Clicked on OK button.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigates to details tab in Create query page and removes the description under description section.
   */
  public void deleteDescription() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_LINK_TYPE_XPATH,
        CCMConstants.EDIT_DESCRIPTION);
    Link lnkeditDescription = this.engine.findFirstElement(Criteria.isLink().withText(CCMConstants.EDIT_DESCRIPTION));
    lnkeditDescription.click();
    LOGGER.LOG.info("Clicked on Edit Description button.");
    waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.getWebElement(CCMConstants.CCMCREATECREATEQUERYPAGE_DESCRIPTION_TEXTBOX_XPATH).clear();
    if (!this.driverCustom.getWebElement("//div[@class='sectionContent']/textarea")
        .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equals("")) {
      throw new WebAutomationException(" Description field is not Cleared.");
    }
    LOGGER.LOG.info("Query description is Removed.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigates to details tab in Create query page and deletes the team or project area under team and project sharing
   * section.
   *
   * @param teamArea is the name of Team or Project area.
   */
  public void removeTeamArea(final String teamArea) {
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_XPATH, teamArea);
    new Actions(this.driverCustom.getWebDriver())
        .moveToElement(this.driverCustom.getWebElement(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_XPATH, teamArea)).build()
        .perform();
    LOGGER.LOG.info("Mouse hover on team area " + teamArea);
    this.driverCustom.click(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, "Remove");
    if (this.driverCustom.isElementVisible(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_XPATH, Duration.ofSeconds(10),
        teamArea)) {
      throw new WebAutomationException(teamArea + " Team area is not Removed.");
    }
    LOGGER.LOG.info("Clicked on" + teamArea + "Remove button.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigates to details tab in Create query page and deletes the user under user sharing section.
   *
   * @param user is user name.
   */
  public void removeUser(final String user) {
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_XPATH, user);
    new Actions(this.driverCustom.getWebDriver())
        .moveToElement(this.driverCustom.getWebElement(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_XPATH, user)).build()
        .perform();
    LOGGER.LOG.info("Mouse hover on user name " + user);
    this.driverCustom.click(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, "Remove");
    if (this.driverCustom.isElementVisible(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_XPATH, Duration.ofSeconds(10),
        user)) {
      throw new WebAutomationException(user + " user is not Removed.");
    }
    LOGGER.LOG.info("Clicked on" + user + "Remove button.");
    waitForSecs(Duration.ofSeconds(5));
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigates to details tab in Create query page and collects the section heading and section values.<br>
   * under the section heading the values are returned.
   *
   * @param heading is section title.
   * @return section values as per the section heading.
   */
  public String getDetails(final String heading) {
    Function<WebElement, String> predKey = e -> e.findElement(By.xpath(".//div[@class='sectionTitle']")).getText();
    Function<WebElement, String> predVal = e -> e.findElement(By.xpath(".//div[@class='sectionContent']")).getText();
    Map<String, String> mapOfSecTitleAndContent =
        this.driverCustom.getWebElements("//div[@class = 'tabContents']/div[@dojoattachpoint='_tabDetailsContent']/div")
            .stream().map(x -> x).collect(Collectors.toMap(predKey, predVal));
    LOGGER.LOG.info("Returns the query " + heading + " related data");
    if (mapOfSecTitleAndContent.get(heading) != null) {
      return mapOfSecTitleAndContent.get(heading);
    }
    throw new WebAutomationException(heading + " is invalid title or doesn't exist in the section title.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to column display tab in query results page, <br>
   * sort the order of the column using "Up" and "Down" arrow button under column section.
   *
   * @param sectiontitle is sub-section under column display tab e.g.-Result Columns,Sort Order.
   * @param column is the column name.
   * @param button "Up" and "Down".
   */
  public void setColumnOrder(final String sectiontitle, final String column, final String button) {
    waitForPageLoaded();
    By locator = this.driverCustom.createLocatorFromProperty(CCMConstants.CCMQUERYPAGE_COLUMN_ORDER_XPATH,
        new String[] { sectiontitle, column });
    this.driverCustom.getWebDriver().findElement(locator).click();
    LOGGER.LOG.info("Click on " + sectiontitle + " column " + column);
    By locators = this.driverCustom.createLocatorFromProperty(CCMConstants.CCMQUERYPAGE_COLUMN_BUTTON_XPATH,
        new String[] { sectiontitle, button });
    this.driverCustom.getWebDriver().findElement(locators).click();
    LOGGER.LOG.info("Click on " + sectiontitle + " button " + button);
    waitForSecs(Duration.ofSeconds(2));
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to column display tab in query results page, <br>
   * As per column name, the values will sort in Ascending or Descending and returns the sorted value.
   *
   * @param columnName name of the column.
   * @param sortingtype "Ascending" or "Descending".
   * @return string of sorted values under the specified column name.
   */
  public String getSortedOrderValues(final String columnName, final String sortingtype) {
    waitForPageLoaded();
    String data = getListOfContentsForEachColumn(columnName);
    String[] getvalues = data.split(Pattern.quote("^"));
    ArrayList<Integer> columndata = new ArrayList<>();
    for (String datas : getvalues) {
      columndata.add(Integer.parseInt(datas));
    }
    List<String> arr = new ArrayList<>();
    if (sortingtype.equalsIgnoreCase("Ascending")) {
      Collections.sort(columndata);
      arr = columndata.stream().map(x -> String.valueOf(x)).collect(Collectors.toList());
      String ord = String.join("^", arr);
      LOGGER.LOG.info("Return column data in Ascending order.\n" + ord);
      return ord;
    }
    Collections.sort(arr, Collections.reverseOrder());
    String ord = String.join("^", arr);
    LOGGER.LOG.info("Return column data in Descending order.\n" + ord);
    return ord;
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to column display tab in query results page, <br>
   * Select sort order against column name.
   *
   * @param columnName name of the column
   * @param sortingordertype pass "Ascending" or "Descending" to sort.
   */
  public void setSortingOrderType(final String columnName, final String sortingordertype) {
    waitForPageLoaded();
    WebElement ele = this.driverCustom.getWebElement(CCMConstants.CCMQUERYPAGE_SORTORDER_DROPDOWN_XPATH, columnName);
    this.driverCustom.select(ele, sortingordertype, SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
    waitForSecs(5);
    LOGGER.LOG.info("Selected column " + columnName + " order as " + sortingordertype);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to column display tab in query results page, <br>
   * From Result Columns section it will take all the column names and it will match with the result table header
   * columns names.
   *
   * @return true if result columns and header columns equals matches.
   */
  public String validateResultColumns() {
    waitForPageLoaded();
    WebElement queryResultsColumns =
        this.driverCustom.getWebElement(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, "Result Columns");
    List<WebElement> resultsColumnNames =
        queryResultsColumns.findElements(By.xpath(".//div[@class='sectionContent']//tr"));
    List<String> columnnames = new ArrayList<>();
    resultsColumnNames.stream().map(WebElement::getText).forEach(columnnames::add);
    List<WebElement> headerelement =
        this.driverCustom.getWebElements("//img[@title='Add operation on this attribute']/..");
    List<String> header = new ArrayList<>();
    headerelement.stream().map(WebElement::getText).forEach(header::add);
    LOGGER.LOG.info("Validates result column heading and result column names.");
    if (columnnames.equals(header)) {
      return "Result column and Query result page column oder both are same.";
    }
    return "Number of result columns '" + columnnames.size() + " ' is not same as columns displayed in the table";
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to column display tab in query results page, <br>
   * From sort order section as per column name it will select the sorting type.
   *
   * @return true if wild card text box shows error else it will return false
   */
  public boolean validateWildcardsTextBox() {
    waitForPageLoaded();
    LOGGER.LOG.info("Validates text box is showing error or not.");
    return this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMPAGE_CONDITION_TEXT_BOX_XPATH,
        Duration.ofSeconds(10));
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Navigate to create query page and add same condition from 1 ...n times, as per index number it will select the
   * option. <br>
   * Index starts from 1....n , pass the index to set value in the condition. <br>
   *
   * @param conditionTitle use to send title of the condition.
   * @param selectOption use to select which type it want to select from the drop down.
   * @param index is to match condition. E.g if conditions are 1---n then index also 1...n.
   */
  public void selectConditionTypeByIndex(final String conditionTitle, final String selectOption, final String index) {
    try {
      JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
      js.executeScript("window.scrollBy(0,-200)");
    }
    catch (Exception e) {
      // TODO: handle exception
    }
    By locator = this.driverCustom.createLocatorFromProperty(CCMConstants.CCMWORKITEMPAGE_CONDITION_BY_INDEX_TYPE_XPATH,
        new String[] { conditionTitle, index });
    this.driverCustom.getWebDriver().findElement(locator).click();
    LOGGER.LOG.info("Select " + conditionTitle + " using index " + index);
    try {
      Cell option = this.engine.findElement(Criteria.isCell().withText(selectOption)).getFirstElement();
      option.getWebElement().click();
      waitForSecs(5);
    }
    catch (Exception e) {
      throw new WebAutomationException(selectOption + " option not found under " + conditionTitle + " widget.");
    }

    LOGGER.LOG.info("Select " + selectOption + " from " + conditionTitle + "widget.");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * After running a query this method checks if any work item present in the result column.
   *
   * @return True if No work items found in result query table.
   */
  public boolean validateNoWorkItemsFound() {
    waitForPageLoaded();
    LOGGER.LOG.info("Returns true if No work items found from the query.");
    try {
      return this.driverCustom.getWebElement(CCMConstants.CCMCREATEQUERYPAGE_SELECT_TAB_XPATH, "No work items found.")
          .isDisplayed();
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * After running a query this method checks if any work item present in the result column.
   *
   * @return True if No work items found in result query table.
   */
  public boolean isResultsFound() {
    waitForSecs(5);
    try {
      return (!this.driverCustom.getWebElement(CCMConstants.CCMCREATEQUERYPAGE_SELECT_TAB_XPATH, "No work items found.")
          .isDisplayed());
    }
    catch (Exception e) {
      return true;
    }
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to create query page and add same condition from 1 ...n times, as per index number it will set the
   * value.<br>
   * Index starts from 1....n , pass the index to set value in the condition. <br>
   *
   * @param conditionTitle use to send title of the condition.
   * @param value use to send value of the condition.
   * @param index is to match condition. E.g if conditions are 1---n then index also 1...n.
   */
  public void setAttributeTextBoxByIndex(final String conditionTitle, final String value, final String index) {
    waitForPageLoaded();
    int indexnumber = Integer.parseInt(index);
    Dialog dialg =
        this.engine.findElement(Criteria.isDialog().withTitle(conditionTitle)).getElementByIndex(indexnumber);
    try {
      TextField attriCond = this.engine.findFirstElement(Criteria.isTextField().inContainer(dialg));
      attriCond.setText(value);
    }
    catch (Exception e) {
      throw new WebAutomationException(conditionTitle + " dialog text box not found or locator not found.");
    }
    LOGGER.LOG.info(
        "Provides attibute value " + value + " as per Condition title " + conditionTitle + " and index" + index + "");
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to create query page and add same condition from 1 ...n times, as per index number it will set the
   * value.<br>
   * Index starts from 1....n , pass the index to set value in the condition. <br>
   *
   * @param additionalParams the map value which contains Index,title and value.
   */
  public void setAttributeTextBoxByIndexDate(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    int indexnumber = Integer.parseInt(additionalParams.get("index"));
    try {
      LOGGER.LOG.info(additionalParams);
      LOGGER.LOG.info(this.driverCustom.getWebElements("//span[@title='Creation Date']").size());
      LOGGER.LOG
          .info(
              this.engine
                  .findElementWithDuration(
                      Criteria.isDialog().withTitle(additionalParams.get(CCMConstants.CONDITIONTITLE)), this.timeInSecs)
                  .getElementList().size());
      Dialog dialg = this.engine
          .findElementWithDuration(Criteria.isDialog().withTitle(additionalParams.get(CCMConstants.CONDITIONTITLE)),
              this.timeInSecs)
          .getElementByIndex(indexnumber);
      try {
        TextField attriCond = this.engine
            .findElementWithDuration(Criteria.isTextField().inContainer(dialg), this.timeInSecs).getElementByIndex(2);
        attriCond.setText(additionalParams.get("value"));
        LOGGER.LOG.info("Set attribute value " + additionalParams.get("value") + " in " +
            additionalParams.get(CCMConstants.CONDITIONTITLE) + " dialog");
      }
      catch (Exception e) {
        throw new WebAutomationException(
            additionalParams.get("conditionTitle") + " dialog text box not found or locator not found.");
      }
      LOGGER.LOG.info("Provides attribute value " + additionalParams.get("value") + " as per Condition title " +
          additionalParams.get("conditionTitle") + " and index" + additionalParams.get("index") + "");
    }
    catch (Exception e) {
      Dialog dialg123 = this.engine
          .findElementWithDuration(Criteria.isDialog().withTitle("Assign Values to Query Parameters"), this.timeInSecs)
          .getFirstElement();
      TextField attriCond = this.engine.findElement(Criteria.isTextField().inContainer(dialg123)).getElementByIndex(2);
      attriCond.setText(additionalParams.get("value"));
      LOGGER.LOG.info(
          "Set attribute value " + additionalParams.get("value") + " in 'Assign Values to Query Parameters' dialog");
    }
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to create query page and add same condition from 1 ...n times, as per index number it will select the
   * type.<br>
   * Index starts from 1....n , pass the index to select value in the condition. <br>
   *
   * @param condition use to send title of the condition.
   * @param type use to select value of the condition.
   * @param index is to match condition. E.g if conditions are 1---n then index also 1...n.
   */
  public void selectValueFromCondition(final String condition, final String type, final String index) {
    waitForPageLoaded();
    int indexnum = Integer.parseInt(index);
    Dialog dia = this.engine.findElement(Criteria.isDialog().withTitle(condition)).getElementByIndex(indexnum);
    try {
      Label lblClickOnSelectType =
          this.engine.findElement(Criteria.isLabel().withText(type).inContainer(dia)).getFirstElement();
      lblClickOnSelectType.click();
      waitForSecs(5);
    }
    catch (Exception e) {
      throw new WebAutomationException(condition + " dialog " + type + " label not found or locator not found.");
    }
    LOGGER.LOG.info("Selects type " + type + " from condition " + condition + " as per index " + index);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Click on Close button
   */
  public void closeAllCondition() {
    waitForPageLoaded();
    while (this.driverCustom.isElementInvisible("//a[@class='button' and@ title='Close']", Duration.ofSeconds(10))) {
      try {
        Button btn = this.engine.findElement(Criteria.isButton().withToolTip("Close")).getFirstElement();
        btn.click();
      }
      catch (Exception e) {
        break;
      }
    }
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   *
   * @param queryname name of query.
   * @return true if query deleted.
   */
  public boolean searchDeletedQuery(final String queryname) {
    refresh();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
    this.driverCustom.getPresenceOfWebElement("//input[@name='filter-box']");
    waitForSecs(5);
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
    if (this.driverCustom.isElementVisible("//select[@class='queryGrid-select-group']", Duration.ofSeconds(20))) {
      WebElement ele = this.driverCustom.getWebElement("//select[@class='queryGrid-select-group']");
      ele.click();
      Select sel = new Select(ele);
      sel.selectByVisibleText("Hide groups");
    }
    this.driverCustom.isElementClickable(CCMConstants.CCMCREATEPLANPAGE_SEARCHPLANS_TEXTBOX_XPATH,
        Duration.ofSeconds(10));
    Text textSearch = this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder(CCMConstants.SEARCH));
    textSearch.clearText();
    textSearch.setText(queryname);
    if (!this.driverCustom.getWebElement("//div[@class='input-wrapper']/input")
        .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equals(queryname)) {
      throw new WebAutomationException(queryname + " Entered Query name is not matched with Searched query.\n");
    }
    LOGGER.LOG.info(queryname + " query name is provided in Search query field to search the query.");
    boolean result = this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_LINK_TYPE_XPATH,
        Duration.ofSeconds(5), queryname);
    return !result;
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   */
  public void clickOnCloseButton() {
    List<WebElement> element = this.driverCustom.getWebElements("//a[@title='Close']");
    for (WebElement ele : element) {
      if (this.driverCustom.isElementVisible(ele, Duration.ofSeconds(2))) {
        ele.click();
      }
    }
    this.driverCustom.getWebElement("//a[@class='closeButton']").click();
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   *
   * @param columnName name of column name.
   * @param columnValue column value.
   * @return true if content validated.
   */
  public boolean validateColumnContent(final String columnName, final String columnValue) {
    String d = "";
    if (!columnName.equals("Type")) {
      d = getListOfContentsForEachColumn(columnName);
    }
    else {
      List<WebElement> lts =
          this.driverCustom.getWebDriver().findElements(By.xpath("//td//span[contains(@title,'Work Item Type')]"));
      List<String> rowContent = new ArrayList<>();
      lts.stream().forEach(x -> rowContent.add(x.getAttribute("alt")));
      d = String.join("^", rowContent);
    }
    if (d.isEmpty()) {
      List<WebElement> lts = this.driverCustom.getWebDriver()
          .findElements(By.xpath("//tr[@class='visibleRow']//td//span[contains(@title,'Work Item Type')]"));
      List<String> rowContent = new ArrayList<>();
      lts.stream().forEach(x -> rowContent.add(x.getAttribute("alt")));
      d = String.join("^", rowContent);
    }
    String[] values = d.split(Pattern.quote("^"));
    String[] colVal = { columnValue };
    if (columnValue.contains("OR")) {
      colVal = columnValue.split("OR");
    }

    boolean tray = false;

    if (colVal.length == 1) {
      for (String data : values) {
        if (data.equals(colVal[0])) {
          tray = true;

        }
        else {
          return false;
        }
      }

      return tray;

    }
    for (int i = 0; i < (colVal.length - 1); i++) {
      for (String data : values) {
        if (data.equals(colVal[i]) || data.equals(colVal[i + 1])) {
          tray = true;
        }
        else {
          return false;
        }
      }
    }
    return tray;
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * <p>
   * Navigate to result query page and store all the result columns name and respective result values in a map. <br>
   * As per provided column name it will return the result column values.
   *
   * @return string of result values of the column.
   */
  public String queryResultValidation() {
    String totalDates = "";
    try {
      totalDates = this.driverCustom.getWebElement("(//span[@class='pageInfoSpan'])[1]//b[3]").getText();
      if (totalDates.contains("1000")) {
        // More 1000 record only show 1000
        totalDates = this.driverCustom.getWebElement("((//span[@class='pageInfoSpan'])[1]//.)[last()]").getText();
        totalDates = totalDates.replaceAll("\\D+", "");
      }
    }
    catch (Exception e) {
      totalDates = this.driverCustom.getWebElement("(//span[@class='pageInfoSpan'])[1]//b[2]").getText();
    }
    return totalDates;
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         This method used to count query result count after clicking RUN button {@link CCMCreateQueryPage#button} }
   * @param QueryResultCount Query Result Count
   * @return total count
   */
  public String queryResultCount(final String QueryResultCount) {
    String totalDates = "";
    try {
      totalDates = this.driverCustom.getWebElement("(//span[@class='pageInfoSpan'])[1]//b[3]").getText();
      LOGGER.LOG.info("Number of work item return after query is : " + totalDates);
    }
    catch (Exception e) {
      totalDates = this.driverCustom.getWebElement("(//span[@class='pageInfoSpan'])[1]//b[2]").getText();
      LOGGER.LOG.info("Number of work item return after query is : " + totalDates);
    }
    if (QueryResultCount.contains(totalDates)) {
      LOGGER.LOG.info("Number of work item return after query is verified with expected Number of work item");
      return totalDates;
    }
    return totalDates;
  }


  /**
   * @author UUM4KOR
   *         <p>
   *         This method used to get selected column value after click on RUN using {@link CCMCreateQueryPage#button} }
   * @param columnName query result column name summary,id,...etc
   * @return string
   */
  public String getSelectedColumnValue(final String columnName) {
    StringBuilder sb = new StringBuilder();
    StringBuilder sb1 = new StringBuilder();
    List<WebElement> ele = this.driverCustom
        .getWebElements("//td[@class='com-ibm-team-rtc-foundation-web-ui-gadgets-table-TableCell " + columnName + "']");
    int i = 0;
    while (i < ele.size()) {
      WebElement parentElement = ele.get(i);
      sb.append(parentElement.getText());
      sb.append(",");
      i++;
    }
    for (WebElement test : ele) {
      String queryColumnValue = test.getText();
      LOGGER.LOG.info(columnName + " value is :" + queryColumnValue);
    }
    if (!this.driverCustom.isElementInvisible("//div[@dojoattachpoint='_metaAreaContainer']//a[text()='Next']",
        Duration.ofSeconds(10))) {
      waitForSecs(5);
      clickOnButtons("Next");
      waitForSecs(5);
      LOGGER.LOG.info("clicked on Next");
      List<WebElement> ele2 = this.driverCustom.getWebElements(
          "//td[@class='com-ibm-team-rtc-foundation-web-ui-gadgets-table-TableCell " + columnName + "']");
      int i1 = 0;
      while (i1 < ele2.size()) {
        WebElement parentElement1 = ele2.get(i1);
        sb1.append(parentElement1.getText());
        sb1.append(",");
        i1++;
      }
      for (WebElement test : ele2) {
        String nextpageValue = test.getText();
        LOGGER.LOG.info(columnName + " next page value : " + nextpageValue);
      }
    }
    else {
      LOGGER.LOG.info(columnName + " next page not found.");
    }
    String allVal = sb.toString() + sb1.toString();
    return allVal;
  }

  /**
   * This method wait for the presence of Work Items link in work items page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEQUERYPAGE_WORKITEMS_LINK_XPATH);
  }

  /**
   * <p>
   * Open 'Work Items' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Query' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Click on Particular selecting Filed Against based on Text
   *
   * @param Type Query Text
   */
  public void clickOnSelectFiledAgainst(final String Type) {
    // waitForPageLoaded();
    Label lblClickOnSelectType = this.engine.findElement(Criteria.isLabel().withText(Type)).getFirstElement();
    lblClickOnSelectType.scrollToElement();
    this.driverCustom.clickUsingActions(lblClickOnSelectType.getWebElement());
    List<WebElement> ele = this.driverCustom.getWebElements("//a[contains(@class,'entry')]");
    for (WebElement test : ele) {
      if (!test.getAttribute("aria-selected").equals("true") && test.getText().equalsIgnoreCase(Type)) {
        this.driverCustom.getFirstVisibleWebElement("//div[@class='content jazz-ui-PageTemplate-flexrow']", null)
            .click();
        this.driverCustom.clickUsingActions(lblClickOnSelectType.getWebElement());
      }
    }
  }
}