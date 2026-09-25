package ge.tbc.testautomation.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CookieBannerComponent {
    private final Page page;

    public CookieBannerComponent(Page page) {
        this.page = page;
    }

    public Locator acceptButton() {
        return page.locator(".cookie-side-window").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("თანხმობა"));
    }

    public void acceptIfPresent() {
        if (acceptButton().isVisible()) {
            acceptButton().click();
        }
    }
}