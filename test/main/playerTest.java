package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;


public class playerTest {
    private static WebDriver webDriver;
    private static String baseUrl;
    // data
    private String Email="steel-fifty-32@inboxkitten.com";
    private String uName="steel-fifty-32";
    private String Password = "SvvtTestingAccount1";

    @BeforeEach
    void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
        baseUrl="https://www.deezer.com/en/ ";
        webDriver.get(baseUrl);
        Thread.sleep(6000);
        //accept cookies if not accepted
        try{
            WebElement acceptCookiesBtn;
            acceptCookiesBtn = webDriver.findElement(By.xpath("//*[@id=\"gdpr-btn-accept-all\"]"));
            acceptCookiesBtn.click();

        }catch (ElementNotVisibleException e){
            System.out.println("Cookies already accepted");

        }

        WebElement loginButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/a"));
        loginButton.click();

        // form
        WebElement emailField = webDriver.findElement(By.xpath("//*[@id=\"login_mail\"]"));
        emailField.sendKeys("steel-fifty-32@inboxkitten.com");
        WebElement passwordField = webDriver.findElement(By.xpath("//*[@id=\"login_password\"]"));
        passwordField.sendKeys("SvvtTestingAccount1");
        Thread.sleep(2000);

        WebElement loginBtn = webDriver.findElement(By.id("login_form_submit"));
        loginBtn.click();
        // in case of captcha, which can happen it is left to 20000 so we can click through it
        Thread.sleep(20000);
    }


    @AfterEach
    void teardown(){
        webDriver.close();
    }

    // fails
    @Test
    void testDailyPlayer() throws InterruptedException {
        WebElement dailyPlayButton = webDriver.findElement(By.xpath("//*[@id=\"page_content\"]/div[4]/div[1]/div[2]/section[9]/div/div[2]/div/div/ul/li[1]/figure/ul/li[1]/button"));
        dailyPlayButton.click();
        Thread.sleep(20000);

    }


    @Test
    void testPlayPodcast() throws InterruptedException{
        WebElement searchbox = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div/form/input"));
        searchbox.sendKeys("Coding");
        webDriver.findElement(By.xpath("//*[@id=\"page_topbar\"]/div[1]/div/form/button[1]")).click();
        Thread.sleep(2000);

        WebElement firstSong = webDriver.findElement(By.xpath("//*[@id=\"page_naboo_search\"]/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div[1]/div[2]/button"));
        firstSong.click();
        assertEquals("PODCAST", webDriver.findElement(By.xpath("//*[@id=\"page_naboo_search\"]/div[2]/div[2]/div/div[1]/div[2]/button/span[1]")).getText());
        Thread.sleep(20000);
    }

    @Test
    void testPlayerCommands() throws InterruptedException{
        WebElement searchbox = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div/form/input"));
        searchbox.sendKeys("The Weeknd");
        webDriver.findElement(By.xpath("//*[@id=\"page_topbar\"]/div[1]/div/form/button[1]")).click();
        Thread.sleep(2000);

        WebElement firstSong = webDriver.findElement(By.xpath("//*[@id=\"page_naboo_search\"]/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div[1]/div[2]/button"));
        firstSong.click();


        Thread.sleep(2000);
        WebElement songName = webDriver.findElement(By.className("track-link"));

        assertEquals("The Hills", songName.getText());
        WebElement viewLyrics = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div/div[2]/div/div[1]/div[3]/ul/li[1]/button"));
        viewLyrics.click();

        WebElement skip = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div[1]/div[1]/ul/li[5]/div/button"));
        skip.click();
        assertNotEquals("Blinding Lights", songName.getText());
        WebElement pause = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div[1]/div[1]/ul/li[3]/button"));
        pause.click();
        Thread.sleep(20000);

    }



}
