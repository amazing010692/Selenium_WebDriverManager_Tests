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

public class Checkboxes_RadioButtons_Links {
	
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
		
		//Navigate to this site for sample checkbox.
		driver.get("https://www.goair.in/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());

		//Select the "Armed Forces" checkbox.
		WebElement ArmedForces = driver.findElement(By.xpath("//label[@for='Armed']"));
		ArmedForces.click();
		
		//Navigate to this site for radio buttons.
		driver.get("http://echoecho.com/htmlforms10.htm");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Store in "count1" variable the number of elements in the 1st group of radio buttons
		int count1 = driver.findElements(By.xpath("//input[@name='group1']")).size();
		
		//Print the number of elements from the first group of radio buttons.
		System.out.println("The number of elements in the 1st group of radio buttons: " + count1);
		
		//This will click each radio button one by one from the 1st group of radio buttons.
		for(int i = 0; i < count1; i++) {
					
			//This will start to select each radio button one by 1.
			driver.findElements(By.xpath("//input[@name='group1']")).get(i).click();
			
			//Print the values from the 1st group of radio buttons.
			List<WebElement> values1 = driver.findElements(By.xpath("//input[@name='group1']"));
			System.out.println("Value #" + (i + 1) + " from the 1st group of radio buttons is: " + values1.get(i).getAttribute("value"));	

		}
		
		//Refresh the page.
		driver.navigate().refresh();
		
		//This will click the "Cheese" option first since "Butter" is selected by default.
		for(int i = 0; i < count1; i++) {
			
			String text = driver.findElements(By.xpath("//input[@name='group1']")).get(i).getAttribute("value");
			if(text.equals("Cheese")) {
				driver.findElements(By.xpath("//input[@name='group1']")).get(i).click();
				System.out.println("Cheese successfully selected!!!");
			}
			
		}
		
		//


	}

}
