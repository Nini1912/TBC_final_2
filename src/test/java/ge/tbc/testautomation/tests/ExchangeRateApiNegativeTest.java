package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.api.clients.ExchangeRateApiClient;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ExchangeRateApiNegativeTest {

    private ExchangeRateApiClient client;

    @BeforeClass
    public void setUp() {
        client = new ExchangeRateApiClient();
    }

    @Test(description = "KAN-TXX | TBC Digital API-ის არავალიდური მოთხოვნის შემოწმება")
    public void invalidCurrencyCodeIsRejected() {
        Response response = client.getExchangeRate("ZZZ", "GEL");

        String contentType = response.contentType();
        String body = response.asString();

        boolean isRejectedByGateway = contentType != null
                && contentType.contains("text/html")
                && body.contains("Request Rejected");

        assertTrue(isRejectedByGateway,
                "Expected the gateway to reject an invalid currency code. "
                        + "Content-Type=" + contentType + ", body=" + body);
    }
}