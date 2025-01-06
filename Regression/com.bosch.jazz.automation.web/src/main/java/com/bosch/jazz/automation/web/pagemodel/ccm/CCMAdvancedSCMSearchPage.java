package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.ByType;
import com.bosch.jazz.automation.web.common.constants.SourceControlEnums;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.text.LabelFinder;

import finder.button.JazzButtonFinder;
import finder.text.label.JazzLabelFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * Represents the advanced scm search page in RTC.
 */
public class CCMAdvancedSCMSearchPage extends AbstractCCMPage {

  /**
   * Constructor setting the {@link WebDriverCustom}
   * @param driverCustom required for interacting with the browser.
   */
  public CCMAdvancedSCMSearchPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzLabelFinder(), new LabelFinder(), new JazzTextFieldFinder(), new JazzButtonFinder());
  }

  /**
   * @param artifactType See {@link SourceControlEnums}
   * @param artifact name to be searched in the repository.
   */
  public void selectArtifactTypeAndSearchArtifact(final String artifactType, final String artifact) {
    waitForPageLoaded();
    Label txtAtrifactTypeAndSearchArtifact =
        this.engine.findElement(Criteria.isLabel().withText(artifactType)).getFirstElement();
    txtAtrifactTypeAndSearchArtifact.click();
    searchForSCMArtifact(artifact);
    this.driverCustom.waitForPageLoaded("Change and Configuration Management");

  }

  /**
   * @return list of artifacts shown on the page after advance search.
   */
  public List<String> getListOfArtifacts() {
    waitForPageLoaded();
    if (!this.driverCustom.isElementPresent(CCMConstants.CCMSOURCECONTROLPAGE_ADVANCEDSEARCH_LISTOFARTIFACTS_XPATH,
        Duration.ofSeconds(5))) {
      return new ArrayList<>();
    }
    return this.driverCustom.getWebElementsText(CCMConstants.CCMSOURCECONTROLPAGE_ADVANCEDSEARCH_LISTOFARTIFACTS_XPATH);
  }

  /**
   * Enters the given artifact prefix (since all artifacts starting with that prefix will be returned by the search) and
   * then clicks on the search button. Finally waits until the search is finished and the results are shown on the
   * screen.
   *
   * @param artifactNamePrefix the prefix of the name of the artifacts to search for, must not be null
   */
  private void searchForSCMArtifact(final String artifactNamePrefix) {
    waitForPageLoaded();
    Text txtQueryName = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Name begins with:"));
    txtQueryName.click();
    txtQueryName.setText(artifactNamePrefix);
    Button btnSave = this.engine.findElement(Criteria.isButton().withText("Search")).getFirstElement();
    btnSave.click();
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    this.driverCustom
        .openURL(getProjectAreaURL(repositoryURL, projectAreaName) + "#action=com.ibm.team.scm.advancedSearch");
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebDriverSetup().getConfigurationForExplicitWaitTime());
    String string = "Advanced search";
    By locator = this.driverCustom.createLocator("//span[contains(.,'" + string + "')]", ByType.XPATH);
    ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOfElementLocated(locator);
    wait.until(condition);
    waitForPageLoaded();
    // Cannot use the title of the page to verify that the page is properly loaded.
    // It seems the page title is not set properly if just the URL of the advanced search is opened again.
    // Might be a caching problem. Only if refreshing the URL in the browser using F5 the correct title is appearing
    // again.
    // But this cannot be done or should not be done using Selenium
  }
  /*
   * @inheritDoc
   */
  @Override
  public void waitForPageLoaded() {
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebDriverSetup().getConfigurationForExplicitWaitTime());
    String string = "Source Control";
    By locator = this.driverCustom
        .createLocator("//a[contains(@class , 'j-action-secondary') and text()='" + string + "']", ByType.XPATH);
    ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOfElementLocated(locator);
    wait.until(condition);
  }
}
