package framework.managers;

import com.microsoft.playwright.Page;
import framework.pages.LoginPage;

import java.util.Objects;

public class PageManager {
    private final Page page;
    private LoginPage loginPage;

    public PageManager(Page page) {
        this.page = page;
    }

    public LoginPage loginPage() {
        return Objects.requireNonNullElseGet(loginPage, () -> loginPage = new LoginPage(page));
    }
}

