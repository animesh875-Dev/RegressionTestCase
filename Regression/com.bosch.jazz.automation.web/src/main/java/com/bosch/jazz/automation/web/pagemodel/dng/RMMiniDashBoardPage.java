/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractJazzWebPage;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.text.TextField;
import com.bosch.psec.web.test.finder.RadioButtonFinder;
import com.bosch.psec.web.test.finder.container.RowFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.JazzTextFinder;

/**
 * Represents the Requirement Management DashBoard Page.
 */
public class RMMiniDashBoardPage extends AbstractRMPage {

  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom required for interacting with the browser.
   */
  public RMMiniDashBoardPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzButtonFinder(), new JazzDropdownFinder(), new RowCellFinder(),
        new JazzDialogFinder(), new JazzRadioButtonFinder(), new JazzTextFinder(),
        new JazzRowFinder(this.driverCustom.getWebDriver()), new LinkFinder(), new RowFinder(),
        new RadioButtonFinder());
  }

  /**
   * <p>
   * After opening the Mini Dashboard using ${@link RMArtifactsPage#miniDashboardOpen()} then select expand the parant
   * item in Module Explorer widget by the section number
   * <p>
   *
   * @author NVV1HC
   * @param name number of section we want to expand,e.g: to expand the parent item with section number is 2.3
   */
  public void expandArtifactInModuleExplorer(final String name) {
    waitForSecs(3);
    try {
      this.driverCustom.switchToFrame(RMConstants.MINIDASHBOARDPAGE_IFRAME_MODULEEXPLORER_WIDGET_XPATH);
    }
    catch (Exception e) {}
    try {
      this.driverCustom.isElementClickable(RMConstants.MINIDASHBOARDPAGE_ARTIFACT_INMODULEEXPLORER_XPATH,  Duration.ofSeconds(60),
          name.trim());
      this.driverCustom.getWebElement(RMConstants.MINIDASHBOARDPAGE_ARTIFACT_INMODULEEXPLORER_XPATH, name.trim())
      .click();
      this.driverCustom.isElementClickable(RMConstants.MINIDASHBOARDPAGE_EXPANDBUTTON_INMODULEEXPLORER_XPATH,  Duration.ofSeconds(60), name);
      this.driverCustom.getWebElement(RMConstants.MINIDASHBOARDPAGE_EXPANDBUTTON_INMODULEEXPLORER_XPATH, name).click();
    }
    catch (Exception e) {
      LOGGER.LOG.warn("This item is already expanded!");
    }
    waitForSecs(3);
  }


  /**
   * <p>
   * After open Mini Dashboard using ${AbstractRMPage{@link AbstractJazzWebPage#miniDashboardOpen()} then select one
   * chapter in Module Explorer
   * <p>
   *
   * @author NVV1HC
   * @param name name of Heading artifact to be selected
   *          iframe at the beginning of this method
   * @return return artifact ID of heading artifact is selected
   */
  public String selectArtifactInModuleExplorer(final String name) {
    waitForSecs(5);
    try {
      this.driverCustom.switchToFrame(RMConstants.MINIDASHBOARDPAGE_IFRAME_MODULEEXPLORER_WIDGET_XPATH);
    }
    catch (Exception e) {}
    this.driverCustom.isElementClickable(RMConstants.MINIDASHBOARDPAGE_ARTIFACT_INMODULEEXPLORER_XPATH, this.timeInSecs,
        name);
    WebElement artifact =
        this.driverCustom.getWebElement(RMConstants.MINIDASHBOARDPAGE_ARTIFACT_INMODULEEXPLORER_XPATH, name);
    String artifactID = artifact.getAttribute("id");
    artifact.click();
    this.driverCustom.switchToDefaultContent();
    return artifactID;
  }

  /**
   * <p>
   * Verify that the added widget is displayed in Mini Dashboard after ${@link AbstractJazzWebPage#miniDashboardOpen()}
   * <p>
   *
   * @author NVV1HC
   * @param widgetName name of widget is verified that is displayed or not
   * @return passed verification message if the widget is displayed or vice versa
   */
  public boolean isWidgetDisplayedInMiniDashboard(final String widgetName) {
    waitForSecs(5);
    boolean isWidgetDisplayed =
        this.driverCustom.isElementVisible(RMConstants.MINIDASHBOARDPAGE_WIDGET_XPATH, this.timeInSecs, widgetName);
    if (isWidgetDisplayed) {
      return true;
    }
    return false;
  }


  /**
   * <p>
   * <p>
   *
   * @author NVV1HC
   * @param params store pair of key and value as: <br>
   *          <widgetName>:'Module Explorer' <br>
   *          Note that in normal case, just need to input the widgetName is enough.<br>
   *          In a special case, the widget name is "About Me", one more parameter need to be declared is: <br>
   *          <userName>: 'CDG ALM Tester system-user-CC (XC-ECO/ESI1)'
   */
  public void addWidget(final Map<String, String> params) {
    String widgetName = params.get("widgetName");
    this.driverCustom.getWebElement(RMConstants.RMMINIDASHBOARDPAGE_ADDWIDGETBUTTON_XPATH).click();
    //Wait for 'Add Widget' popup displays.
    String widgetPopupTitle = "//div[text()='Add Widget']/ancestor::div[@widgetid]";
    this.driverCustom.isElementVisible(widgetPopupTitle,  Duration.ofSeconds(15));
    Dialog dialog = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Add Widget"), this.timeInSecs)
        .getFirstElement();
    TextField txFiltertBox = this.engine.findFirstElementWithDuration(
        Criteria.isTextField().withAriaLabel("Search text").inContainer(dialog), this.timeInSecs);
    txFiltertBox.clearText();
    txFiltertBox.setText(widgetName);
    waitForSecs(5);
    Button buttonAddWidget =
        this.engine.findElement(Criteria.isButton().withText("Add Widget").inContainer(dialog)).getFirstElement();
    buttonAddWidget.click();
    this.driverCustom.switchToFrame(RMConstants.MINIDASHBOARDPAGE_IFRAME_MODULEEXPLORER_WIDGET_XPATH);
    waitForSecs(2);
    this.driverCustom.isElementInvisible(RMConstants.RMARTIFACTPAGE_LOADINGIMG_XPATH, this.timeInSecs);
    this.driverCustom.switchToDefaultContent();
    this.driverCustom.getWebElement(RMConstants.RMMINIDASHBOARDPAGE_CLOSEBUTTON_OFADDWIDGETDIALOG_XPATH).click();
    waitForSecs(5);
  }

  /**
   * <p>
   * This method is to remove a widget displayed in Mini dashboard
   * <p>
   *
   * @author NVV1HC {@inheritDoc}
   */
  @SuppressWarnings("javadoc")
  @Override
  public void removeWidget(final String widgetName) {
    waitForSecs(3);
    this.driverCustom.getWebElement(RMConstants.RMMINIDASHBOARDPAGE_REMOVEWIDGETBUTTON_XPATH, widgetName).click();
    this.driverCustom.getWebDriver().switchTo().alert().accept();
    LOGGER.LOG.info(widgetName + "is removed properly.");
    waitForSecs(2);
  }
}
