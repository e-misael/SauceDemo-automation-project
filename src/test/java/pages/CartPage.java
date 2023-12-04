package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private By pageTitle = By.className("header_label");
    private By productName = By.className("inventory_item_name");
    private By productDescription = By.className("inventory_item_desc");
    private By productPrice = By.className("inventory_item_price");
    private By btn_ContinueShopping = By.id("continue-shopping");
    private By btn_Checkout = By.id("checkout");

    public CartPage(WebDriver browser) {
        super(browser);
    }

    public List<String> loadItemCartDetails(){

        waitFor(pageTitle);

        List<String> productDetails = new ArrayList<String>();
        productDetails.add(browser.findElement(productName).getText());
        productDetails.add(browser.findElement(productDescription).getText());
        productDetails.add(browser.findElement(productPrice).getText());

        return productDetails;
    }

    public ProductPage returnToInventory (){
        browser.findElement(btn_ContinueShopping).click();

        return new ProductPage(browser);
    }

    public CheckoutInformationPage goToCheckoutInformationPage (){
        browser.findElement(btn_Checkout).click();
        return new CheckoutInformationPage(browser);
    }
}
