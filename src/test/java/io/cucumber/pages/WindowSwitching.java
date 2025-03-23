package io.cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class WindowSwitching extends Page {
    public WindowSwitching(ChromeDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Open new window")
    private WebElement link;

    public WebElement getLink() {
        return link;
    }

    public WebElement getFrame() {
        return frame;
    }

    public WebElement getTextMessage() {
        return textMessage;
    }

    @FindBy(name = "myframe")
    private WebElement frame;

    @FindBy(tagName = "div")
    private WebElement textMessage;


}
