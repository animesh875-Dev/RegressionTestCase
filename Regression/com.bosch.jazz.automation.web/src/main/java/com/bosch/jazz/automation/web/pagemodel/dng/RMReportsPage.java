/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.JazzTextFinder;
import finder.text.textField.JazzSpanTextFieldFinder;

/**
 * Represents the Requirements Management Reports Page.
 */
public class RMReportsPage extends AbstractRMPage {

  /**
   * Constructor setting the {@link WebDriverCustom}.
   *
   * @param driverCustom required for interacting with the browser.
   */
  public RMReportsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDropdownFinder(), new JazzButtonFinder(), new JazzTextFinder(),
        new JazzDialogFinder(), new RowCellFinder(), new LinkFinder(), new JazzSpanTextFieldFinder(), new TextFinder());
  }


  /**
   * <p>
   * Open Reports menu using {@link RMDashBoardPage#openMenu(String)}, 'Reports' page is displayed.<br>
   * Click on 'Generate a report' link.
   */
  public void clickOnGenerateReport() {
    waitForPageLoaded();
    this.driverCustom.click(RMConstants.RMREPORTSPAGE_CLICKTHEGENERATEREPORT_XPATH);
  }

  /**
   * <p>
   * Open Reports menu using {@link RMDashBoardPage#openMenu(String)}, 'Reports' page is displayed.<br>
   * Click on 'Generate a report' link using {@link RMReportsPage#clickOnGenerateReport()},'Create a Report' dialog is
   * displayed.<br>
   * Select the report type from 'Select the Report Type' section.<br>
   * Click on 'Next >' button.<br>
   * Provide report name in 'Report Information' section.<br>
   * Click on 'Finish' button.<br>
   * Verify 'Report generation complete' successful message.<br>
   * Click on 'Close' button.
   *
   * @param reportType type of report.
   * @return created report name
   */
  public String generateReport(final String reportType) {
    waitForPageLoaded();
    Dialog dlgCreateAReport =
        this.engine.findElement(Criteria.isDialog().withTitle("Create a Report")).getFirstElement();
    Label lblReportType = this.engine.findElement(Criteria.isLabel().withText(reportType).inContainer(dlgCreateAReport))
        .getFirstElement();
    lblReportType.click();
    clickOnJazzButtons(RMConstants.NEXT);
    clickOnJazzButtons(RMConstants.NEXT);
    String reportName = "Report_" + DateUtil.getCurrentDateAndTime();
    this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement()).sendKeys(reportName);
    clickOnJazzButtons(RMConstants.NEXT);
    clickOnJazzButtons(RMConstants.NEXT);
    clickOnJazzButtons(RMConstants.FINISHBUTTON);
    Label messageComplete =
        this.engine.findElement(Criteria.isLabel().withText("Report generation complete")).getFirstElement();
    if (messageComplete != null) {
      // Click on Close button
      Button btnClose = this.engine.findElement(Criteria.isButton().withText("Close")).getFirstElement();
      btnClose.click();
    }
    return reportName;
  }

  /**
   * <p>
   * Open Reports menu using {@link RMDashBoardPage#openMenu(String)}, 'Reports' page is displayed.<br>
   * Click on 'Generate a report' link using {@link RMReportsPage#clickOnGenerateReport()},'Create a Report' dialog is
   * displayed.<br>
   * Create a report using {@link RMReportsPage#generateReport(String)}.<br>
   * Verify displayed successful message while generating report.
   *
   * @return status test of report generation.
   */
  public String getReportGenerationStatusText() {
    waitForPageLoaded();
    return this.driverCustom.getText(RMConstants.RMARTIFACTPAGE_REPORTGENMSG_LABEL_XPATH);
  }

  /**
   * WaitForPageLoaded method is used to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Reports");
  }

}