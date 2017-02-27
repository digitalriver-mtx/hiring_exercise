# FatFooGoo Hiring Exercise

# Setup
* import pom.xml as Maven project in the IDE of your choice (recommended: IntelliJ IDEA)
* required IDE plugins for IntelliJ IDEA: Cucumber, JUnit
* Google Chrome must be installed
* download the Selenium Google Chrome driver for your operating system and copy it to the src/resources/bin folder inside the project (update the property in DriverHandler.getDriver if necessary)

# Introduction
This test project is set up as a Maven project with Gherkin and Selenium.
Gherkin files are used to describe a feature or a test. The Gherkin files are located in the "features" folder.
Each step in a Gherkin scenario is mapped to a Java method using an annotation with a regular expression. The Java classes implementing the Gherkin steps are called "glue".
The glue classes are located in the "com.fatfoogoo.glue" package.
The tests are executed by running the TestRunner class from the IDE.

# Hiring Exercise
* extend the "Digital River Contact Information" feature with a new scenario to verify the address of the Digital River Austria office
* add a feature with the following scenario: search for "FatFooGoo" on Google.com and verify that the correct phone number is displayed
* add one successful scenario (= the test case should not fail) that throws an exception and handle the exception properly (e.g. catch an ElementNotFoundException)
* avoid code duplication and rework existing methods if necessary to make the more generic

# Resources & Documentation
* Gherkin reference: https://cucumber.io/docs/reference
* Selenium WebDriver docs: http://www.seleniumhq.org/docs/03_webdriver.jsp
