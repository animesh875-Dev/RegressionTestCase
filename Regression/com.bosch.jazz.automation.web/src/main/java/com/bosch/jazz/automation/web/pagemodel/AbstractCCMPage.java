/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMSubMenus;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMAllProjectsPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;

/**
 * <p>Represents abstraction of methods to change and configuration methods pages.
 * <br>Acts as super class to all change and configuration management pages.
 * <br>Declare methods which are common to change and configuration pages,
 * declare abstract methods which are to be overridden by every other pages of change and configuration management.
 *
 * @author HRT5KOR
 */
public class AbstractCCMPage extends AbstractJazzWebPage {

  /**
   * Constructor setting the object of {@link WebDriverCustom} class.
   * @param driverCustom required for interacting with the browser.
   */
  public AbstractCCMPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName, final Map<String, String> additionalParams) {
    try {
      this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEQUERYPAGE_WORKITEMS_LINK_XPATH);
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    try {
      this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEQUERYPAGE_WORKITEMS_LINK_XPATH);
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
  }

  /**
   * <p>
   * Change and configuration management has menus like "Project Dashboard", "Work items", "Plans" and etc.Refer {@link CCMMenus}<br>
   * Menus are shown in all the pages of change and configuration management application.<br>
   * This methods will be available in all the pages which extends this class.
   * <p>
   * After opening project area from the list of all project areas page {@link CCMAllProjectsPage}}, Dashboard will the
   * first page opened so methods {@link #openMenu(String)} and {@link #openSubMenu(String)} are implemented in this
   * page.
   *
   * @param menuName See {@link CCMMenus} enum values for names of the menus.
   *
   */
  public void openMenu(final String menuName) {
    waitForPageLoaded();
    Optional<WebElement> matchedOption =
        this.driverCustom.getWebElements("//a[starts-with(@class,'jazz-ui-MenuPopup')]").stream()
        .filter(x -> x.getText().equals(menuName)).findFirst();
    if (!matchedOption.isPresent()) {
      throw new WebAutomationException(menuName + " is invalid or does not exists in the list of menus.");
    }
    matchedOption.get().click();
    LOGGER.LOG.info("'" + menuName + "' clicked successfully from the list of menus.");
  }

  /**
   * <p>
   * After opening the menu {@link #openMenu(String)}, sub menu will be opened.<br>
   * e.g:To create a work item, work item types are displayed under work item menu.
   * <p>
   * Input the sub menu {@link CCMSubMenus} name to open required type under the menu {@link CCMMenusEnum}
   *
   * @param subMenu name of the sub menu, to find the sub menu name refer {@link CCMSubMenus} enumerator values.
   */
  public void openSubMenu(final String subMenu) {
    waitForPageLoaded();
    if (subMenu.startsWith("Select Type")) {
      clickOnSelectWorkItemType();
      LOGGER.LOG.info("'" + subMenu + "' clicked successfully from  menus drop down.");
      return;
    }
    try {
      this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMPROJECTAREAPAGE_SUBMENU_LINK_XPATH, subMenu);
      this.driverCustom.click(CCMConstants.CCMPROJECTAREAPAGE_SUBMENU_LINK_XPATH, subMenu);
      waitForSecs(5);
    }
    catch (Exception er) {
      throw new WebAutomationException(subMenu + " not found. " + "\n or \n" + er.getMessage());
    }
  }

  /**
   * <p>Open {@link CCMMenus#WORK_ITEMS} menu using {@link #openMenu(String)}.
   * <br>Open "Select Type" sub menu using {@link #openSubMenu(String)}.
   * <br>This method is called internally by {@link #openSubMenu(String)}.
   *
   */
  protected void clickOnSelectWorkItemType() {
    waitForPageLoaded();
    this.driverCustom.click(CCMConstants.CCMPROJECTAREAPAGE_WORKITEMSELECTTYPE_LINK_XPATH);
  }
}