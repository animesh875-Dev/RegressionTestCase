/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bosch.jazz.automation.web.AbstractFrameworkTest;
import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.DriverSetup;
import com.bosch.jazz.automation.web.common.WebDriverCustom;

/**
 * @author HCM6KOR Unit test class for CommandExecutor.
 */
public class CommandExecutorTest extends AbstractFrameworkUnitTest {

  /**
   * The ExpectedException rule allows you to verify that your code throws a specific exception.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * @throws Exception e
   */
  @SuppressWarnings("unchecked")
  @Test
  public void testexecuteScript() throws Exception {
    Map<String, String> env = new LinkedHashMap<>();
    List<ExcelScriptCommand> commandList = new ArrayList<>();
    ExcelScriptCommand command1 = new ExcelScriptCommand();
    command1.setAction("CommandExecutorData.testCommandExcecutor2");
    ExcelScriptCommand command2 = new ExcelScriptCommand();
    command2.setAction("Assert.assertEquals");
    SimpleImmutableEntry<String, String> map = new AbstractMap.SimpleImmutableEntry(null, "true");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list = new LinkedList<>();
    list.add(map);
    command2.setParameter(list);

    ExcelScriptCommand command4 = new ExcelScriptCommand();
    command4.setAction("set");
    SimpleImmutableEntry<String, String> map5 = new AbstractMap.SimpleImmutableEntry(null, "PI");
    SimpleImmutableEntry<String, String> map6 = new AbstractMap.SimpleImmutableEntry(null, "3.14");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list3 = new LinkedList<>();
    list3.add(map5);
    list3.add(map6);
    command4.setParameter(list3);

    ExcelScriptCommand command3 = new ExcelScriptCommand();
    command3.setAction("CommandExecutorData.testCommandExcecutor");
    SimpleImmutableEntry<String, String> map1 = new AbstractMap.SimpleImmutableEntry(null, "catframework");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list1 = new LinkedList<>();
    list1.add(map1);
    command3.setParameter(list1);

    ExcelScriptCommand command5 = new ExcelScriptCommand();
    command5.setAction("set");
    SimpleImmutableEntry<String, String> map7 = new AbstractMap.SimpleImmutableEntry(null, "PI");
    SimpleImmutableEntry<String, String> map8 = new AbstractMap.SimpleImmutableEntry(null, "lastResult");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list4 = new LinkedList<>();
    list4.add(map7);
    list4.add(map8);
    command5.setParameter(list4);

    ExcelScriptCommand command6 = new ExcelScriptCommand();
    command6.setAction("Assert.assertEquals");
    SimpleImmutableEntry<String, String> map9 = new AbstractMap.SimpleImmutableEntry(null, "true");
    SimpleImmutableEntry<String, String> map10 = new AbstractMap.SimpleImmutableEntry(null, "true");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list5 = new LinkedList<>();
    list5.add(map9);
    list5.add(map10);
    command6.setParameter(list5);

    ExcelScriptCommand command7 = new ExcelScriptCommand();
    command7.setAction("Assert.assertEquals");
    SimpleImmutableEntry<String, String> map11 = new AbstractMap.SimpleImmutableEntry(null, "Assertion failed here");
    SimpleImmutableEntry<String, String> map12 = new AbstractMap.SimpleImmutableEntry(null, "true");
    SimpleImmutableEntry<String, String> map13 = new AbstractMap.SimpleImmutableEntry(null, "true");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list6 = new LinkedList<>();
    list6.add(map11);
    list6.add(map12);
    list6.add(map13);
    command7.setParameter(list6);

    commandList.add(command1);
    commandList.add(command2);
    commandList.add(command4);
    commandList.add(command3);
    commandList.add(command5);
    commandList.add(command1);
    commandList.add(command6);
    commandList.add(command1);
    commandList.add(command7);
    ExcelTestScriptExecutor e = new ExcelTestScriptExecutor(new WebDriverCustom(driver, driverSetup));
    List<ExcelScriptResult> result = e.executeScript(env, commandList, driver, null);
    assertEquals("true", result.get(0).getReturnValue());
  }

