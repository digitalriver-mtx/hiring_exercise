package com.fatfoogoo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverHandler {
  private static volatile DriverHandler _instance = null;
  private WebDriver _driver = null;

  private DriverHandler() {
    this._driver = this.getDriver();
  }

  public static DriverHandler getInstance() {
    if (_instance == null) {
      _instance = new DriverHandler();
    }
    return _instance;
  }

  public WebDriver getDriver() {
    if (this._driver == null) {
      System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
      this._driver = new ChromeDriver();
    }
    return this._driver;
  }

  public void closeDriver() {
    this._driver.quit();
  }

}
