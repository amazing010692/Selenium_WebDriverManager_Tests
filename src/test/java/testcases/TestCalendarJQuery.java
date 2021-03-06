package testcases;

import java.text.ParseException;
import java.util.Calendar;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		
		//Preferred date to be set.
		String dateToSet = "28/03/2020";
		
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
		
		//Loop condition in clicking the increment and decrement buttons.
		for(int i = 0; i < jumpMonthsBy; i ++) {
			if(increment) {
				driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
			} else {
				driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[1]/span")).click();
			}
			Thread.sleep(1000);
		}
		
		//Clicking the target day.
		WebElement selectTargetDay = driver.findElement(By.linkText(Integer.toString(targetDay)));
		selectTargetDay.click();
		
		//Clear the Date field and enter the desired date.
		fieldDate.clear();
		fieldDate.sendKeys(dateToSet);
		Thread.sleep(2000);
		System.out.println("Successfully entered the target date.");
	}
	
}

