package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

import static ge.tbc.testautomation.data.Constants.*;

public class NavigationPage {
    private final Page page;

    public NavigationPage(Page page) {
        this.page = page;
    }

    public Locator forMeMenuItem() {
        return page.getByRole(AriaRole.BANNER)
                .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions()
                        .setName(Pattern.compile("ჩემთვის|Personal", Pattern.CASE_INSENSITIVE)));
    }

    public Locator loansMenuItem() {
        return page.locator("tbcx-pw-mega-menu-sub-group")
                .filter(new Locator.FilterOptions().setHasText(LOANS_MENU_TEXT))
                .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(LOANS_MENU_TEXT));
    }

    public Locator currencyRateMenuItem() {
        return page.locator(".tbcx-pw-mega-menu-quick-acitons-item__title")
                .filter(new Locator.FilterOptions().setHasText(CURRENCY_RATES_TEXT)).first();
    }

    public Locator breadcrumb() {
        return page.locator(".tbcx-pw-breadcrumbs__items");
    }

    public Locator consumerLoanMenuItem() {
        return page.locator(".tbcx-pw-mega-menu--open .show-tablet-up a[href*='/loans/consumer-loan']");
    }

    public Locator offersMenuItem() {
        return page.locator(".tbcx-pw-mega-menu-quick-acitons-item__title")
                .filter(new Locator.FilterOptions().setHasText("შეთავაზებები")).first();
    }
}