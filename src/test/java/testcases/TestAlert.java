package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAlert {
	
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
			
		//Navigate to this site for sample alert.
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Click the Sign-In button so that the JavaScript Alert will pop-put.
		WebElement buttonSignIn = driver.findElement(By.xpath("//input[@name='proceed']"));
		buttonSignIn.click();
		
		//Print the text seen in the alert message.
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		//System.out.println(driver.switchTo().alert().getText());
		
		//Click the OK button in the pop-up
		alert.accept();
		//driver.switchTo().alert().accept();
		System.out.println("OK button has been clicked.");
		
		//Navigate to this site for sample alert with explicit wait.
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Click the Sign-In button so that the JavaScript Alert will pop-put.
		WebElement buttonSignIn1 = driver.findElement(By.xpath("//input[@name='proceed']"));
		buttonSignIn1.click();
		
		//Apply Explicit wait until alert is present.
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
		
		//Print the text seen in the alert message.
		System.out.println(alert1.getText());
		//System.out.println(driver.switchTo().alert().getText());
		
		//Click the OK button in the pop-up
		alert1.accept();
		//driver.switchTo().alert().accept();
		System.out.println("OK button has been clicked.");
		
	}

}
