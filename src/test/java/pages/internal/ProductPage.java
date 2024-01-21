package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {
    private By header_Products = By.xpath("//span[@class='title']");
    private By btn_Cart = By.id("shopping_cart_container");
    private By btn_AddToCart;

    private By product;


    public ProductPage(WebDriver browser) {

        super(browser);
    }

    public ProductPage addItemToCartWithoutDetailing (String productName){
        btn_AddToCart = By.xpath("//div[text()='" + productName + "']/../../../div/button[text()='Add to cart']");
        browser.findElement(btn_AddToCart).click();

        return this;
    }

    public ProductDetailsPage detailInventoryProduct (String productName) {

        waitFor(header_Products);
        product = By.linkText(productName);
        browser.findElement(product).click();

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

        waitFor(header_Products);
        return browser.findElement(header_Products).getText();

    }

    public boolean identifyIfHeaderIsDisplayed (){

        return browser.findElement(header_Products).isDisplayed();
    }
}
