package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoanCalculatorPage{
    public Locator standardCalculatorContainer;
    public Locator loanAmountInput;
    public Locator loanTermInput;
    public Locator monthlyPaymentValue;
    public Locator interestRateValue;
    public Locator resultAmountValue;
    public Locator resultPeriodValue;

    public LoanCalculatorPage(Page page) {
        standardCalculatorContainer = page.locator("#standard-calculator");
        loanAmountInput = page.locator("#standard-calculator-amount");
        loanTermInput = page.locator("#standard-calculator-period");
        monthlyPaymentValue = page.locator("#standard-calculator-result-payment");
        interestRateValue = page.locator("#standard-calculator-result-rate");
        resultAmountValue = page.locator("#standard-calculator-result-amount");
        resultPeriodValue = page.locator("#standard-calculator-result-period");
    }
}