package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CardPage {
    public Locator pageTitle;
    public Locator mainInfoSection;

    public CardPage(Page page) {
        pageTitle = page.locator("h1.app-rich-text-heading-1");
        mainInfoSection = page.locator("h1.app-rich-text-heading-1")
                .locator("xpath=following::*[contains(@class,'tbcx-pw-list')][1]");
    }
}
