package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static ge.tbc.testautomation.data.Constants.GET_CARD_BUTTON_TEXT;

public class TbcCardPage {

    public final Locator getCardButton;
    public final Locator qrPopup;

    public TbcCardPage(Page page) {
        getCardButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(GET_CARD_BUTTON_TEXT)).first();
        qrPopup = page.locator(".tbcx-pw-app-download-banner-popup__qr");
    }
}