  /**
   * This method tests the CommandExecutor for methods with single String parameter.
   *
   * @throws Exception exception
   */
  @SuppressWarnings("unchecked")
  @Test
  public void testExecuteCommandSingleParam() throws Exception {
    Map<String, String> env = new LinkedHashMap<>();
    ExcelScriptCommand command = new ExcelScriptCommand();
    command.setAction("CommandExecutorData.testCommandExcecutor");
    command.setActionType("xpath");
    SimpleImmutableEntry<String, String> map1 = new AbstractMap.SimpleImmutableEntry(null, "catframework");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list = new LinkedList<>();
    list.add(map1);
    command.setParameter(list);
    CommandExecutor ce = new CommandExecutor(null);
    ce.executeCommand(env, command);
    assertEquals("framework", env.get("lastResult"));
  }

  /**
   * This method tests the CommandExecutor for methods with zero parameters.
   *
   * @throws Exception exception
   */
  @Test
  public void testExecuteCommandMultiParam() throws Exception {
    Map<String, String> env = new LinkedHashMap<>();
    ExcelScriptCommand command = new ExcelScriptCommand();
    command.setAction("CommandExecutorData.testCommandExcecutor2");
    command.setActionType("xpath");
    CommandExecutor ce = new CommandExecutor(null);
    ce.executeCommand(env, command);
    assertEquals("true", env.get("lastResult"));
  }

  /**
   * This method tests the CommandExecutor for methods with map parameter and String parameters combined.
   *
   * @throws Exception exception
   */
  @SuppressWarnings("unchecked")
  @Test
  public void testExecuteCommandMapOfParam() throws Exception {
    Map<String, String> env = new LinkedHashMap<>();
    ExcelScriptCommand command = new ExcelScriptCommand();
    command.setAction("CommandExecutorData.testCommandExcecutor3");
    command.setActionType("xpath");
    SimpleImmutableEntry<String, String> map1 = new AbstractMap.SimpleImmutableEntry("name", "Robert");
    SimpleImmutableEntry<String, String> map2 = new AbstractMap.SimpleImmutableEntry("company", "Bosch");
    SimpleImmutableEntry<String, String> map3 = new AbstractMap.SimpleImmutableEntry(null, "Cat");
    SimpleImmutableEntry<String, String> map4 = new AbstractMap.SimpleImmutableEntry(null, "Framework");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list = new LinkedList<>();
    list.add(map1);
    list.add(map2);
    list.add(map3);
    list.add(map4);
    command.setParameter(list);
    CommandExecutor ce = new CommandExecutor(null);
    ce.executeCommand(env, command);
    assertEquals("RobertBoschCatFramework", env.get("lastResult"));
  }

  /**
   * This method tests the CommandExecutor for methods with only one map parameter.
   *
   * @throws Exception if the exception banki
   * @throws SecurityException to indicate a security violation.
   */
  @SuppressWarnings("unchecked")
  @Test
  public void testExecuteCommandMapOfParamStr() throws Exception {
    Map<String, String> env = new LinkedHashMap<>();
    ExcelScriptCommand command = new ExcelScriptCommand();
    command.setAction("CommandExecutorData.testCommandExcecutor4");
    command.setActionType("xpath");
    SimpleImmutableEntry<String, String> map1 = new AbstractMap.SimpleImmutableEntry("name", "Robert");
    SimpleImmutableEntry<String, String> map2 = new AbstractMap.SimpleImmutableEntry("company", "Bosch");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list = new LinkedList<>();
    list.add(map1);
    list.add(map2);
    command.setParameter(list);
    CommandExecutor ce = new CommandExecutor(null);
    ce.executeCommand(env, command);
    assertEquals("RobertBosch", env.get("lastResult"));
  }

  /**
   * This method is a WebDriverCustom based test for the CommandExecutor.
   *
   * @throws Exception file cannot be found.
   */
  @Test
  public void testGetTestcaseID() throws Exception {
    Path file = Paths.get("src/test/resources/rqm/test_case_id_files.html");
    AbstractFrameworkUnitTest.driver.get(file.toUri().toString());
    Map<String, String> env = new LinkedHashMap<>();
    ExcelScriptCommand command = new ExcelScriptCommand();
    command.setAction("RQMConstructionPage.getRqmArtifactID");
    DriverSetup driverSetup1 = new DriverSetup(AbstractFrameworkTest.class);
    if (driverSetup1.getBrowserMobProxy() != null) {
      driverSetup1.getBrowserMobProxy().newHar();
    }
    WebDriverCustom browser = new WebDriverCustom(driver, driverSetup1);
    CommandExecutor ce = new CommandExecutor(browser);
    ce.executeCommand(env, command);
    assertEquals("13233", env.get("lastResult"));
  }

