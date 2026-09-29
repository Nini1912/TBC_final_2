package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static ge.tbc.testautomation.data.Constants.*;

public class AddressesPage {

    private final Page page;

    public final Locator branchesTab;
    public final Locator branchesTabWrapper;
    public final Locator cityDropdown;
    public final Locator selectedCity;
    public final Locator locationSearch;
    public final Locator filter247;
    public final Locator filter247Input;
    public final Locator filterOpen;
    public final Locator filterOpenInput;
    public final Locator items;
    public final Locator descriptions;
    public final Locator results;
    public final Locator emptyStateTitle;
    public final Locator skeleton;

    public AddressesPage(Page page) {
        this.page = page;

        branchesTab = page.locator("span")
                .filter(new Locator.FilterOptions().setHasText(BRANCHES_TAB_TEXT))
                .first();
        branchesTabWrapper = branchesTab.locator("xpath=..");

        cityDropdown = page.getByText(CITY_PLACEHOLDER_TEXT).first().locator("xpath=../..");
        selectedCity = page.locator(".tbcx-dropdown-selector__selection-text__slot__container");
        locationSearch = page.getByPlaceholder(LOCATION_PLACEHOLDER_TEXT);

        filter247 = filterChip(FILTER_247);
        filter247Input = filter247.locator("input.tbcx-pw-chip__input");
        filterOpen = filterChip(FILTER_OPEN);
        filterOpenInput = filterOpen.locator("input.tbcx-pw-chip__input");

        items = page.locator("div.tbcx-pw-atm-branches-section__list-item");
        descriptions = page.locator("div.tbcx-pw-atm-branches-section__list-item-description");
        results = page.locator("div.tbcx-pw-atm-branches-section__list-item-title");
        emptyStateTitle = page.locator(".tbcx-pw-atm-branches-section__empty-state-title");
        skeleton = page.locator(".tbcx-pw-atm-branches-section__list-item-skeleton-description");
    }

    private Locator filterChip(String text) {
        return page.locator("xpath=//label[.//span[normalize-space()='" + text + "']]");
    }

    public Locator cityOption(String city) {
        return page.getByText(city, new Page.GetByTextOptions().setExact(true)).first();
    }
}