package com.weather.comparator.Steps.Steps;

import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.NdtvPageCommon;

public class NdtvCommonSteps {

  NdtvPageCommon ndtvPageCommon = new NdtvPageCommon();

  @When("^Ndtv site is opened$")
  public void openImdbUrl() throws Exception {
    ndtvPageCommon.openNdtvUrl();
  }

  @And("^User navigates to '(.*)' section in NDTV page$")
  public void navigateToSection(String section) {
    ndtvPageCommon.openSectionFromNav(section);
  }

  @And("^user searches for '(.*)' in the pin your city search bar$")
  public void searchCityInPinYourCityInput(String searchText) {
    ndtvPageCommon.performSearchInPinYourCityInput(searchText);
  }

  @Then("^validate the '(.*)' city name is displayed on the map$")
  public void validateTheCityNameOnMap(String cityName) {
    Assert.assertTrue(cityName + "is not displayed on the map",
        ndtvPageCommon.validateCityNameOnMapIsDisplayed(cityName));
  }


  @And("^save the weather information of '(.*)' on ndtv$")
  public void saveNdtvWeatherDetails(String cityName) {
    ndtvPageCommon.clickOnCityNameOnMap(cityName);
    ndtvPageCommon.setNdtvWeatherDetails();
  }

}
