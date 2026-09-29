package ge.tbc.testautomation.api.clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static ge.tbc.testautomation.data.Constants.API_BASE_PATH;
import static ge.tbc.testautomation.data.Constants.API_BASE_URL;

public abstract class BaseApi {

    protected static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri(API_BASE_URL)
                    .setBasePath(API_BASE_PATH)
                    .setAccept(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .addFilter(new ResponseLoggingFilter())
                    .build();
}