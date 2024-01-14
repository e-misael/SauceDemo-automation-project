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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OverviewTest extends Browser {
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

        methodName = "_testShouldCheckoutAndProceedToOverview";

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String pageTitle = sauceDemoLoginPage
            .doLogin(standardUser.username(), standardUser.password())
            .detailInventoryProduct(productName)
            .addInventoryProductToCart()
            .goToCart()
            .goToCheckoutInformationPage()
            .fillCheckoutAndContinue(firstName, lastName, zipCode)
                .getPageTitle();

        assertEquals(expectedPageTitle, pageTitle);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
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

        methodName = "_testShouldVerifyProductDetailsDuringOverview";

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage (browser);

        List<String> expectedProductDetails = new ArrayList<String>();
            expectedProductDetails.add(productQty);
            expectedProductDetails.add(productName);
            expectedProductDetails.add(productDescription);
            expectedProductDetails.add(productPrice);
            expectedProductDetails.add(summarySubtotal);
            expectedProductDetails.add(summaryTax);
            expectedProductDetails.add(summaryTotal);

        List<String> productDetails =
                sauceDemoLoginPage
                        .doLogin(standardUser.username(), standardUser.password())
                        .addItemToCartWithoutDetailing(productName)
                        .goToCart()
                        .goToCheckoutInformationPage()
                        .fillCheckoutAndContinue(firstName, lastName, zipCode)
                        .getProductDetails();

        assertEquals(expectedProductDetails, productDetails);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
    }

    /**
     * Description: This test should do the complete order scenario.
     **/
    @ParameterizedTest(name = "Data: {0}, {1}, {2}, {3}, {4}")
    @MethodSource("data.OverviewTestData#dataForOverviewPageCheckingAfterSuccessfulPurchase")
    public void testShouldCompleteOrderCorrectly (
                                                  String productName,
                                                  String firstName,
                                                  String lastName,
                                                  String zipCode,
                                                  String expectedSuccessMessage) {

        methodName = "_testShouldCompleteOrderCorrectly";

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String successMessage = sauceDemoLoginPage
                .doLogin(standardUser.username(), standardUser.password())
                .addItemToCartWithoutDetailing(productName)
                .goToCart()
                .goToCheckoutInformationPage()
                .fillCheckoutAndContinue(firstName, lastName, zipCode)
                .goToCompletePage()
                .getSuccessMessage();

        assertEquals(expectedSuccessMessage, successMessage);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
    }
}