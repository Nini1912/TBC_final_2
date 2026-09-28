package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CardPage {
    private final Page page;

    public CardPage(Page page) {
        this.page = page;
    }

    public Locator pageTitle() {
        return page.locator("h1.app-rich-text-heading-1");
    }

    public Locator mainInfoSection() {
        return page.locator("h1.app-rich-text-heading-1")
                .locator("xpath=following::*[contains(@class,'tbcx-pw-list')][1]");
    }
}