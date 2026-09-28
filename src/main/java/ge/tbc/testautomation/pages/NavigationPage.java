package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

import static ge.tbc.testautomation.data.Constants.*;

public class NavigationPage {
    public Locator forMeMenuItem;
    public Locator loansMenuItem;
    public Locator currencyRateMenuItem;
    public Locator breadcrumb;
    public Locator consumerLoanMenuItem;
    public Locator offersMenuItem;
    public Locator cardsMenuItem;

    public NavigationPage(Page page) {
        forMeMenuItem = page.getByRole(AriaRole.BANNER)
                .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions()
                        .setName(Pattern.compile("ჩემთვის|Personal", Pattern.CASE_INSENSITIVE)));
        loansMenuItem = page.locator("tbcx-pw-mega-menu-sub-group")
                .filter(new Locator.FilterOptions().setHasText(LOANS_MENU_TEXT))
                .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(LOANS_MENU_TEXT));
        currencyRateMenuItem = page.locator(".tbcx-pw-mega-menu-quick-acitons-item__title")
                .filter(new Locator.FilterOptions().setHasText(CURRENCY_RATES_TEXT)).first();
        breadcrumb = page.locator(".tbcx-pw-breadcrumbs__items");
        consumerLoanMenuItem = page.locator(".tbcx-pw-mega-menu--open .show-tablet-up a[href*='/loans/consumer-loan']");
        offersMenuItem = page.locator(".tbcx-pw-mega-menu-quick-acitons-item__title")
                .filter(new Locator.FilterOptions().setHasText(OFFERS)).first();
        cardsMenuItem = page.locator("tbcx-pw-mega-menu-sub-group")
                .filter(new Locator.FilterOptions().setHasText(CARDS_MENU_TEXT))
                .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(CARDS_MENU_TEXT));
    }
}