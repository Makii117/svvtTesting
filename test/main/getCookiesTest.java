package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;


public class getCookiesTest {
    private static WebDriver webDriver;
    private static String baseUrl;
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
    void test() throws InterruptedException {
        Thread.sleep(2000);
        assertEquals("steel-fifty-32",webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[3]/button/img")).getAttribute("alt"));

    }

    @Test
    void playSong(){

    }

}
