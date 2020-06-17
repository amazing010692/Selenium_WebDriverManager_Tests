package testcases;

import java.io.IOException;
import java.util.ArrayList;

public class TestSample {

	public static void main(String[] args) throws IOException {
		
		// Create object of ExcelDemo and call it's utility.
		ExcelDemo ed = new ExcelDemo();
		
		//Since getData method returns an array list, so
		 ArrayList<String> data = ed.getdata("TC4");
		 
		 //Now we know that an array stores data in indices, so
		 System.out.println(data.get(0));
		 System.out.println(data.get(1));
		 System.out.println(data.get(2));
		 
		 /*
		  * We can now call this test data that is present in excel into our 
		  * selenium code. So assuming data.get(1) contain username, we can pass it like below:
		  * driver.findElement(By.xpath("//tagName[@attribute='value']")).sendKeys(data.get(1));
		  */
	}

}
