package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.CurrencyCalculatorPage;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CurrencyCalculateSteps {

    private final CurrencyCalculatorPage currencyCalculatorPage;

    public CurrencyCalculateSteps(Page page) {
        currencyCalculatorPage =
                new CurrencyCalculatorPage(page);
    }

    public CurrencyCalculateSteps validateDropDownsNumber() {
        assertThat(
                currencyCalculatorPage.currencyDropdowns
        ).hasCount(2);

        return this;
    }

    public CurrencyCalculateSteps selectSellCurrency(String currencyCode) {
        currencyCalculatorPage.currencyDropdowns
                .nth(0)
                .click();

        currencyCalculatorPage.currenciesList
                .filter(new Locator.FilterOptions()
                        .setHasText(currencyCode))
                .first()
                .click();

        return this;
    }

    public CurrencyCalculateSteps selectBuyCurrency(String currencyCode) {
        currencyCalculatorPage.currencyDropdowns
                .nth(1)
                .click();

        currencyCalculatorPage.currenciesList
                .filter(new Locator.FilterOptions()
                        .setHasText(currencyCode))
                .first()
                .click();

        return this;
    }

    public CurrencyCalculateSteps verifySellCurrencySelected(String currencyCode) {
        assertThat(
                currencyCalculatorPage.currencyDropdowns.nth(0)
        ).containsText(currencyCode);

        return this;
    }

    public CurrencyCalculateSteps verifyBuyCurrencySelected(String currencyCode) {
        assertThat(
                currencyCalculatorPage.currencyDropdowns.nth(1)
        ).containsText(currencyCode);

        return this;
    }

    public CurrencyCalculateSteps enterSellAmount(String amount) {
        currencyCalculatorPage.sellAmount.fill(amount);

        assertThat(currencyCalculatorPage.sellAmount)
                .hasValue(amount);

        return this;
    }

    public double getUiExchangeRate(
            String sellCurrency,
            String buyCurrency
    ) {
        assertThat(currencyCalculatorPage.exchangeRateElement)
                .containsText(sellCurrency);

        assertThat(currencyCalculatorPage.exchangeRateElement)
                .containsText(buyCurrency);

        String exchangeRateText =
                currencyCalculatorPage.exchangeRateElement
                        .innerText()
                        .trim();

        Pattern pattern = Pattern.compile(
                "1\\s+" + Pattern.quote(sellCurrency)
                        + "\\s*=\\s*([0-9]+(?:\\.[0-9]+)?)\\s+"
                        + Pattern.quote(buyCurrency)
        );

        Matcher matcher = pattern.matcher(exchangeRateText);

        Assert.assertTrue(
                matcher.find(),
                "Could not extract exchange rate from UI text: "
                        + exchangeRateText
        );

        return Double.parseDouble(matcher.group(1));
    }

    public CurrencyCalculateSteps verifyExchangeRateMatchesApi(
            double apiBuyRate,
            String sellCurrency,
            String buyCurrency,
            String errorMessage
    ) {
        double uiRate = getUiExchangeRate(
                sellCurrency,
                buyCurrency
        );

        Assert.assertEquals(
                uiRate,
                apiBuyRate,
                0.001,
                errorMessage
        );

        return this;
    }

    public double calculateExpectedBuyAmount(double apiBuyRate) {
        double enteredAmount =
                Double.parseDouble(
                        currencyCalculatorPage.sellAmount.inputValue()
                );

        return enteredAmount * apiBuyRate;
    }

    public CurrencyCalculateSteps verifyConvertedAmount(
            double expectedAmount,
            String errorMessage
    ) {
        assertThat(currencyCalculatorPage.buyAmount)
                .not()
                .hasValue("");

        double actualAmount = Double.parseDouble(
                currencyCalculatorPage.buyAmount.inputValue()
        );

        Assert.assertEquals(
                actualAmount,
                expectedAmount,
                0.01,
                errorMessage
        );

        return this;
    }
}