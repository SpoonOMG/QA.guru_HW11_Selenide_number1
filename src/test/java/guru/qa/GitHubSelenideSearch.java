package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GitHubSelenideSearch {
    @BeforeAll
    static void TestConfiguration() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Selenide.clearBrowserCookies();
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://github.com/";
    }

    @Test
    void GitHubSelenideSearchTest() {
        step("Откройте страницу Slenide в github", () -> {
            open("/selenide/selenide");
        });
        step("Перейдите в раздел Wiki проекта", () -> {
            $("#wiki-tab").click();
        });
        step("Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions", () -> {
            $(byText("Show 2 more pages…")).click();
            $("#wiki-pages-box").shouldHave(Condition.text("SoftAssertions"));
        });
        step("Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5", () -> {
            $("#wiki-pages-box").$(byText("SoftAssertions")).click();
            $(".markdown-body").shouldHave(Condition.text("Using JUnit5"));
        });
    }
}
