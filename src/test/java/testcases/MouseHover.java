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

public class MouseHover {
	
	public static String browser = "chrome"; //excel sheet
	public static WebDriver driver;
	
	public static boolean isElementPresent(By by) {

		int size = driver.findElements(by).size();
		if(size == 0) {
			return false;			
		} else {
			return true;
		}

	}

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
		
		//Navigate to this site for sample checkbox.
		driver.get(" https://americangolf.co.uk");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Use Actions class to hover the mouse to Golf Clubs.
		Actions action = new Actions(driver);
		WebElement GolfClubs = driver.findElement(By.xpath("//*[@id='header-navigation']/div[1]/ul/li[2]/a"));
		action.moveToElement(GolfClubs).build().perform();
		
		//Click Drivers link under Golf Clubs.
		WebElement linkDrivers = driver.findElement(By.xpath("//div[@id='CLUBS_1']//span[@class='name'][contains(text(),'Drivers')]"));
		linkDrivers.click();
		
		//Hover the mouse to Clothings & Shoes
		WebElement ClothingsShoes = driver.findElement(By.xpath("//a[@class='a-level-1'][contains(text(),'Clothing & Shoes')]"));
		action.moveToElement(ClothingsShoes).build().perform();
		
		//Click Shirts link under Clothings & Shoes
	}

}
