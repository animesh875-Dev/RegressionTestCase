/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import org.openqa.selenium.Keys;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.text.Text;

/**
 * @author UUM4KOR
 *         <p>
 *         Aftr selecting welcomes to work items menu. <br>
 *         Represents Change and configuration management work item editor page, has capabilities to edit and change
 *         work flow state of any work items of any template.
 */
public class CCMWelcomeToWorkItemPage extends AbstractCCMPage {

  /**
   * <p>
   * Aftr selecting welcomes to work items menu. <br>
   * Represents Change and configuration management work item editor page, has capabilities to edit and change work flow
   * state of any work items of any template.
   *
   * @param driverCustom required for interacting with the browser.
   */
  public CCMWelcomeToWorkItemPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * <p>
   * Aftr selecting welcomes to work items menu, Search workitem.
   *
   * @param wiId search the workitem Id.
   */

  public void searchWorkItem(final String wiId) {
    waitForPageLoaded();
    Text txtSearchField = this.engine.findFirstElement(Criteria.isTextField().withToolTip("Search All Work Items"));
    txtSearchField.setText(wiId + Keys.ENTER);
  }

  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getWebElements("//div[@class='ViewletTitle']");
  }

 
}
