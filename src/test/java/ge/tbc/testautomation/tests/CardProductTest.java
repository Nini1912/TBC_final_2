package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.CardSteps;
import ge.tbc.testautomation.steps.NavigationSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class CardProductTest extends BaseTest {

    NavigationSteps navigationSteps;
    CardSteps cardSteps;

    @BeforeClass
    public void innerSetUp() {
        navigationSteps = new NavigationSteps(page);
        cardSteps = new CardSteps(page);
    }

    @Test(priority = 1, description = "KAN-T42 | ბართების გვერდზე გადასვლა")
    public void navigateToCardsPage() {
        navigationSteps.navigateToForMe().navigateToCards();
    }

    @Test(priority = 2, description = "KAN-T42 | საბარათე პროდუქტის არჩევა")
    public void selectCardProduct() {
        cardSteps.selectCard(LOYALTY_CREDIT_CARD_NAME);
    }

    @Test(priority = 3, description = "KAN-T42 | პროდუქტის დასახელების და ძირითადი ინფორმაციის ვალიდაცია")
    public void verifyCardProductInfo() {
        cardSteps
                .verifyProductName(LOYALTY_CREDIT_CARD_NAME)
                .verifyMainInfoDisplayed(LOYALTY_CREDIT_CARD_MAIN_INFO);
    }
}