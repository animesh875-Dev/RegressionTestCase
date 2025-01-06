/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.ccm;

import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;
import com.bosch.jazz.automation.web.pagemodel.AbstractJazzWebPage;
import com.bosch.jazz.automation.web.pagemodel.AbstractWebPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMCreateQueryPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.container.Dialog;

/**
 * Most basic page class of CCMCreateQueryPageVerification.
 */
public class CCMCreateQueryPageVerification extends AbstractWebPage {

  /**
   * @param driverCustom driver.
   */
  public CCMCreateQueryPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * <p>
   * Verifies the action of {@link AbstractCCMPage#openMenu(String)}.
   *
   * @param menuName name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyOpenMenu(final String menuName, final String lastResult) {
    WebElement menulist =
        this.driverCustom.getWebElement(CCMConstants.CCMCREATEQUERYPAGE_CCMPAGE_DROPDOWN_XPATH, menuName);
    if (menulist.getAttribute("aria-expanded").equals("true")) {
      String result =
          CCMConstants.CCMCREATEQUERYPAGE_QUERY_VERIFICATION + menuName + "\" Drop down menu opened. Actual result\"" +
              menuName + "\" opened and \n Expected result\" " + menuName + "\" opened";
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false, "Verified \"" + menuName + "\" Drop down menu opened. Actual result\"" +
        menuName + "\" not opened \n Expected Result \"" + menuName + "\" Drop down menu not opened.");
  }

  /**
   * <p>
   * Verifies the action {@link CCMCreateQueryPage#setQueryName(String)}.
   *
   * @param queryName name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySetQueryName(final String queryName, final String lastResult) {
    String queryname =
        this.driverCustom.getWebElement(CCMConstants.CCMCREATEREPORTFROMRESOURCEPAGE_REPORTNAME_TEXTBOX_XPATH)
            .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE);
    if (queryname.contains(queryName)) {
      String result = "Verified query name present in the name text box .\n Actual Query name \"" + queryname +
          "\" and \n Expected Query name \" " + queryname + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false,
        "Verified query name present in the name text box .\n Actual Query name \"" + queryName +
            "\" and \n Expected Query name \"" + queryname + "\"");
  }

  /**
   * <p>
   * Verifies the action {@link CCMCreateQueryPage#button(String)}.
   *
   * @param buttonName name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySaveButton(final String buttonName, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    String result = "\"" + buttonName + "\" button Clicked Successfully Verified .\n Actual Result \"" + buttonName +
        "\" Clicked successfully and \n Expected Result \"" + buttonName + "\" Clicked successfully";
    if (buttonName.equalsIgnoreCase("Save Copy") || buttonName.equalsIgnoreCase("Close")) {
      this.driverCustom.getPresenceOfWebElement("//span[@class='titleText']");
      return new TestAcceptanceMessage(true, result);
    }
    else if (buttonName.equals("Run")) {
      this.driverCustom.getPresenceOfWebElement("//table[contains(@class,'queryResultsTable')]");
      return new TestAcceptanceMessage(true, result);
    }
    else if (!buttonName.equals("Run")) {
      this.driverCustom.getPresenceOfWebElement("//button[text()='" + buttonName + "' and @disabled ]");
      return new TestAcceptanceMessage(true, result);
    }
    try {
      this.driverCustom.waitForSecs(Duration.ofSeconds(5));
      this.driverCustom.getPresenceOfWebElement("//button[@class='j-button-primary' and @disabled]");
      List<WebElement> button = this.driverCustom.getWebElements("//button[@class='j-button-primary' and @disabled]");
      for (WebElement buttondisabled : button) {
        if (buttondisabled.getText().equals(buttonName)) {
          return new TestAcceptanceMessage(true, result);
        }

      }
    }
    catch (Exception e) {
      this.driverCustom.getPresenceOfWebElement("//button[@class='Button' and @disabled]");
      List<WebElement> button = this.driverCustom.getWebElements("//button[@class='Button' and @disabled]");
      for (WebElement buttondisabled : button) {
        if (buttondisabled.getText().equals(buttonName)) {
          return new TestAcceptanceMessage(true, result);
        }
      }
    }
    return new TestAcceptanceMessage(false, buttonName + " Button is not clicked.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#getQueryname()}.
   *
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyGetQueryName(final String lastResult) {
    String queryname =
        this.driverCustom.getWebElement(CCMConstants.CCMCREATEREPORTFROMRESOURCEPAGE_REPORTNAME_TEXTBOX_XPATH)
            .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE);
    if (queryname.contains(lastResult)) {
      String result = "Verified query name present in the name text box .\n Actual query name is \"" + queryname +
          "\" and \n Expected query name \"" + queryname + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false, queryname + " Query name Not found from Query name textbox.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#clickOnEditCurrentQuery()}.
   *
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyClickOnEditCurrentQuery(final String lastResult) {
    this.driverCustom.isElementInvisible("//table[@class='queryResultsTable']", Duration.ofSeconds(50));
    this.driverCustom.getPresenceOfWebElement("//div[contains(@style,'display: none') and text()='Opening Query...']");
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEQUERYPAGE_EDITCURRENT_QUERY_TITLE_XPATH);
    this.driverCustom.getPresenceOfWebElement("//div[text()='Conditions']");
    List<WebElement> tab =
        this.driverCustom.getWebElements(CCMConstants.CCMCREATEQUERYPAGE_EDITCURRENT_QUERY_TITLE_XPATH);
    ArrayList<String> nameoftab = new ArrayList<>();
    for (WebElement tabnames : tab) {
      nameoftab.add(tabnames.getText());
      if (tabnames.getText().equals("Conditions")) {
        String result = "Verified Conditions Tab Name present in the Query .\n Actual Tab name contains \"" +
            tabnames.getText() + " \" and \n Expected Tab Name " + tabnames.getText() + "";
        return new TestAcceptanceMessage(true, result);
      }
    }
    return new TestAcceptanceMessage(false, "Conditions Tab name not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#clickOnQuery(String)}.
   *
   * @param queryname name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyClickOnQuery(final String queryname, final String lastResult) {
    if (queryname.contains("...")) {
      String result =
          "Verified by text of query name present in the Assign Values to Query Parameters dialog box .\n Actual query name is \"" +
              queryname + CCMConstants.CCMCREATEQUERYPAGE_QUERY_EXPECTEDNAME + queryname + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    WebElement submenus =
        this.driverCustom.getFirstVisibleWebElement(CCMConstants.CCMCREATEQUERYPAGE_QUERY_TITLE_XPATH, queryname);
   
    if (submenus.getText().contains(queryname)) {
      String result = "Verified by text of query name present in the name text box .\n Actual query name is \"" +
          submenus.getText() + "\" \n Expected query name\"" + submenus.getText() + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    if (submenus.getText().contains("...")) {
      String result = "Verified by text of query name present in the name text box .\n Actual query name is \"" +
          submenus.getText() + "\" \n Expected query name\"" + submenus.getText() + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false, queryname + " Verified query name not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#selectTab(String)}.
   *
   * @param Type is the name of tab.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySelectTab(final String Type, final String lastResult) {
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEQUERYPAGE_EDITCURRENT_QUERY_TITLE_XPATH);
    List<WebElement> tabs = this.driverCustom.getWebElements("//div[@class='title']");
    ArrayList<String> nameoftabs = new ArrayList<>();
    for (WebElement tabsnames : tabs) {
      nameoftabs.add(tabsnames.getText());
      if (tabsnames.getText().equals(Type)) {
        String result = "Verified \"" + Type + "\" Tab Name present in the Query .\n Actual Tab name \"" +
            tabsnames.getText() + "\" and \n Expected Tab Name \"" + tabsnames.getText() + "\"";
        return new TestAcceptanceMessage(true, result);
      }
    }
    return new TestAcceptanceMessage(false, "\"" + Type + "\" Verified Tab Name not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#searchQueryName(String)}.
   *
   * @param queryname name of project area.
   * @param additionalparameter for assert true.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySearchQueryName(final String queryname, final String additionalparameter,
      final String lastResult) {
    List<WebElement> querynames =
        this.driverCustom.getWebElements("//div[@class='entryChildren']//a[contains(@href,'runSavedQuery')]");
    for (WebElement nametext : querynames) {
      if (queryname.contains(nametext.getText())) {
        return new TestAcceptanceMessage(true,
            " Verified Actual Query name is" + queryname + " matched with Expected Query name is" + queryname + ".");
      }
    }
    return new TestAcceptanceMessage(false, queryname + " Verified Query name Not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#editDescription(String)}.
   *
   * @param description Description of Query.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyEditDescription(final String description, final String lastResult) {
    String descriptionvalue = this.driverCustom.getWebElement("//div[@class='sectionContent']/textarea")
        .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE);
    if (descriptionvalue.equals(description)) {
      return new TestAcceptanceMessage(true, descriptionvalue +
          " Verified Actual Description is matched with \n Expected Description " + descriptionvalue + ".");
    }
    return new TestAcceptanceMessage(false, description + " Verified Description value not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#selectTeamAreaOrProjectArea(String, String)}.
   *
   * @param ProjectArea name of project area.
   * @param TeamArea name of the Team area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySelectTeamAreaOrProjectArea(final String ProjectArea, final String TeamArea,
      final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.isElementPresent("//div[@dojoattachpoint='_teamAreaSharingColumn']//td", Duration.ofSeconds(10));
    List<WebElement> teamarea =
        this.driverCustom.getWebElements("//div[@dojoattachpoint='_teamAreaSharingColumn']//td");
    for (WebElement teamareatext : teamarea) {
      if (teamareatext.getText().equals(TeamArea)) {
        return new TestAcceptanceMessage(true, "Verified Actual Team area " + teamareatext.getText() +
            "is matched with \n Expected team area " + TeamArea + "\"");
      }
    }
    return new TestAcceptanceMessage(false, TeamArea + "Verified Team area name Not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#addUser(String, String)}.
   *
   * @param UserId name of project area.
   * @param UserValue name of the Team area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyAddUser(final String UserId, final String UserValue, final String lastResult) {
    this.driverCustom.getPresenceOfWebElement("//div[@dojoattachpoint='_userSharingColumn']//td");
    List<WebElement> user = this.driverCustom.getWebElements("//div[@dojoattachpoint='_userSharingColumn']//td");
    for (WebElement usertext : user) {
      if (usertext.getText().equals(UserValue)) {
        return new TestAcceptanceMessage(true,
            usertext.getText() + ": Verified Actual Team area is matched with \n Expected team area " + UserValue);
      }
    }
    return new TestAcceptanceMessage(false, UserValue + " Verified user name Not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#deleteDescription()}.
   *
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyDeleteDescription(final String lastResult) {
    String descriptionvalue = this.driverCustom.getWebElement("//div[@class='sectionContent']/textarea")
        .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE);
    if (descriptionvalue.equals("")) {
      return new TestAcceptanceMessage(true, " Verified Description is removed.");
    }
    return new TestAcceptanceMessage(false, "Verified Description value is Not Removed.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#removeTeamArea(String)}.
   *
   * @param TeamArea name of the Team area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyRemoveTeamArea(final String TeamArea, final String lastResult) {
    WebElement ele =
        this.driverCustom.getWebElement("//div[@title='Team and Project Area Sharing']//div[@class='sectionContent']");
    if (!ele.getText().contains(TeamArea)) {
      return new TestAcceptanceMessage(true, " Verified Team area is Removed.");
    }
    return new TestAcceptanceMessage(false, "Verified Team area is not removed.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#removeUser(String)}.
   *
   * @param user name of the Team area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyRemoveUser(final String user, final String lastResult) {
    WebElement elem = this.driverCustom.getWebElement("//div[@title='User Sharing']//div[@class='sectionContent']");
    if (!elem.getText().contains(user)) {
      return new TestAcceptanceMessage(true, " Verified User is Removed.");
    }
    return new TestAcceptanceMessage(false, user + "Verified User is not remmoved.");
  }

  /**
   * <p>
   * Verifies the action of {@link AbstractJazzWebPage#logOutWithUrl(String)}.
   *
   * @param repositoryUrl name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifylogOutWithUrl(final String repositoryUrl, final String lastResult) {
    if (this.driverCustom.isTitleContains("Logout", Duration.ofSeconds(20))) {
      return new TestAcceptanceMessage(true, " Verified User is Logged out successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified User is not Logged out.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#getDetails(String)}.
   *
   * @param heading name of project area.
   * @param additionalparameter parameter.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyGetDetails(final String heading, final String additionalparameter,
      final String lastResult) {
    List<WebElement> details =
        this.driverCustom.getWebElements(CCMConstants.CCMCREATEQUERYPAGE_QUERY_SECTIONDETAILS_XPATH, heading);
    for (WebElement webElement : details) {
      if (webElement.getText().contains(lastResult)) {
        return new TestAcceptanceMessage(true,
            webElement.getText() + ": Verified Actual result matched with \n Expected Result" + lastResult);
      }
    }
    return new TestAcceptanceMessage(false, "Expected Result" + lastResult + " is not matched with Actual.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#openSubMenu(String)}.
   *
   * @param subMenu name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyOpenSubMenu(final String subMenu, final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//div[@class='navbar']//a[contains(@aria-label,'Drop-Down Menu') and @aria-expanded='false']", Duration.ofSeconds(40))) {
      return new TestAcceptanceMessage(true,
          "Verified opening of \"" + subMenu +
              "\" by closing the drop down and opening the new page. \n Actual result is " + subMenu +
              " opened and Expectedresult is \"" + subMenu + "\" opened.");
    }
    return new TestAcceptanceMessage(false, "Verified opening of \"" + subMenu +
        "\" by closing the drop down and opening the new page. \n Actual result is " + subMenu + " not opened.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#deleteQuery(String)}.
   *
   * @param queryname name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyDeleteQuery(final String queryname, final String lastResult) {
    if (!this.driverCustom.isElementVisible(CCMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(20), queryname)) {
      return new TestAcceptanceMessage(true, "Verified deleted query  \"" + queryname +
          "\" by searching from the query search box. \n Actual result is " + queryname + " is Deleted.");
    }
    return new TestAcceptanceMessage(false, "Verified deleted query  \"" + queryname +
        "\" by searching from the query search box. \n Actual result is " + queryname + " is not Deleted.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#clickAddCondition()}.
   *
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyClickAddCondition(final String lastResult) {
    if (this.driverCustom.isElementVisible("//input[@placeholder='Type Filter Text']",
        this.driverCustom.getWebDriverSetup().getConfigurationForImplicitWaitTime())) {
      return new TestAcceptanceMessage(true,
          " Verified Add condition Button clicked. \n Actual result is \"search field is visible\" and \n Expected " +
              "result is \"search field is visible\".");
    }
    return new TestAcceptanceMessage(false,
        " Verified Add condition Button is not clicked \"search field is not visible\".");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#selectItemInListBox(String)}.
   *
   * @param text name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySelectItemInListBox(final String text, final String lastResult) {
    if (this.driverCustom.isElementVisible("//div[@class='simpleItem' and @aria-selected='true']",
        this.driverCustom.getWebDriverSetup().getConfigurationForImplicitWaitTime())) {
      return new TestAcceptanceMessage(true, " Verified condition type is selected. \n Actual result is \"" + text +
          "\" condition selected. and \n Expected result is \"" + text + "\" condition selected.");
    }
    return new TestAcceptanceMessage(false,
        CCMConstants.CCMCREATEQUERYPAGE_QUERY_CONDITION + text + CCMConstants.CCMCREATEQUERYPAGE_QUERY_NOTSELECTED);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#clickOnAddAttributeCondition()}.
   *
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyClickOnAddAttributeCondition(final String lastResult) {
    List<WebElement> ele = this.driverCustom.getWebElements("//div[@class='top-section']");
    ArrayList<String> test = new ArrayList<>();
    for (WebElement iterate : ele) {
      test.add(iterate.getText());
    }
    if (test.size() > 1) {
      return new TestAcceptanceMessage(true,
          " Verified Add attribute condition button is Clicked. \n Actual result is \"" +
              CCMConstants.CCMCREATEQUERYPAGE_QUERY_ADDATTRIBUTE_CONDITION +
              "\" button is Clicked.\n Expected result is \"" + "Add attribute condition" + "\" button is Clicked.");
    }
    return new TestAcceptanceMessage(false,
        CCMConstants.CCMCREATEQUERYPAGE_QUERY_CONDITION + "Add attribute condition" + "\" is not Clicked.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#filterText(String)}.
   *
   * @param text name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyFilterText(final String text, final String lastResult) {
    List<WebElement> label = this.driverCustom.getWebElements("//div[@class='simpleItem']");
    for (WebElement labelname : label) {
      if (labelname.getText().equalsIgnoreCase(text)) {
        String result = "Verified filtered attribute \"" + labelname.getText() + "\" is visible.\n Actual result \"" +
            labelname.getText() + "\" attribute is visible. \n Expected result \"" + text + "\" attribute is visible.";
        return new TestAcceptanceMessage(true, result);
      }
    }
    return new TestAcceptanceMessage(false, "Verified filtered attribute \"" + text + "\" attribute is not visible.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#clickOnSelectType(String)}.
   *
   * @param Type name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyClickOnSelectType(final String Type, final String lastResult) {

    List<WebElement> ele = this.driverCustom.getWebElements("//a[contains(@class,'entry')]");
    for (WebElement test : ele) {
      if (test.getAttribute("aria-selected").equals("true") && test.getText().equalsIgnoreCase(Type)) {
        return new TestAcceptanceMessage(true, " Verified Workitem type is selected. \n Actual result is \"" + Type +
            "\" selected. and \n Expected result is \"" + Type + "\" selected.");
      }
    }
    return new TestAcceptanceMessage(false, " Verified condition \"" + Type + "\" is not selected.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#clickOnSelectTypeWithTitle(String, String)}.
   *
   * @author VDY1HC
   * @param text - selected option
   * @param title - title of condition box
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyClickOnSelectTypeWithTitle(final String title, final String text,
      final String lastResult) {
    WebElement selectItemInListBox = this.driverCustom.getWebElement("//span[@title='" + title +
        "']/ancestor::div[@class='top-section']/following-sibling::div[@class='middle-section']//a[@role='option' and @aria-label='" +
        text + "']");
    String selectedAttribute = selectItemInListBox.getAttribute("aria-selected");
    if (selectedAttribute.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Selected option: " + text + " for condition " + title);
    }
    return new TestAcceptanceMessage(false,
        "Verified: FAILED - Unable to selected option: " + text + " for condition " + title);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#clickOnSelectType(String)}.
   *
   * @param Type name of project area.
   * @param number -
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySelectOptionInDropDownBYIndex(final String Type, final String number,
      final String lastResult) {
    List<WebElement> ele = this.driverCustom.getWebElements("//a[contains(@class,'entry')]");
    for (WebElement test : ele) {
      if (test.getAttribute("aria-selected").equals("true") && test.getText().equalsIgnoreCase(Type)) {
        return new TestAcceptanceMessage(true, " Verified Workitem type is selected. \n Actual result is \"" + Type +
            "\" selected. and \n Expected result is \"" + Type + "\" selected.");
      }
    }
    return new TestAcceptanceMessage(false, " Verified condition \"" + Type + "\" is not selected.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#getListOfContentsForEachColumn(String)}.
   *
   * @param columnName name of project area.
   * @param additionalparameter parameter
   * @param sortingtype sorting.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyGetListOfContentsForEachColumn(final String columnName,
      final String additionalparameter, final String sortingtype, final String lastResult) {
    int count = 0;
    if ((lastResult != null) && !lastResult.isEmpty()) {
      String[] getvalues = lastResult.split(Pattern.quote("^"));
      ArrayList<Integer> columndata = new ArrayList<>();
      if (columnName.equalsIgnoreCase("id")) {
        for (String datas : getvalues) {
          columndata.add(Integer.parseInt(datas));
        }
      }
      else if (columnName.equalsIgnoreCase("Summary")) {
        String temp = this.driverCustom.getFirstVisibleWebElement("//span[@class='pageInfoSpan']/b[2]", null).getText();
        count = Integer.parseInt(temp);
        return new TestAcceptanceMessage(lastResult.contains(additionalparameter),
            CCMConstants.VERIFY_THE_DISPLAYED_RESULTS_AS_PER_THE_QUERY_CONDITION + count +
                CCMConstants.ITEMS_FOUND_UNDER + columnName + CCMConstants.COLUMN);
      }
      if (columnName.equalsIgnoreCase("Parent") || columnName.equalsIgnoreCase("Children")) {
        return new TestAcceptanceMessage(lastResult.contains(additionalparameter),
            "Verify the displayed results as per the Query conditions.\n\nActual result is '" + additionalparameter +
                "' link found under '" + columnName + "' column.");
      }
      count = getvalues.length;
      List<String> arr = new ArrayList<>();
      if (sortingtype.equalsIgnoreCase("Ascending")) {
        Collections.sort(columndata);
        arr = columndata.stream().map(x -> String.valueOf(x)).collect(Collectors.toList());
        String ord = String.join("^", arr);
        if (lastResult.equals(ord)) {
          return new TestAcceptanceMessage(true,
              "Actual result after sort in Ascending order is " + ord.replaceAll("\\^", ","));
        }
      }
      else if (sortingtype.equalsIgnoreCase("Descending")) {
        Collections.sort(arr, Collections.reverseOrder());
        String ord = String.join("^", arr);
        if (lastResult.equals(ord)) {
          return new TestAcceptanceMessage(true, "Actual result after sort in Descending order is " + arr);
        }
      }
      else {
        for (String s : getvalues) {
          if (s.equals(additionalparameter)) {
            return new TestAcceptanceMessage(lastResult.contains(additionalparameter),
                "Verify the displayed results as per the Query condition is matching.\n\nActual result is '" +
                    getvalues.length + "' items found under '" + columnName +
                    "' column and the list of item displayed \n" + lastResult.replaceAll("\\^", ",") + ".");
          }
        }
      }
    }
    return new TestAcceptanceMessage(false,
        "Verify the displayed results as per the Query conditions.\n\nActual result is '" + count +
            "' items found under '" + columnName + "' column.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#setId(String)}.
   *
   * @param idValue name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySetId(final String idValue, final String lastResult) {
    String queryname =
        this.driverCustom.getWebElement("//span[@title='Id']/ancestor::div[@class='top-section']/..//input")
            .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE);
    if (idValue.contains(queryname)) {
      String result = "Actual ID value is \"" + idValue + "\" \n Expected ID value is\"" + idValue + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false, queryname + " Verified ID value not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#getEnableInputQueryName(String)}.
   *
   * @param query name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyGetEnableInputQueryName(final String query, final String lastResult) {
    if (lastResult.endsWith("...")) {
      String result = "Actual query name is \"" + lastResult + "\" \n Expected query name\"" + lastResult + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false, lastResult + " Verified Query name not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#enableInputConditionValuesWhenQueryIsRun()}.
   *
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyEnableInputConditionValuesWhenQueryIsRun(final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//a[@title='Disable Input of Condition Values when Query is Run' and @aria-pressed='true']",
        this.driverCustom.getWebDriverSetup().getConfigurationForImplicitWaitTime())) {
      return new TestAcceptanceMessage(true,
          " Verified Enable Input of Condition Values when Query is Run Button clicked. \n Actual result is \"Enable " +
              "Input of Condition Values when Query is Run \" button is clicked. and \n " +
              "Expected result is \"Enable Input of Condition Values when Query is Run\" button is clicked.");
    }
    return new TestAcceptanceMessage(false,
        " Verified Enable Input of Condition Values when Query is Run Button is not clicked.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#setAttributeTextBox(String, String)}.
   *
   * @param AttributeName name of project area.
   * @param value name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySetAttributeTextBox(final String AttributeName, final String value,
      final String lastResult) {
    if (AttributeName.contains("Assign Values to Query Parameters")) {
      String result = "Actual " + AttributeName + " value is \"" + value + "\" \n Expected " + AttributeName +
          " value is\"" + value + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    String queryname = this.driverCustom
        .getWebElement(
            "//span[text()='" + AttributeName + "']/ancestor::div[@class='top-section']/..//input[@role='textbox']")
        .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE);
    if (value.contains(queryname)) {
      String result = "Actual " + AttributeName + " value is \"" + value + "\" \n Expected " + AttributeName +
          " value is\"" + value + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false, queryname + " Verified " + AttributeName + " value not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#editQuery(String)}.
   *
   * @param queryname name of the query.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyEditQuery(final String queryname, final String lastResult) {
    this.driverCustom.getPresenceOfWebElement("//div[@class='title']");
    String querynames =
        this.driverCustom.getWebElement(CCMConstants.CCMCREATEREPORTFROMRESOURCEPAGE_REPORTNAME_TEXTBOX_XPATH)
            .getAttribute(CCMConstants.VALUE);
    if (querynames.contains(queryname)) {
      String result = "Verified " + queryname + " Edit button is clicked.\n Actual Result \"" + queryname +
          "\" Edit button is clicked. \n Expected Result \"" + queryname + "\" Edit button is clicked.";
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false, queryname + " Edit button is not clicked.");
  }


  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#addColumn(String)}.
   *
   * @param columnName name of the Team area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyAddColumn(final String columnName, final String lastResult) {
    List<WebElement> user = this.driverCustom.getWebElements("//div[@dojoattachpoint='_resultsColumn']//td");
    for (WebElement usertext : user) {
      if (usertext.getText().equals(columnName)) {
        return new TestAcceptanceMessage(true,
            usertext.getText() + CCMConstants.CCMCREATEQUERYPAGE_QUERY_ACTUAL_EXPECTED);
      }
    }
    return new TestAcceptanceMessage(false, columnName + CCMConstants.CCMCREATEQUERYPAGE_QUERY_COLUMN_NAME);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#setColumnOrder(String, String, String)}.
   *
   * @param sectiontitle section name.
   * @param column name of the Team area.
   * @param button name of button.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySetColumnOrder(final String sectiontitle, final String column, final String button,
      final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    if (this.driverCustom.getWebElement("//tr[@class='tableEntry selected' or @class='selected']").getText()
        .equalsIgnoreCase(column)) {
      return new TestAcceptanceMessage(true,
          column + ": Verified Actual Column Name is matched with \n Expected Column Name " + column);

    }

    else if (this.driverCustom.getWebElement("//tr[@class='selected']").getText().equalsIgnoreCase(column)) {
      return new TestAcceptanceMessage(true,
          column + ": Verified Actual Column Name is matched with \n Expected Column Name " + column);
    }
    return new TestAcceptanceMessage(false, column + " Verified Column Name Not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#addSortColumn(String)}.
   *
   * @param columnName name of the Team area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyAddSortColumn(final String columnName, final String lastResult) {
    List<WebElement> user = this.driverCustom.getWebElements("//div[@dojoattachpoint='_orderColumn']//td");
    for (WebElement usertext : user) {
      if (usertext.getText().equals(columnName)) {
        return new TestAcceptanceMessage(true,
            columnName + ": Verified Actual Column Name is matched with \n Expected Column Name " + columnName);
      }
    }
    return new TestAcceptanceMessage(false, columnName + " Verified Column Name Not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#setSortingOrderType(String, String)}.
   *
   * @param columnName name of the Team area.
   * @param sortingordertype sorting type.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySetSortingOrderType(final String columnName, final String sortingordertype,
      final String lastResult) {
    this.driverCustom.click(CCMConstants.CCMQUERYPAGE_SORTORDER_DROPDOWN_XPATH, columnName);
    WebElement ele = this.driverCustom.getWebElement(CCMConstants.CCMQUERYPAGE_SORTORDER_DROPDOWN_XPATH, columnName);
    Select sel = new Select(ele);
    if (sel.getFirstSelectedOption().getText().equalsIgnoreCase(sortingordertype)) {
      return new TestAcceptanceMessage(true, sortingordertype +
          ": Verified Actual column sorting type is matched with \n Expected column sorting type " + sortingordertype);
    }
    return new TestAcceptanceMessage(false, sortingordertype + " Verified column sorting type not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#validateResultColumns()}.
   *
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyValidateResultColumns(final String lastResult) {
    if (!lastResult.contains("not same as columns displayed in the table")) {
      return new TestAcceptanceMessage(true,
          "Verified result coloums against coloums dispalyed in table\n\nActual result is " + lastResult);
    }
    return new TestAcceptanceMessage(false,
        "Verified result coloums against coloums dispalyed in table\n\nActual result is " + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#selectConditionType(String, String)}.
   *
   * @param conditionTitle title of Condition.
   * @param selectOption option name.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySelectConditionType(final String conditionTitle, final String selectOption,
      final String lastResult) {
    List<WebElement> menu = this.driverCustom.getWebElements("//span[@dojoattachpoint='labelSpan']");
    for (WebElement webElement : menu) {
      if (webElement.getText().equalsIgnoreCase(selectOption)) {
        return new TestAcceptanceMessage(true, " Verified Actual Menu Name \"" + selectOption +
            "\" is matched with \n Expected Menu Name \"" + selectOption + "\"");
      }
    }
    return new TestAcceptanceMessage(false, "Menu Name not Found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#searchDeletedQuery(String)}.
   *
   * @param queryname name of project area.
   * @param additionalparameter parameter.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySearchDeletedQuery(final String queryname, final String additionalparameter,
      final String lastResult) {
    if (!this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_LINK_TYPE_XPATH, Duration.ofSeconds(10), queryname)) {
      return new TestAcceptanceMessage(true,
          " Verified Actual Query \"" + queryname + "\" is deleted. \n Expected Query " + queryname + "\" is deleted.");
    }
    return new TestAcceptanceMessage(false, queryname + " Verified Query is not deleted..");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#validateWildcardsTextBox()}.
   *
   * @param lastResult last result of method.
   * @param additionalparameter parameter.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyValidateWildcardsTextBox(final String additionalparameter,
      final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Validated text box is showing error:" + lastResult);
    }
    return new TestAcceptanceMessage(false, "Validated text box is not showing error :" + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#validateNoWorkItemsFound()}.
   *
   * @param lastResult last result of method.
   * @param additionalparameter parameter
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyValidateNoWorkItemsFound(final String additionalparameter,
      final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Validated No work items found :" + lastResult);
    }
    if (!Boolean.valueOf(additionalparameter)) {
      return new TestAcceptanceMessage(true, "Work items found for the searched query.");
    }
    return new TestAcceptanceMessage(false, "Work items found for the searched query.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#isResultsFound()}.
   *
   * @param lastResult last result of method.
   * @param additionalparameter parameter
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyIsResultsFound(final String additionalparameter, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Actual Result is running query with '" + additionalparameter +
              "' value  shows results in the query table.\n\n" + "Expected is results should be displayed for '" +
              additionalparameter + "' value.");
    }

    return new TestAcceptanceMessage(false,
        "Actual Result is running query with '" + additionalparameter +
            "' value does'nt  shows results in the query table.\n\n" + "Expected is results should be displayed for '" +
            additionalparameter + "' value.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#clickOnCloseButton()}.
   *
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyClickOnCloseButton(final String lastResult) {
    if (!this.driverCustom.isElementVisible("//a[@class='closeButton']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Validated all Attribute widgets are closed :" + lastResult);
    }
    return new TestAcceptanceMessage(false, "Validated Attribute widgets are not closed:" + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#selectConditionTypeByIndex(String, String, String)}.
   *
   * @param conditionTitle title of Condition.
   * @param selectOption option name.
   * @param index number of index.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySelectConditionTypeByIndex(final String conditionTitle, final String selectOption,
      final String index, final String lastResult) {
    List<WebElement> menus = this.driverCustom.getWebElements("//span[@dojoattachpoint='labelSpan']");
    for (WebElement webElement : menus) {
      if (webElement.getText().equalsIgnoreCase(selectOption)) {
        return new TestAcceptanceMessage(true, " Verified Actual Menu Name \"" + selectOption +
            "\" is matched with \n Expected Menu Name \"" + selectOption + "\"");
      }
    }
    return new TestAcceptanceMessage(false, "Menu Name not Found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#selectValueFromCondition(String, String, String)}.
   *
   * @param condition type.
   * @param index number.
   * @param type name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyselectValueFromCondition(final String condition, final String type,
      final String index, final String lastResult) {
    List<WebElement> ele = this.driverCustom.getWebElements("//a[contains(@class,'entry')]");
    for (WebElement test : ele) {
      if (test.getAttribute("aria-selected").equals("true") && test.getText().equalsIgnoreCase(type)) {
        return new TestAcceptanceMessage(true, " Verified Workitem type is selected. \n Actual result is \"" + type +
            "\" selected. and \n Expected result is \"" + type + "\" selected.");
      }
    }
    return new TestAcceptanceMessage(false, " Verified condition \"" + type + "\" is not selected.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#setAttributeTextBoxByIndex(String, String, String)}.
   *
   * @param conditionTitle title of condition.
   * @param value value of text box.
   * @param index number.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySetAttributeTextBoxByIndex(final String conditionTitle, final String value,
      final String index, final String lastResult) {
    String queryname = this.driverCustom
        .getWebElement("//span[@title='" + conditionTitle + "']/ancestor::div[@class='top-section']/..//input")
        .getAttribute("value");
    if (value.contains(queryname)) {
      String result = "Actual ID value is \"" + value + "\" \n Expected ID value is\"" + value + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false, queryname + " Verified ID value not found.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#clickOnDialogButton(String, String)}.
   *
   * @param dialog title of condition.
   * @param button value of text box.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyclickOnDailogButton(final String dialog, final String button,
      final String lastResult) {
    String result = "\"" + button + "\" button Clicked Successfully Verified .\n Actual Result \"" + button +
        "\" Clicked successfully and \n Expected Result \"" + button + "\" Clicked successfully";
    if (button.contains("Run")) {
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false, button + " not found.");
  }


  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#validateColumnContent(String, String)}.
   *
   * @param lastResult last result of method.
   * @param columnName parameter
   * @param columnValue value
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyvalidateColumnContent(final String columnName, final String columnValue,
      final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Actual Result \"" + columnName + "\" column contains only \"" +
          columnValue + "\" value, which matches the Query condition.");
    }
    return new TestAcceptanceMessage(false,
        "Validated \"" + columnName + "\" column doesn't contains only \"" + columnValue + "\" value.");
  }


  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#clickOnDialogButton(String, String)}.
   *
   * @param lastResult last result of method.
   * @param dialog parameter
   * @param button value
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyDialogButton(final String dialog, final String button, final String lastResult) {
    Dialog dlgNewFilter = null;
    try {
      dlgNewFilter = this.engine.findElementWithinDuration(Criteria.isDialog().withTitle(dialog), Duration.ofSeconds(10)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (dlgNewFilter != null) {
      return new TestAcceptanceMessage(false,
          "The '" + dialog + "' is displayed after click on '" + button + "' button.");
    }
    return new TestAcceptanceMessage(true,
        "The '" + dialog + "' is not displayed after click on '" + button + "' button.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#queryResultValidation()}.
   *
   * @param creationDateAfterInc parameter
   * @param creationDateBeforeInc parameter.
   * @param totalDatesCount dates count.
   * @param sortingtype sorting.
   * @param lastResult last result of method.
   * @return acceptance message.
   * @throws ParseException exception.
   */
  public TestAcceptanceMessage verifyQueryResultValidation(final String creationDateAfterInc,
      final String creationDateBeforeInc, final String totalDatesCount, final String sortingtype,
      final String lastResult)
      throws ParseException {

    boolean res = false;
    String totalDates = "";
    try {
      totalDates = this.driverCustom.getWebElement("(//span[@class='pageInfoSpan'])[1]//b[3]").getText();
    }
    catch (Exception e) {
      totalDates = this.driverCustom.getWebElement("(//span[@class='pageInfoSpan'])[1]//b[2]").getText();
    }
    if (totalDates.equals(totalDatesCount)) {
      res = true;
    }
    return new TestAcceptanceMessage(res,
        "Total number of data found is : " + totalDates + " \n Expexted number of data is : " + totalDatesCount);
  }

  /**
   * @author UUM4KOR
   * @param QueryResultCount -
   * @param lastResult -
   * @return -
   */
  public TestAcceptanceMessage verifyQueryResultCount(final String QueryResultCount, final String lastResult) {
    boolean res = false;
    if (lastResult.equals(QueryResultCount)) {
      res = true;
      return new TestAcceptanceMessage(res, "Verified: PASSED - Number of work item return after query is : " +
          lastResult + " \nExpexted number of work item after query is : " + QueryResultCount);
    }
    return new TestAcceptanceMessage(res,
        "Verified: FAILED - Unable to get number of work item return after query \nActual result:" + lastResult +
            " \nExpexted number of work item return after query : " + QueryResultCount);
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#queryResultValidation()} <br>
   *
   * @author VDY1HC
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyQueryResultValidation(final String lastResult) {
    try {
      Integer.parseInt(lastResult);
      return new TestAcceptanceMessage(true,
          "Verified: PASSED - Number of work item return after query is " + lastResult);
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified: FAILED - Unable to get number of work item return after query \nActual result: " + lastResult);
    }
  }

  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#setAttributeTextBoxByIndexDate(Map)}.
   *
   * @param additionalParams refers to key value pair.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifySetAttributeTextBoxByIndexDate(final Map<String, String> additionalParams,
      final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    List<WebElement> datePickerInput = this.driverCustom.getWebElements("//div[@class='DatePicker']");
    String inputDate = null;
    int countForDisplayed = 0;
    if (datePickerInput.size() > 1) {
      for (int i = 0; i < datePickerInput.size(); i++){
        if (datePickerInput.get(i).isEnabled()) {
          countForDisplayed++;
          if (countForDisplayed == Integer.parseInt(additionalParams.get("index"))) {
            inputDate = this.driverCustom.getWebElement("(//div[@class='DatePicker'])[" + additionalParams.get("index") + "]//input").getAttribute("value");
            break;
          }
        }
      }
    }
    else {
      inputDate = this.driverCustom.getWebElement("//div[@class='DatePicker']//input").getAttribute("value");
    }
    if (additionalParams.get("value").contains(inputDate)) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Input date in text box with expected value." +
          "\nActual value: " + inputDate + "\nExpected value: " + additionalParams.get("value"));
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Unable to input date in text box with expected value." +
        "\nActual value: " + inputDate + "\nExpected value: " + additionalParams.get("value"));
  }

