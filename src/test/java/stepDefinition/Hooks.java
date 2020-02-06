package stepDefinition;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.HashMap;
import java.util.Map;

public class Hooks {

    private static WebDriver driver;
    private static String browser;
    private static String url;
    private static boolean init = true;

    public static WebDriver getDriver() {
        return driver;
    }

    public static String getBrowser() {
        return browser;
    }

    public static String getUrl() {
        return url;
    }

    public void initializeDriver() {
        System.out.println("bhanu before class =========");
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.iedriver().setup();

        UrlValidator urlValidator = new UrlValidator();
        url = System.getProperty("url");
        System.out.println("before cehck url ===> " + url);
        if (!urlValidator.isValid(url)) {
            url = "http://127.0.0.1:8000";
        }
        System.out.println("Game url ====> " + url);

        browser = System.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        System.out.println("Testing with Browser =====>" + browser);


        Map<String, String> mobileEmulation = new HashMap<>();
        ChromeOptions chromeOptions = new ChromeOptions();

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "ie":
                driver = new InternetExplorerDriver();
                break;

            case "chrome-android-mobile":
                mobileEmulation.put("deviceName", "Pixel 2");
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                driver = new ChromeDriver(chromeOptions);
                break;

            case "chrome-ios-mobile":
                mobileEmulation.put("deviceName", "iPhone X");
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                driver = new ChromeDriver(chromeOptions);
                break;

            default:
                driver = new ChromeDriver();
                break;
        }
    }

    @Before
    public void beforeScenario() {
        if (init) {
            init = false;
            initializeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    driver.close();
                }
            });
        }
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }

    }
}