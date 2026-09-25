package ge.tbc.testautomation.data;

import ge.tbc.testautomation.database.models.LocaleTestData;
import org.testng.annotations.DataProvider;

public class LocalizationDataProvider {
    private LocalizationDataProvider() {}

    @DataProvider(name = "localeTestData")
    public static Object[][] localeTestData() {
        return new Object[][] {
                { new LocaleTestData(
                        "ka",
                        "ქარ",
                        "სამომხმარებლო სესხი",
                        "გაცემის საკომისიო 0%"
                ) },
                { new LocaleTestData(
                        "en",
                        "EN",
                        "Consumer Loan",
                        "0% disbursement fee"
                ) }
        };
    }
}