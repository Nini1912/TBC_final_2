package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.components.LanguageSwitcherComponent;
import ge.tbc.testautomation.components.NavigationComponent;
import ge.tbc.testautomation.data.LocalizationDataProvider;
import ge.tbc.testautomation.steps.ConsumerLoanSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class LoanPageLocalizationTest extends BaseTest {
    LanguageSwitcherComponent languageSwitcher;
    NavigationComponent navigationComponent;
    ConsumerLoanSteps consumerLoanSteps;

    private final String locale;
    private final String expectedTitle;
    private final String expectedSnippet;

    @Factory(dataProvider = "localeTestData", dataProviderClass = LocalizationDataProvider.class)
    public LoanPageLocalizationTest(String locale, String expectedTitle, String expectedSnippet) {
        this.locale = locale;
        this.expectedTitle = expectedTitle;
        this.expectedSnippet = expectedSnippet;
    }

    @BeforeClass
    public void innerSetUp() {
        languageSwitcher = new LanguageSwitcherComponent(page);
        navigationComponent = new NavigationComponent(page);
        consumerLoanSteps = new ConsumerLoanSteps(page);
    }

    @Test(priority = 1, description = "KAN-T39/KAN-T40 ენის არჩევა")
    public void selectLanguage() {
        languageSwitcher.switchTo(locale);
    }


    @Test(priority = 2, description = "KAN-T39/KAN-T40 სამომხმარებლო სესხის გვერდზე გადასვლა")
    public void navigateToConsumerLoanPage() {
        navigationComponent.navigateToForMe()
                .navigateToConsumerLoan();
    }

    @Test(priority = 3, description = "KAN-T39/KAN-T40 გვერდის სათაურის და ძირითადი ინფორმაციის ვალიდაცია")
    public void verifyPageContentLocalized() {
        consumerLoanSteps.verifyPageTitle(expectedTitle)
                .verifyMainInfoDisplayed(expectedSnippet);
    }
}