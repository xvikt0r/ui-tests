package tests;

import helpers.factories.pages.PageFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import objects.desktop.pages.MainPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Google")
@Feature("Google search")
public class GoogleTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    MainPage mainPage;

    @BeforeClass(alwaysRun = true, description = "Инициализация PageObjects")
    public void initPages() {
        mainPage = PageFactory.initPage(MainPage.class);
    }

    @BeforeMethod(alwaysRun = true, description = "Открыть страницу")
    public void setUp() {
        mainPage
                .open();
    }

    @Test(groups = "smoke", description = "Поиск на главной страницы Google")
    public void googleSearch() {
        mainPage
                .fillQueryField("Test text")
                .clickSearchButton()
                .searchResultsShouldBeVisible();
    }
}
