package tests;

import data.UserDataFactory;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pages.SauceDemoLoginPage;
import pojo.User;
import utils.Browser;

import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.junit.Assert.assertEquals;

public class CartTest extends Browser {
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
     * Description: This test is responsible for add an item to the cart and verify its details.
     **/

    //@Test
    @Severity(NORMAL)
    @ParameterizedTest(name = "Current product data -> Name: {0}, Description: {1}, Price: {2}")
    @MethodSource("data.CartTestData#allProductsData")
    @DisplayName("Add product to cart and verify item details")
    public void testShouldAddProductToCartAndVerifyItemDetails(String productName,
                                                               String productDescription,
                                                               String productPrice){
        methodName = "_testShouldAddProductToCartAndVerifyItemDetails";
        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        List<String> receivedProductDetails =
            sauceDemoLoginPage
                .doLogin(standardUser.username(), standardUser.password())
                .addItemToCartWithoutDetailing(productName)
                .goToCart()
                .loadItemCartDetails();

        List<String> expectedProductDetails =
                Arrays.asList(productName,
                        productDescription,
                        productPrice);

        assertEquals(expectedProductDetails, receivedProductDetails);

//        Screenshot.takeScreenshot(browser, filePath
//                + RandomDateGenerator.generateTimestampToFile()
//                + methodName + imageExt);
    }

    /**
     * Description: This test is responsible for return to Inventory Page from Cart.
     **/

    @ParameterizedTest(name = "Product name -> {0}")
    @MethodSource("data.CartTestData#getRandomProductName")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Return to inventory page after adding an item to cart without detailing it.")
    public void testShouldReturnToInventory(String productName){

        methodName = "_testShouldReturnToInventory";
        String expectedTitle = "Products";

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String productPageTitle =
            sauceDemoLoginPage
                .doLogin(standardUser.username(), standardUser.password())
                .addItemToCartWithoutDetailing(productName)
                .goToCart()
                .returnToInventory()
                .getProductPageTitle();

        assertEquals(expectedTitle, productPageTitle);

//        Screenshot.takeScreenshot(browser, filePath
//                + RandomDateGenerator.generateTimestampToFile()
//                + methodName + imageExt);
    }
}