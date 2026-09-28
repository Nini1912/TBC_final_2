package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.components.LanguageSwitcherComponent;
import ge.tbc.testautomation.database.models.LocaleTestData;
import ge.tbc.testautomation.data.LocalizationDataProvider;
import ge.tbc.testautomation.steps.ConsumerLoanSteps;
import ge.tbc.testautomation.steps.NavigationSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class LoanPageLocalizationTest extends BaseTest {

    private final LocaleTestData testData;

    private LanguageSwitcherComponent languageSwitcher;
    private NavigationSteps navigationSteps;
    private ConsumerLoanSteps consumerLoanSteps;

    @Factory(dataProvider = "localeTestData", dataProviderClass = LocalizationDataProvider.class)
    public LoanPageLocalizationTest(LocaleTestData testData) {
        this.testData = testData;
    }

    @BeforeClass
    public void innerSetUp() {
        languageSwitcher = new LanguageSwitcherComponent(page);
        navigationSteps = new NavigationSteps(page);
        consumerLoanSteps = new ConsumerLoanSteps(page);
    }

    @Test(priority = 1, description = "ენის არჩევა")
    public void selectLanguage() {
        languageSwitcher.switchTo(testData.getLocale());
    }

    @Test(priority = 2, dependsOnMethods = "selectLanguage", description = "...")
    public void navigateToConsumerLoanPage() {
        System.out.println("html lang before nav = " + languageSwitcher.getCurrentLanguage());
        System.out.println("forMeMenuItem count = " + navigationSteps.getNavigationPage().forMeMenuItem().count());

        navigationSteps.navigateToForMe();

        System.out.println("consumerLoanMenuItem count = " + navigationSteps.getNavigationPage().consumerLoanMenuItem().count());
        System.out.println("consumerLoanMenuItem hrefs = " + navigationSteps.getNavigationPage().consumerLoanMenuItem().allTextContents());

        navigationSteps.navigateToConsumerLoan();
    }

    @Test(priority = 3, dependsOnMethods = "navigateToConsumerLoanPage", description = "...")
    public void verifyPageContentLocalized() {
        System.out.println("URL at verification = " + page.url());
        consumerLoanSteps.verifyPageTitle(testData.getExpectedPageTitle())
                .verifyMainInfoDisplayed(testData.getExpectedInfoSnippet());
    }
}