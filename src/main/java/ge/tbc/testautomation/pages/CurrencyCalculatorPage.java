package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CurrencyCalculatorPage {

    public final Locator currencyDropdowns;
    public final Locator currenciesList;
    public final Locator sellAmount;
    public final Locator buyAmount;
    public final Locator exchangeRateElement;

    public CurrencyCalculatorPage(Page page) {
        currencyDropdowns =
                page.locator("button.currency-dropdown__trigger");

        currenciesList =
                page.locator("li.currency-dropdown__item");

        sellAmount =
                page.locator("#sell-amount");

        buyAmount =
                page.locator("#buy-amount");

        exchangeRateElement =
                page.locator("p.exchange-rates-calculator__description");
    }
}