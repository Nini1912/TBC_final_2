package ge.tbc.testautomation.data;

import ge.tbc.testautomation.database.models.CurrencyTestData;
import org.testng.annotations.DataProvider;

import static ge.tbc.testautomation.data.Constants.*;

public class CurrencyDataProvider {
    private CurrencyDataProvider() {}

    @DataProvider(name = "currencyTestData")
    public static Object[][] currencyTestData() {
        return new Object[][] {
                { new CurrencyTestData("Valid currency amount", EUR, GEL, VALID_CURRENCY_INPUT, VALID_CURRENCY_INPUT) },
                { new CurrencyTestData("Invalid currency amount - corrected", EUR, GEL, INVALID_CURRENCY_INPUT, CORRECTED_CURRENCY_INPUT) }
        };
    }
}