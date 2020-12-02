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
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "ProductDataTest.csv")

public class ProductTest extends Browser {
    private final String filePath = "src/test/testReports/";
    private final String imageExt = ".png";
    private String methodName = "_Undefined_Method_Name";

    private WebDriver browser;
    private SauceDemoLoginPage sauceDemoLoginPage;

    @Before
    public void initBrowser() {
        browser = createChrome();
    }

    /**
     * Description: This test should verify whether inventory items are shown as registered.
     **/
    @Test
    public void testShouldListInventoryProducts(@Param(name = "url") String url,
                                                @Param(name = "user") String user,
                                                @Param(name = "password") String password,
                                                @Param(name = "productName1") final String productName1,
                                                @Param(name = "productName2") final String productName2,
                                                @Param(name = "productName3") final String productName3,
                                                @Param(name = "productName4") final String productName4,
                                                @Param(name = "productName5") final String productName5,
                                                @Param(name = "productName6") final String productName6) {

        methodName = "_testShouldListInventoryProducts";

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        List<String> receivedProductList =
                sauceDemoLoginPage
                        .accessURL(url)
                        .doLogin(user, password)
                        .showListOfProductNames();

        List<String> expectedProductList =
                Arrays.asList(productName1, productName2, productName3, productName4, productName5, productName6);

        assertEquals(expectedProductList, receivedProductList);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
    }

    /**
     * Description: This test aims to verify if cart counter is representing all added products.
     **/

    @Test
    public void testShouldAddProductToCart(@Param(name = "url") String url,
                                           @Param(name = "user") String user,
                                           @Param(name = "password") String password,
                                           @Param(name = "productName1") String productName1,
                                           @Param(name = "productName2") String productName2) {

        methodName = "_testShouldAddProductToCart";

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        int expectedCartSize = 2;

        int cartSize =
                Integer.parseInt(
                        sauceDemoLoginPage
                                .accessURL(url)
                                .doLogin(user, password)
                                .addItemToCartWithoutDetailing(productName1)
                                .detailInventoryProduct(productName2)
                                .addInventoryProductToCart()
                                .verifyCartSize());

        assertEquals(expectedCartSize, cartSize);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
    }

    /**
     * Description: This test aims to verify item details on detailing.
     **/

    @Test
    public void testShouldDetailingProduct(@Param(name = "url") String url,
                                           @Param(name = "user") String user,
                                           @Param(name = "password") String password,
                                           @Param(name = "productName") final String productName,
                                           @Param(name = "productDescription") final String productDescription,
                                           @Param(name = "productPrice") final String productPrice) {

        methodName = "_testShouldDetailingProduct";

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        List<String> receivedProductsData =
                sauceDemoLoginPage
                        .accessURL(url)
                        .doLogin(user, password)
                        .detailInventoryProduct(productName)
                        .loadProductDataOnDetailing();

        List<String> expectedProductsData =
                Arrays.asList(productName, productDescription, productPrice);

        assertEquals(expectedProductsData, receivedProductsData);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
    }

    @After
    public void tearDown() {
        browser.quit();
    }
}