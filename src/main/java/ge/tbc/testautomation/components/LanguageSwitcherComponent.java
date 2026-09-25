package ge.tbc.testautomation.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LanguageSwitcherComponent {
    private final Page page;

    public LanguageSwitcherComponent(Page page) {
        this.page = page;
    }

    private Locator headerScope() {
        return page.getByRole(AriaRole.BANNER);
    }

    private Locator dropdownTrigger() {
        return headerScope().locator(".tbcx-language-select__field");
    }

    private Locator selectedLanguageLabel() {
        return headerScope().locator(".tbcx-language-select__selected");
    }

    public String getCurrentLanguage() {
        String label = selectedLanguageLabel().textContent().trim();
        return label.equalsIgnoreCase("EN") ? "ka" : "en";
    }

    public void switchTo(String localeCode) {
        if (!localeCode.equalsIgnoreCase(getCurrentLanguage())) {
            String labelBeforeClick = selectedLanguageLabel().textContent().trim();
            dropdownTrigger().click();
            assertThat(selectedLanguageLabel()).not().hasText(labelBeforeClick);
        }
    }
}