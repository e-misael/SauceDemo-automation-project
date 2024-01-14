package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceDemoLoginPage extends BasePage {
    private By field_User = By.cssSelector("[data-test='username']");
    private By field_Password = By.cssSelector("[data-test='password']");
    private By btn_Login = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public SauceDemoLoginPage(WebDriver browser) {
        super(browser);
    }

    public SauceDemoLoginPage accessURL(String url) {
        browser.navigate().to(url);

        return this;
    }

    public SauceDemoLoginPage fillUser (String user) {
        browser.findElement(field_User).sendKeys(user);
        return this;
    }

    public SauceDemoLoginPage fillPassword(String senha) {
        browser.findElement(field_Password).sendKeys(senha);
        return this;
    }

    public ProductPage clickAtLogin() {
        browser.findElement(btn_Login).click();

        return new ProductPage(browser);
    }

    public ProductPage doLogin(String user, String password) {
        fillUser(user);
        fillPassword(password);
        clickAtLogin();

        return new ProductPage(browser);
    }

    public String getValidationMessage(){
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));

        return browser.findElement(errorMessage).getText();

    }
}