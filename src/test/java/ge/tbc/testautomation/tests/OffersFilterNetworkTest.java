package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.NavigationSteps;
import ge.tbc.testautomation.steps.OffersSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OffersFilterNetworkTest extends BaseTest {

    private OffersSteps offersSteps;
    private NavigationSteps navigationSteps;

    @BeforeClass
    public void innerSetUp() {
        offersSteps = new OffersSteps(page);
        navigationSteps = new NavigationSteps(page);
    }

    @Test(
            priority = 1,
            description = "TBC-XXXX | Step 1 - შეთავაზებების გვერდზე გადასვლა"
    )
    public void navigateToOffersPage() {
        navigationSteps.navigateToForMe()
                .navigateToOffers();

        offersSteps.navigateToAllOffers();
    }

    @Test(
            priority = 2,
            description = "TBC-XXXX | Step 2 - ფილტრის არჩევა"
    )
    public void selectShoppingFilter() {
        offersSteps.selectShoppingFilterAndCaptureResponse();
    }

    @Test(
            priority = 3,
            description = "TBC-XXXX | Step 3 - Network Request-ის endpoint და HTTP method-ის შემოწმება"
    )
    public void validateRequestEndpointAndMethod() {
        offersSteps.verifyRequestEndpoint()
                .verifyRequestMethod();
    }

    @Test(
            priority = 4,
            description = "TBC-XXXX | Step 4 - მიღებული Response-ის HTTP status code-ს ვალიდაცია"
    )
    public void validateResponseStatusCode() {
        offersSteps.verifyResponseStatusCode();
    }

    @Test(
            priority = 5,
            description = "TBC-XXXX | Step 5 - Request-ის შესაბამისი filter parameter-ის ვალიდაცია"
    )
    public void validateShoppingFilterParameter() {
        offersSteps.verifyShoppingFilterParameter();
    }

    @Test(
            priority = 6,
            description = "TBC-XXXX | Step 6 - შეთავაზებების სიის UI-ზე ვალიდაცია"
    )
    public void validateDisplayedOffers() {
        offersSteps.verifyDisplayedOffersAgainstResponse();
    }
}