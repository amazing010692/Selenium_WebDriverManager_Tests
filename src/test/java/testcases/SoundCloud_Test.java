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

public class SoundCloud_Test {
	
	public static String browser = "chrome"; //excel sheet
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		
		/*int rep = 1;
		while(rep <2) {	*/
		
		//Repeat the entire scenario for 5 times.
		for(int rep = 1; rep <= 5; rep++ ) {
		
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
			
		//Go to SoundCloud URL.	
		driver.get("https://soundcloud.com/janielle-joy-gregorio/i-miss-you-goblin-ost-duet-cover-by-janielle-joy-gregorio");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
			
		//Click the Play button
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement playButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@role='button'][@title='Play'])[1]")));
		playButton.isDisplayed();
		playButton.click();
		
		//Print out in the console.
		System.out.println("Run #" + rep + ": COMPLETED!");
		Thread.sleep(3000);
		
		//Refresh the browser
		driver.close();
		}
	}

}
