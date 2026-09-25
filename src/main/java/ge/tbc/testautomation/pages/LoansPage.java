package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static ge.tbc.testautomation.data.Constants.LOAN_REQUEST_BUTTON_TEXT;

public class LoansPage {
    private final Page page;

    public LoansPage(Page page) {
        this.page = page;
    }

    public Locator loanRequestButton() {
        return page.locator("tbcx-pw-cta-banner-section")
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName(LOAN_REQUEST_BUTTON_TEXT));
    }
}