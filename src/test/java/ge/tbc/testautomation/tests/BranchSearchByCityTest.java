package ge.tbc.testautomation.tests;

import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class BranchSearchByCityTest extends AddressesBaseTest {

    @Test(priority = 1, description = "მისამართების გვერდზე გადასვლა")
    public void navigateToAddressesPage() {
        navigation.navigateToForMe()
                .navigateToAddresses()
                .verifyBreadcrumbContains(ADDRESSES_TEXT);
    }

    @Test(priority = 2, description = "'ფილიალები' ტაბის არჩევა")
    public void selectBranchesTab() {
        addressesSteps.selectBranchesTab()
                .verifyBranchesTabActive();
    }

    @Test(priority = 3, description = "ქალაქის არჩევა")
    public void selectCity() {
        addressesSteps.selectCity(VALID_CITY)
                .verifyCitySelected(VALID_CITY);
    }

    @Test(priority = 4, description = "ლოკაციის არჩევა")
    public void selectLocation() {
        addressesSteps.searchLocation(VALID_LOCATION)
                .verifyAllResultsContainLocation(VALID_LOCATION);
    }
}