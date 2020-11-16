package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsPage extends BasePage {
    private final By productName = By.className("inventory_details_name");
    private final By productDescription = By.className("inventory_details_desc");
    private final By productPrice = By.className("inventory_details_price");

    private final By btn_AddToCart = By.xpath("//button[text()='ADD TO CART']");
    private final By cartSize = By.xpath("//div[@id='header_container']//span");

    public ProductDetailsPage(WebDriver browser) {
        super(browser);
    }

    public ProductDetailsPage addInventoryProductToCart () {
        browser.findElement(btn_AddToCart).click();

        return this;
    }

    public List<String> loadProductDataOnDetailing (){
        List <String> productDetail = new ArrayList<String>();
        productDetail.add(browser.findElement(productName).getText());
        productDetail.add(browser.findElement(productDescription).getText());
        productDetail.add(browser.findElement(productPrice).getText());

        return productDetail;
    }

    public String verifyCartSize() {
        return browser.findElement(cartSize).getText();
    }

}
