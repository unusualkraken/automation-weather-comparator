package com.weather.comparator.Steps.Steps;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import pages.WeatherComparator;

public class WeatherCompatorSteps {
  WeatherComparator weatherComparator = new WeatherComparator();


  @Then("^comnpare the temp is within the range$")
  public void validateTempVariance() {
    Assert.assertTrue("The Temp variance between NDTV and Open weather is more than expected",
        weatherComparator
            .compareTempVariance(Double.parseDouble(OpenWeatherMapSteps.getTempResponse())));
  }

  @Then("^comnpare the wind is within the range$")
  public void validateWindVariance() {
    Assert.assertTrue("The wind variance between NDTV and Open weather is more than expected",
        weatherComparator
            .compareWindVariance(Double.parseDouble(OpenWeatherMapSteps.getWindSpeedResponse())));
  }

  @Then("^comnpare the humidity is within the range$")
  public void validateHumidityVariance() {
    Assert.assertTrue("The humidity variance between NDTV and Open weather is more than expected",
        weatherComparator.compareHumidityVariance(
            Double.parseDouble(OpenWeatherMapSteps.getHumidityResponse())));
  }
}
