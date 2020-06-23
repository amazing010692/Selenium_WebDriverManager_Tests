package testcases;

import java.util.Iterator;
import java.util.Set;
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

public class TestTabsPopups {
	
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
			
		//Navigate to this site wherein if you click the child window, it will automatically open in a new tab.
		driver.get("https://www.hdfc.com/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Click the Close button.
		WebElement closeButton = driver.findElement(By.xpath("//*[@id=\"HomepageModalVideo\"]/div/div/div[1]/button"));
		closeButton.click();
		
		//Click the Blogs link and it will open in a new tab.
		WebElement linkBlogs = driver.findElement(By.xpath("(//a[@href='https://www.hdfc.com/blog'])[2]"));
		linkBlogs.click();
				
		//The console first prints the title of child window and then the title of the parent window.
		java.util.Iterator<String> iterate = driver.getWindowHandles().iterator();
		String parentID = iterate.next();			//first iterate.next() points to current parent window
		String childID = iterate.next();			//second iterate.next() points to current child window
				
		driver.switchTo().window(childID);			//switches to child window
		System.out.println(driver.getTitle());		//prints title of child window
				
		driver.switchTo().window(parentID);			//switches back to parent window
		System.out.println(driver.getTitle());		//prints title of parent window
				
		driver.switchTo().window(childID);			//switches to child window
		driver.close();								//close the child window
		driver.switchTo().window(parentID);			//switches back to parent window
		
		//Navigate to this site for another approach in handling new windows and new tabs.
		driver.get("https://www.hdfcbank.com/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Store in a Set and Iterator class to manage the window handles.
		System.out.println("-----Generating Window IDs from the 1st window-----");
		Set<String> windowIDs = driver.getWindowHandles();
		Iterator<String> iterate1 = windowIDs.iterator();
		
		//Print the name of the first window.
		String firstWindow = iterate1.next();
		System.out.println(firstWindow);
		
		//Click the Login button.
		WebElement buttonLogin = driver.findElement(By.xpath("//button[@class='btn btn-primary login-btn ng-scope']"));
		buttonLogin.click();
		System.out.println("Log In button has been successfully clicked.");
		
		//Click the "KNOW MORE" button and open it in a new window.
		WebElement buttonKnowMore = driver.findElement(By.xpath("//a[@class='btn-default register-url']"));
		Actions action = new Actions(driver);
		action.keyDown(Keys.SHIFT).click(buttonKnowMore).keyUp(Keys.SHIFT).build().perform();
		System.out.println("The KNOW MORE button has been successfully clicked and it open in a new window.");
		
		//Switch the WebDriver focus to the newly opened window.
		
		//Click the Online Banking link and it will open in a new tab.
		WebElement linkOnlineBanking = driver.findElement(By.xpath("//a[contains(text(),'Online Banking')]"));
		String ctrlEnter = Keys.chord(Keys.CONTROL,Keys.ENTER);
		linkOnlineBanking.sendKeys(ctrlEnter);
		System.out.println("The link for Online Banking has been successfully open in a new tab.");
		

		

	}

}
