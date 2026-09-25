package ge.tbc.testautomation.steps;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.LoansPage;

public class LoanSteps {
    private final Page page;
    private final BrowserContext context;
    private final LoansPage loansPage;
    private Page loanCalculatorTab;

    public LoanSteps(Page page) {
        this.page = page;
        this.context = page.context();
        this.loansPage = new LoansPage(page);
    }

    public LoanSteps clickLoanRequest() {
        loanCalculatorTab = context.waitForPage(() -> loansPage.loanRequestButton().click());
        loanCalculatorTab.waitForLoadState();
        return this;
    }

    public Page getLoanCalculatorTab() {
        return loanCalculatorTab;
    }
}