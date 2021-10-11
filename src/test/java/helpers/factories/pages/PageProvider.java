package helpers.factories.pages;

import helpers.factories.WebDriverFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class PageProvider {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    @SuppressWarnings("unchecked")
    public static <T> T instance(final Class<T> pageClass, String typePage, final Object... args) {
        Class<T> instanceClass = pageClass;
        String instanceClassName = pageClass.getName();
        try {
            if (!typePage.equals("desktop")) {
                instanceClassName = instanceClassName.replaceFirst(".desktop.", String.format(".%s.", typePage))
                        + StringUtils.capitalize(typePage);
            }
            instanceClass = (Class<T>) Class.forName(instanceClassName);
        } catch (ClassNotFoundException e) {
            logger.debug("Can not find class {}", instanceClassName);
        }
        try {
            final T pageObject = ConstructorUtils.invokeConstructor(instanceClass, args);
            if (pageObject == null) {
                throw new NoSuchMethodException("Can not find matching accessible constructor");
            }
            return pageObject;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error("Can not create instance of class [{}] with arguments", instanceClassName, args);
            throw new RuntimeException("Can not create instance of class [" + instanceClassName + "] with arguments " + args, e
            );
        }
    }
}
