package ge.tbc.testautomation.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CookieBannerComponent {
    public Locator acceptBtn;

    public CookieBannerComponent(Page page) {
        acceptBtn = page.locator(".cookie-side-window")
                .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("თანხმობა"));
    }

    public void acceptIfPresent() {
        if (acceptBtn.isVisible()) {
            acceptBtn.click();
        }
    }
}