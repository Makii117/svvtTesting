package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

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
        //check if darkmode is currently on or off
        if(darkmodeButton.getAttribute("class").equals("input-switch input-switch is-checked")){

            darkmodeButton.click();
            assertEquals("input-switch input-switch",darkmodeButton.getAttribute("class"));
            Thread.sleep(5000);
        }else{
            darkmodeButton.click();
            assertEquals("input-switch input-switch is-checked",darkmodeButton.getAttribute("class"));
            Thread.sleep(5000);
        }


    }
    @Test
    void downloadButton(){
        try{
            Thread.sleep(1000);
            WebElement downloadBtn = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div[2]/div[1]/a"));
            downloadBtn.click();
            Thread.sleep(1000);

            ArrayList<String> tabs2 = new ArrayList<String> (webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs2.get(0));
            webDriver.close();
            webDriver.switchTo().window(tabs2.get(1));
            Thread.sleep(1000);
            assertEquals("https://www.deezer.com/en/download",webDriver.getCurrentUrl());
            Thread.sleep(1000);
        }catch (ElementNotVisibleException | InterruptedException e){
            System.out.println("Download button not visible");
            System.out.println(e);
        }
    }

    @Test
    void leftNavbarTest() throws InterruptedException {
        WebElement musicTab,podcastTab,browseTab,favouritesTab,favouriteTracksTab,playlistsTab,albumsTab,artistsTab,savedPodcasts;

        ArrayList<WebElement> navBarItems = new ArrayList<>();
        for(int i = 1;i<10;i++){

            String xpath = String.format("/html/body/div[1]/div/div[4]/div[2]/div[3]/div/ul/li[%d]/a",i);
            WebElement navBarItem = webDriver.findElement(By.xpath(xpath));
            navBarItems.add(navBarItem);

        }
        musicTab = navBarItems.get(0);
        podcastTab = navBarItems.get(1);
        browseTab = navBarItems.get(2);
        favouritesTab = navBarItems.get(3);
        favouriteTracksTab = navBarItems.get(4);
        playlistsTab = navBarItems.get(5);
        albumsTab = navBarItems.get(6);
        artistsTab  = navBarItems.get(7);
        savedPodcasts = navBarItems.get(8);

        musicTab.click();
        Thread.sleep(1000);
        assertEquals("https://www.deezer.com/en/",webDriver.getCurrentUrl());
        Thread.sleep(500);
        podcastTab.click();
        Thread.sleep(500);
        assertEquals("https://www.deezer.com/en/podcasts",webDriver.getCurrentUrl());
        browseTab.click();
        Thread.sleep(500);
        assertEquals("https://www.deezer.com/en/channels/explore",webDriver.getCurrentUrl());
        favouritesTab.click();
        Thread.sleep(500);
        assertEquals("https://www.deezer.com/en/profile/4792945622",webDriver.getCurrentUrl());
        favouriteTracksTab.click();
        Thread.sleep(500);
        assertEquals("https://www.deezer.com/en/profile/4792945622/loved",webDriver.getCurrentUrl());
        playlistsTab.click();
        Thread.sleep(500);
        assertEquals("https://www.deezer.com/en/profile/4792945622/playlists",webDriver.getCurrentUrl());
        albumsTab.click();
        Thread.sleep(500);
        assertEquals("https://www.deezer.com/en/profile/4792945622/albums",webDriver.getCurrentUrl());
        artistsTab.click();
        Thread.sleep(500);
        assertEquals("https://www.deezer.com/en/profile/4792945622/artists",webDriver.getCurrentUrl());
        savedPodcasts.click();
        Thread.sleep(500);
        assertEquals("https://www.deezer.com/en/profile/4792945622/podcasts",webDriver.getCurrentUrl());
        Thread.sleep(500);

    }

    @Test
    void searchTestCyrilic() throws InterruptedException {
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div/form/input")).sendKeys("Тает лёд");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div/form/input")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        assertEquals("Тает лёд", webDriver.findElement(By.xpath("/html/body/div[1]/div/main/div[5]/div[1]/div[2]/div[2]/div[1]/div/div[2]/div[1]/div/div[1]/div[3]/div/span")).getText());
        Thread.sleep(500);

    }

}



