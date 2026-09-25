package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.NavigationPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NavigationSteps {
    private final NavigationPage navigationPage;

    public NavigationSteps(Page page) {
        this.navigationPage = new NavigationPage(page);
    }

    public NavigationSteps navigateToForMe() {
        navigationPage.forMeMenuItem().click();
        return this;
    }

    public NavigationSteps navigateToLoans() {
        navigationPage.loansMenuItem().click();
        return this;
    }

    public NavigationSteps navigateToCurrencyRate() {
        navigationPage.currencyRateMenuItem().click();
        return this;
    }

    public NavigationSteps verifyBreadcrumbContains(String expectedText) {
        assertThat(navigationPage.breadcrumb()).containsText(expectedText);
        return this;
    }

    public NavigationSteps navigateToConsumerLoan() {
        navigationPage.consumerLoanMenuItem().click();
        return this;
    }

    public NavigationSteps navigateToOffers() {
        navigationPage.offersMenuItem().click();
        return this;
    }
}