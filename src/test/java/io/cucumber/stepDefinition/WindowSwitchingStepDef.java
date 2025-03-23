package io.cucumber.stepDefinition;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.pages.WindowSwitching;
import org.testng.Assert;

import java.util.Set;

public class WindowSwitchingStepDef extends Context {

    WindowSwitching window;
    Set<String> handles;
    String currentHandle;

    public WindowSwitchingStepDef(Manager manager) {
        super(manager);
        window = new WindowSwitching(manager.getDriver());
    }

    @When("user clicks Open new window link")
    public void userClicksOpenNewWindowLink() {
        window.getLink().click();
    }

    @Then("a new window should open")
    public void aNewWindowShouldOpen() {
        handles = manager.getDriver().getWindowHandles();
        currentHandle = manager.getDriver().getWindowHandle();
        for (String h : handles)
            if (!h.equals(currentHandle)) {
                manager.getDriver().switchTo().window(h);
                break;
            }

    }

    @And("verify text on new window")
    public void verifyTextOnNewWindow() {
        Assert.assertEquals(window.getTextMessage().getText(), "Simple page with simple test.");
    }

    @Then("navigate back to previous window")
    public void navigateBackToPreviousWindow() {
        manager.getDriver().switchTo().window(currentHandle);
    }

    @And("verify text on main window by switching iframe")
    public void verifyTextOnMainWindow() {
        manager.getDriver().switchTo().frame(window.getFrame());
        Assert.assertEquals(window.getTextMessage().getText(), "Simple page with simple test.");
    }
}
