package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.SauceDemoLoginPage;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("e2e")
public class OverviewTest extends BaseTest {

    /**
     * Description: This test is responsible for do checkout and verify if the correct page is shown.
     **/
    @ParameterizedTest(name = "Data: {0}, {1}, {2}, {3}")
    @MethodSource("data.OverviewTestData#dataForOverviewPageTitleCheck")
    public void testShouldCheckoutAndProceedToOverview(
                                                       String productName,
                                                       String firstName,
                                                       String lastName,
                                                       String zipCode,
                                                       String expectedPageTitle) {

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String currentPageTitle = sauceDemoLoginPage
            .doLogin(standardUser.username(), standardUser.password())
            .detailInventoryProduct(productName)
            .addInventoryProductToCart()
            .goToCart()
            .goToCheckoutInformationPage()
            .fillCheckoutAndContinue(firstName, lastName, zipCode)
                .getPageTitle();

        assertThat(currentPageTitle).isEqualTo(expectedPageTitle);
    }

    /**
     * Description: This test is responsible for present all details of purchase on overview.
     **/
    @ParameterizedTest(name = "Data: {0}, {1}, {2}, {3}, {4}, {5}")
    @MethodSource("data.OverviewTestData#dataForOverviewFormDataCheck")
    public void testShouldVerifyProductDetailsDuringOverview (
                                                              String productQty,
                                                              String productName,
                                                              String productDescription,
                                                              String productPrice,
                                                              String firstName,
                                                              String lastName,
                                                              String zipCode,
                                                              String summarySubtotal,
                                                              String summaryTax,
                                                              String summaryTotal){

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage (browser);

        List<String> expectedProductDetails = new ArrayList<String>();
            expectedProductDetails.add(productQty);
            expectedProductDetails.add(productName);
            expectedProductDetails.add(productDescription);
            expectedProductDetails.add(productPrice);
            expectedProductDetails.add(summarySubtotal);
            expectedProductDetails.add(summaryTax);
            expectedProductDetails.add(summaryTotal);

        List<String> currentproductDetails =
                sauceDemoLoginPage
                        .doLogin(standardUser.username(), standardUser.password())
                        .addItemToCartWithoutDetailing(productName)
                        .goToCart()
                        .goToCheckoutInformationPage()
                        .fillCheckoutAndContinue(firstName, lastName, zipCode)
                        .getProductDetails();

        assertThat(currentproductDetails).isEqualTo(expectedProductDetails);
    }

    /**
     * Description: This test should do the complete order scenario.
     **/
    @Tags({@Tag("smoke"), @Tag("regression")})
    @ParameterizedTest(name = "Data: {0}, {1}, {2}, {3}, {4}")
    @MethodSource("data.OverviewTestData#dataForOverviewPageCheckingAfterSuccessfulPurchase")
    public void testShouldCompleteOrderCorrectly (
                                                  String productName,
                                                  String firstName,
                                                  String lastName,
                                                  String zipCode,
                                                  String expectedSuccessMessage) {

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String currentSuccessMessage = sauceDemoLoginPage
                .doLogin(standardUser.username(), standardUser.password())
                .addItemToCartWithoutDetailing(productName)
                .goToCart()
                .goToCheckoutInformationPage()
                .fillCheckoutAndContinue(firstName, lastName, zipCode)
                .goToCompletePage()
                .getSuccessMessage();

        assertThat(currentSuccessMessage).isEqualTo(expectedSuccessMessage);
    }
}