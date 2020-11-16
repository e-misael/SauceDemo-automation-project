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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "CartDataTest.csv")

public class CartTest extends Browser {
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
     * Description: This test is responsible for add an item to the cart and verify its details.
     **/

    @Test
    public void testShouldAddProductToCartAndVerifyItemDetails(@Param(name = "url") String url,
                                                           @Param(name = "user") String user,
                                                           @Param(name = "password") String password,
                                                           @Param(name = "productName") String productName,
                                                           @Param(name = "productDescription") String productDescription,
                                                           @Param(name = "productPrice") String productPrice) {

        methodName = "_testShouldAddProductToCartAndVerifyItemDetails";

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        List<String> receivedProductDetails =
                sauceDemoLoginPage
                        .accessURL(url)
                        .doLogin(user, password)
                        .addItemToCartWithoutDetailing(productName)
                        .goToCart()
                        .loadItemCartDetails();

        List<String> expectedProductDetails =
                Arrays.asList(productName,
                        productDescription.replace("\"", "'"),
                        productPrice);

        assertEquals(expectedProductDetails, receivedProductDetails);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
    }

    /**
     * Description: This test is responsible for return to Inventory Page from Cart.
     **/

    @Test
    public void testShouldReturnToInventory(@Param(name = "url") String url,
                                            @Param(name = "user") String user,
                                            @Param(name = "password") String password,
                                            @Param(name = "productName") String productName) {

        methodName = "_testShouldReturnToInventory";
        String expectedTitle = "Products";

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String productPageTitle =
                sauceDemoLoginPage
                        .accessURL(url)
                        .doLogin(user, password)
                        .addItemToCartWithoutDetailing(productName)
                        .goToCart()
                        .returnToInventory()
                        .getProductPageTitle();

        assertEquals(expectedTitle, productPageTitle);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
    }
}