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

public class TestCheckboxes {

	public static String browser = "chrome"; //excel sheet
	public static WebDriver driver;
	
	public static boolean isElementPresent(By by) {
		/*try {
			driver.findElement(By.xpath(locator));
			return true;
		} catch(Throwable t) {
			return false;
		}*/
		
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
		
		driver.get("http://www.tizag.com/htmlT/htmlcheckboxes.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*WebElement soccerCheckbox = driver.findElement(By.xpath("//div[4]/input[1]"));
		soccerCheckbox.click();
		
		WebElement footballCheckbox = driver.findElement(By.xpath("//div[4]/input[2]"));
		footballCheckbox.click();
		
		WebElement baseballCheckbox = driver.findElement(By.xpath("//div[4]/input[3]"));
		baseballCheckbox.click();
		
		WebElement basketballCheckbox = driver.findElement(By.xpath("//div[4]/input[4]"));
		basketballCheckbox.click();*/
		
		/*for(int i = 1; i <= 4; i++) {
			driver.findElement(By.xpath("//div[4]/input[" + i + "]")).click();
		}*/
		
		/*int i = 1;
		int count = 0;
		
		while(isElementPresent(By.xpath("//div[4]/input[" + i + "]"))) {
			driver.findElement(By.xpath("//div[4]/input[" + i + "]")).click();
			i++;
			count++;
		}
		
		System.out.println("Total checkboxes are: " + count);*/
		
		List<WebElement> checkboxes = driver.findElements(By.name("sports"));
		System.out.println("Total checkboxes are: " + checkboxes.size());
		
		for(WebElement checkbox : checkboxes) {
			checkbox.click();
		}
	}

}
