package io.cucumber.stepDefinition;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.pages.WebForm;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

public class WebFormStepDef extends Context {

    WebForm webForm;
    JavascriptExecutor js;

    public WebFormStepDef(Manager manager) {
        super(manager);
        webForm = new WebForm(manager.getDriver());
        js = (JavascriptExecutor) manager.getDriver();
    }

    @When("Enter {string} text/value in {string} field")
    public void enterTextInTextInputField(String value, String textboxName) {
        webForm.enterTextInInputField(textboxName, value);
    }

    @And("Check whether Disable input field is disabled")
    public void checkWhetherDisableInputFieldIsDisabled() {
        Assert.assertFalse(webForm.checkIfDisabled());
    }

    @And("Select {string} value from select dropdown")
    public void selectValueFromSelectDropdown(String value) {
        webForm.selectFromDropdown(value);
        Select select = new Select(webForm.getSelectDropdown());
        Assert.assertEquals(select.getFirstSelectedOption().getText(), value);
    }

    @And("Upload a File: {string}")
    public void uploadAFile(String file) {
        webForm.uploadFile(file);
        String filename = file.substring(file.lastIndexOf('/') + 1);
        String value = (String) (js.executeScript("return arguments[0].value", webForm.getFileInput()));
        Assert.assertEquals(value.substring(value.lastIndexOf('\\') + 1), filename);
    }

    @And("{string} checkbox {string}")
    public void checkUncheckCheckbox(String action, String checkboxName) {
        WebElement checkbox;
        Predicate<String> check = (String a) -> a.equals("Check");
        if (checkboxName.equals("Checked"))
            checkbox = webForm.getCheckBoxes().get(0);
        else
            checkbox = webForm.getCheckBoxes().get(1);
        webForm.checkUncheckCheckbox(action, checkbox);

        Assert.assertEquals(checkbox.isSelected(), check.test(action));
    }

    @And("select radio button {string}")
    public void selectRadioButton(String radioButtonName) {

        WebElement radiobutton;
        if (radioButtonName.equals("Checked"))
            radiobutton = webForm.getRadioButtons().get(0);
        else
            radiobutton = webForm.getRadioButtons().get(1);
        webForm.selectDeselectRadioButton(radiobutton);

        Assert.assertTrue(radiobutton.isSelected());
    }

    @And("Select today's date")
    public void selectTodaySDate() {
        LocalDate date = LocalDate.now();
        webForm.selectDate(date);
        //LocalDate actualDate = webForm.verifyActiveDateOnCalendar();
        //Assert.assertEquals(actualDate,date);
    }

    @And("Select date {string} from calendar")
    public void selectDateSepFromCalendar(String date) {
        LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        webForm.selectDateFromCalendar(d);

        String value = (String) (js.executeScript("return arguments[0].value", webForm.getDateInputBox()));
        Assert.assertEquals(value, d.format(DateTimeFormatter.ofPattern("MM/dd/uuuu")));
    }

    @And("Move Example range to {string}")
    public void moveExampleRangeToMax(String value) {
        webForm.moveExampleRange(value);
    }

    @Then("Click on Submit")
    public void clickOnSubmit() {
        webForm.clickSubmit();
    }

    @And("Verify form submitted message is displayed")
    public void verifyFormSubmittedMessageIsDisplayed() {
        Assert.assertEquals(webForm.getFormSubmitMessage().getText(), "Form submitted");
    }

    @And("Pick {string} color")
    public void pickColor(String colorName) {
        Color color = Color.fromString(colorName);
        System.out.println(color.toString());
        js.executeScript("arguments[0].value = arguments[1];", webForm.getColorPicker(), color.asHex());

    }
}