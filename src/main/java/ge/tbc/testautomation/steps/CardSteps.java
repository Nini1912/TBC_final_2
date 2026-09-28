package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.CardPage;
import ge.tbc.testautomation.pages.CardsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CardSteps {
    CardsPage cardsPage;
    CardPage cardPage;

    public CardSteps(Page page) {
        this.cardsPage = new CardsPage(page);
        this.cardPage = new CardPage(page);
    }

    public CardSteps selectCard(String cardName) {
        cardsPage.cardProductLink(cardName).click();
        return this;
    }

    public CardSteps verifyProductName(String expectedName) {
        assertThat(cardPage.pageTitle).containsText(expectedName);
        return this;
    }

    public CardSteps verifyMainInfoDisplayed(String expectedSnippet) {
        assertThat(cardPage.mainInfoSection).containsText(expectedSnippet);
        return this;
    }
}