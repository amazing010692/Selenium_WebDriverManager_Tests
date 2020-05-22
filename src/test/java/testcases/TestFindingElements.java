package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestFindingElements {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://gmail.com");
		driver.manage().window().maximize();
		
		WebElement username = driver.findElement(By.id("identifierId"));
		username.sendKeys("tcoptestcebumabolo@gmail.com");
		
		//driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span"));
		nextButton.click();
		
		Thread.sleep(3000);
		driver.close();
	}

}
