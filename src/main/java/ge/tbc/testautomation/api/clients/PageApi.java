package ge.tbc.testautomation.api.clients;

import io.restassured.response.Response;

import static ge.tbc.testautomation.data.Constants.PAGE_ENDPOINT;
import static io.restassured.RestAssured.given;

public class PageApi extends BaseApi {

    public Response getPage(String pageId, String locale) {
        return given()
                .spec(REQ_SPEC)
                .pathParam("pageId", pageId)
                .queryParam("locale", locale)
                .when()
                .get(PAGE_ENDPOINT);
    }
}