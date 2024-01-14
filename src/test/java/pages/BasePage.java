package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver browser;
    private By btn_Cart = By.id("shopping_cart_container");

    public BasePage(WebDriver browser) {
        this.browser = browser;
    }

    public CartPage goToCart(){
        browser.findElement(btn_Cart).click();

        return new CartPage(browser);
    }

    public void waitFor(By element) {

        WebDriverWait waitForElement = new WebDriverWait(browser, Duration.ofSeconds(20));
        waitForElement.until(ExpectedConditions.presenceOfElementLocated(element));

    }

}
