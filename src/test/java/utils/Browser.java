package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Browser {

    public static WebDriver createChrome() {

        System.setProperty("webdriver.chrome.driver", "src\\drivers\\chromedriver.exe");

        WebDriver browser = new ChromeDriver();
        browser.manage().deleteAllCookies();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        browser.navigate().to("https://www.saucedemo.com/");

        return browser;
    }

}
