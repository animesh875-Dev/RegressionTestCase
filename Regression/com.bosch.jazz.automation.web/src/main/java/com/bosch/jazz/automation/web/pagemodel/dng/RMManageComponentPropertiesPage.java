/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.RadioButton;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.element.text.TextField;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * <p>
 * Represents the Requirement Management Component Properties Page. <br>
 * This is common for ReqIF,Artifact Type,Artifact Attribute,Artifact Data Type etc.. for Creating, Editing and Deleting
 * purpose.
 */
public class RMManageComponentPropertiesPage extends AbstractRMPage {

  /**
   * Constructor setting the {@link WebDriverCustom}.
   *
   * @param driverCustom required for interacting with the browser.
   */

  public RMManageComponentPropertiesPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTextFieldFinder(), new TextFieldFinder(), new JazzRadioButtonFinder(),
        new JazzButtonFinder(), new RowCellFinder(), new JazzDropdownFinder(), new TextFieldFinder(),
        new JazzDialogFinder());
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select component properties type like 'Artifact Type',Artifact Attributes','ReqIF','Link Validity' etc...
   *
   * @param propertiesType like 'Artifact Types','Artifact Attributes','Link Validity' etc...
   */
  public void selectComponentPropertiesType(final String propertiesType) {
    waitForPageLoaded();
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_SELECTPAGE_XPATH, propertiesType);
  }


  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select component properties type using
   * {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Provide Name for the selected properties type.
   *
   * @param name input name for the selected properties type.
   * @return input name
   */
  public String inputNameForComponentProperties(final String name) {
    waitForPageLoaded();
    waitForSecs(10);
    String inputName = "";
    this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_NAMEFIELD_XPATH, Duration.ofSeconds(5));
    List<TextField> textFields =
        this.engine.findElementWithDuration(Criteria.isTextField(), this.timeInSecs).getElementList();
    for (TextField nameField : textFields) {
      WebElement nameFieldElement = nameField.getWebElement();
      if (nameFieldElement.getAttribute("name").equals("title") & nameFieldElement.isDisplayed()) {
        nameFieldElement.clear();
        nameFieldElement.sendKeys(name);
        return nameFieldElement.getAttribute("value");
      }
    }
    waitForSecs(3);
    return inputName;
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'Attribute Data Types' using
   * {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Click on 'New Data Type...' button present in 'Attribute Data Types' section.<br>
   * New Data Type window displayed right side of the page.<br>
   * Provide Name of the data type using
   * {@link RMManageComponentPropertiesPage#inputNameForComponentProperties(String)}.<br>
   * Set the Description of the data type using
   * {@link RMManageComponentPropertiesPage#setComponentPropertiesDescription(String)}.
   * <p>
   * If Kind of Value is 'Simple', provide 'Base Data Type','URI' and click on 'Save' button.*
   * <p>
   * If Kind of Value is 'Enumerated list of values', provide 'Base Data Type','Add Multiple Values','URI' and click on
   * 'Save' button.
   * <p>
   * If Kind of Value is 'Bounded range of values', provide 'Base Data Type','Minimum Value','Maximum Value','URI' and
   * click on 'Save' button.
   *
   * @param additionalParams stores key value
   *          'KIND_OF_VALUE','BASE_DATA_TYPE','MULTIPLE_VALUES','MINIMUM_VALUE','MAXIMUM_VALUE' etc...
   * 
   */
  public void createNewAttributeDataType(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String kindOfValue = additionalParams.get(RMConstants.KIND_OF_VALUE);
    if (kindOfValue.equals("Simple")) {
      RadioButton rbSimple = this.engine
          .findElementWithDuration(Criteria.isRadioButton().withText(kindOfValue), this.timeInSecs).getFirstElement();
      rbSimple.click();
      this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_BASEDATATYPE_XPATH);
      this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_SELECTTYPE_XPATH,
          additionalParams.get("BASE_DATA_TYPE"));
    }
    else if (kindOfValue.equals("Enumerated list of values")) {
      RadioButton rbEnum = this.engine
          .findElementWithDuration(Criteria.isRadioButton().withText(kindOfValue), this.timeInSecs).getFirstElement();
      rbEnum.click();
      clickOnJazzSpanButtons("Add Multiple Values...");
      String st = additionalParams.get("MULTIPLE_VALUES");
      String[] str = st.split(",");
      StringBuilder temp = new StringBuilder();
      for (String el : str) {
        temp.append(temp + el + "\n");
      }
      Dialog addMultipleValuesDialog =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Add Multiple Values"), this.timeInSecs)
          .getFirstElement();
      TextField addValuesTextField =
          this.engine.findFirstElement(Criteria.isTextField().isMultiLine().inContainer(addMultipleValuesDialog));
      addValuesTextField.setText(temp.toString());
      this.driverCustom.click(RMConstants.JAZZPAGE_BUTTON_XPATH, "OK");
    }
    else if (kindOfValue.equals("Bounded range of values")) {
      RadioButton rbBounded = this.engine
          .findElementWithDuration(Criteria.isRadioButton().withText(kindOfValue), this.timeInSecs).getFirstElement();
      rbBounded.click();
      this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_BASEDATATYPE_XPATH);
      this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_SELECTTYPE_XPATH,
          additionalParams.get("BASE_DATA_TYPE"));
      TextField minimumTextField = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Minimum"));
      minimumTextField.setText(additionalParams.get("MINIMUM_VALUE"));
      TextField maximumTextField = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Maximum"));
      maximumTextField.setText(additionalParams.get("MAXIMUM_VALUE"));

    }
    List<WebElement> e = this.driverCustom.getWebElements(RMConstants.MANAGECOMPPROPERTIES_URI_TEXTFIELD_XPATH);
    for (WebElement lst : e) {
      if (this.driverCustom.isElementVisible(lst, Duration.ofSeconds(5))) {
        lst.sendKeys(additionalParams.get("URI") + DateUtil.getCurrentDateAndTime());
        break;
      }
    }
    Button saveButton =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Save"), this.timeInSecs).getFirstElement();
    saveButton.click();
    waitForPageLoaded();
    //return this.driverCustom.getWebElementsText(RMConstants.MANAGECOMPPROPERTIES_ALT_LIST_XPATH);
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'Artifact Attributes' using
   * {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Click on 'New Attribute...' button present in 'Artifact Attribute' section.<br>
   * New Attribute section is displayed in right side of the page.<br>
   * Provide Name of the attribute using
   * {@link RMManageComponentPropertiesPage#inputNameForComponentProperties(String)}.<br>
   * Set the Description of the attribute using
   * {@link RMManageComponentPropertiesPage#setComponentPropertiesDescription(String)}.<br>
   * Hover on 'Data Type' values.
   * <p>
   * If 'Number of Values' is 'One value allowed' then provide the Initial value as per data type.<br>
   * Provide 'URI' and click on 'Save' button.
   * <p>
   * If 'Number of Values' is 'Multiple values allowed' then provide the Initial value as per data type.<br>
   * Provide 'URI' and click on 'Save' button.
   *
   * @param additionalParams stores key value 'DATA_TYPE','NUMBER_OF_VALUES','SINGLE_VALUE','INITIAL_VALUES','URI'
   *          etc...
   *  Created artifact attribute Names.
   */
  public void createNewArtifactAttributeDataType(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.isElementInvisible("//div[normalize-space(text()) = 'Loading...']", Duration.ofSeconds(60));
    List<WebElement> ls = this.driverCustom.getWebElements(RMConstants.MANAGECOMPPROPERTIES_SELECTDATATYPE_XPATH);
    WebElement w = this.driverCustom.getWebElement(RMConstants.MANAGECOMPPROPERTIES_DATATYPEBOX_XPATH);
    for (WebElement ele : ls) {
      if (ele.getText().trim().equals(additionalParams.get("DATA_TYPE"))) {
        ele.click();
        break;
      }
      new Actions(this.driverCustom.getWebDriver()).moveToElement(w).build().perform();
    }
    if (additionalParams.get("NUMBER_OF_VALUES").equals("One value allowed")) {
      RadioButton singleRadioButton =
          this.engine.findElementWithDuration(Criteria.isRadioButton().withText("One value allowed"), this.timeInSecs)
          .getFirstElement();
      singleRadioButton.click();
      TextField initialValueTextField = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Initial Value"));
      initialValueTextField.setText(additionalParams.get("SINGLE_VALUE").trim());
    }
    else if (additionalParams.get("NUMBER_OF_VALUES").equals("Multiple values allowed")) {
      RadioButton multipleRadioButton = this.engine
          .findElementWithDuration(Criteria.isRadioButton().withText("Multiple values allowed"), this.timeInSecs)
          .getFirstElement();
      multipleRadioButton.click();
      String va = additionalParams.get("INITIAL_VALUES");
      String[] te = va.split(",");
      for (String element : te) {
        this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_MULTIPLEVALUE_CHECKBOX_XPATH, element);
      }
    }
    else if (additionalParams.get("NUMBER_OF_VALUES").equals("null")) {
       List<WebElement> e = this.driverCustom.getWebElements(RMConstants.MANAGECOMPPROPERTIES_INITIALVALUE_TEXTFIELD_XPATH);
       for (WebElement lst : e) {
         if (this.driverCustom.isElementVisible(lst, Duration.ofSeconds(5))) {
           lst.sendKeys(additionalParams.get("INITIAL_VALUE").trim());
           break;
         }
       }
      
     }
    
    
    List<WebElement> e = this.driverCustom.getWebElements(RMConstants.MANAGECOMPPROPERTIES_URI_TEXTFIELD_XPATH);
    for (WebElement lst : e) {
      if (this.driverCustom.isElementVisible(lst, Duration.ofSeconds(5))) {
        lst.sendKeys(additionalParams.get("URI")+ DateUtil.getCurrentDateAndTime()+".com");
        break;
      }
    }
    clickOnJazzSpanButtons("Save");
    waitForPageLoaded();
   // return this.driverCustom.getWebElementsText(RMConstants.MANAGECOMPPROPERTIES_ADT_LIST_XPATH);
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'Artifact Types' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Click on 'New Type...' button present in 'Artifact Types' section.<br>
   * New Artifact Type section is displayed in right side of the page.<br>
   * Provide Name of the artifact type using
   * {@link RMManageComponentPropertiesPage#inputNameForComponentProperties(String)}.<br>
   * Set the Description of the artifact type using
   * {@link RMManageComponentPropertiesPage#setComponentPropertiesDescription(String)}.<br>
   * Select the 'Default artifact format' value from the drop down list.<br>
   * Provide 'URI' value.<br>
   * Click on 'Add Attribute' button,'Add Attribute' dialog is displayed.<br>
   * Select the Attribute Type, click on 'OK' button.<br>
   * Click on 'Save' button.
   *
   * @param additionalParams stores key value 'DEFAULT_ARTIFACT_FORMAT','URI','ATTRIBUTE_DATA_TYPE' etc...
   *  Created artifact type Names.
   */
  public void createNewArtifactType(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.typeText(RMConstants.MANAGECOMPPROPERTIES_DEFAULTARTIFACTFORMATE_CHECKBOX_XPATH,
        additionalParams.get("DEFAULT_ARTIFACT_FORMAT"));
    List<WebElement> e = this.driverCustom.getWebElements(RMConstants.MANAGECOMPPROPERTIES_AT_URI_TEXTFIELD_XPATH);
    for (WebElement lst : e) {
      if (this.driverCustom.isElementVisible(lst, Duration.ofSeconds(5))) {
        lst.sendKeys(additionalParams.get("URI") + DateUtil.getCurrentDateAndTime());
        break;
      }
    }

    String attributeDataType = additionalParams.get("ATTRIBUTE_DATA_TYPE");
    if ((attributeDataType!=null) && !attributeDataType.isEmpty()) {
      Button btnAddAttribute = this.engine
          .findElementWithDuration(Criteria.isButton().withText("Add Attribute..."), this.timeInSecs).getFirstElement();
      btnAddAttribute.click();
      // Select Attribute=
      WebElement rowAttribute = this.driverCustom.getPresenceOfWebElement(RMConstants.ADD_ATTRIBUTE_ROW_ATTRIBUTE_XPATH, attributeDataType);
      rowAttribute.click();
      // Click Ok button
      Button btnOK =
          this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
      btnOK.click();
    }
    clickOnJazzSpanButtons("Save");
    waitForSecs(20);
   // return this.driverCustom.getWebElementsText(RMConstants.MANAGECOMPPROPERTIES_AT_LIST_XPATH);

  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Click on 'New Definition...' button present in 'ReqIF Definitions' section.<br>
   * New Definition section is displayed in right side of the page.<br>
   * Provide Name ReqIF using {@link RMManageComponentPropertiesPage#inputNameForComponentProperties(String)}.<br>
   * Set the Description of the ReqIF using
   * {@link RMManageComponentPropertiesPage#setComponentPropertiesDescription(String)}.<br>
   * Include Links/Folders/Tags in the ReqIF Definition.<br>
   * Click on Button Type 'Add Artifact...','Add Module...' etc...,'Select Artifacts for Export' or 'Select Modules for
   * Export' dialog is displayed.<br>
   * Select the required Artifact/Module, Click on 'Add' button.<br>
   * Click on 'Close' button.<br>
   * Click on 'Save' button to save the ReqIF Definition.
   *
   * @param additionalParams stores key value 'INCLUDE_TYPE','BUTTON_TYPE' etc...
   * @return Created ReqIF Definition Names.
   */
  public List<String> createNewReqIFDefinition(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String includeLink = additionalParams.get("INCLUDE_TYPE");
    if ((includeLink != null)) {
      String[] links = includeLink.split(",");
      for (String link : links) {
        this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_INCLUDECHECKBOX_XPATH, link);
      }
    }
    clickOnJazzSpanButtons(additionalParams.get("BUTTON_TYPE"));
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_SELECTMODULE_XPATH);
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    List<String> artifactlist =
        this.driverCustom.getWebElementsText(RMConstants.RMARTIFACTPAGE_ARTIFACTNAMESFOR_EXPORT_XPATH);
    for (int i = 1; i <= artifactlist.size(); i++) {
      this.driverCustom.getWebDriver().findElement(By.xpath("//div[@class='content-area']/a[" + i + "]/span[2]"))
      .click();
      Button addButton =
          this.engine.findElementWithDuration(Criteria.isButton().withText("Add"), this.timeInSecs).getFirstElement();
      addButton.click();
      this.driverCustom.waitForSecs(Duration.ofSeconds(1));
      if (i == 5) {
        Button closeButton =
            this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.CLOSEBUTTON), this.timeInSecs)
            .getFirstElement();
        closeButton.click();
        break;
      }
    }
    clickOnJazzButtons("Save");
    waitForPageLoaded();
    return this.driverCustom.getWebElementsText(RMConstants.MANAGECOMPPROPERTIES_SELECTPA_XPATH);
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Create a new ReqIF Definition using {@link RMManageComponentPropertiesPage#createNewReqIFDefinition(Map)}.<br>
   * Click on the created ReqIF Definition, list of menus displayed.<br>
   * Click on 'Export...' menu from the list of menus.<br>
   * 'Creating ReqIF File' dialog is displayed, wait till it's achived 100%.<br>
   * Click on 'Download' button,file is downloaded with definition name.<br>
   * Click on 'Close' button.
   * <p>
   * Click on 'Delete..' menu from list of menus,'Delete' pop up window is displayed.<br>
   * Click on 'Yes' button.
   * <p>
   * Click on 'Save' button to save the ReqIF Definition.
   *
   * @param reqIFDefinitionName name of ReqIF Definition.
   * @param menuOption Menu type like Export...,Create..., Copy... and Delete... etc...
   * @return List of Exported Definition
   */
  public List<String> exportReqIF(final String reqIFDefinitionName, final String menuOption) {
    waitForPageLoaded();
    String value = "";
    List<String> lst = new ArrayList<>();
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, reqIFDefinitionName);
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_REQIF_DROPDOWN_XPATH, reqIFDefinitionName);
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_MENU_XPATH, menuOption);
    if (menuOption.equals("Export...")) {
      int i = 1;
      while (i < 30000) {
        value = this.driverCustom.getWebElement(RMConstants.MANAGECOMPPROPERTIES_PROGRESSBAR_XPATH)
            .getAttribute("aria-valuenow");
        if (value.equals("100")) {
          this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_EXPORT_COMPLETED_MESSAGE_XPATH, Duration.ofSeconds(30));
          String exportCompletedMessage = this.driverCustom.getText(RMConstants.MANAGECOMPPROPERTIES_EXPORT_COMPLETED_MESSAGE_XPATH).trim();
          clickOnButtons("Download");
          clickOnButtons("Close");
          //          clickOnJazzButtons("Download");
          //          clickOnJazzButtons("Close");
          lst.add(exportCompletedMessage);
          break;
        }
        i++;
      }
      //lst= this.driverCustom.getWebElementsText(RMConstants.MANAGECOMPPROPERTIES_EXPORTMENU_XPATH);
    }
    else if (menuOption.equals(RMConstants.DELETEBUTTON)) {
      clickOnJazzButtons("Yes");
      lst = this.driverCustom.getWebElementsText(RMConstants.MANAGECOMPPROPERTIES_SELECTPA_XPATH);
    }
    else {
      clickOnJazzSpanButtons("Save");
      lst = this.driverCustom.getWebElementsText(RMConstants.MANAGECOMPPROPERTIES_SELECTPA_XPATH);
    }
    return lst;
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'Import Component Properties' using
   * {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)},'Import Component Properties' dialog
   * is displayed.<br>
   * Click on 'Browse...' button present next to Component field,'Select a Component Configuration' dialog is
   * displayed.<br>
   * Select Project Area,Component,Config Type and Config Name.<br>
   * Click on 'Next >' button.<br>
   * Click on 'Finish' button.<br>
   * Click on 'Close' button.<br>
   * Verify the successful message for project importing successfully.
   *
   * @param additionalParams stores key value 'PROJECT_AREA','COMPONENT','CONFIG_TYPE','CONFIG_NAME' etc...
   * @return Message text file is imported or not
   */
  public String importComponentProperties(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Button browseButton =
        this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.BROWSEBUTTON), this.timeInSecs)
        .getFirstElement();
    browseButton.click();
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_CONFIG_XPATH, "Project Area:");
    this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement())
    .sendKeys(additionalParams.get("PROJECT_AREA"), Keys.ENTER);
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_CONFIG_XPATH, "Component:");
    this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement())
    .sendKeys(additionalParams.get("COMPONENT_NAME"), Keys.ENTER);
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_CONFIGTYPE_XPATH);
    this.driverCustom.click(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, additionalParams.get("CONFIG_TYPE"));
    TextField filterField =
        this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder("Type to search (enter * to show all)"));
    filterField.getWebElement().sendKeys(additionalParams.get("CONFIG_NAME"));
    Button okBtn =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    okBtn.getWebElement().click();
    Button nextBtn = this.engine
        .findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), this.timeInSecs).getFirstElement();
    nextBtn.click();
    // here we have a progress bar that is loading
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    Button finishBtn =
        this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.FINISHBUTTON), this.timeInSecs)
        .getFirstElement();
    finishBtn.click();
    Button closeBtn =
        this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.CLOSEBUTTON), this.timeInSecs)
        .getFirstElement();
    closeBtn.click();
    return this.driverCustom.getText(RMConstants.MANAGECOMPPROPERTIES_IMPORTPROPERTIES_MSG_XPATH);
  }
  
  
  /**
   * <p>This method is used to iport component properties.
   * @param additionalParams PROJECT_AREA,COMPONENT_NAME,CONFIG_TYPE,CONFIG_NAME
   */
  public void importExistingComponentProperties(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Button browseButton =
        this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.BROWSEBUTTON), this.timeInSecs)
        .getFirstElement();
    browseButton.click();
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_CONFIG_XPATH, "Project Area:");
    this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement())
    .sendKeys(additionalParams.get("PROJECT_AREA"), Keys.ENTER);
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_CONFIG_XPATH, "Component:");
    this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement())
    .sendKeys(additionalParams.get("COMPONENT_NAME"), Keys.ENTER);
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_CONFIGTYPE_XPATH);
    this.driverCustom.click(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, additionalParams.get("CONFIG_TYPE"));
    TextField filterField =
        this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder("Type to search (enter * to show all)"));
    filterField.getWebElement().sendKeys(additionalParams.get("CONFIG_NAME"));
    waitForSecs(4);
    this.driverCustom.click(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, additionalParams.get("CONFIG_NAME"));
    waitForSecs(1);
    Button okBtn =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    okBtn.getWebElement().click();
    waitForSecs(2);
    Button nextBtn = this.engine
        .findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), this.timeInSecs).getFirstElement();
    nextBtn.click();
   
    
    
    
  }
  /**
   * <p>this method used to get the importing source cpomponent propertys. this method used after using  {#RMManageComponentPropertiesPage.importExistingComponentProperties()} 
   * @author UUM4KOR
   * @param additionalParams ARTIFACT_TYPE importing proprrtis type. 
   * @return imported properties
   */
  public String getImportComponentProperties(final Map<String, String> additionalParams) {
  
    StringBuilder sb = new StringBuilder();
    if (additionalParams.get("ARTIFACT_TYPE").equals("One value allowed")) {      
      List<String> list=this.driverCustom.getWebElementsText("//div[@class='group']/.//div[contains(text(),'"+additionalParams+"')]/..//div[@class='resourceTitle']");
     LOGGER.LOG.info("-----> "+list);     
    } 
    waitForPageLoaded();
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getPresenceOfWebElement("//div[@class='jazz-ui-Dialog-header']//div[contains(text(),'"+additionalParams.get("DIALOG_HEADER")+"')]");
   JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();  
   List<WebElement> linkElements1=this.driverCustom.getWebElements("//div[@class='resourceTitle']");
   
   for (WebElement linkElement1 : linkElements1) {
     
     je.executeScript("arguments[0].scrollIntoView(true);", linkElement1);
   
   }       
        List<WebElement> linkElements2 = this.driverCustom.getWebElements("//div[contains(@class,'groupTitle')]"); 
         for (WebElement linkElement2 : linkElements2) {
         String s1=linkElement2.getText();        
         LOGGER.LOG.info("\n"+s1+" ------>> Imported\n");
         if (additionalParams.get("ARTIFACT_TYPE").equals("Artifact Types")) {
         if(s1.contains("Artifact Types")) {
           List<WebElement> linkElements3 = this.driverCustom.getWebElements("//div[contains(text(),'Artifact Types')]/..//div[@class='resourceTitle']");
           for (WebElement linkElement3 : linkElements3) {
                
             int i = 0;
             while (i < linkElements3.size()) {
               WebElement parentElement = linkElements3.get(i);
               sb.append(parentElement.getText());
               sb.append(",");
               i++;
             }               
             LOGGER.LOG.info(linkElements3.size()+" Imported proprties of '"+additionalParams.get("ARTIFACT_TYPE")+"' is ---->"+sb.toString());
                     
         }  
         } 
         }
         else  if (additionalParams.get("ARTIFACT_TYPE").equals("Artifact Attributes")) {
          if(s1.contains(additionalParams.get("ARTIFACT_TYPE"))) {
           List<WebElement> linkElements4 = this.driverCustom.getWebElements("//div[contains(text(),'Artifact Attributes')]/..//div[@class='resourceTitle']");
           for (WebElement linkElement4 : linkElements4) {
                    
             int i = 0;
             while (i < linkElements4.size()) {
               WebElement parentElement = linkElements4.get(i);
               sb.append(parentElement.getText());
               sb.append(",");

               i++;
             }              
             LOGGER.LOG.info(linkElements4.size()+" Imported proprties of '"+additionalParams.get("ARTIFACT_TYPE")+"' is ---->"+sb.toString());
          
         }  
         }  
         }
         else if (additionalParams.get("ARTIFACT_TYPE").equals("Attribute Data Types")) {
          if(s1.contains(additionalParams.get("ARTIFACT_TYPE"))) {
           List<WebElement> linkElements5 = this.driverCustom.getWebElements("//div[contains(text(),'Attribute Data Types')]/..//div[@class='resourceTitle']");
           for (WebElement linkElement5 : linkElements5) {
                 
             int i = 0;
             while (i < linkElements5.size()) {
               WebElement parentElement = linkElements5.get(i);
               sb.append(parentElement.getText());
               sb.append(",");
               i++;
             }              
             LOGGER.LOG.info(linkElements5.size()+" Imported proprties of '"+additionalParams.get("ARTIFACT_TYPE")+"' is ---->"+sb.toString());
           
         }  
         }  }         
         else if (additionalParams.get("ARTIFACT_TYPE").equals("Link Types")) { 
        if(s1.contains(additionalParams.get("ARTIFACT_TYPE"))) {
           List<WebElement> linkElements6 = this.driverCustom.getWebElements("//div[contains(text(),'Link Types')]/..//div[@class='resourceTitle']");
           for (WebElement linkElement4 : linkElements6) {
                   
             int i = 0;
             while (i < linkElements6.size()) {
               WebElement parentElement = linkElements6.get(i);
               sb.append(parentElement.getText());
               sb.append(",");
               i++;
             }              
             LOGGER.LOG.info(linkElements6.size()+" Imported proprties of '"+additionalParams.get("ARTIFACT_TYPE")+"'  is ---->"+sb.toString());
           
             
         }          
         }      
         }
         else if (additionalParams.get("ARTIFACT_TYPE").equals("Link Constraints")) {  
         if(s1.contains(additionalParams.get("ARTIFACT_TYPE"))) {          
          
           if(this.driverCustom.getPresenceOfWebElement("//div[@class='sectionTitle']").getText().contains("None")) {        
          
             List<WebElement> linkElements7 = this.driverCustom.getWebElements("//div[contains(text(),'Link Constraints')]/..//div[@class='resourceTitle']");
          
           for (WebElement linkElement7 : linkElements7) {
            
             int i = 0;
             while (i < linkElements7.size()) {
               WebElement parentElement = linkElements7.get(i);
               sb.append(parentElement.getText());
               sb.append(",");

               i++;
             }              
             LOGGER.LOG.info(linkElements7.size()+" Imported proprties of '"+additionalParams.get("ARTIFACT_TYPE")+"' is ---->"+sb.toString());
             
           }  
         }else  {
           LOGGER.LOG.info("No properties found");
        }
         }   
         }  
           else {
             LOGGER.LOG.info("not found");
         }
         }
        return sb.toString();          
  }
  /**
   * <p>This method used to check  imported properties is present or not. this method used after {#RMManageComponentPropertiesPage.clickOnTab()}
   * @author UUM4KOR
   * @param additionalParams type of artifact
   * @return true if artifact type is present, otherwise false
   */
  
  public boolean isImportedComponentsPropertiesPresent(final Map<String, String> additionalParams) {
    StringBuilder sb = new StringBuilder();
    if (additionalParams.get("ARTIFACT_TYPE").equals("Artifact Types")) { 
      JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();  
      List<WebElement> actualElements = this.driverCustom.getWebElements("//td[@colid='otl_title_col']//div[text()]");     
      LOGGER.LOG.info(actualElements.size()+" Component Proprties Imported for '"+additionalParams.get("ARTIFACT_TYPE")+"' showing below.\n");
      for (WebElement linkElement1 : actualElements) {
        je.executeScript("arguments[0].scrollIntoView(true);", linkElement1);
       String names=linkElement1.getText();
       
       LOGGER.LOG.info("---"+names+"\n");
      
        int i = 0;
        while (i < actualElements.size()) {
          WebElement parentElement = actualElements.get(i);
          sb.append(parentElement.getText());
          sb.append(",");
          i++;
        }                          
      }   
     String expectedTypes= additionalParams.get("EXPECTED_ARTIFACT_TYPE");      
     String[] s = expectedTypes.split(",");
     for (String ss : s) {      
       String actualdata = sb.toString();      
         if (actualdata.contains(ss.trim())) {
           LOGGER.LOG.info("'" + ss + "'>>------------>>>> Available in "+additionalParams.get("ARTIFACT_TYPE")+" section.\n");
         }        
         else {
           LOGGER.LOG.info("'" + ss +
               "' Import Component Properties not automated successfully.\n - Expected data not available in "+additionalParams.get("ARTIFACT_TYPE")+" or Expected data not available in "+additionalParams.get("ARTIFACT_TYPE")+" section please check Imported data.");
           return false;
         }    
    }
    } 
    else  if (additionalParams.get("ARTIFACT_TYPE").equals("Artifact Attributes")) { 
      JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();  
      List<WebElement> actualElements = this.driverCustom.getWebElements("//td[@colid='adl_title_col']//div[text()]");     
      for (WebElement linkElement1 : actualElements) {
        je.executeScript("arguments[0].scrollIntoView(true);", linkElement1);
     
        int i = 0;
        while (i < actualElements.size()) {
          WebElement parentElement = actualElements.get(i);
          sb.append(parentElement.getText());
          sb.append(",");
          i++;
        }                          
      }  
      LOGGER.LOG.info(actualElements.size()+" Imported proprties of '"+additionalParams.get("ARTIFACT_TYPE")+"' is ---->"+sb.toString());      
     String expectedTypes= additionalParams.get("EXPECTED_ARTIFACT_TYPE");      
     String[] s = expectedTypes.split(",");
     for (String ss : s) {
       String actualdata = sb.toString();
         if (actualdata.contains(ss.trim())) {
           LOGGER.LOG.info("'" + ss + "'>>------------>>>> Available in "+additionalParams.get("ARTIFACT_TYPE")+" section.");
         }
         else {
           LOGGER.LOG.info("'" + ss +
               "'  not automated successfully. - Expected data not available in "+additionalParams.get("ARTIFACT_TYPE")+" or Expected data not available in "+additionalParams.get("ARTIFACT_TYPE")+" section please check Imported data.");
           return false;
         }    
    }    
    }     
    else if (additionalParams.get("ARTIFACT_TYPE").equals("Attribute Data Types")) { 
      JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();  
      List<WebElement> actualElements = this.driverCustom.getWebElements("//td[@colid='atl_title_col']//div[text()]");      
      for (WebElement linkElement1 : actualElements) {
        je.executeScript("arguments[0].scrollIntoView(true);", linkElement1);
    
        int i = 0;
        while (i < actualElements.size()) {
          WebElement parentElement = actualElements.get(i);
          sb.append(parentElement.getText());
          sb.append(",");
          i++;
        }                            
      }  
      LOGGER.LOG.info(actualElements.size()+" Imported proprties of '"+additionalParams.get("ARTIFACT_TYPE")+"' is ---->"+sb.toString());      
     String expectedTypes= additionalParams.get("EXPECTED_ARTIFACT_TYPE");      
     String[] s = expectedTypes.split(",");
     for (String ss : s) {
       String actualdata = sb.toString();     
         if (actualdata.contains(ss.trim())) {
           LOGGER.LOG.info("'" + ss + "'>>------------>>>> Available in "+additionalParams.get("ARTIFACT_TYPE")+" section.");
         }      
         else {
           LOGGER.LOG.info("'" + ss +
               "'  not automated successfully. - Expected data not available in "+additionalParams.get("ARTIFACT_TYPE")+" or Expected data not available in "+additionalParams.get("ARTIFACT_TYPE")+" section please check Imported data.");
           return false;
         }    
    }   
    } 
    else  if (additionalParams.get("ARTIFACT_TYPE").equals("Link Types")) { 
      JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();  
      List<WebElement> actualElements = this.driverCustom.getWebElements("//tbody[@class='table-secondary']//a[text()]");      
      LOGGER.LOG.info(actualElements.size()+" Component Proprties Imported in '"+additionalParams.get("ARTIFACT_TYPE")+"' showing below.\n");
      for (WebElement linkElement1 : actualElements) {
        je.executeScript("arguments[0].scrollIntoView(true);", linkElement1);
       String names=linkElement1.getText();
      
        LOGGER.LOG.info("---"+names+"\n");
     
        int i = 0;
        while (i < actualElements.size()) {
          WebElement parentElement = actualElements.get(i);
          sb.append(parentElement.getText());
          sb.append(",");
          i++;
        }                            
      }       
     String expectedTypes= additionalParams.get("EXPECTED_ARTIFACT_TYPE");      
     String[] s = expectedTypes.split(",");
     for (String ss : s) {      
       String actualdata = sb.toString();      
         if (actualdata.contains(ss.trim())) {
           LOGGER.LOG.info("'" + ss + "'>>------------>>>> Available in "+additionalParams.get("ARTIFACT_TYPE")+" section.\n");
         }        
         else {
           LOGGER.LOG.info("'" + ss +
               "'  not automated successfully. - Expected data not available in "+additionalParams.get("ARTIFACT_TYPE")+" or Expected data not available in "+additionalParams.get("ARTIFACT_TYPE")+" section please check Imported data.");
           return false;
         }    
    }   
    } 
    return true;
  }

  
  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'Artifact Types' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Select the Artifact Type from the list of artifact types.<br>
   * Click on 'Add Attribute...' button,'Add Attribute' dialog is displayed.<br>
   * Select Attribute and click on 'OK' button.<br>
   * Click on 'Save' button to save the selected Artifact Type.
   *
   * @param additionalParams stores key value for 'ARTIFACT_TYPE','ARTIFACT_ATTRIBUTE' etc...
   * @return Added Attributes list.
   * @author LTU7HC
   */
  public List<String> addAttributesToArtifactType(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    clickOnArtifactType(additionalParams.get("ARTIFACT_TYPE"));
    clickOnJazzSpanButtons("Add Attribute...");
    Cell artifactAttributeCell =
        this.engine.findElementWithDuration(Criteria.isCell().withText(additionalParams.get("ARTIFACT_ATTRIBUTE")),
            this.timeInSecs).getFirstElement();
    artifactAttributeCell.scrollToElement();
    artifactAttributeCell.click();
   
    Button okButton =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    okButton.click();
    Button saveButton =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Save"), this.timeInSecs).getFirstElement();
    saveButton.click();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
    return this.driverCustom.getWebElementsText(RMConstants.MANAGECOMPPROPERTIES_AT_ATTRIBUTELIST_XPATH);
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Click on 'Templates' using
   * {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)},'Templates' page is displayed.<br>
   * Select 'Artifact Templates' from left side of the Templates page.<br>
   * Click on 'New Template...' button present right side of the 'Templates' page,'Create Template' dialog is
   * displayed.<br>
   * Provide Template Name,Description.<br>
   * Click on 'Browse...' button,'Select Artifact' dialog is displayed.<br>
   * Search and select the artifact,Click on 'OK' button.<br>
   * Click on 'Create' button inside 'Create Template' dialog.<br>
   * Click on the created Template Name and get the Template Name.
   *
   * @param templateName is name of artifact template
   * @return the created template artifact type
   */
  public String createNewArtifactTemplate(final String templateName) {

    waitForPageLoaded();
    this.driverCustom.click(RMConstants.DNG_TEMPLATESPAGE_ARTITEMP_XPATH);
    Button btnNewTemplate = this.engine
        .findElementWithDuration(Criteria.isButton().withText("New Template..."), this.timeInSecs).getFirstElement();
    btnNewTemplate.click();
    String date = DateUtil.getCurrentDateAndTime();
    Dialog createTemplateDialog = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Create Template"), this.timeInSecs).getFirstElement();
    Text templateNameTextField = this.engine
        .findFirstElement(Criteria.isTextField().hasLabel("Template name:").inContainer(createTemplateDialog));
    templateNameTextField.setText(templateName);
    TextField templateDescriptionTextField = this.engine.findFirstElement(
        Criteria.isTextField().hasLabel("Description").isMultiLine().inContainer(createTemplateDialog));
    templateDescriptionTextField.setText("Template_Detailed_Description_" + date);
    Button browseButton =
        this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.BROWSEBUTTON), this.timeInSecs)
        .getFirstElement();
    browseButton.click();

    TextField artifactFilterTextField =
        this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder("Type to filter by text or by ID"));
    artifactFilterTextField.setText("*");

    List<WebElement> artifactList = this.driverCustom.getWebElements(RMConstants.DNG_TEMPLATEPAGE_SELECTART_LIST_XPATH);
    if ((artifactList != null) && !(artifactList.isEmpty())) {
      artifactList.get(0).click();
      Button okButton =
          this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
      okButton.click();
      Button createButton = this.engine.findElementWithDuration(Criteria.isButton().withText("Create"), this.timeInSecs)
          .getFirstElement();
      createButton.click();
    }
    this.driverCustom.click(RMConstants.DNG_CREATEDTEMPLATE_XPATH, templateName);
    return this.driverCustom.getText(RMConstants.DNG_TEMPLATE_ARTIFACTTYPE_XPATH, templateName);
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Click on 'New Definition...' button present in 'ReqIF Definitions' section.<br>
   * New Definition section is displayed in right side of the page.<br>
   * Set the ReqIF Definition Name.
   *
   * @param reqIFDefinitionName ReqIF file name.
   * @return ReqIF file name
   */
  public String setReqIFDefinitionName(final String reqIFDefinitionName) {
    this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_NAMEFIELD_XPATH, Duration.ofSeconds(5));
    List<WebElement> nameList =
        this.driverCustom.getWebDriver().findElements(By.xpath("//label[contains(., 'Name:')]"));
    for (WebElement e1 : nameList) {
      if (e1.isDisplayed()) {
        List<WebElement> textList = e1.findElements(By.xpath("//input[@name='title' and @type='text']"));
        for (WebElement e2 : textList) {
          if (e2.isDisplayed()) {
            e2.sendKeys(reqIFDefinitionName);
          }
        }
      }
    }

    return reqIFDefinitionName;
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Click on 'New Definition...' button present in 'ReqIF Definitions' section.<br>
   * New Definition section is displayed in right side of the page.<br>
   * Include 'Links','Folders,'Tags in the ReqIF by selecting the required check box.
   *
   * @param checkboxName include check box Links, Folders, Tags.
   * @return checkboxName
   */
  public String includeTypeForReqIF(final String checkboxName) {
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_CHECKBOX_XPATH, checkboxName);
    if (!this.driverCustom.getWebElement(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_CHECKBOX_XPATH, checkboxName)
        .getAttribute("aria-checked").equalsIgnoreCase("true")) {

      this.driverCustom.getWebElement(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_CHECKBOX_XPATH, checkboxName).click();
    }
    return checkboxName;
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select component properties type using
   * {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Provide Description for the selected properties type.
   *
   * @param description input description for the selected properties type.
   * @return description
   */
  public String setComponentPropertiesDescription(final String description) {
    this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_DESFIELD_XPATH, Duration.ofSeconds(5));
    TextField desc = this.engine.findFirstElement(Criteria.isTextField().isMultiLine().hasLabel("Description"));
    desc.setText(description);
    return description;
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Click on 'New Definition...' button present in 'ReqIF Definitions' section.<br>
   * New Definition section is displayed in right side of the page.<br>
   * Click on Button Type 'Add Artifact...','Add Module...' etc...,'Select Artifacts for Export' or 'Select Modules for
   * Export' dialog is displayed.<br>
   * Search the required Artifact/Module.
   *
   * @param dialog name of the dialog.
   * @param artifactID Id of the artifact.
   * @return id of the artifact
   */
  public String searchArtifact(final String dialog, final String artifactID) {
    Dialog dlg =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(dialog), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info("Dialog 'Select Modules/Artifact for Export' displayed.");
    Text txt = this.engine.findElementWithDuration(
        Criteria.isTextField().withPlaceHolder("Type to filter artifacts by text or by ID").inContainer(dlg),
        this.timeInSecs).getFirstElement();
    txt.setText(artifactID + Keys.ENTER);
    LOGGER.LOG.info("Searched " + artifactID + " in 'Select Modules/Artifact for Export' dialog.");
    waitForSecs(80);
    return artifactID;
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Hover on the required ReqIF Definition icon will display next to Req IF name<br>
   * Click on displayed icon, list of menus displayed.<br>
   * Click on menu 'Delete...','Create Copy...','Export...' etc...menu from the list of menus.<br>
   *
   * @param reqIFName name of ReqIF.
   * @param dropdownOption drop down value displayed after selecting ReqIF.
   */
  public void clickOnReqIFDropdownOption(final String reqIFName, final String dropdownOption) {
    Actions action = new Actions(this.driverCustom.getWebDriver());
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTARTIFACTFORMATVALUE_XPATH, Duration.ofSeconds(10), reqIFName);
    action
    .moveToElement(
        this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_SELECTARTIFACTFORMATVALUE_XPATH, reqIFName))
    .contextClick().build().perform();
    Cell cellDropdownOption = this.engine
        .findElementWithDuration(Criteria.isCell().withText(dropdownOption), this.timeInSecs).getFirstElement();
    cellDropdownOption.click();
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Click on 'New Definition...' button present in 'ReqIF Definitions' section.<br>
   * New Definition section is displayed in right side of the page.<br>
   * Click on Button Type 'Add Artifact...','Add Module...' etc...,'Select Artifacts for Export' or 'Select Modules for
   * Export' dialog is displayed.<br>
   * Search the required Artifact/Module using
   * {@link RMManageComponentPropertiesPage#searchArtifact(String, String)}.<br>
   * Select the searched Artifact/Module.
   *
   * @param dialog name of the dialog.
   * @param artifactID Id of the artifact.
   * @return id of the artifact.
   * @author LTU7HC
   */
  public String selectArtifact(final String dialog, final String artifactID) {
    Dialog dlg =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(dialog), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info("Dialog 'Select Modules/Artifact for Export' displayed.");
    waitForSecs(2);
    Link lnk = this.engine
        .findElementWithDuration(Criteria.isLink().withText(artifactID + ":").inContainer(dlg), this.timeInSecs)
        .getFirstElement();
    lnk.click();
    LOGGER.LOG.info("Selected " + artifactID + " in 'Select Modules/Artifact for Export' dialog.");
    // Handle for the artifact with long header name - pop up will showup and hide the button
    if(this.driverCustom.isElementVisible("//div[contains(@id,'RichHoverPresentation')]", Duration.ofSeconds(10))) {
      this.driverCustom.click("//div[contains(@id,'RichHoverPresentation')]//a[@class='closeButton']");
    }
    return artifactID;
  }

  /**
   * <P>
   * Click on Button which present inside a dialog.
   *
   * @param dialog name of the dialog.
   * @param button name of the button.
   * @return true if dialog button is clicked.
   */
  @Override
  public Boolean clickOnDialogButton(final String dialog, final String button) {
    try {
      Dialog dlgCreateRootCategory =
          this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle(dialog), this.timeInSecs);
      Button btnOK =
          this.engine.findFirstElementWithDuration(Criteria.isButton().withText(button).inContainer(dlgCreateRootCategory),
              this.timeInSecs);
      if (btnOK.isElementEnable(Duration.ofSeconds((this.timeInSecs.getSeconds() *10)))) {
        btnOK.click();
        waitForSecs(2);
      }
      LOGGER.LOG.info("Button \"" + button + "\" in '" + dialog + "' dialog is clicked sucessfully.");
      return true;
    }
    catch (Exception e) {
      LOGGER.LOG
      .info("Button \"" + button + "\" in '" + dialog + "' dialog is not found by engine.\n" + e.getMessage());
      return false;
    }
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Click on 'New Definition...' button present in 'ReqIF Definitions' section.<br>
   * New Definition section is displayed in right side of the page.<br>
   * Click on Button Type 'Add Module...','Select Modules For Export' dialog is displayed.<br>
   * Search the view using {@link RMManageComponentPropertiesPage#searchArtifact(String, String)}.<br>
   * Select the searched module view.
   *
   * @param nameView name of the view.
   * @return name of the view.
   */
  public String selectModuleViewForReqIF(final String nameView) {
    waitForPageLoaded();

    this.driverCustom.getFirstVisibleWebElement(RMConstants.RMARTIFACTSPAGE_SELECTVIEW_XPATH, "Select View").click();
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, Duration.ofSeconds(30), nameView);
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, nameView);
    this.driverCustom.getAttribute("//div[@class='tags-container']//span[text()='" + nameView + "']", "aria-selected");
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, nameView);
    waitForSecs(Duration.ofSeconds(5));
    String attributeValue = this.driverCustom
        .getAttribute("//div[@class='tags-container']//span[text()='" + nameView + "']", "aria-selected");
    LOGGER.LOG.info("'" + nameView + "' aria-selected : " + attributeValue);
    String falseValue = "false";
    if (attributeValue.equalsIgnoreCase(falseValue)) {
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, nameView);
      LOGGER.LOG.info("Select '" + nameView + "' view");
    }
    else {
      LOGGER.LOG.info("'" + nameView + "' already selected");
    }
    return nameView;
  }


  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Hover on the required ReqIF Definition icon will display next to Req IF name<br>
   * Click on displayed icon, list of menus displayed.<br>
   * Click on menu 'Export...' using
   * {@link RMManageComponentPropertiesPage#clickOnReqIFDropdownOption(String, String)},'Creating ReqIF File' dialog is
   * displayed.<br>
   * Click on button 'Show Report','Download','Close'.
   *
   * @param buttonName name of the button 'Show Report','Download','Close'.
   */
  public void clickOnButtonFromCreatingReqIFFileWindow(final String buttonName) {
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(20), RMConstants.DOWNLOAD);
    while (!this.driverCustom.getWebDriver().findElement(By.xpath("//div[@class='dijitProgressBarLabel']")).getText()
        .equals("100%")) {
      if (this.driverCustom.getWebDriver().findElement(By.xpath("//div[@class='dijitProgressBarLabel']")).getText()
          .equals("100%")) {
        break;
      }
    }
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(5), "button");
    Dialog dlgCreateReqIFFile =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Creating ReqIF File"), this.timeInSecs)
        .getFirstElement();
    Button download =
        this.engine.findElementWithDuration(Criteria.isButton().withText(buttonName).inContainer(dlgCreateReqIFFile),
            this.timeInSecs).getFirstElement();
    download.click();
  }


  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Hover on the required ReqIF Definition icon will display next to Req IF name<br>
   * Click on displayed icon, list of menus displayed.<br>
   * Click on menu 'Export...' using
   * {@link RMManageComponentPropertiesPage#clickOnReqIFDropdownOption(String, String)},'Creating ReqIF File' dialog is
   * displayed.<br>
   * Click on button 'Download' using
   * {@link RMManageComponentPropertiesPage#clickOnReqIFDropdownOption(String, String)}.<br>
   * Get the downloaded ReqIF file name and path.
   *
   * @param exportFileName name of the created ReqiF file.
   * @return exported file location.
   */
  public String getDownloadedReqIFFileNameAndPath(final String exportFileName) {
    waitForSecs(15);
    String path = this.driverCustom.getDownloadFolderLocation();
    LOGGER.LOG.info("Download Folder Location  " + path);
    LOGGER.LOG.info("Download file name as: " + exportFileName + ".reqifz");
    String directoriesPath = this.driverCustom.getDownloadFolderLocation();
    String reqIF = directoriesPath + "\\" + exportFileName + ".reqifz";
    LOGGER.LOG.info("File Download path and name as : export");
    return reqIF;
  }

  /**
   * <p>
   * This method is to validate the Progress Bar while exporting ReqIF and Clicking on button 'Show Report','Download'
   * etc..
   *
   * @param button name of the button.
   */
  @Override
  public void clickOnButtons(final String button) {
    Button btnSave;
    try {
      btnSave = this.engine.findElementWithinDuration(Criteria.isButton().withText(button), Duration.ofSeconds(40)).getFirstElement();
      btnSave.click();
      LOGGER.LOG.info(button + "- Clicked successfully.");
    }
    catch (Exception e) {
      // TODO: click on create a query link
      refresh();
      WebElement createQueryLink= driverCustom.getFirstVisibleWebElement(RMConstants.CCMMODULEPAGE_CLICKON_CREATEAQUERYLINK_XPATH, "Create a query");
      createQueryLink.click();
      LOGGER.LOG.info(button + "- Clicked successfully.");
    }

  }

  /**
   * <p>
   * Expot the ReqIF file.<br>
   * Get the Summary of the ReqIF file.
   *
   * @param summary of the created ReqIF.
   * @return summary of the created ReqIF.
   */
  public String getSummaryOfExportedReqIF(final String summary) {
    waitForSecs(15);
    WebElement mytable = this.driverCustom.getPresenceOfWebElement(By.xpath("//table//tbody"));
    List<WebElement> tablerows = mytable.findElements(By.xpath("//tr"));
    List<String> columValues = new ArrayList<>();
    for (int i = 0; i < tablerows.size(); i++) {
      List<WebElement> columnsRows = tablerows.get(i).findElements(By.className("labelValue"));
      columnsRows.stream().map(WebElement::getText).forEach(columValues::add);
      columValues = columValues.stream().filter(x -> x.startsWith(summary)).collect(Collectors.toList());
    }
    List<String> lst = columValues;
    lst.stream().forEach(x -> {
      String atx = x.trim();
      if (atx.equals("[]")) {
        lst.remove(atx);
      }
    });
    StringBuilder sb = new StringBuilder();
    lst.stream().forEach(x -> sb.append(x));
    LOGGER.LOG.info("Report Summary of Exported ReqIF File: " + sb.toString());
    return sb.toString();
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Click on 'New Definition...' button present in 'ReqIF Definitions' section.<br>
   * New Definition section is displayed in right side of the page.<br>
   * Click on Button Type 'Add Artifact...','Add Module...' etc...,'Select Artifacts for Export' or 'Select Modules for
   * Export' dialog is displayed.<br>
   * Verify dialog 'Select Artifacts for Export' or 'Select Modules for Export' dialog is displayed.
   *
   * @param dialogName Name of the dialog (Title of the widget).
   * @return title of the dialog.
   */
  public String isWidgetOpened(final String dialogName) {
    waitForSecs(3);
    if (this.driverCustom.isElementInvisible("//div[text()='" + dialogName + "'] | //div[@class='header-text true']",
        Duration.ofSeconds(50))) {
      LOGGER.LOG.info(dialogName + "- widget is not popped up successfully.");
      throw new WebAutomationException(dialogName + "  Widget is not popped up successfully.");
    }
    LOGGER.LOG.info(dialogName + "- Widget pop up successfully.");
    return dialogName;
  }

  /**
   * <p>
   * Verify ReqIF Definition is created successfully.
   *
   * @param reqifDefinitionName name of the ReqIF Definition Name.
   * @return true if reqIF definition saved.
   */
  public boolean isReqIFDefinitionSaved(final String reqifDefinitionName) {
    if (!this.driverCustom.isElementVisible("//div[contains(text(),'" + reqifDefinitionName + "')]", Duration.ofSeconds(60))) {
      LOGGER.LOG.info(reqifDefinitionName + "Saved successfully.");
      return false;
    }
    return true;
  }

  /**
   * <p>
   * Verify Progress bar reachd 100%.
   *
   * @return true if progress bar reached 100%.
   */
  public boolean isProgressBarReached100Percent() {
    waitForSecs(500);
    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.MANAGECOMPPROPERTIES_DONE_PROGRESSBAR_XPATH);
    }
    catch (Exception e) {
      this.driverCustom.getPresenceOfWebElement(RMConstants.MANAGECOMPPROPERTIES_DONE_PROGRESSBAR_XPATH);
    }
    if (!this.driverCustom.getWebElement(RMConstants.MANAGECOMPPROPERTIES_PROGRESSBAR_XPATH)
        .getAttribute("aria-valuenow").equals("100")) {
      LOGGER.LOG.info(" 'Create ReqIF file' Progress Bar not reached 100%");
      throw new WebAutomationException("'Create ReqIF file' Progress Bar not reached 100%");
    }
    return true;
  }

  /**
   * <p>
   * Clicks on tab which is present in same window with multiple pages. <br>
   * E.g. CCM work item page Overview, Links, Attachment etc tabs.
   *
   * @param tab is common for all image buttons
   */
  @Override
  public void clickOnTab(final String tab) {
    
    boolean isTabInvisible = this.driverCustom.isElementInvisible("//span[text()='" + tab + "']", Duration.ofSeconds(10));

    // If the "tab name" is not displayed, we can click on next to find tab.
    if(isTabInvisible) {
      // Click on Next button
      this.driverCustom.getPresenceOfWebElement("//div[@aria-disabled='false']/span[contains(@class,'SlideRightIcon')]").click();
      LOGGER.LOG.info("'" + tab + " Tab' is not availble and Click on the Next Scroll option");
    }

    Tab tabArtifactCategories = this.engine.findFirstElementWithDuration(Criteria.isTab().withText(tab), this.timeInSecs);
    tabArtifactCategories.click();
    LOGGER.LOG.info(tab + " tab is clicked successfully.");
  }
  
  /**
   * In Component/Project properties page, verify if list of tabs are displayed with match number and order <br>
   * @author VDY1HC
   * @param listTabsSortInOrder - list of tabs already sort in order separated by ";" 
   * @return true if tabs displayed match number and order
   */
  public boolean verifyPropertiesTabsOrder (String listTabsSortInOrder) {
    WebElement drdTabs = this.driverCustom.getPresenceOfWebElement("//span[contains(@class,'dijitTabStripMenuIcon')]");
    drdTabs.click();
    List<WebElement> listOfTabs = this.driverCustom.getVisibleWebElements("//table[contains(@class,'dijitMenuActive')]//tr/.");
    String[] listOfTabsInput = listTabsSortInOrder.split(";");
    if (listOfTabs.size() != listOfTabsInput.length) return false;
    for (int i = 0; i < listOfTabs.size(); i++) {
      if (!listOfTabs.get(i).getText().equalsIgnoreCase(listOfTabsInput[i])) {
        return false;
      }
    }
    return true;
  }

  /**
   * <p>
   * Clicks on tab which is present in same window with multiple pages. <br>
   * E.g. CCM work item page Overview, Links, Attachment etc tabs.
   *
   * @param tab is common for all image buttons
   */

  public void clickOnManageProjectPropertiesTab(final String tab) {
    WebElement tabList = this.driverCustom.getWebElement("//div[@class='nowrapTabStrip dijitTabContainerTop-tabs']");

    List<WebElement> listOptions = tabList.findElements(By.tagName("//div/span"));
    for (WebElement opt1 : listOptions) {
      if (opt1.getText().contains(tab)) {
        opt1.click();
        break;
      }
    }
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Artifacts");
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Hover on the required ReqIF Definition icon will display next to Req IF name<br>
   * Click on displayed icon, list of menus displayed.<br>
   * Click on menu 'Delete...','Create Copy...','Export...' etc...menu from the list of menus.<br>
   *
   * @param reqIFName name of ReqIF.
   * @return name of ReqIF.
   */
  public String clickOnReqIFDropdownOptionExport(final String reqIFName) {
    Cell cellArtifact = null;
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, reqIFName);
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_REQIF_DROPDOWN_XPATH, reqIFName);
    cellArtifact =
        this.engine.findElementWithDuration(Criteria.isCell().withText("Export..."), this.timeInSecs).getFirstElement();
    cellArtifact.click();
    LOGGER.LOG.info("Export..." + "Option is clicked successfully.");
    return reqIFName;
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Select 'ReqIF' using {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.<br>
   * Hover on the required ReqIF Definition icon will display next to Req IF name<br>
   * Click on displayed icon, list of menus displayed.<br>
   * Click on menu 'Delete...','Create Copy...','Export...' etc...menu from the list of menus.<br>
   *
   * @param reqIFName name of ReqIF.
   * @return name of ReqIF
   */
  public String clickOnReqIFDropdownOptionDelete(final String reqIFName) {
    Cell cellArtifact = null;
    //Click on ReqIF Definitions name
    this.driverCustom.isElementClickable(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, Duration.ofSeconds(30), reqIFName);
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, reqIFName);
    //Click on ReqIF Definitions dropdown
    this.driverCustom.isElementClickable(RMConstants.MANAGECOMPPROPERTIES_REQIF_DROPDOWN_XPATH, Duration.ofSeconds(30), reqIFName);
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_REQIF_DROPDOWN_XPATH, reqIFName);
    cellArtifact =
        this.engine.findElementWithDuration(Criteria.isCell().withText(RMConstants.DELETEBUTTON), this.timeInSecs)
        .getFirstElement();
    cellArtifact.click();
    LOGGER.LOG.info(RMConstants.DELETEBUTTON + "Option is clicked successfully.");
    return reqIFName;
  }

  /**
   * @param artifactType type of artifact
   */
  public void deleteArtifactType(final String artifactType) {
    waitForSecs(Duration.ofSeconds(5));
    //refresh();
    try {
      this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, artifactType);
      waitForSecs(Duration.ofSeconds(5));
      
      try {
        this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_REQIF_DROPDOWN_XPATH, artifactType);
      }
      catch (Exception f) {
        this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_ARTIFACTTYPE_DROPDOWN_XPATH, artifactType);
      }
    
    }
    catch (Exception e) {
      waitForSecs(2);
      this.driverCustom.getPresenceOfWebElement(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, artifactType);
      waitForSecs(Duration.ofSeconds(5));
      WebElement element = this.driverCustom.getWebElement(
          "(//div[contains(@id,'com_ibm_rdm_web_viewer_ObjectTypesListWidget_0') and @dojoattachpoint='_rootNode'])");
      ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("window.scrollBy(0,1)", element);
      LOGGER.LOG.info("Scroll down to element visible : " + artifactType);
      waitForSecs(Duration.ofSeconds(5));
      this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, artifactType);
      try {
        this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_REQIF_DROPDOWN_XPATH, artifactType);
      }
      catch (Exception f) {
        this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_ARTIFACTTYPE_DROPDOWN_XPATH, artifactType);
      }
    }
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.click(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_DELETE_ARTIFACT_TYPE_XPATH);
    LOGGER.LOG.info("Delete... Option is clicked successfully.");
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.click(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_YES_BUTTON_XPATH);
    waitForSecs(Duration.ofSeconds(5));
    LOGGER.LOG.info("Click on 'Yes' button in Confirm dialog.");
  }

  /**
   * @param artifactType type of artifact
   * @return true if artifact type is present, otherwise false
   */
  public boolean isArtifactTypePresent(final String artifactType) {
    WebElement artifacTypeElement =
        this.driverCustom.getPresenceOfWebElement(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, artifactType);
    LOGGER.LOG.info("Attribute type '" + artifactType + "' present");
    return artifacTypeElement.isDisplayed();
  }

  /**
   *
   */
  public void clickOnImportComponentPropertiesButton() {
    waitForSecs(1);
    boolean b = this.driverCustom.isElementInvisible("//div[@aria-disabled='false']//img", Duration.ofSeconds(3));

    // clickOnButtons
    if (!b) {
      waitForSecs(2);
      this.driverCustom.getPresenceOfWebElement("//div[@aria-disabled='false']//img").click();

    }
    Button btnSave = this.engine
        .findElementWithinDuration(Criteria.isButton().withText("Import Component Properties"), Duration.ofSeconds(30)).getFirstElement();
    btnSave.click();
    waitForSecs(1);
  }

  public void clickOnAddArtifactTypesFromComponentSetupDialogue(final Map<String, String> attributeArtifactTypes) {
    waitForSecs(1);
    
   // String artifactContextMenu = additionalParams.get("");
    String applyAcomponentTemplate = attributeArtifactTypes.get("Apply a component template");
    String importComponentPropertiesFromAnExistingComponent = attributeArtifactTypes.get("IMPORT_COMPONENT_PROPERTIES_FROM_AN_EXISTING_COMPONENT");
    String manuallyDefineArtifactTypes = attributeArtifactTypes.get("Manually define artifact types");
    

    List<String> artifactlist = this.driverCustom.getWebElementsText("//div[@class='linkSection']//a");
    String s = artifactlist.toString();
    
    String[] s7 = s.split(",");
    LOGGER.LOG.info("<<<<--- Component setup dialog opens with following options --->>>> ");
    for (String ss : s7) {
      LOGGER.LOG.info("" + ss + "");
      LOGGER.LOG.info("\n");
    }
    waitForSecs(2);
    
    this.driverCustom.getPresenceOfWebElement(RMConstants.MANAGECOMPPROPERTIES_IMPORT_COMPONENT_PROPERTIES_FROM_AN_EXISTING_COMPONENT_XPATH,importComponentPropertiesFromAnExistingComponent).click();
  LOGGER.LOG.info("Clocked on : "+importComponentPropertiesFromAnExistingComponent);
    waitForSecs(1);
  }
  /**
   * <p>
   * This method used arter {@link RMDashBoardPage#openSettingsSubMenu(String)} to import component proprties and
   * checking warning message as 'The properties were imported successfully' and if no change also checking warning
   * message as 'There are no changes to import'.
   *
   * @author UUM4KOR
   * @param otherComponentName source component name
   * @param streamName stream name
   * @return message
   */
  public String importComponentProperties(final String otherComponentName, final String streamName) {
    waitForSecs(Duration.ofSeconds(5));
    String message = null;
    this.driverCustom.getPresenceOfWebElement("//a[@title='Import Component Properties' and@class='selected']");
    // clock Browse...
    Dialog importProjectProperties = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Import Component Properties"), this.timeInSecs)
        .getFirstElement();
    Button download = this.engine.findElementWithDuration(
        Criteria.isButton().withText(RMConstants.BROWSEBUTTON).inContainer(importProjectProperties), this.timeInSecs)
        .getFirstElement();
    download.click();
    LOGGER.LOG.info("clocked on Browse... button ");
    waitForSecs(Duration.ofSeconds(10));

    // Select a Component Configuration dialog
    Dialog dlgSelectAcomponentConfig = this.engine
        .findElementWithinDuration(Criteria.isDialog().withTitle("Select a Component Configuration"), this.timeInSecs)
        .getFirstElement();

    // Select Component
    WebElement drdComponent = this.driverCustom
        .getPresenceOfWebElement("//tr[@dojoattachpoint='_componentRow']//td[@class='project-select']");
    drdComponent.click();
    WebElement optComponent = this.driverCustom.getWebElement("//tr[@title='" + otherComponentName + "']");
    optComponent.click();
    waitForSecs(Duration.ofSeconds(5));

    // Search Stream
    Text txtSearchStream = this.engine.findElementWithinDuration(Criteria.isTextField()
        .withPlaceHolder("Type to search (enter * to show all)").inContainer(dlgSelectAcomponentConfig), Duration.ofSeconds(20))
        .getFirstElement();
    txtSearchStream.setText(streamName);
    waitForSecs(Duration.ofSeconds(10));
    LOGGER.LOG.info("Search stream with name: " + streamName);

    // Select the Stream
    Row rowStream = this.engine
        .findElementWithDuration(Criteria.isRow().withText(streamName).inContainer(dlgSelectAcomponentConfig), Duration.ofSeconds(20))
        .getFirstElement();
    rowStream.click();
    LOGGER.LOG.info("Found and select stream name: " + streamName);

    // Click on OK button
    Button btnOK = this.engine.findElementWithinDuration(Criteria.isButton().withText("OK"), Duration.ofSeconds(20)).getFirstElement();
    btnOK.click();
    waitForSecs(8);
    LOGGER.LOG.info("Clicked on [OK] button");

    // Click on Next button
    Button btnNext =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >").inContainer(importProjectProperties),
            this.timeInSecs).getFirstElement();
    btnNext.click();
    waitForSecs(Duration.ofSeconds(20));
    this.driverCustom.getPresenceOfWebElement("//a[@title='Review Property Changes' and@class='selected']");
    LOGGER.LOG.info("Verified presence of 'Review Property Changes' in section label");
    waitForSecs(Duration.ofSeconds(20));
    LOGGER.LOG.info(" clicked on next >");

    if (!this.driverCustom.getPresenceOfWebElement("//button[text()='Finish']").isEnabled()) {
      message = this.driverCustom.getWebElement("//div[@class='messageSummary']").getText().toString();
      LOGGER.LOG.info("Verified validation message displayed as : " + message);
      waitForSecs(Duration.ofSeconds(5));

      // Review Property Changes
      Button btnClose =
          this.engine.findElementWithinDuration(Criteria.isButton().withText("Cancel"), Duration.ofSeconds(20)).getFirstElement();
      btnClose.click();
      waitForSecs(Duration.ofSeconds(10));
      LOGGER.LOG.info("Select Cancel button");
    }

    else if (this.driverCustom.getPresenceOfWebElement("//button[text()='Finish']").isEnabled()) {
      List<String> artifactlist = this.driverCustom.getWebElementsText("//div[@class='group']");
      String s = artifactlist.toString();
      String[] s7 = s.split(",");
      LOGGER.LOG.info(">>--------Review Property Changes form Import Component Properties dialoge --->>>> " + s7.toString());
      for (String ss : s7) {
       
        LOGGER.LOG.info(">>-----> " + ss + " >>--------->>>> importated.");
        LOGGER.LOG.info("\n");
      }
      LOGGER.LOG.info("Print all properties ---> " + artifactlist);
      waitForSecs(2);

      // Click on Finish button
      Button btnFinish =
          this.engine.findElementWithinDuration(Criteria.isButton().withText("Finish"), Duration.ofSeconds(10)).getFirstElement();
      btnFinish.click();
      waitForSecs(Duration.ofSeconds(20));
      LOGGER.LOG.info("Select [Finish] button");
      if (this.driverCustom.getPresenceOfWebElement(
          "//div[@class='messageSummary' and text()='The properties were imported successfully.']").isDisplayed()) {
        message = "The properties were imported successfully.";
        LOGGER.LOG.info("Verified validation message displayed as : " + message);
      }
      else {
        message = this.driverCustom.getWebElement("//div[@class='messageSummary']").getText().toString();
        LOGGER.LOG.info("Verified validation message displayed not valid as : " + message);
      }
      // Wait for action completed and click on Close button
      Button btnClose =
          this.engine.findElementWithinDuration(Criteria.isButton().withText("Close"), Duration.ofSeconds(20)).getFirstElement();
      btnClose.click();
      LOGGER.LOG.info("Select [Close] button");
    }
    else {
     
      LOGGER.LOG.info(" not imported succesfuly ");
    }
    return message;
  }

  /**
   *
   */
  public void clickOnImportprojectPropertiesButton() {
    waitForSecs(1);
    boolean b = this.driverCustom.isElementInvisible("//div[@aria-disabled='false']//img", Duration.ofSeconds(3));

    // clickOnButtons
    if (!b) {
      waitForSecs(2);
      this.driverCustom.getPresenceOfWebElement("//div[@aria-disabled='false']//img").click();
    }
    waitForSecs(1);

    // clock Import Project Properties
    Button btnSave = this.engine
        .findElementWithinDuration(Criteria.isButton().withText("Import Project Properties"), Duration.ofSeconds(30)).getFirstElement();
    btnSave.click();
    waitForSecs(1);
  }

  /**
   * @param sourceProject project source name
   * @return message
   */
  public String importprojectProperties(final String sourceProject) {
    String message = null;

    // clock Browse...
    Dialog importProjectProperties =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Import Project Properties"), this.timeInSecs)
        .getFirstElement();
    Button download = this.engine.findElementWithDuration(
        Criteria.isButton().withText(RMConstants.BROWSEBUTTON).inContainer(importProjectProperties), this.timeInSecs)
        .getFirstElement();
    download.click();
    LOGGER.LOG.info("clocked on Browse... button ");
    waitForSecs(2);
    Dialog selectAproject = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a project"), this.timeInSecs).getFirstElement();
    waitForSecs(2);

    // select project -->AE Workplace ALM (RM)
    Link lnk =
        this.engine.findElementWithDuration(Criteria.isLink().withText(sourceProject).inContainer(selectAproject), Duration.ofSeconds(120))
        .getFirstElement();
    lnk.click();
    LOGGER.LOG.info(sourceProject + " -->    selected");
    waitForSecs(1);

    // clock on ok
    Button download1 = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(selectAproject), this.timeInSecs)
        .getFirstElement();
    download1.click();
    LOGGER.LOG.info("clocked on ok button");
    waitForSecs(1);
    Button btnNext =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >").inContainer(importProjectProperties),
            this.timeInSecs).getFirstElement();
    btnNext.click();
    LOGGER.LOG.info(" clicked on next >");
    waitForSecs(20);
    this.driverCustom.getPresenceOfWebElement("//a[@title='Review Property Changes' and@class='selected']");
    LOGGER.LOG.info("Verified presence of 'Review Property Changes' in section label");
    waitForSecs(20);
    if (!this.driverCustom.getPresenceOfWebElement("//button[text()='Finish']").isEnabled()) {
      message = this.driverCustom.getWebElement("//div[@class='messageSummary']").getText().toString();
      LOGGER.LOG.info("Verified validation message displayed as : " + message);
      waitForSecs(Duration.ofSeconds(5));

      // Review Property Changes
      Button btnClose =
          this.engine.findElementWithinDuration(Criteria.isButton().withText("Cancel"), Duration.ofSeconds(20)).getFirstElement();
      btnClose.click();
      waitForSecs(Duration.ofSeconds(10));
      LOGGER.LOG.info("Select Cancel button");
    }
    else if (this.driverCustom.getPresenceOfWebElement("//button[text()='Finish']").isEnabled()) {
      List<String> artifactlist = this.driverCustom.getWebElementsText("//div[@class='group']");
      String s = artifactlist.toString();
      String[] s7 = s.split(",");
      LOGGER.LOG.info(">>-------->>>> " + s7.toString());
      for (String ss : s7) {
        LOGGER.LOG.info(">>------> " + ss + " >>---------->>>> importated.");
        LOGGER.LOG.info("\n");
      }
      LOGGER.LOG.info("Print all properties ---> " + artifactlist);
      waitForSecs(2);
      Button btnFinish = this.engine
          .findElementWithDuration(Criteria.isButton().withText("Finish").inContainer(importProjectProperties),
              this.timeInSecs)
          .getFirstElement();
      btnFinish.click();
      LOGGER.LOG.info("'Finish' button clicked");
      waitForSecs(2);
      if (this.driverCustom.getPresenceOfWebElement(
          "//div[@class='messageSummary' and text()='The properties were imported successfully.']").isDisplayed()) {
        message = this.driverCustom.getWebElement("//div[@class='messageSummary']").getText();
        LOGGER.LOG.info("Verified validation message displayed as : " + message);
      }
      else {
        message = this.driverCustom.getWebElement("//div[@class='messageSummary']").getText();
        LOGGER.LOG.info("Verified validation message displayed not valid as : " + message);
      }

      waitForSecs(Duration.ofSeconds(5));
      // Wait for action completed and click on Close button
      Button btnClose =
          this.engine.findElementWithinDuration(Criteria.isButton().withText("Close"), Duration.ofSeconds(20)).getFirstElement();
      btnClose.click();
      LOGGER.LOG.info("Select [Close] button");
    }
    else {
      System.out.println("Not imported succesfuly ");
    }
    return message;
  }

  public void importExistingProjectProperties(final Map<String, String> additionalParams) {
  
 

    // clock Browse...
    Dialog importProjectProperties =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Import Project Properties"), this.timeInSecs)
        .getFirstElement();
    Button download = this.engine.findElementWithDuration(
        Criteria.isButton().withText(RMConstants.BROWSEBUTTON).inContainer(importProjectProperties), this.timeInSecs)
        .getFirstElement();
    download.click();
    LOGGER.LOG.info("clocked on Browse... button ");
    waitForSecs(2);
    Dialog selectAproject = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a project"), this.timeInSecs).getFirstElement();
    waitForSecs(2);

    // select project -->AE Workplace ALM (RM)
    Link lnk =
        this.engine.findElementWithDuration(Criteria.isLink().withText(additionalParams.get("PROJECT_AREA")).inContainer(selectAproject), Duration.ofSeconds(120))
        .getFirstElement();
    lnk.click();
    LOGGER.LOG.info(additionalParams.get("PROJECT_AREA") + " -->    selected");
    waitForSecs(1);

    // clock on ok
    Button download1 = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(selectAproject), this.timeInSecs)
        .getFirstElement();
    download1.click();
    LOGGER.LOG.info("clocked on ok button");
    waitForSecs(1);
    Button btnNext =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >").inContainer(importProjectProperties),
            this.timeInSecs).getFirstElement();
    btnNext.click();
    
    LOGGER.LOG.info(" clicked on next >");
    waitForSecs(20);
      
    
  }
  
  
  /**
   * @param projectName name of project
   * @param projectSummary summary of project
   */
  public void createAnewProjectArea(final String projectName, final String projectSummary) {
    waitForSecs(2);

    // Click on Create Project Area button
    Button btnCreateProjectArea = this.engine
        .findElementWithinDuration(Criteria.isButton().withText("Create Project Area"), Duration.ofSeconds(60)).getFirstElement();
    btnCreateProjectArea.click();
    waitForSecs(1);

    // Input Project Area Name
    Text txtProjectAreaName = this.engine
        .findElementWithinDuration(Criteria.isTextField().hasLabel("Project Area Name"), Duration.ofSeconds(60)).getFirstElement();
    txtProjectAreaName.setText(projectName);

    // Input Summary
    Text txtSummary =
        this.engine.findElementWithinDuration(Criteria.isTextField().hasLabel("Summary:"), Duration.ofSeconds(60)).getFirstElement();
    txtSummary.setText(projectSummary);

    // Input Description
    Text txtDescription = this.engine
        .findElementWithinDuration(Criteria.isTextField().hasLabel("Description:").isMultiLine(), Duration.ofSeconds(60)).getFirstElement();
    txtDescription.setText("projectDescription");

    // Select proccess template
    Dropdown dropdown12 = this.engine
        .findElementWithDuration(
            Criteria.isDropdown().withDefaultValue("com.bosch.dng.tmpl.default.process.usa2016.1.0"), this.timeInSecs)
        .getFirstElement();
    dropdown12.selectOptionWithPartText("Requirements Management application template");

    // Click on Add Member button
    List<Button> btnAddList =
        this.engine.findElementWithinDuration(Criteria.isButton().withText("Add..."), Duration.ofSeconds(60)).getElementList();
    Button btnAddMember = btnAddList.get(0);
    Button btnAddAdministrator = btnAddList.get(1);
    btnAddMember.click();

    // Search User
    Dialog dlgSelectUser =
        this.engine.findElementWithinDuration(Criteria.isDialog().withTitle("Select Users"), Duration.ofSeconds(60)).getFirstElement();

    // Search member
    Text txtSearchMember1 = this.engine
        .findElementWithinDuration(
            Criteria.isTextField().hasLabel("Enter a name filter to load the list.").inContainer(dlgSelectUser), Duration.ofSeconds(60))
        .getFirstElement();
    txtSearchMember1.setText("CDG ALM Tester system-user-CC (XC-ECO/ESI1)");
    waitForSecs(Duration.ofSeconds(5));

    // ‎CDG ALM Tester system-user-CC (XC-ECO/ESI1) - ‎DYC9SI
    Dropdown drdMatchingMember1 = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Matching users:").inContainer(dlgSelectUser), Duration.ofSeconds(60))
        .getFirstElement();
    waitForSecs(1);
    drdMatchingMember1.selectOptionWithPartText("DYC9SI");
    waitForSecs(1);
    Button btnAddandCloseSelectUser = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Add and Close").inContainer(dlgSelectUser), Duration.ofSeconds(60))
        .getFirstElement();
    btnAddandCloseSelectUser.click();

    // Click on Add Admin button
    btnAddAdministrator.click();

    // Search User
    Dialog dlgSelectAdministrator =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Select Users"), Duration.ofSeconds(60)).getFirstElement();

    // Search member
    Text txtSearchAdmin = this.engine.findElementWithDuration(
        Criteria.isTextField().hasLabel("Enter a name filter to load the list.").inContainer(dlgSelectAdministrator),
        Duration.ofSeconds(60)).getFirstElement();
    txtSearchAdmin.setText("dyc9si");
    waitForSecs(Duration.ofSeconds(5));
    Dropdown drdMatchingAdmin =
        this.engine
        .findElementWithDuration(
            Criteria.isDropdown().withLabel("Matching users:").inContainer(dlgSelectAdministrator), Duration.ofSeconds(60))
        .getFirstElement();
    waitForSecs(1);
    drdMatchingAdmin.selectOptionWithPartText("CDG ALM Tester system-user-CC (XC-ECO/ESI1)");
    waitForSecs(1);
    Button btnAddandCloseSelectAdmin = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Add and Close").inContainer(dlgSelectAdministrator), Duration.ofSeconds(60))
        .getFirstElement();
    btnAddandCloseSelectAdmin.click();
    waitForSecs(2);
    Button btnSave = this.engine.findElementWithinDuration(Criteria.isButton().withText("Save"), Duration.ofSeconds(60)).getFirstElement();
    btnSave.click();
    waitForSecs(Duration.ofSeconds(3));
    // clock Import Project Properties
    Button btnExploreProject =
        this.engine.findElementWithinDuration(Criteria.isButton().withText("Explore Project"), Duration.ofSeconds(60)).getFirstElement();
    btnExploreProject.click();
    waitForSecs(1);

    Dialog dlgSetup = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Project Setup"), this.timeInSecs).getFirstElement();
    this.driverCustom.isElementInvisible("//a[text()='Apply a project template']", Duration.ofSeconds(5));
    this.driverCustom.isElementInvisible("//a[text()='Import project properties from an existing project']", Duration.ofSeconds(5));
    this.driverCustom.isElementInvisible("Manually define artifact types", Duration.ofSeconds(5));
    Button btnAddandClose = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Close").inContainer(dlgSetup), Duration.ofSeconds(60)).getFirstElement();
    btnAddandClose.click();
  }

  /**
   *
   */
  public void clickOnImportComponentProperties() {
    waitForSecs(1);
    boolean b = this.driverCustom.isElementInvisible("//div[@aria-disabled='false']//img", Duration.ofSeconds(3));
    System.out.println(b);

    // clickOnButtons
    if (!b) {
      waitForSecs(2);
      this.driverCustom.getPresenceOfWebElement("//div[@aria-disabled='false']//img").click();
    }
    waitForSecs(1);
    // clock Import Project Properties
    Button btnSave = this.engine
        .findElementWithinDuration(Criteria.isButton().withText("Import Component Properties"), Duration.ofSeconds(30)).getFirstElement();
    btnSave.click();
    waitForSecs(1);

    // clock Browse...
    Dialog importProjectProperties = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Import Component Properties"), this.timeInSecs)
        .getFirstElement();
    Button download = this.engine.findElementWithDuration(
        Criteria.isButton().withText(RMConstants.BROWSEBUTTON).inContainer(importProjectProperties), this.timeInSecs)
        .getFirstElement();
    download.click();
    waitForSecs(2);
    Dialog selectAproject = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a Component Configuration"), this.timeInSecs)
        .getFirstElement();
    waitForSecs(4);

    // select project
    Row rowtest = this.engine
        .findElementWithDuration(
            Criteria.isRow().containsText("component_001 Initial Stream").inContainer(selectAproject), Duration.ofSeconds(60))
        .getFirstElement();
    rowtest.click();
    waitForSecs(Duration.ofSeconds(3));

    // clock on ok
    Button download1 = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(selectAproject), this.timeInSecs)
        .getFirstElement();
    download1.click();
    waitForSecs(1);
    Button btnNext =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >").inContainer(importProjectProperties),
            this.timeInSecs).getFirstElement();
    btnNext.click();

    waitForSecs(2);
    this.driverCustom.getPresenceOfWebElement("//a[@title='Review Property Changes' and@class='selected']");
    try {
      String message = this.driverCustom.getWebElement("//div[@class='messageSummary']").getText();
      LOGGER.LOG.info(message);
    }
    catch (Exception e) {
      List<String> artifactlist = this.driverCustom.getWebElementsText("//div[@class='group']");
      String s = artifactlist.toString();
      String[] s7 = s.split(",");
      System.out.println(">>-------->>>> " + s7.toString());
      for (String ss : s7) {
        System.out.println(">>------> " + ss + " >>---------->>>> importated.");
        LOGGER.LOG.info("\n");
      }
      System.out.println("Print all properties ---> " + artifactlist);
    }
    waitForSecs(2);

    // Finish
    Button btnFinish =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Cancel").inContainer(importProjectProperties),
            this.timeInSecs).getFirstElement();
    btnFinish.click();
    waitForSecs(Duration.ofSeconds(5));
  }

  /**
   * @param componentName name of component
   * @param componentDescription description of component
   */
  public void createAnewComponent(final String componentName, final String componentDescription) {
    // Input Component Name
    Text txtComponentName =
        this.engine.findElementWithinDuration(Criteria.isTextField().hasLabel("Component name:"), Duration.ofSeconds(60)).getFirstElement();
    txtComponentName.setText(componentName);

    // Input Component Description
    Text txtComponentDescription =
        this.engine.findElementWithinDuration(Criteria.isTextField().hasLabel("Description:"), Duration.ofSeconds(60)).getFirstElement();
    txtComponentDescription.setText(componentDescription);
    waitForSecs(2);

    // click next
    Dialog createComponentDlg = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Create Component"), this.timeInSecs).getFirstElement();
    Button btnNext =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >").inContainer(createComponentDlg),
            this.timeInSecs).getFirstElement();
    btnNext.click();
    waitForSecs(2);
    Button btnFinish =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Finish").inContainer(createComponentDlg),
            this.timeInSecs).getFirstElement();
    btnFinish.click();
    waitForSecs(Duration.ofSeconds(10));
    Button btnSave = this.engine
        .findElementWithinDuration(Criteria.isButton().withText("Import Component Properties"), Duration.ofSeconds(30)).getFirstElement();
    btnSave.click();

    // clock Browse...
    Dialog importProjectProperties = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Import Component Properties"), this.timeInSecs)
        .getFirstElement();
    Button download = this.engine.findElementWithDuration(
        Criteria.isButton().withText(RMConstants.BROWSEBUTTON).inContainer(importProjectProperties), this.timeInSecs)
        .getFirstElement();
    download.click();
    waitForSecs(2);
    Dialog selectAcomponent = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a Component Configuration"), this.timeInSecs)
        .getFirstElement();
    waitForSecs(2);
    this.driverCustom.isElementVisible("//td[text()='Component:']", Duration.ofSeconds(5));
    try {
      this.driverCustom.getPresenceOfWebElement("//td[text()='Component:']/following-sibling::td").click();
      waitForSecs(2);
      this.driverCustom.getPresenceOfWebElement("//input[@placeholder='Type to filter list']").click();
      waitForSecs(2);// rbd_briBk1_hw
      this.driverCustom.getPresenceOfWebElement("//input[@placeholder='Type to filter list']").sendKeys("component_001",
          Keys.ENTER);
    }
    catch (Exception e) {
      LOGGER.LOG.info("Component: " + " drop down attribute failed to select value '" + "component_001 Initial Stream" +
          "' by Engine.\n" + e.getMessage());
    }

    this.driverCustom.click(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, "component_001 Initial Stream");
    waitForSecs(1);

    // clock on ok
    Button download1 = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(selectAcomponent), this.timeInSecs)
        .getFirstElement();
    download1.click();
    waitForSecs(1);
    Button btnNext1 =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >").inContainer(importProjectProperties),
            this.timeInSecs).getFirstElement();
    btnNext1.click();
    waitForSecs(Duration.ofSeconds(60));
    List<String> artifactlist = this.driverCustom.getWebElementsText("//div[@class='group']");
    String s = artifactlist.toString();
    String[] s7 = s.split(",");
   
    LOGGER.LOG.info(">>-------->>>> " + s7.toString());
    for (String ss : s7) {
     
      LOGGER.LOG.info(">>------> " + ss + " >>---------->>>> importated.");
      LOGGER.LOG.info("\n");
    }
   
    LOGGER.LOG.info("Print all properties ---> " + artifactlist);
    waitForSecs(2);

    // Finish
    Button btnFinish1 =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Cancel").inContainer(importProjectProperties),
            this.timeInSecs).getFirstElement();
    btnFinish1.click();
    waitForSecs(25);
  }

  /**<p>this method used after selecting dropdon from administration {#RMDashBoardPage.openSettingsSubMenu}
   * @author UUM4KOR
   * @param componentName component name
   * @param componentDescription new component description
   * @param checkBoxSelect boolean condition
   */
  public String createAnewComponentFromAdministration(final String componentName, final String componentDescription,String checkBoxSelect) {
    // Input Component Name
    Text txtComponentName =
        this.engine.findElementWithinDuration(Criteria.isTextField().hasLabel("Component name:"), Duration.ofSeconds(60)).getFirstElement();
    txtComponentName.setText(componentName);

    // Input Component Description
    Text txtComponentDescription =
        this.engine.findElementWithinDuration(Criteria.isTextField().hasLabel("Description:"), Duration.ofSeconds(60)).getFirstElement();
    txtComponentDescription.setText(componentDescription);
    waitForSecs(2);

    // click next
    Dialog createComponentDlg = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Create Component"), this.timeInSecs).getFirstElement();
    Button btnNext =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >").inContainer(createComponentDlg),
            this.timeInSecs).getFirstElement();
    btnNext.click();
    
    String checkboxAttributeVal=this.driverCustom.getAttribute("//div[@role='dialog']//input[@type='checkbox']", "aria-checked").toString();
    if(checkBoxSelect.equalsIgnoreCase("true")) {
      
      if(checkboxAttributeVal.equals("true")) {
        LOGGER.LOG.info("Use a template to initially populate the component check Box selected.");
      }
      if(checkboxAttributeVal.equals("false")) {
        this.driverCustom.getPresenceOfWebElement("//div[@role='dialog']//input[@type='checkbox']").click();  
        LOGGER.LOG.info("Use a template to initially populate the component check Box selected.");
      }
    }
    else if(checkBoxSelect.equalsIgnoreCase("fasle")) {
      
      if(checkboxAttributeVal.equals("true")) {
        this.driverCustom.getPresenceOfWebElement("//div[@role='dialog']//input[@type='checkbox']").click();  
        LOGGER.LOG.info("Use a template to initially populate the component check Box not selected.");
      }
      if(checkboxAttributeVal.equals("false")) {
        this.driverCustom.getPresenceOfWebElement("//div[@role='dialog']//input[@type='checkbox']").click();  
        LOGGER.LOG.info("Use a template to initially populate the component check Box not selected.");
      }
    }
    
    
    waitForSecs(2);
    Button btnFinish =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Finish").inContainer(createComponentDlg),
            this.timeInSecs).getFirstElement();
    btnFinish.click();
    return componentName;
  }
  /**
   * <P>
   * This method use to switch to 2nd tab in the opened browser. <br>
   * Navigate to DNG {@link RMManageComponentPropertiesPage} and click on ReqIF tab. <br>
   * After exporting a particular ReqIF file click on show result and then a new tab will open.
   */

  @Override
  public String switchTowindowTab() {
    String reportUrl = this.driverCustom.getWebDriver().getCurrentUrl();
   
    LOGGER.LOG.info(reportUrl);
    waitForSecs(Duration.ofSeconds(10));
    if (this.driverCustom.getWebDriver().getCurrentUrl().contains("reqif/reports")) {
      refresh();
    }
    String main = this.driverCustom.getWebDriver().getWindowHandle();
    Set<String> windows = this.driverCustom.getWebDriver().getWindowHandles();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
    for (String win : windows) {
      if (!win.equals(main)) {
        this.driverCustom.getWebDriver().switchTo().window(win);
        LOGGER.LOG.info(win + "tab/window  is Opened successfully.");
        String reportUrl1 = this.driverCustom.getWebDriver().getCurrentUrl();
        
        LOGGER.LOG.info(reportUrl1);
        break;
      }
    }
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(15));
    if (reportUrl.equals(this.driverCustom.getWebDriver().getCurrentUrl())) {
      return reportUrl + "doesn't changed after switching the window";
    }
    return reportUrl + "Switch to main window" + this.driverCustom.getWebDriver().getCurrentUrl();
  }

  /**
   * @param linkTypeName name of link type
   */
  public void deleteLinkTypes(final String linkTypeName) {
    waitForSecs(8);
    // Delete the previous process link
    JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
    js.executeScript("window.scrollBy(0,300)");
    
    WebElement lnkPreviousProcessLink =
        this.driverCustom.getWebElement(("//a[contains(.,'" + linkTypeName + "')]"));
  
    js.executeScript("arguments[0].scrollIntoView(true);", lnkPreviousProcessLink);
    
  Actions actions = new Actions(this.driverCustom.getWebDriver()); 
     actions.moveToElement(lnkPreviousProcessLink).build().perform();
     waitForSecs(2);
    WebElement lnkPreviousProcessLink1 =
        this.driverCustom.getWebElement(("//a[text()='"+linkTypeName+"']/../..//img"));
    Actions actions1 = new Actions(this.driverCustom.getWebDriver());
    actions1.moveToElement(lnkPreviousProcessLink1).click();
    
    waitForSecs(Duration.ofSeconds(5));
    lnkPreviousProcessLink1.click();
    waitForSecs(2);
   
    waitForSecs(2);
    this.driverCustom.click(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_YES_BUTTON_XPATH);
    waitForSecs(Duration.ofSeconds(5));
    LOGGER.LOG.info("Click on 'Yes' button in Confirm dialog.");
   
  }
  /**
   * <p>
   * This function used after {@link #RMManageComponentPropertiesPage.selectArtifact}
   *
   * @author KCE1KOR
   * @param dialog name of widget "Add Views to the ReqIF Definition"
   * @param drpDwnVal name is "Public Module Views"
   * @param viewName name of selecte view
   * @return true if View for Req If is selected
   */
  public boolean selectViewForReqIF(final String dialog, final String drpDwnVal, final String viewName) {
    this.driverCustom.isElementPresent(RMConstants.MANAGECOMPPROPERTIES_REQIF_VIEWS_XPATH, Duration.ofSeconds(20));
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_REQIF_VIEWSBUTTON_XPATH);
    waitForSecs(2);
    LOGGER.LOG.info("Dialog 'Add Views to the ReqIF Definition' displayed.");
    waitForSecs(2);
    LOGGER.LOG.info("Selected " + dialog + " in 'Add Views to the ReqIF Definition' dialog.");
    WebElement drdViewOption = this.driverCustom.getPresenceOfWebElement("//div[@dojoattachpoint='_viewsCategoryDiv']//input[@ value='▼ ']");
    this.driverCustom.clickUsingActions(drdViewOption);
    WebElement viewOpt = this.driverCustom.getPresenceOfWebElement("//td[text()='" + drpDwnVal + "']");
    this.driverCustom.getClickableWebElement(viewOpt).click();
    waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_REQIF_DISPLAYEDVIEW_XPATH, Duration.ofSeconds(10));
    this.driverCustom.click("//div[@class='add-views grid-container']//div[contains(text(),'" + viewName + "')]");
    LOGGER.LOG.info("' View  selected as '" + viewName + "' ");
    Button spanBtn = this.engine.findElementWithinDuration(Criteria.isButton().withToolTip("Add"), Duration.ofSeconds(5)).getFirstElement();
    spanBtn.click();
    return true;
  }

  /**
   * <p>
   * This function used after {@link #RMManageComponentPropertiesPage.isReqIFSaved}
   *
   * @author KCE1KOR
   * @param moduleNameOne of "ReqIF Definition"
   * @param moudleNameTwo of "ReqIF Definition"
   * @param viewName name of selecte view
   * @param artifactType of modules
   * @return true if module level views are same in for ReqIFDefinition
   */

  public boolean checkModuleNamesInReqIFDefinition(final String moduleNameOne, final String moudleNameTwo,
      final String artifactType, final String viewName) {
    String moduleViewNameOne = moduleNameOne + " (View: " + viewName + ")";
    String moduleViewNameTwo = moudleNameTwo + " (View: " + viewName + ")";
    WebElement mytable = this.driverCustom.getPresenceOfWebElement(By.xpath("//table//tbody"));
    List<WebElement> tablerows = mytable.findElements(By.xpath("//tr"));
    List<String> columValues = new ArrayList<>();
    for (int i = 0; i < tablerows.size(); i++) {
      List<WebElement> columnsRows = tablerows.get(i).findElements(By.xpath("td"));
      columnsRows.stream().map(WebElement::getText).forEach(columValues::add);
      columValues = columValues.stream().collect(Collectors.toList());
    }
    if (columValues.contains(moduleViewNameOne) && columValues.contains(moduleViewNameTwo) &&
        columValues.contains(artifactType)) {
      LOGGER.LOG.info("Different module names " + moduleViewNameOne + " and " + moduleViewNameTwo +
          " but the same Artifact Type '" + artifactType + "' in the ReqIF definition");
      return true;
    }
    LOGGER.LOG.info("Not the Different module names and not the same Artifact Type in the ReqIF definition");
    return false;
  }

  /**
   * <p>
   * Click on Administration icon using {@link RMDashBoardPage#openSettingsMenu(String)}. <br>
   * Choose option 'Manage Project Properties' using {@link RMDashBoardPage#openSettingsSubMenu(String)}. <br>
   * Choose 'Artifact Types' tab using ${@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}. <br>
   * Then call this method to find an artifact type and click on 'Delete...' button
   * <p>
   *
   * @author PDU6HC
   * @param artifactType : name of artifact type.
   * @return true if button delete is enabled
   *
   */
  public boolean clickOnDeleteAnArtifactType(final String artifactType) {
    waitForSecs(Duration.ofSeconds(10));
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, artifactType);
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_REQIF_DROPDOWN_XPATH, artifactType);
    waitForSecs(Duration.ofSeconds(5));
    boolean isDeleteButtonEnabled = this.driverCustom
        .isElementClickable(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_DELETE_ARTIFACT_TYPE_XPATH, this.timeInSecs);
    this.driverCustom.click(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_DELETE_ARTIFACT_TYPE_XPATH);
    return isDeleteButtonEnabled;
  }

  /**
   * <p>
   * Click on Administration icon using {@link RMDashBoardPage#openSettingsMenu(String)}. <br>
   * Choose option 'Manage Project Properties' using {@link RMDashBoardPage#openSettingsSubMenu(String)}. <br>
   * Choose 'Artifact Types' tab using ${@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}. <br>
   * Find an artifact type and click on 'Delete...' button using
   * ${@link RMManageComponentPropertiesPage#clickOnDeleteAnArtifactType(String)}. <br>
   * then call this method to verify if notice dialog displayed. <br>
   * <p>
   *
   * @author PDU6HC
   * @return true if Notice message display
   *
   */
  public boolean isNoticeMessageDisplay() {
    return this.driverCustom
        .isElementPresent(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_NOTICE_MESSAGE_XPATH, this.timeInSecs);
  }
  
  /**
   * @author UUM4KOR
   * This method used to check the successfull mesage. this method used after calling {#RMManageComponentPropertiesPage.importExistingComponentProperties} method wile importing component properties
   * @param successfulMessage message
   * @return BOOLEAN CONDITION
   */
  public boolean isSuccessfullMessageDisplayed(final String successfullMessage) {
   waitForSecs(Duration.ofSeconds(5));
    try {
      WebElement errorMsg = this.driverCustom.getWebElement(RMConstants.SUCCESSFUL_MESSAGE_XPATH);
      LOGGER.LOG.info("successful message is showing on the screen.");
      return errorMsg.getText().contains(successfullMessage);
    }
    catch (Exception e) {
      LOGGER.LOG.error("successfully message is NOT found.\n" + e.getMessage());
      return false;
    }
  }


  /**
   * <p>
   * Click on Administration icon using {@link RMDashBoardPage#openSettingsMenu(String)}. <br>
   * Choose option 'Manage Project Properties' using {@link RMDashBoardPage#openSettingsSubMenu(String)}. <br>
   * Choose 'Artifact Types' tab using {@link RMManageComponentPropertiesPage#clickOnTab(String)}. <br>
   * Then call this method to click on Artifact Type with name ("String"). <br>
   * Eg: clickOnArtifactType with name "clickOnArtifactType". <br>
   * <p>
   *
   * @author NVV1HC
   * @param artifactType artifact type to be selected
   */
  public void clickOnArtifactType(final String artifactType) {
    this.driverCustom.isElementClickable(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_ARTIFACTTYPE_XPATH,
        this.timeInSecs, artifactType);
    this.driverCustom.click(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_ARTIFACTTYPE_XPATH, artifactType);
  }

  /**
   * <p>
   * Click on Administration icon using {@link RMDashBoardPage#openSettingsMenu(String)}. <br>
   * Choose option 'Manage Project Properties' using {@link RMDashBoardPage#openSettingsSubMenu(String)}. <br>
   * Choose 'Artifact Types' tab using {@link RMManageComponentPropertiesPage#clickOnTab(String)}. <br>
   * Click on Artifact Type using {@link RMManageComponentPropertiesPage#clickOnArtifactType(String)}. <br>
   * Then call this method to remove Artifact Attribute From Artifact Type. <br>
   * <p>
   *
   * @author NVV1HC
   * @param artifactAttribute artifact attribute to be removed from the artifact type
   */
  public void removeArtifactAttributeFromArtifactType(final String artifactAttribute) {
    this.driverCustom.isElementClickable(
        RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_ARTIFACTATTRIBUTE_INARTIFACTTYPE_XPATH, this.timeInSecs,
        artifactAttribute);
    this.driverCustom.click(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_ARTIFACTATTRIBUTE_INARTIFACTTYPE_XPATH,
        artifactAttribute);
    clickOnButtons("Remove Attribute...");
    clickOnButtons("Yes");
    clickOnJazzSpanButtons("Save");
    waitForSecs(8);
  }

  /**
   * <p>
   * Click on Administration icon using {@link RMDashBoardPage#openSettingsMenu(String)}. <br>
   * Choose option 'Manage Project Properties' using {@link RMDashBoardPage#openSettingsSubMenu(String)}. <br>
   * Choose 'Artifact Attributes' tab using {@link RMManageComponentPropertiesPage#clickOnTab(String)}. <br>
   * Then call this method to delete Artifact Attribute. <br>
   * <p>
   *
   * @author NVV1HC
   * @param artifactAttribute artifact attribute to be deleted
   */
  public void deleteArtifactAttribute(final String artifactAttribute) {
    this.driverCustom.isElementClickable(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_ATTRIBUTE_XPATH,
        this.timeInSecs, artifactAttribute);
    this.driverCustom.click(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_ATTRIBUTE_XPATH, artifactAttribute);
    this.driverCustom.isElementClickable(
        RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_MOREACTIONS_OFARTIFACTATTRIBUTE_XPATH, this.timeInSecs,
        artifactAttribute);
    this.driverCustom.click(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_MOREACTIONS_OFARTIFACTATTRIBUTE_XPATH,
        artifactAttribute);
    this.driverCustom.click("//*[text()='Delete...']");
    clickOnDialogButton("Confirm","Yes");
    waitForSecs(Duration.ofSeconds(5));
  }

  /**
   * <p>
   * Click on Administration icon using {@link RMDashBoardPage#openSettingsMenu(String)}. <br>
   * Choose option 'Manage Project Properties' using {@link RMDashBoardPage#openSettingsSubMenu(String)}. <br>
   * Choose 'Artifact Attributes' tab using {@link RMManageComponentPropertiesPage#clickOnTab(String)}. <br>
   * Then call this method to verify if Artifact Attribute is displayed with correct name. <br>
   * <p>
   *
   * @author NVV1HC
   * @param artifactAttributeName artifact attribute to be verified
   * @return true if attribute is displayed with correct name and data type
   */
  public boolean isArtifactAttributeDisplayed(final String artifactAttributeName) {
    if (this.driverCustom.isElementVisible(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_ATTRIBUTE_XPATH,timeInSecs, artifactAttributeName)) {
    List<WebElement> listAttribute = this.driverCustom
        .getWebElements(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_ATTRIBUTE_XPATH, artifactAttributeName);
    if (listAttribute.size() == 1) {
      return listAttribute.get(0).isDisplayed();
    }
    }
    return false;
  }
}