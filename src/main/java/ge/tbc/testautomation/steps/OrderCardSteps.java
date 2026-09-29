package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.TbcCardPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class OrderCardSteps {

    private final TbcCardPage tbcCardPage;

    public OrderCardSteps(Page page) {
        this.tbcCardPage = new TbcCardPage(page);
    }

    public OrderCardSteps startOrder() {
        tbcCardPage.getCardButton.click();
        return this;
    }

    public OrderCardSteps verifyOrderStarted() {
        assertThat(tbcCardPage.qrPopup).isVisible();
        return this;
    }
}