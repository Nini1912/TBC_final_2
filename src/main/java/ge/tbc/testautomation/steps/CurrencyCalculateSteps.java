package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.pages.CurrencyCalculatorPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class CurrencyCalculateSteps {

    private final CurrencyCalculatorPage currencyCalculatorPage;

    public CurrencyCalculateSteps(Page page) {
        currencyCalculatorPage =
                new CurrencyCalculatorPage(page);
    }

    public CurrencyCalculateSteps validateDropDownsNumber() {
        PlaywrightAssertions
                .assertThat(currencyCalculatorPage.currencyDropdowns)
                .hasCount(2);

        return this;
    }

    public CurrencyCalculateSteps selectSellCurrency(
            String currencyCode
    ) {
        currencyCalculatorPage.currencyDropdowns
                .nth(0)
                .click();

        currencyCalculatorPage.currenciesList
                .filter(
                        new Locator.FilterOptions()
                                .setHasText(currencyCode)
                )
                .first()
                .click();

        return this;
    }

    public CurrencyCalculateSteps selectBuyCurrency(
            String currencyCode
    ) {
        currencyCalculatorPage.currencyDropdowns
                .nth(1)
                .click();

        currencyCalculatorPage.currenciesList
                .filter(
                        new Locator.FilterOptions()
                                .setHasText(currencyCode)
                )
                .first()
                .click();

        return this;
    }

    public CurrencyCalculateSteps verifySellCurrencySelected(
            String currencyCode
    ) {
        PlaywrightAssertions
                .assertThat(
                        currencyCalculatorPage
                                .currencyDropdowns
                                .nth(0)
                )
                .containsText(currencyCode);

        return this;
    }

    public CurrencyCalculateSteps verifyBuyCurrencySelected(
            String currencyCode
    ) {
        PlaywrightAssertions
                .assertThat(
                        currencyCalculatorPage
                                .currencyDropdowns
                                .nth(1)
                )
                .containsText(currencyCode);

        return this;
    }

    public CurrencyCalculateSteps enterSellAmount(
            String amount
    ) {
        String previousBuyAmount =
                currencyCalculatorPage.buyAmount.inputValue();

        currencyCalculatorPage.sellAmount.fill(amount);

        PlaywrightAssertions
                .assertThat(currencyCalculatorPage.sellAmount)
                .hasValue(amount);

        PlaywrightAssertions
                .assertThat(currencyCalculatorPage.buyAmount)
                .not()
                .hasValue(previousBuyAmount);

        return this;
    }

    public double getUiExchangeRate(
            String sellCurrency,
            String buyCurrency
    ) {
        PlaywrightAssertions
                .assertThat(currencyCalculatorPage.exchangeRateElement)
                .containsText(sellCurrency);

        PlaywrightAssertions
                .assertThat(currencyCalculatorPage.exchangeRateElement)
                .containsText(buyCurrency);

        String exchangeRateText =
                currencyCalculatorPage
                        .exchangeRateElement
                        .innerText()
                        .trim();

        Pattern pattern = Pattern.compile(
                "1\\s+"
                        + Pattern.quote(sellCurrency)
                        + "\\s*=\\s*([0-9]+(?:\\.[0-9]+)?)\\s+"
                        + Pattern.quote(buyCurrency)
        );

        Matcher matcher =
                pattern.matcher(exchangeRateText);

        assertThat(
                "Could not extract exchange rate from UI text: "
                        + exchangeRateText,
                matcher.find(),
                is(true)
        );

        return Double.parseDouble(
                matcher.group(1)
        );
    }

    public CurrencyCalculateSteps verifyExchangeRateMatchesApi(
            double apiBuyRate,
            String sellCurrency,
            String buyCurrency,
            String errorMessage
    ) {
        double uiRate =
                getUiExchangeRate(
                        sellCurrency,
                        buyCurrency
                );

        assertThat(
                errorMessage,
                uiRate,
                closeTo(apiBuyRate, 0.001)
        );

        return this;
    }

    public double calculateExpectedBuyAmount(
            double apiBuyRate
    ) {
        double enteredAmount =
                Double.parseDouble(
                        currencyCalculatorPage
                                .sellAmount
                                .inputValue()
                );

        return enteredAmount * apiBuyRate;
    }

    public CurrencyCalculateSteps verifyConvertedAmount(
            double expectedAmount,
            String errorMessage
    ) {
        PlaywrightAssertions
                .assertThat(currencyCalculatorPage.buyAmount)
                .not()
                .hasValue("");

        double actualAmount =
                Double.parseDouble(
                        currencyCalculatorPage
                                .buyAmount
                                .inputValue()
                );

        assertThat(
                errorMessage,
                actualAmount,
                closeTo(expectedAmount, 0.01)
        );

        return this;
    }
}