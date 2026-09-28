package ge.tbc.testautomation.database.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DatabaseConfig {

    private static final String JDBC_URL =
            "jdbc:h2:mem:tbc_automation;DB_CLOSE_DELAY=-1";

    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private static SqlSessionFactory sqlSessionFactory;

    static {
        initializeDatabase();
        initializeMyBatis();
    }

    private static void initializeDatabase() {

        try (
                Connection connection =
                        DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                Statement statement = connection.createStatement();

                InputStream inputStream =
                        Resources.getResourceAsStream("testdata/loan_test_data.sql");

                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        inputStream,
                                        StandardCharsets.UTF_8))
        ) {

            String sql = reader.lines()
                    .collect(Collectors.joining("\n"));

            for (String command : sql.split(";")) {
                if (!command.trim().isEmpty()) {
                    statement.execute(command);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize H2 database", e);
        }
    }

    private static void initializeMyBatis() {

        try (InputStream inputStream =
                     Resources.getResourceAsStream("mybatis/mybatis-config.xml")) {

            sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize MyBatis", e);
        }
    }

    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }
}