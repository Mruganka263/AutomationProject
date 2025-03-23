package io.cucumber.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class WebForm extends Page {

    public WebForm(ChromeDriver driver) {
        super(driver);
    }

    @FindBy(id = "my-text-id")
    private WebElement textInput;

    @FindBy(css = "[type='password']")
    private WebElement password;

    @FindBy(tagName = "textarea")
    private WebElement textarea;

    @FindBy(name = "my-disabled")
    private WebElement disabledInput;

    @FindBy(name = "my-select")
    private WebElement selectDropdown;

    @FindBy(name = "my-datalist")
    private WebElement datalistDropdown;

    @FindBy(name = "my-file")
    private WebElement fileInput;

    @FindAll({@FindBy(css = ".form-check [type='checkbox']")})
    private List<WebElement> checkBoxes;

    @FindAll({@FindBy(css = ".form-check [type='radio']")})
    private List<WebElement> radioButtons;

    public WebElement getColorPicker() {
        return colorPicker;
    }

    @FindBy(css = "[type='color']")
    private WebElement colorPicker;

    @FindBy(name = "my-date")
    private WebElement dateInputBox;
    @FindAll({@FindBy(css = ".datepicker-switch")})
    private List<WebElement> datePickerSwitch;
    @FindAll({@FindBy(css = ".year")})
    private List<WebElement> calYearList;
    @FindAll({@FindBy(className = "month")})
    private List<WebElement> calMonthList;
    @FindAll({@FindBy(css = ".day:not(.old):not(.new)")})
    private List<WebElement> calDays;
    @FindAll({@FindBy(css = ".prev")})
    private List<WebElement> prevArrow;
    @FindAll({@FindBy(css = ".next")})
    private List<WebElement> nextArrow;

    @FindBy(css = ".active.day")
    private WebElement activeDay;
    @FindBy(css = ".active.year")
    private WebElement activeYear;
    @FindBy(css = ".datepicker .active.month")
    private WebElement activeMonth;

    @FindBy(name = "my-range")
    private WebElement exampleRange;

    @FindBy(css = "[type='submit']")
    private WebElement submitButton;



    @FindBy(tagName = "h1")
    private WebElement formSubmitMessage;

    public WebElement getSelectDropdown() {
        return selectDropdown;
    }

    public WebElement getFileInput() {
        return fileInput;
    }

    public WebElement getDateInputBox() {
        return dateInputBox;
    }

    public List<WebElement> getCheckBoxes() {
        return checkBoxes;
    }
    public WebElement getFormSubmitMessage() {
        return formSubmitMessage;
    }

    public List<WebElement> getRadioButtons() {
        return radioButtons;
    }


    public void enterTextInInputField(String textboxName, String value) {
        switch (textboxName) {
            case "Text input":
                textInput.sendKeys(value);
                break;
            case "Password":
                password.sendKeys(value);
                break;
            case "Textarea":
                textarea.sendKeys(value);
                break;
            case "Datalist Dropdown":
                datalistDropdown.sendKeys(value);
                break;
            default:
                System.out.println("Incorrect field name");
                break;
        }
    }

    public boolean checkIfDisabled() {
        return disabledInput.isEnabled();
    }

    public void selectFromDropdown(String value) {
        Select dropdown = new Select(selectDropdown);
        dropdown.selectByVisibleText(value);
    }

    public void uploadFile(String file) {
        File file1 = new File(file);
        fileInput.sendKeys(file1.getAbsolutePath());
    }

    public void checkUncheckCheckbox(String action, WebElement checkbox) {
        if (action.equals("Uncheck") && checkbox.isSelected())
            checkbox.click();
        else if (action.equals("Check") && !checkbox.isSelected())
            checkbox.click();
    }

    public void selectDeselectRadioButton(WebElement radioButton) {
        if (!radioButton.isSelected())
            radioButton.click();
    }

    public void selectDate(LocalDate date) {
        dateInputBox.sendKeys(date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }

    public LocalDate verifyActiveDateOnCalendar() {
        dateInputBox.click();

        String day = activeDay.getText();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String year = (String) (js.executeScript("return arguments[0].value", activeYear));
        String mon = (String) (js.executeScript("return arguments[0].value", activeMonth));

        System.out.println(year + mon + day);
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("yyyyMMMdd").toFormatter();
        return LocalDate.parse(year + mon + day, df);
    }

    public void selectDateFromCalendar(LocalDate date) {
        dateInputBox.click();
        String day = String.valueOf(date.getDayOfMonth());
        String mon = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int year = date.getYear();

        if (!datePickerSwitch.get(0).getText().equals(mon + " " + year)) {
            //Clicking on Mont-Year heading
            datePickerSwitch.get(0).click();
            if (!Integer.toString(year).equals(datePickerSwitch.get(1).getText())) {
                //Clicking on year heading to change year
                datePickerSwitch.get(1).click();
                String[] yearRange = datePickerSwitch.get(2).getText().split("-");
                //Navigate to left/right until year is in range
                while (Integer.parseInt(yearRange[0]) > year || Integer.parseInt(yearRange[1]) < year) {
                    if (Integer.parseInt(yearRange[0]) > year)
                        prevArrow.get(2).click();
                    if (Integer.parseInt(yearRange[1]) < year)
                        nextArrow.get(2).click();
                    yearRange = datePickerSwitch.get(2).getText().split("-");
                }
                for (WebElement y : calYearList)
                    if (y.getText().equals(Integer.toString(year))) {
                        y.click();
                        break;
                    }
            }
            //Loop to check and select month
            for (WebElement m : calMonthList)
                if (m.getText().equals(mon.substring(0, 3))) {
                    m.click();
                    break;
                }
            for (WebElement d : calDays) {

                if (d.getText().equals(day)) {
                    d.click();
                    break;
                }
            }
            textInput.click();
        }
    }

    public void moveExampleRange(String value) {
        int width = exampleRange.getSize().getWidth();
        Actions move = new Actions(driver);
        System.out.println(width);
        if (value.equals("MAX")) {
            move.dragAndDropBy(exampleRange, width / 2, 0).perform();
        } else if (value.equals("MIN")) {
            move.dragAndDropBy(exampleRange, -width / 2, 0).perform();
        }
    }

    public void clickSubmit() {
        submitButton.click();
    }
}
