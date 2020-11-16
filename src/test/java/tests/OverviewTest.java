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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "OverviewDataTest.csv")

public class OverviewTest extends Browser {
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
     * Description: This test is responsible for do checkout and verify if the correct page is shown.
     **/
    @Test
    public void testShouldCheckoutAndProceedToOverview(@Param(name = "url") String url,
                                                       @Param(name = "user") String user,
                                                       @Param(name = "password") String password,
                                                       @Param(name = "productName") String productName,
                                                       @Param(name = "firstname") String firstName,
                                                       @Param(name = "lastname") String lastName,
                                                       @Param(name = "zipcode") String zipCode,
                                                       @Param(name = "pagetitle") String expectedPageTitle) {

        methodName = "_testShouldCheckoutAndProceedToOverview";

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String pageTitle = sauceDemoLoginPage
                .accessURL(url)
                .doLogin(user, password)
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
    @Test
    public void testShouldVerifyProductDetailsDuringOverview (@Param(name = "url") String url,
                                                              @Param(name = "user") String user,
                                                              @Param(name = "password") String password,
                                                              @Param(name = "productQty") String productQty,
                                                              @Param(name = "productName") String productName,
                                                              @Param(name = "productDescription") String productDescription,
                                                              @Param(name = "productPrice") String productPrice,
                                                              @Param(name = "firstname") String firstName,
                                                              @Param(name = "lastname") String lastName,
                                                              @Param(name = "zipcode") String zipCode,
                                                              @Param(name = "summarySubtotal") String summarySubtotal,
                                                              @Param(name = "summaryTax") String summaryTax,
                                                              @Param(name = "summaryTotal") String summaryTotal){

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
                        .accessURL(url)
                        .doLogin(user, password)
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
    @Test
    public void testShouldCompleteOrderCorrectly (@Param(name = "url") String url,
                                                   @Param(name = "user") String user,
                                                   @Param(name = "password") String password,
                                                   @Param(name = "productName") String productName,
                                                   @Param(name = "firstname") String firstName,
                                                   @Param(name = "lastname") String lastName,
                                                   @Param(name = "zipcode") String zipCode,
                                                   @Param(name = "message") String expectedSuccessMessage) {

        methodName = "_testShouldCompleteOrderCorrectly";

        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        String successMessage = sauceDemoLoginPage
                .accessURL(url)
                .doLogin(user, password)
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