package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class AjaxFieldsDropdowns {
	
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
		
		//Navigate to this site for sample ajax dropdowns.
		driver.get("https://ksrtc.in/oprs-web/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Type "BENG" in the Leaving From field.
		WebElement fieldLeavingFrom = driver.findElement(By.xpath("//input[@id='fromPlaceName']"));
		fieldLeavingFrom.sendKeys("BENG");
		
		//Press the Down key twice and print the attribute value.
		fieldLeavingFrom.sendKeys(Keys.DOWN);
		fieldLeavingFrom.sendKeys(Keys.DOWN);
		System.out.println(fieldLeavingFrom.getAttribute("value"));
		
		//Navigate to this site for another exercise regarding ajax dropdowns.
		driver.get("https://www.makemytrip.com/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Click the From field.
		WebElement linkFrom = driver.findElement(By.xpath("//span[contains(text(),'From')]"));
		linkFrom.click();
		
		//Input" MUM" in the search field then press down key and hit Enter.
		WebElement fieldSearch = driver.findElement(By.xpath("//input[contains(@placeholder,'From')]"));
		fieldSearch.sendKeys("MUM");
		fieldSearch.sendKeys(Keys.DOWN);
		Thread.sleep(1000);
		fieldSearch.sendKeys(Keys.ENTER);
		
		//Navigate to this site for another exercise regarding ajax fields.
		driver.get("https://ph.yahoo.com/?p=us");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Input "hello" in the search text field.
		WebElement fieldSearchText = driver.findElement(By.xpath("//input[@id='header-search-input']"));
		fieldSearchText.sendKeys("hello");
		Thread.sleep(2000);
		
		//Store all the values in the search text field.
		List<WebElement> values = driver.findElements(By.xpath("//div[@type='normal']"));
		
		//Print all the auto-suggestive values in the console.
		for(int i = 0; i < values.size(); i++) {
			System.out.println(values.get(i).getText());
		}
		
		//Quits the WebDriver session.
		driver.quit();
		
	}

}
