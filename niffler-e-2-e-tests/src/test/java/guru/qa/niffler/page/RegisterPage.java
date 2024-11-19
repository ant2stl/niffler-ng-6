package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement passwordSubmitInput = $("#passwordSubmit");
    private final SelenideElement passwordSubmitBtn = $(".form__submit");
    private final SelenideElement signInBtn = $(".form_sign-in");
    private final SelenideElement formError = $(".form__error");

    public RegisterPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    public RegisterPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public RegisterPage setPasswordSubmit(String password) {
        passwordSubmitInput.setValue(password);
        return this;
    }

    public RegisterPage submitRegistration() {
        passwordSubmitBtn.click();
        return this;
    }

    public RegisterPage register(String username, String password, String submitPassword) {
        setUsername(username);
        setPassword(password);
        setPasswordSubmit(submitPassword);
        submitRegistration();
        return this;
    }

    public LoginPage goToLoginPage() {
        signInBtn.click();
        return new LoginPage();
    }

    public String getErrorText() {
        return formError.getText();
    }

    public void checkErrorText(String expectedText) {
        formError.shouldHave(text(expectedText));
    }
}