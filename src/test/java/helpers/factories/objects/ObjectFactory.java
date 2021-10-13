package helpers.factories.objects;

import annotations.Page;
import helpers.contexts.TestMethodContext;
import helpers.factories.WebDriverFactory;
import objects.desktop.pages.MainPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.BaseTest;

import java.lang.reflect.Field;

public class ObjectFactory {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    public static <T extends BaseTest> void initObjects(T test) {
        String typeObject = TestMethodContext.type();
        Class<? extends BaseTest> testClass = test.getClass();
        try {
            for (Field field : testClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Page.class)) {
                    Object[] args = field.getAnnotation(Page.class).args();
                    Class<?> objectClass = field.getType();
                    Object object = ObjectProvider.instance(objectClass, typeObject, args);
                    field.setAccessible(true);
                    field.set(test, object);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static MainPage init(Class<MainPage> objectClass, Object... args) {
        String typeObject = TestMethodContext.type();
        return ObjectProvider.instance(objectClass, typeObject, args);
    }
}
