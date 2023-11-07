import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchSortAssertion {

    private final String JUNIT5_TEST_CODE = """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
            """;
    @BeforeAll
    static void configs(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void findInWikiPageJUnit5Test() {

        // Откройте страницу Selenide в Github
        open("/selenide/selenide");

        //Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));

        // Открыть страницу SoftAssertions
        $("a[href='/selenide/selenide/wiki/SoftAssertions']").click();
        // проверить что внутри есть текст "3. Using JUnit5 extend test class"
        $("#user-content-3-using-junit5-extend-test-class").shouldHave(text("3. Using JUnit5 extend test class"));

        // Найти следующий элемент после id=user-content-3-using-junit5-extend-test-class
        // и проверить что он содержит пример кода для JUnit5
        $("#user-content-3-using-junit5-extend-test-class").sibling(0).shouldHave(text(JUNIT5_TEST_CODE));
    }
}
