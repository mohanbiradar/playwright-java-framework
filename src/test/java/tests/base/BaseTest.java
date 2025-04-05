package tests.base;

import com.microsoft.playwright.Page;
import framework.config.ConfigManager;
import framework.core.DriverFactory;
import framework.managers.PageManager;
import framework.utils.ExtentReportUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(framework.utils.TestListener.class)
public class BaseTest {
    protected Page page;
    protected PageManager pageManager;

    @BeforeMethod
    public void setup() {
        String browser = ConfigManager.getConfig().browser();
        DriverFactory.initBrowser(browser);
        page = DriverFactory.getPage();

        pageManager = new PageManager(page);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.closeBrowser();
        // Clean up test logging
        ExtentReportUtil.removeTest();
    }
}
