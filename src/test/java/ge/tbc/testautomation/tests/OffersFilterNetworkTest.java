package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.NavigationSteps;
import ge.tbc.testautomation.steps.OffersSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OffersFilterNetworkTest extends BaseTest {

    OffersSteps offersSteps;
    NavigationSteps navigationSteps;

    @BeforeClass
    public void innerSetUp() {
        offersSteps = new OffersSteps(page);
        navigationSteps = new NavigationSteps(page);
    }

    @Test(priority = 1, description = "KAN-T48 შეთავაზებების გვერდზე გადასვლა")
    public void navigateToOffersPage() {
        navigationSteps
                .navigateToForMe()
                .navigateToOffers();

        offersSteps.navigateToAllOffers();
    }

    @Test(priority = 2, description = "KAN-T48 ფილტრის არჩევა")
    public void selectShoppingFilter() {
        offersSteps.selectShoppingFilterAndCaptureResponse();
    }

    @Test(priority = 3, description = "KAN-T48 Network Request-ის endpoint და HTTP method-ის შემოწმება")
    public void validateRequestEndpointAndMethod() {
        offersSteps
                .verifyRequestEndpoint()
                .verifyRequestMethod();
    }

    @Test(priority = 4, description = "KAN-T48 მიღებული Response-ის HTTP status code-ს ვალიდაცია")
    public void validateResponseStatusCode() {
        offersSteps.verifyResponseStatusCode();
    }

    @Test(priority = 5, description = "KAN-T48 Request-ის შესაბამისი filter parameter-ის ვალიდაცია")
    public void validateShoppingFilterParameter() {
        offersSteps.verifyShoppingFilterParameter();
    }

    @Test(priority = 6, description = "KAN-T48 შეთავაზებების სიის UI-ზე ვალიდაცია")
    public void validateDisplayedOffers() {
        offersSteps.verifyDisplayedOffersAgainstResponse();
    }
}