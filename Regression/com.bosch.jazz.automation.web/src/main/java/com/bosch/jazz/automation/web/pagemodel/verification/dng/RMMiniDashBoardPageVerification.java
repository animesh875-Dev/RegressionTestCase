/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.dng;

import java.time.Duration;
import java.util.Map;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMMiniDashBoardPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;

/**
 * @author hrt5kor
 */
public class RMMiniDashBoardPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom must not be null.
   */
  public RMMiniDashBoardPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * <p>
   * Verify widget is added successfully after ${@link RMMiniDashBoardPage#expandArtifactInModuleExplorer(String)}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link RMMiniDashBoardPage#expandArtifactInModuleExplorer(String)}
   * @param name artifact name is expanded
   * @param additionalParam - expected children artifact should be displayed, you should input name of the
   *          child artifact here
   * @return verification message
   */
  public TestAcceptanceMessage verifyExpandArtifactInModuleExplorer(final String name, final String additionalParam, final String lastResult) {
    waitForSecs(5);
    String[] parentAndChildrenArtifact = { name, additionalParam };
    try {
      this.driverCustom.switchToFrame(RMConstants.MINIDASHBOARDPAGE_IFRAME_MODULEEXPLORER_WIDGET_XPATH);
    }
    catch (Exception e) {
      // Already in frame
    }
    boolean result =
        this.driverCustom.isElementVisible(RMConstants.MINIDASHBOARDPAGE_CHILDRENARTIFACT_OFANARTIFACT_XPATH,
            Duration.ofSeconds(60), parentAndChildrenArtifact);
    this.driverCustom.switchToDefaultContent();
    if (result) {
      return new TestAcceptanceMessage(true,
          "Verify the parent artifact '" + name +
          "' is expanded successfully.\nThe expected children artifact with content '" +
          additionalParam + "' is displayed as expectation!");
    }
    return new TestAcceptanceMessage(false,
        "Verify the parent artifact '" + name +
        "' is expanded but failed.\nThe expected children artifact with content '" +
        additionalParam + "' is not displayed as expectation!");
  }

  /**
   * <p>
   * Verify widget is added successfully after ${@link AbstractRMPage#miniDashboardOpen()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link AbstractRMPage#miniDashboardOpen()}
   * @param widgetName name of widget
   * @param additionalParam true if you want to check the widget is displayed or vice versa
   * @return verification message
   */
  public TestAcceptanceMessage verifyIsWidgetDisplayedInMiniDashboard(final String widgetName,
      final String additionalParam, final String lastResult) {
    waitForSecs(5);
    if (lastResult.equalsIgnoreCase(additionalParam)) {
      if (Boolean.parseBoolean(lastResult)) {
        return new TestAcceptanceMessage(true, "Widget '" + widgetName + "' is displayed as expectation!");
      }
      return new TestAcceptanceMessage(true, "Widget '" + widgetName + "' is not displayed as expectation!");
    }
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(false, "Expected result: widget '" + widgetName +
          "' is not displayed!\nActual result: widget '" + widgetName + "' is displayed");
    }
    return new TestAcceptanceMessage(false, "Expected result: widget '" + widgetName +
        "' is displayed!\nActual result: widget '" + widgetName + "' is not displayed");
  }

  /**
   * <p>
   * Verify widget is added successfully after ${@link RMMiniDashBoardPage#selectArtifactInModuleExplorer(String)}
   * <p>
   *
   * @author NVV1HC
   * @param name name of the artifact is selected
   * @param lastResult result from ${@link RMMiniDashBoardPage#selectArtifactInModuleExplorer(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifySelectArtifactInModuleExplorer(final String name, final String lastResult) {
    waitForSecs(5);
    String[] artifactIDAndName = { lastResult, name };
    boolean result = this.driverCustom.isElementVisible(
        RMConstants.MINIDASHBOARDPAGE_VERIFY_ARTIFACTISSELECTED_INMODULE,  Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)), artifactIDAndName);
    if (result) {
      return new TestAcceptanceMessage(true, "Verify heading artifact '" + name + "' is selected successfully!");
    }
    return new TestAcceptanceMessage(false, "Verify heading artifact '" + name +
        "' is selected failed!\nExpected result: After selecting the heading artifact '" + name +
        "' in the Module Explorer widget, this artifact is selected in the module also.\nActual: This artifact is not selected in the module");
  }

  /**
   * <p>
   * This method is called after executing ${@link RMMiniDashBoardPage#addWidget(Map)}}
   * <p>
   *
   * @author NVV1HC
   * @param params store pair of key and value as: <br>
   *          <widgetName>:'Module Explorer' <br>
   *          Note that in normal case, just need to input the widgetName is enough.<br>
   *          In a special case, the widget name is "About Me", one more parameter need to be declared is: <br>
   *          <userName>: 'CDG ALM Tester system-user-CC (XC-ECO/ESI1)'
   * @param lastResult result from the ${@link RMMiniDashBoardPage#addWidget(Map)}}
   * @return verification message
   */
  public TestAcceptanceMessage verifyAddWidget(final Map<String, String> params, final String lastResult) {
    waitForSecs(3);
    boolean result = false;
    String widgetName = params.get("widgetName");
    String nameOfWidgetDisplayed = this.driverCustom
        .getWebElement(RMConstants.RMMINIDASHBOARDPAGE_NAMEOFTHEFIRSTWIDGET_XPATH).getAttribute("innerText");
    if (widgetName.equals("About Me")) {
      result = nameOfWidgetDisplayed.equals(params.get("userName"));
      return new TestAcceptanceMessage(result, "Verify adding 'About Me' widget: " + result);
    }
    result = widgetName.equals(nameOfWidgetDisplayed);
    if (result) {
      return new TestAcceptanceMessage(result, "Verify adding widget '" + widgetName + "': PASSED!");
    }
    return new TestAcceptanceMessage(false,
        "Verify adding widget '" + widgetName + "': FAILED!\nExpected widget name is: '" + widgetName +
        "'.\nActual widget name displayed: '" + nameOfWidgetDisplayed + "'");
  }


  /**
   * <p>
   * This method is called after executing ${@link RMMiniDashBoardPage#removeWidget(String)}}
   * <p>
   *
   * @author NVV1HC {@inheritDoc}
   */
  @Override
  @SuppressWarnings("javadoc")
  public TestAcceptanceMessage verifyRemoveWidget(final String widgetName, final String lastResult) {
    waitForSecs(5);
    boolean result =
        this.driverCustom.isElementInvisible(RMConstants.MINIDASHBOARDPAGE_WIDGET_XPATH, this.timeInSecs, widgetName);
    if (result) {
      return new TestAcceptanceMessage(true,
          "Widget '" + widgetName + "' is removed from Mini Dashboard successfully!");
    }
    return new TestAcceptanceMessage(false, "Widget '" + widgetName + "' is not removed from Mini Dashboard!");
  }
}
