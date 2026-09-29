package ge.tbc.testautomation.data;

public class Constants {
    public static final String BASE_URL = "https://www.tbcbank.ge/";
    public static final String OFFERS = "შეთავაზებები";
    public static final String LOANS_MENU_TEXT = "სესხები";
    public static final String LOAN_REQUEST_BUTTON_TEXT = "სესხის მოთხოვნა";
    public static final String LOAN_ERROR_MESSAGE = "გამოთვლილი ყოველთვიური შენატანი არ ემთხვევა მოსალოდნელს";
    public static final String EUR = "EUR" ;
    public static final String GEL = "GEL" ;
    public static final String INVALID_CURRENCY_INPUT = "-2e0" ;
    public static final String VALID_CURRENCY_INPUT = "200" ;
    public static final String CORRECTED_CURRENCY_INPUT = "20" ;
    public static final String CURRENCY_ERROR_MESSAGE = "კონვერტაციის შედეგად მიღებული თანხა არასწორია";
    public static final String CURRENCY_RATES_TEXT = "ვალუტის კურსები";
    public static final String CARDS_MENU_TEXT = "ბარათები";
    public static final String LOYALTY_CREDIT_CARD_NAME = "ერთგული საკრედიტო ბარათი";

    public static final String USD = "USD";

    public static final String EXCHANGE_RATE_ENDPOINT =
            "/exchangeRates/getExchangeRate";

    public static final String EXCHANGE_RATE_UI_ERROR_MESSAGE =
            "UI exchange rate does not match API buy rate";

    public static final String CONVERTED_AMOUNT_API_ERROR_MESSAGE =
            "Converted amount does not match amount calculated using API buy rate";

    public static final int STATUS_OK = 200;

    public static final String API_BASE_URL =
            "https://apigw.tbcbank.ge";

    public static final String API_BASE_PATH =
            "/api/v1";

    public static final String PAGE_ENDPOINT =
            "/sites/pages/{pageId}";

    public static final String INTEGRATION_SERVICES_PAGE_ID =
            "3P0Fh8hmprOuqPd3QlufhV";

    public static final String INVALID_PAGE_ID =
            "invalid-page-id";

    public static final String KA_LOCALE =
            "ka-GE";

    public static final String INTEGRATION_SERVICES_KEY =
            "site-business-digital-services-integration-services";

    public static final String INTEGRATION_SERVICES_SLUG =
            "/business/digital-services/integration-services";

    public static final String FEATURE_GRID_SECTION_TYPE =
            "featureGridSection";

    public static final String EXPECTED_FEATURE_SUBTITLE =
            "სხვადასხვა ტიპის გადარიცხვა თქვენი საბუღალტრო პროგრამიდან";

    public static final String OBJECT_NOT_FOUND_EXCEPTION =
            "ObjectNotFoundException";

    public static final String OBJECT_NOT_FOUND_TITLE =
            "Object not found";

    public static final String INVALID_PAGE_DETAIL =
            "Page:invalid-page-id not found";

    public static final int STATUS_NOT_FOUND = 404;

    public static final String OFFERS_API_URL =
            "https://apigw.tbcbank.ge/api/v1/marketing/entries/offer";

    public static final String POST_METHOD = "POST";

    public static final String SHOPPING_FILTER_PARAMETER =
            "Category:Shopping";

    public static final String ALL_OFFERS_TEXT =
            "ყველა შეთავაზება";

    public static final String CATEGORY_TEXT =
            "კატეგორია";

    public static final String SHOPPING_TEXT =
            "შოპინგი";

    public static final String LOYALTY_CREDIT_CARD_MAIN_INFO =
            "ეფექტური საპროცენტო განაკვეთი საშეღავათო პერიოდით 0%-დან";

    public static final String DEPOSITS_TEXT =
            "ანაბრები";

    public static final String TERM_DEPOSIT_NAME =
            "ვადიანი ანაბარი";

    public static final String TERM_DEPOSIT_URL_PART =
            "/ka/deposits/term-deposit";

    public static final String DEPOSIT_CONDITIONS_TEXT = "პირობები";

    public static final String DEPOSIT_INTEREST_RATE_TEXT =
            "საპროცენტო განაკვეთი";

    public static final String FIXED_INTEREST_RATE_TEXT =
            "ფიქსირებული";

    public static final String DEPOSITS_URL_PART = "/ka/deposits";
    public static final String ADDRESSES_TEXT = "მისამართები";
    public static final String BRANCHES_TAB_TEXT = "ფილიალები";
    public static final String CITY_PLACEHOLDER_TEXT = "აირჩიე ქალაქი";
    public static final String LOCATION_PLACEHOLDER_TEXT = "მიუთითე სასურველი ლოკაცია";
    public static final String VALID_CITY = "თბილისი";
    public static final String VALID_LOCATION = "პეკინი";
    public static final String FILTER_247 = "24/7";
    public static final String FILTER_OPEN = "ღიაა";
    public static final String TAB_ACTIVE_COLOR = "0, 173, 238";
    public static final String BRANCHES_TAB_INACTIVE_ERROR_MESSAGE = "ფილიალების ტაბი აქტიური არ არის. ";
}
