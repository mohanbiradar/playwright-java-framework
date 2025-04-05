package framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import framework.config.ConfigManager;
import framework.utils.ExtentReportUtil;

public class LoginPage extends BasePage{

    private final String usernameSelector = "[name='username']";
    private final String passwordSelector = "[name='password']";
    private final String loginButtonSelector = "[type='submit']";
    private final String loginPageHeaderSelector = "text=Login";
    private final String dashboardSelector = "text=Dashboard";

    public LoginPage(Page page) {
        super(page);
    }

    @Override
    public void isPageLoaded() {
        waitForSelectorVisible(loginPageHeaderSelector);
    }

    public void login(String username, String password) {
        isPageLoaded();
        type(usernameSelector, username);
        type(passwordSelector, password);
        ExtentReportUtil.attachScreenshot(page, "login page");
        click(loginButtonSelector);
    }

    public boolean isLoggedIn() {
        waitForSelectorVisible(dashboardSelector);
        ExtentReportUtil.attachScreenshot(page, "logged in page");
        return isVisible(dashboardSelector);
    }

    public void navigate(String url) {
        navigateTo(url);
    }
}