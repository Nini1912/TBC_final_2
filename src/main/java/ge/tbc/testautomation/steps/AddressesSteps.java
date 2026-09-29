package ge.tbc.testautomation.steps;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import ge.tbc.testautomation.pages.AddressesPage;
import ge.tbc.testautomation.utils.OpenHoursChecker;
import org.testng.Assert;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ge.tbc.testautomation.data.Constants.*;

public class AddressesSteps {

    private final Page page;
    private final AddressesPage addressesPage;
    private final OpenHoursChecker openHoursChecker = new OpenHoursChecker();

    public AddressesSteps(Page page) {
        this.page = page;
        this.addressesPage = new AddressesPage(page);
    }

    public AddressesSteps selectBranchesTab() {
        addressesPage.branchesTab.click();
        return this;
    }

    public AddressesSteps verifyBranchesTabActive() {
        ElementHandle tabWrapper = addressesPage.branchesTabWrapper.elementHandle();
        try {
            page.waitForFunction(
                    "el => getComputedStyle(el, '::after').getPropertyValue('background-color')"
                            + ".includes('" + TAB_ACTIVE_COLOR + "')",
                    tabWrapper
            );
        } catch (PlaywrightException e) {
            Assert.fail(BRANCHES_TAB_INACTIVE_ERROR_MESSAGE
                    + "Expected underline color: " + TAB_ACTIVE_COLOR);
        }
        return this;
    }

    public AddressesSteps selectCity(String city) {
        addressesPage.cityDropdown.click();
        addressesPage.cityOption(city).click();
        return this;
    }

    public AddressesSteps verifyCitySelected(String city) {
        assertThat(addressesPage.selectedCity).containsText(city);
        return this;
    }

    public AddressesSteps searchLocation(String location) {
        addressesPage.locationSearch.fill(location);
        assertThat(addressesPage.locationSearch).hasValue(location);
        return this;
    }

    public AddressesSteps verifyAllResultsContainLocation(String location) {
        assertThat(addressesPage.results.first()).containsText(location);
        assertThat(addressesPage.results.filter(
                new Locator.FilterOptions().setHasNotText(location))).hasCount(0);
        return this;
    }

    public AddressesSteps select247Filter() {
        addressesPage.filter247.click();
        assertThat(addressesPage.filter247Input).isChecked();
        return this;
    }

    public AddressesSteps selectOpenFilter() {
        addressesPage.filterOpen.click();
        assertThat(addressesPage.filterOpenInput).isChecked();
        return this;
    }

    public AddressesSteps waitForResults() {
        waitForResultsToAppear();
        return this;
    }

    private void waitForResultsToAppear() {
        try {
            assertThat(addressesPage.items.first()).isVisible();
        } catch (AssertionError e) {
            throw new AssertionError(
                    "Address results did not appear."
                            + "\nURL: " + page.url()
                            + "\nTitle: " + page.title()
                            + "\nitems=" + addressesPage.items.count()
                            + "\ndescriptions=" + addressesPage.descriptions.count()
                            + "\nskeleton=" + addressesPage.skeleton.count()
                            + "\nemptyState=" + addressesPage.emptyStateTitle.count(),
                    e
            );
        }
    }

    public AddressesSteps verifyAllResultsAre247() {
        waitForResultsToAppear();

        Locator not247 = addressesPage.descriptions
                .filter(new Locator.FilterOptions().setHasNotText(FILTER_247));

        assertThat(not247).hasCount(0);

        return this;
    }

    public AddressesSteps verifyAllResultsCurrentlyOpen() {
        waitForResultsToAppear();

        long deadline = System.currentTimeMillis() + 15_000;
        List<String> descriptions;
        List<String> closed;
        do {
            descriptions = addressesPage.descriptions.allInnerTexts();
            closed = descriptions.stream()
                    .filter(text -> !openHoursChecker.isCurrentlyOpen(text))
                    .toList();

            if (!descriptions.isEmpty() && closed.isEmpty()) {
                return this;
            }
        } while (System.currentTimeMillis() < deadline);

        Assert.fail("Results are empty or contain branches that are not open now: " + closed);
        return this;
    }
}