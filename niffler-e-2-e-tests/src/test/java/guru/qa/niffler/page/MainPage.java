package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final ElementsCollection tableRows = $("#spendings tbody").$$("tr");
    private final SelenideElement statisticsHeader = $(byText("Statistics"));
    private final SelenideElement historyOfSpendingHeader = $(byText("History of Spendings"));
    private final SelenideElement menuBtn = $("button[aria-label='Menu']");
    private final SelenideElement profileBtn = $("a[href='/profile']");

    public EditSpendingPage editSpending(String spendingDescription) {
        tableRows.find(text(spendingDescription)).$$("td").get(5).click();
        return new EditSpendingPage();
    }

    public void checkThatTableContainsSpending(String spendingDescription) {
        tableRows.find(text(spendingDescription)).should(visible);
    }

    public MainPage checkMainComponents() {
        statisticsHeader.shouldBe(visible);
        historyOfSpendingHeader.shouldBe(visible);
        return this;
    }

    public ProfilePage gotoProfilePage() {
        menuBtn.click();
        profileBtn.click();
        return new ProfilePage();
    }
}
