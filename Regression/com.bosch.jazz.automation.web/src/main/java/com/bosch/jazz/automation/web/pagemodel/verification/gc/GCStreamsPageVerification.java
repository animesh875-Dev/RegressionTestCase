/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.gc;

import java.time.Duration;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.gc.GCStreamsPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;

/**
 * @author LTU7HC
 *
 */
public class GCStreamsPageVerification extends AbstractWebPageVerification{

  /**
   * @param driverCustom driver
   */
  public GCStreamsPageVerification(WebDriverCustom driverCustom) {
    super(driverCustom);
  }
  
  /**
   * <p>Verifies the action of {@link GCStreamsPage#filterAndSelectStream(String)}.
   * 
   * @param streamName name of stream
   * @param lastResult the last result
   * @return test acceptance message
   */
  public TestAcceptanceMessage verifyFilterAndSelectStream(final String streamName, final String lastResult) {
    try {
      this.driverCustom.isElementInvisible("//div[text()='Loading Configuration...']", Duration.ofSeconds(5));
      String actualStreamName =
          this.driverCustom.getPresenceOfWebElement("//span[@dojoattachpoint='_pageTitleNode']").getText().trim();
      if(actualStreamName.equals(streamName)) {
        return new TestAcceptanceMessage(true, String.format(
            "Verify that search and select stream successfully with stream name: '%s'", actualStreamName));
      }
      return new TestAcceptanceMessage(false, String.format(
          "Verify that search and select stream not completed - Actual stream name: '%s'", actualStreamName));

    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Verify that search and select stream not completed");
    }
  }

}
