/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.gc;

import java.time.Duration;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.gc.GCAllProjectsPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;

/**
 * @author HRT5KOR
 *
 */
public class GCAllProjectsPageVerification extends AbstractWebPageVerification{

  /**
   * @param driverCustom driver.
   */
  public GCAllProjectsPageVerification(WebDriverCustom driverCustom) {
    super(driverCustom);
  }
  /**
   * Assumes the page is shown where all project areas are listed, then clicks on the given project area in the list.
   * 
   * <p>Verifies the action of {@link GCAllProjectsPage#selectProjectArea(String)}.
   *
   * @param projectAreaName the name of the project area, must not be null
   * @param lastResult result.
   * @return true if passed.
   */
  public TestAcceptanceMessage verifySelectProjectAreaGC(final String projectAreaName, final String lastResult) 
  {
     if(driverCustom.isTitleContains("Global Configuration Management", Duration.ofSeconds(10)))
       return new TestAcceptanceMessage(true, "Verified by Welcome Global configration page.\n\nActual result is "+projectAreaName+" opened sucessfully as expected.");
     return new TestAcceptanceMessage(false, "Verified by Welcome Global configration page.\n\nActual result is "+projectAreaName+" not opened sucessfully as expected.");
  }
  
}
