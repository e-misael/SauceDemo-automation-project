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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest extends Browser {

    private WebDriver browser;
    private SauceDemoLoginPage sauceDemoLoginPage;
    private User standardUser;

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
     * Description: This test should verify whether inventory items are shown as registered.
     **/
    @ParameterizedTest(name = "Data: {0}")
    @MethodSource("data.ProductTestData#inventoryProducts")
    public void testShouldListInventoryProducts(List<?> expectedProducts) {

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        List<String> receivedProductList =
                sauceDemoLoginPage
                        .doLogin(standardUser.username(), standardUser.password())
                        .showListOfProductNames();

        assertThat(receivedProductList).isEqualTo(expectedProducts);
    }

    /**
     * Description: This test aims to verify if cart counter is representing all added products.
     **/

    @ParameterizedTest(name = "Data: P1: {0}, P2: {1}")
    @MethodSource("data.ProductTestData#productsToBeAddedInTheCart")
    public void testShouldAddProductToCart(
                                           String productName1,
                                           String productName2) {

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        int expectedCartSize = 2;

        int currentCartSize =
                Integer.parseInt(
                        sauceDemoLoginPage
                                .doLogin(standardUser.username(), standardUser.password())
                                .addItemToCartWithoutDetailing(productName1)
                                .detailInventoryProduct(productName2)
                                .addInventoryProductToCart()
                                .verifyCartSize());

        assertThat(currentCartSize).isEqualTo(expectedCartSize);
    }

    /**
     * Description: This test aims to verify item details on detailing.
     **/

    @ParameterizedTest(name = "Data: Product name - {0}, Product description - {1}, Price - {2}")
    @MethodSource("data.ProductTestData#allProductsData")
    public void testShouldDetailingProduct(
                                           String productName,
                                           String productDescription,
                                           String productPrice) {

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        List<String> receivedProductsData =
                sauceDemoLoginPage
                        .doLogin(standardUser.username(), standardUser.password())
                        .detailInventoryProduct(productName)
                        .loadProductDataOnDetailing();

        List<String> expectedProductsData =
                Arrays.asList(productName, productDescription, productPrice);

        assertThat(receivedProductsData).isEqualTo(expectedProductsData);
    }
}