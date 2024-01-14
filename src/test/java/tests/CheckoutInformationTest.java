package tests;

import data.UserDataFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pages.SauceDemoLoginPage;
import pojo.User;
import utils.Browser;
import utils.RandomDateGenerator;
import utils.Screenshot;

import static org.junit.Assert.assertEquals;

public class CheckoutInformationTest extends Browser {
    private final String filePath = "src/test/testReports/";
    private final String imageExt = ".png";
    private String methodName = "_Undefined_Method_Name";
    private User standardUser;

    private WebDriver browser;

    @BeforeEach
    public void initBrowser() {
        browser = createChrome();
        standardUser = UserDataFactory.createStandardUser();
    }

    @AfterEach
    public void tearDown() {
        browser.quit();
    }

    /**
     * Description: This test should verify the system behavior on try to checkout with blank fields.
     **/
    @ParameterizedTest(name = "Data -> Product name: {0}, Firstname: {1}, Lastname: {2}, ZipCode: {3}")
    @MethodSource("data.CheckoutInformationTestData#dataForCheckoutFormValidation")
    public void testShouldValidateRequiredFields(
                                                 String productName,
                                                 String firstName,
                                                 String lastName,
                                                 String zipCode,
                                                 String expectedMessage) {

        methodName = "_testShouldValidateRequiredFields";

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String receivedMessage = sauceDemoLoginPage
                .doLogin(standardUser.username(), standardUser.password())
                .addItemToCartWithoutDetailing(productName)
                .goToCart()
                .goToCheckoutInformationPage()
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillZipCode(zipCode)
                .clickAtContinueButtonAndStay()
                .getValidationMessage();

        assertEquals(expectedMessage, receivedMessage);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
    }
}