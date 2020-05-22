package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InvokeGenericBrowser {
	
	public static String browser = "chrome"; //excel sheet
	public static WebDriver driver;
	
	public static void main(String[] args) {
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\hello\\Documents\\SELENIUM\\Browser_Executables\\chromedriver.exe");
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		
		driver.get("http://way2automation.com/");
		String title = driver.getTitle();
		System.out.println(title.length());
		System.out.println(title);
		
		driver.close();		//close the current browser window
		driver.quit();		//close the current browser window and all related browser in the same session
	}

}
