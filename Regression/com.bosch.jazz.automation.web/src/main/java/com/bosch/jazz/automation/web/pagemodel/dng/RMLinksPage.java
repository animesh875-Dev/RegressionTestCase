/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMSectionMenus;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.TestProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.AddLinkToArtifact;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactContextMenu;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.DiagramEnums;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.LinkContextMenu;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ModuleEnums;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.SearchTypeEnums;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemLinkSection;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemLinkTypes;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMWorkItemEditorPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.element.text.TextField;
import com.bosch.psec.web.test.finder.container.RowFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;
import com.bosch.psec.web.test.finder.text.TextFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.treeNode.JazzTreeNodeFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.JazzTextEditorFinder;
import finder.text.JazzTextFinder;
import finder.text.label.JazzSpanLabelFinder;
import finder.text.textField.JazzSpanTextFieldFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * Represents the RM Links Page this is common for Artifacts, Modules and Collections pages
 */
public class RMLinksPage extends AbstractRMPage {

  /**
   *
   */
  public static final String LINK_WITH_ID = "Link with id ";
  /**
   * @param driverCustom set value to instance variable of WebCustom of this class.
   */
  public RMLinksPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTreeNodeFinder(), new JazzDropdownFinder(), new JazzButtonFinder(),
        new JazzSpanLabelFinder(), new JazzRowFinder(driverCustom.getWebDriver()), new JazzDialogFinder(),
        new RowCellFinder(), new RowFinder(), new LinkFinder(), new JazzTextFinder(), new JazzSpanTextFieldFinder(),
        new TextFinder(), new TextFieldFinder(), new JazzTextFieldFinder(), new JazzTextEditorFinder());
  }

  /**
   * Creates link from artifacts or modules(Such as Validated By , Implemented By,Link To..... etc) after open 'Create
   * Link' dialog <br>
   *
   * @param additionalParams stores keys: {@link ArtifactContextMenu#ADD_LINK_TO_ARTIFACT} - Link type <br>
   *          and Data base on type of link selected:<br>
   *          -- LINK_TO, SATISFIED_BY, LINK_FROM, SATISFIES: {@link ArtifactProperties#ID}. <br>
   *          -- VALIDATED_BY: Existing - {@link RMConstants#PROJECT_AREA}, {@link TestProperties#ID} or Create new -
   *          {@link TestProperties#TEST_CASE_NAME}. <br>
   *          -- IMPLEMENTED_BY: {@link RMConstants#PROJECT_AREA}, {@link CCMConstants#WORKITEM_ID} <br>
   *          -- SATISFIED_BY_ARCHITECTURE_ELEMENT: {@link ArtifactProperties#ARTIFACT_CONTAINER},
   *          {@link ArtifactProperties#DIAGRAM_NAME}
   * @return -
   */
  public String chooseExistingItemFromCreateLinkDialog(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String linkType = additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString());
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE_01, Duration.ofSeconds(180));
    // Work arround for 11Q since the iFrame of target PA will not shown if user does not have permission to access to the target PA
    if (!this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH, Duration.ofSeconds(15))) {      
      String strCBBLinkTypeXpath = "//table[contains(@class,'MenuActive')]//td[normalize-space(text())='%s']";
//      WebElement cbbLinkType = this.driverCustom.getWebElement(strCBBLinkTypeXpath);      
//      cbbLinkType.click();
      String linkTypeDropDownXpath = "//label[text()='Link type:']/parent::td/following-sibling::td[contains(@class,'link-type')]//table[@role='listbox']";
      this.driverCustom.click(linkTypeDropDownXpath);
      this.driverCustom.click(String.format(strCBBLinkTypeXpath, linkType));
    }  
    // End of Work arround for 11Q ------
    if (linkType.equals(AddLinkToArtifact.LINK_TO.toString()) ||
        linkType.equals(AddLinkToArtifact.SATISFIED_BY.toString()) ||
        linkType.equals(AddLinkToArtifact.LINK_FROM.toString()) ||
        linkType.equals(AddLinkToArtifact.SATISFIES.toString())||
        linkType.equals(AddLinkToArtifact.CAT_AUTOMATION_OUT.toString())||
        linkType.equals(AddLinkToArtifact.CAT_AUTOMATION_IN.toString())||
        linkType.equals(AddLinkToArtifact.CAT_LINK_OUT.toString())||
        linkType.equals(AddLinkToArtifact.CAT_LINK_IN.toString())) {
      return chooseSatisfiedOrLinkTo(additionalParams);
    }
    else if (linkType.equals(AddLinkToArtifact.VALIDATED_BY.toString())) {
      chooseExistingValidatedLink(additionalParams);
      return null;
    }
    else if (linkType.contains(AddLinkToArtifact.IMPLEMENTED_BY.toString())) {
      chooseExistingImplementedLink(additionalParams);
      return null;
    }
    else if (linkType.contains(AddLinkToArtifact.SATISFIED_BY_ARCHITECTURE_ELEMENT.toString())) {
      chooseExistingSatisfiedArchitectureLink(additionalParams);
      return null;
    }
    throw new WebAutomationException("Add link to artifact not matching any of the links.\nPlease provide valide link type.");
  }

  /**
   * <p>
   * After open Create Links dialog {@link RMLinksPage#chooseExistingItemFromCreateLinkDialog} with Link Type
   * {@link AddLinkToArtifact#LINK_TO or AddLinkToArtifact#SATISFIED_BY or AddLinkToArtifact#LINK_FROM or
   * AddLinkToArtifact#SATISFIES} <br>
   * Try to 'Clear all filters' of the search box inside the dialog if there is an existing filter <br>
   * Search Artifact ID and Click on 'OK' button
   * 
   * @author KYY1HC
   * @param additionalParams stores keys: {@link ArtifactProperties#ID}
   * @return string
   */
  public String chooseSatisfiedOrLinkTo(final Map<String, String> additionalParams) {
    String linkType = additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString());
    String artifactId = additionalParams.get(ArtifactProperties.ID.toString());
    String moduleId = additionalParams.get(ArtifactProperties.MODULE_ID.toString())!= null 
        ? additionalParams.get(ArtifactProperties.MODULE_ID.toString()) : "";
    String componentName = additionalParams.get(ArtifactProperties.ARTIFACT_COMPONENT.toString())!= null 
        ? additionalParams.get(ArtifactProperties.ARTIFACT_COMPONENT.toString()) : "";
        waitForSecs(5); try {
         // Dropdown drdAction = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withToolTip("Add Link to Artifact"), Duration.ofSeconds(20));
        //  drdAction.click();
          String drdOption = "//table[contains(@class,'MenuActive')]//td[normalize-space(text())='%s']";
        // if(this.driverCustom.isElementInvisible(String.format(drdOption, linkType), Duration.ofSeconds(10))) {
            // In case, the link type is not displayed after clicking on Add Link to Artifact button
            if(this.driverCustom.isElementVisible(String.format(drdOption, "More..."), Duration.ofSeconds(10))) {
              this.driverCustom.click(String.format(drdOption, "More..."));
           //   if (this.driverCustom.isElementVisible("//label[text()='Link type:']//..//..//span/descendant::input[@value='▼ ']", Duration.ofSeconds(40))) {   
            //label[text()='Link type:']//..//..//span/descendant::input[@value='▼ ']
             
              
           //   }
              }
          }catch (Exception e) {
          // TODO: handle exception
        } 
    Dialog dlgCreateArtifact = this.engine
        .findFirstElementWithDuration(Criteria.isDialog().withTitle(RMConstants.CREATE_LINK), this.timeInSecs);
    LOGGER.LOG.info("Create Link Dialog opened.");

    // Clear all filter if it is displayed.
    try {
      Button btnClearFilter = this.engine.findFirstElementWithDuration(
          Criteria.isButton().withToolTip(RMConstants.CLEAR_FILTER_BUTTON_TOOLTIP).inContainer(dlgCreateArtifact), Duration.ofSeconds(10));
      btnClearFilter.click();
      waitForSecs(3);
      LOGGER.LOG.info(RMConstants.CLICKED_ON + RMConstants.CLEAR_FILTER_BUTTON_TOOLTIP + RMConstants.BUTTON);
    }
    catch (Exception e) {
      LOGGER.LOG.info("No 'Clear all filters' button found!");
    }
    
    // Check the default value of Component Drop-down list
    if (this.driverCustom.isElementVisible("//input[@aria-invalid='false' and contains(@title,'DYNAMIC_VAlUE')]", Duration.ofSeconds(20), componentName)) {
      LOGGER.LOG.info("Component selection Drop down is disabled.Used the default component name.");
    }
    else {
      // Select Component.
      if (!componentName.equalsIgnoreCase("")) {
        String xpathComponentDropDown = "//input[@aria-invalid='false' and contains(@class,'rdmComponentsFilteringSelect')]";
        WebElement txtComponent = this.driverCustom.getWebElement(xpathComponentDropDown);
        txtComponent.clear();
        txtComponent.sendKeys(componentName);
        waitForSecs(2);
        txtComponent.sendKeys(Keys.ENTER);
        this.driverCustom.click(RMConstants.RMARTIFACTPAGE_CREATELINKDIALOG_COMPONENT_DROPDOWNBUTTON_XPATH);
        LOGGER.LOG.info("Selected Component: " + componentName);
        waitForSecs(5);
      }
    }
    
    // Check Module or Folder radio and input module id to select.
    if (!moduleId.equalsIgnoreCase("")) {
      selectModuleInCreateLinkDialog(moduleId);
      LOGGER.LOG.info("Selected Module: " + moduleId);
    }

    // Search Artifact ID
    Text txtFilterArtifact = this.engine.findFirstElementWithDuration(Criteria.isTextField()
          .withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID).inContainer(dlgCreateArtifact), Duration.ofSeconds(30));
    txtFilterArtifact.setText(artifactId + Keys.ENTER);
    waitForSecs(3);
    LOGGER.LOG.info(artifactId + " ID entered to search Artifact.");
    
    try {
    // Click on Link
      String xpathArtifactLink = "//a[@class='jazz-ui-ResourceLink'][contains(text(),'DYNAMIC_VAlUE')]/following-sibling::span";
    WebElement lnkArtifacts = this.driverCustom.getWebElement(xpathArtifactLink, artifactId);
    lnkArtifacts.click();
    LOGGER.LOG.info("Clicked on Link with ID " + artifactId);
    
    // Click on OK button
    Button btn = this.engine.findFirstElementWithDuration(Criteria.isButton().withText(RMConstants.OK), this.timeInSecs);
    btn.click();
    waitForSecs(5);
    LOGGER.LOG.info(RMConstants.CLICK_ON_OK_BUTTON);
    }
    catch (Exception e) {
      this.driverCustom.getPresenceOfWebElement("//a[@style='display: block; color: rgb(204, 204, 204);']");
      LOGGER.LOG.info(artifactId + " is  disabled. \nNot able to select");
      return "disabled";
    }
    return "true";
  }

  /**
   * <p>
   * After open Create Links dialog {@link RMLinksPage#chooseExistingItemFromCreateLinkDialog} using
   * {@link AddLinkToArtifact#VALIDATED_BY} <br>
   * Select 'Choose Existing' or 'Create New' radion button for {Create link with existing artifact} or {Create link
   * with newly created artifact} using {@link TestProperties#CREATE_OR_EXISTING}. <br>
   * --If {@link TestProperties#CHOOSE_EXISTING} is selected, no need to select on radio button. <br>
   * -----Input Project area name into 'Artifact Container' dropdown using {@link RMConstants#PROJECT_AREA}. <br>
   * -----Open 'Inline Filters' and search artifact using ID and Click OK button. <br>
   * --If {@link TestProperties#CREATE_NEW} is selected, click on radion button with following text. <br>
   * -----Input Test case name for new Test case and Click OK button. <br>
   *
   * @param additionalParams stores keys: {@link TestProperties#CREATE_OR_EXISTING} (with value:
   *          {@link TestProperties#CHOOSE_EXISTING} or @link {@link TestProperties#CREATE_NEW}); <br>
   *          If 'Choose Existing': {@link RMConstants#PROJECT_AREA}; {@link TestProperties#ID}. <br>
   *          If 'Create New': {@link TestProperties#TEST_CASE_NAME} - name of test case to be created.
   */
  public void chooseExistingValidatedLink(final Map<String, String> additionalParams) {
    String chooseExistingString = TestProperties.CHOOSE_EXISTING.toString();
    String createOrExistingText = additionalParams.get(TestProperties.CREATE_OR_EXISTING.toString());
    String id = additionalParams.get(TestProperties.ID.toString());
    String projectArea = additionalParams.get(RMConstants.PROJECT_AREA);
    String component = additionalParams.get("TEST_CASE_COMPONENT");
    
    // Create with 'Choose Existing'
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE_01, Duration.ofSeconds(60));
    if (createOrExistingText.equals(chooseExistingString)) {
      this.driverCustom.isElementClickable(RMConstants.RM_ARTIFACRPAGE_TESTSPEC_CREATEOREXISTS_RADIO_XPATH, Duration.ofSeconds(15), chooseExistingString);
      Label label = this.engine.findFirstElementWithDuration(Criteria.isLabel().withText(createOrExistingText), Duration.ofSeconds(20));
      label.click();
      if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_ARTIFACTCONTAINER_XPATH, Duration.ofSeconds(20))) {
        WebElement elements = this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_ARTIFACTCONTAINER_TEXTBOX_XPATH);
        elements.clear();
        elements.sendKeys(projectArea);
        waitForSecs(5);
        try {
          this.driverCustom.click(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, projectArea);
          LOGGER.LOG.info("Selected " + projectArea + " Project area from Project area dropdown");
          waitForSecs(3);
        }
        catch (Exception e) {
          LOGGER.LOG.error(e.getMessage());
          throw new WebAutomationException(projectArea + " not valid/not found in drop dowm.\n" + e.getMessage());
        }
      }
      LOGGER.LOG.info(RMConstants.CLICKED_ON + createOrExistingText + RMConstants.BUTTON);
      this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
      
    // Select from Component drop down if existing
      if (this.driverCustom.isElementVisible("//label[text()='Component:']",timeInSecs)) {
        if (this.driverCustom.isElementVisible("//label[text()='Component:']/../../following-sibling::td/div[text()='"+component+"']",
            this.timeInSecs)) {
          LOGGER.LOG.info("By Default component is selected.");
          this.driverCustom.switchToDefaultContent();
        }
        else {
          Dropdown drdComponent =
              this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Component:"), Duration.ofSeconds(60)).getFirstElement();
          drdComponent.selectOptionWithText(component);
          LOGGER.LOG.info(" Selected '" + component +"' Component from 'Create Link' dialog.");
          waitForSecs(2);
          this.driverCustom.switchToDefaultContent();
        }
      }
      this.driverCustom.isElementClickable(RMConstants.RM_ARTIFACRPAGE_TESTSPEC_CREATEOREXISTS_RADIO_XPATH, Duration.ofSeconds(20), chooseExistingString);
      //iFrame available on 12Q but not on 23Q, put switchToFrame method inside if()
      if(this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH, Duration.ofSeconds(20))) {
        this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
      }
      if (!this.driverCustom.isElementVisible(RMConstants.RM_ARTIFACTSPAGE_TESTSPEC_ID_TEXTFEILD_XPATH, Duration.ofSeconds(20))) {
        this.driverCustom.isElementClickable(RQMConstants.RQMPROJECT_SHOW_SLIDERDOWN_DROPDOWN_XPATH, Duration.ofSeconds(20));
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_SHOW_SLIDERDOWN_DROPDOWN_XPATH).click();
        LOGGER.LOG.info(RMConstants.CLICKED_ON + "Slide Down" + RMConstants.BUTTON);
      }
      
      //Clear all filter before input text into search box
      Button btnClearAllFilter = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip(RMConstants.CLEAR_FILTER_BUTTON_TOOLTIP), Duration.ofSeconds(10));
      btnClearAllFilter.click();
      
      this.driverCustom.switchToDefaultContent();
      this.driverCustom.switchToFrame(RMConstants.RMPROJECT_CREATELINK_IFRAME_XPATH);
      this.driverCustom.isElementClickable(RMConstants.RM_ARTIFACTSPAGE_TESTSPEC_ID_TEXTFEILD_XPATH, Duration.ofSeconds(30));
      this.driverCustom.typeText(RMConstants.RM_ARTIFACTSPAGE_TESTSPEC_ID_TEXTFEILD_XPATH, id);
      LOGGER.LOG.info("Searched for id " + id);
      
      this.driverCustom.isElementClickable(RMConstants.RM_ARTIFACRPAGE_TESTSPEC_RUN_BUTTON_XPATH, Duration.ofSeconds(20));
      Button btnApplyAllFilters = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip(RMConstants.APPLY_ALL_FILTERS_TOOLTIP), Duration.ofSeconds(10));
      btnApplyAllFilters.click();
      waitForSecs(3);
      LOGGER.LOG.info(RMConstants.CLICKED_ON + RMConstants.APPLY_ALL_FILTERS_TOOLTIP + RMConstants.BUTTON);
      
      this.driverCustom.switchToDefaultContent();
      this.driverCustom.switchToFrame(RMConstants.RMPROJECT_CREATELINK_IFRAME_XPATH);
      this.driverCustom.getPresenceOfWebElement(RMConstants.RM_ARTIFACRPAGE_TESTSPEC_CHECKBOX_BUTTON_XPATH, id);
      this.driverCustom.getWebElement(RMConstants.RM_ARTIFACRPAGE_TESTSPEC_CHECKBOX_BUTTON_XPATH, id).click();
      LOGGER.LOG.info(RMConstants.CLICKED_ON + id + RMConstants.CHECKBOX);
      waitForSecs(Duration.ofSeconds(10));
      Button btn = this.engine.findFirstElementWithDuration(Criteria.isButton().withText(RMConstants.OK), Duration.ofSeconds(30));
      btn.click();
      
      waitForSecs(Duration.ofSeconds(10));
      LOGGER.LOG.info(RMConstants.CLICK_ON_OK_BUTTON);
    } 
    else { // Create with 'Create New'
      String createNewText = TestProperties.CREATE_NEW.toString();
      String testCaseName = additionalParams.get(TestProperties.TEST_CASE_NAME.toString());
      
      this.driverCustom.isElementClickable(RMConstants.RMMODULEINSIDEPAGE_CREATELINKNEWTESTCASE_BUTTON_XPATH, Duration.ofSeconds(30));
      Label label = this.engine.findFirstElementWithDuration(Criteria.isLabel().withText(createNewText), Duration.ofSeconds(20));
      label.click();
      LOGGER.LOG.info("Clicked on Create New button");
      this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEINSIDEPAGE_CREATELINKTESTCASE_TEXTBOX_XPATH, RMConstants.NAME);
      this.driverCustom.click(RMConstants.RMMODULEINSIDEPAGE_CREATELINKTESTCASE_TEXTBOX_XPATH, RMConstants.NAME);
      this.driverCustom.typeText(RMConstants.RMMODULEINSIDEPAGE_CREATELINKTESTCASE_TEXTBOX_XPATH, testCaseName, RMConstants.NAME);
      this.driverCustom.isElementVisible(RMConstants.RMMODULEINSIDEPAGE_CREATELINKTESTCASEOK_BUTTON_XPATH, Duration.ofSeconds(30));
      
      Button btn = this.engine.findFirstElementWithDuration(Criteria.isButton().withText(RMConstants.OK), Duration.ofSeconds(30));
      btn.click();
      waitForSecs(Duration.ofSeconds(10));
      LOGGER.LOG.info(RMConstants.CLICK_ON_OK_BUTTON);
      this.driverCustom.getWebDriver().switchTo().defaultContent();
    }
    
    waitForSecs(Duration.ofSeconds(5));
  }

  /**
   * <p>
   * After open Create Links dialog {@link RMLinksPage#chooseExistingItemFromCreateLinkDialog} using
   * {@link AddLinkToArtifact#IMPLEMENTED_BY}. <br>
   * Input Project area name into 'Artifact Container' dropdown using {@link RMConstants#PROJECT_AREA}. <br>
   * Select 'Type' drop down to show all Type of Work Item Search for Work Item in search box Un-check checkbox to show
   * all Work Items. <br>
   * Search for Work Item in search box and Click OK button. <br>
   *
   * @param additionalParams stores keys: {@link RMConstants#PROJECT_AREA}, {@link CCMConstants#WORKITEM_ID}.
   */
  public void chooseExistingImplementedLink(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    // Module Type and Information Type
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_ARTIFACTCONTAINER_XPATH, Duration.ofSeconds(40))) {
      WebElement ele = this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_ARTIFACTCONTAINER_TEXTBOX_XPATH);
      ele.clear();
      ele.sendKeys(additionalParams.get(RMConstants.PROJECT_AREA));
      waitForSecs(2);
      try {
        this.driverCustom.click(RMConstants.JAZZADMIN_SPANSELECTION_XPATH,
            additionalParams.get(RMConstants.PROJECT_AREA));
        LOGGER.LOG.info(additionalParams.get(RMConstants.PROJECT_AREA) + " Project area from Project area dropdown " +
            RMConstants.IS_SELECTED);
      }
      catch (Exception e) {
        throw new WebAutomationException(
            additionalParams.get(RMConstants.PROJECT_AREA) + " not valid/not found in drop dowm.\n" + e.getMessage());
      }
    }
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.isElementVisible(RMConstants.JAZZ_MESSAGE_SUMMARY_XPATH, Duration.ofSeconds(5));
    this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);

    if (this.driverCustom.isElementVisible(RMConstants.RM_ARTIFACTSPAGE_TYPE_SELECT_LISTBOX_XPATH, Duration.ofSeconds(5))) {// Information
      // Type
      this.driverCustom.select(RMConstants.RM_ARTIFACTSPAGE_TYPE_SELECT_LISTBOX_XPATH, "(Show All)",
          SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
      LOGGER.LOG.info(RMConstants.CLICKED_ON + "Show all Button to show all type of work item.");
      this.driverCustom.typeText(RMConstants.RMMODULEINSIDEPAGE_IMPLEMENTED_TEXTBOX_XPATH,
          additionalParams.get(CCMConstants.WORKITEM_ID));
      LOGGER.LOG.info("Work item ID entered as " + additionalParams.get(CCMConstants.WORKITEM_ID));
      waitForSecs(2);
    }
    else { // Module Type
      this.driverCustom.typeText(RMConstants.RMMODULEINSIDEPAGE_IMPLEMENTED_TEXTBOX_XPATH,
          additionalParams.get(CCMConstants.WORKITEM_ID));
      LOGGER.LOG.info("Work item ID entered as " + additionalParams.get(CCMConstants.WORKITEM_ID));
      waitForSecs(2);
    }

    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_CREATELINK_SHOWRELATED_WORKITEM_XPATH, Duration.ofSeconds(20))) {
      // Information Type
      this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_CREATELINK_SHOWRELATED_WORKITEM_XPATH);
      waitForSecs(2);
    }
    else {
      // Module Type
    }

    if (this.driverCustom.isElementVisible(RMConstants.RM_ARTIFACTSPAGE_QUERY_SELECT_LISTBOX_XPATH, Duration.ofSeconds(20))) {
      // Information Type
      this.driverCustom.select(RMConstants.RM_ARTIFACTSPAGE_QUERY_SELECT_LISTBOX_XPATH,
          additionalParams.get(CCMConstants.WORKITEM_ID), SelectTypeEnum.SELECT_BY_VALUE);
    }
    else {
      // Module Type
      this.driverCustom.getWebElement(RMConstants.RMMODULEINSIDEPAGE_IMPLEMENTED_LISTWI_XPATH,
          additionalParams.get(CCMConstants.WORKITEM_ID)).click();
    }

    LOGGER.LOG.info(additionalParams.get(CCMConstants.WORKITEM_ID) + RMConstants.IS_SELECTED);
    waitForSecs(2);
    try {
    Button btn = this.engine.findFirstElementWithDuration(Criteria.isButton().withText(RMConstants.OK), Duration.ofSeconds(30));
    btn.click();
    LOGGER.LOG.info(RMConstants.CLICK_ON_OK_BUTTON);
    }
    catch(Exception e) {
      waitForSecs(1);
    }
    waitForSecs(Duration.ofSeconds(10));
    
    this.driverCustom.switchToDefaultContent();
  }


  /**
   * <p>
   * After open Create Links dialog {@link RMLinksPage#chooseExistingItemFromCreateLinkDialog} using
   * {@link AddLinkToArtifact#SATISFIED_BY_ARCHITECTURE_ELEMENT}. <br>
   * Input Project area name into 'Artifact Container' dropdown using {@link RMConstants}. <br>
   * Set 'Search Type' with value {@link SearchTypeEnums#SEARCH}, and search for Diagram with name
   * {@link ArtifactProperties#DIAGRAM_NAME} <br>
   * If diagram is found, select the founded diagram and click OK. If no diagram found, click cancel to close the
   * 'Create Link' dialog. <br>
   *
   * @param additionalParams stores keys - use all the required value to work for both AM and DM
   * @return true if saving message is invisible
   */
  @SuppressWarnings("unused")
  public boolean chooseExistingSatisfiedArchitectureLink(final Map<String, String> additionalParams) {
    String dmProject = additionalParams.get(ArtifactProperties.ARTIFACT_CONTAINER.toString());
    String diagramName = additionalParams.get(ArtifactProperties.DIAGRAM_NAME.toString());
    String componentName = additionalParams.get(ArtifactProperties.ARTIFACT_COMPONENT.toString());
    String associateText = additionalParams.get("ASSOCIATE_BUTTON");
    String ccmProjectArea = additionalParams.get("CCM_PROJECTAREA");
    String isLinkToExisting = additionalParams.get("IS_LINK_EXISTING");
    String workItemID = additionalParams.get("WORKITEM_ID");
    String existingType = additionalParams.get("EXISTING_WORKITEM_TYPE");
    boolean savingMessage;
    // Update: wait for dialog loading stable
    try {
      this.driverCustom.switchToFrame(RMConstants.RMPROJECT_CREATELINK_IFRAME_XPATH);
      this.driverCustom.isElementVisible(RMConstants.RMLINKSPAGE_CANCEL_BUTTON_XPATH, timeInSecs);
      this.driverCustom.switchToDefaultContent();
    }
    catch (Exception e) {
      // For project does not has permission to access
    }
    // Clear and input DM project in Artifact Container
    WebElement artifactContainer =
        this.driverCustom.getVisibleWebElements(RMConstants.RMARTIFACTSPAGE_ARTIFACTCONTAINER_TEXTBOX_XPATH).get(0);
    artifactContainer.clear();
    waitForSecs(1);
    artifactContainer.sendKeys(dmProject);
    waitForSecs(1);
    artifactContainer.sendKeys(Keys.ENTER);
    waitForSecs(1);
    artifactContainer.sendKeys(Keys.ENTER);
    waitForSecs(3);
    artifactContainer.sendKeys(Keys.TAB);
    waitForSecs(Duration.ofSeconds(5));
    LOGGER.LOG.info(String.format("DM Project '%s' is selected from Artifact Container", dmProject));

    // For project have warning: "The action cannot be completed because an invalid global configuration was specified." - Happening on 05Q-RM. 
    // Warning xpath located inside the dialog 'Create Link' > inside the iframe below 'Artifact Container'
    this.driverCustom.switchToFrame(RMConstants.RMPROJECT_CREATELINK_IFRAME_XPATH);
    // Verify warning visible
    if(this.driverCustom.isElementVisible(RMConstants.WARNING_MESSAGE_XPATH, timeInSecs)) {
      // Return from iframe 
      this.driverCustom.switchToDefaultContent();
      // Sendkeys to re-select 'Artifact Container' - Workaround to solve warning issue. 
      WebElement artifactContainerTxt =
          this.driverCustom.getVisibleWebElements(RMConstants.RMARTIFACTSPAGE_ARTIFACTCONTAINER_XPATH).get(0);
      artifactContainerTxt.click();
      artifactContainer.sendKeys(Keys.ARROW_DOWN);
      waitForSecs(1);
      artifactContainer.sendKeys(Keys.ARROW_DOWN);
      waitForSecs(1);
      artifactContainer.sendKeys(Keys.ENTER);
      waitForSecs(1);
      artifactContainer.clear();
      waitForSecs(1);
      artifactContainer.sendKeys(dmProject);
      waitForSecs(1);
      artifactContainer.sendKeys(Keys.ENTER);
      waitForSecs(3);
      artifactContainer.sendKeys(Keys.ENTER);
      waitForSecs(3);
      artifactContainer.sendKeys(Keys.TAB);
      waitForSecs(Duration.ofSeconds(5));
      LOGGER.LOG.info(String.format("Due to  warning: 'The action cannot be completed because an invalid global configuration was specified'. " +
          "\nRe-select Project '%s' from Artifact Container", dmProject));
      // Switch to iframe again
      this.driverCustom.switchToFrame(RMConstants.RMPROJECT_CREATELINK_IFRAME_XPATH);
    }
    
    // Search and select the diagram
    WebElement drdSearchType = null;
    try {
      // Using DM PA
      drdSearchType = this.driverCustom.getPresenceOfWebElement("//label[text()='Search Type:']");
    }
    catch (Exception ex) {
      // Using AM PA
    }
    if (drdSearchType != null) {
      // Using DM PA
      Dropdown searchSelectType = this.engine.findFirstElementWithDuration(
          Criteria.isDropdown().withDefaultValue(SearchTypeEnums.EXPLORER.toString()), Duration.ofSeconds(30));
      searchSelectType.selectOptionWithText(SearchTypeEnums.SEARCH.toString());
      // Search input element visible
      if (this.driverCustom.isElementVisible(RMConstants.RM_ARTIFACTSPAGE_CREATE_LINK_SEARCH_DIAGRAM_XPATH, Duration.ofSeconds(10))) {
        // Search diagram name and select
        WebElement inputDiagram =
            this.driverCustom.getWebElement(RMConstants.RM_ARTIFACTSPAGE_CREATE_LINK_SEARCH_DIAGRAM_XPATH);
        inputDiagram.clear();
        inputDiagram.sendKeys(diagramName + Keys.ENTER);
        this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_PAFOLDER_XPATH, Duration.ofSeconds(10), diagramName);
        this.driverCustom.click(RMConstants.RMARTIFACTPAGE_EDITATTRIBUTES_CHECKBOX_XPATH, diagramName);
      }
      else {
        LOGGER.LOG.error(String.format("No attributes displayed in '%s'", dmProject));
        this.driverCustom.click(RMConstants.JAZZPAGE_BUTTON_XPATH, RMConstants.CANCEL);
      }
      this.driverCustom.switchToDefaultContent();
      this.driverCustom.switchToFrame(RMConstants.RMPROJECT_CREATELINK_IFRAME_XPATH);
      // Click on OK button
      try {
        this.driverCustom.click(RMConstants.JAZZPAGE_BUTTON_XPATH, "OK");
      }
      catch (Exception e) {
        this.driverCustom.click(RMConstants.JAZZPAGE_BUTTON_XPATH, "Ok");
      }
      this.driverCustom.switchToDefaultContent();
    }
    else {
      chooseExistingSatisfiedArchitectureLinkAM(additionalParams);
    }
    savingMessage =
        this.driverCustom.isElementInvisible(RMConstants.LOADINGARTIFACTLINKS_MESSAGE_XPATH, this.timeInSecs);
    this.waitForSecs(3);
    return savingMessage;
  }

  private void chooseExistingSatisfiedArchitectureLinkAM (final Map<String, String> additionalParams)  {
    String diagramName = additionalParams.get(ArtifactProperties.DIAGRAM_NAME.toString());
    String componentName = additionalParams.get(ArtifactProperties.ARTIFACT_COMPONENT.toString());
    String associateText = additionalParams.get("ASSOCIATE_BUTTON");
    String ccmProjectArea = additionalParams.get("CCM_PROJECTAREA");
    String isLinkToExisting = additionalParams.get("IS_LINK_EXISTING");
    String workItemID = additionalParams.get("WORKITEM_ID");
    String existingType = additionalParams.get("EXISTING_WORKITEM_TYPE");
    // Associate to Change request
    this.driverCustom.switchToDefaultContent();
    this.driverCustom.switchToFrame(RMConstants.RMPROJECT_CREATELINK_IFRAME_XPATH);
    if(!associateText.equalsIgnoreCase("null")) {
      WebElement lnkAssociate = this.driverCustom.getPresenceOfWebElement("//a[contains(text(),'" + associateText + "')]");
      this.driverCustom.getClickableWebElement(lnkAssociate).click();
      this.driverCustom.switchToDefaultContent();
      this.driverCustom.switchToFrame(RMConstants.RMPROJECT_CREATELINK_IFRAME_XPATH);
      WebElement optCcmProjectArea = this.driverCustom.getPresenceOfWebElement("//option[text()='"+ ccmProjectArea + "']");
      this.driverCustom.getClickableWebElement(optCcmProjectArea).click();
    }
    if (Boolean.parseBoolean(isLinkToExisting)) {
      try {
        WebElement rbnLinkExisting = this.driverCustom.getPresenceOfWebElement("//label[text()='Link to existing:']/parent::td//input");
        this.driverCustom.getClickableWebElement(rbnLinkExisting).click();
        Dialog dlgAddLink = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), timeInSecs).getFirstElement();
        Dropdown drdExistingType = this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Link to existing:").inContainer(dlgAddLink), timeInSecs).getFirstElement();
        drdExistingType.selectOptionWithText(existingType);
        Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink), timeInSecs).getFirstElement();
        btnOK.click();
        waitForSecs(Duration.ofSeconds(5));
        this.driverCustom.switchToFrame(RMConstants.RMPROJECT_CREATELINK_IFRAME_XPATH);   
        WebElement txbSearch = this.driverCustom.getPresenceOfWebElement("//input[@dojoattachpoint='queryInput']");
        this.driverCustom.getClickableWebElement(txbSearch).click();
        txbSearch.clear();
        txbSearch.sendKeys(workItemID + Keys.ENTER);
        WebElement optWorkItem = this.driverCustom.getPresenceOfWebElement("//option[@value='" + workItemID + "']");
        this.driverCustom.getClickableWebElement(optWorkItem).click();
        WebElement btnOK1 = this.driverCustom.getPresenceOfWebElement("//button[text()='OK']");
        JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
        je.executeScript("arguments[0].scrollIntoView(true);", btnOK1);
        this.driverCustom.getClickableWebElement(btnOK1).click();
      }
      catch (Exception e) {
        throw new WebAutomationException("Create new option is not handled completedly");
      }
    }
