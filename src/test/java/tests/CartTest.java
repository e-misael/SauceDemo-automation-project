package tests;

import data.UserDataFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pages.SauceDemoLoginPage;
import pojo.User;
import utils.Browser;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends Browser {
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

    @ParameterizedTest(name = "Current product data -> Name: {0}, Description: {1}, Price: {2}")
    @MethodSource("data.CartTestData#allProductsData")
    @DisplayName("Add product to cart and verify item details")
    @Tag("regression")
    public void testShouldAddProductToCartAndVerifyItemDetails(String productName,
                                                               String productDescription,
                                                               String productPrice){
        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        List<String> currentProductDetails =
            sauceDemoLoginPage
                .doLogin(standardUser.username(), standardUser.password())
                .addItemToCartWithoutDetailing(productName)
                .goToCart()
                .loadItemCartDetails();

        List<String> expectedProductDetails =
                Arrays.asList(productName,
                        productDescription,
                        productPrice);

        assertThat(currentProductDetails).isEqualTo(expectedProductDetails);

    }

    /**
     * Description: This test is responsible for return to Inventory Page from Cart.
     **/

    @ParameterizedTest(name = "Product name -> {0}")
    @MethodSource("data.CartTestData#getRandomProductName")
    @DisplayName("Return to inventory page after adding an item to cart without detailing it.")
    @Tag("scrsht")
    public void testShouldReturnToInventory(String productName){

        String expectedTitle = "Products";

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String currentProductPageTitle =
            sauceDemoLoginPage
                .doLogin(standardUser.username(), standardUser.password())
                .addItemToCartWithoutDetailing(productName)
                .goToCart()
                .returnToInventory()
                .getProductPageTitle();

            assertThat(currentProductPageTitle).isEqualTo(expectedTitle);
    }
}