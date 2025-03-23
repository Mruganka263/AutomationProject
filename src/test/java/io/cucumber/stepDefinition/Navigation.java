package io.cucumber.stepDefinition;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.java.en.Given;
import io.cucumber.util.CommonUtilities;

import java.io.IOException;

public class Navigation extends Context {

    public Navigation(Manager manager) {
        super(manager);
    }

    @Given("^the page under test is '(.+)'$")
    public void navToPage(String url) throws IOException {
        manager.getDriver().get(CommonUtilities.getConfigProperty(url));
    }
}