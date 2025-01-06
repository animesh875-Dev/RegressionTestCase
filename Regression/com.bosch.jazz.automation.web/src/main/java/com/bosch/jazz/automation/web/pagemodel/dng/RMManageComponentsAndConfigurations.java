/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.JazzTextEditorFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * Represents the Requirement Management Component and Configuration page.
 */
public class RMManageComponentsAndConfigurations extends AbstractRMPage {

  /**
   * Constructor setting the {@link WebDriverCustom}.
   *
   * @param driverCustom required for interacting with the browser.
   */
  public RMManageComponentsAndConfigurations(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTextFieldFinder(), new JazzRadioButtonFinder(), new JazzButtonFinder(),
        new JazzRowFinder(driverCustom.getWebDriver()), new RowCellFinder(), new JazzDropdownFinder(),
        new TextFieldFinder(), new JazzDialogFinder(), new JazzTextEditorFinder());
  }

  /**
   * <p>
   * Open all projects page in Requirement Management.<br>
   * Select any 'Configuration Enabled' project area.<br>
   * Click on 'Create Component' button,'Create Component' dialog is displayed.<br>
   * Provide Component name, Description in 'Create a requirement component' section and click on 'Next >'
   * button,'Choose a template' section is displayed.<br>
   * Enable the check box for 'Use a template to initially populate the component'.<br>
   * Search the template name in 'Type to filter' text box.<br>
   * Select the required template and click on 'Finish' button.
   *
   * @param componentTemplate provide the template to import to component
   */
  public void selectATemplateFromCreateComponentDialog(final String componentTemplate) {
    waitForSecs(3);
    Dialog dlgCreateComponent =
        this.engine.findElement(Criteria.isDialog().withTitle("Create Component")).getFirstElement();
    LOGGER.LOG.info("The 'Create Component' dialog is displayed");
    Checkbox cbxUseTemplate =
        this.engine.findElement(Criteria.isCheckbox().inContainer(dlgCreateComponent)).getFirstElement();
    cbxUseTemplate.click();
    waitForSecs(3);
    Text txtFilter =
        this.engine.findElement(Criteria.isTextField().withPlaceHolder("Type to filter")).getFirstElement();
    txtFilter.setText(componentTemplate);
    LOGGER.LOG.info("Input " + componentTemplate + " component template to 'Type to filter' text field");
    waitForSecs(3);
    try {
      Label lblTemplate = this.engine.findElement(Criteria.isLabel().withText(componentTemplate)).getFirstElement();
      lblTemplate.click();
    }
    catch (Exception e) {
      throw new InvalidArgumentException(componentTemplate + " - doesn't exist in the template list.");
    }
    LOGGER.LOG.info("Select " + componentTemplate + " from the template list");
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Create Component' option using {@link RMDashBoardPage#openSettingsSubMenu(String)},'Create Component'
   * dialog is displayed.<br>
   * Provide Component name, Description in 'Create a requirement component' section and click on 'Next >'
   * button,'Choose a template' section is displayed.<br>
   * Enable the check box for 'Use a template to initially populate the component'.<br>
   * Search the template name in 'Type to filter' text box.<br>
   * Select the required template and click on 'Finish' button.
   *
   * @param componentName input component name.
   * @param componentDescription input component description.
   * @param componentTemplate select template for component.
   * @return component name with date time
   */
  public String createComponentFromAdministrations(final String componentName, final String componentDescription,
      final String componentTemplate) {
    waitForSecs(3);
    Dialog dlgCreateComponent =
        this.engine.findElement(Criteria.isDialog().withTitle("Create Component")).getFirstElement();
    LOGGER.LOG.info("The 'Create Component' dialog is displayed");
    Text txtComponentName =
        this.engine.findElement(Criteria.isTextField().hasLabel("Component name:").inContainer(dlgCreateComponent))
        .getFirstElement();
    txtComponentName.setText(componentName);
    LOGGER.LOG.info("Input component Name is " + componentName + " in the Create Component dialog");

    Text txtComponentDescription = this.engine
        .findElement(Criteria.isTextField().isMultiLine().hasLabel("Description").inContainer(dlgCreateComponent))
        .getFirstElement();
    txtComponentDescription.setText(componentDescription);
    LOGGER.LOG.info("Input component Description is " + componentDescription + " in the Create Component dialog");

    clickOnJazzButtons("Next >");
    LOGGER.LOG.info("Click on Next > button in the Create Component dialog");
    waitForSecs(3);
    try {
      // update - engine not work fine so replace by xpath:
      // cbxTemplate => ko find dc element nay vs engine
      //     Checkbox cbxTemplate =
      //          this.engine.findElement(Criteria.isCheckbox().withLabel("Use a template to initially populate the component")
      //          .inContainer(dlgCreateComponent)).getFirstElement();
      //      cbxTemplate.click();


      // temporary - related to ISSUE with ticket ID 590021
      waitForSecs(5);
      this.driverCustom.getWebElement(RMConstants.CHECKBOX_CREATECOMPONENTDIALOG_XPATH).click();
      LOGGER.LOG.info("Click on 'Use a template to initially populate the component' check-box ");
    }
    catch (Exception e) {
      waitForSecs(1);
    }
    waitForSecs(3);
    Text textFilter =
        this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to filter"), timeInSecs).getFirstElement();
    textFilter.setText(componentTemplate);
    LOGGER.LOG.info("Input " + componentTemplate + " component template to 'Type to filter' text field.");
    waitForSecs(3);
    try {
      Label lblCompTemplate = this.engine.findElement(Criteria.isLabel().withText(componentTemplate)).getFirstElement();
      lblCompTemplate.click();
    }
    catch (Exception e) {
      throw new InvalidArgumentException(componentTemplate + " - doesn't exist in the template list.");
    }
    LOGGER.LOG.info("Select " + componentTemplate + " from the template list.");
    clickOnJazzButtons("Finish");
    return componentName;
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Components and Configurations' option using
   * {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Click on 'Browse Components' link present top of the page, list of components displayed.<br>
   * Verify the created component is displayed in the list of components.
   *
   * @param componentName name of component need to verify
   * @return true if link of component is displayed, false if link of component is not displayed
   */
  public boolean isComponentOrConfigurationLinkDisplayed(final String componentName) {
    waitForPageLoaded();
    Link link = null;
    try {
      link = this.engine.findElement(Criteria.isLink().withText(componentName)).getFirstElement();
      LOGGER.LOG.info("Component " + componentName + RMConstants.IS_DISPLAYED);
    }
    catch (Exception e) {
      if (this.driverCustom.isElementVisible(RMConstants.NEXT_PAGING_BUTTON_XPATH,  Duration.ofSeconds(5))) {
        while (this.driverCustom.isEnabled(RMConstants.NEXT_PAGING_BUTTON_XPATH) && (link == null)) {
          this.driverCustom.click(RMConstants.NEXT_PAGING_BUTTON_XPATH);
          LOGGER.LOG.info("Click on Next button");
          try {
            link = this.engine.findElement(Criteria.isLink().withText(componentName)).getFirstElement();
            LOGGER.LOG.info("Component " + componentName + RMConstants.IS_DISPLAYED);
          }
          catch (Exception e2) {
            LOGGER.LOG.error(e2);
          }
        }
      }
    }
    return link != null;
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Components and Configurations' option using {@link RMDashBoardPage#openSettingsSubMenu(String)},
   * component page is displayed.<br>
   * Hover on a row of configuration item to open 'Actions' drop down present near to the configuration name.<br>
   * Select the required sub menu from the drop down.
   *
   * @param configName the stream, baseline for identify Context menu
   * @param subMenu Refresh, Archive, Create Baseline, Create Steam...
   */
  public void clickOnContextMenuForAConfigurations(final String configName, final String subMenu) {
    waitForSecs(3);
    Row rowConfig = null;
    try {
      rowConfig = this.engine.findElement(Criteria.isRow().containsText(configName)).getFirstElement();
      LOGGER.LOG.info("Configuration" + configName + " is displayed");
    }
    catch (Exception e) {
      if (this.driverCustom.isElementVisible("//a[contains(@class,'next') and contains(@aria-disabled,'false')]",  Duration.ofSeconds(5))) {
        while (this.driverCustom.isEnabled(RMConstants.NEXT_PAGING_BUTTON_XPATH) && (configName != null)) {
          
          this.driverCustom.click(RMConstants.NEXT_PAGING_BUTTON_XPATH);
          LOGGER.LOG.info("Click on 'Next' button");
          try {
            rowConfig = this.engine.findElement(Criteria.isRow().containsText(configName)).getFirstElement();
            if (rowConfig != null) {
              rowConfig.hoverOnElement();
              Dropdown actionDropdown = this.engine
                  .findElement(Criteria.isDropdown().withToolTip("Actions").inContainer(rowConfig)).getFirstElement();
              actionDropdown.selectOptionWithText(subMenu);
              LOGGER.LOG.info(String.format("Click on 'Actions' drop down menu of '%s' and select '%s' option", configName, subMenu));
            }
            else {
              LOGGER.LOG.info("Could not found " + configName);
            }
            LOGGER.LOG.info("Configuration" + configName + " is displayed");
            return;
          }
          catch (Exception e2) {
            // Do not log it into selenium log file
            //LOGGER.LOG.error(e2);
          }
        }
      }else {
        LOGGER.LOG.info(" 'Next' button is disabled. ");
      }
        
    }
    if (rowConfig != null) {
      waitForSecs(5);
      rowConfig.hoverOnElement();
      Dropdown actionDropdown = this.engine
          .findElement(Criteria.isDropdown().withToolTip("Actions").inContainer(rowConfig)).getFirstElement();
      actionDropdown.selectOptionWithText(subMenu);
      LOGGER.LOG.info("Click on Actions dropdown menu of " + configName + " and select " + rowConfig);
    }
    else {
      LOGGER.LOG.info("Could not found " + configName);
    }
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Components and Configurations' option using {@link RMDashBoardPage#openSettingsSubMenu(String)},
   * component page is displayed.<br>
   * Hover on a row of configuration item to open 'Actions' drop down present near to the configuration name.<br>
   * Select the sub menu 'Archive' from the drop down, 'Archive Warning' dialog is displayed.<br>
   * Click on 'Archive the configuration and all children' button.<br>
   * Click on 'OK' button.
   *
   * @param configName is baseline, stream, change set...
   */
  public void archiveConfiguration(final String configName) {
    clickOnContextMenuForAConfigurations(configName, "Archive");
    waitForSecs(2);
    try {
      try {
        clickOnJazzSpanButtons("Archive the configuration and all children");
        waitForSecs(2);
        clickOnJazzSpanButtons("OK");
      }
      catch (Exception e) {
        clickOnJazzSpanButtons("Archive configuration and all children");
        waitForSecs(2);
        clickOnJazzSpanButtons("OK");
      }

    }
    catch (Exception e) {
      clickOnJazzButtons("Archive configuration and all children");
      waitForSecs(2);
      clickOnJazzButtons("OK");
    }
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Components and Configurations' option using
   * {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Click on 'Browse Components' link present top of the page, list of components displayed in Browse Components
   * page.<br>
   * Search the component name 'Search by name' text box present right side of the 'Browse Components' page.
   *
   * @param componentName input the component name that you want to search
   * @return true if found component, otherwise return false.
   */
  public boolean searchComponentInBrowseComponentPage(final String componentName) {
    waitForSecs(3);
    Text txtSearch =
        this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Search by name"), this.timeInSecs)
        .getFirstElement();
    txtSearch.setText(componentName);
    LOGGER.LOG.info("Searched " + componentName + " in 'Search by name' text box.");
    waitForSecs(3);
    this.driverCustom.getPresenceOfWebElement(RMConstants.COMPONENT_LIST_IN_BROWSE_COMPONENT_XAPTH);
    List<WebElement> allRows = this.driverCustom.getWebElements(RMConstants.COMPONENT_LIST_IN_BROWSE_COMPONENT_XAPTH);
    ArrayList<String> rowValues = new ArrayList<>();
    for (WebElement row : allRows) {
      rowValues.add(row.getText());
    }
    if (!rowValues.contains(componentName)) {
      LOGGER.LOG.warn(componentName + " component doesn't exist in the Browse Components page.");
      return false;
    }
    return true;
  }

  /**
   * <p>
   * Select on the first archived component name from the list of component in the 'Browse Components' page.
   *
   * @return name of selected component if passed, otherwise return false.
   */
  public String selectTheFirstArchivedComponentInBrowseComponentPage() {
    waitForSecs(3);
    this.driverCustom.getPresenceOfWebElement(RMConstants.COMPONENT_LIST_IN_BROWSE_COMPONENT_XAPTH);
    List<WebElement> lstComponents = this.driverCustom.getWebElements(RMConstants.COMPONENT_LIST_IN_BROWSE_COMPONENT_XAPTH);
    if (!lstComponents.isEmpty()) {
      WebElement firstComponent = lstComponents.get(0);
      String componentName = firstComponent.getText();
      firstComponent.click();
      LOGGER.LOG.info(componentName + " component is selected in the Browse Components page.");
      return componentName;
    }
    return "No Components";
  }
  
  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Components and Configurations' option using
   * {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Click on 'Browse Components' link present top of the page, list of components displayed in Browse Components
   * page.<br>
   * Click on 'Show archived components' icon.<br>
   * Click on required component, component is opened successfully.<br>
   * Click on 'Restore this component' icon present right side of the component page.<br>
   * 'Confirm Restore' dialog is displayed.<br>
   * Click on 'OK' button.<br>
   * Restore this component without permission.<br>
   * Error message will display. <br>
   * Verify Error message is displayed.
   *
   * @param errorMessage error message summary
   * @return true if Error message is displayed else it will return false.
   */
  public boolean isErrorMessageDisplayed(final String errorMessage) {
    try {
      WebElement errorMsg = this.driverCustom.getWebElement(RMConstants.ERROR_MESSAGE_XPATH);
      LOGGER.LOG.info("Error message is showing on the screen.");
      return errorMsg.getText().contains(errorMessage);
    }
    catch (Exception e) {
      LOGGER.LOG.error("Error message is NOT found.\n" + e.getMessage());
      return false;
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
   * After navigate to the component, click on 'Browse Components' button
   * <p>
   * 
   * @author NVV1HC
   */
  public void backToBrowseComponentPage() {
    this.driverCustom
    .getPresenceOfWebElement(RMConstants.RMMANAGECOMPONENTANDCONFIGURATIONPAGE_BROWSECOMPONENTLINK_XPATH).click();
    waitForPageLoaded();
    this.driverCustom.isElementVisible(RMConstants.RMMANAGECOMPONENTANDCONFIGURATIONPAGE_BROWSECOMPONENTPAGETITLE_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)));
  }

  /**
   * <p>
   * After navigate to the Manage Components and Configurations page, search component then explore this component *
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Components and Configurations' option using
   * {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Click on 'Search by name' text box then type component name <br>
   * Click on the searchresult.<br>
   * Click on 'Explore Component' button.<br>
   * Wait for navigate to the selected component successfully
   * <p>
   * 
   * @author NVV1HC
   * @param componentName name of component that you want to explore
   */
  public void searchAndExploreComponent(final String componentName) {
    waitForSecs(3);
    Text txtSearch =
        this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Search by name"), this.timeInSecs)
        .getFirstElement();
    txtSearch.setText(componentName);
    LOGGER.LOG.info("Searched " + componentName + " in 'Search by name' text box.");
    waitForSecs(3);
    this.driverCustom.getPresenceOfWebElement(RMConstants.COMPONENT_LIST_IN_BROWSE_COMPONENT_XAPTH);
    this.driverCustom
    .getPresenceOfWebElement(RMConstants.RMMANGECOMPONENTSANDCONFIGURATION_SEARCH_RESULT_COMPONENT, componentName)
    .click();
    waitForPageLoaded();
    Button exploreComponentBtn =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Explore Component"), Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)))
        .getFirstElement();
    exploreComponentBtn.click();
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_COMPONENT_NAME, componentName);
    waitForSecs(3);
  }

  /**
   * <p>
   * After navigate to the specific archived component, click on 'Restore this components' button
   * <p>
   * 
   * @author KYY1HC
   */
  public void restoreArchivedComponent() {
    waitForPageLoaded();
    Button restoreThisComponentBtn = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Restore this component"), Duration.ofSeconds(20));
    restoreThisComponentBtn.click();
    clickOnDialogButton("Confirm Restore", "OK");
    this.driverCustom.isElementInvisible(RMConstants.RMMANAGECOMPONENTANDCONFIGURATIONPAGE_SAVINGLOADING_XPATH, timeInSecs);
    clickOnJazzButtons("OK");
  }

  /**
   * <p>
   * After navigate to the specific active component, click on 'Archive this components' button
   * <p>
   * 
   * @author KYY1HC
   */
  public void archiveActiveComponent() {
    waitForPageLoaded();   
    Button archiveThisComponentBtn = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Archive this component to hide it and its contents"), Duration.ofSeconds(20));
    archiveThisComponentBtn.click(); 
    clickOnDialogButton("Confirm Archive", "Archive");
    this.driverCustom.isElementInvisible(RMConstants.RMMANAGECOMPONENTANDCONFIGURATIONPAGE_SAVINGLOADING_XPATH, timeInSecs);
  }
  
  /**
   * @param componenetName
   */
  public void archiveCreatedNewComponent(String componenetName) {
    waitForPageLoaded();
    if (this.driverCustom.isTitleContains(componenetName, Duration.ofSeconds(20))) {
      System.out.println("Title contains '"+componenetName+"'");
      
    }else {
      System.out.println("Title not contains '"+componenetName+"'");
    }
    try {
    Button archiveThisComponentBtn = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Archive this component to hide it and its contents"), Duration.ofSeconds(20));
    archiveThisComponentBtn.click();
    }
    catch (Exception e) {
      // TODO: handle exception
      
      this.driverCustom.getPresenceOfWebElement("//a[@title='Archive this component to hide it and its contents']").click();
    }
    clickOnDialogButton("Confirm Archive", "Archive");
    this.driverCustom.isElementInvisible(RMConstants.RMMANAGECOMPONENTANDCONFIGURATIONPAGE_SAVINGLOADING_XPATH, timeInSecs);
 
  }

  /**
   * <p>
   * After navigate to the specific active component, click on 'Archive this components' button
   * <p>
   * 
   * @author KYY1HC
   * @param componentName name of component
   * @return true if component epxlored successfully, otherwise return false
   */
  public boolean exploreActiveComponent(final String componentName) {
    waitForSecs(3);
    refresh();
    waitForPageLoaded();
    Button exploreThisComponentBtn = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("View and edit the artifacts in this component"), Duration.ofSeconds(20));
    exploreThisComponentBtn.click();
    LOGGER.LOG.info("Waiting for 'Loading Project Dashboard...' is invisible.");
    this.driverCustom.isElementInvisible(RMConstants.RMMANAGECOMPONENTANDCONFIGURATIONPAGE_PROJECTDASHBOARDLOADING_XPATH, Duration.ofSeconds(20));
    LOGGER.LOG.info("Waiting for 'Loading artifacts page...' is invisible.");
    this.driverCustom.isElementInvisible(RMConstants.RMMANAGECOMPONENTANDCONFIGURATIONPAGE_ARTIFACTSPAGELOADING_XPATH, Duration.ofSeconds(20));
    LOGGER.LOG.info("Waiting for 'Loading permissions...' is invisible.");
    this.driverCustom.isElementInvisible(RMConstants.RMMANAGECOMPONENTANDCONFIGURATIONPAGE_PERMISSIONSLOADING_XPATH, Duration.ofSeconds(20));
    WebElement currentComponent = this.driverCustom.getWebElement("//span[@class='rdm-team-component-menu']");
    if (currentComponent.getText().contains(componentName)) {
      LOGGER.LOG.info("Active Component " + componentName + " is explored successfully.");
      return true;
    }
    LOGGER.LOG.info("Active Component " + componentName + " is not explored successfully.");
    return false;
  }
}