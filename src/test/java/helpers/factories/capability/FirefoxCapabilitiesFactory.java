package helpers.factories.capability;

import helpers.contexts.TestMethodContext;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxCapabilitiesFactory implements ICapabilitiesFactory {
    @Override
    public DesiredCapabilities getCapabilities() {
        String browserName = TestMethodContext.getBrowserName();
        String browserVersion = TestMethodContext.getBrowserVersion();
        String methodName = TestMethodContext.getMethodName();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName);
        capabilities.setVersion(browserVersion);
        capabilities.setCapability("name", methodName);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.setCapability("moz:webdriverClick", false);
        firefoxOptions.addArguments("--headless");
        firefoxOptions.setAcceptInsecureCerts(true);
        capabilities.merge(firefoxOptions);
        return capabilities;
    }
}
