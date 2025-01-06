/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.gc;

import java.time.Duration;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.gc.ComponentsPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;

/**
 * @author HRT5KOR
 */
public class ComponentsPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom driver
   */
  public ComponentsPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);

  }

  /**
   * <p>Verifies the action of {@link ComponentsPage#filterComponent(String)}.
   * 
   * @param name name of component
   * @param lastResult the last result
   * @return test acceptance message
   */
  public TestAcceptanceMessage veifyFilterComponent(final String name, final String lastResult) {
    try {
      this.engine.findElement(Criteria.isLink().withText(name)).getFirstElement();
      return new TestAcceptanceMessage(true, "Verified by filtered component displyed on page .\n\nActual result is " +
          name + " component found as expected.");

    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Verified by filtered component displyed on page .\n\nActual result is " +
          name + " component not found as expected.");
    }

  }
  
  /**
   * <p>Verifies the action of {@link ComponentsPage#filterAndSelectComponent(String)}.
   * 
   * @param componentName name of component
   * @param lastResult the last result
   * @return test acceptance message
   */
  public TestAcceptanceMessage verifyFilterAndSelectComponent(final String componentName, final String lastResult) {
    try {
      this.driverCustom.isElementInvisible("//div[text()='Loading Components...']", Duration.ofSeconds(5));
      String actualComponentName =
          this.driverCustom.getPresenceOfWebElement("//span[starts-with(@class, 'gc-header')]").getText().trim();
      if(actualComponentName.equals(componentName)) {
        return new TestAcceptanceMessage(true, String.format(
            "Verify that search and select component successfully with component name: '%s'", actualComponentName));
      }
      return new TestAcceptanceMessage(false, String.format(
          "Verify that search and select component not completed - Actual component name: '%s'", actualComponentName));

    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Verify that search and select component not completed");
    }
  }
}
