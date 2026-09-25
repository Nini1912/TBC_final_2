package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.ConsumerLoanPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ConsumerLoanSteps {
    private final ConsumerLoanPage consumerLoanPage;

    public ConsumerLoanSteps(Page page) {
        this.consumerLoanPage = new ConsumerLoanPage(page);
    }

    public ConsumerLoanSteps verifyPageTitle(String expectedTitle) {
        assertThat(consumerLoanPage.pageTitle()).containsText(expectedTitle);
        return this;
    }

    public ConsumerLoanSteps verifyMainInfoDisplayed(String expectedSnippet) {
        assertThat(consumerLoanPage.mainInfoSection()).containsText(expectedSnippet);
        return this;
    }
}