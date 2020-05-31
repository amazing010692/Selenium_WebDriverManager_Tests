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
import io.github.bonigarcia.wdm.WebDriverManager;

public class TextCaptcha_03 {
	
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
		
		driver.get("https://timesofindia.indiatimes.com/poll.cms");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Get the equation and store in a String variable, "equation"
		String equation = driver.findElement(By.xpath("//span[@id='mathq2']")).getText();
		
		//Split the equation and store in a String array, "splitVars"
		String[] splitVars = equation.split(" ");
		
		//Store the 1st and 2nd numbers in an integer variable.
		int firstNum = Integer.valueOf(splitVars[0]);
		int secondNum = Integer.valueOf(splitVars[2]);
		
		//Store the operator in a string variable.
		String operator = splitVars[1];
		
		//Switch statement for handling the different operators.
		int answer = 0;		
		switch(operator) {	
		
		case "+":
			answer = firstNum + secondNum;
			break;
		case "-":
			answer = firstNum - secondNum;
			break;
		case "*":
			answer = firstNum * secondNum;
			break;
		case "/":
			answer = firstNum / secondNum;
			break;
		
		}
		
		//Write the answer of the mathematical equation in the box.
		WebElement mathAnswer = driver.findElement(By.xpath("//input[@id='mathuserans2']"));
		mathAnswer.sendKeys(String.valueOf(answer));
		
		//Print out in the console.
		System.out.println(equation + " " + answer);
		
		//Refresh the page.
		driver.navigate().refresh();

	}

}
