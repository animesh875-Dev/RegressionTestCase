/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import java.security.InvalidParameterException;

/**
 * @author hrt5kor
 * Utility class to perform  actions on the Strings.
 */
public final class StringUtils {
  private StringUtils() {}
 /**
 * @param text provided to verify null or empty.
 */
public static void throwExceptionIfTestIsNullOrEmpty(final String text)
 {
   if(text==null || text.isEmpty())
   throw new InvalidParameterException("Provided input is null or empty.Input is:"+text);
 }
}
