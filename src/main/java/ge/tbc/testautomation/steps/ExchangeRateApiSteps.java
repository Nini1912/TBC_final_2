package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.api.clients.ExchangeRateApi;
import ge.tbc.testautomation.api.models.ExchangeRateResponse;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ExchangeRateApiSteps {

    private final ExchangeRateApi exchangeRateApi =
            new ExchangeRateApi();

    public Response getExchangeRate(
            String iso1,
            String iso2
    ) {
        return exchangeRateApi.getExchangeRate(iso1, iso2);
    }

    public void validateStatusCode(
            Response response,
            int expectedStatusCode
    ) {
        assertThat(
                "Unexpected HTTP status code",
                response.statusCode(),
                is(expectedStatusCode)
        );
    }

    public ExchangeRateResponse deserializeExchangeRateResponse(
            Response response
    ) {
        ExchangeRateResponse exchangeRateResponse =
                response.as(ExchangeRateResponse.class);

        assertThat(
                "Exchange rate response should not be null",
                exchangeRateResponse,
                notNullValue()
        );

        return exchangeRateResponse;
    }

    public void validateExchangeRateData(
            ExchangeRateResponse response,
            String expectedIso1,
            String expectedIso2
    ) {
        assertThat(
                "Unexpected source currency",
                response.getIso1(),
                is(expectedIso1)
        );

        assertThat(
                "Unexpected target currency",
                response.getIso2(),
                is(expectedIso2)
        );

        assertThat(
                "buyRate should be greater than 0",
                response.getBuyRate(),
                greaterThan(0.0)
        );

        assertThat(
                "sellRate should be greater than 0",
                response.getSellRate(),
                greaterThan(0.0)
        );
    }
}