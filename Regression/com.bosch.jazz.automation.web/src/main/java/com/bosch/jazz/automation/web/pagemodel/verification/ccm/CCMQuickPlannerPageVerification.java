/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.ccm;

import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMQuickPlannerPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.container.Panel;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.container.panel.JazzPanelFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.dropdown.JazzDropdownFinder;

/**
 * @author UUM4KOR
 */
public class CCMQuickPlannerPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom must not be null.
   */
  public CCMQuickPlannerPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDropdownFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new RowCellFinder(), new JazzDialogFinder(), new JazzTabFinder(), new JazzPanelFinder());
  }


  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#createABoard(String, String, String)}.
   * 
   * @param boardName the name of test board
   * @param boardType the type of test board
   * @param interationName the interation name in lane dropdown
   * @param lastResult get the last result of {@link CCMQuickPlannerPage#createABoard(String, String, String)}
   * @return validation message
   */
  public TestAcceptanceMessage verifyCreateABoard(final String boardName, final String boardType,
      final String interationName, final String lastResult) {
    waitForSecs(3);
    try {
      this.driverCustom.switchToFrame("//iframe[@name='qpNextContent']");
      WebElement testBoard = this.driverCustom
          .getPresenceOfWebElement("//span[@class='nameContainer' and contains(., 'DYNAMIC_VAlUE')]", boardName);
      return new TestAcceptanceMessage(true,
          "Verified that the test board '" + boardName + "' is created successfully.\n" + "Actual Result '" +
              testBoard.getText().trim() + "'  is created and expected result is '" + boardName + "' created.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified that the test board '" + boardName + "' is not created successfully.\n" +
              "Actual result is exception thrown away and cannot find the board '" + boardName + "'.\n" +
              e.getMessage());
    }
  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#selectDropdownInWorkItemRow(String, String, String, String)}.
   * 
   * @param pannelName the column/pannel containing work item in the board
   * @param workItemName the name of work item
   * @param drdTooltip the tooltip of type showing
   * @param drdValue the value is selected
   * @param additionalPram the title pannel name containing work item after changing type dropdown
   * @param lastResult get the last result of
   *          {@link CCMQuickPlannerPage#selectDropdownInWorkItemRow(String, String, String, String)}
   * @return validation message
   */
  public TestAcceptanceMessage verifySelectDropdownInWorkItemRow(final String pannelName, final String workItemName,
      final String drdTooltip, final String drdValue, final String additionalPram, final String lastResult) {
    waitForSecs(3);
    try {
      Panel panel = this.engine.findElementWithinDuration(Criteria.isPanel().withTitle(additionalPram), this.timeInSecs)
          .getFirstElement();
      this.engine.findElementWithDuration(Criteria.isRow().withText(workItemName).inContainer(panel), this.timeInSecs)
          .getFirstElement();
      return new TestAcceptanceMessage(true,
          "Verified that the type of work item is changed from '" + drdTooltip + "' to '" + drdValue +
              "' successfully.\n" + "Actual Result '" + workItemName + "' is found in the '" + additionalPram +
              "' pannel.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified that the type of work item is not changed from '" + drdTooltip + "' to '" + drdValue +
              "' successfully.\n" + "Actual Result  '" + workItemName + "' is not found in the '" + additionalPram +
              "' pannel.\n" + e.getMessage());
    }
  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#selectWorkItemLinkInPannel(String, String)}.
   * 
   * @param workItemID the id of work item
   * @param workItemName the name of work item
   * @param lastResult get the last result of {@link CCMQuickPlannerPage#selectWorkItemLinkInPannel(String, String)}
   * @return validation message
   */
  public TestAcceptanceMessage verifySelectWorkItemLinkInPannel(final String workItemID, final String workItemName,
      final String lastResult) {
    waitForSecs(3);
    String result = this.driverCustom
        .getFirstVisibleWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_WORKITEMID_TEXT_XPATH, null).getText();
    if (result.contains(workItemID)) {
      return new TestAcceptanceMessage(true,
          "Verified that the work item '" + workItemName + "' is selected successfully.\n" + "Actual result is '" +
              result + "' string containing '" + workItemID + "' work item.");
    }
    return new TestAcceptanceMessage(false,
        "Verified that the work item '" + workItemName + "' is not selected successfully.\n" + "Actual result is '" +
            result + "' string not containing '" + workItemID + "' work item.");
  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#isWorkItemCreated(String)}.
   * 
   * @param summary work item summary
   * @param lastResult {@link Boolean}
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyIsWorkItemCreated(final String summary, final String lastResult) {
    waitForSecs(3);
    String result = this.driverCustom.getWebElement("//a[contains(text(),'" + summary + "')]").getText();
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified work item '" + summary + "' is created successfully.\n" + "Actual work item text is '" + result +
              "' \nActual work item text containing expected value as '" + summary + "'.");
    }
    return new TestAcceptanceMessage(false, "Verified work item '" + summary + "' is not created successfully.\n" +
        "Actual work item text is '" + result + "'\nActual work item text not containing '" + summary + "'.");

  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#getAttributeValue(String)}.
   * 
   * @param attributeName attribute Name
   * @param additionalParam expected value
   * @param lastResult not used
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyGetAttributeValue(final String attributeName, final String additionalParam,
      final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Verified Actual attribute '" + attributeName + "' value is - \n \"" + lastResult + "\".");
    }
    return new TestAcceptanceMessage(false,
        "Verified Actual value '" + attributeName + "' value is - \"" + lastResult + "\".");
  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#clickOnExpandButtonIconArrowToExpandAll(String)}.
   * 
   * @param workItemSummary work Item Summary
   * @param lastResult not used
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyClickOnExpandButtonIconArrowToExpandAll(final String workItemSummary,
      final String lastResult) {
    waitForSecs(4);
    try {
      Optional<WebElement> ele = this.driverCustom.getWebElements("//div[@class='workItemContents']").stream()
          .filter(x -> x.getText().contains(workItemSummary)).findFirst();
      if (ele.isPresent()) {
        this.driverCustom.getChildElement(ele.get(), By.xpath("//div[@class='expandButton icon-arrow-collapseall']"))
            .isDisplayed();

        waitForSecs(2);
        LOGGER.LOG.info(
            "Clicked visibility for expand Button 'icon-arrow-expandall' with respect to '" + workItemSummary + "'");

      }
      return new TestAcceptanceMessage(true,
          "Verified 'expandButton icon-arrow-collapseall' after Clicking expand Button 'icon-arrow-expand' with respect to work item summary '" +
              workItemSummary + "'");

    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified 'expandButton icon-arrow-collapseall' not Clicked expand Button 'icon-arrow-expand' with respect to '" +
              workItemSummary + "'.\n" + e.getMessage());
    }
  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#getValidationMessageFromNotificationView()}.
   * 
   * @param additionalParam expected message
   * @param lastResult message
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyGetValidationMessageFromNotificationView(final String additionalParam,
      final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.contains(additionalParam),
          "Verified Actual validation message displayed in notification view is - \"" + lastResult +
              "\".\nExpected  validation message is \"" + additionalParam + "\"");
    }
    return new TestAcceptanceMessage(false,
        "Verified Expected validation message \"" + additionalParam +
            "\" not displayed in notification view or \n Actual message displayed in notification view is - \"" +
            lastResult + "\".");
  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#setTextInQuickPlannerCreateTextBox(String)}.
   * 
   * @param additionalParam expected text value
   * @param lastResult actual value
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySetTextInQuickPlannerCreateTextBox(final String additionalParam,
      final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.contains(additionalParam),
          "Verified Actual text set in the 'Quick Planner Create Text Box'  is - \"" + lastResult + "\".");
    }
    return new TestAcceptanceMessage(false,
        "Verified Expected text \"" + additionalParam +
            "\" not set in the 'Quick Planner Create Text Box' or \n Actual text displayed in Text Box as - \"" +
            lastResult + "\".");
  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#selectWorkitemType(String)}.
   * 
   * @param workItemType work Item Type
   * @param lastResult last value
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySelectWorkitemType(final String workItemType, final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.contains(workItemType),
          "Verified Actual work item type selected is - \n \"" + lastResult + "\".");
    }
    return new TestAcceptanceMessage(false,
        "Verified Actual work item type not selected or \n Actual work item type is - \"" + lastResult + "\".");
  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#setSummary(String)}.
   * 
   * @param summary work item summary
   * @param lastResult value
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySetSummary(final String summary, final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.contains(summary),
          "Verified Actual work item summary is - \n \"" + lastResult + "\".");
    }
    return new TestAcceptanceMessage(false,
        "Verified Actual work item summary not set or \n Actual work item summary is - \"" + lastResult + "\".");
  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#clickOnCreateButton()}.
   * 
   * @param additionalParam expected value
   * @param lastResult not used
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyClickOnCreateButton(final String additionalParam, final String lastResult) {

    waitForSecs(4);
    if (this.driverCustom.isElementPresent("//div[@class='NotificationView']", Duration.ofSeconds(15))) {
      LOGGER.LOG.info("Verified 'error notification view' in notification view after clicking 'Create' button");
      return new TestAcceptanceMessage(true, "Verified 'Create' button clicked successfully");
    }
    CCMQuickPlannerPage quickPlan = new CCMQuickPlannerPage(this.driverCustom);


    if (quickPlan.isWorkItemCreated(additionalParam)) {
      LOGGER.LOG.info("Verified work item create in view after clicking 'Create' button");
      return new TestAcceptanceMessage(true, "Verified 'Create' button clicked successfully");
    }
    return new TestAcceptanceMessage(false, "Verified 'Create' button not clicked successfully");
  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#selectWorkBoardFromMyBoard(String)}.
   * 
   * @param workBoardName work Board Name
   * @param lastResult Actual work board name
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySelectWorkBoardFromMyBoard(final String workBoardName, final String lastResult) {

    if (this.driverCustom.isElementPresent(
        "//li[@class='boardLinkContainer active']//span[contains(text(),'" + workBoardName + "')]", Duration.ofSeconds(15))) {
      return new TestAcceptanceMessage(true, "Verified Actual work board selected is - \n \"" + lastResult +
          "\".\n Expected work board is '" + workBoardName + "'");
    }
    return new TestAcceptanceMessage(false, "Verified Expected work board \"" + workBoardName +
        "\" not selected or \n Actual work board is - \"" + lastResult + "\".");
  }

  /**
   * <p>Verifies the action of {@link CCMQuickPlannerPage#clickOnWorkItemMenu(String)}.
   * 
   * @param menuName menu Name
   * @param lastResult expected value
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyClickOnWorkItemMenu(final String menuName, final String lastResult) {

    if (lastResult.contains(menuName)) {
      return new TestAcceptanceMessage(true, "Verified clicked on work item menu is - \"" + lastResult +
          "\".\n Expected worki item menu is '" + menuName + "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified not clicked on work item menu or \n Actual work item menu is - \"" + lastResult + "\".");
  }

}