  /**
   * @param columnName column Name
   * @param additionalparameter -
   * @param lastResult string column value
   * @return string
   */
  public TestAcceptanceMessage verifyGetSelectedColumnValue(final String columnName, final String additionalparameter,
      final String lastResult) {
    List<String> pcrlist = new ArrayList<>();
    List<String> pcrlist1 = new ArrayList<>();
    String str = lastResult;
    String[] res = str.split("[,]", 0);
    int allcount1 = res.length;
    for (String myStr : res) {
      if (myStr.toLowerCase().contains(additionalparameter.toLowerCase())) {
        pcrlist.add(myStr);
      }
    }
    for (String myStr : res) {
      if (!myStr.toLowerCase().contains(additionalparameter.toLowerCase())) {
        {
          pcrlist1.add(myStr);
          LOGGER.LOG.info("Not matching query result is : " + pcrlist1);
        }
      }
    }
    if (res.length == pcrlist.size()) {
      return new TestAcceptanceMessage(true, "Verified Actual Query result count is : " + allcount1 + ".\n'" +
          additionalparameter + "' is present in all query result.\nAll query result is :  " + pcrlist);
    }
    return new TestAcceptanceMessage(false,
        "Verified Actual Query result count is : " + allcount1 + ".\n\n\n Not Matching all query result is: " +
            pcrlist1 + ".\n\n\n Actual Query result not contains '" + additionalparameter +
            "'.\nAll query result is : " + pcrlist);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEQUERYPAGE_WORKITEMS_LINK_XPATH);

  }
  
  /**
   * <p>
   * Verifies the action of {@link CCMCreateQueryPage#clickOnSelectFiledAgainst(String)}.
   *
   * @param Type name of project area.
   * @param lastResult last result of method.
   * @return acceptance message.
   */
  public TestAcceptanceMessage verifyClickOnSelectFiledAgainst(final String Type, final String lastResult) {

    List<WebElement> ele = this.driverCustom.getWebElements("//a[contains(@class,'entry')]");
    for (WebElement test : ele) {
      if (test.getAttribute("aria-selected").equals("true") && test.getText().equalsIgnoreCase(Type)) {
        return new TestAcceptanceMessage(true, " Verified Filed Against is selected. \n Actual result is \"" + Type +
            "\" selected. and \n Expected result is \"" + Type + "\" selected.");
      }
    }
    return new TestAcceptanceMessage(false, " Verified condition \"" + Type + "\" is not selected.");
  }

}