/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;


/**
 * @author vam8kor
 */
public class RequirementsManagementEnum {

  /**
   * @author KDO1KOR
   */
  @SuppressWarnings("javadoc")
  public enum Menu {

                    ARTIFACTS("Artifacts"),
                    REVIEWS("Reviews"),
                    REPORTS("Reports");

    private final String name;

    private Menu(final String s) {
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

  @SuppressWarnings("javadoc")
  public enum ArtifactTypes {
                             ALL("All"),
                             MODULES("Modules"),
                             COLLECTIONS("Collections");

    private final String name;

    private ArtifactTypes(final String s) {
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

  @SuppressWarnings("javadoc")
  public enum Artifact {

                        NAME("Name"),
                        ARTIFACT_TYPE("Artifact type"),
                        ARTIFACT_FORMAT("Artifact format"),
                        TEMPLATE("Template"),
                        FOLDER("Folder"),
                        TAGS("Tags"),
                        OPEN_ARTIFACT("Open Artifact"),
                        FOLDER_NAME("Folder Name");


    private final String name;

    private Artifact(final String s) {
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

  @SuppressWarnings("javadoc")
  public enum ArtifactAttributes {

                                  ARTIFACT_TYPES("Artifact Types"),
                                  ARTIFACT_ATTRIBUTES("Artifact Attributes"),
                                  ATTRIBUTE_DATA_TYPES("Attribute Data Types"),
                                  LINK_TYPES("Link Types"),
                                  LINK_CONSTRAINTS("Link Constraints"),
                                  LINK_VALIDITY("Link Validity"),
                                  MODULE_OPTIONS("Module Options"),
                                  TEMPLATES("Templates"),
                                  TEAM_OWNERSHIP_OVERVIEW("Team Ownership Overview"),
                                  REQIF("ReqIF"),
                                  MIGRATION("Migration"),
                                  IMPORT_COMPONENT_PROPERTIES("Import Component Properties"),
                                  CONFIGURATION_MANAGEMENT("Configuration Management");


    private final String name;

    private ArtifactAttributes(final String s) {
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

  @SuppressWarnings("javadoc")
  public enum ArtifactContextMenu {

                                   ADD_LINK_TO_ARTIFACT("Add Link to Artifact"),
                                   PASTE_SPECIAL("Paste Special..."),
                                   COPY_ARTIFACT("Copy Artifact"),
                                   MORE("More...");

    private final String name;

    private ArtifactContextMenu(final String s) {
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

  @SuppressWarnings("javadoc")
  public enum LinkContextMenu {

                               CONFIRM_REMOVE_DIALOG("Confirm Removing This Link"),
                               ADD_LINK("Add Link"),
                               PASTE_AS_LINK("Paste as Link"),
                               REMOVE_LINK("Remove Link");

    private final String name;

    private LinkContextMenu(final String s) {
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

  @SuppressWarnings("javadoc")
  public enum AddLinkToArtifact {

                                 IMPLEMENTED_BY("Implemented By"),
                                 SATISFIED_BY_ARCHITECTURE_ELEMENT("Satisfied By Architecture Element"),
                                 VALIDATED_BY("Validated By"),
                                 LINK_TO("Link To"),
                                 LINK_FROM("Link From"),
                                 SATISFIES("Satisfies"),
                                 TRACKEDBY("Tracked By"),
                                 SATISFIED_BY("Satisfied By"),
CAT_AUTOMATION_OUT("CAT_Automation_New Link_Out"),
CAT_AUTOMATION_IN("CAT_Automation_New Link_In"),
CAT_LINK_OUT("CAT_Link_Type_out"),
CAT_LINK_IN("CAT_Link_Type_in");

    private final String name;

    private AddLinkToArtifact(final String s) {
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

  @SuppressWarnings("javadoc")
  public enum ArtifactProperties {

                                  ARTIFACT_CONTEXT_MENU("Artifact Context menu"),
                                  ARTIFACT_CONTEXT_SUBMENU("Artifact Context Submenu"),
                                  ID("id"),
                                  MODULE_ID("moduleId"),
                                  ID_2("id"),
                                  ID_NAME("ID_NAME"),
                                  /**
                                   * Artifact Container in Create Link dialog
                                   */
                                  ARTIFACT_CONTAINER("Artifact Container"),/**
                                   * Artifact Component in Create Link dialog
                                   */
                                  ARTIFACT_COMPONENT("Artifact Component"),
                                  /**
                                   * Search Type in Create Link dialog
                                   */
                                  DIAGRAM_NAME("Diagram Name"),
                                  /**
                                   * Type of the artifcat.
                                   */
                                  TYPE("Type"),
                                  /**
                                   * Status of the artifact.
                                   */
                                  STATUS("Status"),
                                  /**
                                   * State of the artifact.
                                   */
                                  STATE("State:");

    @SuppressWarnings("unused")
    private final String name;

    private ArtifactProperties(final String s) {
      this.name = s;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

  @SuppressWarnings("javadoc")
  public enum ModuleEnums {

                           SIDE_BAR_AREA("_sidebarArea"),
                           MODULE_SECTION("Module"),
                           ARTIFACT_SECTION("Selected Artifact"),
                           MODULE_LINKS("Module Links"),
                           LINKS("Links"),
                           ARTIFACT_LINKS("Artifact Links"),
                           LINKS_EXPLORER("Links Explorer"),
                           MODULE_TYPE("Module Type"),
                           MODULE_NAME("Module Name");

    private final String name;

    private ModuleEnums(final String s) {
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

  @SuppressWarnings("javadoc")
  public enum DiagramEnums {

                            DIAGRAM_VIEW("Diagram view of related links"),
                            C0NFIGURE_LINK_DISPLAY("Configure Link Display"),
                            FETCHING_LINKS("Fetching Links...");

    private final String name;

    private DiagramEnums(final String s) {
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

  @SuppressWarnings("javadoc")
  public enum SearchTypeEnums {

                               SEARCH_TYPE("Search Type"),
                               EXPLORER("Explorer"),
                               SEARCH("Search"),
                               PINBOARD("Pinboard");

    private final String name;

    private SearchTypeEnums(final String s) {
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
  
  
  @SuppressWarnings("javadoc")
  public enum EditArtifactContentEnums {
                              FIND_AND_REPLACE("Find And Replace");

    private final String name;

    private EditArtifactContentEnums(final String s) {
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
