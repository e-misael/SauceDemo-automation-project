package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutInformationPage extends BasePage{
    private By errorMessage = By.cssSelector("[data-test='error']");

    private By firstNameField = By.cssSelector("[data-test='firstName']");
    private By lastNameField = By.cssSelector("[data-test='lastName']");
    private By zipCodeField = By.cssSelector("[data-test='postalCode']");

    private By btn_Checkout = By.xpath("//input[contains(@value, 'CONTINUE')]");

    public CheckoutInformationPage(WebDriver browser) {
        super(browser);
    }

    public CheckoutInformationPage fillFirstName (String firstname){
        browser.findElement(firstNameField).sendKeys(firstname);
        return this;
    }

    public CheckoutInformationPage fillLastName (String lastName){
        browser.findElement(lastNameField).sendKeys(lastName);
        return this;
    }

    public CheckoutInformationPage fillZipCode (String zipCode){
        browser.findElement(zipCodeField).sendKeys(zipCode);
        return this;
    }

    public OverviewPage clickAtCheckoutButtonAndProceed(){
        browser.findElement(btn_Checkout).click();
        return new OverviewPage(browser);
    }

    public CheckoutInformationPage clickAtCheckoutButtonAndStay(){
        browser.findElement(btn_Checkout).click();
        return this;
    }

    public String getValidationMessage(){
        WebDriverWait wait = new WebDriverWait(browser, 5);

        wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));

        return browser.findElement(errorMessage).getText();

    }

    public OverviewPage fillCheckoutAndContinue(String firstname, String lastname, String zipcode){
        fillFirstName(firstname);
        fillLastName(lastname);
        fillZipCode(zipcode);
        clickAtCheckoutButtonAndProceed();

        return new OverviewPage(browser);
    }

}