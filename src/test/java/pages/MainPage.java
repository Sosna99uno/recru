package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class MainPage {
     public final Page page;
     public final Locator cookies;
    public final Locator searchInput;
    public final Locator searchIcon;

    public MainPage(Page page){
        this.page = page;
        this.cookies = page.locator("[data-test-id=\"cookie-accept-all-btn\"]");
        this.searchInput = page.locator("input[type=\"search\"]");
        this.searchIcon = page.locator("[class=\"search_icon\"]");
    }

    public void goTo(){
        this.page.navigate("https://www.g2a.com/pl");
    }

    public void acceptCookies(){
        if (this.cookies.isVisible()){
            this.cookies.click();
        }else {
            System.out.println("No cookies to accept");
        }
    }

    public void searchForItem(String item){
        this.searchInput.fill(item);
        this.searchIcon.click();
        this.page.waitForLoadState(LoadState.LOAD);
    }
}
