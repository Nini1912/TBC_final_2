package ge.tbc.testautomation.steps;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.LoansPage;

public class LoanSteps {
    Page page;
    BrowserContext context;
    LoansPage loansPage;
    Page loanCalculatorTab;

    public LoanSteps(Page page) {
        this.page = page;
        this.context = page.context();
        this.loansPage = new LoansPage(page);
    }

    public LoanSteps clickLoanRequest() {
        loanCalculatorTab = context.waitForPage(() -> loansPage.loanRequestButton.click());
        loanCalculatorTab.waitForLoadState();

        return this;
    }

    public Page getLoanCalculatorTab() {
        return loanCalculatorTab;
    }
}