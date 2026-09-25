package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.database.models.CurrencyTestData;
import ge.tbc.testautomation.data.CurrencyDataProvider;
import ge.tbc.testautomation.steps.CurrencyCalculateSteps;
import ge.tbc.testautomation.steps.NavigationSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class CurrencyCalculatorTest extends BaseTest {

    private final CurrencyTestData testData;

    private NavigationSteps navigationSteps;
    private CurrencyCalculateSteps currencyCalculateSteps;

    @Factory(dataProvider = "currencyTestData", dataProviderClass = CurrencyDataProvider.class)
    public CurrencyCalculatorTest(CurrencyTestData testData) {
        this.testData = testData;
    }

    @BeforeClass
    public void innerSetUp() {
        navigationSteps = new NavigationSteps(page);
        currencyCalculateSteps = new CurrencyCalculateSteps(page);
    }

    @Test(priority = 1, description = "ვალუტის კურსის გვერდზე გადასვლა")
    public void navigateToCurrencyRatesPage() {
        navigationSteps.navigateToForMe()
                .navigateToCurrencyRate()
                .verifyBreadcrumbContains(CURRENCY_RATES_TEXT);
    }

    @Test(priority = 2, description = "საწყისი და სამიზნე ვალუტის არჩევა")
    public void selectCurrencies() {
        currencyCalculateSteps
                .validateDropDownsNumber()
                .selectSellCurrency(testData.getSellCurrency())
                .selectBuyCurrency(testData.getBuyCurrency())
                .verifySellCurrencySelected(testData.getSellCurrency())
                .verifyBuyCurrencySelected(testData.getBuyCurrency());
    }

    @Test(priority = 3, description = "თანხის შეყვანა")
    public void enterAmount() {
        currencyCalculateSteps.enterSellAmount(
                testData.getEnteredAmount(),
                testData.getExpectedDisplayedAmount()
        );
    }

    @Test(priority = 4, description = "კონვერტაციის შედეგად მიღებული თანხის გადამოწმება")
    public void verifyConvertedAmount() {
        double exchangeRate = currencyCalculateSteps.getExchangeRate();
        double expectedAmount = currencyCalculateSteps.calculateExpectedBuyAmount(exchangeRate);
        currencyCalculateSteps.verifyConvertedAmount(expectedAmount, CURRENCY_ERROR_MESSAGE);
    }
}