package helpers.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static java.util.Arrays.asList;

public class TestIdListener implements IAnnotationTransformer {
    private static final String[] specifiedTests = System.getProperty("tests", "").replace(" ", "").split(",");

    public static boolean isSpecificTestsRun() {
        return specifiedTests.length != 0 && !specifiedTests[0].equals("");
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        if (isSpecificTestsRun()) {
            annotation.setEnabled(false);
            for (String group : specifiedTests) {
                if (asList(annotation.getGroups()).contains(group)) {
                    annotation.setEnabled(true);
                    break;
                }
            }
        }
    }
}
