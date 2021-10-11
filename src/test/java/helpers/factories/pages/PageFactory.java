package helpers.factories.pages;

import annotations.Page;
import helpers.contexts.TestClassContext;
import helpers.contexts.TestMethodContext;
import helpers.factories.WebDriverFactory;
import objects.desktop.pages.MainPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.BaseTest;

import java.lang.reflect.Field;
import java.util.Optional;

public class PageFactory {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    public static <T extends BaseTest> void initPages(T test) {
        String typePage = Optional.of(TestClassContext.getTypePage()).orElse(TestMethodContext.getTypePage());
        Class<? extends BaseTest> testClass = test.getClass();
        try {
            for (Field field : testClass.getDeclaredFields()) {

                if (field.isAnnotationPresent(Page.class)) {
                    Object[] args = field.getAnnotation(Page.class).args();
                    Class<?> pageClass = field.getType();
                    Object page = PageProvider.instance(pageClass, typePage, args);
                    field.setAccessible(true);
                    field.set(test, page);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static MainPage initPage(Class<MainPage> pageClass, Object... args) {
        String typePage = Optional.of(TestClassContext.getTypePage()).orElse(TestMethodContext.getTypePage());
        return PageProvider.instance(pageClass, typePage, args);
    }
}
