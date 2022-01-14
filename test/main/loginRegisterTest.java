package main;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class loginRegisterTest {
    private static WebDriver webDriver;
    private static String baseUrl;
    //Use dummy email and password from https://inboxkitten.com/
    //
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

    }

    @AfterEach
    void tearDown() {
        webDriver.close();
    }

    @Test
    void loginTest() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/a"));
        loginButton.click();

        // form
        WebElement emailField = webDriver.findElement(By.xpath("//*[@id=\"login_mail\"]"));
        emailField.sendKeys(Email);
        // password - > SvvtTestingAccount1
        WebElement passwordField = webDriver.findElement(By.xpath("//*[@id=\"login_password\"]"));
        passwordField.sendKeys(Password);
        Thread.sleep(2000);

        WebElement loginBtn = webDriver.findElement(By.id("login_form_submit"));
        loginBtn.click();
        // in case of captcha, which can happen it is left to 20000 so we can click through it
        Thread.sleep(20000);

        // test if we made it to the personalized homepage
        WebElement text = webDriver.findElement(By.className("heading-2"));

        assertEquals("Made for you", text.getText());
    }

    @Test
    void registerTest() throws InterruptedException {


        WebElement registerButton;
        registerButton= webDriver.findElement(By.xpath("//*[@id=\"page-homepage\"]/section[1]/ul[1]/li/div/a[2]"));
        registerButton.click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement email,username,password,age,pwStrength;
        Select identity;
        String pwStrengthText;
        email = webDriver.findElement(By.xpath("//*[@id=\"register_form_mail_input\"]"));
        username = webDriver.findElement(By.xpath("//*[@id=\"register_form_username_input\"]"));
        password =webDriver.findElement(By.xpath("//*[@id=\"register_form_password_input\"]"));
        age = webDriver.findElement(By.xpath("//*[@id=\"register_form_age_input\"]"));
        identity = new Select(webDriver.findElement(By.xpath("//*[@id=\"register_form_gender_input\"]")));
        identity.selectByValue("M");
        email.sendKeys(Email);
        username.sendKeys(uName);

        age.sendKeys("20");
        //show/hide Password
        WebElement pwHider;
        String pwType;
        pwType = password.getAttribute("Type");
        assertEquals("password",pwType);
        //in case of another cookie pop up, just close it fast
        pwHider = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[3]/form/div[3]/span"));
        pwHider.click();

        pwType = password.getAttribute("Type");
        assertEquals("text",pwType);

        //check pw strength

        //send 1-8 as pw
        //assert pw = weak

        password.sendKeys("12345678");
        pwStrength = webDriver.findElement(By.xpath("//*[@id=\"pwd-level-sign\"]"));
        pwStrengthText =  pwStrength.getText();
        assertEquals("Weak",pwStrengthText);
        Thread.sleep(2000);

        //clear pw
        password.sendKeys(Keys.CONTROL+"a");
        password.sendKeys(Keys.DELETE);

        //send 8x num 1x letter
        //assert pw = medium
        password.sendKeys("12345678a");
        pwStrength = webDriver.findElement(By.xpath("//*[@id=\"pwd-level-sign\"]"));
        pwStrengthText =  pwStrength.getText();
        assertEquals("Medium",pwStrengthText);
        Thread.sleep(2000);

        //clear pw
        password.sendKeys(Keys.CONTROL+"a");
        password.sendKeys(Keys.DELETE);


        //send SvvtTestingAccount1
        //assert pw = strong + cont
        password.sendKeys("SvvtTestingAccount1");
        pwStrength = webDriver.findElement(By.xpath("//*[@id=\"pwd-level-sign\"]"));
        pwStrengthText =  pwStrength.getText();
        assertEquals("Strong",pwStrengthText);
        Thread.sleep(2000);


        //finish sign up
        WebElement signUpButton = webDriver.findElement(By.xpath("//*[@id=\"register_form_submit\"]"));
        signUpButton.click();
        //In case of captcha uncomment and click fast
        //Thread.sleep(20000);
        WebElement welcomeHeader = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div[1]/h1"));

        assertEquals("Welcome",welcomeHeader.getText());

        ArrayList<WebElement> artists = new ArrayList<>();

        for(int j = 1; j<=3; j++){
            String xpath = String.format("//*[@id=\"artist-grid\"]/div/div/div[3]/div/div/div[%d]",j);
            WebElement artist = webDriver.findElement(By.xpath(xpath));
            artists.add(artist);
        }

        for(int j=0;j<=2;j++){
            artists.get(j).click();
        }
        WebElement contButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div[3]/button"));
        WebElement contButtonSpan = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div[3]/button/span"));
        assertEquals("CONTINUE WITH 3 ARTISTS", contButtonSpan.getText());
        contButton.click();
        // wait for content loading and close popup
        WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[8]/div[2]/div/div/button")));
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[8]/div[2]/div/div/button")).click();

        //open profile drop down and assert that we are logged in
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[3]/button")).click();

        //assert that profile drop down is open
        assertEquals("topbar-profile is-active",webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[3]/button")).getAttribute("class"));
        //assert that the username matches the registered one
        assertEquals(uName,webDriver.findElement(By.className("account-name")).getText());

        //Test finished
        System.out.println("All tests passed");
        Thread.sleep(2000);
    }


}
