/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.TextField;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.container.cell.ColumnCellFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * @author hrt5kor
 */
public class WorkEnvironmenUserProfilePage extends UserProfilePage {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public WorkEnvironmenUserProfilePage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDropdownFinder(), new CheckboxFinder(), new LinkFinder(), new JazzTextFieldFinder(),
        new JazzDialogFinder(), new RowCellFinder(), new ColumnCellFinder());
  }

  /**
   * Opens work environment link.
   */
  public void openWorkEnvironment() {
    waitForPageLoaded();
    Link workEnvironmentLinkText = this.engine.findFirstElement(Criteria.isLink().withText("Work Environment"));
    workEnvironmentLinkText.click();
  }

  /**
   * Opens "Time Zone:" drop down and select the value.
   *
   * @param timeZone value to be selected.
   */
  public void selectTimeZoneDropBox(final String timeZone) {
    waitForPageLoaded();
    Dropdown timeZoneDropDown = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Time Zone:"));
    timeZoneDropDown.selectOptionWithText(timeZone);

  }

  /**
   * Opens "Regional Settings:" drop down and select the value.
   *
   * @param regionName value to be selected.
   */
  public void selectRegionalDropBox(final String regionName) {
    waitForPageLoaded();
    Dropdown regionalSettingDropDown =
        this.engine.findFirstElement(Criteria.isDropdown().withLabel("Regional Settings:"));
    regionalSettingDropDown.selectOptionWithText(regionName);

  }

  /**
   * Schedules day as holiday.
   *
   * @param monday enumerator to store value of Days.
   */
  public void scheduleDayAsHoliday(final String monday) {
    if (workDayStatus(monday)) {
      clickScheduleCheckBox(monday);
    }

    clickOk();
  }

  /**
   * Clicks on "Schedule check box", sets work day length hours and quitting time.
   *
   * @param weekDay enumerator to store vale of Days.
   * @param workLengthInHours stores length time in hours.
   * @param quittingTime stores quitting time as string.
   */
  public void scheduleDayAsWork(final String weekDay, final String workLengthInHours, final String quittingTime) {
    if (!workDayStatus(weekDay)) {
      clickScheduleCheckBox(weekDay);
    }
    TextField webElementForWorkdayLength =
        this.engine.findFirstElement(Criteria.isTextField().hasLabel("Workday Length (hours):"));
    TextField webElementForQuittingTime =
        this.engine.findFirstElement(Criteria.isTextField().hasLabel("Quitting Time:"));
    webElementForWorkdayLength.setText(workLengthInHours);
    webElementForQuittingTime.setText(quittingTime);

    clickOk();
  }


  /**
   * Gets the work day status.
   *
   * @param weekDay enumerator to store week day.
   * @return true if the "Schedule Sunday as a work day" is selected.
   */
  public boolean workDayStatus(final String weekDay) {
    Checkbox c = this.engine.findElement(Criteria.isCheckbox().withLabel("Schedule " + weekDay + " as a work day"))
        .getFirstElement();

    return c.getWebElement().isSelected();
  }

  /**
   * Select "Schedule Sunday as a work day" check box.
   *
   * @param weekDay enumerator to store week day.
   */
  public void clickScheduleCheckBox(final String weekDay) {
    waitForPageLoaded();
    Checkbox c = this.engine.findElement(Criteria.isCheckbox().withLabel("Schedule " + weekDay + " as a work day"))
        .getFirstElement();

    c.click();
  }

  /**
   * Click on "OK" button.
   */
  public void clickOk() {
    waitForPageLoaded();

    clickOnJazzButtons("OK");
  }

  /**
   * waitForPageLoaded is see to wait for the project name to load in the page, once the project will load in the
   * current page it will throw the control to next line.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, "Project");
  }
}
