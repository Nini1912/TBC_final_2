package ge.tbc.testautomation.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.pages.OffersPage;

import java.util.ArrayList;
import java.util.List;

import static ge.tbc.testautomation.data.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OffersSteps {

    private final Page page;
    private final OffersPage offersPage;
    private final ObjectMapper objectMapper;

    private Response shoppingFilterResponse;

    public OffersSteps(Page page) {
        this.page = page;
        this.offersPage = new OffersPage(page);
        this.objectMapper = new ObjectMapper();
    }

    public OffersSteps navigateToAllOffers() {
        offersPage.allOffersLink.click();

        return this;
    }

    public OffersSteps openCategoryFilter() {
        offersPage.categoryFilter.click();

        PlaywrightAssertions
                .assertThat(offersPage.shoppingCheckbox)
                .not()
                .isChecked();

        return this;
    }

    public OffersSteps selectShoppingFilterAndCaptureResponse() {
        openCategoryFilter();

        shoppingFilterResponse = page.waitForResponse(
                response ->
                        response.url().equals(OFFERS_API_URL)
                                && response.request()
                                .method()
                                .equals(POST_METHOD),
                () -> offersPage.shoppingFilterItem
                        .locator("button.filter-item")
                        .click()
        );

        PlaywrightAssertions
                .assertThat(offersPage.shoppingCheckbox)
                .isChecked();

        validateCapturedResponse();

        return this;
    }

    public OffersSteps verifyRequestEndpoint() {
        validateCapturedResponse();

        assertThat(
                "Unexpected offers API endpoint",
                shoppingFilterResponse.url(),
                is(OFFERS_API_URL)
        );

        return this;
    }

    public OffersSteps verifyRequestMethod() {
        validateCapturedResponse();

        assertThat(
                "Unexpected HTTP method",
                shoppingFilterResponse.request().method(),
                is(POST_METHOD)
        );

        return this;
    }

    public OffersSteps verifyResponseStatusCode() {
        validateCapturedResponse();

        assertThat(
                "Unexpected response status code",
                shoppingFilterResponse.status(),
                is(STATUS_OK)
        );

        return this;
    }

    public OffersSteps verifyShoppingFilterParameter() {
        validateCapturedResponse();

        String requestBody =
                shoppingFilterResponse.request().postData();

        assertThat(
                "Shopping filter request body should not be null",
                requestBody,
                notNullValue()
        );

        try {
            JsonNode requestJson =
                    objectMapper.readTree(requestBody);

            JsonNode filterNode =
                    requestJson.get("filter");

            assertThat(
                    "Request body does not contain 'filter'",
                    filterNode,
                    notNullValue()
            );

            assertThat(
                    "Request 'filter' should be an array",
                    filterNode.isArray(),
                    is(true)
            );

            List<String> filters = new ArrayList<>();

            for (JsonNode filter : filterNode) {
                filters.add(filter.asText());
            }

            assertThat(
                    "Request filter does not contain expected value",
                    filters,
                    hasItem(SHOPPING_FILTER_PARAMETER)
            );

        } catch (Exception e) {
            throw new AssertionError(
                    "Failed to parse shopping filter request body: "
                            + requestBody,
                    e
            );
        }

        return this;
    }

    public OffersSteps verifyDisplayedOffersAgainstResponse() {
        validateCapturedResponse();

        List<String> responseTitles =
                getResponseOfferTitles();

        assertThat(
                "The API response contains no offers",
                responseTitles,
                is(not(empty()))
        );

        PlaywrightAssertions
                .assertThat(offersPage.offerTitles)
                .hasText(
                        responseTitles.toArray(new String[0])
                );

        return this;
    }

    private List<String> getResponseOfferTitles() {
        validateCapturedResponse();

        try {
            JsonNode responseJson =
                    objectMapper.readTree(
                            shoppingFilterResponse.text()
                    );

            JsonNode list =
                    responseJson.get("list");

            assertThat(
                    "Response does not contain 'list'",
                    list,
                    notNullValue()
            );

            assertThat(
                    "Response 'list' should be an array",
                    list.isArray(),
                    is(true)
            );

            List<String> titles =
                    new ArrayList<>();

            for (JsonNode offer : list) {
                JsonNode title =
                        offer.get("title");

                if (title != null
                        && !title.asText().isBlank()) {

                    titles.add(
                            title.asText().trim()
                    );
                }
            }

            return titles;

        } catch (Exception e) {
            throw new AssertionError(
                    "Failed to parse offers response: "
                            + e.getMessage(),
                    e
            );
        }
    }

    private void validateCapturedResponse() {
        assertThat(
                "Shopping filter response was not captured",
                shoppingFilterResponse,
                notNullValue()
        );
    }
}