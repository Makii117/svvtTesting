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
    private static logIn login = new logIn();

    @BeforeEach
    void setup() throws InterruptedException {
        webDriver = login.login();
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
        assertEquals("input-switch input-switch is-checked",darkmodeButton.getAttribute("class"));
        Thread.sleep(1000);
    }

}



