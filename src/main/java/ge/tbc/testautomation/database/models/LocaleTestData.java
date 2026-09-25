package ge.tbc.testautomation.database.models;

public class LocaleTestData {
    private final String locale;
    private final String languageButtonText;
    private final String expectedPageTitle;
    private final String expectedInfoSnippet;

    public LocaleTestData(String locale, String languageButtonText, String expectedPageTitle, String expectedInfoSnippet) {
        this.locale = locale;
        this.languageButtonText = languageButtonText;
        this.expectedPageTitle = expectedPageTitle;
        this.expectedInfoSnippet = expectedInfoSnippet;
    }

    public String getLocale() { return locale; }
    public String getLanguageButtonText() { return languageButtonText; }
    public String getExpectedPageTitle() { return expectedPageTitle; }
    public String getExpectedInfoSnippet() { return expectedInfoSnippet; }

    @Override
    public String toString() { return locale; } 
}