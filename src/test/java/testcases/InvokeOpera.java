package testcases;

import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InvokeOpera {

	public static void main(String[] args) {
		
		//System.setProperty("webdriver.opera.driver", "C:\\Users\\hello\\Documents\\SELENIUM\\Browser_Executables\\operadriver.exe");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		OperaOptions options = new OperaOptions();
		options.setBinary("C:\\Users\\hello\\AppData\\Local\\Programs\\Opera\\64.0.3417.73\\opera.exe");
		capabilities.setCapability(OperaOptions.CAPABILITY, options);
		
		WebDriverManager.operadriver().setup();
		OperaDriver driver = new OperaDriver(options);
		driver.get("http://way2automation.com/");

	}

}
