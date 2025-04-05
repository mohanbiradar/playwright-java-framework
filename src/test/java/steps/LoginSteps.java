package steps;

import framework.config.ConfigManager;
import framework.pages.LoginPage;
import framework.core.DriverFactory;
import framework.managers.PageManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {
    private final PageManager pageManager = new PageManager(DriverFactory.getPage());
    private final LoginPage loginPage = pageManager.loginPage();

    @Given("user is on login page")
    public void user_is_on_login_page() {
        loginPage.navigate(ConfigManager.getConfig().baseUrl());
    }

    @When("user enters valid username and password")
    public void user_enters_credentials() {
        loginPage.login("user1", "password1");
    }

    @Then("user should be logged in successfully")
    public void verify_login() {
        Assert.assertTrue(loginPage.isLoggedIn());
    }
}