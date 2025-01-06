/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractGCPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.text.Text;

/**
 * This page represents BrowseComponents Page.
 */
public class BrowseComponentsPage extends AbstractGCPage {

  /**
   * @param driverCustom driver.
   */
  public BrowseComponentsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * Clicks on create baseline button appears on top right side of the page.
   */
  public void clickOnCreateComponentButton() {
    this.driverCustom.getFirstVisibleWebElement("//a[@class='button']/span[text()='Create Component...']", null)
        .click();
    LOGGER.LOG.info("Clicked on Create componenet button.");
  }

  /**
   * Creates component by setting value to "Name" , "Description".
   *
   * @param componentName name of the component.
   * @param componentDescription description of component.
   * @param existingTag name of the existing tag.
   */
  public void createComponent(final String componentName, final String componentDescription, final String existingTag) {
    Dialog dlgCreateBaseline =
        this.engine.findElement(Criteria.isDialog().withTitle("Create Component")).getFirstElement();
    Text txtName =
        this.engine.findElement(Criteria.isTextField().hasLabel("Component Name:").inContainer(dlgCreateBaseline))
            .getFirstElement();
    txtName.clearText();
    txtName.setText(componentName);
    LOGGER.LOG.info("Input configuration name is " + componentName);
    Text txtDescription = this.engine
        .findElement(Criteria.isTextField().isMultiLine().hasLabel("Description:").inContainer(dlgCreateBaseline))
        .getFirstElement();
    txtDescription.setText(componentDescription);
    LOGGER.LOG.info("Componnet description is " + componentDescription);
    waitForSecs(2);
    Button btn =
        this.engine.findElement(Criteria.isButton().withText("OK").inContainer(dlgCreateBaseline)).getFirstElement();
    btn.click();
    LOGGER.LOG.info("Clicked on ok button.");
    waitForSecs(15);// After clicking on ok button it takes time to create and close the dialog completely.
  }
}