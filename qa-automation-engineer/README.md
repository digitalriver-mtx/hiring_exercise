# FatFooGoo New Hire Task

# Setup
* import the project as Maven project in the IDE of your choice (recommended: IntelliJ IDEA)
* required IDE plugins: Cucumber, JUnit
* Google Chrome must be installed
* download the Selenium Google Chrome driver, copy it to the bin folder inside the project and update the file name in class DriverHandler if necessary

# Introduction
This test project is set up as a Maven project with Gherkin and Selenium.
Gherkin files are used to describe a feature or a test. The Gherkin files are located in the "features" folder.
Each step in a Gherkin scenario is mapped to a Java method using an annotation with a regular expression. The Java classes implementing the Gherkin steps are called "glue".
The glue classes are located in the "com.fatfoogoo.glue" package.
The tests are executed by running the TestRunner class from the IDE.

# New Hire Task
* extend the "Digital River Contact Information" feature with a new scenario to verify the address of the Digital River Austria office
* add a second feature with the following scenario search for "FatFooGoo" on Google.com and verfiy that the correct phone number is displayed

# Resources & Documentation
* Gherkin reference: https://cucumber.io/docs/reference
* Selenium WebDriver docs: http://www.seleniumhq.org/docs/03_webdriver.jsp
