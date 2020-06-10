package testcases;
 
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		WebDriverWait wait = new WebDriverWait(driver, 20);
		/*
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
		driver.get("https://www.spicejet.com/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		
		//Click the Departure City Dropdown List.
		WebElement departureCity = driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_originStation1']"));
		departureCity.click();
		
		//Select the preferred city.
		WebElement selectBLR = driver.findElement(By.xpath("//a[contains(text(),'Bengaluru (BLR)')]"));
		selectBLR.click();
		
		//Click the Arrival City Dropdown List.
		WebElement arrivalCity = driver.findElement(By.xpath("ctl00_mainContent_ddl_destinationStation1"));
		arrivalCity.click();*/
		
		//Navigate to this site for sample dynamic dropdown with no "select" tag.
		driver.get("https://www.jotformpro.com/form/43514646570961?");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Input full name in the Full Name field.
		WebElement fullName = driver.findElement(By.xpath("//input[@id='input_8']"));
		fullName.sendKeys("Michiko Daimon");
		
		//Input email address in the E-mail field.
		WebElement emailAddress = driver.findElement(By.xpath("//input[@id='input_5']"));
		emailAddress.sendKeys("yonekura.ryoko@tv-asahi-music.co.jp");
		
		//Switch to Dynamic Dropdowns iframe.
		driver.switchTo().frame(0);
		
		//Click the Capacity Dropdown List.
		String xpathArrowDown1 = "//*[@id=\"select2-hq1b-container\"]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathArrowDown1)));
		WebElement ArrowDown1 = driver.findElement(By.xpath(xpathArrowDown1));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click();", ArrowDown1);

	}
 
}