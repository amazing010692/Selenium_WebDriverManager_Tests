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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDropDownList {

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
		
		/*driver.get("http://way2automation.com/way2auto_jquery/index.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement country = driver.findElement(By.name("country"));
		country.sendKeys("French Guiana");*/
		
		driver.get("https://www.wikipedia.org/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement language = driver.findElement(By.id("searchLanguage"));
		Select select = new Select(language);
		//select.selectByVisibleText("Eesti");
		//select.selectByValue("hi");
		select.selectByIndex(0);
		
		/*Find all option tag in the whole web page. If there are many dropdown list in a page.
		List<WebElement> values = driver.findElements(By.tagName("option"));*/
		
		//Find the option tag in a particular dropdown only.
		List<WebElement> values = language.findElements(By.tagName("option"));
		//In a list, the 1st value is always at the 0th index, and the last value is n-1 
		System.out.println("Total values are: " + values.size());
		
		//Get the 7th index, or the 8th value in the list of languages
		System.out.println(values.get(7).getText());
		
		//Print the list of languages in a Wikipedia page.
		for(int i = 0; i < values.size(); i++ ) {
			//System.out.println(values.get(i).getText());
			System.out.println(values.get(i).getAttribute("lang"));
		}
		
		//Block section links
		WebElement block = driver.findElement(By.xpath("//*[@id=\'www-wikipedia-org\']/div[6]"));
		//Get the number of links within the block section only.
		List<WebElement> links_section = block.findElements(By.tagName("a"));
		System.out.println("----Printling Links in the Block Section----");
		System.out.println("Total links are: " + links_section.size());
		//Print all the links within the block section only.
		for(WebElement linkBlock : links_section) {
			System.out.println(linkBlock.getText() + " -- URL IS -- " + linkBlock.getAttribute("href"));
		}
		
		//Get the number of links in Wikipedia page.
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("----Printling Links in the whole Wikipedia Page----");
		System.out.println("Total links are: " + links.size());		
		//Print all the links in Wikipedia page.
		for(WebElement link : links) {
			System.out.println(link.getText() + " -- URL IS -- " + link.getAttribute("href"));
		}
		
		Thread.sleep(3000);
		driver.close();
	}

}
