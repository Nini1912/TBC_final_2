package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.api.clients.PageApi;
import ge.tbc.testautomation.api.models.ErrorResponse;
import ge.tbc.testautomation.api.models.PageResponse;
import ge.tbc.testautomation.api.models.SectionComponent;
import io.restassured.response.Response;

import static ge.tbc.testautomation.data.Constants.FEATURE_GRID_SECTION_TYPE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class IntegrationServicesApiSteps {

    private final PageApi pageApi = new PageApi();

    public Response getPage(String pageId, String locale) {
        return pageApi.getPage(pageId, locale);
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

    public PageResponse deserializePageResponse(Response response) {
        PageResponse pageResponse =
                response.as(PageResponse.class);

        assertThat(
                "Page response should be successfully deserialized",
                pageResponse,
                notNullValue()
        );

        return pageResponse;
    }

    public ErrorResponse deserializeErrorResponse(Response response) {
        ErrorResponse errorResponse =
                response.as(ErrorResponse.class);

        assertThat(
                "Error response should be successfully deserialized",
                errorResponse,
                notNullValue()
        );

        return errorResponse;
    }

    public void validateKey(
            PageResponse pageResponse,
            String expectedKey
    ) {
        assertThat(
                "Unexpected key value",
                pageResponse.getKey(),
                is(expectedKey)
        );
    }

    public void validateSlug(
            PageResponse pageResponse,
            String expectedSlug
    ) {
        assertThat(
                "Unexpected slug value",
                pageResponse.getSlug(),
                is(expectedSlug)
        );
    }

    public void validateSeo(PageResponse pageResponse) {
        assertThat(
                "SEO object should not be null",
                pageResponse.getSeo(),
                notNullValue()
        );

        assertThat(
                "SEO title should not be null",
                pageResponse.getSeo().getTitle(),
                notNullValue()
        );

        assertThat(
                "SEO title should not be empty",
                pageResponse.getSeo().getTitle(),
                not(is(emptyOrNullString()))
        );
    }

    public void validateSectionComponents(PageResponse pageResponse) {
        assertThat(
                "sectionComponents should not be null",
                pageResponse.getSectionComponents(),
                notNullValue()
        );

        assertThat(
                "sectionComponents should not be empty",
                pageResponse.getSectionComponents(),
                is(not(empty()))
        );
    }

    public SectionComponent validateFeatureGridItems(
            PageResponse pageResponse
    ) {
        SectionComponent featureGridSection =
                pageResponse.getSectionComponents()
                        .stream()
                        .filter(section ->
                                FEATURE_GRID_SECTION_TYPE.equals(
                                        section.getType()
                                )
                        )
                        .findFirst()
                        .orElseThrow(() ->
                                new AssertionError(
                                        "featureGridSection was not found"
                                )
                        );

        assertThat(
                "featureGridSection inputs should not be null",
                featureGridSection.getInputs(),
                notNullValue()
        );

        assertThat(
                "featureGridItems should not be null",
                featureGridSection
                        .getInputs()
                        .getFeatureGridItems(),
                notNullValue()
        );

        assertThat(
                "featureGridItems should not be empty",
                featureGridSection
                        .getInputs()
                        .getFeatureGridItems(),
                is(not(empty()))
        );

        return featureGridSection;
    }

    public void validateSubTitle(
            SectionComponent featureGridSection,
            String expectedSubTitle
    ) {
        String actualSubTitle =
                featureGridSection
                        .getInputs()
                        .getFeatureGridItems()
                        .get(0)
                        .getSubTitle();

        assertThat(
                "subTitle should not be null",
                actualSubTitle,
                notNullValue()
        );

        assertThat(
                "subTitle should not be empty",
                actualSubTitle,
                not(is(emptyOrNullString()))
        );

        assertThat(
                "Unexpected subTitle",
                actualSubTitle,
                is(expectedSubTitle)
        );
    }

    public void validateJsonResponseBody(Response response) {
        assertThat(
                "Response body should not be null",
                response.getBody(),
                notNullValue()
        );

        assertThat(
                "Response body should not be empty",
                response.getBody().asString(),
                not(is(emptyOrNullString()))
        );

        assertThat(
                "Response should be JSON",
                response.contentType().toLowerCase(),
                containsString("json")
        );
    }

    public void validateErrorTypeAndCode(
            Response response,
            String expectedValue
    ) {
        assertThat(
                "Unexpected error type",
                response.jsonPath().getString("type"),
                is(expectedValue)
        );

        assertThat(
                "Unexpected error code",
                response.jsonPath().getString("code"),
                is(expectedValue)
        );
    }

    public void validateErrorTitleAndStatus(
            Response response,
            String expectedTitle,
            int expectedStatus
    ) {
        assertThat(
                "Unexpected error title",
                response.jsonPath().getString("title"),
                is(expectedTitle)
        );

        assertThat(
                "Unexpected error status",
                response.jsonPath().getInt("status"),
                is(expectedStatus)
        );
    }

    public void validateErrorDetail(
            Response response,
            String expectedDetail
    ) {
        assertThat(
                "Unexpected error detail",
                response.jsonPath().getString("detail"),
                is(expectedDetail)
        );
    }

    public void validateDeserializedError(
            ErrorResponse errorResponse,
            int expectedStatus,
            String expectedCode
    ) {
        assertThat(
                "Unexpected deserialized error status",
                errorResponse.getStatus(),
                is(expectedStatus)
        );

        assertThat(
                "Unexpected deserialized error code",
                errorResponse.getCode(),
                is(expectedCode)
        );
    }
}