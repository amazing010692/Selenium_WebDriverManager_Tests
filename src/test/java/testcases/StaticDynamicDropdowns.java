package testcases;

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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StaticDynamicDropdowns {
	
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
		
		//Navigate to this site for sample static dropdown with "select" tag.
		driver.get("http://echoecho.com/htmlforms11.htm");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());

		//Different ways to select an option from drowdown list.
		WebElement dropdownMenu = driver.findElement(By.xpath("//select[@name='dropdownmenu']"));
		Select select = new Select(dropdownMenu);
		
		//Select by value will select the value indicated.
		Thread.sleep(1000);
		select.selectByValue("Cheese");
		System.out.println("Cheese is selected.");
		
		//Select by visible text will literally select the exact text fron the dropdown list.
		Thread.sleep(1000);
		select.selectByVisibleText("Milk");
		System.out.println("Milk is selected.");
		
		//If index is 0, the first option from the dropdown list will be selected which is "Butter".
		Thread.sleep(1000);
		select.selectByIndex(0);
		System.out.println("Butter is selected.");
		
		//Navigate to this site for sample dynamic dropdown with no "select" tag.
		driver.get("https://www.jotformpro.com/form/43514646570961?");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Click the 1st Dropdown List.
		WebElement Capacity = driver.findElement(By.xpath("//span[@role='presentation'])[1]"));
		Capacity.click();
		
		//Select Small capacity from the Dropdown List.
		WebElement smallCapacity = driver.findElement(By.xpath("//span[@aria-activedescendant='select2-5x31-result-at2h-Small Capacity or Families']"));
		smallCapacity.click();
	}

}
