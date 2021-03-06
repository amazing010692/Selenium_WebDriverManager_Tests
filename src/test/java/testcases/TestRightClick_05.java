package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestRightClick_05 {
	
	public static String browser = "chrome"; //excel sheet
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		
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
		
		//Navigate to this site for right clicking of an object.
		driver.get("http://deluxe-menu.com/popup-mode-sample.html");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Right click in an image.
		Actions action = new Actions(driver);
		WebElement image = driver.findElement(By.xpath("//p[contains(text(),'Click the image to show the menu')]"));
		action.contextClick(image).perform();
		System.out.println("Successfully right click an image.");
		
		//Move mouse to "Product Info".
		WebElement optionProductInfo = driver.findElement(By.xpath("//td[@id='dm2m1i1tdT']"));
		action.moveToElement(optionProductInfo).perform();
		
		//Move mouse to "Installation".
		WebElement optionInstallation = driver.findElement(By.xpath("//td[@id='dm2m2i1tdT']"));
		action.moveToElement(optionInstallation).perform();
		
		//Click "How To Setup".
		WebElement optionHowToSetup = driver.findElement(By.xpath("//td[@id='dm2m3i1tdT']"));
		action.click(optionHowToSetup).perform();
		
		//The title of each window including the parent window gets printed.
		java.util.Iterator<String> iter = driver.getWindowHandles().iterator();	
		while(iter.hasNext()) {
			driver.switchTo().window(iter.next());
			System.out.println(driver.getTitle());
		}
		
		//Quits the WebDriver session.
		driver.quit();
	}

}
