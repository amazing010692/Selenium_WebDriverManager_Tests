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

public class TestSliders {
	
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
		
		//Navigate to this site for sample test sliders.
		driver.get("https://jqueryui.com/resources/demos/slider/default.html");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Drag and drop the slider using Actions class.
		Actions action = new Actions(driver);
		WebElement miniSlider = driver.findElement(By.xpath("//*[@id=\"slider\"]/span"));
		action.dragAndDropBy(miniSlider, 400, 0).perform();
		System.out.println("Move the slider 400 offset in the x-axis.");
		
		//Navigate to this site for test sliders.
		driver.get("https://jqueryui.com/resources/demos/slider/default.html");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Drag and drop the slider to the middle measurement of the main horizontal slider.
		WebElement horizontalSlider = driver.findElement(By.xpath("//*[@id=\"slider\"]"));
		WebElement miniSlider1 = driver.findElement(By.xpath("//*[@id=\"slider\"]/span"));
		int half_width = horizontalSlider.getSize().width/2;
		action.dragAndDropBy(miniSlider1, half_width, 0).perform();
		System.out.println("Move the slider in the middle measurement of the main horizontal slider.");
	}

}
