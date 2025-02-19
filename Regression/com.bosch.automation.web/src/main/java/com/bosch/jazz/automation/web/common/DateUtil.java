/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Util class dealing with dates.
 */
public class DateUtil {

  private DateUtil() {
    // do nothing
  }

  /**
   * Returns the current date and time as string, using this format: dd_MM_yyyy_HH_MM_SS
   *
   * @return Current data and Time as string.
   */
  public static String getCurrentDateAndTime() {
    Date date1 = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_MM_SS");
    String dateFormatted = dateFormat.format(date1);
    return dateFormatted;
  }
  
  /**
   * @param dateTime is date
   * @return true if the dates are in ascending order , false if dates are in descending order,
   * true if the dates are not in order.
   */
  public static boolean isDatesAreinAscendingOrder(final List<Date> dateTime) {
   
    boolean flag = false;
    for(int i = 0; i < dateTime.size()-1; i++)
    {
     if (dateTime.get(i).compareTo(dateTime.get(i+1)) > 0) 
     {
        flag = false;
        break;
     }
     else if (dateTime.get(i).compareTo(dateTime.get(i+1)) < 0) 
       flag = true;
     else  if (dateTime.get(i).compareTo(dateTime.get(i+1))  == 0)
       flag = true;
  }
  
 return flag;
  }
  /**
   * @param dateTime is date
   * @return false if the dates are in ascending order , true if dates are in descending order,
   * true if the dates are not in order.
   */
  public static boolean isDatesAreinDescendingOrder(final List<Date> dateTime) {
    boolean flag = false;
    int j= dateTime.size()/2;
    for(int i = j; i < j+1; i++)
    {
     if (dateTime.get(i).compareTo(dateTime.get(i+1)) > 0) 
        flag = true;
     else if (dateTime.get(i).compareTo(dateTime.get(i+1)) < 0) {
       flag = false;
       break;
     }
     else  if (dateTime.get(i).compareTo(dateTime.get(i+1))  == 0)
       flag = true;
  }
  
 return flag;
  }

  /**
   * Get Current date in the dd_MM_yyyy format.
   * @return only current date.
   * @throws ParseException if date format is not matched.
   */
  public static String getCurrentData()throws ParseException {
    Date date1 = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
    String dateFormatted = dateFormat.format(date1);
    return dateFormatted;
  }
  /**
   * Compare current date to passed date.
   * @param date the date
   * @return true of date is older to current current.
   * @throws ParseException if date format is not matched.
   */
  public static boolean isDateOlderToCurrentDate(final String date) throws ParseException
  {
    Date curdate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
    String dateFormatted = dateFormat.format(curdate);
    SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
    Date date1 = sdf.parse(dateFormatted);
    Date date2 = sdf.parse(date); 
    return date1.after(date2);
  }
}
