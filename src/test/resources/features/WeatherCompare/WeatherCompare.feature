Feature: To compare the weather details for a city on different platform

  Scenario: To compares the weather details on different platforms for a particular city
    When Ndtv site is opened
    And User navigates to 'WEATHER' section in NDTV page
    And user searches for 'Bengaluru' in the pin your city search bar
    Then validate the 'Bengaluru' city name is displayed on the map
    And save the weather information of 'Bengaluru' on ndtv
    When user wants weather information for 'Bengaluru'
    And user gets the weather information
    Then comnpare the temp is within the range
    And comnpare the wind is within the range
    And comnpare the humidity is within the range
