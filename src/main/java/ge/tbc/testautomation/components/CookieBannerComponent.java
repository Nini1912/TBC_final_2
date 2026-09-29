package ge.tbc.testautomation.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CookieBannerComponent {
    public Locator acceptBtn;
    private Locator mainSiteAcceptBtn;

    public CookieBannerComponent(Page page) {
        acceptBtn = page.locator(".cookie-side-window")
                .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("თანხმობა"));
        mainSiteAcceptBtn = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("თანხმობა").setExact(true)).first();

    }

    public void acceptIfPresent() {
        if (acceptBtn.isVisible()) {
            acceptBtn.click();
        }
    }

    public void acceptMainSiteIfPresent() {
        if (mainSiteAcceptBtn.isVisible()) {
            mainSiteAcceptBtn.click();
        }
    }
}