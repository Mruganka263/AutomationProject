package io.cucumber.core;

import org.openqa.selenium.chrome.ChromeDriver;

public class Manager {

  //ExtentReports extentReports;

  private ChromeDriver driver;

  public Manager() {
    //this.extentReports= new ExtentReports();
  }

  public ChromeDriver getDriver() {
    return driver;
  }

  public void setDriver(ChromeDriver driver) {
    this.driver = driver;
  }
}