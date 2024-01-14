package tests;

import data.UserDataFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pages.SauceDemoLoginPage;
import pojo.User;
import utils.Browser;
import utils.RandomDateGenerator;
import utils.Screenshot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SauceDemoLoginTest extends Browser {
    private final String filePath = "src/test/testReports/";
    private final String imageExt = ".png";
    private String methodName = "_Undefined_Method_Name";

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
     * Description: This test should verify the system behavior on try to checkout with incorrect values.
     **/
    @ParameterizedTest(name = "User: {0}, Password: {1}")
    @MethodSource("data.UserDataFactory#invalidUsers")
    public void testShouldValidateIncorrectLogin(
                                                 String user,
                                                 String password,
                                                 String expectedMessage) {

        methodName = "_testShouldValidateIncorrectLogin";

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        sauceDemoLoginPage
            .fillUser(user)
            .fillPassword(password)
            .clickAtLogin();

        String receivedMessage = sauceDemoLoginPage.getValidationMessage();

        assertEquals(expectedMessage, receivedMessage);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
    }

    /**
     * Description: Login correctly.
     **/
    @Test
    public void testShouldSuccessfullyLogin() {

        methodName = "_testShouldSuccessfullyLogin";

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        boolean isHeaderDisplayed =
                sauceDemoLoginPage
                    .doLogin(standardUser.username(), standardUser.password())
                    .identifyIfHeaderIsDisplayed();

        assertTrue(isHeaderDisplayed);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
    }
}