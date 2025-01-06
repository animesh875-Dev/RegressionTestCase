/*
 * d * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.AbstractJazzWebPage;
import com.bosch.jazz.automation.web.pagemodel.ManageProjectAreaPage;
import com.bosch.jazz.automation.web.pagemodel.PagemodelConstants;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Text;
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
public class ManageProjectAreaPageVerification extends AbstractJazzWebPage {

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    // TODO Auto-generated method stub
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    // TODO Auto-generated method stub
  }

  /**
   * @param driverCustom must not be null.
   */
  public ManageProjectAreaPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new LabelFinder(), new JazzLabelFinder(), new TextFieldFinder(), new JazzTextFieldFinder(),
        new LinkFinder(), new JazzTabFinder(), new DialogFinder());
  }

  /**
   * <p>
   * Verify actions do with member after
   * ${@link ManageProjectAreaPage#teamMemberActions(String, String, String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param Member name of Team Member
   * @param Action click on Edit/Add/Remove Process Roles, Invite to Join Team, Remove Member buttons
   * @param role Assigning to role to Team Member
   * @param button OK/Cancel/Next buttons
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyTeamMemberActions(final String Member, final String Action, final String role,
      final String button, final String lastResult) {

    Text textSearch = this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder("Search..."));
    textSearch.setText(Member);
    waitForSecs(3);
    if (Action.equals("Remove Member")) {
      try {
        this.driverCustom.getPresenceOfWebElement(
            "//div[@dojoattachpoint='membersDiv']//div[@class='memberName']//a[@class='jazz-ui-ResourceLink' and text()='" +
                Member + "']");
        return new TestAcceptanceMessage(false, "Verified: member '" + Member + "' is removed: FAILED!");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(true, "Verified: member '" + Member + "' is removed: PASSED!");
      }
    }
    String memberRoles = this.driverCustom.getPresenceOfWebElement(
        "//div[@dojoattachpoint='membersDiv']//div[@class='memberName']//a[@class='jazz-ui-ResourceLink' and contains(text(),'" +
            Member + "')]/following::div[1][@class='memberRoles']")
        .getText();

    if (Action.equals("Add Process Roles...")) {
      if (memberRoles.contains(role)) {
        return new TestAcceptanceMessage(true,
            "Role '" + role + "' is added for member '" + Member + "' successfully!");
      }
      return new TestAcceptanceMessage(false, "Role '" + role + "' is not added for member '" + Member + "'!");
    }
    if (Action.equals("Remove Process Roles...")) {
      if (memberRoles.contains(role)) {
        return new TestAcceptanceMessage(false, "Role '" + role + "' of member '" + Member + "' is not removed!");
      }
      return new TestAcceptanceMessage(true,
          "Role '" + role + "' of member '" + Member + "' is removed successfully!");
    }
    return new TestAcceptanceMessage(false, "Verify action '" + Action + "' for the member '" + Member +
        "' is failed because it does not match with all the cases that this verification method can covers!");
  }

  /**
   * <p>
   * Verify member is added with all roles after ${@link ManageProjectAreaPage#addMemberWithAllRoles(String)}
   * <p>
   *
   * @author NVV1HC
   * @param memberName name of member
   * @param lastResult returned value of method which is executed just before the method.
   * @return true if the member is added all roles or vice versa
   */
  public TestAcceptanceMessage verifyAddMemberWithAllRoles(final String memberName,
      final String lastResult) {
    refresh();
    waitForSecs(5);
//    Text textSearch =
//        this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Search..."), Duration.ofSeconds(60))
//        .getFirstElement();
    WebElement textSearch = this.driverCustom.getPresenceOfWebElement("//div[contains(@id,'FilterBox_1')]//input[@aria-label='Search text']");
    this.driverCustom.typeTextUsingActions(textSearch, memberName.trim());
    waitForSecs(5);
    Row row =
        this.engine.findElementWithDuration(Criteria.isRow().withText(memberName.trim()), Duration.ofSeconds(60))
        .getFirstElement();
    row.scrollToElement();
    row.hoverOnBottomElement();
    Dropdown drdMembersMenu =
        this.engine.findElementWithDuration(Criteria.isDropdown().withToolTip("Members Menu").inContainer(row), Duration.ofSeconds(60))
        .getFirstElement();
    drdMembersMenu.selectOptionWithText("Edit Process Roles...");
    boolean result1 =
        this.driverCustom.isElementInvisible("//select[@aria-label='Available Roles:']/option", Duration.ofSeconds(60));
    boolean result2 =
        this.driverCustom.isElementVisible("//select[@aria-label='Selected Roles:']/option", Duration.ofSeconds(60));
    boolean result = result1 & result2;
    try {
    clickOnJazzSpanButtons("OK");
    }
    catch(Exception e) {
      waitForSecs(1);
    }
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    if (result) {
      return new TestAcceptanceMessage(true,
          "Verified: member '" + memberName + "' is added with all the roles successfully!");
    }
    return new TestAcceptanceMessage(false,
        "Verified: member '" + memberName + "' is added with all the roles failed!");
  }

  /**
   * <p>
   * Verify the Team area is selected successfully after ${@link ManageProjectAreaPage#selectTeamArea(String)}
   * <p>
   *
   * @author NVV1HC
   * @param TeamName name of Team area
   * @param lastResult returned value of method which is executed just before the method.
   * @return true if the team area is selected successfully or vice versa
   */
  public TestAcceptanceMessage verifySelectTeamArea(final String TeamName, final String lastResult) {
    waitForSecs(3);
    String previewName = this.driverCustom
        .getPresenceOfWebElement("//span[@class='previewField']").getText();
    previewName.replace("&nbsp;", " ");
    if (TeamName.equals(previewName)) {
      return new TestAcceptanceMessage(true, "Verify select team area '" + TeamName + "' successfully!");
    }
    return new TestAcceptanceMessage(false,
        "Verify select team area '" + TeamName + "' failed!\nTeam area need to be selected as expected result: '" +
            TeamName + "'\nTeam area is selected actually: '" + previewName + "'");
  }

  /**
   * <p>
   * Verify click on "Explore Project" button successfully after ${@link ManageProjectAreaPage#exploreProjButton()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult returned value of method which is executed just before the method.
   * @return true if click on Project Area button successfully and navigate to the project page or the page to select
   *         Component with GC project, or vice versa
   */
  public TestAcceptanceMessage verifyExploreProjButton(final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//div[@role='tab' and @title='My Projects']|//div[@class='com-ibm-team-dashboard-content']",
        this.timeInSecs)) {
      return new TestAcceptanceMessage(true, "Click on 'Explore Project' button successfully!");
    }
    return new TestAcceptanceMessage(false, "Click on 'Explore Project' button failed!");
  }

  /**
   * Verify open tab successfully after {@link ManageProjectAreaPage#tabSection(String)} <br>
   * Handled for Permission tab and others tab (with others tab, you can update method for matching verification point) <br>
   *
   * @author VDY1HC
   * @param tabName - Name of tab to open
   * @param lastResult returned value of method which is executed just before the method.
   * @return true if loaded successfully and Page title is displayed match tab name
   */
  public TestAcceptanceMessage verifyTabSection(final String tabName, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Open section with name: " + tabName + " is selected and page loaded successfully");
    }
    return new TestAcceptanceMessage(false, "Unable to open section: " + tabName + ".");
  }

  /**
   * Verify method {@link ManageProjectAreaPage#createRole(Map)}
   *
   * @author KYY1HC
   * @param additionalParams includes 4 keys: IDENTIFIER, NAME, CARDINALITY and DESCRIPTION of new Role
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyCreateRole(final Map<String, String> additionalParams, final String lastResult) {
    String successfulMessage = "Project area saved successfully.";
    WebElement messageSummary = this.driverCustom.getWebElement("//div[@class='messageSummary']");
    String message = messageSummary.getText();
    String nameRole = additionalParams.get("NAME");
    WebElement newRole = this.driverCustom.getWebElement("//option[text()='" + nameRole + "']");
    if (newRole.isDisplayed() && message.equalsIgnoreCase(successfulMessage)) {
      return new TestAcceptanceMessage(true, "New role with name " + nameRole + " created successfully");
    } else if (!message.equalsIgnoreCase(successfulMessage)) {
      return new TestAcceptanceMessage(false, "Error message shown: " + message + " when trying to create new role.");
    } else {
      return new TestAcceptanceMessage(false, "Not found new role with name " + nameRole);
    }
  }


  /**
   * Verify method {@link ManageProjectAreaPage#grantOrRevokePermission(String, String, String)}
   * @author VDY1HC
   * @param roleName name of Role
   * @param permissionName name of permission
   * @param typePermission two values "Grant" if you want to grant permission, "Revoke" for revoking permission
   * @param lastResult returned value of method {@link ManageProjectAreaPage#grantOrRevokePermission(String, String, String)}.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGrantOrRevokePermission(final String roleName, final String permissionName, final String typePermission, final String lastResult) {
    this.driverCustom.isElementInvisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_DISPLAY_XPATH, Duration.ofSeconds(600));
    this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_PERMISSIONTABLE_LOADED_XPATH, Duration.ofSeconds(600));
    if (lastResult.equalsIgnoreCase("Role")) {
      return verifyGrantOrRevokePermissionForRole(roleName,permissionName,typePermission,lastResult);
    }
    return verifyGrantOrRevokePermissionForOperation(roleName,permissionName,typePermission,lastResult);
  }


  /**
   * Verify method {@link ManageProjectAreaPage#grantOrRevokePermission(String, String, String)} in case "Show by Operation"
   * @author VDY1HC
   * @param roleName name of Role
   * @param permissionName name of permission
   * @param typePermission two values "Grant" if you want to grant permission, "Revoke" for revoking permission
   * @param lastResult returned value of method {@link ManageProjectAreaPage#grantOrRevokePermission(String, String, String)}.
   * @return acceptance object which contains verification results.
   */
  private TestAcceptanceMessage verifyGrantOrRevokePermissionForOperation (final String roleName, final String permissionName, final String typePermission, final String lastResult) {
    if (this.driverCustom.isElementClickable(PagemodelConstants.MANAGEPROJECTAREAPAGE_SAVE_BTTUON_XPATH, Duration.ofSeconds(15))) {
      return new TestAcceptanceMessage (false, "Verified: FAILED - Unable to Save changes");
    }
    String actualPermission = this.driverCustom.getWebElement("//td[@dojoattachpoint='permissionsByActionName']").getText();
    if (!actualPermission.equalsIgnoreCase(permissionName)) {
      return new TestAcceptanceMessage (false, "Verified: FAILED - Permission name displayed is not match expected \nActual result: "
          + actualPermission + "\nExpected result: " + permissionName);
    }
    String expectedPermit = "Not Permitted";
    if (typePermission.equals("Grant")) {
      expectedPermit = "Permitted";
    }
    boolean result = this.driverCustom.isElementPresent("//td[text()='" + roleName + "']/parent::tr//td[@class='permissionColumn']//a[@title='" + expectedPermit + "']", Duration.ofSeconds(10));
    if (result) {
      return new TestAcceptanceMessage (true, "Verified: PASSED - Permission with role is set corrent " +
          "\nExpected result: Permission: " + permissionName + " - Role: " + roleName + " - Type: " + typePermission);
    }
    return new TestAcceptanceMessage (false, "Verified: FAILED - Permission with role is NOT set corrent " +
        "\nExpected result: Permission: " + permissionName + " - Role: " + roleName + " - Type: " + typePermission);

  }

  /**
   * Verify method {@link ManageProjectAreaPage#grantOrRevokePermission(String, String, String)} in case "Show by Role"
   *
   * @author KYY1HC
   * @param roleName name of Role
   * @param permissionName name of permission
   * @param typePermission two values "Grant" if you want to grant permission, "Revoke" for revoking permission
   * @param lastResult returned value of method {@link ManageProjectAreaPage#grantOrRevokePermission(String, String, String)}.
   * @return acceptance object which contains verification results.
   */
  private TestAcceptanceMessage verifyGrantOrRevokePermissionForRole (final String roleName, final String permissionName, final String typePermission, final String lastResult) {
    String successfulMessage = "Project area saved successfully.";
    WebElement messageSummary = this.driverCustom.getWebElement("//div[@class='messageSummary']");
    String message = messageSummary.getText();
    String xpathFilter = "//input[contains(@class,'filterText')]";
    this.driverCustom.isElementPresent(xpathFilter, this.timeInSecs);
    WebElement txtSearch = this.driverCustom.getWebElement(xpathFilter);
    txtSearch.clear();
    txtSearch.sendKeys(permissionName);
    WebElement permission = null;
    String xpathPermit;
    //Grant Permission
    if (typePermission.equals("Grant")) {
      xpathPermit = String.format("//td[text()='%s']/following-sibling::td/a[@title='Permitted']", permissionName);
      this.driverCustom.isElementPresent(xpathPermit, this.timeInSecs);
      permission = this.driverCustom.getWebElement(xpathPermit);
      if (permission.isDisplayed() && message.equalsIgnoreCase(successfulMessage)) {
        return new TestAcceptanceMessage(true, String.format("Permission %s is grant to role %s successfully.", permissionName, roleName));
      } else if (!message.equalsIgnoreCase(successfulMessage)) {
        return new TestAcceptanceMessage(false, "Error message shown: " + message + " when trying to grant permission.");
      } else {
        return new TestAcceptanceMessage(false, String.format("Permission %s is still revoke to role %s. Please double-check manually.", permissionName, roleName));
      }
    }
    //Revoke Permission
    xpathPermit = String.format("//td[text()='%s']/following-sibling::td/a[@title='Not Permitted']", permissionName);
    this.driverCustom.isElementPresent(xpathPermit, this.timeInSecs);
    permission = this.driverCustom.getWebElement(xpathPermit);
    if (permission.isDisplayed() && message.equalsIgnoreCase(successfulMessage)) {
      return new TestAcceptanceMessage(true, String.format("Permission %s is revoke to role %s successfully.", permissionName, roleName));
    } else if (!message.equalsIgnoreCase(successfulMessage)) {
      return new TestAcceptanceMessage(false, "Error message shown: " + message + " when trying to revoke permission.");
    } else {
      return new TestAcceptanceMessage(false, String.format("Permission %s is still grant to role %s. Please double-check manually.", permissionName, roleName));
    }
  }

  /**
   * Verify method {@link ManageProjectAreaPage#deleteRole(String)}
   *
   * @author KYY1HC
   * @param roleName name of role
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteRole(final String roleName, final String lastResult) {
    String expected = "Project area saved successfully.";
    WebElement messageSummary = this.driverCustom.getWebElement("//div[@class='messageSummary']");
    String message = messageSummary.getText();
    if (this.driverCustom.isElementInvisible("//option[text()='" + roleName + "']", Duration.ofSeconds(5)) && message.equalsIgnoreCase(expected)) {
      return new TestAcceptanceMessage(true, "Role " + roleName + " is deleted successfully");
    } else if (!message.equalsIgnoreCase(expected)) {
      return new TestAcceptanceMessage(false, "Error message shown: " + message + " when trying to delete " + roleName + " role.");
    } else {
      return new TestAcceptanceMessage(false, "Cannot delete role with name " + roleName);
    }
  }



  /**
   * Verifies the action of {@link ManageProjectAreaPage#selectDislayMembersPerPage(String)}.
   * @author VDY1HC
   * @param dropdownValue - number of member show per page
   * @param lastResult - lastResult
   * @return the verification message
   */
  public TestAcceptanceMessage verifySelectDislayMembersPerPage (final String dropdownValue, final String lastResult) {
    WebElement drdDisplayMembers = this.driverCustom.getWebElement("//select[@class='pageSizeSelect']");
    String selectedValue = drdDisplayMembers.getAttribute("value");
    if (selectedValue.equalsIgnoreCase(dropdownValue)) {
      return new TestAcceptanceMessage (true, "Verified: PASSED - Select value for dropdown with value: " + dropdownValue +
          "\nExpected value: " + dropdownValue + "\nActual value: " + selectedValue);
    }
    return new TestAcceptanceMessage (false, "Verified: FAILED - Unable to select value for dropdown with value: " + dropdownValue +
        "\nExpected value: " + dropdownValue + "\nActual value: " + selectedValue);
  }

  /**
   * Verifies the action of {@link ManageProjectAreaPage#addMemberToProjectAreaWithRole(Map)}.
   * @author VDY1HC
   * @param additionalParams - with key:
   *                           MEMBER_ID - ID of member to be added
   *                           LIST_OF_MEMBER_ROLES - List of role of member
   *                           MEMBER_NAME - Name of member to be added
   * @param lastResult - lastResult
   * @return the verification message
   */
  public TestAcceptanceMessage verifyAddMemberToProjectAreaWithRole (final Map<String, String> additionalParams, final String lastResult) {
    String listOfRole = additionalParams.get("LIST_OF_MEMBER_ROLES");
    String memberName = additionalParams.get("MEMBER_NAME");
    this.driverCustom.switchToDefaultContent();
    WebElement cellProcessRoles = this.driverCustom.getPresenceOfWebElement(RMConstants.MANAGECOMPPROPERTIES_PROCESSROLES_CELL_XPATH,memberName);
    String listRoles = cellProcessRoles.getText();
    if (listOfRole.contains(";")) {
      String[] roles = listOfRole.split(";");
      for (String role : roles) {
        if (!listRoles.contains(role)) {
          return new TestAcceptanceMessage (false, "Verified: FAILED - Unable to select role '" + role +
              "'\nActual selected roles: " + listRoles);
        }
      }
    }
    else {
      if(!listRoles.contains(listOfRole) || listRoles.isEmpty()) {
        return new TestAcceptanceMessage (false, "Verified: FAILED - Unable to select role '" + listOfRole +
            "'\nActual selected role: " + listRoles);
      }
    }
    return new TestAcceptanceMessage (true, "Verified: PASSED - Added member successfully with role: " + listRoles);
  }

  /**
   * Verifies the action of {@link ManageProjectAreaPage#clickOnSaveButton(String)}.
   * @author VDY1HC
   * @param expectedSaveMessage - Expected save message to be displayed
   * @param lastResult - lastResult
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClickOnSaveButton (final String expectedSaveMessage, final String lastResult) {
    String actualMessage = this.driverCustom.getPresenceOfWebElement("//div[@class='messageSummary']").getText();
    try {
      this.driverCustom.getWebElement("//button[text()='Save' and contains(@style,disabled)]");
      if (expectedSaveMessage.equalsIgnoreCase(actualMessage)) {
        return new TestAcceptanceMessage (true, "Verified: PASSED - Click on Save button successfully with message IS MATCH expected." +
            "\nActual message: " + actualMessage + "\nExpected message: " + expectedSaveMessage);
      }
      return new TestAcceptanceMessage (false, "Verified: FAILED - Click on Save button successfully with message IS NOT MATCH expected." +
          "\nActual message: " + actualMessage + "\nExpected message: " + expectedSaveMessage);
    }
    catch (Exception e) {
      LOGGER.LOG.info("Save button is Enabled");
    }
    return new TestAcceptanceMessage (false, "Verified: FAILED - Unable to click on Save button.");
  }

  /**
   * Verifies the action of {@link ManageProjectAreaPage#clickOnRefreshButton()}.
   * @author VDY1HC
   * @param lastResult - lastResult
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClickOnRefreshButton (final String lastResult) {
    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.LOADED_PROJECTAREA_MESSAGE);
      return new TestAcceptanceMessage (true, "Verified: PASSED - Click on 'Refresh' button and project area reloaded successfully");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage (false, "Verified: FAILED - Click on 'Refresh' button and project area IS NOT reloaded");
    }
  }

  /**
   * Verifies the action of {@link ManageProjectAreaPage#isExistingMemberInProjectArea(Map)}.
   * @author VDY1HC
   * @param additionalParams - contains key:
   *                            ROLE - role of member: Members or Administrators <br>
   *                            MEMBER_NAME - Name of member <br>
   *                            EXPECTED_RESULT - true if expected to be found <br>
   * @param lastResult - lastResult
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsExistingMemberInProjectArea (final Map<String, String> additionalParams, final String lastResult) {
    boolean finalResult = Boolean.parseBoolean(lastResult);
    String role = additionalParams.get("ROLE");
    String memberName = additionalParams.get("MEMBER_NAME");
    boolean expectedResult = Boolean.parseBoolean(additionalParams.get("EXPECTED_RESULT"));
    String result = "FAILED";
    String messageText = "Member is NOT found";
    if (finalResult) {
      result = "PASSED";
    }
    if (expectedResult) {
      messageText = "Member is found";
    }
    return new TestAcceptanceMessage (finalResult, "Verified: " + result + " - " + messageText + " with name: " + memberName + " and role: " + role);
  }

  /**
   * Verifies the action of {@link ManageProjectAreaPage#removeMemberFromProjectArea(Map)}.
   * @author VDY1HC
   * @param additionalParams - contains key:
   *                            ROLE - role of member: Members or Administrators <br>
   *                            MEMBER_NAME - Name of member <br>
   * @param lastResult - lastResult
   * @return the verification message
   */
  public TestAcceptanceMessage verifyRemoveMemberFromProjectArea (final Map<String, String> additionalParams, final String lastResult) {
    String memberName = additionalParams.get("MEMBER_NAME");
    try {
      this.engine.findElementWithinDuration(Criteria.isRow().containsText(memberName), Duration.ofSeconds(10)).getFirstElement();
      return new TestAcceptanceMessage (false, "Verified: FAILED - Existing member is still visible in list of member");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage (true, "Verified: PASSED - Existing member is removed in list of member");
    }
  }

  /**
   * Verifies the action of {@link ManageProjectAreaPage#getDisplayedMessage()}.
   * @author VDY1HC
   * @param lastResult - displayed message
   * @return the verification message
   */
  public TestAcceptanceMessage verifyGetDisplayedMessage(final String lastResult) {
    if (lastResult.isEmpty()) {
      return new TestAcceptanceMessage (true, "Verified: PASSED - Get displayed message - No Message displayed");
    }
    return new TestAcceptanceMessage (true, "Verified: PASSED - Get displayed message - Message: " + lastResult);
  }

  /**
   * Verifies the action of {@link ManageProjectAreaPage#selectShowByInPermission(String)}.
   * @author VDY1HC
   * @param showByValue - Operation/Role
   * @param lastResult - lastResult
   * @return the verification message
   */
  public TestAcceptanceMessage verifySelectShowByInPermission (final String showByValue, final String lastResult) {
    boolean result = false;
    switch (showByValue) {
      case "Role":
        result = this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_PERMISSIONTABLE_FOR_ROLE_XPATH, Duration.ofSeconds(300));
        break;
      case "Operation":
        result = this.driverCustom.isElementVisible("//span[contains(text(),'Select an operation or action to view its permissions.')]", Duration.ofSeconds(150));
        if (!result) {
          result = this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_PERMISSIONTABLE_FOR_OPERATION_XPATH, Duration.ofSeconds(150));
        }
        break;
      default:
        throw new InvalidArgumentException("'" + showByValue + "' is not one of the valid option (Valid option: Role / Operation)");
    }
    if (result) {
      return new TestAcceptanceMessage (true, "Verified: PASSED - Selected on 'Show by " + showByValue + "' option.");
    }
    return new TestAcceptanceMessage (false, "Verified: FAILED - Unable to selected on 'Show by " + showByValue + "' option.");
  }
  
  /**
   * Verifies the action of {@link ManageProjectAreaPage#isAssociationDisplayed(String,String)}.
   * @author KYY1HC
   * @param association The section name to link with project area.
   * @param artifactContainerProject The project name is associated with Association in artifact container.
   * @param lastResult - displayed association.
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsAssociationDisplayed(final String association, final String artifactContainerProject, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage (true, String.format("Verified: PASSED - Association '%s' with Artifact Container '%s' is displayed", association, artifactContainerProject));
    }
    return new TestAcceptanceMessage (false, String.format("Verified: FAILED - Association '%s' with Artifact Container '%s' is NOT displayed", association, artifactContainerProject));
  }
  
  /**
   * This method is call after executing the method ${@link ManageProjectAreaPage#verifyIsPermissionShowByRole(Map)} <br>
   * @author NCY3HC
   * @param additionalParams - contains keys: ROLE_NAME - name of role
   *                                        operationTypeSortInOrder - list of operation name ( Manage baselines, Manage Change Sets,..)
   *                                        permissionSortInOrder -list of permission (Permitted, Not Permitted, Some actions are Permiited) base on operation
   * @param lastResult - true if permission show by role displayed match operation and permission
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyIsPermissionShowByRole(final Map<String, String> additionalParams,final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Verify permission show by role" );
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED - Verify permission show by role");
  }
  
  /**
   * This method is call after executing the method ${@link ManageProjectAreaPage#verifyIsPermissionShowByOperation(Map)} <br>
   * @author NCY3HC
   * @param additionalParams - contains keys: ROLE_NAME - name of role
   *                                        operationTypeSortInOrder - list of operation name ( Manage baselines, Manage Change Sets,..)
   *                                        permissionSortInOrder -list of permission (Permitted, Not Permitted, Some actions are Permiited) base on operation
   * @param lastResult - true if permission show by Operation displayed match role name and permission
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyIsPermissionShowByOperation(final Map<String, String> additionalParams, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Verify permission show by Operation" );
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED - Verify permission show by Operation");
  }
  
  /**
   * This method is call after executing the method ${@link ManageProjectAreaPage#verifyDefinedRolesInPA(String)} <br>
   * @author NCY3HC
   * @param listOfRolesSortInOrder - list of roles already sort in order separated by ";" 
   * @param lastResult - true if roles displayed match number and order
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyDefinedRolesInPA(final String listOfRolesSortInOrder, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Verify list of roles displayed by order " +
          "\nList of role sort in order: " + listOfRolesSortInOrder);
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED - Verify list of tab NOT displayed by order " +
        "\nList of tabs roles in order: " + listOfRolesSortInOrder);
  }
  
  /**
   * This method is call after executing the method {@link ManageProjectAreaPage#getMemberNameList(String)}.
   * @param lastResult of member name list
   * @return the verification message
   * @author VUP5HC
   */
  public TestAcceptanceMessage verifyGetMemberNameList(final String lastResult) {
    if (lastResult.isEmpty()) {
      return new TestAcceptanceMessage (false, "Verified: FAILED - Cound not get member name list");
    }
    return new TestAcceptanceMessage (true, "Verified: PASSED - Get member name list succeed:\n" + lastResult);
  }
  
  /**
   * This method is call after executing the method {@link ManageProjectAreaPage#getMemberRoleList(String)}.
   * @param lastResult of member role list
   * @return the verification message
   * @author VUP5HC
   */
  public TestAcceptanceMessage verifyGetMemberRoleList(final String lastResult) {
    if (lastResult.isEmpty()) {
      return new TestAcceptanceMessage (false, "Verified: FAILED - Cound not get member role list");
    }
    return new TestAcceptanceMessage (true, "Verified: PASSED - Get member role list succeed:\n" + lastResult);
  }
  
  /**
   * This method is call after executing the method {@link ManageProjectAreaPage#isDisplayedMemberListWithRoles(String,String)}.
   * @param memberNames Member Name in String, can get first list by method {@link ManageProjectAreaPage#getMemberNameList(String)}
   * @param memberRoles Member Role in String, can get first list by method {@link ManageProjectAreaPage#getMemberRoleList(String)}
   * @param lastResult true if verification method success, else false
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsDisplayedMemberListWithRoles(final String memberNames, final String memberRoles, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Member name correspoding to its role displays correct from expected list.");
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED - Member name correspoding to its role displays not correct from expected list.");
  }
  
  /**
   * This method is called after executing the method {@link ManageProjectAreaPage#createTimeLineOrIterations(Map)}
   * @param additionalParams - contains: key and value 
   * @param lastResult - true if create timeline/iteration successfully
   * @return the verification message
   */
  public TestAcceptanceMessage verifyCreateTimeLineOrIterations(final Map<String, String> additionalParams, final String lastResult) {
    waitForPageLoaded();
    String btnOption = additionalParams.get("BUTTON_OPTION");
    String name = additionalParams.get("NAME");
    boolean isIterationDisplayed = this.driverCustom.isElementVisible(CCMConstants.CCMPROJECTAREA_NEWCREATETIMELINE_NEWCRETAEITERATION_DISPLAYED_XPATH, timeInSecs, name);
    if(isIterationDisplayed) {
      if(btnOption.contains("Iteration")) {
      return new TestAcceptanceMessage(true,"New Iteration named '" + name +"' is created successfully");
      }
        return new TestAcceptanceMessage(true, "New Timeline named '" + name +"' is created successfully");
      }
    if (btnOption.contains("Iteration")) {
      return new TestAcceptanceMessage(false, "New Iteration named '" + name +"' is created unsuccessfully");
    }
      return new TestAcceptanceMessage(false, "New Timeline named '" + name +"' is created unsuccessfully");
    }
  
  /**
   * This method is called after executing the method {@link ManageProjectAreaPage#editPropertiesTimelineOrIteration(Map)}
   * @param param - contains: key and value of param from main method
   * @param lastResult - true if create timeline/iteration successfully
   * @return the verification message
   */
  public TestAcceptanceMessage verifyEditPropertiesTimelineOrIteration(final Map<String,String> param, final String lastResult) {
    Checkbox ckbEditTimeline = this.engine.findElementWithDuration(Criteria.isCheckbox().withLabel("Use this timeline as the project timeline"), timeInSecs).getFirstElement();
    boolean isSelected = ckbEditTimeline.getWebElement().isSelected();
    Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), timeInSecs).getFirstElement();
    btnOK.click();
    if(isSelected) {
      return new TestAcceptanceMessage(true,"Set timeline as Project Timeline successfully");
      }
        return new TestAcceptanceMessage(false, "Set timeline as Project Timeline successfully");
  }
  }
