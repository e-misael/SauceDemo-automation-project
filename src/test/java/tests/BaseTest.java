package tests;

import data.UserDataFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pojo.User;
import utils.Browser;

public class BaseTest extends Browser {
    protected User standardUser;

    @BeforeEach
    public void initBrowser() {
        browser = createChrome();
        standardUser = UserDataFactory.createStandardUser();
    }

    @AfterEach
    public void tearDown() {
        browser.quit();
    }
}
