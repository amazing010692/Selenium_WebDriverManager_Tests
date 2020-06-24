package testcases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestTimeStamp {

	public static void main(String[] args) {
		
		//Prints the current system date and convert it to string.
		Date d = new Date();
		System.out.println(d.toString().replace(":", "_").replace(" ", "_") + ".jpg");
		
		//Get Current Date and Time: java.time.format.DateTimeFormatter
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));  
		
	}

}
