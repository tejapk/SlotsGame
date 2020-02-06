package util;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {

    public static boolean isClickable(WebDriver driver, WebElement webe) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(webe));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
