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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowTabs_Iterator_Switch {
	
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
		
		//Navigate to this site for links.
		driver.get("https://www.facebook.com");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Store in an integer variable the total number of links.
		int linkCounts = driver.findElements(By.tagName("a")).size();
		
		//Print in the console the total number of links.
		System.out.println("The total number of links in this website is: " + linkCounts);
		
		//Actions class to scroll down in a page.
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.CONTROL.END).perform();
		
		//Store in a "footer" variable the footer section of the page.
		WebElement footer = driver.findElement(By.cssSelector(".footer__info-links.grid-col.grid-col-2"));
		
		//Print in the console the number of links in the footer section
		List<WebElement> footerLinks = footer.findElements(By.tagName("a"));
		System.out.println("The number of links in the footer section is: " + footerLinks.size());
		System.out.println("------Print All Links in the Footer Section Only------");
		
		//Print all the links within the footer section only.
		for(WebElement footerBlock : footerLinks) {
			System.out.println(footerBlock.getText() + " | " + footerBlock.getAttribute("href"));
		}
	}

}
