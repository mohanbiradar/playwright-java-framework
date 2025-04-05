package framework.core;

import com.microsoft.playwright.*;
import framework.config.ConfigManager;
import framework.config.FrameworkConfig;

public class DriverFactory {

    private DriverFactory() {
        throw new IllegalStateException("Driver factory utility class");
    }

    private static final ThreadLocal<Browser> BROWSER = new ThreadLocal<>();
    private static final ThreadLocal<Page> PAGE = new ThreadLocal<>();
    private static final ThreadLocal<Playwright> PLAYWRIGHT = new ThreadLocal<>();

    public static void initBrowser(String browserName) {
        FrameworkConfig config = ConfigManager.getConfig();
        boolean headless = config.headless();

        Playwright playwright = Playwright.create();
        PLAYWRIGHT.set(playwright);

        BrowserType browserType = switch (browserName.toLowerCase()) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium();
        };

        Browser browser = browserType.launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
        );
        BROWSER.set(browser);

        // ✅ Maximize window by disabling the default viewport
        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions().setViewportSize(null)
        );

        Page page = context.newPage();
        PAGE.set(page);
    }

    public static Page getPage() {
        return PAGE.get();
    }

    public static void closeBrowser() {
        if (PAGE.get() != null) {
            PAGE.get().close();
            PAGE.remove();
        }

        if (BROWSER.get() != null) {
            BROWSER.get().close();
            BROWSER.remove();
        }

        if (PLAYWRIGHT.get() != null) {
            PLAYWRIGHT.get().close();
            PLAYWRIGHT.remove();
        }
    }
}
