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

    private Response response;
    private PageResponse pageResponse;
    private SectionComponent featureGridSection;

    @Test(priority = 1, description = "KAN-T43 შესაბამის API endpoint-ზე მოთხოვნის გაგზავნა")
    public void sendValidApiRequest() {
        response = apiSteps.getPage(
                INTEGRATION_SERVICES_PAGE_ID,
                KA_LOCALE
        );

        apiSteps.validateStatusCode(
                response,
                STATUS_OK
        );
    }

    @Test(priority = 2, description = "KAN-T43 Response body-ის Java POJO-ში დესერიალიზაცია")
    public void deserializeResponseBody() {
        pageResponse =
                apiSteps.deserializePageResponse(response);
    }

    @Test(priority = 3, description = "KAN-T43 Response-ის key ველის შემოწმება")
    public void validateKey() {
        apiSteps.validateKey(
                pageResponse,
                INTEGRATION_SERVICES_KEY
        );
    }

    @Test(priority = 4, description = "KAN-T43 Response-ის slug ველის შემოწმება")
    public void validateSlug() {
        apiSteps.validateSlug(
                pageResponse,
                INTEGRATION_SERVICES_SLUG
        );
    }

    @Test(priority = 5, description = "KAN-T43 Nested seo ობიექტის შემოწმება")
    public void validateSeo() {
        apiSteps.validateSeo(pageResponse);
    }

    @Test(priority = 6, description = "KAN-T43 sectionComponents სიის შემოწმება")
    public void validateSectionComponents() {
        apiSteps.validateSectionComponents(pageResponse);
    }

    @Test(priority = 7, description = "KAN-T43 featureGridSection და featureGridItems სიის შემოწმება")
    public void validateFeatureGridItems() {
        featureGridSection =
                apiSteps.validateFeatureGridItems(pageResponse);
    }

    @Test(priority = 8, description = "KAN-T43 featureGridItems subTitle-ის შემოწმება")
    public void validateFeatureGridItemSubTitle() {
        apiSteps.validateSubTitle(
                featureGridSection,
                EXPECTED_FEATURE_SUBTITLE
        );
    }
}