/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.text.label.JazzDivLabelFinder;
import finder.text.textField.JazzCommonTextFieldFinder;


/**
 * Represents the first visible page (before saving) when a plan should be created.
 */
public class CCMCreatePlanPage extends AbstractCCMPage {

  private static final String URL_SUFFIX = "#action=com.ibm.team.apt.createPlan";
  private static final String URL_MIDDLE_AFTER_SAVE = "com.ibm.team.apt.web.ui.plannedItems";


  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom required for interacting with the browser.
   */
  public CCMCreatePlanPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * Opens the page for creating a new RTC plan.
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    this.driverCustom.openURL(getProjectAreaURL(repositoryURL, projectAreaName) + URL_SUFFIX);
  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Plan' as 'Sprint Backlog','Release Backlog','Cross-Project Plan','Product Backlog' Sub Menu using
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * This method sets name of the Plan in 'New Plan' text field.
   *@param name passed name of the plan using key PLAN_NAME and value.
   *@return New Plan name
   */
  public String setPlanName(final String name) {

    waitForPageLoaded();
    if ((name != null) && (!name.isEmpty())) {
      this.driverCustom.isElementVisible(CCMConstants.CCMCREATEQUERYPAGE_SELECT_TAB_XPATH, Duration.ofSeconds(10), "New Plan");
      ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(15));
      Text txtTestPlanName =
          this.engine.findElementWithinDuration((Criteria.isTextField().hasLabel("New Plan")), Duration.ofSeconds(60)).getFirstElement();
      txtTestPlanName.setText(name);
      txtTestPlanName.click();
      WebElement inputPlanName = this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEM_PLAN_NAME_VALUE_XPATH);
      return inputPlanName.getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE);
    }
    throw new IllegalArgumentException("Invalid parameter or null or empty in the plan name.");
  }


  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Plan' as 'Sprint Backlog','Release Backlog','Cross-Project Plan','Product Backlog' Sub Menu using
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * This method sets 'owner' of the plan in the plan editor.<br>
   * this method clicking 'Browse...' button in 'Select Owner' dialog <br>
   * set owner in 'Type Filter Text' field then clicking on 'OK' button.
   *
   * @Note name should not be null/empty. otherwise "Invalid owner or null or empty for the owner" exception will be
   *       thrown.
   * @param owner Pass the value of the owner.
   */
  public void setOwner(final String owner) {
    waitForPageLoaded();
    if ((owner != null) && (!owner.isEmpty())) {
      // Click on Owner button
      Button btnOwner = this.engine.findElement(Criteria.isButton().withToolTip("Owner:Browse...")).getFirstElement();
      btnOwner.click();
      // Search Owner
      this.engine.addFinder(new JazzDialogFinder());
      this.engine.addFinder(new JazzCommonTextFieldFinder());
      this.engine.addFinder(new JazzDivLabelFinder());

      Dialog dlgSelectOwner = this.engine.findElement(Criteria.isDialog().withTitle("Select Owner")).getFirstElement();
      WebElement txtSearchOwner =
          this.driverCustom.getPresenceOfWebElement("//input[@title='Search Owners Filter Box']");
      txtSearchOwner.sendKeys(owner);
      // Select Owner
      Label lblOwner =
          this.engine.findElement(Criteria.isLabel().withText(owner).inContainer(dlgSelectOwner)).getFirstElement();
      lblOwner.click();
      // Click on Ok button
      this.driverCustom.isElementVisible(CCMConstants.CCMCREATEQUERYPAGE_QUERY_BUTTON_XPATH, Duration.ofSeconds(10), "OK");
      Button oKbtn =
          this.engine.findElement(Criteria.isButton().withText("OK").inContainer(dlgSelectOwner)).getFirstElement();
      oKbtn.click();
      return;
    }
    throw new IllegalArgumentException("Invalid owner or null or empty for the owner.");

  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Plan' as 'Sprint Backlog','Release Backlog','Cross-Project Plan','Product Backlog' Sub Menu using
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * This method sets iteration of the plan in the plan editor.<br>
   * this method clicking 'Browse...' button in 'Select Iteration' dialog <br>
   * set owner in 'Type Filter Text' field then clicking on 'OK' button<br>
   * and selecting 'Include All Items' check box.
   *
   * @Note name should not to be null/empty, otherwise "Invalid iteration or null or empty for the iteration" exception
   *       will be thrown.
   * @param iteration pass value of the iteration.
   */
  public void setIteration(final String iteration) {
    waitForPageLoaded();
    if ((iteration != null) && (!iteration.isEmpty())) {
      this.engine.addFinder(new JazzDialogFinder());
      this.engine.addFinder(new JazzCommonTextFieldFinder());
      this.engine.addFinder(new JazzDivLabelFinder());

      // Click on Iteration button
      Button btnIteration =
          this.engine.findElement(Criteria.isButton().withToolTip("Iteration:Browse...")).getFirstElement();
      btnIteration.click();

      // Search Iteration
      Dialog dlgSelectIteration =
          this.engine.findElement(Criteria.isDialog().withTitle("Select Iteration")).getFirstElement();
      Text txtSearchIteration = this.engine.findElementWithDuration(Criteria.isTextField().withToolTip("Search Iterations Filter Box").inContainer(dlgSelectIteration), timeInSecs).getFirstElement();
      txtSearchIteration.setText(iteration);

      // Select Iteration
      Label lblIteration = this.engine
          .findElement(Criteria.isLabel().withText(iteration).inContainer(dlgSelectIteration)).getFirstElement();
      lblIteration.click();

      // Click on Ok button
      Button btnOK2 =
          this.engine.findElement(Criteria.isButton().withText("OK").inContainer(dlgSelectIteration)).getFirstElement();
      btnOK2.click();

      // Check "Include All Items" check box
      this.engine.addFinders(new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder());
      Row rowIncludeAll = this.engine.findElement(Criteria.isRow().containsText("Include All Items")).getFirstElement();
      Cell cellCheckbox =
          this.engine.findElement(Criteria.isCell().inContainer(rowIncludeAll).withIndex(1)).getFirstElement();
      Checkbox cbxIncludeAllItem =
          this.engine.findElement(Criteria.isCheckbox().inContainer(cellCheckbox)).getFirstElement();
      cbxIncludeAllItem.click();
      return;
    }
    throw new IllegalArgumentException("Invalid iteration or null or empty for the iteration.");

  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Plan' as 'Sprint Backlog','Release Backlog','Cross-Project Plan','Product Backlog' Sub Menu using
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * This method select the type of the plan from 'plan type' drop down.
   *
   * @param planType @see Enum PlanTypes for the parameter.
   */
  public void setPlanType(final String planType) {
    waitForPageLoaded();
    if ((planType != null) && (!planType.isEmpty())) {
      this.engine.addFinders(new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder());
      Row rowPlanType = this.engine.findElement(Criteria.isRow().withText("Phase Plan")).getFirstElement();
      Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().inContainer(rowPlanType));
      drdMenu.selectOptionWithText("Phase Plan");
      return;
    }

    throw new IllegalArgumentException("Invalid parameter or null or empty for the planType.");

  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Plan' as 'Sprint Backlog','Release Backlog','Cross-Project Plan','Product Backlog' Sub Menu using
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * without setting mandate fields click on save button using {@link CCMCreatePlanPage #save()}}<br>
   * This method used to get validation message from 'messageArea'
   *
   * @return Error message shown on the Plan editor.
   */
  public String getValidationMessage() {
    waitForPageLoaded();
    return this.driverCustom.getText(CCMConstants.CCMCREATEPLANPAGE_ERRORMESSAGE_SUMMARY_LABEL_SELECTOR);
  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Plan' as 'Sprint Backlog','Release Backlog','Cross-Project Plan','Product Backlog' Sub Menu using
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * After save plan using {@link CCMCreatePlanPage #save()}} <br>
   * This method used to check boolean condition of plan (save button is enabled or desabled)
   *
   * @return true if the plan is saved successfully.
   */
  public boolean isPlanSaved() {
    waitForPageLoaded();
    return this.driverCustom.isTextPresentInTheCurrentUrl(URL_MIDDLE_AFTER_SAVE, 10);
  }

  /**
   * <p>
   * Open 'Plans' Menu using {@link CCMProjectAreaDashboardPage #openMenu(String)} <br>
   * Click on 'Create Plan' as 'Sprint Backlog','Release Backlog','Cross-Project Plan','Product Backlog' Sub Menu using
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} <br>
   * After filling all mandatory fields used this method to save plan<br>
   * Presses on 'Save' button to create a plan. Then returns the page representing the existing plan.
   *
   * @return the page representing the created plan
   */
  public CCMPlanPage save() {
    waitForPageLoaded();
    Button btnSave = this.engine.findElement(Criteria.isButton().withText("Save")).getFirstElement();
    btnSave.click();
    return null;
  }

  /**
   * This method wait for the presence of Plans link in plan page.
   */
  @Override
  public void waitForPageLoaded() {

    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPLANPAGE_PLANS_LINK_XPATH);
  }
  
  /**
   * Open All Test Plan using method {@link CCMProjectAreaDashboardPage #openMenu}, {@link CCMProjectAreaDashboardPage#openSubMenu}
   * Search for the target Plan
   * Click on 'X' buton to delete test plan
   * @author NCY3HC
   * @param planName : which need to be deleted
   */
  public void deleteTestPlan(final String planName) {
   this.driverCustom.isElementInvisible("//div[contains(@class,'status-message') and contains(text(),'Searching')]", timeInSecs);
   this.driverCustom.getWebElement(CCMConstants.CCMWORKITEMEDITPAGE_TEST_PLAN_ICON_XPATH, planName).click();
   WebElement btnDeletePlan = this.driverCustom.getWebElement(CCMConstants.CCMWORKITEMEDITPAGE_DELETE_PLAN_XPATH, planName);
   btnDeletePlan.click();
//   new Actions(this.driverCustom.getWebDriver())
//   .moveToElement(this.driverCustom.getWebElement(CCMConstants.CCMWORKITEMEDITPAGE_DELETE_PLAN_XPATH, planName))
//   .click().build().perform();
//   Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), timeInSecs).getFirstElement();
//   btnOK.click();
//   this.driverCustom.getWebDriver().switchTo().activeElement().sendKeys(Keys.ENTER);
   ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(30));
   
      }  
}
