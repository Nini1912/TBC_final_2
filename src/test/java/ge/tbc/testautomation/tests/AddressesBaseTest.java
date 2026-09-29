package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.components.CookieBannerComponent;
import ge.tbc.testautomation.components.NavigationComponent;
import ge.tbc.testautomation.steps.AddressesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class AddressesBaseTest extends BaseTest {

    protected NavigationComponent navigation;
    protected AddressesSteps addressesSteps;
    private CookieBannerComponent cookieBanner;

    @BeforeClass
    public void addressesSetUp() {
        navigation = new NavigationComponent(page);
        addressesSteps = new AddressesSteps(page);
        cookieBanner = new CookieBannerComponent(page);
    }

    @BeforeMethod
    public void dismissCookies() {
        cookieBanner.acceptMainSiteIfPresent();
        cookieBanner.acceptIfPresent();
    }
}