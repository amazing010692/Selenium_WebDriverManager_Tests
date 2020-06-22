package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestIframe_06 {
	
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
			
		//Navigate to this site for sample IFrame.
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_submit_get");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Switch to frame by id name.
		driver.switchTo().frame("iframeResult");
		
		//Click the "Try It" button.
		WebElement buttonTryIt = driver.findElement(By.xpath("/html/body/button"));
		buttonTryIt.click();
		System.out.println("Try It button has been successfully clicked.");
		
		//Switch the focus outside the iframe or to the main web page.
		driver.switchTo().defaultContent();
		System.out.println("Successfully switched to the main webpage and already outside the iframe.");
		
		//Store all elements with an <iframe> tag.
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		
		//Print the total number of frames.
		System.out.println("The total number of frames are: " + frames.size());
		
		//Print all the id names of the iframe present in a webpage.
		System.out.println("***The list of iframe by ID are:***");
		for(WebElement frame : frames) {
			System.out.println(frame.getAttribute("id"));
		}
		
		System.out.println("===========IFRAME ASSIGNMENT===========");
		
		//Navigate to this site for iframe assignment where no id  is present.
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_submit_get");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Switch to frame by index.
		driver.switchTo().frame(0);
		
		//Click the "Try It" button.
		WebElement buttonTryIt1 = driver.findElement(By.xpath("/html/body/button"));
		buttonTryIt1.click();
		System.out.println("Try It button has been successfully clicked.");
		
		//Switch the focus outside the iframe or to the main web page.
		driver.switchTo().parentFrame();
		System.out.println("Successfully switched to the parent frame.");
		
		//Store all elements with an <iframe> tag.
		List<WebElement> frames1 = driver.findElements(By.tagName("iframe"));
		
		//Print the total number of frames.
		System.out.println("The total number of frames are: " + frames1.size());
		
		//Print all the id names of the iframe present in a webpage.
		System.out.println("***The list of iframe by ID are:***");
		for(WebElement frame1 : frames1) {
			System.out.println(frame1.getAttribute("id"));
		}
		
		//Quits WebDriver session.
		driver.quit();
	}

}
