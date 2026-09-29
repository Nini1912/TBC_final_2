package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.AddressesDataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.List;

import static ge.tbc.testautomation.data.Constants.ADDRESSES_TEXT;

public class AddressesFilterTest extends AddressesBaseTest {

    private final List<String> filters;

    @Factory(dataProvider = "filterCombinations", dataProviderClass = AddressesDataProvider.class)
    public AddressesFilterTest(List<String> filters) {
        this.filters = filters;
    }

    @Test(priority = 1, description = "მისამართების გვერდზე გადასვლა")
    public void navigateToAddressesPage() {
        navigation.navigateToForMe()
                .navigateToAddresses()
                .verifyBreadcrumbContains(ADDRESSES_TEXT);
    }

    @Test(priority = 2, description = "ფილტრის არჩევა და შედეგების ვალიდაცია")
    public void applyFiltersAndVerifyResults() {
        filters.forEach(addressesSteps::applyFilterAndVerify);
    }
}