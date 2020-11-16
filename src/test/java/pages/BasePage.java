package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}
