/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.dng;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMSectionMenus;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.TestProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactContextMenu;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ModuleEnums;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemLinkSection;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemLinkTypes;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMWorkItemEditorPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMLinksPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstructionPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.text.Link;

/**
 * @author UUM4KOR
 */
public class RMLinksPageVerification extends AbstractRMPage {

  /**
   * @param driverCustom must not be null.
   */
  public RMLinksPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * <p>
   * Verifies if Artifact details page is open after select Link in tab<br>
   * This method called after {@link RMLinksPage#openLinkByFromTab(Map)}.
   *
   * @param additionalParams refers to key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenLinkByFromTab(final Map<String, String> additionalParams,
      final String lastResult) {
    waitForSecs(10);
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(50));
    if (this.driverCustom.isTitleContains(additionalParams.get(ArtifactProperties.ID_NAME.toString()), Duration.ofSeconds(20))) {
      return new TestAcceptanceMessage(true,
          RMConstants.VERIFIED_NAVIGATION_OF +
              additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString()) + "' \n\nActual title '" +
              this.driverCustom.getWebDriver().getTitle() +
              "'.\" Contains Expected text or link is opened as expected. \n\nActual result is '" +
              additionalParams.get(ArtifactProperties.ID_NAME.toString()) + "' link is opened as expected.");
    }
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, RMConstants.VERIFIED_NAVIGATION_OF +
          additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString()) + "' link.\n\nActual result is '" +
          additionalParams.get(ArtifactProperties.ID_NAME.toString()) + "' link is opened as expected.");

    }
    return new TestAcceptanceMessage(false,
        RMConstants.VERIFIED_NAVIGATION_OF + additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString()) +
            "' Link.\n\nActual result is '" + additionalParams.get(ArtifactProperties.ID_NAME.toString()) +
            "' Link is not opened or link does'n exist.");
  }

  /**
   * <p>
   * Verifies after preform selecting from Artifact context menu.<br>
   * This method called after {@link RMLinksPage#clickOnContextMenuOfArtifactInModule(Map)}
   *
   * @param additionalParams map of parameters.
   * @param lastResult is result returned from the action.
   * @return object of TestAcceptanceMessage.
   */
  public TestAcceptanceMessage verifyClickOnContextMenuOrSubMenuForArtifact(final Map<String, String> additionalParams,
      final String lastResult) {
    String artifactContextMenu = additionalParams.get(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString());
    if (artifactContextMenu.equalsIgnoreCase(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString())) {
      try {
        this.engine.findElementWithinDuration(Criteria.isDialog().withTitle(RMConstants.CREATE_LINK), this.timeInSecs)
            .getFirstElement();
        return new TestAcceptanceMessage(true,
            "Verified by visiblity of Create link Dialog.\n\nActual Result is 'Create Link' dialog verifyOpened as expected.");
      }
      catch (Exception e) {
        LOGGER.LOG.error(e.getMessage());
        return new TestAcceptanceMessage(false,
            "Verified by visiblity of Create link Dialog.\n\nActual Result is 'Create Link' dialog not verifyOpened/loaded as expected.");
      }
    }
    return new TestAcceptanceMessage(true, "Context menu other option verification menu not implemented.");
  }

  /**
   * <p>
   * Verifies Adding an Exsiting Item using Create link Dialog. <br>
   * This method called after {@link RMLinksPage#chooseExistingItemFromCreateLinkDialog(Map)}.
   *
   * @param additionalParams map of parameters.
   * @param lastResult is result returned from the action.
   * @return object of TestAcceptanceMessage.
   */
  public TestAcceptanceMessage verifyChooseExistingItemFromCreateLinkDialog(final Map<String, String> additionalParams,
      final String lastResult) {
    String artifactId = additionalParams.get(ArtifactProperties.ID.toString());
    
    waitForSecs(10);
    if (!this.driverCustom.isElementVisible("//button[@disabled and text()= 'OK']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Validated slected item by ok button enable or disable.\n\nActual result is existing item found and selected.\n\nExpected is also same.");
    }
    if (lastResult.contains("disabled")) {
      return new TestAcceptanceMessage(true, "Validated " + artifactId +
          " and ok button enable or disable.\n\nActual result is existing item found and disabled.\n\nExpected is " +
          artifactId + " disabled.\n 'Link To' to same artifact (link to itself) should not be possible!");
    }
    return new TestAcceptanceMessage(false,
        "Validated slected item by ok button enable or disable.\n\nActual result is existing item not found or not selected.\n\nExpected existing item to be selected.");
  }

  /**
   * <p>
   * Verifies if Link is already exsiting when Create link <br>
   * This method called after {@link RMLinksPage#createLinkIfNotAlreadyExists()}.
   *
   * @param lastResult is result returned from the action.
   * @return object of TestAcceptanceMessage.
   */
  public TestAcceptanceMessage verifyCreateLinkIfNotAlreadyExists(final String lastResult) {
    if (lastResult.contains(RMConstants.RM_ARTIFACE_WARNING_MESSAGE_EXPECTED)) {
      return new TestAcceptanceMessage(true,
          "Validated by message displayed after create link.\n\nActual result is Link is to be created.\n\nExpected is one or more link already exists.");
    }
    else if (lastResult.contains("Link created")) {
      return new TestAcceptanceMessage(true,
          "Validated after closing create link dialog.\n\nActual result is Link is to be created.\n\nExpected is one or more link already exists.");
    }
    else {
      return new TestAcceptanceMessage(true,
          "Validated by message displayed after create link.\n\nActual result is Link is to be created.\n\nExpected is link not created.\n" +
              lastResult);
    }
  }

  /**
   * <p>
   * Verifies opened the Link with Link Type: "Satisfies". <br>
   * This method called after {@link RMLinksPage#openSatisfiesLink(Map)}.
   *
   * @param additionalParams refers to key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenSatisfiesLink(final Map<String, String> additionalParams,
      final String lastResult) {
    String idName = additionalParams.get(ArtifactProperties.ID_NAME.toString());
    String xpathDocumentID = String.format("//span[@class='document-id' and contains(text(),'%s')]", (idName.split(":"))[0]);
    String messagePassed = String.format("Verify open Satisfies link.\n\nActual result is Satisfies link with id '%s' is openned successfully.", idName);
    String messageFailed = String.format("Verify open Satisfies link.\n\nActual result is Satisfies link with id '%s' is NOT openned successfully.", idName);
    
    if (this.driverCustom.isElementVisible(xpathDocumentID, Duration.ofSeconds(30))) {
      return new TestAcceptanceMessage(true, messagePassed);
    }
    return new TestAcceptanceMessage(false, messageFailed);
  }
  
  /**
   * <p>
   * Verifies opened the Link with Link Type: "Implemented By". <br>
   * This method called after {@link RMLinksPage#openImplementedByLink(Map)}.
   * @author VDY1HC
   * @param additionalParams list of key value pairs required to validate implemented by link.
   * @param lastResult is result returned from the action.
   * @return true if the link is validated successfully.
   * @throws Exception 
   */
  public TestAcceptanceMessage verifyOpenImplementedByLink(final Map<String, String> additionalParams,
      final String lastResult) throws Exception {
    String wiType = additionalParams.get("WORKITEM_TYPE");
    String workItemID = additionalParams.get(CCMConstants.WORKITEM_ID);
    String artifactID = additionalParams.get(ArtifactProperties.ID.toString());
    boolean flag = false;
    String wiPageTitle = "";
    String tempGetText = "";
    CCMWorkItemEditorPage ccmPage = new CCMWorkItemEditorPage(this.driverCustom);
    // Check which case by wiType
    switch (wiType) {
      case CCMConstants.WORK_ITEMS:
    // Case 1: WI
        wiPageTitle = ccmPage.getWorkItemID();
        if (!wiPageTitle.equals(workItemID)) {
          throw new WebAutomationException ("Error: Expected Work Item ID is - " + workItemID + "\nActual Work Item ID: " + wiPageTitle);
        }
      ccmPage.selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
        try {
          for (WebElement ele : ccmPage.getLinksList(WorkItemLinkSection.LINKS.toString(),
              WorkItemLinkTypes.IMPLEMENTS_REQUIREMENT.toString())) {
            if (ele.getText().startsWith(artifactID + ":")) {
              tempGetText = ele.getText();
              flag = true;
              break;
            } // No need for ELSE here
          }
          //Throw exception when TRY failed, and will be captured by CATCH (Exception not throw automatically because FOR is inside TRY)
          if(!flag) {
          throw new WebAutomationException("artifact " + artifactID + " not visible in " + WorkItemLinkSection.LINKS.toString() + " section");
            }
          }
        catch (WebAutomationException e) {
         //LOGGER.LOG.info("artifact "+artifactID+" not visible in "+WorkItemLinkSection.LINKS.toString()+" section");
          for (WebElement ele : ccmPage.getLinksList(WorkItemLinkSection.PROCESS_LINKS.toString(),WorkItemLinkTypes.IMPLEMENTS_REQUIREMENT.toString())) {
            if (ele.getText().startsWith(artifactID + ":")) {
            tempGetText = ele.getText();
            flag = true;
            break;
            }
          }
          LOGGER.LOG.info("artifact "+artifactID+" visible in "+WorkItemLinkSection.PROCESS_LINKS.toString()+" section");
        }
        break;
      case CCMConstants.PLANS:
        // Case 2: Plans
        wiPageTitle = this.driverCustom.getPresenceOfWebElement("//div[@aria-label='Plan Name']").getText();
        if (!wiPageTitle.contains(workItemID) || !workItemID.contains(wiPageTitle)) {
          throw new WebAutomationException ("Error: Expected page title is - " + workItemID + "\nActual page title: " + wiPageTitle);
        }
      // Click on Links tab
          WebElement lnkTab = this.driverCustom.getPresenceOfWebElement(CCMConstants.LINKS_TAB_IN_CCMPLANPAGE_XPATH);
          this.driverCustom.getClickableWebElement(lnkTab).click();
      this.driverCustom.getWebElement(CCMConstants.LINKS_TAB_IN_CCMPLANPAGE_XPATH).click();
          this.driverCustom.switchToDefaultContent();
      // Verify Artifact ID display
          this.driverCustom.isElementVisible(CCMConstants.LINKTYPE_HEADING_XPATH, timeInSecs);
        if (this.driverCustom.isElementVisible(CCMConstants.IMPLEMENTS_REQUIREMENT_COLLECTION, timeInSecs, artifactID)) {
          WebElement ele = this.driverCustom.getPresenceOfWebElement(CCMConstants.IMPLEMENTS_REQUIREMENT_COLLECTION, artifactID);
            tempGetText = ele.getText();
        flag = true;
      }
        break;
      default:
        throw new Exception("Error: Missing param to target WI type: Plans/ Work Items");
    }
    // Check flag to output result
    if (flag) {
      return new TestAcceptanceMessage(true,
          "Verified by visiblity of implements link under Implemented by coloumn in links tab of work item.\n\nActual result is implements link is visible with id '" +
              tempGetText + "' matched with the expected id '" + artifactID + "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified not found implements link with ID " + artifactID + " in opened work item by id.\n\nActual work item id opened '" + wiPageTitle);
  }

  /**
   * <p>
   * Verifies opened the Link with Link Type: "Requirement Link". <br>
   * This method called after {@link RMLinksPage#openRequirementLink(Map)}. <br>
   *
   * @param additionalParams list of key value pairs required to validate requirement links.
   * @param lastResult returned value of method which is executed just before the method.
   * @return true if the link is validated successfully.
   */
  public TestAcceptanceMessage verifyOpenRequirementLink(final Map<String, String> additionalParams,
      final String lastResult) {

    RMLinksPage rmlp = new RMLinksPage(this.driverCustom);
    if (this.driverCustom.isElementVisible(this.driverCustom.getChildElement(
        rmlp.getWebElementOfSelectedArtifact(additionalParams.get(ArtifactProperties.ID.toString())),
        By.xpath(".//td[@class = 'val-by']//a[@class = 'jazz-ui-ResourceLink' and starts-with(text() , '" +
            additionalParams.get(TestProperties.ID.toString()) + "')]")),
        Duration.ofSeconds(5))) {
      return new TestAcceptanceMessage(true,
          "Verified by visible link under validated by coloumn.\n\nActual result is validated by link with id '" +
              additionalParams.get(TestProperties.ID.toString()) + "' visible under validated by coloumn.");
    }
    return new TestAcceptanceMessage(false,
        "Verified by visible link under validated by coloumn.\n\nActual result is validated by link with id '" +
            additionalParams.get(TestProperties.ID.toString()) + "' not visible under validated by coloumn.");
  }

  /**
   * <p>
   * Verifies opened the Link with Link Type: "Implemented By". <br>
   * This method called after {@link RMLinksPage#openImplementsLinks(Map)}. <br>
   *
   * @param additionalParams list of key value pairs required to validate implements link.
   * @param lastResult returned value of method which is executed just before the method.
   * @return true if the link is validated successfully.
   */
  public TestAcceptanceMessage verifyOpenImplementsLinks(final Map<String, String> additionalParams,
      final String lastResult) {
    waitForSecs(20);
    refresh();
    waitForSecs(20);
    RMLinksPage rmlp = new RMLinksPage(this.driverCustom);
    if (this.driverCustom.isElementVisible(this.driverCustom.getChildElement(
        rmlp.getWebElementOfSelectedArtifact(additionalParams.get(ArtifactProperties.ID.toString())),
        By.xpath(".//td[@class = 'imp-by']//a[@class = 'jazz-ui-ResourceLink' and starts-with(text() , '" +
            additionalParams.get(CCMConstants.WORKITEM_ID) + "')]")),
        Duration.ofSeconds(5))) {
      return new TestAcceptanceMessage(true,
          "Verified by visible link under Implemented by coloumn.\n\nActual result is Implemented by link with id '" +
              additionalParams.get(CCMConstants.WORKITEM_ID) + "' visible under Implemented by coloumn.");
    }

    return new TestAcceptanceMessage(false,
        "Verified by visible link under Implemented by coloumn.\n\nActual result is Implemented by link with id '" +
            additionalParams.get(CCMConstants.WORKITEM_ID) + "' not visible under Implemented by coloumn.");
  }

  /**
   * <p>
   * Verifies opened the Link with Link Type: "Validated By". <br>
   * This method called after {@link RMLinksPage#openValidatedByLink(Map)}. <br>
   *
   * @param additionalParams list of key value pairs required to validate validated by link.
   * @param lastResult returned value of method which is executed just before the method.
   * @return true if the link is validated successfully.
   */
  public TestAcceptanceMessage verifyOpenValidatedByLink(final Map<String, String> additionalParams,
      final String lastResult) {
    // Update verify method for 2 cases: Linked to Test Plan and Linked to Test Case
    boolean flag = false;
    String linkTest = "";
    waitForSecs(10);
    // Case 1: Linked to Test Case - Session: "Requirement Links" will be found
    if (this.driverCustom.isElementClickable(RQMConstants.RQMPROJECT_TESTCASE_SECTIONS_XPATH, Duration.ofSeconds(200),
        RQMSectionMenus.REQUIREMENT_LINKS.toString())) {
      Link reqlink =
          this.engine.findFirstElement(Criteria.isLink().withText(RQMSectionMenus.REQUIREMENT_LINKS.toString()));
      reqlink.click();

      RQMConstructionPage rqmConsPage = new RQMConstructionPage(this.driverCustom);
      for (WebElement ele : rqmConsPage.getRequirementLinks()) {
        linkTest = ele.getText();
        if (ele.getText().startsWith(additionalParams.get(ArtifactProperties.ID.toString()) + ":")) {
          flag = true;
          break;
        }

      }

    }

    // Case 2: Linked to Test Plan - Session: "Requirement Collection Links" will be found
    else if (this.driverCustom.isElementClickable(RQMConstants.RQMPROJECT_TESTCASE_SECTIONS_XPATH, Duration.ofSeconds(200),
        RQMSectionMenus.REQUIREMENT_COLLECTION_LINKS.toString())) {
      Link reqlink02 = this.engine
          .findFirstElement(Criteria.isLink().withText(RQMSectionMenus.REQUIREMENT_COLLECTION_LINKS.toString()));
      reqlink02.click();

      RQMConstructionPage rqmConsPage02 = new RQMConstructionPage(this.driverCustom);
      for (WebElement ele : rqmConsPage02.getRequirementLinks()) {
        linkTest = ele.getText();
        if (ele.getText().startsWith(additionalParams.get(ArtifactProperties.ID.toString()) + ":")) {
          flag = true;
          break;
        }
      }

    }


    // Output message result
    if (flag) {
      return new TestAcceptanceMessage(true,
          "Verified by visiblity of validated link under Requirements link in Test specification.\n\nActual requirement link visible is with id '" +
              linkTest + "' matched with the expected id '" + additionalParams.get(ArtifactProperties.ID.toString()) +
              "'");
    }

    return new TestAcceptanceMessage(true,
        "Verified by visiblity of validated link under Requirements link in Test specification.\n\nActual requirement link visible is with id '" +
            linkTest + "' not matched with the expected id '" + additionalParams.get(ArtifactProperties.ID.toString()) +
            "'");
  }

  /**
   * <p>
   * Verifies remove link from Right side bar if existing.<br>
   * This method called after {@link RMLinksPage#removeLinkIfExists(Map)}.
   *
   * @param additionalParams use to send the data
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyRemoveLinkIfExists(final Map<String, String> additionalParams,
      final String lastResult) {
    String idName = additionalParams.get(ArtifactProperties.ID_NAME.toString());
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified: LINK DELETED - Deleted link under links tab of requirement specification.\n\nActual Result: '" +
              idName + "' link deleted as expected.");
    }
    return new TestAcceptanceMessage(true,
        "Verified: LINK IS NOT EXIST TO REMOVE - Deleted link under links tab of requirement specification.\n\nActual Result: '" +
            idName + "' link is already deleted AS EXPECTED LINK IS NOT EXIST TO REMOVE.");
  }

  /**
   * <p>
   * Verifies link from Right side bar.<br>
   * This method called after {@link RMLinksPage#isLinkExistsInSideBarArea(Map)}.
   *
   * @param additionalParams use to send the data
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsLinkExistsInSideBarArea(final Map<String, String> additionalParams,
      final String lastResult) {
    String sidebarArea = additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString());
    String idName = additionalParams.get(ArtifactProperties.ID_NAME.toString());
    String expectedResult = additionalParams.get("EXPECTED_RESULT");
    boolean verifyWithExpected = true;
    String result = "PASSED";
    if ((expectedResult != null) && !lastResult.equalsIgnoreCase(expectedResult)) {
      verifyWithExpected = false;
      result = "FAILED";
    }
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(verifyWithExpected,
          "Verified: " + result + " - Link under links tab of requirement specification.\n\nActual Result: '" + idName +
              "' link is visible under '" + sidebarArea + " ' section as expected.");
    }
    return new TestAcceptanceMessage(verifyWithExpected,
        "Verified: " + result + " - Link IS NOT under links tab of requirement specification.\n\nActual Result: '" +
            idName + "' link is NOT visible under '" + sidebarArea + " ' section as expected.");
  }

  /**
   * <p>
   * Verifies get all the linked link from Side Bar. <br>
   * This method called after {@link RMLinksPage#getAllLinksFromSelectedView(Map)}.
   *
   * @param additionalParams refers to key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetAllLinksFromSelectedView(final Map<String, String> additionalParams,
      final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Actual value is -------------------- \n>>---->>>> \"" + lastResult + "\".\n------------------------");
    }
    return new TestAcceptanceMessage(false, "Actual value  is - \"" + lastResult + "\".");
  }

  /**
   * This method called after {@link RMLinksPage#getAllLinksFromSelectedArtifact(Map)}.
   *
   * @param additionalParams refers to key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetAllLinksFromSelectedArtifact(final Map<String, String> additionalParams,
      final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Actual value is -------------------- \n>>---->>>> \"" + lastResult +
          "\".\n---------------------------------------------");
    }
    else if (lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Actual value is empty : -------------------- \n>>---->>>> \"" +
          lastResult + "\".\n----------------------------");
    }
    return new TestAcceptanceMessage(false, "Actual value  is - \"" + lastResult + "\".");
  }

  /**
   * <p>
   * Verifies opened the Link with Link Type: "Satisfied By". <br>
   * This method called after {@link RMLinksPage#openSatisfiedByLink(Map)}.
   *
   * @param additionalParams refers to key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenSatisfiedByLink(final Map<String, String> additionalParams,
      final String lastResult) {
    String idName = additionalParams.get(ArtifactProperties.ID_NAME.toString());
    String xpathDocumentID = String.format("//span[@class='document-id' and contains(text(),'%s')]", (idName.split(":"))[0]);
    String messagePassed = String.format("Verify open Satisfied By link.\n\nActual result is Satisfied By link with id '%s' is openned successfully.", idName);
    String messageFailed = String.format("Verify open Satisfied By link.\n\nActual result is Satisfied By link with id '%s' is NOT openned successfully.", idName);
    
    if (this.driverCustom.isElementVisible(xpathDocumentID, Duration.ofSeconds(30))) {
      return new TestAcceptanceMessage(true, messagePassed);
    }
    return new TestAcceptanceMessage(false, messageFailed);
  }

  /**
   * <p>
   * Verifies opened the Link with Link Type: "Satisfied By Architecture Element Link". <br>
   * This method called after {@link RMLinksPage#openSatisfiedByArchitectureElementLink(Map)}.
   *
   * @param additionalParams refers to key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenSatisfiedByArchitectureElementLink(final Map<String, String> additionalParams,
      final String lastResult) {
    // Verify access to correct page:
    this.driverCustom.isElementVisible(RMConstants.COMPONENT_PROJECT_TITLE_XPATH, Duration.ofSeconds(50),
        additionalParams.get(ArtifactProperties.ARTIFACT_CONTAINER.toString()));
    try {
      // CLick on Links tab:
      this.driverCustom.getWebElement(RMConstants.LINKSTAB_IN_DMPAGE_XPATH).click();
      // Wait for Load data after switch to Links tab:
      this.driverCustom.isElementVisible(RMConstants.SATISFIES_IN_LINKSTAB_DMPAGE_XPATH, Duration.ofSeconds(50));
      LOGGER.LOG.info("Info: Current Application is DM");
      // Verify Artifact ID display or not:
      if (this.driverCustom.isElementVisible(RMConstants.ARTIFACTID_IN_LINKSTAB_DMPAGE_XPATH, Duration.ofSeconds(20),
          additionalParams.get(ArtifactProperties.ID.toString()))) {
        return new TestAcceptanceMessage(true,
            "Verified by visible link under satified coloumn.\n\nActual result is Satisfies link with id '" +
                additionalParams.get(ArtifactProperties.ID.toString()) + "' visible under satisfies coloumn.");
      }
    }
    catch (Exception e) {
      LOGGER.LOG.info("Info: Current Application is AM");
      if (this.driverCustom.isElementVisible(RMConstants.ARTIFACTID_IN_LINKSTAB_AMPAGE_XPATH, Duration.ofSeconds(20),
          additionalParams.get(ArtifactProperties.ID.toString()))) {
        return new TestAcceptanceMessage(true,
            "Verified by visible link under satified coloumn.\n\nActual result is Satisfies link with id '" +
                additionalParams.get(ArtifactProperties.ID.toString()) + "' visible under satisfies coloumn.");
      }
    }
    return new TestAcceptanceMessage(false,
        "Verified by visible link under satified coloumn.\n\nActual result is Satisfies link with id '" +
            additionalParams.get(ArtifactProperties.ID.toString()) + "' not visible under satisfies coloumn.");
  }

  /**
   * This method called after {@link RMLinksPage#openSatisfiedByArchitectureElementLink(Map)}.
   *
   * @param id - the id of artifact
   * @param menu menu
   * @param submenu sub menu
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectContextMenuOfArtifactInModule(final String id, final String menu,
      final String submenu, final String lastResult) {
    if (!id.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Successfully selected arifact menu as : '" + menu + "' and submenu as : '" + submenu + "' of ID :" + id);
    }
    return new TestAcceptanceMessage(false,
        "Successfully not selected arifact menu as : '" + menu + "' and submenu as : '" + submenu + "' of ID :" + id);
  }

  /**
   * <p>
   * Verify link is removed successfully after ${@link RMLinksPage#removeLinkOfArtifactFromTable(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param additionalParams store the keys: {@link ArtifactContextMenu#ADD_LINK_TO_ARTIFACT} - Link type <br>
   *          {@link ArtifactProperties#ID} - ID of Artifact link to be removed
   * @param lastResult result from ${@link RMLinksPage#removeLinkOfArtifactFromTable(Map)}
   * @return true if link is removed or vice versa
   */
  public TestAcceptanceMessage verifyRemoveLinkOfArtifactFromTable(final Map<String, String> additionalParams,
      final String lastResult) {
    String ID = additionalParams.get(ArtifactProperties.ID.toString());
    String colName = additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString());
    List<WebElement> listHeaderElm =
        this.driverCustom.getWebElements("//div[@class='com-ibm-rdm-web-grid-ViewHeader']//tr[1][@role='row']/td");
    int i;
    for (i = 0; i <= listHeaderElm.size(); i++) {
      if (listHeaderElm.get(i).getText().trim().equals(colName)) {
        i += 1;
        break;
      }
    }
    String name = this.driverCustom
        .getWebElement("//table[@rowtype='resourceRow']//tr[1]/td[" + Integer.toString(i) + "]").getText();
    if (name.contains(ID)) {
      return new TestAcceptanceMessage(false,
          "Verify remove link with ID '" + ID + "': FAILED.\nLink still be displayed in the table.");
    }
    return new TestAcceptanceMessage(true, "Verify remove link with ID '" + ID + "' PASSED.");
  }

  /**
   * <p>
   * Verify click on sub menu successfully after ${@link RMLinksPage#clickOnContextSubmenu(String)}
   * <p>
   *
   * @author NVV1HC
   * @param optionInSubmenu option: "Open Artifact", "Add Link to Artifact"
   * @param lastResult result from ${@link RMLinksPage#clickOnContextSubmenu(String)}
   * @return true if click on sub menu of artifact successfully or vice versa
   */
  public TestAcceptanceMessage verifyClickOnContextSubmenu(final String optionInSubmenu, final String lastResult) {
    // Case 1: Menu is selected at previous step is 'Open Artifact'
    waitForSecs(5);
    if (optionInSubmenu.contains("Open Artifact")) {
      WebElement resourceID = null;
      WebElement resourceName = null;
      try {
        resourceID = this.driverCustom.getWebElement("//span[@class='resource-id']");
        resourceName = this.driverCustom.getWebElement("//span[@class='resource-title']");
        return new TestAcceptanceMessage(true,
            "Verify select option '" + optionInSubmenu + "' in sub menu successfully!!!\nResource ID is: '" +
                resourceID.getText() + "'.\nResource Name is: '" + resourceName.getText() + "'.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, "Verify select option '" + optionInSubmenu + "' in sub menu failed!!!");
      }
    }

    // Case 2: Menu is selected at previous step is 'Add Link to Artifact'
    try {
      this.driverCustom.getWebElement("//div[@role='dialog']//*[text()='Create Link']");
      return new TestAcceptanceMessage(true, "Select option '" + optionInSubmenu +
          "' in sub menu successfully!!!\nCreate Link dialog is opened as expectation!");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Select option '" + optionInSubmenu +
          "' in sub menu failed!!!\nCreate Link dialog is not opened as expectation!");
    }
  }

  /**
   * <p>
   * Verify create a "Validate By" link between artifact and test case successfully after
   * ${@link RMLinksPage#chooseExistingValidatedLink(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param additionalParams param story the key:{@link TestProperties#CREATE_OR_EXISTING} (with value:
   *          {@link TestProperties#CHOOSE_EXISTING} or @link {@link TestProperties#CREATE_NEW}); <br>
   *          If 'Choose Existing': {@link RMConstants#PROJECT_AREA}; {@link TestProperties#ID}. <br>
   *          If 'Create New': {@link TestProperties#TEST_CASE_NAME} - name of test case to be created. <br>
   * @param lastResult result from ${@link RMLinksPage#chooseExistingValidatedLink(Map)}
   * @return True message if choose existing validated link successfully or vice versa
   */
  public TestAcceptanceMessage verifyChooseExistingValidatedLink(final Map<String, String> additionalParams, final String lastResult) {
    String testCaseName = additionalParams.get(TestProperties.TEST_CASE_NAME.toString());
    String id = additionalParams.get(TestProperties.ID.toString());
    String createOrExistingText = additionalParams.get(TestProperties.CREATE_OR_EXISTING.toString());
    String chooseExisting = TestProperties.CHOOSE_EXISTING.toString();
    
    if (createOrExistingText.equals(chooseExisting)) {
      try {
        this.driverCustom.getPresenceOfWebElement(String.format("//a[contains(@class,'jazz-ui-ResourceLink') and contains(text(),'%s')]", id));
        return new TestAcceptanceMessage(true, "Verify choose existing validated link with ID '" + id + "' PASSED!!!");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, "Verify choose existing validated link with ID '" + id + "' FAILED!!!");
      }
    }
    try {
      this.driverCustom.getWebElements("//td[@class='val-by']//a[@class='jazz-ui-ResourceLink' and contains(text(),'" + testCaseName + "')]");
      return new TestAcceptanceMessage(true, "Verify create a link to a newly created test case with name '" + testCaseName + "' PASSED!!!");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Verify create a link to a newly created test case with name '" + testCaseName + "' PASSED!!!");
    }
  }

  /**
   * @author PDU6HC Verify for method ${@link RMLinksPage#isArtifactLinkPresentforSelectedArtifact(String, String)}
   * @param additionalParam -
   * @param artifactID Artifact id to check the availabel link.
   * @param linkName added links in table ex: Traced By Architecture Element, Validated By, Implemented By...etc
   * @param lastResult result from ${@link RMLinksPage#isArtifactLinkPresentforSelectedArtifact(String, String)}
   * @return true if link existed for artifact or module
   */
  public TestAcceptanceMessage verifyIsArtifactLinkPresentforSelectedArtifact(final String artifactID,
      final String linkName, final String lastResult) {
    LOGGER.LOG.info("Link presence is "+lastResult);
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified PASSED - Link expected: " + linkName + " for Artifact ID: " + artifactID + " existed");
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Link expected: " + linkName + " for Artifact ID: " + artifactID + " is not existed");
  }




  /**
   * <p>
   * Verify Warning Message display or not via expectResult, refer
   * ${@link RMLinksPage#isWarningMessageExistsInArtifactLinksSideBarArea(String)}
   * <p>
   *
   * @author LPH1HC
   * @param expectResult -is expect visibility of Warning Message, true if expect it display, false expect if it hide
   * @param lastResult result from ${@link RMLinksPage#isWarningMessageExistsInArtifactLinksSideBarArea(String)}
   * @return true if visibility of Warning Message equal to expectResult
   */
  public TestAcceptanceMessage verifyIsWarningMessageExistsInArtifactLinksSideBarArea(final String expectResult,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      if (expectResult.equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true, "Warning Message is visible");
      }
      return new TestAcceptanceMessage(true, "There is no warning and error message");
    }
    return new TestAcceptanceMessage(false, "Warning Message and expectResult are not the same");
  }

  /**
   * Verify get number next to Artifact Links in Side Bar Area, refer
   * ${@link RMLinksPage#getNumberOfArtifactLinksInSideBarArea()} <br>
   *
   * @author LPH1HC <br>
   * @param additionparam expect number get from Artifact Links in Side Bar Area <br>
   * @param lastResult result from ${@link RMLinksPage#getNumberOfArtifactLinksInSideBarArea()} <br>
   * @return validation message <br>
   */
  public TestAcceptanceMessage verifyGetNumberOfArtifactLinksInSideBarArea(final String additionparam,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase(additionparam)) {
      return new TestAcceptanceMessage(true,
          "Get Number of Artifact Links in Side Bar Area successfully. The number is " + lastResult);
    }
    return new TestAcceptanceMessage(false, "Get Number of Artifact Links in Side Bar Area fail");
  }

  /**
   * Verify click On Add Artifact Link From Right Side Bar refer
   * ${@link RMLinksPage#clickOnAddArtifactLinkFromRightSideBar(Map)} <br>
   *
   * @author LPH1HC <br>
   * @param additionalParams stores keys: {@link ModuleEnums#SIDE_BAR_AREA} - Section contains linked links (Ex:
   *          'Links', 'Module Links') <br>
   * @param lastResult result from ${@link RMLinksPage#clickOnAddArtifactLinkFromRightSideBar(Map)} <br>
   * @return validation message <br>
   */
  public TestAcceptanceMessage verifyclickOnAddArtifactLinkFromRightSideBar(final Map<String, String> additionalParams,
      final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Click on Add Artifact Link From Right Side bar successfully");
    }
    return new TestAcceptanceMessage(false, "Click on Add Artifact Link From Right Side bar fail");
  }

  /**
   * Verify click On Add Artifact Link From Right Side Bar refer ${@link RMLinksPage#chooseExistingImplementedLink(Map)}
   * <br>
   *
   * @author LPH1HC <br>
   * @param additionalParams stores keys: {@link RMConstants#PROJECT_AREA}, {@link CCMConstants#WORKITEM_ID}<br>
   * @param lastResult result from ${@link RMLinksPage#chooseExistingImplementedLink(Map)} <br>
   * @return validation message <br>
   */
  public TestAcceptanceMessage verifyChooseExistingImplementedLink(final Map<String, String> additionalParams,
      final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Choose Existing Implemented Link successfully");
    }
    return new TestAcceptanceMessage(false, "Choose Existing Implemented Link fail");
  }

  /**
   * @author LPH1HC <br>
   *         Verify for method ${@link RMLinksPage#clickOnAttributeLinkFromRightSideBar(Map)} <br>
   * @param additionalParams - <br>
   * @param lastResult - result from ${@link RMLinksPage#clickOnAttributeLinkFromRightSideBar(Map)} <br>
   * @return validation message <br>
   */
  public TestAcceptanceMessage verifyClickOnAttributeLinkFromRightSideBar(final Map<String, String> additionalParams,
      final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Verify click on " + additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()) + " successfully");
    }
    return new TestAcceptanceMessage(false,
        "Verify click on " + additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()) + " fail");
  }

  /**
   * @author PDU6HC <br>
   *         Verify for method ${@link RMLinksPage#selectModuleInCreateLinkDialog} <br>
   * @param moduleId - Input moduleId <br>
   * @param lastResult - result from ${@link RMLinksPage#selectModuleInCreateLinkDialog} <br>
   * @return validation message <br>
   */
  public TestAcceptanceMessage verifySelectModuleInCreateLinkDialog(final String moduleId, final String lastResult) {
    if (this.driverCustom.isElementPresent("//span[@class='matching-results']", this.timeInSecs)) {
      return new TestAcceptanceMessage(true,
          "Verified PASSED - Click on modules radio button and input Modules ID successfully ");
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Can't click on modules radio button or input Modules ID failed ");
  }

  /**
   * @author PDU6HC Verify for method ${@link RMLinksPage#chooseSatisfiedOrLinkTo}
   * @param additionalParams stores keys: {@link ArtifactProperties#ID}
   * @param lastResult - result from ${@link RMLinksPage#chooseSatisfiedOrLinkTo}
   * @return validation message
   */
  public TestAcceptanceMessage verifyChooseSatisfiedOrLinkTo(final Map<String, String> additionalParams,
      final String lastResult) {
    if (!this.driverCustom.isElementPresent(RMConstants.XPATH_OK_BUTTON_CREATE_TERM_DIALOG, this.timeInSecs)) {
      return new TestAcceptanceMessage(true, "Verified PASSED - Created link to artifact is selected successfully");
    }
    return new TestAcceptanceMessage(false, "Verified FAILED - Created link to artifact is not selected failed");
  }

  /**
   * @author PDU6HC Verify for method ${@link RMLinksPage#chooseExistingSatisfiedArchitectureLink}
   * @param additionalParams stores keys: {@link ArtifactProperties#ARTIFACT_CONTAINER} - Name of Project area contains
   *          Diagram <br>
   *          {@link ArtifactProperties#DIAGRAM_NAME} - Name of Diagram to link with. <br>
   *          {@link ArtifactProperties#ARTIFACT_COMPONENT} - Component contains Diagram artifact.
   * @param lastResult - result from ${@link RMLinksPage#chooseExistingSatisfiedArchitectureLink}
   * @return link is added to artifact
   */
  public TestAcceptanceMessage verifyChooseExistingSatisfiedArchitectureLink(final Map<String, String> additionalParams,
      final String lastResult) {
    if (lastResult.equals("true")) {
      return new TestAcceptanceMessage(true, "Verified PASSED - Selected link to artifact is selected successfully");
    }
    return new TestAcceptanceMessage(false, "Verified FAILED - Selected link to artifact is not selected failed");
  }

  /**
   * @author PDU6HC Verify for method ${@link RMLinksPage#verifyLinkExistedForMultipleArtifacts}
   * @param additionalParams stores keys: linkName - name of link you want to verify. id1 - id of artifact 1. id2 - id
   *          of artifact 2. so on so forth...
   * @param lastResult - result from ${@link RMLinksPage#chooseExistingSatisfiedArchitectureLink}
   * @return all artifact have existed link
   */
  public TestAcceptanceMessage verifyVerifyLinkExistedForMultipleArtifacts(final Map<String, String> additionalParams,
      final String lastResult) {
    if (lastResult.equals("true")) {
      return new TestAcceptanceMessage(true,
          "Verified PASSED - All artifacts have existed link. Expected link name: " + additionalParams.get("linkName"));
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - All artifacts doesn't have existed link. Expected link name: " +
            additionalParams.get("linkName"));
  }

  /**
   * Verify for method ${@link RMLinksPage#refreshPageAfterPeriodOfTime(String)}
   * @author VDY1HC
   * @param timeInSeconds - time in seconds
   * @param lastResult - last result
   * @return - true
   */
  public TestAcceptanceMessage verifyRefreshPageAfterPeriodOfTime (String timeInSeconds, final String lastResult) {
    return new TestAcceptanceMessage(true, "Verify: Page refresh after - " + timeInSeconds + " - seconds.");
  }

  /**
   * @param additionalParams -
   * @param additionalParam_pageTitle -
   * @param additionalParam_linkType -
   * @param lastResult -
   * @return -
   */
  public TestAcceptanceMessage verifyOpenLinkIfExists(final Map<String, String> additionalParams,
      final String additionalParam_pageTitle, final String additionalParam_linkType, final String lastResult) {

    waitForSecs(Duration.ofSeconds(20));
    this.driverCustom.waitForSecs(Duration.ofSeconds(15));
    String actualPageTitle = this.driverCustom.getCurrentPageTitle();
    if (actualPageTitle.contains(additionalParam_pageTitle) || additionalParam_linkType.contains("Implemented By")) {
      return new TestAcceptanceMessage(true, "Verified: Title of the page contains " + additionalParam_pageTitle +
          " .\n Clicked on the " + additionalParam_linkType + " link '" + additionalParam_pageTitle + "' successfully");
    }
    if (actualPageTitle.contains(additionalParam_pageTitle) || additionalParam_linkType.contains("Validated By")) {
      return new TestAcceptanceMessage(true, "Verified: Title of the page contains " + additionalParam_pageTitle +
          " .\n Clicked on the " + additionalParam_linkType + " link '" + additionalParam_pageTitle + "' successfully");
    }

    return new TestAcceptanceMessage(false,
        "Verified title of the page.\n\nActual result title of the page not contains expected id '" +
            additionalParams.get(CCMConstants.WORKITEM_ID) + "'.");
  }


  /**
   * @param attributeSummary -
   * @param lastResult - 
   * @return - 
   */
  public TestAcceptanceMessage verifyGetLinkID(final String attributeSummary, final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified new Link ID'" + lastResult + "' created successfully");
    }
    return new TestAcceptanceMessage(false, "Verified new link Not created successfully");
  }
}