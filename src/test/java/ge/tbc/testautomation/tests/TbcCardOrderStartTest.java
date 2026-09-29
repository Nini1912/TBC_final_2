package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.components.CookieBannerComponent;
import ge.tbc.testautomation.components.NavigationComponent;
import ge.tbc.testautomation.steps.OrderCardSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.TBC_CARD_TEXT;

public class TbcCardOrderStartTest extends BaseTest {

    NavigationComponent navigation;
    OrderCardSteps orderCardSteps;
    CookieBannerComponent cookieBanner;

    @BeforeClass
    public void innerSetUp() {
        navigation = new NavigationComponent(page);
        orderCardSteps = new OrderCardSteps(page);
        cookieBanner = new CookieBannerComponent(page);
    }

    @BeforeMethod
    public void dismissCookies() {
        cookieBanner.acceptMainSiteIfPresent();
    }

    @Test(priority = 1, description = "თიბისი ბარათის გვერდზე გადასვლა")
    public void navigateToTbcCardPage() {
        navigation.navigateToForMe()
                .navigateToTbcCard()
                .verifyBreadcrumbContains(TBC_CARD_TEXT);
    }

    @Test(priority = 2, description = "ბარათის აღების დაწყება")
    public void startGettingCard() {
        orderCardSteps.startOrder()
                .verifyOrderStarted();
    }
}