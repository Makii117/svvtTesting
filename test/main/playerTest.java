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
    void testDailyPlayer() throws InterruptedException {
        Thread.sleep(2000);
        WebElement dailyPlayButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/section[1]/div/div[2]/div/div/ul/li[1]/figure/ul/li/button"));
        dailyPlayButton.click();
        Thread.sleep(15000);

        WebElement pauseBtn = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div[1]/div[1]/ul/li[3]/button"));
        String pause = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div/div[1]/ul/li[3]/button")).getAttribute("aria-label");
        System.out.println(pause);
        assertEquals("Pause", pause);
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
        Thread.sleep(5000);
        viewLyrics.click();
        Thread.sleep(1000);

        WebElement skip = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div[1]/div[1]/ul/li[5]/div/button"));
        skip.click();
        Thread.sleep(1000);

        assertNotEquals("Blinding Lights", songName.getText());

        // check if button is play or pause
        WebElement pauseBtn = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div[1]/div[1]/ul/li[3]/button"));
        String pause = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div/div[1]/ul/li[3]/button")).getAttribute("aria-label");
        System.out.println(pause);
        assertEquals("Pause", pause);
        pauseBtn.click();
        Thread.sleep(500);
        pause = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div/div[1]/ul/li[3]/button")).getAttribute("aria-label");
        System.out.println(pause);
        assertEquals("Play", pause);

        Thread.sleep(10000);

    }



}
