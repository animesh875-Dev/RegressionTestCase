package com.bosch.jazz.automation.web.excel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author CIK5KOR
 *
 */
public class Monitor {
  
private Monitor() {
  
}

  /**
   * @throws IOException 
   * @throws MalformedURLException 
   * @throws SocketTimeoutException  is 
   */
  static Boolean isReachable(int timeOut, String spec) {
    HttpsURLConnection conn = null;
    try {
      conn = (HttpsURLConnection) new URL(spec).openConnection();
      conn.setConnectTimeout(timeOut);
      conn.setReadTimeout(timeOut);
      conn.connect();
      if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
        return true;
      }
    } 
    catch(Exception e)
    {
      
    }
    finally {
      if (conn != null) {
        conn.disconnect();
      }
    }
    return false;
  }

}