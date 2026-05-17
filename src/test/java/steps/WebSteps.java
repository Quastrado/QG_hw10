package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import testData.TestData;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    TestData testData = new TestData();

    @Step("Открытие главной страницы")
    public void openMainPage() {
        open(testData.startUrl);
    }

    @Step("Вызов поисковой строки")
    public void clickSearchButton() {
        $(".header-search-button").click();
    }

    @Step("Отправка поискового запроса")
    public void searchRepository() {
        $("#query-builder-test").setValue(testData.searchQuery);
        $("#query-builder-test").submit();
    }

    @Step("Переход во вкладку Issues")
    public void clickIssues() {
        $(linkText(testData.searchQuery)).click();
        $("#issues-tab").click();
    }

    @Step("Проверка наличия Issues")
    public void existIssues() {
        $(withText(testData.issueName)).should(Condition.exist);
    }
}
