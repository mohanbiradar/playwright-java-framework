package framework.utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import framework.config.ConfigManager;
import framework.config.FrameworkConfig;

import java.util.function.Supplier;

public class WaitUtil {

    private static final FrameworkConfig config = ConfigManager.getConfig();
    private static final int DEFAULT_TIMEOUT = config.defaultTimeout();

    private WaitUtil() {
        throw new IllegalStateException("Wait utility class");
    }

    public static void waitForSelector(Page page, String selector, WaitForSelectorState state) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(state)
                .setTimeout(DEFAULT_TIMEOUT));
    }

    public static void waitForLocatorVisible(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(DEFAULT_TIMEOUT));
    }

    public static void waitForLocatorHidden(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(DEFAULT_TIMEOUT));
    }

    public static void waitForURL(Page page, String urlPattern) {
        page.waitForURL(urlPattern, new Page.WaitForURLOptions()
                .setTimeout(DEFAULT_TIMEOUT));
    }

    public static void waitForLoad(Page page, LoadState state) {
        page.waitForLoadState(state, new Page.WaitForLoadStateOptions()
                .setTimeout(DEFAULT_TIMEOUT));
    }

    public static void waitForTextInLocator(Locator locator, String expectedText) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < DEFAULT_TIMEOUT) {
            try {
                if (locator.textContent().trim().contains(expectedText)) {
                    return;
                }
            } catch (Exception ignored) {}
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("Timeout waiting for text: " + expectedText);
    }

    public static void waitUntil(Supplier<Boolean> condition, String failureMessage) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < DEFAULT_TIMEOUT) {
            try {
                if (condition.get()) {
                    return;
                }
            } catch (Exception ignored) {}
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("Timeout waiting for condition: " + failureMessage);
    }
}