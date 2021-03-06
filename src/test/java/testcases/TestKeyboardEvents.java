package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class TestKeyboardEvents {
	
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
		
		//Navigate to this site for sample keyboard events.
		driver.get("https://gmail.com");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Hit Enter key after filling out the Email Address field.
		Actions action = new Actions(driver);
		WebElement fieldEmail = driver.findElement(By.xpath("//input[@id='identifierId']"));
		fieldEmail.sendKeys("trainer@way2automation.com");
		action.sendKeys(Keys.ENTER).perform();
		System.out.println("Email address has been successfully inputted.");
		Thread.sleep(5000);
		
		//Navigate to this site for sample keyboard events.
		driver.get("https://en.wikipedia.org/wiki/Main_Page");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
	    
		//Click outside the Input field and hit "CTRL + A" and "CTRL + C".
		Actions action1 = new Actions(driver);
		WebElement fieldOutside = driver.findElement(By.xpath("//*[@id=\"content\"]"));
		fieldOutside.click();
		System.out.println("Successfully clicked the outside field.");
		Thread.sleep(2000);
		action1.keyDown(Keys.CONTROL).sendKeys(Keys.chord("a")).keyUp(Keys.CONTROL).perform();
		action1.keyDown(Keys.CONTROL).sendKeys(Keys.chord("c")).keyUp(Keys.CONTROL).perform();
		
		//Click in the Search and hit "CTRL + V".
		WebElement fieldSearch = driver.findElement(By.xpath("//input[@id='searchInput']"));
		fieldSearch.click();
		action1.keyDown(Keys.CONTROL).sendKeys(Keys.chord("v")).perform();
		System.out.println("Successfully pasted some contents in the Search Field.");
	
	}

}
