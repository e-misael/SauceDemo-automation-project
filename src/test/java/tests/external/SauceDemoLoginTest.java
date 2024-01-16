package tests.internal;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.SauceDemoLoginPage;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;
@Tag("e2e")

public class SauceDemoLoginTest extends BaseTest {
    private SauceDemoLoginPage sauceDemoLoginPage;

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

        var currentMessage = sauceDemoLoginPage.getValidationMessage();

        assertThat(currentMessage).isEqualTo(expectedMessage);
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