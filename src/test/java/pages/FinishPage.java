package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinishPage extends BasePage {
    private By message = By.className("complete-header");

    public FinishPage (WebDriver browser) {
        super(browser);
    }

    public String getSuccessMessage (){
        return browser.findElement(message).getText();
    }


}
