package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.SauceDemoLoginPage;
import utils.Browser;
import utils.RandomDateGenerator;
import utils.Screenshot;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "CheckoutInformationDataTest.csv")

public class CheckoutInformationTest extends Browser {
    private final String filePath = "src/test/testReports/";
    private final String imageExt = ".png";
    private String methodName = "_Undefined_Method_Name";

    private WebDriver browser;

    @Before
    public void initBrowser() {
        browser = createChrome();
    }

    @After
    public void tearDown() {
        browser.quit();
    }

    /**
     * Description: This test should verify the system behavior on try to checkout with blank fields.
     **/
    @Test
    public void testShouldValidateRequiredFields(@Param(name = "url") String url,
                                                 @Param(name = "user") String user,
                                                 @Param(name = "password") String password,
                                                 @Param(name = "productName") String productName,
                                                 @Param(name = "firstname") String firstName,
                                                 @Param(name = "lastname") String lastName,
                                                 @Param(name = "zipcode") String zipCode,
                                                 @Param(name = "message") String expectedMessage) {

        methodName = "_testShouldValidateRequiredFields";

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String receivedMessage = sauceDemoLoginPage
                .accessURL(url)
                .doLogin(user, password)
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