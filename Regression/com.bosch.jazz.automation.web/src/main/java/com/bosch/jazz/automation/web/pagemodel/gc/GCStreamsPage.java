/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc;

import java.time.Duration;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractGCPage;

/**
 * @author LTU7HC
 *
 */
public class GCStreamsPage extends AbstractGCPage{

  /**
   * @param driverCustom driver
   */
  public GCStreamsPage(WebDriverCustom driverCustom) {
    super(driverCustom);
  }
  
  /**
   * <p>
   * Filter and select component name using {@link ComponentsPage#searchAndSelectComponent(String)} <br>
   * Then call this method to filter and select stream with given name <br>
   *
   * @author LTU7HC
   *
   * @param streamName - name of stream want to filter and select.
   */
  public void filterAndSelectStream(final String streamName) {
    this.driverCustom.getPresenceOfWebElement("(//input[@placeholder='Filter by name'])[1]").clear();
    this.driverCustom.getPresenceOfWebElement("(//input[@placeholder='Filter by name'])[1]")
        .sendKeys(streamName);
    // Wait for loading spinner to disappear
    this.driverCustom.isElementInvisible("//span[@dojoattachpoint='_spinner'][@style='']",  Duration.ofSeconds(5));
    Boolean execute = clickOnLink(streamName);
    if(Boolean.FALSE.equals(execute)) {
      throw new WebAutomationException("Can not click on link: " + streamName);
    }
    LOGGER.LOG.info(String.format("Clicked on stream: '%s'", streamName));
    // Wait some time to display configurations on page.
    this.driverCustom.isElementInvisible("//span[text()='Loading...']",  Duration.ofSeconds(5));
  }

}
