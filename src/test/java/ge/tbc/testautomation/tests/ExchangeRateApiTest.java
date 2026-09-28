package ge.tbc.testautomation.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import ge.tbc.testautomation.api.clients.ExchangeRateApiClient;
import ge.tbc.testautomation.api.models.ExchangeRate;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ExchangeRateApiTest {

    private ExchangeRateApiClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeClass
    public void setUp() {
        client = new ExchangeRateApiClient();
    }

    @Test(description = "KAN-TXX | TBC Digital API-ის ვალიდური მოთხოვნის შემოწმება")
    public void validRequestReturnsExchangeRate() throws Exception {
        Response response = client.getExchangeRate("EUR", "GEL");

        assertEquals(response.statusCode(), 200, "Unexpected status code");

        ExchangeRate rate = objectMapper.readValue(response.asString(), ExchangeRate.class);

        assertEquals(rate.getIso1(), "EUR");
        assertEquals(rate.getIso2(), "GEL");
        assertNotNull(rate.getBuyRate());
        assertNotNull(rate.getSellRate());
        assertTrue(rate.getBuyRate() > 0, "Buy rate should be positive");
        assertTrue(rate.getSellRate() > rate.getBuyRate(), "Sell rate should exceed buy rate");
    }
}