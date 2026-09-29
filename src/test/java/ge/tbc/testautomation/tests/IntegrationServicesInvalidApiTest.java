package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.api.models.ErrorResponse;
import ge.tbc.testautomation.steps.IntegrationServicesApiSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class IntegrationServicesInvalidApiTest {

    private final IntegrationServicesApiSteps apiSteps =
            new IntegrationServicesApiSteps();

    @Test(description = "KAN-T44 Invalid Integration Services API response validation")
    public void validateInvalidIntegrationServicesApi() {

        Response response = apiSteps.getPage(
                INVALID_PAGE_ID,
                KA_LOCALE
        );

        apiSteps.validateStatusCode(
                response,
                STATUS_NOT_FOUND
        );

        apiSteps.validateJsonResponseBody(response);

        apiSteps.validateErrorTypeAndCode(
                response,
                OBJECT_NOT_FOUND_EXCEPTION
        );

        apiSteps.validateErrorTitleAndStatus(
                response,
                OBJECT_NOT_FOUND_TITLE,
                STATUS_NOT_FOUND
        );

        apiSteps.validateErrorDetail(
                response,
                INVALID_PAGE_DETAIL
        );

        ErrorResponse errorResponse =
                apiSteps.deserializeErrorResponse(response);

        apiSteps.validateDeserializedError(
                errorResponse,
                STATUS_NOT_FOUND,
                OBJECT_NOT_FOUND_EXCEPTION
        );
    }
}