package com.bosch.jazz.automation.web.pagemodel.dng.verification;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMCollectionsPageVerification;

/**
 * Unit tests for the RMCollectionsPageVerificationTest.
 */
public class RMCollectionsPageVerificationTest extends AbstractFrameworkUnitTest {

  @SuppressWarnings("unused")
  private static final WebDriver WebDriver = null;
  /**
   * <p>
   * Unit  test to verify method {@link RMCollectionsPageVerification#verifySelectOptionFromCreateCollectionDropdown(String, String)}
   */
  @Test
  public void testVerifySelectOptionFromCreateCollectionDropdown() {
  loadPage("dng/createNewArtifact.html");
  RMCollectionsPageVerification rmcv = getJazzPageFactory().getRMCollectionsPageVerification();
  assertNotNull(rmcv);
  rmcv.verifySelectOptionFromCreateCollectionDropdown("Collection", "true");
  }
  /**
   * <p>
   * Unit  test to verify method {@link RMCollectionsPageVerification#verifySelectOptionFromCreateCollectionDropdown(String, String)}
   */
  @Test
  public void testverifySelectOptionFromCreateCollectionDropdown() {
  loadPage("dng/select_option_from_create_collection_dropdown.html");
  RMCollectionsPageVerification rmcv = getJazzPageFactory().getRMCollectionsPageVerification();
  assertNotNull(rmcv);
  rmcv.verifySelectOptionFromCreateCollectionDropdown("Collection", "false");
  }
  /**
   * Unit test to verify {@link RMCollectionsPageVerification#verifyCreateCollection(Map, String)}.
   */
  @Test
  public void testverifyCreateCollection() {
    loadPage("dng/HoverOnLinksByText_01.html");
    RMCollectionsPageVerification rmcv = getJazzPageFactory().getRMCollectionsPageVerification();
    assertNotNull(rmcv);
    Map<String, String> additionalParams = new LinkedHashMap<>();
    additionalParams.put("COLLECTION_NAME", "Text");
    rmcv.verifyCreateCollection(additionalParams,"false");
  }
  /**
   * Unit test to verify {@link RMCollectionsPageVerification#verifyCreateCollection(Map, String)}.
   */
  @Test
  public void testVerifyCreateCollection() {
    loadPage("dng/createArtifact.html");
    RMCollectionsPageVerification rmcv = getJazzPageFactory().getRMCollectionsPageVerification();
    assertNotNull(rmcv);
    Map<String, String> additionalParams = new LinkedHashMap<>();
    additionalParams.put("COLLECTION_NAME", "}");
    rmcv.verifyCreateCollection(additionalParams,"true");
  }
}
