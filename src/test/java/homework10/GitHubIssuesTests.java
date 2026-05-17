package homework10;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.WebSteps;
import testData.TestData;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class GitHubIssuesTests {
    TestData testData = new TestData();

    @Test
    @DisplayName("Тест Selenide")
    @Owner("Quastrado")
    @Tag("test")
    public void SelenideIssuesTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(testData.startUrl);
        $(".header-search-button").click();
        $("#query-builder-test").setValue(testData.searchQuery);
        $("#query-builder-test").submit();
        $(linkText(testData.searchQuery)).click();
        $("#issues-tab").click();
        $(withText(testData.issueName)).should(Condition.exist);
    }

    @Test
    @DisplayName("Тест со Steps")
    @Owner("Quastrado")
    @Tag("test")
    public void StepsIssuesTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.clickSearchButton();
        steps.searchRepository();
        steps.clickIssues();
        steps.existIssues();
    }

    @Test
    @DisplayName("Тест с Lambda")
    @Owner("Quastrado")
    @Tag("test")
    public void LambdaIssuesTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открытие главной страницы", () -> {
            open(testData.startUrl);
        });
        step("Вызов поисковой строки", () -> {
            $(".header-search-button").click();
        });
        step("Отправка поискового запроса", () -> {
            $("#query-builder-test").setValue(testData.searchQuery);
            $("#query-builder-test").submit();
        });
        step("Переход во вкладку Issues", () ->{
            $(linkText(testData.searchQuery)).click();
            $("#issues-tab").click();
        });
        step("Проверка наличия Issues", () ->{
            $(withText(testData.issueName)).should(Condition.exist);
        });
    }
}
