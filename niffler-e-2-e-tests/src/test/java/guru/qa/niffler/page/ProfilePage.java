package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {
    private final ElementsCollection activeCategories = $$(".MuiChip-colorPrimary");
    private final ElementsCollection archivedCategories = $$(".MuiChip-colorDefault");
    private final SelenideElement showArchivedSwitcher = $(".MuiSwitch-colorPrimary");

    public ProfilePage showArchivedCategories() {
        return this;
    }

    public void checkActiveCategory(String categoryName) {
        activeCategories.find(text(categoryName))
                .shouldBe(visible);
    }

    public void checkArchivedCategory(String categoryName) {
        showArchivedSwitcher.click();
        archivedCategories.find(text(categoryName))
                .shouldBe(visible);
    }
}