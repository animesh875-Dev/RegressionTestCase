/*
 * d * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.AbstractJazzWebPage;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;
import com.bosch.jazz.automation.web.pagemodel.AbstractWebPage;
import com.bosch.jazz.automation.web.pagemodel.PagemodelConstants;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMArtifactsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMDashBoardPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMManageComponentsAndConfigurations;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.finder.container.DialogFinder;
import com.bosch.psec.web.test.finder.text.LabelFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.container.tab.JazzTabFinder;
import finder.text.label.JazzLabelFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * @author hrt5kor
 */
public class AbstractWebPageVerification extends AbstractJazzWebPage {

  /**
   * @param driverCustom must not be null.
   */
  public AbstractWebPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new LabelFinder(), new JazzLabelFinder(), new TextFieldFinder(), new JazzTextFieldFinder(),
        new LinkFinder(), new JazzTabFinder(), new DialogFinder());
  }
  

  /**
   * Verifies whether data exists in server by searching in the search box.
   * This method called after {@link RMDashBoardPage#quickSearchFilterByProject(String, String)}
   * @author VDY1HC
   * @param searchText - element to be searched.
   * @param projectName - Name of project or / All Projects
   * @param additionalParams value to be pass in Excel Test Script.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyQuickSearchFilterByProject(final String searchText, final String projectName, final String additionalParams,
      final String lastResult) {
    String scope = this.driverCustom.getPresenceOfWebElement("//span[@class='scope-text']").getText();
   if (!projectName.equalsIgnoreCase(scope)) {
     return new TestAcceptanceMessage(false, "Verified: FAILED - Unable to select project scope");
   }
   return verifyQuickSearchWithFirstAndExactResult(searchText,additionalParams,lastResult);
  }
  
  private TestAcceptanceMessage verifyQuickSearchWithFirstAndExactResult (final String name, final String additionalParams, final String lastResult) {
    Boolean isFound = false;
    String resultWithExactMatch = "";
    String scope = this.driverCustom.getPresenceOfWebElement("//span[@class='scope-text']").getText();
    WebElement searchResult = this.driverCustom.getPresenceOfWebElement(RMConstants.QUICK_SEARCH_BOX_RESULT_XPATH);
    String resultAfterSerarch = searchResult.getText();
    if (resultAfterSerarch.startsWith("No ")) {
      return new TestAcceptanceMessage(!Boolean.valueOf(additionalParams),
          "Verified: No results displayed with scope: '" + scope + "'." +
          "\nActual result: No item found for \"" + name + "\" searched in quick search text box.");
    }
    List<WebElement> matchsFound = this.driverCustom.getWebElements("(//div[@class='jazz-ui-BannerSearchArea']//div[@class='search-result'])");
    for (int i = 1; i <= matchsFound.size(); i++) {
      String textDisplayed = this.driverCustom.getWebElement("(//div[@class='jazz-ui-BannerSearchArea']//div[@class='search-result'])[" + i + "]//span").getText();
      if (textDisplayed.toLowerCase().contains(name.toLowerCase())) {
          isFound = true;
          resultWithExactMatch = textDisplayed;
          break;
        }
      } 
    Boolean finalResult = (Boolean.parseBoolean(additionalParams)==isFound);
    if (isFound) {
      return new TestAcceptanceMessage(finalResult,
          "Verified: Found first result with exact match - " + resultWithExactMatch + " - with scope: '" + scope + "'." +
          "\nSearch text: " + name +
          "\nExected to be found: " + additionalParams);
    }
    return new TestAcceptanceMessage(finalResult,
        "Verified: NOT Found result with exact match with scope: '" + scope + "'." +
        "\nSearch text: " + name +
        "\nExected to be found: " + additionalParams);
  }
  
  /**
   * <P>
   * Verifies whether data exists in server by searching in the search box.
   * <P>
   * This method called after {@link RMDashBoardPage#quickSearch(String)}
   *
   * @param name value to search in search box.
   * @param additionalParams value to be pass in Excel Test Script.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyQuickSearch(final String name, final String additionalParams,
      final String lastResult) {
    waitForSecs(5);
    if (this.driverCustom.isElementVisible(RMConstants.QUICK_SEARCH_BOX_STATUS_XPATH, Duration.ofSeconds(10))) {
      if (this.driverCustom.isElementVisible(
          "//div[@class='search-area']//div[@class='status-info' and starts-with(text(),'No')]", Duration.ofSeconds(10))) {
        return new TestAcceptanceMessage(!Boolean.valueOf(additionalParams),
            "Verified: No results displayed.\nActual result: No item found for \"" + name +
            "\" searched in quick search text box.");
      }
    }

    List<String> results =
        this.driverCustom.getWebElements("//div[@class='search-area']//div[@class='results-area']//a").stream()
        .map(x -> x.getText()).collect(Collectors.toList());
    if ((results != null) && results.stream().anyMatch(s -> s.equals(name))) {
      return new TestAcceptanceMessage(Boolean.valueOf(additionalParams),
          "Verified: searched item found.\nActual result for searching item is - \"" + name +
          ".\" \n Results displayed after search are - " + results + RMConstants.EXPECTED_RESULT_IS + name + "\"");
    }
    if ((results != null) && results.stream().anyMatch(s -> s.contains(name))) {
      return new TestAcceptanceMessage(Boolean.valueOf(additionalParams),
          "Verified: Searched item found.\n Actual result for searching item is - \"" + name +
          ".\"\n Results displayed after search are  - " + results + "\"\nExpected result is \"" + name + "\"");
    }

    while (this.driverCustom.isElementVisible(
        "//div[@class='search-area']//div[@class='status-area']//a[@class='button _next' and @aria-disabled='false']",
        Duration.ofSeconds(10))) {
      this.driverCustom.getFirstVisibleWebElement(
          "//div[@class='search-area']//div[@class='status-area']//a[@class='button _next']", null).click();
      waitForSecs(2);
      results = this.driverCustom.getWebElements("//div[@class='search-area']//div[@class='results-area']//a").stream()
          .map(x -> x.getText()).collect(Collectors.toList());
      if ((results != null) && results.stream().anyMatch(s -> s.equals(name))) {
        return new TestAcceptanceMessage(Boolean.valueOf(additionalParams),
            "Verified: searched item found.\n Actual result for searching item is - \"" + name +
            ".\" \n Results displayed after search are - \"" + results + "\"\nExpected result is \"" + name + "\"");
      }
      if ((results != null) && results.stream().anyMatch(s -> s.contains(name))) {
        return new TestAcceptanceMessage(Boolean.valueOf(additionalParams),
            "Verified: searched item found.\n Actual result for searching item is - \"" + name +
            ".\"\n Results displayed after search are  - \"" + results + "\" \nExpected result is \"" + name +
            "\"");
      }

    }
    if ((results != null) && (results.size() > 0)) {
      return new TestAcceptanceMessage(Boolean.valueOf(additionalParams),
          "Verified: searched item(s) displayed related to pattern \"" + name + "\".\nActual results found for  \"" +
              name + "\" are \"" + results + "\" as expected.");
    }

    return new TestAcceptanceMessage(!Boolean.valueOf(additionalParams),
        "Verified displayed items shown from quick search.\n Actual results:  No item found for \"" + name +
        "\" value by searching from search box.");
  }

  /**
   * <P>
   * Verifies name or id of the artifact.
   * <P>
   * This method called after {@link RMDashBoardPage#openSearchedSpecification(String)}
   *
   * @param name of the searched artifact
   * @param lastResult returned value of method which is executed just before the method.
   * @return Acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenSearchedSpecification(final String name, final String lastResult) {
    if (this.driverCustom.isTitleContains(name, Duration.ofSeconds(20))) {
      return new TestAcceptanceMessage(true, "Verified by title of the opened specification.\nActual title is -\"" +
          this.driverCustom.getWebDriver().getTitle() + ".\"\nActual title contains Expected text \"" + name + ".\"");
    }
    return new TestAcceptanceMessage(false, "Verified by title of the opened specification.\nActual title is -\"" +
        this.driverCustom.getWebDriver().getTitle() + ".\" \nActual title not contain Expected text \"" + name + ".\"");
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#selectProjectArea(String)}.
   *
   * @param projectAreaName name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySelectProjectArea(final String projectAreaName, final String lastResult) {
    if (this.driverCustom.isTitleContains(RMConstants.PROJECT_DASHBOARD, Duration.ofSeconds(20))) {
      String verMsg = "Verified by title of page \n Actual title is -\"" + this.driverCustom.getCurrentPageTitle() +
          "\"\n Actual title contains expected text : " + '"' + "Project Dashboards:" + '"';
      String title =
          this.driverCustom.getFirstVisibleWebElement(RMConstants.RMALLPROJECTS_BANNER_TITLE_XPATH, null).getText();
      if (title.trim().startsWith(projectAreaName)) {
        String verMsg2 = "\n" + RMConstants.VERIFIED_BANNER_TITLE + title + RMConstants.CONTAINS_EXPECTED_TEXT +
            projectAreaName + ".\"";
        return new TestAcceptanceMessage(true, verMsg + verMsg2);
      }
    }
    return new TestAcceptanceMessage(false, "Project area name is not matched - " + projectAreaName);
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#openMainMenuByMenuTitle(String)}.
   *
   * @param menuTitle main menu to click
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenMainMenuByMenuTitle(final String menuTitle, final String lastResult) {
    switch (menuTitle) {
      case "Projects Dashboards":
        if (this.driverCustom.isElementVisible(RQMConstants.NODE_MENU_ITEM_XPATH, Duration.ofSeconds(5),
            "Browse Project and Team Dashboards")) {
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
              " Actual result is the 'Browse Project and Team Dashboards' node menu is displayed after clicking on '" +
              menuTitle + "' and expected is 'Browse Project and Team Dashboards' node menu should be displayed");
        }
        return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
            " Actual result is the 'Browse Project and Team Dashboards' node menu is NOT displayed after clicking on '" +
            menuTitle + "' and expected is 'Browse Project and Team Dashboards' node menu should be displayed");
      case "Requirements":
        if (this.driverCustom.isElementVisible(RQMConstants.MENU_ITEM_XPATH, Duration.ofSeconds(5), "Create Requirement")) {
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
              " Actual result is the 'Create Requirement' menu is displayed after clicking on '" + menuTitle +
              "' and expected is 'Create Requirement' menu should be displayed");
        }
        return new TestAcceptanceMessage(false,
            RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
            " Actual result is the 'Create Requirement' node menu is NOT displayed after clicking on '" +
            menuTitle + "' and expected is 'Create Requirement' node menu should be displayed");
      case "Planning":
        String[] dynamicValue1 = { RMConstants.BROWSE, "Test Plans" };
        if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_NAVIGATEBAR_DROPDOWN_XPATH, this.timeInSecs,
            dynamicValue1)) {
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
              "\nActual result is the 'Test Plans' menu under 'Browse' node menu is displayed after clicking on '" +
              menuTitle + "'\nExpected is the 'Test Plans' menu under 'Browse' section.");
        }
        return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
            "\nActual result is the 'Test Plans' menu under 'Browse' node menu is not displayed after clicking on '" +
            menuTitle + "'\nExpected is the 'Test Plans' menu under 'Browse' section..");
      case "Construction":
        String[] dynamicValue2 = { RMConstants.BROWSE, "Test Cases" };
        if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_NAVIGATEBAR_DROPDOWN_XPATH, Duration.ofSeconds(5), dynamicValue2)) {
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU + "\nThe '" + menuTitle + "' menu is open successfully.");
        }
        return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
            "\nThe '" + menuTitle + "' menu is NOT displayed after clicking on '" + menuTitle + "'");
      case "Lab Management":
        String[] dynamicValue3 = { RMConstants.BROWSE, "Test Environments" };
        if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_NAVIGATEBAR_DROPDOWN_XPATH, Duration.ofSeconds(5), dynamicValue3)) {
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
              "\nActual result is the 'Lab Resources' menu under 'Browse' node menu is displayed after clicking on '" +
              menuTitle + "'\nExpected is the 'Lab Resources' menu under 'Browse' section.");
        }
        return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
            "\nActual result is the 'Lab Resources' menu under 'Browse' node menu is NOT displayed after clicking on '" +
            menuTitle + "'\nExpected is the 'Lab Resources' menu under 'Browse' menu.");
      case "Builds":
        String[] dynamicValue4 = { RMConstants.BROWSE, "Build Records" };
        if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_NAVIGATEBAR_DROPDOWN_XPATH, Duration.ofSeconds(5), dynamicValue4)) {
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
              "\nActual result is the 'Build Records' menu under 'Browse' node menu is displayed after clicking on '" +
              menuTitle + "'\nExpected is the 'Build Records' menu under 'Browse' menu section.");
        }
        return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
            "\nActual result is the 'Build Records' menu under 'Browse' node menu is not displayed after clicking on '" +
            menuTitle + "'\nExpected is the 'Build Records' menu under 'Browse' node menu section.");
      case "Execution":
        String[] dynamicValue5 = { RMConstants.BROWSE, "Test Case Results" };
        if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_NAVIGATEBAR_DROPDOWN_XPATH, Duration.ofSeconds(5), dynamicValue5)) {
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
              " Actual result is the 'Test Case Results' menu under 'Browse' node menu is displayed after clicking on '" +
              menuTitle + "' and\nExpected is the 'Test Case Results' menu under 'Browse' node menu section.");
        }
        return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
            "\nActual result is the 'Test Case Results' menu under 'Browse' node menu is NOT displayed after clicking on '" +
            menuTitle + "'\nExpected is the 'Test Case Results' menu under 'Browse' menu.section.");
      case "Reports":
        String[] dynamicValue6 = { "Browse", "My Reports" };
        if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_NAVIGATEBAR_DROPDOWN_XPATH, Duration.ofSeconds(60), dynamicValue6)) {
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
              " Actual result is the 'My Reports' menu under 'Browse' node menu is displayed after clicking on '" +
              menuTitle + "' and expected is the 'My Reports' menu under 'Browse' node menu should be displayed");
        }
        return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DISPLAYED_MENU_AFTER_CLICK_ON_MAIN_MENU +
            " Actual result is the 'My Reports' menu under 'Browse' node menu is NOT displayed after clicking on '" +
            menuTitle + "' and expected is the 'My Reports' menu under 'Browse' node menu should be displayed");
      case "Change Requests":
        if (this.driverCustom.isElementVisible(RQMConstants.MENU_ITEM_XPATH, Duration.ofSeconds(5), "Work Items Home")) {
          return new TestAcceptanceMessage(true,
              "Verified displayed menu after click on main menu." +
                  " Actual result is the 'Work Items Home' menu is displayed after clicking on '" + menuTitle +
              "' and expected is the 'Work Items Home' menu should be displayed");
        }
        return new TestAcceptanceMessage(false,
            "Verified displayed menu after click on main menu." +
                " Actual result is the 'Work Items Home' menu is NOT displayed after clicking on '" + menuTitle +
            "' and expected is the 'Work Items Home' menu should be displayed");
      case "Work Items":
        if (this.driverCustom.isElementVisible(RQMConstants.WORKITEMS_POPUP_XPATH, Duration.ofSeconds(10))) {
          return new TestAcceptanceMessage(true,
              "Verified after clicking on 'Work Items' tab, the Work Items pop up is displayed! The Work Items pop up was displayed as expectation!");
        }
        return new TestAcceptanceMessage(false,
            "Verified after clicking on 'Work Items' tab, the Work Items pop up is displayed as expectation! The Work Items pop up was not displayed as expectation!");
      default:
        return new TestAcceptanceMessage(false, "The " + menuTitle + " doesn't match");
    }
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#openSubMenuUnderSection(String, String)}.
   *
   * @param sectionName is Browse, Create, Import...
   * @param subMenuName is menu under section Name
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenSubMenuUnderSection(final String sectionName, final String subMenuName,
      final String lastResult) {
    waitForSecs(Duration.ofSeconds(10));
    switch (sectionName) {
      case "Browse":
        if (this.driverCustom.isElementVisible(RQMConstants.RQM_ARTIFACT_HEADING_XPATH, Duration.ofSeconds(60), subMenuName) ||
            this.driverCustom.isElementVisible("//span[@role='heading' and text()='" + subMenuName + "']", Duration.ofSeconds(60)) ||
            this.driverCustom.isElementVisible("//span[@dojoattachpoint='heading' and text()='" + subMenuName + "']",
                Duration.ofSeconds(60))) {
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFIED_DISPLAYED_PAGE_AFTER_CLICK_ON_SUB_MENU_UNDER_SECTION +
              RMConstants.ACTUAL_RESULT_IS_THE + subMenuName + RMConstants.PAGE_IS_DISPLAYED_AFTER_CLICKING_ON +
              subMenuName + RMConstants.MENU_UNDER + sectionName + "' node menu.\n\nExpected is the '" +
              subMenuName + "'.");
        }
        return new TestAcceptanceMessage(false,
            RMConstants.VERIFIED_DISPLAYED_PAGE_AFTER_CLICK_ON_SUB_MENU_UNDER_SECTION +
            RMConstants.ACTUAL_RESULT_IS_THE + subMenuName + RMConstants.PAGE_IS_NOT_DISPLAYED_AFTER_CLICKING_ON +
            subMenuName + RMConstants.MENU_UNDER + sectionName + RMConstants.NODE_MENU_AND_EXPECTED_IS_THE +
            subMenuName + RMConstants.PAGE_SHOULD_BE_DISPLAYED);
      case "Create":
        try {
          this.engine
          .findElementWithDuration(Criteria.isTextField().withPlaceHolder("Enter New " + subMenuName + " Name"),
              this.timeInSecs)
          .getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFIED_DISPLAYED_PAGE_AFTER_CLICK_ON_SUB_MENU_UNDER_SECTION +
              RMConstants.ACTUAL_RESULT_IS_THE + subMenuName + RMConstants.PAGE_IS_DISPLAYED_AFTER_CLICKING_ON +
              subMenuName + RMConstants.MENU_UNDER + sectionName + RMConstants.NODE_MENU_AND_EXPECTED_IS_THE +
              subMenuName + RMConstants.PAGE_SHOULD_BE_DISPLAYED);
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFIED_DISPLAYED_PAGE_AFTER_CLICK_ON_SUB_MENU_UNDER_SECTION +
              RMConstants.ACTUAL_RESULT_IS_THE + subMenuName + RMConstants.PAGE_IS_NOT_DISPLAYED_AFTER_CLICKING_ON +
              subMenuName + RMConstants.MENU_UNDER + sectionName + RMConstants.NODE_MENU_AND_EXPECTED_IS_THE +
              subMenuName + RMConstants.PAGE_SHOULD_BE_DISPLAYED);
        }
      case "Import":
        String s = sectionName + " " + subMenuName;
        if (this.driverCustom.isElementVisible(RQMConstants.RQM_ARTIFACT_HEADING_XPATH, Duration.ofSeconds(5), s)) {
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFIED_DISPLAYED_PAGE_AFTER_CLICK_ON_SUB_MENU_UNDER_SECTION +
              RMConstants.ACTUAL_RESULT_IS_THE + s + "' page is displayed after clicking on '" + subMenuName +
              RMConstants.MENU_UNDER + sectionName + RMConstants.NODE_MENU_AND_EXPECTED_IS_THE + subMenuName +
              RMConstants.PAGE_SHOULD_BE_DISPLAYED);
        }
        return new TestAcceptanceMessage(false,
            RMConstants.VERIFIED_DISPLAYED_PAGE_AFTER_CLICK_ON_SUB_MENU_UNDER_SECTION +
            RMConstants.ACTUAL_RESULT_IS_THE + s + "' page is NOT displayed after clicking on '" + subMenuName +
            RMConstants.MENU_UNDER + sectionName + RMConstants.NODE_MENU_AND_EXPECTED_IS_THE + subMenuName +
            RMConstants.PAGE_SHOULD_BE_DISPLAYED);
      case "Create Report":
        if (this.driverCustom.isElementVisible("//span[@dojoattachpoint='heading' and text()='Report']", Duration.ofSeconds(10))) {
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFIED_DISPLAYED_PAGE_AFTER_CLICK_ON_SUB_MENU_UNDER_SECTION +
              RMConstants.ACTUAL_RESULT_IS_THE + subMenuName + "' page is displayed after clicking on '" +
              subMenuName + "' menu under '" + sectionName + "' node menu and expected is the '" + subMenuName +
              "' page should be displayed");
        }
        return new TestAcceptanceMessage(false,
            "Verified displayed page after click on sub menu under section." + RMConstants.ACTUAL_RESULT_IS_THE +
            subMenuName + "' page is NOT displayed after clicking on '" + subMenuName + "' menu under '" +
            sectionName + "' node menu and expected is the '" + subMenuName + "' page should be displayed");
      default:
        return new TestAcceptanceMessage(false, "The " + sectionName + ", " + subMenuName + " doesn't match");
    }
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#openRQMSection(String)}.
   *
   * @param sectionName of section in the left side of the artifact details page
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenRQMSection(final String sectionName, final String lastResult) {
    waitForSecs(3);
    try {
      switch (sectionName) {
        case "Manage Sections":
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle(sectionName), this.timeInSecs)
          .getFirstElement();
          break;
        case "History":
          this.driverCustom.getPresenceOfWebElement("//div[@dojoattachpoint='historyContainerNode']");
          break;
        default:
          this.engine
          .findElementWithDuration(Criteria.isTextField().isMultiLine().withText(sectionName), this.timeInSecs)
          .getFirstElement();
      }
      return new TestAcceptanceMessage(true,
          RMConstants.VERIFIED_DISPLAYED_SECTION_AFTER_CLICK_ON_RQM_SECTION_IN_THE_ARTIFACT_DETAILS_PAGE +
          RMConstants.ACTUAL_RESULT_IS_THE + sectionName + "' section is displayed after clicking on '" +
          sectionName + RMConstants.SECTION_MENU_AND_EXPECTED_IS_THE + sectionName +
          RMConstants.SECTION_SHOULD_BE_DISPLAYED);
    }
    catch (Exception e) {
      List<WebElement> listElement = this.driverCustom.getWebElements("//span[@dojoattachpoint='titleNode']");
      for (WebElement element : listElement) {
        if (element.getText().equals(sectionName)) {
          return new TestAcceptanceMessage(true,
              "Verified displayed section after click on RQM section in the artifact details page." +
                  " Actual result is the '" + sectionName + "' section is displayed after clicking on '" + sectionName +
                  "' section menu and expected is the '" + sectionName + "' section should be displayed");
        }
      }
      return new TestAcceptanceMessage(false,
          "Verified displayed section after click on RQM section in the artifact details page." +
              " Actual result is the '" + sectionName + "' section is NOT displayed after clicking on '" + sectionName +
              "' section menu and expected is the '" + sectionName + "' section should be displayed");
    }
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#clickOnJazzButtons(String)}.
   *
   * @param button name of Jazz button
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnJazzButtons(final String button, final String lastResult) {
    return verifyClickOnJazzButtons(button, "TRUE", lastResult);
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#clickOnJazzButtons(String)}.
   *
   * @param button name of Jazz button
   * @param additionalPram to be passed in Excel Test Scripts.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnJazzButtons(final String button, final String additionalPram,
      final String lastResult) {
    waitForSecs(3);
    Boolean isClickable = this.driverCustom.isElementClickable(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(5), button);
    if (!isClickable && Boolean.valueOf(additionalPram)) {
      return new TestAcceptanceMessage(true,
          RMConstants.VERIFIED + button + "' button is clicked successfully.\n\n" + "Actual result '" + button +
          "' button is disabled after clicking.\n\nExpected result '" + button + "' button should be disabled");
    }
    else if (isClickable && !Boolean.valueOf(additionalPram)) {
      return new TestAcceptanceMessage(true,
          RMConstants.VERIFIED + button + "' button is clicked.\n\n" + "Actual result '" + button +
          "' button is not disabled after clicking as error message/dialog displayed.\n\nExpected result '" + button +
          "' button should not be disabled");
    }
    else if (Boolean.valueOf(true)) {
      return new TestAcceptanceMessage(true, RMConstants.VERIFIED + button + "' button is clicked.\n" +
          "Actual result '" + button + "' button is disabled after clicking");
    }
    return new TestAcceptanceMessage(false, "Verified a button after clicking." + "Actual result is the '" + button +
        "' button is NOT disabled after clicking and expected is the '" + button + "' button should be disabled");
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#clickOnJazzImageButtons(String)}.
   *
   * @param button name of Jazz Image button
   * @param lastResult get the last result of {@link AbstractJazzWebPage#clickOnJazzImageButtons(String)} or
   * @return acceptance object which contains verification results. A lot of contexts after clicking on the image
   *         button. So only verify the button clicked or not.
   */
  public TestAcceptanceMessage verifyClickOnJazzImageButtons(final String button, final String lastResult) {
    if (this.driverCustom.isElementVisible("//div[text()='No requirements were changed or removed.']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Verified: Clicked on 'Clear Suspicion' button.No Requirements displayed after clear suspicion.");
    }
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: '" + button + "' button is clicked successfully and last result is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: '" + button + "' button is not clicked successfully and last result is '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#clickOnDialogButton(String, String)}.
   *
   * @param dialog name of the dialog.
   * @param button name of Jazz Image button
   * @param additionalParam to be passed in Excel Test Scripts.
   * @param lastResult get the last result of {@link AbstractJazzWebPage#clickOnDialogButton(String,String)} or
   *          {@link AbstractJazzWebPage#clickOnDialogToolTipButton(String,String)}
   * @return acceptance object which contains verification results. A lot of contexts after clicking on the image
   *         button. So only verify the button clicked or not.
   */
  public TestAcceptanceMessage verifyClickOnDialogButton(final String dialog, final String button,
      final String additionalParam, final String lastResult) {
    if (additionalParam.equals("Restore")) {
      try {
        waitForSecs(Duration.ofSeconds(5));
        this.engine.findElementWithinDuration(Criteria.isDialog().withTitle(dialog), Duration.ofSeconds(5)).getFirstElement();
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(true,
            "Verified archive by visibilty of Restore button.\n\nActual result is artifact is archived and confirms that save button is changed to restore button as expected.");
      }
    }
    if(additionalParam.equalsIgnoreCase("Copy work item")) {
      if(this.driverCustom.isElementInvisible(CCMConstants.CCMWORKITEM_MOVE_OR_COPY_WORKITEM_POPUP, timeInSecs)) {
        return new TestAcceptanceMessage(true, "Click on 'Copy' button succesfully. Pop-up 'Move or copy this work item' is closed!");
      }
      return new TestAcceptanceMessage(false, "Click on Move/Copy button unsuccesfully! Pop-up is still displayed.");
    }
    return new TestAcceptanceMessage(false,
        "Verified archive by visibilty of Restore button.\n\nActual result is artifact is not archived and confirms that save button is not changed to restore button as expected.");
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#clickOnDialogButton(String, String)}.
   *
   * @param dialog name of the dialog.
   * @param button name of Jazz Image button
   * @param lastResult get the last result of {@link AbstractJazzWebPage#clickOnDialogButton(String,String)} or
   *          {@link AbstractJazzWebPage#clickOnDialogToolTipButton(String,String)}
   * @return acceptance object which contains verification results. A lot of contexts after clicking on the image
   *         button. So only verify the button clicked or not.
   */
  public TestAcceptanceMessage verifyClickOnDialogButton(final String dialog, final String button,
      final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, RMConstants.VERIFIED + button + "' button in '" + dialog +
          "' dialog is clicked successfully and last result is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false, RMConstants.VERIFIED + button + "' button in '" + dialog +
        "' dialog is not clicked successfully and last result is '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#openSettingsMenu(String)}.
   *
   * @param menuName name of the menu.
   * @param lasResult get the last result of {@link AbstractJazzWebPage#openSettingsMenu(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyOpenSettingsMenu(final String menuName, final String lasResult) {
    try {
      this.engine.findElement(Criteria.isLink().withText(menuName)).getFirstElement();
      return new TestAcceptanceMessage(true,
          RMConstants.VERIFIED_SUBMENU_AFTER_CLICK_ON + menuName + "' menu.\nActual the submenu is opened as expected");
    }
    catch (Exception e) {
      Optional<WebElement> matchingOptional = this.driverCustom.getWebElements("//tr[@role = 'menuitem']").stream()
          .filter(x -> x.getText().startsWith(menuName)).findFirst();
      if (matchingOptional.isPresent()) {
        return new TestAcceptanceMessage(true,
            "Verified submenu after click on '" + menuName + "' menu.\nActual the submenu is opened as expected");
      }
      return new TestAcceptanceMessage(false,
          "Verified submenu after click on '" + menuName + "' menu.\nActual the submenu is not opened as expected");
    }
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#openSettingsSubMenu(String)}.
   *
   * @param subMenuName name of the sub menu.
   * @param lasResult get the last result of {@link AbstractJazzWebPage#openSettingsSubMenu(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyOpenSettingsSubMenu(final String subMenuName, final String lasResult) {
    waitForSecs(Duration.ofSeconds(5));
    switch (subMenuName) {
      case "Manage Baselines": {
        try {
          this.engine.findFirstElementWithDuration(Criteria.isLink().withText("Stream"), this.timeInSecs);
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage Components and Configurations' page is displayed as expected.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage Components and Configurations' page is not displayed as expected.");
        }
      }
      case "Manage Components and Configurations":
        try {
          this.driverCustom.isElementVisible("//span[text()='Manage Components and Configurations']", this.timeInSecs);
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage Components and Configurations' page is displayed as expected.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage Components and Configurations' page is not displayed as expected.");
        }
      case "View Trash":
        try {
          this.driverCustom.isElementVisible("//span[text()='Trash']", this.timeInSecs);
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Trash' page is displayed as expected.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Trash' page is not displayed as expected.");
        }
      case "Manage Component Properties":
        try {
          this.engine.findElementWithDuration(Criteria.isTab().withText("Attribute Data Types"), this.timeInSecs)
          .getFirstElement();
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage Component Properties' page is displayed as expected.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage Component Properties' page is not displayed as expected.");
        }
      case "Manage Project Properties":
        try {
          this.driverCustom.getPresenceOfWebElement(RQMConstants.RQM_ARTIFACT_HEADING_XPATH, subMenuName);
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage Project Properties' page is displayed as expected.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage Project Properties' page is not displayed as expected.");
        }
      case "Manage Artifact Templates":
        try {
          this.driverCustom.getPresenceOfWebElement(RQMConstants.RQM_ARTIFACT_HEADING_XPATH, subMenuName);
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage Artifact Templates' page is displayed as expected.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage Artifact Templates' page is not displayed as expected.");
        }
      case "Create Component":
        try {
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Create Component"), this.timeInSecs)
          .getFirstElement();
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Create Component' dialog is displayed as expected.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Create Component' dialog is not displayed as expected.");
        }
      case "Manage This Project Area":
        try {
          this.engine.findElementWithDuration(Criteria.isLink().withText("Active Project Areas"), this.timeInSecs)
          .getFirstElement();
          return new TestAcceptanceMessage(true, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage This Project Area' page is displayed as expected.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false, RMConstants.VERIFIED_DIPLAYED_PAGE_AFTER_CLICK_ON + subMenuName +
              "'.\nActual the 'Manage This Project Area' page is not displayed as expected.");
        }
      case "Manage Project Areas":
        try {
          this.engine.findFirstElementWithDuration(Criteria.isButton().withText("Create Project Area"),
              this.timeInSecs);
          return new TestAcceptanceMessage(true, "Verified: Displayed page after click on '" + subMenuName +
              "'.\nActual the 'Manage Project Areas' page is displayed as expected.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false, "Verified: Displayed page after click on '" + subMenuName +
              "'.\nActual the 'Manage Project Areas' page is not displayed as expected.");
        }
      case "Manage Templates":
        try {
          this.engine.findFirstElementWithDuration(Criteria.isButton().withText("Import Template"), this.timeInSecs);
          return new TestAcceptanceMessage(true, "Verified displayed page after click on '" + subMenuName +
              "'.\nActual the 'Process Templates' page is displayed as expected.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false, "Verified displayed page after click on '" + subMenuName +
              "'.\nActual the 'Process Templates' page is not displayed as expected.");
        }
      case "View My Profile and Licenses":
        try {
          this.driverCustom.getPresenceOfWebElement("//div[@class='userEditor']");
          return new TestAcceptanceMessage(true, "Verified displayed page after click on '" + subMenuName +
              "'.\nActual the 'View My Profile and Licenses' page is displayed as expected.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false, "Verified displayed page after click on '" + subMenuName +
              "'.\nActual the 'View My Profile and Licenses' page is not displayed as expected.");
        }
      default:
        return new TestAcceptanceMessage(false, "The '" + subMenuName + "' does not match.");
    }
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#clickOnLink(String)}.
   *
   * @param linkText name of the link.
   * @param lastResult get the last result of {@link AbstractJazzWebPage#clickOnLink(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClickOnLink(final String linkText, final String lastResult) {
  try {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified '" + linkText + "' link is clicked successfully and last result is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified '" + linkText + "' link is not clicked successfully and last result is '" + lastResult + "'.");
  }
  catch (Exception e) {
    if (this.driverCustom.getAttribute("//a[@id='myReports']", "aria-selected").equals("true")) {
      return new TestAcceptanceMessage(true,
          "Verified '" + linkText + "' link is clicked successfully and last result is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified '" + linkText + "' link is not clicked successfully and last result is '" + lastResult + "'.");
  }
  
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#clickOnLinkWithPartOfText(String)}.
   *
   * @param linkText name of the link.
   * @param lastResult get the last result of {@link AbstractJazzWebPage#clickOnLink(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClickOnLinkWithPartOfText(final String linkText, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified Opening of link.\n\nActual result is '" + linkText + "' link is clicked successfully as expected.");
    }
    return new TestAcceptanceMessage(false,
        "Verified Opening of link.\n\nActual result is '" + linkText + "' link not clicked.");
  }

  /**
   * <p>
   * Verifies the action
   * {@link AbstractJazzWebPage#selectProjectAreaAndGlobalConfiguration(String, String, String, String)}.
   *
   * @param projectAreaName name of project area
   * @param gcName name of GC
   * @param componentName name of component
   * @param streamName name of stream
   * @param StreamType type of stream
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectProjectAreaAndGlobalConfiguration(final String projectAreaName,
      final String gcName, final String componentName, final String streamName, final String StreamType,
      final String lastResult) {
    return verifySelectProjectAreaAndGlobalConfiguration(projectAreaName, gcName, componentName, streamName,
        lastResult);
  }

  /**
   * <p>
   * Verifies the action
   * {@link AbstractJazzWebPage#selectProjectAreaAndGlobalConfiguration(String, String, String, String)}.
   *
   * @param projectAreaName name of the project area.
   * @param gcName name of global configuration.
   * @param componentName name of the component.
   * @param streamName name of the stream.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectProjectAreaAndGlobalConfiguration(final String projectAreaName,
      final String gcName, final String componentName, final String streamName, final String lastResult) {
    String title =
        this.driverCustom.getFirstVisibleWebElement("//span[starts-with(@class, 'banner-title')]", null).getText();
    if (this.driverCustom.isTitleContains("Project Dashboard:", Duration.ofSeconds(20)) || title.trim().startsWith(projectAreaName)) {
      String verMsg2 = "\n" + "Verified by banner title text present next to home button. \n\n Actual title is :\"" +
          title + ".\" \n\nExpected title is : \"" + projectAreaName + ".\"";
      boolean match = true;
      if ((!gcName.equalsIgnoreCase("null") && (!componentName.equalsIgnoreCase("null"))) ||
          (!streamName.equalsIgnoreCase("null"))) {
        if (this.driverCustom.isElementVisible("//span[@class='configurationUiNode']", Duration.ofSeconds(5))) {
          String comp = this.driverCustom.getText("//span[@class='configurationUiNode']");
          match = comp.trim().equalsIgnoreCase(componentName.trim());
          LOGGER.LOG.info("Actual component name " + comp.trim() + " Expected component name : " + componentName);
        }
        else if (this.driverCustom.isElementVisible("//a[@title='Current Project Component']", Duration.ofSeconds(10))) {
          String comp = this.driverCustom.getText("//a[@title='Current Project Component']");
          LOGGER.LOG.info("Actual component name " + comp + "Expected component name :" + componentName);
          match = comp.trim().equalsIgnoreCase(componentName);
        }

        if (match) {
          verMsg2 = verMsg2 + "\n\nVerified displayed component name.\nActual result is component switched to '" +
              componentName + "' as expected.";
        }
        else {
          verMsg2 = verMsg2 + "\n\nVerified displayed component name.\nActual result is component not switched to '" +
              componentName + "' component name as expected.";
          return new TestAcceptanceMessage(match, verMsg2);
        }
        String currentConfig;
        if (this.driverCustom.isElementVisible(RMConstants.RMCURRENT_CONFIGURATION_XPATH, Duration.ofSeconds(10))) {
          currentConfig = this.driverCustom.getText(RMConstants.RMCURRENT_CONFIGURATION_XPATH);
          LOGGER.LOG.info("Actual stream name " + streamName + "Expected stream name :" + currentConfig);
        }
        else {
          currentConfig = this.driverCustom.getText(RMConstants.CURRENT_CONFIGURATION_XPATH);
          LOGGER.LOG.info("Actual stream name " + streamName + "Expected stream name :" + currentConfig);
        }
        match = currentConfig.trim().equalsIgnoreCase(streamName);
        if (match) {
          verMsg2 = verMsg2 + "\n\nVerified displayed stream name.\nActual result is stream switched to '" +
              currentConfig + "' as expected";
        }
        else {
          verMsg2 = verMsg2 + "\n\nVerified displayed stream name.\nActual result is stream not switched to '" +
              currentConfig + "' as expected";
          return new TestAcceptanceMessage(match, verMsg2);
        }
      }
      return new TestAcceptanceMessage(match, verMsg2);
    }

    return new TestAcceptanceMessage(false, "Project area name is not matched " + projectAreaName);
  }

  /**
   * <p>
   * Verifies the action {@link AbstractRMPage#selectGlobalConfiguration(String, String, String, String)}.
   *
   * @param projectAreaName name of the project area.
   * @param gcName name of the global configuration.
   * @param componentName name of the component.
   * @param streamName name of the stream.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectGlobalConfiguration(final String projectAreaName, final String gcName,
      final String componentName, final String streamName, final String lastResult) {
    String title =
        this.driverCustom.getFirstVisibleWebElement("//span[starts-with(@class, 'banner-title')]", null).getText();
    if (this.driverCustom.isTitleContains("Project Dashboard:", Duration.ofSeconds(20)) || title.trim().startsWith(projectAreaName)) {
      String verMsg2 = "\n" + "Verified by banner title text present next to home button \n \n Actual title \"" +
          title + "\" contains expected text : \"" + projectAreaName + "\".";
      boolean match =
          this.driverCustom.getText("//span[contains(@class,'component-menu')]").equalsIgnoreCase(componentName);
      if (match) {
        verMsg2 = verMsg2 + "\n\nVerified displayed component name.\nActual result is switched to '" + componentName +
            "' component name as expected";
      }
      else {
        verMsg2 = verMsg2 + "\n\nVerified displayed component name.\nActual result is not switched to '" +
            componentName + "' component name as expected";
      }
      String currentConfig;
      if (this.driverCustom.isElementVisible(RMConstants.RMCURRENT_CONFIGURATION_XPATH, Duration.ofSeconds(10))) {
        currentConfig = this.driverCustom.getText(RMConstants.RMCURRENT_CONFIGURATION_XPATH);
      }
      else {
        currentConfig = this.driverCustom.getText(RMConstants.CURRENT_CONFIGURATION_XPATH);
      }
      match = currentConfig.equalsIgnoreCase(streamName);
      if (match) {
        verMsg2 = verMsg2 + "\n\nVerified displayed stream name.\nActual result is switched to '" + currentConfig +
            "' stream name as expected";
      }
      else {
        verMsg2 = verMsg2 + "\n\nVerified displayed stream name.\nActual result is not switched to '" + currentConfig +
            "' stream name as expected";
      }

      return new TestAcceptanceMessage(match, verMsg2);
    }
    return new TestAcceptanceMessage(false, "Project area name is not matched " + projectAreaName);
  }

  /**
   * <p>
   * Verifies the action {@link AbstractRMPage#getRMAttributes()}.
   *
   * @param attributeName name of the attribute.
   * @param lastResult last result {@link AbstractRMPage#getRMAttributeValue(String)}
   * @return test acceptance message
   */
  public TestAcceptanceMessage verifyGetRMAttributeValue(final String attributeName, final String lastResult) {
    return verifyGetRMAttributeValue(attributeName, "", lastResult);
  }
  
  /**
   * <p>
   * Verifies the action {@link AbstractRMPage#getRMAttributes()}.
   *
   * @param attributeName name of the attribute.
   * @param additionalPram -
   * @param lastResult last result {@link AbstractRMPage#getRMAttributeValue(String)}
   * @return test acceptance message
   */
  public TestAcceptanceMessage verifyGetRMAttributeValue(final String attributeName, final String additionalPram,
      final String lastResult) {
    if (!lastResult.isEmpty() && (additionalPram.isEmpty() || lastResult.equalsIgnoreCase(additionalPram))) {
      return new TestAcceptanceMessage(true,"Artifact has '" + attributeName + "' is '" + lastResult + "' from artifact details.");
    }
    return new TestAcceptanceMessage(false,
        "Artifact has '" + attributeName + "' is '" + lastResult + "' from artifact details.");
  }

  /**
   * <p>
   * Verifies the action {@link AbstractRMPage#refresh() and @link AbstractRMPage#navigateBack()}.
   *
   * @param additionalPram the expected page title
   * @param lastResult last result {@link AbstractJazzWebPage#refresh()}
   * @return test acceptance message
   */
  public TestAcceptanceMessage verifyPageNavigation(final String additionalPram, final String lastResult) {
    waitForSecs(Duration.ofSeconds(10));
    this.driverCustom.isElementInvisible("//div[@class='status-message']", timeInSecs);
    String getPageTitle = this.driverCustom.getCurrentPageTitle().trim();
    if (getPageTitle.contains(additionalPram)) {
      return new TestAcceptanceMessage(true,
          "Verified: PASSED - that the current page is navigated/refreshed successfully.\n'" +
              "Actual current page title is '" + getPageTitle + "' and the expected page title is '" + additionalPram +
          "' .");
    }
    return new TestAcceptanceMessage(false,
        "Verified: FAILED - that the current page is not navigated/refreshed successfully.\n'" +
            "Actual current page title is '" + getPageTitle + "' and the expected page title is '" + additionalPram +
        "' .");
  }

  /**
   * <p>
   * Verifies the action {@link AbstractRMPage#selectConfigContext(String, String, String)}.
   *
   * @param configManagementType type of configuration management.
   * @param configType type of the configuration.
   * @param configName name of the configuration.
   * @param lastResult get the last result of
   *          {@link RMManageComponentsAndConfigurations#selectConfigContext(String, String, String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifySelectConfigContext(final String configManagementType, final String configType,
      final String configName, final String lastResult) {
    waitForSecs(3);
    if (this.driverCustom.isElementVisible(RMConstants.RMCURRENT_CONFIGURATION_XPATH, Duration.ofSeconds(20))) {
      String s1 = this.driverCustom.getText(RMConstants.RMCURRENT_CONFIGURATION_XPATH).trim();
      if (s1.equals(configName.trim())) {
        return new TestAcceptanceMessage(true,
            "Verified: Current configuration after selecting.\n\nActual Result '" + s1 +
            RMConstants.IS_DISPLAYED_AS_CURRENT_CONFIGURATION + configName +
            RMConstants.SHOULD_BE_DISPLAYED_AS_CURRENT_CONFIGURATION);
      }
      return new TestAcceptanceMessage(false,
          "Verified: Current configuration after selecting.\\n\\nActual Result '" + s1 +
          RMConstants.IS_DISPLAYED_AS_CURRENT_CONFIGURATION + configName +
          RMConstants.SHOULD_BE_DISPLAYED_AS_CURRENT_CONFIGURATION);
    }
    String s2 = this.driverCustom.getText(
        "//span[@title='Current Configuration']//a[@class='configurationUiNode hideLink']//span[@class='titleNode']");
    if (s2.equals(configName)) {
      return new TestAcceptanceMessage(true,
          "Verified current configuration after selecting.\n\nActual is the '" + s2 +
          "' is displayed as current configuration.\n\nExpected is the '" + configName +
          "' should be displayed as current configuration.");
    }
    return new TestAcceptanceMessage(false,
        "Verified current configuration after selecting.\n\nActual is the '" + s2 +
        "' is displayed as current configuration.\n\nExpected is the '" + configName +
        "' should be displayed as current configuration.");
  }

  /**
   * <p>
   * Verify page is navigated successfully.
   * <p>
   * This method called after {@link AbstractJazzWebPage#navigateBack()}.
   *
   * @param additionalParam to be passed in Excel Test Script.
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyNavigateBack(final String additionalParam, final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    String url = this.driverCustom.getCurrUrl();
    if (url.contains(additionalParam)) {
      return new TestAcceptanceMessage(true, "Verified: Page is navigated successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Not navigated to proper page.");
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#navigateToURL(String)}.
   *
   * @param url is repository url.
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyNavigateToURL(final String url, final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    String currenturl = this.driverCustom.getCurrUrl();
    if (currenturl.contains(url) || url.contains(currenturl)) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Page is navigated to '" + url + "' url.");
    }
    return new TestAcceptanceMessage(false, "Verified: Page is not navigated to '" + url + "' url.\nExpected URL: '" +
        url + "'\nActual URL: '" + currenturl + "'");
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#navigateToURL(String)}.
   *
   * @param url is repository url.
   * @param lastResult returned value of method which is executed just before the method.
   * @param additionalParam - Page title of expected page
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyNavigateToURLByPageTitle(final String url, final String additionalParam,
      final String lastResult) {
    waitForSecs(5);
    String currenturl = this.driverCustom.getCurrUrl();
    if (currenturl.contains(url) || url.contains(currenturl)) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Page is navigated to '" + url + "' url.");
    }
    String pageTitle = this.driverCustom.getCurrentPageTitle();
    if (pageTitle.contains(additionalParam)) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Page is navigated to '" + url +
          "' url.\nExpected Page Title: " + additionalParam + "\nActual Page Title: " + pageTitle);
    }
    return new TestAcceptanceMessage(false, "Verified: Page is not navigated to '" + url + "' url.\nExpected URL: '" +
        url + "'\nActual URL: '" + currenturl + "'");
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Artifacts");
    }
    catch (Exception e) {
      waitForSecs(2);
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {}


  /**
   * <p>
   * Verifies the action get Creation Date of input Baseline in "Select the Configuration Context" dialog
   *
   * @param configType type of the configuration.
   * @param configName name of the configuration.
   * @param lastResult get the last result of {@link AbstractRMPage#getCreationDateFromBaseline(String, String)}
   * @return test acceptance message
   */
  public TestAcceptanceMessage verifyGetCreationDateFromBaseline(final String configType, final String configName,
      final String lastResult) {
    waitForSecs(3);
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Get Creation Date successfully for configuration name as " + configName + ", type as " + configType);
    }
    return new TestAcceptanceMessage(false,
        "Get Creation Date fail for configuration name as " + configName + ", type as " + configType);
  }

  /**
   * <p>
   * Verifies the action {@link AbstractRMPage#isDate01beforeDate02(String, String)}.
   *
   * @param Date01 type of String, format:"MMM dd, yyyy, h:mm:ss a" or "MMM dd, yyyy h:mm a".
   * @param Date02 type of String, format:"MMM dd, yyyy, h:mm:ss a" or "MMM dd, yyyy h:mm a".
   * @param lastResult get the last result of {@link AbstractRMPage#isDate01beforeDate02(String, String)}
   * @return test acceptance message
   */
  public TestAcceptanceMessage verifyIsDate01beforeDate02(final String Date01, final String Date02,
      final String lastResult) {
    waitForSecs(3);
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Compare successfully with result as " + Date01 + " is before " + Date02);
    }
    return new TestAcceptanceMessage(false,
        "Compare not successfully or result as " + Date01 + " is after or equal to " + Date02);
  }

  /**
   * <p>
   * Verifies the action {@link AbstractRMPage#compareTwoInputAsNumber(String, String,String)}.
   *
   * @param input1 data type of String, as number
   * @param input2 data type of String, as number
   * @param expectResult data type of String, as "greater"/"less than"/"equal"/"not equal" for comparing between input1
   *          and input2
   * @param lastResult get the last result of {@link AbstractRMPage#compareTwoInputAsNumber(String, String,String)}
   * @return test acceptance message
   */

  public TestAcceptanceMessage verifyCompareTwoInputAsNumber(final String input1, final String input2,
      final String expectResult, final String lastResult) {
    waitForSecs(5);
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Compare between " + input1 + " and " + input2 + " is pass - Actual result and expect result are the same");
    }
    return new TestAcceptanceMessage(false, "Compare between " + input1 + " and " + input2 +
        " is fail - Actual result is different from expect result. Or input expect result is not correct format (ex: 'greater' or 'less than' or 'equal' or 'not equal'), please check again");
  }

  /**
   * <p>
   * Verifies the action {@link AbstractRMPage#compareTwoInputAsText(String, String,String)}.
   *
   * @author VDY1HC <br>
   * @param input1 - text 1 <br>
   * @param input2 - text 2 <br>
   * @param expectResult - contains, equal
   * @param lastResult get the last result of {@link AbstractRMPage#compareTwoInputAsText(String, String,String)}
   * @return test acceptance message
   */

  public TestAcceptanceMessage verifyCompareTwoInputAsText(final String input1, final String input2,
      final String expectResult, final String lastResult) {
    waitForSecs(5);
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified result: PASSED - \nCompare data between " + input1 + " and " + input2 +
          ". They are " + expectResult + ".");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Compare between " + input1 + " and " + input2 +
        " with expected " + expectResult + " is NOT pass - Actual result and expect result are unmatch");
  }

  /**
   * <p>
   * Verify the current position is in the artifactID or not
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact we want to check
   * @param additionalParam true or false, true if we want to check the current position is in the artifact, false if we
   *          want to check the current position is not in the artifact
   * @param lastResult get the last result of {@link AbstractRMPage#verifyPositionInTheArtifactOrNot(String)}
   * @return true if the current position is similar with the expectation
   */
  public TestAcceptanceMessage verifyVerifyPositionInTheArtifactOrNot(final String artifactID,
      final String additionalParam, final String lastResult) {
    waitForSecs(3);
    if (lastResult.equals(additionalParam)) {
      if (lastResult.equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true,
            "Current position is in the artifact '" + artifactID + "' as expected result!");
      }
      return new TestAcceptanceMessage(true,
          "Current position is not in the artifact '" + artifactID + "' as expected result!");
    }
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(false,
          "Current position is not as expected result. \nExpected result: is not in artifact '" + artifactID +
          "'.\nActual result: is in artifact '" + artifactID + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Current position is not as expected result. \nExpected result: is in artifact '" + artifactID +
        "'.\nActual result: is not in artifact '" + artifactID + "'.");
  }

  /**
   * <p>
   * Verify get the absolute path of file to be imported
   * <p>
   *
   * @author NVV1HC
   * @param fileName name of file to be imported
   * @param lastResult result from ${@link AbstractRMPage#getAbsoluteFilePathToBeImported(String)}
   * @return true if successfully get the path of the file or vice versa
   */
  public TestAcceptanceMessage verifyGetAbsoluteFilePathToBeImported(final String fileName, final String lastResult) {
    waitForSecs(3);
    LOGGER.LOG.info("File path: " + lastResult);
    if (lastResult.contains("src\\test\\resources\\dng\\" + fileName)) {
      return new TestAcceptanceMessage(true, "Get absolute path of file '" + fileName + "' successfully!");
    }
    return new TestAcceptanceMessage(false,
        "Get absolute path of file '" + fileName + "' failed.\nExpected path contains: '" +
            "src\\test\\resources\\dng\\" + fileName + "'\nActual: '" + lastResult + "'");
  }

  /**
   * <p>
   * Verify a change set is displayed or not after ${@link AbstractRMPage#isChangesetDisplayed(String)}
   * <p>
   *
   * @author NVV1HC
   * @param changesetName name of changset
   * @param additionalParam true or false, true if you want to verify the changeset is displayed, or vice versa
   * @param lastResult result after ${@link AbstractRMPage#isChangesetDisplayed(String)}
   * @return true if changeset displayed when additionalParam is true or not displayed when additionalParam is false, or
   *         vice versa
   */
  public TestAcceptanceMessage verifyIsChangesetDisplayed(final String changesetName, final String additionalParam,
      final String lastResult) {
    waitForSecs(3);

    if (lastResult.equalsIgnoreCase(additionalParam)) {
      if (lastResult.equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true, "Changeset '" + changesetName + "' is displayed as expected result!");
      }
      return new TestAcceptanceMessage(true, "Changeset '" + changesetName + "' is not displayed as expected result!");
    }
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(false, "Expected result: Changeset '" + changesetName +
          "' is not displayed.\nActual result: Changeset '" + changesetName + "' is displayed!");
    }
    return new TestAcceptanceMessage(false, "Expected result: Changeset '" + changesetName +
        "' is displayed.\nActual result: Changeset '" + changesetName + "' is not displayed!");
  }

  /**
   * @author LPH1HC verify for method {@link AbstractRMPage#switchToTheOtherTab(String)}.
   * @param index - index of tab need to switch to
   * @param lastResult result after ${@link AbstractRMPage#switchToTheOtherTab(String)}
   * @return true if switch to index tab successfully
   */
  public TestAcceptanceMessage verifySwitchToTheOtherTab(final String index, final String lastResult) {
    //    waitForPageLoaded();
    waitForSecs(5);
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Switch to the " + index + " tab successfully");
    }
    return new TestAcceptanceMessage(false, "Switch to the " + index + " tab fail");
  }

  /**
   * <p>
   * Verify open a new tab in existing window and navigate to the URL after
   * ${@link AbstractWebPage#openNewTabWithURL(String)}
   * <p>
   *
   * @author NVV1HC
   * @param URL url to navigate to
   * @param expectedTitle expected title of the website after opening
   * @param lastResult result from ${@link AbstractWebPage#openNewTabWithURL(String)}
   * @return verification message true if open a new tab successfully and navigate to the URL or vice versa
   */
  public TestAcceptanceMessage verifyOpenNewTabWithURL(final String URL, final String expectedTitle,
      final String lastResult) {
    waitForPageLoaded();
    String currentTitle = this.driverCustom.getWebDriver().getTitle();
    if (currentTitle.trim().equals(expectedTitle)) {
      return new TestAcceptanceMessage(true, "Verify open new tab and navigate to URL '" + URL +
          "' successfully!\nExpected title: '" + expectedTitle + "'\nActual title: '" + currentTitle + "'");
    }
    return new TestAcceptanceMessage(false, "Verify open new tab and navigate to URL '" + URL +
        "' failed!\nExpected title: '" + expectedTitle + "'\nActual title: '" + currentTitle + "'");
  }

  /**
   * <p>
   * Verify widget is added successfully after ${@link AbstractRMPage#miniDashboardOpen()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link AbstractRMPage#miniDashboardOpen()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyMiniDashboardOpen(final String lastResult) {
    waitForSecs(5);
    boolean result =
        this.driverCustom.isElementVisible(RMConstants.MINIDASHBOARDPAGE_MINIDASHBOARD_XPATH, this.timeInSecs);
    if (result) {
      return new TestAcceptanceMessage(true, "Mini Dashboard is opened successfully!");
    }
    return new TestAcceptanceMessage(false, "Mini Dashboard is not opened successfully!");
  }

  /**
   * <p>
   * Verify widget is added successfully after ${@link AbstractRMPage#addDashboardWidget(String, String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param tabName tab
   * @param widgetCategory category of the widget, e.g: All, Project,
   * @param widgetName name of widget to be added
   * @param lastResult result from ${@link AbstractRMPage#addDashboardWidget(String, String, String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyAddDashboardWidget(final String tabName, final String widgetCategory,
      final String widgetName, final String lastResult) {
    waitForSecs(5);
    boolean result =
        this.driverCustom.isElementVisible(RMConstants.DASHBOARDPAGE_WIDGET_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)), widgetName);
    if (result) {
      return new TestAcceptanceMessage(true, "Widget '" + widgetName + "' is added successfully!");
    }
    return new TestAcceptanceMessage(false, "Widget '" + widgetName + "' is not added!");
  }

  /**
   * <p>
   * Verify widget is added successfully after ${@link AbstractRMPage#removeWidget(String)}
   * <p>
   *
   * @author NVV1HC
   * @param widgetName tab
   * @param lastResult result from ${@link AbstractRMPage#removeWidget(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyRemoveWidget(final String widgetName, final String lastResult) {
    waitForSecs(5);
    boolean result =
        this.driverCustom.isElementInvisible(RMConstants.DASHBOARDPAGE_WIDGET_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)), widgetName);
    if (result) {
      return new TestAcceptanceMessage(true, "Widget '" + widgetName + "' is removed successfully!");
    }
    return new TestAcceptanceMessage(false, "Widget '" + widgetName + "' is not removed!");
  }

  /**
   * <p>
   * Verify widget is added successfully after ${@link AbstractRMPage#saveWidget()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link AbstractRMPage#saveWidget()}
   * @return verification message
   */
  public TestAcceptanceMessage verifySaveWidget(final String lastResult) {
    boolean result1 = this.driverCustom.isElementVisible(RMConstants.DASHBOARDPAGE_SAVEDASHBOARDSUCCESSFULLY_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)));
    if (result1) {
      return new TestAcceptanceMessage(true, "Verify save widget successfully!");
    }
    return new TestAcceptanceMessage(true, "Verify save widget failed!");
  }

  /**
   * <p>
   * Verify a checkbox is enabled successfully after running
   * ${@link AbstractJazzWebPage#enableACheckBox(String, String)}
   * <p>
   *
   * @author YNT2HC
   * @param criteriaValue is value of criteria
   * @param criteriaType is type of criteria
   * @param lastResult is return result
   * @return verification message
   */
  @SuppressWarnings("null")
  public TestAcceptanceMessage verifyEnableACheckBox(final String criteriaValue, final String criteriaType,
      final String lastResult) {
    Checkbox cbxElement = null;
    try {
      switch (criteriaType) {
        case "withLabel":
          cbxElement =
          this.engine.findElementWithDuration(Criteria.isCheckbox().withLabel(criteriaValue), this.timeInSecs)
          .getFirstElement();
          break;
        case "withAriaLabel":
          cbxElement =
          this.engine.findElementWithDuration(Criteria.isCheckbox().withAriaLabel(criteriaValue), this.timeInSecs)
          .getFirstElement();
          break;
        case "withToolTip":
          cbxElement =
          this.engine.findElementWithDuration(Criteria.isCheckbox().withToolTip(criteriaValue), this.timeInSecs)
          .getFirstElement();
          break;
      }
    }
    catch (Exception e) {}
    if (cbxElement.getWebElement().getAttribute("checked").equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Checkbox '" + criteriaValue + "' is enabled successfully.");
    }
    return new TestAcceptanceMessage(false, "Checkbox '" + criteriaValue + "' is not enabled successfully.");
  }

  /**
   * <p>
   * This method is called after running ${@link AbstractJazzWebPage#closeWindow()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from the main method ${@link AbstractJazzWebPage#closeWindow()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyCloseWindow(final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify close window passed!");
    }
    return new TestAcceptanceMessage(false, "Verify close window failed!");
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#clickOnLabel(String)}.
   *
   * @author KYY1HC
   * @param labelName name of the label.
   * @param lastResult get the last result of {@link AbstractJazzWebPage#clickOnLabel(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClickOnLabel(final String labelName, final String lastResult) {
    String xpathTabSelected = String.format("//label[normalize-space(text())='%s']/parent::div[@class='item-container highlighted']", labelName);
    if (this.driverCustom.isElementVisible(xpathTabSelected, this.timeInSecs)) {
      return new TestAcceptanceMessage(true, "Verified - PASSED: The Label '" + labelName + "' is selected successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified - FAILED: The Label '" + labelName + "' is NOT selected successfully.");
  }

  /**
   * <p>
   * This method is called after executing ${@link AbstractWebPage#addTwoNumbers(String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param number1 number 1
   * @param number2 number 2
   * @param lastResult total of 2 numbers
   * @return true
   */
  public TestAcceptanceMessage verifyAddTwoNumbers(final String number1, final String number2,
      final String lastResult) {
    return new TestAcceptanceMessage(true,
        "This method is to add 2 numbers: '" + number1 + " + " + number2 + " = " + lastResult + "'!");
  }
  
  /**
   * This method is called after executing ${@link AbstractRMPage#isDateTimeAtTheMoment(String, String)}
   * @param dateTime - input date time
   * @param dateTimeFormat - format of input date
   * @param lastResult - last result
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsDateTimeAtTheMoment (String dateTime, String dateTimeFormat, String lastResult) {
    DateFormat formatter = new SimpleDateFormat(dateTimeFormat);
    Date actualDate = new Date();
    String result = "PASSED";
    boolean lastResultInBoolean = Boolean.parseBoolean(lastResult);
    if (!lastResultInBoolean) {
      result = "FAILED";
    }
    return new TestAcceptanceMessage(lastResultInBoolean, "Verified: " + result + " - Input date is least than 1 hour from the momment" +
        "\nInput date time: " + dateTime +
        "\nActual date time: " + formatter.format(actualDate) +
        "\nLast result: " + lastResult);
  }
  /**
   * <p>
   * Verify global configuration item count.
   * <p>
   * This method called after {@link AbstractRMPage#getGlobalConfigItemsCount(String, String)}.
   * 
   * @param proJectAreaName project area to select for config.
   * @param itemName name of the config item.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetGlobalConfigItemsCount(final String proJectAreaName,final String itemName,final String lastResult) {
    if (lastResult != null) {
      return new TestAcceptanceMessage(true, "Total number of '"+itemName+"' displayed for the project area '"+proJectAreaName+"' - " + lastResult);
    }

    return new TestAcceptanceMessage(false, "Number of Streams displayed for the project area is not correct - "+lastResult);

  }
  
  /**
   * <p>
   * This method is called after executing ${@link AbstractJazzWebPage#removeAllWidgets(String)}}
   * <p>
   *
   * @author LTU7HC
   * @param widgetName name of widget.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyRemoveAllWidgets(final String widgetName, final String lastResult) {
    boolean result =
        this.driverCustom.isElementInvisible("//div[@class='header-primary']/span[text()='DYNAMIC_VAlUE']", Duration.ofSeconds(30), widgetName);
    if (result) {
      return new TestAcceptanceMessage(true,
          String.format("All Widgets with name: '%s' are removed successfully", widgetName));
    }
    return new TestAcceptanceMessage(false,
        String.format("All Widgets with name: '%s' are not removed", widgetName));
  }
  
  /**
   * Verify action wait for secs
   * This method called after {@link RMArtifactsPage#waitForSecs(String)}.
   * @author VDY1HC
   * @param timeInSecs1 - String
   * @param lastResult - last result
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyWaitForSecs (final String timeInSecs1, final String lastResult) {
    return new TestAcceptanceMessage(true, "Verified: Action wait for certain time in seconds - " + timeInSecs1);
  }
  
  /**
   * Verify action getNumberOfRowsInTable This method called after
   * {@link AbstractRQMPage#getNumberOfRowsInTable(String)}.
   * 
   * @author LTU7HC
   * @param tableName name of table
   * @param additionalParam param parse in excel
   * @param lastResult - last result
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetNumberOfRowsInTable(final String tableName, final String additionalParam,
      final String lastResult) {
    if (lastResult.equals(additionalParam)) {
      return new TestAcceptanceMessage(true,
          "Verified: Action getNumberOfRowsInTable successfully - Number of rows in table: " + lastResult);
    }
    else if (additionalParam.isEmpty()) {
      if (!lastResult.equals("0")) {
        return new TestAcceptanceMessage(true,
            "Verified: Action getNumberOfRowsInTable successfully - Number of rows in table: " + lastResult);
      }
    }
    return new TestAcceptanceMessage(false, "Verified: Action getNumberOfRowsInTable FAILED!");
  }
  
  /**
   * Verify click on 'Show Inline Filters'<br>
   * {@link AbstractRQMPage#showInlineFilters()}.
   * @author LTU7HC
   * @param lastResult - returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyShowInlineFilters(final String lastResult) {
    if (this.driverCustom.isElementPresent(RQMConstants.RQMPROJECT_HIDE_SLIDERDOWN_DROPDOWN_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 6)))) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Clicked on 'Show Inline Filters'");
    }
    return new TestAcceptanceMessage(true, "Verify: FAILED - Not clicked on 'Show Inline Filters'");
  }
  
  /**
   * Verify click on 'Show Filters'<br>
   * {@link AbstractRQMPage#showFilters()}.
   * @author LTU7HC
   * @param lastResult - returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyShowFilters(final String lastResult) {
    
    if (this.driverCustom.isElementPresent(RQMConstants.RQMPROJECT_HIDE_FILTERS_BUTTON_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 6)))) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Clicked on 'Show Filters'");
    }
    return new TestAcceptanceMessage(true, "Verify: FAILED - Not clicked on 'Show Filters'");
  }
 
  /**
   * @param name
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyRemoveOpenSocialGadget(final String name,final String lastResult) {
    waitForSecs(5);
    String xpath = PagemodelConstants.SPAN_TEXT + name +"']";
    
    //this.driverCustom.isElementVisible(xpath, Duration.ofSeconds(10));
    if (!this.driverCustom.isElementVisible(xpath, Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Widget removed.\"");
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED -Widget not removed.");
  }
 // isWidgetSavedInMiniDashboard
  /**
   * @param widgetTitle
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyIsWidgetSavedInMiniDashboard(final String widgetTitle,final String additionalParam,final String lastResult) {
   
    if (additionalParam.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalParam), "Verify PASSED'"+widgetTitle+"' Widget Saved In Mini Dashboard passed!");
  }
    if (additionalParam.equalsIgnoreCase("false")) {
      return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalParam), "Verify PASSED'"+widgetTitle+"' Widget removed or not found In Mini Dashboard passed!");
  }
  return new TestAcceptanceMessage(false, "Verify '"+widgetTitle+"' Widget not Saved In Mini Dashboard failed!");
}
 

  
  /**
   * @param widgetUrl
   * @param additionalParam is widgetTitle/name
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyAddOpenSocialGadget(final String widgetUrl,final String additionalParam,final String lastResult) {
    
    String xpath = PagemodelConstants.SPAN_TEXT + additionalParam +"']";
    LOGGER.LOG.info("Verified Widget Saved In Mini Dashboard : " +additionalParam);
    if (this.driverCustom.isElementVisible(xpath, Duration.ofSeconds((this.timeInSecs.getSeconds() / 6)))) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Widget Saved In Mini Dashboard.\"");
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED -Widget not Saved/found In Mini Dashboard.");
  }
  
  
}
