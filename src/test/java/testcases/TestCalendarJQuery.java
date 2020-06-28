package testcases;

import java.text.ParseException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCalendarJQuery {
	
	public static void getCurrentDateMonthAndYear() {
		Calendar cal = Calendar.getInstance();
		currentDay = cal.get(Calendar.DAY_OF_MONTH);
		currentMonth = cal.get(Calendar.MONTH) + 1;
		currentYear = cal.get(Calendar.YEAR);
	}
	
	public static void getTargetDateMonthAndYear(String dateString) {
		int firstIndex = dateString.indexOf("/");
		int lastIndex = dateString.lastIndexOf("/");
		
		String day = dateString.substring(0, firstIndex);
		targetDay = Integer.parseInt(day);
		
		String month = dateString.substring(firstIndex + 1, lastIndex);
		targetMonth = Integer.parseInt(month);
		
		String year = dateString.substring(lastIndex + 1, dateString.length());
		targetYear = Integer.parseInt(year);
	}
	
	public static void CalculateHowManyMonthsToJump() {
		if((targetMonth - currentMonth) > 0 ) {
			jumpMonthsBy = (targetMonth - currentMonth);
		} else {
			jumpMonthsBy = (currentMonth - targetMonth);
			increment = false;
		}
	}
	
	//Store initial values in global variables.
	static int targetDay = 0,
			targetMonth = 0,
			targetYear = 0;
	
	static int currentDay = 0,
			currentMonth = 0,
			currentYear = 0;
	
	static int jumpMonthsBy = 0;
	
	static boolean increment = true;
	
	public static String browser = "chrome"; //excel sheet
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, ParseException {
		
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
		
		//Navigate to this site for sample handling of jquery calendar.
		driver.get("http://www.qa.way2automation.com/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Preferred date to be set.
		String dateToSet = "01/08/2020";
		
		//Get current date.
		getCurrentDateMonthAndYear();
		System.out.println(currentDay + "  " + currentMonth + "  " + currentYear);
		
		//Get target date.
		getTargetDateMonthAndYear(dateToSet);
		System.out.println(targetDay + "  " + targetMonth + "  " + targetYear);
		
		//Get Jump Month.
		CalculateHowManyMonthsToJump();
		System.out.println(jumpMonthsBy);
		System.out.println(increment);
	}
	
	

}

