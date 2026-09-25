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

    @Test(priority = 2, description = "სამომხმარებლო სესხის გვერდზე გადასვლა")
    public void navigateToConsumerLoanPage() {
        navigationSteps.navigateToForMe()
                .navigateToConsumerLoan();
    }

    @Test(priority = 3, description = "გვერდის სათაურის და ძირითადი ინფორმაციის ვალიდაცია")
    public void verifyPageContentLocalized() {
        consumerLoanSteps.verifyPageTitle(testData.getExpectedPageTitle())
                .verifyMainInfoDisplayed(testData.getExpectedInfoSnippet());
    }
}