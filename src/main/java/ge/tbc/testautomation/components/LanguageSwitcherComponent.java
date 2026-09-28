package ge.tbc.testautomation.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LanguageSwitcherComponent {
    Page page;
    public Locator dropdownTrigger;
    public Locator htmlTag;

    public LanguageSwitcherComponent(Page page) {
        this.page = page;
        dropdownTrigger = page.getByRole(AriaRole.BANNER).locator(".tbcx-language-select__field");
        htmlTag = page.locator("html");
    }

    public String getCurrentLanguage() {
        return htmlTag.getAttribute("lang");
    }

    public void switchTo(String localeCode) {
        if (!localeCode.equalsIgnoreCase(getCurrentLanguage())) {
            dropdownTrigger.click();
            assertThat(htmlTag).hasAttribute("lang", Pattern.compile(localeCode, Pattern.CASE_INSENSITIVE));
            page.waitForLoadState(LoadState.NETWORKIDLE);
        }
    }
}