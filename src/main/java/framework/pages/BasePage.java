package framework.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import framework.utils.WaitUtil;

public abstract class BasePage {

    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    // Abstract method to enforce load check
    public abstract void isPageLoaded();

    // Wrapper methods for common actions

    public void navigateTo(String url) {
        page.navigate(url);
    }

    public void click(String selector) {
        WaitUtil.waitForSelector(page, selector, com.microsoft.playwright.options.WaitForSelectorState.VISIBLE);
        page.click(selector);
    }

    public void type(String selector, String text) {
        WaitUtil.waitForSelector(page, selector, com.microsoft.playwright.options.WaitForSelectorState.VISIBLE);
        page.fill(selector, text);
    }

    public String getText(String selector) {
        WaitUtil.waitForSelector(page, selector, com.microsoft.playwright.options.WaitForSelectorState.VISIBLE);
        return page.textContent(selector);
    }

    public void waitForURL(String urlPattern) {
        WaitUtil.waitForURL(page, urlPattern);
    }

    public boolean isVisible(String selector) {
        return page.isVisible(selector);
    }

    public boolean isEnabled(String selector) {
        return page.isEnabled(selector);
    }

    public void clearText(String selector) {
        WaitUtil.waitForSelector(page, selector, com.microsoft.playwright.options.WaitForSelectorState.VISIBLE);
        page.fill(selector, "");
    }

    public void pressKey(String selector, String key) {
        page.locator(selector).press(key);
    }

    public Locator getLocator(String selector) {
        return page.locator(selector);
    }

    public void hover(String selector) {
        page.hover(selector);
    }

    public void waitForSelectorVisible(String selector) {
        WaitUtil.waitForSelector(page, selector, com.microsoft.playwright.options.WaitForSelectorState.VISIBLE);
    }

    public void waitForSelectorHidden(String selector) {
        WaitUtil.waitForSelector(page, selector, com.microsoft.playwright.options.WaitForSelectorState.HIDDEN);
    }

    public void waitForLoadState(LoadState state) {
        WaitUtil.waitForLoad(page, state);
    }

    public void waitForTextInLocator(Locator locator, String expectedText) {
        WaitUtil.waitForTextInLocator(locator, expectedText);
    }

    public void waitUntil(java.util.function.Supplier<Boolean> condition, String failureMessage) {
        WaitUtil.waitUntil(condition, failureMessage);
    }
}

