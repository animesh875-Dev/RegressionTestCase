package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Panel;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.RadioButtonFinder;
import com.bosch.psec.web.test.finder.container.PanelFinder;
import com.bosch.psec.web.test.finder.container.RowFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.panel.JazzPanelFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.label.JazzLabelFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * <P>
 * Represents Change and configuration management work item editor page, has capabilities to create, edit and change
 * work flow state of any work items of any template.
 */
public class CCMQuickPlannerPage extends AbstractCCMPage {

  /**
   * Constructor setting the object of {@link WebDriverCustom} class.
   *
   * @param driverCustom required for interacting with the browser.
   */
  public CCMQuickPlannerPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzRowFinder(this.driverCustom.getWebDriver()), new JazzTextFieldFinder(),
        new JazzDropdownFinder(), new JazzButtonFinder(), new RowCellFinder(), new JazzTabFinder(),
        new JazzDialogFinder(), new CheckboxFinder(), new RowFinder(), new JazzLabelFinder(), new LinkFinder(),
        new DropdownFinder(), new RadioButtonFinder(), new JazzRadioButtonFinder(), new TextFieldFinder(),
        new PanelFinder(), new JazzPanelFinder(), new RowCellFinder(), new RowFinder());
  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)}, 'Create a board'
   * page is displayed.<br>
   * <br>
   * Provide Board Name,Board Type,Iteration Name in 'Create a board' page
   * using{@link #createABoard(String, String, String)}<br>
   *
   * @param boardName the name of Test Board
   * @param boardType the type of the board: Work Board or Plan Board
   * @param iterationName the Iteration Name of the project
   */
  public void createABoard(final String boardName, final String boardType, final String iterationName) {
    waitForSecs(Duration.ofSeconds(15));
    Text txtBoardName =
        this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Your board name"), this.timeInSecs)
            .getFirstElement();
    txtBoardName.setText(boardName);
    LOGGER.LOG.info("Enter board name in Create Board Page");

    // Select Board Type
    try {
      this.driverCustom.switchToFrame("//iframe[@name='qpNextContent']");
      waitForSecs(Duration.ofSeconds(5));
      this.driverCustom.click("//button[contains(., 'DYNAMIC_VAlUE')]", boardType);
      waitForSecs(Duration.ofSeconds(10));
    }
    catch (Exception e) {
      waitForSecs(Duration.ofSeconds(5));
      this.driverCustom.click("//button[contains(., 'DYNAMIC_VAlUE')]", boardType);
      waitForSecs(Duration.ofSeconds(10));
    }
    LOGGER.LOG.info("Select board type in Create Board Page");

    // Select preconfigured lane
    this.driverCustom.click("//div[@class='BoardIterationSettingQP']");
    waitForSecs(Duration.ofSeconds(10));
    this.driverCustom.typeText("//input[@role='combobox' and @aria-expanded='true']", iterationName);
    waitForSecs(Duration.ofSeconds(5));
    WebElement optionElement = this.driverCustom.getWebElement(
        "//div[@role='option']/descendant::div[text()[normalize-space() = 'DYNAMIC_VAlUE']]", iterationName);
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.clickUsingActions(optionElement);
    this.driverCustom.switchToParentFrame();
    LOGGER.LOG.info("Select Interation name in Lane dropdown of Create Board Page");

    Button btnCreate =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Create"), this.timeInSecs).getFirstElement();
    btnCreate.click();
    LOGGER.LOG.info("Click on Create button in Create Board Page");
    waitForSecs(Duration.ofSeconds(10));
  }

  /**
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * This method used to select dropdown in work item row with respect to 'pannel Name', 'workItem Name', 'drdTooltip'
   * of drop down and 'dropdown Value' using
   * {@link CCMQuickPlannerPage #selectDropdownInWorkItemRow(String, String, String, String)}
   *
   * @param pannelName the name of Pannel
   * @param workItemName the name of work item row
   * @param drdTooltip the tooltip of work item
   * @param drdValue the type which want to change
   */
  public void selectDropdownInWorkItemRow(final String pannelName, final String workItemName, final String drdTooltip,
      final String drdValue) {
    Panel panel = this.engine.findElementWithDuration(Criteria.isPanel().withTitle(pannelName), this.timeInSecs)
        .getFirstElement();
    Row workItemRow =
        this.engine.findElementWithDuration(Criteria.isRow().withText(workItemName).inContainer(panel), this.timeInSecs)
            .getFirstElement();
    workItemRow.scrollToElement();
    Dropdown drdType =
        this.engine.findElementWithDuration(Criteria.isDropdown().withToolTip(drdTooltip).inContainer(workItemRow),
            this.timeInSecs).getFirstElement();
    drdType.selectOptionWithText(drdValue);
    LOGGER.LOG.info("Select '" + drdValue + "' in dropdown with '" + drdTooltip + "' tooltip" + "of '" + workItemName +
        "' work item.");
  }


  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * This method used to verify work item is avalabel under withr respective pannel using
   * {@link CCMQuickPlannerPage #isWorkItemInPanel(String, String)}
   *
   * @param pannelName the name of Pannel
   * @param workItemName the name of work item row
   * @return {@link Boolean}
   */
  public Boolean isWorkItemInPanel(final String pannelName, final String workItemName) {
    Panel panel = this.engine.findElementWithDuration(Criteria.isPanel().withTitle(pannelName), this.timeInSecs)
        .getFirstElement();
    Row workItemRow = null;
    try {
      workItemRow =
          this.engine.findElement(Criteria.isRow().withText(workItemName).inContainer(panel)).getFirstElement();
    }
    catch (Exception e) {
      waitForSecs(Duration.ofSeconds(2));
    }
    return workItemRow != null;
  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * This method used to select(open) work item link in pannel using {@link #selectWorkItemLinkInPannel(String, String)}
   *
   * @param workItemID the id of work item
   * @param workItemName the name of work item
   */
  public void selectWorkItemLinkInPannel(final String workItemID, final String workItemName) {
    Link wiLink = this.engine
        .findElementWithDuration(Criteria.isLink().withText(workItemID + ": " + workItemName), this.timeInSecs)
        .getFirstElement();
    wiLink.click();
    LOGGER.LOG.info("Select '" + workItemName + "' In the board.");
    refresh();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {

    String errorMessage =
        "CCM Quick Planner Page not loaded or The Condition used to check whether page is loaded or not is not general.";
    this.driverCustom.anyMatch(By.xpath("//a[@class = 'tab']"), 2, x -> x.getSize().getHeight() > 0, errorMessage);
    waitForSecs(Duration.ofSeconds(2));
  }


  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * This method used to select work board from my board using
   * {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   *
   * @param workBoardName Work Board name.
   * @return string.
   */
  public String selectWorkBoardFromMyBoard(final String workBoardName) {

    waitForSecs(Duration.ofSeconds(5));
    if (this.driverCustom.isElementInvisible("//span[contains(text(),'" + workBoardName + "')]", Duration.ofSeconds(20))) {

      waitForSecs(Duration.ofSeconds(2));
      this.driverCustom.switchToDefaultContent();
      waitForSecs(Duration.ofSeconds(3));
      this.driverCustom.switchToFrame("//iframe[@name='qpNextContent']");

     this.driverCustom.isElementInvisible("//span[contains(text(),'" + workBoardName + "')]", Duration.ofSeconds(20));
      this.driverCustom.getWebElement("//span[contains(text(),'DYNAMIC_VAlUE')]", workBoardName);
      this.driverCustom.getWebElement("//span[contains(text(),'DYNAMIC_VAlUE')]", workBoardName).click();
      LOGGER.LOG.info("Work Board '" + workBoardName + "' Selected successfully.  ");

    }
    else {
      throw new WebAutomationException(workBoardName + " invalid tab name.");
    }
    LOGGER.LOG.info(workBoardName + " : Selected from  quick planner work item page successfully.");
    return workBoardName;
  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * This method used to click in 'Create a work item...' text box using
   * {@link CCMQuickPlannerPage #clickOnCreateWorkitemtextBox()}
   */
  public void clickOnCreateWorkitemtextBox() {
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getWebElement("//input[@placeholder='Create a work item...']").click();
    LOGGER.LOG.info("Clicked on create work item text box successfully");

  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * This method used to click on work item menu ex:'Type:' using {@link #clickOnWorkItemMenu(String)}}
   *
   * @param menuName menu name.
   * @return string
   */


  public String clickOnWorkItemMenu(final String menuName) {

    waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.getWebElement("//a[@title='" + menuName + "']");
    this.driverCustom.getWebElement("//a[@title='" + menuName + "']").click();
    LOGGER.LOG.info("Clicked on work item successfully");
    return menuName;
  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * This method used to click and set summary in 'Create a work item...' text box using
   * {@link CCMQuickPlannerPage #setSummary(String)}
   *
   * @param summary work item summary
   * @return string
   */
  public String setSummary(final String summary) {
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getWebElement("//input[@placeholder='Create a work item...']").click();
    LOGGER.LOG.info("Clicked on create work item text box successfully");
    waitForSecs(Duration.ofSeconds(2));
    waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.getWebElement(CCMConstants.CCMQUICKPLANNER_SUMMARYFIELD_XPATH).sendKeys(summary);
    LOGGER.LOG.info("Summary ' " + summary + " ' Set successfully");
    waitForSecs(Duration.ofSeconds(2));

    return summary;


  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * clock on work item menu{@link CCMQuickPlannerPage #clickOnWorkItemMenu(String)}} This method used to select work
   * item type from menu list using {@link #selectWorkitemType(String)}}
   *
   * @param workItemType work Item Type
   * @return string
   */
  public String selectWorkitemType(final String workItemType) {


    this.driverCustom.getWebElement("//span[text()='" + workItemType + "']");
    this.driverCustom.getWebElement("//span[text()='" + workItemType + "']").click();
    LOGGER.LOG.info("Work item type ' " + workItemType + " ' Selected successfully");
    return workItemType;
  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * This method used to set required attributes text in quick planner create text box using some standards quick
   * planner syntax using {@link CCMQuickPlannerPage #setTextInQuickPlannerCreateTextBox(String)}}.
   *
   * @param text value to set.
   * @return string
   */
  public String setTextInQuickPlannerCreateTextBox(final String text) {

    waitForSecs(Duration.ofSeconds(6));
    try {
      this.driverCustom.getWebElement(CCMConstants.CCMQUICKPLANNER_SUMMARYFIELD_XPATH).click();
      waitForSecs(Duration.ofSeconds(2));
      this.driverCustom.getWebElement(CCMConstants.CCMQUICKPLANNER_SUMMARYFIELD_XPATH).sendKeys(Keys.END + text);
    }
    catch (Exception e) {
      this.driverCustom.getWebElement("//div[contains(@class,'NotificationView')]//..//div[@class='input-container']")
          .click();
      waitForSecs(Duration.ofSeconds(2));
      this.driverCustom
          .getWebElement("//div[contains(@class,'NotificationView')]//..//div[@class='input-container']//input")
          .sendKeys(Keys.END + text);
    }
    LOGGER.LOG.info("Text set in planner create text box ' " + text + " ' Successfully");
    waitForSecs(Duration.ofSeconds(2));
    return text;
  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * Create a new work item using {@link CCMQuickPlannerPage #setSummary}
   * ,{@link CCMQuickPlannerPage #selectWorkitemType}.<br>
   * This method used to click on create button using {@link CCMQuickPlannerPage #clickOnCreateButton}.
   */
  public void clickOnCreateButton() {

    this.driverCustom.getWebElement("//a[text()='Create']");
    this.driverCustom.getWebElement("//a[text()='Create']").click();
    LOGGER.LOG.info("Clicked on 'create' button successfully");
    waitForSecs(Duration.ofSeconds(10));

  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * set text in quick planner create text box using {@link #setTextInQuickPlannerCreateTextBox(String)}} and miss any
   * mandatory field (meeting type).<br>
   * Click On Create Button using {@link CCMQuickPlannerPage #clickOnCreateButton}<br>
   * Get validation message from notification view using
   * {@link CCMQuickPlannerPage #getValidationMessageFromNotificationView()}.
   *
   * @return error message
   */
  public String getValidationMessageFromNotificationView() {


    this.driverCustom.getPresenceOfWebElement("//span[text()='Show details']");
    this.driverCustom.getPresenceOfWebElement("//span[text()='Show details']").click();
    LOGGER.LOG.info("clicked ok 'Show details' link successfully");
    waitForSecs(Duration.ofSeconds(2));
    String errorMessage = this.driverCustom.getWebElement("//div[@class='message']").getText();

    LOGGER.LOG.info("Validation message displayed in notification view is '" + errorMessage + "'");
    return this.driverCustom.getWebElement("//div[@class='message']").getText();


  }

  /**
   * <p>
   * Create a new work item using {@link CCMQuickPlannerPage #setSummary},
   * {@link CCMQuickPlannerPage #selectWorkitemType}..etc<br>
   * Click On Create Button using {@link CCMQuickPlannerPage #clickOnCreateButton},newly created work item is displayed
   * under quick planner page in the selected board.<br>
   * Verify newly created work item displayed in the selected board using
   * {@link CCMQuickPlannerPage #isWorkItemCreated(String)}}.
   *
   * @param workItemSummary summary of the work item.
   * @return {@link Boolean}
   */
  public boolean isWorkItemCreated(final String workItemSummary) {
    waitForSecs(Duration.ofSeconds(4));
    this.driverCustom.getWebElement("//a[contains(text(),'" + workItemSummary + "')]");
    LOGGER.LOG.info("Work item created as '" + workItemSummary + "'");
    return true;
  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * Create a new work item using {@link CCMQuickPlannerPage #setSummary},
   * {@link CCMQuickPlannerPage #selectWorkitemType}..etc.<br>
   * Click On Create Button using {@link CCMQuickPlannerPage #clickOnCreateButton}, newly created work item is displayed
   * under quick planner page in the selected board.<br>
   * Click On Expand Button Icon Arrow To Expand All work item details with respect to work item using
   * {@link CCMQuickPlannerPage #clickOnExpandButtonIconArrowToExpandAll(String)}}.
   *
   * @param workItemSummary work Item summary
   */
  public void clickOnExpandButtonIconArrowToExpandAll(final String workItemSummary) {
    waitForSecs(Duration.ofSeconds(4));
    Optional<WebElement> ele = this.driverCustom.getWebElements("//div[@class='workItemContents']").stream()
        .filter(x -> x.getText().contains(workItemSummary)).findFirst();
    if (ele.isPresent()) {
      this.driverCustom.getChildElement(ele.get(), By.xpath(".//div[@class='expandButton icon-arrow-expandall']"))
          .click();
      waitForSecs(Duration.ofSeconds(6));
      LOGGER.LOG.info("Clicked expand Button icon-arrow-expand all with respect to '" + workItemSummary + "'");

      waitForSecs(Duration.ofSeconds(3));

    }
    else {
      LOGGER.LOG.info("Not clicked expand Button icon-arrow-expand all with respect to '" + workItemSummary + "'");
    }


  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Quick Planner' Sub Menu using {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * Select Work Board From My Board {@link CCMQuickPlannerPage #selectWorkBoardFromMyBoard(String)}} <br>
   * Create a new work item using {@link CCMQuickPlannerPage #setSummary},
   * {@link CCMQuickPlannerPage #selectWorkitemType}..etc.<br>
   * Click On Create Button using {@link CCMQuickPlannerPage #clickOnCreateButton},newly created work item is displayed
   * under quick planner page in the selected board.<br>
   * Click on expand button icon arrow to expand all work item details with respect to work item using
   * {@link CCMQuickPlannerPage #clickOnExpandButtonIconArrowToExpandAll(String)}}<br>
   * This method is used to get attribute values ex: 'Filed Against' using
   * {@link CCMQuickPlannerPage #getAttributeValue(String)}}.
   *
   * @param attributeName attribute Name Filed Against, etc
   * @return string value
   */
  public String getAttributeValue(final String attributeName) {
    waitForSecs(Duration.ofSeconds(2));
    String attributeVal = this.driverCustom
        .getWebElement("//td[@title='" + attributeName + "']/following-sibling :: td/span/button").getText();

    LOGGER.LOG.info(attributeName + "' value is '" + attributeVal + "'");
    waitForSecs(Duration.ofSeconds(2));
    return attributeVal;
  }
}