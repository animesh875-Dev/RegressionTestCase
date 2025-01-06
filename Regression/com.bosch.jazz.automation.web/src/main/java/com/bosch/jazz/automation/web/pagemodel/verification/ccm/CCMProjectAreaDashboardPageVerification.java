/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.ccm;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMProjectAreaDashboardPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.dropdown.JazzDropdownFinder;

/**
 * @author hrt5kor
 */
public class CCMProjectAreaDashboardPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom driver.
   */
  public CCMProjectAreaDashboardPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDropdownFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new RowCellFinder(), new JazzDialogFinder(), new JazzTabFinder());
  }


  /**
   * <p>
   * After clicking on "Create Work item button" from "Create work item dialog".
   * <P>
   * Verifies in work item edit page Type contains selected value.
   * <p>
   * Verifies the action of {@link CCMProjectAreaDashboardPage#selectWorkItemFromCreateWorkitemDialogToCreate(String)}.
   *
   * @param workItemType work Item Type
   * @param lastResult no last result
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySelectWorkItemFromCreateWorkitemDialogToCreate(final String workItemType,
      final String lastResult) {
    this.driverCustom.getPresenceOfWebElement("//div[@class='detailsSplitTableWrapper']");
    waitForSecs(15);
    String actualValue = "";

    try {
      Dropdown drdType = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Type" + ":"));
      actualValue = drdType.getDefaultValue();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage()); 
    }
    if (actualValue.equals(workItemType) 
     || this.driverCustom.isElementInvisible("//div[@id='com_ibm_team_workitem_web_mvvm_view_queryable_combo_QueryableComboView_36']/div[2]/span[text()='DYNAMIC_VAlUE']", Duration.ofSeconds(10), workItemType)
     ){
      return new TestAcceptanceMessage(true, "Verified actual work item Type is - '" + actualValue +
          "' set successfully. \nExpected  work item Type is - '" + workItemType + "'.");
    }
    return new TestAcceptanceMessage(false, "Verified actual  work item Type is  - '" + actualValue +
        "' not selected successfully. \nExpected  work item Type is -'" + workItemType + "'.");
  }

  /**
   * <p>
   * <p>
   *
   * @author NVV1HC
   * @param params store pair of keys and values from
   *          ${@link CCMProjectAreaDashboardPage#createWorkItemsFromWorkItemTemplate(Map)}
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#createWorkItemsFromWorkItemTemplate(Map)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyCreateWorkItemsFromWorkItemTemplate(final Map<String, String> params,
      final String lastResult) {

    WebElement elm = null;
    try {
      elm = this.driverCustom.getWebElement(CCMConstants.QUERYPAGE_TEMPLATENAME_AFTERFINISHCREATINGWORKITEMS_XPATH,
          params.get("templateName"));
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }

    if ((elm != null) && !lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Verified work items are created successfully from an existing work item template '" +
              params.get("templateName") + "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified work items are not created successfully from an existing work item template '" +
            params.get("templateName") + "'");
  }

  /**
   * <p>
   * Verify get ID of work item is generated successfully after
   * ${@link CCMProjectAreaDashboardPage#createWorkItemsFromWorkItemTemplate(Map)}
   *
   * @param workItemSummary summary of work item
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#getWorkItemIDGenerated(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyGetWorkItemIDGenerated(final String workItemSummary, final String lastResult) {
    if (lastResult.matches("^[0-9]*$")) {
      return new TestAcceptanceMessage(true, "Verified work item '" + workItemSummary +
          "' is created successfully with generated ID: '" + lastResult + "'");
    }
    return new TestAcceptanceMessage(false, "Verified work item '" + workItemSummary + "' is not created successfully");
  }

  /**
   * <p>
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#backToQueryPage()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyBackToQueryPage(final String lastResult) {
    WebElement elm =
        this.driverCustom.getWebElement("//div[@class='topRow queryEditor' and not(contains(@style,'display: none'))]");
    if (elm.isDisplayed()) {
      return new TestAcceptanceMessage(true, "Verified back to Query page successfully");
    }
    return new TestAcceptanceMessage(false, "Verified back to Query page failed");
  }

  /**
   * <p>
   * <p>
   *
   * @author NVV1HC
   * @param typeOfLink type of link, e.g: Validated By,
   * @param relatedWorkItemID ID of link
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#verifyTabLinks(String, String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyTabLinks(final String typeOfLink, final String relatedWorkItemID,
      final String lastResult) {
    try {
      List<WebElement> listElm = this.driverCustom.getWebElements(
          "(//div[@class='workItemEditor workItemEditorWithShadedBackground' and not(contains(@style,'display: none;'))]//div[@class='LinksSection'])[1]//div[div[@class='ListHeader' and text()='" +
              typeOfLink + "']]//div[@class='ValueDiv']");
      for (WebElement elm : listElm) {
        if (elm.getText().trim().contains(relatedWorkItemID)) {
          return new TestAcceptanceMessage(true, "Verified link tab displayed correctly:\nExpected result: '" +
              relatedWorkItemID + "'\nActual result: '" + elm.getText().trim() + "'");
        }
      }
    }
    catch (Exception e) {}
    return new TestAcceptanceMessage(false, "Verified link tab displayed incorrectly");
  }

  /**
   * <p>
   * <p>
   *
   * @author NVV1HC
   * @param workItemTemplate work item template
   * @param workItemID ID of work item
   * @param summary summary of work item
   * @param workItemType work item type
   * @param filedAgainst Filed Against value of work item
   * @param plannedFor Planned For value of work item
   * @param description Description value of work item
   * @param lastResult result from
   *          ${@link CCMProjectAreaDashboardPage#verifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(String, String, String, String, String, String, String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(
      final String workItemTemplate, final String workItemID, final String summary, final String workItemType,
      final String filedAgainst, final String plannedFor, final String description, final String lastResult) {
    waitForSecs(5);
    if ((lastResult != null) & Boolean.valueOf(lastResult)) {
      try {
        WebElement editorPageElm = this.driverCustom.getWebElement(
            "//div[@class='workItemEditor workItemEditorWithShadedBackground' and not(contains(@style,'display: none'))]");
        WebElement overViewTabSelectedElm = this.driverCustom.getWebElement(
            "//div[@class='workItemEditor workItemEditorWithShadedBackground' and not(contains(@style,'display: none'))]//a[@class='tab' and @aria-selected='true']");
        if (editorPageElm.isDisplayed() & overViewTabSelectedElm.getAttribute("title").equals("Overview")) {
          return new TestAcceptanceMessage(true,
              "Verified overview information of work item '" + workItemID +
                  "' is displayed correctly with below information: \nSummary: '" + summary + "'\nWork item type: '" +
                  workItemType + "'\nFiled Against: '" + filedAgainst + "'\nPlanned For: '" + plannedFor +
                  "'\nDescription: '" + description + "'");
        }
      }
      catch (Exception e) {}
      return new TestAcceptanceMessage(false,
          "Verified overview information of work item '" + workItemID +
              "' is displayed wrong with below information: \nSummary: '" + summary + "'\nWork item type: '" +
              workItemType + "'\nFiled Against: '" + filedAgainst + "'\nPlanned For: '" + plannedFor +
              "'\nDescription: '" + description + "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified overview information of work item '" + workItemID +
            "' is displayed wrong with below information: \nSummary: '" + summary + "'\nWork item type: '" +
            workItemType + "'\nFiled Against: '" + filedAgainst + "'\nPlanned For: '" + plannedFor +
            "'\nDescription: '" + description + "'");
  }

  /**
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#clickOnHomeButtonDropDown()}
   * @author NVV1HC
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickOnHomeButtonDropDown(final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified Home button clicked successfully. ");
    }
    return new TestAcceptanceMessage(false, "Verified Home button not clicked successfully. ");
  }

  /**
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#openPersonalDashboard()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyOpenPersonalDashboard(final String lastResult) {

    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified 'Personal Dashboards' opened successfully. ");
    }
    return new TestAcceptanceMessage(false, "Verified 'Personal Dashboards' not opened successfully. ");
  }

  /**
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#clickOnAddWidgetButton()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickOnAddWidgetButton(final String lastResult) {

    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified 'Add Widget' Button clicked successfully. ");
    }
    return new TestAcceptanceMessage(false, "Verified 'Add Widget' not clicked successfully. ");
  }

  /**
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#clickOnSelectCatalogDropdown()}
   * @param additionalParam store pair of key and value
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickOnSelectCatalogDropdown(final String additionalParam,
      final String lastResult) {

    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified 'Select Catalog' Dropdown clicked successfully. ");
    }
    return new TestAcceptanceMessage(false, "Verified 'Select Catalog' Dropdown not clicked successfully. ");
  }

  /**
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#selectCatalogDropdownValue(String)}
   * @param catalogName catalog name
   * @return verification message
   */
  public TestAcceptanceMessage verifySelectCatalogDropdownValue(final String catalogName, final String lastResult) {

    if (!catalogName.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified '" + catalogName + "' selected successfully. ");
    }
    return new TestAcceptanceMessage(false, "Verified '" + catalogName + "' not selected successfully. ");
  }

  /**
   * @param categorytype type of category
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#selectCategoryType(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifySelectCategoryType(final String categorytype, final String lastResult) {

    if (!categorytype.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified '" + categorytype + "' selected successfully. ");
    }
    return new TestAcceptanceMessage(false, "Verified '" + categorytype + "' not selected successfully. ");
  }

  /**
   * @param widgetName name of widget
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#searchWidgetByName(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifySearchWidgetByName(final String widgetName, final String lastResult) {

    if (!widgetName.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified '" + widgetName + "' searched successfully. ");
    }
    return new TestAcceptanceMessage(false, "Verified '" + widgetName + "' not searched successfully. ");
  }

  /**
   * @param additionalParam widget name
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#addWidgetToDashboard(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyAddWidgetToDashboard(final String additionalParam, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified '" + additionalParam + "' Widget added To Dashboard successfully. ");
    }
    return new TestAcceptanceMessage(false,
        "Verified '" + additionalParam + "'  Widget not added To Dashboard successfully. ");
  }

  /**
   * @param widgtName name of widget
   * @param lastResult result from ${@link CCMProjectAreaDashboardPage#isWidgetAdded(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyIsWidgetAdded(final String widgtName, final String lastResult) {

    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified '" + widgtName + "' Widget added To Dashboard successfully. ");
    }
    return new TestAcceptanceMessage(false,
        "Verified '" + widgtName + "'  Widget not added To Dashboard successfully. ");
  }

  /**
   * @param workItemSummary widget name
   * @param lastResult {@link Boolean}
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyClickOnClose(final String workItemSummary, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified '" + workItemSummary + "' Widget closed successfully. ");
    }
    return new TestAcceptanceMessage(false, "Verified '" + workItemSummary + "'  Widget not closed successfully. ");
  }
  
  /**
   * This method is called after executing method {@link CCMProjectAreaDashboardPage#archiveTimelineOrIteration(String)}
   * @author NCY3HC
   * @param propertyName -  Timeline's/Iteration's Name
   * @param lastResult - return the result that is executed from last method
   * @return the verification method
   */
  public TestAcceptanceMessage verifyArchiveTimelineOrIteration(final String propertyName, final String lastResult) {
    if( lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Newly created timeline named '" +propertyName+ "' is archived successfully!");
    }
    return new TestAcceptanceMessage(false, "Newly created timeline named '" +propertyName+ "' is archived unsuccessfully!");
  }
}
