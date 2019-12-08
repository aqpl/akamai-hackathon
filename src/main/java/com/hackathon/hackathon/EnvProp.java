package com.hackathon.hackathon;

import java.util.List;
import java.util.ResourceBundle;

import static com.mysql.cj.util.StringUtils.isNullOrEmpty;
import static java.util.Arrays.asList;

public class EnvProp {

  private static final String ALLOWED_CORS_ORIGIN_APP_PROP = getProperty("allowed.cors.origins");
  public static final List<String> ALLOWED_CORS_ORIGIN = asList(ALLOWED_CORS_ORIGIN_APP_PROP);

  private static String getProperty(String key) {
    String value = getProperty(key, null);
    if (isNullOrEmpty(value)) throw new RuntimeException();
    return value;
  }

  private static String getProperty(String key, String defaultValue) {
    try {
      ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
      return resourceBundle.getString(key);
    } catch (Exception ex) {
      return defaultValue;
    }
  }

}
