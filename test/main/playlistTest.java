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
        webDriver.findElement(By.xpath("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/div[2]/div/div/section/div[2]/ul/li[1]/div[2]/div/button")).click();
        Thread.sleep(100);
        WebElement plName = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[8]/div[2]/div/div/div[2]/div/div[1]/input"));
        plName.sendKeys("TestPlaylist");
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[8]/div[2]/div/div/div[2]/div/div[3]/button")).click();

    }
    @Test
    void addToPlaylist() throws InterruptedException {
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[2]/div[3]/div/ul/li[3]/a")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/section[2]/div[2]/ul/li[1]/a/figure/div[1]")).click();

        Thread.sleep(1000);
        webDriver.findElement(By.xpath("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/section[1]/div/div[2]/div/div/ul/li[1]/figure/div")).click();
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
        //wait 10 seconds and exit
        Thread.sleep(10000);

    }
}
