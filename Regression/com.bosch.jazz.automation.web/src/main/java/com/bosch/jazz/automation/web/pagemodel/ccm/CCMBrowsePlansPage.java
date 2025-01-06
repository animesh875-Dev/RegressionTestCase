/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.util.HashMap;
import java.util.Map;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;


/**
 * Represents the page for browsing/searching a plan
 */
public class CCMBrowsePlansPage extends AbstractCCMPage {

  private static final String URL_SUFFIX_MY_CURRENT_PLANS = "#action=com.ibm.team.apt.search&predef=myCurrent";
  private static final String URL_SUFFIX_CURRENT_PLANS = "#action=com.ibm.team.apt.search&predef=current";
  private static final String URL_SUFFIX_ALL_PLANS = "#action=com.ibm.team.apt.search&predef=all";
  private static final String URL_SUFFIX = "URL_SUFFIX";

  /**
   * Constructor setting the {@link WebDriverCustom}
   * @param driverCustom required for interacting with the browser.
   */
  public CCMBrowsePlansPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * This is a generic method! Better take one of the below mentioned, otherwise you have to know how to fill the
   * 'additionalParams' properly.
   * <p>
   *
   * @see #openAllPlans(String, String)
   * @see #openCurrentPlans(String, String)
   * @see #openMyCurrentPlans(String, String)
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    String urlSuffix = additionalParams.get(URL_SUFFIX);
    this.driverCustom.openURL(getProjectAreaURL(repositoryURL, projectAreaName) + urlSuffix);
  }

  /**
   * Opens the "Browse Plans" page and shows "My Current Plans"
   *
   * @param repositoryURL the repository, must not be null
   * @param projectAreaName the project area name, must not be null
   */
  public void openMyCurrentPlans(final String repositoryURL, final String projectAreaName) {
    waitForPageLoaded();
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(URL_SUFFIX, URL_SUFFIX_MY_CURRENT_PLANS);
    open(repositoryURL, projectAreaName, additionalParams);
  }

  /**
   * Opens the "Browse Plans" page and shows "Current Plans"
   *
   * @param repositoryURL the repository, must not be null
   * @param projectAreaName the project area name, must not be null
   */
  public void openCurrentPlans(final String repositoryURL, final String projectAreaName) {
    waitForPageLoaded();
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(URL_SUFFIX, URL_SUFFIX_CURRENT_PLANS);
    open(repositoryURL, projectAreaName, additionalParams);
  }

  /**
   * Opens the "Browse Plans" page and shows "All Plans"
   *
   * @param repositoryURL the repository, must not be null
   * @param projectAreaName the project area name, must not be null
   */
  public void openAllPlans(final String repositoryURL, final String projectAreaName) {
    waitForPageLoaded();
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(URL_SUFFIX, URL_SUFFIX_ALL_PLANS);
    open(repositoryURL, projectAreaName, additionalParams);
  }

  /**
   * This method wait for the presence of Plans link in plan page.
   */
  @Override
  public void waitForPageLoaded() {

    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPLANPAGE_PLANS_LINK_XPATH);
  }

  /**
   * Enters in the search box the given plan name and triggers the search
   *
   * @param planName the name of the plan to enter in the search field public void searchPlan(final String planName) {
   *          this.driverCustom.click("CCMPlansPage.SearchPaln.MenuItem_xpath"); if
   *          (this.driverCustom.isElementVisible("CCMPlansPage.AllPlans.Link_xpath", 5)) {
   *          this.driverCustom.click("CCMPlansPage.AllPlans.Link_xpath"); }
   *          this.driverCustom.typeText("CCMPlansPage.SearchTextbox.TextArea_xpath", planName);
   *          this.driverCustom.pressKey("CCMPlansPage.SearchTextbox.TextArea_xpath", Keys.ENTER); }
   */
}
