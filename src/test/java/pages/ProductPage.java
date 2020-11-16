package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {
    private By pageTitle = By.className("product_label");
    private By btn_Cart = By.id("shopping_cart_container");
    private By btn_AddToCart;

    private By product_SauceLabsBackpack = By.linkText("Sauce Labs Backpack");
    private By header_Products = By.className("product_label");


    public ProductPage(WebDriver browser) {

        super(browser);
    }

    public ProductPage addItemToCartWithoutDetailing (String productName){
        btn_AddToCart = By.xpath("//div[text()='" + productName + "']/../../../div/button");

        browser.findElement(btn_AddToCart).click();

        return this;
    }


    public ProductDetailsPage detailInventoryProduct (String productName) {
        browser.findElement(product_SauceLabsBackpack).click();

        return new ProductDetailsPage(browser);
    }

    public List<String> showListOfProductNames () {

        List<String> productsList = new ArrayList<String>();

        for (WebElement product : browser.findElements(By.className("inventory_item_name"))) {
            productsList.add(product.getText());
        }

        return productsList;
    }

    public String getProductPageTitle(){
          return browser.findElement(pageTitle).getText();
    }

    public boolean identifyIfHeaderIsDisplayed (){

        return browser.findElement(header_Products).isDisplayed();
    }
}
