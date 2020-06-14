package testcases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class Calendars_ArticleTest {
	
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
		
		//Navigate to this site for sample calendar.
		driver.get("https://www.goibibo.com/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Click the Departure field.
		WebElement fieldDeparture = driver.findElement(By.xpath("//input[@id='departureCalendar']"));
		fieldDeparture.click();
		
		//Store a predefined date in variable ‘d’. This date is in dd/mm/yyyy format.
		String d = "14/06/2021";
		
		//Create a month array & enter all the months inside it.
		String[] months = {"January", "February", "March", "April", "May", "June", 
				"July", "August", "September", "October", "November", "December"};
		
		/*Create an object of existing java class ‘SimpleDateFormat’. 
		Pass the same dd/mm/yyyy format in the constructor that we have used in our predefined date above*/
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		//Parse the predefined date ‘d’ with the help of parse method. This parse method returns an object of ‘Date’ class.
		Date mydate = df.parse(d);
		
		//Create a calendar instance.
		java.util.Calendar cal = java.util.Calendar.getInstance();
		
		//Set date in calendar using setTime method.
		cal.setTime(mydate);
		
		//Get the day, month and year using ‘get’ method.
		int day = cal.get(java.util.Calendar.DAY_OF_MONTH);
		int month = cal.get(java.util.Calendar.MONTH);
		int year = cal.get(java.util.Calendar.YEAR);
		
		/*Print it in the console. The day is printed as 14.
		Month is printed as numeric 5 which is June (January is represented as 0, Feb as 1, Mar as 2 and so on.
		Year gets printed as 2021.*/
		System.out.println(day);
		//System.out.println(month);
		System.out.println(months[month]);
		System.out.println(year);
		
		//Store the xpath of the forward arrow button in a String variable.
		String xpathForwardArrow = "//span[@class='DayPicker-NavButton DayPicker-NavButton--next']";
		
		//Create a string that is equal to month concatenated by white space concatenated by year.
		String travelMonth = months[month] + " " + year;
		System.out.println(travelMonth);
		
		//Store the xpath of the month-section.
		String xpathMonthYearSection = "//div[@class='fareCalFlt ']/div/div[2]/div";
		
		//Develop a logic that will keep clicking forward arrow > till we get June 2021 calendar
		while(!driver.findElement(By.xpath(xpathMonthYearSection)).getText().equals(travelMonth)) {
			driver.findElement(By.xpath(xpathForwardArrow)).click();
		}
		
		//Select the desired date. This will click day of the month stated in the above d.
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='" + day + "']")).click();
		
		//
	}

}
