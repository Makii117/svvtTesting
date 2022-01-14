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


public class homePageTest {
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


    @Test
    void darkModeTest() throws InterruptedException {
        WebElement profileButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[3]/button"));
        profileButton.click();
        Thread.sleep(1000);

        WebElement darkmodeButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[3]/div/div[1]/ul/li[6]/div/label"));
        darkmodeButton.click();
    }

}



