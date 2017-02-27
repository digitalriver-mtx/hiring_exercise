package com.fatfoogoo;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "features",
        glue = {"classpath:com/fatfoogoo/glue"},
        monochrome = true,
        format = {"html:target/reports/results"})

public class TestRunner {

  private TestRunner() {
  }

  @AfterClass
  public static void teardown() {
    DriverHandler.getInstance().closeDriver();
  }

}
