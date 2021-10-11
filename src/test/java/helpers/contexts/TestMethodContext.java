package helpers.contexts;

import java.lang.reflect.Method;

public class TestMethodContext {
    private static final ThreadLocal<TestMethodContext> context = ThreadLocal.withInitial(() -> new TestMethodContext());

    private TestClassContext testContext;
    private Method testMethod;

    private TestMethodContext() {
    }

    public static void init(Method method) {
        TestMethodContext currentContext = context.get();
        currentContext.testContext = TestClassContext.get();
        currentContext.testMethod = method;
    }

    public static TestMethodContext get() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }

    public static String getMethodName() {
        return get().testMethod.getName();
    }

    public static String getHub() {
        return context.get().testContext.getHub();
    }

    public static String getBrowserName() {
        return context.get().testContext.getBrowserName();
    }

    public static String getBrowserVersion() {
        return context.get().testContext.getBrowserVersion();
    }

    public static String getBrowserSize() {
        return context.get().testContext.getBrowserSize();
    }

    public static String getDevice() {
        return context.get().testContext.getDevice();
    }

    public static String getSkin() {
        return context.get().testContext.getSkin();
    }

    public static String getTypePage() {
        return context.get().testContext.getTypePage();
    }
}
