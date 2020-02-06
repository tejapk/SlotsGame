package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SlotsGamePage {

    WebDriver driver;

    public SlotsGamePage(WebDriver driver) {
        this.driver = driver;
    }

    By StatusDiv = By.id("status");
    By ResultDiv = By.id("result");
    By StartDiv = By.id("start");

    public WebElement getStatusDiv() {
        return driver.findElement(StatusDiv);
    }

    public WebElement getResultDiv() {
        return driver.findElement(ResultDiv);
    }

    public WebElement getStartDiv() {
        return driver.findElement(StartDiv);
    }

    public List<WebElement> getResultSubdiv() {
        return driver.findElement(ResultDiv).findElements(By.tagName("div"));
    }

}
