/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.JazzSettingsEnums.TeamArea;
import com.bosch.jazz.automation.web.common.constants.JazzSettingsEnums.TeamMemberAction;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.container.TreeNode;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.treeNode.JazzTreeNodeFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.JazzTextFinder;

/**
 * Represents the ManageProjectArea Page
 */
public class ManageProjectAreaPage extends AbstractJazzWebPage {

  String userselect = "";
  String userselected = "";
  String availableRole = "";
  private static final String ITERATIONNAME = "IterationName";
  private static final String DESCRIPTION = "Create a new process description";
  private static final String SHOWDETAILS = "show details";
  HashMap<String, String> map = new HashMap<>();


  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom must not be null
   */
  public ManageProjectAreaPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzButtonFinder(), new LinkFinder(), new JazzTextFinder(), new JazzDropdownFinder(),
        new JazzDialogFinder(), new RowCellFinder(), new TextFieldFinder(), new DropdownFinder(), new LinkFinder(),
        new JazzTreeNodeFinder());
  }

  /**
   * Add button in Manage This Project Area
   *
   * @param name is button click on particular area
   */
  public void clikOnAddButton(final String name) {
    waitForPageLoaded();
    this.driverCustom.isLocaterVisible(By.xpath(PagemodelConstants.MANAGEPROJECTAREAPAGE_TEAMAREA_ADDLINK_XPATH), Duration.ofSeconds(3));
    Button lnkAdd =
        this.engine.findElement(Criteria.isButton().withText(PagemodelConstants.ADD_LINK)).getFirstElement();
    lnkAdd.click();
  }


  /**
   * Add the Members in selected project area with the role.
   *
   * @param member is Name of the Team Member
   * @param Role Assigning role to the Member
   */
  public void addMembers(final String member, final String Role) {
    waitForPageLoaded();
    if (this.driverCustom.isLocaterVisible(By.xpath(PagemodelConstants.MANAGEPROJECTAREAPAGE_ADDNEWMEMBERS_XPATH), Duration.ofSeconds(5))) {
      this.driverCustom.getWebDriver()
          .findElement(By.xpath(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SEARCHTEXTAREA_XPATH))
          .sendKeys(member);
      this.driverCustom.isLocaterVisible(By.xpath(PagemodelConstants.MANAGEPROJECTAREAPAGE_SELECTUSER_XPATH), Duration.ofSeconds(2));
      this.driverCustom.click(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_ADD_POSTSEARCHBOX_XPATH);
      this.userselect =
          this.driverCustom.getText(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_ADD_POSTSEARCHBOX_XPATH);
      Button btnAdd = this.engine.findElement(Criteria.isButton().withToolTip("Add")).getFirstElement();
      btnAdd.click();
      this.driverCustom.click(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SELECTEDMEMBER_XPATH);
      this.userselected =
          this.driverCustom.getText(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SELECTEDMEMBER_XPATH);
      Button btnNext = this.engine.findElement(Criteria.isButton().withText("Next >")).getFirstElement();
      btnNext.click();
      this.driverCustom.select(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_AVAILABLEROLES_XPATH, Role,
          SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
      this.driverCustom.click(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_POSTSELECTROLENEXTBUTTON_XPATH);
      Button btnFinish = this.engine.findElement(Criteria.isButton().withText("Finish")).getFirstElement();
      btnFinish.click();
      Button btnSave = this.engine.findElement(Criteria.isButton().withText("Save")).getFirstElement();
      btnSave.click();
      Button btnCancel = this.engine.findElement(Criteria.isButton().withText("Cancel")).getFirstElement();
      btnCancel.click();
    }
    else {
      this.driverCustom.getWebDriver()
          .findElement(By.xpath(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SEARCHTEXTAREA_XPATH))
          .sendKeys(member);
      this.driverCustom.isLocaterVisible(By.xpath(PagemodelConstants.MANAGEPROJECTAREAPAGE_SELECTUSER_XPATH), Duration.ofSeconds(2));
      this.driverCustom.click(PagemodelConstants.MANAGEPROJECTAREAPAGE_SELECTUSER_XPATH);
      Button btnAddAndClose = this.engine.findElement(Criteria.isButton().withText("Add and Close")).getFirstElement();
      btnAddAndClose.click();
    }
  }

  /**
   * setTeamAreaFields is creating the team area
   *
   * @param teamAreaName passing the Team Area name,summary,description and time line
   */
  public void setTeamAreaFields(final Map<String, String> teamAreaName) {
    waitForPageLoaded();
    if ((teamAreaName != null) && !teamAreaName.isEmpty()) {

      if (this.driverCustom.isElementPresent(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_CREATETEAM_AREA_XPATH,
          Duration.ofSeconds(5))) {
        Button btnCreateTeam =
            this.engine.findElement(Criteria.isButton().withToolTip("Create Team...")).getFirstElement();
        btnCreateTeam.click();
      }
      if (teamAreaName.get(TeamArea.NAME.toString()) != null) {
        this.driverCustom.getPresenceOfWebElement(
            PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_TITLENAME_XPATH,
            teamAreaName.get(TeamArea.NAME.toString()));
        this.driverCustom.isElementClickable(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_TITLENAME_XPATH,
             Duration.ofSeconds(10));
        Text txtTeamAreaName = this.engine.findFirstElement(Criteria.isTextField().withText("Team Area Name"));
        txtTeamAreaName.setText(teamAreaName.get(TeamArea.NAME.toString()));
      }
      if (teamAreaName.get(TeamArea.SUMMARY.toString()) != null) {
        Text txtTeamSummary = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Summary:"));
        txtTeamSummary.setText(teamAreaName.get(TeamArea.SUMMARY.toString()));

      }
      if (teamAreaName.get(TeamArea.DESCRIPTION.toString()) != null) {
        Text txtTeamDescription = this.engine.findFirstElement(Criteria.isTextField()
            .hasLabel(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_DESCRIPTION).isMultiLine());
        txtTeamDescription.setText(teamAreaName.get(TeamArea.DESCRIPTION.toString()));
      }

      if (teamAreaName.get(TeamArea.TIMELINE.toString()) != null) {
        Dropdown drdTeamTimeline = this.engine.findFirstElement(Criteria.isDropdown().withToolTip("Timeline"));
        drdTeamTimeline.selectOptionWithText(teamAreaName.get(TeamArea.TIMELINE.toString()));
      }

      setTeamAreaMembersAndAdministrations(teamAreaName);
      return;
    }
    throw new InvalidArgumentException("List is empty or null");
  }

  /**
   * adding the teamArea members and administrations.
   *
   * @param teamAreaName passing the Team Area name,summary,description and time line.
   */

  private void setTeamAreaMembersAndAdministrations(final Map<String, String> teamAreaName) {
    waitForPageLoaded();
    clikOnAddButton(TeamArea.MEMBERS.toString());
    if (teamAreaName.get(TeamArea.MEMBERS.toString()) != null) {
      String member = teamAreaName.get(TeamArea.MEMBERS.toString());
      String[] arr = member.split(";");
      for (String element : arr) {
        String[] memberAndRole = element.split("_");
        if (memberAndRole.length == 2) {
          addMembers(memberAndRole[0], memberAndRole[1]);
        }
        else {
          addMembers(memberAndRole[0], null);
        }

      }
    }

    clikOnAddButton(TeamArea.ADMINISTRATORS.toString());
    if (teamAreaName.get(TeamArea.ADMINISTRATORS.toString()) != null) {
      String admin = teamAreaName.get(TeamArea.ADMINISTRATORS.toString());
      String[] arr = admin.split(";");
      for (String element : arr) {
        String[] adminAndRole = element.split("_");
        if (adminAndRole.length == 2) {
          addMembers(adminAndRole[0], adminAndRole[1]);
        }
        else {
          addMembers(adminAndRole[0], null);
        }
      }
    }

  }

  /**
   * Select Iteration in selected Time Line in ProjectArea
   *
   * @param additionalParams Iteration Name
   */
  public void selectIterationInProjArea(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String name = additionalParams.get("Name");
    String timeline = additionalParams.get("SelectTimeLine");
    TreeNode timelineTreeNode = this.engine.findElement(Criteria.isTreeNode().containText(timeline)).getFirstElement();
    timelineTreeNode.expand();
    TreeNode nameNode = this.engine.findElement(Criteria.isTreeNode().containText(name)).getFirstElement();
    nameNode.expand();

  }


  /**
   * Archive Time Line or Iteration in selected project area
   */
  public void archiveButtonInProjArea() {
    waitForPageLoaded();
    Button btnArchive = this.engine.findElement(Criteria.isButton().withText("Archive...")).getFirstElement();
    btnArchive.click();
    this.driverCustom.getWebDriver().switchTo().alert().accept();
    Button btnSave = this.engine.findElement(Criteria.isButton().withText("Save")).getFirstElement();
    btnSave.click();
  }


  /**
   * Delete Time Line or Iteration in selected project area
   */
  public void deleteButtonInProjArea() {
    waitForPageLoaded();
    Button btnArchive = this.engine.findElement(Criteria.isButton().withText("Archive...")).getFirstElement();
    btnArchive.click();
  }

  /**
   * modify the process attachment.
   *
   * @param processName is name of the process define
   * @param file path of the file
   */
  public void modifyProcessAttachment(final String processName, final String file) {
    waitForPageLoaded();
    Button btnEdit = this.engine.findElement(Criteria.isButton().withToolTip("Edit")).getFirstElement();
    btnEdit.click();
    Button btnCreatePractice =
        this.engine.findElement(Criteria.isButton().withToolTip("Create New Practice")).getFirstElement();
    btnCreatePractice.click();
    this.driverCustom.isElementClickable(
        PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSDESCRIPTION_CREATENEWPRACTICE_TITLE_XPATH, Duration.ofSeconds(20));
    this.driverCustom.getWebDriver()
        .findElement(By
            .xpath(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSDESCRIPTION_CREATENEWPRACTICE_TITLE_XPATH))
        .sendKeys(processName);
    Button btnSave = this.engine.findElement(Criteria.isButton().withText("Save")).getFirstElement();
    btnSave.click();
    this.driverCustom.isElementVisible(
        PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSATTACHMENT_SELECTPRACTICENAME_XPATH, Duration.ofSeconds(5), processName);
    this.driverCustom.click(
        PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSATTACHMENT_SELECTPRACTICENAME_XPATH, processName);
    this.driverCustom.isElementClickable(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSATTACHMENTS_XPATH,
        Duration.ofSeconds(20));
    this.driverCustom.click(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSATTACHMENTS_XPATH);
    Button btnAddFile = this.engine.findElement(Criteria.isButton().withText("Add File")).getFirstElement();
    btnAddFile.click();
    this.driverCustom.getWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSATTACHMENT_BROWSE_XPATH)
        .sendKeys(file);
    Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOK.click();
    this.driverCustom.getWebDriver().navigate().refresh();
  }

  /**
   * Adding or editing or removing the Team Members Process roles
   *
   * @param Member name of Team Member
   * @param Action click on Edit/Add/Remove Process Roles, Invite to Join Team, Remove Member buttons
   * @param role Assigning to role to Team Member
   * @param button OK/Cancel/Next buttons
   */
  public void teamMemberActions(final String Member, final String Action, final String role, final String button) {
    waitForPageLoaded();
    try {
      refresh();
      ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.LOG.info("Page takes a lot of time to load, wait for more 20 seconds to finish loading");
      waitForSecs(20);
      // Force stop loading
      this.driverCustom.executeJavaScript("\"window.stop();\"");
    }
    Text textSearch = this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder("Search..."));
    textSearch.setText(Member);
    waitForSecs(3);
    Row row =
        this.engine.findElementWithDuration(Criteria.isRow().withText(Member), Duration.ofSeconds((this.timeInSecs.getSeconds() / 2))).getFirstElement();
    row.scrollToElement();
    row.hoverOnBottomElement();
    Dropdown drdMembersMenu =
        this.engine.findElementWithDuration(Criteria.isDropdown().withToolTip("Members Menu").inContainer(row),
            Duration.ofSeconds((this.timeInSecs.getSeconds() / 2))).getFirstElement();
    drdMembersMenu.selectOptionWithText(Action);
    waitForSecs(3);
    if (!Action.equals("Remove Member")) {
      this.driverCustom.select(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_SELECTROLES_XPATH, role,
          SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
      if (Action.equals(TeamMemberAction.REMOVEPROCESSROLES.toString()) ||
          Action.equals(TeamMemberAction.ADDPROCESSROLES.toString())) {
        Button btn = this.engine.findElement(Criteria.isButton().withText(button)).getFirstElement();
        btn.click();
      }
      else if (Action.equals(TeamMemberAction.EDITPROCESSROLES.toString())) {
        Button btnAdd = this.engine.findElement(Criteria.isButton().withToolTip("Add")).getFirstElement();
        btnAdd.click();
        Button btn = this.engine.findElement(Criteria.isButton().withText(button)).getFirstElement();
        btn.click();
      }
    }

    waitForSecs(3);
    driverCustom.executeJavaScript("window.scrollTo(0,document.body.scrollTop)");
    Button btnSave = this.engine.findElementWithDuration(Criteria.isButton().withText("Save"),  Duration.ofSeconds(10)).getFirstElement();
    btnSave.click();
    waitForSecs(3);
  }

  /**
   * Select Team Area from the list.
   *
   * @param TeamName Selecting the Team Area Name
   */
  public void selectTeamArea(final String TeamName) {
    waitForPageLoaded();
    // Link link = this.engine.findFirstElement(Criteria.isLink().withText(TeamName));
    Link link = this.engine.findElementWithDuration(Criteria.isLink().withText(TeamName), timeInSecs).getFirstElement();
    link.click();
  }

  /**
   * Click on Project Area in Team Area
   *
   * @param projecAreaname is name of the Project area
   */
  public void teamAreaToProjArea(final String projecAreaname) {
    waitForPageLoaded();
    this.driverCustom
        .getPresenceOfWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREATOPROJAREA_XPATH);
    Link lnkProjectAreaName = this.engine.findFirstElement(Criteria.isLink().withText(projecAreaname));
    lnkProjectAreaName.click();
  }
  /**
   * @param TeamAreaName this method is verifyes created team area under 'Team Area Hierarchy'
   */
  public void isTeamAreaCreatedInTeamAreaHierarchyOverView(final String TeamAreaName) {
    waitForPageLoaded();
    this.driverCustom
    .getPresenceOfWebElement("//div[@class='hierarchyContainer']//span[text()='DYNAMIC_VAlUE']",TeamAreaName);
  }

  /**
   * archives the team area.
   *
   * @param projecAreaname is name of the project area
   */
  public void archiveTeamArea(final String projecAreaname) {
    waitForPageLoaded();
    String mat = this.driverCustom.getWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_NAME_XPATH)
        .getText();
    this.driverCustom
        .isLocaterVisible(By.xpath(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREATOPROJAREA_XPATH), Duration.ofSeconds(3));
    Link lnkProjectAreaName = this.engine.findFirstElement(Criteria.isLink().withText(projecAreaname));
    lnkProjectAreaName.click();
    WebElement element = this.driverCustom.getWebElement(CCMConstants.JAZZPAGE_LINKS_XPATH, mat);
    Actions builder = new Actions(this.driverCustom.getWebDriver());
    builder.moveToElement(element).build().perform();
    WebElement ele = this.driverCustom
        .getWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TIMELINES_SELECTARCHIVEBUTTON_XPATH, mat);
    builder.click(ele).build().perform();
    Alert alert = this.driverCustom.getWebDriver().switchTo().alert();
    alert.accept();
  }

  /**
   * Create TimeLine or Iteration selected project area
   * @author NCY3HC
   * @param additionalParams is Name of TimeLine or Iteration
   *    btnOption: likes 'Create TimeLine' or 'Create Iterations'
   *    name: name of New Timeline or New Iteration
   *    duration: duration of New Timeline or New Iteration
   */
  public void createTimeLineOrIterations(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String btnOption = additionalParams.get("BUTTON_OPTION");
    String name = additionalParams.get("NAME");
    String duration = additionalParams.get("DURATION");
    
    this.driverCustom.waitForPageLoaded();
    this.driverCustom.isElementClickable(CCMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(10),btnOption);
    this.driverCustom.getPresenceOfWebElement(CCMConstants.JAZZPAGE_BUTTONS_XPATH, btnOption).click();
    if (this.driverCustom.isElementVisible(CCMConstants.CCMPROJECTAREA_NAME_INCREATETIMELINE_CRETAEITERATION_DIALOG_XPATH, timeInSecs)) {
     WebElement inputName= this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPROJECTAREA_NAME_INCREATETIMELINE_CRETAEITERATION_DIALOG_XPATH);
     inputName.sendKeys(name);
     WebElement inputDuration= this.driverCustom.getWebElement(CCMConstants.CCMPROJECTAREA_DURATION_INCREATETIMELINE_CRETAEITERATION_DIALOG_XPATH);
     inputDuration.sendKeys(duration);
      Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"),timeInSecs).getFirstElement();
      btnOK.click();
    }
  }

  /**
   * Create new Role in the selected project area
   *
   * @param additionalParams includes 4 keys: IDENTIFIER, NAME, CARDINALITY and DESCRIPTION of new Role
   */
  public void createRole(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Button createRole = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Create Role"),  Duration.ofSeconds(10));
    createRole.click();
    waitForSecs(2);

    WebElement txtRoleIdentifer = this.driverCustom.getWebElement("//input[@aria-label='Identifier:']");
    txtRoleIdentifer.clear();
    txtRoleIdentifer.sendKeys(additionalParams.get("IDENTIFIER"));

    WebElement txtRoleName = this.driverCustom.getWebElement("//input[@dojoattachpoint='name'][@type='text']");
    txtRoleName.sendKeys(additionalParams.get("NAME"));

    WebElement rdoRoleCardinality = additionalParams.get("CARDINALITY").equals("many")
        ? this.driverCustom.getWebElement("//input[@dojoattachpoint='manyCardinality'][@type='radio']")
        : this.driverCustom.getWebElement("//input[@dojoattachpoint='singleCardinality'][@type='radio']");
    rdoRoleCardinality.click();

    WebElement txtDescription = this.driverCustom.getWebElement("//textarea[@aria-label='Description:']");
    txtDescription.sendKeys(additionalParams.get("DESCRIPTION"));

    clickOnButtons("Save");
    waitForSecs(3);
  }

  /**
   * Delete Role in the selected project area
   *
   * @param roleName name of role
   */
  public void deleteRole(final String roleName) {
    waitForPageLoaded();
    WebElement role =
        this.driverCustom.getWebElement(String.format("//select[@name='roles']/option[text()='%s']", roleName));
    role.click();
    Button deleteRole =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Delete Role"), Duration.ofSeconds(5)).getFirstElement();
    deleteRole.click();
    try {
      this.driverCustom.acceptAlertAndReturnAlertText();
    }
    catch (Exception e) {
      LOGGER.LOG.info("Skipped this step for Unit Test");
    }
    waitForSecs(2);
    clickOnButtons("Save");
    waitForSecs(3);
  }

  private void waitForLoadingMessage() {
    int i = 0;
    do {
      this.driverCustom.isElementInvisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_DISPLAY_XPATH,
          timeInSecs);
      this.driverCustom.waitForSecs(Duration.ofSeconds(1));
      if (i++ >  10) {
        break;
      }
    }
    while (this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_DISPLAY_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() /  10))));
  }

  /**
   * Grant or Revoke permission to role in Permission section of Manage this project area and Save project area
   * 
   * @author VDY1HC
   * @param roleName - name of Role
   * @param permissionName - name of permission
   * @param typePermission - "Grant" if you want to grant permission, "Revoke" for revoking permission
   * @return type of show by option
   */
  public String grantOrRevokePermission(final String roleName, final String permissionName,
      final String typePermission) {
    waitForLoadingMessage();
    String showBy = "";
    if (this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_PERMISSIONTABLE_FOR_ROLE_XPATH,
        Duration.ofSeconds(600))) {
      // Show by Role
      grantOrRevokePermissionWithShowByRole(roleName, permissionName, typePermission);
      showBy = "Role";
    }
    else {
      // Show by Operation
      grantOrRevokePermissionWithShowByOperation(roleName, permissionName, typePermission);
      showBy = "Operation";
    }
    this.driverCustom.click(PagemodelConstants.MANAGEPROJECTAREAPAGE_SAVE_BTTUON_XPATH);
    this.driverCustom.isElementInvisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_PERMISSIONTABLE_LOADED_XPATH, Duration.ofSeconds(300));
    this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_PERMISSIONTABLE_LOADED_XPATH, Duration.ofSeconds(400));
    return showBy;
  }

  /**
   * Grant or Revoke permission to role in Permission section of Manage this project area<br>
   * Show by Role
   * 
   * @param roleName - name of Role
   * @param permissionName - name of permission
   * @param typePermission - "Grant" if you want to grant permission, "Revoke" for revoking permission
   */
  private void grantOrRevokePermissionWithShowByRole(final String roleName, final String permissionName,
      final String typePermission) {
    String xpathRoleName = String.format("//div[@role='tree']/div//span[@role='treeitem'][text()='%s']", roleName);
    if (this.driverCustom.isElementClickable(xpathRoleName, timeInSecs)) {
      this.driverCustom.click(xpathRoleName);
      this.driverCustom.isElementInvisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_DISPLAY_XPATH,
          timeInSecs);
      WebElement txtSearch = this.driverCustom
          .getPresenceOfWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_PERMISSIONTAB_SEARCHBOX_XPATH);
      txtSearch.clear();
      txtSearch.sendKeys(permissionName);
      this.driverCustom.isElementInvisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_XPATH, timeInSecs);
      WebElement txtoverrideLocks = this.driverCustom.getWebElement(String.format("//td[text()='%s']", permissionName));
      this.driverCustom.getActions().moveToElement(txtoverrideLocks).build().perform();
      Button btnPermission = this.engine
          .findElementWithDuration(Criteria.isButton().withToolTip(typePermission + " Permission"), timeInSecs)
          .getFirstElement();
      btnPermission.click();
    }
    else {
      throw new InvalidArgumentException(String.format("Role name '%s' not found.", roleName));
    }
  }

  /**
   * Grant or Revoke permission to role in Permission section of Manage this project area<br>
   * Show by Operation
   * 
   * @author VDY1HC
   * @param roleName - name of Role
   * @param permissionName - name of permission
   * @param typePermission - "Grant" if you want to grant permission, "Revoke" for revoking permission
   */
  private void grantOrRevokePermissionWithShowByOperation(final String roleName, final String permissionName,
      final String typePermission) {
    WebElement txtSearch = this.driverCustom
        .getPresenceOfWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_PERMISSIONTAB_SEARCHBOX_XPATH);
    txtSearch.clear();
    txtSearch.sendKeys(permissionName);
    waitForSecs(5);
    this.driverCustom.isElementInvisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_XPATH, timeInSecs);
    WebElement permissionNode = this.driverCustom
        .getPresenceOfWebElement("//span[@class='dijitTreeLabel' and text()='" + permissionName + "']");
    this.driverCustom.clickUsingActions(permissionNode);
    this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_OPERATION_PERMISSIONTABLE_LOADED_XPATH,
        Duration.ofSeconds(1200));
    WebElement roleNode = this.driverCustom.getPresenceOfWebElement(String.format("//td[text()='%s']", roleName));
    waitForSecs(3);
    this.driverCustom.getActions().moveToElement(roleNode).build().perform();
    Button btnPermission =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip(typePermission + " Permission"), timeInSecs)
            .getFirstElement();
    btnPermission.click();
  }

  /**
   * Creates the process description.
   *
   * @param DesName Name of the process description
   * @return the Description Name
   */
  public String createProcessDescription(final String DesName) {
    waitForPageLoaded();
    if (!(this.driverCustom.isElementVisible(CCMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(2), DESCRIPTION))) {
      deleteProcessDescription();
    }
    this.driverCustom.getPresenceOfWebElement(CCMConstants.JAZZPAGE_LINKS_XPATH, DESCRIPTION);
    Link lnkCreateProDesc = this.engine.findFirstElement(
        Criteria.isLink().withText(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_CREATE_PROCESSDESCRIPTION));
    lnkCreateProDesc.click();
    Text txtTeamAreaName = this.engine.findFirstElement(Criteria.isTextField().withText("New Process Description"));
    txtTeamAreaName.setText(DesName);
    Text txtProcessDescription = this.engine.findFirstElement(Criteria.isTextField()
        .hasLabel(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_DESCRIPTION).isMultiLine());
    txtProcessDescription.setText("Test_Process_Description_Detailed_Description_1");
    Button btnSave = this.engine.findElement(Criteria.isButton().withText("Save")).getFirstElement();
    btnSave.click();
    return this.driverCustom.getWebElement("ManageProjectAreaPage.Settings.ProcessDes.SuccessfulyMessage_xpath")
        .getText();
  }

  /**
   * Create the project area
   *
   * @param projectArea is name of project area
   * @param TemplateName is template name
   * @return successfully message
   */
  public String createProjectArea(final Map<String, String> projectArea, final String TemplateName) {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(5), "Create Project Area");
    Button btnCreateProjectArea =
        this.engine.findElement(Criteria.isButton().withText("Create Project Area")).getFirstElement();
    btnCreateProjectArea.click();

    if ((projectArea != null) && !projectArea.isEmpty()) {

      if (projectArea.get(TeamArea.NAME.toString()) != null) {
        this.driverCustom
            .getPresenceOfWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_TITLENAME_XPATH);
        Text txtProjectAreaName = this.engine.findFirstElement(Criteria.isTextField().withText("Project Area Name"));
        txtProjectAreaName.setText(projectArea.get(TeamArea.NAME.toString()));
      }
      if (projectArea.get(TeamArea.SUMMARY.toString()) != null) {
        Text txtProjectSummary = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Summary:"));
        txtProjectSummary.setText(projectArea.get(TeamArea.SUMMARY.toString()));
      }
      if (projectArea.get(TeamArea.DESCRIPTION.toString()) != null) {
        Text txtProjectDescription =
            this.engine.findFirstElement(Criteria.isTextField().hasLabel("Description:").isMultiLine());
        txtProjectDescription.setText(projectArea.get(TeamArea.DESCRIPTION.toString()));
      }
      this.driverCustom.select(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_PROCESSTEMPLATE_XPATH,
          TemplateName, SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);

      setTeamMembersAndAdministrations(projectArea);

    }
    int i = 0;
    while (i <  10) {
      if (this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTON_XPATH, Duration.ofSeconds(1), "Save")) {
        Button btnSave = this.engine.findElement(Criteria.isButton().withText("Save")).getFirstElement();
        btnSave.click();
        break;
      }
      i++;
    }
    if (this.driverCustom.isElementVisible(CCMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(3), SHOWDETAILS)) {
      Link lnkShoWDetails = this.engine
          .findFirstElement(Criteria.isLink().withText(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SHOWDETAILS));
      lnkShoWDetails.click();
    }
    return this.driverCustom.getText(RMConstants.RMDASHBOARDPAGE_MESSAGE_XPATH);
  }

  /**
   * adding the teamArea members and administrations.
   *
   * @param teamAreaName passing the Team Area name,summary,description and time line.
   */

  private void setTeamMembersAndAdministrations(final Map<String, String> projectArea) {
    waitForPageLoaded();
    clikOnAddButton(TeamArea.MEMBERS.toString());
    if (projectArea.get(TeamArea.MEMBERS.toString()) != null) {
      String member = projectArea.get(TeamArea.MEMBERS.toString());
      String[] arr = member.split(";");
      for (String element : arr) {
        String[] memberAndRole = element.split("_");
        if (memberAndRole.length == 2) {
          addMembers(memberAndRole[0], memberAndRole[1]);
        }
        else {
          addMembers(memberAndRole[0], null);
        }
      }
    }
    clikOnAddButton(TeamArea.ADMINISTRATORS.toString());
    if (projectArea.get(TeamArea.ADMINISTRATORS.toString()) != null) {
      String admin = projectArea.get(TeamArea.ADMINISTRATORS.toString());
      String[] arr = admin.split(";");
      for (String element : arr) {
        String[] adminAndRole = element.split("_");
        if (adminAndRole.length == 2) {
          addMembers(adminAndRole[0], adminAndRole[1]);
        }
        else {
          addMembers(adminAndRole[0], null);
        }
      }
    }
  }

  /**
   * Archive button in the Project Area
   *
   * @param ProjectArea name of the Project
   */
  public void archiveProjectArea(final String ProjectArea) {
    waitForPageLoaded();
    Link lnkActiveProjArea = this.engine.findFirstElement(Criteria.isLink().withText("Active Project Areas"));
    lnkActiveProjArea.click();
    this.driverCustom.isElementVisible(CCMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(5), ProjectArea);
    WebElement we = this.driverCustom.getWebElement(CCMConstants.JAZZPAGE_LINKS_XPATH, ProjectArea);
    Actions act = new Actions(this.driverCustom.getWebDriver());
    act.moveToElement(we).build().perform();
    List<WebElement> lst = this.driverCustom.getWebElements(RMConstants.RMDASHBOARDPAGE_ARCHIVEBUTTON_LINK_XPATH);
    for (WebElement el : lst) {
      if (this.driverCustom.isElementVisible(el, Duration.ofSeconds(3))) {
        el.click();
        break;
      }
    }
  }

  /**
   * Modifies the Explore Project Area.
   *
   * @param projectName ProjectAreaName
   */
  public void exploreProjArea(final String projectName) {
    waitForPageLoaded();
    Link lnkProjectName = this.engine.findFirstElement(Criteria.isLink().withText(projectName));
    lnkProjectName.click();
  }

  /**
   * Selection the Tab Section in Manage This Project Area.
   * 
   * @author VDY1HC
   * @param tabName - Tab Name to open
   * @return true if tab is selected, otherwise false
   */
  public boolean tabSection(final String tabName) {
    waitForPageLoaded();
    String xpathTab = String.format("//a[text()='%s']", tabName);
    this.driverCustom.isElementClickable(xpathTab, timeInSecs);
    WebElement tab = this.driverCustom.getWebElement(xpathTab);
    tab.click();
    this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_LOADING_MESSAGE_XPATH, Duration.ofSeconds(60));

    // For Overview tab, we use different Xpath to get pageTitle text.
    WebElement pageTitle;
    switch (tabName) {
      case "Overview":
        pageTitle =
            this.driverCustom.getWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_OVERVIEW_HEADER_PAGE_TITLE_XPATH);
        break;
      case "Permissions":
        this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_PERMISSIONTABLE_FOR_ROLE_XPATH,
            Duration.ofSeconds(120));
        pageTitle = this.driverCustom.getWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_PAGE_TITLE_XPATH);
        break;
      default:
        pageTitle = this.driverCustom.getWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_PAGE_TITLE_XPATH);
    }
    return pageTitle.getText().trim().contains(tabName);
  }

  /**
   * deletes process description.
   *
   * @return the text message
   */
  public String deleteProcessDescription() {
    waitForPageLoaded();
    this.driverCustom.isLocaterVisible(
        By.xpath(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSDESCRIPTION_DELETE_BUTTON_XPATH), Duration.ofSeconds(5));
    Button btnDelete =
        this.engine.findElement(Criteria.isButton().withToolTip("Delete process description")).getFirstElement();
    btnDelete.click();
    Alert alert = this.driverCustom.getWebDriver().switchTo().alert();
    alert.accept();
    Button btnSave = this.engine.findElement(Criteria.isButton().withText("Save")).getFirstElement();
    btnSave.click();
    return this.driverCustom
        .getWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_PROCESSDESCRIPTION_SUCCESSFULLMESSAGE_XPATH)
        .getText();
  }

  /**
   * Creating,Editing,Assigning,Delecting the Workflows
   *
   * @param ID is workflow ID
   * @param Button is Add or Edit or Delete Button
   * @return the Success/Error Message
   */
  public String workFlows(final String ID, final String Button) {
    waitForPageLoaded();
    String st = "";
    if (Button.equals(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_ADD_BUTTON)) {
      Button button = this.engine.findElement(Criteria.isButton().withText(Button)).getFirstElement();
      button.click();
      Dialog dlgAddWorkflow = this.engine.findElement(Criteria.isDialog().withTitle("Add Workflow")).getFirstElement();
      Text txtId = this.engine.findFirstElement(Criteria.isTextField().inContainer(dlgAddWorkflow).hasLabel("ID:"));
      txtId.setText(ID);
      this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_WORKFLOWID_XPATH).sendKeys(Keys.TAB);
      Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
      btnOK.click();
      if (ExpectedConditionsCustom.isAlertPresent(this.driverCustom.getWebDriver(), Duration.ofSeconds(5))) {
        st = acceptAlertAndReturnAlertText();
      }
    }
    else {
      if (Button.equals("Remove")) {
        this.driverCustom.select(PagemodelConstants.MANAGEPROJECTAREAPAGE_WORKFLOW_SELECTWORKFLOW_DROPDOWN_XPATH, ID,
            SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
        Button button = this.engine.findElement(Criteria.isButton().withText(Button)).getFirstElement();
        button.click();
        ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(),  Duration.ofSeconds(10));
      }
      else if (Button.equals("Edit")) {
        if (ExpectedConditionsCustom.isAlertPresent(this.driverCustom.getWebDriver(), Duration.ofSeconds(5))) {
          Button button = this.engine.findElement(Criteria.isButton().withText(Button)).getFirstElement();
          button.click();
        }
        this.driverCustom.select(PagemodelConstants.MANAGEPROJECTAREAPAGE_WORKFLOW_SELECTWORKFLOW_DROPDOWN_XPATH, ID,
            SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
        Text txtWorkflowsName = this.engine.findFirstElement(
            Criteria.isTextField().hasLabel(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_WORKFLOWS_NAME));
        txtWorkflowsName.setText("Modified " + DateUtil.getCurrentDateAndTime());
        Text txtWorkflowsDesc =
            this.engine.findFirstElement(Criteria.isTextField().hasLabel("Description:").isMultiLine());
        txtWorkflowsDesc.setText("Modifying/Editing the WorkFlow");
      }
    }
    return st;

  }

  /**
   * After Saving the any updates it returns the Message
   *
   * @param button is name of button
   * @return the Success/Error Message
   */
  public String msg(final String button) {
    waitForPageLoaded();
    int i = 0;
    while (i <  10) {
      if (this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTON_XPATH, Duration.ofSeconds(1), button)) {
        Button btn = this.engine.findElement(Criteria.isButton().withText(button)).getFirstElement();
        btn.click();
        break;
      }
      i++;
    }
    if (this.driverCustom.isElementVisible(CCMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(3), SHOWDETAILS)) {
      Link lnkShoWDetails = this.engine
          .findFirstElement(Criteria.isLink().withText(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SHOWDETAILS));
      lnkShoWDetails.click();
    }
    return this.driverCustom.getText(RMConstants.RMDASHBOARDPAGE_MESSAGE_XPATH);
  }

  /**
   * Set the Workflow Transaction States and Actions
   *
   * @param Name is workflow Name
   * @param StateIcon is Icon type in state
   * @param ActionIcon is Icon type in Action
   * @param StateGroup select the state group value from the dropdown
   * @param ActionTarget select the Action Target value from the dropdown
   * @param Button is Add or Edit or Delete Button
   */
  public void workflowTransactions(final String Name, final String StateIcon, final String ActionIcon,
      final String StateGroup, final String ActionTarget, final String Button) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_STATEACTION_XPATH, "States");
    Link lnkStateAdd = this.engine.findFirstElement(
        Criteria.isLink().withText(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_ADD_BUTTON));
    lnkStateAdd.click();
    Dialog dlgAddState = this.engine.findElement(Criteria.isDialog().withTitle("Add State")).getFirstElement();
    Text txtStateName = this.engine.findFirstElement(Criteria.isTextField().inContainer(dlgAddState).hasLabel("Name:"));
    txtStateName.setText(Name);
    Dropdown drdStateGroup = this.engine.findFirstElement(Criteria.isDropdown().inContainer(dlgAddState));
    drdStateGroup.selectOptionWithText(StateGroup.trim());
    Link lnkStateIcon = this.engine.findFirstElement(Criteria.isLink().withText(StateIcon));
    lnkStateIcon.click();
    Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOK.click();
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_STATEACTION_XPATH, "Actions");
    Link lnkActionsAdd = this.engine.findElement(Criteria.isLink().withText("Add...")).getElementByIndex(2);
    lnkActionsAdd.click();
    Dialog dlgAddAction = this.engine.findElement(Criteria.isDialog().withTitle("Add Action")).getFirstElement();
    Text txtActionName =
        this.engine.findFirstElement(Criteria.isTextField().inContainer(dlgAddAction).hasLabel("Name:"));
    txtActionName.setText(Name);
    Link lnkActionIcon = this.engine.findFirstElement(Criteria.isLink().withText(ActionIcon));
    lnkActionIcon.click();
    Dropdown drdTargetState =
        this.engine.findFirstElement(Criteria.isDropdown().withLabel("Target State").inContainer(dlgAddAction));
    drdTargetState.selectOptionWithText(Name);
    Button btnOk = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOk.click();
    if (Button.equals("Add...")) {
      this.driverCustom.select(RMConstants.RMDASHBOARDPAGE_STATEDROPDOWN_XPATH, Name,
          SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
    }
  }

  /**
   * Click on Manage Explore Project button
   */
  public void exploreProjButton() {
    // waitForPageLoaded();
    waitForSecs(5);
    this.driverCustom
        .isLocaterVisible(By.xpath(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_EXPLOREPROJECT_BUTTON_XPATH), Duration.ofSeconds(4));
    Button exploreProjButton =
        this.engine.findElement(Criteria.isButton().withText("Explore Project")).getFirstElement();
    exploreProjButton.click();
    waitForSecs(5);
  }

  /**
   * this method is not implemented.
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {

    throw new UnsupportedOperationException("Currently Method is not support to any operation");
  }

  /**
   * This method is not implemented for the presence of the element in the ManageProjectAreaPage.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, "Project");
  }


  /**
   * Search the build information release version text from the list of locaters then returns text.
   *
   * @return release version of alm.
   */
  public String almReleaseVersion() {
    Optional<WebElement> matchingOptional = this.driverCustom.getWebElements("//td[@class = 'patchName']").stream()
        .filter(x -> x.getText().startsWith("JTS Patch is active")).findFirst();
    if (matchingOptional.isPresent()) {
      return matchingOptional.get().findElement(By.xpath("following-sibling::td[1]")).getText();
    }
    throw new WebAutomationException("AlM release version not found.");

  }

  /**
   * Search the build information release ifix version text from the list of locaters then returns text.
   *
   * @return alm ifix release version.
   */
  public String getAlmIFIXReleaseVersion() {
    Optional<WebElement> matchingOptional = this.driverCustom.getWebElements("//td[@class = 'patchName']").stream()
        .filter(x -> x.getText().startsWith("JTS Patch is active")).findFirst();
    if (matchingOptional.isPresent()) {
      return matchingOptional.get().findElement(By.xpath("following-sibling::td[2]")).getText();
    }
    throw new WebAutomationException("AlM release version not found.");

  }

  /**
   * Clicks on "Configuration Management" link if exists, verify whether "Enable Configuration Management" button exists
   * or not.
   *
   * @return true if GC enabled else false.
   */
  public boolean isGcEnabled() {
    Link lnkArtifact = this.engine.findFirstElement(Criteria.isLink().withText("Configuration Management"));
    lnkArtifact.click();
    return this.driverCustom.getWebElements("//button").stream()
        .anyMatch(x -> x.getText().contains("Enable Configuration Management") && (x.getAttribute("disabled") != null));

  }

  /**
   * <p>
   * Add a new member on the project area and assign all roles for this member
   * <p>
   *
   * @author NVV1HC
   * @param memberName name of member to be added on the project area
   */
  public void addMemberWithAllRoles(final String memberName) {
    this.driverCustom.isElementClickable(PagemodelConstants.MANAGEPROJECTAREAPAGE_ADDMEMBER_BUTTON_XPATH,
        this.timeInSecs);
    this.driverCustom.click(PagemodelConstants.MANAGEPROJECTAREAPAGE_ADDMEMBER_BUTTON_XPATH);

    this.driverCustom.isElementInvisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SEARCHTEXTAREA_XPATH,
        this.timeInSecs);
    this.driverCustom.getWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SEARCHTEXTAREA_XPATH)
        .sendKeys(memberName);

    this.driverCustom.isElementInvisible((PagemodelConstants.MANAGEPROJECTAREAPAGE_SELECTUSER_XPATH), Duration.ofSeconds(5));

    this.driverCustom.isElementClickable(
        PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_ADD_POSTSEARCHBOX_XPATH, this.timeInSecs);
    this.driverCustom.click(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_ADD_POSTSEARCHBOX_XPATH);
    this.userselect =
        this.driverCustom.getText(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_TEAMAREA_ADD_POSTSEARCHBOX_XPATH);
    Button btnAdd =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Add"), this.timeInSecs).getFirstElement();
    btnAdd.click();

    this.driverCustom.isElementClickable(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SELECTEDMEMBER_XPATH,
        this.timeInSecs);
    this.driverCustom.click(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SELECTEDMEMBER_XPATH);

    this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SELECTEDMEMBER_XPATH,
        this.timeInSecs);
    this.userselected =
        this.driverCustom.getText(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_SELECTEDMEMBER_XPATH);
    Button btnNext =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Next >"), this.timeInSecs).getFirstElement();
    btnNext.click();

    this.driverCustom.isElementClickable(PagemodelConstants.MANAGEPROJECTAREAPAGE_ADDROLE_FIRSTOPTION_XPATH,
        this.timeInSecs);
    this.driverCustom.click(PagemodelConstants.MANAGEPROJECTAREAPAGE_ADDROLE_FIRSTOPTION_XPATH);

    this.driverCustom.isElementVisible(PagemodelConstants.MANAGEPROJECTAREAPAGE_ADDROLE_LASTOPTION_XPATH,
        this.timeInSecs);
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.keyDown(Keys.SHIFT).click(
        this.driverCustom.getPresenceOfWebElement(PagemodelConstants.MANAGEPROJECTAREAPAGE_ADDROLE_LASTOPTION_XPATH))
        .perform();

    this.driverCustom.isElementClickable(
        PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_POSTSELECTROLENEXTBUTTON_XPATH, this.timeInSecs);
    this.driverCustom.click(PagemodelConstants.MANAGEPROJECTAREAPAGE_SETTINGS_POSTSELECTROLENEXTBUTTON_XPATH);
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    Button btnFinish =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Finish"), this.timeInSecs).getFirstElement();
    btnFinish.click();
    Button btnSave =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Save"), this.timeInSecs).getFirstElement();
    btnSave.click();
    waitForSecs(5);
    try {
      Button btnCancel =
          this.engine.findElementWithDuration(Criteria.isButton().withText("Cancel"), Duration.ofSeconds(30)).getFirstElement();
      btnCancel.click();
    }
    catch (Exception e) {
    }
  }


  /**
   * In 'Manage this project area page', click on dropdown show member per page, and select number of member per page
   * <br>
   * 
   * @param dropdownValue - number of member show per page
   */
  public void selectDislayMembersPerPage(String dropdownValue) {
    WebElement drdDisplayMembers = this.driverCustom.getWebElement("//select[@class='pageSizeSelect']");
    drdDisplayMembers.click();
    waitForSecs(2);
    WebElement drdOption = this.driverCustom.getWebElement("//option[@value='" + dropdownValue + "']");
    drdOption.click();
    waitForSecs(2);
  }

  /**
   * @param dropdownValue
   */
  @SuppressWarnings("javadoc")
  public void selectCreatedCategory(String categoryName,String subMenuToBeSelected) {
    WebElement createdCategory = this.driverCustom.getWebElement("//td[@title='Categories'] /span[text()= '"+ categoryName +"' ]/../../td[@title='Actions']");
    //createdCategory.click();
   this.driverCustom.getActions().moveToElement(createdCategory).perform();  
   createdCategory.click();
   
   WebElement subMenuOption=this.driverCustom.getWebElement("//td[text()='"+ subMenuToBeSelected +"']");
   this.driverCustom.getActions().moveToElement(subMenuOption).perform();
   subMenuOption.click();
       //WebElement drdOption = this.driverCustom.getWebElement("//option[@value='" + dropdownValue + "']");
  //  drdOption.click();
   
  }
  
  
  /**
   * In 'Manage this project area page', click on add button to add new member with list of role and click finish to
   * close dialog <br>
   * 
   * @author VDY1HC
   * @param additionalParams - with key: MEMBER_ID - ID of member to be added LIST_OF_MEMBER_ROLES - List of role of
   *          member MEMBER_NAME - Name of member to be added
   */
  @SuppressWarnings("unused")
  public void addMemberToProjectAreaWithRole(final Map<String, String> additionalParams) {
    String memberID = additionalParams.get("MEMBER_ID");
    String listOfRole = additionalParams.get("LIST_OF_MEMBER_ROLES");
    String memberName = additionalParams.get("MEMBER_NAME");
    this.driverCustom.getPresenceOfWebElement(
        "//div[@role='heading' and text()='Members']/ancestor::div[@class='section']//a[text()='Add...']").click();
    WebElement txbSearch = this.driverCustom.getPresenceOfWebElement("//input[@dojoattachpoint='searchText']");
    txbSearch.click();
    txbSearch.sendKeys(memberID);
    this.driverCustom.getPresenceOfWebElement("//option[contains(text(),'" + memberID + "')]").click();
    Button btnAdd = this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Add"), Duration.ofSeconds(60)).getFirstElement();
    btnAdd.click();
    Button btnNext = this.engine.findElementWithDuration(Criteria.isButton().withText("Next >"), Duration.ofSeconds(60)).getFirstElement();
    btnNext.click();
    if (listOfRole.contains(";")) {
      String[] roles = listOfRole.split(";");
      for (String role : roles) {
        this.driverCustom
            .getPresenceOfWebElement("//select[@aria-label='Available Roles:']//option[text()='" + role + "']").click();
      }
    }
    else {
      this.driverCustom
          .getPresenceOfWebElement("//select[@aria-label='Available Roles:']//option[text()='" + listOfRole + "']")
          .click();
    }
    this.driverCustom.getPresenceOfWebElement("//button[@title='Add' and not(@class='disabled')]").click();
    Button btnFinish =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Finish"), Duration.ofSeconds(60)).getFirstElement();
    btnFinish.click();
  }

  /**
   * Click on save button and verify by message <br>
   * 
   * @author VDY1HC
   * @param expectedSaveMessage - Expected save message to be displayed
   */
  public void clickOnSaveButton(String expectedSaveMessage) {
    Button btnSave = this.engine.findElementWithinDuration(Criteria.isButton().withText("Save"), Duration.ofSeconds(60)).getFirstElement();
    btnSave.click();
    waitForSecs(3);
    try {
      Dialog dlgSendInvitations = this.engine
          .findElementWithDuration((Criteria.isDialog().withTitle("Send Team Invitations to New Members?")), Duration.ofSeconds(30))
          .getFirstElement();
      Button btnCancel = this.engine
          .findElementWithDuration((Criteria.isButton().withText("Cancel").inContainer(dlgSendInvitations)), Duration.ofSeconds(30))
          .getFirstElement();
      btnCancel.click();
    }
    catch (Exception e) {
      LOGGER.LOG.info("Dialog 'Send Team Invitations to New Members?' is not displayed");
    }
  }

  /**
   * Click on Refresh button<br>
   * 
   * @author VDY1HC
   */
  public void clickOnRefreshButton() {
    Button btnRefresh =
        this.engine.findElementWithinDuration(Criteria.isButton().withToolTip("Refresh"), Duration.ofSeconds(60)).getFirstElement();
    btnRefresh.click();
    waitForSecs(3);
  }

  /**
   * Search for member with expected member role if existing in project area. <br>
   * 
   * @param additionalParams - contains key: ROLE - role of member: Members or Administrators <br>
   *          MEMBER_NAME - Name of member <br>
   *          EXPECTED_RESULT - true if expected to be found <br>
   * @return true - if found member with expected role and vice versa.
   */
  public boolean isExistingMemberInProjectArea(final Map<String, String> additionalParams) {
    waitForSecs( Duration.ofSeconds(10));
    String role = additionalParams.get("ROLE");
    String memberName = additionalParams.get("MEMBER_NAME");
    boolean expectedResult = Boolean.parseBoolean(additionalParams.get("EXPECTED_RESULT"));
    boolean actualResult = false;
    WebElement txbSearch = this.driverCustom.getWebElement(
        "//div[@role='heading' and contains(text(),'" + role + "')]/ancestor::div[@class='section']//input");
    txbSearch.click();
    txbSearch.clear();
    txbSearch.sendKeys(memberName + Keys.ENTER);
    waitForSecs(5);
    try {
      this.driverCustom.getPresenceOfWebElement("//div[@role='heading' and contains(text(),'" + role +
          "')]/ancestor::div[@class='section']//a[text()='" + memberName + "']");
      actualResult = true;
    }
    catch (Exception e) {
      LOGGER.LOG.info("Not found user with expected role. Exception: " + e.toString());
    }
    return actualResult == expectedResult;
  }


  /**
   * In Manage This Project Area, open tab role, verify if list of roles are displayed matching number and order
   * 
   * @author NCY3HC
   * @param listOfRolesSortInOrder - list of roles sorted in order, seperated by ";"
   * @return true if roles are displayed matching with number and order
   */
  public boolean verifyDefinedRolesInPA(final String listOfRolesSortInOrder) {
    List<WebElement> numberOfRoles = this.driverCustom.getVisibleWebElements("//select[@name='roles']//option");
    String[] listOfRolesInput = listOfRolesSortInOrder.split(";");
    if (numberOfRoles.size() != listOfRolesInput.length)
      return false;
    for (int i = 0; i < numberOfRoles.size(); i++) {
      if (!numberOfRoles.get(i).getText().equalsIgnoreCase(listOfRolesInput[i])) {
        return false;
      }
    }
    return true;
  }

  /**
   * In Permission tab, verify permission when checked radio button "Show by role"
   * 
   * @author NCY3HC
   * @param additionalParams contains keys: ROLE_NAME - name of role operationTypeSortInOrder - list of operation name (
   *          Manage baselines, Manage Change Sets,..) permissionSortInOrder -list of permission (Permitted, Not
   *          Permitted, Some actions are Permiited) base on operation
   * @return if operation and permission are matched with operationTypeSortInOrder and permissionSortInOrder
   */
  public boolean verifyIsPermissionShowByRole(final Map<String, String> additionalParams) {
    WebElement rbnShowByRole = this.driverCustom.getWebElement("//input[@id='editPermissionsChoiceByRole']");
    rbnShowByRole.click();
    String roleName = additionalParams.get("ROLE_NAME");
    Label lblRoleName =
        this.engine.findElementWithDuration(Criteria.isLabel().containText(roleName), timeInSecs).getFirstElement();
    lblRoleName.click();
    waitForLoadingMessage();
    String[] operationTypeSortInOrder = additionalParams.get("OPERATION_TYPE_SORT_IN_ORDER").split(";");

    String[] permissionSortInOrder = additionalParams.get("PERMISSION_SORT_IN_ORDER").split(";");
    String xpath =
        "//td[text()=\"DYNAMIC_VAlUE0\"]//ancestor::tr[@class='permisionsTableRow']//a[@title='DYNAMIC_VAlUE1']";
    if (operationTypeSortInOrder.length == permissionSortInOrder.length) {
      for (int i = 0; i < operationTypeSortInOrder.length; i++) {
        String[] value = new String[] { operationTypeSortInOrder[i], permissionSortInOrder[i] };
        WebElement permissionFollowOperation = this.driverCustom.getWebElement(xpath, value);
        if (!permissionFollowOperation.isDisplayed()) {
          return false;
        }
      }
    }
    else {
      return false;
    }
    return true;
  }

  /**
   * In Permission tab, verify permission when checked radio button "Show by operation"
   * 
   * @author NCY3HC
   * @param additionalParams contains keys: ROLE_NAME - name of role operationTypeSortInOrder - list of operation name (
   *          Manage baselines, Manage Change Sets,..) permissionSortInOrder -list of permission (Permitted, Not
   *          Permitted, Some actions are Permiited) base on operation
   * @return if operation and permission are matched with operationTypeSortInOrder and permissionSortInOrder
   */
  public boolean verifyIsPermissionShowByOperation(final Map<String, String> additionalParams) {
    WebElement rbnShowByOperation = this.driverCustom.getWebElement("//input[@id='editPermissionsChoiceByAction']");
    rbnShowByOperation.click();
    waitForLoadingMessage();
    String roleName = additionalParams.get("ROLE_NAME");
    String[] operationTypeSortInOrder = additionalParams.get("OPERATION_TYPE_SORT_IN_ORDER").split(";");
    String[] permissionSortInOrder = additionalParams.get("PERMISSION_SORT_IN_ORDER").split(";");
    String xpath = "//td[text()='" + roleName + "']/following-sibling::td//a[@title='DYNAMIC_VAlUE']";
    for (int i = 0; i < operationTypeSortInOrder.length; i++) {
      Label lblOperationName =
          this.engine.findElementWithDuration(Criteria.isLabel().containText(operationTypeSortInOrder[i]), timeInSecs)
              .getFirstElement();
      lblOperationName.click();
      waitForLoadingMessage();
      WebElement permissionByRole = this.driverCustom.getWebElement(xpath, permissionSortInOrder[i]);
      if (!permissionByRole.isDisplayed()) {
        return false;
      }
    }
    return true;
  }


  /**
   * Search and remove member in list with role Members/Admins <br>
   * 
   * @param additionalParams - contains key: ROLE - role of member: Members or Administrators <br>
   *          MEMBER_NAME - Name of member <br>
   */
  public void removeMemberFromProjectArea(final Map<String, String> additionalParams) {
    String role = additionalParams.get("ROLE");
    String memberName = additionalParams.get("MEMBER_NAME");
    isExistingMemberInProjectArea(additionalParams);
    Row rowMemberVerify =
        this.engine.findElementWithDuration(Criteria.isRow().containsText(memberName), Duration.ofSeconds(60)).getFirstElement();
    rowMemberVerify.hoverOnElement();
    Button actionMenu =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip(role + " Menu"), Duration.ofSeconds(60)).getFirstElement();
    actionMenu.click();
    this.driverCustom.getPresenceOfWebElement("//table[contains(@class,'dijitMenuPassive')]//td[text()='Remove " +
        role.substring(0, role.length() - 1) + "']").click();
  }

  /**
   * Get displayed message <br>
   * 
   * @return displayedMessage if found, "" if not found
   */
  public String getDisplayedMessage() {
    try {
      this.driverCustom.isElementVisible("//div[@class='messageSummary']", timeInSecs);
      WebElement message = this.driverCustom.getWebElement("//div[@class='messageSummary']");
      return message.getText();
    }
    catch (Exception e) {
      return "";
    }
  }

  /**
   * In permisson page, select show by Role or Operation radio button
   * 
   * @author VDY1HC
   * @param showByValue - Show by Role \ Operation
   */
  public void selectShowByInPermission(String showByValue) {
    if (showByValue.equalsIgnoreCase("Operation")) {
      this.driverCustom.clickUsingActions(
          this.driverCustom.getWebElement("//input[@dojoattachpoint='editPermissionsChoiceByAction']"));
    }
    else {
      this.driverCustom.clickUsingActions(
          this.driverCustom.getWebElement("//input[@dojoattachpoint='editPermissionsChoiceByRole']"));
    }
  }

  /**
   * Get displayed Association <br>
   * 
   * @author KYY1HC
   * @param association The section name to link with project area.
   * @param artifactContainerProject The project name is associated with Association in artifact container
   * @return true if the artifact container is displayed, otherwise return false.
   */
  public boolean isAssociationDisplayed(String association, String artifactContainerProject) {
    String xpathArtifactContainer =
        String.format("//td[text()='%s']/following-sibling::td/a[text()='%s']", association, artifactContainerProject);
    WebElement artifactContainer = this.driverCustom.getPresenceOfWebElement(xpathArtifactContainer);
    JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
    je.executeScript("arguments[0].scrollIntoView(true);", artifactContainer);
    this.waitForSecs(5);
    return this.driverCustom.isElementVisible(xpathArtifactContainer, Duration.ofSeconds(30));
  }

  /**
   * Get member name list from "Name" column at Member
   * Support for method {@link ManageProjectAreaPage#isDisplayedMemberListWithRoles(String,String)}
   * @author VUP5HC
   * @param section The section which contains member list (eg: Members, Administrators etc.)
   * @return memberList
   */
  public String getMemberNameList(String section) {
    List<WebElement> elementList = this.driverCustom.getWebElements(
        "//div[@role='heading' and text()='Members']/ancestor::div[@class='section']//div[@class='memberName']");
    StringBuilder sb = new StringBuilder();
    String memberList = "";
    for (WebElement element : elementList) {
      memberList = sb.append(element.getText()).append(";").toString();
    }
    // Remove last ";"
    return sb.deleteCharAt(memberList.length() - 1).toString();
  }

  /**
   * Get member role list from "Process Roles" column
   * Support for method {@link ManageProjectAreaPage#isDisplayedMemberListWithRoles(String,String)}
   * @author VUP5HC
   * @param section The section which contains member list (eg: Members, Administrators etc.)
   * @return memberRoleList
   */
  public String getMemberRoleList(String section) {
    List<WebElement> elementList = this.driverCustom.getWebElements(
        "//div[@role='heading' and text()='Members']/ancestor::div[@class='section']//div[@class='memberRoles']");
    StringBuilder sb = new StringBuilder();
    String memberRoleList = "";
    for (WebElement element : elementList) {
      memberRoleList = sb.append(element.getText()).append(";").toString();
    }
    // Remove last ";"
    return sb.deleteCharAt(memberRoleList.length() - 1).toString();
  }

  /**
   * @param memberNames Member Name in String, can get first list by method {@link ManageProjectAreaPage#getMemberNameList(String)}
   * @param memberRoles Member Rolein String, can get first list by method {@link ManageProjectAreaPage#getMemberRoleList(String)}
   * @return true if all name and role is matched and founded
   * @author VUP5HC
   */
  public boolean isDisplayedMemberListWithRoles(String memberNames, String memberRoles) {
    // Declare variables
    List<String> memberList = Arrays.asList(memberNames.split(";"));
    List<String> roleList = Arrays.asList(memberRoles.split(";"));
    String name = "";
    String role = "";
    String xpathMemberWithRole =
        "//div[@role='heading' and text()='Members']/ancestor::div[@class='section']//a[contains(text(),'%s')]/ancestor::tr[@id]//div[@class='memberRoles' and contains(text(),'%s')]";
    Iterator<String> memberIterator = memberList.iterator();
    Iterator<String> roleIterator = roleList.iterator();

    // Select to 250 for faster execution
    selectDislayMembersPerPage("250");

    // Verifying
    if (memberList.size() == roleList.size()) {
      while (memberIterator.hasNext() && roleIterator.hasNext()) {
        try {
          name = memberIterator.next().trim();
          role = roleIterator.next().trim();
          this.driverCustom.switchToDefaultContent();
          this.driverCustom.getPresenceOfWebElement(String.format(xpathMemberWithRole, name, role));
        }
        catch (Exception e) {
          LOGGER.LOG.error("Not found " + name + " with role: " + role);
          return false;
        }
      }
    }
    else {
      // Throw Exception if member names and roles is not the same.
      throw new InvalidArgumentException("Number of member names and roles does not match, Member names has: " +
          memberList.size() + " but roles has: " + roleList.size());
    }
    return true;
  }
  
  /**
   * After open 'Manage This Project Area' > select 'Timeline' tab
   * Edit properties for specific Timeline/Iteration
   * @author NCY3HC
   * @param param - contains:
   *        name - timeline's/ iteration's Name
   * 
   */
  public void editPropertiesTimelineOrIteration (final Map<String,String> param ) {
    String name = param.get("NAME");
    String editDialogName = param.get("EDIT_DIALOG_NAME");
    this.driverCustom.isElementVisible(CCMConstants.CCMPROJECTAREA_NEWCREATETIMELINE_NEWCRETAEITERATION_DISPLAYED_XPATH, timeInSecs, name);
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPROJECTAREA_NEWCREATETIMELINE_NEWCRETAEITERATION_DISPLAYED_XPATH, name).click();
    Button btnEditProperties = this.engine.findElementWithDuration(Criteria.isButton().withText("Edit Properties..."), timeInSecs).getFirstElement();
    btnEditProperties.click();
    Dialog dlgEditProperties = this.engine.findElementWithDuration(Criteria.isDialog().withTitle(editDialogName), timeInSecs).getFirstElement();
    Checkbox ckbSetTimeline = this.engine.findElementWithDuration(Criteria.isCheckbox().withLabel("Use this timeline as the project timeline").inContainer(dlgEditProperties), timeInSecs).getFirstElement();
    ckbSetTimeline.click();
  }
  /**
   * Click on Action Menu
   * select Add Category
   * @param option
   */
  
  @SuppressWarnings("javadoc")
  public void selectAddCategory(final String option) {
    waitForPageLoaded();
   WebElement button = this.driverCustom.getPresenceOfWebElement("//td[text()='Add Category...']");
    this.driverCustom.getClickableWebElement(button).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
}
  
  /**
   
   */
  
  @SuppressWarnings("javadoc")
  public void clickOnToAssociateTeamArea(final String teamArea) {
    waitForPageLoaded();
   WebElement button = this.driverCustom.getPresenceOfWebElement("//td[@title='Team Area'][1]");
    this.driverCustom.getClickableWebElement(button).click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
}
  
  /**
   * 
   * @param inputCategoryName
   */
  
  @SuppressWarnings("javadoc")
  public void inputCategory(final String inputCategoryName) {
    Text fCategoryName =
        this.engine.findElementWithDuration(Criteria.isTextField(), this.timeInSecs).getFirstElement();
    fCategoryName.clearText();
    fCategoryName.setText(inputCategoryName);
    LOGGER.LOG.info(inputCategoryName + " -  is added as Name to the Category.");
    Button btnOk =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOk.click();
    LOGGER.LOG.info("Clicked on ' OK' button in the Add Category Dialogue.");
  }
  
  /**
   * Open 'Manage This Project Area', get Project Area displayed in Process sharing section
   * 
   */
  
    public String getProjectAreainProcessSharing() {
      boolean isProjectAreaDisplayed = this.driverCustom.isElementVisible("//label[text()='Project Area:']//parent::div[@class='align_padded']//descendant::a", timeInSecs);
      if(isProjectAreaDisplayed) {
        String prjAreaName = this.driverCustom.getText("//label[text()='Project Area:']//parent::div[@class='align_padded']//descendant::a");
        return prjAreaName;
      }
      return null;
    }
    
   /**
     * Open 'Manage This Project Area'Set value to search box in Permissions tab
     * @param searchValue - Permission/Action which you want to search
     */
      public void setValueIntoSearchBox(String searchValue) {
        boolean searchBox = this.driverCustom.isElementVisible("//input[@aria-label='Search text']", timeInSecs);
        if (searchBox) {
          this.driverCustom.getWebElement("//input[@aria-label='Search text']").sendKeys(searchValue + Keys.ENTER);
          this.driverCustom.waitForSecs(Duration.ofSeconds(5));
          
        }
        
      }
        
}
