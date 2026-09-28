package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.api.clients.PageApi;
import ge.tbc.testautomation.api.models.ErrorResponse;
import ge.tbc.testautomation.api.models.PageResponse;
import ge.tbc.testautomation.api.models.SectionComponent;
import io.restassured.response.Response;
import org.testng.Assert;

import static ge.tbc.testautomation.data.Constants.FEATURE_GRID_SECTION_TYPE;

public class IntegrationServicesApiSteps {

    private final PageApi pageApi = new PageApi();

    public Response getPage(String pageId, String locale) {
        return pageApi.getPage(pageId, locale);
    }

    public void validateStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(
                response.statusCode(),
                expectedStatusCode,
                "Unexpected HTTP status code"
        );
    }

    public PageResponse deserializePageResponse(Response response) {
        PageResponse pageResponse = response.as(PageResponse.class);

        Assert.assertNotNull(
                pageResponse,
                "Page response should be successfully deserialized"
        );

        return pageResponse;
    }

    public ErrorResponse deserializeErrorResponse(Response response) {
        ErrorResponse errorResponse = response.as(ErrorResponse.class);

        Assert.assertNotNull(
                errorResponse,
                "Error response should be successfully deserialized"
        );

        return errorResponse;
    }

    public void validateKey(PageResponse pageResponse, String expectedKey) {
        Assert.assertEquals(
                pageResponse.getKey(),
                expectedKey,
                "Unexpected key value"
        );
    }

    public void validateSlug(PageResponse pageResponse, String expectedSlug) {
        Assert.assertEquals(
                pageResponse.getSlug(),
                expectedSlug,
                "Unexpected slug value"
        );
    }

    public void validateSeo(PageResponse pageResponse) {
        Assert.assertNotNull(
                pageResponse.getSeo(),
                "SEO object should not be null"
        );

        Assert.assertNotNull(
                pageResponse.getSeo().getTitle(),
                "SEO title should not be null"
        );

        Assert.assertFalse(
                pageResponse.getSeo().getTitle().isBlank(),
                "SEO title should not be empty"
        );
    }

    public void validateSectionComponents(PageResponse pageResponse) {
        Assert.assertNotNull(
                pageResponse.getSectionComponents(),
                "sectionComponents should not be null"
        );

        Assert.assertFalse(
                pageResponse.getSectionComponents().isEmpty(),
                "sectionComponents should not be empty"
        );
    }

    public SectionComponent validateFeatureGridItems(PageResponse pageResponse) {
        SectionComponent featureGridSection =
                pageResponse.getSectionComponents()
                        .stream()
                        .filter(section ->
                                FEATURE_GRID_SECTION_TYPE.equals(section.getType()))
                        .findFirst()
                        .orElseThrow(() ->
                                new AssertionError(
                                        "featureGridSection was not found"
                                )
                        );

        Assert.assertNotNull(
                featureGridSection.getInputs(),
                "featureGridSection inputs should not be null"
        );

        Assert.assertNotNull(
                featureGridSection.getInputs().getFeatureGridItems(),
                "featureGridItems should not be null"
        );

        Assert.assertFalse(
                featureGridSection.getInputs()
                        .getFeatureGridItems()
                        .isEmpty(),
                "featureGridItems should not be empty"
        );

        return featureGridSection;
    }

    public void validateSubTitle(
            SectionComponent featureGridSection,
            String expectedSubTitle
    ) {
        String actualSubTitle = featureGridSection
                .getInputs()
                .getFeatureGridItems()
                .get(0)
                .getSubTitle();

        Assert.assertNotNull(
                actualSubTitle,
                "subTitle should not be null"
        );

        Assert.assertFalse(
                actualSubTitle.isBlank(),
                "subTitle should not be empty"
        );

        Assert.assertEquals(
                actualSubTitle,
                expectedSubTitle,
                "Unexpected subTitle"
        );
    }

    public void validateJsonResponseBody(Response response) {
        Assert.assertNotNull(
                response.getBody(),
                "Response body should not be null"
        );

        Assert.assertFalse(
                response.getBody().asString().isBlank(),
                "Response body should not be empty"
        );

        Assert.assertTrue(
                response.contentType().contains("json"),
                "Response should be JSON"
        );
    }

    public void validateErrorTypeAndCode(
            Response response,
            String expectedValue
    ) {
        Assert.assertEquals(
                response.jsonPath().getString("type"),
                expectedValue,
                "Unexpected error type"
        );

        Assert.assertEquals(
                response.jsonPath().getString("code"),
                expectedValue,
                "Unexpected error code"
        );
    }

    public void validateErrorTitleAndStatus(
            Response response,
            String expectedTitle,
            int expectedStatus
    ) {
        Assert.assertEquals(
                response.jsonPath().getString("title"),
                expectedTitle,
                "Unexpected error title"
        );

        Assert.assertEquals(
                response.jsonPath().getInt("status"),
                expectedStatus,
                "Unexpected error status"
        );
    }

    public void validateErrorDetail(
            Response response,
            String expectedDetail
    ) {
        Assert.assertEquals(
                response.jsonPath().getString("detail"),
                expectedDetail,
                "Unexpected error detail"
        );
    }

    public void validateDeserializedError(
            ErrorResponse errorResponse,
            int expectedStatus,
            String expectedCode
    ) {
        Assert.assertEquals(
                errorResponse.getStatus(),
                expectedStatus,
                "Unexpected deserialized error status"
        );

        Assert.assertEquals(
                errorResponse.getCode(),
                expectedCode,
                "Unexpected deserialized error code"
        );
    }
}