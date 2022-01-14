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

        assertEquals("Experience Flow, only on Deezer.", discoverText.getText()) ;
    }


    @Test
    void testLearnMore() throws InterruptedException {
        WebElement learnMore = webDriver.findElement(By.xpath("//*[@id=\"page-homepage\"]/section[5]/a"));
        learnMore.click();
        WebElement learnText = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/main/section[1]/div/h1"));
        Thread.sleep(1000);

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


    // TODO - FIGURE OUT HOW TO TEST MULTIPLE LINKS AT ONCE
    @Test
    void testSocials(){

    }

    @AfterEach
    void tearDown() {
        webDriver.close();
    }

}
