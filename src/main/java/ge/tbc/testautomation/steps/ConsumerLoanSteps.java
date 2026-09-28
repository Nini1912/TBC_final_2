package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.ConsumerLoanPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ConsumerLoanSteps {
    Page page;
    ConsumerLoanPage consumerLoanPage;

    public ConsumerLoanSteps(Page page) {
        this.page = page;
        consumerLoanPage = new ConsumerLoanPage(page);
    }

    public ConsumerLoanSteps verifyPageTitle(String expectedTitle) {
        assertThat(consumerLoanPage.pageTitle).containsText(expectedTitle);

        return this;
    }

    public ConsumerLoanSteps verifyMainInfoDisplayed(String expectedSnippet) {
        assertThat(consumerLoanPage.mainInfoSection).containsText(expectedSnippet);

        return this;
    }
}