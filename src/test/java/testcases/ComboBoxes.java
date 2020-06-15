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

public class ComboBoxes {
	
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
		
		//Navigate to this site for sample combo boxes.
		driver.get("https://www.jobserve.com/in/en/Job-Search/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Click the Industry Dropdown.
		String xpath_inDrop = "//span[@id='ddcl-selInd']//span[@class='ui-dropdownchecklist-selector ui-state-default']";
		WebElement dropdownIndustry = driver.findElement(By.xpath(xpath_inDrop));
		dropdownIndustry.click();
		
		//De-select the "Select All Industries" checkbox.
		WebElement checkboxSelectAll = driver.findElement(By.xpath("//label[contains(text(),'Select All Industries')]"));
		checkboxSelectAll.click();
		
		//Create a string variable and store ‘Education’ value in it.
		String industry = "Education";
		
		//Store all industries in a list.
		List<WebElement> allIndustries = driver.findElements(By.xpath("//div[@id='industryDisplay']/div/div/div/label"));
		
		//Print all the industries using the ‘for’ loop.
		for(int i = 0; i < allIndustries.size(); i++) {
			System.out.println(allIndustries.get(i).getText());
		}
		
		//
	}

}
