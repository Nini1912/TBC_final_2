package ge.tbc.testautomation.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ge.tbc.testautomation.data.Constants.*;

public class NavigationComponent {

    private final Locator forMeMenuItem;
    private final Locator loansMenuItem;
    private final Locator cardsMenuItem;
    private final Locator currencyRateMenuItem;
    private final Locator consumerLoanMenuItem;
    private final Locator offersMenuItem;
    private final Locator breadcrumb;
    private final Locator addressesMenuItem;

    public NavigationComponent(Page page) {
        forMeMenuItem = page.getByRole(AriaRole.BANNER)
                .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions()
                        .setName(Pattern.compile("ჩემთვის|Personal", Pattern.CASE_INSENSITIVE)));

        loansMenuItem = subGroupLink(page, LOANS_MENU_TEXT);
        cardsMenuItem = subGroupLink(page, CARDS_MENU_TEXT);

        currencyRateMenuItem = quickAction(page, CURRENCY_RATES_TEXT);
        offersMenuItem = quickAction(page, OFFERS);

        consumerLoanMenuItem = page.locator(
                ".tbcx-pw-mega-menu--open .show-tablet-up a[href*='/loans/consumer-loan']");

        breadcrumb = page.locator(".tbcx-pw-breadcrumbs__items");

        addressesMenuItem = page.locator(".tbcx-pw-mega-menu--open")
                .getByText(ADDRESSES_TEXT, new Locator.GetByTextOptions().setExact(true))
                .first();
    }

    private Locator subGroupLink(Page page, String text) {
        return page.locator("tbcx-pw-mega-menu-sub-group")
                .filter(new Locator.FilterOptions().setHasText(text))
                .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(text));
    }

    private Locator quickAction(Page page, String text) {
        return page.locator(".tbcx-pw-mega-menu-quick-acitons-item__title")
                .filter(new Locator.FilterOptions().setHasText(text))
                .first();
    }

    public NavigationComponent navigateToForMe() {
        forMeMenuItem.click();
        return this;
    }

    public NavigationComponent navigateToLoans() {
        loansMenuItem.click();
        return this;
    }

    public NavigationComponent navigateToCards() {
        cardsMenuItem.click();
        return this;
    }

    public NavigationComponent navigateToCurrencyRate() {
        currencyRateMenuItem.click();
        return this;
    }

    public NavigationComponent navigateToConsumerLoan() {
        consumerLoanMenuItem.click();
        return this;
    }

    public NavigationComponent navigateToOffers() {
        offersMenuItem.click();
        return this;
    }

    public NavigationComponent verifyBreadcrumbContains(String expectedText) {
        assertThat(breadcrumb).containsText(expectedText);
        return this;
    }

    public NavigationComponent navigateToAddresses() {
        addressesMenuItem.click();
        return this;
    }
}