//    else {
//      throw new WebAutomationException("Create new option is not handled completedly");
//    }
    // Select AM Diagram
    this.waitForSecs(2);
    this.driverCustom.switchToDefaultContent();
    this.driverCustom.switchToFrame(RMConstants.RMPROJECT_CREATELINK_IFRAME_XPATH);
    this.waitForSecs(2);
    WebElement txbSearchComponent = this.driverCustom.getPresenceOfWebElement("//div[@class='searchMain']//input");
    txbSearchComponent.click();
    txbSearchComponent.clear();
    txbSearchComponent.sendKeys(componentName);
    this.driverCustom.click("//div[@class='searchMain']//button[text()='Search']");
    this.waitForSecs(Duration.ofSeconds(5));
    WebElement rowComponent = this.driverCustom.getPresenceOfWebElement("//div[@data-dojo-attach-point='_dataAreaNode']//span[text()='" + componentName + "']");
    this.driverCustom.getClickableWebElement(rowComponent).click();
    this.waitForSecs(Duration.ofSeconds(5));
    WebElement rowArtifact = this.driverCustom.getPresenceOfWebElement("//div[@class='dojoxGridContent']//a[text()='" + diagramName + "']");
    this.driverCustom.getClickableWebElement(rowArtifact).click();
    this.waitForSecs(3);
    this.driverCustom.switchToDefaultContent();
    this.driverCustom.switchToFrame(RMConstants.RMPROJECT_CREATELINK_IFRAME_XPATH);
    // Click on OK button
    this.driverCustom.click("//div[@data-dojo-attach-point='_controlArea']//button[@class='j-button-primary']");
    this.driverCustom.switchToDefaultContent();
  }

  /**
   * <p>
   * After input all the required data to create link {@link RMLinksPage#chooseExistingItemFromCreateLinkDialog}, check
   * if Link is already existing. <br>
   * Close pop-up dialog and re-try to click Ok in Create Links dialog. <br>
   * Get message displayed in dialog and compare with expected message of Duplicated link (using @link
   * {@link RMLinksPage#getErrorMessageIfVisible(String)}). <br>
   * Close popup dialog and click Cancel in Create link dialog.
   *
   * @return Message (successful or unsucessful) after creating link.
   */
  public String createLinkIfNotAlreadyExists() {
    waitForSecs(Duration.ofSeconds(5));
    if (this.driverCustom.isElementClickable(RMConstants.CLOSE_BUTTON, Duration.ofSeconds(10))) {
      this.driverCustom.getPresenceOfWebElement(RMConstants.CLOSE_BUTTON).click();
    }
    waitForSecs(3);
    Button btn =
        this.engine.findElementWithinDuration(Criteria.isButton().withText(RMConstants.OK), Duration.ofSeconds(10)).getFirstElement();
    btn.click();
    LOGGER.LOG.info(RMConstants.CLICK_ON_OK_BUTTON);
    waitForSecs(3);
    this.driverCustom.isElementVisible(RMConstants.JAZZ_MESSAGE_SUMMARY_XPATH, Duration.ofSeconds(20));
    waitForSecs(5);
    this.driverCustom.isElementInvisible("//div[@role='dialog' and contains(@style,'display: none')]", Duration.ofSeconds(60));
    Dialog dlg = null;
    try {
      dlg = this.engine.findElement(Criteria.isDialog().withTitle(RMConstants.CREATE_LINK)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    waitForSecs(Duration.ofSeconds(10));
    Boolean createLink = (dlg != null);
    String errorMessage = getErrorMessageIfVisible(RMConstants.JAZZ_MESSAGE_SUMMARY_XPATH);
    if (getErrorMessageIfVisible(RMConstants.JAZZ_MESSAGE_SUMMARY_XPATH)
        .contains(RMConstants.RM_ARTIFACE_WARNING_MESSAGE_EXPECTED) || createLink) {
      waitForSecs(5);
      Dialog dlgCreateArtifacts =
          this.engine.findElement(Criteria.isDialog().withTitle(RMConstants.CREATE_LINK)).getFirstElement();
      Button btnCancel =
          this.engine.findElement(Criteria.isButton().withText(RMConstants.CANCEL).inContainer(dlgCreateArtifacts))
          .getFirstElement();
      waitForSecs(5);
      btnCancel.click();
      waitForSecs(5);
      LOGGER.LOG.info(RMConstants.CLICK_ON_CANCEL_BUTTON);
      LOGGER.LOG.info(errorMessage);
      return errorMessage;
    }
    return "Link created and Create link dialog closed.";
  }

  /**
   * <p>
   * Get Error message if there is existing one. <br>
   *
   * @param property - locater of Error message
   * @return error messages, "" if no message displayed
   */
  public String getErrorMessageIfVisible(final String property) {
    if (this.driverCustom.isElementVisible(property, Duration.ofSeconds(5))) {
      String errorMsg = "";
      for (String s : this.driverCustom.getWebElementsText(property)) {
        errorMsg = s.concat(errorMsg);
      }
      LOGGER.LOG.info("Returned error Message " + errorMsg);
      return errorMsg;
    }
    return "";

  }

  /**
   * <p>
   * From 'Artifacts' page, Validates whether link of type 'Satisfies' is created or added to selected artifact. <br>
   * Open Artifact with {@link ArtifactProperties#ID_NAME}.
   *
   * @param additionalParams stores keys : {@link ArtifactProperties#ID_NAME}, {@link ArtifactProperties#ID}.
   */
  public void openSatisfiesLink(final Map<String, String> additionalParams) {
    String idName = additionalParams.get(ArtifactProperties.ID_NAME.toString());
    String id = additionalParams.get(ArtifactProperties.ID.toString());

    // Not found title in HTML dom update as text
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTPAGE_VALIDATEDBYLINK_XPATH, Duration.ofSeconds(10), idName);

    String xpathSatisfiesLink = "";
    try {
      xpathSatisfiesLink = String.format("//a[text()='%s']/ancestor::td[@role='gridcell']/following-sibling::td//a[contains(text(),'%s')]", id, idName);
      WebElement satisfiesLink = this.driverCustom.getPresenceOfWebElement(xpathSatisfiesLink);
      JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
      je.executeScript("arguments[0].scrollIntoView(true);", satisfiesLink);
      this.waitForSecs(Duration.ofSeconds(5));
      Actions act = new Actions(this.driverCustom.getWebDriver());
      // Click to the top left of link element
      int xValueInMiddle = satisfiesLink.getSize().getWidth() / 3;
      act.moveToElement(satisfiesLink,-xValueInMiddle,0).click().build().perform();
      this.waitForSecs(Duration.ofSeconds(5));
      act.release().perform();
    }
    catch (Exception e) {
      WebElement satisfiesLink = this.driverCustom.getPresenceOfWebElement(xpathSatisfiesLink);
      this.driverCustom.clickUsingActions(satisfiesLink);
    }

    LOGGER.LOG.info(RMConstants.CLICKED_ON_ARTIFACT + idName);
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
  }

  /**
   * <p>
   * From 'Artifacts' page, Validates whether link of type 'Implemented By' is created or added to selected artifact.
   * <br>
   * Open Work Item with {@link CCMConstants#WORKITEM_ID}. <br>
   *
   * @param additionalParams stores keys : {@link CCMConstants#WORKITEM_ID}, {@link ArtifactProperties#ID}.
   */
  public void openImplementedByLink(final Map<String, String> additionalParams) {
    String artifactID = additionalParams.get(ArtifactProperties.ID.toString());
    String workItemID = additionalParams.get(CCMConstants.WORKITEM_ID);
    this.waitForSecs(Duration.ofSeconds(10));
    try {
      WebElement implementLink = this.driverCustom.getPresenceOfWebElement("//table[@rowlabel='Artifact " + artifactID +
          "']//td[@colid='imp-by']//a[contains(.,'" + workItemID + "')]");
      JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
      je.executeScript("arguments[0].scrollIntoView(true);", implementLink);
      this.waitForSecs(Duration.ofSeconds(5));
      Actions act = new Actions(this.driverCustom.getWebDriver());
      // Click to the top left of link element
      int xValueInMiddle = implementLink.getSize().getWidth() / 2;
      act.moveToElement(implementLink,-xValueInMiddle,0).click().build().perform();
      this.waitForSecs(Duration.ofSeconds(5));
      try {
        implementLink.click();
        LOGGER.LOG.info("link clicked successfuly");
      }catch (Exception e) {
        LOGGER.LOG.info("link not found to click");
      }
    }
    catch (Exception e) {
      WebElement implementLink = this.driverCustom.getPresenceOfWebElement("//table[@rowlabel='Artifact " + artifactID +
          "']//td[@colid='imp-by']//strike[contains(text(),'" + workItemID + "')]");
      implementLink.click();
    }
    LOGGER.LOG.info(RMConstants.CLICKED_ON_ARTIFACT + workItemID);
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
  }

  /**
   * <p>
   * From 'Work Item details' page, Validates whether link of type 'Implements' is created or added to selected Work
   * Item. <br>
   * Open Artifact with {@link ArtifactProperties#ID}. <br>
   *
   * @param additionalParams stores keys : {@link ArtifactProperties#ID}.
   */
  public void openImplementsLinks(final Map<String, String> additionalParams) {
    CCMWorkItemEditorPage ccmPage = new CCMWorkItemEditorPage(this.driverCustom);
    ccmPage.selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    refresh();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
    LOGGER.LOG.info(RMConstants.CLICKED_ON + "Tab " + WorkItemEnums.WorkItemTab.LINKS.toString());
    for (WebElement ele : ccmPage.getLinksList(WorkItemLinkSection.LINKS.toString(),
        WorkItemLinkTypes.IMPLEMENTS_REQUIREMENT.toString())) {
      if (ele.getText().startsWith(additionalParams.get(ArtifactProperties.ID.toString()) + ":")) {
        ele.click();
        LOGGER.LOG.info(RMConstants.CLICKED_ON_ARTIFACT + additionalParams.get(ArtifactProperties.ID.toString()));
        ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
      }
    }
  }

  /**
   * <p>
   * From 'Artifacts' page, Validates whether link of type 'Validated By' is created or added to selected artifact. <br>
   * Open Test case with {@link ArtifactProperties#ID_NAME}. <br>
   * @author VDY1HC
   * @param additionalParams stores keys : {@link ArtifactProperties#ID}, {@link ArtifactProperties#ID_NAME}.
   */
  public void openValidatedByLink(final Map<String, String> additionalParams) {
    waitForSecs(Duration.ofSeconds(15));
    String artifactID = additionalParams.get(ArtifactProperties.ID.toString());
    String testCaseIDAndName = additionalParams.get(ArtifactProperties.ID_NAME.toString());
    this.waitForSecs(Duration.ofSeconds(5));
    String[] paramArr = { artifactID, testCaseIDAndName };
    
    
    try {
   // switchToDefaultContent() to make sure Link elements visible by WebDriver
      this.driverCustom.switchToDefaultContent();
      // Refresh to make sure page will load selected artifact
      //refresh();
      WebElement validatedByLink = this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_VALIDATEDBY_LINK_XPATH, paramArr);
      this.waitForSecs(5);
      // Scroll into view when artifact is out of bounds
      this.driverCustom.scrollIntoCenterOfView(validatedByLink);
      
      // Click to the top left of link element
      validatedByLink.click();
    }catch(Exception e) {
   // switchToDefaultContent() to make sure Link elements visible by WebDriver
      this.driverCustom.switchToDefaultContent();
      // Refresh to make sure page will load selected artifact
      refresh();
      WebElement validatedByLink = this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_VALIDATEDBY_LINK_XPATH, paramArr);
      this.waitForSecs(5);
      // Scroll into view when artifact is out of bounds
      this.driverCustom.scrollIntoCenterOfView(validatedByLink);
      
      // Click to the top left of link element
      validatedByLink.click();
    }
    
    
    
    LOGGER.LOG.info(RMConstants.CLICKED_ON_ARTIFACT + testCaseIDAndName);
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
  }

  /**
   * <p>
   * From 'Test case details' page, open {@link RQMSectionMenus#REQUIREMENT_LINKS} section. <br>
   * Validates whether link of type 'Requirement Link' is created or added to selected Test case. <br>
   * Open Artifact with {@link ArtifactProperties#ID_NAME}. <br>
   *
   * @param additionalParams stores keys : {@link ArtifactProperties#ID_NAME}.
   */
  public void openRequirementLink(final Map<String, String> additionalParams) {
    waitForSecs(Duration.ofSeconds(10));
    this.driverCustom.isElementClickable(RQMConstants.RQMPROJECT_TESTCASE_SECTIONS_XPATH, Duration.ofSeconds(15),
        RQMSectionMenus.REQUIREMENT_LINKS.toString());
    Link lnkArtifact =
        this.engine.findFirstElement(Criteria.isLink().withText(RQMSectionMenus.REQUIREMENT_LINKS.toString()));
    lnkArtifact.click();
    LOGGER.LOG.info(RMConstants.CLICKED_ON + RQMSectionMenus.REQUIREMENT_LINKS.toString() + " Section.");
 
    waitForSecs(Duration.ofSeconds(5));
    for (int i = 0; i < 5; i++) {
     // if(this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_REQUIREMENT_LINKS_XPATH, Duration.ofSeconds(5), additionalParams.get(ArtifactProperties.ID_NAME.toString()))) {
      if(!this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_REQUIREMENT_LINKS_XPATH, Duration.ofSeconds(10), additionalParams.get(ArtifactProperties.ID_NAME.toString()))) {
        String xpathNextButton = "//div[@dojoattachpoint='footerPageControlNode']//a[@class='button' and @aria-disabled='false']/span[text()='Next']";
        if(this.driverCustom.getWebDriver().findElement(By.xpath(xpathNextButton)).isEnabled()) {
          this.driverCustom.click(xpathNextButton);
          try {
            Link artifactlink = this.engine
                .findFirstElementWithDuration(Criteria.isLink().withText(additionalParams.get(ArtifactProperties.ID_NAME.toString())), Duration.ofSeconds(5));
            waitForSecs(2);
            artifactlink.click();
            break;
          }
          catch (Exception e) {
            LOGGER.LOG.error(String.format("'Artifact Link' with ID: '%s' not found", additionalParams.get(ArtifactProperties.ID_NAME.toString())));
          }
        }
      }else {
          Link artifactlink = this.engine
              .findFirstElementWithDuration(Criteria.isLink().withText(additionalParams.get(ArtifactProperties.ID_NAME.toString())), Duration.ofSeconds(5));
          waitForSecs(2);
          artifactlink.click();
          break;
      }
    }
  }

  /**
   * <p>
   * From 'Artifacts' page, Validates whether link of type 'Satisfied By' is created or added to selected artifact. <br>
   * Open Artifact with {@link ArtifactProperties#ID_NAME}.
   *
   * @param additionalParams stores keys : {@link ArtifactProperties#ID_NAME}, {@link ArtifactProperties#ID}.\
   */
  public void openSatisfiedByLink(final Map<String, String> additionalParams) {
    String idName = additionalParams.get(ArtifactProperties.ID_NAME.toString());
    String id = additionalParams.get(ArtifactProperties.ID.toString());

    // Not found title in HTML dom update as text
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTPAGE_VALIDATEDBYLINK_XPATH, Duration.ofSeconds(10), idName);

    String xpathSatisfiesLink = "";
    try {
      xpathSatisfiesLink = String.format("//a[text()='%s']/ancestor::td[@role='gridcell']/following-sibling::td//a[contains(text(),'%s')]", id, idName);      
      WebElement satisfiesLink = this.driverCustom.getPresenceOfWebElement(xpathSatisfiesLink);
      JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
      je.executeScript("arguments[0].scrollIntoView(true);", satisfiesLink);
      this.waitForSecs(Duration.ofSeconds(5));
      Actions act = new Actions(this.driverCustom.getWebDriver());
      // Click to the top left of link element
      int xValueInMiddle = satisfiesLink.getSize().getWidth() / 2;
      act.moveToElement(satisfiesLink,-xValueInMiddle,0).click().perform();
      this.waitForSecs(Duration.ofSeconds(5));      
      act.release().perform();
      // Refresh to make sure page will scroll to selected artifact
      refresh();
    }
    catch (Exception e) {
      WebElement satisfiedByLink = this.driverCustom.getPresenceOfWebElement(xpathSatisfiesLink);
      this.driverCustom.clickUsingActions(satisfiedByLink);
    }

    LOGGER.LOG.info(RMConstants.CLICKED_ON_ARTIFACT + idName);
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
    
  }

  /**
   * <p>
   * From 'Artifacts' page, get web element of artifact using its ID. <br>
   *
   * @param id - ID of artifact.
   * @return Web element of selected artifact.
   */
  public WebElement getWebElementOfSelectedArtifact(final String id) {
    return this.driverCustom.getWebElement(RMConstants.RM_ARTIFACTSPAGE_ARTIFACT_TABLE_XPATH, "Artifact " + id);
  }

  /**
   * <p>
   * From 'Artifacts' page, get Attribute value of 'column' using Column Header. <br>
   *
   * @param columnHeader Header of Column.
   * @return value of attribute 'column' if column is found, null if no column found.
   */
  public String getColoumAttributeByHeader(final String columnHeader) {
    for (WebElement coloumElement : this.driverCustom
        .getWebElements(RMConstants.RM_ARTIFACTSPAGE_COLOUMS_TABLE_XPATH)) {
      if (coloumElement.getText().startsWith(columnHeader)) {
        return coloumElement.getAttribute("column");
      }
    }
    return null;
  }

  /**
   * <p>
   * From 'Artifact details' page, Validtes whether link is created or added to selected artifact <br>
   * Expand Side bar area (if needed), open 'Links' section (using {@link ModuleEnums#SIDE_BAR_AREA} = 'Links'). <br>
   * Scroll though list of Artifacts linked to current Artifact (support for link type:
   * {@link AddLinkToArtifact#LINK_TO}, {@link AddLinkToArtifact#LINK_FROM}, {@link AddLinkToArtifact#SATISFIED_BY},
   * {@link AddLinkToArtifact#SATISFIES}, {@link AddLinkToArtifact#TRACKEDBY}, {@link AddLinkToArtifact#IMPLEMENTED_BY},
   * {@link AddLinkToArtifact#VALIDATED_BY} - stored in variable {@link ArtifactContextMenu#ADD_LINK_TO_ARTIFACT}. <br>
   * Open Artifact with {@link ArtifactProperties#ID_NAME} in the list.
   *
   * @param additionalParams stores keys : {@link ModuleEnums#SIDE_BAR_AREA} - Section contains linked links (Ex:
   *          'Links', 'Module Links') <br>
   *          {@link ArtifactContextMenu#ADD_LINK_TO_ARTIFACT} - in list of support link types,
   *          {@link ArtifactProperties#ID_NAME} - ID of linked Artifact
   * @return true - if the link is added to the Module, false - if the link is not found.
   */
  public boolean openLinkByFromTab(final Map<String, String> additionalParams) {
    waitForSecs(Duration.ofSeconds(10));
    WebElement element = this.driverCustom.getWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH,
        additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()));
    if (!this.driverCustom.getChildElement(element, By.xpath(RMConstants.NAVIGATE_TO_PARENT))
        .getAttribute(RMConstants.CLASS).endsWith(RMConstants.EXPANDED)) {
      this.driverCustom.clickUsingActions(this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH,
          additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString())));
      LOGGER.LOG.info(
          RMConstants.CLICKED_ON + "Artifact side bar " + additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()));
    }
    waitForSecs(Duration.ofSeconds(30));
    String linkType = additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString());
    if (linkType.equalsIgnoreCase(AddLinkToArtifact.LINK_TO.toString()) ||
        linkType.equalsIgnoreCase(AddLinkToArtifact.LINK_FROM.toString()) ||
        linkType.equalsIgnoreCase(AddLinkToArtifact.SATISFIED_BY.toString()) ||
        linkType.equalsIgnoreCase(AddLinkToArtifact.SATISFIES.toString()) ||
        linkType.equalsIgnoreCase(AddLinkToArtifact.TRACKEDBY.toString()) ||
        linkType.equalsIgnoreCase(AddLinkToArtifact.IMPLEMENTED_BY.toString()) ||
        linkType.equalsIgnoreCase(AddLinkToArtifact.VALIDATED_BY.toString())||
        linkType.equalsIgnoreCase(AddLinkToArtifact.CAT_AUTOMATION_OUT.toString())||
        linkType.equalsIgnoreCase(AddLinkToArtifact.CAT_AUTOMATION_IN.toString())||
        linkType.equalsIgnoreCase(AddLinkToArtifact.CAT_LINK_OUT.toString())||
        linkType.equalsIgnoreCase(AddLinkToArtifact.CAT_LINK_IN.toString())) {
      waitForSecs(25);
      try {
        List<WebElement> linkElementss = this.driverCustom.getWebElements(RMConstants.JAZZPAGE_LINKED_LINKS_XPATH);
        for (WebElement linkElements : linkElementss) {
          // getText from link got whitespace, therefore need to remove from the string
          if (linkElements.getText().replaceAll("\n","").contains(additionalParams.get(ArtifactProperties.ID_NAME.toString()))) {
            try {
              JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
              je.executeScript("arguments[0].scrollIntoView(true);", linkElements);
              waitForSecs(3);
              je.executeScript("arguments[0].click();", linkElements);
            }
            catch (Exception e) {
              LOGGER.LOG.error("");
            }
            LOGGER.LOG
            .info(RMConstants.CLICKED_ON_ARTIFACT + additionalParams.get(ArtifactProperties.ID_NAME.toString()));
            return true;
          }
        }
      }
      catch (Exception e) {
        throw new WebAutomationException("There is not any links under Links section");
      }
      throw new WebAutomationException(
          additionalParams.get(ArtifactProperties.ID_NAME.toString()) + " does not present under Links section");
    }
    throw new WebAutomationException(linkType + " is not supported in this method 'openLinkByFromTab'");
  }

  /**
   * <p>
   * From 'Artifact details' page, Validtes whether link is created or added to selected artifact <br>
   * Expand Right Side bar area (if needed), open 'Links' section (using {@link ModuleEnums#SIDE_BAR_AREA} = 'Links').
   * <br>
   * Click on 'Add Link to Artifact' button.
   *
   * @author KYY1HC
   * @param additionalParams stores keys: {@link ModuleEnums#SIDE_BAR_AREA} - Section contains linked links (Ex:
   *          'Links', 'Module Links') <br>
   *          {@link ArtifactContextMenu#ADD_LINK_TO_ARTIFACT} - Link type (Ex: Validated By, Tracked By,...)
   */
  public void clickOnAddArtifactLinkFromRightSideBar(final Map<String, String> additionalParams) {
    waitForSecs(Duration.ofSeconds(15));
    String sideBarArea = additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString());
    String linkType = additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString());

    waitForPageLoaded();
    this.driverCustom.isElementPresent(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, Duration.ofSeconds(180), sideBarArea);
    WebElement element = this.driverCustom.getWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, sideBarArea);

    if (!this.driverCustom.getChildElement(element, By.xpath(RMConstants.LEFTSIDE_CONTAINER_CHILDS_XPATH))
        .getAttribute(RMConstants.CLASS).endsWith(RMConstants.EXPANDED)) {
      this.driverCustom.clickUsingActions(this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, sideBarArea));
      LOGGER.LOG.info(RMConstants.CLICKED_ON + "Artifact side bar " + sideBarArea);
    }

    try {
      Dropdown drdAction = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withToolTip("Add Link to Artifact"), Duration.ofSeconds(20));
      drdAction.click();
      String drdOption = "//table[contains(@class,'MenuActive')]//td[normalize-space(text())='%s']";
      if(this.driverCustom.isElementInvisible(String.format(drdOption, linkType), Duration.ofSeconds(10))) {
        // In case, the link type is not displayed after clicking on Add Link to Artifact button
        if(this.driverCustom.isElementVisible(String.format(drdOption, "More..."), Duration.ofSeconds(10))) {
          this.driverCustom.click(String.format(drdOption, "More..."));
        }
//        WebElement scrollIcon = this.driverCustom.getWebElement("//div[@dojoattachpoint='scrollDownButton']/span");
//        this.driverCustom.clickUsingActions(scrollIcon);
        this.driverCustom.isElementVisible(RMConstants.RM_ARTIFACTSPAGE_CREATE_LINK_SEARCH_BOX_XPATH, Duration.ofSeconds(10));
        // Select 'LinkType' value
        String linkTypeDropDownXpath = "//label[text()='Link type:']/parent::td/following-sibling::td[contains(@class,'link-type')]//table[@role='listbox']";
        this.driverCustom.click(linkTypeDropDownXpath);
        this.driverCustom.click(String.format(drdOption, linkType));
      }else {
        // In case, the link type is displayed after clicking on Add Link to Artifact button
        this.driverCustom.click(String.format(drdOption, linkType));
      }
    }
    catch (Exception e) {
      // In case, the link type need scroll down to display
      if (this.driverCustom.isElementPresent(RMConstants.RMLINKSPAGE_ADDLINKTOARTIFACT_SUBMENU_XPATH, Duration.ofSeconds(120), linkType)) {
        Cell cell = this.engine.findFirstElementWithDuration(Criteria.isCell().withText(linkType), Duration.ofSeconds(10));
        cell.scrollToElement();
        cell.click();
      }
    }
    LOGGER.LOG.info(String.format("'Add Link to Artifact' as: '%s'", linkType));
    this.driverCustom.isElementVisible(RMConstants.RM_ARTIFACTSPAGE_CREATE_LINK_SEARCH_BOX_XPATH, Duration.ofSeconds(20));
  }



  /**
   * <p>
   * From 'Module details' page, after open context menu of an Artifact inside Module. <br>
   * Select options inside this context menu. This method supports for up to 2 options (using
   * {@link RMLinksPage#clickOnContextSubmenu}<br>
   *
   * @param additionalParams stores keys: {@link ArtifactProperties#ARTIFACT_CONTEXT_MENU} - Option in context menu,
   *          {@link ArtifactProperties#ARTIFACT_CONTEXT_SUBMENU} - Option in context submenu (optional)
   */
  public void clickOnContextMenuOfArtifactInModule(final Map<String, String> additionalParams) {
    String artifactContextMenu = additionalParams.get(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString());
    String artifactSubContextMenu = additionalParams.get(ArtifactProperties.ARTIFACT_CONTEXT_SUBMENU.toString());
    this.driverCustom.clickUsingActions(
        this.driverCustom.getWebElement(RMConstants.RMMODULEINSIDE_ARTIFACTEDIT_MENU_XPATH, artifactContextMenu));
    LOGGER.LOG.info(RMConstants.CLICKED_ON + artifactContextMenu);
    if (artifactSubContextMenu != null) {
      clickOnContextSubmenu(artifactSubContextMenu);
      LOGGER.LOG.info(RMConstants.CLICKED_ON + artifactSubContextMenu);
    }
    LOGGER.LOG.info(RMConstants.CLICKED_ON + artifactSubContextMenu);
  }

  /**
   * @param id artifact is
   * @param menu drop down menu
   * @param submenu drop down sub menu
   */
  public void selectContextMenuOfArtifactInModule(final String id, final String menu, final String submenu) {
waitForSecs(5);
    Row row = this.engine.findElement(Criteria.isRow().withText(id)).getFirstElement();
    Cell cellAction = this.engine.findElement(Criteria.isCell().inContainer(row).withIndex(2)).getFirstElement();
    Dropdown drdMenu = this.engine.findElement(Criteria.isDropdown().inContainer(cellAction)).getFirstElement();
    LOGGER.LOG.info(RMConstants.CLICKED_ON + menu);
    drdMenu.selectOptionsWithText(menu, submenu);
    LOGGER.LOG.info(RMConstants.CLICKED_ON + submenu);
    waitForSecs(5);

  }

  /**
   * <p>
   * From 'Module details' page, after open context menu of an Artifact inside Module and selected the first option
   * (which will open a submenu) (using {@link RMLinksPage#clickOnContextMenuOfArtifactInModule}).<br>
   * Select option inside context submenu. <br>
   *
   * @param optionInSubmenu - Option to select in submenu.
   */
  public void clickOnContextSubmenu(final String optionInSubmenu) {
    if (optionInSubmenu.equals(ArtifactContextMenu.COPY_ARTIFACT.toString())) {
      WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(15));
      wait.until(ExpectedConditions.alertIsPresent());
      Alert alert = this.driverCustom.getWebDriver().switchTo().alert();
      alert.accept();
    }
    if (this.driverCustom.isElementPresent(RMConstants.RMMODULEINSIDE_SELECT_LEFTSIDE_MENU_XPATH, Duration.ofSeconds(5),
        ArtifactContextMenu.MORE.toString())) {
      Cell more =
          this.engine.findElement(Criteria.isCell().withText(ArtifactContextMenu.MORE.toString())).getFirstElement();
      more.click();
      LOGGER.LOG.info(RMConstants.CLICKED_ON + ArtifactContextMenu.MORE.toString() + " dropdown option.");
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_CREATE_LINKTYPE_XPATH);
      this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_CREATE_LINKTYPE_XPATH);
      LOGGER.LOG.info("Clicked on link type dropdown.");
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEINSIDE_SELECT_LINKTYPE_XPATH, optionInSubmenu);
      this.driverCustom.click(String.format("//td[@role='presentation' and contains(text(),'%s')]", optionInSubmenu));
      LOGGER.LOG.info(RMConstants.CLICKED_ON + optionInSubmenu + RMConstants.FROM_LINK_TYPE_DROPDOWN);
      waitForSecs(5);
      return;
    }
    if (this.driverCustom.isElementVisible(RMConstants.RMMODULEINSIDE_ADDLINKTOARTIFACT_LEFTSIDEMENU_TYPE_TWO_XPATH,
        this.timeInSecs, optionInSubmenu)) {
      waitForSecs(2);

      try {
        this.driverCustom.click(RMConstants.RMMODULEINSIDE_ADDLINKTOARTIFACT_LEFTSIDEMENU_TYPE_TWO_XPATH,
            optionInSubmenu);
      }
      catch (Exception e) {
        this.driverCustom.click("//tr[@role='menuitem']/td[contains(translate(text(),' ',''),'" +
            optionInSubmenu.replaceAll("\\s", "") + "')]");
        waitForSecs(2);
        LOGGER.LOG.info(RMConstants.CLICKED_ON + optionInSubmenu + " from link type dropdown.");
      }
      return;
    }
    if (!this.driverCustom.isElementVisible(RMConstants.RMMODULEINSIDE_ADDLINKTOARTIFACT_LEFTSIDEMENU_XPATH, Duration.ofSeconds(10),
        optionInSubmenu)) {
      this.driverCustom.clickUsingActions(
          this.driverCustom.getWebElement(RMConstants.RM_ADD_ARTIFACT_LINKS_SUB_MENU_DOWNARROW_XPATH));
    }
    Cell menu = this.engine.findElement(Criteria.isCell().withText(optionInSubmenu)).getFirstElement();
    menu.click();
    LOGGER.LOG.info(RMConstants.CLICKED_ON + optionInSubmenu + " from link type dropdown.");
  }

  /**
   * <p>
   * This method use to select link which you want to see in Diagram view using Configure link display. <br>
   * From 'Module details' page, after open {@link ModuleEnums#MODULE_SECTION} in Right side bar area.<br>
   * Click on {@link ModuleEnums#MODULE_LINKS} to open 'Module Links' section and click on
   * {@link ModuleEnums#LINKS_EXPLORER} button. <br>
   * In {@link DiagramEnums#DIAGRAM_VIEW}, click on {@link DiagramEnums#C0NFIGURE_LINK_DISPLAY} button. <br>
   * Search for Link type to view (using {@link ArtifactContextMenu#ADD_LINK_TO_ARTIFACT}) and click 'Apply' button.
   *
   * @param additionalParams stores keys: {@link ArtifactContextMenu#ADD_LINK_TO_ARTIFACT} - Link type to view
   * @return true - if "Fetching links" finished, false - if unable to finish 'Fetching links"
   */
  public boolean showDiagramForSelectedLinkType(final Map<String, String> additionalParams) {
    this.driverCustom.isElementClickable(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, Duration.ofSeconds(30),
        ModuleEnums.MODULE_LINKS.toString());
    this.driverCustom.clickUsingActions(
        this.driverCustom.getWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, ModuleEnums.MODULE_LINKS.toString()));
    LOGGER.LOG.info(RMConstants.CLICKED_ON + ModuleEnums.MODULE_LINKS.toString() + RMConstants.BUTTON);
    this.driverCustom.isElementClickable(RMConstants.RMMODULEINSIDE_MODULELINKS_BUTTON_XPATH, Duration.ofSeconds(20),
        ModuleEnums.LINKS_EXPLORER.toString());
    Button addlinktoartifacts = this.engine
        .findElement(Criteria.isButton().withToolTip(ModuleEnums.LINKS_EXPLORER.toString())).getFirstElement();
    addlinktoartifacts.click();
    LOGGER.LOG.info(RMConstants.CLICKED_ON + ModuleEnums.LINKS_EXPLORER.toString() + RMConstants.BUTTON);
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEINSIDE_SELECTLINK_BUTTON_XPATH,
        DiagramEnums.C0NFIGURE_LINK_DISPLAY.toString());
    Button configurelinkdisplay = this.engine
        .findElement(Criteria.isButton().withToolTip(DiagramEnums.C0NFIGURE_LINK_DISPLAY.toString())).getFirstElement();
    configurelinkdisplay.click();
    LOGGER.LOG.info(RMConstants.CLICKED_ON + DiagramEnums.C0NFIGURE_LINK_DISPLAY.toString() + RMConstants.BUTTON);
    Text txtSearchVerify =
        this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_OF_FILTERS));
    txtSearchVerify.setText(additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString()) + Keys.CONTROL);
    Text linklabel = this.engine.findFirstElement(
        Criteria.isTextField().withText(additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString())));
    linklabel.click();
    LOGGER.LOG.info(
        RMConstants.CLICKED_ON_ARTIFACT + additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString()));
    Button apply = this.engine.findElement(Criteria.isButton().withText(RMConstants.APPLY)).getFirstElement();
    apply.click();
    LOGGER.LOG.info(RMConstants.CLICK_ON_APPLY_BUTTON);
    return (!this.driverCustom.isElementVisible(RMConstants.DNG_CREATEDTEMPLATE_XPATH, Duration.ofSeconds(10),
        DiagramEnums.FETCHING_LINKS.toString()));
  }

  /**
   * This method removes the existing links with {@link ArtifactProperties#ID_NAME} <br>
   * Expand Side bar area (if needed), open 'Links' section (using {@link ModuleEnums#SIDE_BAR_AREA} = 'Links', 'Module
   * Links',...). <br>
   * Remove the linked link Artifact displayed inside {@link ModuleEnums#SIDE_BAR_AREA} section
   *
   * @param additionalParams stores keys: {@link ModuleEnums#SIDE_BAR_AREA} - Section contains linked links (Ex:
   *          'Links', 'Module Links') <br>
   *          {@link ArtifactProperties#ID_NAME} - ID of Artifact to be removed <br>
   * @return true - if the link is deleted, false - if the link doesn't exists to be deleted.
   */
  public boolean removeLinkIfExists(final Map<String, String> additionalParams) {
    waitForSecs(5);
    String linkSectionText = additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString());
    String idName = additionalParams.get(ArtifactProperties.ID_NAME.toString());

    if(linkSectionText.equals("Module Links")) {
      this.driverCustom.click("//span[@class='section-content-title' and text()='Module']");
    }

    WebElement linksSection = this.driverCustom.getClickableWebElement(
        this.driverCustom.getWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, linkSectionText));
    if (!this.driverCustom.getChildElement(linksSection, By.xpath(RMConstants.LEFTSIDE_CONTAINER_CHILDS_XPATH))
        .getAttribute(RMConstants.CLASS).endsWith(RMConstants.EXPANDED)) {
      this.driverCustom.clickUsingActions(
          this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, linkSectionText));
      LOGGER.LOG.info(RMConstants.CLICKED_ON + "Artifact side bar " + linkSectionText);
      waitForSecs(5);
    }

    try {
      Button btnClearFilter = this.engine
          .findElement(Criteria.isButton().withToolTip(RMConstants.CLEAR_FILTER_BUTTON_TOOLTIP)).getFirstElement();
      btnClearFilter.click();
      WebElement element = this.driverCustom.getWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, linkSectionText);
      if (!this.driverCustom.getChildElement(element, By.xpath("./../../..")).getAttribute(RMConstants.CLASS)
          .endsWith(RMConstants.EXPANDED)) {
        this.driverCustom.clickUsingActions(
            this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, linkSectionText));
        LOGGER.LOG.info(RMConstants.CLICKED_ON + linkSectionText + RMConstants.BUTTON);
      }
      waitForSecs(5);
    }
    catch (Exception e) {
      waitForSecs(2);
    }

    if (!this.driverCustom.isElementPresent(RMConstants.RM_ARTIFACTPAGE_MODULE_LINKS_LIST_XPATH, Duration.ofSeconds(5))) {
      return false;
    }

    waitForSecs(Duration.ofSeconds(5));
    List<WebElement> linkElements = this.driverCustom.getWebElements(RMConstants.JAZZPAGE_LINKED_LINKS_XPATH);
    for (WebElement linkElement : linkElements) {
      if (linkElement.getText().contains(idName)) {
        try {
          WebElement buttonTree = this.driverCustom.getWebElement("//span[@class='link-result']//a[contains(.,'" + idName + "')]");
          JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
          je.executeScript("arguments[0].scrollIntoView(true);", buttonTree);
          LOGGER.LOG.info("scroll successfully");
          this.driverCustom.getActions().moveToElement(buttonTree).build();
        }
        catch (Exception e) {
          WebElement buttonTree = this.driverCustom.getWebElement("//span[@class='link-result']//strike[contains(.,'" + idName + "')]");
          JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
          je.executeScript("arguments[0].scrollIntoView(true);", buttonTree);
          LOGGER.LOG.info("scroll successfully");
          this.driverCustom.getActions().moveToElement(buttonTree).build();
        }
        Row row = this.engine.findElementWithDuration(Criteria.isRow().containsText(idName), Duration.ofSeconds(60)).getFirstElement();
        Dropdown dropdown = this.engine.findElementWithDuration(Criteria.isDropdown().inContainer(row), Duration.ofSeconds(60)).getFirstElement();
        dropdown.click();
        Cell cellremove = this.engine.findElementWithDuration(Criteria.isCell().withText(RMConstants.REMOVE), Duration.ofSeconds(60)).getFirstElement();
        cellremove.click();
        LOGGER.LOG.info(RMConstants.CLICKED_ON + RMConstants.REMOVE + RMConstants.BUTTON);
        Button btnYes = this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.YES), Duration.ofSeconds(60)).getFirstElement();
        btnYes.click();
        LOGGER.LOG.info(RMConstants.CLICKED_ON + RMConstants.YES + RMConstants.BUTTON);
        LOGGER.LOG.info("Artifact side bar " + linkSectionText + " " + idName + " removed successfully ");
        waitForSecs(5);
        return true;
      }
    }
    return false;
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         This method uded to validate presence of any link in right side bar area
   * @param additionalParams ex: ${_sidebarArea:Artifact Links,ID_NAME:${ARTIFACT_ID_NAME},Add Link to
   *          Artifact:Satisfies,EXPECTED_RESULT:TRUE}
   * @return {@link Boolean}
   */
  public boolean isLinkExistsInSideBarArea(final Map<String, String> additionalParams) {
    String sidebarArea = additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString());
    String idName = additionalParams.get(ArtifactProperties.ID_NAME.toString());
    String linkType = additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString());
    waitForSecs(5);
    this.driverCustom.isElementClickable(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, Duration.ofSeconds(10), sidebarArea);

    waitForSecs(5);
    WebElement element1 = this.driverCustom.getWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, sidebarArea);


    if (!this.driverCustom.getChildElement(element1, By.xpath(RMConstants.LEFTSIDE_CONTAINER_CHILDS_XPATH))
        .getAttribute(RMConstants.CLASS).endsWith(RMConstants.EXPANDED)) {


      this.driverCustom
      .clickUsingActions(this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, sidebarArea));
      LOGGER.LOG.info(RMConstants.CLICKED_ON + "Artifact side bar " + sidebarArea);
    }
    waitForSecs(Duration.ofSeconds(15));
    try {
      Button btnClearFilter = this.engine
          .findElement(Criteria.isButton().withToolTip(RMConstants.CLEAR_FILTER_BUTTON_TOOLTIP)).getFirstElement();
      btnClearFilter.click();
      WebElement element = this.driverCustom.getWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, sidebarArea);
      if (!this.driverCustom.getChildElement(element, By.xpath("./../../..")).getAttribute(RMConstants.CLASS)
          .endsWith(RMConstants.EXPANDED)) {
        this.driverCustom
        .clickUsingActions(this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, sidebarArea));
        LOGGER.LOG.info(RMConstants.CLICKED_ON + sidebarArea + RMConstants.BUTTON);
      }
    }
    catch (Exception e) {
      waitForSecs(5);
    }
    waitForSecs(5);

    if (!this.driverCustom.isElementPresent(RMConstants.RM_ARTIFACTPAGE_MODULE_LINKS_LIST_XPATH, Duration.ofSeconds(5))) {
      return false;
    }
    waitForSecs(5);
    List<WebElement> linkElements = this.driverCustom.getWebElements(RMConstants.JAZZPAGE_LINKED_LINKS_XPATH);
    for (WebElement linkElement : linkElements) {
      String linkText = linkElement.getText().trim();
      if ((linkText.equals(idName) || idName.startsWith(linkText) || linkText.startsWith(idName)) && !linkText.isEmpty()) {
        LOGGER.LOG.info("'" + linkElement.getText() + "' is present in Artifact side bar " + linkType);
        return true;
      }
    }
    return false;
  }


  /**
   * <p>
   * This Method uses to get all the linked links from current artifact. <br>
   * From 'Artifact details' page or From 'Module details' page with one artifact selected. <br>
   * Expand Side bar area (if needed), open 'Links' section (using {@link ModuleEnums#SIDE_BAR_AREA} = 'Links', 'Module
   * Links',...). <br>
   * Get all the linked links displayed in {@link ModuleEnums#SIDE_BAR_AREA} section and combined into one line. <br>
   *
   * @param additionalParams stores keys: {@link ModuleEnums#SIDE_BAR_AREA} - Section contains linked links (Ex:
   *          'Links', 'Module Links') <br>
   * @return all links with link type.
   */
  public String getAllLinksFromSelectedArtifact(final Map<String, String> additionalParams) {
    waitForSecs(Duration.ofSeconds(30));
    StringBuilder sb = new StringBuilder();
    this.driverCustom.isElementClickable(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, Duration.ofSeconds(20),
        additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()));
    waitForSecs(5);
    WebElement element = this.driverCustom.getWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH,
        additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()));
    if (!this.driverCustom.getChildElement(element, By.xpath("./../../..")).getAttribute(RMConstants.CLASS)
        .endsWith("expanded")) {
      waitForSecs(2);
      this.driverCustom.clickUsingActions(this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH,
          additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString())));
      waitForSecs(25);
      LOGGER.LOG.info(
          RMConstants.CLICKED_ON + additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()) + RMConstants.BUTTON);
    }
    waitForSecs(25);
    // RMConstants.RM_ARTIFACTPAGE_MODULE_LINKS_LIST_XPATH
    // div[@class = 'com-ibm-rdm-web-grid-Module
    // module-container']//table[@rowtype = 'linkTypesGrouping' or @rowtype = 'resourceRow']
    if (!this.driverCustom.isElementPresent(
        "//div[@class='rdm-sidebar-section ibmdl']//div[@class = 'com-ibm-rdm-web-grid-Module module-container']//table[@rowtype = 'linkTypesGrouping' or @rowtype = 'resourceRow']",
        Duration.ofSeconds(5))) {
      return sb.toString();
    }
    List<WebElement> linksList = this.driverCustom.getWebElements(
        "//div[@class='rdm-sidebar-section ibmdl']//div[@class = 'com-ibm-rdm-web-grid-Module module-container']//table[@rowtype = 'linkTypesGrouping' or @rowtype = 'resourceRow']");
    int i = 0;
    while (i < linksList.size()) {
      WebElement parentElement = linksList.get(i);
      sb.append(parentElement.getText());
      sb.append(",");

      i++;

    }

    LOGGER.LOG.info("----------------------RM links----------------------------------------");
    LOGGER.LOG.info("---->" + sb.toString());
    LOGGER.LOG.info("----------------------------------------------------------------------\n");
    return sb.toString();

  }

  /**
   * <p>
   * This Method uses to get all the linked links from current artifact. <br>
   * From 'Artifact details' page or From 'Module details' page with one artifact selected. <br>
   * Expand Side bar area (if needed), open 'Links' section (using {@link ModuleEnums#SIDE_BAR_AREA} = 'Links', 'Module
   * Links',...). <br>
   * Get all the linked links displayed in {@link ModuleEnums#SIDE_BAR_AREA} section and combined into one line. <br>
   *
   * @param additionalParams stores keys: {@link ModuleEnums#SIDE_BAR_AREA} - Section contains linked links (Ex:
   *          'Links', 'Module Links') <br>
   * @return all links with link type.
   */
  public String getAllLinksFromSelectedView(final Map<String, String> additionalParams) {
    waitForSecs(Duration.ofSeconds(30));
    StringBuilder sb = new StringBuilder();
    // this.driverCustom.isElementClickable(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, Duration.ofSeconds(20),
    // additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()));
    // waitForSecs(5);
    // WebElement element = this.driverCustom.getWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH,
    // additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()));
    // if (!this.driverCustom.getChildElement(element, By.xpath("./../../..")).getAttribute(RMConstants.CLASS)
    // .endsWith("expanded")) {
    // waitForSecs(2);
    // this.driverCustom.clickUsingActions(this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH,
    // additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString())));
    // waitForSecs(25);
    // LOGGER.LOG.info(
    // RMConstants.CLICKED_ON + additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()) + RMConstants.BUTTON);
    // }
    // waitForSecs(25);
    // RMConstants.RM_ARTIFACTPAGE_MODULE_LINKS_LIST_XPATH //div[@class = 'com-ibm-rdm-web-grid-Module
    // module-container']//table[@rowtype = 'linkTypesGrouping' or @rowtype = 'resourceRow']
    //// div[@class='pageWrapper' ]//table[@rowtype='resourceRow']//a[@class='jazz-ui-ResourceLink']
    // div[@class='label trimLabel' or @class='with-image trimLabel']//a[@class='jazz-ui-ResourceLink']
    //// div[@class='label' or @class='with-image trimLabel']//a[@class='jazz-ui-ResourceLink']
    // div[@class='pageWrapper' ]//table[@rowtype='resourceRow']//a[@class='jazz-ui-ResourceLink']

    // div[@class = 'com-ibm-rdm-web-grid-Module module-container']//table[@rowtype = 'linkTypesGrouping' or @rowtype =
    // 'resourceRow']//a[@class='jazz-ui-ResourceLink']


    // div[@class = 'com-ibm-rdm-web-grid-Module module-container']//table[@rowtype = 'linkTypesGrouping' or @rowtype =
    // 'resourceRow']
    try {

      if (!this.driverCustom.isElementPresent(
          "//div[@class = 'com-ibm-rdm-web-grid-Module module-container']//table[@rowtype = 'linkTypesGrouping' or @rowtype = 'resourceRow']",
          Duration.ofSeconds(5))) {
        return sb.toString();
      }


    }
    catch (Exception e) {
      if (!this.driverCustom.isElementPresent(
          "//div[@class='label' or @class='with-image trimLabel']//a[@class='jazz-ui-ResourceLink']", Duration.ofSeconds(5))) {
        return sb.toString();
      }
    }
    try {
      if (!this.driverCustom.isElementPresent(
          "//div[@class='label' or @class='with-image trimLabel']//a[@class='jazz-ui-ResourceLink']", Duration.ofSeconds(5))) {
        List<WebElement> linksList = this.driverCustom.getWebElements(
            "//div[@class = 'com-ibm-rdm-web-grid-Module module-container']//table[@rowtype = 'linkTypesGrouping' or @rowtype = 'resourceRow']//a[@class='jazz-ui-ResourceLink']");

        int i = 0;
        while (i < linksList.size()) {
          WebElement parentElement = linksList.get(i);
          sb.append(parentElement.getText());
          sb.append(",");

          i++;


        }
      }

      else if (this.driverCustom.isElementPresent(
          "//div[@class = 'com-ibm-rdm-web-grid-Module module-container']//table[@rowtype = 'linkTypesGrouping' or @rowtype = 'resourceRow']//a[@class='jazz-ui-ResourceLink']",
          Duration.ofSeconds(5))) {
        List<WebElement> linksList = this.driverCustom.getWebElements(
            "//div[@class = 'com-ibm-rdm-web-grid-Module module-container']//table[@rowtype = 'linkTypesGrouping' or @rowtype = 'resourceRow']//a[@class='jazz-ui-ResourceLink']");

        int i = 0;
        while (i < linksList.size()) {
          WebElement parentElement = linksList.get(i);
          sb.append(parentElement.getText());
          sb.append(",");

          i++;

        }
      }
    }
    catch (Exception e) {
      List<WebElement> linksList = this.driverCustom
          .getWebElements("//div[@class='label' or @class='with-image trimLabel']//a[@class='jazz-ui-ResourceLink']");

      int i = 0;
      while (i < linksList.size()) {
        WebElement parentElement = linksList.get(i);
        sb.append(parentElement.getText());
        sb.append(",");

        i++;
      }
    }
    LOGGER.LOG.info("----------------------RM links----------------------------------------");
    LOGGER.LOG.info("---->" + sb.toString());
    LOGGER.LOG.info("----------------------------------------------------------------------\n");
    return sb.toString();

  }


  /**
   * <p>
   * From 'Artifacts' page or 'Module details' page, after Add the column of Link type in View using
   * {@link RMArtifactsPage#changeColumnDisplaySettings(String)}.<br>
   * And Search for the artifact to remove link from<br>
   * Click on'Edit Links' icon, 'Remove Links' option is displayed.<br>
   * Click on 'Remove Links' option, Linked Artifact option is displayed.<br>
   * Select Linked Artifact option, 'Confirm Removing This Link' dialog is displayed.<br>
   * Click on 'Yes' button.
   *
   * @param additionalParams stores keys: {@link ArtifactContextMenu#ADD_LINK_TO_ARTIFACT} - Link type <br>
   *          {@link ArtifactProperties#ID} - ID of Artifact link to be removed
   */
  public void removeLinkOfArtifactFromTable(final Map<String, String> additionalParams) {
    // Hover and click on 'Edit Links' dropdown image
    String startWithText = additionalParams.get(ArtifactProperties.ID.toString()) + ":";
    String colName =
        getColoumAttributeByHeader(additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString()));
    this.driverCustom
    .clickUsingActions(this.driverCustom.getWebElement("(//td[@class='" + colName + "' or @class='lk9_rem']//img)[1]"));
    this.driverCustom.clickUsingActions(
        this.driverCustom.getWebElement(RMConstants.RMPROJECTAREADASHBOARDPAGE_MENUITEM_XPATH, RMConstants.EDIT_LINKS));

    // Select 'Remove link' option
    this.driverCustom.clickUsingActions(this.driverCustom.getWebElement(
        RMConstants.RMARTIFACTPAGE_LINKCONTEXTMENU_OPTION_XPATH, LinkContextMenu.REMOVE_LINK.toString()));
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_LINKCONTEXTMENU_OPTION_XPATH, startWithText).click();
    LOGGER.LOG.info(LinkContextMenu.CONFIRM_REMOVE_DIALOG.toString() + RMConstants.IS_DISPLAYED);
    Button btnYes =
        this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.YES), Duration.ofSeconds(5)).getFirstElement();
    btnYes.click();
    waitForSecs(Duration.ofSeconds(5));
    // Refresh page - make sure link is removed and updated on table
    this.driverCustom.getWebDriver().navigate().refresh();
    waitForPageLoaded();
    waitForSecs(3);
    LOGGER.LOG.info(String.format("The link with ID '%s' is removed successfully",
        additionalParams.get(ArtifactProperties.ID.toString())));
  }

  /**
   * <p>
   * This method to get column header from CSV file based on column index
   *
   * @param pathCSVFile - full path of CSV file in your computer after exporting
   * @param columnIndex - index number of column that you want to get the value (start with 1)
   * @return value of column header in your given index
   */
  public String getColumnHeaderFromCSVFileByIndex(final String pathCSVFile, final String columnIndex) {
    String columnHeader = "";
    String line = "";
    // Update: Change Data Type of columnIndex to String and convert to Integer
    int columnIndex_number = Integer.parseInt(columnIndex);
    try (BufferedReader br = new BufferedReader(new FileReader(pathCSVFile))) {
      if ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        columnHeader = values[columnIndex_number - 1];
      }
      else {
        LOGGER.LOG.error(String.format("The CSV/XLSX '%s' file is empty. Please add data to this file", pathCSVFile));
      }
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }

    LOGGER.LOG.info(String.format("The header column name at column index '%s' is '%s'", columnIndex, columnHeader));
    return columnHeader;
  }


  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, RMConstants.ARTIFACTS);
    }
    catch (Exception e) {
      LOGGER.LOG.error("Page is not loaded");
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, RMConstants.ARTIFACTS);
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
  }

  /**
   * <p>
   * From 'Artifacts' page, Validates whether link of type 'Satisfied By Architecture Element' is created or added to
   * selected artifact. <br>
   * Open Artifact with {@link ArtifactProperties#ID} and {@link ArtifactProperties#ID_NAME}.
   *
   * @param additionalParams stores keys : {@link ArtifactProperties#ID_NAME}, {@link ArtifactProperties#ID}.
   * @return true - if the link is added to the artifact, throw Exception if link is not added.
   */
  public boolean openSatisfiedByArchitectureElementLink(final Map<String, String> additionalParams) {
    String colName = getColoumAttributeByHeader(AddLinkToArtifact.SATISFIED_BY_ARCHITECTURE_ELEMENT.toString());
    String aritfactID = additionalParams.get(ArtifactProperties.ID.toString());
    String linkedArtifactIDName = additionalParams.get(ArtifactProperties.ID_NAME.toString());
    waitForSecs(Duration.ofSeconds(10));
    WebElement artifactLink = this.driverCustom.getClickableWebElement(this.driverCustom
        .getChildElement(getWebElementOfSelectedArtifact(aritfactID), By.xpath(".//td[@class = '" + colName +
            "']//a[@class = 'jazz-ui-ResourceLink' and starts-with(text() , '" + linkedArtifactIDName + "')]")));
    this.driverCustom.clickUsingActions(artifactLink);
    LOGGER.LOG.info("Opened " + AddLinkToArtifact.SATISFIED_BY_ARCHITECTURE_ELEMENT.toString() + " and " +
        RMConstants.CLICKED_ON_ARTIFACT + linkedArtifactIDName);
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
    return true;
  }

  /**
   * <p>
   * This method used after {@link RMArtifactsPage#changeColumnDisplaySettings}
   * </p>
   *
   * @author PDU6HC
   * @param artifactID Artifact id to check the availabel link.
   * @param linkName added links in table ex: Traced By Architecture Element, Validated By, Implemented By...etc
   * @return true if artifact/module link present.
   */
  public boolean isArtifactLinkPresentforSelectedArtifact(final String artifactID, final String linkName) {
    String[] artifactIdAndLinkText = {artifactID, linkName};
    waitForSecs(Duration.ofSeconds(10));
    return this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_LINK_ON_COLUMN_XPATH, this.timeInSecs,
        artifactIdAndLinkText);
  }

  /**
   * @param additionalParams -
   * @return -
   */
  public Boolean clickOnAttributeLinkFromRightSideBar(final Map<String, String> additionalParams) {
    waitForSecs(Duration.ofSeconds(15));
    WebElement element = this.driverCustom.getWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH,
        additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()));
    if (!this.driverCustom.getChildElement(element, By.xpath(RMConstants.LEFTSIDE_CONTAINER_CHILDS_XPATH))
        .getAttribute(RMConstants.CLASS).endsWith(RMConstants.EXPANDED)) {
      this.driverCustom.clickUsingActions(this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH,
          additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString())));
      LOGGER.LOG.info(
          RMConstants.CLICKED_ON + "Artifact side bar " + additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString()));
      return true;
    }
    return false;
  }

  /**
   * Verify Warning Message is visible or invisible - via expectResult <br>
   * Case 1: if expectResult is true - Verify Warning Message is visible <br>
   * Case 2: if expectResult is false - Verify Warning Message is invisible <br>
   *
   * @param expectResult - Expective Warning Message is visible/invisible
   * @return true - if expectResult and actual display of Warning Message are the same
   * @author LPH1HC
   */
  public boolean isWarningMessageExistsInArtifactLinksSideBarArea(final String expectResult) {
    boolean check = false;
    if (this.driverCustom.isElementVisible(RMConstants.WARNING_MESSAGE_XPATH, Duration.ofSeconds(50))) {
      check = Boolean.parseBoolean(expectResult);
    }
    else {
      check = !Boolean.parseBoolean(expectResult);
    }
    return check;
  }


  /**
   * @author LPH1HC
   *         <p>
   *         Get total quantity of Artifact Links in Side Bar Area
   * @return number of Artifact Links of linkType
   */
  public String getNumberOfArtifactLinksInSideBarArea() {
    String temp = "";
    this.driverCustom.isElementInvisible(RMConstants.LOADING_ARTIFACT_LINKS_XPATH, Duration.ofSeconds(150));
    this.driverCustom.isElementVisible(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(150), "Artifact Links (");
    temp = this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, "Artifact Links (").getText();
    String number = temp.replace("Artifact Links (", "").replace(")", "").trim();
    return number;
  }

  /**
   * @author PDU6HC
   *         <p>
   *         Select Modules radio button with label 'What to look in' in Create Link dialog Input module id in dropdown
   * @param moduleId - input Module id
   */
  public void selectModuleInCreateLinkDialog(final String moduleId) {
    this.driverCustom.isElementVisible(RMConstants.RMLINKSPAGE_MODULES_LABEL_CREATELINK_DIALOG_XPATH, this.timeInSecs);
    try {
      this.driverCustom.isElementClickable(RMConstants.RMLINKSPAGE_MODULE_RADIO_BUTTON_XPATH, Duration.ofSeconds(30));
      WebElement rdoModules = this.driverCustom.getWebElement(RMConstants.RMLINKSPAGE_MODULE_RADIO_BUTTON_XPATH);
      rdoModules.click();
      waitForSecs(Duration.ofSeconds(10));
    }
    catch(Exception e){
      LOGGER.LOG.info("No Module to select!");
    }

    String xpathModuleDropDown1 = "//input[contains(@id,'ArtifactInstancesFilteringSelect')][@style='color: black;']";
    String xpathModuleDropDown2 = "//input[contains(@id,'ArtifactInstancesFilteringSelect_1')][@style='color: gray;']";
    String xpathOption = String.format("//span[text()='%s']/parent::span/parent::div", moduleId);
    WebElement txtModules = null;
    try {
      this.driverCustom.isElementVisible(xpathModuleDropDown1, Duration.ofSeconds(30));
      txtModules = this.driverCustom.getWebDriver().findElement(By.xpath(xpathModuleDropDown1));
      txtModules.clear();
      txtModules.sendKeys(moduleId + Keys.ENTER);
      waitForSecs(Duration.ofSeconds(5));
    } catch (NoSuchElementException noSuchElement) {
      TextField txtSearch = this.engine.findFirstElementWithDuration(
          Criteria.isTextField().withText("Select all modules, a single module, or search by a name or ID"), Duration.ofSeconds(30));
      txtSearch.setText(moduleId);
      this.driverCustom.isElementVisible(xpathOption, Duration.ofSeconds(30));
      this.driverCustom.getWebDriver().findElement(By.xpath(xpathOption)).click();
      waitForSecs(Duration.ofSeconds(5));
    } catch (Exception e) {
      this.driverCustom.isElementVisible(xpathModuleDropDown2, Duration.ofSeconds(30));
      txtModules = this.driverCustom.getWebElement(xpathModuleDropDown2);
      txtModules.clear();
      txtModules.sendKeys(moduleId + Keys.ENTER);
      waitForSecs(Duration.ofSeconds(5));
    }

    this.driverCustom.click(RMConstants.RMLINKSPAGE_WHAT_TO_LOOK_IN_XPATH);
    this.driverCustom.isElementInvisible(RMConstants.RMARTIFACTPAGE_LINK_MODULE_LOADINGARTIFACTS_XPATH, Duration.ofSeconds(30));
  }

  /**
   * <p>
   * This method used after {@link RMArtifactsPage#changeColumnDisplaySettings}
   * </p>
   *
   * @author PDU6HC
   * @param additionalParams stores keys: linkName - name of link you want to verify. id1 - id of artifact 1. id2 - id
   *          of artifact 2. so on so forth...
   * @return true if link existed for multiple artifacts.
   */
  public boolean verifyLinkExistedForMultipleArtifacts(final Map<String, String> additionalParams) {
    RMArtifactsPage artifactPage = new RMArtifactsPage(this.driverCustom);

    for (int artifactNum = 1; artifactNum < additionalParams.size(); artifactNum++) {
      String artifactID = additionalParams.get(ArtifactProperties.ID.toString() + artifactNum);
      String linkName = additionalParams.get("linkName");

      artifactPage.selectArtifact(artifactID);

      isArtifactLinkPresentforSelectedArtifact(artifactID, linkName);
    }
    return true;
  }

  /**
   * @param additionalParams - 
   * @return - 
   */
  public boolean openLinkIfExists(final Map<String, String> additionalParams) {
    waitForSecs(Duration.ofSeconds(10));
    String linkSectionText = additionalParams.get(ModuleEnums.SIDE_BAR_AREA.toString());
    String idName = additionalParams.get(ArtifactProperties.ID_NAME.toString());

    if (linkSectionText.equals("Module Links")) {
      this.driverCustom.click("//span[@class='section-content-title' and text()='Module']");
    }

    WebElement linksSection = this.driverCustom.getClickableWebElement(
        this.driverCustom.getPresenceOfWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, linkSectionText));
    waitForSecs(Duration.ofSeconds(5));
    if (!this.driverCustom.getChildElement(linksSection, By.xpath(RMConstants.LEFTSIDE_CONTAINER_CHILDS_XPATH))
        .getAttribute(RMConstants.CLASS).endsWith(RMConstants.EXPANDED)) {
      this.driverCustom.clickUsingActions(
          this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, linkSectionText));
      LOGGER.LOG.info(RMConstants.CLICKED_ON + "Artifact side bar " + linkSectionText);
      waitForSecs(Duration.ofSeconds(15));
    }

    try {
      Button btnClearFilter = this.engine
          .findElement(Criteria.isButton().withToolTip(RMConstants.CLEAR_FILTER_BUTTON_TOOLTIP)).getFirstElement();
      btnClearFilter.click();
      WebElement element = this.driverCustom.getWebElement(RMConstants.LEFTSIDE_CONTAINER_BOX_XPATH, linkSectionText);
      if (!this.driverCustom.getChildElement(element, By.xpath("./../../..")).getAttribute(RMConstants.CLASS)
          .endsWith(RMConstants.EXPANDED)) {
        this.driverCustom.clickUsingActions(
            this.driverCustom.getWebElement(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, linkSectionText));
        LOGGER.LOG.info(RMConstants.CLICKED_ON + linkSectionText + RMConstants.BUTTON);
      }
      waitForSecs(Duration.ofSeconds(5));
    }
    catch (Exception e) {
      waitForSecs(Duration.ofSeconds(5));
    }

    if (!this.driverCustom.isElementPresent(RMConstants.RM_ARTIFACTPAGE_MODULE_LINKS_LIST_XPATH, Duration.ofSeconds(5))) {
      return false;
    }

    waitForSecs(Duration.ofSeconds(5));
    List<WebElement> linkElements = this.driverCustom.getWebElements(RMConstants.JAZZPAGE_LINKED_LINKS_XPATH);
    for (WebElement linkElement : linkElements) {
      if (linkElement.getText().contains(idName)) {
        WebElement buttonTree = this.driverCustom
            .getPresenceOfWebElement("//span[@class='link-result']//a[contains(text(),'" + idName + "')]");
        JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
        je.executeScript("arguments[0].scrollIntoView(true);", buttonTree);
        LOGGER.LOG.info("scroll successfully");
        this.driverCustom.getActions().moveToElement(buttonTree).build().perform();
        Row row = this.engine.findElement(Criteria.isRow().containsText(idName)).getFirstElement();
        row.click();
        waitForSecs(Duration.ofSeconds(10));
        LOGGER.LOG.info("clicked on " + idName + " link.");

        waitForSecs(Duration.ofSeconds(15));
        return true;
      }
    }
    return false;
  }

  /**
   * @param additionalParams - 
   */
  public void createNewItemFromCreateLinkDialog(final Map<String, String> additionalParams) {
    // update
    waitForPageLoaded();
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE_01, Duration.ofSeconds(180));
    String linkType = additionalParams.get(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString());
    if (linkType.equals(AddLinkToArtifact.LINK_TO.toString()) ||
        linkType.equals(AddLinkToArtifact.SATISFIED_BY.toString()) ||
        linkType.equals(AddLinkToArtifact.LINK_FROM.toString()) ||
        linkType.equals(AddLinkToArtifact.SATISFIES.toString())) {
      chooseSatisfiedOrLinkTo(additionalParams);
      return;
    }
    else if (linkType.equals(AddLinkToArtifact.VALIDATED_BY.toString())) {
      createValidatedByLink(additionalParams);
      return;
    }
    else if (linkType.contains(AddLinkToArtifact.IMPLEMENTED_BY.toString())) {

      createImplementedByLink(additionalParams);
      return;
    }
    else if (linkType.contains(AddLinkToArtifact.SATISFIED_BY_ARCHITECTURE_ELEMENT.toString())) {
      chooseExistingSatisfiedArchitectureLink(additionalParams);
      return;
    }
    throw new WebAutomationException(
        "Add link to artifact not matching any of the links.\nPlease provide valide link tye.");
  }

  /**
   * <p>
   * After open Create Links dialog {@link RMLinksPage#chooseExistingItemFromCreateLinkDialog} using
   * {@link AddLinkToArtifact#VALIDATED_BY} <br>
   * Select 'Choose Existing' or 'Create New' radion button for {Create link with existing artifact} or {Create link
   * with newly created artifact} using {@link TestProperties#CREATE_OR_EXISTING}. <br>
   * --If {@link TestProperties#CHOOSE_EXISTING} is selected, no need to select on radio button. <br>
   * -----Input Project area name into 'Artifact Container' dropdown using {@link RMConstants#PROJECT_AREA}. <br>
   * -----Open 'Inline Filters' and search artifact using ID and Click OK button. <br>
   * --If {@link TestProperties#CREATE_NEW} is selected, click on radion button with following text. <br>
   * -----Input Test case name for new Test case and Click OK button. <br>
   *
   * @param additionalParams stores keys: {@link TestProperties#CREATE_OR_EXISTING} (with value:
   *          {@link TestProperties#CHOOSE_EXISTING} or @link {@link TestProperties#CREATE_NEW}); <br>
   *          If 'Choose Existing': {@link RMConstants#PROJECT_AREA}; {@link TestProperties#ID}. <br>
   *          If 'Create New': {@link TestProperties#TEST_CASE_NAME} - name of test case to be created.
   */
  public void createValidatedByLink(final Map<String, String> additionalParams) {
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_ARTIFACTCONTAINER_XPATH, Duration.ofSeconds(40))) {
      WebElement ele = this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_ARTIFACTCONTAINER_TEXTBOX_XPATH);
      ele.clear();
      ele.sendKeys(additionalParams.get(RMConstants.PROJECT_AREA));
      waitForSecs(2);
      try {
        this.driverCustom.click(RMConstants.JAZZADMIN_SPANSELECTION_XPATH,
            additionalParams.get(RMConstants.PROJECT_AREA));
        LOGGER.LOG.info(additionalParams.get(RMConstants.PROJECT_AREA) + " Project area from Project area dropdown " +
            RMConstants.IS_SELECTED);
      }
      catch (Exception e) {
        throw new WebAutomationException(
            additionalParams.get(RMConstants.PROJECT_AREA) + " not valid/not found in drop dowm.\n" + e.getMessage());
      }
    }
    waitForSecs(Duration.ofSeconds(10));


    // Create with 'Create New'

    this.driverCustom.isElementClickable(RMConstants.RMMODULEINSIDEPAGE_CREATELINKNEWTESTCASE_BUTTON_XPATH, Duration.ofSeconds(30));
    Label label =
        this.engine.findElement(Criteria.isLabel().withText(TestProperties.CREATE_NEW.toString())).getFirstElement();
    label.click();
    LOGGER.LOG.info("Clicked on Create New button");
    waitForSecs(Duration.ofSeconds(15));
    this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);

    this.driverCustom.click(RMConstants.RMMODULEINSIDEPAGE_CREATELINKTESTCASE_TEXTBOX_XPATH, RMConstants.NAME);
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.typeText(RMConstants.RMMODULEINSIDEPAGE_CREATELINKTESTCASE_TEXTBOX_XPATH,
        additionalParams.get(RMConstants.TESTCASE_NAME), RMConstants.NAME);
    waitForSecs(Duration.ofSeconds(5));


    WebElement buttonTree1 = this.driverCustom.getPresenceOfWebElement("//body[@class='claro']//button[text()='OK']");
    JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
    je.executeScript("arguments[0].scrollIntoView(true);", buttonTree1);
    LOGGER.LOG.info("scroll 1 successfully");
    this.driverCustom.getActions().moveToElement(buttonTree1).build().perform();
    Button requirementbtnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    requirementbtnOK.click();


    waitForSecs(Duration.ofSeconds(10));
    LOGGER.LOG.info(RMConstants.CLICK_ON_OK_BUTTON);

    refresh();
    waitForSecs(Duration.ofSeconds(15));
  }

  /**
   * Wait for certain period of time and refresh page <br>
   * To Handle delay in loading newly created link between domain <br> 
   * @author VDY1HC
   * @param timeInSeconds - time in seconds to wait
   */
  public void refreshPageAfterPeriodOfTime (String timeInSeconds) {
    waitForSecs(Integer.parseInt(timeInSeconds));
    this.driverCustom.getWebDriver().navigate().refresh();
  }

  /**
   * @param additionalParams - 
   */
  public void createImplementedByLink(final Map<String, String> additionalParams) {
    waitForSecs(Duration.ofSeconds(5));
    // update
    waitForPageLoaded();
    // Module Type and Information Type
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_ARTIFACTCONTAINER_XPATH, Duration.ofSeconds(40))) {
      WebElement ele = this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_ARTIFACTCONTAINER_TEXTBOX_XPATH);
      ele.clear();
      ele.sendKeys(additionalParams.get(RMConstants.PROJECT_AREA));
      waitForSecs(2);
      try {
        this.driverCustom.click(RMConstants.JAZZADMIN_SPANSELECTION_XPATH,
            additionalParams.get(RMConstants.PROJECT_AREA));
        LOGGER.LOG.info(additionalParams.get(RMConstants.PROJECT_AREA) + " Project area from Project area dropdown " +
            RMConstants.IS_SELECTED);
      }
      catch (Exception e) {
        throw new WebAutomationException(
            additionalParams.get(RMConstants.PROJECT_AREA) + " not valid/not found in drop dowm.\n" + e.getMessage());
      }
    }

    Dialog dlgAddLink1 =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(RMConstants.CREATE_LINK), this.timeInSecs)
        .getFirstElement();
    waitForSecs(8);
    this.driverCustom.click("//input[@id='newItemCheckBox']");

    waitForSecs(8);

    LOGGER.LOG.info("'Create New' Radio button selected successfully");


    LOGGER.LOG.info("Select work item type");
    // TODO: handle exception
    // setSummary("test automation story");
    try {
      Text txtSummary =
          this.engine.findElementWithDuration(Criteria.isTextField().hasLabel("Summary:").inContainer(dlgAddLink1), Duration.ofSeconds(10))
          .getFirstElement();
      txtSummary.setText("test story to automate");
    }

    catch (Exception e) {
      // TODO: handle exception
      setSummary(additionalParams.get(RMConstants.SUMMARY));
    }
    LOGGER.LOG.info("summ" + "ary set successfully");
    // Select Field Against
    Dropdown drdFieldAgainst =
        this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Filed Against:"), Duration.ofSeconds(10)).getFirstElement();
    drdFieldAgainst.selectOptionWithText(additionalParams.get(RMConstants.FILED_AGAINST));
    LOGGER.LOG.info(
        "Filed Against dropdown value '" + additionalParams.get(RMConstants.FILED_AGAINST) + "' Selected successfully");
    WebElement buttonTree = this.driverCustom.getPresenceOfWebElement("//body[@class='claro']//button[text()='OK']");
    JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
    je.executeScript("arguments[0].scrollIntoView(true);", buttonTree);
    LOGGER.LOG.info("scroll 1 successfully");
    this.driverCustom.getActions().moveToElement(buttonTree).build().perform();
    Button requirementbtnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    requirementbtnOK.click();
    LOGGER.LOG.info("clicked 'OK' button successfully");
    waitForSecs(Duration.ofSeconds(5));
    refresh();
    waitForSecs(Duration.ofSeconds(15));
  }

  /**
   * @param summary - 
   * @return - 
   */
  public String setSummary(final String summary) {
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getPresenceOfWebElement("//div[@aria-label='Summary']").click();
    LOGGER.LOG.info("Clicked on create work item text box successfully");

    waitForSecs(3);
    this.driverCustom.getPresenceOfWebElement("//div[@aria-label='Summary']").sendKeys(summary);
    LOGGER.LOG.info("Summary ' " + summary + " ' Set successfully");
    waitForSecs(2);

    return summary;
  }

  /**
   * @param attributeSummary -
   * @return - 
   */
  public String getLinkID(final String attributeSummary) {
    String result = this.driverCustom
        .getText("//div[@class='rdm-dashboard-tab-page']//a[contains(text() , '" + attributeSummary + "')]");

    if (result != null) {
      String[] resArr = result.split(":");
      if (resArr.length >= 2) {
        LOGGER.LOG.info("Work item id is '" + resArr[0] + "'");
        return resArr[0];
      }
    }
    return null;
  }
}