/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.ccm;


import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMCreatePlanPage;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.dropdown.JazzDropdownFinder;

/**
 * @author NCY3HC
 */
public class CCMCreatePlanPageVerification extends CCMPlanPageVerification {

  /**
   * @param driverCustom driver.
   */
  public CCMCreatePlanPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDropdownFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new RowCellFinder(), new JazzDialogFinder(), new JazzTabFinder(), new DropdownFinder());
  }

  /**
   *This method is called after executing {@link CCMCreatePlanPage#isPlanSaved()}
   *@author NCY3HC
   *@param lastResult return result from last execution 
   *@param expectedPlanName - New plan name which just inputed into new plan
   *@return verification message
   */
  
  public TestAcceptanceMessage verifyIsPlanedSaved(final String expectedPlanName, final String lastResult) {
    if (!this.driverCustom.isElementClickable(CCMConstants.JAZZPAGE_BUTTONS_XPATH, timeInSecs, "Save")) {
        return new TestAcceptanceMessage(true,
            "New plan '"+expectedPlanName+"' is created successfully!  ");
      }
    return new TestAcceptanceMessage(false, "New plan '"+expectedPlanName+"' is created unsuccessfully!");
  }
 
 /**
  * This method is called after executing ${link {@link CCMCreatePlanPage#setPlanName(String)}
  * @param name - name of plan
  * @param lastResult - return the result from last execution
  * @return verification message
  */
 public TestAcceptanceMessage verifySetPlanName(final String name, final String lastResult) {
  if(lastResult.equalsIgnoreCase(name)) {
    return new TestAcceptanceMessage(true, "Input '" + name + "' into Plan Name successfully! ");
  }
  return new TestAcceptanceMessage(false, "Input " + name + " into Plan Name unsuccessfully! ");
 }
 
 /**
  * This method is called after executing ${link {@link CCMCreatePlanPage#setOwner(String)}
  * @param owner - plan's owner
  * @param lastResult - return the result from last execution
  * @return verification message
  */
 public TestAcceptanceMessage verifySetOwner(final String owner, final String lastResult) {
   WebElement actualOwnerName = this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEPLANPAGE_SELECTEDOWNER_ITERATION_XPATH, "Owner");
  if(actualOwnerName.getText().equalsIgnoreCase(owner)) {
    return new TestAcceptanceMessage(true, "Select owner '" +owner+ "' for new Plan Name successfully! ");
  }
  return new TestAcceptanceMessage(false, "Select owner '" +owner+ "' for new Plan Name unsuccessfully! ");
 }
 
 /**
  * This method is called after executing ${link {@link CCMCreatePlanPage#setIteration(String)}
  * @param iteration - plan's iteration
  * @param lastResult - return the result from last execution
  * @return verification message
  */
 public TestAcceptanceMessage verifySetIteration(final String iteration, final String lastResult) {
   WebElement actualIterationName = this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEPLANPAGE_SELECTEDOWNER_ITERATION_XPATH, "Iteration");
  if(actualIterationName.getText().equalsIgnoreCase(iteration)) {
    return new TestAcceptanceMessage(true, "Select iteration' " +iteration+ "' for new Plan Name successfully! ");
  }
  return new TestAcceptanceMessage(false, "Select iteration '" +iteration+ "' for new Plan Name unsuccessfully! ");
 }
 
 /**
  * This method is called after executing ${link {@link CCMCreatePlanPage#deleteTestPlan(String)}
  * @param planName - plan's name
  * @param lastResult - return the result from last execution
  * @return verification message
  */
 public TestAcceptanceMessage verifyDeleteTestPlan(final String planName, final String lastResult) {
   this.driverCustom.isElementInvisible("//div[contains(@class,'status-message') and contains(text(),'Delete Plan')]", timeInSecs);
   this.driverCustom.typeText(CCMConstants.CCMCREATEPLANPAGE_SEARCHPLANS_TEXTBOX_XPATH, planName);
   this.driverCustom.isElementInvisible("//div[contains(@class,'status-message') and contains(text(),'Searching')]", timeInSecs);
   Boolean isPlanDisplayed = this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITPAGE_TEST_PLAN_XPATH, timeInSecs, planName);
  if(!isPlanDisplayed) {
    return new TestAcceptanceMessage(true, "Delete Plan ' " +planName+ " ' successfully! ");
  }
  return new TestAcceptanceMessage(false, "Delete Plan ' " +planName+ " ' unsuccessfully!");
 }
 
}
