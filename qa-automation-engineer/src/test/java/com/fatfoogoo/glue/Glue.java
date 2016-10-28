package com.fatfoogoo.glue;

import com.fatfoogoo.DriverHandler;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Glue {

  @Given("^the website \"([^\"]*)\" is opened$")
  public void the_website_is_opened(String url) throws Throwable {
    DriverHandler.getInstance().getDriver().get(url);
  }

  @When("^clicking on \"([^\"]*)\"$")
  public void clicking_on(String link) throws Throwable {
    WebDriver driver = DriverHandler.getInstance().getDriver();
    WebElement contactLink = driver.findElement(By.xpath("//a[text()=\"Contact Us\"]"));
    contactLink.click();
  }

  @Then("^the phone number \"([^\"]*)\" is visible$")
  public void the_phone_number_is_visible(String phone) throws Throwable {
    WebDriver driver = DriverHandler.getInstance().getDriver();
    WebElement phoneElement = driver.findElement(By.xpath("//*[text()=\"+1 (800) 598-7450\"]"));
    if (!phoneElement.isDisplayed()) {
      throw new ElementNotVisibleException("the phone number is not visible");
    }
  }

}
