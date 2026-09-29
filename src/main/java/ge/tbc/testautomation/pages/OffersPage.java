package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static ge.tbc.testautomation.data.Constants.*;

public class OffersPage {

    public final Locator allOffersLink;
    public final Locator categoryFilter;
    public final Locator shoppingFilterItem;
    public final Locator shoppingCheckbox;
    public final Locator offerCards;
    public final Locator offerTitles;

    public OffersPage(Page page) {

        allOffersLink = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName(ALL_OFFERS_TEXT)
        );

        categoryFilter = page.getByText(
                CATEGORY_TEXT,
                new Page.GetByTextOptions()
                        .setExact(true)
        );

        shoppingFilterItem = page.locator("app-marketing-filter-item")
                .filter(
                        new Locator.FilterOptions()
                                .setHasText(SHOPPING_TEXT)
                );

        shoppingCheckbox = shoppingFilterItem.locator(
                "input[type='checkbox']"
        );

        offerCards = page.locator(
                ".marketing__cards-list "
                        + ".tbcx-pw-card.tbcx-pw-card--advanced"
        );

        offerTitles = page.locator(
                ".marketing__cards-list "
                        + ".tbcx-pw-card__title"
        );
    }
}