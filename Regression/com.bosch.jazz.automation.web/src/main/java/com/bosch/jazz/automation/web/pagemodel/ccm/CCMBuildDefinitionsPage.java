package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.util.Map;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMSubMenus;


/**
 * Represents the "Build Definitions" page in CCM application.
 */
public class CCMBuildDefinitionsPage extends CCMProjectAreaDashboardPage {

  private static final String URL_ACTION_SUFFIX = "#action=com.ibm.team.build.viewDefinitionList";


  /**
   * See {@link CCMProjectAreaDashboardPage#CCMProjectAreaDashboardPage(WebDriverCustom)}
   */
  public CCMBuildDefinitionsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * Opens the page by simplying opening the specific URL.
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    this.driverCustom.openURL(getProjectAreaURL(repositoryURL, projectAreaName) + URL_ACTION_SUFFIX);
  }

  /**
   * This method wait for the presence of Work Items menu builds in build defination page.
   */

  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMBUILDDEFINITION_WORKITEMMENU_BUILDS_XPATH);
  }

  /**
   * Opens the "Build Definitions" page by clicking on the right menu. Before this methods is executed the related
   * project area must already be opened in the browser by e.g. calling {@link #selectProjectArea(String)} in the
   * project area overview page or {@link #openProjectArea(String, String)}.
   */
  public void openViaMenu() {
    waitForPageLoaded();
    openMenu(CCMMenus.BUILDS.toString());
    openSubMenu(CCMSubMenus.BUILD_DEFINITIONS.toString());
  }


}
