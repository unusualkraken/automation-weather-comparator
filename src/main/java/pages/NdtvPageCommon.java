package pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.web.WebdriverPageObject;
import utilitities.PropertiesUtilities;
import utilitities.constants.LocatorConstants;

public class NdtvPageCommon extends WebdriverPageObject {

  private static final Logger log = LoggerFactory.getLogger(NdtvPageCommon.class);

  public static Map<String, String> getNdtvWeatherDetails() {
    return ndtvWeatherDetails;
  }

  private static Map<String, String> ndtvWeatherDetails = new HashMap<>();
  PropertiesUtilities propertiesUtilities;
  private String navMoreIcon = "//a[@class='topnavmore']";
  private String navBarSection =
      "//div[@class='topnav_cont']//a[text()='" + LocatorConstants.TEXT_PRESENT + "']";
  private String pinYourCitySearchInput = "//input[@id='searchBox']";
  private String cityOptions =
      "//div[@class='message']//label[@for='" + LocatorConstants.TEXT_PRESENT + "']";
  private String cityOnMap =
      "//div[@class='cityText' and text()='" + LocatorConstants.TEXT_PRESENT + "']";
  private String cityWeatherDetailsSelector =
      "//div[@Class='leaflet-popup-content']//span[@class" + "='heading']/b";

  public void openNdtvUrl() throws Exception {
    propertiesUtilities = new PropertiesUtilities();
    String ndtvUrl = PropertiesUtilities.getProperty("browser.url.ndtv");
    get(ndtvUrl);
  }

  private void clickOnNavMoreIcon() {
    find(By.xpath(navMoreIcon)).click();
  }


  public void openSectionFromNav(String sectionName) {
    clickOnNavMoreIcon();
    find(By.xpath(navBarSection.replace(LocatorConstants.TEXT_PRESENT, sectionName))).click();
  }


  public void setSearchInput(String cityName) {
    find(By.xpath(pinYourCitySearchInput)).clear();
    find(By.xpath(pinYourCitySearchInput)).sendKeys(cityName);
  }

  public void selectCityFromOptions(String cityName) {
    if (find(By.xpath(cityOptions.replace(LocatorConstants.TEXT_PRESENT, cityName)))
        .isDisplayed()) {
      WebElement cityOptionElement =
          find(By.xpath(cityOptions.replace(LocatorConstants.TEXT_PRESENT, cityName)));
      if (find(
          By.xpath(cityOptions.replace(LocatorConstants.TEXT_PRESENT, cityName).concat("/input")))
              .getAttribute(LocatorConstants.CHECKED) == null) {
        cityOptionElement.click();
      }
    } else {
      log.error(cityName + "option is not found.");
    }
  }

  public void performSearchInPinYourCityInput(String cityName) {
    setSearchInput(cityName);
    selectCityFromOptions(cityName);
  }

  public boolean validateCityNameOnMapIsDisplayed(String cityName) {
    return find(By.xpath(cityOnMap.replace(LocatorConstants.TEXT_PRESENT, cityName))).isDisplayed();
  }

  public void clickOnCityNameOnMap(String cityName) {
    find(By.xpath(cityOnMap.replace(LocatorConstants.TEXT_PRESENT, cityName))).click();
  }

  public void setNdtvWeatherDetails() {
    int contentFieldsSize = findAll(By.xpath(cityWeatherDetailsSelector)).size();
    if (contentFieldsSize > 0) {
      for (int contentIterator = 0; contentIterator < contentFieldsSize; contentIterator++) {
        String detail =
            findAll(By.xpath(cityWeatherDetailsSelector)).get(contentIterator).getText();
        ndtvWeatherDetails.put(detail.split(":")[0], detail.split(":")[1].split(" ")[1]);
      }

      System.out.print(ndtvWeatherDetails);
    } else {
      log.error("No city details found.");
    }
  }

}
