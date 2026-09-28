package ge.tbc.testautomation.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import ge.tbc.testautomation.pages.OffersPage;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static ge.tbc.testautomation.data.Constants.*;

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

        Assert.assertTrue(
                offersPage.shoppingCheckbox.isChecked(),
                "Shopping filter should be checked after selection"
        );

        Assert.assertNotNull(
                shoppingFilterResponse,
                "Shopping filter response was not captured"
        );

        return this;
    }

    public OffersSteps verifyRequestEndpoint() {
        validateCapturedResponse();

        Assert.assertEquals(
                shoppingFilterResponse.url(),
                OFFERS_API_URL,
                "Unexpected offers API endpoint"
        );

        return this;
    }

    public OffersSteps verifyRequestMethod() {
        validateCapturedResponse();

        Assert.assertEquals(
                shoppingFilterResponse.request().method(),
                POST_METHOD,
                "Unexpected HTTP method"
        );

        return this;
    }

    public OffersSteps verifyResponseStatusCode() {
        validateCapturedResponse();

        Assert.assertEquals(
                shoppingFilterResponse.status(),
                STATUS_OK,
                "Unexpected response status code"
        );

        return this;
    }

    public OffersSteps verifyShoppingFilterParameter() {
        validateCapturedResponse();

        String requestBody =
                shoppingFilterResponse.request().postData();

        Assert.assertNotNull(
                requestBody,
                "Shopping filter request body should not be null"
        );

        try {
            JsonNode requestJson =
                    objectMapper.readTree(requestBody);

            JsonNode filterNode =
                    requestJson.get("filter");

            Assert.assertNotNull(
                    filterNode,
                    "Request body does not contain 'filter'"
            );

            Assert.assertTrue(
                    filterNode.isArray(),
                    "Request 'filter' should be an array"
            );

            boolean containsShoppingFilter = false;

            for (JsonNode filter : filterNode) {
                if (SHOPPING_FILTER_PARAMETER.equals(filter.asText())) {
                    containsShoppingFilter = true;
                    break;
                }
            }

            Assert.assertTrue(
                    containsShoppingFilter,
                    "Request filter does not contain expected value: "
                            + SHOPPING_FILTER_PARAMETER
                            + ". Actual filter: "
                            + filterNode
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

        List<String> displayedTitles =
                getDisplayedOfferTitles();

        Assert.assertFalse(
                responseTitles.isEmpty(),
                "The API response contains no offers"
        );

        Assert.assertFalse(
                displayedTitles.isEmpty(),
                "No offers are displayed in the UI"
        );

        Assert.assertEquals(
                displayedTitles,
                responseTitles,
                "Displayed offers do not match offers returned by the API"
        );

        return this;
    }

    public List<String> getDisplayedOfferTitles() {
        return normalizeTitles(
                offersPage.offerTitles.allTextContents()
        );
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

            Assert.assertNotNull(
                    list,
                    "Response does not contain 'list'"
            );

            Assert.assertTrue(
                    list.isArray(),
                    "Response 'list' should be an array"
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

    private List<String> normalizeTitles(
            List<String> titles
    ) {
        return titles.stream()
                .map(String::trim)
                .filter(title -> !title.isBlank())
                .toList();
    }

    private void validateCapturedResponse() {
        Assert.assertNotNull(
                shoppingFilterResponse,
                "Shopping filter response was not captured"
        );
    }

    public OffersSteps openCategoryFilter() {
        offersPage.categoryFilter.click();
        offersPage.shoppingCheckbox.waitFor();

        Assert.assertFalse(
                offersPage.shoppingCheckbox.isChecked(),
                "Shopping filter should initially be unchecked"
        );

        return this;
    }
}