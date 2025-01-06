/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.ccm;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMPlanPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.dropdown.JazzDropdownFinder;

/**
 * @author UUM4KOR
 */
public class CCMPlanPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom driver.
   */
  public CCMPlanPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDropdownFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new RowCellFinder(), new JazzDialogFinder(), new JazzTabFinder(), new DropdownFinder());
  }

  /**
   * <p>
   * This method validate whether searched plan is displayed in the searched result
   *
   * <p>Verifies the action of {@link CCMPlanPage#searchBox(String)}.
   *
   * @param PlanName name
   * @param lastResult result
   * @return true
   */
  public TestAcceptanceMessage verifySearchBox(final String PlanName, final String lastResult) {
    waitForSecs(4);
    this.driverCustom.isElementVisible("//div[@class='search-page searchPage']", Duration.ofSeconds(15));
    this.driverCustom.isElementInvisible("//div[contains(@class,'status-message') and contains(text(),'Searching')]", timeInSecs);
    if (this.driverCustom.isElementVisible("//div/a[text()='" + PlanName + "']", Duration.ofSeconds(15))) {
      if (this.driverCustom.getWebElement("//div/a[text()='DYNAMIC_VAlUE']", PlanName).getText().equals(PlanName)) {
        return new TestAcceptanceMessage(true,
            "Verified expected plan searched and displayed in result section.\nExpected plan is- '" + PlanName + "'");
      }
      return new TestAcceptanceMessage(false,
          "Verified expected plan not searched and not displayed in result section.\nExpected plan is- '" + PlanName +
          "'");
    }
    return new TestAcceptanceMessage(false,
        "Expected plan not searched and not displayed in result section.\\nExpected plan is- '" + PlanName + "'");
  }

  /**
   * <p>Verifies the action of {@link CCMPlanPage#clickOnPlanPhase(String)}.
   *
   * @param planName name
   * @param lastResult result
   * @return true
   */
  public TestAcceptanceMessage verifyClickOnPlanPhase(final String planName, final String lastResult) {

    if (this.driverCustom.isElementVisible("//div[text()='" + planName + "']", Duration.ofSeconds(15))) {
      if (this.driverCustom.getWebElement("//div[text()='DYNAMIC_VAlUE']", planName).getText().equals(planName)) {
        return new TestAcceptanceMessage(true,
            "Verified expected plan clicked and opened successfully.\nExpected plan is- '" + planName + "'");
      }
      return new TestAcceptanceMessage(false,
          "Verified expected plan not searched and not opened successfully.\nExpected plan is- '" + planName + "'");
    }
    return new TestAcceptanceMessage(false,
        "Expected plan not searched and not opened successfully.\nExpected plan is- '" + planName + "'");
  }

  /**
   * <p>Verifies the action of {@link CCMPlanPage#viewAs(String)}.
   *
   * @param viewType viewType
   * @param lastResult not used
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyViewAs(final String viewType, final String lastResult) {
    if (this.driverCustom.isElementVisible(CCMConstants.CCMPLANPAGEVERIFICATION_LABEL_XPATH, Duration.ofSeconds(30))) {
      String actualValue = "";
      try {
        this.driverCustom.getPresenceOfWebElement("//select[@aria-label= 'View As:']");
        actualValue = this.driverCustom.getSelectedValueFromDropDown("//select[@aria-label= 'View As:']");
      }
      catch (Exception e) {
        LOGGER.LOG.error(e.getMessage());
      }
      if (actualValue.equals(viewType)) {
        return new TestAcceptanceMessage(true, "Verified expected View As '" + viewType +
            "' is set successfully. \n Actual state is - '" + actualValue + "'");
      }
      return new TestAcceptanceMessage(false, "Verified expected View As '" + viewType +
          "' is not set successfully.\n Actual state is - '" + actualValue + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Expected View As not visible and plan not opened successfully.\nExpected View As is- '" + viewType + "'");
  }

  /**
   *<p>Verifies the action of {@link CCMPlanPage#addWorkItemInsidePlan(String)}.
   *
   * @param witype work item type
   * @param lastResult not used
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyAddWorkItemInsidePlan(final String witype, final String lastResult) {
    if (this.driverCustom.isElementVisible("//div[@class='root  children  expanded']", Duration.ofSeconds(60))) {

      try {
        this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPLANPAGEVERIFICATION_NEWWORKITEMTYPE_ID_XPATH);
      }
      catch (Exception e) {
        LOGGER.LOG.error(e.getMessage());
      }
      if (this.driverCustom.isElementVisible("//div[@class='outliner']/a[text()='']/preceding::td[@title='Id']", Duration.ofSeconds(30))) {
        return new TestAcceptanceMessage(true, CCMConstants.CCMPLANPAGEVERIFICATION_WORKITEMTYPE_VERIFIED + witype +
            "' selected from 'Add Work Item' drop down successfully.");
      }
      else if (this.driverCustom.isElementVisible(CCMConstants.CCMPLANPAGEVERIFICATION_NEWWORKITEMTYPE_ID_XPATH, Duration.ofSeconds(30))) {
        return new TestAcceptanceMessage(true, CCMConstants.CCMPLANPAGEVERIFICATION_WORKITEMTYPE_VERIFIED + witype +
            "' selected from 'Add Work Item' drop down successfully.");
      }
      else if (this.driverCustom.isElementVisible("//div[@class='outliner']/input", Duration.ofSeconds(30))) {
        return new TestAcceptanceMessage(true, CCMConstants.CCMPLANPAGEVERIFICATION_WORKITEMTYPE_VERIFIED + witype +
            "' selected from 'Add Work Item' drop down successfully.");
      }
      return new TestAcceptanceMessage(false,
          CCMConstants.CCMPLANPAGEVERIFICATION_WORKITEMTYPE_VERIFIED + witype + "' not selected from 'Add Work Item' drop down successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Expected work item type not selected from 'Add Work Item' drop down \nor \n'Add Work Item' button not clicked successfully.");

  }

  /**
   * <P>
   * This method Verifies after clicking action menu drop down Dialog Plan Page loaded or not and Condition used to
   * check whether page is loaded
   * <p>Verifies the action of {@link CCMPlanPage#openDropdownFromLeftSideActionButtonOfPlanItemAndSelectMenu(String, String)}.
   *
   * @param summary name
   * @param menu name
   * @param lastResult name
   * @return name
   */
  public TestAcceptanceMessage verifyOpenDropdownFromLeftSideActionButtonOfPlanItemAndSelectMenu(final String summary,
      final String menu, final String lastResult) {
    // boolean value = this.driverCustom.getWebElements("//div[@class = 'header-primary']").stream()
    // .anyMatch(x -> x.getText().equals("Edit New Work Item"));
    waitForSecs(4);
    boolean value = false;
    try {
      value = this.driverCustom
          .getWebElements("//div[contains(@id,'jazz_ui_Dialog')]//*[contains(text(),'Edit New Work Item')]").stream()
          .anyMatch(x -> x.getText().equals("Edit New Work Item"));
    }
    catch (Exception e) {}

    if (value) {
      return new TestAcceptanceMessage(true, "Verified clicked on '" + menu + "' action menu drop down successfully");
    }
    return new TestAcceptanceMessage(false, "Verified '" + menu +
        "' Dialog Plan Page not loaded or The Condition used to check whether page is loaded or not general");
  }


  /**
   * <p>
   * Verifies the methods with Boolean value return such as 'work item' displayed under plan item and is 'work item'
   * displayed under plan item
   *
   * <p>Verifies the action of {@link CCMPlanPage#isWorkItemDisplayedUnderPlanItem(String)}.
   *
   * @param workItemName pass name of attribute.
   * @param lastResult get the boolean value.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyIsWorkItemDisplayedUnderPlanItem(final String workItemName,
      final String lastResult) {
    waitForSecs(4);
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified work item displayed under plan item. \n Expected Work item is '" + workItemName + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified work item not displayed under plan item. \n Expected Work item is '" + workItemName + "'.");
  }

  /**
   * <p>
   * This method verified after Select iteration and click on left side action button which opens drop down, Select work
   * Item type from the "Create Work item" menu. SubButtons[@param Task,Story....etc] Under respective plan name.
   *
   * <p>Verifies the action of {@link CCMPlanPage#selectWorkItemToCreateFromLeftSideActionButtonOfPlanItems(String, String)}.
   *
   * @param iteration is name of plan where to created work item.
   * @param workItemType is click on sub button(Task,Story....etc)
   * @param lastResult not used
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySelectWorkItemToCreateFromLeftSideActionButtonOfPlanItems(final String iteration,
      final String workItemType, final String lastResult) {
    if (this.driverCustom.isElementVisible("//div[@class='root  children  expanded']", Duration.ofSeconds(30))) {

      try {
        this.driverCustom.getPresenceOfWebElement("//div[@class='outliner']/a[text()='']/following::td[@title='Id']");
      }
      catch (Exception e) {
        LOGGER.LOG.error(e.getMessage());
      }
      if (this.driverCustom.isElementVisible("//div[@class='outliner']/a[text()='']/following::td[@title='Id']", Duration.ofSeconds(30))) {

        return new TestAcceptanceMessage(true,
            "Verified work item type '" + workItemType + "' selected from 'Add Work Item' drop down successfully.");
      }
      else if (this.driverCustom.isElementVisible("//div[@class='outliner']/input", Duration.ofSeconds(30))) {
        return new TestAcceptanceMessage(true,
            "Verified work item type '" + workItemType + "' selected from 'Add Work Item' drop down successfully.");
      }
      return new TestAcceptanceMessage(false,
          "Verified work item type '" + workItemType + "' not selected from 'Add Work Item' drop down successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Expected work item type not selected from 'Add Work Item' drop down \nor \n'Add Work Item' button not clicked successfully.\nEXpected work iten is - '" +
            workItemType + "'");
  }

  /**
   * <p>
   * This method verifies after enter text in to 'Type to Filter' filter search weather searched item displayed in
   * result section.
   * <p>Verifies the action of {@link CCMPlanPage#filterItems(String)}.
   * @author VDY1HC
   * @param filterItem name
   * @param lastResult name
   * @return name
   */
  public TestAcceptanceMessage verifyFilterItems(final String filterItem, final String lastResult) {
    waitForSecs(4);
    this.driverCustom.isElementVisible("//input[@placeholder='Type to Filter']", Duration.ofSeconds(30));
    // Work Item not found / not 1st level - need to expand
    if (!this.driverCustom.isElementVisible("//div/a[text()='" + filterItem + "']", Duration.ofSeconds(20))) {
      List<WebElement> btnExpands = this.driverCustom.getVisibleWebElements("//div[contains(@class,' children') and contains(@class,'collapsed') and contains(@class,'entry')]//span[@title='+/-']");
      if (!btnExpands.isEmpty()) {
        for (WebElement btnExpand : btnExpands) {
          btnExpand.click();
        }
      }
    }
    if (this.driverCustom.isElementVisible("//div/a[text()='" + filterItem + "']", timeInSecs)) {
      return new TestAcceptanceMessage(true,
          "Verified expected filter item searched and displayed in result section.\nExpected filter item is- '" +
              filterItem + "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified expected filter item not searched and not displayed in result section.\nExpected filter item is- '" +
            filterItem + "'");
  }

  /**
   * Verify the action of {@link CCMPlanPage#clickOnActionInPlanPhase(String, String)}. <br>
   * @author VDY1HC
   * @param wiName - name of work item
   * @param subbuttons - sub-menu
   * @param lastResult - lastresult
   * @return validation message.
   */
  public TestAcceptanceMessage verifyClickOnActionInPlanPhase (final String wiName, final String subbuttons, final String lastResult) {
    boolean result = false;
    String[] buttons = subbuttons.split(" > ");
    String menuLevel1 = buttons[0];
    String menuLevel2 = "";
    if (buttons.length > 1) {
      menuLevel2 = buttons[1];
    }
    switch (menuLevel1)
    {
      case "Edit Work Item":
        // Check for present dialog: Edit 'wiName'...
        String dialogTitle = "Edit '" + wiName + "'...";
        result = this.driverCustom.isElementPresent(CCMConstants.DIALOG_TITLE_XPATH, Duration.ofSeconds(60), dialogTitle);
        break;
      case "Refresh Work Item":
        // Check for reload message
        result = this.driverCustom.isElementVisible(CCMConstants.CCMPLANPAGE_LOADING_MESSAGE_XPATH, Duration.ofSeconds(60));
        break;
      case "Create Work Item":
      case "Create Child Work Item":
        if (menuLevel2.isEmpty()) {
          // Check for submenu to be displayed
          List<WebElement> submenu = this.driverCustom.getWebElements(CCMConstants.CONTEXT_MENUS_XPATH);
          result = (submenu.size() == 2);
        }
        else {
          // Check for new row to be displayed
          result = this.driverCustom.isElementVisible(CCMConstants.CCMPLANPAGE_NEWROW_XPATH, this.timeInSecs);
        }
        break;
      default:
        throw new WebAutomationException("Sub-menu with value: " + subbuttons + " is NOT handled for verification method in automation script");
    }
    if (result) {
      return new TestAcceptanceMessage(result, "Verified: PASSED - Selected action menu for " + wiName + " with submenus: " + subbuttons + " successfully.");
    }
    return new TestAcceptanceMessage(result, "Verified: FAILED - Unable to selected action menu for " + wiName + " with submenus: " + subbuttons);
  }

  /**
   * Verify the action of {@link CCMPlanPage#inputDataForNewWorkItemRow(Map)}. <br>
   * @author VDY1HC
   * @param additionalParams - map
   * @param lastResult - lastresult
   * @return validation message.
   */
  public TestAcceptanceMessage verifyInputDataForNewWorkItemRow (final Map<String,String> additionalParams, final String lastResult) {
    String attributeName = additionalParams.get("ATTRIBUTE_NAME");
    String attributeValue = additionalParams.get("ATTRIBUTE_VALUE");
    if (attributeName.equalsIgnoreCase("Summary")) {
      attributeName = "summary";
    }
    WebElement newInputText = this.driverCustom.getWebElement(CCMConstants.CCMPLANPAGE_NEWROW_TEXTBOX_XPATH, new String[] { attributeName, attributeValue });
    boolean isNewRowWithInputTextVisible = this.driverCustom.isElementVisible(newInputText, Duration.ofSeconds(60));
    if (isNewRowWithInputTextVisible) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Input data to new row with text successfully." +
          "\nAttribute name: " + attributeName +
          "\nAttribute value: " + attributeValue);
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Unable to input data to new row with text." +
        "\nAttribute name: " + attributeName +
        "\nAttribute value: " + attributeValue);
  }
  
  /**
   * @param WI used to verify the WI name in WI list of view 
   * @param WInum used to verify the number of WI loaded
   */
  public TestAcceptanceMessage verifyWIUnderView(final String WInum, final String WI) {
    waitForSecs(5);
    
    WebElement ele = this.driverCustom.getFirstVisibleWebElement("//div[@dojoattachpoint='ftDetails']//div[1][contains(@class,'detail')]//div[contains(@class,'label')]", null);
    String WIcount = ele.getText();
    
    if(WIcount.compareTo(WInum)==0 && this.driverCustom.isElementVisible("//a[text()='DYNAMIC_VAlUE']", Duration.ofSeconds(30), WI)) {
      LOGGER.LOG.info(WInum + " are loaded successfully.");
      return new TestAcceptanceMessage(true, "Verified: the WI are loaded successfully.");
    }
    else {
      LOGGER.LOG.info(WInum + " are not loaded successfully.");
      return new TestAcceptanceMessage(false, "Verified: the WI are not loaded successfully.");
    }
    
  }
  
  /**
   * 
   */
  public TestAcceptanceMessage verifyViewLoad() {
    waitForSecs(5);

    if(!this.driverCustom.isElementPresent("//div[@class='messageSummary']", Duration.ofSeconds(10))) {
      LOGGER.LOG.info("The view is loaded successfully.");
      return new TestAcceptanceMessage(true, "Verified: the view is loaded successfully.");
    }
    else
    {
      LOGGER.LOG.info("The view is not loaded successfully.");
      return new TestAcceptanceMessage(false, "Verified: the view is not loaded successfully.");
    }
    
  }
}
