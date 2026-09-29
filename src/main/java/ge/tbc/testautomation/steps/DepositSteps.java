package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.DepositPage;
import ge.tbc.testautomation.pages.DepositsPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ge.tbc.testautomation.data.Constants.*;

public class DepositSteps {

    private final Page page;
    private final DepositsPage depositsPage;
    private final DepositPage depositPage;

    public DepositSteps(Page page) {
        this.page = page;
        this.depositsPage = new DepositsPage(page);
        this.depositPage = new DepositPage(page);
    }

    public DepositSteps verifyDepositsPageOpened() {
        assertThat(page)
                .hasURL(
                        Pattern.compile(
                                ".*" + DEPOSITS_URL_PART + "/?$"
                        )
                );

        return this;
    }

    public DepositSteps selectTermDeposit() {
        depositsPage.termDepositLink.click();
        return this;
    }

    public DepositSteps verifyTermDepositPageOpened() {
        assertThat(page)
                .hasURL(
                        Pattern.compile(
                                ".*" + TERM_DEPOSIT_URL_PART + "/?$"
                        )
                );

        assertThat(depositPage.pageTitle)
                .hasText(TERM_DEPOSIT_NAME);

        return this;
    }

    public DepositSteps verifyDepositConditionsDisplayed() {
        assertThat(depositPage.conditionsTab)
                .isVisible();

        assertThat(depositPage.interestRateItem)
                .isVisible();

        assertThat(depositPage.interestRateItem)
                .containsText(DEPOSIT_INTEREST_RATE_TEXT);

        assertThat(depositPage.interestRateItem)
                .containsText(FIXED_INTEREST_RATE_TEXT);

        return this;
    }
}