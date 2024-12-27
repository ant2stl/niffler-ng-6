package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;

public class LoginWebTest {
    private static final Config CFG = Config.getInstance();

    @Test
    void mainPageShouldBeDisplayedAfterSuccessLogin() {
        String username = "duck";
        String password = "Qwer1234";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login(username, password)
                .checkMainComponents();
    }

    @Test
    void userShouldStayOnLoginPageAfterLoginWithBadCredentials() {
        String username = "duck";
        String badPassword = "Qwer" + Math.random();

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .setUsername(username)
                .setPassword(badPassword)
                .checkCurrentPage();
    }
}