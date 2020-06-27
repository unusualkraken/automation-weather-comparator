package core.web;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import utilitities.BrowserNames;
import utilitities.PropertiesUtilities;

public class WebdriverPageObject {
  private WebDriver driver;
  private PropertiesUtilities PROPERTIES;

  public WebdriverPageObject() {
    this.driver = this.initializeWebdriver();
    this.setImplicitWait();
    this.driver.manage().window().maximize();
  }

  public WebDriver initializeWebdriver() {
    try {
      this.PROPERTIES = new PropertiesUtilities();
      BrowserNames browserName = BrowserNames.valueOf(
          this.PROPERTIES.getProperty("webdriver.browser").isEmpty() ? "chrome".toUpperCase()
              : this.PROPERTIES.getProperty("webdriver.browser").toUpperCase());
      switch (browserName) {
        case CHROME:
          System.setProperty("webdriver.chrome.driver",
              "src/test/resources/webdrivers/chromedriver");
          return new ChromeDriver();
        case CHROME_HEADLESS:
          ChromeOptions chromeHeadlessOptions = new ChromeOptions();
          chromeHeadlessOptions.setHeadless(true);
          this.driver = new ChromeDriver(chromeHeadlessOptions);
          return this.driver;
        case FIREFOX:
          return new FirefoxDriver();
        case FIREFOX_HEADLESS:
          FirefoxOptions firefoxOptions = new FirefoxOptions();
          firefoxOptions.setHeadless(true);
          this.driver = new FirefoxDriver(firefoxOptions);
          return this.driver;
        case IE:
          return new InternetExplorerDriver();
        case SAFARI:
          return new SafariDriver();
        default:
          return new ChromeDriver();
      }
    } catch (Exception error) {
      return null;
    }
  }


  public WebDriver getDriver() {
    return this.driver;
  }

  public WebElement find(By selector) {
    return getDriver().findElement(selector);
  }

  public List<WebElement> findAll(By selector) {
    return getDriver().findElements(selector);
  }

  public void get(String urlPath) {
    getDriver().get(urlPath);
  }

  private void setImplicitWait() {
    this.driver.manage().timeouts().implicitlyWait(
        Long.valueOf(PropertiesUtilities.getProperty("driver.implicit.wait")), TimeUnit.SECONDS);
  }

}
