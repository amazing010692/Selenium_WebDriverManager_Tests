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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestFindingElements {
	
	public static String browser = "chome"; //excel sheet
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
		
		driver.get("http://gmail.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		WebElement username = driver.findElement(By.id("identifierId"));
		username.sendKeys("java@way2automation.com");
		
		//driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		WebElement nextButtonU = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span"));
		nextButtonU.click();
		
		WebElement password =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		//WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("TestPassword123");
		
		WebElement nextButtonP = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/span/span"));
		nextButtonP.click();
		
		WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/"
				+ "div[1]/div/form/span/section/div/div/div[1]/div[2]/div[2]/span"));
		System.out.println(errorMessage.getText());
		
		Thread.sleep(3000);
		driver.close();
	}

}
