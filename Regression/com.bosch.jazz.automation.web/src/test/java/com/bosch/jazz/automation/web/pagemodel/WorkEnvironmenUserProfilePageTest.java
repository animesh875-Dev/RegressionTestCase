/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.Enums.Day;

/**
 * @author NEE2KOR
 */
public class WorkEnvironmenUserProfilePageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads change and configuration management WorkEnvironmenUserProfile page and open the workEnvironment.
   */

  @Test
  public void testOpenWorkEnvironment() {
    loadPage("ccm/open_work_environment.html");
    WorkEnvironmenUserProfilePage workEnvironment = getJazzPageFactory().getWorkEnvironmenUserProfilePage();
    assertNotNull(workEnvironment);
    workEnvironment.openWorkEnvironment();
  }

  /**
   * Loads change and configuration management WorkEnvironmenUserProfile page and checks if selectTimeZoneDropBox()
   * checking the timeZone value in workEnvironment page
   */
  @Test
  public void testSelectTimeZoneDropBox() {
    loadPage("ccm/select_timeZone_dropBox.html");
    WorkEnvironmenUserProfilePage workEnvironment = getJazzPageFactory().getWorkEnvironmenUserProfilePage();
    assertNotNull(workEnvironment);
    String timeZone = "Europe/Berlin";
    workEnvironment.selectTimeZoneDropBox(timeZone);
  }

  /**
   * Loads change and configuration management WorkEnvironmenUserProfile page and checks if selectRegionalDropBox()
   * checking the regionalSetting value in workEnvironment page
   */

  @Test
  public void testSelectRegionalDropBox() {
    loadPage("ccm/select_regional_dropBox.html");
    WorkEnvironmenUserProfilePage workEnvironment = getJazzPageFactory().getWorkEnvironmenUserProfilePage();
    assertNotNull(workEnvironment);
    String regionName = "English";
    workEnvironment.selectRegionalDropBox(regionName);
  }

  /**
   * Loads change and configuration management WorkEnvironmenUserProfile page and checks if clickScheduleCheckBox()
   * clicking on scheduleCheckBox in workEnvironment page
   */

  @Test
  public void testClickScheduleCheckBox() {
    loadPage("ccm/click_schedule_checkBox.html");
    WorkEnvironmenUserProfilePage workEnvironment = getJazzPageFactory().getWorkEnvironmenUserProfilePage();
    assertNotNull(workEnvironment);

    workEnvironment.clickScheduleCheckBox(Day.SUNDAY.toString());
  }

  /**
   * Loads change and configuration management WorkEnvironmenUserProfile page and checks if clickOk() clicking on ok
   * button in workEnvironment page
   */

  @Test
  public void testClickOk() {
    loadPage("ccm/workEnvironment_clickOk.html");
    WorkEnvironmenUserProfilePage workEnvironment = getJazzPageFactory().getWorkEnvironmenUserProfilePage();
    assertNotNull(workEnvironment);

    workEnvironment.clickOk();
  }

  /**
   * Loads change and configuration management WorkEnvironmenUserProfile page and check "Schedule check box" is enabled,
   * sets work day length hours and quitting time.
   */
  @Test
  public void testScheduleDayAsWork() {
    loadPage("ccm/WorkEnvironmentscheduleDayAsWork.html");
    WorkEnvironmenUserProfilePage workEnvironment = getJazzPageFactory().getWorkEnvironmenUserProfilePage();
    assertNotNull(workEnvironment);
    workEnvironment.scheduleDayAsWork(Day.MONDAY.toString(), "08:00", "5:00 PM");
  }

}
