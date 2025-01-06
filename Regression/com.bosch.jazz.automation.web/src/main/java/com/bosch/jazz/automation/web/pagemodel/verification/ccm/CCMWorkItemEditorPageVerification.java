/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.ccm;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMWorkItemEditorPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.dropdown.JazzDropdownFinder;

/**
 * @author UUM4KOR
 */
public class CCMWorkItemEditorPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom must not be null.
   */
  public CCMWorkItemEditorPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDropdownFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new RowCellFinder(), new JazzDialogFinder(), new JazzTabFinder());
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#getStatus()}.
   *
   * @param additionalParam is expected value to be verified against actual value.
   * @param lastResult get the last result of {@link CCMWorkItemEditorPage#getStatus()}
   * @return validation message
   */
  public TestAcceptanceMessage verifyGetStatus(final String additionalParam, final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalParam),
          "Current state of the work item is '" + lastResult + "' From status drop down.");
    }
    return new TestAcceptanceMessage(false,
        "Current state of the work item is '" + lastResult + "' From status drop down.");


  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#getResolution()}.
   *
   * @param additionalParam is expected value to be verified against actual value.
   * @param lastResult get the last result of {@link CCMWorkItemEditorPage#getResolution()}
   * @return validation message
   */
  public TestAcceptanceMessage verifyGetResolution(final String additionalParam, final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalParam),
          "Current resolution of the work item is \"" + lastResult + "\" From status drop down.");
    }
    return new TestAcceptanceMessage(false,
        "Current resolution of the work item is \"" + lastResult + "\" From status drop down.");
  }


  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#clickOnAddApproval(String)}.
   *
   * @param dropdown option of the drop down.
   * @param lastResult returned value of method which is executed just before the method.
   * @return return true if button "Add Approvers..." return after selecting Add Approval value in drop down
   */
  public TestAcceptanceMessage verifyClickOnAddApproval(final String dropdown, final String lastResult) {
    Button btnAddApproval = null;
    try {
      btnAddApproval = this.engine.findElement(Criteria.isButton().withText("Add Approvers...")).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (btnAddApproval != null) {
      return new TestAcceptanceMessage(true,
          "Verified 'Add Approvers...' button is displayed, after clicking '" + dropdown + "' drop down.");
    }
    return new TestAcceptanceMessage(false,
        "Verified 'Add Approvers...' button is not displayed, after clicking '" + dropdown + "' drop down.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#addApprovers(String)}.
   *
   * @param user : the name of approver
   * @param lastResult returned value of method which is executed just before the method.
   * @return return true if row contains user name found after adding approval in dialog
   */
  public TestAcceptanceMessage verifyAddApprovers(final String user, final String lastResult) {
    Row rowApprover = null;
    try {
      rowApprover =
          this.engine.findElementWithDuration(Criteria.isRow().withText(user), this.timeInSecs).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if ((rowApprover != null) && lastResult.equalsIgnoreCase(user + ": Approver added successfully.")) {
      return new TestAcceptanceMessage(true,
          "Verified user add by checking the 'Select User' dialog.\n Actual result is -'" + user +

              "'  added and dialog closed.");

    }
    else if (lastResult.equalsIgnoreCase(user + ": Approver can't be selected (Approver is disabled)")) {

      return new TestAcceptanceMessage(true,
          "Verified same user added second time by checking the 'Select User' dialog . \n Actual result is '" + user +
              "' not added and dialog not closed.");
    }
    return new TestAcceptanceMessage(false,
        "Verified user add by checking the 'Select User' dialog . \n Actual result is '" + user +
            "' not added and dialog not closed.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#setDueDate(String, String)}.
   *
   * @param approvalType is the type of approval attribute.
   * @param date Input Due Date of Review.
   * @param lastResult returned value of method which is executed just before the method.
   * @return return true if row contains date is showing after setting date.
   */
  public TestAcceptanceMessage verifySetDueDate(final String approvalType, final String date, final String lastResult) {
    String getDate = "";
    String xpathDueDate =
        "(//span[contains(@class ,'SectionMenuAction_Enabled')][text()='%s']/ancestor::tr[@class='SectionListTableRow']/td[@class='SectionListTableColumn'][.//div[@class='DatePicker']]//input)[last()]";
    try {
      Row rowApproval = this.engine.findElement(Criteria.isRow().withText(approvalType)).getFirstElement();
      Cell cllDueDate1 =
          this.engine.findElement(Criteria.isCell().inContainer(rowApproval).withIndex(4)).getFirstElement();
      Text txtDueDate1 = this.engine.findFirstElement(Criteria.isTextField().inContainer(cllDueDate1));
      getDate = txtDueDate1.getWebElement().getAttribute("value").trim();
    }
    catch (Exception e) {
      try {
        // handle on 05Q server
        this.driverCustom.isElementVisible(String.format(xpathDueDate, approvalType), Duration.ofSeconds(5));
        getDate = this.driverCustom.getWebDriver().findElement(By.xpath(String.format(xpathDueDate, approvalType))).getAttribute("value").trim();
      }
      catch (Exception e1) {
        LOGGER.LOG.error(e1.getMessage()); 
      }
    }
    // Sometimes set due data successfully but engine get it empty, we will try to find it by selenium to make sure value is not empty.
    if(getDate.isEmpty()) {
      try {
        this.driverCustom.isElementVisible(String.format(xpathDueDate, approvalType), Duration.ofSeconds(5) );
        getDate = this.driverCustom.getWebDriver().findElement(By.xpath(String.format(xpathDueDate, approvalType))).getAttribute("value").trim();
      }
      catch (Exception e) {
        LOGGER.LOG.info("Can not get due date - actual: " +getDate);
      }
    }
    if (!getDate.isEmpty() && getDate.equals(date)) {
      return new TestAcceptanceMessage(true, "Verified actual date '" + getDate + "' displayed in '" + approvalType +
          "'  and \n expected date  is -'" + date + "' added to '" + approvalType + "'.");
    }
    return new TestAcceptanceMessage(false, "Verified actual date '" + getDate + "' displayed in '" + approvalType +
        "'  and \n expected date  is -'" + date + "' not added to '" + approvalType + "'.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#selectTab(String)}.
   *
   * @param tabName the name of Tab
   * @param lastResult returned value of method which is executed just before the method.
   * @return return true if row contains user name found after adding approval in dialog
   */
  public TestAcceptanceMessage verifySelectTab(final String tabName, final String lastResult) {

    Boolean isSelected = false;
    try {
      List<Tab> tabLinks = this.engine.findElement(Criteria.isTab().withText(tabName)).getElementList();
      isSelected =
          Boolean.valueOf(tabLinks.get(tabLinks.size() - 1).getWebElement().getAttribute("aria-selected").trim());
      if (isSelected) {
        return new TestAcceptanceMessage(true, "Verified  tab '" + tabName +
            "' is highlighted with blue line under the text. \n Expected tab is -'" + tabName + "'.");
      }
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return new TestAcceptanceMessage(false, "Verified tab is '" + tabName +
        "' is not highlighted with blue line under the text.\n Expected tab is -'" + tabName + "'.");
  }


  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#saveWorkItem()}.
   *
   * @param additionalParam is expected value to be verified against actual value.
   * @param lastResult returned value of method which is executed just before the method.
   * @return return true if Save button is disable return after clicking Save button
   */
  public TestAcceptanceMessage verifySaveWorkItem(final String additionalParam, final String lastResult) {
    waitForSecs(2);
    List<WebElement> elements = this.driverCustom.getWebElements("//button[text() = 'Save']");
    boolean isClicked =
        elements.size() >= 1 ? elements.get(elements.size() - 1).getAttribute(CCMConstants.DISABLED) != null : false;
    if (isClicked) {

      return new TestAcceptanceMessage(Boolean.valueOf(additionalParam),
          "Verified save button is 'disabled' hence Work item is saved successfully.");
    }

    else if (!Boolean.valueOf(additionalParam)) {
      return new TestAcceptanceMessage(true, "Verified save button is 'enabled' hence Work item is not saved.");
    }
    return new TestAcceptanceMessage(false,
        "Verified save button is 'not disabled' hence Work item is not saved successfully.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#notSaveWorkItem()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return return true if Save button is disable return after clicking Save button
   */
  public TestAcceptanceMessage verifyNotSaveWorkItem(final String lastResult) {
    waitForSecs(4);
    Boolean isClicked = this.driverCustom.getWebElements("//button[text() = 'Save']").stream()
        .anyMatch(x -> x.getAttribute("disabled") == null);
    if (isClicked) {
      return new TestAcceptanceMessage(true,
          "Verified save button is 'not disabled' hence Work item is not saved successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verified save button is 'disabled' hence Work item is saved successfully.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#deleteApproval(String)}.
   *
   * @param approvalType type of approval.
   * @param lastResult returned value of method which is executed just before the method.
   * @return return true if Save button is disable return after clicking Save button
   */
  public TestAcceptanceMessage verifyDeleteApproval(final String approvalType, final String lastResult) {
    Button btnRestoreApprover = null;
    try {
      btnRestoreApprover =
          this.engine.findElement(Criteria.isButton().withToolTip("Restore this deleted approval")).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (btnRestoreApprover != null) {
      return new TestAcceptanceMessage(true,
          "Verified 'Restore this deleted approval' button is displayed after deleting approval from the approval type - '" +
              approvalType + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified 'Restore this deleted approval' button is not displayed after deleting approval from the approval type - '" +
            approvalType + "'.");
  }

  /**
   * <P>
   * Verifies the methods with Boolean value return such as save Work item and isWorkItemSaved.
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isApprovarUserAdded(String, String)}.
   *
   * @param approvalType type of the approval.
   * @param user name of the user.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsApprovarUserAdded(final String approvalType, final String user,
      final String lastResult) {
    return verifyIsApprovarUserAdded(approvalType, user, "TRUE", lastResult);
  }

  /**
   * <P>
   * Verifies the methods with Boolean value return such as save Work item and isWorkItemSaved.
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isApprovarUserAdded(String, String)}.
   *
   * @param approvalType type of the approval.
   * @param user name of the user.
   * @param additionalParam true if Approvar is added successfully.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsApprovarUserAdded(final String approvalType, final String user,
      final String additionalParam, final String lastResult) {
    if (Boolean.valueOf(additionalParam) && Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified actual user '" + user + "' Is added as '" + approvalType +
          "' and expected '" + user + "' Is added as '" + approvalType + "' displayed in approval section.");
    }
    else if (!Boolean.valueOf(additionalParam) && !Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified actual user '" + user + "' Is NOT added as '" + approvalType +
          "' and expected '" + user + "' Is NOT added as '" + approvalType + "' displayed in approval section.");
    }
    else {
      return new TestAcceptanceMessage(false, "Verified actual '" + user + "' is added as '" + approvalType +
          "' and expected '" + user + "' is added as '" + approvalType + "' not displayed in approval section.");
    }
  }

  /**
   * <P>
   * Verifies the methods with Boolean value return such as saveWorkItem and isWorkItemSaved, isXXX ...
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isDueDateAdded(String, String)}.
   *
   * @param approvalType type of the approval.
   * @param date for the approval.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsDueDateAdded(final String approvalType, final String date,
      final String lastResult) {
    return verifyIsDueDateAdded(approvalType, date, "TRUE", lastResult);
  }

  /**
   * <P>
   * Verifies the methods with Boolean value return such as saveWorkItem and isWorkItemSaved, isXXX ...
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isDueDateAdded(String, String)}.
   *
   * @param approvalType type of the approval.
   * @param date for the approval.
   * @param additionalParam true if Due Date is added successfully.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsDueDateAdded(final String approvalType, final String date,
      final String additionalParam, final String lastResult) {
    if (Boolean.valueOf(additionalParam) && Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified actual result '" + lastResult +
          "' with expected expected result '" + date + "'  Approver is added as '" + approvalType + "' successfully.");
    }
    else if (!Boolean.valueOf(additionalParam) && !Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified actual result '" + lastResult +
          "' with expected expected result '" + date + "'  Approver is NOT added as '" + approvalType + "'.");
    }
    else {
      return new TestAcceptanceMessage(false, "Verified actual result '" + lastResult +
          "' with expected expected result '" + date + "'  Approver is added as '" + approvalType + "' successfully.");
    }
  }

  /**
   * <P>
   * Verifies the methods with Boolean value return such as saveWorkItem and isWorkItemSaved, isXXX ...
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#setDropDownAttributeValue(String, String)}.
   *
   * @param dropDownAttributeName name of the drop down attribute.
   * @param attributeValue value of the attribute.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetDropDownAttributeValue(final String dropDownAttributeName,
      final String attributeValue, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Expected '" + dropDownAttributeName + "' value is -'" + attributeValue + "' is selected successfuly in '" +
              dropDownAttributeName + "' dropdown. Actual last resut is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Expected '" + dropDownAttributeName + "' value is -'" + attributeValue + "' is NOT selected successfuly in '" +
            dropDownAttributeName + "' dropdown. Actual last resut is '" + lastResult + "'.");
  }

  /**
   * <P>
   * Verifies the methods with Boolean value return such as saveWorkItem and isWorkItemSaved, isXXX ...
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#setResolution(String)}.
   *
   * @param resolution of the work item.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetResolution(final String resolution, final String lastResult) {

    String actualValue = "";
    try {
      Dropdown drdTaskType = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Resolution"));
      actualValue = drdTaskType.getDefaultValue();
    }
    catch (Exception e) {
      try {
        actualValue = this.driverCustom.getText(CCMConstants.CCMWORKITEMEDITORPAGE_DETAILEDSTATUS_XPATH);
      }
      catch (Exception e1) {
        LOGGER.LOG.error(e.getMessage());
      }
    }
    if (actualValue.equals(resolution)) {
      return new TestAcceptanceMessage(true, "Expected resolution -'" + resolution +
          " is set successfully.\nActual resolution is - '" + actualValue + "'.");
    }
    return new TestAcceptanceMessage(false, "Expected resolution -'" + resolution +
        "is not set successfully. \nActual resolution is - '" + actualValue + "'.");
  }

  /**
   * <P>
   * Verifies the methods with Boolean value return such as saveWorkItem and isWorkItemSaved, isXXX ...
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#setStatus(String)}.
   *
   * @param state state of the work item.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetStatus(final String state, final String lastResult) {
    String actualValue = "";
    try {
      Dropdown drdStatus = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Status"));
      actualValue = drdStatus.getDefaultValue();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (actualValue.equals(state)) {
      return new TestAcceptanceMessage(true,
          "Verified expected state '" + state + "' is set successfully. \n Actual state is - '" + actualValue + "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified expected state '" + state + "is not set successfully.\n Actual state is - '" + actualValue + "'.");
  }


  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isAttributeMandatory(String)}.
   *
   * @param attributeName pass name of attribute.
   * @param additionalParam is expected value to be verified against actual value.
   * @param lastResult get the boolean value.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyIsAttributeMandatory(final String attributeName, final String additionalParam,
      final String lastResult) {
    waitForSecs(4);
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(Boolean.valueOf(additionalParam),
          "Verified attribute with star(*) label.\nAttribute '" + attributeName + "' Is mandatory.");
    }
    else if (!Boolean.valueOf(additionalParam)) {
      return new TestAcceptanceMessage(true,
          "Verified attribute with star(*) label.\n Attribute '" + attributeName + "' Is not mandatory.");
    }

    return new TestAcceptanceMessage(false,
        "Verified attribute with star(*) label.\n Attribute '" + attributeName + "' is not mandatory.");

  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isAttributeNotMandatory(String)}.
   *
   * @param attributeName pass name of attribute.
   * @param lastResult get the boolean value.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyIsAttributeNotMandatory(final String attributeName, final String lastResult) {
    waitForSecs(4);

    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified attribute with star(*) label. \n Attribute '" + attributeName + "' is not mandatory.");
    }
    return new TestAcceptanceMessage(false,
        "Verified attribute with star(*) label. \n Attribute '" + attributeName + "' is mandatory.");

  }


  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#getDropDownValue(String)}.
   *
   * @param attributeName pass attribute Name
   * @param additionalParam is expected value to be verified against actual value.
   * @param lastResult last Result.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyGetDropDownValue(final String attributeName, final String additionalParam,
      final String lastResult) {
    waitForSecs(20);
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.contains(additionalParam),
          "Actual value of \"" + attributeName + "\" is \"" + lastResult + "\" from attribute drop down.");
    }
    return new TestAcceptanceMessage(false,
        "Actual value of \"" + attributeName + "\" is \"" + lastResult + "\" from attribute drop down.");


  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#getValidationMessageFromNotificationArea()}.
   *
   * @param additionalParam is expected value to be verified against actual value.
   * @param lastResult get last result of getValidationMessageFromNotificationArea(String)}}.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyGetValidationMessageFromNotificationArea(final String additionalParam,
      final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.contains(additionalParam),
          "Actual message displayed in notification area is - \n \"" + lastResult + "\".");
    }
    return new TestAcceptanceMessage(false,
        "Actual value message not displayed in notification area or \n Actual message not displayed in notification area is - \"" +
            lastResult + "\".");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#addWorkItemFromNotificationAreaLink(String)}.
   *
   * @param workitemID pass work item id.
   * @param lastResult last Result.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyAddWorkItemFromNotificationAreaLink(final String workitemID,
      final String lastResult) {
    waitForSecs(4);
    WebElement lnkPreviousProcessLink =
        this.driverCustom.getWebElement(("//a[contains(.,'" + workitemID + "')]//ancestor::span"));
    if (this.driverCustom.isElementVisible("//a[@class='closeButton']", Duration.ofSeconds(4))) {
      this.driverCustom.getFirstVisibleWebElement("//a[@class='closeButton']", null).click();
    }
    if (lnkPreviousProcessLink.isDisplayed()) {
      return new TestAcceptanceMessage(true,
          "Work item searched from 'Select Work Item' dialog and saved successfully. \n Verified added work item \"" +
              workitemID + "\" displayed in 'Links' section");
    }
    return new TestAcceptanceMessage(false,
        "Verified added work item \"" + workitemID + "\" not displayed in 'Links' section");
  }

  /**
   * <p>
   * Verify existing link is added in 'Links' section of the work item.
   * <p>
   * This method called after {@link CCMWorkItemEditorPage#addLinkToExistingObject(Map)}.
   *
   * @param additionalParam contains list of key value pair data.
   * @param additionalParams is expected value to be verified against actual value.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyAddLinkToExistingObject(final Map<String, String> additionalParams,
      final String additionalParam, final String lastResult) {
    switch (additionalParams.get(CCMConstants.LINKACTIONS)) {
      case "Add Tested By Test Case":
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        List<String> allLinks =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allLinks != null) && allLinks.stream()
            .anyMatch(x -> (x != null) && x.startsWith(additionalParams.get(CCMConstants.LINKTYPE_ID)))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Tested By Test Case' link is added in 'Links' section of the Work Items.\n Actual result \"" +
                  additionalParams.get(CCMConstants.LINKTYPE_ID) + "' is added in 'Links' section. \n" +
                  CCMConstants.EXPECTED_RESULT + additionalParams.get(CCMConstants.LINKTYPE_ID) +
                  "' should be added in 'Links' section. ");
        }
        return new TestAcceptanceMessage(false,
            "'Add Tested By Test Case' link not added in 'Links' section of the Work Item.");

      case "Add Related Test Plan":
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        List<String> allTestPlanLinks =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allTestPlanLinks != null) && allTestPlanLinks.stream()
            .anyMatch(x -> (x != null) && x.startsWith(additionalParams.get(CCMConstants.LINKTYPE_ID)))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Related Test Plan' link is added in 'Links' section of the Work Items.\nActual result '" +
                  additionalParams.get(CCMConstants.LINKTYPE_ID) + "' Is added in 'Links' section.\n" +
                  " Expected Result '" + additionalParams.get(CCMConstants.LINKTYPE_ID) +
                  "' Should be added in 'Links' section. ");
        }
        return new TestAcceptanceMessage(false,
            "'Add Related Test Plan' link not added in 'Links' section of the Work Item.");

      case "Add Related Test Case":
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        List<String> allTestCaseLinks =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allTestCaseLinks != null) && allTestCaseLinks.stream()
            .anyMatch(x -> (x != null) && x.startsWith(additionalParams.get(CCMConstants.LINKTYPE_ID)))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Add Related Test Case' link is added in 'Links' section of the Work Items." +
                  "\nActual result '" + additionalParams.get(CCMConstants.LINKTYPE_ID) +
                  "' is added in 'Links' section.\n" + "Expected Result '" +
                  additionalParams.get(CCMConstants.LINKTYPE_ID) + "' Should be added in 'Links' section. ");
        }
        return new TestAcceptanceMessage(false,
            "'Add Related Test Case' link not added in 'Links' section of the Work Item.");

      case "Add Children":
        Select sel = new Select(this.driverCustom.getWebElement("//select[@class='QueryResults']"));
        if (sel.getOptions().stream()
            .anyMatch(x -> x.getText().startsWith(additionalParams.get(CCMConstants.LINKTYPE_ID) + ":") &&
                (x.getAttribute("disabled") != null))) {
          return new TestAcceptanceMessage(true,
              "Verified:Searched Work Item is the parent Work Item.\nActual result \"" +
                  additionalParams.get(CCMConstants.LINKTYPE_ID) +
                  "\" Trying to add which is same with Parent work item." + "\nExpected result \"" +
                  additionalParams.get(CCMConstants.LINKTYPE_ID) + "\" Should be same with Parent work item");
        }
        return new TestAcceptanceMessage(false, "Verified: Searched Work Item is not the parent Work Item.");

      case "Add Contributes To":
        this.driverCustom.switchToDefaultContent();
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        String addContributesToworkItemID = additionalParams.get(CCMConstants.LINKTYPE_ID);
        List<String> allLinks5 =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allLinks5 != null) && allLinks5.stream()
            .anyMatch(x -> (x != null) && x.startsWith(additionalParams.get(CCMConstants.LINKTYPE_ID)))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Add Contributes To' link is added in 'Links' section of the Work Items." +
                  "\nActual result '" + addContributesToworkItemID + "' Is added in 'Links' section.\n" + "Expected Result '" +
                  addContributesToworkItemID + "' Should be added in 'Links' section.");
        }
        return new TestAcceptanceMessage(false, "'Add Contributes To' link not added in 'Links' section of the Work Item.");
      case "Add Tracks":
        waitForSecs(4);
        this.driverCustom.switchToDefaultContent();
        String workItemID = additionalParams.get(CCMConstants.LINKTYPE_ID);
        String xPathTrackLinks = String.format("//span[@aria-label='"+additionalParams.get(CCMConstants.LINK_SECTION)+"']/ancestor::div[@dojoattachpoint='innerBorderDIV']/descendant::a[contains(text(),'%s')]", workItemID);
        if (this.driverCustom.isElementVisible(xPathTrackLinks, Duration.ofSeconds(30))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Add Tracks' link is added in 'Links' section of the Work Items." +
                  "\nActual result '" + workItemID + "' Is added in 'Links' section.\n" + "Expected Result '" +
                  workItemID + "' Should be added in 'Links' section.");
        }
        return new TestAcceptanceMessage(false, "'Add Tracks' link not added in 'Links' section of the Work Item.");
      case "Set Parent":
        waitForSecs(2);
        this.driverCustom.switchToDefaultContent();
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        List<String> allLinks1 =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allLinks1 != null) && allLinks1.stream()
            .anyMatch(x -> (x != null) && x.startsWith(additionalParams.get(CCMConstants.LINKTYPE_ID)))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Set Parent' link is added in 'Links' section of the Work Items." + " \n Actual result \"" +
                  additionalParams.get(CCMConstants.LINKTYPE_ID) + "\" is added in 'Links' section.\n " +
                  " Expected Result '" + additionalParams.get(CCMConstants.LINKTYPE_ID) +
                  "' Should be added in 'Links' section.");
        }
        return new TestAcceptanceMessage(false, "'Set Parent' link not added in 'Links' section of the Work Item.");

      case "Add Tracks Requirement":
        waitForSecs(2);
        this.driverCustom.switchToDefaultContent();
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        List<String> allTracksLinks =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allTracksLinks != null) && allTracksLinks.stream()
            .anyMatch(x -> (x != null) && x.startsWith(additionalParams.get(CCMConstants.LINKTYPE_ID)))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Add Tracks Requirement' link is added in 'Links' section of the Work Items." +
                  CCMConstants.ACTUAL_RESULT + additionalParams.get(CCMConstants.LINKTYPE_ID) +
                  CCMConstants.IS_ADDED_IN_LINKS_SECTION + " Expected Result \" " +
                  additionalParams.get(CCMConstants.LINKTYPE_ID) + CCMConstants.SHOULD_BE_ADDED_IN_LINKS_SECTION);
        }
        return new TestAcceptanceMessage(false,
            "'Add Tracks Requirement' link not added in 'Links' section of the Work Item.");
        
      case "Set Duplicate Of":
        waitForSecs(2);
        this.driverCustom.switchToDefaultContent();
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        List<String> allDuplicateOfLinks =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allDuplicateOfLinks != null) && allDuplicateOfLinks.stream()
            .anyMatch(x -> (x != null) && x.startsWith(additionalParams.get(CCMConstants.DUPLICATE_OF_NAME)))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Set Duplicate Of' link is added in 'Links' section of the Work Items." +
                  CCMConstants.ACTUAL_RESULT + additionalParams.get(CCMConstants.DUPLICATE_OF_NAME) +
                  CCMConstants.IS_ADDED_IN_LINKS_SECTION + " Expected Result \" " +
                  additionalParams.get(CCMConstants.DUPLICATE_OF_NAME) + CCMConstants.SHOULD_BE_ADDED_IN_LINKS_SECTION);
        }
        return new TestAcceptanceMessage(false,
            "'Set Duplicate Of' link not added in 'Links' section of the Work Item.");

      case "Add Related":
        waitForSecs(2);
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        List<String> allrelatedLinks =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allrelatedLinks != null) && allrelatedLinks.stream()
            .anyMatch(x -> (x != null) && x.startsWith(additionalParams.get(CCMConstants.LINKTYPE_ID)))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Add Related' link is added in 'Links' section of the Work Items." + "\n Actual result \"" +
                  additionalParams.get(CCMConstants.LINKTYPE_ID) + "\" is added in 'Links' section.\n" +
                  " Expected Result \"" + additionalParams.get(CCMConstants.LINKTYPE_ID) +
                  "\" Should be added in 'Links' section.");
        }
        return new TestAcceptanceMessage(false, "'Add Related' link not added in 'Links' section of the Work Item.");

      case "Add Affects Test Case Result":
        waitForSecs(2);
        this.driverCustom.switchToDefaultContent();
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        List<String> allLinks2 =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allLinks2 != null) && allLinks2.stream()
            .anyMatch(x -> (x != null) && x.startsWith(additionalParams.get(CCMConstants.LINKTYPE_ID)))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Add Tracks Requirement' link is added in 'Links' section of the Work Items." +
                  "\n Actual result \"" + additionalParams.get(CCMConstants.LINKTYPE_ID) +
                  "\" is added in 'Links' section.\n" + " Expected Result \" " +
                  additionalParams.get(CCMConstants.LINKTYPE_ID) + "\" Should be added in 'Links' section.");
        }
        return new TestAcceptanceMessage(false,
            "'Add Affects Test Case Result' link not added in 'Links' section of the Work Item.");

      default:
        return new TestAcceptanceMessage(false, "Link is not added any of the Links section.");
    }
  }

  /**
   * <p>
   * Verify test case is added in Links section of the work item.
   * <p>
   * This method called after {@link CCMWorkItemEditorPage#isTestArtifactAddedInLinksSection(String)}.
   *
   * @param testCaseName name of the test case.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsTestArtifactAddedInLinksSection(final String testCaseName, final String lastResult) {
    return verifyIsTestArtifactAddedInLinksSection(testCaseName, "TRUE", lastResult);
  }

  /**
   * <p>
   * Verify test case is added in Links section of the work item.
   * <p>
   * This method called after {@link CCMWorkItemEditorPage#isTestArtifactAddedInLinksSection(String)}.
   *
   * @param testCaseName name of the test case.
   * @param additionalParam true if test case is added successfully.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsTestArtifactAddedInLinksSection(final String testCaseName,
      final String additionalParam, final String lastResult) {
    waitForSecs(8);
    if (Boolean.valueOf(additionalParam) && Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: Links added in 'Links' section of the Work Item.\nActual Result \"" + testCaseName + "\" " +
              "is visible in 'Links' section.\nExpected Result \"" + testCaseName +
              "\" should be visible in 'Links' section.");
    }
    else if (!Boolean.valueOf(additionalParam) && !Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: Links NOT added in 'Links' section of the Work Item.\nActual Result \"" + testCaseName + "\" " +
              "is NOT visible in 'Links' section.\nExpected Result \"" + testCaseName +
              "\" should NOT be visible in 'Links' section.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Links is not added in 'Links' section of the Work Item.\nActual Result \"" + testCaseName +
            "\" is not added " + "in 'Links' section of the Work Item.\nExpected Result \"" + testCaseName +
            "\" should be visible in 'Links' section.");
  }

  /**
   * <p>
   * Verify clicked on the link from Links section of the work item.
   * <p>
   * This method called after {@link CCMWorkItemEditorPage#clickOnLinkFromWorkItemLinksSection(String, String, String)}.
   *
   * @param linkSection link section as 'Process Links','Links'.
   * @param linkId id of the link.
   * @param linkType type of link.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnLinkFromWorkItemLinksSection(final String linkSection, final String linkId,
      final String linkType, final String lastResult) {
    waitForSecs(5);
    boolean isLinkListFound = this.driverCustom.isElementVisible(
        "//div[@dojoattachpoint='titleOutterContainerNode']//span[contains(@dojoattachpoint,'view')]", Duration.ofSeconds(25));
    if (!isLinkListFound && this.driverCustom.isTitleContains(linkId, Duration.ofSeconds(25))) {
      return new TestAcceptanceMessage(true,
          "Verified: Navigated to target pages after clicking link from the Work Item page." +
              this.driverCustom.getWebDriver().getTitle() + ".\"\nActual title contains Expected text '" + linkId +
              "'.");

    }
    try {
      List<WebElement> results = this.driverCustom.getWebElements(
          "//div[@dojoattachpoint='titleOutterContainerNode']//span[contains(@dojoattachpoint,'view')]");


      for (WebElement artifact : results) {
        if (linkId.contains(artifact.getText())) {
          return new TestAcceptanceMessage(true,
              "Verified: Navigated to target pages after clicking link from the Work Item." +
                  "\nActual Result is clicked on \"" + linkId + "\" from \"" + linkType + "\" " + linkSection +
                  "  section of the Work Item.\nExpected Result is \"" + linkId + "\" should be displayed from \"" +
                  linkType + "\" " + linkSection + " section of the Work Item.");
        }

      }
      return new TestAcceptanceMessage(false,
          "Verified: Link is not navigated to  proper pages after clicking link from the work Item '" + linkSection +
              "' section.");

    }
    catch (Exception e) {
      List<WebElement> results1 = this.driverCustom.getWebElements("//div[@dojoattachpoint='_contentNode']//a");


      for (WebElement artifact : results1) {
        if (linkId.contains(artifact.getText())) {
          return new TestAcceptanceMessage(true,
              "Verified: Navigated to target pages after clicking link from the Work Item." +
                  "\nActual Result is clicked on \"" + linkId + "\" from \"" + linkType + "\" " + linkSection +
                  "  section of the Work Item.\nExpected Result is \"" + linkId + "\" should be displayed from \"" +
                  linkType + "\" " + linkSection + " section of the Work Item.");
        }

      }
      return new TestAcceptanceMessage(false,
          "Verified: Link is not navigated to  proper pages after clicking link from the work Item '" + linkSection +
              "' section.");
    }
  }

  /**
   * <P>
   * Verify work item is visible in the Test Case.
   * <P>
   * This method called after {@link CCMWorkItemEditorPage#isWorkItemVisibleInTestCase(String)}.
   *
   * @param testCaseId id of the test case.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsWorkItemVisibleInTestCase(final String testCaseId, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: Work Item is displayed in 'Development Items' section of the Test Case." + "\n Actual Result \"" +
              testCaseId + "\" is displayed in the Test Case.\nExpected Result \"" + testCaseId +
              "\" should be displayed in the Test Case.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Work Item is not displayed in 'Development Items' section of the Test Case.");
  }

  /**
   * <p>
   * Verify work item is clicked from Test Case.
   * <p>
   * This method called after {@link CCMWorkItemEditorPage#clickOnWorkItemFromTestCase(String)}.
   *
   * @param workItemId id of the work item.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnWorkItemFromTestCase(final String workItemId, final String lastResult) {
    WebElement artifactId = this.driverCustom.getFirstVisibleWebElement(CCMConstants.TITLE_TEXT, null);
    if (artifactId.getText().contains(workItemId)) {
      return new TestAcceptanceMessage(true,
          "Verified: Work Item is opened from 'Development Items' section of the Test Case." + "\n Actual Result \"" +
              workItemId + "\" is opened from Test Case.\nExpected Result \"" + workItemId +
              "\" should be opened from 'Test Case'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Work Item is not opened from 'Development Items' section of the Test Case.");
  }

  /**
   * <P>
   * Verify link is removed from the work item.
   * <p>
   * This method called after {@link CCMWorkItemEditorPage#deleteAllLinks(String)}.
   *
   * @param workitemName name of the work item.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteAllLinks(final String workitemName, final String lastResult) {
    List<WebElement> allLinks = this.driverCustom.getWebElements("//a[@class='jazz-ui-ResourceLink']");
    for (WebElement link : allLinks) {
      if (link.getText().equalsIgnoreCase(workitemName)) {
        return new TestAcceptanceMessage(false, "Verified: Link is not removed from 'Links' section of the Work Item.");
      }
    }
    return new TestAcceptanceMessage(true,
        "Verified: Link is removed from 'Links' section of the Work Item.\nActual Result is \"" + workitemName +
            "\" removed from Work Item" + "\nExpected Result is \"" + workitemName +
            "\" should be removed from 'Links' section of the Work Item.");
  }

  /**
   * <P>
   * Verify work item is visible in the Test Plan.
   * <P>
   * This method called after {@link CCMWorkItemEditorPage#isWorkItemVisibleInTestArtifact(String)}.
   *
   * @param workItemId id of the work item.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsWorkItemVisibleInTestArtifact(final String workItemId, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: Work Item is displayed in 'Related Change Requests' section of the Test Artifact." +
              "\nActual Result \"" + workItemId + "\" is displayed in the Test Artifact.\nExpected Result is \"" +
              workItemId + "\" should be displayed in the Test Artifact.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Work Item is not displayed in 'Related Change Requests' section of the Test Artifact.");
  }

  /**
   * <p>
   * Verify work item is clicked from Test Plan.
   * <p>
   * This method called after {@link CCMWorkItemEditorPage#clickOnWorkItemFromTestArtifact(String)}.
   *
   * @param workItemId id of the work item.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnWorkItemFromTestArtifact(final String workItemId, final String lastResult) {
    WebElement artifactId = this.driverCustom.getFirstVisibleWebElement(CCMConstants.TITLE_TEXT, null);
    if (artifactId.getText().contains(workItemId)) {
      return new TestAcceptanceMessage(true,
          "Verified: Work Item is opened from 'Related Change Requests' section of the Test Artifact." +
              "\nActual Result \"" + workItemId + "\" Is opened from Test Artifact.\nExpected Result \"" + workItemId +
              "\" should be opened from 'Test Artifact'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Work Item is not opened from 'Related Change Requests' section of the Test Artifact.");
  }

  /**
   * <p>
   * Verify created link is added in 'Links' section of the work item.
   * <p>
   * This method called after {@link CCMWorkItemEditorPage#createLinkToExistingObject(Map)}.
   *
   * @param additionalParams contains list of key value pair data.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyCreateLinkToExistingObject(final Map<String, String> additionalParams,
      final String lastResult) {
    switch (additionalParams.get(CCMConstants.LINKACTIONS)) {
      case "Add Tested By Test Case":
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        List<String> allLinks =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allLinks != null) && allLinks.stream()
            .anyMatch(x -> (x != null) && x.contains(additionalParams.get(CCMConstants.TESTCASE_NAME)))) {
          return new TestAcceptanceMessage(true,
              "Verified: Created Test Case 'Tested By Test Case' link is added in 'Links' section of the Work Items." +
                  " \n Actual result \"" + additionalParams.get(CCMConstants.TESTCASE_NAME) +
                  "\" Created new test case is added in 'Links' section.\n" + " Expected Result \"" + lastResult +
                  "\" Created test case should be added in 'Links' section.");
        }
        return new TestAcceptanceMessage(false,
            "'Add Tested By Test Case' link not added in 'Links' section of the Work Item.");
      case "Add Related Test Plan":
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        List<String> allTestPlanLinks =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allTestPlanLinks != null) && allTestPlanLinks.stream()
            .anyMatch(x -> (x != null) && x.contains(additionalParams.get(CCMConstants.TESTPLAN_NAME)))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Related Test Plan' link is added in 'Links' section of the Work Items." +
                  "\nActual result \"" + additionalParams.get(CCMConstants.TESTPLAN_NAME) +
                  "\" created new test plan is added in 'Links' section.\n" + "Expected Result \"" +
                  additionalParams.get(CCMConstants.TESTPLAN_NAME) +
                  "\" created new test plan should be added in 'Links' section.");
        }
        return new TestAcceptanceMessage(false,
            "'Add Related Test Plan' link not added in 'Links' section of the Work Item.");
      case "Add Related":
        if (this.driverCustom.isTitleContains("New Work Item - Change and Configuration Management", Duration.ofSeconds(20))) {
          return new TestAcceptanceMessage(true,
              "Verified by title of the opened specification.\nActual title is -\"" +
                  this.driverCustom.getWebDriver().getTitle() + ".\"\nActual title contains Expected text \"" +
                  "New Work Item" + ".\"");
        }
        return new TestAcceptanceMessage(true,
            "Verified by title of the opened specification.\nActual title is -\"" +
                this.driverCustom.getWebDriver().getTitle() + ".\" \nActual title not contain Expected text \"" +
                "New Work Item" + ".\"");
      case "Add Implements Requirement":
        this.driverCustom.getPresenceOfWebElement(CCMConstants.TITLE_TEXT);
        List<String> allImplementsLinks =
            this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_LINKSECTION_ALLLINKS_XPATH).stream()
                .map(x -> x.getText()).collect(Collectors.toList());
        if ((allImplementsLinks != null) && allImplementsLinks.stream()
            .anyMatch(x -> (x != null) && x.contains(additionalParams.get(CCMConstants.REQUIREMENT_NAME)))) {
          return new TestAcceptanceMessage(true,
              "Verified: 'Add Implements Requirement' link is added in 'Links' section of the Work Items." +
                  "\nActual result \"" + additionalParams.get("RequirementName") +
                  "\" created new 'Implements Requirement' is added in 'Links' section.\n" + "Expected Result \"" +
                  additionalParams.get("RequirementName") +
                  "\" created new 'Implements Requirement' should be added in 'Links' section.");
        }
        return new TestAcceptanceMessage(false,
            "'Add Implements Requirement' link not added in 'Links' section of the Work Item.");
      default:
        return new TestAcceptanceMessage(false, "Link is not added any of the Links section.");
    }
  }

  /**
   * <P>
   * Verify added test case is duplicated.
   * <P>
   * This method called after {@link CCMWorkItemEditorPage#isTestCaseDuplicated()}
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsTestCaseDuplicated(final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: Added Test Case is not duplicated.Same test case is not added again under 'Add Related Test Case' section.-" +
              "" + lastResult);
    }
    return new TestAcceptanceMessage(false, "Verified: Test Case is not duplicated.");
  }

  /**
   * <P>
   * Verify added same work item is disabled.
   * <P>
   * This method called after {@link CCMWorkItemEditorPage#isWorkItemDisabled(String)}
   *
   * @param workItem id or name of the work item.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsWorkItemDisabled(final String workItem, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: Searched work item is disabled.\nActual result \"" + workItem +
          "\" is disabled." + "\nExpected result \"" + workItem + "\" should be disabled.");
    }
    return new TestAcceptanceMessage(false, "Verified searched work item is not disabled.");

  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#setAttributeValueInTextBox(String, String)}.
   *
   * @param attributeName summary... etc.
   * @param attributeValue text.
   * @param lastResult text.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySetAttributeValueInTextBox(final String attributeName, final String attributeValue,
      final String lastResult) {
    return verifySetAttributeValueInTextBox(attributeName, attributeValue, "false", lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#setAttributeValueInTextBox(String, String)}.
   *
   * @param attributeName summary... etc.
   * @param attributeValue text.
   * @param isMultiLine true if textbox has multiple lines
   * @param lastResult text.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySetAttributeValueInTextBox(final String attributeName, final String attributeValue,
      final String isMultiLine, final String lastResult) {

    String actualValue = "";
    try {
      Text txtTextFiled = null;
      if (Boolean.valueOf(isMultiLine)) {
        txtTextFiled = this.engine
            .findElementWithDuration(Criteria.isTextField().hasLabel(attributeName).isMultiLine(), this.timeInSecs)
            .getFirstElement();
      }
      else {
        txtTextFiled = this.engine
            .findElementWithDuration(Criteria.isTextField().hasLabel(attributeName), this.timeInSecs).getFirstElement();
      }
      if (txtTextFiled != null) {
        actualValue = txtTextFiled.getText().trim();
      }
      if (!actualValue.equals(attributeValue) && (txtTextFiled != null)) {
        actualValue = txtTextFiled.getWebElement().getAttribute("value").trim();
      }
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (actualValue.contains(attributeValue)) {
      return new TestAcceptanceMessage(true,
          "Verified: Actual value of " + attributeName + " is " + actualValue +
              "' set successfully. \nActual value of " + attributeName + " contains Expected value - '" +
              attributeValue + "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Actual value of  " + attributeName + " is -" + actualValue +
            "' not set successfully. \nActual value of " + attributeName + " not contains Expected value - '" +
            attributeValue + "'");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#getAttributeValueInTextBox(String)}.
   *
   * @param attributeName summary...etc.
   * @param additionalParam is expected value to be verified against actual value.
   * @param lastResult not used.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyGetAttributeValueInTextBox(final String attributeName,
      final String additionalParam, final String lastResult) {
    return verifyGetAttributeValueInTextBox(attributeName, "false", additionalParam, lastResult);

  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#getAttributeValueInTextBox(String)}.
   *
   * @param attributeName summary...etc.
   * @param isMultiLine true if textbox has multiple lines
   * @param additionalParam is expected value to be verified against actual value.
   * @param lastResult not used.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyGetAttributeValueInTextBox(final String attributeName, final String isMultiLine,
      final String additionalParam, final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalParam),
          "Actual '" + attributeName + "' value is - \n \"" + lastResult + "\".");
    }
    else if (additionalParam.isEmpty() && lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.equals(additionalParam),
          String.format("Verify that '%s' value is EMPTY as expected", attributeName));
    }
    return new TestAcceptanceMessage(false, "Actual value '" + attributeName + "' value is - \"" + lastResult + "\".");
  }

  /**
   * <p>
   * This method verifies after adding work item in link section checks respective work item in link section.
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isPilotPlanningWorkItemAdded(String)}.
   *
   * @param epic_PilotWIName work item name or id.
   * @param lastResult returned value of method which is executed just before the method.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyIsPilotPlanningWorkItemAdded(final String epic_PilotWIName,
      final String lastResult) {
    return verifyIsPilotPlanningWorkItemAdded(epic_PilotWIName, "TRUE", lastResult);
  }

  /**
   * <p>
   * This method verifies after adding work item in link section checks respective work item in link section.
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isPilotPlanningWorkItemAdded(String)}.
   *
   * @param epic_PilotWIName work item name or id.
   * @param additionalParam true if pilot planning is added successfully.
   * @param lastResult returned value of method which is executed just before the method.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyIsPilotPlanningWorkItemAdded(final String epic_PilotWIName,
      final String additionalParam, final String lastResult) {
    if (Boolean.valueOf(additionalParam) && Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified work item '" + epic_PilotWIName + "' added under Adding Pilot section successfully.");
    }
    else if (!Boolean.valueOf(additionalParam) && !Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified work item '" + epic_PilotWIName + "' is NOT added under Adding Pilot section.");
    }
    else {
      return new TestAcceptanceMessage(false,
          "Verified work item '" + epic_PilotWIName + "' not added under Adding Pilot section successfully.");
    }
  }

  /**
   * <p>
   * This method verifies after adding work item in link section checks respective work item in link section.
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isWorkItemAddedInProcessLinkSection(String)}.
   *
   * @param task_ChildrenWIName work item name or id.
   * @param lastResult returned value of method which is executed just before the method.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyIsWorkItemAddedInProcessLinkSection(final String task_ChildrenWIName,
      final String lastResult) {
    return verifyIsWorkItemAddedInProcessLinkSection(task_ChildrenWIName, "TRUE", lastResult);
  }

  /**
   * <p>
   * This method verifies after adding work item in link section checks respective work item in link section.
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isWorkItemAddedInProcessLinkSection(String)}.
   *
   * @param task_ChildrenWIName work item name or id.
   * @param additionalParam true if work item is added successfully.
   * @param lastResult returned value of method which is executed just before the method.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyIsWorkItemAddedInProcessLinkSection(final String task_ChildrenWIName,
      final String additionalParam, final String lastResult) {
    if (Boolean.valueOf(additionalParam) && Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified work item '" + task_ChildrenWIName + "' added under links section successfully.");
    }
    else if (!Boolean.valueOf(additionalParam) && !Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified work item '" + task_ChildrenWIName + "' is NOT added under links section.");
    }
    else {
      return new TestAcceptanceMessage(false,
          "Verified work item '" + task_ChildrenWIName + "' not added under links section successfully.");
    }
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isReadOnlyDropdown(String)}.
   *
   * @param drdLabel the label of dropdown
   * @param additionalPram additional value
   * @param lastResult get the last result of {@link CCMWorkItemEditorPage#isReadOnlyDropdown}}
   * @return validation message
   */
  public TestAcceptanceMessage verifyIsReadOnlyDropdown(final String drdLabel, final String additionalPram,
      final String lastResult) {
    return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalPram),
        "Verify that the '" + drdLabel + " is disabled.\nThe fact is that the expected value - '" + additionalPram +
            "' and the actual result - '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#clickOnRefreshButton()}.
   *
   * @param additionalPram summary or id
   * @param lastResult string
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyClickOnRefreshButton(final String additionalPram, final String lastResult) {
    CCMWorkItemEditorPage summary = new CCMWorkItemEditorPage(this.driverCustom);
    if (summary.getAttributeValueInTextBox("Summary").equalsIgnoreCase(additionalPram)) {
      return new TestAcceptanceMessage(true, "Verified after refreshing the work Item");
    }
    return new TestAcceptanceMessage(false, "Verified after refreshing the work Item");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#getWorkItemID()}.
   *
   * @param lastResult actual value
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyGetWorkItemID(final String lastResult) {

    if (lastResult != null) {
      return new TestAcceptanceMessage(true, "Actual work item ID is - \n \"" + lastResult + "\".");
    }
    return new TestAcceptanceMessage(false,
        "Actual work item ID not set or \n Actual work item summary is - \"" + lastResult + "\".");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#modifyDescriptionTextFont(String)}.
   *
   * @param font to be set for the work item description.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyModifyDescriptionTextFont(final String font, final String lastResult) {
    List<WebElement> fieldformats =
        this.driverCustom.getWebElements("//a[@title='DYNAMIC_VAlUE' and @role='button']", font);
    for (WebElement fieldformat : fieldformats) {
      Boolean isFontPressed = false;
      try {
        isFontPressed = Boolean.valueOf(fieldformat.getAttribute("aria-pressed"));
      }
      catch (Exception e) {}
      if (isFontPressed) {
        return new TestAcceptanceMessage(true, "Verified: Description field is editted with - " + font);
      }
    }
    return new TestAcceptanceMessage(false, "Verified: Description field is not editted with - " + font);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#setTags(String)}.
   *
   * @param tagValue tag value of the work item.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetTags(final String tagValue, final String lastResult) {
    List<WebElement> tags = this.driverCustom.getWebElements("//div[@aria-label='Tags']//span");
    for (WebElement tag : tags) {
      if (tag.getAttribute("title").contains(tagValue)) {
        return new TestAcceptanceMessage(true,
            "Verified: Tag is added for the work item.\nActual Result '" + tagValue +
                "' tag is added in the work item." + "\nExpected Result '" + tagValue +
                "' tag should be added in the work item.");
      }
    }
    return new TestAcceptanceMessage(false, "Verified: Tag '" + tagValue + "'is not added for the work item.");
  }
  
  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#getTagsValues}.
   * @param additionalParam : expeceted tags value
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetTagsValues(final String additionalParam, final String lastResult) {
    String tagsXpath = "//div[@aria-label='Tags']//span[@class='TagContent']";
    List<WebElement> tags = this.driverCustom.getWebDriver().findElements(By.xpath(tagsXpath));
    if(!tags.isEmpty()) {
      for (WebElement tag : tags) {
        if(additionalParam.equalsIgnoreCase(tag.getText().trim())) {
          return new TestAcceptanceMessage(true, String.format("Verified: 'Tags' value is '%s' as expected", tag.getText().trim()));
        }
      } 
    }
    else if(additionalParam.isEmpty() && tags.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified: 'Tags' value is EMPTY as expected");
    }
    return new TestAcceptanceMessage(false, "Verified: 'Tags' value is not displayed as expected");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#removeDueDate()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyRemoveDueDate(final String lastResult) {
    String getDate = "";
    Text txtTextDueDate = this.engine
        .findElementWithDuration(Criteria.isTextField().hasLabel("Due Date: "), this.timeInSecs).getFirstElement();
    getDate = txtTextDueDate.getWebElement().getAttribute("value").trim();
    if (getDate.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified: Due date is deleted from the work item.");
    }
    return new TestAcceptanceMessage(false, "Verified: Due date is not deleted from the work item.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#removeTags(String)}.
   *
   * @param tagName tag name of the work item.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyRemoveTags(final String tagName, final String lastResult) {
    this.driverCustom.isElementVisible("//div[@aria-label='Tags']//span", Duration.ofSeconds(15));
    List<WebElement> tags = this.driverCustom.getWebElements("//div[@aria-label='Tags']//span");
    for (WebElement tag : tags) {
      if (!tag.getAttribute("title").contains(tagName)) {
        return new TestAcceptanceMessage(true, "Verified: Tag '" + tagName + "' is deleted from the work item.");
      }
    }
    return new TestAcceptanceMessage(false, "Verified: Tag is not deleted from the work item.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#setDate(String, String)}.
   *
   * @param attributeName Due Date of the work item.
   * @param attributeValue value of the attribute to be set.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetDate(final String attributeName, final String attributeValue,
      final String lastResult) {
    String actualValue = "";
    String getDate = "";
    Text txtTextDueDate =
        this.engine.findElementWithDuration(Criteria.isTextField().hasLabel(attributeName + ":"), this.timeInSecs)
            .getFirstElement();
    getDate = txtTextDueDate.getWebElement().getAttribute("value").trim();
    if (!getDate.isEmpty() && getDate.equals(attributeValue)) {
      return new TestAcceptanceMessage(true,
          "Verified actual value of " + attributeName + " is " + actualValue +
              "' set successfully. \nActual value of " + attributeName + " contains Expected value - '" +
              attributeValue + "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified actual value of " + attributeName + " is -" + actualValue +
            "' not set successfully. \nActual value of " + attributeName + " not contains Expected value - '" +
            attributeValue + "'");
  }

  /**
   * <p>
   * Verify test cases not run result.
   * <p>
   * This method called after {@link CCMWorkItemEditorPage#clickOnTab(String)}.
   *
   * @param tab name of tab.
   * @param lastResult returned value of method which is executed just before the method.
   * @return which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnTab(final String tab, final String lastResult) {
    List<WebElement> tabs = this.driverCustom.getWebElements("//a[@class='tab']");
    for (WebElement tabname : tabs) {
      if (tabname.getText().contains(tab) && tabname.getAttribute("aria-selected").equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true, RQMConstants.VERIFIED_COLON + tab + RQMConstants.CLICKED_SUCESSFULLY);
      }
    }
    return new TestAcceptanceMessage(false, RQMConstants.VERIFIED_COLON + tab + RQMConstants.NOT_CLICKED_SUCESSFULLY);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isDescriptionSameInPrintPage(String)}.
   *
   * @param additionalParam is expected value to be verified against actual value.
   * @param descriptionValue value in description
   * @param lastResult returned value of method which is executed just before the method.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyIsDescriptionSameInPrintPage(final String descriptionValue,
      final String additionalParam, final String lastResult) {
    waitForSecs(20);
    String description = this.driverCustom.getWebElement(CCMConstants.WORKITEMEDITORPAGE_DESCRIPTION_TEXTFIELD_XPATH).getText();
    if (description.contains(descriptionValue)) {
      return new TestAcceptanceMessage(true, "Actual value of work item description text ' " + description +
          " 'is matches with Expected Results: " + descriptionValue);
    }
    return new TestAcceptanceMessage(false, "Actual value of work item description text '" + description +
        "' is not matches with Expected Results: " + descriptionValue);
  }

  /**
   * @param ownedBy onwed by
   * @param lastResult returned value of method which is executed just before the method.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyIsWarningSymbolDisplayed(final String ownedBy, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified: Warning Symbol is Displayed.\nActual result \"" + ownedBy + "\" warning symbol is displayed." +
              "\nExpected result \"" + ownedBy + "\" warning symbole should be displayed.");
    }
    return new TestAcceptanceMessage(false, "Verified Warning Symbol is not Displayed.");

  }

  /**
   * @param ownedBy onwed by
   * @param additionalParam addition parameter for this method
   * @param lastResult returned value of method which is executed just before the method.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyClickOnWarningSymbol(final String ownedBy, final String additionalParam,
      final String lastResult) {
    waitForSecs(5);
    String warMessage = this.driverCustom.getPresenceOfWebElement("//div[text()='" + additionalParam + "']").getText();
    if (warMessage.contains(additionalParam)) {
      return new TestAcceptanceMessage(true,
          "Verified after clicking warning symbol. \nActual value of Warning message is ' " + warMessage +
              "' matches with Expected warning message. \nExpected warning message after clicking warning symbol is : '" +
              additionalParam + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified after clicking warning symbol.\nwarning symbol is not clicked successfully. \nExpected Results: warning message should display" +
            warMessage);


  }

  /**
   * @param warningMessage the test of warning message
   * @param lastResult returned value of method which is executed just before the method.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyGetWarningMessageOnWorningSymbol(final String warningMessage,
      final String lastResult) {
    if (lastResult.contains(warningMessage)) {
      return new TestAcceptanceMessage(true,
          "Verified: Warning Message displayed On Worning Symbol as : " + lastResult + ".");
    }
    return new TestAcceptanceMessage(false, "Verified: Not displayed Warning Message On Worning Symbol.");
  }

  /**
   * Verify link details dialog loaded completedy<br>
   * This method called after {@link CCMWorkItemEditorPage#hoverOnArtifactLinkInLinkTab(String, String, String)}.
   *
   * @author VDY1HC
   * @param linkType - Link type of linked artifact
   * @param artifactLinkText - Text of link displayed
   * @param waitTimeInSecs - Max time to wait until loading finished
   * @param lastResult - lastResult
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyHoverOnArtifactLinkInLinkTab(final String linkType, final String artifactLinkText,
      final String waitTimeInSecs, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(Integer.parseInt(waitTimeInSecs)));
    WebDriver driverWithOutWait = this.driverCustom.getWebDriver();
    String linkedArtifactID = artifactLinkText.split(":")[0];
    Boolean popupOpened = false;
    Boolean loaded = false;
    Boolean titleText = false;
    String title = "";
    try {
      loaded = !driverWithOutWait.findElement(By.xpath("//span[@class='image-loading hidden']")).isDisplayed();
      popupOpened = driverWithOutWait.findElement(By.xpath("//iframe[@dojoattachpoint='hoverIframe']")).isDisplayed();
      title = driverWithOutWait.findElement(By.xpath("//a[@dojoattachpoint='titleLink']")).getText();
      titleText = title.contains(linkedArtifactID + ": ");
    }
    catch (Exception e) {
      LOGGER.LOG.info("One condition failed due to: " + e.toString());
    }
    if (popupOpened && loaded && titleText) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Link popup is opened and loaded successfully after " + waitTimeInSecs + " secs " +
              "\nActual loading status: Pop up opened - " + popupOpened + ", Popup loaded - " + loaded +
              ", Title text displayed - " + title);
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - Link popup is opened and loaded successfully after " + waitTimeInSecs + " secs " +
            "\nActual loading status: Pop up opened - " + popupOpened + ", Popup loaded - " + loaded +
            ", Title text displayed - " + title);
  }

  /**
   * This method called after {@link CCMWorkItemEditorPage#addLinkToExistingObjectWithOutClose(Map)}.
   *
   * @author VDY1HC
   * @param additionalParams - additionalParams
   * @param lastResult - lastResult
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyAddLinkToExistingObjectWithOutClose(final Map<String, String> additionalParams,
      final String lastResult) {
    try {
      this.driverCustom.switchToFrame("//iframe[@dojoattachpoint='iframe']");
    }
    catch (Exception e) {
      LOGGER.LOG.info("Content focus in the active frame");
    }
    String actualMessage = this.driverCustom
        .getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_ADDLINKDIALOG_LOADED_ARTIFACT_XPATH).getText();
    String expectedMsg = additionalParams.get("expectedMsg");
    if (actualMessage.equalsIgnoreCase(expectedMsg)) {
      return new TestAcceptanceMessage(true,
          "Verified: PASSED - Text displayed in Select Artifact Dialog match expected " +
              "\nActual message displayed: " + actualMessage + "\nExpected message displayed: " + expectedMsg);
    }
    return new TestAcceptanceMessage(false,
        "Verified: FAILED - Text displayed in Select Artifact Dialog DOES NOT match expected " +
            "\nActual message displayed: " + actualMessage + "\nExpected message displayed: " + expectedMsg);
  }

  /**
   * This method called after {@link CCMWorkItemEditorPage#generateExpectedMessageDisplayed(Map)}.
   *
   * @param additionalParams - list of text
   * @param lastResult - final message
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGenerateExpectedMessageDisplayed(final Map<String, String> additionalParams,
      final String lastResult) {
    if (lastResult.isEmpty()) {
      return new TestAcceptanceMessage(false, "Verified: FAILED - Unable to join all the text");
    }
    return new TestAcceptanceMessage(true, "Verified: PASSED - Generated text with result: " + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#isSummarySameInPrintPage(String)}.
   *
   * @param expectedSummary summary of work item or link text in Tracks link.
   * @param lastResult returned value of method which is executed just before the method.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyIsSummarySameInPrintPage(final String expectedSummary, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Actual summary of Tracks link to work item is matches with Expected Results: " + expectedSummary);
    }
    return new TestAcceptanceMessage(false, "Actual summary of Tracks link to work item is not matches with Expected Results: " + expectedSummary);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMWorkItemEditorPage#removeLinksFromLinksSectionById(String)}.
   * @author KYY1HC 
   *
   * @param workItemId id of work item or link text in Tracks link.
   * @param lastResult returned value of method which is executed just before the method.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyRemoveLinksFromLinksSectionById(final String workItemId, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Removed link with id '" + workItemId + "' is successfully from the 'Links' section of the work item.");
    }
    return new TestAcceptanceMessage(false, "Removed link with id '" + workItemId + "' is NOT successfully from the 'Links' section of the work item.");
  }
  
  /**
   * This method is called after executing method {@link CCMWorkItemEditorPage#verifyWorkItemIsSavedWithNewDropdownValue(Map)}
   * Verify that newly created dropdownValue is available for creating work item.
   * @param params contains key and value from last execution
   * @param lastResult  returned value of method which is executed just before the method.
   * @return verification message
   * @author NCY3HC
   */
  public TestAcceptanceMessage verifyVerifyWorkItemIsSavedWithNewDropdownValue(final Map<String, String> params, final String lastResult) {
    String expectedDropdownValue = params.get("DROPDOWN_VALUE");
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "The newly created attribute '" +expectedDropdownValue + "' are Available for creating work item");
    }
    return new TestAcceptanceMessage(false, "The newly created attribute '" +expectedDropdownValue + "' are Unvailable for creating work item");
  }
 
  /**
   * This method is called after executing method {@link CCMWorkItemEditorPage#setPlannedFor(String)}
   * Verify that set Planned For successfully
   * @param attributeValue Plan value
   * @param lastResult  returned value of method which is executed just before the method.
   * @return verification message
   * @author NCY3HC
   */
  public TestAcceptanceMessage verifySetPlannedFor(final String attributeValue, final String lastResult) {
    if (lastResult.equalsIgnoreCase(attributeValue)) {
      return new TestAcceptanceMessage(true, "Expected 'Planned For:' value is -'" +attributeValue+ "' is available in dropdown.");
    }
    return new TestAcceptanceMessage(false, "Expected 'Planned For:' value is -'" +attributeValue + "' is unavailable in dropdown.");
  }
  
  /**
   * This method is called after executing method {@link CCMWorkItemEditorPage#verifyTextInDescriptionFont(String)}
   * @param font "Bold", "Italic", "Underline", "Strikethrough"
   * @param lastResult  returned value of method which is executed just before the method.
   * @return verification message
   * @author LTU7HC
   */
  public TestAcceptanceMessage verifyVerifyTextInDescriptionFont(final String font, final String lastResult) {
    String actualText = "";
    try {
      Text txtTextFiled = this.engine
          .findElementWithDuration(Criteria.isTextField().hasLabel("Description").isMultiLine(), Duration.ofSeconds(30))
          .getFirstElement();
      actualText = txtTextFiled.getText().trim();
    }
    catch (Exception e) {}
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          String.format("Verify that text in Description is '%s' with Style: '%s'", actualText, font));
    }
    return new TestAcceptanceMessage(false, "Verify that Style of text in Description is not expected");
  }
  
  /**
   * This method is called after executing method {@link CCMWorkItemEditorPage#moveOrCopyWorkItemToAnotherProject(String, String, String)}
   * @author NCY3HC
   * @param projectName : target prject which is selected to ve/copy work item to.
   * @param option : Mover/ Copy
   * @param copy_attachments : checkbox will be display after selected 'Copy' option
   * @param lastResult  returned value of method which is executed just before the method.
   * @return verification message
   */
  public TestAcceptanceMessage verifyMoveOrCopyWorkItemToAnotherProject(final String projectName, final String option,final String copy_attachments,final String lastResult) {
    Select drdProjectArea = new Select
        (this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEM_CURRENT_PROJECTARE));
    String presentProjectArea =  drdProjectArea.getFirstSelectedOption().getText();
    //Copy work item
  if (option.equalsIgnoreCase("Copy")) {
     String rdoStatusCopy = this.driverCustom.getAttribute(CCMConstants.CCMWORKITEM_COPY_BUTTON, "checked"); 
     String cbxStatusCopyAttachments = null;
     if(copy_attachments.equalsIgnoreCase("true")) {
        cbxStatusCopyAttachments = this.driverCustom.getAttribute(CCMConstants.CCMWORKITEM_COPY_ATTACHMENTS_CHECKBOX, "checked");
     }
     if(presentProjectArea.equalsIgnoreCase(projectName)&& rdoStatusCopy.equalsIgnoreCase("true")&& cbxStatusCopyAttachments.equalsIgnoreCase("true")) {
       return new TestAcceptanceMessage(true, "Verify inputting data to 'Move or copy this work item' dialog.\n Select project area '"+projectName+"' successfully!.\n 'Copy' radio button is checked.\n 'Copy attachment' checkbox is selected");
     }    
    }
  // Move workitem
  String rdoStatusMove = this.driverCustom.getAttribute(CCMConstants.CCMWORKITEM_MOVE_BUTTON, "checked"); 
  if(presentProjectArea.equalsIgnoreCase(projectName)&& rdoStatusMove.equalsIgnoreCase("true")) {
    return new TestAcceptanceMessage(true, "Verify inputting data to 'Move or copy this work item' dialog.\n Select project area '"+projectName+"' successfully!.\n 'Move' radio button is checked.");
  }
  return new TestAcceptanceMessage(false, "Select data to move or copy work item unsuccessfully!");  

  }
  
  /** 
   * This method is called after executing method {@link CCMWorkItemEditorPage#getHeaderValidationMessage()}
   * @author NCY3HC
   * @param additionalParam expected message
   * @param lastResult return the value of method which is just executed before this method
   * @return verification message
   */
  public TestAcceptanceMessage verifyHeaderValidationMessage(final Map<String,String> additionalParam,final String lastResult) {
    String expMessage = additionalParam.get("EXPECTED_MESSAGE");
    if(lastResult.contains(expMessage)) {
      return new TestAcceptanceMessage(true, "Copy work item to another project area successfully! \nThe System is throwing the massage '"+lastResult+"'.");
    }
    return new TestAcceptanceMessage(false, "Copy work item to another project area unsuccessfully!");
  }
  
 /**
  * This method is called after executing method {@link CCMWorkItemEditorPage#isLinksDisplayedInsideWorkItem(Map)}
  * @author NCY3HC
  * @param additionalParam : expected project
  * @param params contains Links linkType - Parent/Implemented By/... 
  *  linkLabel - link text
  * @param lastResult return the value of method which is just executed before this method
  * @return verification message
  */
 public TestAcceptanceMessage verifyIsLinksDisplayedInsideWorkItem(final Map<String,String> params,final String additionalParam,final String lastResult) {
   String linkType = params.get("LINK_TYPE");
   String linkLabel = params.get("LINK_LABEL");
       if (lastResult.equalsIgnoreCase("true")) {
         return new TestAcceptanceMessage(true, "Verify current work item Links '"+linkType+":"+linkLabel+"' are existed in '"+additionalParam+"'.");
       }
   return new TestAcceptanceMessage(false, "Verify current work item Links '"+linkLabel+"' are not existed in '"+additionalParam+"'.");
 }
 
 /**
  * This method is called after executing method {@link CCMWorkItemEditorPage#isFilesDisplayedInsideWorkItem(String)}
  * @author NCY3HC
  * @param filesName : name of attachments
  * @param additionalParam : expected project
  * @param lastResult return the value of method which is just executed before this method
  * @return verification message
  */
 public TestAcceptanceMessage verifyIsFilesDisplayedInsideWorkItem(final String filesName,final String additionalParam,final String lastResult) {
       if (lastResult.equalsIgnoreCase("true")) {
         return new TestAcceptanceMessage(true, "Verify  work item attachments '"+filesName+"' are existed inside the'"+additionalParam+"'.");
       }
   return new TestAcceptanceMessage(false, "Verify current work item attachments '"+filesName+"' are not existed inside the'"+additionalParam+"'.");
 }
 
 /**
  * This method is called after executing method {@link CCMWorkItemEditorPage#isCommentDisplayedInsideWorkItem(String)}
  * @author NCY3HC
  * @param comment : comment inside work item 
  * @param additionalParam : expected project
  * @param lastResult return the value of method which is just executed before this method
  * @return verification message
  */
 public TestAcceptanceMessage verifyIsCommentDisplayedInsideWorkItem(final String comment,final String additionalParam, final String lastResult) {
       if (lastResult.equalsIgnoreCase("true")) {
         return new TestAcceptanceMessage(true, "Verify that comment '"+comment+"' is  existed in project area '"+additionalParam+"'.");
       }
   return new TestAcceptanceMessage(false, "Verify that comment '"+comment+"' is  existed in project area '"+additionalParam+"'.");
 }
 
 /**
  * This method is called after executing method {@link CCMWorkItemEditorPage#getTagValueByLabel(String)}
  * @author NCY3HC
  * @param label : field name
  * @param lastResult return the value of method which is just executed before this method
  * @param additionalParam : expected value in order to compare to last result
  * @return verification message
  */
 public TestAcceptanceMessage verifyGetTagValueByLabel(final String label,final String additionalParam, final String lastResult) {
       if (lastResult.equalsIgnoreCase(additionalParam)) {
         return new TestAcceptanceMessage(true, "Verify current '"+label+ "' in the work item. \nActual value is '"+lastResult+"' mapping with the target value '"+additionalParam+"'.");
       }
   return new TestAcceptanceMessage(false, "Verify current '"+label+ "' in the work item. \nActual value is '"+lastResult+"' not mapping with the target value '"+additionalParam+"'.");
 }
}

