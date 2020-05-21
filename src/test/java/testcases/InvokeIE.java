package testcases;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InvokeIE {

	public static void main(String[] args) {
		
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		
		//System.setProperty("webdriver.ie.driver", "C:\\Users\\hello\\Documents\\SELENIUM\\Browser_Executables\\IEDriverServer.exe");
		WebDriverManager.iedriver().setup();
		@SuppressWarnings("deprecation")
		InternetExplorerDriver driver = new InternetExplorerDriver(capabilities);
		driver.get("http://way2automation.com/");		

	}

}
