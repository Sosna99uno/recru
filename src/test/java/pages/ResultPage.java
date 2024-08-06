package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ResultPage {
    public final Page page;
    private final Locator resultList;
    private final Locator firstTopResult;
    private final Locator firstTopResultPrice;

    public ResultPage(Page page){
        this.page = page;
        this.resultList = page.locator("[class=\"sc-euEtCV indexes__StyledListMobile-wklrsw-111 hTuscY bQhJrc\"]");
        this.firstTopResult = resultList.locator("li").first();
        this.firstTopResultPrice = firstTopResult.locator("[data-locator='zth-price']").first();
    }

    public String getPriceFromFirstTopProduct(){
        String price = this.firstTopResultPrice.textContent();
        System.out.println(price);
        return price;
    }
    public void goInsideFirstProduct(){
        this.firstTopResult.click();
        this.page.waitForLoadState(LoadState.LOAD);
    }

}
