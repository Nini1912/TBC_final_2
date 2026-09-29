package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.components.CookieBannerComponent;
import ge.tbc.testautomation.pages.LoanCalculatorPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoanCalculatorSteps {

    Page page;
    LoanCalculatorPage loanCalculatorPage;
    CookieBannerComponent cookieBanner;

    public LoanCalculatorSteps(Page loanCalculatorTab) {
        this.page = loanCalculatorTab;
        this.loanCalculatorPage =
                new LoanCalculatorPage(loanCalculatorTab);
        this.cookieBanner =
                new CookieBannerComponent(loanCalculatorTab);
    }

    public LoanCalculatorSteps acceptCookiesIfPresent() {
        cookieBanner.acceptIfPresent();

        return this;
    }

    public LoanCalculatorSteps verifyLoanAmountTabActive() {
        com.microsoft.playwright.assertions.PlaywrightAssertions
                .assertThat(
                        loanCalculatorPage.standardCalculatorContainer
                )
                .isVisible();

        return this;
    }

    public LoanCalculatorSteps enterLoanAmount(double amount) {
        loanCalculatorPage.loanAmountInput.fill(
                String.valueOf((long) amount)
        );

        loanCalculatorPage.loanAmountInput.blur();

        return this;
    }

    public LoanCalculatorSteps selectLoanTerm(int months) {
        loanCalculatorPage.loanTermInput.fill(
                String.valueOf(months)
        );

        loanCalculatorPage.loanTermInput.blur();

        return this;
    }

    public double calculateExpectedMonthlyPayment(
            double amount,
            int termMonths,
            double annualRatePercent
    ) {
        double monthlyRate =
                annualRatePercent / 100 / 12;

        if (monthlyRate == 0) {
            return amount / termMonths;
        }

        double factor =
                Math.pow(
                        1 + monthlyRate,
                        termMonths
                );

        return amount
                * monthlyRate
                * factor
                / (factor - 1);
    }

    public LoanCalculatorSteps verifyMonthlyPayment(
            double expected,
            String errorMessage
    ) {
        long actualRounded =
                Math.round(
                        getDisplayedMonthlyPayment()
                );

        long expectedRounded =
                Math.round(expected);

        assertThat(
                errorMessage,
                actualRounded,
                is(expectedRounded)
        );

        return this;
    }

    private double parseNumeric(String raw) {
        String cleaned =
                raw.replaceAll(
                                "[^0-9.,]",
                                ""
                        )
                        .replace(",", "");

        return Double.parseDouble(cleaned);
    }

    public double getActualLoanAmount() {
        return parseNumeric(
                loanCalculatorPage
                        .resultAmountValue
                        .textContent()
        );
    }

    public int getActualLoanTerm() {
        return (int) parseNumeric(
                loanCalculatorPage
                        .resultPeriodValue
                        .textContent()
        );
    }

    public double getInterestRate() {
        return parseNumeric(
                loanCalculatorPage
                        .interestRateValue
                        .textContent()
        );
    }

    public double getDisplayedMonthlyPayment() {
        return parseNumeric(
                loanCalculatorPage
                        .monthlyPaymentValue
                        .textContent()
        );
    }
}