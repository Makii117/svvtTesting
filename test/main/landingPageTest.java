package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class landingPageTest {

    private static WebDriver webDriver;
    private static String baseUrl;

    private static String learnmoreURL = "https://features.deezer.com/transfer-playlist/";
    private static String discoverURL = "https://features.deezer.com/?_ga=2.171111136.1133185371.1642300747-995392933.1642300747&_gl=1*1ifyzmf*_ga*OTk1MzkyOTMzLjE2NDIzMDA3NDc.*_ga_71WQ7Y8JLG*MTY0MjMwMDc0Ny4xLjAuMTY0MjMwMDc0Ny4w";

    @BeforeEach
    void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        baseUrl = "https://www.deezer.com/en/ ";
        webDriver.get(baseUrl);
        Thread.sleep(2000);
        //accept cookies if not accepted
        try {
            WebElement acceptCookiesBtn;
            acceptCookiesBtn = webDriver.findElement(By.xpath("//*[@id=\"gdpr-btn-accept-all\"]"));
            acceptCookiesBtn.click();

        } catch (ElementNotVisibleException e) {
            System.out.println("Cookies already accepted");

        }
    }


    // TODO : CHECK IF NEEDED
    @Test
    void testPageName(){
        String title = webDriver.getTitle();
        assertEquals("Deezer | Listen to music | Online music streaming platform", title);
    }

    @Test
    void  testFAQ() throws InterruptedException {
        WebElement wDeezer = webDriver.findElement(By.xpath("//*[@id=\"faq\"]/ul/li[1]/details/summary/div/h3"));
        wDeezer.click();
        Thread.sleep(2000);
        // test if all FAQ Links say what is intended
        assertEquals("What is Deezer?", wDeezer.getText());
    }


    @Test
    void testDiscover() throws InterruptedException {
        Thread.sleep(1000);
        WebElement discoverLink = webDriver.findElement(By.xpath("//*[@id=\"page-homepage\"]/section[2]/a"));
        discoverLink.click();
        WebElement discoverText = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/main/section[2]/div[1]/div/h2"));

        assertEquals(discoverURL, webDriver.getCurrentUrl());
        assertEquals("Experience Flow, only on Deezer.", discoverText.getText()) ;
    }


    @Test
    void testLearnMore() throws InterruptedException {
        WebElement learnMore = webDriver.findElement(By.xpath("//*[@id=\"page-homepage\"]/section[5]/a"));
        learnMore.click();
        WebElement learnText = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/main/section[1]/div/h1"));
        Thread.sleep(1000);

        assertEquals(learnmoreURL, webDriver.getCurrentUrl());
        assertEquals("Switching to Deezer has never been easier", learnText.getText());
    }


    @Test
    void testChangeLanguage() throws InterruptedException{
        Select languageElement = new Select(webDriver.findElement(By.xpath("//*[@id=\"language_select\"]")));

        languageElement.selectByValue("hr");

        String title = webDriver.getTitle();
        Thread.sleep(3000);
        assertEquals("Deezer | Slušaj glazbu | Online glazbena streaming platforma", title);
    }


    // TODO - app download pages
    @Test
    void testAppDownloads() throws InterruptedException {
        WebElement appStore = webDriver.findElement(By.xpath("/html/body/div[3]/footer/div[1]/a[1]/img"));
        appStore.click();
        Thread.sleep(1000);
        WebElement appStoreHeader = webDriver.findElement(By.xpath("/html/body/div[5]/main/div[2]/section[1]/div/div[2]/header/h1"));
        assertEquals("Deezer: Music & Podcast Player 12+",appStoreHeader.getText());
        Thread.sleep(1000);
        webDriver.navigate().back();
        webDriver.navigate().back();

        Thread.sleep(1000);
        WebElement googlePlay = webDriver.findElement(By.xpath("/html/body/div[3]/footer/div[1]/a[2]/img"));
        googlePlay.click();
        Thread.sleep(1000);
        WebElement googlePlayHeader = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/c-wiz/div/div[2]/div/div/main/c-wiz[1]/c-wiz[1]/div/div[2]/div/div[1]/c-wiz[1]/h1/span"));
        assertEquals("Deezer: Music & Podcast Player",googlePlayHeader.getText());

    }

    @AfterEach
    void tearDown() {
        webDriver.close();
    }

}
