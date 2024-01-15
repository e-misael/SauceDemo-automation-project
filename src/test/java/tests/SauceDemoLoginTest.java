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

import static org.assertj.core.api.Assertions.assertThat;

public class SauceDemoLoginTest extends Browser {

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
     * Description: This test should verify the system behavior on try to check out with incorrect values.
     **/
    @ParameterizedTest(name = "User: {0}, Password: {1}")
    @MethodSource("data.UserDataFactory#invalidUsers")
    public void testShouldValidateIncorrectLogin(
                                                 String user,
                                                 String password,
                                                 String expectedMessage) {

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        sauceDemoLoginPage
            .fillUser(user)
            .fillPassword(password)
            .clickAtLogin();

        String receivedMessage = sauceDemoLoginPage.getValidationMessage();

        assertThat(receivedMessage).isEqualTo(expectedMessage);
    }

    /**
     * Description: Login correctly.
     **/
    @Test
    public void testShouldSuccessfullyLogin() {

        sauceDemoLoginPage = new SauceDemoLoginPage(browser);

        boolean isHeaderDisplayed =
                sauceDemoLoginPage
                    .doLogin(standardUser.username(), standardUser.password())
                    .identifyIfHeaderIsDisplayed();

        assertThat(isHeaderDisplayed).isTrue();
    }
}