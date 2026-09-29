package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.api.models.PageResponse;
import ge.tbc.testautomation.api.models.SectionComponent;
import ge.tbc.testautomation.steps.IntegrationServicesApiSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class IntegrationServicesValidApiTest {

    private final IntegrationServicesApiSteps apiSteps =
            new IntegrationServicesApiSteps();

    @Test(description = "KAN-T43 Integration Services API response validation")
    public void validateIntegrationServicesApi() {

        Response response = apiSteps.getPage(
                INTEGRATION_SERVICES_PAGE_ID,
                KA_LOCALE
        );

        apiSteps.validateStatusCode(
                response,
                STATUS_OK
        );

        PageResponse pageResponse =
                apiSteps.deserializePageResponse(response);

        apiSteps.validateKey(
                pageResponse,
                INTEGRATION_SERVICES_KEY
        );

        apiSteps.validateSlug(
                pageResponse,
                INTEGRATION_SERVICES_SLUG
        );

        apiSteps.validateSeo(pageResponse);

        apiSteps.validateSectionComponents(pageResponse);

        SectionComponent featureGridSection =
                apiSteps.validateFeatureGridItems(pageResponse);

        apiSteps.validateSubTitle(
                featureGridSection,
                EXPECTED_FEATURE_SUBTITLE
        );
    }
}