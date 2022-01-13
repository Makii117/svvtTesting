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

class loginRegisterTest {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeEach
    void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
        baseUrl="https://www.deezer.com/en/ ";
        webDriver.get(baseUrl);
        Thread.sleep(2000);
        //accept cookies if not accepted
        try{
            WebElement acceptCookiesBtn;
            acceptCookiesBtn = webDriver.findElement(By.xpath("//*[@id=\"gdpr-btn-accept-all\"]"));
            acceptCookiesBtn.click();

        }catch (ElementNotVisibleException e){
            System.out.println("Cookies already accepted");

        }

    }

    @AfterEach
    void tearDown() {
        webDriver.close();
    }

    @Test
    void main() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test
    void loginTest(){

    }
    @Test
    void registerTest(){

    }

    @Test
    void playSong(){

    }
    @Test
    void searchForSong(){

    }
}
