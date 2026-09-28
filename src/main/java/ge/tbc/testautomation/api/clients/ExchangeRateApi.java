package ge.tbc.testautomation.api.clients;

import io.restassured.response.Response;

import static ge.tbc.testautomation.data.Constants.EXCHANGE_RATE_ENDPOINT;
import static io.restassured.RestAssured.given;

public class ExchangeRateApi extends BaseApi {

    public Response getExchangeRate(String iso1, String iso2) {
        return given()
                .spec(REQ_SPEC)
                .queryParam("Iso1", iso1)
                .queryParam("Iso2", iso2)
                .when()
                .get(EXCHANGE_RATE_ENDPOINT);
    }
}