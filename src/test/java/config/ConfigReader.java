package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Centralized configuration reader for test properties.
 */
public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                logger.warn("config.properties not found, using defaults");
            }
        } catch (IOException e) {
            logger.error("Error loading config.properties", e);
        }
    }

    public static String getBrowser() {
        return System.getProperty("browser",
                properties.getProperty("browser", "chrome"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(
                System.getProperty("headless",
                        properties.getProperty("headless", "false")));
    }

    public static long getImplicitWait() {
        return Long.parseLong(properties.getProperty("implicit.wait", "10"));
    }

    public static long getPageLoadTimeout() {
        return Long.parseLong(properties.getProperty("page.load.timeout", "30"));
    }

    public static String getScreenshotDir() {
        return properties.getProperty("screenshot.dir", "target/screenshots");
    }

    public static String getTestDataDir() {
        return properties.getProperty("testdata.dir", "src/test/resources/testdata");
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
