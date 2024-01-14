package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class OverviewPage extends BasePage {
    private By pageTitle = By.className("header_secondary_container");
    private By itemQty = By.className("cart_quantity");
    private By itemName = By.className("inventory_item_name");
    private By itemDescription = By.className("inventory_item_desc");
    private By itemPrice = By.className("inventory_item_price");

    private By summarySubtotal = By.className("summary_subtotal_label");
    private By summaryTax = By.className("summary_tax_label");
    private By summaryTotal = By.className("summary_total_label");

    private By btn_Finish = By.id("finish");

    public OverviewPage(WebDriver browser) {
        super(browser);
    }

    public String getPageTitle (){
        return browser.findElement(pageTitle).getText();
    }

    public List<String> getProductDetails (){

        waitFor(pageTitle);

        // Details
        List<String> itemDetails = new ArrayList<String>();
        itemDetails.add(browser.findElement(itemQty).getText());
        itemDetails.add(browser.findElement(itemName).getText());
        itemDetails.add(browser.findElement(itemDescription).getText());
        itemDetails.add(browser.findElement(itemPrice).getText());

        // Values
        itemDetails.add(browser.findElement(summarySubtotal).getText());
        itemDetails.add(browser.findElement(summaryTax).getText());
        itemDetails.add(browser.findElement(summaryTotal).getText());
        return itemDetails;
    }

    public FinishPage goToCompletePage (){
        browser.findElement(btn_Finish).click();

        return new FinishPage (browser);
    }
}
