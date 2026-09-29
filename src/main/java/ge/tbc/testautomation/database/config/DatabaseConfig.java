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
import java.util.Properties;
import java.util.stream.Collectors;

public final class DatabaseConfig {

    private static final String DATABASE_PROPERTIES =
            "database.properties";

    private static final String SQL_SCRIPT =
            "testdata/loan_test_data.sql";

    private static final String MYBATIS_CONFIG =
            "mybatis/mybatis-config.xml";

    private static final Properties PROPERTIES =
            loadProperties();

    private static final SqlSessionFactory SQL_SESSION_FACTORY =
            initialize();

    private DatabaseConfig() {
    }

    private static Properties loadProperties() {
        try (InputStream inputStream =
                     Resources.getResourceAsStream(DATABASE_PROPERTIES)) {

            Properties properties = new Properties();
            properties.load(inputStream);

            return properties;

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to load database properties",
                    e
            );
        }
    }

    private static SqlSessionFactory initialize() {
        initializeDatabase();
        return initializeMyBatis();
    }

    private static void initializeDatabase() {
        String jdbcUrl =
                PROPERTIES.getProperty("jdbc.url");

        String username =
                PROPERTIES.getProperty("jdbc.username");

        String password =
                PROPERTIES.getProperty("jdbc.password");

        try (
                Connection connection =
                        DriverManager.getConnection(
                                jdbcUrl,
                                username,
                                password
                        );

                Statement statement =
                        connection.createStatement();

                InputStream inputStream =
                        Resources.getResourceAsStream(SQL_SCRIPT);

                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        inputStream,
                                        StandardCharsets.UTF_8
                                )
                        )
        ) {

            String sql = reader.lines()
                    .collect(Collectors.joining("\n"));

            for (String command : sql.split(";")) {

                String trimmedCommand = command.trim();

                if (!trimmedCommand.isEmpty()) {
                    statement.execute(trimmedCommand);
                }
            }

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to initialize H2 database",
                    e
            );
        }
    }

    private static SqlSessionFactory initializeMyBatis() {
        try (InputStream inputStream =
                     Resources.getResourceAsStream(MYBATIS_CONFIG)) {

            return new SqlSessionFactoryBuilder()
                    .build(inputStream);

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to initialize MyBatis",
                    e
            );
        }
    }

    public static SqlSession getSession() {
        return SQL_SESSION_FACTORY.openSession();
    }
}