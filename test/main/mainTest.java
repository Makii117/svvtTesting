package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class mainTest {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeEach
    void setUp() {
    System.setProperty("webdriver.chrome.driver","chromedriver.exe");
    webDriver= new ChromeDriver();
    baseUrl="http://a.testaddressbook.com/ ";

    }

    @AfterEach
    void tearDown() {
        webDriver.close();
    }

    @Test
    void main() {
    }
}