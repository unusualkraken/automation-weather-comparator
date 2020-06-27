package pages;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utilitities.PropertiesUtilities;
import utilitities.constants.NdtvDetailsFieldsConstants;


public class WeatherComparator {
  private static final Logger log = LoggerFactory.getLogger(WeatherComparator.class);
  Map<String, String> ndtvWeatherDetails = NdtvPageCommon.getNdtvWeatherDetails();

  public boolean compareTempVariance(Double tempResponse) {
    Double tempInNdtv =
        Double.parseDouble(ndtvWeatherDetails.get(NdtvDetailsFieldsConstants.TEMPERATURE));
    Double tempVariance =
        Double.parseDouble(PropertiesUtilities.getProperty("temp.variance" + ".percent"));

    return (Math.abs(tempInNdtv - tempResponse) <= tempVariance);
  }


  public boolean compareWindVariance(Double windResponse) {
    Double windInNdtv = Double.parseDouble(ndtvWeatherDetails.get(NdtvDetailsFieldsConstants.WIND));
    Double windVariance =
        Double.parseDouble(PropertiesUtilities.getProperty("wind.variance" + ".percent"));

    return (Math.abs(windInNdtv - windResponse) <= windVariance);
  }


  public boolean compareHumidityVariance(Double humidityResponse) {
    Double humidityInNdtv = Double
        .parseDouble(ndtvWeatherDetails.get(NdtvDetailsFieldsConstants.HUMIDITY).split("%")[0]);
    Double humidityVariance =
        Double.parseDouble(PropertiesUtilities.getProperty("humidity.variance" + ".percent"));

    return (Math.abs(humidityInNdtv - humidityResponse) <= humidityVariance);
  }

}
