package testcases;

import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InvokeEdge {

	public static void main(String[] args) {
		
		//System.setProperty("webdriver.edge.driver", "C:\\Users\\hello\\Documents\\SELENIUM\\Browser_Executables\\msedgedriver.exe");
		WebDriverManager.edgedriver().setup();
		EdgeDriver driver = new EdgeDriver();
		driver.get("http://way2automation.com/");

	}

}
