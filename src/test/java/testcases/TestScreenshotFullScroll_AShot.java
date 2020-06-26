package testcases;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestScreenshotFullScroll_AShot {
	
	//Utility for capturing scrollable full page screenshot.
	public static void captureFullScrollScreenshot() throws IOException {
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_") + "_full.png";
		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(fpScreenshot.getImage(),"PNG", new File(".//screenshot//" + fileName));
		System.out.println("Successfully captured a full scrollable screenshot.");
	}
	
	//Utility for capturing screenshot.
	public static void captureScreenshot() throws IOException {
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(".//screenshot//" + fileName));
		System.out.println("Successfully captured a full page screenshot.");
	}
	
	//Utility to capture a particular element screenshot.
	public static void drawRedBorder(By by) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(by));
		System.out.println("Successfully created a border around the specified element.");
	    }
	public static void captureElementScreenshot(By by) throws IOException {
		drawRedBorder(by);
		WebElement element = driver.findElement(by);
		File screenshot_ele = element.getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_") + "_elem.jpg";
		File screenshotLocation = new File(".//screenshots//" + fileName);
		FileUtils.copyFile(screenshot_ele, screenshotLocation);
		System.out.println("Successfully captured a screenshot of the particular element.");
	    }
	
	public static String browser = "chrome"; //excel sheet
	public static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		} else if(browser.equals("ie")) {			
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			
		} else if(browser.equals("edge")) {			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		
		} else if(browser.equals("opera")) {			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			OperaOptions options = new OperaOptions();
			options.setBinary("C:\\Users\\hello\\AppData\\Local\\Programs\\Opera\\64.0.3417.73\\opera.exe");
			capabilities.setCapability(OperaOptions.CAPABILITY, options);
			
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver(options);
		
		}
		
		//Pre-conditions | Maximize the browser and apply implicit waits.
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		//Navigate to this site for sample site to capture element screenshot.
		driver.get("https://www.google.com/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Capture a full scrollable screenshot of the whole page.
		captureFullScrollScreenshot();
		
		//Capture screenshot of the whole page.
		captureScreenshot();
		
		//Capture the particular element screenshot and store in a current directory.
		captureElementScreenshot(By.xpath("//*[@id=\'hplogo\']"));
				
		//Navigate to this site for another sample site to capture element screenshot.
		driver.get("http://way2automation.com/index.html");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		
		//Capture a full scrollable screenshot of the whole page.
		captureFullScrollScreenshot();
		
		//Capture screenshot of the whole page.
		captureScreenshot();
		
		//Capture the particular element screenshot and store in a current directory.
		captureElementScreenshot(By.xpath("//img[@class='header-logo__img']"));
		
		//Quits the WebDriver session.
		driver.quit();

	}

}
