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

public class TestWebTable {
	
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
		
		driver.get("https://money.rediff.com/gainers/bsc/daily/groupa");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		List<WebElement> rowNum = driver.findElements(By.xpath("//*[@class='dataTable']/tbody/tr"));
		System.out.println("Total rows are: " + rowNum.size());
		
		List<WebElement> colNum = driver.findElements(By.xpath("//*[@class='dataTable']/tbody/tr[1]/td"));
		System.out.println("Total columns are: " + colNum.size());
		
		for(int rows = 1; rows <= rowNum.size(); rows++) {
			for(int cols = 1; cols <= colNum.size(); cols++) {
				System.out.print(driver.findElement(By.xpath("//*[@class='dataTable']/tbody/tr["+ rows +"]/td["+ cols +"]")).getText() + "  ");
			}
			System.out.println();
		}

	}

}
