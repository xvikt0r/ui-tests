package helpers.contexts;

import org.testng.ITestContext;

public final class TestClassContext {
    private static final ThreadLocal<TestClassContext> context = new ThreadLocal<>();

    private final ITestContext testContext;
    private final String hub;
    private final String browserName;
    private final String browserVersion;
    private final String browserSize;
    private final String device;
    private final String skin;
    private final String typePage;

    public TestClassContext(ITestContext testContext, String hub, String browserName, String browserVersion, String browserSize, String device, String skin, String typePage) {
        this.testContext = testContext;
        this.hub = hub;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.browserSize = browserSize;
        this.device = device;
        this.skin = skin;
        this.typePage = typePage;
    }

    public static void init(
            ITestContext testContext,
            String hub,
            String browserName,
            String browserVersion,
            String browserSize,
            String device,
            String skin,
            String typePage
    ) {
        context.set(new TestClassContext(
                testContext,
                hub,
                browserName,
                browserVersion,
                browserSize,
                device,
                skin,
                typePage
        ));
    }

    public static TestClassContext get() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }

    public static String getHub() {
        return context.get().hub;
    }

    public static String getBrowserName() {
        return context.get().browserName;
    }

    public static String getBrowserVersion() {
        return context.get().browserVersion;
    }

    public static String getBrowserSize() {
        return context.get().browserSize;
    }

    public static String getDevice() {
        return context.get().device;
    }

    public static String getSkin() {
        return context.get().skin;
    }

    public static String getTypePage() {
        return context.get().typePage;
    }

    @Override
    public String toString() {
        return "TestClassContext{" +
                "testContext=" + testContext +
                ", hub='" + hub + '\'' +
                ", browserName='" + browserName + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                ", browserSize='" + browserSize + '\'' +
                ", device='" + device + '\'' +
                ", skin='" + skin + '\'' +
                ", typePage='" + typePage + '\'' +
                '}';
    }
}
