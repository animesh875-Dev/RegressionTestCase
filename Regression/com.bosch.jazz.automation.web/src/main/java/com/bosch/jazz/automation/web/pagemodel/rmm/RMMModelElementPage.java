/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rmm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMMPage;
import com.bosch.psec.web.test.finder.ButtonFinder;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.ListboxFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.container.treeNode.JazzTreeNodeFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.JazzTextFinder;
import finder.text.label.JazzSpanLabelFinder;
import finder.text.textField.JazzSpanTextFieldFinder;

/**
 * @author HLB1KOR Represents the Architecture Management Model element Page
 */
public class RMMModelElementPage extends AbstractRMMPage {

  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom required for interacting with the browser.
   */
  public RMMModelElementPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTreeNodeFinder(), new JazzDropdownFinder(), new JazzButtonFinder(),
        new JazzTextFinder(), new JazzSpanLabelFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new JazzDialogFinder(), new RowCellFinder(), new LinkFinder(), new JazzSpanTextFieldFinder(), new TextFinder(),
        new JazzTabFinder(), new ListboxFinder(), new CheckboxFinder(), new ButtonFinder(),
        new JazzRadioButtonFinder());
  }

  /**
   * <p>
   * Open a model element.<br>
   * Select a view.<br>
   * Check for the column content in Table view under Architecture element
   *
   * @return true if the content data does not contain spectioal character else it returns false.
   */
  public boolean validateTableContent() {

    Optional<WebElement> viewTableMatched = this.driverCustom
        .getWebElements("//div[@id='com_ibm_team_rmm_web_ui_internal_widgets_SelectableStyledTable_0']").stream()
        .findFirst();
    WebElement viewTable = viewTableMatched.isPresent() ? viewTableMatched.get() : null;
    if (viewTable == null) {
      throw new WebAutomationException("View table is empty.");
    }

    List<WebElement> firstRowContect = viewTable.findElements(By.xpath(
        "//div[@id='com_ibm_team_rmm_web_ui_internal_widgets_SelectableStyledTable_0']//table[@class='jazz-ui-StyledTable']//tbody[@class='table-hover']"));

    List<String> contentList = new ArrayList<>();
    firstRowContect.stream().forEach(x -> contentList.add(x.getText()));
    LOGGER.LOG.info("Table contains the value");

    if (contentList.stream().anyMatch(x -> x.contains("%EOL%"))) {
      throw new WebAutomationException("Table contains special character in content");
    }
    return true;
  }

  /**
   * <p>
   * Open a model element.<br>
   * Select a view.<br>
   * Check for the column content in Table view under Architecture element
   *
   * @return true if the content data does not contain spectioal character else it returns false.
   */
  public boolean validateMatrixViewPresence() {

    Optional<WebElement> viewTableMatched =
        this.driverCustom.getWebElements("//div[@id='com_ibm_team_rmm_web_ui_internal_matrixes_MatrixPageContent_1']")
            .stream().findFirst();
    WebElement viewTable = viewTableMatched.isPresent() ? viewTableMatched.get() : null;
    if (viewTable == null) {
      throw new WebAutomationException("View table is empty.");
    }

    List<WebElement> firstRowContect = viewTable.findElements(By.xpath(
        "//div[@id='com_ibm_team_rmm_web_ui_internal_widgets_SelectableStyledTable_0']//table[@class='jazz-ui-StyledTable']//tbody[@class='table-hover']"));
    List<String> contentList = new ArrayList<>();
    firstRowContect.stream().forEach(x -> contentList.add(x.getText()));
    LOGGER.LOG.info("Table contains the value");

    if (contentList.isEmpty()) {
      throw new WebAutomationException("MatrixView does not contain any data");
    }
    return true;

  }
}
