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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "SauceDemoLoginDataTest.csv")

public class SauceDemoLoginTest extends Browser {
    private final String filePath = "src/test/testReports/";
    private final String imageExt = ".png";
    private String methodName = "_Undefined_Method_Name";

    private WebDriver browser;
    private SauceDemoLoginPage sauceDemoLoginPage;

    @Before
    public void initBrowser() {
        browser = createChrome();
    }

    @After
    public void tearDown() {
        browser.quit();
    }

    /**
     * Description: This test should verify the system behavior on try to checkout with incorrect values.
     **/
    @Test
    public void testShouldValidateIncorrectLogin(@Param(name = "url") String url,
                                                 @Param(name = "user") String user,
                                                 @Param(name = "password") String password,
                                                 @Param(name = "message") String expectedMessage) {

        methodName = "_testShouldValidateIncorrectLogin";

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        sauceDemoLoginPage
                .accessURL(url)
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
    public void testShouldSuccessfullyLogin(@Param(name = "url") String url,
                                        @Param(name = "user") String user,
                                        @Param(name = "password") String password) {

        methodName = "_testShouldSuccessfullyLogin";

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        boolean isHeaderDisplayed =
                sauceDemoLoginPage
                        .accessURL(url)
                        .doLogin(user, password)
                        .identifyIfHeaderIsDisplayed();

        assertTrue(isHeaderDisplayed);

        Screenshot.takeScreenshot(browser, filePath
                + RandomDateGenerator.generateTimestampToFile()
                + methodName + imageExt);
    }
}
