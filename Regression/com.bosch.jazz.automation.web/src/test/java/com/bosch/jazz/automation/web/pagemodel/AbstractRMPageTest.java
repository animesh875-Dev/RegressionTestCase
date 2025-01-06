/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.dng.RMArtifactsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;

/**
 * This class represents AbstractRMPageTest.
 */
public class AbstractRMPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */


  @Test
  public void testExecutionRecord() {
    loadPage("dng/select_project_area_and_global_configuration_current_project_component_alm_system.html");
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Cases");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "dng/testExecutionRecord01.html");
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    clickToPage.put(4, null);
    clickToPage.put(5, null);
    clickToPage.put(6, null);
    clickToPage.put(7, "dng/select_project_area_and_global_configuration_type_to_filter_list.html");
    clickToPage.put(8, "dng/testExecutionRecord03.html");
    clickToPage.put(9, null);
    clickToPage.put(10, null);
    clickToPage.put(11, "dng/testExecutionRecord04.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.selectGlobalConfiguration("ALM Test (RM)", "invalid", "testcomponent", "testcomponent Initial Stream");
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testSelectGlobalConfiguration() {
    loadPage("dng/select_project_area_and_global_configuration_current_project_component.html");
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Cases");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/select_project_area_and_global_configuration_current_project_component.html");
    clickToPage.put(2, "rqm/select_project_area_and_global_configuration_choose_another_component.html");
    clickToPage.put(3, "dng/select_project_area_and_global_configuration_type_to_filter_list.html");
    clickToPage.put(4, null);
    clickToPage.put(5, null);
    clickToPage.put(6, null);
    clickToPage.put(7, "rqm/select_project_area_and_global_configuration_component_and_ok.html");
    clickToPage.put(8, "dng/select_theconfig_Context.html");
    clickToPage.put(9, "dng/selectTheConfigContext_window.html");
    clickToPage.put(10, "rqm/select_project_area_and_global_configuration_stream.html");
    clickToPage.put(11, null);
    clickToPage.put(12, null);
    clickToPage.put(13, "rqm/select_project_area_and_global_configuration_stream_files/configurationPicker.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.selectGlobalConfiguration("ALM Test (RM)", "invalid", "ALM_System", "ALM_System_AcceptanceTest_Platform");
  }

  /*
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */

  /*
   * @Test public void testSelectProjectAreaAndGlobalConfiguration() {
   * loadPage("dng/select_project_area_and_global_configuration_pa.html"); RMArtifactsPage rqm =
   * getJazzPageFactory().getRMArtifactPage(); Map<String, String> additionalParams = new LinkedHashMap<String,
   * String>(); additionalParams.put("testArtifactName", "Test Cases"); Map<Integer, String> clickToPage = new
   * LinkedHashMap<Integer, String>(); clickToPage.put(1,
   * "dng/select_project_area_and_global_configuration_current_project_component.html"); clickToPage.put(2,
   * "rqm/select_project_area_and_global_configuration_current_project_component.html"); clickToPage.put(3,
   * "rqm/select_project_area_and_global_configuration_choose_another_component.html"); clickToPage.put(4,
   * "dng/select_project_area_and_global_configuration_type_to_filter_list.html"); clickToPage.put(5,
   * "dng/select_project_area_and_global_configuration_component_ok_all.html"); clickToPage.put(6, null);
   * clickToPage.put(7, null); clickToPage.put(8, "dng/select_theconfig_Context.html"); clickToPage.put(9,
   * "dng/select_theconfig_Context.html"); clickToPage.put(10, "dng/selectTheConfigContext_window.html");
   * clickToPage.put(11, "rqm/select_project_area_and_global_configuration_stream.html"); clickToPage.put(12, null);
   * clickToPage.put(13, null); clickToPage.put(14,
   * "rqm/select_project_area_and_global_configuration_stream_files/configurationPicker.html");
   * loadNewPageOnNthDriverClickElementCall(clickToPage); assertNotNull(rqm);
   * rqm.selectProjectAreaAndGlobalConfiguration("ALM Test (RM)", "invalid", "ALM_System",
   * "ALM_System_AcceptanceTest_Platform"); }
   *//**
      * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case
      * and check the test case passed or not.
      *//*
         * @Test public void testGetRMAttributes() { loadPage("dng/getRmAttributeValues.html"); RMArtifactsPage rqm =
         * getJazzPageFactory().getRMArtifactPage(); assertNotNull(rqm); rqm.getRMAttributes(); } /** Loads RQM test
         * case page and executionRecord(final Map<String, String> additionalParams) will run the test case and check
         * the test case passed or not.
         **/
  @Test(expected = Exception.class)
  public void testSelectConfigContext() {
    loadPage("dng/select_theconfig_Context.html");
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Cases");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "dng/select_theconfig_Context.html");
    clickToPage.put(2, "dng/selectTheConfigContext_window.html");
    clickToPage.put(3, "rqm/select_project_area_and_global_configuration_stream_files/configurationPicker.html");
    clickToPage.put(4, "rqm/select_project_area_and_global_configuration_stream_files/configurationPicker.html");
    clickToPage.put(5, null);
    clickToPage.put(6, null);
    clickToPage.put(7, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.selectConfigContext("Global Configuration", "Streams", " ALM_System_AcceptanceTest_Platform");
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test(expected = Exception.class)
  public void testSelectTheConfigContext() {
    loadPage("dng/select_theconfig_Context.html");
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Cases");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "dng/select_theconfig_Context.html");
    clickToPage.put(2, "dng/selectTheConfigContext_window.html");
    clickToPage.put(3, null);
    clickToPage.put(4, null);
    clickToPage.put(5, null);
    clickToPage.put(6, null);
    clickToPage.put(7, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    Map<String, String> param = new HashMap<String, String>();
    param.put("type", "Switch");
    param.put("Global Configuration", "Global Configuration");
    param.put("Streams", "Streams");
    param.put("streamName", "Sample Stream");
    rqm.selectTheConfigContext(param);
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testIsSeachResultsNotFound() {
    loadPage("dng/editArtifact_CommentUser.html");
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    rqm.isSeachResultsNotFound();
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  /*
   * @Test public void testGetRMSpecificationsSummary() { loadPage("dng/editArtifact_CommentUser.html"); RMArtifactsPage
   * rqm = getJazzPageFactory().getRMArtifactPage(); assertNotNull(rqm); rqm.getRMSpecificationsSummary(); }
   *//**
      * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case
      * and check the test case passed or not.
      */
  @Test(expected = Exception.class)
  public void testGetRMAttributeValue() {
    loadPage("dng/editArtifact_CommentUser.html");
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    rqm.getRMAttributeValue("CC-AS:");
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testOpenRMSpecification() {
    loadPage("dng/clikOnCreateButton.html");
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    rqm.openRMSpecification("188727");
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test(expected = Exception.class)
  public void testOpenRMInvalidSpecification() {
    loadPage("dng/clikOnCreateButton.html");
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    rqm.openRMSpecification("1234567");
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */


  @Test(expected = Exception.class)
  public void testGetCreationDateFromBaseline() {
    loadPage("dng/getCreationDateFromBaseline.html");
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Cases");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "dng/getCreationDateFromBaselineDialog.html");
    clickToPage.put(2, "dng/getCreationDateFromBaselineDialogSwitch.html");
    clickToPage.put(3, "dng/getCreationDateFromBaselineDialogSearch.html");
    assertNotNull(rqm);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.getCreationDateFromBaseline("Baselines", "New Baseline PPP_17_03_2021_15_03_999");
  }


  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   *
   * @throws ParseException parse exception
   */
  @Test (expected = Exception.class)
  public void testIsDate01beforeDate02() throws ParseException {
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    boolean flag = false;
    flag = rqm.isDate01beforeDate02("Mar 15, 2021, 6:47:50 PM", "Mar 15, 2021 6:49 PM");
    assertTrue(flag);
  }
  
  /**
   * Unit test for {@link AbstractRMPage#compareTwoInputAsText(String,String,String)} <br>
   */
  @Test
  public void testCompareTwoInputAsText() {
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    boolean flag = false;
    flag = rqm.compareTwoInputAsText("a", "ab", "contains");
    assertTrue(flag);
  }
  
  /**
   * Unit test for {@link AbstractRMPage#compareTwoInputAsNumber(String,String,String)}. Note: case 1 "less than" - true
   */
  @Test
  public void testCompareTwoInputAsNumber01() {
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    boolean flag = false;
    flag = rqm.compareTwoInputAsNumber("123", "124", "less than");
    assertTrue(flag);
  }

  /**
   * Unit test for {@link AbstractRMPage#compareTwoInputAsNumber(String,String,String)}. Note: case 2 "less than" -
   * fasle
   */
  @Test
  public void testCompareTwoInputAsNumber02() {
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    boolean flag = true;
    flag = rqm.compareTwoInputAsNumber("124", "123", "less than");
    assertFalse(flag);
  }

  /**
   * Unit test for {@link AbstractRMPage#compareTwoInputAsNumber(String,String,String)}. Note: case 3 "greater" - true
   */
  @Test
  public void testCompareTwoInputAsNumber03() {
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    boolean flag = false;
    flag = rqm.compareTwoInputAsNumber("124", "123", "greater");
    assertTrue(flag);
  }

  /**
   * Unit test for {@link AbstractRMPage#compareTwoInputAsNumber(String,String,String)}. Note: case 4 "greater" - fasle
   */
  @Test
  public void testCompareTwoInputAsNumber04() {
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    boolean flag = true;
    flag = rqm.compareTwoInputAsNumber("123", "124", "greater");
    assertFalse(flag);
  }

  /**
   * Unit test for {@link AbstractRMPage#compareTwoInputAsNumber(String,String,String)}. Note: case 5 "equal" - true
   */
  @Test
  public void testCompareTwoInputAsNumber05() {
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    boolean flag = false;
    flag = rqm.compareTwoInputAsNumber("124", "124", "equal");
    assertTrue(flag);
  }

  /**
   * Unit test for {@link AbstractRMPage#compareTwoInputAsNumber(String,String,String)}. Note: case 6 "equal" - fasle
   */
  @Test
  public void testCompareTwoInputAsNumber06() {
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    boolean flag = true;
    flag = rqm.compareTwoInputAsNumber("123", "124", "equal");
    assertFalse(flag);
  }


  /**
   * Unit test for {@link AbstractRMPage#compareTwoInputAsNumber(String,String,String)}. Note: case 7 "not equal" - true
   */
  @Test
  public void testCompareTwoInputAsNumber07() {
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    boolean flag = false;
    flag = rqm.compareTwoInputAsNumber("123", "124", "not equal");
    assertTrue(flag);
  }

  /**
   * Unit test for {@link AbstractRMPage#compareTwoInputAsNumber(String,String,String)}. Note: case 8 "not equal" -
   * fasle
   */
  @Test
  public void testCompareTwoInputAsNumber08() {
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    boolean flag = true;
    flag = rqm.compareTwoInputAsNumber("124", "124", "not equal");
    assertFalse(flag);
  }

  /**
   * Unit test for {@link AbstractRMPage#switchToTheOtherTab(String)} <br>
   * Case 1
   * @author LPH1HC
   */

  @Test
  public void testSwitchToTheOtherTab() {
    RMArtifactsPage rqm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rqm);
    loadPage("dng/FilterAddListValue_01.html");
    WebDriver driver1 = rqm.driverCustom.getWebDriver();
    // Close error dialog
    rqm.driverCustom.getWebElement("//a[@class='jazz-ui-SimpleToolbar-button close']").click();
    // Open new tab
    Actions actions = new Actions(driver1);
    actions.keyDown(Keys.LEFT_CONTROL)
        .click(rqm.driverCustom.getWebElement(RMConstants.RMMODULEINSIDE_ARTIFACT_ID_XPATH, "611268"))
        .keyUp(Keys.LEFT_CONTROL).build().perform();
    boolean result = false;

    result = rqm.switchToTheOtherTab("1");
    assumeTrue(result);
  }
  
  /**
   * <p>
   * Unit test cover for ${@link RMArtifactsPage#isChangesetDisplayed(String)} <br>
   * <p>
   * 
   * @author NVV1HC
   */
  @Test
  public void testIsChangesetDisplayed() {
    loadPage("dng/isChangesetDisplayed.html");
    RMArtifactsPage abstractRM = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(abstractRM);
    boolean result = abstractRM.isChangesetDisplayed("Test change set");
    assertTrue(result);
  }

  /**
   * Unit test for {@link AbstractRMPage#isDateTimeAtTheMoment(String, String)}
   * @author VDY1HC
   */
  @Test (expected = Exception.class)
  public void testIsDateTimeAtTheMoment() {
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    boolean flag = rm.isDateTimeAtTheMoment("Oct 18, 2021, 7:00:13 PM", "MMM d, yyyy, h:mm:ss aaa");
    assertTrue(!flag);
  }
}
