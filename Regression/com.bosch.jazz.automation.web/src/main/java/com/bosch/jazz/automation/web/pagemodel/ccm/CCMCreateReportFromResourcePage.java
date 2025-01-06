package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.time.Duration;
import java.util.Map;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMSubMenus;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Text;

import finder.text.JazzTextFinder;


/**
 * @author hrt5kor
 */
public class CCMCreateReportFromResourcePage extends AbstractCCMPage {


  /**
   *
   */
  private static final String INVALID_PARAMETER_OR_NULL_OR_EMPTY = "Invalid parameter or null or empty.";
  /**
   * Constructor setting the {@link WebDriverCustom}
   * @param driverCustom required for interacting with the browser.
   */
  public CCMCreateReportFromResourcePage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinder(new JazzTextFinder());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    String urlSuffix = "#action=com.ibm.team.reports.newReport";
    this.driverCustom.openURL(getProjectAreaURL(repositoryURL, projectAreaName) + urlSuffix);
    this.driverCustom.waitForPageLoaded("New Report");
  }

  /**
   * Opens the page by clicking on the menu: Reports -> Create new report from resource.<br>
   * Assumes the project area is already open, otherwise the menu is not available.
   */
  public void openViaMenu() {
    waitForPageLoaded();
    openMenu(CCMMenus.REPORTS.toString());
    openSubMenu(CCMSubMenus.REPORT_RESOURCES.toString());
    this.driverCustom.waitForPageLoaded("New Report");
  }

  /**
   * Sets the name of the report.
   *
   * @param name of the Report.
   */
  public void setReportName(final String name) {
    waitForPageLoaded();
    if ((name != null) && !name.isEmpty()) {
      Text txtName = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Name:"));
      txtName.setText(name);
      return;
    }
    throw new IllegalArgumentException(INVALID_PARAMETER_OR_NULL_OR_EMPTY);
  }

  /**
   * Sets the description in the report.
   *
   * @param desc of the Report.
   */
  public void setDescription(final String desc) {
    waitForPageLoaded();
    if ((desc != null) && !desc.isEmpty()) {
      Text txtName = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Description:"));
      txtName.setText(desc);
      return;
    }
    throw new IllegalArgumentException(INVALID_PARAMETER_OR_NULL_OR_EMPTY);
  }

  /**
   * @param resouceName pass the value of the resource.
   */
  public void selectResource(final String resouceName) {
    waitForPageLoaded();
    if ((resouceName != null) && !resouceName.isEmpty()) {
      Label lblFolder = this.engine.findElement(Criteria.isLabel().withText(resouceName)).getFirstElement();
      lblFolder.click();
      return;
    }
    throw new IllegalArgumentException(INVALID_PARAMETER_OR_NULL_OR_EMPTY);
  }

  /**
   * Select the folder.
   *
   * @param folderName name of the Folder.
   */
  public void selectFolder(final String folderName) {

    waitForPageLoaded();
    if ((folderName != null) && (!folderName.isEmpty())) {
      Label lblFolder = this.engine.findElement(Criteria.isLabel().withText(folderName)).getFirstElement();
      lblFolder.click();
      return;
    }
    throw new IllegalArgumentException("Invalid parameter or null or empty in the list or Missing parameters.");
  }

  /**
   * @return true if the report is saved successfully.
   */
  public boolean isReportSaved() {
    waitForPageLoaded();
    return !(this.driverCustom
        .isElementVisible(CCMConstants.CCMCREATEREPORTFROMRESOURCEPAGE_VALIDATIONMESSAGE_TEXTFIELD_XPATH, Duration.ofSeconds(30)));
  }

  /**
   * @return validation message on the report resource page.
   */
  public String getVadationMessageOnReportResource() {
    waitForPageLoaded();
    return this.driverCustom.getText(CCMConstants.CCMCREATEREPORTFROMRESOURCEPAGE_VALIDATIONMESSAGE_TEXTFIELD_XPATH);

  }

  /**
   * This method saves and verify whether the report is saved or not.
   */
  public void saveReport() {
    waitForPageLoaded();
    Button btnSave = this.engine.findElement(Criteria.isButton().withText("Save")).getFirstElement();
    btnSave.click();
  }

  /**
   * This method wait for the presence of Reports link in Reports page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMREPORSPAGE_REPORTS_LINK_XPATH);
  }

}
