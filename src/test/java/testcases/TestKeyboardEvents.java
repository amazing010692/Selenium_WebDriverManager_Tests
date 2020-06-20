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
		
		//Navigating back in browser 
		Thread.sleep(3000);
	    driver.navigate().back();
	    
		//Click outside the Input field and hit "CTRL + A" and "CTRL + C".
	    Thread.sleep(3000);
		WebElement fieldOutside = driver.findElement(By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div"));
		fieldOutside.click();
		System.out.println("Successfully clicked the outside field.");
		action.sendKeys(Keys.chord(Keys.CONTROL + "A")).perform();
		action.sendKeys(Keys.chord(Keys.CONTROL + "C")).perform();
		
		//In the Password field, hit "CTRL + V".
		WebElement fieldPassword = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
		fieldPassword.click();
		action.sendKeys(Keys.chord(Keys.CONTROL + "V")).perform();
		WebElement viewPass = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/span/div/span/span/span[2]/svg"));
		viewPass.click();
		
	}

}
