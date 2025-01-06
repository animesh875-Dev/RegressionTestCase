/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.Keys;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactTypes;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
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
 * Represents the Requirement Management Collections Page this is common for Collections.
 */
public class RMCollectionsPage extends AbstractRMPage {

  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom required for interacting with the browser.
   */
  public RMCollectionsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTreeNodeFinder(), new JazzDropdownFinder(), new JazzButtonFinder(),
        new JazzTextFinder(), new JazzSpanLabelFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new JazzDialogFinder(), new RowCellFinder(), new LinkFinder(), new JazzSpanTextFieldFinder(), new TextFinder(),
        new JazzTabFinder(), new ListboxFinder(), new CheckboxFinder(), new ButtonFinder(),
        new JazzRadioButtonFinder());
  }

  /**
   * <p>
   * Open Artifacts menu using {@link RMDashBoardPage#openMenu(String)}.<br>
   * Search module id in 'Type to filter artifacts by text or by ID' text box.<br>
   * Select the drop down menu contains in the searched module id.<br>
   * Click on 'Add Artifact to a Collection...' option.
   * Input  data in 'Add artifact to a Collection' dialog.
   *
   * @param collectionID collection id.
   */
  public void addArtifactToCollection(final String collectionID) {
    waitForPageLoaded();
    Dialog dlgAddArtifactToCollections =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Add Artifact to a Collection"),timeInSecs).getFirstElement();
    Text txtSearchVerify = this.engine.findFirstElement(
        Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID));
    txtSearchVerify.setText(collectionID + Keys.ENTER);
    waitForPageLoaded();
    Link lnkCollectionID = this.engine.findElementWithDuration(Criteria.isLink().withText(collectionID).inContainer(dlgAddArtifactToCollections), timeInSecs).getFirstElement();
    lnkCollectionID.click();
    waitForPageLoaded();
  }

  /**
   * <p>
   * Get the Created Collection ID
   *
   * @return returns the Main Artifact, Module, Collection Id which is available inside the Artifact, Module,
   *         Collection.
   */
  public String getCollectionID() {
    waitForPageLoaded();
    return this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_GETCOLLECTIONSTEST_XPATH);
  }

  /**
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}. <br>
   * Click on 'Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Click on create button present top left of Artifacts page using
   * {@link RMArtifactsPage#clickOnCreateButton(String)},'Create Artifact' dialog is displayed.<br>
   * Provide collection name and click on 'OK' button.
   * <p>
   * NOTE:- Currently this method supporting only for collection name.
   *
   * @param additionalParams stores key value for 'COLLECTION_NAME'.
   */
  public void createCollection(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Dialog dlgCreateArtifact =
        this.engine.findElement(Criteria.isDialog().withTitle("Create Artifact")).getFirstElement();
    Text txtName = this.engine.findElement(Criteria.isTextField().hasLabel("Name:").inContainer(dlgCreateArtifact))
        .getElementByIndex(2);
    txtName.setText(additionalParams.get("COLLECTION_NAME"));
    if(!this.driverCustom.isElementVisible("//div[@class='open-artifact-option ']//input[@aria-checked='"+additionalParams.get("OPEN_ARTIFACT")+"']",  Duration.ofSeconds(5))) {
      this.driverCustom.getWebElement("//div[@class='open-artifact-option ']//input").click();
      LOGGER.LOG.info("Clicked on 'Open artifact' check box with value - " +additionalParams.get("OPEN_ARTIFACT"));
     }
    Button btnOK =
        this.engine.findElement(Criteria.isButton().withText("OK").inContainer(dlgCreateArtifact)).getFirstElement();
    btnOK.click();
  }

  /**
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}. <br>
   * Click on 'All' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the existing collection ID in 'Type to filter artifacts by text or by ID' text box.<br>
   * Click on the searched collection, selected collection page is displayed.<br>
   * Click on 'Add' button present left side of the page, list of options displayed.<br>
   * Click on 'Other...' from the displayed options,'Create Artifact' dialog is displayed.<br>
   * Provide necessary details like Artifact Name,Artifact Type,Artifact Format etc.. <br>
   * Click on 'OK' button present inside 'Create Artifact' window.<br>
   * Click on 'Done' button.
   *
   * @param additionalParams stores key value for 'COLLECTION_ID','ARTIFACT_NAME','ARTIFACT_TYPE','ARTIFACT_FORMAT'
   *          etc..
   */
  public void editCollections(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Link lnkArtifact = this.engine.findFirstElement(Criteria.isLink().withText(Menu.ARTIFACTS.toString()));
    lnkArtifact.click();
    Link lnkAll = this.engine.findFirstElement(Criteria.isLink().withText(ArtifactTypes.ALL.toString()));
    lnkAll.click();
    waitForPageLoaded();
    Text txtSearch = this.engine
        .findFirstElement(Criteria.isTextField().withPlaceHolder("Type to filter artifacts by text or by ID"));
    txtSearch.setText(additionalParams.get(RMConstants.COLLECTIONID) + Keys.ENTER);
    waitForPageLoaded();
    Row rowModule =
        this.engine.findElement(Criteria.isRow().containsText(additionalParams.get("COLLECTION_ID"))).getFirstElement();
    Link linkid = this.engine
        .findFirstElement(Criteria.isLink().withText(additionalParams.get("COLLECTION_ID")).inContainer(rowModule));
    linkid.click();
    Button btnAdd = this.engine.findElement(Criteria.isButton().withText("Add")).getFirstElement();
    btnAdd.click();
    Cell cell = this.engine.findElement(Criteria.isCell().withText("Other...")).getFirstElement();
    cell.click();
    Dialog dlgCreateArtifact =
        this.engine.findElement(Criteria.isDialog().withTitle("Create Artifact")).getFirstElement();
    Text txtName = this.engine.findElement(Criteria.isTextField().hasLabel("Name:").inContainer(dlgCreateArtifact))
        .getElementByIndex(2);
    txtName.setText(additionalParams.get("ARTIFACT_NAME"));
    Dropdown drdArtifactType =
        this.engine.findFirstElement(Criteria.isDropdown().withLabel("Artifact type:").inContainer(dlgCreateArtifact));
    drdArtifactType.selectOptionWithText(additionalParams.get("ARTIFACT_TYPE"));
    Dropdown drdArtifacFormat = this.engine
        .findFirstElement(Criteria.isDropdown().withLabel("Artifact format:").inContainer(dlgCreateArtifact));
    drdArtifacFormat.selectOptionWithText(additionalParams.get("ARTIFACT_FORMAT"));
    Button buttontnOK =
        this.engine.findElement(Criteria.isButton().withText("OK").inContainer(dlgCreateArtifact)).getFirstElement();
    buttontnOK.click();
    Button btnDone = this.engine.findElement(Criteria.isButton().withText("Done")).getFirstElement();
    btnDone.click();
  }

  /**
   * *
   * <p>
   * Open menu ${@link RMDashBoardPage#openMenu(String)} using {@link Menu#ARTIFACTS}. <br>
   * Click on 'Collections' using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
   * Search the Collection in 'Type to filter artifacts by text or by ID' text box.<br>
   * Select the searched collection row, click on the menu launcher,list of menus displayed.<br>
   * Click on 'Delete Artifact...' menu.<br>
   * Click on 'Delete Artifact' button.
   *
   * @param collectionID use to take collection id
   */
  public void deleteCollections(final String collectionID) {
    waitForPageLoaded();
    Link lnkArtifacts = this.engine.findFirstElement(Criteria.isLink().withText(Menu.ARTIFACTS.toString()));
    lnkArtifacts.click();
    Link lnkAllArtifacts = this.engine.findFirstElement(Criteria.isLink().withText(ArtifactTypes.ALL.toString()));
    lnkAllArtifacts.click();
    Text txtSearchVerify = this.engine
        .findFirstElement(Criteria.isTextField().withPlaceHolder("Type to filter artifacts by text or by ID"));
    txtSearchVerify.setText(collectionID + Keys.ENTER);
    Row rowModule = this.engine.findElement(Criteria.isRow().containsText(collectionID)).getFirstElement();
    Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().inContainer(rowModule));
    drdMenu.selectOptionWithText("Delete Artifact...");
    Button btnDeleteArtifact =
        this.engine.findElement(Criteria.isButton().withText("Delete Artifact")).getFirstElement();
    btnDeleteArtifact.click();
  }
  /**
   * <p>
   * Open artifacts page using {@link RMDashBoardPage #openMenu(String)}. <br>
   * Click on 'Create' drop down button visible at the top left of the page. <br>
   * Select appropriate options like "Import Artifact...","Upload Artifact...","Collection" or any other artifact type.
   *
   * @param option selects option from create drop down.
   */
  public void selectOptionFromCreateCollectionDropdown(final String option) {
    waitForPageLoaded();
    Button btn=this.engine.findElement(Criteria.isButton().withToolTip("Create Collection")).getFirstElement();
    btn.click();
    LOGGER.LOG.info("Clicked on 'Create Collection' button.");
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_SELECTREQUIREMENT_XPATH, Duration.ofSeconds(30), option);
    this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_SELECTREQUIREMENT_XPATH, option);
    LOGGER.LOG.info("Clicked on option - "+option);
  }
  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Artifacts");
  }

}