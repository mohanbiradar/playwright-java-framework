package tests.scenarios;

import framework.config.ConfigManager;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = pageManager.loginPage();
        loginPage.navigate(ConfigManager.getConfig().baseUrl());
        loginPage.login("Admin", "admin123");
        Assert.assertTrue(loginPage.isLoggedIn());
    }
}