package com.fatfoogoo.glue;

import com.fatfoogoo.DriverHandler;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Glue {

  @Given("^the website \"([^\"]*)\" is opened$")
  public void the_website_is_opened(String url) throws Throwable {
    DriverHandler.getInstance().getDriver().get(url);
  }

  @When("^clicking on \"([^\"]*)\" link$")
  public void clicking_on(String linkText) throws Throwable {
    WebDriverWait wait = getWebDriverWait();
    By locator = By.xpath("//a[text()=\"" + linkText + "\"]");
    WebElement link = wait.until(ExpectedConditions.elementToBeClickable(locator));
    link.click();
  }

  @Then("^the text \"([^\"]*)\" is visible$")
  public void the_phone_number_is_visible(String text) throws Throwable {
    WebDriverWait wait = getWebDriverWait();
    By locator = By.xpath("//*[text()=\"" + text + "\"]");
    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    if (!element.isDisplayed()) {
      throw new ElementNotVisibleException(String.format("the text \"%s\" is not displayed", text));
    }
  }

  private WebDriverWait getWebDriverWait() {
    WebDriver driver = DriverHandler.getInstance().getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 3);
    return wait;
  }

}
