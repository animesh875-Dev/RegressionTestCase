/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.WebAutomationException;

/**
 * Unit test for the JRSBuildNewReportPage.
 *
 * @author hrt5kor
 */
public class JRSBuildNewReportPageTest extends AbstractFrameworkUnitTest {

  /**
   *
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * Loads report builder page, checks clickContinueButton clicking on continue button or not.
   */
  @Test
  public void clickContinueButtonTest() {
    loadPage("jrs/jrs_build_new_report_cancel_button.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.clickContinueButton();
  }

  /**
   * Loads report builder page name and share tab, checks saveReport button is saving or not.
   */
  @Test
  public void saveReportTest() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.saveReport();

  }

  /**
   * Loads report builder page and opens Choose artifacts, checks chooseArtfacts choosing artifact or not.
   */
  @Test
  public void chooseArtfactsTest() {
    loadPage("jrs/choose_artifact.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.chooseArtifacts("Work Item", "false");
  }

  /**
   * Loads report builder page and opens Choose artifacts, checks chooseChildArtfacts choosing artifact or not.
   */
  @Test
  public void chooseChildArtfactsTest() {

    loadPage("jrs/choose_child_artifact.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.chooseArtifacts("Global Component", "true");
  }

  /**
   * Loads report builder page, checks clickCancelButton clicking on cancel button or not.
   */
  @Test
  public void clickCancelButtonTest() {
    loadPage("jrs/jrs_build_new_report_cancel_button.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.clickCancelButton();
  }

  /**
   * Loads report builder page, checks chooseReportType choosing report or not.
   */
  @Test
  public void chooseReportTypeTest() {
    loadPage("jrs/jrs_build_new_report_cancel_button.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.chooseReportType("Historical Trends");
  }

  /**
   * Loads report builder page, checks attribute is set or not on choose data.
   */
  @Test
  public void setAttributeValueInRadioButtonTest() {
    loadPage("jrs/set_attribute_artifact.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.setAttributeValueInRadioButton();
  }

  /**
   * Loads report builder page, edit the data source on choose data.
   */
  @Test
  public void editDataSourceTest() {
    loadPage("jrs/edit_data_source_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/edit_data_source_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.editDataSource("Lifecycle Query Engine");
  }

  /**
   * Loads report builder page, click OK button to discard the changes on choose data.
   */
  @Test
  public void clickOkButtonToDiscardChangesTest() {
    loadPage("jrs/click_okbutton_to_discardchanges_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/click_okbutton_to_discardchanges_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.clickOkButtonToDiscardChanges();
  }

  /**
   * Loads report builder page, select the project area from the list.
   */
  @Test
  public void setProjectAreaNameTest() {
    loadPage("jrs/set_project_area.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.setProjectAreaName("BBM ALM (CCM)_System_Test_Testdata");

  }

  /**
   * Loads report builder format tab page, select the graph type.
   */
  @Test
  public void setGraphTypeTest() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/select_graph_type.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setGraphType("Line");

  }

  /**
   * Loads report builder page, sets condition and value into it.
   *
   * @throws InterruptedException in case of exception
   */
  @Test
  public void setConditionTest() throws InterruptedException {
    loadPage("jrs/set_conditions_2.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/set_conditions_5.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "jrs/set_conditions_7.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setCondition("Filed Against", "Public");

  }

 
  /**
   * Loads report builder page and perform logging out from report builder page.
   *
   * @throws InterruptedException in case of exception
   */
  @Test
  public void logOutFromJRSTest() throws InterruptedException {
    loadPage("jrs/log_out_jrs_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/log_out_jrs_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.logOutFromJRS();
  }

  /**
   * Loads report builder page, checks format result tab on choose data.
   */
  @Test
  public void checkFormatResultTabTest() {
    loadPage("jrs/check_format_result_tab.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.checkFormatResultTab();
  }

  /**
   * Loads report builder page, set the owner value on choose data.
   *
   * @throws InterruptedException - if exception occur.
   * @throws WebAutomationException - if exception occur.
   */
  @Test
  public void setOwnerValueTest() throws WebAutomationException, InterruptedException {
    loadPage("jrs/set_owner_value_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/set_owner_value_2.html");
    clickNumberToPagePath.put(2, "jrs/set_owner_value_3.html");
    clickNumberToPagePath.put(3, "jrs/set_owner_value_4.html");
    clickNumberToPagePath.put(4, "jrs/set_owner_value_5.html");
    clickNumberToPagePath.put(5, "jrs/set_owner_value_6.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setOwnerValue("Geetha Mallegowda (RBEI/ETC3)");
  }

  /**
   * Loads report builder page, checks tab switch to RUN REPORT.
   */
  @Test
  public void clickRunReportButtonTest() {
    loadPage("jrs/click_run_report_button.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.clickRunReportButton();
  }

  /**
   * Loads report builder page, checks tab switch to CHOOSE DATA.
   */
  @Test
  public void clickChooseDataButtonTest() {
    loadPage("jrs/clickChooseDataButton.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.clickChooseDataButton();
  }

  /**
   * Loads report builder historical trends page and set the time range.
   */
  @Test
  public void setTimeRangeforHistoricaltrendsTest() {
    loadPage("jrs/set_time_range_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/set_time_range_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setTimeRangeforHistoricaltrends("06/05/2019", "10/05/2019");
  }

  /**
   * Loads report builder time range page, verify the start date and end date.
   */
  @Test
  public void verifySetTimeRangeResultTest() {
    loadPage("jrs/set_time_range_verify.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.verifySetTimeRangeResult();
  }

  /**
   * Loads report builder time range page, verify the custom start date and end date in set condition.
   */
  @Test
  public void verifySetTimeRangeResultInSetConditionTest() {
    loadPage("jrs/set_time_range_verify_in_set_condition.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.verifySetTimeRangeResultInSetCondition();
  }

  /**
   * Loads report builder page format results page , sets units and dimensions for the graph.
   */
  @Test
  public void setGraphValuesInFormatResultTest() {
    loadPage("jrs/set_graph_values_in_format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.setGraphValuesInFormatResult("Count all results in the category", "Work Item ID");
  }

  /**
   * Loads report builder choose artifact page, Change the artifact.
   */
  @Test
  public void changeSourceArtifactInTracebilityTest() {
    loadPage("jrs/change_source_artifact_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/change_source_artifact_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.changeSourceArtifactInTracebility("Test Case Result");
  }

  /**
   * Loads report builder trace relationship page, add multi relation ships for the report.
   */
  @Test
  public void addMultipleRelationship() {
    loadPage("jrs/add_relationship_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_relationship_2.html");
    clickNumberToPagePath.put(2, "jrs/add_relationship_3.html");
    clickNumberToPagePath.put(3, "jrs/add_relationship_4.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addMultipleRelationship("Merge");
  }

  /**
   * Loads report builder trace relationship page, add path existence options to relation ship.
   *
   * @throws InterruptedException in case of exception
   */
  @Test
  public void addpathExistenceOptions() throws InterruptedException {
    loadPage("jrs/add_path_existance_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.addpathExistenceOptions("Optional");
  }

  /**
   * Loads report builder format results graph page, select date scale from the graph.
   */
  @Test
  public void setDateScaleTest() {
    loadPage("jrs/set_date_scale_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.setDateScale("Months");
  }

  /**
   * Loads report builder page, checks result is not populated on choose data.
   */
  @Test
  public void verifyResultForUnassignedValueTest() {
    loadPage("jrs/verify_result_for_unassigned_value.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("Result not generated"));
    jbnrp.verifyResultForUnassignedValue();
  }

  /**
   * Loads report builder page, checks result is populated on choose data.
   */
  @Test
  public void verifyResultForUnassignedValue_Condition_caseTest() {
    loadPage("jrs/verify_result_for_unassigned_value_conditional_case.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.verifyResultForUnassignedValue();
  }

  /**
   * Loads report builder graph page, select data for the graph.
   */
  @Test
  public void selectGraphInResultPageTest() {
    loadPage("jrs/select_graph_in_result_page.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.selectGraphInResultPage("Graph");
  }

  /**
   * Loads report builder trace relationship page, add multi relation ships for the report.
   */
  @Test
  public void addMultipleRelationshipConditionTwoTest() {
    loadPage("jrs/add_relationship_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_relationship_2.html");
    clickNumberToPagePath.put(2, "jrs/add_relationship_3.html");
    clickNumberToPagePath.put(3, "jrs/add_relationship_4.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addMultipleRelationship("Append");
  }

  /**
   * Loads report builder trace relationship page, add multi relation ships for the report.
   */
  @Test
  public void addMultipleRelationshipConditionThreeTest() {
    loadPage("jrs/add_relationship_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_relationship_2.html");
    clickNumberToPagePath.put(2, "jrs/add_relationship_3.html");
    clickNumberToPagePath.put(3, "jrs/add_relationship_4.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addMultipleRelationship("Append in new columns");
  }

  /**
   * Loads report builder format results graph page, select date scale from the graph.
   */
  @Test
  public void setDateScaleConditionTwoTest() {
    loadPage("jrs/set_date_scale_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.setDateScale("Days");
  }

  /**
   * Loads report builder format results graph page, select date scale from the graph.
   */
  @Test
  public void setDateScaleConditionThreeTest() {
    loadPage("jrs/set_date_scale_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.setDateScale("Weeks");
  }

  /**
   * Loads report builder format results graph page, select date scale from the graph.
   */
  @Test
  public void setDateScaleConditionFourTest() {
    loadPage("jrs/set_date_scale_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.setDateScale("Years");
  }

  /**
   * Loads report builder page, checks chooseReportType choosing report or not.
   */
  @Test
  public void chooseReportTypeConditionTwoTest() {
    loadPage("jrs/jrs_build_new_report_cancel_button.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.chooseReportType("Current Data");
  }

  /**
   * Loads report builder page, select the project area from the list.
   */
  @Test
  public void setProjectAreaNameConditionTest() {
    loadPage("jrs/set_project_area.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("Project Not Found"));
    jbnrp.setProjectAreaName("BBM ALM (CCM)_System_Test_Testdata_Invalid");
  }

  /**
   * Loads report builder format tab page, select the graph type.
   */
  @Test
  public void setGraphTypeConditionTwoTest() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/select_graph_type.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setGraphType("Stacked Bar");

  }

  /**
   * Loads report builder format tab page, select the graph type.
   */
  @Test
  public void setGraphTypeConditionThreeTest() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/select_graph_type.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setGraphType("Grouped Bar");

  }

  /**
   * Loads report builder format tab page, select the graph type.
   *
   * @throws InterruptedException , if any error occur.
   */
  @Test
  public void selectGraphInFormatResultTest() throws InterruptedException {
    loadPage("jrs/select_graph_in_format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.selectGraphInFormatResult();
  }

  /**
   * Loads report builder page name and share tab, checks saveReport button is disabled.
   */
  @Test
  public void saveReport_conditioncase_button_disabledTest() {
    loadPage("jrs/save_report_button_disabled.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    try {
      jbnrp.saveReport();
    }
    catch (WebAutomationException e) {
      assertEquals("Save button is disabled cannot able to save the report.", e.getMessage());
    }
  }

  /**
   * Load report builder page name and share tab, set attribute value in text box
   */
  @Test
  public void setAttributeValueInTextBoxTest() {
    loadPage("jrs/build_report_name_and_share_tab.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String attributeName = "Report Name";
    String attributeValue = "Report";
    jbnrp.setAttributeValueInTextBox(attributeName, attributeValue);
  }

  /**
   * Load report builder page name and share tab, throws WebAutomationException when attribute not found
   */
  @Test
  public void setAttributeValueInTextBoxTest_attributeNotFound() {
    loadPage("jrs/build_report_name_and_share_tab.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String attributeName = "Testing";
    String attributeValue = "Report";

    this.thrown.expect(WebAutomationException.class);
    this.thrown.expectMessage(CoreMatchers
        .is(attributeName + JRSBuildNewReportPage.ATTRIBUTE_NOT_FOUND_IN_THE_WORKITEM_EDITOR_OR_NOT_A_TYPE_TEXTBOX));

    jbnrp.setAttributeValueInTextBox(attributeName, attributeValue);
  }

  /**
   * Loads report builder results page and verify the calculated column result.
   */
  @Test
  public void verifyCalculatedColumnResultTest() {
    loadPage("jrs/verify_calculated_coloumn_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.checkCalculatedColumnResultsAddedInReport("ID [Type: Work Item]: Count total number of artifacts", "37");
  }


  /**
   * Loads report builder results page and verify the calculated column result.
   */
  @Test
  public void verifyCalculatedColumnResultTest1() {
    loadPage("jrs/verify_calculated_coloumn_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.checkCalculatedColumnResultsAddedInReport("Complexity [Type: Work Item]: Sum total", "0");
  }

  /**
   * Loads report builder results page and verify the calculated column result.
   */
  @Test
  public void verifyCalculatedColumnResultCaseTest() {
    loadPage("jrs/verify_calculated_coloumn_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("calculated calumn name is not correct"));
    jbnrp.checkCalculatedColumnResultsAddedInReport("Count total number of artifacts", "35");
  }

  /**
   * Loads report builder results page and verify the calculated column result.
   */
  @Test
  public void verifyCalculatedColumnResultCaseTwoTest() {
    loadPage("jrs/verify_calculated_coloumn_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("calculated column value is not correct"));
    jbnrp.checkCalculatedColumnResultsAddedInReport("ID [Type: Work Item]: Count total number of artifacts", "35");
  }

  /**
   * Load report builder page name and share tab, set attribute value in text area box
   */
  @Test
  public void setAttributeValueInTextAreaTest() {
    loadPage("jrs/build_report_name_and_share_tab.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String attributeName = "Description";
    String attributeValue = "Report";
    jbnrp.setAttributeValueInTextAreaBox(attributeName, attributeValue);
  }

  /**
   * Load report builder page name and share tab, throws WebAutomationException when attribute not found as text area
   */
  @Test
  public void setAttributeValueInTextAreaTest_attributeNotFound() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String attributeName = "Report name";
    String attributeValue = "Report";
    this.thrown.expect(WebAutomationException.class);
    this.thrown.expectMessage(
        CoreMatchers.is("Report name attribute not found in the workitem editor or not a type textbox."));
    jbnrp.setAttributeValueInTextAreaBox(attributeName, attributeValue);
  }

  /**
   * Load report builder page choose data tab, add relationship when relationShip attribute is empty
   *
   * @throws InterruptedException - in case of exception
   * @throws WebAutomationException - in case of exception
   */
  @Test
  public void addRelationshipTest_relationshipEmpty() throws WebAutomationException, InterruptedException {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String relationshipValue = "";
    String artfact = "";
    jbnrp.addRelationship(relationshipValue, artfact);
  }

  /**
   * Load report builder page choose data tab, add relationship invalid relationship value
   *
   * @throws InterruptedException - in case of exception
   * @throws WebAutomationException - in case of exception
   */
  @Test
  public void addRelationshipTest_invalidRelationShipValue() throws WebAutomationException, InterruptedException {
    loadPage("jrs/add_relationship.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_relationship_1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String relationshipValue = "Implemented By";
    String artfact = "";
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("Given Relationship Does not exist"));
    jbnrp.addRelationship(relationshipValue, artfact);
  }

  /**
   * Loads report builder page, checks for conditional case for set owner.
   *
   * @throws InterruptedException , if any error occur
   */
  @Test
  public void setOwnerValueconditioncase_ExceptionTest() throws InterruptedException {
    loadPage("dng/edit_artifact_attributes.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    this.thrown.expect(WebAutomationException.class);
    this.thrown.expectMessage(CoreMatchers.is(" attribute not found in the workitem editor or not a type textbox."));
    jbnrp.setOwnerValue("Geetha Mallegowda (RBEI/ETC3)");
  }

  /**
   * Loads report builder all reports page and finds the report from the list of reports.
   */
  @Test
  public void findReportTest() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String attributeName = "Report Name";
    String attributeValue = "Report";
    jbnrp.setAttributeValueInTextBox(attributeName, attributeValue);
    loadPage("jrs/find_reports.html");
    jbnrp.findReport();


  }

  /**
   * Loads report builder all reports page and finds the report from the list of reports.
   */
  @Test
  public void findReportConditionTest() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String attributeName = "Report Name";
    String attributeValue = "Report2";
    jbnrp.setAttributeValueInTextBox(attributeName, attributeValue);
    loadPage("jrs/find_reports.html");
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("Tag not found"));
    jbnrp.findReport();
  }

  /**
   * Loads format results tab page and sets calculated column, attribute and value.
   *
   * @throws InterruptedException if the element not found with in time.
   * @throws WebAutomationException if the element is not found.
   */
  @Test
  public void addCalculatedColumninFormatResultTest_notCountNumberOfArtifactsURL()
      throws WebAutomationException, InterruptedException {
    loadPage("jrs/format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(2, "jrs/add_calc_col_maximum_value.html");
    clickNumberToPagePath.put(3, "jrs/add_calc_col_max_value_comment_count.html");
    clickNumberToPagePath.put(4, "jrs/add_calculated_column_confirmation.html");
    clickNumberToPagePath.put(5, "jrs/format_result_with_calColumns.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addCalculatedColumninFormatResult("Maximum value", "Comment Count", "");
  }

  /**
   * Loads format results tab page and sets calculated column, attribute and value.
   *
   * @throws InterruptedException if the element not found with in time.
   * @throws WebAutomationException if the element is not found.
   */
  @Test
  public void addCalculatedColumninFormatResultConditionTest_CountNumberOfArtifactsURL()
      throws WebAutomationException, InterruptedException {
    loadPage("jrs/format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(2, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(3, "jrs/add_calculated_column_confirmation.html");
    clickNumberToPagePath.put(4, "jrs/format_result_with_calColumns.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addCalculatedColumninFormatResult("Count number of artifacts by URL",
        "All (Count all artifacts in the group)", "");
  }

  /**
   * Loads format results tab page and sets calculated column, attribute and value.
   *
   * @throws InterruptedException if the element not found with in time.
   * @throws WebAutomationException if the element is not found.
   */
  @Test
  public void addCalculatedColumninFormatResultConditionTest_CountNumberOfArtifactsID()
      throws WebAutomationException, InterruptedException {
    loadPage("jrs/format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(2, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(3, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(4, "jrs/add_calculated_column_confirmation.html");
    clickNumberToPagePath.put(5, "jrs/format_result_with_calColumns.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addCalculatedColumninFormatResult("Count number of artifacts by ID", "All (Count all artifacts in the group)",
        "");
  }

  /**
   * Loads format results tab page and sets calculated column, attribute and value.
   *
   * @throws InterruptedException if the element not found with in time.
   * @throws WebAutomationException if the element is not found.
   */
  @Test
  public void addCalculatedColumninFormatResultConditionTest_StringInvalid()
      throws WebAutomationException, InterruptedException {
    loadPage("jrs/format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(2, "jrs/add_calc_col_limit_URL.html");
    clickNumberToPagePath.put(3, "jrs/add_calc_col_limit_URL.html");
    clickNumberToPagePath.put(4, "jrs/add_calc_col_limit_URL_val.html");
    clickNumberToPagePath.put(5, "jrs/add_calculated_column_confirmation.html");
    clickNumberToPagePath.put(6, "jrs/format_result.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("Failed: Test Category Column not found"));
    jbnrp.addCalculatedColumninFormatResult("Count number of artifacts by URL",
        "Additional Information in HTML (Custom)", "No set value");
  }

  /**
   * Loads format results tab page and sets calculated column, attribute and value.
   *
   * @throws InterruptedException if the element not found with in time.
   * @throws WebAutomationException if the element is not found.
   */
  @Test
  public void addCalculatedColumninFormatResultConditionTest_Person()
      throws WebAutomationException, InterruptedException {
    loadPage("jrs/format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(2, "jrs/add_calc_col_limit_person.html");
    clickNumberToPagePath.put(3, "jrs/add_calc_col_limit_person.html");
    clickNumberToPagePath.put(4, "jrs/add_calc_col_limit_URL_val.html");
    clickNumberToPagePath.put(5, "jrs/add_calculated_column_confirmation.html");
    clickNumberToPagePath.put(6, "jrs/format_result_with_calColumns.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addCalculatedColumninFormatResult("Count number of artifacts by URL", "Owner", "No set value");
  }

  /**
   * Loads format results tab page and sets calculated column, attribute and value.
   *
   * @throws InterruptedException if the element not found with in time.
   * @throws WebAutomationException if the element is not found.
   */
  @Test
  public void addCalculatedColumninFormatResultConditionTest_PersonCurrentUser()
      throws WebAutomationException, InterruptedException {
    loadPage("jrs/format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(2, "jrs/add_calc_col_limit_person.html");
    clickNumberToPagePath.put(3, "jrs/add_calc_col_limit_person.html");
    clickNumberToPagePath.put(4, "jrs/add_calc_col_limit_URL_val.html");
    clickNumberToPagePath.put(5, "jrs/add_calculated_column_confirmation.html");
    clickNumberToPagePath.put(6, "jrs/format_result_with_calColumns.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addCalculatedColumninFormatResult("Count number of artifacts by URL", "Owner", "Current User");
  }

  /**
   * Loads format results tab page and sets calculated column, attribute and value.
   *
   * @throws InterruptedException if the element not found with in time.
   * @throws WebAutomationException if the element is not found.
   */
  @Test
  public void addCalculatedColumninFormatResultConditionTest_String()
      throws WebAutomationException, InterruptedException {
    loadPage("jrs/format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(2, "jrs/add_calc_col_limit_URL.html");
    clickNumberToPagePath.put(3, "jrs/add_calc_col_limit_URL.html");
    clickNumberToPagePath.put(4, "jrs/add_calc_col_limit_URL_val.html");
    clickNumberToPagePath.put(5, "jrs/add_calculated_column_confirmation.html");
    clickNumberToPagePath.put(6, "jrs/format_result_with_calColumns.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addCalculatedColumninFormatResult("Count number of artifacts by URL", "URL", "http");
  }

  /**
   * Loads format results tab page and sets calculated column, attribute and value.
   *
   * @throws InterruptedException if the element not found with in time.
   * @throws WebAutomationException if the element is not found.
   */
  @Test
  public void addCalculatedColumninFormatResultConditionTest_checkbox()
      throws WebAutomationException, InterruptedException {
    loadPage("jrs/format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(2, "jrs/add_calc_col_limit_checkbox.html");
    clickNumberToPagePath.put(3, "jrs/add_calc_col_limit_checkbox.html");
    clickNumberToPagePath.put(4, "jrs/add_calc_col_limit_checkbox.html");
    clickNumberToPagePath.put(5, "jrs/add_calculated_column_confirmation.html");
    clickNumberToPagePath.put(6, "jrs/format_result_with_calColumns.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addCalculatedColumninFormatResult("Count number of artifacts by URL", "Component (Custom)", "ASW");
  }

  /**
   * Loads format results tab page and sets calculated column, attribute and value.
   *
   * @throws InterruptedException if the element not found with in time.
   * @throws WebAutomationException if the element is not found.
   */
  @Test
  public void addCalculatedColumninFormatResultConditionTest_timerange()
      throws WebAutomationException, InterruptedException {
    loadPage("jrs/format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(2, "jrs/add_calc_col_limit_timerange.html");
    clickNumberToPagePath.put(3, "jrs/add_calc_col_limit_timerange.html");
    clickNumberToPagePath.put(4, "jrs/time_range_pop_up.html");
    clickNumberToPagePath.put(5, "jrs/time_range_pop_up.html");
    clickNumberToPagePath.put(6, "jrs/add_calc_col_limit_timerange.html");
    clickNumberToPagePath.put(7, "jrs/add_calculated_column_confirmation.html");
    clickNumberToPagePath.put(8, "jrs/format_result_with_calColumns.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addCalculatedColumninFormatResult("Count number of artifacts by URL", "Activation Date", "01/01/2019");
  }

  /**
   * Loads the url and waits until the page is fully loaded
   */
  @Test
  public void openTest() {
    loadPage("jrs/open.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/rs";
    jbnrp.open(repositoryURL, null, null);
  }

  /**
   * Loads run report results page and verify number of records.
   */
  @Test
  public void verifyTotalNoOfRecordsTest() {
    loadPage("jrs/verify_number_of_records.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.verifyTotalNoOfRecords("191");
  }

  /**
   * Loads run report results page and verify number of records.
   */
  @Test
  public void verifyTotalNoOfRecordsConditionTwoTest() {
    loadPage("jrs/no_result_table.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.verifyTotalNoOfRecords("0");
  }

  /**
   * Loads run report results page and verify number of records.
   */
  @Test
  public void verifyTotalNoOfRecordsConditionThreeTest() {
    loadPage("jrs/verify_number_of_records.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("No of records are not correct, total no of records in the reports is" +
        191 + " and total no recrods expected is " + 192));
    jbnrp.verifyTotalNoOfRecords("192");
  }

  /**
   * Loads report results page and verifies alert message.
   */
  @Test
  public void verifyAlertSessionTest() {
    loadPage("jrs/verify_alert_message.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.verifyAlertSession("Successfully saved the updated report.");
  }

  /**
   * Loads report results page and verifies alert message.
   */
  @Test
  public void verifyAlertSessionConditionTest() {
    loadPage("jrs/verify_alert_message.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("Alert Message not Matched,test case failed"));
    jbnrp.verifyAlertSession("Successfully saved_invalid");
  }

  /**
   * Loads format results tab page and sets calculated column, attribute and value.
   *
   * @throws InterruptedException if the element not found with in time.
   * @throws WebAutomationException if the element is not found.
   */
  @Test
  public void addCalculatedColumninFormatResultConditionTest_PersonOtherUser()
      throws WebAutomationException, InterruptedException {
    loadPage("jrs/format_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_calculated_column_popup.html");
    clickNumberToPagePath.put(2, "jrs/add_calc_col_limit_person.html");
    clickNumberToPagePath.put(3, "jrs/add_calc_col_limit_person.html");
    clickNumberToPagePath.put(4, "jrs/add_calc_col_limit_person.html");
    clickNumberToPagePath.put(5, "jrs/select_user.html");
    clickNumberToPagePath.put(6, "jrs/select_user_search.html");
    clickNumberToPagePath.put(7, "jrs/select_user_search.html");
    clickNumberToPagePath.put(8, "jrs/add_calc_col_limit_person.html");
    clickNumberToPagePath.put(9, "jrs/add_calculated_column_confirmation.html");
    clickNumberToPagePath.put(10, "jrs/format_result_with_calColumns.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addCalculatedColumninFormatResult("Count number of artifacts by URL", "Owner", "Ankita");
  }

  /**
   * Load report builder page name and share tab, set Tag value in tab of the Build new report page of JRS.
   */
  @Test
  public void setTagValueTest() {
    loadPage("jrs/build_report_name_and_share_tab.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String attributeValue = "System_test_2019.1.0";
    jbnrp.setTagValue(attributeValue);
  }

  /**
   * Load report builder page name and share tab, throws WebAutomationException when Tag Value not found
   */
  @Test
  public void setTagValueTest_attributeNotFound() {
    loadPage("dng/edit_artifact_attributes.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String attributeValue = "System_test_2019.1.0";

    this.thrown.expect(WebAutomationException.class);
    this.thrown.expectMessage(
        CoreMatchers.is(JRSBuildNewReportPage.ATTRIBUTE_NOT_FOUND_IN_THE_WORKITEM_EDITOR_OR_NOT_A_TYPE_TEXTBOX));

    jbnrp.setTagValue(attributeValue);
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void openColumnFiltersTest() {
    loadPage("jrs/report_result_column_filter_result.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/click_column_filter_report_result_page.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "jrs/report_result_column_filter_result.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);

    String columnValue = "System requirement";
    String columnName = "Stakeholder Requirement";
    jbnrp.openColumnFilters(columnValue, columnName);
  }

  /**
   * Load the report result page and set report name (Name and share).
   */
  @Test
  public void setReportTest() {
    loadPage("jrs/set_report_name.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String reportName = "Test report name";
    jbnrp.setReportName(reportName);
  }

  /**
   * Load the report result page and validate report report (qm test suite id and qm test suite execution id).
   */
  @Test
  public void validateReport() {
    loadPage("jrs/validate_testcase_id_one.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    String testcaseid = "4754";
    jbnrp.validateReport(testcaseid);
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testClickOnAddRelationship() {
    loadPage("jrs/click_on_add_relationship.html");
    JRSBuildNewReportPage jrs = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jrs);
    jrs.clickOnAddRelationship();
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testPicARelationship() {
    loadPage("jrs/pic_a_relationship.html");
    JRSBuildNewReportPage jrs = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jrs);
    jrs.picARelationship();
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testClickOkRelationship() {
    loadPage("jrs/pic_a_relationship.html");
    JRSBuildNewReportPage jrs = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jrs);
    jrs.clickOkRelationship();
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testPicAnAtrifactFromRelatedWorkItem() {
    loadPage("jrs/pic_an_atrifact_from_related_workitem.html");
    JRSBuildNewReportPage jrs = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jrs);
    jrs.picAnAtrifactFromRelatedWorkItem();
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testClickOkfromAnArtifact() {
    loadPage("jrs/pic_an_atrifact_from_related_workitem.html");
    JRSBuildNewReportPage jrs = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jrs);
    jrs.clickOkfromAnArtifact();
  }

  /**
   * Loads report builder page and opens Choose artifacts, checks chooseArtfacts choosing artifact or not.
   */
  @Test
  public void testChooseAnArtifact() {
    loadPage("jrs/choose_artifact.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.chooseAnArtifact("Requirement", "Stream");
  }
  
  /**
   * Loads report builder page and opens Choose artifacts, checks chooseArtfacts choosing artifact or not.
   */
  @Test
  public void testChooseAnArtifact1() {
    loadPage("jrs/chooseAnArtifactInDialog.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.chooseAnArtifact("Requirement", "Assumption");
  }
  
  /**
   * Loads report builder page and opens Choose artifacts, checks chooseArtfacts choosing artifact or not.
   */
  @Test
  public void testChooseAnArtifact2() {
    loadPage("jrs/chooseAnArtifactInDialog.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.chooseAnArtifact("Work Item", "");
  }

  /**
   * Loads report builder page, sets condition and value into it.
   */
  @Test
  public void testSetConditionType() {
    loadPage("jrs/set_conditions_5.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/set_conditions_1.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setConditionType("is");
  }

  /**
   * Loads report builder page, sets condition and value into it.
   */
  @Test
  public void testSetConditionAttributesOfvalue() {
    loadPage("jrs/set_conditions_5.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setConditionAttributesOfvalue("Work Item [Type: Work Item]");
  }

  /**
   * Loads report builder page, sets condition and value into it.
   */
  @Test
  public void testSearchCondition() {
    loadPage("jrs/set_conditions_5.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.searchCondition("Work Item [Type: Work Item]");
  }

  /**
   * Loads report builder page, sets condition and value into it.
   */
  @Test
  public void testClickOnRadiobutton() {
    loadPage("jrs/set_conditions_5.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.clickOnRadiobutton("Hidden");
  }

  /**
   * Loads report builder page, sets condition and value into it.
   */
  @Test
  public void testSetClickOnfilterRadioButton() {
    loadPage("jrs/set_conditions_5.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setClickOnfilterRadioButton(" Hidden");
  }

  /**
   * Loads report builder page, sets condition and value into it.
   */
  @Test
  public void testSetConditionDate() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setConditionDate("12/12/2020");
  }

  /**
   * Loads report builder page, sets condition and value into it.
   */
  @Test
  public void testSetDaysAgo() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    assertTrue(jbnrp.setDaysAgo("1", "years ago"));
  }

  /**
   * Loads report builder page, sets condition and value into it.
   */
  @Test
  public void testSetConditionInTextBox() {
    loadPage("jrs/set_condition_intextbox.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    assertTrue(jbnrp.setConditionInTextBox("9"));
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testSetJrsTextBoxValue() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPage jrs = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jrs);
    jrs.setJrsTextBoxValue("Y axis (vertical):", "test");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testSetGroupByAttributeValue() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPage jrs = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jrs);
    jrs.setGroupByAttributeValue("Work Item ID");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testSetDimensionValue() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPage jrs = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jrs);
    jrs.setDimensionValue("Work Item");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testSetUnits() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPage jrs = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jrs);
    jrs.setUnits("Count all results in the category");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testSelectGraphAndChart() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/select_graph_type.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.selectGraphAndChart("Graph type:", "Stacked Bar");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testSelectShowValuesAndShowTotals() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/select_graph_type.html");
    clickNumberToPagePath.put(2, "jrs/set_graph_type.html");
    clickNumberToPagePath.put(3, "jrs/select_graph_type.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.selectShowValuesAndShowTotals();
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testValidateHorizontalGraph() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/select_graph_type.html");
    clickNumberToPagePath.put(2, "jrs/set_graph_type.html");
    clickNumberToPagePath.put(3, "jrs/select_graph_type.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.validateHorizontalGraph();
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testSetAddAttributesToTheReport() {
    loadPage("jrs/set_add_attributes_to_the_report.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setAddAttributesToTheReport("Work Item [Type: Work Item]");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testSearchAttributeCondition() {
    loadPage("jrs/set_add_attributes_to_the_report.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.searchAttributeCondition("date");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testSetDefaultVisualization() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setDefaultVisualization("Table");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testSetJRSReportName() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setJRSReportName("test");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testClickOnReportTab() {
    loadPage("jrs/choose_report_type_conditional_case.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.clickOnReportTab("FORMAT RESULTS");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test(expected = Exception.class)
  public void testDeleteReport() {
    loadPage("jrs/delete_reports#mine-reports_files");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.deleteReport("01", null, null);
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testAddRelationshipByLabel() {
    loadPage("jrs/add_relationship.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_relationship_2.html");
    clickNumberToPagePath.put(2, "jrs/add_relationship_1.html");
    clickNumberToPagePath.put(3, "jrs/add_relationship_1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addRelationshipByLabel("Iteration", " All link types");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test
  public void testAddrelationshipByLabel() {
    loadPage("jrs/add_relationship_7.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_relationship_2.html");
    clickNumberToPagePath.put(2, "jrs/add_relationship_1.html");
    clickNumberToPagePath.put(3, "jrs/add_relationship_1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addRelationshipByLabel("Iteration", " All link types");
  }

  /**
   * Load the report result page and add column filter.
   */
  @Test(expected = Exception.class)
  public void testAddAttributeinFormatResult() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/set_add_attributes_to_the_report.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "jrs/add_calc_col_limit_person.html");
    clickNumberToPagePath.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addAttributeinFormatResult("Owner");
  }

  /**
   * Load report builder page name and share tab, select folder in tab of the Build new report page of JRS.
   */
  @Test
  public void selectFoldertest() {
    loadPage("jrs/build_report_name_and_share_tab.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/select_folder_1.html");
    clickNumberToPagePath.put(2, "jrs/select_folder_2.html");
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.selectFolder("SprintwiseData/2018");
  }

  /**
   * Load report builder page format results tab, sort type in tab of the Build new report page of JRS.
   */
  @Test
  public void sortTypeTest() {
    loadPage("jrs/build_report_format_results_tab.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.sortType("Title", "Descending");
    jbnrp.sortType("Id", "Ascending");
  }

  /**
   * Load report builder page format results tab, sort type in tab of the Build new report page of JRS.
   */
  @Test
  public void checkArtifacetRelationshipsAutoCreatedTest() {
    loadPage("jrs/Run_Report.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<String, String> coloumnValue = new HashMap<>();
    coloumnValue.put("columnValue1", "87482");
    coloumnValue.put("columnValue2", "1069786");
    jbnrp.checkArtifacetRelationshipsAutoCreated(coloumnValue);
  }

  /**
   * 
   */
  @Test
  public void selectShowAsDropDownCustomerExpressionTab() {
    loadPage("jrs/CustomerExpressionAttribute_ShowAs.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.selectShowAsDropDownCustomerExpressionTab("String (default)");
  }

  /**
   * 
   */
  @Test
  public void setCustomExpressionOfValue() {
    loadPage("jrs/setCustomExpressionOfValue.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.setCustomExpressionOfValue("QM Test Plan [Type: QM Test Plan]");
  }

  /**
   * 
   */
  @Test
  public void addCustomExpressionAttribute() {
    loadPage("jrs/addCustomExpression.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/addCustomExpression_1.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "jrs/addCustomExpression_3.html");
    clickNumberToPagePath.put(5, "jrs/addCustomExpression_4.html");
    clickNumberToPagePath.put(6, "jrs/addCustomExpression_5.html");
    clickNumberToPagePath.put(7, "jrs/addCustomExpression_6.html");
    clickNumberToPagePath.put(8, "jrs/addCustomExpression_7.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addCustomExpressionAttribute("QM Test Plan [Type: QM Test Plan]", "Planning effort", "String (default)",
        "MAX($QM Test Plan:Planning effort$)");
  }

  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#addACustomExpressionColumn(Map)}.
   * @author YNT2HC
   */
  @Test
  public void testAddACustomExpressionColumn() {
    loadPage("jrs/addCustomExpression_1.html"); 
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "jrs/addCustomExpression_3.html");
    clickNumberToPagePath.put(3, "jrs/addCustomExpression_4.html");
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "jrs/addCustomExpression_5.html");

    Map<String, String> coloumnValue = new HashMap<>();
    coloumnValue.put("ATTRIBUTE_OF", "QM Test Plan [Type: QM Test Plan]");
    coloumnValue.put("ATTRIBUTE_VALUE", "Planning effort");
    coloumnValue.put("CUSTOM_EXPRESSION", "MAX($QM Test Plan:Planning effort$)");
    coloumnValue.put("SHOW_AS", "String (default)");

    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addACustomExpressionColumn(coloumnValue);
  }
  
  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#addACustomExpressionColumn(Map)}.
   * @author YNT2HC
   */
  @Test
  public void testAddACustomExpressionColumn1() {
    loadPage("jrs/addACustomExpression2.html"); 
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, null);
    clickNumberToPagePath.put(9, "jrs/addACustomExpression4.html");

    Map<String, String> coloumnValue = new HashMap<>();
    coloumnValue.put("ATTRIBUTE_OF", "QM Test Result [Type: QM Test Result]");
    coloumnValue.put("ATTRIBUTE_VALUE", "Points Passed/Points Attempted");
    coloumnValue.put("CUSTOM_EXPRESSION", "(SUM($QM Test Result:Points Passed$)) / (SUM($QM Test Result:Points Attempted$))");
    coloumnValue.put("SHOW_AS", "Percentage (a number between 0 and 1 shown as a percentage in the client's locale)");

    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.addACustomExpressionColumn(coloumnValue);
  }

  /**
   * 
   */
  @Test
  public void verifyColumnAddedInThePreviewTableOfFormatResultTab() {
    loadPage("jrs/columnAddedInThePreviewTableOfFormatResultTable.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.verifyColumnAddedInThePreviewTableOfFormatResultTab("Planning effort (custom)");
  }

  /**
   * 
   */
  @Test(expected = Exception.class)
  public void verifyArtifactDisplayedInReport(){
    loadPage("jrs/artifactDisplayedInReport.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<String, String> coloumnValue = new HashMap<>();
    coloumnValue.put("value1", "AE Workplace ALM (QM)");
    coloumnValue.put("value2", "156");
    coloumnValue.put("value3", "TPL_Delta_6.0.3_ifix003");
    coloumnValue.put("value4", "0.0");
    jbnrp.verifyArtifactDisplayedInReport(coloumnValue);
  }

  /**
   * 
   */
  @Test
  public void verifyVerifySortOrder() {
    loadPage("jrs/verifySortOrder.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.verifySortOrder("Id", "1");
  }

  /**
   * 
   */
  @Test
  public void verifyChooseArtifact() {
    loadPage("jrs/verifyChooseArtifact.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<String, String> artifact = new HashMap<>();
    artifact.put("parentArtifact", "Work Item");
    artifact.put("childArtifact", "Epic");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/verifyChooseArtifact_1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.chooseArtifact(artifact);
  }
  
  /**
   * 
   */
  @Test
  public void verifyChooseArtifact1() {
    loadPage("jrs/chooseAnArtifactInDialog.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<String, String> artifact = new HashMap<>();
    artifact.put("parentArtifact", "Requirement");
    artifact.put("childArtifact", "");
    jbnrp.chooseArtifact(artifact);
  }

  /**
   * Load the add_relationship page and verify the required option is seleted or not
   */
  @Test
  public void testSelectRequiredOprtionFromAddRelationship() {
    loadPage("jrs/add_relationship_1.html");
    JRSBuildNewReportPage jrs = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jrs);
    jrs.selectRequiredOprtionFromAddRelationship("Required");
  }

  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#verifySetCondition(Map)}.
   * @author YNT2HC
   */
  @Test
  public void testVerifySetCondition() {
    loadPage("jrs/verify_set_condition.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<String, String> artifact = new HashMap<>();
    artifact.put("conditionType", "All must match");
    artifact.put("Type", "Type is Task");
    artifact.put("Status", "Status is any of In Progress, New");
    artifact.put("FiledAgainst", "Filed Against is PS-XS Automation Test (Change Management)");
    artifact.put("CreationDateIs", "Creation Date after 05/01/2021");
    artifact.put("CreationDateAfter", "Creation Date before 05/29/2021");
    assertTrue(jbnrp.verifySetCondition(artifact));
  }

  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#setGroupConditions(Map)}.
   * @author YNT2HC
   */
  @Test
  public void testSetGroupConditions() {
    loadPage("jrs/set_condition_group.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);

    Map<String, String> artifact = new HashMap<>();
    artifact.put("GROUP_TYPE", "Any can match (OR):");
    artifact.put("GROUP_CONDITION_ONE", "Status");
    artifact.put("GROUP_CONDITION_TWO", "Filed Against");

    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/set_condition_group.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "jrs/set_condition_group1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);

    jbnrp.setGroupConditions(artifact);
  }

  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#selectMultilePathOption(String, String)}.
   * @author YNT2HC
   */
  @Test
  public void testSelectMultilePathOption() {
    loadPage("jrs/selectMultipleOptions.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.selectMultilePathOption("Merge with...", "Requirement 1");
  }
  
  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#selectAnArtifactInDialog(String, String)}.
   * @author YNT2HC
   */
  @Test
  public void testSelectAnArtifactInDialog() {
    loadPage("jrs/chooseAnArtifactInDialog.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.selectAnArtifactInDialog("Requirement", "Assumption");
  }
  
  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#chooseComponentForConfigurations(String)}.
   * @author YNT2HC
   */
  @Test
  public void testChooseComponentForConfigurations() {
    loadPage("jrs/chooseComponentForConfiguration.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.switchToIFrame();
    jbnrp.chooseComponentForConfigurations("ALM_System");
  }
  
  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#selectOuterGroupType(String)}.
   * @author YNT2HC
   */
  @Test
  public void testSelectOuterGroupType(){
    loadPage("jrs/set_conditions_2.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    jbnrp.selectOuterGroupType("Not all match (AND NOT):");
  }
  
  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#chooseSelectAllVisibleItems()}.
   * @author KYY1HC
   */
  @Test
  public void testChooseSelectAllVisibleItems(){
    loadPage("jrs/ChooseSelectAllVisibleItems_01.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/ChooseSelectAllVisibleItems_02.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.chooseSelectAllVisibleItems();
  }

  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#verifyParameterValueInFilters(String)}.
   * Failed case
   * @author KYY1HC
   */
  @Test
  public void testVerifyParameterValueInFilters(){
    loadPage("jrs/VerifyParameterValueInFilters_01.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    assertEquals(false, jbnrp.verifyParameterValueInFilters(": Accepted, N/A, New/Changed, Rejected, To clarify"));
  }

  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#verifyParameterValueInFilters(String)}.
   * Passed case
   * @author KYY1HC
   */
  @Test
  public void testVerIfyParameterValueInFilters(){
    loadPage("jrs/VerifyParameterValueInFilters_01.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    assertEquals(true, jbnrp.verifyParameterValueInFilters(": 6244"));
  }
  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#selectAConfigurationAndValidate(String)}.
   */
  @Test
  public void testChooseAConfiguration() {
    loadPage("jrs/choose_a_configuration.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.switchToIFrame();
    jbnrp.selectAConfigurationAndValidate("ALM_System_AcceptanceTest_Platform");
  }
  /**
   * <p>
   * Unit test to verify {@link JRSBuildNewReportPage#setCreator(String)}.
   */
  @Test
  public void testSetCreator() {
    loadPage("jrs/set_creator_1.html");
    JRSBuildNewReportPage jbnrp = getJazzPageFactory().getJRSBuildNewReportPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/set_creator_2.html");
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jbnrp.setCreator("Prakash Lingaraj (MS/EMT4-XC)");
  }
}