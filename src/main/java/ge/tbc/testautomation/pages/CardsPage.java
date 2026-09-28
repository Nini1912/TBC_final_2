package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CardsPage {
    private final Page page;

    public CardsPage(Page page) {
        this.page = page;
    }

    public Locator cardProductLink(String cardName) {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(cardName));
    }
}