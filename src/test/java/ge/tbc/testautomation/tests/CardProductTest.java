package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.CardSteps;
import ge.tbc.testautomation.steps.NavigationSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.LOYALTY_CREDIT_CARD_NAME;

public class CardProductTest extends BaseTest {

    private NavigationSteps navigationSteps;
    private CardSteps cardSteps;

    @BeforeClass
    public void innerSetUp() {
        navigationSteps = new NavigationSteps(page);
        cardSteps = new CardSteps(page);
    }

    @Test(priority = 1, description = "KAN-TXX | ბართების გვერდზე გადასვლა")
    public void navigateToCardsPage() {
        navigationSteps.navigateToForMe().navigateToCards();
    }

    @Test(priority = 2, description = "KAN-TXX | საბარათე პროდუქტის არჩევა")
    public void selectCardProduct() {
        cardSteps.selectCard(LOYALTY_CREDIT_CARD_NAME);
    }

    @Test(priority = 3, description = "KAN-TXX | პროდუქტის დასახელების და ძირითადი ინფორმაციის ვალიდაცია")
    public void verifyCardProductInfo() {
        cardSteps.verifyProductName(LOYALTY_CREDIT_CARD_NAME)
                .verifyMainInfoDisplayed("ეფექტური საპროცენტო განაკვეთი საშეღავათო პერიოდით 0%-დან");
    }
}