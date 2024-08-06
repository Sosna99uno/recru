package tests;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class TestBaseClass implements TestWatcher {
    static Playwright playwright;
    static Browser browser;
    static BrowserContext browserContext;
    static Page page;

    @BeforeAll
    public static void setUp() {

        List<String> arguments = new ArrayList<>();
        arguments.add("--disable-blink-features=AutomationControlled");
        arguments.add("--disable-infobars");
        arguments.add("--disable-popup-blocking");
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(arguments));

    }

    @BeforeEach
    public void beforeEachTest(){
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterEach
    public void afterEachTest(){
        browserContext.clearCookies();
        browserContext.close();
        page.close();
    }


    @AfterAll
    public static void tearDown(){
        page.close();
        browserContext.close();
        browser.close();
        playwright.close();
    }

    public void takeScreenshot(String name) {
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions());
        Allure.addAttachment(name, "image/png", new ByteArrayInputStream(screenshot), ".png");
    }
}
