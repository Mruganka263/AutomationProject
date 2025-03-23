package io.cucumber.core;

import java.util.HashMap;
import org.openqa.selenium.chrome.ChromeDriver;

public class Context {

  protected Manager manager;

  public Context(Manager manager) {
    this.manager = manager;
  }

  public ChromeDriver getDriver() {
    return manager.getDriver();
  }
}