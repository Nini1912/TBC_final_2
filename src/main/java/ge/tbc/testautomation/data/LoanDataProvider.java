package ge.tbc.testautomation.data;

import ge.tbc.testautomation.database.config.DatabaseConfig;
import ge.tbc.testautomation.database.mappers.LoanTestDataMapper;
import ge.tbc.testautomation.database.models.LoanTestData;
import org.testng.annotations.DataProvider;

import java.util.List;

public class LoanDataProvider {
    private LoanDataProvider() {}

    @DataProvider(name = "loanTestData")
    public static Object[][] loanTestData() {
        LoanTestDataMapper mapper = DatabaseConfig.loanTestDataMapper();
        List<LoanTestData> records = mapper.getAllLoanTestData();

        Object[][] data = new Object[records.size()][1];
        for (int i = 0; i < records.size(); i++) {
            data[i][0] = records.get(i);
        }
        return data;
    }
}