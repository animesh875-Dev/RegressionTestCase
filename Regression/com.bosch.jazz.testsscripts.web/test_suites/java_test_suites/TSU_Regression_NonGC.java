/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.bosch.jazz.automation.web.common.constants.JRSdataSourceTest;
import com.bosch.jazz.automation.web.common.constants.JrsURLTest;
import com.bosch.jazz.automation.web.common.constants.PlansEnumTest;
import com.bosch.jazz.automation.web.common.constants.SourceControlEnumsTest;
import com.bosch.jazz.automation.web.common.constants.VariableExpanderTest;
import com.bosch.jazz.automation.web.config.ConfigReaderTest;
import com.bosch.jazz.automation.web.config.TestDataRuleTest;
import com.bosch.jazz.automation.web.excel.CommandExecutorTest;
import com.bosch.jazz.automation.web.excel.EnvironmentProviderTest;
import com.bosch.jazz.automation.web.excel.ExcelReaderTest;
import com.bosch.jazz.automation.web.excel.ExcelSuiteRunnerTest;
import com.bosch.jazz.automation.web.excel.ExcelTestScriptExecutorTest;
import com.bosch.jazz.automation.web.poc.TCS_UC_JRS_173490_Traceability_links_RQM_and_all_ALM_domains_LQE_data_source;
import com.bosch.jazz.automation.web.poc.TCS_UC_JRS_173490_Traceability_links_RQM_and_all_ALM_domains_LQE_data_source_Validation;

import java_test_scripts.DNG.TS_13143_addArtifactLinkUsingConfigurePageSettingsDropDown;
import java_test_scripts.DNG.TS_13159_browseTheArtifactsInArtifactsPage;
import java_test_scripts.DNG.TS_13424_browseTheArtifactsInsideTheModule;
import java_test_scripts.DNG.TS_16322_browseArtifactsUsingIDandText;
import java_test_scripts.DNG.TS_17473_browseTheArtifactsInsideTheCollections;
import java_test_scripts.DNG.TS_17474_browseArtifactsUsingInvalidIdSpecialcharactersAndNegativeTest;
import java_test_scripts.ETM.TS_14647_createTestCaseExecutionRecordInsideTestPlan;
import java_test_scripts.ETM.TS_14649_Create_TSER_Inside_TPL;
import java_test_scripts.EWM.TS_173431_createAworkitemFromdropdownmenu;
import java_test_scripts.dng.TCS_UC_DNG_173304_AddArtifactLink;


/**
 * @author NBO5KOR
 */


@RunWith(Suite.class)

@Suite.SuiteClasses({ 
  //DNG
  TS_13143_addArtifactLinkUsingConfigurePageSettingsDropDown.class,
  TS_13159_browseTheArtifactsInArtifactsPage.class,
  TS_13424_browseTheArtifactsInsideTheModule.class,
  TS_16322_browseArtifactsUsingIDandText.class,
  TS_17473_browseTheArtifactsInsideTheCollections.class,
  TS_17474_browseArtifactsUsingInvalidIdSpecialcharactersAndNegativeTest.class,
  //CCM
  TS_13302_chm_Query_Handling_Create_a_Query.class,
  TS_14392_addingWorkItemApprovalsInExistingWorkitem.class,
  TS_14373_createAWorkitemByCreateLinkedTask.class,
  TS_14344_createAWorkItemFromDropdownMenu.class,
  //RQM
  TS_14647_createTestCaseExecutionRecordInsideTestPlan.class,
  TS_14648_createTestCaseExecutionRecordOutsideTestPlan.class,
  TS_14649_createTestSuiteExecutionRecordInsideTestPlan.class,
  TS_14651_createTestSuiteExecutionRecordOutSideTestPlan.class

})
public class TSU_Regression_NonGC {

}