  /**
   * This method tests the CommandExecutor complex parameter. The invoked method takes two String parameters. The
   * argument is passed as a map with two key value pairs. Here we get the formal parameter name of the invoking method
   * and compare it with the key from map. If it matches, then the value is assigned to the first String and so on.
   * "Class.method | paramName1:paramVal1, paramName2:paramVal2" is "method (String paramName1, String paramName2)"
   *
   * @throws Exception exception
   */
  @SuppressWarnings("unchecked")
  @Test
  public void testgetValuesAsArguments() throws Exception {
    Map<String, String> env = new LinkedHashMap<>();
    ExcelScriptCommand command = new ExcelScriptCommand();
    command.setAction("CommandExecutorData.testCommandExcecutor5");
    command.setActionType("xpath");
    command.setLineNumber(5);
    command.setSheetName("mysheet");
    SimpleImmutableEntry<String, String> map1 = new AbstractMap.SimpleImmutableEntry("firstString", "cat");
    SimpleImmutableEntry<String, String> map2 = new AbstractMap.SimpleImmutableEntry("secondString", "framework");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list = new LinkedList<>();
    list.add(map1);
    list.add(map2);
    command.setParameter(list);
    this.thrown.expect(NoSuchMethodException.class);
    CommandExecutor ce = new CommandExecutor(null);
    ce.executeCommand(env, command);
    assertEquals("catframework", env.get("lastResult"));
  }

  /**
   * This method tests the CommandExecutor complex parameter. The invoked method takes two String parameters. The
   * argument is passed as a map with one key value pairs. Here we get the formal parameter name of the invoking method
   * and compare it with the key from map. If it matches, then the value is assigned to the first String. Then we look
   * for the environment map for the next parameter. ------------------------------------------------------------------
   * "Class.method | map{pName1:pVal1}, env{pName2:pVal2}" is "method (String pName1, String pName2)"
   *
   * @throws Exception exception
   */
  @SuppressWarnings("unchecked")
  @Test
  public void testgetValuesFromEnvironment() throws Exception {
    Map<String, String> env = new LinkedHashMap<>();
    env.put("secondString", "framework");
    ExcelScriptCommand command = new ExcelScriptCommand();
    command.setAction("CommandExecutorData.testCommandExcecutor5");
    command.setActionType("xpath");
    SimpleImmutableEntry<String, String> map1 = new AbstractMap.SimpleImmutableEntry("firstString", "cat");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list = new LinkedList<>();
    list.add(map1);
    command.setParameter(list);
    this.thrown.expect(NoSuchMethodException.class);
    CommandExecutor ce = new CommandExecutor(null);
    ce.executeCommand(env, command);
    assertEquals("catframework", env.get("lastResult"));
  }

  /**
   * @throws Exception e
   */
  @Test
  public void testAssertEqulas() throws Exception {
    Map<String, String> env = new LinkedHashMap<>();
    env.put("lastResult", "true");
    ExcelScriptCommand command = new ExcelScriptCommand();
    command.setAction("Assert.assertEquals");
    command.setSheetName("mySheet");
    command.setLineNumber(8);
    this.thrown.expect(IllegalArgumentException.class);
    this.thrown.expectMessage(CoreMatchers
        .is("Parameter for the Assert.assertEquals command is invalid at sheet Name mySheet and line number 8"));
    CommandExecutor ce = new CommandExecutor(null);
    ce.executeCommand(env, command);
  }

  /**
   * @throws Exception e
   */
  @Test
  public void testSetCommand() throws Exception {
    Map<String, String> env = new LinkedHashMap<>();
    env.put("lastResult", "true");
    ExcelScriptCommand command = new ExcelScriptCommand();
    command.setAction("set");
    command.setSheetName("mySheet");
    command.setLineNumber(8);
    this.thrown.expect(IllegalArgumentException.class);
    this.thrown.expectMessage(
        CoreMatchers.is("Parameter for the set command is invalid at sheet Name mySheet and line number 8"));
    CommandExecutor ce = new CommandExecutor(null);
    ce.executeCommand(env, command);
  }

}

