package main;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class logIn {
    private static WebDriver webDriver;
    private static String baseUrl;
    private String Email="steel-fifty-32@inboxkitten.com";
    private String uName="steel-fifty-32";
    private String Password = "SvvtTestingAccount1";

    public WebDriver login() throws InterruptedException {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().window().maximize();

        baseUrl="https://www.deezer.com/en/";
        driver.get(baseUrl);
        try{
            File file = new File("Cookies.data");
            FileReader fileReader = new FileReader(file);
            BufferedReader Buffreader = new BufferedReader(fileReader);
            String strline;
            while((strline=Buffreader.readLine())!=null){
                StringTokenizer token = new StringTokenizer(strline,";");
                while(token.hasMoreTokens()){
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    Date expiry = null;

                    String val;
                    if(!(val=token.nextToken()).equals("null"))
                    {
                        Date expDate = new SimpleDateFormat( "EEE MMM dd HH:mm:ss z yyyy", Locale.UK).parse(val);
                        expiry = expDate;
                    }
                    Boolean isSecure = Boolean.valueOf(token.nextToken());
                    Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);
                    System.out.println(ck);
                    driver.manage().addCookie(ck); // This will add the stored cookie to your current session
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        Thread.sleep(2000);
        driver.get(baseUrl);
        Thread.sleep(2000);
        System.out.println("Logged in successfully");
        return driver;
    }

    void getCookie() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();

        baseUrl="https://www.deezer.com/en/";
        webDriver.get(baseUrl);
        //accept the cookies
        try{
            WebElement acceptCookiesBtn;
            acceptCookiesBtn = webDriver.findElement(By.xpath("//*[@id=\"gdpr-btn-accept-all\"]"));
            acceptCookiesBtn.click();

        }catch (ElementNotVisibleException e){
            System.out.println("Cookies already accepted");

        }

        //login
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


        //get cookie
        File file = new File("Cookies.data");
        try
        {
            // Delete old file if exists
            file.delete();
            file.createNewFile();
            FileWriter fileWrite = new FileWriter(file);
            BufferedWriter Bwrite = new BufferedWriter(fileWrite);

            for(Cookie ck : webDriver.manage().getCookies())
            {
                Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));
                Bwrite.newLine();
            }
            Bwrite.close();
            fileWrite.close();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        Thread.sleep(2000);

    }

    void closeDriver(){
        webDriver.close();
    }

}
