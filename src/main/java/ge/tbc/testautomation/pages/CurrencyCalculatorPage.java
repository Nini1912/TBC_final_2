package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CurrencyCalculatorPage {

    private final Page page;

    public CurrencyCalculatorPage(Page page) {
        this.page = page;
    }

    public Locator sellCurrencyDropdown() {
        return page.locator("app-currency-dropdown[formcontrolname='sellCurrency'] button.currency-dropdown__trigger");
    }

    public Locator buyCurrencyDropdown() {
        return page.locator("app-currency-dropdown[formcontrolname='buyCurrency'] button.currency-dropdown__trigger");
    }

    public Locator currencyOption(String currencyCode) {
        return page.locator(".cdk-overlay-pane")
                .getByText(currencyCode, new Locator.GetByTextOptions().setExact(true));
    }

    public Locator sellAmountInput() {
        return page.locator("#sell-amount");
    }

    public Locator buyAmountInput() {
        return page.locator("#buy-amount");
    }

    public Locator exchangeRateDescription() {
        return page.locator(".exchange-rates-calculator__description");
    }
}