package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.api.models.ExchangeRateResponse;
import ge.tbc.testautomation.steps.CurrencyCalculateSteps;
import ge.tbc.testautomation.steps.ExchangeRateApiSteps;
import ge.tbc.testautomation.steps.NavigationSteps;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class ExchangeRateApiToUiTest extends BaseTest {

    NavigationSteps navigationSteps;
    CurrencyCalculateSteps currencyCalculateSteps;
    ExchangeRateApiSteps exchangeRateApiSteps;

    private Response response;
    private ExchangeRateResponse exchangeRateResponse;

    private double apiBuyRate;
    private double apiSellRate;

    @BeforeClass
    public void innerSetUp() {
        navigationSteps = new NavigationSteps(page);
        currencyCalculateSteps = new CurrencyCalculateSteps(page);
        exchangeRateApiSteps = new ExchangeRateApiSteps();
    }

    @Test(priority = 1, description = "KAN-T45 ვალუტის კურსის API-ზე მოთხოვნის გაგზავნა")
    public void sendExchangeRateApiRequest() {
        response = exchangeRateApiSteps.getExchangeRate(
                USD,
                GEL
        );

        exchangeRateApiSteps.validateStatusCode(
                response,
                STATUS_OK
        );
    }

    @Test(priority = 2, description = "KAN-T45 API response body-ის Java POJO-ში დესერიალიზაცია")
    public void deserializeExchangeRateResponse() {
        exchangeRateResponse =
                exchangeRateApiSteps.deserializeExchangeRateResponse(
                        response
                );
    }

    @Test(priority = 3, description = "KAN-T45 API response-დან buyRate და sellRate მნიშვნელობების მიღება")
    public void extractExchangeRates() {
        exchangeRateApiSteps.validateExchangeRateData(
                exchangeRateResponse,
                USD,
                GEL
        );

        apiBuyRate = exchangeRateResponse.getBuyRate();
        apiSellRate = exchangeRateResponse.getSellRate();
    }

    @Test(priority = 4, description = "KAN-T45 ვალუტის კურსის გვერდზე გადასვლა")
    public void navigateToCurrencyRatesPage() {
        navigationSteps
                .navigateToForMe()
                .navigateToCurrencyRate()
                .verifyBreadcrumbContains(CURRENCY_RATES_TEXT);
    }

    @Test(priority = 5, description = "KAN-T45 საწყისი და სამიზნე ვალუტის არჩევა")
    public void selectCurrencies() {
        currencyCalculateSteps
                .validateDropDownsNumber()
                .selectSellCurrency(USD)
                .selectBuyCurrency(GEL)
                .verifySellCurrencySelected(USD)
                .verifyBuyCurrencySelected(GEL);
    }

    @Test(priority = 6, description = "KAN-T45 თანხის შეყვანა")
    public void enterAmount() {
        currencyCalculateSteps.enterSellAmount(
                VALID_CURRENCY_INPUT
        );
    }

    @Test(priority = 7, description = "KAN-T45 UI-ზე ნაჩვენები გაცვლითი კურსის API buyRate-თან შედარება")
    public void verifyUiExchangeRateMatchesApi() {
        currencyCalculateSteps.verifyExchangeRateMatchesApi(
                apiBuyRate,
                USD,
                GEL,
                EXCHANGE_RATE_UI_ERROR_MESSAGE
        );
    }

    @Test(priority = 8, description = "KAN-T45 კონვერტაციის შედეგად მიღებული თანხის API კურსის საფუძველზე გადამოწმება")
    public void verifyConvertedAmountUsingApiRate() {
        double expectedAmount =
                currencyCalculateSteps.calculateExpectedBuyAmount(
                        apiBuyRate
                );

        currencyCalculateSteps.verifyConvertedAmount(
                expectedAmount,
                CONVERTED_AMOUNT_API_ERROR_MESSAGE
        );
    }
}