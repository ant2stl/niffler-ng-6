package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;


public class RegistrationWebTest {
    private static final Config CFG = Config.getInstance();

    @Test
    void shouldRegisterNewUser() {
        final String username = "new User" + Math.random();
        final String password = "Qwer1234";
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .goToRegisterPage()
                .register(username, password, password)
                .goToLoginPage()
                .login(username, password)
                .checkMainComponents();
    }

    @Test
    void shouldNotRegisterUserWithExistingUsername() {
        String existingUsername = "duck";
        String password = "Qwer1234";
        String expectedErrorText = "Username `" + existingUsername + "` already exists";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .goToRegisterPage()
                .register(existingUsername, password, password)
                .checkErrorText(expectedErrorText);
    }

    @Test
    void shouldShowErrorIfPasswordAndConfirmPasswordAreNotEqual() {
        final String username = "new User" + Math.random();
        final String password = "Qwer1234";
        String expectedErrorText = "Passwords should be equal";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .goToRegisterPage()
                .register(username, password, "Qwer12345")
                .checkErrorText(expectedErrorText);
    }
}