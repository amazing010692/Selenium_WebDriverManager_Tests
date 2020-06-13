package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class JavaScript_Executor {
	
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
		
		//Navigate to this site for sample javascript executor.
		driver.get("https://ksrtc.in/oprs-web/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Type "BENG" in the Leaving From field.
		WebElement fieldLeavingFrom = driver.findElement(By.xpath("//input[@id='fromPlaceName']"));
		fieldLeavingFrom.sendKeys("BENG");
		
		//Press the Down key twice and print the attribute value.
		fieldLeavingFrom.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		fieldLeavingFrom.sendKeys(Keys.DOWN);
		
		//Create an object of JavascriptExecutor interface by Type casting.
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		//Add the return keyword and store it in a variable.
		String script = "return document.getElementById(\"fromPlaceName\").value;";
		
		//Execute script and print the text in console.
		String text = (String) js.executeScript(script);
		System.out.println(text);
		
		//Navigate to this site for sample javascript executor.
		driver.get("https://ksrtc.in/oprs-web/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Type "BENG" in the Leaving From field.
		WebElement fieldLeavingFrom1 = driver.findElement(By.xpath("//input[@id='fromPlaceName']"));
		fieldLeavingFrom1.sendKeys("BENG");
		
		//Press the Down key twice and print the attribute value.
		fieldLeavingFrom1.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		fieldLeavingFrom1.sendKeys(Keys.DOWN);
		
		//Create an object of JavascriptExecutor interface by Type casting.
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		
		//Add the return keyword and store it in a variable.
		String script1 = "return document.getElementById(\"fromPlaceName\").value;";
		
		//Execute script and print the text in console.
		String text1 = (String) js1.executeScript(script1);
		System.out.println(text1);
		
		/*Keeps scrolling down until "BENGALURU INTERNATION AIRPORT" gets selected. 
		Script tries for 10 runs, than prints ‘Element not found’*/
		int i = 0;
		while(!text1.equalsIgnoreCase("bengaluru inernation airport")) {
			i++;
			fieldLeavingFrom1.sendKeys(Keys.DOWN);
			Thread.sleep(1000);
			text1 = (String) js.executeScript(script1);
			System.out.println(text1);
			if(i > 10)
				break;
		}
		if(i > 10) {
			System.out.println("Element not found");
		} else
			System.out.println("Element found");
	}

}
