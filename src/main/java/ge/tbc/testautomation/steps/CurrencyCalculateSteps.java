package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.CurrencyCalculatorPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CurrencyCalculateSteps {

    private final CurrencyCalculatorPage currencyCalculatorPage;

    public CurrencyCalculateSteps(Page page) {
        currencyCalculatorPage = new CurrencyCalculatorPage(page);
    }

    public CurrencyCalculateSteps validateDropDownsNumber() {
        assertThat(currencyCalculatorPage.sellCurrencyDropdown())
                .isVisible();

        assertThat(currencyCalculatorPage.buyCurrencyDropdown())
                .isVisible();

        return this;
    }

    public CurrencyCalculateSteps selectSellCurrency(String currencyCode) {
        currencyCalculatorPage.sellCurrencyDropdown().click();
        currencyCalculatorPage.currencyOption(currencyCode).click();

        return this;
    }

    public CurrencyCalculateSteps selectBuyCurrency(String currencyCode) {
        currencyCalculatorPage.buyCurrencyDropdown().click();
        currencyCalculatorPage.currencyOption(currencyCode).click();

        return this;
    }

    public CurrencyCalculateSteps verifySellCurrencySelected(String currencyCode) {
        assertThat(currencyCalculatorPage.sellCurrencyDropdown())
                .containsText(currencyCode);

        return this;
    }

    public CurrencyCalculateSteps verifyBuyCurrencySelected(String currencyCode) {
        assertThat(currencyCalculatorPage.buyCurrencyDropdown())
                .containsText(currencyCode);

        return this;
    }

    public CurrencyCalculateSteps enterValidSellAmount(String amount) {
        Locator sellAmount = currencyCalculatorPage.sellAmountInput();

        assertThat(sellAmount).isVisible();

        sellAmount.fill(amount);

        assertThat(sellAmount).hasValue(amount);

        return this;
    }

    public CurrencyCalculateSteps enterInvalidSellAmount(
            String invalidAmount,
            String correctedAmount
    ) {
        Locator sellAmount = currencyCalculatorPage.sellAmountInput();

        assertThat(sellAmount).isVisible();

        sellAmount.fill(invalidAmount);

        assertThat(sellAmount).hasValue(correctedAmount);

        return this;
    }

    public double getExchangeRate() {
        Locator exchangeRate = currencyCalculatorPage.exchangeRateDescription();

        assertThat(exchangeRate).isVisible();

        String text = exchangeRate.textContent();

        if (text == null || text.isBlank()) {
            throw new AssertionError("Exchange rate text is empty.");
        }

        Matcher matcher = Pattern.compile(
                "=\\s*([0-9]+(?:[.,][0-9]+)?)\\s*GEL"
        ).matcher(text);

        if (!matcher.find()) {
            throw new AssertionError(
                    "Could not extract exchange rate from: " + text
            );
        }

        return Double.parseDouble(
                matcher.group(1).replace(",", ".")
        );
    }

    public double calculateExpectedBuyAmount(double exchangeRate) {
        String sellAmount =
                currencyCalculatorPage.sellAmountInput().inputValue();

        if (sellAmount == null || sellAmount.isBlank()) {
            throw new AssertionError("Sell amount is empty.");
        }

        double enteredAmount = Double.parseDouble(sellAmount);

        return enteredAmount * exchangeRate;
    }

    public CurrencyCalculateSteps verifyConvertedAmount(
            double expectedAmount,
            String errorMessage
    ) {
        Locator buyAmount = currencyCalculatorPage.buyAmountInput();

        assertThat(buyAmount).isVisible();

        String actualValue = buyAmount.inputValue();

        if (actualValue == null || actualValue.isBlank()) {
            throw new AssertionError(
                    errorMessage + " Converted amount is empty."
            );
        }

        double actualAmount;

        try {
            actualAmount = Double.parseDouble(actualValue);
        } catch (NumberFormatException e) {
            throw new AssertionError(
                    errorMessage
                            + " Invalid converted amount: "
                            + actualValue,
                    e
            );
        }

        if (Math.abs(actualAmount - expectedAmount) > 0.01) {
            throw new AssertionError(
                    errorMessage
                            + " Expected: "
                            + expectedAmount
                            + ", Actual: "
                            + actualAmount
            );
        }

        return this;
    }

    public CurrencyCalculateSteps enterSellAmount(String input, String expectedDisplayedValue) {
        Locator sellAmount = currencyCalculatorPage.sellAmountInput();
        assertThat(sellAmount).isVisible();
        sellAmount.fill(input);
        assertThat(sellAmount).hasValue(expectedDisplayedValue);
        return this;
    }
}