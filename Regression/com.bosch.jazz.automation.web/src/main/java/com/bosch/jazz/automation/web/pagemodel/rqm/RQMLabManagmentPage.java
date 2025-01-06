/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.ByType;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.ColumnFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LabelFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.JazzTextEditorFinder;
import finder.text.label.JazzLabelFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * @author KDO1KOR
 */
public class RQMLabManagmentPage extends AbstractRQMPage {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMLabManagmentPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDialogFinder(), new JazzTextFieldFinder(), new JazzTextEditorFinder(),
        new JazzDropdownFinder(), new DropdownFinder(), new JazzRowFinder(this.driverCustom.getWebDriver()),
        new RowCellFinder(), new ColumnFinder(), new LinkFinder(), new JazzRadioButtonFinder(), new TextFieldFinder(),
        new CheckboxFinder(), new LabelFinder(), new JazzLabelFinder(), new JazzButtonFinder());
  }

  /**
   * This summaryText used to handle Summary Text field in Test Environment RQM record
   *
   * @param additionalParams use to get value from the test cases
   */
  public void summaryText(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().findElement(By.xpath("//textarea[contains(@class,'summary-value')]"))
        .sendKeys(RQMConstants.TEST_ARTIFACT_TITLE_VALUE);
  }


  /**
   * This selectTypeAddContents used to add Test Environment type in RQM Artifact.
   *
   * @param additionalParams use to get value from the test cases
   * @param selectName used to select the criteria
   * @param selectValue used to select the value from Dropdown.
   * @param domainName used to select the domain from Dropdown.
   */
  public void selectTypeAddContents(final Map<String, String> additionalParams, final String selectName,
      final String selectValue, final String domainName) {
    waitForPageLoaded();
    RQMConstructionPage rqm = new RQMConstructionPage(this.driverCustom);
    if ((additionalParams.get(domainName)).equalsIgnoreCase("Type:")) {
      addTestEnvironCriteria(additionalParams, domainName);
      waitForPageLoaded();
      rqm.selectCriteria(additionalParams.get(selectValue), "Machine");
    }
    else if ((additionalParams.get(domainName)).equalsIgnoreCase("Operating System:")) {
      waitForPageLoaded();
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTENVI_OSADDCONLINK_XPATH,
          additionalParams.get(domainName));
      this.driverCustom.click(RQMConstants.RQMPROJECT_TESTENVI_OSADDCONLINK_XPATH, additionalParams.get(domainName));
      waitForPageLoaded();
      rqm.selectCriteria(additionalParams.get(selectValue), "Machine");
    }
    Button button =
        this.engine.findElement(Criteria.isButton().withText(additionalParams.get("addButton"))).getFirstElement();
    button.click();
  }

  /**
   * This addTestEnvironCriteria used to click on the link Add Contents in Test Environment
   *
   * @param additionalParams use to get value from the test cases
   * @param domainName use to add domain in testenvironment
   */
  public void addTestEnvironCriteria(final Map<String, String> additionalParams, final String domainName) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTENVI_ADDCONLINK_XPATH,
        additionalParams.get(domainName));
    this.driverCustom.click(RQMConstants.RQMPROJECT_TESTENVI_ADDCONLINK_XPATH, additionalParams.get(domainName));
  }

  /**
   * @param testEnvName name of the Test Environment.
   * @param button name of the button.
   */
  public void removeTestEnvironment(final String testEnvName, final String button) {
//    Row rowEnv = this.engine.findElementWithDuration(Criteria.isRow().withText(testEnvName), timeInSecs).getFirstElement();
//    Checkbox chb = this.engine.findElementWithDuration(Criteria.isCheckbox().inContainer(rowEnv), timeInSecs).getFirstElement();
//    chb.click();
    //Engine finder does not work correctly - replace by selenium web driver
    try {
      this.driverCustom.getWebElement("//div[@title='DYNAMIC_VAlUE']/ancestor::tr[contains(@name,'row')]//input[@type='checkbox']", testEnvName).click();
      Button btnDelete = this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Delete"), timeInSecs).getFirstElement();
      btnDelete.click();
      Button btn = this.engine.findElementWithDuration(Criteria.isButton().withText(button), timeInSecs).getFirstElement();
      btn.click();
      waitForSecs(3);
    }
    catch (Exception e) {
      throw new WebAutomationException(String.format("Can not remove Test environment: '%s' - errors: %s", testEnvName, e));
    }
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebDriverSetup().getConfigurationForExplicitWaitTime());
    By locator = this.driverCustom.createLocator("//a[text()='Lab Management']", ByType.XPATH);
    ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOfElementLocated(locator);
    wait.until(condition);

  }

}
