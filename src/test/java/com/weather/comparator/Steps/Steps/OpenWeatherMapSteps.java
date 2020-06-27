package com.weather.comparator.Steps.Steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilitities.constants.ServiceConstants;

public class OpenWeatherMapSteps {

  private static final Logger log = LoggerFactory.getLogger(OpenWeatherMapSteps.class);
  private static final String _APP_ID = "7fe67bf08c80ded756e598d6f8fedaea";
  private static String tempResponse;
  private static String windSpeedResponse;
  private static String humidityResponse;
  private String OPEN_WEATHER_ENDPOINT = "http://api.openweathermap.org/data/2.5/weather";
  private RequestSpecification request;
  private Response response;
  private JsonPath responseJson;

  public static String getTempResponse() {
    return tempResponse;
  }

  public static String getWindSpeedResponse() {
    return windSpeedResponse;
  }

  public static String getHumidityResponse() {
    return humidityResponse;
  }

  @When("^user wants weather information for '(.*)'$")
  public void userSetsParamForWeather(String city) {
    request =
        RestAssured.given().param(ServiceConstants.Q, city).param(ServiceConstants.APP_ID, _APP_ID);
  }

  @And("^user gets the weather information$")
  public void userGetsWeatherInformation() {
    response = request.when().get(OPEN_WEATHER_ENDPOINT);
    log.info("response : " + response.prettyPrint());
    responseJson = response.jsonPath();
    tempResponse =
        String.valueOf(Double.parseDouble(responseJson.get("main.temp").toString()) - 273);
    humidityResponse =
        String.valueOf(Double.parseDouble(responseJson.get("main.humidity").toString()));
    windSpeedResponse =
        String.valueOf(Double.parseDouble(responseJson.get("wind.speed").toString()));
  }
}
