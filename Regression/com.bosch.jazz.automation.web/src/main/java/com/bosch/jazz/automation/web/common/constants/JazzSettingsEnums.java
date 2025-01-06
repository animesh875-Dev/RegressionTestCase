/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;


/**
 * @author hrt5kor
 */
@SuppressWarnings("javadoc")
public class JazzSettingsEnums {


  public enum TeamArea {

                        NAME("Name"),
                        SUMMARY("Summary"),
                        DESCRIPTION("Description"),
                        TIMELINE("Timeline"),
                        MEMBERS("Members"),
                        ADMINISTRATORS("Administrators");

    private final String name;

    private TeamArea(final String s) {
      this.name = s;
    }

    public boolean equalsName(final String otherName) {
      return this.name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }

  }

  /**
   * Setting menus text value.
   *
   * @author hrt5kor
   */

  public enum SettingsMenusEnum {

                                 MY_STUFF("My Stuff"),
                                 USER_PROFILE("User Profile"),
                                 ADMINISTRATION("Administration"),
                                 HELP("Help");

    private final String name;

    private SettingsMenusEnum(final String s) {
      this.name = s;
    }

    public boolean equalsName(final String otherName) {
      return this.name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }

  }

  /**
   * Members Process Roles like Edit ,Add,Remove, Remove Members selection.
   *
   * @author kdo1kor
   */

  public enum TeamMemberAction {
                                EDITPROCESSROLES("Edit Process Roles..."),
                                ADDPROCESSROLES("Add Process Roles..."),
                                REMOVEPROCESSROLES("Remove Process Roles..."),
                                REMOVEMEMBER("Remove Member");

    private final String name;

    private TeamMemberAction(final String s) {
      this.name = s;
    }

    public boolean equalsName(final String otherName) {
      return this.name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }

  }


  /**
   * Settings sub menus text value.
   *
   * @author hrt5kor
   */
  public enum SettingsSubMenusEnum {
                                    MY_PROFILE_LICENSES("View My Profile and Licenses"),
                                    OPEN_MY_PERSONAL_DASHBOARD("Open My Personal Dashboard (default)"),
                                    MY_GLOBALIZATION_PREFERENCES("My Globalization Preferences"),
                                    LOG_OUT("Log Out"),
                                    MANAGE_THIS_PROJECT_AREA("Manage This Project Area"),
                                    MANAGE_PROJECT_AREA("Manage Project Areas"),
                                    MANAGE_COMPONENT_AND_PROPERTIES("Manage Component Properties"),
                                    MANAGE_PROJECT_AND_PROPERTIES("Manage Project Properties"),
                                    MANAGE_COMPONENTS_AND_CONFIG("Manage Components and Configurations"),
                                    HELP_CONTENTS("Help Contents"),
                                    MANAGE_TEMPLATES("Manage Templates"),
                                    JAZZ_TEAM_SERVER_HOME("Jazz Team Server Home"),
                                    GETTING_STARTED("Getting Started"),
                                    JAZZ_NET("Jazz.net"),
                                    JAZZ_NET_FORUM("Jazz.net Forum"),
                                    ABOUT_THIS_APPLICATION("About This Application");

    private final String name;

    private SettingsSubMenusEnum(final String s) {
      this.name = s;
    }

    public boolean equalsName(final String otherName) {
      return this.name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }

  }
}
