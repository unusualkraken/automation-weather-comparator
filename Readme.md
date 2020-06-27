# Weather Comparator

Weather comparator is a testing automation framweork for comparing the weathers info listed in 
different 
platforms. The configutation of the framework is as follows:

- *Automation Framework* : BDD Cucumber
- *UI Automation Tool* : Selenium
- *Test Framework* : Junit
- *API Automation* : Rest-Assured


### Commands to run

```mvn clean install```

```mvn verify```


### Project Structure

 - ***src/main/java/core.web/WebdriverPageObject***
 This class is used to initiate and utilies all the APIs provided by the Webdriver. Some of the examples are: initializing the webdriver, setting the waits, methods to fetch elements and many more..

- ***src/main/java/pages***
 Pages packages will consist of all the Pageobject classes.

- ***src/main/java/utilities***
 Utilities package consisit of utility class for the framework.
It being used for the bellow mentioned utilities:
    1. Contants: To store all the constants at one place.
    2. BrowserNames: Enum to store the name of the browser
    3. PropertiesUtility : to fetch the property from the property files.

- ***src/test/java/CucumberRunner***
 CucumberRunner class is used to run the tests with configuration to be set in @CucumberOptions.

- ***src/test/resources/feature***
 features package is used to store all the features written in gherkin language.

- ***src/test/resources/Webdriver***
 This directory is used to store all the webdriver executables.

- ***src/test/resources/config.properties***
Property file to store the prooperties of the project level for configuration.



