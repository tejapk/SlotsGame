package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pageobjects.SlotsGamePage;
import util.DriverUtils;

public class StepDefinition {

    private WebDriver driver;
    private SlotsGamePage slotsGamePage;

    public StepDefinition() {
        driver = Hooks.getDriver();
        slotsGamePage = new SlotsGamePage(driver);
    }

    @Given("^Player opens the gamepage$")
    public void player_opens_the_gamepage() throws Throwable {
        driver.get(Hooks.getUrl());
        System.out.println(slotsGamePage.getStatusDiv().getText());
    }

    @Then("^Player should see welcome message$")
    public void player_should_see_gamepage() throws Throwable {

        if (slotsGamePage.getStatusDiv().getText().equalsIgnoreCase("welcome")) {
            System.out.println("GamePage Launched Successfully");
        } else {
            System.out.println("GamePage Launch Failed");
        }

    }

    @Then("^Start button should be clickable$")
    public void start_button_should_be_clickable() throws Throwable {

        if (DriverUtils.isClickable(driver, slotsGamePage.getStartDiv())) {
            System.out.println("Start button is clickable");
        } else {
            System.out.println("Start button is not clickable");
        }
    }

    @When("^Player hits on the play button$")
    public void player_hits_on_the_play_button() throws Throwable {
        slotsGamePage.getStartDiv().click();
    }

    @Then("^Correct status should be displayed$")
    public void correct_status_shouldbe_displayed() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(slotsGamePage.getStatusDiv().getText());

        String a = slotsGamePage.getResultSubdiv().get(0).getAttribute("style");
        String b = slotsGamePage.getResultSubdiv().get(1).getAttribute("style");
        String c = slotsGamePage.getResultSubdiv().get(2).getAttribute("style");

        if (a.equals(b) && b.equals(c)) {
            if (slotsGamePage.getStatusDiv().getText().equalsIgnoreCase("Big win, congratulatoin.")) {
                System.out.println("Case Successful");
            } else {
                System.out.println("Case Failed");
            }
        } else if (a.equals(b) || b.equals(c)) {
            if (slotsGamePage.getStatusDiv().getText().equalsIgnoreCase("Small win, try again to win more.")) {
                System.out.println("Case Successful");
            } else {
                System.out.println("Case Failed");
            }
        } else {
            if (slotsGamePage.getStatusDiv().getText().equalsIgnoreCase("No Win, try again.")) {
                System.out.println("Case Successful");
            } else {
                System.out.println("Case Failed");
            }
        }

    }

}
