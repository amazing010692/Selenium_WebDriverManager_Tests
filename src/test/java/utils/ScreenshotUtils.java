package utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.ConfigReader;

/**
 * Reusable screenshot utility for capturing full-page and element screenshots.
 */
public class ScreenshotUtils {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String fileName = testName + "_" + timestamp + ".png";
        String filePath = ConfigReader.getScreenshotDir() + File.separator + fileName;

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(filePath);
            FileUtils.copyFile(src, dest);
            logger.info("Screenshot saved: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to capture screenshot", e);
        }
        return filePath;
    }

    public static String captureElementScreenshot(WebDriver driver, By locator, String testName) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String fileName = testName + "_element_" + timestamp + ".png";
        String filePath = ConfigReader.getScreenshotDir() + File.separator + fileName;

        try {
            highlightElement(driver, locator);
            WebElement element = driver.findElement(locator);
            File src = element.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(filePath));
            logger.info("Element screenshot saved: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to capture element screenshot", e);
        }
        return filePath;
    }

    public static void highlightElement(WebDriver driver, By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(locator));
    }
}
