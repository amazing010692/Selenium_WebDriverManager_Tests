package testcases;

import java.util.Date;

public class TestTimeStamp {

	public static void main(String[] args) {
		
		//Prints the current system date and convert it to string.
		Date d = new Date();
		System.out.println(d.toString().replace(":", "_").replace(" ", "_") + ".jpg");

	}

}
