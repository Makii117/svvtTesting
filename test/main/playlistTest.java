package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;


public class playlistTest {
    private static WebDriver webDriver;
    private static String baseUrl;

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
    void createPlaylist() throws InterruptedException {

        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[2]/div[3]/div/ul/li[6]/a")).click();
        Thread.sleep(5000);
        //in case of small monitor scroll down for the js to load
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/div[2]/div/div/section/div[2]/ul/li[1]/div[2]/div/button")).click();
        Thread.sleep(100);

        Thread.sleep(2000);
        WebElement plName = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[8]/div[2]/div/div/div[2]/div/div[1]/input"));
        //playlist name
        plName.sendKeys("TestPlaylist1");
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[8]/div[2]/div/div/div[2]/div/div[3]/button")).click();
        Thread.sleep(100);
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div[2]/div[2]/button")).click();

        Thread.sleep(500);
        assertEquals("TestPlaylist1",webDriver.findElement(By.xpath("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/div[1]/div/div[1]/div/div[2]/h1")).getText());
        Thread.sleep(1000);
    }
    @Test
    void addToPlaylist() throws InterruptedException {
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[2]/div[3]/div/ul/li[3]/a")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/section[2]/div[2]/ul/li[1]/a/figure/div[1]")).click();

        Thread.sleep(1000);

        webDriver.findElement(By.xpath("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/section[1]/div/div[2]/div/div/ul/li[1]/figure/div")).click();
        Thread.sleep(1000);

        //in case of small monitor scroll down for the js to load
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(1000);
        for(int i=1;i<=6; i++){
            String xpath = String.format("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/div[1]/div/div[3]/div[2]/div/div/div[%d]/div/div[1]/div[4]/div/button",i);

            webDriver.findElement(By.xpath(xpath)).click();
            Thread.sleep(500);
            webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/ul/li[4]/button/span[2]"));
            Thread.sleep(500);
            webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/ul/li[4]/button")).click();


            Thread.sleep(500);
            webDriver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div[2]/ul/div/div/li[1]/button")).click();
            Thread.sleep(500);
            WebElement addedPopup = webDriver.findElement(By.xpath("/html/body/div[1]/div/aside/div/h2"));
            if(addedPopup.getText().equals("Added to playlist")) {
                assertEquals("Added to playlist", addedPopup.getText());
                Thread.sleep(500);
            }else{
                assertEquals("This track has already been added to the playlist", addedPopup.getText());
                Thread.sleep(500);
            }
        }

    }

    @Test
    void openPlaylistAndPlay() throws InterruptedException {
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[2]/div[3]/div/ul/li[6]/a")).click();
        Thread.sleep(1000);
        //click on most recent playlist
        webDriver.findElement(By.xpath("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/div[2]/div/div/section/div[2]/ul/li[2]/figure/div")).click();
        Thread.sleep(1000);
        //play the playlist
        webDriver.findElement(By.xpath("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/button")).click();
        Thread.sleep(5000);
        //Check if its being played
        String pause = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div/div[1]/ul/li[3]/button")).getAttribute("aria-label");
        System.out.println(pause);
        assertEquals("Pause", pause);
        //wait 9 seconds and exit
        Thread.sleep(9000);

    }
}
