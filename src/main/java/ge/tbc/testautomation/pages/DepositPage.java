package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static ge.tbc.testautomation.data.Constants.*;

public class DepositPage {

    public final Locator pageTitle;
    public final Locator conditionsTab;
    public final Locator interestRateItem;

    public DepositPage(Page page) {

        pageTitle = page.locator("h1.app-rich-text-heading-1");

        conditionsTab = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName(DEPOSIT_CONDITIONS_TEXT)
                        .setExact(true)
        );

        interestRateItem = page.locator(
                ".tbcx-pw-details-list__item"
        ).filter(
                new Locator.FilterOptions()
                        .setHasText(DEPOSIT_INTEREST_RATE_TEXT + " " + FIXED_INTEREST_RATE_TEXT)
        );
    }
}