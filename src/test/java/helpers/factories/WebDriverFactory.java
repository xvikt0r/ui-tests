package helpers.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class WebDriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private static final int MAX_ATTEMPTS_CREATE_DRIVER = 3;

    public static WebDriver createDriver(DesiredCapabilities capabilities, URL hubUrl) {
        logger.info("Create driver: {}", capabilities);
        for (int attempt = 1; attempt <= MAX_ATTEMPTS_CREATE_DRIVER; attempt++) {
            RemoteWebDriver driver;
            try {
                driver = new RemoteWebDriver(hubUrl, capabilities);
            } catch (WebDriverException e) {
                logger.warn("Attempt to create driver: {}", attempt);
                logger.warn("Unable to create driver for {}\n{}\n{}", capabilities.getBrowserName(), e.getMessage(), e.getStackTrace());
                continue;
            } catch (Exception e) {
                logger.warn("Unable to create driver for {}\n{}\n{}", capabilities.getBrowserName(), e.getMessage(), e.getStackTrace());
                break;
            }
            logger.info("Driver was created. Session id: {}, capabilities: {}", driver.getSessionId(), driver.getCapabilities());
            return driver;
        }
        logger.error("Driver {} didn't create.", capabilities.getBrowserName());
        System.exit(1);
        throw new RuntimeException("Driver didn't create.");
    }

    public static void adjustBrowserSize(WebDriver driver, String browserSize) {
        if (browserSize != null && !browserSize.isEmpty()) {
            String[] dimension = browserSize.split("x");
            int width = Integer.parseInt(dimension[0]);
            int height = Integer.parseInt(dimension[1]);
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
        } else {
            logger.warn("Unable to adjust browser size: {} ", browserSize);
        }
    }
}
