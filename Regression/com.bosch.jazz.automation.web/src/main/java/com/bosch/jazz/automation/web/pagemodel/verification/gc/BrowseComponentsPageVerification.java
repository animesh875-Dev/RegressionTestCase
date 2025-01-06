/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.gc;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.AbstractGCPage;
import com.bosch.jazz.automation.web.pagemodel.gc.BrowseComponentsPage;
import com.bosch.psec.web.test.criteria.Criteria;

/**
 * @author HRT5KOR
 */
public class BrowseComponentsPageVerification extends AbstractGCPage {

  /**
   * @param driverCustom driver.
   */
  public BrowseComponentsPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * Clicks on Create Component button appears on top right side of the page.
   * 
   * <p>Verifies the action of {@link BrowseComponentsPage#clickOnCreateComponentButton()}.
   *
   * @param lastResult result
   * @return true
   */
  public TestAcceptanceMessage verifyClickOnCreateComponentButton(final String lastResult) {
    try {
      waitForSecs(5);
      this.engine.findElement(Criteria.isDialog().withTitle("Create Component")).getFirstElement();
      return new TestAcceptanceMessage(true,
          "Verified by Create Component line dialog opened.\n\nActual result is Create Component dialog opened as expected.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified by Create Component line dialog opened.\n\nActual result is Create Component dialog not opened.");
    }

  }

  /**
   * Creates baseline by setting value to "Name Suffix:" , "Description".
   * 
   * <p>Verifies the action of {@link BrowseComponentsPage#createComponent(String, String, String)}.
   * 
   * @param compName name of component
   * @param compDescription description of component
   * @param existingTag name of the existing tag.
   * @param lastResult result
   * @return true
   */
  public TestAcceptanceMessage verifyCreateComponent(final String compName, final String compDescription,
      final String existingTag, final String lastResult) {
    if (this.driverCustom.getWebElements("//span[@class='gc-header' or @Class='gc-header gc-flexrow__truncated-text']").stream()
        .anyMatch(x -> x.getText().contains(compName))) {
      return new TestAcceptanceMessage(true,
          "Verified by displyed title on page.\n\nActual result is component created with name containing " + compName +
              " as expected.");
    }
    return new TestAcceptanceMessage(false,
        "Verified by displyed title on page.\n\nActual result is component created with name not containing " +
            compName + " as expected.");
  }
}
