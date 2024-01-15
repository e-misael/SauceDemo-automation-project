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

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutInformationTest extends Browser {
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
     * Description: This test should verify the system behavior when checking out with blank fields.
     **/
    @ParameterizedTest(name = "Data -> Product name: {0}, Firstname: {1}, Lastname: {2}, ZipCode: {3}")
    @MethodSource("data.CheckoutInformationTestData#dataForCheckoutFormValidation")
    public void testShouldValidateRequiredFields(
                                                 String productName,
                                                 String firstName,
                                                 String lastName,
                                                 String zipCode,
                                                 String expectedMessage) {

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String currentMessage = sauceDemoLoginPage
                .doLogin(standardUser.username(), standardUser.password())
                .addItemToCartWithoutDetailing(productName)
                .goToCart()
                .goToCheckoutInformationPage()
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillZipCode(zipCode)
                .clickAtContinueButtonAndStay()
                .getValidationMessage();

        assertThat(currentMessage).isEqualTo(expectedMessage);
    }
}