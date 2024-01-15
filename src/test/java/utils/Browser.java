package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Browser {
    public WebDriver browser;
    final protected static String _URL = "https://www.saucedemo.com/";
    public WebDriver createChrome() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--headless");

        browser = new ChromeDriver(options);
        browser.manage().deleteAllCookies();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        browser.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        browser.navigate().to(_URL);

        return browser;
    }
}
