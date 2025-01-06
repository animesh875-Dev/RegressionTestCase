/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.DateUtil;

/**
 * Unit test coverage for the methods of RMCollectionsPage.
 */
public class RMCollectionsPageTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test to verify {@link RMCollectionsPage#createCollection(Map)}.
   */
  @Test
  public void testCreateCollection() {
    loadPage("dng/cretaeCollection.html");
    RMCollectionsPage rm = getJazzPageFactory().getRMCollectionsPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    String name = "Test Collections " + DateUtil.getCurrentDateAndTime();
    additionalParams.put("COLLECTION_NAME", name);
    rm.createCollection(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMCollectionsPage#deleteCollections(String)}.
   */
  @Test
  public void testDeleteCollections() {
    loadPage("dng/select_and_delete_collections.html");
    RMCollectionsPage rm = getJazzPageFactory().getRMCollectionsPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/select_and_delete_collections.html");
    clickNumberToPagePath.put(5, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.deleteCollections("149411");
  }

  /**
   * <p>
   * Unit test to verify {@link RMCollectionsPage#addArtifactToCollection(String)}.
   */
  @Test
  public void testAddArtifactToCollection() {
    loadPage("dng/moveArtifact_TextField.html");
    RMCollectionsPage rm = getJazzPageFactory().getRMCollectionsPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/moveArtifact_DropMenu.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.addArtifactToCollection("148476");
  }

  /**
   * <p>
   * Unit test to verify {@link RMCollectionsPage#getCollectionID()}.
   */
  @Test
  public void testGetCollectionID() {
    loadPage("dng/cretaeCollection_ID.html");
    RMCollectionsPage rm = getJazzPageFactory().getRMCollectionsPage();
    assertNotNull(rm);
    assertEquals("127410", rm.getCollectionID());
  }

  /**
   * <p>
   * Unit test to verify {@link RMCollectionsPage#editCollections(Map)}.
   */
  @Test
  public void testEditCollections() {
    loadPage("dng/edit_collection_1.html");
    RMCollectionsPage rm = getJazzPageFactory().getRMCollectionsPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/edit_collection_2.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/edit_collection_3.html");
    clickNumberToPagePath.put(5, "dng/edit_collection_4.html");
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, null);
    clickNumberToPagePath.put(9, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    String name2 = "Test Artifact " + DateUtil.getCurrentDateAndTime();
    additionalParams.put("ARTIFACT_NAME", name2);
    additionalParams.put("COLLECTION_ID", "149466");
    additionalParams.put("ARTIFACT_TYPE",
        "[SAFe] Lifecycle Scenario (The Lifecycle Scenario illustrates the Vision by providing a...)");
    additionalParams.put("ARTIFACT_FORMAT", "Text");
    rm.editCollections(additionalParams);
  }
  /**
   * <p>
   * Unit test to verify {@link RMCollectionsPage#selectOptionFromCreateCollectionDropdown(String)}.
   */
  @Test
  public void testSelectOptionFromCreateCollectionDropdown() {
    loadPage("dng/select_option_from_create_collection_dropdown.html");
    RMCollectionsPage rm = getJazzPageFactory().getRMCollectionsPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    rm.selectOptionFromCreateCollectionDropdown("Collection");
  }

}
