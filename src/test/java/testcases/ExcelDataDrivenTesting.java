package testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDataDrivenTesting {
	
	public static String browser = "chrome"; //excel sheet
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		
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
		
		//Create FileInputStream object pointing towards the excel (make sure that the filepath is within double quotes
		FileInputStream fis = new FileInputStream("C:\\Users\\hello\\Documents\\TestDataExcel.xlsx");
		
		//Create an object of XSSFWorkbook class to take control of the entire excel application.
		XSSFWorkbook workbook = new XSSFWorkbook(fis);	
		
		//Below logic to fetch �TestData� sheet from "TestDataExcel" file.
		
		
		
		/*
		//Pre-conditions | Maximize the browser and apply implicit waits.
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Navigate to this site for sample combo boxes.
		driver.get("https://www.jobserve.com/in/en/Job-Search/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		*/
	}

}
