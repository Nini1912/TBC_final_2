package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static ge.tbc.testautomation.data.Constants.TERM_DEPOSIT_URL_PART;

public class DepositsPage {
    public Locator termDepositLink;

    public DepositsPage(Page page) {
        termDepositLink = page.locator(
                "a[href='" + TERM_DEPOSIT_URL_PART + "']:visible"
        ).first();
    }
}