package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameInput = $("input[name='username']");
    private final SelenideElement passwordInput = $("input[name='password']");
    private final SelenideElement submitButton = $("button[type='submit']");
    private final SelenideElement registerButton = $("a[href='/register']");
    private final SelenideElement loginHeader = $(".header");


    public MainPage login(String username, String password) {
        setUsername(username);
        setPassword(password);
        submitButton.click();
        return new MainPage();
    }

    public RegisterPage goToRegisterPage() {
        registerButton.click();
        return new RegisterPage();
    }

    public LoginPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public LoginPage checkCurrentPage() {
        loginHeader.shouldHave(text("Log in"));
        return this;
    }
}
