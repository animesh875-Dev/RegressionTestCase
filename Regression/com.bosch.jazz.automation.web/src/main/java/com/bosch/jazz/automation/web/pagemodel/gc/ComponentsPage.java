/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc;

import java.time.Duration;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractGCPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.text.TextField;

/**
 * This page represents Components Page.
 *
 */
public class ComponentsPage extends AbstractGCPage {

  /**
   * @param driverCustom driver.
   */
  public ComponentsPage(WebDriverCustom driverCustom) {
    super(driverCustom);
  }
  /**
   * @param name name of the Tag.
   */
  public void filterComponent(final String name)
  {
    TextField tfd=this.engine.findElement(Criteria.isTextField().withPlaceHolder("Filter by name and tags")).getFirstElement();
    tfd.clearText();
    tfd.setText(name);
    this.driverCustom.isElementVisible("//span[@dojoattachpoint='_spinner'][@style='']",  Duration.ofSeconds(5)); // Wait some time to display components on page.
  }
  
  /**
   * <p>
   * Click on link 'Browse and create components' using {@link GCDashBoardPage#clickOnLink(String)}, loads all components for the GC project. <br>
   * Then call this method to filter and select component with given name <br>
   *
   * @author LTU7HC
   *
   * @param componentName - name of component want to filter and select.
   */
  public void filterAndSelectComponent(final String componentName) {
    filterComponent(componentName);
    Boolean execute = clickOnLink(componentName);
    if(Boolean.FALSE.equals(execute)) {
      throw new WebAutomationException("Can not click on link: " + componentName);
    }
    LOGGER.LOG.info(String.format("Clicked on component: '%s'", componentName));
  }
  
}
