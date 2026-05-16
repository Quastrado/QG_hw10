package homework10;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import testData.TestData;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class GitHubIssuesTests {
    TestData testData = new TestData();

    @Test
    public void SelenideIssuesTest() {
        open("https://github.com");
        $(".header-search-button").click();
        $("#query-builder-test").setValue(testData.searchQuery);
        $("#query-builder-test").submit();
        $(linkText(testData.searchQuery)).click();
        $("#issues-tab").click();
        $(withText(testData.issueName)).should(Condition.exist);
    }
}
