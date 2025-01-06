/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


/**
 * @author BFE6SI
 */
public class TimeZoneConverter {

  // default dateFormat
  private static String[] dateFormat = { "MMM d, yyyy, h:mm:ss a" };


  /**
   * @param date String in the format "MMM d, yyyy, h:mm:ss a"
   * @param fromZone Timezone from where the date is coming from
   * @return converted Date
   */
  public static String changeTimezone(String date, String fromZone) {
    return changeTimezone(date, fromZone, TimeZone.getDefault().getID());
  }
  /**
   * @param date String in the format "MMM d, yyyy, h:mm:ss a"
   * @param totimeZone Timezone to where the date is be converted.
   * @param timeFormat predefined Timeformat String, not used unless we add more formats
   * @return converted Date
   */
  public static String changeTimezone(String date, String totimeZone,  int timeFormat) {
    return changeTimezone(date,getCurrentTimeZone() , totimeZone, timeFormat);
  }

  /**
   * @param date String in the format "MMM d, yyyy, h:mm:ss a"
   * @param fromZone Timezone from where the date is coming from
   * @param toZone Timezone in which the date shall be converted to
   * @return converted Date
   */
  public static String changeTimezone(String date, String fromZone, String toZone) {
    return changeTimezone(date, fromZone, toZone, 0);
  }

  /**
   * @param date String in the format "MMM d, yyyy, h:mm:ss a"
   * @param fromZone Timezone from where the date is coming from
   * @param toZone Timezone in which the date shall be converted to
   * @param timeFormat predefined Timeformat String, not used unless we add more formats
   * @return converted Date
   */
  private static String changeTimezone(String date, String fromZone, String toZone, int timeFormat) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat[timeFormat], Locale.US);
    LocalDateTime dateObject = LocalDateTime.parse(date, dtf);

    ZoneId fromTimeZone = getTimeZone(fromZone);
    ZoneId toTimeZone = getTimeZone(toZone);

    ZonedDateTime originalDate = dateObject.atZone(fromTimeZone);
    ZonedDateTime convertedDate = originalDate.withZoneSameInstant(toTimeZone);

    return convertedDate.format(dtf);
  }

  private static ZoneId getTimeZone(String timeZoneId) {
    String convertedZone = ZoneId.SHORT_IDS.get(timeZoneId);
    if (convertedZone != null) {
      return ZoneId.of(convertedZone);
    }
    return ZoneId.of(timeZoneId);

  }
  /**
   * 
   * @return current zone where application is running.
   */
  public static String getCurrentTimeZone()
  {
    Calendar calendar = new GregorianCalendar();
    TimeZone t=calendar.getTimeZone();
    return t.getID();
  }
}
