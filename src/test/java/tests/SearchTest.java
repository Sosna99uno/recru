package tests;

import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CartPage;
import pages.MainPage;
import pages.ResultPage;
import pages.SpecificProductPage;

import java.util.stream.Stream;

public class SearchTest extends TestBaseClass {


    static Stream<String> itemName() {
        String item = System.getProperty("itemName", "witcher3"); // default value if not set
        return Stream.of(item);
    }


    @ParameterizedTest
    @Description("TestForRecrutation")
    @MethodSource("itemName")
    public void searchForItemAndMatchPrices(String name) {
        MainPage mainPage = new MainPage(page);
        mainPage.goTo();
        mainPage.searchForItem(name);
        ResultPage resultPage = new ResultPage(page);
        takeScreenshot("resultPage");
        resultPage.goInsideFirstProduct();
        SpecificProductPage specificProductPage = new SpecificProductPage(page);
        takeScreenshot("productSite");
        String itemPriceOnProductSite = specificProductPage.getHigherPrice();
        specificProductPage.addItemToCart();
        CartPage cartPage = new CartPage(page);
        String itemPriceInCart = cartPage.getPriceValue();
        takeScreenshot("cart");
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(itemPriceOnProductSite).isEqualTo(itemPriceInCart);
        softly.assertAll();
    }

}
