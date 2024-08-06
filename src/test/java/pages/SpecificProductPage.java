package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class SpecificProductPage {
    public final Page page;
    private final Locator addToCart;
    private final Locator firstPrice;
    private final Locator firstPriceValue;
    private final Locator popup;
    public SpecificProductPage(Page page){
        this.page = page;
        this.addToCart = page.locator("[data-locator=\"ppa-payment__btn\"]").first();
        this.firstPrice = page.locator("[data-locator=\"ppa-payment\"]");
        this.firstPriceValue = firstPrice.locator("[data-locator=\"zth-price\"]").first();
        this.popup = page.locator("[data-test-id=\"primary-button\"]");
    }
    public void addItemToCart(){
        this.addToCart.click();
        this.page.waitForLoadState(LoadState.LOAD);
        if (this.popup.isVisible()){
            this.popup.click();
        }
    }
    public String getHigherPrice(){
        String higherPrice = this.firstPriceValue.textContent();
        System.out.println(higherPrice);
        return higherPrice;
    }
}
