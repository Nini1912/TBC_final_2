package ge.tbc.testautomation.api.clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ExchangeRateApiClient {

    private static final String EXCHANGE_RATE_ENDPOINT =
            "https://apigw.tbcbank.ge/api/v1/exchangeRates/getExchangeRate";

    public Response getExchangeRate(String iso1, String iso2) {
        return given()
                .queryParam("Iso1", iso1)
                .queryParam("Iso2", iso2)
                .when()
                .get(EXCHANGE_RATE_ENDPOINT);
    }
}