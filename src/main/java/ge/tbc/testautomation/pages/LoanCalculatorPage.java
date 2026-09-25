package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoanCalculatorPage {
    private final Page page;

    public LoanCalculatorPage(Page page) {
        this.page = page;
    }

    public Locator standardCalculatorContainer() {
        return page.locator("#standard-calculator");
    }

    public Locator loanAmountInput() {
        return page.locator("#standard-calculator-amount");
    }

    public Locator loanTermInput() {
        return page.locator("#standard-calculator-period");
    }

    public Locator monthlyPaymentValue() {
        return page.locator("#standard-calculator-result-payment");
    }

    public Locator interestRateValue() {
        return page.locator("#standard-calculator-result-rate");
    }

    public Locator resultAmountValue() {
        return page.locator("#standard-calculator-result-amount");
    }

    public Locator resultPeriodValue() {
        return page.locator("#standard-calculator-result-period");
    }
}