package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class OffersPage {

    private final Page page;

    public OffersPage(Page page) {
        this.page = page;
    }

    public Locator allOffersLink() {
        return page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions().setName("ყველა შეთავაზება")
        );
    }

    public Locator categoryFilter() {
        return page.getByText(
                "კატეგორია",
                new Page.GetByTextOptions().setExact(true)
        );
    }

    public Locator shoppingFilter() {
        return page.getByText(
                "შოპინგი",
                new Page.GetByTextOptions().setExact(true)
        );
    }

    public Locator offerCards() {
        return page.locator(
                ".marketing__cards-list .tbcx-pw-card.tbcx-pw-card--advanced"
        );
    }

    public Locator offerTitles() {
        return page.locator(
                ".marketing__cards-list .tbcx-pw-card__title"
        );
    }
}