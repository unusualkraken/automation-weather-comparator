package utilitities;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtilities {

  private static final String DEFAULT_PROPERTIES_FILE = "config.properties";
  private static Properties PROPERTIES = new Properties();
  private String propertyFile;

  public PropertiesUtilities() throws Exception {
    this.propertyFile = this.DEFAULT_PROPERTIES_FILE;
    InputStream input = getClass().getClassLoader().getResourceAsStream(this.propertyFile);

    if (input != null) {
      PROPERTIES.load(input);
    } else {
      throw new Exception("property file not found");
    }
  }

  public static String getProperty(String key) {
    return PROPERTIES.getProperty(key);
  }

  public static Properties getProperties() {
    return PROPERTIES;
  }
}
