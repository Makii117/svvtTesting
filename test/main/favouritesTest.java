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


public class favouritesTest {

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

    @Test
    void testFavourites() throws InterruptedException {
        WebElement artistTitle = webDriver.findElement(By.linkText("The Weeknd"));
        assertEquals("The Weeknd", artistTitle.getText());
        artistTitle.click();
        Thread.sleep(1000);

        WebElement artistHeading = webDriver.findElement(By.xpath("//*[@id=\"page_naboo_artist\"]/div[1]/div/div[2]/h1"));
        assertEquals("The Weeknd", artistHeading.getText());

        // click button in order to play
        WebElement mixButton = webDriver.findElement(By.xpath("//*[@id=\"page_naboo_artist\"]/div[1]/div/div[2]/div/ul/li[1]/button"));
        mixButton.click();
        Thread.sleep(10000);
    }


    @Test
    void testLinks() throws InterruptedException {
        WebElement artistTitle = webDriver.findElement(By.linkText("The Weeknd"));
        assertEquals("The Weeknd", artistTitle.getText());
        artistTitle.click();
        Thread.sleep(1000);

        // links
        WebElement discography = webDriver.findElement(By.xpath("//*[@id=\"page_naboo_artist\"]/nav/div/ul/li[1]/a"));
        WebElement topTracks = webDriver.findElement(By.xpath("//*[@id=\"page_naboo_artist\"]/nav/div/ul/li[2]/a"));
        WebElement similarArtists = webDriver.findElement(By.xpath("//*[@id=\"page_naboo_artist\"]/nav/div/ul/li[3]/a"));
        WebElement playlists = webDriver.findElement(By.xpath("//*[@id=\"page_naboo_artist\"]/nav/div/ul/li[4]/a"));
        WebElement onTour = webDriver.findElement(By.xpath("//*[@id=\"page_naboo_artist\"]/nav/div/ul/li[5]/a"));
        WebElement bio = webDriver.findElement(By.xpath("//*[@id=\"page_naboo_artist\"]/nav/div/ul/li[6]/a"));
        Thread.sleep(1000);
        // assert they all say what they should
        // check if they are clickable
        assertEquals("Discography", discography.getText());
        discography.click();
        Thread.sleep(100);
        assertEquals("Top tracks", topTracks.getText());
        topTracks.click();
        Thread.sleep(100);
        assertEquals("Similar artists", similarArtists.getText());
        similarArtists.click();
        Thread.sleep(100);
        assertEquals("Playlists", playlists.getText());
        playlists.click();
        Thread.sleep(100);
        assertEquals("On tour", onTour.getText());
        onTour.click();
        Thread.sleep(100);
        assertEquals("Bio", bio.getText());
        bio.click();
        Thread.sleep(20000);
    }

    // TODO : FIGURE OUT WHY IT KEEPS FAILING
    @Test
    void testAddFavourite() throws InterruptedException {
        WebElement artists_tab  = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[2]/div[3]/div/ul/li[8]/a"));
       Thread.sleep(1000);
        artists_tab.click();

        Thread.sleep(5000);

        WebElement add_artist = webDriver.findElement(By.xpath("//*[@id=\"page_profile\"]/div[2]/div/div/section/div[2]/ul/li[1]/div[2]/div/button"));
        Thread.sleep(5000);
        add_artist.click();
        Thread.sleep(5000);
        WebElement artist = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div[2]/div/div/div[3]/div/div/div[2]/div/div[1]"));
        artist.click();
        WebElement add_button = webDriver.findElement(By.xpath("//*[@id=\"dzr-app\"]/div/div[1]/div[2]/div[1]/div[3]/button"));
        add_button.click();
        Thread.sleep(5000);
        webDriver.navigate().back();
        Thread.sleep(5000);
    }



    @AfterEach
    void teardown(){
        webDriver.close();
    }


}
