package com.bosch.jazz.automation.web.pagemodel.verification.dng;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMCollectionsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
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
 * @author BBC1KOR
 *
 */
public class RMCollectionsPageVerification extends AbstractRMPage {

  /**
   * @param driverCustom must not be null.
   */
  public RMCollectionsPageVerification(WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTreeNodeFinder(), new JazzDropdownFinder(), new JazzButtonFinder(),
        new JazzTextFinder(), new JazzSpanLabelFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new JazzDialogFinder(), new RowCellFinder(), new LinkFinder(), new JazzSpanTextFieldFinder(), new TextFinder(),
        new JazzTabFinder(), new ListboxFinder(), new CheckboxFinder(), new ButtonFinder(),
        new JazzRadioButtonFinder());
  }
  /**
   * <p> 
   * Verify the selected option from 'Create' drop down in Collections page.
   * <p>
   * This method called after {@link RMCollectionsPage#selectOptionFromCreateCollectionDropdown(String)}.
   * 
   * @param option : which selected from drop down.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectOptionFromCreateCollectionDropdown(final String option, final String lastResult) {
    boolean result= this.driverCustom.isElementInvisible(RMConstants.RMARTIFACTSPAGE_SELECTREQUIREMENT_XPATH, Duration.ofSeconds(30), option);
     if(result) {
       return new TestAcceptanceMessage(true,"Select option from drop down successfully.- "+option);
     }
     return new TestAcceptanceMessage(false,"Select option from drop down fail.");
   }
  /**
   * <p>Verify Collection is created.
   * <br>This method called after {@link RMCollectionsPage#createCollection(Map)}.
   * 
   * @param additionalParams key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyCreateCollection(final Map<String, String> additionalParams, final String lastResult) {
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[@class='icon-resourcelink']//a[text()='" + additionalParams.get("COLLECTION_NAME") + "']")));
      return new TestAcceptanceMessage(true,
          "Collection with name '" + additionalParams.get("COLLECTION_NAME") + "' is created successfully.");
    }
    catch (Exception e) {}
    return new TestAcceptanceMessage(false,
        "Collection with name '" + additionalParams.get("COLLECTION_NAME") + "' is not created successfully.");
  }
}