package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {
    public final Page page;
    private final Locator zthPriceRightColumn;
    public CartPage(Page page){
        this.page = page;
        this.zthPriceRightColumn = page.locator("div.indexes__RightColumn2-oz50c-19.idzYVM [data-locator='zth-price']");
    }
    public String getPriceValue(){
        String priceValue = this.zthPriceRightColumn.textContent();
        System.out.println(priceValue);
        return priceValue;
    }
}
