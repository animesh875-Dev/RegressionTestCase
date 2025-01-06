/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Artifact;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactAttributes;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.EditArtifactContentEnums;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.jazz.automation.web.pagemodel.ManageProjectAreaPage;
import com.bosch.jazz.automation.web.pagemodel.PagemodelConstants;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.utility.ExcelCSVReader;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.RadioButton;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.container.TreeNode;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.element.text.TextField;
import com.bosch.psec.web.test.finder.ButtonFinder;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.ListboxFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.container.treeNode.JazzTreeNodeFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.JazzTextFinder;
import finder.text.label.JazzSpanLabelFinder;
import finder.text.textField.JazzSpanTextFieldFinder;


/**
 * Represents the Requirement Management Artifacts Page this is common for Artifacts,Modules and Collections...
 */
public class RMArtifactsPage extends AbstractRMPage {

  private static final String INVALID_PARAMETER_OR_NULL_OR_EMPTY = "Invalid parameter or null or empty.";

  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom required for interacting with the browser.
   */
  public RMArtifactsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTreeNodeFinder(), new JazzDropdownFinder(), new JazzButtonFinder(),
        new JazzTextFinder(), new JazzSpanLabelFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new JazzDialogFinder(), new RowCellFinder(), new LinkFinder(), new JazzSpanTextFieldFinder(), new TextFinder(),
        new JazzTabFinder(), new ListboxFinder(), new CheckboxFinder(), new ButtonFinder(),
        new JazzRadioButtonFinder());
  }

  /**
   * <p>
   * Open Artifacts menu {@link RMDashBoardPage#openMenu(String)}, loads all artifacts for the project area. <br>
   * Left side of artifacts page contains all the folders of the project. <br>
   * Double click on the root folder of the project area i.e folder (with project area name) loads all artifacts in
   * selected project area.
   *
   * @author VDY1HC
   * @param rootFolderName - name of the root folder.
   */
  public void loadArtifactsInRootFolder(final String rootFolderName) {
    waitForLoadingMessage();
    WebElement treeProjectFolder =
        this.driverCustom.getPresenceOfWebElement(RMConstants.FOLDER_NAME_XPATH, rootFolderName);
    this.driverCustom.getClickableWebElement(treeProjectFolder).click();
    waitForSecs(2);
    if (this.driverCustom.isElementVisible(RMConstants.FILTER_FOLDER_ENABLE_NAME_XPATH, this.timeInSecs)) {
      treeProjectFolder.click();
      waitForSecs(2);
    }
  }

  /**
   * Wait for loading message to completely loaded
   *
   * @author VDY1HC
   */
  public void waitForLoadingMessage() {
    int i = 0;
    do {
      this.driverCustom.isElementInvisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_DISPLAY_XPATH,
          this.timeInSecs);
      this.driverCustom.waitForSecs(Duration.ofSeconds(1));
      if (i++ > 10) {
        break;
      }
    }
    while (this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_DISPLAY_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() / 10))));
  }

  /**
   * <p>
   * Open artifacts page using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'Create' drop down button visible at the top left of the page. <br>
   * Select appropriate options like "Import Artifact...","Upload Artifact...","Other..." or any other artifact type.
   *
   * @param option selects option from create drop down.
   */
  public void selectOptionFromCreateDropDown(final String option) {
    waitForPageLoaded();
    Button btn = this.engine.findElement(Criteria.isButton().withToolTip("Create Module")).getFirstElement();
    btn.click();
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTREQUIREMENT_XPATH, Duration.ofSeconds(30),
        option);
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECTREQUIREMENT_XPATH, option);
  }


  /**
   * @param option
   
   * 
   */
  @SuppressWarnings("javadoc")
  public void selectOptionFromDropDown(final String option) {
    waitForPageLoaded();
    WebElement btn = this.driverCustom.getPresenceOfWebElement("//span[text()='Select the link type']");
    this.driverCustom.getClickableWebElement(btn).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    try {
      this.driverCustom
          .click("(//table[contains(@class, 'dijitSelectMenu dijitValidationTextBoxMenu')])//td[contains(text(),'" +
              option + "')]");
    }
    catch (Exception e) {
      System.out.println(option + "link type not selected" + e);
    }
  }


  /**
   * click on Artifact context menu, and Move Artifact to folder Click on Folder
   *
   * @param option
   */
  @SuppressWarnings("javadoc")
  public void selectFolderFromMoveArtifactToFolder(final String option) {
    waitForPageLoaded();
    WebElement button =
        this.driverCustom.getPresenceOfWebElement("(//span[text()='Artifact For Automation Testing'])[2]");
    this.driverCustom.getClickableWebElement(button).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));

  }

  /**
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}. <br>
   * Click on create button present top left of Artifacts page using
   * {@link RMArtifactsPage#clickOnCreateButton(String)}. <br>
   * 'Create Artifact' pop up window will display. <br>
   * Provide necessary details like Artifact Name,Artifact Type,Artifact Format etc.. <br>
   * Click on 'OK' button present inside 'Create Artifact' window.
   *
   * @param additionalParams list of keys like 'ARTIFACT_NAME','ARTIFACT_TYPE','ARTIFACT_FORMAT'...
   */
  public void createArtifact(final Map<String, String> additionalParams) {
    // update
    waitForPageLoaded();
    String artifactName = additionalParams.get("ARTIFACT_NAME");
    String artifactType = additionalParams.get("ARTIFACT_TYPE");
    String artifactFormat = additionalParams.get("ARTIFACT_FORMAT");
    Dialog createArtifactDialog =
        this.engine.findElement(Criteria.isDialog().withTitle("Create Artifact")).getFirstElement();
    Dropdown drdArtifactType = this.engine.findFirstElement(
        Criteria.isDropdown().withLabel(Artifact.ARTIFACT_TYPE.toString() + ":").inContainer(createArtifactDialog));
    drdArtifactType.selectOptionWithText(artifactType);
    try {
      // Select Artifact Format
      Dropdown drdArtifacFormat = this.engine.findFirstElement(
          Criteria.isDropdown().withLabel(Artifact.ARTIFACT_FORMAT.toString() + ":").inContainer(createArtifactDialog));
      drdArtifacFormat.selectOptionWithText(artifactFormat);
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    Text txtInitalContent =
        this.engine.findElement(Criteria.isTextField().hasLabel(RMConstants.NAME).inContainer(createArtifactDialog))
            .getElementByIndex(2);
    txtInitalContent.setText(artifactName);

    Button btnOK =
        this.engine.findElement(Criteria.isButton().withText("OK").inContainer(createArtifactDialog)).getFirstElement();
    btnOK.click();
    waitForSecs(5);
  }

  /**
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}. <br>
   * Click on 'Configure Page Setting' icon.<br>
   * Column names like ID,Name,Artifact Type etc... display. <br>
   * Click on 'More...' option from the pop up window,'Change Column Display Settings' dialog will display. <br>
   * Search the artifact attribute name 'Type to filter by attribute name' text box. <br>
   * Select the searched artifact attribute and click on 'ADD' button. <br>
   * Click on 'OK' button.
   *
   * @param columnHeaderName name of the artifact attribute to be added in the Artifact table.
   */
  public void addArtifactAttributeInToArtifactTable(final String columnHeaderName) {
    waitForPageLoaded();
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), this.timeInSecs);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='Configure Page Settings']")));
    this.driverCustom.waitForSecs(Duration.ofSeconds(1));

    WebElement configurationElement =
        this.driverCustom.getPresenceOfWebElement("//img[@title='Configure Page Settings']");
    this.driverCustom.getClickableWebElement(configurationElement).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));

    WebElement optionElement = this.driverCustom.getPresenceOfWebElement("//td[text()='" + RMConstants.MORE + "']");
    this.driverCustom.getClickableWebElement(optionElement).click();

    Dialog dlgChangeColumnDisplaySetting =
        this.engine.findElement(Criteria.isDialog().withTitle("Change Column Display Settings")).getFirstElement();

    Text txtSearch = this.engine.findFirstElement(Criteria.isTextField().inContainer(dlgChangeColumnDisplaySetting)
        .withPlaceHolder("Type to filter by attribute name"));
    txtSearch.setText(columnHeaderName);
    try {
      // Select search result
      Label lblColumn = this.engine.findElementWithDuration(
          Criteria.isLabel().withText(columnHeaderName).inContainer(dlgChangeColumnDisplaySetting),
          Duration.ofSeconds(30)).getFirstElement();
      lblColumn.click();

      // Click on Add button
      Button btnAdd = this.engine
          .findElementWithDuration(Criteria.isButton().withText("Add").inContainer(dlgChangeColumnDisplaySetting),
              Duration.ofSeconds(30))
          .getFirstElement();
      btnAdd.click();

      // Click on OK button
      Button btnOKInChangeColumnDisplaySettingsPopup = this.engine
          .findElement(Criteria.isButton().withText("OK").inContainer(dlgChangeColumnDisplaySetting)).getFirstElement();
      btnOKInChangeColumnDisplaySettingsPopup.click();

      // Wait for loading after added
      String xpathLoadingLink = "//div[@class='status-message'][text()='Loading artifact links...']";
      this.driverCustom.isElementVisible(xpathLoadingLink, Duration.ofSeconds(5));
      this.driverCustom.isElementVisible(xpathLoadingLink, Duration.ofSeconds(5));


    }
    catch (Exception e) {
      // Click on Cancel button
      Button btnCancelInChangeColumnDisplaySettingsPopup =
          this.engine.findElement(Criteria.isButton().withText("Cancel").inContainer(dlgChangeColumnDisplaySetting))
              .getFirstElement();
      btnCancelInChangeColumnDisplaySettingsPopup.click();
    }
  }

  /**
   * <p>
   * {@link #createArtifact(Map)} creates artifact with providing unique id to the artifact. <br>
   * Returns id of the created artifact.
   *
   * @return id of the artifact.
   */
  public String getArtifactID() {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_GETARTIFACTTEST_XPATH, Duration.ofSeconds(10));
    LOGGER.LOG.info("Get the newly created Artifact ID.");
    return this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_GETARTIFACTTEST_XPATH);
  }

  /**
   * Verify that the link is visible on the table with the selected artifact id inside the module
   *
   * @param text full text of the link
   * @return true if the link is visible on the table
   */
  public boolean isTheLinkVisibledOnTheTable(final String text) {
    waitForPageLoaded();
    return this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_ARTIFACTID_LINK_XPATH, Duration.ofSeconds(10),
        text);
  }

  /**
   * <p>
   * Search the existing artifact from quick search text box in Artifacts Page. <br>
   * Searched artifact will display. <br>
   * Click on 'Edit' button present right side in Artifact page. <br>
   * 'Select Type Section' is 'Overview' or 'Comments' section. <br>
   * If section is equals 'Overview' enter Description text in 'Overview' section and click on 'Done' button. <br>
   * If section is equals 'Comments',click on 'Create comment for Artifact...' icon. <br>
   * 'Create comment for Artifact' pop up window will display. <br>
   * Provide necessary details Subject,Comments,Select Users... and click on 'OK' button.
   *
   * @param additionalParams stores key values in the map "Artifact ID","Select Type Section","Select Type
   *          Section","Select Item","Sub Item Selection","User ID".
   */
  public void editArtifact(final Map<String, String> additionalParams) {
    // waitForPageLoaded();
    String artifactID = additionalParams.get("Artifact ID");
    String sec = additionalParams.get("Select Type Section");
    this.driverCustom.typeText(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH, artifactID);
//  Quick search box default options: Folder and Module will shown 2 results - Artifact ID and Artifact ID "in module".
    // this.driverCustom.click(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, artifactID);
    try {
      this.driverCustom.click(
          "//*[contains(text(),'In Module: ')]/parent::div/parent::div/following-sibling::div[1]//*[contains(text(),'DYNAMIC_VAlUE')]",
          artifactID);
    }
    catch (Exception e) {
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_QUICKSEARCH_ARTIFACT_ID_IN_MODULE_, artifactID);
    }
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_EDITTHEARTIFACT_XPATH, Duration.ofSeconds(60));
    Button btnEdit = this.engine.findElement(Criteria.isButton().withText("Edit")).getFirstElement();
    btnEdit.getWebElement().click();
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_SAVETHEARTIFACT_XPATH, Duration.ofSeconds(10));

    if (!(sec.equals(RMConstants.OVERVIEW))) {
      this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_OVERVIEWTAB_FIELD_XPATH, sec);
    }
    // Edit Overview Description
    if (sec.equals(RMConstants.OVERVIEW)) {
      // Only edit Description
      String newDescription = "EditedByDNGTestAutomationTeam_" + DateUtil.getCurrentDateAndTime();
      this.driverCustom.typeText(RMConstants.RMARTIFACTSPAGE_DESCRIPTIONBUTTON_XPATH, newDescription);
      this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_DONETHEARTIFACT_XPATH, Duration.ofSeconds(10));
      Button btnDone = this.engine.findElement(Criteria.isButton().withText("Done")).getFirstElement();
      btnDone.click();
    }
    // Edit comment
    else if (sec.equals("Comments")) {
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_COMMENT_LINKS_XPATH, Duration.ofSeconds(10),
          additionalParams.get(RMConstants.SELECT_ITEM));
      Button btnSelectItem =
          this.engine.findElement(Criteria.isButton().withToolTip(additionalParams.get(RMConstants.SELECT_ITEM)))
              .getFirstElement();
      btnSelectItem.click();
      if (additionalParams.get(RMConstants.SELECT_ITEM).equals("Comments")) {
        waitForPageLoaded();
        this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTREQUIREMENT_XPATH, Duration.ofSeconds(10),
            additionalParams.get("Sub Item Selection"));
        Cell cellSubItem = this.engine
            .findElement(Criteria.isCell().withText(additionalParams.get("Sub Item Selection"))).getFirstElement();
        cellSubItem.click();
        this.driverCustom.typeText(RMConstants.RMARTIFACTSPAGE_SUBJECT_TEXTFIELD_XPATH,
            additionalParams.get("Comment Subject"));
        this.driverCustom.switchToFrame(RMConstants.RMARTIFACTSPAGE_COMMENT_IFRAME_XPATH);
        this.driverCustom.typeText(RMConstants.RMARTIFACTSPAGE_COMMENT_TEXTFIELD_XPATH,
            additionalParams.get("Comment Description"));
        WebElement we = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
        we.sendKeys(Keys.TAB, "Create comment");
        this.driverCustom.switchToParentFrame();
        this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(10),
            "Select Users...");
        Button btnSelUser = this.engine.findElement(Criteria.isButton().withText("Select Users...")).getFirstElement();
        btnSelUser.click();
        this.driverCustom.typeText(RMConstants.RMREVIEWSPAGE_SELECTUSERS_SEARCHBOX_XPATH,
            additionalParams.get("User ID"));
        try {
          this.driverCustom.click("//div[@id='addUser_DYNAMIC_VAlUE']", additionalParams.get("User ID"));
          waitForSecs(5);
          this.driverCustom.click("(//button[contains(text(),'OK')])[2]");
          waitForSecs(5);
          this.driverCustom.click("//button[contains(text(),'OK')]");
          waitForSecs(10);
          return;
        }
        catch (Exception e) {
          this.driverCustom.select(RMConstants.RMARTIFACTSPAGE_SELECT_USER_XPATH, 0, SelectTypeEnum.SELECT_BY_INDEX);
          this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(10), "Add & Close");
          Button btnAddAndClose =
              this.engine.findElement(Criteria.isButton().withText("Add & Close")).getFirstElement();
          btnAddAndClose.click();
        }
        // Click OK button
        this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, this.timeInSecs, "OK");
        Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
        btnOK.click();
        Button btnOKCommentDialog = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
        btnOKCommentDialog.click();
      }
    }
  }

  /**
   * @param additionalParams keys: "EDIT_ACTION", "OLD_TEXT", "NEW_TEXT".
   */
  public void editArtifactContent(final Map<String, String> additionalParams) {
    String actionType = additionalParams.get("EDIT_ACTION");
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_EDITTHEARTIFACT_XPATH, Duration.ofSeconds(10));
    Button btnEdit = this.engine.findElement(Criteria.isButton().withText("Edit")).getFirstElement();
    btnEdit.getWebElement().click();
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_DONETHEARTIFACT_XPATH, Duration.ofSeconds(10));

    // Find And Replace
    if (actionType.equals(EditArtifactContentEnums.FIND_AND_REPLACE.toString())) {
      String textToBeReplace = additionalParams.get("OLD_TEXT");
      String textReplace = additionalParams.get("NEW_TEXT");
      waitForSecs(5);
      this.driverCustom.click("//a[@title='Edit']");
      waitForSecs(1);
      this.driverCustom.switchToFrame("//iframe[@class='cke_panel_frame']");
      waitForSecs(1);
      this.driverCustom.click("//a[contains(@title,'Find And Replace')]");
      this.driverCustom.switchToDefaultContent();
      waitForSecs(1);
      Text txbFind = this.engine
          .findElementWithDuration(Criteria.isTextField().hasLabel("Find:"), Duration.ofSeconds(10)).getFirstElement();
      txbFind.setText(textToBeReplace);
      Text txbReplace =
          this.engine.findElementWithDuration(Criteria.isTextField().hasLabel("Replace with:"), Duration.ofSeconds(10))
              .getFirstElement();
      txbReplace.setText(textReplace);
      Button btnReplaceAll =
          this.engine.findElementWithDuration(Criteria.isButton().withText("Replace All"), Duration.ofSeconds(60))
              .getFirstElement();
      btnReplaceAll.click();
      this.driverCustom.getWebDriver().switchTo().alert().accept();
      Button btnClose =
          this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.CLOSE), Duration.ofSeconds(60))
              .getFirstElement();
      btnClose.click();
    }
    else {
      throw new WebAutomationException(actionType + " is not implemented");
    }
    Button btnDone = this.engine.findElement(Criteria.isButton().withText("Done")).getFirstElement();
    btnDone.click();
    waitForSecs(5);
  }

  /**
   * <p>
   * Edit the artifact in the section Overview,Comments,Links using {@link RMArtifactsPage#editArtifact(Map)}. <br>
   * Return the text added in the required section.
   *
   * @param sectionName name of the section such as Overview,Comments,Links etc.
   * @return the text from the selected Section.
   */
  public String getSectionText(final String sectionName) {
    waitForPageLoaded();
    if (!(sectionName.equals(RMConstants.OVERVIEW))) {
      if (this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTON_XPATH, Duration.ofSeconds(10), "Done")) {
        this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTON_XPATH, Duration.ofSeconds(10), "Done");
        Button btn = this.engine.findElement(Criteria.isButton().withText("Done")).getFirstElement();
        btn.click();
      }
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_OVERVIEWTAB_FIELD_XPATH, Duration.ofSeconds(10),
          sectionName);
      this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_OVERVIEWTAB_FIELD_XPATH, sectionName);
    }
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_COMMENT_TEXT_XPATH, Duration.ofSeconds(5));
    return this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_COMMENT_TEXT_XPATH).getText();
  }

  /**
   * <p>
   * Search for the existing artifact using {@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open the searched artifact using {@link RMArtifactsPage#openRMSpecification(String)}. <br>
   * Click on 'More Actions' icon(icon will display with three lines) present right side of the Artifacts page. <br>
   * Click on 'Delete Artifact' option from the 'More Actions' drop down. <br>
   * 'Confirm Deletion' pop up window will display. <br>
   * Click on 'Delete Artifact' button present inside 'Confirm Deletion' pop up.
   *
   * @param artifactID id of the artifact to delete.
   */
  public void deleteArtifact(final String artifactID) {
    Dropdown drdMoreActions = this.engine
        .findElementWithDuration(Criteria.isDropdown().withToolTip("More Actions"), this.timeInSecs).getFirstElement();
    drdMoreActions.selectOptionWithText(RMConstants.DELETE_ARTIFACT);
    waitForSecs(10);
    Button btnDeleteArtifact =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Delete Artifact"), Duration.ofSeconds(10))
            .getFirstElement();
    btnDeleteArtifact.click();
    waitForSecs(35);
  }

  /**
   * <p>
   * Select Artifact Folder from 'Folders' section of the Artifacts page. <br>
   * Click on the selected folder, drop down will be displayed.<br>
   * If the folder is out of view scroll to the element and select the folder. <br>
   * Select the option 'Delete Folder...' for the selected folder. <br>
   * 'Delete Folder' pop up window will be displayed. <br>
   * Enable the check box 'Delete the folder and all its contents, including child folders'. <br>
   * Click on the 'Delete' button from 'Delete Folder' wizard. <br>
   * Wait till the message 'To show artifacts, either select a folder or view, or create a new filter.' is displayed.
   *
   * @author VDY1HC
   * @param artifactFolder folder name to delete. If folder is inside another folder, using syntax:
   *          FOLDER_PARENT>FOLDER_NAME
   * @param isEmptyContent is true if no contents inside folder,If isEmptyContent is false, deletes all the contents
   *          with folder.
   */
  public void deleteArtifactFolder(final String artifactFolder, final String isEmptyContent) {
    String nameOfArtifactFolder = artifactFolder;
    String parentFolder = "";
    if (artifactFolder.contains(">")) {
      parentFolder = artifactFolder.split(">")[0];
      nameOfArtifactFolder = artifactFolder.split(">")[1];
      WebElement btnExpParent =
          this.driverCustom.getPresenceOfWebElement("//span[@class='dijitTreeLabel' and text()='" + parentFolder +
              "']/ancestor::div[@data-dojo-attach-point='rowNode']" + "//span[@data-dojo-attach-point='expandoNode']");
      if (!btnExpParent.getAttribute("class").contains("dijitTreeExpandoOpened")) {
        this.driverCustom.getClickableWebElement(btnExpParent).click();
      }
    }
    // Check for folder visibility before delete
    if (this.driverCustom.isElementPresent(RMConstants.ARTIFACT_FOLDER_XPATH, this.timeInSecs, nameOfArtifactFolder)) {
      try {
        WebElement folderTree =
            this.driverCustom.getFirstVisibleWebElement(RMConstants.ARTIFACT_FOLDER_XPATH, nameOfArtifactFolder);
        JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
        je.executeScript("arguments[0].scrollIntoView(true);", folderTree);
        waitForSecs(1);
        this.driverCustom.getActions().moveToElement(folderTree).build().perform();
        // Focus on top left of webElement
        int xOffset = folderTree.getSize().getWidth() / 2;
        Actions act = new Actions(this.driverCustom.getWebDriver());
        act.moveToElement(folderTree, -xOffset, 0).click().build().perform();
        WebElement folderMenu = this.driverCustom.getFirstVisibleWebElement(RMConstants.MENU_ON_ARTIFACT_FOLDER_XPATH,
            nameOfArtifactFolder);
        this.driverCustom.clickUsingActions(folderMenu);
        waitForSecs(2);
        this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECTREQUIREMENT_XPATH, "Delete Folder...");
      }
      catch (ElementNotInteractableException e) {
        TreeNode treeArtifactFolder = this.engine
            .findFirstElementWithDuration(Criteria.isTreeNode().containText(nameOfArtifactFolder), this.timeInSecs);
        treeArtifactFolder.click();
        waitForSecs(5);
        Dropdown drdMenu = this.engine
            .findFirstElementWithDuration(Criteria.isDropdown().inContainer(treeArtifactFolder), this.timeInSecs);
        drdMenu.selectOptionWithText("Delete Folder...");
      }

      Dialog dlgDeleteFolder = this.engine
          .findElementWithDuration(Criteria.isDialog().withTitle("Delete Folder"), this.timeInSecs).getFirstElement();
      try {
        Checkbox cbxDeleteAll = this.engine.findElementWithDuration(
            Criteria.isCheckbox().withLabel("Delete the folder and all its contents").inContainer(dlgDeleteFolder),
            this.timeInSecs).getFirstElement();
        cbxDeleteAll.click();
      }
      catch (Exception e) {
        // Folder is empty
      }
      Button btnDelete =
          this.engine.findElementWithDuration(Criteria.isButton().withText("Delete").inContainer(dlgDeleteFolder),
              Duration.ofSeconds(60)).getFirstElement();
      btnDelete.click();
      // Wait for completed deleting.
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_CSVSUCCESSMSG_XPATH, Duration.ofSeconds(30));
      this.driverCustom.isElementInvisible(RMConstants.RMARTIFACTSPAGE_CSVSUCCESSMSG_XPATH, Duration.ofSeconds(30));
    }
    else {
      throw new WebAutomationException("Folder - " + nameOfArtifactFolder + " - is NOTFOUND!");
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Click on 'Create' drop down menu present top left side of the page.
   *
   * @param type artifact type,module type,collection type visible under create menu.
   */
  public void clickOnCreateButton(final String type) {
    waitForPageLoaded();
    waitForSecs(5);
    WebElement btnCreate = null;
    if (this.driverCustom.isElementClickable(RMConstants.DNG_ARTIFACTCREATE_CREATEBUTTON_XPATH,
        Duration.ofSeconds(30))) {
      // Create button in Artifacts page
      btnCreate = this.driverCustom.getWebElement(RMConstants.DNG_ARTIFACTCREATE_CREATEBUTTON_XPATH);
    }
    else {
      // Create button in Module page
      btnCreate = this.driverCustom.getWebElement(RMConstants.DNG_MODULEPAGE_CREATEBUTTON_XPATH);
    }
    btnCreate.click();
    String menuOptionXpath = "(//div[contains(@class,'jazz-ui-MenuPopup')]//td[text()='%s'])[last()]";
    this.driverCustom.isElementVisible(menuOptionXpath, Duration.ofSeconds(30));
    String menuOption = String.format(menuOptionXpath, type);
    this.driverCustom.getPresenceOfWebElement(menuOption).click();
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on the artifact type link 'All','Modules','Collections.
   *
   * @param type of the Artifact like All, Modules, Collections etc...
   */
  public void clickOnArtifactTypes(final String type) {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_LINK_XPATH, Duration.ofSeconds(30), type);
    try {
      Link lnkArtifactType = this.engine.findFirstElement(Criteria.isLink().withText(type));
      lnkArtifactType.click();
      LOGGER.LOG.info(RMConstants.CLICKED_ON_TYPE + type + "' type artifact.");
    }
    catch (Exception e) {
      throw new WebAutomationException(
          type + ": type artifact not found,Please provide valid input.\n" + "or\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box. <br>
   * Click on the drop down menu contains the row for the searched artifact. <br>
   * Select the option 'Export Artifact...' from the displayed drop down menu. <br>
   * 'CSV' file format for export is selected by default. <br>
   * Click on 'OK' button in 'Export' dialog.
   * <p>
   * NOTE:- Currently this method supporting only exporting of 'CSV' formatted file.Refactor the method to export for
   * other file format.
   *
   * @param artifactID - id of the Artifact to be used for Export. <br>
   * @param fileType - type of file to be exported
   */
  public void exportArtifact(final String artifactID, final String fileType) {
    waitForPageLoaded();
    // Search for Artifact to export
    Text txtSearchArtifact = this.engine.findFirstElement(
        Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID));
    txtSearchArtifact.setText(artifactID + Keys.ENTER);
    // Wait for artifact completedly load
    this.driverCustom.getPresenceOfWebElement(
        "//div[@class='status-message' and contains(@style,'display: none;') and text()='Loading artifacts...']");
    waitForSecs(10);
    // Export artifact
    Row rowArtifact = this.engine.findElementWithDuration(Criteria.isRow().withText(artifactID), Duration.ofSeconds(60))
        .getFirstElement();
    Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().inContainer(rowArtifact));
    drdMenu.selectOptionWithText("Export Artifact...");
    RadioButton rbtnFileType = this.engine
        .findElementWithDuration(Criteria.isRadioButton().withText(fileType), Duration.ofSeconds(60)).getFirstElement();
    rbtnFileType.click();
    Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOK.click();
    waitForSecs(5);
  }

  /**
   * <p>
   * Uploads file in Requirements page. <br>
   * Code used for Robot class to controls the upload event of a file.
   *
   * @param fileLocation location of file to be exported.
   * @throws AWTException if file can't be uploaded.
   */
  public void uploadFile(final String fileLocation) throws AWTException {
    waitForPageLoaded();
    setClipboardData(fileLocation);
    Robot robot = new Robot();
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
  }


  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Click on 'Create' drop down menu using {@link RMArtifactsPage#clickOnCreateButton(String)}. <br>
   * Click on the option 'Import Artifact...' from 'Create' drop down menu. <br>
   * 'Import' window is displayed. <br>
   * Select the option 'Import requirements from a migration package' radio button from 'Import' dialog. <br>
   * Click on 'Next' button,'Select Package File' title page is displayed.<br>
   * Select the file type migiz from provided file path to upload and Click on'Upload' button,'The package file has been
   * uploaded' successful message is displayed.<br>
   * Click on 'Next' button,'Specify Migrations Option' title page is displayed.<br>
   * Click on 'Browse...' button,'Select a folder' pop up window is displayed.<br>
   * Select the appropriate folder and click on 'OK' button.<br>
   * Provide necessary details for 'Attribute for imported artifact','Attribute for imported modules' under 'Select
   * import option' and click on 'Next' button.<br>
   * Verify the successful message and click on 'Close' button.
   *
   * @param additionalParams stores key values for
   *          'FILE_PATH','IMPORTED_FOLDER','ARTIFACT_ATTRIBUTE','MODULE_ATTRIBUTE'.
   * @return the message file is imported.
   * @author YNT2HC
   */
  public String importArtifactsWithMigizFileExtension(final Map<String, String> additionalParams) {

    // waitForPageLoaded();
    String importFileAbsolutePath = Paths
        .get(RMConstants.IMPORT_FILE_LOCATION + additionalParams.get(RMConstants.FILETYPE)).toAbsolutePath().toString();
    additionalParams.put(RMConstants.FILETYPE, importFileAbsolutePath);
    String st = null;
    waitForSecs(5);
    Dialog dlgImport =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Import"), this.timeInSecs).getFirstElement();

    // Check "Import requirements from a migration package" radio button
    RadioButton rdoImport =
        this.engine
            .findElementWithDuration(
                Criteria.isRadioButton().withText(RMConstants.MIGRATION_IMPORT).inContainer(dlgImport), this.timeInSecs)
            .getFirstElement();
    rdoImport.click();
    waitForSecs(12);
    // Click on Next button
    Button btnNext1 = this.engine.findElement(Criteria.isButton().withText(RMConstants.NEXT)).getFirstElement();
    btnNext1.click();
    waitForSecs(10);
    // Click on Upload button
    performUpload(additionalParams);
    Button btnUpload = this.engine.findElement(Criteria.isButton().withText("Upload")).getFirstElement();
    btnUpload.click();
    waitForSecs(12);
    // Click on Next button
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.NEXT);
    Button btnNext2 = this.engine
        .findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), this.timeInSecs).getFirstElement();
    btnNext2.click();
    waitForSecs(10);
    // Browse import folder
    Button btnBrowse = this.engine.findElementWithDuration(Criteria.isButton().withText("Browse..."), this.timeInSecs)
        .getFirstElement();
    btnBrowse.click();
    waitForSecs(10);
    Dialog dlgSelectFolder = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a folder"), this.timeInSecs).getFirstElement();
    TreeNode treeFolder = this.engine.findElementWithDuration(
        Criteria.isTreeNode().withText(additionalParams.get("IMPORTED_FOLDER")).inContainer(dlgSelectFolder),
        this.timeInSecs).getFirstElement();
    treeFolder.scrollToElement();
    treeFolder.click();
    waitForSecs(10);
    Button btnOk = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOk.click();
    waitForSecs(10);
    // Input imported artifact attributes
    Text txtImportedArtifact = this.engine
        .findElementWithDuration(Criteria.isTextField().hasLabel("Attribute for imported artifacts:"), this.timeInSecs)
        .getFirstElement();
    txtImportedArtifact.setText(additionalParams.get("ARTIFACT_ATTRIBUTE"));
    waitForSecs(5);
    Text txtImportedModule =
        this.engine.findElement(Criteria.isTextField().hasLabel("Attribute for imported modules:")).getFirstElement();
    txtImportedModule.setText(additionalParams.get("MODULE_ATTRIBUTE"));
    waitForSecs(10);
    // Click on Next button
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTFINISH_BUTTON_XPATH,
        Duration.ofSeconds(10))) {
      Button btnNext3 =
          this.engine.findElement(Criteria.isButton().withText(RMConstants.FINISHBUTTON)).getFirstElement();
      btnNext3.click();
    }
    else {
      Button btnNext3 = this.engine.findElement(Criteria.isButton().withText(RMConstants.NEXT)).getFirstElement();
      btnNext3.click();
    }
    waitForSecs(10);

    // Click Close button
    this.driverCustom.isElementPresent(RMConstants.JAZZPAGE_LINK_XPATH, Duration.ofSeconds(300),
        RMConstants.SHOWREPORT);
    st = this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_REQIFSUCCESSMSG_XPATH);
    Button btnClose = this.engine.findElement(Criteria.isButton().withText(RMConstants.CLOSE)).getFirstElement();
    btnClose.click();
    waitForSecs(10);
    return RMConstants.MIGRATION_IMPORT + " :: " + st;

  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Click on 'Create' drop down menu using {@link RMArtifactsPage#clickOnCreateButton(String)}. <br>
   * Click on the option 'Import Artifact...' from 'Create' drop down menu. <br>
   * 'Import' window is displayed.<br>
   * Select what to import in 'Import' window, select type as text document/csv/reqif/migration/rich text etc..
   * <p>
   * If the import artifact type 'Import requirements from within a text document' is selected,Wait for the 'Next'
   * buttom to be enabled and click on 'Next' button, 'Select text document' section is displayed. <br>
   * Click on 'Add Files' and choose appropriate file format with .doc, .docx, .rtf, .zip format and Click on 'Next'
   * button, 'Identify requirements' section is displayed. <br>
   * Choose appropriate 'HEADING_DROP_DOWN_VALUE' for 'For each heading, create artifact type:' attribute. <br>
   * Choose appropriate 'IMAGE_DROP_DOWN_VALUE' for 'For each image, create artifact type:' attribute. <br>
   * Choose appropriate 'OTHER_TEXT_DROP_DOWN_VALUE' for 'For all other text (not matching criterion above), create
   * artifact type:' attribute. <br>
   * Click on 'Next' button, 'Extract Requirements' section is displayed. <br>
   * Click on 'Finish' button, Wait till 'Success' message is displayed.<br>
   * Click on 'Close' button.
   * <p>
   * If the import artifact type 'Import requirements from a CSV file or spreadsheet' is selected,Wait for the 'Next'
   * buttom to be enabled and click on 'Next' button, 'Import Requirements' section is displayed.<br>
   * Click on 'Choose File', file selection window is displayed. <br>
   * Choose appropriate file format with .csv,.xlsx, .xls format.<br>
   * Click on 'Finish' button.<br>
   * Click on 'Show details' link, get the detailed message.
   * <p>
   * If the import artifact type 'Import requirements from a ReqIF file' is selected.<br>
   * Click on 'Next' button,'Select Package File' section is displayed.<br>
   * Choose appropriate file with .xml, .reqif, .zip, .reqifz, and .gz extension from choose file window and click on
   * 'Upload' button, verify 'The package file (file name) has been uploaded. Click Next to continue the import.'
   * message is displayed.<br>
   * Click on 'Next' button, 'Specify Import Options' section is displayed.<br>
   * Click on 'Next' button,'Specify Attribute Options' section is displayed.<br>
   * Click on 'Select All Attributes' button,all the attributes is selected.<br>
   * Click on 'Next>' button,'Import Requirements' section is displayed.<br>
   * Wait until 'Importing Requirement' is displayed with 100% bar.<br>
   * Check 'Show Report' link is present, get the successful message 'The import is complete.'.<br>
   * Click on 'Close' button.
   * <p>
   * If the import artifact type 'Import requirements from a migration package' is selected.<br>
   * Click on 'Next' button,'Select Package File' title page is displayed.<br>
   * Select the file type migiz from provided file path to upload and Click on'Upload' button,'The package file has been
   * uploaded' successful message is displayed.<br>
   * Click on 'Next' button,'Specify Migrations Option' title page is displayed.<br>
   * Click on 'Browse...' button,'Select a folder' pop up window is displayed.<br>
   * Select the appropriate folder and click on 'OK' button.<br>
   * Provide necessary details for 'Attribute for imported artifact','Attribute for imported modules' under 'Select
   * import option' and click on 'Next' button.<br>
   * Verify the successful message and click on 'Close' button.
   * <p>
   * If the import artifact type 'Import requirements from a ReqIF file in this component or project' is selected.<br>
   * Click on 'Next>' button, 'Import Requirements' section is displayed.<br>
   * Click on 'Next>' button.<br>
   * Verify the success message and Click on 'Close' button.
   * <p>
   * If the import artifact type 'Import a text document and convert to a rich text artifact' is selected.<br>
   * Click on 'Next>' button 'Select text Document' section is displayed.<br>
   * Choose appropriate file with docx, doc, rtf, or odt extension.<br>
   * Click on 'Finish' button.<br>
   * Click on 'Show details' link, Verify the success message.
   *
   * @param additionalParams stores key values for
   *          'HEADING_DROP_DOWN_VALUE','IMAGE_DROP_DOWN_VALUE','OTHER_TEXT_DROP_DOWN_VALUE' etc...
   * @return the message file is imported.
   */
  public String importArtifactTypes(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible("//a[@class='selected' and @title='Import']", Duration.ofSeconds(10))) {
      Dialog dlgImport = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Import"), this.timeInSecs)
          .getFirstElement();
      RadioButton rdoImport = this.engine.findElementWithDuration(
          Criteria.isRadioButton().withText(additionalParams.get(RMConstants.IMPORTTYPE)).inContainer(dlgImport),
          this.timeInSecs).getFirstElement();
      rdoImport.click();
      // Click on Next button
      Button btnNext = this.engine
          .findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), this.timeInSecs).getFirstElement();
      btnNext.click();
    }
    String st = null;
    if (additionalParams.get(RMConstants.IMPORTTYPE).equals(RMConstants.TEXTDOCUMENT) ||
        additionalParams.get(RMConstants.IMPORTTYPE).equals(RMConstants.CSVIMPORT) ||
        additionalParams.get(RMConstants.IMPORTTYPE).equals(RMConstants.REQIF_IMPORT) ||
        additionalParams.get(RMConstants.IMPORTTYPE).equals(RMConstants.MIGRATION_IMPORT) ||
        additionalParams.get(RMConstants.IMPORTTYPE).equals(RMConstants.RICH_TEXT_IMPORT)) {
      performUpload(additionalParams);
      if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_ERRORMSG_XPATH, Duration.ofSeconds(3))) {
        return this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_ERRORMSG_XPATH);
      }
    }
    switch (additionalParams.get(RMConstants.IMPORTTYPE)) {
      case RMConstants.TEXTDOCUMENT:
        st = importTextDocument(additionalParams);
        break;
      case RMConstants.CSVIMPORT:
        st = importCSV();
        break;
      case RMConstants.REQIF_IMPORT:
        st = importReqIF();
        break;
      case RMConstants.MIGRATION_IMPORT:
        st = importMigrationFile();
        break;
      case "Import requirements from a ReqIF file in this component or project":
        this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECTCOMPONENTORPROJECTREQ1_XPATH);
        this.driverCustom.getClickableWebElement(
            this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_SELECTNEXT_BUTTON_XPATH));
        this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECTNEXT_BUTTON_XPATH);
        this.driverCustom.getClickableWebElement(
            this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_SELECTNEXT_BUTTON_XPATH));
        this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECTNEXT_BUTTON_XPATH);
        this.driverCustom.isElementPresent(RMConstants.JAZZPAGE_LINK_XPATH, Duration.ofSeconds(20),
            RMConstants.SHOWREPORT);
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_REQIFSUCCESSMSG_XPATH);
        WebElement preferredLinkTypesElement =
            this.driverCustom.getWebDriver().findElement(By.xpath(RMConstants.RMARTIFACTSPAGE_REQIFSUCCESSMSG_XPATH));
        st = preferredLinkTypesElement.getText();
        this.driverCustom.click(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.CLOSEBUTTON);
        break;
      case RMConstants.RICH_TEXT_IMPORT:
        this.driverCustom.getClickableWebElement(
            this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_SELECTFINISH_BUTTON_XPATH));
        this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECTFINISH_BUTTON_XPATH);
        while (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTFINISH_BUTTON_XPATH,
            Duration.ofSeconds(5), RMConstants.FINISHBUTTON)) {
          this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(10), "show details");
        }
        this.driverCustom.click(RMConstants.JAZZPAGE_LINKS_XPATH, RMConstants.SHOW_DETAILS);

        WebElement preferredLinkTypesElement1 =
            this.driverCustom.getWebDriver().findElement(By.xpath(RMConstants.RMARTIFACTSPAGE_CSVSUCCESSMSG_XPATH));
        String at = preferredLinkTypesElement1.getText();
        st = at + this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_DETAILSMSG_XPATH);
        break;
      default:
        throw new WebAutomationException(
            "Option: " + additionalParams.get(RMConstants.IMPORTTYPE) + " is NOT implemented for automation");
    }
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(3));
    return additionalParams.get(RMConstants.IMPORTTYPE) + " :: " + st;
  }

  private void setDropdownValue(final String dropdownName, final String dropdownValue) {
    Actions act = new Actions(this.driverCustom.getWebDriver());
    WebElement element = this.driverCustom.getWebElement(RMConstants.DROPDOWN_FOR_HEADING_XPATH, dropdownName);
    ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].scrollIntoView();", element);
    act.moveToElement(element);
    act.click().build().perform();
    String searchString = "//div[.=\"" + dropdownValue + "\"]";
    this.driverCustom.isElementVisible(searchString, Duration.ofSeconds(3));
    List<WebElement> divDowns = this.driverCustom.getWebElements(searchString);
    for (WebElement we : divDowns) {
      if (this.driverCustom.isElementVisible(we, Duration.ofSeconds(3))) {
        we.click();
        break;
      }
    }
  }

  /**
   * Perform Upload action in {@link RMArtifactsPage#importArtifactTypes(Map)}
   *
   * @param additionalParams - with keys: FILE_PATH - file path IMPORT_TYPE - type of import
   */
  private void performUpload(final Map<String, String> additionalParams) {
    String filePath = additionalParams.get(RMConstants.FILETYPE);
    LOGGER.LOG.info("Selected the file to import is : " + filePath);
    if (!filePath.contains("\\")) {
      filePath = Paths.get(RMConstants.IMPORT_FILE_LOCATION + filePath).toAbsolutePath().toString();
    }
    if (this.driverCustom.isElementVisible("//button[text()='Upload']", Duration.ofSeconds(3))) {
      if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_UPLOADBUTTON_XPATH, Duration.ofSeconds(3))) {
        this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_UPLOADBUTTON_XPATH).sendKeys(filePath);
      }
      else {
        JavascriptExecutor jse = (JavascriptExecutor) this.driverCustom.getWebDriver();
        WebElement inputUpload =
            this.driverCustom.getWebDriver().findElement(By.xpath(RMConstants.RMARTIFACTSPAGE_UPLOADBUTTON_XPATH));
        jse.executeScript("arguments[0].style='display: block;'", inputUpload);
        inputUpload = this.driverCustom.getClickableWebElement(inputUpload);
        inputUpload.sendKeys(filePath);
      }
    }
    else if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_CSVFILE_UPLOADBUTTON_XPATH,
        Duration.ofSeconds(1))) {
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_CSVFILE_UPLOADBUTTON_XPATH)
          .sendKeys(additionalParams.get(RMConstants.FILETYPE));
    }
    else if (additionalParams.get(RMConstants.IMPORTTYPE).equals(RMConstants.TEXTDOCUMENT)) {
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_UPLOADBUTTON_FOR_DOC_XPATH)
          .sendKeys(additionalParams.get(RMConstants.FILETYPE));
    }

  }

  /**
   * @param selectWhatToImport
   * @return boolean
   * 
   */
  public boolean selectRadioButtonWhatToImport(final String selectWhatToImport) {
    waitForSecs(5);
    Dialog dlgImport =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Import"), this.timeInSecs).getFirstElement();

    // Check "Import requirements from a ReqIF file" radio button
    if (selectWhatToImport.equalsIgnoreCase(RMConstants.REQIF_IMPORT)) {
      RadioButton rdoImport =
          this.engine
              .findElementWithDuration(
                  Criteria.isRadioButton().withText(RMConstants.REQIF_IMPORT).inContainer(dlgImport), this.timeInSecs)
              .getFirstElement();
      rdoImport.click();
      LOGGER.LOG.info("Check '" + RMConstants.REQIF_IMPORT + "' radio button");
      waitForSecs(5);

    }
    return true;

  }


  /**
   * @param additionalParams
   * @return
   */
  public String selectTheFileToImport(final Map<String, String> additionalParams) {

    Dialog dlgImport =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Import"), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info("Import dialog is opened successfully.");
    String importFileAbsolutePath = Paths
        .get(RMConstants.IMPORT_FILE_LOCATION + additionalParams.get(RMConstants.FILETYPE)).toAbsolutePath().toString();
    additionalParams.put(RMConstants.FILETYPE, importFileAbsolutePath);

    waitForSecs(2);

    performUpload(additionalParams);

    LOGGER.LOG.info(additionalParams + " loaded successfully.");
    if (this.driverCustom.isElementClickable("//button[text()='Upload']", Duration.ofSeconds(5))) {

      Button btnUpload = this.engine.findElement(Criteria.isButton().withText("Upload")).getFirstElement();
      btnUpload.click();
      LOGGER.LOG.info("Clicked on 'Upload' button");
      waitForSecs(5);
    }
    return importFileAbsolutePath;

  }

  /**
   * @author UUM4KOR
   *         <p>
   *         This method used after selecting Remove artifact
   *         {@link #RMArtifactsPage.openContextMenuForSelectedArtifact}
   * @return warning message
   */
  public String getValidationMessage() {
    waitForSecs(5);
    // String validationMessage = this.driverCustom.getPresenceOfWebElement("//div[@class='messageSummary']").getText();
    // LOGGER.LOG.info("Message displayed as : '" + validationMessage + "'");

    if (this.driverCustom.isElementPresent(RMConstants.JAZZPAGE_LINK_XPATH, Duration.ofSeconds(60),
        RMConstants.SHOWREPORT)) {
      String ShowReport = this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_REQIFSUCCESSMSG_XPATH);

      LOGGER.LOG.info("validated'" + ShowReport + "' text is displayed.");
      waitForSecs(15);
      this.driverCustom.getPresenceOfWebElement("//div[@role='progressbar']//div[text()='100%']");
      LOGGER.LOG.info("progressbar '100%' done in 'Importing Requirements'");
      waitForSecs(5);
      return ShowReport;
    }
    waitForSecs(5);
    String validationMessage = this.driverCustom.getPresenceOfWebElement("//div[@class='messageSummary']").getText();
    LOGGER.LOG.info("Message displayed as : '" + validationMessage + "'");
    return validationMessage;

  }

  /**
   * @param additionalParams
   */
  public String browseImportFolder(final Map<String, String> additionalParams) {
    waitForSecs(5);
    // Browse import folder
    Button btnBrowse = this.engine.findElementWithDuration(Criteria.isButton().withText("Browse..."), this.timeInSecs)
        .getFirstElement();
    btnBrowse.click();
    LOGGER.LOG.info("clicked on 'Browse...' to lode import folder ");
    waitForSecs(5);
    Dialog dlgSelectFolder = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a folder"), this.timeInSecs).getFirstElement();
    TreeNode treeFolder = this.engine.findElementWithDuration(
        Criteria.isTreeNode().withText(additionalParams.get("IMPORTED_FOLDER")).inContainer(dlgSelectFolder),
        this.timeInSecs).getFirstElement();
    treeFolder.scrollToElement();
    treeFolder.click();
    waitForSecs(5);
    LOGGER.LOG.info(additionalParams.get("IMPORTED_FOLDER") + " folder selected in 'Select a folder' dialogue");
    // Button btnOk = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    // btnOk.click();

    waitForSecs(5);
    // LOGGER.LOG.info("clicked on 'OK' button");
    return additionalParams.get("IMPORTED_FOLDER");
  }


  /**
   * @param additionalParams
   */
  public boolean selectCheckBox(final Map<String, String> additionalParams) {
    Checkbox checkbxValue = null;
    checkbxValue = this.engine.findElement(Criteria.isCheckbox().withLabel(additionalParams.get("CHECKBOX_LABEL")))
        .getFirstElement();
    if (additionalParams.get("CHECKBOX_LABEL").contains(checkbxValue.getText().toString())) {
      try {
        // checkbxValue =
        // this.engine.findElement(Criteria.isCheckbox().withLabel(additionalParams.get("checkBoxLabel"))).getFirstElement();
        checkbxValue.click();
        LOGGER.LOG.info(RMConstants.CLICKED_ON + " '" + additionalParams.get("CHECKBOX_LABEL") + "' check box.");
      }
      catch (Exception e) {
        LOGGER.LOG.error(e.getMessage());
        LOGGER.LOG.info("Not selected '" + additionalParams.get("CHECKBOX_LABEL") + "' check box.");
      }
    }
    return true;

  }


  /**
   * @param additionalParams
   */
  public String browseImportModule(final Map<String, String> additionalParams) {

    String BrowseModule = null;
//Browse import Module
    this.driverCustom
        .getPresenceOfWebElement("//button[@dojoattachpoint='_selectDefaultModuleButton' and text()='Browse...']")
        .click();
    waitForSecs(5);
    Dialog dlgSelectModule = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a module"), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info("'Select a module' dialogue opened successfully");
    // filterArtifactByTextOrId("moduleID");
    Text txtSearchVerify = this.engine.findElementWithDuration(
        Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID), this.timeInSecs)
        .getFirstElement();
    txtSearchVerify.setText(additionalParams.get("MODULE_ID") + Keys.ENTER);
    waitForSecs(5);
    LOGGER.LOG.info(
        "Searched " + additionalParams.get("MODULE_ID") + " in 'Type To Filter' textbox in 'Select a module' Dialog .");
    this.driverCustom.isElementVisible("//td[@class='results-area']//a[contains(text(),'DYNAMIC_VAlUE')]",
        Duration.ofSeconds(10), additionalParams.get("MODULE_ID"));
//td[@class='results-area']//a[contains(text(),'DYNAMIC_VAlUE')]

    try {
      // Click on Link
      String xpathArtifactLink =
          "//a[contains(text(),'" + additionalParams.get("MODULE_ID") + "')]/following-sibling::span";
      WebElement lnkArtifacts = this.driverCustom.getWebElement(xpathArtifactLink, additionalParams.get("MODULE_ID"));
      lnkArtifacts.click();
      LOGGER.LOG.info("Clicked on Link with ID " + additionalParams.get("MODULE_ID"));

      // Click on OK button
      // Button btn = this.engine.findFirstElementWithDuration(Criteria.isButton().withText(RMConstants.OK),
      // this.timeInSecs);
      // btn.click();
      // waitForSecs(5);
      // LOGGER.LOG.info(RMConstants.CLICK_ON_OK_BUTTON);
    }
    catch (Exception e) {
      this.driverCustom.getPresenceOfWebElement("//a[@style='display: block; color: rgb(204, 204, 204);']");
      LOGGER.LOG.info(additionalParams.get("MODULE_ID") + " is  disabled. \nNot able to select");
      // return "disabled";
    }
    return additionalParams.get("MODULE_ID");


  }

  /**
   * @param additionalParams
   * @return
   */
  public void SelectRadioButtonWhatToImport1(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    // if (this.driverCustom.isElementVisible("//a[@class='selected' and @title='Import']", 10)) {
    // Dialog dlgImport = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Import"), this.timeInSecs)
    // .getFirstElement();
    // RadioButton rdoImport = this.engine.findElementWithDuration(
    // Criteria.isRadioButton().withText(additionalParams.get(RMConstants.IMPORTTYPE)).inContainer(dlgImport),
    // this.timeInSecs).getFirstElement();
    // rdoImport.click();
    // LOGGER.LOG.info(additionalParams.get(RMConstants.IMPORTTYPE)+" Radio button selected");

    // }

    String importFileAbsolutePath = Paths
        .get(RMConstants.IMPORT_FILE_LOCATION + additionalParams.get(RMConstants.FILETYPE)).toAbsolutePath().toString();
    additionalParams.put(RMConstants.FILETYPE, importFileAbsolutePath);
    String st = null;
    waitForSecs(5);
    Dialog dlgImport =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Import"), this.timeInSecs).getFirstElement();

    // Check "Import requirements from a migration package" radio button
    RadioButton rdoImport = this.engine
        .findElementWithDuration(Criteria.isRadioButton().withText(RMConstants.REQIF_IMPORT).inContainer(dlgImport),
            this.timeInSecs)
        .getFirstElement();
    rdoImport.click();
    waitForSecs(12);
    // Click on Next button
    Button btnNext1 = this.engine.findElement(Criteria.isButton().withText(RMConstants.NEXT)).getFirstElement();
    btnNext1.click();
    waitForSecs(10);
    // Click on Upload button
    performUpload(additionalParams);
    Button btnUpload = this.engine.findElement(Criteria.isButton().withText("Upload")).getFirstElement();
    btnUpload.click();
    waitForSecs(12);
    String message = this.driverCustom.getPresenceOfWebElement("//div[@class='messageSummary']").getText();
    System.out.println(message);


    // Click on Next button
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.NEXT);
    Button btnNext2 = this.engine
        .findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), this.timeInSecs).getFirstElement();
    btnNext2.click();
    waitForSecs(10);


    // Browse import folder
    Button btnBrowse = this.engine.findElementWithDuration(Criteria.isButton().withText("Browse..."), this.timeInSecs)
        .getFirstElement();
    btnBrowse.click();
    waitForSecs(10);
    Dialog dlgSelectFolder = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a folder"), this.timeInSecs).getFirstElement();
    TreeNode treeFolder = this.engine.findElementWithDuration(
        Criteria.isTreeNode().withText(additionalParams.get("IMPORTED_FOLDER")).inContainer(dlgSelectFolder),
        this.timeInSecs).getFirstElement();
    treeFolder.scrollToElement();
    treeFolder.click();
    waitForSecs(10);
    Button btnOk = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOk.click();
    waitForSecs(10);


    Checkbox checkbxValue = null;
    String BrowseFolder = null;
    String BrowseModule = null;
    try {
      checkbxValue = this.engine.findElement(Criteria.isCheckbox().withLabel(additionalParams.get("checkBoxLabel")))
          .getFirstElement();
      checkbxValue.click();
      LOGGER.LOG.info(RMConstants.CLICKED_ON + additionalParams.get("checkBoxLabel") + " check box.");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }


// Browse import folder
    this.driverCustom
        .getPresenceOfWebElement("//button[@dojoattachpoint='_selectDefaultModuleButton' and text()='Browse...']")
        .click();
    waitForSecs(10);
    Dialog dlgSelectModule = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a module"), this.timeInSecs).getFirstElement();
    // filterArtifactByTextOrId("moduleID");
    Text txtSearchVerify = this.engine.findElementWithDuration(
        Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID), this.timeInSecs)
        .getFirstElement();
    txtSearchVerify.setText(additionalParams.get("MODULE_ID") + Keys.ENTER);
    waitForSecs(5);
    LOGGER.LOG.info(
        "Searched " + additionalParams.get("MODULE_ID") + " in 'Type To Filter' textbox in 'Select a module' Dialog .");
    this.driverCustom.isElementVisible("//td[@class='results-area']//a[contains(text(),'DYNAMIC_VAlUE')]",
        Duration.ofSeconds(10), additionalParams.get("MODULE_ID"));
    // td[@class='results-area']//a[contains(text(),'DYNAMIC_VAlUE')]

    try {
      // Click on Link
      String xpathArtifactLink =
          "//a[contains(text(),'" + additionalParams.get("MODULE_ID") + "')]/following-sibling::span";
      WebElement lnkArtifacts = this.driverCustom.getWebElement(xpathArtifactLink, additionalParams.get("MODULE_ID"));
      lnkArtifacts.click();
      LOGGER.LOG.info("Clicked on Link with ID " + additionalParams.get("MODULE_ID"));

      // Click on OK button
      Button btn =
          this.engine.findFirstElementWithDuration(Criteria.isButton().withText(RMConstants.OK), this.timeInSecs);
      btn.click();
      waitForSecs(5);
      LOGGER.LOG.info(RMConstants.CLICK_ON_OK_BUTTON);
    }
    catch (Exception e) {
      this.driverCustom.getPresenceOfWebElement("//a[@style='display: block; color: rgb(204, 204, 204);']");
      LOGGER.LOG.info(additionalParams.get("MODULE_ID") + " is  disabled. \nNot able to select");
      // return "disabled";
    }


// Click on Next button
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.NEXT);
    Button btnNext3 = this.engine
        .findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), this.timeInSecs).getFirstElement();
    btnNext3.click();


    System.out.println("Clicked on 'Next' in  'Specify Import Options'");
    waitForSecs(10);


    Button btnNext4 =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Select All Attributes"), this.timeInSecs)
            .getFirstElement();
    btnNext4.click();
    System.out.println("Clicked on 'Select All Attributes' in  'Specify Attribute Options'");
    waitForSecs(10);
    Button btnNext5 =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Select Existing Attributes"), this.timeInSecs)
            .getFirstElement();
    btnNext5.click();
    System.out.println("Clicked on 'Select Existing Attributes' in  'Specify Attribute Options'");

    // Click on Finish button
    // Click on Next button
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTFINISH_BUTTON_XPATH,
        Duration.ofSeconds(10))) {
      Button btnNext6 =
          this.engine.findElement(Criteria.isButton().withText(RMConstants.FINISHBUTTON)).getFirstElement();
      btnNext6.click();
    }
    else {
      Button btnNext7 = this.engine.findElement(Criteria.isButton().withText(RMConstants.NEXT)).getFirstElement();
      btnNext7.click();
    }
    System.out.println("Clicked on '" + RMConstants.FINISH + "' in  'Specify Import Options'");
    waitForSecs(10);

    String message1 = this.driverCustom.getPresenceOfWebElement("//div[@class='messageSummary']").getText();
    System.out.println(message1);

// Click Close button
    this.driverCustom.isElementPresent(RMConstants.JAZZPAGE_LINK_XPATH, Duration.ofSeconds(300),
        RMConstants.SHOWREPORT);
    st = this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_REQIFSUCCESSMSG_XPATH);
    System.out.println(st);
    waitForSecs(10);
    this.driverCustom.getPresenceOfWebElement("//div[@role='progressbar']//div[text()='100%']");
    System.out.println("progressbar '100%' done in 'Importing Requirements'");
    waitForSecs(10);
    // Click Close button
    Button btnClose = this.engine.findElement(Criteria.isButton().withText(RMConstants.CLOSE)).getFirstElement();
    btnClose.click();
    waitForSecs(10);

  }

  /**
   * @param additionalParams
   */
  public void selectPackageFile(final Map<String, String> additionalParams) {
    performUpload(additionalParams);
    Button btnUpload = this.engine.findElement(Criteria.isButton().withText("Upload")).getFirstElement();
    btnUpload.click();
  }

  /**
   * @param additionalParams
   */
  public void specifyImportOptions(final Map<String, String> additionalParams) {
    Checkbox checkbxValue = null;
    String BrowseFolder = null;
    String BrowseModule = null;
    try {
      checkbxValue = this.engine.findElement(Criteria.isCheckbox().withLabel(additionalParams.get("attributeValue")))
          .getFirstElement();
      checkbxValue.click();
      LOGGER.LOG.info(RMConstants.CLICKED_ON + additionalParams.get("attributeValue") + " check box.");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }


  }

  /**
   * Import with IMPORT_TYPE = Import requirements from a ReqIF file Using in
   * {@link RMArtifactsPage#importArtifactTypes(Map)}
   *
   * @return message
   */
  private String importReqIF() {
    String msgText = null;
    // Click upload label
    Button upldBtn =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Upload"), this.timeInSecs).getFirstElement();
    upldBtn.click();
    Button nextBtn = this.engine
        .findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), this.timeInSecs).getFirstElement();
    nextBtn.waitUntilElementEnabled(3);
    nextBtn.click();
    nextBtn = this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), this.timeInSecs)
        .getFirstElement();
    nextBtn.click();
    Button allAttributeBtn =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Select All Attributes"), this.timeInSecs)
            .getFirstElement();
    allAttributeBtn.click();
    try {
      // V7.0 this step will click "Finish"
      nextBtn =
          this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.FINISHBUTTON), this.timeInSecs)
              .getFirstElement();
      nextBtn.click();
    }
    catch (Exception e) {
      nextBtn = this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), this.timeInSecs)
          .getFirstElement();
      nextBtn.click();
    }
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_TEXTDOCUMENTUPLOADED_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() * 10)));
    this.driverCustom.isElementPresent(RMConstants.JAZZPAGE_LINK_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() * 10)), RMConstants.SHOWREPORT);
    WebElement msg =
        this.driverCustom.getPresenceOfWebElement(By.xpath(RMConstants.RMARTIFACTSPAGE_REQIFSUCCESSMSG_XPATH));
    msgText = msg.getText();
    Button closeBtn = this.engine
        .findElementWithDuration(Criteria.isButton().withText(RMConstants.CLOSE), this.timeInSecs).getFirstElement();
    closeBtn.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    return msgText;
  }

  /**
   * Import with IMPORT_TYPE = "Import requirements from within a text document" Using in
   * {@link RMArtifactsPage#importArtifactTypes(Map)}
   *
   * @return message
   */
  private String importTextDocument(final Map<String, String> additionalParams) {
    Button nextBtn = this.engine.findElement(Criteria.isButton().withText(RMConstants.NEXT)).getFirstElement();
    nextBtn.waitUntilElementEnabled(10);
    nextBtn.click();
    waitForSecs(3);
    setDropdownValue("For each heading, create artifact type:", additionalParams.get("HEADING_DROP_DOWN_VALUE"));
    setDropdownValue("For each image, create artifact type:", additionalParams.get("IMAGE_DROP_DOWN_VALUE"));
    setDropdownValue("For all other text (not matching criterion above), create artifact type:",
        additionalParams.get("OTHER_TEXT_DROP_DOWN_VALUE"));
    this.driverCustom.getClickableWebElement(
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_SELECT_NEXTBUTTON_XPATH));
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECT_NEXTBUTTON_XPATH);
    this.driverCustom.getClickableWebElement(
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_SELECTFINISH_BUTTON_XPATH));
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECTFINISH_BUTTON_XPATH);
    waitForSecs(4);
    WebElement msg =
        this.driverCustom.getPresenceOfWebElement(By.xpath(RMConstants.RMARTIFACTSPAGE_DOCUSUCCESSMSG_XPATH));
    String msgText = msg.getText();
    this.driverCustom.getClickableWebElement(
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_CLOSETHEREQFWIZARD_XPATH));
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_CLOSETHEREQFWIZARD_XPATH);
    return msgText;
  }

  /**
   * This method call in {@link RMArtifactsPage#importArtifactTypes(Map)} IMPORT_TYPE = "Import requirements from a CSV
   * file or spreadsheet"
   */
  private String importCSV() {
    Button finishBtn = this.engine.findElement(Criteria.isButton().withText("Finish")).getFirstElement();
    finishBtn.click();
    WebElement msg =
        this.driverCustom.getPresenceOfWebElement(By.xpath(RMConstants.RMARTIFACTSPAGE_CSVSUCCESSMSG_XPATH));
    String msgText = msg.getText();
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(60), "show details");
    this.driverCustom.click(RMConstants.JAZZPAGE_LINKS_XPATH, RMConstants.SHOW_DETAILS);
    WebElement lnkArtifact =
        this.driverCustom.getWebDriver().findElement(By.xpath(RMConstants.RMARTIFACTSPAGE_DETAILSMSG_XPATH));
    msgText = msgText + lnkArtifact.getText();
    return msgText;
  }

  /**
   * This method call in {@link RMArtifactsPage#importArtifactTypes(Map)} IMPORT_TYPE = "Import requirements from a
   * migration package"
   */
  private String importMigrationFile() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.UPLOAD);
    Button upldBtn = this.engine.findElement(Criteria.isButton().withText(RMConstants.UPLOAD)).getFirstElement();
    upldBtn.click();
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTNEXT_BUTTON_XPATH, Duration.ofSeconds(5));
    this.driverCustom.getClickableWebElement(
        this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.NEXT));
    this.driverCustom.click(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.NEXT);
    Button btnNext = this.engine.findElement(Criteria.isButton().withText(RMConstants.NEXT)).getFirstElement();
    btnNext.click();
    this.driverCustom.isElementPresent(RMConstants.JAZZPAGE_LINK_XPATH, Duration.ofSeconds(20), RMConstants.SHOWREPORT);
    String msg = this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_REQIFSUCCESSMSG_XPATH);
    this.driverCustom.click(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.CLOSEBUTTON);
    return msg;
  }

  /**
   * <p>
   * Clip board Data property holds a DataTransfer object.
   *
   * @param string set the clip board data.
   */
  public void setClipboardData(final String string) {
    StringSelection stringSelection = new StringSelection(string);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
  }

  /**
   * <p>
   * Click on 'Administration' drop down menu in Requirement Management page.<br>
   * Click on the link 'Manage Components and Configurations', list of streams displayed. <br>
   * Click on the stream name.<br>
   * Click on the 'Streams' link.<br>
   * Select the stream and click on 'Actions' drop down button present near the stream name. <br>
   * Select the option 'Rename', 'Rename Stream' wizard is displayed.<br>
   * Enter the new name for the stream and Click on 'OK' button.
   *
   * @param streamName - name of the stream.
   * @param renameStream - new name for the stream.
   */
  public void renameStream(final String streamName, final String renameStream) {
    waitForPageLoaded();
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_ADMIN_XPATH);
    Link linkManageCompConf =
        this.engine.findFirstElement(Criteria.isLink().withText("Manage Components and Configurations"));
    linkManageCompConf.click();
    Link linkStreamName = this.engine.findFirstElement(Criteria.isLink().withText(streamName));
    linkStreamName.click();
    Link linkSteams = this.engine.findFirstElement(Criteria.isLink().withText("Streams"));
    linkSteams.click();
    List<WebElement> streamsList =
        this.driverCustom.getWebElements(RMConstants.RMARTIFACTSPAGE_STREAMSLISTINUNDERMAINSTREAM_XPATH);
    if (!(streamsList.isEmpty())) {
      streamsList.get(0).isSelected();
    }
    Button btnActions = this.engine.findElement(Criteria.isButton().withToolTip("Actions")).getFirstElement();
    btnActions.click();
    Cell cellRename = this.engine.findElement(Criteria.isCell().withText("Rename")).getFirstElement();
    cellRename.click();
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECTTHESTREAMANDRENAME_XPATH);
    String date = com.bosch.jazz.automation.web.common.DateUtil.getCurrentDateAndTime();
    this.driverCustom.typeText(RMConstants.RMARTIFACTSPAGE_SELECTTHETEXTRENAME_XPATH, renameStream + date);
    Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOK.click();
  }

  /**
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}.<br>
   * Click on drop down menu contains folder name.<br>
   * Select option 'Assign Team Ownership', 'Associate Project/Team Area' dialog displayed.<br>
   * Select the team area name and click on 'OK' button.
   *
   * @param folderName name of the folder.
   * @param teamAreaName name of the team area.
   */
  public void assignTeamOwnership(final String folderName, final String teamAreaName) {
    waitForPageLoaded();
    TreeNode treeFolderNames = this.engine.findElement(Criteria.isTreeNode().containText(folderName)).getFirstElement();
    treeFolderNames.click();
    waitForPageLoaded();
    List<WebElement> element =
        this.driverCustom.getWebElements(RMConstants.RMARTIFACTPAGE_ASSIGNTEAMOWNERSHIPLINK_XPATH);
    for (WebElement el : element) {
      if (this.driverCustom.isElementVisible(el, Duration.ofSeconds(3))) {
        el.click();
        break;
      }
    }
    Dialog dlgAssociateProjectOrTeamArea =
        this.engine.findElement(Criteria.isDialog().withTitle("Associate Project/Team Area")).getFirstElement();
    TreeNode treeTeamAreaNames = this.engine
        .findElement(Criteria.isTreeNode().inContainer(dlgAssociateProjectOrTeamArea).containText(teamAreaName))
        .getFirstElement();
    treeTeamAreaNames.click();
    clickOnJazzButtons("OK");
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage This Project Area' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Click on link 'Explore Project', all the component for that project area is displayed.<br>
   * Click on the provided component name
   *
   * @param componentName name of the component.
   */
  public void openComponentFromAllProjectsAreaPage(final String componentName) {
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_MANAGETHISPROJECTAREA_EXPLOREPROJECT_BUTTON_XPATH);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_SELECT_COMPONENT_XPATH, componentName);
    this.driverCustom.waitForPageLoaded();
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on the image menu launcher,list of options is displayed.<br>
   * Click on 'Insert New Artifact' option.<br>
   * Choose 'After', 'Before', 'Below(as a Child)' option for adding Artifact.<br>
   * Click inner Artifact added for the Artifact. <br>
   * Click on 'twistie' button.<br>
   * Click on 'Search' button present in Artifacts page inside module, 'Module Find' window is displayed.<br>
   * Click on 'Go To' tab, Enter artifact id in 'Module Find' dialog.<br>
   * Click on 'Go To' button.
   *
   * @return child attribute is visible or not this method is used to search artifact inside module.
   */
  public boolean searchWordInCollapsedArtifact() {
    waitForPageLoaded();
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action
        .moveToElement(this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_MENULAUNCHER_BUTTON_XPATH))
        .click().build().perform();
    action.moveToElement(this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_SELECTREQUIREMENT_XPATH,
        "Insert New Artifact")).build().perform();
    action.sendKeys(Keys.chord(Keys.ARROW_RIGHT)).build().perform();
    this.driverCustom.waitForPageLoaded();
    action.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
    this.driverCustom.waitForPageLoaded();
    action.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    action.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));

    String child = "Child_" + DateUtil.getCurrentDateAndTime();
    action.sendKeys(child).build().perform();
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_MODULEINNERARTIFACT_IDS_XPATH);

    String childId = this.driverCustom.getText(RMConstants.RMDASHBOARDPAGE_ARTIFACTCHILD_ID_XPATH);
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_CHILDCLOSE_BUTTON_XPATH);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_CHILDCLOSE_BUTTON_XPATH);
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_MODULEARTIFACTSEARCH_BUTTON_XPATH);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_MODULEARTIFACTSEARCH_BUTTON_XPATH);
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, RMConstants.GOTO);
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, RMConstants.GOTO);
    action.sendKeys(childId).build().perform();
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.GOTO);
    clickOnJazzButtons(RMConstants.GOTO);

    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_MODULEARTIFACTSEARCH_HIGHLIGHT_XPATH,
        Duration.ofSeconds(10), child)) {
      return true;
    }

    this.driverCustom.isElementInvisible(RMConstants.RMARTIFACTPAGE_MODULEARTIFACTMASSAGE_BOX_XPATH,
        Duration.ofSeconds(5));

    return false;
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Click on 'ReqIF' tab.<br>
   * Click on 'New Definition...' button present left side of the page.<br>
   * Provide 'Name:','Description:' for the definition.<br>
   * Click on 'Add Artifact...' button, 'Select Artifacts for Export' dialog is displayed.<br>
   * Add multiple artifacts and click on 'Close' button.<br>
   * Click on 'Save' button.<br>
   * Click on menu present next to Definition Name.<br>
   * Select the option 'Export...' from the list displayed, 'Creating ReqIF File' dialog is displayed.<br>
   * Once the progress bar achieved 100%, 'Download' button is enabled.<br>
   * Click on 'Download' button present inside 'Creating ReqIF File' dialog, Created ReqIF file is downloaded.<br>
   * Click on 'Close' button present inside 'Creating ReqIF File' dialog.
   *
   * @param definitionName name of the ReqIF file.
   * @param definitionDescription description for the ReqIF file.
   * @param addArtifact existing artifacts to be added for creating a ReqIF file.
   */
  public void exportReqIF(final String definitionName, final String definitionDescription, final String addArtifact) {
    // waitForPageLoaded();
    Actions action = new Actions(this.driverCustom.getWebDriver());
    this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10), "ReqIF");
    Tab tabReqIF =
        this.engine.findElementWithDuration(Criteria.isTab().withText("ReqIF"), this.timeInSecs).getFirstElement();
    tabReqIF.click();
    this.driverCustom.isElementPresent(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10),
        RMConstants.NEWDEFINATION);
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZ_SPANSELECTION_XPATH, RMConstants.NEWDEFINATION);
    this.driverCustom.isElementVisible(RMConstants.JAZZ_SPANSELECTION_XPATH, Duration.ofSeconds(10),
        RMConstants.NEWDEFINATION);
    Button btnNewDefinition =
        this.engine.findElement(Criteria.isButton().withText("New Definition...")).getFirstElement();
    btnNewDefinition.click();
    addNameAndDescriptionForReqIF(definitionName, definitionDescription);
    Button btnAddArtifact =
        this.engine.findElement(Criteria.isButton().withText(addArtifact + "...")).getFirstElement();
    btnAddArtifact.click();
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_SELECTMODULE_XPATH, Duration.ofSeconds(10));
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_SELECTMODULE_XPATH);
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_ARTIFACTNAMESFOR_EXPORT_XPATH);
    this.driverCustom.waitForSecs(Duration.ofSeconds(1));

    List<String> artifactlist =
        this.driverCustom.getWebElementsText(RMConstants.RMARTIFACTPAGE_ARTIFACTNAMESFOR_EXPORT_XPATH);

    for (int i = 1; i <= artifactlist.size(); i++) {
      this.driverCustom.getWebDriver().findElement(By.xpath("//div[@class='content-area']/a[" + i + "]/span[2]"))
          .click();
      Button btnAdd = this.engine.findElement(Criteria.isButton().withText("Add")).getFirstElement();
      btnAdd.click();
      this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(5),
          RMConstants.CLOSEBUTTON);

      if (i == 5) {
        break;
      }
    }

    this.driverCustom.waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.CLOSEBUTTON);
    Button btnClose = this.engine.findElement(Criteria.isButton().withText(RMConstants.CLOSEBUTTON)).getFirstElement();
    btnClose.click();
    Button btnSave = this.engine.findElement(Criteria.isButton().withText("Save")).getFirstElement();
    btnSave.click();
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTARTIFACTFORMATVALUE_XPATH,
        Duration.ofSeconds(10), definitionName);
    action.moveToElement(
        this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_SELECTARTIFACTFORMATVALUE_XPATH, definitionName))
        .contextClick().build().perform();
    this.driverCustom.waitForPageLoaded();
    this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_MENU_XPATH, Duration.ofSeconds(5), "Export...");
    Cell cellExport = this.engine.findElement(Criteria.isCell().withText("Export...")).getFirstElement();
    cellExport.click();
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(20),
        RMConstants.DOWNLOAD);
    while (!this.driverCustom.getWebDriver().findElement(By.xpath("//div[@class='dijitProgressBarLabel']")).getText()
        .equals("100%")) {
      if (this.driverCustom.getWebDriver().findElement(By.xpath("//div[@class='dijitProgressBarLabel']")).getText()
          .equals("100%")) {
        break;
      }
    }
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(5), "Download");
    Dialog dlgCreatingReqIF =
        this.engine.findElement(Criteria.isDialog().withTitle("Creating ReqIF File")).getFirstElement();
    Button btnDownload = this.engine.findElement(Criteria.isButton().withText("Download").inContainer(dlgCreatingReqIF))
        .getFirstElement();
    btnDownload.click();
    Button btnCloseCreatingReqIF = this.engine.findElement(Criteria.isButton().withText("Close")).getFirstElement();
    btnCloseCreatingReqIF.click();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(3));
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Click on 'ReqIF' tab.<br>
   * Click on 'New Definition...' button present left side of the page.<br>
   * Provide 'Name:','Description:' for the definition.
   *
   * @param definitionName name to be added for the Definition in ReqIF tab.
   * @param definitionDescription description to be added for the Definition in ReqIF tab.
   */
  public void addNameAndDescriptionForReqIF(final String definitionName, final String definitionDescription) {
    List<WebElement> nameList =
        this.driverCustom.getWebDriver().findElements(By.xpath("//label[contains(., 'Name:')]"));
    for (WebElement e1 : nameList) {
      if (e1.isDisplayed()) {
        List<WebElement> textList = e1.findElements(By.xpath("//input[@name='title' and @type='text']"));
        for (WebElement e2 : textList) {
          if (e2.isDisplayed()) {
            e2.sendKeys(definitionName);
          }
        }
      }
    }
    List<WebElement> descriptionList =
        this.driverCustom.getWebDriver().findElements(By.xpath("//textarea[@name='description']"));
    for (WebElement e : descriptionList) {
      if (e.isDisplayed()) {
        e.sendKeys(definitionDescription);
      }
    }
  }

  /**
   * <p>
   * Click on the 'Administration' icon in the Requirement Management Page using
   * {@link RMDashBoardPage#openSettingsMenu(String)}.<br>
   * Click on 'Manage Component Properties' option using {@link RMDashBoardPage#openSettingsSubMenu(String)}.<br>
   * Click on 'Artifact Attributes' tab.<br>
   * Verify provided artifact attribute is available in Artifact Attributes list.
   *
   * @param attributeName name of artifact attribute.
   * @return true if attribute is available in artifact attribute list.
   */
  public boolean isArtifactAttributeAvailable(final String attributeName) {
    waitForPageLoaded();
    ManageProjectAreaPage dng = new ManageProjectAreaPage(this.driverCustom);
    this.driverCustom.isElementVisible(RMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10),
        "Artifact Attributes");
    dng.openManageComponentProperties(ArtifactAttributes.ARTIFACT_ATTRIBUTES.toString());
    String artifactlists =
        this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_SELECTARTIFACTFORMATVALUE_XPATH, attributeName);
    if (attributeName.equalsIgnoreCase("ForeignCreatedBy")) {
      return artifactlists.contains(attributeName);
    }
    return false;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * If 'ARTIFACT_TYPE' is 'ARTIFACTS',select the row and click on the artifacts.<br>
   * If 'ARTIFACT_TYPE' is 'COLLECTIONS', select the row and click on the collections.<br>
   * If 'ARTIFACT_TYPE' is 'MODULES', select the row and click on the modules.<br>
   * Click on menu launcher and select the option 'Export Artifact...', 'Export' dialog is displayed.<br>
   * Select the file format as 'CSV' from export dialog.<br>
   * Click on 'OK' button.
   *
   * @param additionalParams stores key values 'ARTIFACT_TYPE','EXPORT_ARTIFACTS','EXPORT_MODULE','EXPORT_COLLECTION'
   *          etc...
   * @return true if file exported to csv file.
   */
  public boolean exportCSVfile(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Actions action = new Actions(this.driverCustom.getWebDriver());
    Row rowArtifacts = null;
    if (additionalParams.get(RMConstants.ARTIFACT_TYPE).equalsIgnoreCase("ARTIFACTS")) {
      action
          .moveToElement(
              this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_SELECTANDCLICKTHEPROJECTAREA_XPATH))
          .click().build().perform();
      waitForSecs(2);
      rowArtifacts = this.engine.findElement(Criteria.isRow().containsText(additionalParams.get("EXPORT_ARTIFACTS")))
          .getFirstElement();
    }
    else if (additionalParams.get(RMConstants.ARTIFACT_TYPE).equalsIgnoreCase("MODULES")) {
      refresh();
      waitForPageLoaded();
      action
          .moveToElement(
              this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_SELECTANDCLICKTHEPROJECTAREA_XPATH))
          .click().build().perform();
      waitForSecs(2);
      rowArtifacts = this.engine.findElement(Criteria.isRow().containsText(additionalParams.get("EXPORT_MODULE")))
          .getFirstElement();
    }
    else if (additionalParams.get("ARTIFACT_TYPE").equalsIgnoreCase("COLLECTIONS")) {
      refresh();
      waitForPageLoaded();
      action
          .moveToElement(
              this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_SELECTANDCLICKTHEPROJECTAREA_XPATH))
          .click().build().perform();
      waitForSecs(2);
      rowArtifacts = this.engine.findElement(Criteria.isRow().containsText(additionalParams.get("EXPORT_COLLECTION")))
          .getFirstElement();
    }

    Dropdown drdExportArtifact = this.engine.findFirstElement(Criteria.isDropdown().inContainer(rowArtifacts));
    drdExportArtifact.selectOptionWithText("Export Artifact...");
    Label labelCSV = this.engine.findElement(Criteria.isLabel().withText("CSV")).getFirstElement();
    labelCSV.click();
    if (this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(5), "OK")) {
      clickOnJazzButtons("OK");
      return true;
    }
    return false;
  }

  /**
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}.<br>
   * Click on drop down menu contains folder name.<br>
   * Select option 'Copy From a Component...', 'Copy from project or component' dialog displayed.<br>
   * Click on 'Select Component Configuration...' button present in 'Select Component Configuration' section, 'Select a
   * Component Configuration' dialog is displayed.<br>
   * Click on 'Streams' drop down menu and select appropriate option like Streams/Baseline/Change Set.<br>
   * Select required component name from 'Component:' drop down list.<br>
   * Search the Streams/Baselines/Change Sets in 'Type to search (enter * to show all)' text box, list of items
   * displayed.<br>
   * Click on the required searched text.<br>
   * Click on 'OK' button.<br>
   * Click on 'Next' button, 'Select Artifacts' section is displayed.<br>
   * Click on 'Add Artifact'/'Add Module'/'Add Collection', 'Select Artifacts' dialog is displayed.<br>
   * Select required Artifacts and click on 'OK' button.<br>
   * Click on 'Finish' button.
   *
   * @param additionalParams stores key values for
   *          'FOLDER_NAME','DROP_DOWN_MENU','COMPONENT_NAME','SELECT_ARTIFACT_TYPE'.
   * @return true if copy done with successful message.
   */
  public boolean copyFromAComponent(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    TreeNode treeModuleFolder = this.engine
        .findElement(Criteria.isTreeNode().containText(additionalParams.get("FOLDER_NAME"))).getFirstElement();
    Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().inContainer(treeModuleFolder));
    drdMenu.selectOptionWithText("Copy From a Component...");
    Button btnSelectCompConfig =
        this.engine.findElement(Criteria.isButton().withText("Select Component Configuration...")).getFirstElement();
    btnSelectCompConfig.click();
    Button btnStream = this.engine.findElement(Criteria.isButton().withText("Streams")).getFirstElement();
    btnStream.click();
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH, additionalParams.get("DROP_DOWN_MENU"));
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_COMPONENT_DROPDOWN_XPATH);
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, additionalParams.get("COMPONENT_NAME"));
    Dialog dlgSelectCompConfig =
        this.engine.findFirstElement(Criteria.isDialog().withTitle("Select a Component Configuration"));
    Text txtBaseline = this.engine.findFirstElement(Criteria.isTextField()
        .withPlaceHolder("Type to search (enter * to show all)").inContainer(dlgSelectCompConfig));
    txtBaseline.setText("*");
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_BASELINESEARCH_FILTER_XPATH);
    Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOK.click();
    Button btnNext = this.engine.findElement(Criteria.isButton().withText("Next")).getFirstElement();
    btnNext.click();
    Button btnAddArtifact = this.engine
        .findElement(Criteria.isButton().withText(additionalParams.get("SELECT_ARTIFACT_TYPE"))).getFirstElement();
    btnAddArtifact.click();
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_SELECTMODULES_ARTIFACT_XPATH);
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    for (int i = 1; i < 2; i++) {
      this.driverCustom.getWebDriver().findElement(By.xpath("//div[@class='content-area']/a[" + i + "]/span")).click();
      this.driverCustom.waitForSecs(Duration.ofSeconds(1));
    }
    Button btnOK1 = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOK1.click();
    this.driverCustom.waitForPageLoaded();
    Button btnFinish = this.engine.findElement(Criteria.isButton().withText("Finish")).getFirstElement();
    btnFinish.click();
    return this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_COPYARTIFACTOK_MASSAGE_XPATH,
        Duration.ofSeconds(5));
  }

  /**
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}.<br>
   * Click on 'Compare Configuration...' option using
   * {@link RMDashBoardPage#clickOnCurrentConfigurationDropdownMenu(String)}.<br>
   * 'Select Configuration to Compare' dialog displayed, Select appropriate 'Streams'/'Baselines' and Click on 'OK'
   * button.<br>
   * 'Compare Configurations' page is displayed.<br>
   * Click on 'Next >' button, Configurations with 'Folders' page is displayed.<br>
   * Click on 'Next >' button.<br>
   * Click on 'Close' button.
   *
   * @return true if configuration is compared else return false if its not compared.
   */
  public boolean compareConfigurations() {
    Button btnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOK.click();
    try {
      Dialog dlgExistingDeliver =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Existing Delivery Session Detected"),
              Duration.ofSeconds(30)).getFirstElement();
      Button btnCancel = this.engine.findElementWithDuration(Criteria.isButton()
          .withText("Cancel the other session and continue with this delivery").inContainer(dlgExistingDeliver),
          Duration.ofSeconds(10)).getFirstElement();
      btnCancel.click();
    }
    catch (Exception e) {
      // No existing Deliver session
    }
    this.waitForSecs(40);
    if (this.driverCustom.isElementPresent(RMConstants.RMDASHBOARDPAGE_CHECKING_HEADER_XPATH, Duration.ofSeconds(10),
        "Compare Configurations")) {
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_COMPARECON_COMPONENTPROP_XPATH);
      clickOnJazzButtons(RMConstants.NEXT);
      this.driverCustom.isElementPresent(RMConstants.RMDASHBOARDPAGE_CHECKING_HEADER_XPATH, Duration.ofSeconds(10),
          "Folders");
      this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, "Next");
      clickOnJazzButtons("Next >");
      this.waitForSecs(60);
      this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.CLOSEBUTTON);
      return true;
    }
    return false;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Click on the image menu launcher from the searched artifact, list of options is displayed.<br>
   * Click on 'Edit Attributes...' option from the drop down list, 'Edit Attributes' dialog is displayed.<br>
   * Select the check box contains 'Artifact Type' attribute.<br>
   * Choose appropriate drop down value for 'Artifact Type' attribute.<br>
   * Click on 'Save' button, 'Confirm Change' dialog is displayed.<br>
   * Click on 'Yes' button.<br>
   * Click on 'Clear all filters and reset columns' icon present in 'Type to filter artifacts by text or by ID' text
   * box. <br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box, row displayed contains text artifact
   * id.<br>
   * Select the cell and get the text of modified Artifact Type.
   *
   * @param additionalParams stores key values for 'ARTIFACTS_TYPE','ATTRIBUTE_NAME' etc...
   * @return artifact type attribute of the artifact.
   */
  public String editArtifactAttributes(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    if (this.driverCustom.isElementPresent(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, Duration.ofSeconds(10),
        "Full Text")) {
      Row rowModule = this.engine
          .findElement(Criteria.isRow().containsText(additionalParams.get(RMConstants.ARTIFACTID))).getFirstElement();
      Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().inContainer(rowModule));
      drdMenu.selectOptionWithText("Edit Attributes...");
      Dialog dlgEditAttribute =
          this.engine.findElement(Criteria.isDialog().withTitle(RMConstants.EDIT_ATTRIBUTES)).getFirstElement();
      Row rowArtifactType = this.engine
          .findElement(Criteria.isRow().containsText("Artifact Type").inContainer(dlgEditAttribute)).getFirstElement();
      Cell cllCheckboxArtifactType =
          this.engine.findElement(Criteria.isCell().inContainer(rowArtifactType).withIndex(1)).getFirstElement();
      Checkbox cbxArtifactType =
          this.engine.findElement(Criteria.isCheckbox().inContainer(cllCheckboxArtifactType)).getFirstElement();
      cbxArtifactType.click();

      Row rowArtifactTypeAfterChecked = this.engine
          .findElement(Criteria.isRow().containsText("Artifact Type").inContainer(dlgEditAttribute)).getFirstElement();
      Dropdown drdArtifactType =
          this.engine.findFirstElement(Criteria.isDropdown().inContainer(rowArtifactTypeAfterChecked));
      drdArtifactType.selectOptionWithText(additionalParams.get(RMConstants.ARTIFACTS_TYPE));
      Button btnSave = this.engine.findElement(Criteria.isButton().withText("Save")).getFirstElement();
      btnSave.click();
      clickOnJazzButtons("Yes");
      refresh();
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_FILTERREMOVEBUTTON_XPATH);
      Button btnClearAllFilter = this.engine
          .findElement(Criteria.isButton().withToolTip(RMConstants.CLEAR_FILTER_BUTTON_TOOLTIP)).getFirstElement();
      btnClearAllFilter.click();
      Text txtSearchVerifys = this.engine.findFirstElement(
          Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID));
      txtSearchVerifys.setText(additionalParams.get(RMConstants.ARTIFACTID) + Keys.ENTER);
      if (this.driverCustom.isElementPresent(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, Duration.ofSeconds(10),
          "Full Text")) {
        Row rowModules = this.engine
            .findElement(Criteria.isRow().containsText(additionalParams.get(RMConstants.ARTIFACTID))).getFirstElement();
        Cell cellselect = this.engine
            .findElement(
                Criteria.isCell().inContainer(rowModules).withText(additionalParams.get(RMConstants.ARTIFACTS_TYPE)))
            .getFirstElement();
        cellselect.click();
        return this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_ARTIFACTTYPEGETTEXT_XPATH,
            additionalParams.get("ATTRIBUTE_NAME"));
      }
    }
    return null;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched artifact using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'Edit' button present right side of the Artifact page.<br>
   * Edit the artifact attribute present in 'Overview' section of the artifact.<br>
   * Click on 'Done' button.
   *
   * @param additionalParams stores key values for artifact attributes like 'STATE','TYPE' etc...
   */
  public void editRMAttributesFromOverviewSection(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Button btnEdit = this.engine.findElementWithDuration(Criteria.isButton().withText("Edit"), Duration.ofSeconds(60))
        .getFirstElement();
    btnEdit.click();
    if (additionalParams.get(ArtifactProperties.TYPE.toString()) != null) {
      Dropdown drdType =
          this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(ArtifactProperties.TYPE.toString()),
              Duration.ofSeconds(60)).getFirstElement();
      drdType.selectOptionWithText(additionalParams.get(ArtifactProperties.TYPE.toString()));
    }
    Button btnDone = this.engine.findElementWithDuration(Criteria.isButton().withText("Done"), Duration.ofSeconds(60))
        .getFirstElement();
    btnDone.click();
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search for the existing artifact/ module id in quick search text box of Requirement Management Page.<br>
   * Select searched artifact/ module and click on 'menu' present near to artifact id, list of options displayed.<br>
   * Select the appropriate option from the context menu.
   *
   * @param artifact is artifact/Module id
   * @param option menu option
   */
  public void openContextMenuForSelectedArtifact(final String artifact, final String option) {
    Row rowArtifact = null;
    rowArtifact = this.engine.findFirstElementWithDuration(Criteria.isRow().containsText(artifact), this.timeInSecs);
    if (rowArtifact != null) {
      this.driverCustom.click("//td[@aria-label='Menu button for artifact DYNAMIC_VAlUE']//img", artifact);
      waitForSecs(3);
      this.driverCustom.click(
          "(//table[contains(@class,'dijitMenuActive')] | //table[contains(@class,'dijitMenuSelected ')])//td[contains(text(),'" +
              option + "')]");
      LOGGER.LOG.info("Open context menu of '" + artifact + "' artifact and select '" + option + "' option");
      waitForSecs(10);
    }
    else {
      throw new InvalidArgumentException("'" + artifact + "'  doesn't exist in the attribute list.");
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'Current Configuration' drop down present nect to Configuration name.<br>
   * Click on required option from the displayed drop down list.
   *
   * @param subMenu menu name displays after clicking 'Current Configuration' drop down 'Create Baseline...','Create
   *          Change Set...' etc.
   */
  @Override
  public void openAndSelectSubMenuInCurrentConfiguration(final String subMenu) {
    waitForPageLoaded();
    Dropdown currentConfiguration =
        this.engine.findElementWithDuration(Criteria.isDropdown().withToolTip("Current Configuration"), this.timeInSecs)
            .getFirstElement();
    currentConfiguration.selectOptionWithText(subMenu);
    LOGGER.LOG.info("Click on 'Current Configuration' context menu and select " + subMenu);
    waitForSecs(2);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'Configure Page Setting' icon present in column header in the Artifacts page, list of option is
   * displayed.<br>
   * Click on 'More...' option from the displayed list of option 'Change Column Display Settings' window displayed.<br>
   * Search the attribute name in 'Type to filter by attribute name' text box and click on 'Add' button.<br>
   * Added column is displayed in 'Columns to show' section to be displayed in the artifact table.<br>
   * Click on 'OK' button in 'Change Column Display Settings' dialog.
   *
   * @param addLinkToArtifact stores different link type to add 'Validated By','Implemented By' etc...
   * @author LTU7HC
   */
  public void changeColumnDisplaySettings(final String addLinkToArtifact) {
    this.driverCustom.isElementInvisible(RMConstants.LOADED_PROJECTAREA_MESSAGE, this.timeInSecs);
    List<WebElement> viewColumnNames = this.driverCustom.getWebDriver()
        .findElements(By.xpath(RMConstants.RMARTIFACTSPAGE_COLUMNBORDER_DROPDOWN_XPATH));
    List<String> columnnames = new ArrayList<>();
    viewColumnNames.stream().map(WebElement::getText).forEach(columnnames::add);
    if (!columnnames.stream().anyMatch(x -> x.trim().equals(addLinkToArtifact))) {
      Dropdown drpConfigurePageSettings = this.engine
          .findFirstElementWithDuration(Criteria.isDropdown().withToolTip("Configure Page Settings"), this.timeInSecs);
      LOGGER.LOG.info("Clicked on 'Configure Page Settings' button.");
      drpConfigurePageSettings.selectOptionWithText("More...");
      if (this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_VIEWOPTIONS_MENU_XPATH, Duration.ofSeconds(1),
          "More")) {
        WebElement optionMore =
            this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_VIEWOPTIONS_MENU_XPATH, "More");
        optionMore.click();
      }
      LOGGER.LOG.info("Clicked on 'More...' option.");
      Dialog dlgChangeColumnDisplaySettings = this.engine
          .findElementWithDuration(Criteria.isDialog().withTitle("Change Column Display Settings"), this.timeInSecs)
          .getFirstElement();
      LOGGER.LOG.info("Dialog 'Change Column Display Settings' is displayed.");
      Text txtSearch = this.engine.findFirstElement(Criteria.isTextField().inContainer(dlgChangeColumnDisplaySettings)
          .withPlaceHolder("Type to filter by attribute name"));
      txtSearch.setText(addLinkToArtifact);
      LOGGER.LOG.info(RMConstants.SEARCHED + addLinkToArtifact +
          "' in 'Type to filter attribute name' text box of 'Change Column Display Settings' dialog.");
      if (this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_COLUMNNAME_TEXT_XPATH, Duration.ofSeconds(30),
          addLinkToArtifact)) {
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEINSIDE_SELECTATT_LINKTYPES_XPATH);
        Label lblColumn = this.engine
            .findElement(Criteria.isLabel().withText(addLinkToArtifact).inContainer(dlgChangeColumnDisplaySettings))
            .getFirstElement();
        lblColumn.click();
        LOGGER.LOG.info("Clicked on - " + addLinkToArtifact);
      }
      else {
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEINSIDE_SELECTATT_LINKTYPES_XPATH);
        this.driverCustom.click(RMConstants.RMMODULEINSIDE_SELECTATT_LINKTYPES_XPATH);
        LOGGER.LOG.info("Clicked on - " + addLinkToArtifact);
      }
      clickOnJazzButtons("Add");
      LOGGER.LOG.info("Clicked on 'Add' button.");
      clickOnJazzButtons("OK");
      LOGGER.LOG.info(RMConstants.CLICK_ON_OK_BUTTON);
    }
    else {
      LOGGER.LOG.info("The column '" + addLinkToArtifact + "' is displayed in table.");
    }


  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Selects the artifact from the list of artifacts displayed on the table based on artifact Id.
   *
   * @param artifactId id of the artifact.
   */
  public void selectArtifact(final String artifactId) {
    this.driverCustom.switchToDefaultContent();
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(20), artifactId);
    Row row = null;
    try {
      this.driverCustom.switchToDefaultContent();
      row = this.engine.findFirstElementWithDuration(Criteria.isRow().containsText(artifactId), this.timeInSecs);
      row.scrollToElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (row != null) {
      Cell cel = this.engine.findElement(Criteria.isCell().inContainer(row).withIndex(1)).getFirstElement();
      Checkbox cbxArtifact = this.engine.findElement(Criteria.isCheckbox().inContainer(cel)).getFirstElement();
      cbxArtifact.click();
      LOGGER.LOG.info("Select artifact with id is '" + artifactId + "'");
    }
    else {
      throw new InvalidArgumentException("Artifact " + artifactId + " Could not be found or not selected");
    }
  }


  /**
   * <p>
   * This method used to deselect the sected artifact artifact.
   *
   * @param artifactId to deselect check box
   */
  public void deSelectArtifact(final String artifactId) {
    waitForPageLoaded();
    this.driverCustom.switchToDefaultContent();
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(20), artifactId);
    this.driverCustom.getPresenceOfWebElement(
        "//td[contains(@aria-label,'selected') and contains(@aria-label,'DYNAMIC_VAlUE') ]", artifactId).click();
    LOGGER.LOG.info("Deselect selected artifact with id is '" + artifactId + "'");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Selects the artifact from the list of artifacts displayed on the table based on number of artifacts provided.
   *
   * @param numberOFArtifacts artifact number.
   */
  public void selectMultipleArtifact(final String numberOFArtifacts) {
    waitForPageLoaded();
    List<String> idsList = this.driverCustom.getWebElementsText(RMConstants.VIEW_COLUMNS_ARTIFACT_IDS_XPATH);
    if (Integer.parseInt(numberOFArtifacts) > idsList.size()) {
      throw new WebAutomationException("Number of artifacts to be selected is " + numberOFArtifacts +
          " but there are only " + idsList.size() + " artifacts displayed.");
    }
    int count = Integer.parseInt(numberOFArtifacts);
    int i = 0;
    for (String id : idsList) {
      if (i < count) {
        Row row = null;
        try {
          row = this.engine.findElement(Criteria.isRow().containsText(id)).getFirstElement();
        }
        catch (Exception e) {
          LOGGER.LOG.error(e.getMessage());
        }
        if (row != null) {
          Cell cel = this.engine.findElement(Criteria.isCell().inContainer(row).withIndex(1)).getFirstElement();
          try {
            Checkbox cbxArtifact = this.engine.findElement(Criteria.isCheckbox().inContainer(cel)).getFirstElement();
            cbxArtifact.click();
          }
          catch (Exception e) {
            LOGGER.LOG.error(e.getMessage());
          }
          LOGGER.LOG.info("Select artifact with id is " + id);
        }
        else {
          throw new InvalidArgumentException("Artifact " + id + " could not be found");
        }
        i++;
      }
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Returns id of the artifact from the row index value provided.
   *
   * @param index row no of the artifacts.
   * @return artifact id the row index provided.
   */
  public String getArtifactIdByIndex(final String index) {
    WebElement artifactRow = this.driverCustom
        .getPresenceOfWebElement("(//table[@rowType='resourceRow']//td[@class='id-col']//a)[" + index + "]");
    return artifactRow.getText();
  }

  /**
   * Opens Context menu for the selected artifact.
   *
   * @param id artifact number.
   */
  public void selectArtifactAndOpenContextMenu(final String id) {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(RMConstants.RMMODULEINSIDE_ARTIFACT_ID_XPATH, Duration.ofSeconds(10), id);
    Row rowModule = this.engine.findElement(Criteria.isRow().withText(id)).getFirstElement();
    Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().inContainer(rowModule));
    drdMenu.getWebElement().click();
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on the artifact in the requirements table.
   *
   * @param artifactID id of the artifact.
   */
  public void clickOnArtifact(final String artifactID) {
    waitForPageLoaded();
    String colid = "id-col";
    try {
      Integer.parseInt(artifactID);
    }
    catch (NumberFormatException e) {
      colid = "name";
    }
    String xpath = "//td[@colid='" + colid + "']//a[contains(text(),'" + artifactID + "')]";
    this.driverCustom.isElementVisible(xpath, Duration.ofSeconds(60));
    this.driverCustom.click(xpath);
    this.driverCustom.isElementVisible(RMConstants.LOADINGARTIFACTLINKS_MESSAGE_XPATH, Duration.ofSeconds(10));
    this.driverCustom.isElementInvisible(RMConstants.LOADINGARTIFACTLINKS_MESSAGE_XPATH, Duration.ofSeconds(10));
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Copy an Artifact from one module and paste it on another module, 'Paste Special' dialog is displayed.<br>
   * Enable the check box 'Create a link from the copied artifact to the original artifact' under 'Link Pasted and
   * Original Artifacts' section.<br>
   * Click on 'Show Artifact Types' button, added Artifact Types is displayed.<br>
   * Click on 'Hide Artifact Types' button.<br>
   * Click on 'Select the link type' drop down and select the appropriate option for the link type.
   *
   * @return true if OK button is enabled.
   */
  public boolean pasteSpecial() {
    // waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, "OK");
    if (!this.driverCustom.isElementVisible(RMConstants.RMMODULEINSIDE_PASTESPECIALOK_BUTTON_XPATH,
        Duration.ofSeconds(5))) {
      this.driverCustom.click(RMConstants.MANAGECOMPPROPERTIES_MULTIPLEVALUE_CHECKBOX_XPATH,
          "Create a link from the copied artifact to the original artifact");
      this.driverCustom.waitForPageLoaded();
      if (this.driverCustom.isElementVisible(RMConstants.RMMODULEINSIDE_PASTESPECIALOK_BUTTON_XPATH,
          Duration.ofSeconds(5))) {
        this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, "Show Artifact Types");
        Button btnShowArtifact =
            this.engine.findElement(Criteria.isButton().withText("Show Artifact Types")).getFirstElement();
        btnShowArtifact.getWebElement().click();
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEINSIDE_PASTESPECIAL_ARTIFACTTYPES_XPATH);
        this.driverCustom.isElementVisible(RMConstants.RMMODULEINSIDE_PASTESPECIAL_ARTIFACTTYPES_XPATH,
            Duration.ofSeconds(5));
        this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, "Hide Artifact Types");
        Button btnHideArtifact =
            this.engine.findElement(Criteria.isButton().withText("Hide Artifact Types")).getFirstElement();
        btnHideArtifact.getWebElement().click();
        if (this.driverCustom.isElementVisible(RMConstants.RMMODULEINSIDE_PASTESPECIALOK_BUTTON_XPATH,
            Duration.ofSeconds(5))) {
          return true;
        }
      }
      else if (!this.driverCustom.isElementVisible(RMConstants.RMMODULEINSIDE_PASTESPECIALOK_BUTTON_XPATH,
          Duration.ofSeconds(5))) {
        return false;
      }
    }
    else if (this.driverCustom.isElementVisible(RMConstants.RMMODULEINSIDE_PASTESPECIALOK_BUTTON_XPATH,
        Duration.ofSeconds(5))) {
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEINSIDE_PASTESPECIALLINK_DROPDOWN_XPATH);
      this.driverCustom.click(RMConstants.RMMODULEINSIDE_PASTESPECIALLINK_DROPDOWN_XPATH);
      this.driverCustom.waitForPageLoaded();
      try {
        new Actions(this.driverCustom.getWebDriver())
            .moveToElement(this.driverCustom.getWebDriver().findElement(
                By.xpath("//table[@class='dijitReset dijitMenuTable']/descendant::td[contains(text(),'Link To')]")))
            .click().build().perform();
      }
      catch (Exception e) {
        LOGGER.LOG.error(e.getMessage());
      }
      this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, "OK");
      Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
      btnOK.getWebElement().click();
      return true;
    }
    return false;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'Add filter' icon to add the filter value in the artifacts.
   */
  public void addFilter() {
    try {
      Button btnAddFilter = this.engine
          .findElementWithDuration(Criteria.isButton().withToolTip("Add filter"), this.timeInSecs).getFirstElement();
      btnAddFilter.click();
      LOGGER.LOG.info("Clicked on 'Add filter' button.");
    }
    catch (Exception e) {
      LOGGER.LOG.error("The 'Add filter' button can not be clicked!");
    }

  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'Expand filters' icon to expand the filter value under view in the artifacts.
   */
  public void expandFilters() {
    waitForPageLoaded();
    Button btnExpandFilter =
        this.engine.findElement(Criteria.isButton().withToolTip("Expand filters")).getFirstElement();
    btnExpandFilter.click();
    LOGGER.LOG.info("Clicked on 'Expand filters' button.");
  }

  /**
   * This method to remove filter by full-text of condition
   *
   * @author KYY1HC
   * @param conditionText both just only attribute or full of attribute and value are ok
   */
  public void removeFilterByCondition(final String conditionText) {
    // this.driverCustom.click(String.format("//div[@class='condition-div'][@title='%s']", conditionText));
    String filterName = String.format("//div[@class='condition-div' and starts-with(@title,'%s')]", conditionText);
    this.driverCustom.clickUsingActions(this.driverCustom.getWebElement(filterName));
    // Button btnRemoveFilter =
    // this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Remove filter"), this.timeInSecs);
    // btnRemoveFilter.click();
    waitForSecs(2);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_REMOVEFILTEROFATTRIBUTE_BUTTON_XPATH, conditionText);
    LOGGER.LOG.info("Clicked on 'Remove filter' button.");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'Clear all filters' icon to clear all the filters added in the artifacts.
   */
  public void clearFilter() {
    waitForPageLoaded();
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE_02, this.timeInSecs);
    // Check if ResourceHover is visible then turn it off otherwise clear filter will be failed (LTU7HC add this
    // comment)
    String resourceHoverXpath = "//div[contains(@class,'ResourceHover')]";
    if (this.driverCustom.isElementVisible(resourceHoverXpath, Duration.ofSeconds(10))) {
      this.driverCustom.click(resourceHoverXpath + "//a[@class='closeButton']");
    }
    // Clear Filter in Artifacts page
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_CLEAR_FILTER_XPATH_VERSION7,
        Duration.ofSeconds(5))) {
      WebElement btnClearAllFilterAndResetColumns =
          this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_CLEAR_FILTER_XPATH_VERSION7);
      btnClearAllFilterAndResetColumns.click();
      LOGGER.LOG.info("Clicked on 'Clear all filters and reset columns' button.");
    }
    // Clear Filter in Module page
    if (this.driverCustom.isElementVisible(RMConstants.RMMODULE_CLEAR_FILTER_XPATH, Duration.ofSeconds(5))) {
      WebElement btnClearAllFilterAndResetColumns =
          this.driverCustom.getWebElement(RMConstants.RMMODULE_CLEAR_FILTER_XPATH);
      btnClearAllFilterAndResetColumns.click();
      LOGGER.LOG.info("Clicked on 'Clear all filters and reset columns' button.");
    }
    // Version 6: Clear Filter in Module page
    if (this.driverCustom.isElementClickable(RMConstants.RMARTIFACTPAGE_CLEAR_FILTER_XPATH, Duration.ofSeconds(5))) {
      Button btnClearAllFilter = this.engine
          .findElementWithDuration(Criteria.isButton().withToolTip("Clear all filters"), Duration.ofSeconds(60))
          .getFirstElement();
      btnClearAllFilter.click();
      LOGGER.LOG.info("Clicked on 'Clear all filters' button.");
    }
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE_02, this.timeInSecs);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'Add filter' icon, 'New filter' wizard is displayed.<br>
   * Search the attribute name 'Type to filter' text box inside 'New Filter' dialog.
   *
   * @param attributeName name of the attribute to add in the filter.
   */
  public void searchAttributeInFilter(final String attributeName) {
    Dialog dlgNewFilter = this.engine.findElement(Criteria.isDialog().withTitle("New Filter")).getFirstElement();
    LOGGER.LOG.info("Displayed 'New Filter' dialog.");
    Text txtSearch = this.engine
        .findFirstElement(Criteria.isTextField().withPlaceHolder("Type to filter").inContainer(dlgNewFilter));
    txtSearch.setText(attributeName);
    LOGGER.LOG.info("Searched " + attributeName + " in 'Type to filter' text box.");
    this.driverCustom.getPresenceOfWebElement(RMConstants.ATTRIBUTE_LIST_IN_NEW_FILTER_XPATH);
    List<WebElement> allRows = this.driverCustom.getWebElements(RMConstants.ATTRIBUTE_LIST_IN_NEW_FILTER_XPATH);
    ArrayList<String> rowValues = new ArrayList<>();
    for (WebElement row : allRows) {
      rowValues.add(row.getText());
    }
    if (!rowValues.contains(attributeName)) {
      throw new InvalidArgumentException(attributeName + " - doesn't exist in the attribute list.");
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'Add filter' icon, 'New filter' wizard is displayed.<br>
   * Search the attribute name 'Type to filter' text box inside 'New Filter' dialog using
   * {@link RMArtifactsPage#searchAttributeInFilter(String)}.<br>
   * Select attribute name from the displayed list of attributes.<br>
   * Choose values for the selected attribute name.<br>
   * Click on 'Add and Close' button inside 'New Filter' dialog.
   *
   * @param attributeName name of the attribute to add in the filter.<br>
   *          e.g. Artifact Type,Artifact ID etc.
   * @param attributeValue value of the artifact for the searched attribute.<br>
   *          e.g.Heading,Software Requirement Specification etc.
   */
  public void chooseAttributeValueForSelectedAttribute(final String attributeName, final String attributeValue) {
    waitForPageLoaded();
    Label labelAttribute = this.engine.findElement(Criteria.isLabel().withText(attributeName)).getFirstElement();
    labelAttribute.click();
    LOGGER.LOG.info(RMConstants.CLICKED_ON + attributeName);
    Checkbox checkbxValue = null;
    String txtBoxValue = null;
    if (this.driverCustom.isElementVisible("//div[@role='dialog']//div[contains(@id,'AriaListbox')]",
        this.timeInSecs)) {
      try {
        checkbxValue = this.engine.findElement(Criteria.isCheckbox().withLabel(attributeValue)).getFirstElement();
        checkbxValue.click();
        LOGGER.LOG.info(RMConstants.CLICKED_ON + attributeValue + " check box.");
      }
      catch (Exception e) {
        WebElement txtBox = this.driverCustom.getPresenceOfWebElement("//input[contains(@class,'InputInner')]");
        txtBox.sendKeys(attributeValue);
        txtBoxValue = this.driverCustom.getAttribute("//input[contains(@class,'InputInner')]", "value");
      }
    }
    else {
      // This part needed for TS_25934
      try {
        checkbxValue = this.engine.findElement(Criteria.isCheckbox().withLabel(attributeValue)).getFirstElement();
        checkbxValue.click();
        LOGGER.LOG.info(RMConstants.CLICKED_ON + attributeValue + " check box.");
      }
      catch (Exception e) {
        LOGGER.LOG.error(e.getMessage());
      }
    }


    if ((checkbxValue != null) || (txtBoxValue != null)) {
      Button btnAddAndClose = this.engine.findElement(Criteria.isButton().withText("Add and Close")).getFirstElement();
      btnAddAndClose.click();
      LOGGER.LOG.info("Clicked on 'Add and Close' button.");
    }
    else {
      throw new InvalidArgumentException(attributeValue + " - doesn't exist in the 'New Filter' dialog.");
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'Add filter' icon, 'New filter' wizard is displayed.<br>
   * Search the attribute name 'Type to filter' text box inside 'New Filter' dialog using
   * {@link RMArtifactsPage#searchAttributeInFilter(String)}.<br>
   * Select attribute name from the displayed list of attributes.<br>
   * Choose all values for the selected attribute name using hotkeys<br>
   * Click on 'Add and Close' button inside 'New Filter' dialog.
   *
   * @param attributeName - name of the attribute to add in the filter.<br>
   *          e.g. Artifact Type,Artifact ID etc.
   * @param valueCondition - Condition value for attributeName<br>
   *          e.g. is any of, is not any of
   * @param expectedValue - expected test displayed in filter section Ex: Artifact Type - is any of - (*your expected
   *          value*) displayed in filter section
   * @return number of selected values
   */
  public String chooseAllAttributeValuesForSelectedAttribute(final String attributeName, final String valueCondition,
      final String expectedValue) {
    Label labelAttribute = this.engine.findElement(Criteria.isLabel().withText(attributeName)).getFirstElement();
    labelAttribute.click();
    LOGGER.LOG.info(RMConstants.CLICKED_ON + attributeName);
    WebElement drdAttributeContiditon =
        this.driverCustom.getPresenceOfWebElement(RMConstants.FILTERDIALOG_ATTRIBUTE_CONDITION_XPATH, attributeName);
    if (!drdAttributeContiditon.getText().equalsIgnoreCase(valueCondition)) {
      drdAttributeContiditon.click();
      this.driverCustom.getWebElement(RMConstants.FILTERDIALOG_ATTRIBUTE_CONDITION_OPTION_XPATH, valueCondition)
          .click();
    }
    List<WebElement> opts = this.driverCustom.getWebElements(RMConstants.FILTERDIALOG_ATTRIBUTE_VALUE_XPATH);
    opts.get(0).click();
    JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
    je.executeScript("arguments[0].scrollIntoView(true);", opts.get(opts.size() - 1));
    Actions act = new Actions(this.driverCustom.getWebDriver());
    act.keyDown(Keys.SHIFT).click(opts.get(opts.size() - 1)).keyUp(Keys.SHIFT).build().perform();
    Button btnAddAndClose = this.engine.findElement(Criteria.isButton().withText("Add and Close")).getFirstElement();
    btnAddAndClose.click();
    LOGGER.LOG.info("Clicked on 'Add and Close' button.");
    return String.valueOf(opts.size());
  }


  /**
   * <p>
   * Select attribute from the list of attribute after searching. <br>
   * Select all values of list from the displayed check box list. <br>
   * Click on Add and Close button to add attribute and value, then close New Filter wizard
   *
   * @param attribute name of the attribute to add in the filter.<br>
   *          e.g. Artifact Type,Artifact ID etc.
   * @param values String contains all value seperated by comma ', '
   */
  public void addAndCloseListValueForAttributeInFilter(final String attribute, final String values) {
    // waitForPageLoaded();
    List<String> lstValues = Arrays.asList(values.split(",", -1));
    Label labelAttribute = this.engine.findElement(Criteria.isLabel().withText(attribute)).getFirstElement();
    labelAttribute.click();
    LOGGER.LOG.info(RMConstants.CLICKED_ON + attribute);
    Checkbox checkbxValue = null;
    try {
      for (String value : lstValues) {
        checkbxValue = this.engine.findElement(Criteria.isCheckbox().withLabel(value.trim())).getFirstElement();
        checkbxValue.click();
        LOGGER.LOG.info(RMConstants.CLICKED_ON + value + " checkbox.");
      }
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }

    if (checkbxValue != null) {
      Button btnAddAndClose = this.engine.findElement(Criteria.isButton().withText("Add and Close")).getFirstElement();
      btnAddAndClose.click();
      LOGGER.LOG.info("Clicked on 'Add and Close' button.");
    }
    else {
      throw new InvalidArgumentException(lstValues + " - doesn't exist in the New Filter dialog.");
    }
  }


  /**
   * <p>
   * Add filter by attribute and value for artifact in New Filter<br>
   * Validate condition is set in the Filter.
   *
   * @author KYY1HC
   * @param condition the condition in the Filter.
   * @return true if condition is displayed else it will return false.
   */
  public boolean isConditionSetInFilter(final String condition) {
    // waitForPageLoaded();
    LOGGER.LOG.info("Verified '" + condition + "' displayed.");
    return this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_CONDITION_FILTER_XPATH, this.timeInSecs,
        condition);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'Save as new view' icon present in Views section inside the module.
   */
  public void clickOnSaveAsNewView() {
    waitForPageLoaded();
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE_01, Duration.ofSeconds(200)); // #pending
    Button btnSaveView = this.engine.findElement(Criteria.isButton().withToolTip("Save as new view")).getFirstElement();
    btnSaveView.click();
    LOGGER.LOG.info("Clicked on 'Save as new view' button.");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'Save as new view' icon present in Views section inside the module,'New View' dialog is displayed. <br>
   * Set the view name in 'New View' wizard.
   *
   * @param viewName name of the view.
   */
  public void setViewName(final String viewName) {
    waitForPageLoaded();
    if ((viewName != null) && (!viewName.isEmpty())) {
      Dialog dlgNewView =
          this.engine.findElement(Criteria.isDialog().withTitle(RMConstants.NEW_VIEW)).getFirstElement();
      LOGGER.LOG.info("Dialog 'New View' displayed.");
      TextField txtViewName =
          this.engine.findFirstElement(Criteria.isTextField().hasLabel("Name:").inContainer(dlgNewView));
      txtViewName.setText(viewName);
      LOGGER.LOG.info("Set " + viewName + " in 'New View' dialog.");
      return;
    }
    throw new IllegalArgumentException(INVALID_PARAMETER_OR_NULL_OR_EMPTY);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'Save as new view' icon present in Views section inside the module,'New View' dialog is displayed. <br>
   * Set the view name in 'New View' wizard using {@link RMArtifactsPage#setViewName(String)}.<br>
   * After providing a view name using {@link RMArtifactsPage#setViewName(String)} it returns view name.
   *
   * @return return view name.
   */
  public String getViewName() {
    LOGGER.LOG.info("Returns View Name from the 'New View' dialog.");
    return this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_VIEWNAME_TEXTBOX_XPATH).getAttribute("value");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'Save as new view' icon present in Views section inside the module,'New View' dialog is displayed. <br>
   * Set view type as 'Personal' or 'Shared' inside 'New View' dialog.
   *
   * @param typeOfView is the view type like 'Personal' and 'Shared'.
   */
  public void setViewType(final String typeOfView) {
    Label rdoViewType =
        this.engine.findElementWithDuration(Criteria.isLabel().withText(typeOfView), this.timeInSecs).getFirstElement();
    rdoViewType.click();
    LOGGER.LOG.info("Set view type as - " + typeOfView);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'Save as new view' icon present in Views section inside the module,'New View' dialog is displayed. <br>
   * Set the value for where to show this view 'Just this Module' or 'All Modules'.
   *
   * @param showOfView refers where to show this view 'Just this Module' or 'All Modules'.
   */
  public void setWhereToShowThisView(final String showOfView) {
    waitForPageLoaded();
    Label rdoShowView = null;
    try {
      rdoShowView = this.engine.findElement(Criteria.isLabel().withText(showOfView)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (rdoShowView != null) {
      rdoShowView.click();
      LOGGER.LOG.info("Enabled radio button '" + showOfView + "' to show the view.");
    }
    else {
      throw new InvalidArgumentException(showOfView + " - doesn't exist in the New View dialog.");
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'Save as new view' icon present in Views section inside the module,'New View' dialog is displayed. <br>
   * Enable the check box 'Make this a preferred view' to make the view as preferred view.
   */
  public void makePreferredView() {
    waitForPageLoaded();
    Label lblPreferredView =
        this.engine.findElement(Criteria.isLabel().withText("Make this a preferred view")).getFirstElement();
    lblPreferredView.click();
    LOGGER.LOG.info("Clicked on check box 'Make this a preferred view'");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'Save as new view' icon present in Views section inside the module,'New View' dialog is displayed. <br>
   * Add the description for the view in description text box.
   *
   * @param descriptionOfView refers to description for the view.
   */
  public void addDescriptionInView(final String descriptionOfView) {
    waitForPageLoaded();
    Dialog dlgNewView = this.engine.findElement(Criteria.isDialog().withTitle("New View")).getFirstElement();
    LOGGER.LOG.info("'New View' dialog displayed.");
    Text txtViewDescription =
        this.engine.findFirstElement(Criteria.isTextField().isMultiLine().inContainer(dlgNewView));
    txtViewDescription.setText(descriptionOfView);
    LOGGER.LOG.info("Set the view description as - " + descriptionOfView);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box present in 'Views' section of the module.
   *
   * @param viewname name of the view
   */
  public void searchView(final String viewname) {
    this.driverCustom.getPresenceOfWebElement("//input[@aria-label='Search for views by full or partial view name.']");
    this.driverCustom.getWebElement("//input[@aria-label='Search for views by full or partial view name.']").clear();
    Text txtSearchField = this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder("Search Views"));
    txtSearchField.setText(viewname);
    LOGGER.LOG.info("Searched " + viewname + " in 'Search Views' text field.");
  }

  /**
   * Verify the view is selected or not
   *
   * @param view name of view
   * @return true if view selected else it will return false
   */
  public boolean isViewSelected(final String view) {
    waitForSecs(5);
    boolean check = false;
    WebElement viewElement = this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, view);
    check = Boolean.parseBoolean(viewElement.getAttribute("aria-selected"));
    return check;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name. <br>
   * Click on 'Delete View...' option from the displayed options.<br>
   * 'Delete View' pop up will display,Click on 'Yes' button to delete the view.
   *
   * @param viewNameToDelete name of the view to delete.
   */
  public void deleteView(final String viewNameToDelete) {
    selectView(viewNameToDelete);
    // Hover to view
    String labelViewXpath = "(//div[@dojoattachpoint='_contentDiv']//span[text()='DYNAMIC_VAlUE'])[last()]";
    WebElement labelView = this.driverCustom.getPresenceOfWebElement(labelViewXpath, viewNameToDelete);
    Actions act = new Actions(this.driverCustom.getWebDriver());
    act.moveToElement(labelView).build().perform();
    Dropdown drdViewOption = this.engine
        .findElementWithDuration(Criteria.isDropdown().withToolTip("View Options"), this.timeInSecs).getFirstElement();
    drdViewOption.selectOptionWithText("Delete View...");
    LOGGER.LOG.info("Selected 'Delete View...' from drop down View Options");
    Dialog dlgDeleteView = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Delete View"), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info("'Delete View' dialog displayed.");
    Button btnYes = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Yes").inContainer(dlgDeleteView), this.timeInSecs)
        .getFirstElement();
    btnYes.click();
    waitForSecs(2);
    LOGGER.LOG.info("Clicked on 'Yes' button.");
  }

  /**
   * <p>
   * Open a module.<br>
   * Select a view.<br>
   * Check for the column content for each column name.
   *
   * @param viewColumnName name of the columns in view table.
   * @return true if the content is available for that column else it returns false.
   */
  public String getColumnDatafromTable(final String viewColumnName) {
    this.driverCustom.isElementVisible("//table[@role='row presentation']//tr[1]/td[@aria-label='undefined']",
        Duration.ofSeconds(120));
    Optional<WebElement> viewTableMatched =
        this.driverCustom.getWebElements("//div[@class='result-set-grid-view']").stream().findFirst();
    WebElement viewTable = viewTableMatched.isPresent() ? viewTableMatched.get() : null;
    if (viewTable == null) {
      throw new WebAutomationException("View table is empty.");
    }
    List<WebElement> headers =
        viewTable.findElements(By.xpath("//div[@class='result-set-grid-view']//td[@role='columnheader']"));
    List<String> viewHeadersList = new ArrayList<>();
    headers.stream().forEach(x -> viewHeadersList.add(x.getText()));
    LOGGER.LOG.info("Header column names are added in List.");
    this.driverCustom.isElementVisible("//table[@role='row presentation']//tr[1]/td[@aria-label='undefined']",
        Duration.ofSeconds(120));
    List<WebElement> viewRows = viewTable.findElements(By.xpath("//table[@role='row presentation']/tbody/tr[1]"));
    Map<Integer, List<String>> additionalParam = new LinkedHashMap<>();
    for (int i = 0; i < viewRows.size(); i++) {
      List<WebElement> list = viewRows.get(i).findElements(By.xpath("./td[@aria-label='undefined']"));
      if (viewHeadersList.size() != list.size()) {
        throw new WebAutomationException(
            "Columns size" + viewHeadersList.size() + " rows size " + list.size() + " are not matching.");
      }
      List<String> viewRowContent = new ArrayList<>();
      list.stream().forEach(x -> viewRowContent.add(x.getText()));
      additionalParam.put(i, viewRowContent);
    }
    LOGGER.LOG.info("View column content added to the list.");
    ArrayList<String> viewColumnResults = new ArrayList<>();
    if (viewHeadersList.stream().anyMatch(x -> x.equals(viewColumnName))) {
      int k = viewHeadersList.indexOf(viewColumnName);
      for (int j = 0; j < additionalParam.size(); j++) {
        viewColumnResults.add(additionalParam.get(j).get(k));
      }
      return String.join(",", viewColumnResults);
    }
    throw new WebAutomationException(viewColumnName + " invalid column name.");
  }

  /**
   * This method to get all values from table by columnName and validate by expectedValues <br>
   * Firstly, get actual value from columnName at each row ( => actual value).<br>
   * Secondly, loop through expectedValues (multi value or single value) to compare with actual value => At least one
   * value in expectedValue equal to actual value, total result for this row will be passed. <br>
   * Finally, loop through all visible rows in table => If one row fail , the final result will be fail <br>
   *
   * @param columnName name of column header
   * @param expectedValues expect value to compare with value getting from columnName. Separate 2 cases such as:<br>
   *          Case 1: If expectedValues being empty data, input to parameter with "emptydata" <br>
   *          Case 2: If expectedValues being value, input to parameter with single value (or list value, separated each
   *          value by comma) <br>
   *          For example: getAllValuesAtColumnFromTable (Validated By,list_Value) <br>
   *          list_Value = value_01,value_02,"emptydata",value_03,... <br>
   * @return true if all actual value get from columnName column equal to one of value in expectedValues <br>
   *         false if there is any one value from columnName column not equal to any value in expectedValues <br>
   *         Ex 01: if actual value get from columnName column as value_01 - ExpectedValues as value_01, value_02 =>
   *         return true <br>
   *         Ex 02: if actual value get from columnName column as "emptydata", value_03 - ExpectedValues as value_01,
   *         value_02 => return false <br>
   */

  public boolean getAllValuesAtColumnFromTable(final String columnName, final String expectedValues) {
    String xpathLoading = "//div[@class='status-message' and text()='Loading...']";
    this.driverCustom.isElementVisible(xpathLoading, Duration.ofSeconds(10));
    this.driverCustom.isElementInvisible(xpathLoading, Duration.ofSeconds(30));
    WebElement viewTable = this.driverCustom.getWebElement("//div[@class='result-set-grid-view']");
    List<WebElement> headers =
        viewTable.findElements(By.xpath("//div[@class='result-set-grid-view']//td[@role='columnheader']"));

    List<String> viewHeadersList = new ArrayList<>();
    headers.stream().forEach(x -> viewHeadersList.add(x.getText()));
    LOGGER.LOG.info("Header column names are added in List.");

    int columnIndex =
        viewHeadersList.stream().anyMatch(x -> x.equals(columnName)) ? viewHeadersList.indexOf(columnName) : 0;
    LOGGER.LOG.info(String.format("Header column index is '%s'", columnIndex));

    List<WebElement> rows = viewTable.findElements(By.xpath("//table[@role='row presentation']//tr[1]"));
    ArrayList<String> results = new ArrayList<>();
    for (int i = 0; i < rows.size(); i++) {
      try {
        if (columnIndex == 1) {
          String valueAtContentColumn =
              rows.get(i).findElements(By.xpath("./td[@aria-label='undefined']//p")).get(columnIndex).getText();
          LOGGER.LOG.info(String.format("Value '%s' add to list result", valueAtContentColumn));
          results.add(valueAtContentColumn);
        }
        else {
          String valueAtColumn =
              rows.get(i).findElements(By.xpath("./td[@aria-label='undefined']")).get(columnIndex).getText();
          LOGGER.LOG.info(String.format("Value '%s' add to list result", valueAtColumn));
          results.add(valueAtColumn);
        }
      }
      catch (Exception e) {
        LOGGER.LOG.warn(String.format("Rows index '%d' have no Column index '%d'", i, columnIndex));
      }


    }

    // update
    // split value in expectedValues and put to listValue
    String[] listValue = expectedValues.split(",");
    boolean verifiedAllRowListExpectValue = true;
    // loop each row in table
    for (int j = 0; j < results.size(); j++) {
      boolean verifiedOneRowListExpectValue = false;

      // loop each value in list expected value
      for (String element : listValue) {
        boolean verifiedOneRowOneExpectValue = false;
        // tempExpectedValue is one value in expected value, at index: k
        String tempExpectedValue = element.trim();
        // Case 1: expectedValues is one value, not empty
        if (!tempExpectedValue.equalsIgnoreCase("emptydata")) {
          verifiedOneRowOneExpectValue =
              tempExpectedValue.contains(results.get(j).trim()) && !results.get(j).equals("");

          // LOG File: write to log file result for valiation each row with each value in expected value.
          if (tempExpectedValue.contains(results.get(j).trim()) && (!results.get(j).equals(""))) {
            LOGGER.LOG.warn("Row at index as " + j + " has " + columnName +
                " column contain actual value being the same expect value as " + tempExpectedValue);
          }
          else {
            LOGGER.LOG.warn("Row at index as " + j + " has " + columnName +
                " column contain actual value being different from expect value as " + tempExpectedValue);
          }
        } // Case 2: expectedValues is empty data (this cell is empty - parameter being as "emptydata")
        else if (tempExpectedValue.equalsIgnoreCase("emptydata")) {
          verifiedOneRowOneExpectValue = tempExpectedValue.contains(results.get(j).trim());

          // LOG File: write to log file result for valiation each row with each value in expected value.
          if (tempExpectedValue.contains(results.get(j).trim()) == true) {
            LOGGER.LOG.warn("Row at index as " + j + " has " + columnName +
                " column contain actual value being the same expect value as " + tempExpectedValue);
          }
          else {
            LOGGER.LOG.warn("Row at index as " + j + " has " + columnName +
                " column contain actual value being different from expect value as " + tempExpectedValue);
          }
        }

        /*
         * Check one row: If there is one value in list expect result is the same as actual value get from column,
         * verifiedOneRowListExpectValue will be true - verifiedOneRowOneExpectValue: to verify actual value in each row
         * with each value in list expect result - verifiedOneRowListExpectValue: to verify actual value in each row
         * with all value in list expect result
         */
        verifiedOneRowListExpectValue = verifiedOneRowListExpectValue || verifiedOneRowOneExpectValue;

      }


      /*
       * Check all visible rows: If there is any one row has result false, verifiedAllRowListExpectValue will be false
       * verifiedAllRowListExpectValue: to verify actual value in all visible rows with all value in list expect result.
       */
      verifiedAllRowListExpectValue = verifiedOneRowListExpectValue && verifiedAllRowListExpectValue;

    }

    return verifiedAllRowListExpectValue;
  }

  /**
   * Open a module. Select a view. verify column header is added on table.
   *
   * @author KYY1HC
   * @param columnName name of column will be checked.
   * @return true if column is added successfully, otherwise return false.
   */
  public boolean isColumnHeaderDisplayed(final String columnName) {
    List<WebElement> headers =
        this.driverCustom.getWebElements("//div[@class='result-set-grid-view']//td[@role='columnheader']");
    List<String> viewHeadersList = new ArrayList<>();
    headers.stream().forEach(x -> viewHeadersList.add(x.getText()));
    String allColumnHeader = String.join(",", viewHeadersList);

    return allColumnHeader.contains(columnName);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.* Click on 'Save as new view'
   * icon present in Views section inside the module using {@link RMArtifactsPage#clickOnSaveAsNewView()},'New View'
   * dialog is displayed. <br>
   * Click on 'Change' button to see who can see this view,'Choose Roles' dialog is displayed. <br>
   * Enable the check box for the role who can see the view.
   *
   * @param role who can see the view. e.g. Everyone,Author,Administrator etc.
   */
  public void chooseRole(final String role) {
    Button btnChange = this.engine.findElement(Criteria.isButton().withText("Change...")).getFirstElement();
    btnChange.click();
    Dialog dlgChooseRole = this.engine.findElement(Criteria.isDialog().withTitle("Choose Roles")).getFirstElement();
    if (!this.driverCustom.getWebDriver()
        .findElement(By.xpath("//div[@class='roles-checkbox-container']//input[@role='checkbox']")).isSelected()) {
      Label checkRole =
          this.engine.findElement(Criteria.isLabel().withText(role).inContainer(dlgChooseRole)).getFirstElement();
      checkRole.click();
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name, list of options is displayed.<br>
   * Select the appropriate option from 'View Options' drop down value.
   *
   * @param viewName name of the view.
   * @param dropdownValue drop down value from 'View Options' drop down.
   */
  public void selectOptionFromViewOptionsDropdown(final String viewName, final String dropdownValue) {
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, Duration.ofSeconds(30), viewName);
    WebElement lnkView = this.driverCustom
        .getPresenceOfWebElement("(//div[@dojoattachpoint='_contentDiv']//span[text()='" + viewName + "'])[last()]");
    Actions actions = new Actions(this.driverCustom.getWebDriver());
    actions.moveToElement(lnkView).build().perform();
    try {
      String btnViewOptions = "(//div[@dojoattachpoint='_contentDiv']//span[text()='DYNAMIC_VAlUE'])[last()]";
      WebElement btnViewOption = this.driverCustom.getPresenceOfWebElement(btnViewOptions, viewName);
      Actions act = new Actions(this.driverCustom.getWebDriver());
      act.moveToElement(btnViewOption).build().perform();
      this.driverCustom.click(RMConstants.RMMODULEPAGE_VIEWOPTIONS_MENU_XPATH, dropdownValue);
    }
    catch (Exception e) {
      Dropdown viewOption = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withToolTip("View Options"),
          Duration.ofSeconds(10));
      waitForSecs(5);
      viewOption.selectOptionWithText(dropdownValue);
    }
    LOGGER.LOG.info("'" + viewName + "' Drop down value selected as '" + dropdownValue + "' ");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name, list of options is displayed.<br>
   * Select option 'Edit Attribute View...' from 'View Options' drop down value using
   * {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}, 'Edit Attribute' dialog is
   * displayed.<br>
   * Click on the check box for the required attribute.
   *
   * @param attributeName name of attribute.
   */
  public void clickOnEditAttributeCheckbox(final String attributeName) {
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_EDITATTRIBUTES_CHECKBOX_XPATH, attributeName);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_EDITATTRIBUTES_CHECKBOX_XPATH, attributeName);
    LOGGER.LOG.info("Clicked on '" + attributeName + "' edit attribute check box");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name, list of options is displayed.<br>
   * Select option 'Edit Attribute View...' from 'View Options' drop down value using
   * {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}, 'Edit Attribute' dialog is
   * displayed.<br>
   * Click on the check box for the required attribute.<br>
   * Set attribute value for the selected attribute.
   *
   * @param attributeName name of attribute.
   * @param attributeValue value for the attribute.
   * @return attributeValue of the selected attribute.
   */
  public String setAttributeValue(final String attributeName, final String attributeValue) {
    waitForSecs(15);
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_EDITATTRIBUTES_TEXTBOX_XPATH, attributeName);
    WebElement ele =
        this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_EDITATTRIBUTES_TEXTBOX_XPATH, attributeName);
    ele.click();
    waitForSecs(2);
    ele.sendKeys(attributeValue);
    waitForSecs(2);
    LOGGER.LOG.info("'Edit Attributes' Dialog opened.");
    LOGGER.LOG.info("Set '" + attributeName + "' value as '" + attributeValue + "'");
    return attributeValue;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name, list of options is displayed.<br>
   * Select option 'Edit Attribute View...' from 'View Options' drop down value using
   * {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}, 'Edit Attribute' dialog is
   * displayed.<br>
   * Enable the check box for 'Tags' attribute.<br>
   * Click on 'Edit Tags' icon(pencil icon) present under Value section,'Select Tags' dialog is displayed.<br>
   * Select tag name from the select tags dialog box.
   *
   * @param tagName name of the tag.
   */
  public void selectTags(final String tagName) {
    WebElement lblTag = this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, tagName);
    this.driverCustom.scrollIntoCenterOfView(lblTag);
    lblTag.click();
    LOGGER.LOG.info("'" + tagName + "' Clicked.");
    waitForSecs(2);
    if (this.driverCustom.isElementVisible("//span[text()='DYNAMIC_VAlUE' and @aria-selected='false']",
        Duration.ofSeconds(5), tagName)) {
      this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, tagName);
      waitForSecs(2);
      LOGGER.LOG.info("'" + tagName + "' Selected.");
    }
    else {
      LOGGER.LOG.info("'" + tagName + "' Already selected.");
    }

  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name, list of options is displayed.<br>
   * Select option 'Edit Attribute View...' from 'View Options' drop down value using
   * {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}, 'Edit Attribute' dialog is
   * displayed.<br>
   * Enable the check box for 'Tags' attribute.<br>
   * Click on 'Edit Tags' icon(pencil icon) present under Value section,'Select Tags' dialog is displayed.<br>
   * Click on 'Clear Selection' link present inside 'Select Tags' dialog.
   */
  public void clearSelectedTags() {
    waitForSecs(2);
    Dialog dlgSelectTags = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select Tags"), this.timeInSecs).getFirstElement();
    Link lnkClearSelection = this.engine
        .findElement(Criteria.isLink().withText("Clear Selection").inContainer(dlgSelectTags)).getFirstElement();
    lnkClearSelection.click();
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name, list of options is displayed.<br>
   * Select option 'Edit Attribute View...' from 'View Options' drop down value using
   * {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}, 'Edit Attribute' dialog is
   * displayed.<br>
   * Edit the attributes for the artifacts and click on 'Apply' button. <br>
   * Wait for progress bar to be 100%.
   *
   * @return true if loaded 100%.
   */
  public Boolean waitForHundredPercentLoad() {
    if (this.driverCustom.isElementVisible("//div[@class='messageSummary' and text()='Artifact Update Completed']",
        Duration.ofSeconds(600))) {
      LOGGER.LOG.info("Waits for progress bar to be 100%");
      while (!this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_EDITATTRIBUTES_PROGRESSBAR_XPATH).getText()
          .equals("100%")) {
        this.driverCustom.click(RMConstants.RMARTIFACTPAGE_EDITATTRIBUTES_PROGRESSBAR_XPATH);
        if (this.driverCustom.getWebDriver()
            .findElement(By.xpath(RMConstants.RMARTIFACTPAGE_EDITATTRIBUTES_PROGRESSBAR_XPATH)).getText()
            .equals("100%")) {
          return true;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name, list of options is displayed.<br>
   * Select option 'Edit Attribute View...' from 'View Options' drop down value using
   * {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}, 'Edit Attribute' dialog is
   * displayed.<br>
   * Edit the attributes for the artifacts and click on 'Apply' button. <br>
   * Wait for progress bar till completion of 100% using {@link RMArtifactsPage#waitForHundredPercentLoad()}.<br>
   * Verify success message 'Artifact Update Completed' after completion of 100% progress bar.
   *
   * @param successMessage displayed success message.
   * @return true if edit attribute updated successfully.
   */
  public boolean verifyEditAttributeSuccessMessage(final String successMessage) {
    LOGGER.LOG.info("Expected success message sent as  '" + successMessage + "'");
    return this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_TITLE_XPATH, Duration.ofSeconds(10), successMessage);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box present in 'Views' section of the module using
   * {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view.
   *
   * @author VUP5HC
   * @param viewName name of the view present inside a module.
   */
  public void selectView(final String viewName) {
    try {
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, this.timeInSecs, viewName);
      String isViewSelected = this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, viewName)
          .getAttribute(RMConstants.ARIA_SELECTED);
      if (isViewSelected.equalsIgnoreCase(RMConstants.FALSE)) {
        this.driverCustom.click(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, viewName);
        LOGGER.LOG.info("Select '" + viewName + "' view");
      }
      else {
        LOGGER.LOG.info("'" + viewName + "' already selected");
      }
    }
    catch (NullPointerException e) {
      // On Firefox, if Filter is not selected at the first loading, attribute "aria-selected" will not pres
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, viewName);
      LOGGER.LOG.info("Select '" + viewName + "' view");
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name, list of options is displayed.<br>
   * Select option 'Edit Attribute View...' from 'View Options' drop down value using
   * {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}, 'Edit Attribute' dialog is
   * displayed.<br>
   * Select the attribute name and enter the value for the selected attribute.
   *
   * @param attributeName : select name of attribute need to change
   * @param newValue : select/input new value
   * @return true or false.
   */
  public Boolean editArtifactAttribute(final String attributeName, final String newValue) {
    this.driverCustom.anyMatch(By.xpath("//div[@class='header-primary' or @class='jazz-ui-Dialog-heading']"), 60,
        x -> x.getText().equalsIgnoreCase(RMConstants.EDIT_ATTRIBUTES), "Edit Attributes dialog not opened.");
    Dialog dlgEditAttribute =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(RMConstants.EDIT_ATTRIBUTES), this.timeInSecs)
            .getFirstElement();
    LOGGER.LOG.info("The 'Edit Attributes' dialog is displayed");
    Row rowAttribue = null;
    // int size = this.driverCustom.getWebDriver().findElements(By.tagName("iframe")).size();
    // for (int i = 0; i <= size; i++) {
    // this.driverCustom.getWebDriver().switchTo().frame(i);
    // LOGGER.LOG.info("Frame " + i + " is displayed.");
    try {
      rowAttribue =
          this.engine.findElementWithDuration(Criteria.isRow().withText(attributeName).inContainer(dlgEditAttribute),
              this.timeInSecs).getFirstElement();


    }
    catch (Exception e) {
      LOGGER.LOG.info("Element row not found in this frame");
      rowAttribue = this.engine
          .findElementWithDuration(Criteria.isRow().containsText(attributeName).inContainer(dlgEditAttribute),
              this.timeInSecs)
          .getFirstElement();
      // continue;
    }
    Cell cellAttributeName = this.engine
        .findElementWithDuration(Criteria.isCell().inContainer(rowAttribue), this.timeInSecs).getElementByIndex(2);
    String actualTextAttributeName = cellAttributeName.getText().trim();
    LOGGER.LOG.info("Element row found in this frame");
    // break;
    // }
    if (rowAttribue != null) {
      Cell cllAttribute =
          this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowAttribue).withIndex(1), this.timeInSecs)
              .getFirstElement();
      Checkbox cbxAttribute = this.engine
          .findElementWithDuration(Criteria.isCheckbox().inContainer(cllAttribute), this.timeInSecs).getFirstElement();
      cbxAttribute.click();
      LOGGER.LOG.info("Select '" + actualTextAttributeName + "' Check box.");
      Row rowSelectedAttribute = this.engine
          .findElementWithDuration(Criteria.isRow().withText(actualTextAttributeName).inContainer(dlgEditAttribute),
              this.timeInSecs)
          .getFirstElement();
      Dropdown drdAttribute = null;
      Text textField = null;

      try {
        drdAttribute = this.engine.findFirstElement(Criteria.isDropdown().inContainer(rowSelectedAttribute));
      }
      catch (Exception e) {
        LOGGER.LOG.info("Checked Drop down for attribute '" + actualTextAttributeName + "' but drop down not found.");
      }
      if (drdAttribute != null) {
        drdAttribute.selectOptionWithText(newValue);
        LOGGER.LOG.info("Select drop down '" + newValue + "' for '" + actualTextAttributeName + "'");
        return true;
      }
      textField = this.engine.findFirstElement(Criteria.isTextField().inContainer(rowSelectedAttribute));
      textField.setText(newValue);
      LOGGER.LOG.info("Input '" + newValue + "' for '" + actualTextAttributeName + "'");
      return true;
    }
    throw new InvalidArgumentException("'" + attributeName + "'  doesn't exist in the attribute list.");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name, list of options is displayed.<br>
   * Select option 'Edit Attribute View...' from 'View Options' drop down value using
   * {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}, 'Edit Attribute' dialog is
   * displayed.<br>
   * Unselect(uncheck) the selected attribute name.
   *
   * @param attributeName the selected attribute in the Edit Attributes dialog
   */
  public void uncheckAttributeInEditAttibutesDialog(final String attributeName) {
    // waitForPageLoaded();
    Dialog dlgEditAttribute =
        this.engine.findElement(Criteria.isDialog().withTitle("Edit Attributes")).getFirstElement();
    try {
      Row rowAttribue = this.engine
          .findElement(Criteria.isRow().containsText(attributeName).inContainer(dlgEditAttribute)).getFirstElement();
      Cell cllAttribute =
          this.engine.findElement(Criteria.isCell().inContainer(rowAttribue).withIndex(1)).getFirstElement();
      Checkbox cbxAttribute =
          this.engine.findElement(Criteria.isCheckbox().inContainer(cllAttribute)).getFirstElement();
      cbxAttribute.click();
      LOGGER.LOG.info("Unchecked '" + attributeName + "' Attribute in edit attributes dialog");
      waitForSecs(2);
    }
    catch (Exception e) {
      LOGGER.LOG.error(e);
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Verify the attribute name and value for the selected artifact.
   *
   * @param artifactID id of the artifact present inside a module.
   * @param attributeName name of the attribute e.g. Artifact Type,Satisfies, Satisfied By etc....
   * @param attributeValue value for the attribute name.
   * @return true if the attribute name and value added for the artifact.
   */
  public boolean verifyArtifactValuesInModuleView(final String artifactID, final String attributeName,
      final String attributeValue) {
    String columnHeaderAttribute = "";
    String columnHeader = "";
    try {
      columnHeader = String.format("(//td[@role='columnheader'][.//span[contains(text(),'%s')]])[1]", attributeName);
      this.driverCustom.isElementVisible(columnHeader, Duration.ofSeconds(10));
      columnHeaderAttribute = this.driverCustom.getAttribute(columnHeader, "column");
    }
    catch (Exception e) {
      RMLinksPage rmLinksPage = new RMLinksPage(this.driverCustom);
      columnHeaderAttribute = rmLinksPage.getColoumAttributeByHeader(attributeName);
    }
    List<WebElement> listWithArtifactID = this.driverCustom.getWebDriver()
        .findElements(By.xpath(String.format("//a[normalize-space(text()) = '%s']/ancestor::tr/td[@colid='%s']",
            artifactID, columnHeaderAttribute)));
    if (!listWithArtifactID.isEmpty()) {
      List<String> viewRowContent = new ArrayList<>();
      listWithArtifactID.stream().forEach(x -> viewRowContent.add(x.getText().trim()));

      LOGGER.LOG.info("Verify '" + attributeName + "' of '" + artifactID + "' is '" + attributeValue + "'");
      return viewRowContent.get(0).contains(attributeValue);
    }
    throw new InvalidArgumentException(artifactID + " - doesn't not displayed.");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name, list of options is displayed.<br>
   * Select option 'Edit Attribute View...' from 'View Options' drop down value using
   * {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}, 'Edit Attribute' dialog is
   * displayed.<br>
   * Verify 'Apply' button present inside 'Edit Attribute' dialog is enabled.
   *
   * @return true if 'Apply' button is enabled.
   */
  public boolean isApplyButtonEnabled() {
    // waitForPageLoaded();
    List<WebElement> elements = this.driverCustom.getWebElements("//button[text() = 'Apply']");
    boolean isClicked =
        !(elements.isEmpty()) ? elements.get(elements.size() - 1).getAttribute("disabled") != null : false;
    if (isClicked) {
      LOGGER.LOG.info("'Apply' button not enabled");
    }
    else {
      LOGGER.LOG.info("'Apply' button enabled");
    }
    return isClicked;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Search for an existing view in 'Search Views' text box using {@link RMArtifactsPage#searchView(String)}.<br>
   * Select the searched view using {@link RMArtifactsPage#selectView(String)}.<br>
   * Click on 'View Options'icon present next to the selected view name, list of options is displayed.<br>
   * Select option 'Edit Attribute View...' from 'View Options' drop down value using
   * {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}, 'Edit Attribute' dialog is
   * displayed.<br>
   * Select attribute check box which contains text field using
   * {@link RMArtifactsPage#clickOnEditAttributeCheckbox(String)}.<br>
   * Set attribute value for the selected attribute using {@link RMArtifactsPage#setAttributeValue(String, String)}.<br>
   * Clear the provided attribute value.
   *
   * @param attributeName name of attribute.
   * @return {@link Boolean}
   */
  public boolean clearAttributeValue(final String attributeName) {
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_EDITATTRIBUTES_TEXTBOX_XPATH, attributeName);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_EDITATTRIBUTES_TEXTBOX_XPATH, attributeName);
    waitForSecs(2);
    this.driverCustom.getWebDriver().switchTo().activeElement().clear();
    LOGGER.LOG.info("Attribute value cleared from '" + attributeName + "' text field");
    return true;
  }

  /**
   * <p>
   * Verify the message is displayed in Artifacts page.
   *
   * @author KYY1HC
   * @param messageSummary text of message that you want to verify is displayed.
   * @return true if message is displayed, false if message is not displayed.
   */
  public boolean isMessageDisplayed(final String messageSummary) {
    String message = "";
    try {
      message = this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZ_MESSAGE_SUMMARY_XPATH).getText();
      return message.trim().equals(messageSummary);
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
      return false;
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Verify searched artifact is displayed in Artifacts page.
   *
   * @param artifactID is name or id of artifact
   * @return true if artifact is displayed, false if artifact is not displayed
   */
  public boolean isArtifactDisplayed(final String artifactID) {
    try {
      return this.engine.findElementWithDuration(Criteria.isLink().withText(artifactID), this.timeInSecs)
          .getFirstElement() != null;
    }
    catch (Exception e) {
      return false;
    }

  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open searched artifact using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'Edit' button present right side of the artifact page, Artifact can be editable.<br>
   * Add the artifact content in the Artifact.<br>
   * Click on 'Done' button after adding the artifact content.
   *
   * @param artifactContent is text that you want to input for artifact content
   */
  public void inputArtifactContent(final String artifactContent) {
    Text txtArtifactContent =
        this.engine.findElementWithDuration(Criteria.isTextEditor(), this.timeInSecs).getFirstElement();
    txtArtifactContent.clearText();
    txtArtifactContent.setText(artifactContent);
    LOGGER.LOG.info(artifactContent + " -  is added as Artifact content in the Artifact.");
    Button btnDone =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Done"), this.timeInSecs).getFirstElement();
    btnDone.click();
    LOGGER.LOG.info("Clicked on ' Done' button in the Artifacts page.");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box.<br>
   * Hover the mouse for the searched artifact,'Edit Name' icon displayed near to the artifact name.<br>
   * Edit the artifact name in displayed text box.<br>
   * Click on 'Refresh' button present right side top of the artifact page.
   *
   * @param oldArtifactName name of the artifact.
   * @param newArtifactName name of the renamed artifact.
   * @return name of the artifact.
   */
  public String editArtifactName(final String oldArtifactName, final String newArtifactName) {
    String s = newArtifactName;
    WebElement elmModule =
        this.driverCustom.getWebElement("//div[.='" + oldArtifactName + "']/img[contains(@class,'icon')]");
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.moveToElement(elmModule).build().perform();
    Button btnEditName = this.engine.findElement(Criteria.isButton().withToolTip("Edit Name")).getFirstElement();
    btnEditName.click();
    waitForSecs(3);
    WebElement elmTextModuleName = this.driverCustom.getWebElement("//textarea[@value = '" + oldArtifactName + "']");
    elmTextModuleName.sendKeys(Keys.CONTROL, "A");
    waitForSecs(3);
    elmTextModuleName.sendKeys(Keys.DELETE);
    waitForSecs(3);
    elmTextModuleName.sendKeys(s);
    waitForSecs(3);
    elmTextModuleName.sendKeys(Keys.TAB);
    // Click Refresh button
    // Button btnRefresh = this.engine.findElement(Criteria.isButton().withToolTip("Refresh")).getFirstElement();
    // btnRefresh.click();
    waitForSecs(20);
    return s;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'More Actions' icon present top right side in the Artifacts page,list of options displayed.<br>
   * Choose appropriate option from the menu items.
   *
   * @param menuItem menu item to be select from 'More Actions' drop down list.
   * @return menuItem
   */
  public String selectMenuItemFromMoreActions(final String menuItem) {
    waitForPageLoaded();
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement("//a[@title='More Actions']");
    this.driverCustom.getWebElement("//a[@title='More Actions']").click();
    waitForSecs(8);
    LOGGER.LOG.info("Clicked on 'More Actions' button.");

    try {
      Cell cellMenuItem = this.engine.findElement(Criteria.isCell().withText(menuItem)).getFirstElement();
      cellMenuItem.getWebElement().click();
    }
    catch (Exception e) {
      this.driverCustom.isElementVisible(
          "//div[@class='wrapper']/descendant-or-self::span[contains(@id,'MenuItem') and contains(.,'DYNAMIC_VAlUE')]",
          this.timeInSecs, menuItem);
      WebElement cellMenuItem = this.driverCustom.getWebElement(
          "//div[@class='wrapper']/descendant-or-self::span[contains(@id,'MenuItem') and contains(.,'DYNAMIC_VAlUE')]",
          menuItem);
      ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].click()", cellMenuItem);
    }
    waitForSecs(5);
    LOGGER.LOG.info("Work item type " + menuItem + " selected successfully");
    return menuItem;


  }


  /**
   * @param menuItem
   * @return
   */
  @SuppressWarnings("javadoc")
  public String selectMenuItem(final String menuItem) {
    Cell cellMenuItem = this.engine.findElement(Criteria.isCell().withText(menuItem)).getFirstElement();
    cellMenuItem.getWebElement().click();
    waitForSecs(5);
    LOGGER.LOG.info("Work item type " + menuItem + " selected successfully");
    return menuItem;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'More Actions' icon present top right side in the Artifacts page,list of options displayed.<br>
   * Choose 'Manage Tags...' menu item using {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)}, 'Manage
   * Tags' dialog is displayed.<br>
   * Search the tag name in 'Type to filter' text box.<br>
   * Select the searched tag name and click on the tag action what you want to perform 'New Tag','Delete Tag','Edit Tag'
   * etc...<br>
   * If tag action is delete, click on 'Delete Tag' button.<br>
   * Delete Tag pop up displayed.<br>
   * If you want to delete the tag click on 'Delete tag' or else click on 'Cancel' button.
   *
   * @param searchTagName Tag Name.
   * @param tagAction 'New Tag' ,'Delete Tag' ,'Edit Tag'
   * @param deleteTagAction 'Delete tag','Cancel'
   */
  public void manageTags(final String searchTagName, final String tagAction, final String deleteTagAction) {
    // waitForPageLoaded();
    waitForSecs(Duration.ofSeconds(5));
    Dialog dlgManageTags = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Manage Tags"), this.timeInSecs).getFirstElement();
    Text txtSearchTagName = this.engine
        .findFirstElement(Criteria.isTextField().withPlaceHolder("Type to filter").inContainer(dlgManageTags));
    txtSearchTagName.setText(searchTagName);
    waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.getPresenceOfWebElement("//label[text()='" + searchTagName + "']/../div/input[@type='checkbox']");
    waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.getWebElement("//label[text()='" + searchTagName + "']/../div/input[@type='checkbox']").click();
    waitForSecs(Duration.ofSeconds(3));
    LOGGER.LOG.info("Clicked on '" + searchTagName + "' Check box in 'Manage Tags' dialog.");
    Button btnaction =
        this.engine.findElement(Criteria.isButton().withText(tagAction).inContainer(dlgManageTags)).getFirstElement();
    btnaction.click();
    waitForSecs(5);
    LOGGER.LOG.info("Clicked on " + tagAction + " button in 'Manage Tags' dialog.");

    if (this.driverCustom.isElementPresent("//span[text()='Delete Tag']", Duration.ofSeconds(10))) {

      Dialog deletedlg = this.engine.findElement(Criteria.isDialog().withTitle("Delete Tag")).getFirstElement();
      Button btnDeletetag = this.engine
          .findElement(Criteria.isButton().withText(deleteTagAction).inContainer(deletedlg)).getFirstElement();
      btnDeletetag.click();
      waitForSecs(5);

      LOGGER.LOG.info("Clicked on " + deleteTagAction + " button in 'Delete Tag' dialog.");
    }
    else if (this.driverCustom.isElementPresent("//div[text()='Delete Tag']", Duration.ofSeconds(10))) {
      Dialog deletedlg = this.engine.findElement(Criteria.isDialog().withTitle("Delete Tag")).getFirstElement();
      Button btnDeletetag = this.engine
          .findElement(Criteria.isButton().withText(deleteTagAction).inContainer(deletedlg)).getFirstElement();
      btnDeletetag.click();
      waitForSecs(5);

      LOGGER.LOG.info("Clicked on " + deleteTagAction + " button in 'Delete Tag' dialog.");
    }
    else {
      LOGGER.LOG.info("Not clicked on " + deleteTagAction +
          " button in 'Delete Tag' dialog.\n or \n 'Delete Tag' dialog not found.");

    }

  }


  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'More Actions' icon present top right side in the Artifacts page,list of options displayed.<br>
   * Choose 'Manage Tags...' menu item using {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)}, 'Manage
   * Tags' dialog is displayed.<br>
   * Click on 'New Tag' button, 'New Tag' dialog is displayed. <br>
   * Provide Tag Name,Tag Type,Tag Description in 'New Tag' dialog.<br>
   * Click on 'OK' button.
   *
   * @param tagName name of the tag.
   * @param tagDescription description of the tag.
   */
  public void createNewTag(final String tagName, final String tagDescription) {
    waitForSecs(2);
    Dialog dlgSelectTags = this.engine.findElement(Criteria.isDialog().withTitle("Select Tags")).getFirstElement();
    Link lnkNewTag =
        this.engine.findElement(Criteria.isLink().withText("New Tag").inContainer(dlgSelectTags)).getFirstElement();
    lnkNewTag.click();
    waitForSecs(2);
    Dialog newTagDlg = this.engine.findElement(Criteria.isDialog().withTitle("New Tag")).getFirstElement();
    Text txtTagName =
        this.engine.findElement(Criteria.isTextField().hasLabel("Tag Name:").inContainer(newTagDlg)).getFirstElement();
    txtTagName.setText(tagName);
    LOGGER.LOG.info("Added tag name as - " + tagName);
    waitForSecs(2);
    RadioButton rdoTagType =
        this.engine.findElement(Criteria.isRadioButton().inContainer(newTagDlg)).getElementByIndex(2);
    rdoTagType.click();
    LOGGER.LOG.info("Clicked on 'Shared' radio button in 'New Tag' dialog.");
    Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK").inContainer(newTagDlg)).getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("Clicked on 'OK' button in 'New Tag' dialog.");
    waitForSecs(2);
    if (this.driverCustom.isElementPresent("//*[text()='Duplicate Tag Name']", this.timeInSecs)) {
      Dialog duplicateTagDlg =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Duplicate Tag Name"), this.timeInSecs)
              .getFirstElement();
      Button dupBtnOK =
          this.engine.findElement(Criteria.isButton().withText("OK").inContainer(duplicateTagDlg)).getFirstElement();
      dupBtnOK.click();
      Button newbtnCancel =
          this.engine.findElement(Criteria.isButton().withText("Cancel").inContainer(newTagDlg)).getFirstElement();
      newbtnCancel.click();
      LOGGER.LOG.info("Clicked on 'Cancel' button in 'New Tag' dialog.");

    }


  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Search existing Artifacts,Modules,Collections in quick search text box using
   * {@link RMDashBoardPage#quickSearch(String)}.<br>
   * Open searched artifact using {@link RMDashBoardPage#openSearchedSpecification(String)}.<br>
   * Get the text of the tag present right side in 'Overview' section of the artifact.
   *
   * @param attributeId ID of the requirement management attributes.
   * @return value of the attribute.
   */
  public String getRMAttributeTagValue(final String attributeId) {
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement("//span[text()='DYNAMIC_VAlUE:']", attributeId);
    waitForSecs(5);
    LOGGER.LOG.info("Checked visibility of artifact id '" + attributeId + "' In 'Selected Artifact' section");
    waitForSecs(5);
    String tagVal = this.driverCustom
        .getWebElement("//span[text()='DYNAMIC_VAlUE:']/../../..//div[@dojoattachpoint='_tagSectionDiv']", attributeId)
        .getText();
    LOGGER.LOG.info("Artifact id '" + attributeId + "' Tag value is '" + tagVal + "' In 'Selected Artifact' section");
    return tagVal;
  }


  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open searched artifact using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on the image menu launcher from the searched artifact.<br>
   * Select the artifact with artifact id, list of options is displayed.<br>
   * Click on 'Delete Artifact...' option from the drop down list, 'Delete Artifact' dialog is displayed.<br>
   * Click on 'Confirm Deletion' option to delete the artifact.
   *
   * @param artifactID id of the artifact to delete.
   * @param isModule true if the artifact want to delete is a module/collect which containing child artifacts.
   */
  public void deleteArtifactFromSearchedSpecification(final String artifactID, final String isModule) {
    Row rowArtifact =
        this.engine.findElementWithDuration(Criteria.isRow().withText(artifactID), this.timeInSecs).getFirstElement();
    Checkbox checkbox = this.engine
        .findElementWithDuration(Criteria.isCheckbox().inContainer(rowArtifact), this.timeInSecs).getFirstElement();
    checkbox.click();
    // try {
    // Dropdown drdAction = this.engine
    // .findElementWithDuration(Criteria.isDropdown().inContainer(rowArtifact), this.timeInSecs).getFirstElement();
    // drdAction.selectOptionWithText("Remove Artifact...");
    // clickOnDialogButton("Confirm Removal", "Remove");
    // System.out.println("Removed Artifact is : "+artifactID);
    // }
    // catch (Exception e) {


    Dropdown drdAction = this.engine
        .findElementWithDuration(Criteria.isDropdown().inContainer(rowArtifact), this.timeInSecs).getFirstElement();
    drdAction.selectOptionWithText("Delete Artifact...");
    clickOnDialogButton("Confirm Deletion", "Delete Artifact");
    if (this.driverCustom.isElementPresent(RMConstants.RMARTIFACTPAGE_CONFIRMDELETION_DIALOG_XPATH,
        Duration.ofSeconds(10))) {
      clickOnDialogButton("Confirm Deletion", "Delete Artifact");
      this.driverCustom.isElementInvisible("//div[text()='Deleting artifact(s)...']",
          Duration.ofSeconds((this.timeInSecs.getSeconds() / 3)));
      // }
    }

    // if (isModule.equalsIgnoreCase("true")) {
    // clickOnDialogButton("Confirm Deletion", "Delete Artifact");
    // }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Click on 'Create' drop down menu using {@link RMArtifactsPage#clickOnCreateButton(String)}. <br>
   * Click on the option 'Import Artifact...' from 'Create' drop down menu. <br>
   * 'Import' window is displayed.<br>
   * Select import type as 'Import requirements from a CSV file or spreadsheet'.<br>
   * Click on 'Next' button, Click on 'Browse..' button.<br>
   * Select the file type as csv or xlsx file path.<br>
   * In Specific location, select 'Import requirements into a module'.<br>
   * Click on 'Pickup Module...' button.<br>
   * Search module id in 'Type to filter artifacts by text or by ID' and press Enter<br>
   * Select the module displayed and click OK button.<br>
   * Select import option 'Update artifacts that match entries, and ignore new entries'<br>
   * Click on 'Finish' button.<br>
   * Verify the displayed message 'The document has been submitted and is being processed.'.<br>
   * and the successful message 'The import of the comma-separated values (CSV) or spreadsheet file is complete.'<br>
   * is displayed after that<br>
   *
   * @author KYY1HC
   * @param fileName file Name.
   * @param moduleId id of module
   * @param moduleName name of module (contents)
   * @param importOption value is 1 of 3 options: "Create new artifacts for all entries"; "Update artifacts that match
   *          entries, and create artifacts for new entries"; "Update artifacts that match entries, and ignore new
   *          entries"
   * @return result - true if the successful message is displayed
   */
  public boolean importRequirementsFromACSVFileOrSpreadsheetIntoAModule(final String fileName, final String moduleId,
      final String moduleName, final String importOption) {
    String successfulMsg = "The import of the comma-separated values (CSV) or spreadsheet file is complete.";
    String importFileAbsolutePath = "";
    if (!fileName.contains("automatedBrowserDownloads")) {
      importFileAbsolutePath = Paths.get(RMConstants.IMPORT_FILE_LOCATION + fileName).toAbsolutePath().toString();
    }
    else {
      importFileAbsolutePath = fileName;
    }

    // Select radio option "Import requirements from a CSV file or spreadsheet"
    this.driverCustom.click("//input[@id='csvImportWizardType']");
    // Click on Next button
    Button btnNext1 = this.engine.findElement(Criteria.isButton().withText("Next >")).getFirstElement();
    btnNext1.click();
    // Browse... import file
    JavascriptExecutor jse = (JavascriptExecutor) this.driverCustom.getWebDriver();
    WebElement inputUpload = this.driverCustom.getWebDriver().findElement(By.xpath("//input[@name='dataStream']"));
    jse.executeScript("arguments[0].style='display: block;'", inputUpload);
    inputUpload = this.driverCustom.getClickableWebElement(inputUpload);
    inputUpload.sendKeys(importFileAbsolutePath);
    waitForSecs(2);
    LOGGER.LOG.info("File is selected!");
    this.driverCustom.click("//input[@id='com_ibm_rdm_web_csv_CsvImportDocumentStep_0_module']");
    LOGGER.LOG.info("Selected 'Import requirements into a module' specific location.");
    Button btnPickModule = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Pick Module..."), this.timeInSecs).getFirstElement();
    btnPickModule.click();
    LOGGER.LOG.info("Click on 'Pick Module...' button successfully");

    waitForSecs(5);
    Dialog dlgSelectOrCreateAModule =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Select or create a module"), this.timeInSecs)
            .getFirstElement();
    LOGGER.LOG.info("'Select or create a module' dialog opened.");
    waitForSecs(2);
    Text txtSearch =
        this.engine.findElement(Criteria.isTextField().withPlaceHolder("Type to filter artifacts by text or by ID")
            .inContainer(dlgSelectOrCreateAModule)).getFirstElement();
    txtSearch.setText(moduleId + Keys.ENTER);
    LOGGER.LOG.info(moduleId + " Id entered in filter and pressed enter key.");
    waitForSecs(5);
    // Select attribute
    Label lblAttributeType =
        this.engine.findElementWithDuration(Criteria.isLabel().withText(moduleName), this.timeInSecs).getFirstElement();
    lblAttributeType.click();
    // Click on OK button
    Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("Clicked on 'OK' button");
    this.waitForSecs(3);
    // Choose import option
    WebElement btnImportOption = this.driverCustom.getPresenceOfWebElement("//label[text()='" + importOption + "']");
    this.driverCustom.getClickableWebElement(btnImportOption).click();
    LOGGER.LOG.info("Selected on " + importOption + " import option");
    // Click on Finish button
    Button btnFinish = this.engine.findElement(Criteria.isButton().withText("Finish")).getFirstElement();
    btnFinish.click();
    LOGGER.LOG.info("Clicked on 'Finish' button");
    // Verify import successfully
    String xpathSuccessMessage =
        "//div[@class='messageArea OK']/div[@class='messageSummary' and text()='" + successfulMsg + "']";

    return this.driverCustom.isElementVisible(xpathSuccessMessage, Duration.ofSeconds(60));
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Click on 'Create' drop down menu using {@link RMArtifactsPage#clickOnCreateButton(String)}. <br>
   * Click on the option 'Import Artifact...' from 'Create' drop down menu. <br>
   * 'Import' window is displayed.<br>
   * Select import type as 'Import requirements from a migration package'.<br>
   * Click on 'Next' button,'Select Package File' title page is displayed.<br>
   * Select the file type as csv,xlsx,text document etc... except type migiz from provided file path.<br>
   * Click on 'Next' button.<br>
   * Click on 'Upload' button.<br>
   * Verify the displayed error message 'Only files of the following type are allowed: migiz'.<br>
   * Click on 'Close' button.
   *
   * @param fileName file Name.
   * @return result - is true if the actual error message is similar to expected error message
   */
  public boolean importRequirementsFromAMigrationPackageWithError(final String fileName) {
    // waitForPageLoaded();
    String errorMsg = "Only files of the following type are allowed: migiz";
    Map<String, String> additionalParams = new LinkedHashMap();
    String importFileAbsolutePath = Paths.get(RMConstants.IMPORT_FILE_LOCATION + fileName).toAbsolutePath().toString();
    additionalParams.put(RMConstants.FILETYPE, importFileAbsolutePath);

    this.driverCustom.click("//strong[text()='" + RMConstants.MIGRATION_IMPORT + "']/preceding::input[1]");

    // Click on Next button
    Button btnNext1 = this.engine.findElement(Criteria.isButton().withText("Next >")).getFirstElement();
    btnNext1.click();

    // Click on Upload button
    performUpload(additionalParams);
    boolean result = this.driverCustom.isElementVisible(
        "//div[@class='messageArea ERROR']/div[@class='messageSummary' and text()='" + errorMsg + "']",
        Duration.ofSeconds(10));
    if (this.driverCustom.isElementPresent(RMConstants.RMLINKSPAGE_CANCEL_BUTTON_XPATH, Duration.ofSeconds(20))) {
      Button btnClose = this.engine.findElement(Criteria.isButton().withText("Cancel")).getFirstElement();
      btnClose.click();
    }
    else {
      Button btnClose = this.engine.findElement(Criteria.isButton().withText("Close")).getFirstElement();
      btnClose.click();
    }
    return result;
  }

  /**
   * <p>
   * This method will read the data from the downloaded PDF file. <br>
   * Get the directory path using {@link RMArtifactsPage#getDownloadedPDFFileNameAndPath(String)} and
   * {@link RMArtifactsPage#getDownloadedFileNameAndPath()} based on requirement.
   *
   * @param directoriesPath file path.
   * @return file content.
   * @throws IOException throws exception.
   */
  public String getFileData(final String directoriesPath) throws IOException {

    File file = new File(directoriesPath);
    PDDocument document = PDDocument.load(file);

    PDFTextStripper pdfStripper = new PDFTextStripper();
    for (PDPage page : document.getPages()) {
      List<PDAnnotation> annotations = page.getAnnotations();

      PDFTextStripperByArea stripper = new PDFTextStripperByArea();
      for (int i = 0; i < annotations.size(); i++) {
        PDAnnotation annotation = annotations.get(i);
        if (annotation instanceof PDAnnotationLink) {
          PDAnnotationLink link = (PDAnnotationLink) annotation;
          PDRectangle rect = link.getRectangle();
          float y = rect.getUpperRightY();
          if (page.getRotation() == 0) {
            y = page.getMediaBox().getHeight() - y;
          }
          Rectangle2D.Float awtRect = new Rectangle2D.Float(rect.getLowerLeftX(), y, rect.getWidth(), rect.getHeight());
          stripper.addRegion("" + i, awtRect);
        }
      }
      stripper.extractRegions(page);
      for (PDAnnotation annotation : annotations) {
        if (annotation instanceof PDAnnotationLink) {
          PDAnnotationLink link = (PDAnnotationLink) annotation;
          PDAction action = link.getAction();
          if (action instanceof PDActionURI) {

          }
        }

      }
    }
    // Retrieving text from PDF document
    String text = pdfStripper.getText(document);
    // Closing the document
    document.close();
    return text;
  }

  /**
   * @param attributeName 'Generate a report'
   * @return attribute Name
   */
  public String clickOnGenerateAreportFromReportPage(final String attributeName) {
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement("//div[@class='task1Action']//a[text()='" + attributeName + "']");
    this.driverCustom.getPresenceOfWebElement("//div[@class='task1Action']//a[text()='Generate a report']").click();
    LOGGER.LOG.info("'Generate a report' selected from report page successfully.");
    waitForSecs(5);
    return attributeName;

  }


  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'More Actions' icon present top right side in the Artifacts page,list of options displayed.<br>
   * Choose 'Generate Report for view' menu item using {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)},
   * 'Create Report' dialog is displayed.<br>
   * Select the report type from 'Select the Report Type' section in 'Create Report' dialog.
   *
   * @param reportType type of report like 'Print Module Book','Print Module Table','Traceability Report' etc...
   * @return reportType.
   */
  public String selectAvailableReportType(final String reportType) {
    this.engine.findElementWithDuration(Criteria.isDialog().withTitle(RMConstants.CREATE_A_REPORT), this.timeInSecs)
        .getFirstElement();
    LOGGER.LOG.info("The 'Create a Report' dialog is displayed.");
    waitForSecs(2);
    // Tractability report
    this.driverCustom.getPresenceOfWebElement("//span[text()='" + reportType + "']").click();
    LOGGER.LOG.info(reportType + " Selected from 'Create a Report' dialog.");
    waitForSecs(6);
    return reportType;

  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'More Actions' icon present top right side in the Artifacts page,list of options displayed.<br>
   * Choose 'Generate Report for view' menu item using {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)},
   * 'Create Report' dialog is displayed.<br>
   * Select the report type as 'Traceability Report' from 'Select the Report Type' section in 'Create Report' dialog
   * using {@link RMArtifactsPage#selectAvailableReportType(String)}.<br>
   * Click on 'Next >' button, 'Select the Artifacts' section is displayed.<br>
   * Click on 'Add...' button to add the artifacts in the report,'Add artifacts to report' dialog is displayed.<br>
   * Search the existing artifact in 'Type to filter artifacts by text or by ID' text box.<br>
   * Click on searched artifact and click on 'OK' button.
   *
   * @param attributeAction Add...
   * @param artifactID id of the artifact/module.
   * @param artifactName name of artifact.
   * @return artifactName.
   */
  public String addArtifactsInTraceabilityReport(final String attributeAction, final String artifactID,
      final String artifactName) {
    waitForSecs(2);
    this.driverCustom.getWebElement("//button[text()='" + attributeAction + "']").click();
    LOGGER.LOG.info("Clicked on : " + attributeAction + " Button");
    waitForSecs(5);
    Dialog dlgreportArtifact =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Add artifacts to report"), this.timeInSecs)
            .getFirstElement();
    LOGGER.LOG.info("'Add artifacts to report' dialog opened.");
    waitForSecs(5);
    Text txtSearch = this.engine.findElement(Criteria.isTextField()
        .withPlaceHolder("Type to filter artifacts by text or by ID").inContainer(dlgreportArtifact)).getFirstElement();
    txtSearch.setText(artifactID + Keys.ENTER);
    LOGGER.LOG.info(artifactID + " Id entered in filter and clicked enter.");
    waitForSecs(10);
    try {

      this.driverCustom.getPresenceOfWebElement("//a[text()='" + artifactID + ":']/..//span[@class='entry-label']")
          .click();
      LOGGER.LOG.info("'" + artifactID + "' Selected");
    }
    catch (Exception e) {
      // TODO: handle exception


      // Select attribute
      Label lblAttributeType = this.engine
          .findElementWithDuration(Criteria.isLabel().withText(artifactName), this.timeInSecs).getFirstElement();
      lblAttributeType.click();
    }
    LOGGER.LOG.info("'" + artifactName + "' Selected");
    waitForSecs(5);

    return artifactName;

  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'More Actions' icon present top right side in the Artifacts page,list of options displayed.<br>
   * Choose 'Generate Report for view' menu item using {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)},
   * 'Create Report' dialog is displayed.<br>
   * Select the report type as 'Traceability Report' from 'Select the Report Type' section in 'Create Report' dialog
   * using {@link RMArtifactsPage#selectAvailableReportType(String)}.<br>
   * Click on 'Next >' button, 'Select the Artifacts' section is displayed, 'Add' or 'Remove' the artifacts from the
   * report.<br>
   * Click on 'Next >' button, 'Report Information' section is displayed.<br>
   * Provide Report Name in 'Name' text box.<br>
   * Enable the check box 'Append date/time information to the report name'.<br>
   * Provide Author Name,Company Name in Report Information section.
   *
   * @param params store key values for: ("reportName", <NAME_OF_REPORT>) <br>
   *          ("appendDateTime", <IS_APPEND_DATE_TIME_IN_THE_REPORT_NAME>) - if true - checked checkbox <br>
   *          ("reportFormat", <FORMAT_OF_REPORT>) <br>
   *          ("reportType", <TYPE_OF_REPORT>) <br>
   * @return report Name.
   */
  public String addReportInformationWithDateandTime(final Map<String, String> params) {
    String reportName = params.get("reportName");
    String reportFormat = params.get("reportFormat");
    String reportType = params.get("reportType");
    String isAppendDataTime = params.get("appendDateTime");
    String companyName = params.get("companyName");
    String authorName = params.get("authorName");
    waitForSecs(2);
    // Report name
    WebElement reportNameElm = this.driverCustom.getWebElement("//input[contains(@id,'dijit_form_ValidationTextBox')]");
    reportNameElm.clear();
    waitForSecs(1);
    reportNameElm.sendKeys(reportName);
    LOGGER.LOG.info("Report name '" + reportName + "' entered successfully.");
    waitForSecs(2);
    this.driverCustom
        .getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_ADD_APPENDDATETIME_INFORMATION_TO_THE_REPORTNAME_XPATH);
    LOGGER.LOG.info("Verified presence of 'Append date/time information to the report name' check box");
    String chkBox = this.driverCustom.getAttribute(
        RMConstants.RMARTIFACTSPAGE_ADD_APPENDDATETIME_INFORMATION_TO_THE_REPORTNAME_XPATH, RMConstants.ARIA_CHECKED);
    if (!chkBox.equals(isAppendDataTime)) {
      this.driverCustom
          .getWebElement(RMConstants.RMARTIFACTSPAGE_ADD_APPENDDATETIME_INFORMATION_TO_THE_REPORTNAME_XPATH).click();
      waitForSecs(3);
      if (chkBox.equals("false")) {
        LOGGER.LOG.info(
            "'Append date/time information to the report name' check box selected successfully under 'Report Information' section.");
      }
      else {
        LOGGER.LOG.info(
            "'Append date/time information to the report name' check box is deselected successfully under 'Report Information' section.");
      }
    }

    // Format of report
    waitForSecs(2);
    String reportFormatDisplayed =
        this.driverCustom.getText("//div[@dojoattachpoint='_reportTypeNode']//span[@role='option']");
    if (!reportFormatDisplayed.equals(reportFormat)) {
      Dialog dlgCreateReport = this.engine
          .findElementWithDuration(Criteria.isDialog().withTitle(RMConstants.CREATE_A_REPORT), this.timeInSecs)
          .getFirstElement();
      Dropdown dropdownType =
          this.engine.findElement(Criteria.isDropdown().inContainer(dlgCreateReport)).getFirstElement();
      dropdownType.selectOptionWithText(reportFormat);
      waitForSecs(2);
    }

    if (reportType.equals("Traceability Report")) {
      // Input Company Name
      this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_COMPANYNAME_IN_REPORT_XPATH).clear();
      waitForSecs(2);
      this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_COMPANYNAME_IN_REPORT_XPATH).sendKeys(companyName);
      LOGGER.LOG.info("'" + companyName + "' Company Name Entered Successfully.");
      waitForSecs(2);

      // Input author Name
      this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_AUTHORNAME_IN_REPORT_XPATH).clear();
      waitForSecs(2);
      this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_AUTHORNAME_IN_REPORT_XPATH).click();
      waitForSecs(2);
      this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_AUTHORNAME_IN_REPORT_XPATH).sendKeys(authorName);
      LOGGER.LOG.info("'" + authorName + "' Author Name Entered Successfully.");
      waitForSecs(10);
    }
    return reportName;

  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'More Actions' icon present top right side in the Artifacts page,list of options displayed.<br>
   * Choose 'Generate Report for view' menu item using {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)},
   * 'Create Report' dialog is displayed.<br>
   * Select the report type as 'Traceability Report' from 'Select the Report Type' section in 'Create Report' dialog
   * using {@link RMArtifactsPage#selectAvailableReportType(String)}.<br>
   * Click on 'Next >' button, 'Select the Artifacts' section is displayed, 'Add' or 'Remove' the artifacts from the
   * report.<br>
   * Click on 'Next >' button, 'Report Information' section is displayed.<br>
   * Provide Report Name in 'Name' text box.<br>
   * Provide Author Name,Company Name in Report Information section.
   *
   * @param reportName name of report.
   * @param reportType type of report.
   * @param authorName name of author.
   * @param companyName name of company.
   * @return report Name.
   */
  public String addReportInformationWithoutDateandTime(final String reportName, final String reportType,
      final String authorName, final String companyName) {
    waitForSecs(2);
    // Report name
    this.driverCustom.getWebElement("//input[@id='dijit_form_ValidationTextBox_0']").sendKeys(reportName);
    LOGGER.LOG.info("Report name '" + reportName + "' Entered successfully.");
    LOGGER.LOG.info(" Date time check box not selected.");
    // Report type
    waitForSecs(2);
    Dialog dlg =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(RMConstants.CREATE_A_REPORT), this.timeInSecs)
            .getFirstElement();
    waitForSecs(2);
    Dropdown drdType = this.engine.findElement(Criteria.isDropdown().inContainer(dlg)).getFirstElement();
    drdType.selectOptionWithText(reportType);
    waitForSecs(2);

    // Input Company Name
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_COMPANYNAME_IN_REPORT_XPATH).clear();
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_COMPANYNAME_IN_REPORT_XPATH).sendKeys(companyName);
    LOGGER.LOG.info("'" + companyName + "' Company Name entered Successfully.");
    waitForSecs(2);
    // Input author Name
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_AUTHORNAME_IN_REPORT_XPATH).clear();
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_AUTHORNAME_IN_REPORT_XPATH).click();
    waitForSecs(2);
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_AUTHORNAME_IN_REPORT_XPATH).sendKeys(authorName);
    LOGGER.LOG.info("'" + authorName + "' Author Name entered Successfully.");
    waitForSecs(2);
    return reportName;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'More Actions' icon present top right side in the Artifacts page,list of options displayed.<br>
   * Choose 'Generate Report for view' menu item using {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)},
   * 'Create Report' dialog is displayed.<br>
   * Select the report type as 'Traceability Report' from 'Select the Report Type' section in 'Create Report' dialog
   * using {@link RMArtifactsPage#selectAvailableReportType(String)}.<br>
   * Click on 'Next >' button, 'Select the Artifacts' section is displayed, 'Add' or 'Remove' the artifacts from the
   * report.<br>
   * Click on 'Next >' button, 'Report Information' section is displayed.<br>
   * Provide Report Name, Author Name,Company Name in Report Information section using
   * {@link RMArtifactsPage#addReportInformationWithDateandTime(Map)} and
   * {@link RMArtifactsPage#addReportInformationWithoutDateandTime(String, String, String, String)}.<br>
   * Click on 'Next >' button, 'Save Report to Project' section is displayed.<br>
   * Enable check box 'Save report to project' present in top left corner in 'Save Report to Project' section.
   *
   * @return true if check box selected.
   */
  public String selectSaveReportToProjectCheckBox() {
    waitForSecs(4);
    // Select Check Box

    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_SAVE_REPORT_TO_PROJECT_XPATH);
    LOGGER.LOG.info("Verified presence of check box");
    String chkBox = this.driverCustom.getAttribute(RMConstants.RMARTIFACTSPAGE_SAVE_REPORT_TO_PROJECT_XPATH,
        RMConstants.ARIA_CHECKED);
    if (chkBox.equals("false")) {
      this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_SAVE_REPORT_TO_PROJECT_XPATH).click();
      waitForSecs(3);

      LOGGER.LOG
          .info("'Save report to project' check box selected Successfully under 'Save Report to Project' section.");
    }
    else {
      LOGGER.LOG.info("'Save report to project' check box unselected under 'Save Report to Project' section.");

      waitForSecs(3);
    }
    waitForSecs(3);
    return this.driverCustom.getAttribute(RMConstants.RMARTIFACTSPAGE_SAVE_REPORT_TO_PROJECT_XPATH,
        RMConstants.ARIA_CHECKED);


  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'More Actions' icon present top right side in the Artifacts page,list of options displayed.<br>
   * Choose 'Generate Report for view' menu item using {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)},
   * 'Create Report' dialog is displayed.<br>
   * Select the report type as 'Traceability Report' from 'Select the Report Type' section in 'Create Report' dialog
   * using {@link RMArtifactsPage#selectAvailableReportType(String)}.<br>
   * Click on 'Next >' button, 'Select the Artifacts' section is displayed, 'Add' or 'Remove' the artifacts from the
   * report.<br>
   * Click on 'Next >' button, 'Report Information' section is displayed.<br>
   * Provide Report Name, Author Name,Company Name in Report Information section using
   * {@link RMArtifactsPage#addReportInformationWithDateandTime(Map)} and
   * {@link RMArtifactsPage#addReportInformationWithoutDateandTime(String, String, String, String)}.<br>
   * Click on 'Next >' button, 'Save Report to Project' section is displayed.<br>
   * Unselect check box 'Save report to project' present in top left corner in 'Save Report to Project' section.
   *
   * @return attribute value.
   */
  public String unSelectSaveReportToProjectCheckBox() {
    waitForSecs(4);
    // Select Check Box
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_SAVE_REPORT_TO_PROJECT_XPATH);
    LOGGER.LOG.info("Verified presence of check box");
    String chkBox =
        this.driverCustom.getAttribute(RMConstants.RMARTIFACTSPAGE_SAVE_REPORT_TO_PROJECT_XPATH, "aria-checked");
    if (chkBox.equals("true")) {
      this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_SAVE_REPORT_TO_PROJECT_XPATH).click();
      waitForSecs(3);
      LOGGER.LOG.info("'Save report to project' check box selected under 'Save Report to Project' section.");
    }
    else {
      LOGGER.LOG
          .info("'Save report to project' check box selected Successfully under 'Save Report to Project' section.");

      waitForSecs(3);
    }
    return chkBox;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'More Actions' icon present top right side in the Artifacts page,list of options displayed.<br>
   * Choose 'Generate Report for view' menu item using {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)},
   * 'Create Report' dialog is displayed.<br>
   * Select the report type as 'Traceability Report' from 'Select the Report Type' section in 'Create Report' dialog
   * using {@link RMArtifactsPage#selectAvailableReportType(String)}.<br>
   * Click on 'Next >' button, 'Select the Artifacts' section is displayed, 'Add' or 'Remove' the artifacts from the
   * report.<br>
   * Click on 'Next >' button, 'Report Information' section is displayed.<br>
   * Provide Report Name, Author Name,Company Name in Report Information section using
   * {@link RMArtifactsPage#addReportInformationWithDateandTime(Map)} and
   * {@link RMArtifactsPage#addReportInformationWithoutDateandTime(String, String, String, String)}.<br>
   * Click on 'Next >' button, 'Save Report to Project' section is displayed.<br>
   * Unselect check box 'Save report to project' present in top left corner in 'Save Report to Project' section using
   * {@link RMArtifactsPage#uncheckAttributeInEditAttibutesDialog(String)}.<br>
   * * Enable check box 'Save report to project' present in top left corner in 'Save Report to Project' section using
   * {@link RMArtifactsPage#selectSaveReportToProjectCheckBox()}.<br>
   * Click on 'Next >' button, 'Generate the Report' section is displayed.<br>
   * Click on 'Finish' button.<br>
   * Verify the successful message 'Report generation complete'.
   *
   * @param message to verify report generation.
   * @return message.
   */
  public String getValidationMessageFromReportGenerationMessageArea(final String message) {
    boolean isDisplayed =
        this.driverCustom.isElementVisible(String.format("//span[text()='%s']", message), this.timeInSecs);
    if (isDisplayed) {
      LOGGER.LOG.info(String.format("Message: '%s' is displayed", message));
      return message;
    }
    String failedMsg = String.format("Message: '%s' is not displayed", message);
    fail(failedMsg);
    return failedMsg;
  }

  /**
   * ThermoSystems (RM)
   *
   * @param area -
   * @return -
   */
  public boolean isReportSavedInSelectedArea(final String area) {
    waitForSecs(10);
    this.driverCustom.getPresenceOfWebElement("//div[@class='indent' and text()='" + area + "' ]");

    String area1 =
        this.driverCustom.getPresenceOfWebElement("//div[@class='indent' and text()='" + area + "' ]").getText();
    if (area1.contains(area)) {
      LOGGER.LOG.info("Report seved in '" + area + "' area.");
    }
    else {
      LOGGER.LOG.info("Report not seved in '" + area + "' area.");
      return false;
    }
    LOGGER.LOG.info("Report seved in '" + area + "' area");
    return true;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'More Actions' icon present top right side in the Artifacts page,list of options displayed.<br>
   * Choose 'Generate Report for view' menu item using {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)},
   * 'Create Report' dialog is displayed.<br>
   * Generate a Traceability Report with all the necessary details.<br>
   * Once the report is generated Click on the link 'Download for viewing' to download the pdf file.<br>
   * Get the downloaded file name and path using {@link RMArtifactsPage#getDownloadedFileNameAndPath()}.
   *
   * @return pdfFile.
   */
  public String clickOnDownloadForViewingLink() {
    waitForSecs(10);
    // Download for viewing
    this.driverCustom.getPresenceOfWebElement("//a[text()='Download for viewing ']").click();
    waitForSecs(15);
    LOGGER.LOG.info("Clicked on Download for viewing link");
    String pdfFile = getDownloadedFileNameAndPath();
    waitForSecs(15);
    return pdfFile;
  }

  /**
   * <p>
   * Provides the downloaded PDF file name and path. <br>
   * This method used after calling {@link #clickOnDownloadForViewingLink()} and with respect to report generate
   * {@link #selectOpenTheReportWhenTheWizardIsClosedCheckBox()}.
   *
   * @author VUP5HC
   * @return string path file.
   */
  public String getDownloadedFileNameAndPath() {
    String directoriesPath = this.driverCustom.getDownloadFolderLocation();
    File dir = new File(directoriesPath);
    File[] files = dir.listFiles();
    // Will wait maximum 10 minutes (600s) to make sure exported file is downloaded
    for (int i = 0; i < 600; i++) {
      files = dir.listFiles();
      if ((files == null) || (files.length == 0)) {
        waitForSecs(10);
      }
    }
    // Throw Exception if file is not downloaded instead of null for debug/tracebility
    if ((files == null) || (files.length == 0)) {
      throw new NullPointerException("Not found any downloaded file in directory: " + directoriesPath);
    }
    File lastModifiedFile = files[0];
    for (int i = 1; i < files.length; i++) {
      if (lastModifiedFile.lastModified() < files[i].lastModified()) {
        lastModifiedFile = files[i];
      }
    }
    LOGGER.LOG.info("File downloaded path is : " + lastModifiedFile.getAbsolutePath());
    return lastModifiedFile.getAbsolutePath();
  }

  /**
   * <p>
   * This method used when user download pdf file from Reports tab on Requirement project area page.
   *
   * @param reportName name of the report.
   * @return report Name
   */
  public String getDownloadedPDFFileNameAndPath(final String reportName) {
    waitForSecs(5);
    String path = this.driverCustom.getDownloadFolderLocation();
    LOGGER.LOG.info("Download Folder Location  " + path);
    LOGGER.LOG.info("Download file name as:  " + reportName + ".pdf");
    String directoriesPath = this.driverCustom.getDownloadFolderLocation();
    String pdffile = directoriesPath + "\\" + reportName + ".pdf";
    LOGGER.LOG.info("File Download path and name as : " + pdffile);
    return pdffile;

  }

  /**
   * <p>
   * Verify mentioned datas are available in downloaded pdf file.
   *
   * @param pdffilePath file path.
   * @param expectedData expected Data.
   * @return {@link Boolean}
   * @throws IOException exception
   */
  public boolean isDataAvailableInPdfFile(final String pdffilePath, final String expectedData) throws IOException {

    waitForSecs(2);
    getFileData(pdffilePath);
    String pdfdata = getFileData(pdffilePath).toString();
    if (pdfdata.contains(expectedData)) {
      LOGGER.LOG.info("'" + expectedData + "' Available in pdf file.");
    }
    else {
      LOGGER.LOG.info("Automate PDF report '" + expectedData + "' not available in pdf file.");
      return false;
    }

    return true;
  }


  /**
   * <p>
   * Verifies all the RM links available in downloaded PDF file.
   *
   * @param pdfFilePath down loaded file path to get file path to get file path used getDownloadedPDFFileNameAndPath()
   *          and Stores In new variable.
   * @param rmLinks get from getAllLinksFromSideBarArea() this is available in RMLinksPage.
   * @return {@link Boolean}
   * @throws IOException throws exception.
   */
  public boolean isRmLinksAvailableInPdfFile(final String pdfFilePath, final String rmLinks) throws IOException {

    String sb1 = rmLinks;

    LOGGER.LOG.info("************ Validating RM Links Available In pdf File ****************");
    LOGGER.LOG.info("---------------------------------------------------");
    LOGGER.LOG.info("All RM Links/artifacts from view  >>---->>> " + sb1);
    LOGGER.LOG.info("---------------------------------------------------\n");
    LOGGER.LOG.info("-----> Down loaded pdf File data loaded successfully to validate <-----\n");
    String[] s = sb1.split(",");
    for (String ss : s) {
      String sstImplementedBy;
      String[] sst = ss.split(": ");
      String pdfdata = getFileData(pdfFilePath).toString();
      LOGGER.LOG.info("\n");
      for (String sst3 : sst) {
        if (pdfdata.contains(sst3.trim())) {
          LOGGER.LOG.info("'" + sst3 + "'>>------------>>>> Available in pdf file.");
        }
        else if (sst3.contains("Implemented By")) {
          sstImplementedBy = "Implemented By";
          if (pdfdata.contains(sstImplementedBy)) {
            LOGGER.LOG.info("'" + sstImplementedBy + "'>>------------>>>> Available in pdf file.");
          }
        }
        else if (sst3.contains("Satisfied By")) {
          sstImplementedBy = "Satisfied By";
          if (pdfdata.contains(sstImplementedBy)) {
            LOGGER.LOG.info("'" + sstImplementedBy + "' >>------------>>>> Available in pdf file.");
          }
        }
        else if (sst3.contains("Validated By")) {
          sstImplementedBy = "Validated By";
          if (pdfdata.contains(sstImplementedBy)) {
            LOGGER.LOG.info("'" + sstImplementedBy + "' >>------------>>>> Available in pdf file.");
          }
        }
        else if (sst3.startsWith(RMConstants.SELECT_ITEM)) {
          LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>  RM artifact available in module.");
        }
        else {
          LOGGER.LOG.info("'" + sst3 +
              "' PDF file report not automated successfully. - Expected data not available in pdf file or Expected data not available in RM links section please check added links");
          return false;
        }
      }
    }
    LOGGER.LOG.info("PDF file automated successfully and verified RM links availability in down loaded pdf report.");
    waitForSecs(5);
    return true;
  }

  /**
   * <p>
   * Verifies all the RM links available in downloaded PDF file.
   *
   * @param pdfFilePath down loaded file path to get file path to get file path used getDownloadedPDFFileNameAndPath()
   *          and Stores In new variable.
   * @param rmLinks get from getAllLinksFromSideBarArea() this is available in RMLinksPage.
   * @return {@link Boolean}
   * @throws IOException throws exception.
   */

  public boolean validatePdfContent(final String pdfFilePath, final String rmLinks) throws IOException {

    String sb1 = rmLinks;

    LOGGER.LOG.info("************ Validating RM Links/Artifacts Available In pdf File ****************");
    LOGGER.LOG.info("-------------------------------------------------------------------------------");
    LOGGER.LOG.info("All RM Links/artifacts from selected view  >>------>>> " + sb1);
    LOGGER.LOG.info("---------------------------------------------------\n");
    LOGGER.LOG.info("-----------> Down loaded pdf File data loaded successfully to validate <---------\n");
    String[] s = sb1.split(",");
    for (String ss : s) {
      String sstImplementedBy;
      // String[] ssst=ss.split("\n");
      String[] lines = ss.split("\\r?\\n");
      for (String line : lines) {
        // System.out.println(" line update" + line);

        String[] sst = line.split(": ");
        // sst = ss.split(":");
        // sst = ss.split(" ");
        String pdfdata = getFileData(pdfFilePath).toString();
        LOGGER.LOG.info("\n");

         System.out.println("oooooooooouuuuuutttttttt : " + pdfdata);
        for (String sst3 : sst) {
          // System.out.println(sst3);
          if (pdfdata.contains(sst3.trim())) {
            LOGGER.LOG.info("'" + sst3 + "'>>------------>>>> Available in pdf file.");
          }
          String[] sst1 = sst3.split(":");
          // sst = ss.split(":");
          // sst = ss.split(" ");
          // String pdfdata = getFileData(pdfFilePath).toString();
          LOGGER.LOG.info("\n");

          // System.out.println("oooooooooouuuuuutttttttt : " + pdfdata);
          for (String sst4 : sst1) {
            // System.out.println(sst3);
            if (pdfdata.contains(sst4.trim())) {
              LOGGER.LOG.info("'" + sst4 + "'>>------------>>>> Available in pdf file.");
            }

            else if (sst3.contains("Implemented By")) {
              sstImplementedBy = "Implemented By";
              if (pdfdata.contains(sstImplementedBy)) {
                LOGGER.LOG.info("'" + sstImplementedBy + "'>>------------>>>> Available in pdf file.");
              }
            }
            else if (sst3.contains("Satisfied By")) {
              sstImplementedBy = "Satisfied By";
              if (pdfdata.contains(sstImplementedBy)) {
                LOGGER.LOG.info("'" + sstImplementedBy + "' >>------------>>>> Available in pdf file.");
              }
            }
            else if (sst3.contains("Validated By")) {
              sstImplementedBy = "Validated By";
              if (pdfdata.contains(sstImplementedBy)) {
                LOGGER.LOG.info("'" + sstImplementedBy + "' >>------------>>>> Available in pdf file.");
              }
            }
            else if (sst3.contains("Tracked By")) {
              sstImplementedBy = "Tracked By";
              if (pdfdata.contains(sstImplementedBy)) {
                LOGGER.LOG.info("'" + sstImplementedBy + "' >>------------>>>> Available in pdf file.");
              }
            }
            else if (sst3.startsWith(RMConstants.SELECT_ITEM)) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>  RM artifact available in module.");
            }
            else if (sst3.startsWith("Link From")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>  RM artifact available in module.");
            }
            else if (sst3.startsWith("Link To")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>  RM artifact available in module.");
            }
            else if (sst3.startsWith("Satisfies")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>  RM artifact available in module.");
            }
            else if (sst3.startsWith("ValidatedBy")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>  RM artifact available in module.");
            }

            else if (sst3.startsWith("1.")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>ignore 1.");
            }
            else if (sst3.startsWith("2.")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>ignore 2.");
            }
            else if (sst3.startsWith("3.")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>ignore 3.");
            }
            else if (sst3.startsWith("4.")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>ignore 4.");
            }
            else if (sst3.startsWith("5.")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>ignore 5.");
            }
            else if (sst3.startsWith("6.")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>ignore 6.");
            }
            else if (sst3.startsWith("7.")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>ignore 7.");
            }
            else if (sst3.startsWith("8.")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>ignore 8.");
            }
            else if (sst3.startsWith("9.")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>ignore 9.");
            }
            else if (sst3.startsWith("10.")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>ignore 10.");
            }
            else if (sst3.startsWith("11.")) {
              LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>ignore 11.");
            }
            else {
              LOGGER.LOG.info("'" + sst3 +
                  "' PDF file report not automated successfully. - Expected data not available in pdf file or Expected data not available in RM links section please check added links");
              return false;
            }
          }
        }
      }
    }
    LOGGER.LOG.info("PDF file automated successfully and verified RM links availability in down loaded pdf report.");

    waitForSecs(5);
    return true;
  }

  /**
   * @return -
   */
  public String getAllArtifactIdFromSelectedView() {
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
    StringBuilder sb = new StringBuilder();
    List<WebElement> linksList =
        this.driverCustom.getWebElements("//table[@rowtype='resourceRow']//td[@class='id-col']//a");
    LOGGER.LOG.info("Total number of artifacts from selected view is : " + linksList.size());

    int i = 0;

    while (i < linksList.size()) {
      WebElement parentElement = linksList.get(i);
      LOGGER.LOG.info(">>>>>>>>>>>>>>>>" + parentElement.getText());
      sb.append(parentElement.getText());
      sb.append(",");
      i++;

    }
    LOGGER.LOG.info("----------------------All Arttifacts of selected view ----------------------------------------");
    LOGGER.LOG.info("---->" + sb.toString());
    LOGGER.LOG.info("----------------------------------------------------------------------\n");
    return sb.toString();

  }

  /**
   * @param pdfFilePath -
   * @param artifact -
   * @return -
   * @throws IOException -
   */
  public boolean isArtifactsOfViewAvailableInPdfFile(final String pdfFilePath, final String artifact)
      throws IOException {
    String sb1 = artifact;
    List<WebElement> linksList =
        this.driverCustom.getWebElements("//table[@rowtype='resourceRow']//td[@class='id-col']//a");
    LOGGER.LOG.info("Total number of artifacts from selected view is : " + linksList.size());

    LOGGER.LOG.info("************ Validating Artifacts Available In pdf File ****************");
    LOGGER.LOG.info("---------------------------------------------------");
    LOGGER.LOG.info("All RM Links/artifacts of selected view  >>---->>> " + sb1);
    LOGGER.LOG.info("---------------------------------------------------\n");
    LOGGER.LOG.info("-----> Down loaded pdf File data loaded successfully to validate <-----\n");
    String[] s = sb1.split(",");
    for (String ss : s) {
      String[] sst = ss.split(": ");
      String pdfdata = getFileData(pdfFilePath).toString();
      for (String sst3 : sst) {
        if (pdfdata.contains(sst3.trim())) {
          LOGGER.LOG.info("'" + sst3 + "'>>------------>>>> Available in pdf file.");
        }
        else if (sst3.startsWith(RMConstants.SELECT_ITEM)) {
          LOGGER.LOG.info("'" + sst3 + "' >>------->>----->>>>  RM artifact available in module.");
        }
        else {
          LOGGER.LOG.info("'" + sst3 +
              "' PDF file report not automated successfully. - Expected data not available in pdf file or Expected data not available in RM links section please check added links");
          return false;
        }
      }
    }
    LOGGER.LOG.info("PDF file automated successfully and verified RM links availability in down loaded pdf report.");
    waitForSecs(5);
    return true;
  }


  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on the image menu launcher for the selected artifact or module,list of options is displayed.<br>
   * Select the option 'Generate a report for the artifact', 'Create Report' dialog is displayed.<br>
   * Create a traceability report.<br>
   * Provide all the necessary details at last you will be finding the option 'Open the report when the wizard is
   * closed.'.<br>
   * Select the check box 'Open the report when the wizard is closed.'
   *
   * @return attribute value as true or false.
   */
  public String selectOpenTheReportWhenTheWizardIsClosedCheckBox() {
    waitForSecs(2);

    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_OPEN_REPORT_WHEN_WIZARDIS_CLOSED_XPATH);
    LOGGER.LOG.info(
        "Checked presence of 'Open the report when the wizard is closed' check box in 'Generate the Report' section'");
    // Select Check Box

    String ariaChecked = this.driverCustom
        .getAttribute(RMConstants.RMARTIFACTSPAGE_OPEN_REPORT_WHEN_WIZARDIS_CLOSED_XPATH, "aria-checked");

    if (ariaChecked.equals("false")) {
      this.driverCustom
          .getPresenceOfWebElement("//div[@dojoattachpoint='_openReportDiv']//input[@aria-checked='false']");
      this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_OPEN_REPORT_WHEN_WIZARDIS_CLOSED_XPATH).click();
      waitForSecs(3);
      LOGGER.LOG.info(
          "'Open the report when the wizard is closed' check box selected Successfully under 'Generate the Report' section.");

    }
    else if (ariaChecked.equals("true")) {
      this.driverCustom
          .getPresenceOfWebElement("//div[@dojoattachpoint='_openReportDiv']//input[@aria-checked='true']");

      LOGGER.LOG.info(
          "'Open the report when the wizard is closed' check box already selected Successfully under 'Generate the Report' section.");

    }
    return ariaChecked;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'Modules' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Open the searched module using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Get all the artifact id from the module.
   *
   * @return list of ID's present inside the module.
   */
  public String artifactsID() {
    List<String> id = this.driverCustom.getWebElementsText("//table[@rowType='resourceRow']//td[@class='id-col']//a");
    return String.join(",", id);
  }

  /**
   * This method to remove Link at cell of column of artifact
   *
   * @param removeLinkText text of Link need to remove
   * @return true if remove link is present on page
   */
  public boolean removeLinkOfArtifactFromTable(final String removeLinkText) {
    // Hover and click on 'Edit Links' dropdown image
    // For validated by link type
    if (this.driverCustom.isElementPresent("//td[@class='val-by' or @class='lk9_rem']//img", Duration.ofSeconds(20))) {
      this.driverCustom
          .clickUsingActions(this.driverCustom.getWebElement("//td[@class='val-by' or @class='lk9_rem']//img"));
    }
    // for Traced By Architecture Element link type
    if (this.driverCustom.isElementPresent(RMConstants.RMARTIFACTSPAGE_TRACED_BY_ICON_XPATH, this.timeInSecs,
        removeLinkText)) {
      this.driverCustom.clickUsingActions(
          this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_TRACED_BY_ICON_XPATH, removeLinkText));
    }

    this.driverCustom
        .clickUsingActions(this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_EDIT_LINK_ICON_XPATH));
    // Select 'Remove Link' option
    waitForSecs(3);
    this.driverCustom
        .clickUsingActions(this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_REMOVE_LINK_OPTION_XPATH));
    this.driverCustom.click(String.format("//td[text()='%s']", removeLinkText));
    LOGGER.LOG.info("'Confirm Removing This Link' Dialog is displayed");
    waitForSecs(5);
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_MODIFIEDARTIAFCTCLICKYESBUTTON_XPATH);
    waitForSecs(5);
    LOGGER.LOG.info(String.format("The link with text '%s' is removed successfully", removeLinkText));
    return this.driverCustom.isElementInvisible(RMConstants.STATUS_MESSAGE, this.timeInSecs);
  }


  /**
   * This method to get column header from CSV file based on column index
   *
   * @param pathCSVFile full path of CSV file in your computer after exporting
   * @param columnIndex index number of column that you want to get the value
   * @return data at column and row of CSV file
   */
  public String getColumnHeaderFromCSVFileByIndex(final String pathCSVFile, final String columnIndex) {
    String columnHeader = "";
    String line = "";
    int columnIndexNumber = Integer.parseInt(columnIndex);
    try (BufferedReader br = new BufferedReader(new FileReader(pathCSVFile))) {
      if ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        columnHeader = values[columnIndexNumber - 1];
      }
      else {
        LOGGER.LOG.error(String.format("The CSV '%s' file is empty. Please add data to this file", pathCSVFile));
      }
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }

    LOGGER.LOG.info(String.format("The header column name at column index '%s' is '%s'", columnIndex, columnHeader));
    return columnHeader;
  }

  /**
   * <p>
   * Method to verify button with title (tooltip) is displayed.
   *
   * @author KYY1HC
   * @param title value of title attribute of button
   * @return true if button is displayed else it will return false
   */
  public boolean isButtonDisplayed(final String title) {
    try {
      waitForSecs(3);
      WebElement btn = this.driverCustom.getWebElement("//a[@title='" + title + "']");
      return btn.isDisplayed();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getStackTrace());
      return false;
    }
  }

  /**
   * This method to click on 'Locked by Other' icon to override the lock <br>
   * Dialog 'Locked Artifact' will display <br>
   * Click on button 'Override the Lock' to unlock artifact Then verify unlocked artifact icon is displayed
   *
   * @author KYY1HC
   * @return true if the artifact is unlocked, otherwise false
   */
  public boolean overrideTheLockedArtifact() {
    String xpathLockedArtifact = "//a[contains(@title,'Click to override the lock.')]";
    WebElement lockedArtifactIcon = this.driverCustom.getPresenceOfWebElement(xpathLockedArtifact);
    lockedArtifactIcon.click();
    clickOnDialogButton("Locked Artifact", "Override the Lock");
    waitForSecs(3);
    String xpathUnlockedArtifact = "//a[@title='The artifact is unlocked. Click to manually lock it for editing.']";
    WebElement unlockElement = this.driverCustom.getPresenceOfWebElement(xpathUnlockedArtifact);
    LOGGER.LOG.info("This artifact is override the lock");
    return unlockElement.isDisplayed();
  }

  /**
   * <p>
   * Select "Folder" attribute from the list of attribute after searching. <br>
   * Select one destination folder in Choose Values column <br>
   * Click on Add and Close button to add attribute and value, then close New Filter wizard
   *
   * @param destinationFolder destination folder
   */
  public void addAndCloseValueForFolderAttributeInFilter(final String destinationFolder) {
    // wait
    this.driverCustom.isElementInvisible(RMConstants.RMARTIFACTPAGE_CLEAR_FILTER_XPATH, Duration.ofSeconds(10));

    // click on Filter icon
    addFilter();
    LOGGER.LOG.info("Click on Add Filter icon");
    // search Folder
    searchAttributeInFilter("Folder");
    LOGGER.LOG.info("Search Folder in New Filter dialog");
    // select Folder attribute:
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_NEWFILTER_FOLDERATTRIBUTE_XPATH);
    LOGGER.LOG.info("Select Folder attribute");

    // select destination folder:
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_NEWFILTER_VALUEOFFOLDERATTRIBUTE_XPATH,
        this.timeInSecs, destinationFolder)) {
      Actions act = new Actions(this.driverCustom.getWebDriver());
      WebElement element = this.driverCustom
          .getWebElement(RMConstants.RMARTIFACTPAGE_NEWFILTER_VALUEOFFOLDERATTRIBUTE_XPATH, destinationFolder);
      ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].scrollIntoView();", element);
      act.moveToElement(element);
      act.click().build().perform();

      LOGGER.LOG.info("Select destination folder as " + destinationFolder + " in Folder tree");
    }
    else {
      // if destinationFolder is not found
      LOGGER.LOG.info("Not found folder as " + destinationFolder + " in Folder tree");
      throw new InvalidArgumentException(destinationFolder + " - doesn't exist in the New Filter dialog.");
    }

    // click on "Add and Close" button
    if (this.driverCustom.isElementClickable(RMConstants.RMARTIFACTPAGE_NEWFILTER_ADDANDCLOSEBUTTON_XPATH,
        Duration.ofSeconds(20))) {
      Button btnAddAndClose = this.engine.findElement(Criteria.isButton().withText("Add and Close")).getFirstElement();
      btnAddAndClose.click();
      LOGGER.LOG.info("Clicked on 'Add and Close' button.");
    }
    else {
      throw new InvalidArgumentException(" - doesn't exist in the New Filter dialog.");
    }
  }


  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'Search' button present in Artifacts page inside module, 'Module Find' window is displayed.<br>
   * Click on 'Go To' tab, Enter artifact id in 'Module Find' dialog.<br>
   * Click on 'Go To' button.<br>
   * Close dialog.
   *
   * @param artifactID is ID of artifact belong to this module
   * @return true if found and selected artifact inside module successfully.
   */
  public boolean searchGoToInModuleFind(final String artifactID) {
    boolean flag = false;
    waitForLoadingMessage();
    // Click on search Go To icon
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_MODULEARTIFACTSEARCH_BUTTON_XPATH, this.timeInSecs);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_MODULEARTIFACTSEARCH_BUTTON_XPATH);
    // Click on GO TO tab in dialog
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_MODULEFIND_GOTOTAB_XPATH, Duration.ofSeconds(50));
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_MODULEFIND_GOTOTAB_XPATH);
    // Input Artifact ID to searching field
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTPAGE_MODULEFIND_TEXTBOX_XPATH, Duration.ofSeconds(50));
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_MODULEFIND_TEXTBOX_XPATH).clear();
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_MODULEFIND_TEXTBOX_XPATH).sendKeys(artifactID);

    // Click on GO TO button
    this.driverCustom.isElementClickable(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(50), RMConstants.GOTO);
    clickOnJazzButtons(RMConstants.GOTO);

    // Close dialog
    this.driverCustom.isElementClickable(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(50), RMConstants.CLOSE);
    clickOnJazzButtons(RMConstants.CLOSE);

    // Verify searched Artifact is selected or not
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_CHECKBOX_OFARTIFACT_XPATH, Duration.ofSeconds(20),
        artifactID)) {
      LOGGER.LOG.info("Searched Artifact is selected");
    }
    else {
      LOGGER.LOG.info("Searched Artifact is not selected");
    }
    String getStatusCheckbox = this.driverCustom
        .getWebElement(RMConstants.RMARTIFACTPAGE_CHECKBOX_OFARTIFACT_XPATH, artifactID).getAttribute("aria-label");
    String ariaLabelSelected = "Artifact " + artifactID +
        " selected. To remove from selection press space bar. To move around rows and columns use arrow keys.";
    if (getStatusCheckbox.equalsIgnoreCase(ariaLabelSelected)) {
      flag = true;
    }
    return flag;
  }


  /**
   * Open Add filter dialog {@link RMArtifactsPage #addFilter()} <br>
   * Input "Limit by lifecycle status" to Attribute searching {@link RMArtifactsPage #searchAttributeInFilter(String)}
   * <br>
   * Select "Limit by lifecycle status" in searching result as ATTRIBUTE_NAME parameter <br>
   * Select at least one value of item in Choose Value column <br>
   * Click on "Add and Close" button <br>
   *
   * @param additionalParams is MAP, included Attribute Name, Item Name and Item Value in Life Cycle. With elements such
   *          as (<ID>, <value>): ("ATTRIBUTE_NAME","Limit by lifecycle status") <br>
   *          ("DEVELOPMENT_ITEM", <DEVELOPMENT_VALUE>) <br>
   *          ("TRACKING_ITEM", <TRACKING_VALUE>) <br>
   *          ("AFFECTS_ITEM", <AFFECTS_VALUE>) <br>
   *          ("TEST_ITEM", <TEST_VALUE>) <br>
   *          <DEVELOPMENT_VALUE>,<TRACKING_VALUE>,<AFFECTS_VALUE>,<TEST_VALUE> has available value such as: None, All,
   *          Open, Resolved... <br>
   *          For not select any item, we put "not select" to value variable (Ex: not select DEVELOPMENT_ITEM
   *          ("DEVELOPMENT_ITEM", "not select") )
   */

  public void addItemAndValueForLimitByLifecycleStatusFilter(final Map<String, String> additionalParams) {
    // waitForPageLoaded();
    // Select attribute in searching result
    Label labelAttribute = this.engine
        .findElementWithDuration(Criteria.isLabel().withText(additionalParams.get("ATTRIBUTE_NAME")), this.timeInSecs)
        .getFirstElement();
    labelAttribute.click();
    LOGGER.LOG.info(RMConstants.CLICKED_ON + additionalParams.get("ATTRIBUTE_NAME"));
    // wait for item as ITEM_LIFECYCLE display in dialog:
    this.driverCustom.isElementPresent(RMConstants.ITEM_LIMIT_BY_LIFECYCLE_STATUS_FILTER_XPATH, Duration.ofSeconds(20),
        additionalParams.get("DEVELOPMENT_ITEM"));
    // DEVELOPMENT_ITEM:
    if (additionalParams.get("DEVELOPMENT_VALUE").equalsIgnoreCase("not select")) {
      // Case: Not select any value in DEVELOPMENT Items
      // do nothing
    }
    else {
      // Case: Select some value in DEVELOPMENT Items
      String itemAndValueLifeCycleXpath01 = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("DEVELOPMENT_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("DEVELOPMENT_VALUE") + "']";
      this.driverCustom.getWebElement(itemAndValueLifeCycleXpath01).click();
      LOGGER.LOG.info("Clicked on item as " + additionalParams.get("DEVELOPMENT_ITEM") + " and value of item as " +
          additionalParams.get("DEVELOPMENT_VALUE"));
    }
    // TRACKING_ITEM:
    if (additionalParams.get("TRACKING_VALUE").equalsIgnoreCase("not select")) {
      // Case: Not select any value in TRACKING Items
      // do nothing
    }
    else {
      // Case: Select some value in TRACKING Items
      String itemAndValueLifeCycleXpath02 = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("TRACKING_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("TRACKING_VALUE") + "']";
      this.driverCustom.getWebElement(itemAndValueLifeCycleXpath02).click();
      LOGGER.LOG.info("Clicked on item as " + additionalParams.get("TRACKING_ITEM") + " and value of item as " +
          additionalParams.get("TRACKING_VALUE"));
    }

    // AFFECTS_ITEM:
    if (additionalParams.get("AFFECTS_VALUE").equalsIgnoreCase("not select")) {
      // Case: Not select any value in AFFECTS Items
      // do nothing
    }
    else {
      // Case: Select some value in AFFECTS Items
      String itemAndValueLifeCycleXpath03 = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("AFFECTS_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("AFFECTS_VALUE") + "']";
      this.driverCustom.getWebElement(itemAndValueLifeCycleXpath03).click();
      LOGGER.LOG.info("Clicked on item as " + additionalParams.get("AFFECTS_ITEM") + " and value of item as " +
          additionalParams.get("AFFECTS_VALUE"));
    }

    // TEST_ITEM:
    if (additionalParams.get("TEST_VALUE").equalsIgnoreCase("not select")) {
      // Case: Not select any value in TEST Items
      // do nothing
    }
    else {
      // Case: Select some value in TEST Items

      String itemAndValueLifeCycleXpath04 = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("TEST_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("TEST_VALUE") + "']";
      this.driverCustom.getWebElement(itemAndValueLifeCycleXpath04).click();
      LOGGER.LOG.info("Clicked on item as " + additionalParams.get("TEST_ITEM") + " and value of item as " +
          additionalParams.get("TEST_VALUE"));
    }

    // select success - Click on Add and Close dialog
    if (this.driverCustom.isElementPresent(RMConstants.SUMMARY_CONDITION_LIMIT_BY_LIFECYCLE_STATUS_FILTER_XPATH,
        Duration.ofSeconds(20))) {
      Button btnAddAndClose = this.engine.findElement(Criteria.isButton().withText("Add and Close")).getFirstElement();
      btnAddAndClose.click();
      LOGGER.LOG.info("Clicked on 'Add and Close' button.");
    }
    else {
      Button btnClose = this.engine.findElement(Criteria.isButton().withText("Close")).getFirstElement();
      btnClose.click();
      LOGGER.LOG.info("Clicked on 'Close' button.");
      throw new InvalidArgumentException("Fail at select " + additionalParams.get("ITEM_LIFECYCLE") + "and " +
          additionalParams.get("VALUE_OF_ITEM_LIFECYCLE"));
    }
  }


  /**
   * This method to get size of excel file
   *
   * @param pathExcelFile full path of excel file in your computer after exporting
   * @return size of excel file
   * @throws FileNotFoundException -
   */
  public String getExcelSize(final String pathExcelFile) throws FileNotFoundException {
    String result = "";
    // Read the file
    File excelFile = new File(pathExcelFile);
    // Use length method to get size of file in bytes
    double fileSizeinBytesDouble = excelFile.length();
    int fileSizeinBytesInteger = (int) fileSizeinBytesDouble;
    LOGGER.LOG.info("Size of Excel File in byte is " + fileSizeinBytesInteger + " bytes");
    result = String.valueOf(fileSizeinBytesInteger);
    return result;
  }

  /**
   * <p>
   * Click on radio button in Export dialog <br>
   *
   * @param buttonLabel label with text next to radio button.
   */
  public void clickOnRadioButtonInExportDialog(final String buttonLabel) {
    // waitForPageLoaded();
    // wait for this RadioButton display:
    this.driverCustom.isElementPresent(RMConstants.RADIO_BUTTON_XPATH, this.timeInSecs, buttonLabel);
    // Click on radio button with label as buttonLabel
    this.driverCustom.getWebElement(RMConstants.RADIO_BUTTON_XPATH, buttonLabel).click();
    waitForSecs(10);
  }

  /**
   * This method will get value from cell which has sheet as sheetIndex, row as rowIndex and column as columnIndex in
   * XLSX file
   *
   * @param excelPath - path of excel file , type as XLSX
   * @param sheetIndex - sheet index need to read value
   * @param rowIndex - row index need to read value
   * @param columnIndex - column index need to read value
   * @return String data present in the define cell following (path file, sheet index, column index and row index).
   */
  public String getDataFromCellOfXLSX(final String excelPath, final String sheetIndex, final String rowIndex,
      final String columnIndex) {
    System.out.println(excelPath);
    String result = "";
    ExcelCSVReader ex = new ExcelCSVReader(excelPath);
    result = ex.readDataFromCellOfXLSX(excelPath, sheetIndex, rowIndex, columnIndex);
    System.out.println(result);
    return result;
  }

  /**
   * @param excelPath
   * @param sheetIndex
   * @param rowIndex
   * @param columnIndex
   * @return
   */
  @SuppressWarnings("javadoc")
  public String getDataFromCellOfXLS(final String excelPath, final String sheetIndex) {
    System.out.println(excelPath);
    String result = "";
    ExcelCSVReader ex = new ExcelCSVReader(excelPath);
    result = ex.readDataFromCellOfXLS(excelPath, sheetIndex);
    System.out.println(result);
    return result;
  }

  /**
   * This method will get value from cell which has sheet as sheetIndex, row as rowIndex and column as columnIndex in
   * XLS file
   *
   * @param excelPath - path of excel file , type as XLS
   * @param sheetIndex - sheet index need to read value
   * @param rowIndex - row index need to read value
   * @param columnIndex - column index need to read value
   * @return String data present in the define cell following (path file, sheet index, column index and row index).
   */
  public String getIndividualDataFromCellOfXLS(final String excelPath, final String sheetIndex, final String rowIndex,
      final String columnIndex) {
    String result = "";
    ExcelCSVReader ex = new ExcelCSVReader(excelPath);
    result = ex.readIndividualDataFromCellOfXLS(excelPath, sheetIndex, rowIndex, columnIndex);
    // System.out.println(result);
    return result;
  }

  /**
   * This method will set value from cell which has sheet as sheetIndex, row as rowIndex and column as columnIndex in
   * XLSX file
   *
   * @author VDY1HC
   * @param excelPath - path of excel file , type as XLSX
   * @param sheetIndex - sheet index need to read value
   * @param rowIndex - row index need to read value
   * @param columnIndex - column index need to read value
   * @param updateValue - new value to be updated in XLSX
   */
  public void setDataFromCellOfXLSX(final String excelPath, final String sheetIndex, final String rowIndex,
      final String columnIndex, final String updateValue) {
    ExcelCSVReader reader = new ExcelCSVReader(excelPath);
    reader.setDataFromCellOfXLSX(excelPath, sheetIndex, rowIndex, columnIndex, updateValue);
  }


  /**
   * <p>
   * After creating a new change set using {@link RMDashBoardPage#createConfiguration(String, String)} then select
   * target folder and choose "Clone From a Component" option * @param additionalParams is MAP, included Folder name
   * want to store artifacts clone to an other component, target project area, target component, dropdown option of type
   * of configuration, target local stream, artifact type and artifact ID to be cloned. With elements such as (<ID>,
   * <value>): <br>
   * <p>
   *
   * @author NVV1HC
   * @param params store key values for: ("FOLDER_NAME", <FOLDER_NAME_VALUE>) <br>
   *          ("PROJECT_AREA", <PROJECT_AREA_VALUE>) <br>
   *          ("COMPONENT_NAME", <COMPONENT_NAME_VALUE>) <br>
   *          ("STREAMS_DROPDOWN_OPTION", <STREAMS_DROPDOWN_OPTION_VALUE>) <br>
   *          <STREAM_NAME>,<STREAM_NAME_VALUE> <br>
   *          For not select any item, we put "not select" to value variable (Ex: not select DEVELOPMENT_ITEM
   *          ("DEVELOPMENT_ITEM", "not select") ) ("SELECT_ARTIFACT_TYPE", <ARTIFACT_TYPE>) <br>
   *          ("ARTIFACT_ID", <ARTIFACT_ID>) <br>
   */
  public void cloneFromAComponent(final Map<String, String> params) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_FOLDER_ICON_XPATH, params.get("FOLDER_NAME"))
        .click();

    this.driverCustom
        .getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_FOLDER_TOOLTIP_XPATH, params.get("FOLDER_NAME")).click();

    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_CLONE_FROM_A_COMPONENT_OPTION).click();

    Button btnSelectCompConfig = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Select Component Configuration..."), this.timeInSecs)
        .getFirstElement();
    btnSelectCompConfig.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_PROJECTAREA_DROPDOWN_XPATH).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.getPresenceOfWebElement(RMConstants.CLONE_FROM_A_COMPONENT_DIALOG_PROJECTAREA_OPTION,
        params.get("PROJECT_AREA")).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(8));

    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_COMPONENT_DROPDOWN_XPATH).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, params.get("COMPONENT_NAME"))
        .click();

    Button btnStream =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Streams"), this.timeInSecs).getFirstElement();
    btnStream.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH,
        params.get("STREAMS_DROPDOWN_OPTION")).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));

    Dialog dlgSelectCompConfig = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a Component Configuration"), this.timeInSecs)
        .getFirstElement();
    Text search = this.engine.findElementWithDuration(
        Criteria.isTextField().withPlaceHolder("Type to search (enter * to show all)").inContainer(dlgSelectCompConfig),
        this.timeInSecs).getFirstElement();
    search.setText(params.get("STREAM_NAME"));
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));

    this.driverCustom.getPresenceOfWebElement(RMConstants.CLONE_FROM_A_COMPONENT_DIALOG_COMPONENTNAME_OPTION,
        params.get("STREAM_NAME")).click();

    Button btnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOK.click();
    Button btnNext =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >"), this.timeInSecs).getFirstElement();
    btnNext.click();

    Button btnAddArtifact = this.engine
        .findElementWithDuration(Criteria.isButton().withText(params.get("SELECT_ARTIFACT_TYPE")), this.timeInSecs)
        .getFirstElement();
    btnAddArtifact.click();

    Dialog dlgAlltoModule = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select Artifacts"), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info("Displayed 'Insert Artifact' dialog.");
    Text searchArtifactTextbox = this.engine.findElementWithDuration(Criteria.isTextField()
        .withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID).inContainer(dlgAlltoModule),
        this.timeInSecs).getFirstElement();
    searchArtifactTextbox.setText(params.get("ARTIFACT_ID") + Keys.ENTER);
    LOGGER.LOG
        .info("Input " + params.get("ARTIFACT_ID") + " in 'Type to filter artifacts by text or by ID' text field");
    waitForSecs(3);

    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.ARTIFACTRESULT_AFTERSEARCHINGTOADDORINSERT_XPATH,
          params.get("ARTIFACT_ID")).click();
      waitForSecs(3);
      LOGGER.LOG.info("Select " + params.get("ARTIFACT_ID") + " artifact");
    }
    catch (Exception e) {
      throw new InvalidArgumentException(params.get("ARTIFACT_ID") + " - doesn't found.");
    }

    Button btnOK1 = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK")/* .inContainer(dlgAlltoModule) */, this.timeInSecs)
        .getFirstElement();
    btnOK1.click();
    this.driverCustom.waitForPageLoaded();
    Button btnNext1 =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >"), this.timeInSecs).getFirstElement();
    btnNext1.click();

    Button btnFinish =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Finish"), this.timeInSecs).getFirstElement();
    btnFinish.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
  }


  /**
   * After expand Filter as {@link RMArtifactsPage #expandFilters}<br>
   * Click on Edit Filter icon in Attribute Name need to edit value <br>
   * Edit Filter dialog will open <br>
   *
   * @param attributeFilter is the name of Attribute need to edit value
   */
  public void openEditFilterDialogOfOneCondition(final String attributeFilter) {
    // Hover on tag of filter -> to display Edit Filter icon
    this.driverCustom.isElementPresent(RMConstants.FILTER_ATTRIBUTE_NAME_XPATH, Duration.ofSeconds(50),
        attributeFilter);
    Actions action = new Actions(this.driverCustom.getWebDriver());
    WebElement tagFilter = this.driverCustom.getWebElement(RMConstants.FILTER_ATTRIBUTE_NAME_XPATH, attributeFilter);
    action.moveToElement(tagFilter).build().perform();

    // Click on Edit Filter icon
    Button editFilter = this.engine.findElement(Criteria.isButton().withToolTip("Edit filter")).getFirstElement();
    editFilter.click();
  }


  /**
   * Open Edit filter dialog {@link RMArtifactsPage #openEditFilterDialogOfOneCondition(String)} <br>
   * Edit value of each item in Choose Value column <br>
   * Click on "Update" button <br>
   *
   * @author VUP5HC
   * @param additionalParams is MAP, included Attribute Name, Item Name and Item Value in Life Cycle. With elements such
   *          as (<ID>, <value>): <br>
   *          ("DEVELOPMENT_ITEM", <DEVELOPMENT_VALUE>) <br>
   *          ("TRACKING_ITEM", <TRACKING_VALUE>) <br>
   *          ("AFFECTS_ITEM", <AFFECTS_VALUE>) <br>
   *          ("TEST_ITEM", <TEST_VALUE>) <br>
   *          <DEVELOPMENT_VALUE>,<TRACKING_VALUE>,<AFFECTS_VALUE>,<TEST_VALUE> has available value such as: None, All,
   *          Open, Resolved... <br>
   *          For not select any item, we put "not select" to value variable (Ex: not select DEVELOPMENT_ITEM
   *          ("DEVELOPMENT_ITEM", "not select") )
   */


  public void editItemAndValueForLimitByLifecycleStatusFilter(final Map<String, String> additionalParams) {
    String itemXpath = null;
    String itemSelectedXpath = null;

    // Wait for item as ITEM_LIFECYCLE display in dialog:
    this.driverCustom.isElementPresent(RMConstants.ITEM_LIMIT_BY_LIFECYCLE_STATUS_FILTER_XPATH, Duration.ofSeconds(20),
        additionalParams.get("DEVELOPMENT_ITEM"));
    // Edit
    // DEVELOPMENT_ITEM:
    if (additionalParams.get("DEVELOPMENT_VALUE").equalsIgnoreCase("not select")) {
      // Case: Not select any value in DEVELOPMENT Items
      // Do nothing
    }
    else {
      // Case: Select some value in DEVELOPMENT Items
      itemXpath = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("DEVELOPMENT_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("DEVELOPMENT_VALUE") + "']";
      itemSelectedXpath = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("DEVELOPMENT_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("DEVELOPMENT_VALUE") + "' and @aria-selected='true']";

      if (this.driverCustom.isElementInvisible(itemSelectedXpath, Duration.ofSeconds(5))) {
        this.driverCustom.getWebElement(itemXpath).click();
        LOGGER.LOG.info("Clicked on item as " + additionalParams.get("DEVELOPMENT_ITEM") + " and value of item as " +
            additionalParams.get("DEVELOPMENT_VALUE"));
      }
    }

    // TRACKING_ITEM:
    if (additionalParams.get("TRACKING_VALUE").equalsIgnoreCase("not select")) {
      // Case: Not select any value in TRACKING Items
      // Do nothing
    }
    else {
      // Case: Select some value in TRACKING Items
      itemXpath = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("TRACKING_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("TRACKING_VALUE") + "']";
      itemSelectedXpath = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("TRACKING_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("TRACKING_VALUE") + "' and @aria-selected='true']";
      if (this.driverCustom.isElementInvisible(itemSelectedXpath, Duration.ofSeconds(5))) {
        this.driverCustom.getWebElement(itemXpath).click();
        LOGGER.LOG.info("Clicked on item as " + additionalParams.get("TRACKING_ITEM") + " and value of item as " +
            additionalParams.get("TRACKING_VALUE"));
      }

    }
    // AFFECTS_ITEM:
    if (additionalParams.get("AFFECTS_VALUE").equalsIgnoreCase("not select")) {
      // Case: Not select any value in AFFECTS Items
      // Do nothing
    }
    else {
      // Case: Select some value in AFFECTS Items
      itemXpath = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("AFFECTS_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("AFFECTS_VALUE") + "']";
      itemSelectedXpath = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("AFFECTS_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("AFFECTS_VALUE") + "' and @aria-selected='true']";
      if (this.driverCustom.isElementInvisible(itemSelectedXpath, Duration.ofSeconds(5))) {
        this.driverCustom.getWebElement(itemXpath).click();
        LOGGER.LOG.info("Clicked on item as " + additionalParams.get("AFFECTS_ITEM") + " and value of item as " +
            additionalParams.get("AFFECTS_VALUE"));
      }
    }

    // TEST_ITEM:
    if (additionalParams.get("TEST_VALUE").equalsIgnoreCase("not select")) {
      // Case: Not select any value in TEST Items
      // Do nothing

    }
    else {
      // Case: Select some value in TEST Items
      itemXpath = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("TEST_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("TEST_VALUE") + "']";
      itemSelectedXpath = "//div[@class='lifecycleStatusContentDiv']//div[contains(text(),'" +
          additionalParams.get("TEST_ITEM") + "')]//following-sibling::div//span[@role='option' and text()='" +
          additionalParams.get("TEST_VALUE") + "' and @aria-selected='true']";
      if (this.driverCustom.isElementInvisible(itemSelectedXpath, Duration.ofSeconds(5))) {
        this.driverCustom.getWebElement(itemXpath).click();
        LOGGER.LOG.info("Clicked on item as " + additionalParams.get("TEST_ITEM") + " and value of item as " +
            additionalParams.get("TEST_VALUE"));
      }
    }

    // Select success - Click on Add and Close dialog
    if (this.driverCustom.isElementPresent(RMConstants.SUMMARY_CONDITION_LIMIT_BY_LIFECYCLE_STATUS_FILTER_XPATH,
        Duration.ofSeconds(20)))

    {
      waitForSecs(2);
      Button btnAddAndClose = this.engine.findElement(Criteria.isButton().withText("Update")).getFirstElement();
      btnAddAndClose.click();
      LOGGER.LOG.info("Clicked on 'Update' button.");
      waitForSecs(2);
    }
    else {
      throw new InvalidArgumentException("Fail at select " + additionalParams.get("ITEM_LIFECYCLE") + "and " +
          additionalParams.get("VALUE_OF_ITEM_LIFECYCLE"));
    }
  }

  /**
   * Open Artifact details page of artifact with Format: Text <br>
   * Verify if there is existing line in artifact content. <br>
   * The text displayed in artifact content should not contains any special character. <br>
   *
   * @param textToVerify - Text within a line in artifact content, should not contains any special character.
   * @return true - if existing one line with matching expected text. false - if no line with matching expected text.
   */
  public boolean isExistingTextInArtifactContent(final String textToVerify) {
    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_ARTIFACT_ARTIFACTCONTENTLINE_XPATH,
          textToVerify);
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * @author UUM4KOR
   * @param artifactName New artifact name
   * @param artifactType -
   * @param artifactFormat -
   * @return -
   */
  public String clickOnCreateNewArtifactLinkAndSetName(final String artifactName, final String artifactType,
      final String artifactFormat) {
    String createdArtifact = null;
    // clicking on 'Create New Artifact' link
    this.driverCustom.getWebElement("//span[text()='Create New Artifact']").click();
    LOGGER.LOG.info("'Create New Artifact' link clicked.");
    try {
      this.driverCustom.getWebElement("//h1//div[@contenteditable='true']//p").click();
      // set text
      this.driverCustom.getWebElement("//h1/div[@aria-label='Rich Text Editor, editor1']").sendKeys(artifactName);
      LOGGER.LOG.info("New artifact name is : " + artifactName);
      // outside clicking(clicking filter )
      this.driverCustom.getWebElement(
          "//div[@class='input-wrapper']/input[@placeholder='Type to filter artifacts by text or by ID']").click();
      createdArtifact =
          this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_ARTIFACTIDJUSTCREATED_XPATH).getText();
      LOGGER.LOG.info("Created artifact id is : " + createdArtifact);
    }
    catch (Exception e) {
      Dialog createArtifactDialog = this.engine
          .findElementWithDuration(Criteria.isDialog().withTitle("Create Artifact"), this.timeInSecs).getFirstElement();
      Text txtInitalContent = this.engine.findElementWithDuration(
          Criteria.isTextField().hasLabel(RMConstants.INITIAL_CONTENT).isMultiLine().inContainer(createArtifactDialog),
          this.timeInSecs).getElementByIndex(1);
      txtInitalContent.setText(artifactName);
      LOGGER.LOG.info("artifact name is : " + artifactName);
      Dropdown drdArtifactType = this.engine.findFirstElementWithDuration(
          Criteria.isDropdown().withLabel("Artifact type:").inContainer(createArtifactDialog), this.timeInSecs);
      drdArtifactType.selectOptionWithText(artifactType);
      LOGGER.LOG.info("artifact type is : " + artifactType);
      try {
        Dropdown drdArtifacFormat = this.engine.findFirstElementWithDuration(
            Criteria.isDropdown().withLabel("Artifact format:").inContainer(createArtifactDialog), this.timeInSecs);
        drdArtifacFormat.selectOptionWithText(artifactFormat);
        LOGGER.LOG.info("artifact formate is : " + artifactFormat);
      }
      catch (Exception e2) {
        LOGGER.LOG.error(e2.getMessage());
      }
      Button btnOK =
          this.engine.findElementWithDuration(Criteria.isButton().withText("OK").inContainer(createArtifactDialog),
              this.timeInSecs).getFirstElement();
      btnOK.click();
      waitForSecs(5);
      createdArtifact =
          this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_ARTIFACTIDJUSTCREATED_XPATH).getText();
      LOGGER.LOG.info("Created artifact id is : " + createdArtifact);
      waitForSecs(5);

    }

    return createdArtifact;
  }

  /**
   * <p>
   * This function is used after {@link #RMArtifactsPage.clickOnCreateNewArtifactLinkAndSetName} to get new artifact id
   * under module with respect to newly created artifact name.
   *
   * @author UUM4KOR
   * @param ArtifactName artifact name
   * @return Artifact ID
   */
  public String getArtifactIdFromModule(final String ArtifactName) {
    // get artifact id
    String id = this.driverCustom
        .getWebElement(
            "//p[text()='" + ArtifactName + "']/ancestor::td/preceding-sibling::td//div[@class='icon-resourcelink']//a")
        .getText();
    LOGGER.LOG.info("New artifact ID is :" + id);
    return id;

  }

  /**
   * <p>
   * THis method used after {@link #RMArtifactsPage.setNewChildArtifactName} to get child artifact id.
   *
   * @author UUM4KOR
   * @param name child artifact Name
   * @return artifact ID
   */
  public String getChildArtifactId(final String name) {
    waitForPageLoaded();
    waitForSecs(5);
    // get child artifact id
    String id = this.driverCustom
        .getWebElement("//span[text()='" + name + "']/../../../../../../../preceding-sibling::td[1]//a").getText()
        .toString();
    LOGGER.LOG.info("Child artifact ID is : " + id);

    return id;

  }

  /**
   * <p>
   * This meyhod used after {@link RMLinksPage#selectContextMenuOfArtifactInModule}
   *
   * @author UUM4KOR
   * @param childArtifactName Name of child artifact
   */
  public void setNewChildArtifactName(final String childArtifactName) {
    waitForPageLoaded();
    waitForSecs(10);
    // set text
    Row information = this.engine.findElement(Criteria.isRow().withText("1.1")).getFirstElement();
    Text txt =
        this.engine.findElementWithDuration(Criteria.isTextField().inContainer(information), Duration.ofSeconds(10))
            .getFirstElement();
    txt.setText(childArtifactName);
    LOGGER.LOG.info("New child artifact name is : " + childArtifactName);
    this.driverCustom
        .getWebElement("//div[@class='input-wrapper']/input[@placeholder='Type to filter artifacts by text or by ID']")
        .click();
    waitForSecs(5);
    // page refress
    refresh();
    waitForSecs(5);
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         This method used after selecting Remove artifact
   *         {@link #RMArtifactsPage.openContextMenuForSelectedArtifact}
   * @return warning message
   */
  public String getValidationMessageFromConfirmRemovaldialog() {
    String validationMessage = this.driverCustom.getPresenceOfWebElement("//div[@class='messageSummary']").getText();
    LOGGER.LOG.info("Message displayed as : '" + validationMessage + "'");
    return validationMessage;

  }

  /**
   * @author UUM4KOR
   *         <p>
   *         This method used after secting submenu 'Remove Artifact'
   *         {@link #RMArtifactsPage.openContextMenuForSelectedArtifact}of parent artifact one dialogue will displat in
   *         that dialogue warning message will display
   *         {@link #RMArtifactsPage.getValidationMessageFromConfirmRemovaldialog}
   */
  public void selectCheckBoxFromConfirmRemovalDialog() {
    try {
      Checkbox cbxDeleteAll =
          this.engine
              .findElementWithDuration(Criteria.isCheckbox()
                  .withLabel("If the artifacts are not in other modules, permanently delete them."), this.timeInSecs)
              .getFirstElement();
      cbxDeleteAll.click();
      LOGGER.LOG.info("'If the artifacts are not in other modules, permanently delete them' check box selected");
    }
    catch (Exception e) {

      this.driverCustom.getWebElement(
          "//label[text()='If the artifacts are not in other modules, permanently delete them.']/preceding-sibling::div/input[@type='checkbox']")
          .click();
      LOGGER.LOG.info("'If the artifacts are not in other modules, permanently delete them' check box selected");

    }
  }

  /**
   *
   */
  public void selectLinkTheArtifactCheckBox() {
    Checkbox linkTheArtifacts = this.engine.findElementWithDuration(
        Criteria.isCheckbox().withLabel("Create a link from the copied artifact to the original artifact"),
        this.timeInSecs).getFirstElement();
    linkTheArtifacts.click();

  }

  /**
   * <p>
   * After Export artifact using {@link RMArtifactsPage#exportArtifact} <br>
   * Get Data from specific cell using {@link RMArtifactsPage#getDataFromCellOfXLSX} <br>
   * With the cell data, get the first URI value
   *
   * @param inputData input data
   * @return URI
   */
  public String getUriOfFromCellData(final String inputData) {
    String startOfString = inputData.substring(inputData.indexOf("uri=") + 4, inputData.length());
    return startOfString.split("}")[0].trim();
  }

  /**
   * <p>
   * After click on "Create..." button and select option "Import Artifact..."
   * <p>
   *
   * @author NVV1HC
   * @param importOption option to import a new artifact, e.g: Import requirements from within a text document;Import
   *          requirements from a ReqIF file; Import requirements from a CSV file or spreadsheet
   */
  public void selectOptionOnImportDialogAndClickNext(final String importOption) {
    this.driverCustom.isElementClickable(RMConstants.IMPORT_OPTION_CHECKBOX_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)), importOption);
    this.driverCustom.getWebElement(RMConstants.IMPORT_OPTION_CHECKBOX_XPATH, importOption).click();
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_SELECTNEXT_BUTTON_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)));
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_SELECTNEXT_BUTTON_XPATH).click();
    waitForSecs(3);
  }

  /**
   * <p>
   * Right click on a folder and select "Create Artifact" then select option to create artifact, e.g: "Heading",
   * "Information","Import Artifact..."
   * <p>
   *
   * @author NVV1HC
   * @param folderName Name of folder to right click and create a new artifact
   * @param option option to create a new artifact, e.g: "Heading", "Information","Import Artifact..."
   */
  public void rightClickOnFolderAndSelectCreateArtifact(final String folderName, final String option) {
    waitForSecs(5);
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.contextClick(this.driverCustom.getWebElement(RMConstants.FOLDER_NAME_XPATH, folderName)).perform();
    waitForSecs(2);
    this.driverCustom.getWebElement(RMConstants.CREATE_ARTIFACT_OPTION_XPATH).click();
    waitForSecs(2);
    this.driverCustom.getWebElement(RMConstants.CREATE_ARTIFACT_SUBOPTION_XPATH, option).click();
    waitForSecs(2);
  }

  /**
   * Click on a folder, the we can select create a new artifact inside folder or import a new artifact
   * <p>
   *
   * @author VDY1HC
   * @param folderName Name of folder to be clicked
   */
  public void clickOnFolder(final String folderName) {
    WebElement treeProjectFolder = this.driverCustom.getPresenceOfWebElement(
        "//div[contains(@class,'dijitContentPane dijitContentPaneSingleChild') and not(contains(@style,'display: none'))]" +
            "//span[@title='" + folderName + "']");
    try {
      this.driverCustom.getClickableWebElement(treeProjectFolder).click();
      waitForSecs(2);
      if (this.driverCustom.isElementVisible(
          "//div[contains(@class,'dijitContentPane dijitContentPaneSingleChild') and not(contains(@style,'display: none'))]" +
              "//div[@class='condition-div' and contains(@title,'Folder') and contains(@title,'treenodeName')]",
          Duration.ofSeconds(15))) {}
    }
    catch (ElementClickInterceptedException e) {
      // Wait until covered element disappear
      this.driverCustom.isElementInvisible("//div[@class='busyPane']", this.timeInSecs);
      treeProjectFolder.click();
    }


//    waitForSecs(10);
//    if (this.driverCustom.isElementVisible(
//        "//div[contains(@class,'dijitContentPane dijitContentPaneSingleChild') and not(contains(@style,'display: none'))]" +
//            "//div[@class='condition-div' and contains(@title,'Folder') and contains(@title,'treenodeName')]",
//        15)) {
//      treeProjectFolder.click();
//      waitForSecs(2);
//    }
//    waitForSecs(10);
//    if (this.driverCustom.isElementVisible(
//        "//div[contains(@class,'dijitContentPane dijitContentPaneSingleChild') and not(contains(@style,'display: none'))]" +
//            "//div[@class='condition-div' and contains(@title,'Folder') and contains(@title,'treenodeName')]",
//        15)) {
//      treeProjectFolder.click();
//      waitForSecs(2);
//    }
  }

  /**
   * <p>
   * Delete an artifact without search
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact to be deleted
   */
  public void deleteArtifactWithoutSearch(final String artifactID) {
    waitForSecs(3);
    WebElement artifactToBeDeleted =
        this.driverCustom.getWebElement(RMConstants.RMMODULEPAGE_ARTIFACTCONTENT_XPATH, artifactID);
    ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);",
        artifactToBeDeleted);
    waitForSecs(2);
    artifactToBeDeleted.click();
    waitForSecs(2);
    this.driverCustom.click(RMConstants.RMMODULEPATE_ACTION_BUTTON_OF_ARTIFACT, artifactID);
    waitForSecs(3);
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_REMOVEARTIFACT_BUTTON_XPATH).click();
    waitForSecs(3);
    this.driverCustom.isElementVisible("//label[contains(@for,'removeAndDelete')]", this.timeInSecs);
    WebElement chxRemoveAndDelete =
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_CHECKBOX_REMOVEANDDELETEARTIFACT_XPATH);
    this.driverCustom.getClickableWebElement(chxRemoveAndDelete).click();
    waitForSecs(3);
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_REMOVEANDDELETE_BUTTON_XPATH).click();
    WebDriverWait wait =
        new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(RMConstants.REMOVING_ARTIFACT_XPATH)));
    waitForSecs(3);
  }


  /**
   * <p>
   * Verify the current position is inside the module (artifact) or not
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact to be verified {@inheritDoc}
   */
  @Override
  public boolean verifyPositionInTheArtifactOrNot(final String artifactID) {
    String resourceID = "";
    try {
      resourceID = this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_GETMODULETEST_XPATH).getText().trim();
    }
    catch (Exception e) {}
    return resourceID.equals(artifactID);
  }

  /**
   * @author LPH1HC
   *         <p>
   *         Click on Add filter button {@link RMArtifactsPage#addFilter} Input Attribute name in searching field
   *         {@link RMArtifactsPage#searchAttributeInFilter} Select Attribute name in searching result - using
   *         attributeName parameter Input List value to text field in Choose Value - using listValue parameter
   * @param attributeName - artifact Name which has choose value as text field
   * @param listValue - listValue which is list value need to input text field in choose value<br>
   *          (Format for listValue: "value1, value2, value3...")
   */
  public void addAndCloseListValueByTextFieldForAttributeInFilter(final String attributeName, final String listValue) {
    // waitForPageLoaded();
    // Select Attribute name in searching result
    Label labelAttribute = this.engine
        .findElementWithDuration(Criteria.isLabel().withText(attributeName), this.timeInSecs).getFirstElement();
    labelAttribute.click();
    LOGGER.LOG.info(RMConstants.CLICKED_ON + attributeName);
    WebElement inputValue = this.driverCustom.getVisibleWebElements(RMConstants.CHOOSE_VALUE_TEXTFIELD_XPATH).get(0);
    try {
      inputValue.sendKeys(listValue);
      LOGGER.LOG.info("Input value to Choose Value text field of " + attributeName);
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (inputValue != null) {
      Button btnAddAndClose = this.engine
          .findElementWithDuration(Criteria.isButton().withText("Add and Close"), this.timeInSecs).getFirstElement();
      btnAddAndClose.click();
      LOGGER.LOG.info("Clicked on 'Add and Close' button.");
    }
    else {
      throw new InvalidArgumentException(listValue + " - doesn't exist in the New Filter dialog.");
    }
  }

  /**
   * @author LPH1HC <br>
   *         Click on Artifact ID link in table by open new tab <br>
   * @param artifactID - Artifact ID hyperlink need to open
   */
  public void clickOnArtifactByOpenNewTab(final String artifactID) {
    waitForSecs(10);
    Row rowArtifact = null;
    try {
      rowArtifact = this.engine.findElement(Criteria.isRow().containsText(artifactID)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (rowArtifact != null) {
      WebDriver driver = this.driverCustom.getWebDriver();
      String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
      driver.findElement(By.linkText(artifactID)).sendKeys(selectLinkOpeninNewTab);
      waitForSecs(10);
      LOGGER.LOG.info("Open artifact as " + artifactID + " in new tab successfully");
    }
    else {
      throw new InvalidArgumentException("'" + artifactID + "'  doesn't exist in the attribute list.");
    }
  }

  /**
   * <p>
   * Click on Add filter button {@link RMArtifactsPage#addFilter} <br>
   * Input Attribute name in searching field {@link RMArtifactsPage#searchAttributeInFilter} <br>
   * Click on attribute in searching filter (Attribute value is check-box type) <br>
   * Select Condition in drop down (Condition values such as: exists, does not exist, is unassigned)<br>
   * Click on "Add and Close" button <br>
   *
   * @param additionalParams <br>
   *          ARTIFACT_NAME - artifact Name - Attribute value is check-box type <br>
   *          DEFAULT_CONDITION - default value display in condition drop-down <br>
   *          EXPECT_CONDITION - expect value need to select for condition (Ex: exists, does not exist, is unassigned)
   *          <br>
   * @author LPH1HC
   */
  public void selectConditionWhenAddAndCloseListValueForAttributeInFilter(final Map<String, String> additionalParams) {
    String attribute = additionalParams.get("ARTIFACT_NAME");
    String defaultCondition = additionalParams.get("DEFAULT_CONDITION");
    String expectCondition = additionalParams.get("EXPECT_CONDITION");

    // Select attribute after searching
    Label labelAttribute = this.engine.findElement(Criteria.isLabel().withText(attribute)).getFirstElement();
    labelAttribute.click();
    LOGGER.LOG.info("Clicked on : " + attribute);
    // Select Condition - xpath
    this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
    waitForSecs(8);
    this.driverCustom.getWebElement(RMConstants.DEFAULT_CONDITION_IN_ADD_FILTER_DIALOG, defaultCondition).click();
    waitForSecs(8);
    LOGGER.LOG.info("Clicked on default condition : " + defaultCondition);
    this.driverCustom.getVisibleWebElements("//td[text()='" + expectCondition + "']").get(0).click();
    LOGGER.LOG.info("Selected expected condition value : " + expectCondition);
    if (this.driverCustom.isElementClickable(RMConstants.ADD_AND_CLOSE_BUTTON_XPATH, Duration.ofSeconds(120))) {
      Button btnAddAndClose = this.engine.findElement(Criteria.isButton().withText("Add and Close")).getFirstElement();
      btnAddAndClose.click();
      waitForSecs(15);
      LOGGER.LOG.info("Clicked on 'Add and Close' button.");
    }
    else {
      throw new InvalidArgumentException("Does not select success condition");
    }
  }

  /**
   * @author KCE1KOR <br>
   *         Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   *         Click on 'All' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   *         Click on 'Search' button present in Artifacts page inside module, 'Module Find' window is displayed.<br>
   *         Click on 'Find What' tab, Enter artifact id in 'Module Find' dialog.<br>
   *         Click on 'Find What' button.<br>
   *         Close dialog.
   * @param artifactID is ID of artifact belong to this module
   * @param artifactContent is Content of artifact belong to the artifact
   * @return true if found and selected artifact inside module successfully.
   */
  public boolean searchGoToInModuleFindWhat(final String artifactContent, final String artifactID) {
    boolean flag = false;
    // waitForPageLoaded();
    waitForSecs(20);
    // Click on search Go To icon
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_MODULEARTIFACTSEARCH_BUTTON_XPATH,
        Duration.ofSeconds(50));
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_MODULEARTIFACTSEARCH_BUTTON_XPATH);
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_FINDWHAT_TEXTFIELD_XPATH).clear();
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_FINDWHAT_TEXTFIELD_XPATH).click();
    this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_FINDWHAT_TEXTFIELD_XPATH).sendKeys(Keys.CONTROL, "v");
    Button findBtn = this.engine.findElement(Criteria.isButton().withText("Find")).getFirstElement();
    findBtn.click();
    Row rowArtifact = null;
    try {
      rowArtifact = this.engine.findElement(Criteria.isRow().containsText(artifactContent)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (rowArtifact != null) {
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_SEARCHARTIFACT_CONTENT_XPATH, this.timeInSecs,
          artifactContent);
      waitForSecs(10);
      LOGGER.LOG.error("Searched artifact as " + artifactContent + " is displayed successfully");

    }
    else {
      throw new InvalidArgumentException("'" + artifactID + "'  doesn't exist in the attribute list.");
    }
    this.driverCustom.isElementClickable(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(50), RMConstants.CLOSE);
    clickOnJazzButtons(RMConstants.CLOSE);
    // Verify searched Artifact is displayed or not
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_CHECKBOX_OFARTIFACT_XPATH, Duration.ofSeconds(20),
        artifactID)) {
      System.out.println("true");
    }
    else {
      System.out.println("false");
    }
    String getStatusCheckbox = this.driverCustom
        .getWebElement(RMConstants.RMARTIFACTPAGE_CHECKBOX_OFARTIFACT_XPATH, artifactID).getAttribute("aria-label");
    System.out.println("getStatusCheckbox: " + getStatusCheckbox);
    String ariaLabelSelected = "Artifact " + artifactID +
        " selected. To remove from selection press space bar. To move around rows and columns use arrow keys.";
    String ariaLabelUnSelected =
        "Artifact " + artifactID + ". To select press space bar. To move around rows and columns use arrow keys.";
    if (getStatusCheckbox.equalsIgnoreCase(ariaLabelSelected)) {
      flag = true;
    }
    else if (getStatusCheckbox.equalsIgnoreCase(ariaLabelUnSelected)) {
      flag = false;

    }
    return flag;
  }


  /**
   * @author KCE1KOR
   *         <p>
   *         This method used to select after searhing artifact using content
   * @param artifactContent -
   * @param artifactID is ID of artifact belong to this module
   */
  public void selectSearchedArtifactCheckBoxAndCopyContent(final String artifactContent, final String artifactID) {
    waitForPageLoaded();
    Row rowArtifact = null;
    try {
      rowArtifact = this.engine.findElementWithDuration(Criteria.isRow().containsText(artifactID), this.timeInSecs)
          .getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (rowArtifact != null) {
      rowArtifact.scrollToElement();
      rowArtifact.click();
      LOGGER.LOG.info(" Searched content Artifact =' " + artifactID + " check box selected");
      String[] artifactInfo = { artifactID, artifactContent };
      WebElement ele = this.driverCustom
          .getWebElement(RMConstants.RMARTIFACTPAGE_ARTIFACT_CONTENT_FOLLOWING_ARTIFACT_ID_XPATH, artifactInfo);
      Actions act = new Actions(this.driverCustom.getWebDriver());
      act.moveToElement(ele).doubleClick().build().perform();
      // this.driverCustom.clickTwice(RMConstants.RMARTIFACTPAGE_ARTIFACT_CONTENT_XPATH,artifactContent);
      waitForSecs(2);
      Actions selectAndCopy = new Actions(this.driverCustom.getWebDriver());
      selectAndCopy.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
      selectAndCopy.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
      LOGGER.LOG.info("Searched Artifact Content  =' " + artifactContent + " is copied successfully");
      waitForSecs(2);
    }
    else {
      throw new InvalidArgumentException(
          "'" + artifactID + "'  doesn't exist in the attribute list and content not copied.");
    }
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         This method used after openn module {@link RMDashBoardPage#openSearchedSpecification}
   * @param artifactId artifact id
   * @param artifactName artifact name
   */
  public void rightClickOnSelectedArtifactContents(final String artifactId, final String artifactName) {
    waitForPageLoaded();
    waitForSecs(8);
    this.driverCustom.switchToDefaultContent();
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(20), artifactId);
    Row row = null;
    try {
      this.driverCustom.switchToDefaultContent();
      row = this.engine.findElement(Criteria.isRow().containsText(artifactId)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (row != null) {
      Cell cel = this.engine.findElement(Criteria.isCell().inContainer(row).withIndex(4)).getFirstElement();
      cel.click();
      new Actions(this.driverCustom.getWebDriver())
          .moveToElement(this.driverCustom.getWebElement("//p[text()='" + artifactName + "']")).contextClick().build()
          .perform();
      waitForSecs(10);
      LOGGER.LOG
          .info("Right clicked successfully on Select artifact " + artifactName + " with id is '" + artifactId + "'");
      waitForSecs(5);
    }
    else {
      waitForSecs(10);
      throw new InvalidArgumentException(
          "Artifact " + artifactId + " Could not be found or not right clicked successfully on '" + artifactName + "'");
    }
  }

  /**
   * This method check if is there any existing empty data in columnName column from Artifact Table <br>
   *
   * @author LPH1HC <br>
   * @param columnName Name of column in Artifact Table <br>
   * @return to check variable - true if there is empty data in columnName, false if not any empty data <br>
   */
  public boolean isEmptyDataExistingFromArtifactTable(final String columnName) {
    boolean check = false;
    this.driverCustom.isElementInvisible(RMConstants.MESSAGE_LOADING_XPATH, Duration.ofSeconds(20));
    WebElement viewTable = this.driverCustom.getWebElement(RMConstants.TABLE_XPATH);
    List<WebElement> headers = viewTable.findElements(By.xpath(RMConstants.HEADER_TABLE_XPATH));

    List<String> viewHeadersList = new ArrayList<>();
    headers.stream().forEach(x -> viewHeadersList.add(x.getText()));
    LOGGER.LOG.info("Header column names are added in List.");

    int columnIndex =
        viewHeadersList.stream().anyMatch(x -> x.equals(columnName)) ? viewHeadersList.indexOf(columnName) : 0;
    LOGGER.LOG.info(String.format("Header column index is '%s'", columnIndex));

    List<WebElement> rows = viewTable.findElements(By.xpath(RMConstants.ROW_IN_TABLE_XPATH));
    for (int i = 0; i < rows.size(); i++) {
      try {
        String valueAtColumn =
            rows.get(i).findElements(By.xpath(RMConstants.ROW_TABLE_XPATH)).get(columnIndex).getText();
        if (valueAtColumn.isEmpty()) {
          check = true;
          LOGGER.LOG.info("Data empty in row " + i + " - column " + columnName);
          break;
        }
      }
      catch (Exception e) {
        LOGGER.LOG.warn(String.format("Rows index '%d' have no Column index '%d'", i, columnIndex));
      }
    }

    return check;
  }

  /**
   * This method to check if is there value exists in columnName from Artifact Table <br>
   *
   * @author KYY1HC <br>
   * @param columnName Name of column in Artifact Table <br>
   * @param dataCompare the value in cell to verify is displayed or not <br>
   * @return to check variable - true if there is expectedData displayed in columnName, otherwise returned false <br>
   */
  public boolean verifyDataDisplayedInColumn(final String columnName, final String dataCompare) {
    boolean check = false;
    this.driverCustom.isElementInvisible(RMConstants.MESSAGE_LOADING_XPATH, Duration.ofSeconds(20));
    WebElement viewTable = this.driverCustom.getWebElement(RMConstants.TABLE_XPATH);
    List<WebElement> headers = viewTable.findElements(By.xpath(RMConstants.HEADER_TABLE_XPATH));
    List<String> viewHeadersList = new ArrayList<>();
    headers.stream().forEach(x -> viewHeadersList.add(x.getText()));
    LOGGER.LOG.info("Header column names are added in List.");

    int columnIndex =
        viewHeadersList.stream().anyMatch(x -> x.equals(columnName)) ? viewHeadersList.indexOf(columnName) : 0;
    LOGGER.LOG.info(String.format("Header column index is '%s'", columnIndex));

    List<WebElement> rows = viewTable.findElements(By.xpath(RMConstants.ROW_IN_TABLE_XPATH));
    for (int i = 0; i < rows.size(); i++) {
      try {
        String valueAtColumn =
            rows.get(i).findElements(By.xpath(RMConstants.ROW_TABLE_XPATH)).get(columnIndex).getText();
        if (valueAtColumn.equalsIgnoreCase(dataCompare)) {
          check = true;
          LOGGER.LOG.info("Data '" + dataCompare + "' is displayed in row '" + i + "' at column '" + columnName + "'");
          break;
        }
      }
      catch (Exception e) {
        LOGGER.LOG.warn(String.format("Rows index '%d' have no Column index '%d'", i, columnIndex));
      }
    }

    return check;
  }

  /**
   * @author LPH1HC <br>
   *         This method is check if ID and URL existing in XLSX Export file <br>
   * @param excelPath - path of XLSX export <br>
   * @param sheetIndex - index of sheet in XLSX export <br>
   * @param columnIndex - column index of XLSX export - start from 0<br>
   * @return true if there is existing ID and URL in Excel XLSX Export <br>
   * @throws IOException -
   */
  public boolean isExistingIDAndURLInOneColumnFromExcelXLSXExport(final String excelPath, final String sheetIndex,
      final String columnIndex)
      throws IOException {
    boolean check = false;
    ExcelCSVReader ex = new ExcelCSVReader(excelPath);
    int a = ex.getNumberofArtifactsFromExcelFile();
    for (int i = 1; i < (a + 2); i++) {
      String st = ex.readDataFromCellOfXLSX(excelPath, sheetIndex, String.valueOf(i), columnIndex);
      if (!st.isEmpty()) {
        check = st.contains("{LINK id") && st.contains("uri=https://");
      }
      if (check) {
        break;
      }
    }
    return check;
  }

  /**
   * @author LPH1HC <br>
   *         Select one Artifact in table by check box {@link RMArtifactsPage#selectArtifact(String)} <br>
   *         Edit content {@link RMArtifactsPage#openContextMenuForSelectedArtifact(String, String)} <br>
   *         Open Create Term Dialog by Right Click <br>
   */
  public void openCreateTermDialogByRightClick() {
    // select all
    Actions selectAct = new Actions(this.driverCustom.getWebDriver());
    selectAct.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
    waitForSecs(3);
    // Right-click
    WebElement txb = this.driverCustom.getWebElement(RMConstants.EDIT_ARTIFACT_TEXTBOX_XPATH);
    selectAct.contextClick(txb).perform();
    waitForSecs(3);
    this.driverCustom.switchToFrame(RMConstants.XPATH_IFRAME_MENU_DROPDOWN_RIGHT_CLICK);
    if (this.driverCustom.isElementVisible(RMConstants.XPATH_OPTION_CREATE_TERM, Duration.ofSeconds(20))) {
      this.driverCustom.getWebElement(RMConstants.XPATH_OPTION_CREATE_TERM).click();
    }
    else {
      LOGGER.LOG.error("Can not open menu drop down in Right click");
    }
    this.driverCustom.switchToDefaultContent();
    waitForSecs(5);
  }


  /**
   * @author LPH1HC <br>
   *         Open Creat New Term {@link RMArtifactsPage#openCreateTermDialogByRightClick()} <br>
   *         Input data to Create Term dialog and click OK button <br>
   * @param additionalParams - text need to selected in text content of artifact, included <br>
   *          Initial_Content: Initial Content <br>
   *          Artifact_Type: Artifact Type <br>
   */
  public void createNewTerm(final Map<String, String> additionalParams) {
    Dialog dlgCreateTerm =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Create Term"), Duration.ofSeconds(20))
            .getFirstElement();
    Text initialContentTxt = this.engine
        .findElementWithDuration(Criteria.isTextField().isMultiLine().inContainer(dlgCreateTerm), Duration.ofSeconds(3))
        .getFirstElement();
    String inputInitialContent = additionalParams.get(RMConstants.INITIAL_CONTENT_IN_CREATE_TERM_DIALOG);
    if (initialContentTxt.isElementEnable(Duration.ofSeconds(2))) {
      try {
        initialContentTxt.setText(inputInitialContent);
        LOGGER.LOG.info("Input Initial Content");
        waitForSecs(3);
        Dropdown artifactTypeDropDown =
            this.engine.findElement(Criteria.isDropdown().withLabel("Artifact type:").inContainer(dlgCreateTerm))
                .getFirstElement();
        artifactTypeDropDown
            .selectOptionWithText((additionalParams.get(RMConstants.ARTIFACT_TYPE_IN_CREATE_TERM_DIALOG)));
        LOGGER.LOG.info("Select Artifact type");
        waitForSecs(10);
        Button okBtn =
            this.engine.findElement(Criteria.isButton().withText("OK").inContainer(dlgCreateTerm)).getFirstElement();
        okBtn.click();
        LOGGER.LOG.error("Create New Term Successfully");
        waitForSecs(5);
      }
      catch (Exception e) {
        LOGGER.LOG.error("Create New Term Fail");
      }
    }


  }


  /**
   * @author LPH1HC <br>
   *         Open edit filter icon in filter session {@link RMArtifactsPage#openEditFilterDialogOfOneCondition(String)}
   *         <br>
   *         Edit Artifact ID in Edit Filter dialog and click on Update button <br>
   * @param newID - new Artifact ID
   */
  public void editArtifactIDFilter(final String newID) {
    Dialog dlgEditArtifactIDFilter =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Edit Filter"), Duration.ofSeconds(20))
            .getFirstElement();
    Text artifactIDTxt = this.engine
        .findElementWithDuration(Criteria.isTextField().inContainer(dlgEditArtifactIDFilter), Duration.ofSeconds(3))
        .getFirstElement();
    try {
      artifactIDTxt.clearText();
      artifactIDTxt.setText(newID);
      LOGGER.LOG.info("Input new Artifact ID as " + newID);
      waitForSecs(5);
      Button updateBtn = this.engine
          .findElementWithDuration(Criteria.isButton().withText("Update").inContainer(dlgEditArtifactIDFilter),
              Duration.ofSeconds(5))
          .getFirstElement();
      updateBtn.click();
      LOGGER.LOG.info("Edit successfully Artifact ID as " + newID);
      waitForSecs(10);
    }
    catch (Exception e) {
      LOGGER.LOG.info("Can not input new Artifact ID as " + newID);
    }
  }


  /**
   * @author LPH1HC <br>
   *         Select one Artifact in table by check box {@link RMArtifactsPage#selectArtifact(String)} <br>
   *         Edit content {@link RMArtifactsPage#openContextMenuForSelectedArtifact(String, String)} <br>
   *         Open Look Up Term Dialog by Right Click <br>
   */
  public void openLookUpTermDialogByRightClick() {
    // select all
    Actions selectAct = new Actions(this.driverCustom.getWebDriver());
    selectAct.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
    waitForSecs(Duration.ofSeconds(3));
    // Right-click
    WebElement elementEditEnabled = this.driverCustom.getWebElements(RMConstants.XPATH_ELEMENT_EDIT_ENABLE).get(0);
    selectAct.moveToElement(elementEditEnabled).contextClick().build().perform();
    waitForSecs(3);
    this.driverCustom.switchToFrame(RMConstants.XPATH_IFRAME_MENU_DROPDOWN_RIGHT_CLICK);
    if (this.driverCustom.isElementVisible(RMConstants.XPATH_OPTION_LOOKUP_TERM, Duration.ofSeconds(20))) {
      this.driverCustom.getWebElement(RMConstants.XPATH_OPTION_LOOKUP_TERM).click();
    }
    else {
      LOGGER.LOG.error("Can not open menu drop down in Right click");
    }
    this.driverCustom.switchToDefaultContent();
    waitForSecs(5);
  }

  /**
   * @author LPH1HC <br>
   *         Open Look Up Term dialog {@link RMArtifactsPage#openLookUpTermDialogByRightClick()} <br>
   *         This method is verify if existing term display in look up term dialog or not. Yes return true, No return
   *         false <br>
   * @param termKeyWord - term was created before
   * @return true if termKeyWord display in dialog
   */
  public boolean isTermExistingLookUpTermDialog(final String termKeyWord) {
    boolean check = false;
    List<WebElement> list =
        this.driverCustom.getWebElements(RMConstants.XPATH_LIST_TERM_IN_LOOKUP_TERM_DIALOG, termKeyWord);
    if (!list.isEmpty()) {
      check = true;
    }
    return check;
  }


  /**
   * <p>
   * After edit the view, click on "Save changes to this view" button to save the changes to the current view
   * <p>
   *
   * @author NVV1HC
   */
  public void clickSaveChangesToThisView() {
    Button btnSaveChangesToThisView = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Save changes to this view"), this.timeInSecs)
        .getFirstElement();
    btnSaveChangesToThisView.click();
    waitForPageLoaded();
    waitForSecs(3);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'Configure Page Setting' icon present in column header in the Artifacts page, list of option is
   * displayed.<br>
   * Click on 'More...' option from the displayed list of option 'Change Column Display Settings' window displayed.<br>
   * Search the attribute name in 'Type to filter by attribute name' text box and click on 'Add' button.<br>
   * Added column is displayed in 'Columns to show' section to be displayed in the artifact table.<br>
   * Click on 'OK' button in 'Change Column Display Settings' dialog.
   * <p>
   *
   * @author NVV1HC
   * @param columnName stores different link type of link: 'Validated By','Implemented By' etc...
   */
  public void removeColumnFromTable(final String columnName) {
    waitForPageLoaded();
    List<WebElement> viewColumnNames = this.driverCustom.getWebDriver()
        .findElements(By.xpath(RMConstants.RMARTIFACTSPAGE_COLUMNBORDER_DROPDOWN_XPATH));
    List<String> columnnames = new ArrayList<>();
    viewColumnNames.stream().map(WebElement::getText).forEach(columnnames::add);
    if (columnnames.stream().anyMatch(x -> x.trim().equals(columnName))) {
      for (int i = 0; i < 3; i++) {
        if (!this.driverCustom.isElementVisible(RMConstants.RMREVIEWSPAGE_SELECT_TYPE_XPATH, Duration.ofSeconds(20),
            "More...")) {
          WebElement configurationElement = this.driverCustom.getWebDriver()
              .findElement(By.xpath(RMConstants.ARTIFACTPAGE_CONFIGUREPGESETTINGS_XPATH));
          configurationElement.click();
          LOGGER.LOG.info("Clicked on 'Configure Page Settings' button.");
        }
        else {
          break;
        }
      }
      WebElement optionElement =
          this.driverCustom.getWebDriver().findElement(By.xpath(RMConstants.ARTIFACTPAGE_MOREBUTTON_XPATH));
      optionElement.click();
      LOGGER.LOG.info("Clicked on 'More...' option.");
      waitForSecs(2);
      this.driverCustom.getPresenceOfWebElement(RMConstants.ARTIFACTPAGE_DISPLAYEDCOLUMNHEADER_XPATH, columnName)
          .click();
      LOGGER.LOG.info("Clicked on - " + columnName);
      clickOnJazzButtons("Remove");
      LOGGER.LOG.info("Clicked on 'Remove' button.");
      clickOnJazzButtons("OK");
      LOGGER.LOG.info(RMConstants.CLICK_ON_OK_BUTTON);
      waitForPageLoaded();
      waitForSecs(5);
    }
    else {
      LOGGER.LOG.info("The column '" + columnName + "' is not displayed in table.");
    }
  }

  /**
   * @author LPH1HC <br>
   *         Get total artifact number of Showing artifacts in the bottom of Artifact Table and compare with
   *         originalValue <br>
   * @param originalValue - <br>
   * @return true if all the count values matching with originalValue <br>
   */
  public boolean isAllTheCountValuesMatchingWithTheOriginalValue(final String originalValue) {
    boolean check = false;
    String showingArtifacttxt = "";

    String count = "";
    String filteredCount = "";
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE_XPATH, Duration.ofSeconds(200));
    if (this.driverCustom.isElementVisible(RMConstants.NUMBER_ARTIFACT_SHOWING_IN_TABLE_XPATH,
        Duration.ofSeconds(80))) {
      showingArtifacttxt =
          this.driverCustom.getWebElement(RMConstants.NUMBER_ARTIFACT_SHOWING_IN_TABLE_XPATH).getText();

      count = showingArtifacttxt.split(" ")[3].trim();
      filteredCount = showingArtifacttxt.split(" ")[1].trim();
      LOGGER.LOG.info("Get all the count values as " + count);
      LOGGER.LOG.info("Get the filtered count values as " + filteredCount);
    }
    else {
      LOGGER.LOG.error("Not found Artifact Number showing in the bottom of artifact table");
    }

    if (count.equalsIgnoreCase(originalValue)) {
      check = true;
      LOGGER.LOG.info("All the count values is matching with the original value");
    }
    if (filteredCount.equalsIgnoreCase(originalValue)) {
      check = true;
      LOGGER.LOG.info("All the filtered count values is matching with the expected value");
    }
    else {
      LOGGER.LOG.info("All the count values is mismatching with the original value");
    }

    return check;
  }

  /**
   * @author LPH1HC<br>
   *         Check Status of Validated By links of all visible artifact rows from table, which is passed or not <br>
   * @return true - if there is at least one Passed statatus of one Artifact row <br>
   *         false - if not found any Validate By link having Passed status in one Artifact row
   */
  public boolean isPassedStatusOfValidatedByLinkExistingInOneArtifactRow() {
    boolean check = true;
    boolean checkRow = false;
    String xpathImageIcon = "";
    List<WebElement> listArtifactRow = this.driverCustom.getWebElements(RMConstants.ARTIFACT_ROW_XPATH);
    int countRow = listArtifactRow.size();
    for (int i = 1; i < (countRow + 1); i++) {
      xpathImageIcon = RMConstants.ARTIFACT_ROW_XPATH + "[" + i + "]" + RMConstants.IMG_ICON_XPATH;
      List<WebElement> listElementHasImgIcon = this.driverCustom.getWebElements(xpathImageIcon);
      for (WebElement el : listElementHasImgIcon) {
        if (el.getAttribute("src").contains("success_ovr.gif")) {
          checkRow = true;
          break;
        }
      }
      check = checkRow && check;
      if (!check) {
        LOGGER.LOG.info("Not found any Validate By link having Passed status in one Artifact row with index " + i);
        break;
      }
    }
    LOGGER.LOG.info("There is at least one Validated By link havs Passed status in one Artifact row ");
    return check;
  }

  /**
   * Verify if there is no error message displayed <br>
   *
   * @author VDY1HC
   * @return true - if error message displayed false - if no error message displayed
   */
  public boolean verifyNoErrorMessageDisplay() {
    if (this.driverCustom.isElementVisible(RMConstants.ERROR_MESSAGE_XPATH, Duration.ofSeconds(60))) {
      return this.driverCustom.getPresenceOfWebElement(RMConstants.ERROR_MESSAGE_XPATH).getText().isEmpty();
    }
    return true;
  }

  /**
   * Get curent URL of the page
   *
   * @return curent URL
   */
  public String getCurrentURL() {
    return this.driverCustom.getWebDriver().getCurrentUrl();
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Artifacts");
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  public void waitForRMPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Artifacts");
    String currentWindow = this.driverCustom.getWebDriver().getWindowHandle();
    Set<String> windows = this.driverCustom.getWebDriver().getWindowHandles();

    if (windows.size() > 1) {
      // Now iterate using Iterator
      Iterator<String> window = windows.iterator();
      while (window.hasNext()) {
        String childWindow = window.next();
        if (!currentWindow.equals(childWindow)) {
          // Switch to the SPNEGO window to take screenshot and write to log file.
          this.driverCustom.getWebDriver().switchTo().window(childWindow);
          String childWindowTitle = this.driverCustom.getWebDriver().switchTo().window(childWindow).getTitle();
          LOGGER.LOG.info(" New window : " + childWindowTitle);
          // Handle SPNEGO Authentication Dialog
          if (childWindowTitle.contains("SPNEGO authentication is not supported.")) {
            LOGGER.LOG.info("'" + childWindowTitle + "' --> new window found automatic test script will not compleate");
            throw new WebAutomationException(
                " Unexpected window found with message 'SPNEGO authentication is not supported.' ");
          }
        }
      }
    }
  }


  /**
   * <p>
   * This method is to verify an element is displayed in viewport or not
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact
   * @return true if element is visible in viewport
   */
  public boolean verifyArtifactVisibleInViewport(final String artifactID) {
    WebDriver driver = this.driverCustom.getWebDriver();
    WebElement artifactElm = this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_ARTIFACT_XPATH, artifactID);
    return (Boolean) ((JavascriptExecutor) driver).executeScript(
        "var elem = arguments[0], box = elem.getBoundingClientRect(), cx = box.left + box.width / 2, cy = box.top + box.height / 2,e = document.elementFromPoint(cx, cy); for (; e; e = e.parentElement) {if (e === elem) return true;} return false;",
        artifactElm);
  }


  /**
   * Hover on Artifact link in Link tab and verifiy Link details tooltip loaded successfully <br>
   *
   * @author VDY1HC
   * @param artifactID - main Artifact ID
   * @param linkType - Link type of linked artifact
   * @param artifactLinkText - Text of link displayed
   * @param waitTimeInSecs - Max time to wait until loading finished
   */
  public void hoverOnArtifactLink(final String artifactID, final String linkType, final String artifactLinkText,
      final String waitTimeInSecs) {
    this.waitForSecs(10);
    String colid = this.driverCustom
        .getPresenceOfWebElement("//span[contains(text(),'" + linkType + "')]/ancestor::td[@class='columnHeader']")
        .getAttribute("column");
    this.waitForSecs(5);
    
    WebElement artifactLink = null;
    try {
     artifactLink =
        this.driverCustom.getWebDriver().findElement(By.xpath("//table[@rowlabel='Artifact " + artifactID +
            "']//td[@colid='" + colid + "']//a[@class='jazz-ui-ResourceLink' and text()='" + artifactLinkText + "']"));
    }catch(Exception e) {
      artifactLink =
          this.driverCustom.getWebDriver().findElement(By.xpath("//table[@rowlabel='Artifact " + artifactID +
              "']//td[@colid='" + colid + "']//a[@class='jazz-ui-ResourceLink']/strike[text()='" + artifactLinkText + "']"));
    }
    
    
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    int xValueInMiddle = artifactLink.getSize().getWidth() / 2;
    Actions act = new Actions(this.driverCustom.getWebDriver());
    // Click to the top left of link element
    act.moveToElement(artifactLink, -xValueInMiddle, 0).clickAndHold().build().perform();
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.getWebDriver().switchTo().activeElement();
  }
  
  /**
   * Hover on Artifact link in Link tab and verifiy Link details tooltip loaded successfully <br>
   * 
   * This testcase is made exclusively for 14q XC-CN, as in the moveToElement() method of hoverOnArtifactLink()
   * depends on xValueInMiddle, which depends solely on the monitor height and width (Thus fails manytimes)
   *    
   * @param artifactID - main Artifact ID
   * @param linkType - Link type of linked artifact
   * @param artifactLinkText - Text of link displayed
   * @param waitTimeInSecs - Max time to wait until loading finished
   */
  public void hoverOnArtifactLinkGeneric(final String artifactID, final String linkType, final String artifactLinkText,
      final String waitTimeInSecs) {
    this.waitForSecs(10);
    String colid = this.driverCustom
        .getPresenceOfWebElement("//span[contains(text(),'" + linkType + "')]/ancestor::td[@class='columnHeader']")
        .getAttribute("column");
    this.waitForSecs(5);
    WebElement artifactLink =
        this.driverCustom.getWebDriver().findElement(By.xpath("//table[@rowlabel='Artifact " + artifactID +
            "']//td[@colid='" + colid + "']//a[@class='jazz-ui-ResourceLink' and contains(text(),'" + artifactLinkText + "')]"));
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
    String mouseOverScript = "var event = new MouseEvent('mouseover', { view: window, bubbles: true, cancelable: true }); arguments[0].dispatchEvent(event);";
    js.executeScript(mouseOverScript, artifactLink);
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.getWebDriver().switchTo().activeElement();
  }


  /**
   * Click on close button on Popup if existing
   *
   * @author VDY1HC
   */
  public void clickOnCloseButtonOnPopup() {
    if (this.driverCustom.isElementClickable(RMConstants.CLOSE_BUTTON, Duration.ofSeconds(10))) {
      this.driverCustom.getPresenceOfWebElement(RMConstants.CLOSE_BUTTON).click();
    }
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Click on module by using ${@link RMArtifactsPage#clickOnArtifact(String)}. <br>
   * Clear all filters by using ${@link RMArtifactsPage#clearFilter()}. <br>
   * Add column "Verification Criteria" by using ${@link RMArtifactsPage#changeColumnDisplaySettings(String)}. <br>
   * Search artifact using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Then call this method to add/edit content of "Verification Criteria" attribute
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of an artifact
   * @param content what you want to add/edit the "Verification Criteria" attribute of an artifact
   */
  public void editVerificationCriteria(final String artifactID, final String content) {
    int tmp = 0;

    List<WebElement> listHeader = this.driverCustom.getWebElements(RMConstants.ARTIFACTPAGE_LISTHEADEROFTABLE_XPATH);
    for (int i = 0; i < listHeader.size(); i++) {
      try {
        String titleDisplayed = listHeader.get(i).getAttribute("innerText");
        if (titleDisplayed.equals("Verification Criteria")) {
          tmp = i + 1;
          break;
        }
      }
      catch (Exception e) {}
    }
    String[] arr = { artifactID, Integer.toString(tmp) };
    this.driverCustom.click(RMConstants.ARTIFACTPAGE_VERIFICATIONCRITERIA_COLUMN_XPATH, arr);
    waitForSecs(1);
    this.driverCustom.click(RMConstants.ARTIFACTPAGE_EDIT_VERIFICATOINCRITERIA_BUTTON_XPATH, artifactID);
    waitForSecs(2);
    WebElement inputTextArea =
        this.driverCustom.getWebElement(RMConstants.ARTIFACTPAGE_EDIT_VERIFICATOINCRITERIA_TEXTAREA_XPATH);
    inputTextArea.clear();
    waitForSecs(1);
    inputTextArea.sendKeys(content);
    waitForSecs(1);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Click on module by using ${@link RMArtifactsPage#clickOnArtifact(String)}. <br>
   * Clear all filters by using ${@link RMArtifactsPage#clearFilter()}. <br>
   * Add column "Verification Criteria" by using ${@link RMArtifactsPage#changeColumnDisplaySettings(String)}. <br>
   * Search artifact using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Then call this method to clear content of "Verification Criteria" attribute
   * <p>
   *
   * @author NVV1HC
   */
  public void clearVerificationCriteria() {
    WebElement inputTextArea =
        this.driverCustom.getWebElement(RMConstants.ARTIFACTPAGE_EDIT_VERIFICATOINCRITERIA_TEXTAREA_XPATH);
    inputTextArea.clear();
    waitForSecs(1);
  }

  /**
   * <p>
   * This method is to close Verification Criteria popup without clicking on OK or Cancel button
   * <p>
   *
   * @author NVV1HC
   * @return true if Verification Criteria popup is closed or vice versa
   */
  public boolean closeVerificationCriteriaPopupByClickingOutOfThePopup() {
    WebElement lastColumnElm =
        this.driverCustom.getWebElement(RMConstants.ARTIFACTPAGE_LASTCOLUMNOFARTIFACTTABLE_XPATH);
    lastColumnElm.click();
    waitForSecs(5);
    return this.driverCustom.isElementInvisible(RMConstants.ARTIFACTPAGE_VERIFICATIONCRITERIA_POPUP_XPATH,
        Duration.ofSeconds(20));
  }

  /**
   * <p>
   * This method is called after the selected view is updated some attributes
   * <p>
   *
   * @author NVV1HC
   * @return true if click on 'Apply' button successfully
   */
  public boolean clickOnApplyFilters() {
    Button applyFiltersBtn = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Apply filters"), this.timeInSecs).getFirstElement();
    applyFiltersBtn.click();
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_DISABLED_APPLY_BUTTON_XPATH, this.timeInSecs)) {
      return true;
    }
    fail("Can not click on 'Apply' button!");
    return false;
  }

  /**
   * <p>
   * This method is called after the module created to click add exiting artifact button
   * <p>
   *
   * @author KCE1KOR
   * @return true if click on Add Existing Artifact option successfully.
   */
  public boolean clickOnAddExistingArtifactOption() {
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_ADDEXISTINGARTIFACT_BUTTON_XPATH,
        Duration.ofSeconds(10));
    Button btn = this.engine.findElement(Criteria.isButton().withText("Add Existing Artifact")).getFirstElement();
    btn.click();
    LOGGER.LOG.info("Add Existing Artifact  --> Clicked successfully");
    return true;
  }

  /**
   * <p>
   * Search module ID using ${@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open searched module by ${@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Clear all filters by using ${@link RMArtifactsPage#clearFilter()}. <br>
   * Search the view by using ${@link RMArtifactsPage#searchView(String)}. <br>
   * Select searched view by using ${@link RMArtifactsPage#selectView(String)}. <br>
   * Then call this method: hover on view name then click on 'View Options' dropdown button and select 'Export View...'
   * <p>
   *
   * @author NVV1HC
   * @param viewName name of view to be exported
   * @param exportFormat export format, e.g: XLS, CSV
   * @return file name and path after exporting
   */
  public String exportView(final String viewName, final String exportFormat) {
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.moveToElement(this.driverCustom.getWebElement(RMConstants.RMMODULEPAGE_VIEW_XPATH, viewName)).build()
        .perform();
    waitForSecs(1);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_VIEWOPTION_BUTTON_XPATH);
    waitForSecs(1);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_EXPORTVIEW_BUTTON_XPATH);
    waitForSecs(5);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_EXPORTFORMAT_OPTION_XPATH, exportFormat);
    clickOnJazzButtons("OK");
    waitForSecs(Duration.ofSeconds((this.timeInSecs.getSeconds() * 3)));
    return getDownloadedFileNameAndPath();
  }

  /**
   * <p>
   * Search module ID using ${@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open searched module by ${@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Clear all filters by using ${@link RMArtifactsPage#clearFilter()}. <br>
   * Search the view by using ${@link RMArtifactsPage#searchView(String)}. <br>
   * Select searched view by using ${@link RMArtifactsPage#selectView(String)}. <br>
   * Export View by using ${@link RMArtifactsPage#exportView(String, String)}. <br>
   * Then call this method to verify the expected value of an attribute of one artifact is displayed in the exported XLS
   * file. <br>
   * <p>
   *
   * @author NVV1HC
   * @param expectedAttributeValues expected attribute value should be displayed in XLS file
   * @param fileNameAndPath file name and path
   * @param artifactID target artifact ID
   * @param attribute artifact attribute, e.g: Link:Validated By, Link:Implemented By, Artifact Type
   * @return true if expected attribute value is displayed in XLS file or vice versa
   */
  public boolean verifyAttributeValueOfArtifactDisplayedInXLSFile(final String expectedAttributeValues,
      final String fileNameAndPath, final String artifactID, final String attribute) {
    ExcelCSVReader excelReader = new ExcelCSVReader(fileNameAndPath);
    String attributeValuesFromXLSFile = "";
    try {
      attributeValuesFromXLSFile = excelReader.getAttributeValueOfArtifactFromXLSFile(attribute, artifactID);
      System.out.println("attribute values are:" + attributeValuesFromXLSFile);
    }
    catch (IOException e) {}
    System.out.println(expectedAttributeValues);

    return expectedAttributeValues.equals(attributeValuesFromXLSFile);
  }

  /**
   * <p>
   * Search module ID using ${@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open searched module by ${@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Clear all filters by using ${@link RMArtifactsPage#clearFilter()}. <br>
   * Add artifact attribute 'Validated By' by using
   * ${@link RMArtifactsPage#addArtifactAttributeInToArtifactTable(String)}.<br>
   * Then call this method to verify the status of target test case is displayed correctly under 'Validated By'
   * attribute.<br>
   * <p>
   *
   * @author NVV1HC
   * @param artifactID artifact ID
   * @param testCaseIDAndName test case ID and name
   * @param testcaseStatus status of test case, e.g: Passed or Failed
   * @return true if the test case status display correctly or vice versa
   */
  public boolean verifyTestCaseStatusUnderValidateByAttributeOfArtifact(final String artifactID,
      final String testCaseIDAndName, final String testcaseStatus) {
    String[] artifactIDAndTestCaseIDAndName = { artifactID, testCaseIDAndName };
    if (testcaseStatus.equalsIgnoreCase("passed")) {
      return this.driverCustom.isElementVisible(
          RMConstants.RMARTIFACTPAGE_TESTCASESTATUS_PASSED_UNDER_VALIDATEDBYATTRIBUTE_XPATH, this.timeInSecs,
          artifactIDAndTestCaseIDAndName);
    }
    return this.driverCustom.isElementVisible(
        RMConstants.RMARTIFACTPAGE_TESTCASESTATUS_FAILED_UNDER_VALIDATEDBYATTRIBUTE_XPATH, this.timeInSecs,
        artifactIDAndTestCaseIDAndName);
  }

  /**
   * After expand Filter as {@link RMArtifactsPage #expandFilters}<br>
   * Click on Edit Filter icon in Attribute Value of an Attribute Name need to edit value <br>
   * Edit Filter dialog will open <br>
   *
   * @author PDU6HC
   * @param attributeName the attribute name of 'Edit filter' button
   */
  public void openEditFilterDialogOfAttributeName(final String attributeName) {
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_ATTRIBUTE_VALUE_IN_FILTER_XPATH, attributeName);
    waitForSecs(5);
    // Click on Edit Filter icon
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_ATTRIBUTE_VALUE_EDIT_BUTTON_FILTER_XPATH, attributeName);
    waitForSecs(20);
  }

  /**
   * @author PDU6HC <br>
   *         Open add filter icon in filter session {@link RMArtifactsPage#openEditFilterDialogOfOneCondition(String)}
   *         <br>
   *         Click on checkbox of Attribute Value in Edit Filter dialog and click on Update button <br>
   * @param attributeValue - attribute value
   * @param attributeName - attribute name
   */
  public void addFilterForAttributeName(final String attributeName, final String attributeValue) {
    openEditFilterDialogOfAttributeName(attributeName);
    Dialog dlgEditAttributeFilter =
        this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Edit Filter"), this.timeInSecs);
    Text attributeNameTxt = this.engine
        .findFirstElementWithDuration(Criteria.isTextField().inContainer(dlgEditAttributeFilter), this.timeInSecs);
    attributeNameTxt.setText(attributeValue);
    LOGGER.LOG.info("Input new Artifact ID as " + attributeValue);
    WebElement cbxAttributeValue =
        this.driverCustom.getWebElement("//label/span[text()='DYNAMIC_VAlUE']", attributeValue);
    cbxAttributeValue.click();
    Button updateBtn = this.engine.findFirstElementWithDuration(
        Criteria.isButton().withText("Update").inContainer(dlgEditAttributeFilter), this.timeInSecs);
    updateBtn.click();
    LOGGER.LOG.info("Edit updated successfully filter with " + attributeValue);
    waitForSecs(10);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Click on module by using ${@link RMArtifactsPage#clickOnArtifact(String)}. <br>
   * Add filter by clicking on 'Add filter' button ${@link RMArtifactsPage#addFilter()} then search attribute in filter
   * using ${@link RMArtifactsPage#searchAttributeInFilter(String)} Then add list values for attribute using
   * ${@link RMArtifactsPage#addAndCloseListValueForAttributeInFilter(String, String)} Then call this method to get the
   * percentage of artifacts displayed at the bottom of page
   * <p>
   *
   * @author NVV1HC
   * @return percentage artifact displayed in the page
   */
  public String getPercentageOfArtifactDisplayed() {
    String numArtifact = this.driverCustom.getText(RMConstants.NUMBER_ARTIFACT_SHOWING_IN_TABLE_XPATH);
    String[] percentage = numArtifact.split(Pattern.quote("("));
    return percentage[1].replace(")", "");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on 'Expand filters' icon to expand the filter value under view in the artifacts. Click on 'collapse filters'
   * icon to expand the filter value under view in the artifacts.
   *
   * @author PDU6HC
   */
  public void collapseFilters() {
    waitForPageLoaded();
    Button btnExpandFilter =
        this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Collapse filters"), this.timeInSecs);
    btnExpandFilter.click();
    LOGGER.LOG.info("Clicked on 'Collapse filters' button.");
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Click on module by using ${@link RMArtifactsPage#clickOnArtifact(String)}. <br>
   * Search artifact using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Then call this method to hover on the hyperlink displayed in artifact content of searched artifact
   *
   * @author NVV1HC
   * @param artifactID artifact ID
   * @param hyperLink hyperlink of artifact is displayed in artifact content
   */
  public void hoverOnHyperLink(final String artifactID, final String hyperLink) {
    String[] artifacIDHyperLink = { artifactID, hyperLink };
    WebElement hyperLinkElm = this.driverCustom
        .getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_HYPERLINK_INARTIFACTCONTENT_XPATH, artifacIDHyperLink);
    Actions actions = new Actions(this.driverCustom.getWebDriver());
    actions.moveToElement(hyperLinkElm).build().perform();
    waitForSecs(5);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All','Modules','Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}. <br>
   * Search module using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Click on module by using ${@link RMArtifactsPage#clickOnArtifact(String)}. <br>
   * Search artifact using ${@link RMArtifactsPage#filterArtifactByTextOrId(String)}. <br>
   * Then call this method to verify the hyperlink is displayed in artifact content or not
   *
   * @author NVV1HC
   * @param artifactID artifact ID
   * @param hyperLink hyperlink of artifact is displayed in artifact content
   * @return true if the hyperlink is displayed or vice versa
   */
  public boolean verifyHyperlinkDisplayed(final String artifactID, final String hyperLink) {
    String[] artifacIDHyperLink = { artifactID, hyperLink };
    return this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_HYPERLINK_INARTIFACTCONTENT_XPATH,
        this.timeInSecs, artifacIDHyperLink);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'Modules' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Then call this method to click on 'select all items on this page.'.
   *
   * @author PDU6HC
   */
  public void clickOnSelectAllModules() {
    Label cbxSelectAll = this.engine.findFirstElementWithDuration(
        Criteria.isLabel().withToolTip("Select all items on this page."), this.timeInSecs);
    cbxSelectAll.click();
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'Modules' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Adding link type 'Traced By Architecture Element' column using
   * {@link RMArtifactsPage#changeColumnDisplaySettings(String)} Then call this method to remove module link based on
   * module id.
   *
   * @author PDU6HC
   * @param moduleId is Id of module.
   * @param linkText is text of the link.
   * @return true if status removing link is invisible.
   */
  public boolean removeLinkOnModuleWithId(final String moduleId, final String linkText) {
    waitForSecs(10);
    String[] moduleIdAndLinkText = { moduleId, linkText };
    // Click on module link icon
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_LINK_ICON_XPATH, this.timeInSecs,
        moduleIdAndLinkText);
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_LINK_ICON_XPATH, moduleIdAndLinkText);
    // Click on 'Edit Links' pencil icon
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_EDIT_LINKS_ICON_XPATH, this.timeInSecs, moduleId);
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_EDIT_LINKS_ICON_XPATH, moduleId);
    // Click on 'Remove Link' option
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_REMOVE_LINK_SUB_OPTION_XPATH, this.timeInSecs,
        moduleId);
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_REMOVE_LINK_SUB_OPTION_XPATH, moduleId);
    // Click on link to remove
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_LINK_TO_REMOVE_OPTION_XPATH, this.timeInSecs,
        linkText);
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_LINK_TO_REMOVE_OPTION_XPATH, linkText);
    // Click on 'Yes' button
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_MODIFIEDARTIAFCTCLICKYESBUTTON_XPATH,
        this.timeInSecs);
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_MODIFIEDARTIAFCTCLICKYESBUTTON_XPATH);
    return this.driverCustom.isElementInvisible(RMConstants.STATUS_MESSAGE, this.timeInSecs);
  }

  /**
   * <p>
   * Search for module id in the quick search using {@link RMDashBoardPage#quickSearch(String)}. <br>
   * Open module using {@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Select artifact using {@link RMArtifactsPage#selectArtifact(String)}. <br>
   * Then call this method to navigate to 'Audit History' of selected artifact using shortcut.
   * <p>
   *
   * @author PDU6HC
   * @param artifactId is id of artifact selected
   */
  public void navigateToAuditHistory(final String artifactId) {
    WebElement lnkArtifactId = this.driverCustom.getWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, artifactId);
    String keysPressed = Keys.chord(Keys.ALT, Keys.SHIFT, "h");
    lnkArtifactId.sendKeys(keysPressed);
  }

  /**
   * <p>
   * Open 'Artifacts' menu. <br>
   * Quick Search module ID. <br>
   * Search Artifact ID. <br>
   * Select artifact ID. <br>
   * Then call this method to hover on the Artifact ID.<br>
   *
   * @author KYY1HC
   * @param textOfLink text of Link
   */
  public void hoverOnLinksByText(final String textOfLink) {
    WebElement linkByText =
        this.driverCustom.getWebElement(RMConstants.RMARTIFACTPAGE_LINK_CONTAINS_TEXT_XPATH, textOfLink);
    Actions actions = new Actions(this.driverCustom.getWebDriver());
    actions.moveToElement(linkByText).build().perform();
    waitForSecs(5);
  }

  /**
   * <p>
   * Hover on the Link by text.<br>
   * Switch to Frame of Quick-Info.<br>
   * Check Link To by Text is displayed or not.<br>
   *
   * @author KYY1HC
   * @param textOfLink text of Link
   * @return true if Link by Text is displayed
   */
  public boolean checkLinkWithTextOnQuickInfo(final String textOfLink) {
    Link linkToElement = null;
    this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_LINK_HOVERIFRAME_QUICKINFO_XPATH);
    try {
      if (this.driverCustom.isElementVisible("//span[@id='toggleMessage' and text()='Show More']",
          Duration.ofSeconds(15))) {
        this.driverCustom.getWebElement("//span[@id='toggleMessage' and text()='Show More']").click();
        // Wait for loading disappeared
        this.driverCustom.isElementInvisible("//span[@id='loadingImage']/label[text()='Loading...']",
            Duration.ofSeconds(15));
      }
      linkToElement =
          this.engine.findFirstElementWithDuration(Criteria.isLink().withText(textOfLink), Duration.ofSeconds(15));
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getStackTrace());
      return false;
    }

    return linkToElement.getWebElement().isDisplayed();
  }


  /**
   * <p>
   * Select artifact using {@link RMArtifactsPage#selectArtifact(String)} <br>
   * Add comment to artifact using {@link RMArtifactsPage#editArtifact(Map)}. <br>
   * Then call this method to delete the newly added comment
   * <p>
   *
   * @author PDU6HC
   * @param artifactId is id of artifact selected
   * @param commentSubject is subject of the comment needed to be deleted
   */
  public void deleteComment(final String artifactId, final String commentSubject) {
    String[] commentIndentifier = { artifactId, commentSubject };
    this.driverCustom.click(RMConstants.COMMENT_ARTIFACT_XPATH, commentIndentifier);
    this.driverCustom.click(RMConstants.COMMENT_ARTIFACT_XPATH, commentIndentifier);
    waitForSecs(5);
    try {
      this.driverCustom.click(RMConstants.DELETE_COMMENT_XPATH);
    }
    catch (Exception e) {}
    waitForSecs(3);
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_MODIFIEDARTIAFCTCLICKYESBUTTON_XPATH);
    waitForSecs(15);
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Then call this method to check folder structure .<br>
   * Note: this method base on UI to verify expectation so please input the correct UI as expectation.<br>
   *
   * @author LTU7HC
   * @param additionalParams list of keys like 'rootFolderName','childLv1','childLv2', 'childLv3'
   * @return true if all expectations matches the UI otherwise will return false Note: LIST_CHILD_LV2 & LIST_CHILD_LV3
   *         in excel file should have format like this: folder1,folder2,folder3
   */
  public boolean verifyFolderHierarchy(final Map<String, String> additionalParams) {
    String root = additionalParams.get("rootFolderName");
    String childNameLv1 = additionalParams.get("childLv1");
    String[] expectedChildNameLv2 = additionalParams.get("childLv2").split(",");
    String[] expectedChildNameLv3 = additionalParams.get("childLv3").split(",");

    // Checking root
    String rootAttributeXpath =
        String.format("//span[text()='%s']/preceding-sibling::span[contains(@class,'TreeIcon')]", root);
    if (!this.driverCustom.isElementVisible(rootAttributeXpath, Duration.ofSeconds(20))) {
      LOGGER.LOG.info("Root name is not as expected");
      return false;
    }
    LOGGER.LOG.info("Root name mactches expected: " + root);

    String[] paramArr = { root, childNameLv1 };
    if (!this.driverCustom.isElementVisible(RMConstants.FOLDER_HIERARCHY_CHILD_LEVEL1_XPATH, Duration.ofSeconds(20),
        paramArr)) {
      LOGGER.LOG.info("Child name level 1 is not expected");
      return false;
    }
    LOGGER.LOG.info("Child name level 1 as expected: " + childNameLv1);

    // Checking if child name lv1 has expand button
    if (this.driverCustom.isElementVisible(RMConstants.FOLDER_HIERARCHY_EXPAND_BUTTON_XPATH, Duration.ofSeconds(20),
        childNameLv1)) {
      this.driverCustom.click(RMConstants.FOLDER_HIERARCHY_EXPAND_BUTTON_XPATH, childNameLv1);
    }

    List<String> listChildsLv2OfChildLv1 = getListChildOfGivenFolder(childNameLv1);
    if (!checkAndPrintListChildOfParentNode(listChildsLv2OfChildLv1, expectedChildNameLv2, childNameLv1, 2)) {
      LOGGER.LOG.info("Childs name level 2 is not expected");
      return false;
    }
    LOGGER.LOG.info("Childs name level 2 as expected: " + Arrays.toString(expectedChildNameLv2));

    // Checking if child name lv2 has expand button
    List<String> listChildsLv3OfChildLv2 = new ArrayList<>();
    for (String childLv2 : listChildsLv2OfChildLv1) {

      if (this.driverCustom.isElementVisible(RMConstants.FOLDER_HIERARCHY_EXPAND_BUTTON_XPATH, Duration.ofSeconds(20),
          childLv2)) {
        this.driverCustom.click(RMConstants.FOLDER_HIERARCHY_EXPAND_BUTTON_XPATH, childLv2);

        listChildsLv3OfChildLv2 = getListChildOfGivenFolder(childLv2);
        if (!checkAndPrintListChildOfParentNode(listChildsLv3OfChildLv2, expectedChildNameLv3, childLv2, 3)) {
          LOGGER.LOG.info("Childs name level 3 is not expected");
          return false;
        }
      }
    }
    LOGGER.LOG.info("Childs name level 3 as expected: " + Arrays.toString(expectedChildNameLv3));
    return true;
  }

  private List<String> getListChildOfGivenFolder(final String folderName) {
    String actualChildName = "";
    // List child of given folder
    this.driverCustom.isElementVisible(RMConstants.FOLDER_HIERARCHY_FOLDER_XPATH, Duration.ofSeconds(20), folderName);
    List<WebElement> lstChilds = null;
    try {
      lstChilds = this.driverCustom.getWebElements(RMConstants.FOLDER_HIERARCHY_FOLDER_XPATH, folderName);
    }
    catch (Exception e) {
      // in case of xpath is not find then list will be null
    }

    List<String> listChildsName = new ArrayList<>();
    if (lstChilds != null) {
      // Checking child name
      for (WebElement lstChild : lstChilds) {
        // We will get elements which are visible
        boolean isDisplayed = lstChild.findElement(By.xpath(".//span[@role='treeitem']")).isDisplayed();
        if (isDisplayed) {
          actualChildName = lstChild.findElement(By.xpath(".//span[@role='treeitem']")).getText().trim();
          // Get list child name
          listChildsName.add(actualChildName);
        }
      }
    }
    return listChildsName;
  }

  private boolean checkAndPrintListChildOfParentNode(final List<String> actualListChild,
      final String[] expectedChildsName, final String parent, final int level) {
    if (actualListChild.size() != 0) {
      LOGGER.LOG.info(String.format("'%s' has %s child(s)", parent, actualListChild.size()));
      for (String child : actualListChild) {
        LOGGER.LOG.info(String.format("'%s' - level '%s' is child of '%s'", child, level, parent));
        boolean check = Arrays.stream(expectedChildsName).anyMatch(s -> s.equals(child));
        if (!check) {
          LOGGER.LOG.info(String.format("'%s' - level '%s' is not expectation in [%s]'", child, level,
              expectedChildsName.toString()));
          return false;
        }
      }
    }
    return true;
  }

  /**
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}. <br>
   * Click on create button present top left of Artifacts page using
   * {@link RMArtifactsPage#clickOnCreateButton(String)}. <br>
   * 'Create Artifact' pop up window will display. <br>
   * Provide necessary details like Artifact Name,Artifact Type,Artifact Format etc.. <br>
   * Click on 'Open Artifact' checkbpx if it's not enabled.<br>
   * Click on 'OK' button present inside 'Create Artifact' window.
   *
   * @param additionalParams list of keys like 'ARTIFACT_NAME','ARTIFACT_TYPE','ARTIFACT_FORMAT'...
   */
  public void createAndOpenAnArtifact(final Map<String, String> additionalParams) {
    Dialog createArtifactDialog =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Create Artifact"), Duration.ofSeconds(5))
            .getFirstElement();
    try {
      Dropdown drdArtifacFormat = this.engine.findFirstElementWithDuration(
          Criteria.isDropdown().withLabel("Artifact format:").inContainer(createArtifactDialog), Duration.ofSeconds(5));
      drdArtifacFormat.selectOptionWithText(additionalParams.get("ARTIFACT_FORMAT"));
      LOGGER.LOG.info("Select Artifact Format as - " + additionalParams.get("ARTIFACT_FORMAT"));
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    Text txtInitalContent = this.engine.findElementWithDuration(
        Criteria.isTextField().hasLabel(RMConstants.INITIAL_CONTENT).isMultiLine().inContainer(createArtifactDialog),
        this.timeInSecs).getElementByIndex(1);
    txtInitalContent.setText(additionalParams.get("ARTIFACT_NAME"));
    LOGGER.LOG.info("Enter the Intial Content of the Artifact - " + additionalParams.get("ARTIFACT_NAME"));
    Dropdown drdArtifactType = this.engine.findFirstElementWithDuration(
        Criteria.isDropdown().withLabel("Artifact type:").inContainer(createArtifactDialog), Duration.ofSeconds(5));
    drdArtifactType.selectOptionWithText(additionalParams.get("ARTIFACT_TYPE"));
    LOGGER.LOG.info("Select Artifact Type as - " + additionalParams.get("ARTIFACT_TYPE"));
    if (!this.driverCustom.isElementVisible(
        "//div[@class='open-artifact-option ']//input[@aria-checked='" + additionalParams.get("OPEN_ARTIFACT") + "']",
        Duration.ofSeconds(5))) {
      this.driverCustom.getWebElement("//div[@class='open-artifact-option ']//input").click();
      LOGGER.LOG.info("Clicked on 'Open artifact' check box with value - " + additionalParams.get("OPEN_ARTIFACT"));
    }
    LOGGER.LOG.info("'Open artifact' check box is enabled.");
    Button btnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK").inContainer(createArtifactDialog),
            Duration.ofSeconds(5)).getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("Clicked on 'OK' button.");
  }

  /**
   * <p>
   * Create and open an Artifact using {@link #createAndOpenAnArtifact(Map)},created Artifact page will display. <br>
   * Click on 'To Artifacts' icon present left side of the page.<br>
   * It will navigate to Artifacts page.
   */
  public void navigateToArtifactsPage() {
    /*
     * Button btnRefresh = this.engine.findElement(Criteria.isButton().withToolTip("Refresh")).getFirstElement();
     * btnRefresh.click();
     */
    this.driverCustom.click("//div[@class='client-navigation-icon-back']");
    LOGGER.LOG.info("Clicked on 'To Artifacts' image to navigate Artifacts page.");
  }

  /**
   * <p>
   * {@link #createAndOpenAnArtifact(Map)} creates artifact with providing unique name to the artifact. <br>
   * Returns name of the created artifact.
   *
   * @return name of the artifact.
   */
  public String getArtifactName() {
    waitForPageLoaded();
    this.driverCustom.isElementVisible("//span[@class='resource-title']", Duration.ofSeconds(10));
    LOGGER.LOG.info("Get the newly created Artifact Name.");
    return this.driverCustom.getText("//span[@class='resource-title']");
  }

  /**
   * @param artifactID
   * @return
   */
  public String getArtifactContent(final String artifactID) {
    waitForPageLoaded();
    WebElement contentArea = this.driverCustom.getWebElement(RMConstants.RMMODULEPAGE_ARTIFACTCONTENT_AREA, artifactID);

    LOGGER.LOG.info("Newly Content of Artifact id '" + artifactID + "' is : " +
        this.driverCustom.getText(RMConstants.RMMODULEPAGE_ARTIFACTCONTENT_AREA, artifactID).toString());
    return this.driverCustom.getText(RMConstants.RMMODULEPAGE_ARTIFACTCONTENT_AREA, artifactID);
  }


  /**
   * <p>
   * Hover an Artifact or Module using {@link #hoverOnLinksByText(String)}, quick info pop up will display. <br>
   * Artifacts , Modules , Collections has some attributes like Format, Type etc.. Get the details of the Artifacts from
   * Quick Info pop up.
   *
   * @return value of the artifact attribute.
   */
  public String getArtifactQuickInfo() {
    waitForSecs(5);
    this.driverCustom.switchToFrame("//iframe[@dojoattachpoint='hoverIframe']");
    List<String> attrList = this.driverCustom.getWebElementsText(
        "//div[@class='groupSection']/div[text()='Attributes']//following-sibling::table//tr[1]//td[@class='label']");
    LOGGER.LOG.info(attrList + " - attributes displayed in 'Quick Info' section of the Artifacts.");
    List<String> attrListValue = this.driverCustom.getWebElementsText(
        "//div[@class='groupSection']/div[text()='Attributes']//following-sibling::table//tr[1]//td[@class='labelValue']");
    LOGGER.LOG.info(attrListValue + " - attribute values displayed in 'Quick Info' section of the Artifacts.");
    for (int i = 0; i < attrList.size(); i++) {
      String strAttr = attrList.get(i).replace(":", "");
      attrList.set(i, strAttr);

    }
    String str = "";
    if (attrList.size() == attrListValue.size()) {
      for (int j = 0; j < attrList.size(); j++) {
        str = str + "\n" + "KEY :" + attrList.get(j) + " VALUE :" + attrListValue.get(j);
      }
    }
    return str;
  }

  /**
   * *
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}. <br>
   * Select the required folder from the left side. <br>
   * Click on the drop down menu displayed next to the folder name. <br>
   * Click on 'Create Artifact' menu option. <br>
   * Select artifact type as sub menu option.
   *
   * @param additionalParams represets key value pair.
   */
  public void selectOptionFromFolder(final Map<String, String> additionalParams) {
    WebElement treeProjectFolderDropdown = this.driverCustom.getPresenceOfWebElement(
        "//div[contains(@class,'dijitContentPane dijitContentPaneSingleChild') and not(contains(@style,'display: none'))]" +
            "//span[@title='" + additionalParams.get("FOLDER_NAME") + "']/../..//div");
    this.driverCustom.getClickableWebElement(treeProjectFolderDropdown).click();
    LOGGER.LOG
        .info("Clicked on drop down present next to the '" + additionalParams.get("FOLDER_NAME") + "' folder name.");
    waitForSecs(2);
    this.driverCustom.getWebElement(
        "//div[@class='dijitPopup dijitMenuPopup' and not(contains(@style,'display: none'))]//td[text()='" +
            additionalParams.get("MENU_OPTION") + "']")
        .click();
    LOGGER.LOG.info("Clicked on '" + additionalParams.get("MENU_OPTION") + "' option.");
    waitForSecs(2);
    String subMenuOptionXpath = "//table[contains(@class,'MenuActive')]//td[contains(text(),'%s')][last()]";
    this.driverCustom.isElementVisible(subMenuOptionXpath, Duration.ofSeconds(5));
    String subMenuOption = String.format(subMenuOptionXpath, additionalParams.get("SUB_MENU_OPTION"));
    this.driverCustom.getPresenceOfWebElement(subMenuOption).click();
    LOGGER.LOG.info("Clicked on '" + additionalParams.get("SUB_MENU_OPTION") + "' option.");
    waitForSecs(2);
  }

  /**
   * <p>
   * Get the folder name when hovering the id or name of the artifact.
   *
   * @return folder name.
   */
  public String getLocationOfAnArtifact() {
    this.driverCustom.switchToFrame("//iframe[@dojoattachpoint='hoverIframe']");
    LOGGER.LOG.info("Location of the Artifact inside the 'Artifacts' page.");
    List<WebElement> summaryList = this.driverCustom.getWebElements("//div[@class='groupSection']//span");
    Optional<WebElement> matchingOptional =
        summaryList.stream().filter(x -> x.getAttribute("class").contains("labelValue")).findFirst();
    if (matchingOptional.isPresent()) {
      LOGGER.LOG.info(matchingOptional.get().getText() + " is displayed in 'Quick Info' section of the artifact.");
      return matchingOptional.get().getText();
    }
    throw new WebAutomationException("Location of the Artifact not found.");
  }

  /**
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}.<br>
   * Select any artifact and open context menu using ${@link RMArtifactsPage#selectArtifactAndOpenContextMenu(String)}.
   * <br>
   * Click on 'Create Artifact' option using ${@link RMModulePage#clickOnDropdownOption(String)}. <br>
   * Insert Artifact Name for the selected artifact type.
   *
   * @param artifactName name of the new artifact.
   */
  public void insertArtifactNameInArtifactsPage(final String artifactName) {
    if (this.driverCustom.isElementVisible("//textarea[@id='dijit_form_Textarea_0' and @value='']", this.timeInSecs)) {
      WebElement txtArtifactName =
          this.driverCustom.getWebElement("//textarea[@id='dijit_form_Textarea_0' and @value='']");
      txtArtifactName.sendKeys(artifactName);
      LOGGER.LOG.info("Enter the artifact name  - " + artifactName);
      txtArtifactName.sendKeys(Keys.TAB);
      waitForSecs(2);
    }
  }

  /**
   * @author VUP5HC
   *         <p>
   *         Count number of current displayed artifacts on current page. Will navigate to every page to count
   * @return current displayed artifacts
   */
  public String countNumberOfArtifactDisplayed() {
    String artifactRowXpath = "//div[contains(@style,'display: block')]//td[@colid='_menu']";
    int totalArtifacts = 0;
    try {
      waitForLoadingMessage();
      WebElement lblNext =
          this.driverCustom.getWebElement("//div[contains(@style,'display: block')]//a[@dojoattachpoint='next']");
      LOGGER.LOG.info("Counting number of artifacts displayed.");
      do {
        // Count artifacts number
        totalArtifacts += this.driverCustom.getWebElements(artifactRowXpath).size();
        lblNext.click();
        waitForLoadingMessage();
      }
      while (this.driverCustom.isElementInvisible(
          "//div[contains(@style,'display: block')]//a[@dojoattachpoint='next' and @class='disable']",
          Duration.ofSeconds(1)));
      totalArtifacts += this.driverCustom.getWebElements(artifactRowXpath).size();
    }
    // Case only one artifact page
    catch (Exception e) {
      LOGGER.LOG.info("Only one page of artifact needed to be counted");
      totalArtifacts = this.driverCustom.getWebElements(artifactRowXpath).size();
    }
    LOGGER.LOG.info(String.format("Counted total %s artifacts displayed.", totalArtifacts));
    return String.valueOf(totalArtifacts);
  }

  /**
   * @author VUP5HC
   *         <p>
   *         Get current displayed number of artifacts in page. Eg: Showing 25 of 100 Artifacts, return 25.
   * @return current displayed artifact number
   */
  public String getCurrentDisplayedArtifactsNumber() {
    return this.driverCustom.getText(RMConstants.NUMBER_ARTIFACT_SHOWING_IN_TABLE_XPATH).split(" ")[1].trim();
  }

  /**
   * @author VUP5HC
   *         <p>
   *         Get total number of artifacts. Eg: Showing 25 of 100 Artifacts, return 100.
   * @return all artifact number
   */
  public String getAllArtifactsNumber() {
    return this.driverCustom.getText(RMConstants.NUMBER_ARTIFACT_SHOWING_IN_TABLE_XPATH).split(" ")[3].trim();
  }

  /**
   * <p>
   * After creating a new change set using {@link RMDashBoardPage#createConfiguration(String, String)} then select
   * target folder and choose "Clone From a Component" option * @param additionalParams is MAP, included Folder name
   * want to store artifacts clone to an other component, target project area, target component, dropdown option of type
   * of configuration, target local stream, artifact type and artifact ID to be cloned. With elements such as (<ID>,
   * <value>): <br>
   * <p>
   * @author GLN4HC
   *
   * @author GLN4HC
   * @param params store key values for: ("PROJECT_AREA", <PROJECT_AREA_VALUE>) <br>
   *          ("COMPONENT_NAME", <COMPONENT_NAME_VALUE>) <br>
   *          ("STREAMS_DROPDOWN_OPTION", <STREAMS_DROPDOWN_OPTION_VALUE>) <br>
   *          <STREAM_NAME>,<STREAM_NAME_VALUE> <br>
   *          For not select any item, we put "not select" to value variable (Ex: not select DEVELOPMENT_ITEM
   *          ("DEVELOPMENT_ITEM", "not select") ) ("SELECT_ARTIFACT_TYPE", <ARTIFACT_TYPE>) <br>
   *          ("ARTIFACT_ID", <ARTIFACT_ID>) <br>
   *  
   */
  public void cloneArtifactFromAComponent(final Map<String, String> params) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_CLONE_FROM_A_COMPONENT_OPTION).click();

    Button btnSelectCompConfig = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Select Component Configuration..."), this.timeInSecs)
        .getFirstElement();
    btnSelectCompConfig.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_PROJECTAREA_DROPDOWN_XPATH).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.getPresenceOfWebElement(RMConstants.CLONE_FROM_A_COMPONENT_DIALOG_PROJECTAREA_OPTION,
        params.get("PROJECT_AREA")).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(8));

    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_COMPONENT_DROPDOWN_XPATH).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, params.get("COMPONENT_NAME"))
        .click();

    Button btnStream =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Streams"), this.timeInSecs).getFirstElement();
    btnStream.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH,
        params.get("STREAMS_DROPDOWN_OPTION")).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));

    Dialog dlgSelectCompConfig = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a Component Configuration"), this.timeInSecs)
        .getFirstElement();
    Text search = this.engine.findElementWithDuration(
        Criteria.isTextField().withPlaceHolder("Type to search (enter * to show all)").inContainer(dlgSelectCompConfig),
        this.timeInSecs).getFirstElement();
    search.setText(params.get("STREAM_NAME"));
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));

    this.driverCustom.getPresenceOfWebElement(RMConstants.CLONE_FROM_A_COMPONENT_DIALOG_COMPONENTNAME_OPTION,
        params.get("STREAM_NAME")).click();

    Button btnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOK.click();
    Button btnNext =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >"), this.timeInSecs).getFirstElement();
    btnNext.click();

    Button btnAddArtifact = this.engine
        .findElementWithDuration(Criteria.isButton().withText(params.get("SELECT_ARTIFACT_TYPE")), this.timeInSecs)
        .getFirstElement();
    btnAddArtifact.click();

    Dialog dlgAlltoModule = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select Artifacts"), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info("Displayed 'Insert Artifact' dialog.");
    Text searchArtifactTextbox = this.engine.findElementWithDuration(Criteria.isTextField()
        .withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID).inContainer(dlgAlltoModule),
        this.timeInSecs).getFirstElement();
    searchArtifactTextbox.setText(params.get("ARTIFACT_ID") + Keys.ENTER);
    LOGGER.LOG
        .info("Input " + params.get("ARTIFACT_ID") + " in 'Type to filter artifacts by text or by ID' text field");
    waitForSecs(3);

    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.ARTIFACTRESULT_AFTERSEARCHINGTOADDORINSERT_XPATH,
          params.get("ARTIFACT_ID")).click();
      waitForSecs(3);
      LOGGER.LOG.info("Select " + params.get("ARTIFACT_ID") + " artifact");
    }
    catch (Exception e) {
      throw new InvalidArgumentException(params.get("ARTIFACT_ID") + " - doesn't found.");
    }

    Button btnOK1 = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK")/* .inContainer(dlgAlltoModule) */, this.timeInSecs)
        .getFirstElement();
    btnOK1.click();
    this.driverCustom.waitForPageLoaded();
    Button btnNext1 =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >"), this.timeInSecs).getFirstElement();
    btnNext1.click();

    Button btnFinish =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Finish"), this.timeInSecs).getFirstElement();
    btnFinish.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
  }

  /**
   * <p>
   * Click on the folder and select the menu button "Clone from a Component"
   *
   * @param folderName refer to name of the folder
   */
  public void clickOnCloneFromAComponentBtn(final String folderName) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_FOLDER_XPATH, folderName).click();
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_FOLDER_TOOLTIP_XPATH, folderName).click();
    WebElement elm = this.driverCustom
        .getWebElement("//div[not(contains(@style,'display: none'))]//td[contains(text(),'Clone From a Component')]");
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.moveToElement(elm).build().perform();
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Open an existing artifact, click on Edit button In the right side tab of the webpage under "Overview" select the
   * "State" field and change the state to "Start Working"
   *
   * @author NCY3HC
   * @param button name inside artifact content page (like: Cancel, Save, Done)
   * @return true if button is enabled.
   */
  public boolean isButtonEnabled(final String button) {
    // waitForPageLoaded();
    List<WebElement> elements = this.driverCustom.getWebElements("//button[text() = 'DYNAMIC_VAlUE']", button);
    System.out.println(elements.get(elements.size() - 1).getAttribute("disabled"));
    boolean isClicked =
        !(elements.isEmpty()) ? elements.get(elements.size() - 1).getAttribute("disabled") != null : false;
    if (isClicked) {
      LOGGER.LOG.info(button + " button not enabled");
    }
    else {
      LOGGER.LOG.info(button + " button enabled");
    }
    return isClicked;
  }

  /**
   * <p>
   * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'All' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the artifact in 'Type to filter artifacts by text or by ID' text box in Artifacts page using
   * {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.<br>
   * Open the searched artifact using {@link RMArtifactsPage#openRMSpecification(String)}.<br>
   * Click on 'Edit' button present right side of the Artifact page.<br>
   * Edit the artifact attribute present in 'Overview' section of the artifact.<br>
   * Click on 'Done' or Save button.
   *
   * @param additionalParams stores key values for artifact attributes like 'STATE','TYPE' etc...
   */
  public void editRMAttributesUnderOverviewSection(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Button btnEdit = this.engine.findElementWithDuration(Criteria.isButton().withText("Edit"), Duration.ofSeconds(60))
        .getFirstElement();
    btnEdit.click();

    String attributeType = additionalParams.get("ATTRIBUTE_TYPE");
    switch (attributeType) {
      case "Combo box":
//        this.driverCustom.switchToDefaultContent();
//        Label lblBox = this.engine.findElementWithDuration(Criteria.isLabel().withText("Project:"), Duration.ofSeconds(60))
//        .getFirstElement();
//        lblBox.click();

//        Actions action = new Actions(this.driverCustom.getWebDriver());
//        action
//            .moveToElement(this.driverCustom.getPresenceOfWebElement("//div[contains(@id,'OverviewSection')]//descendant::label[text()='State (Default):']"))
//            .build().perform();
        WebElement lblDropdown =
            this.driverCustom.getPresenceOfWebElement("//label[contains(text(),'DYNAMIC_VAlUE')]", "State (Default):");
        this.waitForSecs(5);
        this.driverCustom.scrollIntoCenterOfView(lblDropdown);
//        Label lblBox = this.engine.findElementWithDuration(Criteria.isLabel().withText(additionalParams.get("ATTRIBUTE_LABEL")), Duration.ofSeconds(60))
//            .getFirstElement();
//        lblBox.scrollToElement();
//        JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
//        js.executeScript("window.scrollBy(0,300)");
        Dropdown drdType = this.engine.findElementWithDuration(
            Criteria.isDropdown().withLabel(additionalParams.get("ATTRIBUTE_LABEL").toString() + ":"),
            Duration.ofSeconds(60)).getFirstElement();
        drdType.click();
        drdType.selectOptionWithText("test");

        WebElement drdName = this.driverCustom.getPresenceOfWebElement(
            "//div[contains(@id,'OverviewSection')]//descendant::label[text()='State (Default):']//following-sibling::div//input[@role='button presentation']");
        drdName.click();


        break;

    }
    Button btnDone =
        this.engine.findElementWithDuration(Criteria.isButton().withText(additionalParams.get("SAVE_OR_DONE_BUTTON")),
            Duration.ofSeconds(60)).getFirstElement();
    btnDone.click();
  }
  

  /**
   * @param upload file to import
   */
  public void uploadFile(final String importType, final String filePath) {
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible("//a[@class='selected' and @title='Import']", Duration.ofSeconds(10))) {
      Dialog dlgImport = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Import"), this.timeInSecs)
          .getFirstElement();
      RadioButton rdoImport = this.engine.findElementWithDuration(
          Criteria.isRadioButton().withText(importType).inContainer(dlgImport),
          this.timeInSecs).getFirstElement();
      rdoImport.click();
      // Click on Next button
      Button btnNext = this.engine
          .findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), this.timeInSecs).getFirstElement();
      btnNext.click();
    }
    @SuppressWarnings("serial")
    Map<String, String> params = new HashMap<String, String>() {
      {
        put(RMConstants.IMPORTTYPE, importType);
        put(RMConstants.FILETYPE, filePath);
      }
    };
    performUpload(params);
  }

/**
   * @return get artifact types from imported file
   */
  public List<String> getArtifactTypesFromImportedFile() {
    // Click upload label
    Button upldBtn =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Upload"), timeInSecs).getFirstElement();
    upldBtn.click();
    Button nextBtn = this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), timeInSecs)
        .getFirstElement();
    nextBtn.waitUntilElementEnabled(3);
    nextBtn.click();
    nextBtn = this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), timeInSecs)
        .getFirstElement();
    nextBtn.click();

    // get values
    String xpath = "//span[@data-dojo-attach-point='titleNode']";
    List<String> artifactTypes = this.driverCustom.getWebElementsText(xpath);
    return artifactTypes;
  }
  
  /**
   * @return get artifact attribute from imported file
   */
  public List<String> getArtifactAttributesFromImportedFile() {
    // get values
    String artifactTypes = "//span[@data-dojo-attach-point='arrowNode']";
    List<WebElement> artifactTypeEles = this.driverCustom.getWebElements(artifactTypes);
    String artifactAttr = "//label[@class='checkLabel']";
    for (int i = 0; i < artifactTypeEles.size(); i++) {
      artifactTypeEles.get(i).click();
    }
    List<String> artifactAttributes = this.driverCustom.getWebElementsText(artifactAttr);
    List<String> results = new ArrayList<String>();
    for (int i = 0; i < artifactAttributes.size(); i++) {
      String artifactAttribute = artifactAttributes.get(i);
      if(artifactAttribute.contains(" (NEW)")){
        artifactAttribute = artifactAttribute.replace(" (NEW)", "");
       
      }
      if(!results.contains(artifactAttribute)) {
        results.add(artifactAttribute);
      }
    }
    //
    Button allAttributeBtn = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Select All Attributes"), timeInSecs).getFirstElement();
    allAttributeBtn.click();
    return results;
  }

  /**
   * @return finish import file
   */
  public String finishImportReqlF() {
    Button nextBtn;
    try {
      // V7.0 this step will click "Finish"
      nextBtn = this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.FINISHBUTTON), timeInSecs)
          .getFirstElement();
      nextBtn.click();
    }
    catch (Exception e) {
      nextBtn = this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.NEXT), timeInSecs)
          .getFirstElement();
      nextBtn.click();
    }
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_TEXTDOCUMENTUPLOADED_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() * 10)));
    this.driverCustom.isElementPresent(RMConstants.JAZZPAGE_LINK_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() * 10)), RMConstants.SHOWREPORT);
    WebElement msg =
        this.driverCustom.getPresenceOfWebElement(By.xpath(RMConstants.RMARTIFACTSPAGE_REQIFSUCCESSMSG_XPATH));
    String msgText = msg.getText();
    Button closeBtn = this.engine.findElementWithDuration(Criteria.isButton().withText(RMConstants.CLOSE), timeInSecs)
        .getFirstElement();
    closeBtn.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(3));
    return msgText;
  }  
}


