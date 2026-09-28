package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.api.models.ErrorResponse;
import ge.tbc.testautomation.steps.IntegrationServicesApiSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class IntegrationServicesInvalidApiTest {

    private final IntegrationServicesApiSteps apiSteps =
            new IntegrationServicesApiSteps();

    private Response response;

    @Test(priority = 1, description = "KAN-T44 შესაბამის API endpoint-ზე მოთხოვნის გაგზავნა")
    public void sendInvalidApiRequest() {
        response = apiSteps.getPage(
                INVALID_PAGE_ID,
                KA_LOCALE
        );

        apiSteps.validateStatusCode(
                response,
                STATUS_NOT_FOUND
        );
    }

    @Test(priority = 2, description = "KAN-T44 Response body-ის შემოწმება")
    public void validateResponseBody() {
        apiSteps.validateJsonResponseBody(response);
    }

    @Test(priority = 3, description = "KAN-T44 type და code ველების შემოწმება")
    public void validateTypeAndCode() {
        apiSteps.validateErrorTypeAndCode(
                response,
                OBJECT_NOT_FOUND_EXCEPTION
        );
    }

    @Test(priority = 4, description = "KAN-T44 title და status ველების შემოწმება")
    public void validateTitleAndStatus() {
        apiSteps.validateErrorTitleAndStatus(
                response,
                OBJECT_NOT_FOUND_TITLE,
                STATUS_NOT_FOUND
        );
    }

    @Test(priority = 5, description = "KAN-T44 detail ველის შემოწმება")
    public void validateDetail() {
        apiSteps.validateErrorDetail(
                response,
                INVALID_PAGE_DETAIL
        );
    }

    @Test(priority = 6, description = "KAN-T44 Response body-ის Error POJO-ში დესერიალიზაცია")
    public void deserializeErrorResponse() {
        ErrorResponse errorResponse =
                apiSteps.deserializeErrorResponse(response);

        apiSteps.validateDeserializedError(
                errorResponse,
                STATUS_NOT_FOUND,
                OBJECT_NOT_FOUND_EXCEPTION
        );
    }
}