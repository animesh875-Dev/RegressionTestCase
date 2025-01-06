/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * This is the unit test to cover the methods in verificationReader class.
 */
public class VerificationConfigTest {

  /**
   * The ExpectedException rule allows you to verify that your code throws a specific exception.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * This method gives the input file and recieves the map from verification Reader.
   *
   * @throws IOException is thrown if file is not found.
   */
  @Test
  public void testReadExcelFile() throws IOException {
    String filePath = new File("src/test/resources/Verification_Config_Test/verification_file.xlsx").getAbsolutePath();
    VerificationReader vr = new VerificationReader();
    Map<String, List<VerificationConfig>> map = vr.readVerificationExcelFile(filePath, null);
    assertNotNull(map);   
    assertEquals("RMDashBoardPage.openMenu", map.get("RMDashBoardPage.openMenu").get(0).getPageModelMethod());
    assertEquals("RMDashBoardPageVerification.openMenuVerification", map.get("RMDashBoardPage.openMenu").get(0).getVerificationMethod());
    assertEquals(1, map.get("RMDashBoardPage.openMenu").get(0).getRating());
    assertEquals("false", map.get("RMDashBoardPage.openMenu").get(0).getHasParameter());
    assertEquals(3, map.get("RMDashBoardPage.openMenu").get(0).getLineNumber());
    assertEquals("verification", map.get("RMDashBoardPage.openMenu").get(0).getSheetName());
  }

  /**
   * This method is to test the exception condition when there is an empty row in input file.
   *
   * @throws IOException is thrown if file is not found.
   */
  @Test
  public void testReadExcelFileEmptyRow() throws IOException {
    String filePath = new File("src/test/resources/Verification_Config_Test/verification_file _Test_Exception.xlsx")
        .getAbsolutePath();
    VerificationReader vr = new VerificationReader();
    assertNotNull(vr);
    this.thrown.expect(InvalidParameterException.class);
    this.thrown.expectMessage(CoreMatchers.is("Data not present in row number 3 of sheet name verification"));
    vr.readVerificationExcelFile(filePath, null);
  }

}
