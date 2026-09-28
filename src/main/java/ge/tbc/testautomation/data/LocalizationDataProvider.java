package ge.tbc.testautomation.data;

import org.testng.annotations.DataProvider;

public class LocalizationDataProvider {
    @DataProvider(name = "localeTestData")
    public static Object[][] localeTestData() {
        return new Object[][]{
                {"ka", "Consumer Loan", "0% disbursement fee"},
                {"en", "სამომხმარებლო სესხი", "გაცემის საკომისიო 0%"}
        };

    }
}