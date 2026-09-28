package ge.tbc.testautomation.data;

import ge.tbc.testautomation.database.config.DatabaseConfig;
import ge.tbc.testautomation.database.mappers.LoanTestDataMapper;
import ge.tbc.testautomation.database.models.LoanTestData;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.DataProvider;

import java.util.List;

public class LoanDataProvider {

    @DataProvider(name = "loanTestData")
    public static Object[][] loanTestData() {

        try (SqlSession session = DatabaseConfig.getSession()) {

            LoanTestDataMapper mapper =
                    session.getMapper(LoanTestDataMapper.class);

            List<LoanTestData> data = mapper.getAllLoanTestData();

            return data.stream()
                    .map(loanData -> new Object[]{loanData})
                    .toArray(Object[][]::new);
        }
    }
}