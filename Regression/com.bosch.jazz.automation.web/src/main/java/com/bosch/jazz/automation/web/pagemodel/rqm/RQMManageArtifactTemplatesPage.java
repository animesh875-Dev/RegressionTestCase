/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.ColumnFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LabelFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.JazzTextEditorFinder;
import finder.text.label.JazzLabelFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * This Page contains RQM Manage Artifact template Page related data.
 */
public class RQMManageArtifactTemplatesPage extends AbstractRQMPage {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMManageArtifactTemplatesPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDialogFinder(), new JazzTextFieldFinder(), new JazzTextEditorFinder(),
        new JazzDropdownFinder(), new DropdownFinder(), new JazzRowFinder(this.driverCustom.getWebDriver()),
        new RowCellFinder(), new ColumnFinder(), new LinkFinder(), new JazzRadioButtonFinder(), new TextFieldFinder(),
        new CheckboxFinder(), new LabelFinder(), new JazzLabelFinder());
  }

  /**
   * <p>
   * Open Manage Artifact Template page by "Administration, Manage Artifact Template" from
   * {@link RQMManageArtifactTemplatesPage#openSubMenuUnderSection(String, String)}. <br>
   * <br>
   * Search the artifact tempate in 'Type filter text and press Enter' text box.
   *
   * @param artifactTemplate name of the test cases or test scripts or keywords etc...
   */
  public void searchRqmArtifactsInFilterTextBox(final String artifactTemplate) {
    waitForPageLoaded();
    Text txtSearch = this.engine.findElementWithDuration(
        Criteria.isTextField().withPlaceHolder(RQMConstants.TYPE_FILTER_TEXT_AND_PRESS_ENTER_PLACEHOLDER),
        this.timeInSecs).getFirstElement();
    txtSearch.setText(artifactTemplate);
    LOGGER.LOG.info(artifactTemplate + " - searched in 'Type Filter' text box.");
    waitForSecs(3);
    Button btnFilter =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.FILTERS), this.timeInSecs)
            .getFirstElement();
    btnFilter.click();
    LOGGER.LOG.info("Clicked on 'Filter' button.");
  }

  /**
   * <P>
   * Navigate to RQM Manage artifact template.<br>
   * select value from create templates drop down.
   *
   * @param dropdownValue value of drop down inside the create template.
   */
  public void selectValueFromCreateTemplate(final String dropdownValue) {
    Dropdown drdCreateTemplate =
        this.engine.findElementWithDuration(Criteria.isDropdown().withToolTip("Create Templates"), this.timeInSecs)
            .getFirstElement();
    drdCreateTemplate.selectOptionWithText(dropdownValue);
    LOGGER.LOG.info(dropdownValue + " is selected successfully.");
  }


  /**
   * <P>
   * Input Name of Keyword Template at Create Keyword Template
   *
   * @param dialog name of the dialog
   * @param templatename Name of Keyword Template.
   * @return Name of Keyword Template.
   */
  public String setRQMArtifactTemplateName(final String dialog, final String templatename) {
    Dialog dlgCreateKeywordTemplate =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(dialog), this.timeInSecs).getFirstElement();
    Text txtName = this.engine
        .findElementWithDuration(Criteria.isTextField().hasLabel("Name:").inContainer(dlgCreateKeywordTemplate),
            this.timeInSecs)
        .getFirstElement();
    String newtemplatename = templatename + DateUtil.getCurrentDateAndTime();
    txtName.setText(newtemplatename);
    LOGGER.LOG.info(newtemplatename + " is entered in Name field.");
    return newtemplatename;
  }

  /**
   * <P>
   * Input Description of Keyword Template at Create Keyword Template
   *
   * @param dialog name of the dialog
   * @param templateDescription Description of Keyword Template.
   */
  public void setRQMArtifactTemplateDescription(final String dialog, final String templateDescription) {
    Dialog dlgCreateKeyword =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(dialog), this.timeInSecs).getFirstElement();
    Text txtDescription = this.engine
        .findElementWithDuration(Criteria.isTextField().hasLabel("Description:").inContainer(dlgCreateKeyword),
            this.timeInSecs)
        .getFirstElement();
    txtDescription.setText(templateDescription);
    LOGGER.LOG.info(templateDescription + " is entered in Description template field.");
  }

  /**
   * <p>
   * Add Detailed Description selection.
   *
   * @param dialog name of the dialog
   * @param option value under Available Sections.
   */
  public void selectAvaiableSectionsValue(final String dialog, final String option) {
    Dialog dlgTemplate =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(dialog), this.timeInSecs).getFirstElement();
    Dropdown drdSelection = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Available Sections:").inContainer(dlgTemplate),
            this.timeInSecs)
        .getFirstElement();
    drdSelection.selectOptionWithText(option);
    LOGGER.LOG.info(option + " is selected successfully.");
  }

  /**
   * <p>
   * Remove Detailed Description selection.
   *
   * @param dialog name of the dialog
   * @param option value under Selected Sections.
   */
  public void selectSelectedSection(final String dialog, final String option) {
    this.driverCustom.select("//select[@dojoattachpoint='chosenOptionsNode']", option,
        SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
    LOGGER.LOG.info(option + " is selected from Selected section successfully.");
  }

  /**
   * Input section name.
   *
   * @param newKeywordSessionName section name.
   * @return section name.
   */
  public String sectionName(final String newKeywordSessionName) {
    Dialog dlgNewCustomSection =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("New Custom Section"), this.timeInSecs)
            .getFirstElement();
    Text txtSectionName = this.engine
        .findElementWithDuration(Criteria.isTextField().hasLabel("Section Name:").inContainer(dlgNewCustomSection),
            this.timeInSecs)
        .getFirstElement();
    String keywordSessionName = newKeywordSessionName + DateUtil.getCurrentDateAndTime();
    txtSectionName.setText(keywordSessionName);
    LOGGER.LOG.info(keywordSessionName + " is entered in 'Section Name' field.");
    return keywordSessionName;
  }

  /**
   * <p>
   * Provides the description of the section.
   *
   * @param sectionDescription is the description in the section.
   */
  public void setSectionDescription(final String sectionDescription) {
    Text txtNewCustomDescriptions = this.engine
        .findElementWithDuration(Criteria.isTextField().hasLabel("Description:"), this.timeInSecs).getFirstElement();
    txtNewCustomDescriptions.setText(sectionDescription);
    LOGGER.LOG.info(sectionDescription + " is entered in Section Description field.");
  }

  /**
   * <P>
   * Set section details.
   *
   * @param details of RQM template
   */
  public void setSectionDetails(final String details) {
    Text txtaSectionDetail =
        this.engine.findElementWithDuration(Criteria.isTextEditor(), this.timeInSecs).getFirstElement();
    txtaSectionDetail.setText(details);
    LOGGER.LOG.info(details + " is entered in Section Detail field.");
  }

  /**
   * <p>
   * verify template exists or not.
   *
   * @param keywordTemplateName name of template.
   * @return true if template visible.
   */
  public boolean verifyTemplate(final String keywordTemplateName) {
    Row criteriaTemplate = this.engine
        .findElementWithDuration(Criteria.isRow().withText(keywordTemplateName), this.timeInSecs).getFirstElement();
    Cell getCellTemplate =
        this.engine.findElement(Criteria.isCell().inContainer(criteriaTemplate).withIndex(3)).getFirstElement();
    LOGGER.LOG.info(keywordTemplateName + " is verified - showing properly");
    return getCellTemplate != null;
  }

  /**
   * <p>
   * verify template sections exists or not.
   *
   * @param keywordTemplateName name of template.
   * @return true if template visible.
   */
  public boolean verifySectionTemplate(final String keywordTemplateName) {
    Row criteriaTemplate = this.engine
        .findElementWithDuration(Criteria.isRow().withText(keywordTemplateName), this.timeInSecs).getFirstElement();
    Cell getCellTemplate =
        this.engine.findElement(Criteria.isCell().inContainer(criteriaTemplate).withIndex(3)).getFirstElement();
    getCellTemplate.click();
    LOGGER.LOG.info(keywordTemplateName + " template is verfied - checked properly");
    return this.driverCustom.isElementVisible("//td[@class='optionSelectColumn']", this.timeInSecs);
  }

  /**
   * @param keywordTemplateName name of the template.
   * @param option to select for the Template.
   */
  public void selectTemplateDropdownValue(final String keywordTemplateName, final String option) {
    Row criteriaRowTemplate = this.engine
        .findElementWithDuration(Criteria.isRow().withText(keywordTemplateName), this.timeInSecs).getFirstElement();
    Cell cllAction =
        this.engine.findElement(Criteria.isCell().inContainer(criteriaRowTemplate).withIndex(1)).getFirstElement();
    Dropdown drdAction = this.engine
        .findElementWithDuration(Criteria.isDropdown().withToolTip("Actions").inContainer(cllAction), this.timeInSecs)
        .getFirstElement();
    drdAction.selectOptionWithText(option);
    LOGGER.LOG.info(option + " is selected from Action dropdown successfully.");
  }

  /**
   * @param keywordTemplateNameCopy name of the template.
   * @return true if default column contains yes.
   */
  public boolean verifyDefaultColumn(final String keywordTemplateNameCopy) {
    waitForSecs(5);
    Row rowDefaultTemplateVerify = this.engine
        .findElementWithDuration(Criteria.isRow().withText(keywordTemplateNameCopy), this.timeInSecs).getFirstElement();
    Cell cllDefault = this.engine
        .findElementWithDuration(Criteria.isCell().inContainer(rowDefaultTemplateVerify).withIndex(5), this.timeInSecs)
        .getFirstElement();
    return cllDefault.getText().equalsIgnoreCase("Yes");
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMREQUIREMENT_LINK_XPATH, "Construction");
  }
}
