package testcases;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class TestCalendarSample {
 
	static int currentDay = 0, currentMonth = 0, currentYear = 0;
	static int futureDay = 0, futureMonth = 0, futureYear = 0;
	static int moveToMonth = 0, moveToYear = 0;
	static boolean incrementMonth = true, incrementYear = true;
	static int monthIncreaseDecreseBy = 0;
	static WebDriver driver;
 
	public static void main(String[] args) throws InterruptedException, IOException {
 
		String futureDate = "28/03/2017";
 
		captureCurrentDate();
		System.out.println(currentDay + " " + currentMonth + " " + currentYear);
 
		getFutureDate(futureDate);
		System.out.println(futureDay + " " + futureMonth + " " + futureYear);
 
		calculateMonthandYear();
		System.out.println(incrementMonth);
		System.out.println(" No. of Month : " + moveToMonth);
		System.out.println(incrementYear);
		System.out.println(" No. of Year : " + moveToYear);
		System.out.println(monthIncreaseDecreseBy);
 
		
		  if (futureMonth != 0 && futureDay != 0 && futureYear != 0) {
		  
		  browserCalenderClick(); }
		 
 
	}
 
	public static void captureCurrentDate() {
 
		Calendar calender = Calendar.getInstance();
 
		currentDay = calender.get(Calendar.DAY_OF_MONTH);
		currentMonth = calender.get(Calendar.MONTH) + 1;
		currentYear = calender.get(Calendar.YEAR);
 
	}
 
	public static void getFutureDate(String date) {
 
		int firstIndex = date.indexOf("/");
		int lastIndex = date.lastIndexOf("/");
 
		String day = date.substring(0, firstIndex);
 
		String month = date.substring(firstIndex + 1, lastIndex);
 
		if (Integer.parseInt(month) < 0 || Integer.parseInt(month) > 13) {
			System.out.println("Print Valid Month");
		} else {
			futureMonth = Integer.parseInt(month);
		}
 
		String year = date.substring(lastIndex + 1, date.length());
		futureYear = Integer.parseInt(year);
 
		if (futureMonth == 1 || futureMonth == 3 || futureMonth == 5 || futureMonth == 7 || futureMonth == 8
				|| futureMonth == 10 || futureMonth == 12) {
			if (Integer.parseInt(day) >= 32) {
				System.out.println("Enter Correct Day of the month");
			} else {
				futureDay = Integer.parseInt(day);
			}
		} else // for 2nd month
		//
		if (futureMonth == 2) {
			if (futureYear % 4 == 0) {
				if (Integer.parseInt(day) >= 30) {
					System.out.println("Enter Correct Day of the month");
				} else
					futureDay = Integer.parseInt(day);
			} else if (Integer.parseInt(day) >= 29) {
				System.out.println("Enter Correct Day of the month");
			} else
				futureDay = Integer.parseInt(day);
 
		} else if (futureMonth == 4 || futureMonth == 6 || futureMonth == 9 || futureMonth == 11) {
			if (Integer.parseInt(day) >= 31) {
				System.out.println("Enter Correct Day of the month");
			} else {
				futureDay = Integer.parseInt(day);
			}
		}
	}
 
	public static void calculateMonthandYear() {
 
		if ((futureMonth - currentMonth) > 0) {
			moveToMonth = (futureMonth - currentMonth);
			incrementMonth = true;
		} else {
			moveToMonth = (currentMonth - futureMonth);
			incrementMonth = false;
		}
		if ((futureYear - currentYear) > 0) {
			moveToYear = (futureYear - currentYear);
			incrementYear = true;
		} else {
			moveToYear = (currentYear - futureYear);
			incrementYear = false;
		}
		
		int monthCheck = (moveToYear * 12) - moveToMonth;
		if (incrementYear) {
			monthIncreaseDecreseBy = (moveToYear * 12) + moveToMonth;
		} else {
			if (monthCheck > 0) {
				monthIncreaseDecreseBy = monthCheck;
			}else {
				monthIncreaseDecreseBy = Math.abs(monthCheck);
			}
				
		}
	}
 
	public static void browserCalenderClick() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		//Pre-conditions | Maximize the browser and apply implicit waits.
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		//Navigate to this site for sample handling of jquery calendar.
		driver.get("http://www.qa.way2automation.com/");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Fill-out the Name field.
		WebElement fieldName = driver.findElement(By.xpath("//input[@name='name']"));
		fieldName.sendKeys("Michiko Daimon");
		
		//Fill-out the Phone field.
		WebElement fieldPhone = driver.findElement(By.xpath("//input[@name='phone']"));
		fieldPhone.sendKeys("+81 3-6406-1111");
		
		//Fill-out the Email field.
		WebElement fieldEmail = driver.findElement(By.xpath("//input[@name='email']"));
		fieldEmail.sendKeys("michikodaimon@tv-asahi.co.jp");
		
		//Select the country from Country dropdown list.
		WebElement listCountry = driver.findElement(By.xpath("//*[@id=\"load_form\"]/fieldset[4]/select"));
		Select select = new Select(listCountry);
		select.selectByValue("Japan");
		
		//Fill-out the City field.
		WebElement fieldCity = driver.findElement(By.xpath("//input[@name='city']"));
		fieldCity.sendKeys("Tokyo");
		
		//Fill-out the Username field.
		WebElement fieldUsername = driver.findElement(By.xpath("(//input[@name='username'])[2]"));
		fieldUsername.sendKeys("michiko_daimon");
		
		//Fill-out the Password field.
		WebElement fieldPassword = driver.findElement(By.xpath("(//input[@name='password'])[2]"));
		fieldPassword.sendKeys("DoctorX0801");
		
		//Click the Submit button.
		WebElement buttonSubmit = driver.findElement(By.xpath("(//input[@value='Submit'])[2]"));
		buttonSubmit.click();
		
		//Click the Sign-In button.
		WebElement buttonSignIn = driver.findElement(By.xpath("//a[@class='fancybox' and @href='#login']"));
		buttonSignIn.click();
		
		//In the Login form, fill-out the Username field.
		WebElement fieldUsernameLogin = driver.findElement(By.xpath("(//input[@name='username'])[2]"));
		fieldUsernameLogin.sendKeys("michiko_daimon");
		
		//In the Login form, fill-out the Password field.
		WebElement fieldPasswordLogin = driver.findElement(By.xpath("(//input[@name='password'])[2]"));
		fieldPasswordLogin.sendKeys("DoctorX0801");
		
		//Click the Submit button.
		WebElement buttonSubmitLogin = driver.findElement(By.xpath("(//input[@value='Submit'])[2]"));
		buttonSubmitLogin.click();
		
		//Mouse over to Widget Menu and call the Actions class.
		driver.navigate().refresh();
		Actions action = new Actions(driver);
		WebElement menuWidget = driver.findElement(By.xpath("//*[@id=\"toggleNav\"]/li[3]/a"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toggleNav\"]/li[3]/a")));
		action.moveToElement(menuWidget).perform();
		
		//Click the Datepicker option.
		WebElement optionDatepicker = driver.findElement(By.xpath("//a[contains(text(),'Datepicker')]"));
		optionDatepicker.click();
		
		//Switch to the iframe present in the page.
		WebElement framePresent = driver.findElement(By.xpath("//div[@id='example-1-tab-1']//iframe[@class='demo-frame']"));
		driver.switchTo().frame(framePresent);
		
		//Click the Date field.
		WebElement fieldDate = driver.findElement(By.xpath("//input[@id='datepicker']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='datepicker']")));
		fieldDate.click();
 
		//Calender open and date search
		WebElement calenderOpen = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]"));
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]")));
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[1]")));
		//increment and decrement of month
		for (int i = 0; i < monthIncreaseDecreseBy; i++) {
			if (incrementYear) {
				calenderOpen.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]")).click();
				//System.out.println("Increment");
			} else {
				calenderOpen.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[1]")).click();
				//System.out.println("Decrement");
			}
//			Thread.sleep(500);
		}
 
		driver.findElement(By.linkText(Integer.toString(futureDay))).click();
		
		
		
		WebElement captureDate = driver.findElement(By.xpath("/html/body/p/input"));
		File screenshot = captureDate.getScreenshotAs(OutputType.FILE); /*
		System.out.println("The Date is : "+captureDate.getScreenshotAs(OutputType.FILE));
		*/
		
		
		FileUtils.copyFile(screenshot, new File(".//ScreenShot/date.jpg"));
	}
 
}