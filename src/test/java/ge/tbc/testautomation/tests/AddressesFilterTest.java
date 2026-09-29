package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.AddressesDataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.List;

import static ge.tbc.testautomation.data.Constants.*;

public class AddressesFilterTest extends AddressesBaseTest {

    private final List<String> filters;

    @Factory(
            dataProvider = "filterCombinations",
            dataProviderClass = AddressesDataProvider.class
    )
    public AddressesFilterTest(List<String> filters) {
        this.filters = filters;
    }

    @Test(description = "მისამართების ფილტრების შემოწმება")
    public void applyFiltersAndVerifyResults() {

        navigation.navigateToForMe()
                .navigateToAddresses()
                .verifyBreadcrumbContains(ADDRESSES_TEXT);

        if (filters.contains(FILTER_247)) {
            addressesSteps.select247Filter();
        }

        if (filters.contains(FILTER_OPEN)) {
            addressesSteps.selectOpenFilter();
        }

        addressesSteps.waitForResults();

        if (filters.contains(FILTER_247)) {
            addressesSteps.verifyAllResultsAre247();
        }

        if (filters.contains(FILTER_OPEN)) {
            addressesSteps.verifyAllResultsCurrentlyOpen();
        }
    }
}