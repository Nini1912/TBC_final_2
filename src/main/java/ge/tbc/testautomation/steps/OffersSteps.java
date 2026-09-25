package ge.tbc.testautomation.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import ge.tbc.testautomation.pages.OffersPage;

import java.util.ArrayList;
import java.util.List;

public class OffersSteps {

    private static final String OFFERS_API_URL =
            "https://apigw.tbcbank.ge/api/v1/marketing/entries/offer";

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
        offersPage.allOffersLink().click();
        return this;
    }

    public OffersSteps selectShoppingCategory() {
        offersPage.categoryFilter().click();
        offersPage.shoppingFilter().click();
        return this;
    }

    public OffersSteps selectShoppingFilterAndCaptureResponse() {
        shoppingFilterResponse = page.waitForResponse(
                response -> response.url().equals(OFFERS_API_URL)
                        && response.request().method().equals("POST"),
                () -> selectShoppingCategory()
        );

        return this;
    }

    public OffersSteps verifyRequestEndpoint() {
        if (shoppingFilterResponse == null) {
            throw new AssertionError("Shopping filter response was not captured.");
        }

        if (!shoppingFilterResponse.url().equals(OFFERS_API_URL)) {
            throw new AssertionError(
                    "Unexpected endpoint: " + shoppingFilterResponse.url()
            );
        }

        return this;
    }

    public OffersSteps verifyRequestMethod() {
        if (shoppingFilterResponse == null) {
            throw new AssertionError("Shopping filter response was not captured.");
        }

        String method = shoppingFilterResponse.request().method();

        if (!method.equals("POST")) {
            throw new AssertionError(
                    "Unexpected HTTP method: " + method
            );
        }

        return this;
    }

    public OffersSteps verifyResponseStatusCode() {
        if (shoppingFilterResponse == null) {
            throw new AssertionError("Shopping filter response was not captured.");
        }

        if (shoppingFilterResponse.status() != 200) {
            throw new AssertionError(
                    "Unexpected response status code: "
                            + shoppingFilterResponse.status()
            );
        }

        return this;
    }

    public OffersSteps verifyShoppingFilterParameter() {
        if (shoppingFilterResponse == null) {
            throw new AssertionError("Shopping filter response was not captured.");
        }

        String requestBody = shoppingFilterResponse.request().postData();

        if (requestBody == null || !requestBody.contains("Category:Shopping")) {
            throw new AssertionError(
                    "Request does not contain Category:Shopping. Request body: "
                            + requestBody
            );
        }

        return this;
    }

    public OffersSteps verifyDisplayedOffersAgainstResponse() {
        if (shoppingFilterResponse == null) {
            throw new AssertionError("Shopping filter response was not captured.");
        }

        List<String> responseTitles = getResponseOfferTitles();
        List<String> displayedTitles = getDisplayedOfferTitles();

        if (responseTitles.isEmpty()) {
            throw new AssertionError(
                    "The API response contains no offers."
            );
        }

        if (displayedTitles.isEmpty()) {
            throw new AssertionError(
                    "No offers are displayed in the UI."
            );
        }

        if (!responseTitles.equals(displayedTitles)) {
            throw new AssertionError(
                    "Displayed offers do not match the offers returned by the API."
                            + "\nAPI offers: " + responseTitles
                            + "\nUI offers: " + displayedTitles
            );
        }

        return this;
    }

    public List<String> getDisplayedOfferTitles() {
        return normalizeTitles(
                offersPage.offerTitles().allTextContents()
        );
    }

    private List<String> getResponseOfferTitles() {
        try {
            JsonNode responseJson =
                    objectMapper.readTree(shoppingFilterResponse.text());

            JsonNode list = responseJson.get("list");

            if (list == null || !list.isArray()) {
                throw new AssertionError(
                        "Response does not contain a valid 'list' array."
                );
            }

            List<String> titles = new ArrayList<>();

            for (JsonNode offer : list) {
                JsonNode title = offer.get("title");

                if (title != null && !title.asText().isBlank()) {
                    titles.add(title.asText().trim());
                }
            }

            return titles;
        } catch (Exception e) {
            throw new AssertionError(
                    "Failed to parse offers response: " + e.getMessage(),
                    e
            );
        }
    }

    private List<String> normalizeTitles(List<String> titles) {
        return titles.stream()
                .map(String::trim)
                .filter(title -> !title.isBlank())
                .toList();
    }
}