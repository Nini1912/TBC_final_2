package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.api.clients.ExchangeRateApi;
import ge.tbc.testautomation.api.models.ExchangeRateResponse;
import io.restassured.response.Response;
import org.testng.Assert;

public class ExchangeRateApiSteps {

    private final ExchangeRateApi exchangeRateApi =
            new ExchangeRateApi();

    public Response getExchangeRate(String iso1, String iso2) {
        return exchangeRateApi.getExchangeRate(iso1, iso2);
    }

    public void validateStatusCode(
            Response response,
            int expectedStatusCode
    ) {
        Assert.assertEquals(
                response.statusCode(),
                expectedStatusCode,
                "Unexpected HTTP status code"
        );
    }

    public ExchangeRateResponse deserializeExchangeRateResponse(
            Response response
    ) {
        ExchangeRateResponse exchangeRateResponse =
                response.as(ExchangeRateResponse.class);

        Assert.assertNotNull(
                exchangeRateResponse,
                "Exchange rate response should not be null"
        );

        return exchangeRateResponse;
    }

    public void validateExchangeRateData(
            ExchangeRateResponse response,
            String expectedIso1,
            String expectedIso2
    ) {
        Assert.assertEquals(
                response.getIso1(),
                expectedIso1,
                "Unexpected source currency"
        );

        Assert.assertEquals(
                response.getIso2(),
                expectedIso2,
                "Unexpected target currency"
        );

        Assert.assertTrue(
                response.getBuyRate() > 0,
                "buyRate should be greater than 0"
        );

        Assert.assertTrue(
                response.getSellRate() > 0,
                "sellRate should be greater than 0"
        );
    }
}