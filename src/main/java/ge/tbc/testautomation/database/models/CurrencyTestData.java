package ge.tbc.testautomation.database.models;

public class CurrencyTestData {
    private final String description;
    private final String sellCurrency;
    private final String buyCurrency;
    private final String enteredAmount;
    private final String expectedDisplayedAmount;

    public CurrencyTestData(String description, String sellCurrency, String buyCurrency,
                             String enteredAmount, String expectedDisplayedAmount) {
        this.description = description;
        this.sellCurrency = sellCurrency;
        this.buyCurrency = buyCurrency;
        this.enteredAmount = enteredAmount;
        this.expectedDisplayedAmount = expectedDisplayedAmount;
    }

    public String getSellCurrency() { return sellCurrency; }
    public String getBuyCurrency() { return buyCurrency; }
    public String getEnteredAmount() { return enteredAmount; }
    public String getExpectedDisplayedAmount() { return expectedDisplayedAmount; }

    @Override
    public String toString() { return description; }
}